package com.twoways.dao;

import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.DateTime;
import com.google.gdata.data.calendar.CalendarEventEntry;
import com.google.gdata.data.extensions.Reminder;
import com.google.gdata.util.ServiceException;

import com.twoways.service.CalendarGoogleService;
import com.twoways.to.ClientsRatesTO;
import com.twoways.to.ClientsTO;

import com.twoways.to.OrdersDocsTO;
import com.twoways.to.OrdersRatesTO;
import com.twoways.to.OrdersTO;

import com.twoways.to.ServicesTO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.net.MalformedURLException;
import java.net.URL;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;


import java.util.Map;

import java.util.ResourceBundle;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

public class OrdersDAOImpl extends AbstractDAO implements OrdersDAO {
    public OrdersDAOImpl() {
    }


    private void insertFiles(OrdersTO order) throws IOException {

        List<FileItem> files = order.getFiles();

        try {

            for (Object fileObj: files.toArray()) {
                Blob doc = null;
                FileItem file = (FileItem)fileObj;
                //statement= con.createStatement(); 
                OrdersDocsTO ordersDocsTO = new OrdersDocsTO();

                ordersDocsTO.setOdoName(file.getName().substring(file.getName().lastIndexOf("\\") + 
                                                                 1));

                Long odoId = 
                    (Long)getSqlMapClientTemplate().queryForObject("order.seq", 
                                                                   "");
                ordersDocsTO.setOrdersOrdId(order.getOrdId());
                ordersDocsTO.setOdoId(odoId);

                try {
                    byte[] b = new byte[(int)file.getSize()];

                    file.getInputStream().read(b);
                    //out.write(b);
                    ordersDocsTO.setOdoDoc(b);

                } catch (FileNotFoundException e) {
                    System.out.println("File Not Found.");
                    e.printStackTrace();
                }

                getSqlMapClientTemplate().insert("insertOrderDoc", 
                                                 ordersDocsTO);

            }

        } catch (IOException e1) {
            System.out.println("Error Upload The File.");
            e1.printStackTrace();
        }

        catch (Exception e) {

            System.out.println("Error The File.");
            e.printStackTrace();

        }


    }

    public OrdersTO getOrderById(Long ordId) throws Exception {
        OrdersTO ordersTO = 
            (OrdersTO)getSqlMapClientTemplate().queryForObject("getOrderById", 
                                                               ordId);
        ordersTO.setOrderRatesTOList((List<OrdersRatesTO>)getSqlMapClientTemplate().queryForList("getOrderRatesByOrderId",ordersTO) );
        ordersTO.setServicesTOList((List<ServicesTO>)getSqlMapClientTemplate().queryForList("getOrderServicesByOrderId",ordersTO) );
        ordersTO.setOrdersDocsTOList((List<OrdersDocsTO>)getSqlMapClientTemplate().queryForList("getOrderDocByOrder", 
                                                                                                ordId));

        return ordersTO;
    }


    public OrdersDocsTO getOrdersDocById(Long docId) throws Exception {
        OrdersDocsTO ordersDocsTO = 
            (OrdersDocsTO)getSqlMapClientTemplate().queryForObject("getOrderDocById", 
                                                                   docId);

        return ordersDocsTO;
    }


