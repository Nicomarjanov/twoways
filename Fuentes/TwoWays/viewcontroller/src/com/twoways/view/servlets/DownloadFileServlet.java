package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.EmployeesTO;
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
        else if(docType.equalsIgnoreCase("listaEmpleadosEnProyectosDoc")) {
            try{
                twoWaysBDL = new TwoWaysBDL();
                Map params= new  HashMap();
                
                if (request.getParameter("empFirstName") != null && request.getParameter("empFirstName").length() > 0) {
                    params.put("empFirstName",request.getParameter("empFirstName")); 
                    request.setAttribute("empFirstName",request.getParameter("empFirstName")) ;
                }
                if (request.getParameter("empLastName") != null && request.getParameter("empLastName").length() > 0) {
                    params.put("empLastName",request.getParameter("empLastName")); 
                    request.setAttribute("empLastName",request.getParameter("empLastName"));
                }
                if (request.getParameter("ProName") != null && request.getParameter("ProName").length() > 0) {
                    params.put("ProName",request.getParameter("ProName"));
                    request.setAttribute("proName",request.getParameter("ProName"));
                }
                if (request.getParameter("Traductor") != null && request.getParameter("Traductor").length() > 0) {
                    params.put("Traductor",request.getParameter("Traductor"));
                    request.setAttribute("Traductor",request.getParameter("Traductor"));
                }
                if (request.getParameter("Editor") != null) {
                    params.put("Editor",request.getParameter("Editor"));
                    request.setAttribute("Editor",request.getParameter("Editor"));
                }
                if (request.getParameter("Revisor") != null) {
                    params.put("Revisor",request.getParameter("Revisor")+" Final");
                    request.setAttribute("Revisor",request.getParameter("Revisor"));
                }
                if (request.getParameter("Maquetador") != null) {
                    params.put("Maquetador",request.getParameter("Maquetador"));
                    request.setAttribute("Maquetador",request.getParameter("Maquetador"));
                }
                if (request.getParameter("PDTP") != null) {
                    params.put("PDTP",request.getParameter("PDTP"));
                    request.setAttribute("PDTP",request.getParameter("PDTP"));
                }
                if (request.getParameter("Proofer") != null) {
                    params.put("Proofer",request.getParameter("Proofer"));
                    request.setAttribute("Proofer",request.getParameter("Proofer"));
                }
                
                List<EmployeesTO> empleados =  twoWaysBDL.getServiceTwoWays().findEmployees(params);
                
                //String itmGastosHidden[]=request.getParameterValues("item-gasto-hidden");
                StringBuffer writer = new StringBuffer();
                nomArchivo=docId;                
                
                if (empleados.size() > 0){
                    writer.append("Nombre");
                    writer.append(',');
                    writer.append("Apellido");
                    writer.append(',');
                    writer.append("Especialidad");
                    writer.append(',');
                    writer.append("Estado asignacion");
                    writer.append(',');      
                    writer.append("Inicio asignacion");
                    writer.append(',');   
                    writer.append("Fin asignacion");
                    writer.append(',');      
                    writer.append("Proyecto");          
                    writer.append(',');      
                    writer.append("Estado proyecto");
                    writer.append(',');      
                    writer.append("Fin proyecto");                    
                    writer.append(','); 
                    writer.append('\n');
                
                    for (EmployeesTO e:empleados){
                     
                        writer.append((e.getEmpFirstName() != null && e.getEmpFirstName().length() > 0)?e.getEmpFirstName().toString():"");
                        writer.append(',');
                        writer.append((e.getEmpLastName() != null && e.getEmpLastName().length() > 0)?e.getEmpLastName().toString():"");
                        writer.append(',');
                        writer.append((e.getEmployeeTypeTO().getEtyName()!= null && e.getEmployeeTypeTO().getEtyName().length() > 0)?e.getEmployeeTypeTO().getEtyName().toString():"");
                        writer.append(',');
                        writer.append((e.getProjectAssignmentsTO().getStatesTO().getStaName() != null && e.getProjectAssignmentsTO().getStatesTO().getStaName().length() > 0 )?e.getProjectAssignmentsTO().getStatesTO().getStaName().toString():"");
                        writer.append(',');      
                        writer.append((e.getProjectAssignmentsTO().getPraAssignDate() != null && e.getProjectAssignmentsTO().getPraAssignDate().toString().length() > 0)?e.getProjectAssignmentsTO().getPraAssignDate().toString():"");
                        writer.append(',');   
                        writer.append((e.getProjectAssignmentsTO().getPraFinishDate() != null && e.getProjectAssignmentsTO().getPraFinishDate().toString().length() > 0)?e.getProjectAssignmentsTO().getPraFinishDate().toString():"");
                        writer.append(',');      
                        writer.append((e.getProjectAssignmentsTO().getProjectsTO().getProName() != null && e.getProjectAssignmentsTO().getProjectsTO().getProName().toString().length() > 0)?e.getProjectAssignmentsTO().getProjectsTO().getProName().toString():"");                
                        writer.append(',');   
                        writer.append((e.getProjectAssignmentsTO().getProjectsTO().getProStartDate() != null && e.getProjectAssignmentsTO().getProjectsTO().getProStartDate().toString().length() > 0)?e.getProjectAssignmentsTO().getProjectsTO().getProStartDate().toString():"");
                        writer.append(',');      
                        writer.append((e.getProjectAssignmentsTO().getProjectsTO().getProFinishDate() != null && e.getProjectAssignmentsTO().getProjectsTO().getProFinishDate().toString().length() > 0)?e.getProjectAssignmentsTO().getProjectsTO().getProFinishDate().toString():"");                
                        writer.append(','); 
                        writer.append('\n');
                    }
                    ouputStream.write(writer.toString().getBytes());

                }
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
            
            if (request.getParameter("Iniciado") != null && request.getParameter("Iniciado").equalsIgnoreCase("Iniciado")) {
                params.put("Iniciado",request.getParameter("Iniciado"));
            }
            if (request.getParameter("Entregado") != null && request.getParameter("Entregado").equalsIgnoreCase("Entregado")) {
                params.put("Entregado",request.getParameter("Entregado"));
            }
            if (request.getParameter("POEnviado") != null && request.getParameter("POEnviado").equalsIgnoreCase("POEnviado")) {
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
               // response.setContentType("application/octet-stream");
                response.setContentType("text/csv");
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
