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

import com.twoways.to.RateTypesTO;


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
import java.util.Iterator;
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
        OrdersTO ordersTO = (OrdersTO)getSqlMapClientTemplate().queryForObject("getOrderById", ordId);
        ordersTO.setOrderRatesTOList((List<OrdersRatesTO>)getSqlMapClientTemplate().queryForList("getOrderRatesByOrderId",ordersTO) );
        ordersTO.setServicesTOList((List<RateTypesTO>)getSqlMapClientTemplate().queryForList("getOrderServicesByOrderId",ordersTO) );
        ordersTO.setOrdersDocsTOList((List<OrdersDocsTO>)getSqlMapClientTemplate().queryForList("getOrderDocByOrder", ordId));

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
           
             
        List <RateTypesTO> services = ordersTO.getServicesTOList();
        
        // insertar las nuevas 
        
        List<RateTypesTO> oldOrdServices = (List<RateTypesTO>) getSqlMapClientTemplate().queryForList("getOrderServicesByOrderId",ordersTO); 
         
        
        for(RateTypesTO servicesTO: services ){
             boolean insertar= true;   
            
            for(RateTypesTO oldServices: oldOrdServices ){
               
                 
                if( oldServices.getRtyName().equals(servicesTO.getRtyName())){
                    insertar=false;
                    break;
                }
                
            }     
             
            if(insertar)
            {
                Map insert= new HashMap();
                insert.put("ordId",ordersTO.getOrdId());
                insert.put("serId",servicesTO.getRtyName());
                getSqlMapClientTemplate().insert("insertOrdersServices",insert);
            } 
        }
        
        //borrar las viejas
        for(RateTypesTO oldServices: oldOrdServices ){
               boolean borrar= true;   
               for(RateTypesTO servicesTO: services ){
               
                   
                   if( oldServices.getRtyName().equals(servicesTO.getRtyName())  ){
                       borrar=false;
                       break;
                   }
                   
               }     
                
               if(borrar)
               {
                   Map insert= new HashMap();
                   insert.put("ordId",ordersTO.getOrdId());
                   insert.put("serId",oldServices.getRtyName());
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
        
        /*ResourceBundle rb = ResourceBundle.getBundle("twoways");
        String userName = rb.getString("userCalendar");
        String userPassword = rb.getString("userCalendarPassword");
        String cliente = ordersTO.getClientsTO().getCliName();
        

        
        CalendarGoogleService  cgsvc= new CalendarGoogleService(userName);
        */ 
        getSqlMapClientTemplate().insert("insertOrders", ordersTO);
        
        //CalendarService myService = new CalendarService("srv"+userName);
 
       
/*
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
              "Id: "+ordersTO.getOrdId()+ " Name: " + ordersTO.getOrdName(),"Id: "+ordersTO.getOrdId()+ " Name: " + ordersTO.getOrdName(),calendar);
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
  */      
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
               insert.put("serId",((RateTypesTO)service).getRtyName());
               getSqlMapClientTemplate().insert("insertOrdersServices",insert);
            }

        }
        
        insertFiles(ordersTO);

        return getOrderById(ordersTO.getOrdId());


    }
    
    public List <OrdersTO> findOrders(Map orderParameters) {
        
       List <OrdersTO> results = new ArrayList<OrdersTO>();
       DataSource ds = this.getDataSource(); 
       Connection con = null;
       Statement stm = null;
       ResultSet rs= null ;
       String query =  " select * " + 
                       " from orders o, clients c " + 
                       " where upper(o.ord_name) like upper('%#ordName#%') " + 
                       " and upper(decode(o.ord_Proj_Id, null , ' ',o.ord_Proj_Id)) like  upper('%#ordProjId#%')"+
                       " and o.clients_cli_id = c.cli_Id " + 
                       " and c.cli_id = decode(#cliId#,0,c.cli_id,#cliId#) " ;
       
       if(orderParameters.get("ordDate") != null && orderParameters.get("ordDate").toString().length() > 0){
       
          String formato = "dd/MM/yyyy hh24:mi";
          if (orderParameters.get("ordDate").toString().length() == 10){
              if(!orderParameters.get("ordDateOpt").toString().equals("=")){ 
                  formato = "dd/MM/yyyy";
                  query += " and o.ord_date "+ orderParameters.get("ordDateOpt").toString()+"  to_date ('#ordDate#','"+formato+"')";
              }else{
                  query += " and o.ord_date >= to_date ('#ordDate# 00:00','"+formato+"') and  o.ord_date <= to_date ('#ordDate# 23:59','"+formato+"') ";
              }
          }else{        
            query += " and o.ord_date "+ orderParameters.get("ordDateOpt").toString()+"  to_date ('#ordDate#','"+formato+"')";
          }  
       }
       
        if(orderParameters.get("ordFinishDate") != null && orderParameters.get("ordFinishDate").toString().length() > 0){
        
           String formato = "dd/MM/yyyy hh24:mi";
           if (orderParameters.get("ordFinishDate").toString().length() == 10){
               if(!orderParameters.get("ordFinishDateOpt").toString().equals("=")){ 
                   formato = "dd/MM/yyyy";
                   query += " and o.ord_finish_date "+ orderParameters.get("ordFinishDateOpt").toString()+"  to_date ('#ordFinishDate#','"+formato+"')";
               }else{
                   query += " and o.ord_finish_date >= to_date ('#ordFinishDate# 00:00','"+formato+"') and  o.ord_date <= to_date ('#ordFinishDate# 23:59','"+formato+"') ";
               }
           }else{        
             query += " and o.ord_finish_date "+ orderParameters.get("ordFinishDateOpt").toString()+"  to_date ('#ordFinishDate#','"+formato+"')";
           }  
        }
      
      
       
       for (Iterator i = orderParameters.keySet().iterator();i.hasNext();){
           String param = (String)i.next();
           query = query.replaceAll("#"+param+"#",orderParameters.get(param).toString());
       }
       
       query+= " order by o.ord_date" ;
        try {
            con = ds.getConnection();
            stm = con.createStatement();
            System.out.println(query);
            System.out.println(query);
            rs = stm.executeQuery(query);
            
            while(rs.next()){
                OrdersTO order= new OrdersTO();
                order.setOrdId(rs.getLong("ord_id"));
                order.setOrdName(rs.getString("ord_name"));
                order.setOrdProjId(rs.getString("ord_Proj_Id"));
                ClientsTO client = new ClientsTO();
                client.setCliId(rs.getLong("cli_id"));
                client.setCliName(rs.getString("cli_name"));
                order.setClientsTO(client);
                if(rs.getTime("ord_finish_date") !=null ){ 
                   
                        
                       java.sql.Timestamp timest = rs.getTimestamp("ord_finish_date"); 
                        order.setOrdFinishDate(timest);
                        
                    
                }
                if(rs.getTime("ord_date") !=null ){ 
                   
                       java.sql.Timestamp timest = rs.getTimestamp("ord_date"); 
                        order.setOrdDate(timest);
                        
                   
                }
                
                results.add(order);
            }
            
        } catch (SQLException e) {
             e.printStackTrace();
        }finally{
            try {
            rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try{
            stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try{
            con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            
            
        }
        
      
        return results;
    }


}