    public OrdersTO updateOrder(OrdersTO ordersTO) throws Exception {

        


        getSqlMapClientTemplate().insert("updateOrders", ordersTO);
        List <OrdersRatesTO> ordRates = ordersTO.getOrderRatesTOList();
        
        // insertar las nuevas 
        
        List<OrdersRatesTO> oldOrdRates = (List<OrdersRatesTO>) getSqlMapClientTemplate().queryForList("getOrderRatesByOrderId",ordersTO); 
         
        if(ordRates ==null){ 
            ordRates= new ArrayList();
        }
        for(OrdersRatesTO ordersRatesTO: ordRates ){
             boolean insertar= true;   
            
            for(OrdersRatesTO oldOrdRate: oldOrdRates ){
               
                 
                if( oldOrdRate.getOrdersTO().getOrdId().equals(ordersRatesTO.getOrdersTO().getOrdId()) && oldOrdRate.getRatesTO().getRatId().equals(ordersRatesTO.getRatesTO().getRatId())  ){
                    insertar=false;
                    break;
                }
                
            }     
             
            if(insertar)
            {
                ordersRatesTO.setOrdersTO(ordersTO);
                getSqlMapClientTemplate().insert("insertOrdersRates",ordersRatesTO);
            }else{
                getSqlMapClientTemplate().insert("updateOrdersRates",ordersRatesTO);
            }  
        }
        
        //borrar las viejas
        for(OrdersRatesTO oldOrdRate: oldOrdRates ){
               boolean borrar= true;   
               for(OrdersRatesTO ordersRatesTO: ordRates ){
               
                   
                   if( oldOrdRate.getOrdersTO().getOrdId().equals(ordersRatesTO.getOrdersTO().getOrdId()) && oldOrdRate.getRatesTO().getRatId().equals(ordersRatesTO.getRatesTO().getRatId())  ){
                       borrar=false;
                       break;
                   }
                   
               }     
                
               if(borrar)
               {
                   getSqlMapClientTemplate().delete("deleteOrdersRates",oldOrdRate);
               }  
           }
           
             
        List <ServicesTO> services = ordersTO.getServicesTOList();
        
        // insertar las nuevas 
        
        List<ServicesTO> oldOrdServices = (List<ServicesTO>) getSqlMapClientTemplate().queryForList("getOrderServicesByOrderId",ordersTO); 
         
        
        for(ServicesTO servicesTO: services ){
             boolean insertar= true;   
            
            for(ServicesTO oldServices: oldOrdServices ){
               
                 
                if( oldServices.getSerId().equals(servicesTO.getSerId())){
                    insertar=false;
                    break;
                }
                
            }     
             
            if(insertar)
            {
                Map insert= new HashMap();
                insert.put("ordId",ordersTO.getOrdId());
                insert.put("serId",servicesTO.getSerId());
                getSqlMapClientTemplate().insert("insertOrdersServices",insert);
            } 
        }
        
        //borrar las viejas
        for(ServicesTO oldServices: oldOrdServices ){
               boolean borrar= true;   
               for(ServicesTO servicesTO: services ){
               
                   
                   if( oldServices.getSerId().equals(servicesTO.getSerId())  ){
                       borrar=false;
                       break;
                   }
                   
               }     
                
               if(borrar)
               {
                   Map insert= new HashMap();
                   insert.put("ordId",ordersTO.getOrdId());
                   insert.put("serId",oldServices.getSerId());
                   getSqlMapClientTemplate().delete("deleteOrdersServices",insert);
                   
                  
               }  
           }
           
            
        List <OrdersDocsTO> docsList = ordersTO.getOrdersDocsTOList();
       
       if (docsList==null){
           docsList = new ArrayList(); 
       }
        List<OrdersDocsTO> documentosOld = (List<OrdersDocsTO>)getSqlMapClientTemplate().queryForList("getOrderDocByOrder", 
                                                                                                ordersTO.getOrdId());
        
        for(OrdersDocsTO oldDocs: documentosOld ){
               boolean borrar= true;   
               for(OrdersDocsTO ordersDocsTO: docsList ){
               
                   
                   if( oldDocs.getOdoName().equals(ordersDocsTO.getOdoName())  ){
                       borrar=false;
                       break;
                   }
                   
               }     
                
               if(borrar)
               {
                   Map insert= new HashMap();
                   insert.put("ordId",ordersTO.getOrdId());
                   insert.put("odoId",oldDocs.getOdoId());
                   getSqlMapClientTemplate().delete("deleteOrdersDocs",insert);
                   
                  
               }  
           }
            
        
        
        insertFiles(ordersTO);

        return getOrderById(ordersTO.getOrdId());


    }
    
    
    public OrdersTO insertarOrder(OrdersTO ordersTO) throws Exception {

        
        Logger log = Logger.getRootLogger(); 
        log.debug("OrdersTO insertarOrder(OrdersTO ordersTO)");
        Long ordId = 
            (Long)getSqlMapClientTemplate().queryForObject("ordersdoc.seq", 
                                                           "");
        ordersTO.setOrdId(ordId);
        ;
        ResourceBundle rb = ResourceBundle.getBundle("twoways");
        String userName = rb.getString("userCalendar");
        String userPassword = rb.getString("userCalendarPassword");
        String cliente = ordersTO.getClientsTO().getCliName();
        

        
        CalendarGoogleService  cgsvc= new CalendarGoogleService(userName);

        getSqlMapClientTemplate().insert("insertOrders", ordersTO);
        
       CalendarService myService = new CalendarService("srv"+userName);
 
       

        try {
        
          log.debug("Antes del login");  
          myService.setUserCredentials(userName, userPassword);
          log.debug("Despues del login");  
          // Demonstrate retrieving a list of the user's calendars.
          log.debug(myService);
          Calendar calendar = new GregorianCalendar();
          calendar.setTimeInMillis(ordersTO.getOrdDate().getTime());
          // Demonstrate creating a single-occurrence event.
          CalendarEventEntry quickAddEvent = cgsvc.createSingleEvent(myService,
              "Id: "+ordersTO.getOrdId()+ " Name: " + ordersTO.getOrdName(),null,calendar);
          log.debug("Successfully created event "
              + quickAddEvent.getTitle().getPlainText());

        } catch (IOException e) {
          // Communications error
          log.error("There was a problem communicating with the service.",e);
          e.printStackTrace();
        } catch (ServiceException e) {
          // Server side error
          log.error("The server had a problem handling your request.",e);
          e.printStackTrace();
        }
        
        if (ordersTO.getOrderRatesTOList() != null) {


            for (Object ordRate: ordersTO.getOrderRatesTOList().toArray()) {

                ((OrdersRatesTO)ordRate).setOrdersTO(ordersTO);
                getSqlMapClientTemplate().insert("insertOrdersRates", 
                                                 (OrdersRatesTO)ordRate);
            }

        }
        
        if (ordersTO.getServicesTOList() != null) {


            for (Object service: ordersTO.getServicesTOList().toArray()) {

               Map insert= new HashMap();
               insert.put("ordId",ordersTO.getOrdId());
               insert.put("serId",((ServicesTO)service).getSerId());
               getSqlMapClientTemplate().insert("insertOrdersServices",insert);
            }

        }
        
        insertFiles(ordersTO);

        return getOrderById(ordersTO.getOrdId());


    }
    
    
    

}
