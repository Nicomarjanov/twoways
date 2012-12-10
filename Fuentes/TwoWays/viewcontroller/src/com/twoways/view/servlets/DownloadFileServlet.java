package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.OrdersDocsTO;
import com.twoways.to.ProjectsTO;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class DownloadFileServlet extends AutorizacionServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, IOException {
                      
    
        TwoWaysBDL twoWaysBDL = null;
    
        String docType =  request.getParameter("docType");
        String docId =  request.getParameter("docId");
        
        String nomArchivo=null;
        byte[] datos = null;
        Map auxMap = null;

        ServletOutputStream ouputStream = response.getOutputStream();

        if (docType.equalsIgnoreCase("orderDoc")){
                       
            try {
           
                twoWaysBDL = new TwoWaysBDL();
                OrdersDocsTO doc =  twoWaysBDL.getServiceTwoWays().getOrdersDocById(Long.parseLong(docId) );
                nomArchivo= doc.getOdoName().toString();                                   
                datos = doc.getOdoDoc();
                
                ouputStream.write(datos);
                
                }catch(Exception e){
                    System.out.println(e);
                    e.printStackTrace();
                }
        }
        else if(docType.equalsIgnoreCase("gastosDoc")) {
        try{
            twoWaysBDL = new TwoWaysBDL();
            List itmExpDate = null;
            String anioId =  request.getParameter("anioId");
            String mesId =  request.getParameter("mesId");
            itmExpDate =  twoWaysBDL.getServiceTwoWays().getItemsExpenseByDate(mesId,anioId);
            
            //String itmGastosHidden[]=request.getParameterValues("item-gasto-hidden");
            StringBuffer writer = new StringBuffer();
            nomArchivo=docId;
            auxMap = new HashMap();
            
            if (itmExpDate.size() > 0){
                writer.append("Fecha");
                writer.append(',');
                writer.append("Item");
                writer.append(',');
                writer.append("Moneda");
                writer.append(',');
                writer.append("Monto");
                writer.append(',');      
                writer.append("Cuenta");
                writer.append(',');   
                writer.append("Comentario");
                writer.append(',');      
                writer.append("Responsable");                
                writer.append(','); 
                writer.append('\n');
            
                for (Iterator i = itmExpDate.iterator();i.hasNext();){
                    auxMap =(HashMap)i.next();
                /*for (String aux:itmExpDate){
                
                    String atribs[]= aux.split("#");
                    */
                    writer.append((auxMap.get("ITE_DATE") != null && auxMap.get("ITE_DATE").toString().length() > 0)?auxMap.get("ITE_DATE").toString():"");
                    writer.append(',');
                    writer.append((auxMap.get("ITM_NAME") != null && auxMap.get("ITM_NAME").toString().length() > 0)?auxMap.get("ITM_NAME").toString():"");
                    writer.append(',');
                    writer.append((auxMap.get("CUR_NAME") != null && auxMap.get("CUR_NAME").toString().length() > 0)?auxMap.get("CUR_NAME").toString():"");
                    writer.append(',');
                    writer.append((auxMap.get("ITE_VALUE") != null && auxMap.get("ITE_VALUE").toString().length() > 0 )?auxMap.get("ITE_VALUE").toString():"");
                    writer.append(',');      
                    writer.append((auxMap.get("ACC_NAME") != null && auxMap.get("ACC_NAME").toString().length() > 0)?auxMap.get("ACC_NAME").toString():"");
                    writer.append(',');   
                    writer.append((auxMap.get("ITE_COMMENT") != null && auxMap.get("ITE_COMMENT").toString().length() > 0)?auxMap.get("ITE_COMMENT").toString():"");
                    writer.append(',');      
                    writer.append((auxMap.get("USR_ID") != null && auxMap.get("USR_ID").toString().length() > 0)?auxMap.get("USR_ID").toString():"");                
                    writer.append(','); 
                    writer.append('\n');
                }
                ouputStream.write(writer.toString().getBytes());
               /* writer.flush();
                writer.close();*/
            }
            }catch(Exception e){
                System.out.println(e);
                e.printStackTrace();
            }
        }
        else {
        
            Map params= new  HashMap(); 
            auxMap = new HashMap();
            StringBuffer writer = new StringBuffer();         
            nomArchivo=docId;
            java.util.Date date;            
            SimpleDateFormat sdfch = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            SimpleDateFormat sdfsh = new SimpleDateFormat("dd/MM/yyyy");            
            
            String listaClientes =  request.getParameter("listaClientes");
            if(request.getParameter("listaClientes") != null && request.getParameter("listaClientes").length() > 0  ){            
              long cliId= Long.parseLong(request.getParameter("listaClientes"));
              params.put("cliId",cliId);
            }
            String projName =  request.getParameter("projName");  
            if (request.getParameter("projName") != null && request.getParameter("projName").length() > 0) {
                params.put("projName",request.getParameter("projName"));
            }
            if (request.getParameter("ordName") != null && request.getParameter("ordName").length() > 0) {
                params.put("ordName",request.getParameter("ordName"));
            }
            
            if (request.getParameter("Iniciado") != null && request.getParameter("Iniciado").length() > 0) {
                params.put("Iniciado",request.getParameter("Iniciado"));
            }
            if (request.getParameter("Finalizado") != null && request.getParameter("Finalizado").length() > 0) {
                params.put("Finalizado",request.getParameter("Finalizado"));
            }
            if (request.getParameter("POEnviado") != null && request.getParameter("POEnviado").length() > 0) {
                params.put("POEnviado",request.getParameter("POEnviado"));
            }
            
            if(request.getParameter("projFinishDate") !=null &&( request.getParameter("projFinishDate").length() == 10 || request.getParameter("projFinishDate").length() == 16)){ 
                try {
                    
                    date = (request.getParameter("projFinishDate").length() ==10 )?sdfsh.parse(request.getParameter("projFinishDate")):sdfch.parse(request.getParameter("projFinishDate"));
                    params.put("projFinishDate",request.getParameter("projFinishDate"));
                    params.put("projFinishDateOpt",request.getParameter("projFinishDateOpt"));
               } catch (ParseException e) {
                   e.printStackTrace();
                }
            }  
            if(request.getParameter("projDate") !=null &&( request.getParameter("projDate").length() == 10 || request.getParameter("projDate").length() == 16)){ 
                 try {
                     date = (request.getParameter("projDate").length() ==10 )?sdfsh.parse(request.getParameter("projDate")):sdfch.parse(request.getParameter("projDate"));                    
                     params.put("projDate",request.getParameter("projDate"));
                     params.put("projDateOpt",request.getParameter("projDateOpt"));
                 } catch (ParseException e) {
                     e.printStackTrace();
                 }
             }
            try{
               twoWaysBDL = new TwoWaysBDL();
               List<ProjectsTO> projects =  twoWaysBDL.getServiceTwoWays().findProjects(params);
               if (projects.size()> 0){
                   writer.append("Nombre del proyecto");
                   writer.append(',');
                   writer.append("Cantidad de palabras");
                   writer.append(',');
                   writer.append("Fecha y hora de entrega");
                   writer.append(',');
                   writer.append('\n');
                                
               
                   for(ProjectsTO p : projects){
    
                        writer.append((p.getProName().toString() != null && p.getProName().toString().length() > 0)?p.getProName().toString():"");
                        writer.append(',');
                        Long palabras = twoWaysBDL.getServiceTwoWays().getTotalPalabrasxProyecto(p.getProId());
                        writer.append((palabras.toString() != null && palabras.toString().length() > 0)?palabras.toString():"");                                       
                        writer.append(',');
                        writer.append((p.getProFinishDate().toString() != null && p.getProFinishDate().toString().length() > 0)?p.getProFinishDate().toString():"");                     
                        writer.append(',');
                        writer.append('\n');
                    }
                    ouputStream.write(writer.toString().getBytes());
               }


            }catch(Exception e){
               e.printStackTrace(); 
            }                        
        }
        
        try {
                response.setContentType("application/octet-stream");
                response.setHeader("Content-Disposition","attachment;filename="+nomArchivo);
                
                ouputStream.flush();
                ouputStream.close();
           
            }catch(Exception e){
                System.out.println(e);
                e.printStackTrace();
            }
        
       
          
    }
    
  

    public void doPost(HttpServletRequest request, 
                       HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
       doGet(request,response); 
    }
}
