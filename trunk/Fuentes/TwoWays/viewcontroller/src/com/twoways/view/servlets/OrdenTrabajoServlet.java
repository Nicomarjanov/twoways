package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ClientResponsableTO;
import com.twoways.to.ClientsRatesTO;
import com.twoways.to.ClientsTO;
import com.twoways.to.DocTypes;
import com.twoways.to.OrdersDocsTO;
import com.twoways.to.OrdersRatesTO;
import com.twoways.to.OrdersTO;
import com.twoways.to.RateTypesTO;
import com.twoways.to.RatesTO;


import java.io.File;
import java.io.IOException;
import java.io.FileOutputStream;

import java.io.FileWriter;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
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
import org.apache.commons.fileupload.FileItemFactory;
import org.apache.commons.fileupload.FileUpload;
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
        
        String script = "<script>onloadOrder();</script>";
        
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
        Map docTypesMap = new HashMap();
        
        if(files !=null){
            for(FileItem file : files){
                
                if(mRequest.get(file.getName()) !=null){
                    docTypesMap.put(file.getName(),mRequest.get(file.getName())); 
                }else{
                    //if(){ 
                   
                    String id =   file.getFieldName().substring("file".length());
                    docTypesMap.put(id,mRequest.get(id)); 
                    System.out.println("file.getFieldName() : "+id);
                     //}
                    
                }
            }
        }
        String accion=(mRequest.get("accion")!= null )?mRequest.get("accion").toString():"";
        List<ClientsTO> clientes = null;
        List<RateTypesTO> services = null;
        List  <DocTypes>  docTypes= null;

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
           tarifas = twoWaysBDL.getServiceTwoWays().getRateByType(rateType);
           clientes= twoWaysBDL.getServiceTwoWays().obtenerClientes();         
           services= twoWaysBDL.getServiceTwoWays().obtenerServicios();
           docTypes = twoWaysBDL.getServiceTwoWays().obtenerTipoDocumentos();
           //services = twoWaysBDL.getServiceTwoWays().obtenerTipoTarifas();
            
           request.setAttribute("listaTarifa",tarifas);
           request.setAttribute("listaCliente",clientes);
           request.setAttribute("listaService",services);
           request.setAttribute("listaDocTypes",docTypes);
            String ordId = request.getParameter("ordId");
            OrdersTO order=  new OrdersTO();
            if (ordId != null &&  ordId.length() > 0 ){
                 try{
                 order =twoWaysBDL.getServiceTwoWays().getOrderById(Long.parseLong(ordId) );
                     
             for (RateTypesTO servOrd:order.getServicesTOList()){
                     
                 for(RateTypesTO serv: services){
                                           
                         if(serv.getRtyName().equals(servOrd.getRtyName()) || serv.getRtyName().equalsIgnoreCase("Cliente") ){
                             services.remove(serv);
                             break;
                         }
                     }
                 }
                 
                 request.setAttribute("order",order);
                 }catch(Exception e){
                     e.printStackTrace();
                 }
            }else{
            
                order.setOrdDate(new Timestamp(new Date().getTime()));
                request.setAttribute("order",order);
            }
           
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        if(accion != null && accion.equalsIgnoreCase("buscarResponsables")){
            List <ClientResponsableTO> cliResponsables = null;

            ClientsTO clientsTO = new ClientsTO(); 
            Long auxCliId = Long.parseLong((mRequest.get("listaClientes")!= null )?mRequest.get("listaClientes").toString():"");
            clientsTO.setCliId(auxCliId);
            cliResponsables = twoWaysBDL.getServiceTwoWays().getClientResponsableByCliId(clientsTO);
            if (cliResponsables.size()> 0){
                request.setAttribute("listaRespClientes",cliResponsables);
            }else{
                ClientResponsableTO auxCliRespTO = new ClientResponsableTO();
                auxCliRespTO.setCreFirstName("No existen responsables");
                cliResponsables.add(0,auxCliRespTO);
                request.setAttribute("listaRespClientes",cliResponsables);
            }
            request.setAttribute("auxCliId",auxCliId);
            request.setAttribute("auxOrdName",(mRequest.get("ordName")!= null )?mRequest.get("ordName").toString():"");
            request.setAttribute("auxFinishDate",(mRequest.get("ordFinishDate")!= null )?mRequest.get("ordFinishDate").toString():"");
            request.setAttribute("auxOrdDate",(mRequest.get("ordDate")!= null )?mRequest.get("ordDate").toString():"");
        }
        else if(accion != null && accion.equalsIgnoreCase("guardar")){
            
            OrdersTO  ordersTO = new OrdersTO();
            ClientsTO clientsTO = new ClientsTO();             
            clientsTO.setCliId(Long.parseLong((mRequest.get("listaClientes")!= null )?mRequest.get("listaClientes").toString():""));
            ordersTO.setClientsTO(clientsTO);
            ClientResponsableTO cliResponsableTO = new ClientResponsableTO();
            if (mRequest.get("listaRespClientes")!= null && !mRequest.get("listaRespClientes").toString().equalsIgnoreCase("") ){
                cliResponsableTO.setCreId(Long.parseLong((mRequest.get("listaRespClientes")!= null )?mRequest.get("listaRespClientes").toString():""));
                ordersTO.setCliResponsableTO(cliResponsableTO);
            }
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
            ordersTO.setDocTypesSelected(docTypesMap);
            Object tarifasHidden[]=null;
            Object wordCountHidden[]=null;
            
            if(mRequest.get("tarifas-hidden") != null && mRequest.get("tarifas-hidden") instanceof ArrayList){
                List aux =(ArrayList) mRequest.get("tarifas-hidden");
                tarifasHidden=aux.toArray();
            }else if (mRequest.get("tarifas-hidden")!=null){
                tarifasHidden = new String[1];
                tarifasHidden[0]=mRequest.get("tarifas-hidden").toString();
            }
            
            if(mRequest.get("wordCount-hidden") != null && mRequest.get("wordCount-hidden") instanceof ArrayList){
                List aux =(ArrayList) mRequest.get("wordCount-hidden");
                wordCountHidden=aux.toArray();
            }else if (mRequest.get("wordCount-hidden")!=null){
                wordCountHidden = new String[1];
                wordCountHidden[0]=mRequest.get("wordCount-hidden").toString();
            }
            
            
            if( tarifasHidden  != null){ 
                
                List<OrdersRatesTO> ordersRatesTOList = new   ArrayList<OrdersRatesTO>();
                
                for(Object aux:tarifasHidden){
                    
                    String atribs[]= ((String)aux).split("#");
                    
                    OrdersRatesTO orderRatesTO = new OrdersRatesTO();
                    orderRatesTO.setClrValue(Double.parseDouble(atribs[1].replaceAll(",",".")));
                   if (atribs.length >2 ){
                       orderRatesTO.setOrrWcount(new Long (atribs[2])); 
                   }else{
                       orderRatesTO.setOrrWcount(new Long (0));
                   } 
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
                   
                   try{
                   
                   ordersTO = twoWaysBDL.getServiceTwoWays().updateOrder(ordersTO);  
                   }catch(Exception e){
                       
                          if(e.getMessage().contains("ORA-02292")){
                            script +="<script>alert('El o los documentos no pueden eliminarse de la orden porque ya se encuentran asignados'); " +
                            "window.location.href='/twoways/ordentrabajo?ordId="+ordersTO.getOrdId() +"';"+
                            "</script>" ;
                          }
                   }
                                    
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
        
        request.setAttribute("script",script);
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
                            
                             if (item.getFieldName().startsWith("file") && item.isFormField() ){
                                 File file = new File(TMP_DIR_PATH+"\\"+ item.getFieldName());
                                 
                                 
                                 FileItemFactory fac= new DiskFileItemFactory();
                                 
                                 FileItem fi = fac.createItem(item.getFieldName().replaceFirst("file",""),item.getContentType(),false,item.getFieldName().replaceFirst("file",""));
                                 fi.getOutputStream().write(("Por favor descargue el archivo ["+ fi.getFieldName()+ "] desde nuestro  FTP").getBytes());
                                 item =fi;
                           }
                            
                            
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
