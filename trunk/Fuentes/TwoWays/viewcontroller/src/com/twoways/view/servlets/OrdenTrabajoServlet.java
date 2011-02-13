package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ClientsRatesTO;
import com.twoways.to.ClientsTO;
import com.twoways.to.OrdersDocsTO;
import com.twoways.to.OrdersRatesTO;
import com.twoways.to.OrdersTO;
import com.twoways.to.RateTypesTO;
import com.twoways.to.RatesTO;


import java.io.File;
import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.log4j.Logger;


public class OrdenTrabajoServlet extends AutorizacionServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    private static final String TMP_DIR_PATH = "C:\\WINDOWS\\Temp";
    private File tmpDir;
    private static  Logger log;   

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log = Logger.getRootLogger();
        List roles= new ArrayList();
        roles.add("Administrador");
        this.setRolesValidos(roles);
        log.info("Inicia OrdenTrabajoServlet");
     
        tmpDir = new File(TMP_DIR_PATH);
        if(!tmpDir.isDirectory()) {
                throw new ServletException(TMP_DIR_PATH + " is not a directory");
        }
        
    }
    
    
    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, IOException {
       
        super.doGet(request,response);
        Map pRequest =  new HashMap();
        Map mRequest =  new HashMap();
        
        
        
       System.out.println(response.getContentType()); 
        
        for(Enumeration obj = request.getHeaderNames(); obj.hasMoreElements()  ;  ){
          String key = obj.nextElement().toString();
          System.out.println(key +" " +request.getHeader(key).toString());
          
        }
        
    
        
        if( request.getHeader("CONTENT-TYPE")!= null &&  request.getHeader("CONTENT-TYPE").startsWith("multipart/form-data") ){ 
             pRequest =  parseRequest(request);
             mRequest = (HashMap)pRequest.get("parameters");
             
        }

        
        
        List<FileItem> files= (List<FileItem>) pRequest.get("Files");
        String accion=(mRequest.get("accion")!= null )?mRequest.get("accion").toString():"";
        List<ClientsTO> clientes = null;
        List<RateTypesTO> services = null;
       /* ClientsTO cliente = new Clients
        * TO(); 
        String cliId = request.getParameter("cliId"); 
*/
        List<RatesTO> tarifas = null;
        
        TwoWaysBDL twoWaysBDL=null;
        
        try {
           twoWaysBDL = new TwoWaysBDL();
           RateTypesTO rateType = new RateTypesTO();
           rateType.setRtyName("Cliente");
           tarifas =  twoWaysBDL.getServiceTwoWays().getRateByType(rateType);
           clientes= twoWaysBDL.getServiceTwoWays().obtenerClientes();
           //services= twoWaysBDL.getServiceTwoWays().obtenerServicios();
           services = twoWaysBDL.getServiceTwoWays().obtenerTipoTarifas();
            
           request.setAttribute("listaTarifa",tarifas);
           request.setAttribute("listaCliente",clientes);
           request.setAttribute("listaService",services);
            String ordId = request.getParameter("ordId");
            OrdersTO order=  null;
            if (ordId != null &&  ordId.length() > 0 ){
                 try{
                 order =twoWaysBDL.getServiceTwoWays().getOrderById(Long.parseLong(ordId) );
                     
             for (RateTypesTO servOrd:order.getServicesTOList()){
                     
                 for(RateTypesTO serv: services){
                     
                       
                         if(serv.getRtyName().equals(servOrd.getRtyName())){
                             services.remove(serv);
                             break;
                         }
                     }
                 }
                 
                 request.setAttribute("order",order);
                 }catch(Exception e){
                     e.printStackTrace();
                 }
            }
           
           
           
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        if(accion != null && accion.equalsIgnoreCase("guardar")){
            
            OrdersTO  ordersTO = new OrdersTO();
            ClientsTO clientsTO = new ClientsTO(); 
            clientsTO.setCliId(Long.parseLong((mRequest.get("listaClientes")!= null )?mRequest.get("listaClientes").toString():""));
            ordersTO.setClientsTO(clientsTO);
            
            try {
            
                SimpleDateFormat sdfc = new SimpleDateFormat("dd/MM/yyyy");
                SimpleDateFormat sdfl = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                
                if(mRequest.get("ordDate")!= null && !mRequest.get("ordDate").toString().equalsIgnoreCase("") ){ 
                    java.util.Date date ;
                    if(mRequest.get("ordDate").toString().length() == 10 ){ 
                       date = sdfc.parse(mRequest.get("ordDate").toString());
                    }else{
                       date = sdfl.parse(mRequest.get("ordDate").toString());  
                    }
                    java.sql.Timestamp timest = new java.sql.Timestamp(date.getTime()); 
                    ordersTO.setOrdDate(timest);
                }
                
                if(mRequest.get("ordFinishDate")!= null && !mRequest.get("ordFinishDate").toString().equalsIgnoreCase("") ){ 
                    java.util.Date date ;
                    if(mRequest.get("ordFinishDate").toString().length() == 10 ){ 
                       date = sdfc.parse(mRequest.get("ordFinishDate").toString());
                    }else{
                       date = sdfl.parse(mRequest.get("ordFinishDate").toString());  
                    }
                    java.sql.Timestamp timest = new java.sql.Timestamp(date.getTime()); 
                    ordersTO.setOrdFinishDate(timest);
                }
                
            
            } catch (Exception e) {
                request.setAttribute("mensaje","<script>alert('La fecha ingresada no es valida')</script>"); 
                e.printStackTrace();
                request.getRequestDispatcher("ordentrabajo.jsp").forward(request,response);
            }
            
            ordersTO.setOrdProjId((mRequest.get("ordProjId")!= null )?mRequest.get("ordProjId").toString():"");    
            ordersTO.setOrdJobId((mRequest.get("ordJobId")!= null )?mRequest.get("ordJobId").toString():"");    
            ordersTO.setOrdJobDescription((mRequest.get("ordJobDescription")!= null )?mRequest.get("ordJobDescription").toString():"");    
            ordersTO.setOrdDescription((mRequest.get("ordDescription")!= null )?mRequest.get("ordDescription").toString():"");    
            ordersTO.setOrdWoNumber(Long.parseLong(((mRequest.get("ordWoNumber")!= null  && mRequest.get("ordWoNumber").toString().length() > 0 )?mRequest.get("ordWoNumber").toString():"0")));    
            ordersTO.setOrdJobName((mRequest.get("ordJobName")!= null )?mRequest.get("ordJobName").toString():""); 
            ordersTO.setOrdName((mRequest.get("ordName")!= null )?mRequest.get("ordName").toString():"");
            ordersTO.setFiles(files);
            Object tarifasHidden[]=null;
            
            if(mRequest.get("tarifas-hidden") != null && mRequest.get("tarifas-hidden") instanceof ArrayList){
                List aux =(ArrayList) mRequest.get("tarifas-hidden");
                tarifasHidden=aux.toArray();
            }else if (mRequest.get("tarifas-hidden")!=null){
                tarifasHidden = new String[1];
                tarifasHidden[0]=mRequest.get("tarifas-hidden").toString();
            }
            
                 
            if( tarifasHidden  != null){ 
                
                List<OrdersRatesTO> ordersRatesTOList = new   ArrayList<OrdersRatesTO>();
                
                for(Object aux:tarifasHidden){
                    
                    String atribs[]= ((String)aux).split("#");
                    
                    OrdersRatesTO orderRatesTO = new OrdersRatesTO();
                    orderRatesTO.setClrValue(Double.parseDouble(atribs[1].replaceAll(",",".")));
                    RatesTO rtTO= new RatesTO();
                    rtTO.setRatId(Long.parseLong(atribs[0]));
                    orderRatesTO.setRatesTO(rtTO );
                    ordersRatesTOList.add(orderRatesTO);
                    orderRatesTO.setOrdersTO(ordersTO);
                }
                
                ordersTO.setOrderRatesTOList(ordersRatesTOList);
            }        
            
            Object listaItemsSelect[]={" "};
            
            if(mRequest.get("listaItemsSelect") != null && mRequest.get("listaItemsSelect") instanceof ArrayList){
                List aux =(ArrayList) mRequest.get("listaItemsSelect");
                listaItemsSelect=aux.toArray();
            }else if (mRequest.get("listaItemsSelect")!=null){
                listaItemsSelect = new String[1];
                listaItemsSelect[0]=mRequest.get("listaItemsSelect").toString();
            }
            
                 
            if( listaItemsSelect  != null){ 
                
                List<RateTypesTO> ordersServices = new   ArrayList<RateTypesTO>();
                
                for(Object aux:listaItemsSelect){
                                       
                    RateTypesTO serviceTO = new RateTypesTO();
                    serviceTO.setRtyName(aux.toString());
                    if(aux.toString().trim().length() > 0){ 
                       ordersServices.add(serviceTO);
                    }
                    
                }
                
                
               
                
                
                ordersTO.setServicesTOList(ordersServices);
            }
            
            
            Object exoList[]= {" "};
            
            if(mRequest.get("exdoc") != null && mRequest.get("exdoc") instanceof ArrayList){
                List aux =(ArrayList) mRequest.get("exdoc");
                exoList=aux.toArray();
            }else if (mRequest.get("exdoc")!=null){
                exoList[0]=mRequest.get("exdoc").toString();
            }
            
            

            try {
              
               if (mRequest.get("ordId")  != null &&  mRequest.get("ordId").toString().length() > 0 ){
                  OrdersTO orderOld = twoWaysBDL.getServiceTwoWays().getOrderById(Long.parseLong(mRequest.get("ordId").toString()));
                  ordersTO.setOrdId(Long.parseLong(mRequest.get("ordId").toString() ));
                  
                  boolean eliminar=true;
                  List <OrdersDocsTO> auxListDoc = new ArrayList <OrdersDocsTO>();
                  
                  for(OrdersDocsTO docOld : orderOld.getOrdersDocsTOList()){ 
                          for(Object  docPage:exoList){
                             eliminar = true;
                              if(docPage.toString().equals(docOld.getOdoName())){
                                  eliminar= false;
                                  break;
                              }
                          }
                          
                          if(!eliminar){
                              auxListDoc.add(docOld); 
                          }
                   }
                   
                   ordersTO.setOrdersDocsTOList(auxListDoc);
                   
                   ordersTO = twoWaysBDL.getServiceTwoWays().updateOrder(ordersTO);  
                  
                  
               }else{
                  ordersTO = twoWaysBDL.getServiceTwoWays().insertarOrder(ordersTO);
               } for (RateTypesTO servOrd:ordersTO.getServicesTOList()){
                
                for(RateTypesTO serv: services){
                
                  
                    if(serv.getRtyName().equals(servOrd.getRtyName())){
                        services.remove(serv);
                        break;
                    }
                }
                }
               
               request.setAttribute("order",ordersTO );
                
            } catch (Exception e) {
              e.printStackTrace();
            }
        }

        request.getRequestDispatcher("ordentrabajo.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, 
                       HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
       
       doGet(request,response);
    }
    
    
    protected Map parseRequest(HttpServletRequest request) throws ServletException, IOException {
       
        DiskFileItemFactory  fileItemFactory = new DiskFileItemFactory ();
        List<FileItem> listItem = new ArrayList<FileItem>();
        Map resultado = new HashMap(); 
        Map parametros = new  HashMap(); 
            /*
             *Set the size threshold, above which content will be stored on disk.
             */
            fileItemFactory.setSizeThreshold(1*1024*1024); //1 MB
            /*
             * Set the temporary directory to store the uploaded files of size above threshold.
             */
            fileItemFactory.setRepository(tmpDir);
    
            ServletFileUpload uploadHandler = new ServletFileUpload(fileItemFactory);
            try {
                    /*
                     * Parse the request
                     */
                    List items = uploadHandler.parseRequest(request);
                    Iterator itr = items.iterator();
                    while(itr.hasNext()) {
                            FileItem item = (FileItem) itr.next();
                            /*
                             * Handle Form Fields.
                             */
                            
                            if(item.isFormField()) {
                            
                                 if (parametros.get(item.getFieldName()) ==null){
                                    parametros.put(item.getFieldName(),item.getString());
                                 }else{
                                    Object obj = parametros.get(item.getFieldName());
                                    if(obj instanceof  ArrayList ){
                                        ((ArrayList)obj).add(item.getString());
                                    }else{
                                        List aux = new ArrayList();
                                        aux.add(obj);
                                        aux.add(item.getString());
                                        parametros.put(item.getFieldName(),aux);
                                    }
                                 }
                                 
                            }else{
                              //Handle Uploaded files.
                                    System.out.println("Field Name = "+item.getFieldName()+
                                            ", File Name = "+item.getName()+
                                            ", Content type = "+item.getContentType()+
                                            ", File Size = "+item.getSize());
                                listItem.add(item);
                                    
                            }
                            
                    }
            }catch(FileUploadException ex) {
                    System.out.println("Error encountered while parsing the request");
                    System.out.println("Error encountered while uploading file");
                    ex.printStackTrace();
            }
            resultado.put("Files",listItem );  
            resultado.put("parameters",parametros); 
            return resultado;
    
    }
    
    
    
    
 
}
