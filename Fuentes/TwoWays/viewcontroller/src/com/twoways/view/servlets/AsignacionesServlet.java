package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.EmployeesTO;
import com.twoways.to.OrdersDocsTO;
import com.twoways.to.OrdersTO;
import com.twoways.to.ProAssigmentsDetailsTO;
import com.twoways.to.ProjectAssignmentsTO;
import com.twoways.to.ProjectsTO;
import com.twoways.to.RateTypesTO;
import com.twoways.to.TranslatorsLanguaguesTO;
import com.twoways.to.TranslatorsTO;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AsignacionesServlet extends AutorizacionServlet {
    public AsignacionesServlet() {
    }
    
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        List roles= new ArrayList();
        roles.add("Administrador");
        roles.add("Usuario");
        this.setRolesValidos(roles);
    }

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, 
                                                           IOException {

        super.doGet(request, response);
        boolean reenviar= true;
        
        ProjectsTO project=null;
        List <EmployeesTO> employeeTOList = null;
        List<OrdersDocsTO> ordersDocsTOList = null; 
        List<RateTypesTO> servicesTOList= null;  
        OrdersTO ordersTO = null;
        ProjectAssignmentsTO projectAssignmentsTO = new  ProjectAssignmentsTO();
        String script ="<script>alert('La asignaci�n se guard� con �xito');\ncancelarAsignacion();</script>";
        String accion = 
            (request.getParameter("accion") != null) ? request.getParameter("accion").toString() : 
            "";

        TwoWaysBDL twoWaysBDL = null;

        try {
            twoWaysBDL = new TwoWaysBDL();
            Long proId = Long.parseLong(request.getParameter("proId"));
            Long praId = (request.getParameter("praId") != null && !request.getParameter("praId").equals(""))?Long.parseLong(request.getParameter("praId")):null;
            project = twoWaysBDL.getServiceTwoWays().getProjectById(proId);
            if(praId !=null){
                projectAssignmentsTO=  twoWaysBDL.getServiceTwoWays().getProjectAssignmentsById(praId);
            }
            projectAssignmentsTO.setProjectsTO(project);
            ordersTO = project.getOrdersTO();
            ordersDocsTOList = ordersTO.getOrdersDocsTOList(); 
            servicesTOList= ordersTO.getServicesTOList();   
            request.setAttribute("listaServices",servicesTOList);
            request.setAttribute("ordersTO",ordersTO);
            request.setAttribute("projectAssignmentsTO",projectAssignmentsTO);
            
            if (accion != null && accion.equalsIgnoreCase("guardar")) {
                try {

                    SimpleDateFormat sdfc = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat sdfl = 
                        new SimpleDateFormat("dd/MM/yyyy HH:mm");

                    if (request.getParameter("proAssignStartDate") != null &&  !request.getParameter("proAssignStartDate").toString().equalsIgnoreCase("")) {
                        java.util.Date date;
                        if (request.getParameter("proAssignStartDate").toString().length() ==   10) {
                            date =  sdfc.parse(request.getParameter("proAssignStartDate").toString());
                        } else {
                            date =  sdfl.parse(request.getParameter("proAssignStartDate").toString());
                        }
                        java.sql.Timestamp timest = 
                            new java.sql.Timestamp(date.getTime());
                        projectAssignmentsTO.setPraAssignDate(timest);
                    }

                    if (request.getParameter("proAssignFinishDate") != null &&     !request.getParameter("proAssignFinishDate").toString().equalsIgnoreCase("")) {
                        java.util.Date date;
                        if (request.getParameter("proAssignFinishDate").toString().length() ==  10) {
                            date =  sdfc.parse(request.getParameter("proAssignFinishDate").toString());
                        } else {
                            date =   sdfl.parse(request.getParameter("proAssignFinishDate").toString());
                        }
                        java.sql.Timestamp timest =                             new java.sql.Timestamp(date.getTime());
                        projectAssignmentsTO.setPraFinishDate(timest);
                    }


                } catch (Exception e) {
                    request.setAttribute("mensaje", 
                                         "<script>alert('La fecha ingresada no es v�lida')</script>");
                    e.printStackTrace();
                    
                    if(reenviar){
                    request.getRequestDispatcher("proyectos.jsp").forward(request, 
                                                                             response);
                                                                             reenviar=false; 
                    }
                }
                
               
                if(request.getParameter("listaServices") != null && request.getParameter("listaServices").length() > 0  ){     
                    RateTypesTO rate = new RateTypesTO();
                    rate.setRtyName(request.getParameter("listaServices"));
                    projectAssignmentsTO.setServiceTO(rate);
                   
                }
                if(request.getParameter("listaEmployees") != null && request.getParameter("listaEmployees").length() > 0  ){     
                    EmployeesTO emp = new EmployeesTO();
                    emp.setEmpId(Long.parseLong(request.getParameter("listaEmployees")));
                    projectAssignmentsTO.setEmployeesTO(emp);
                }

                try {

                    if (projectAssignmentsTO.getPraId() != null && 
                        projectAssignmentsTO.getPraId().toString().length() > 
                        0) {
                        
                        projectAssignmentsTO = twoWaysBDL.getServiceTwoWays().updateProjectAssignament(projectAssignmentsTO);                        

                    } else {
                        projectAssignmentsTO = twoWaysBDL.getServiceTwoWays().insertProjectAssignament(projectAssignmentsTO);                     
                    }
                    
                    projectAssignmentsTO.setProjectsTO(project);
                    
                   /* for( Enumeration documentos = request.getParameterNames(); documentos.hasMoreElements();){
                     System.out.println(documentos.nextElement().toString());   
                    }*/
                    
                    
                    for( Enumeration documentos = request.getParameterNames(); documentos.hasMoreElements();){
                        
                        String doc = documentos.nextElement().toString();
                        if (doc !=null && doc.startsWith("listdocs-") && request.getParameter(doc).equalsIgnoreCase("on") ){
                            ProAssigmentsDetailsTO detail = new ProAssigmentsDetailsTO();
                            detail.setProjectAssignmentsTO(projectAssignmentsTO);
                            OrdersDocsTO ordersDocsTO= twoWaysBDL.getServiceTwoWays().getOrdersDocById(Long.parseLong(doc.split("-")[1])); 
                            detail.setOrdersDocsTO(ordersDocsTO);
                            String languaje = doc.replaceAll("listdocs-","languagues");
                            if(request.getParameter(languaje) != null && request.getParameter(languaje).length() > 0  ){     
                                Long traLan = Long.parseLong(request.getParameter(languaje));
                                TranslatorsLanguaguesTO translatorsLanguaguesTO = new TranslatorsLanguaguesTO(); 
                                translatorsLanguaguesTO.setTlaId(traLan);
                                detail.setPranslatorsLanguaguesTO(translatorsLanguaguesTO);
                              //  projectAssignmentsTO.setEmployeesTO(tralan);
                            }
                            
                            twoWaysBDL.getServiceTwoWays().insertProjectAssignamentDetails(detail);
                        }
                    }
                    request.setAttribute("script",script);                    
                   
                } catch (Exception e) {
                    e.printStackTrace();
                    script = "<script>alert('Ocurri� un error no se pudo crear la asignaci�n')</script>";
                    request.setAttribute("script",script);
                }                
               
            }

            List <ProAssigmentsDetailsTO> proAssigmentsDetailsTOList= twoWaysBDL.getServiceTwoWays().getProjectAssignmentsDetailsById(projectAssignmentsTO.getPraId());
            projectAssignmentsTO.setProAssigmentsDetailsTO(proAssigmentsDetailsTOList);
           
            StringBuffer sb = new StringBuffer("\n<script>\nvar ordersdocsOld= new Array();\n");
            int index=0;
            for (ProAssigmentsDetailsTO proAssigmentsDetailsTO : proAssigmentsDetailsTOList){
                                
                sb.append("\nordersdocsOld[");
                sb.append(String.valueOf(index++));
                sb.append("]=");
                sb.append(proAssigmentsDetailsTO.getOrdersDocsTO().getOdoId());
                sb.append(";");
            }
            sb.append("\n</script>");
            
            request.setAttribute("ordersdocsOld",sb.toString());
            
            if ( projectAssignmentsTO.getServiceTO() !=null){
               employeeTOList= twoWaysBDL.getServiceTwoWays().getEmpByRatesName( projectAssignmentsTO.getServiceTO().getRtyName()); 
               request.setAttribute("listaEmployees",employeeTOList);
               
               if(! projectAssignmentsTO.getServiceTO().getRtyName().equals("Maquetador")){
                   
                   TranslatorsTO translatorTO= twoWaysBDL.getServiceTwoWays().getTraByEmpId(String.valueOf(projectAssignmentsTO.getEmployeesTO().getEmpId()));
                   if (translatorTO!=null){
                     
                       List  resultLanguajes = twoWaysBDL.getServiceTwoWays().getLangByEmpId(projectAssignmentsTO.getEmployeesTO().getEmpId());
                       request.setAttribute("languagues",resultLanguajes);
                   }
               }                
            }
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("projectAssignmentsTO", projectAssignmentsTO);
        //request.setAttribute("project", project);
        if(reenviar){ 
           request.getRequestDispatcher("asignaciones.jsp").forward(request, 
                                                              response);
                                                              reenviar=false;
        }                                                      
    }
    
}
