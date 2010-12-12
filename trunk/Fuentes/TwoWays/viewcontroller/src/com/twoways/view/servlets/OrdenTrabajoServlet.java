package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ClientsTO;
import com.twoways.to.OrdersTO;
import com.twoways.to.RateTypesTO;
import com.twoways.to.RatesTO;
import com.twoways.to.ServicesTO;

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


public class OrdenTrabajoServlet extends AutorizacionServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    private static final String TMP_DIR_PATH = "C:\\WINDOWS\\Temp";
    private File tmpDir;

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        List roles= new ArrayList();
        roles.add("Administrador");
        this.setRolesValidos(roles);
        
     
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
        List<ServicesTO> services = null;
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
           services= twoWaysBDL.getServiceTwoWays().obtenerServicios();
           request.setAttribute("listaTarifa",tarifas);
           request.setAttribute("listaCliente",clientes);
           request.setAttribute("listaService",services);
            String ordId = request.getParameter("ordId");
            OrdersTO order=  null;
            if (ordId != null &&  ordId.length() > 0 ){
                 try{
                 order =twoWaysBDL.getServiceTwoWays().getOrderById(Long.parseLong(ordId) );
                 request.setAttribute("order",order);
                 }catch(Exception e){
                     e.getMessage();
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
            if(mRequest.get("ordDate")!= null && !mRequest.get("ordDate").toString().equalsIgnoreCase("") ){ 
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                java.util.Date date = sdf.parse(mRequest.get("ordDate").toString());
                java.sql.Timestamp timest = new java.sql.Timestamp(date.getTime()); 
                ordersTO.setOrdDate(timest);
            }
            
            } catch (Exception e) {
                request.setAttribute("mensaje","<script>alert('La fecha ingresada no es valida')</script>"); 
                e.printStackTrace();
                request.getRequestDispatcher("ordentrabajo.jsp").forward(request,response);
            }
            
            ordersTO.setOrdJobId((mRequest.get("ordJobId")!= null )?mRequest.get("ordJobId").toString():"");    
            ordersTO.setOrdJobDescription((mRequest.get("ordJobDescription")!= null )?mRequest.get("ordJobDescription").toString():"");    
            ordersTO.setOrdDescription((mRequest.get("ordDescription")!= null )?mRequest.get("ordDescription").toString():"");    
            ordersTO.setOrdWoNumber(Long.parseLong(((mRequest.get("ordWoNumber")!= null  && mRequest.get("ordWoNumber").toString().length() > 0 )?mRequest.get("ordWoNumber").toString():"0")));    
            ordersTO.setOrdJobName((mRequest.get("ordJobName")!= null )?mRequest.get("ordJobName").toString():""); 
            ordersTO.setOrdName((mRequest.get("ordName")!= null )?mRequest.get("ordName").toString():"");
            ordersTO.setFiles(files);
            

            try {
                
               ordersTO = twoWaysBDL.getServiceTwoWays().insertarOrder(ordersTO);
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
                                 parametros.put(item.getFieldName(),item.getString());
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
