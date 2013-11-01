package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ClientsTO;
import com.twoways.to.CurrencyTO;
import com.twoways.to.OrdersDocsTO;
import com.twoways.to.OrdersRatesTO;
import com.twoways.to.OrdersTO;
import com.twoways.to.ProjectAssignmentsTO;
import com.twoways.to.ProAssigmentsDetailsTO;
import com.twoways.to.ProjectsTO;
import com.twoways.to.RateTypesTO;
import com.twoways.to.RatesTO;
import com.twoways.to.ServicesTO;

import com.twoways.to.StatesTO;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.lang.reflect.Method;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;
import java.util.Iterator;

public class ProyectosServlet extends AutorizacionServlet {
    private static final String CONTENT_TYPE = 
        "text/html; charset=windows-1252";
    private static Logger log;
   
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log = Logger.getRootLogger();
        List roles = new ArrayList();
        roles.add("Administrador");
        roles.add("Usuario");
        this.setRolesValidos(roles);
        log.info("Inicia OrdenTrabajoServlet");


    }


    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, 
                                                           IOException {

        super.doGet(request, response);
        ProjectsTO project=null;
        List<CurrencyTO> monedas = null;
        List<StatesTO > estadosList= null;
        Double costoTotalProyecto=  0.0;
        Double cantidadPalabras =0.0;
        
        boolean reenviar = true;


        String accion =  (request.getParameter("accion") != null) ? request.getParameter("accion").toString() :"";

        TwoWaysBDL twoWaysBDL = null;
        
        String script ="<script>onloadOrder();</script>";

        try {
            twoWaysBDL = new TwoWaysBDL();
            
            monedas =  twoWaysBDL.getServiceTwoWays().obtenerMonedas(); 
            estadosList= twoWaysBDL.getServiceTwoWays().getStatesListByType("Proyecto");
            Long ordId = null ; 
            
            if (request.getParameter("ordId") !=null){
               ordId = Long.parseLong(request.getParameter("ordId"));
               project = twoWaysBDL.getServiceTwoWays().getProjectByOrdId(ordId);
            }else if (request.getParameter("proId") !=null){
                ordId = Long.parseLong(request.getParameter("proId"));
                project = twoWaysBDL.getServiceTwoWays().getProjectById(ordId);
            }
            
            if(project == null ) {
                project = new ProjectsTO();
                project.setOrdersTO(twoWaysBDL.getServiceTwoWays().getOrderById(ordId));
                project.setProStartDate(project.getOrdersTO().getOrdStartDate());
                project.setProName(project.getOrdersTO().getOrdName());
            }

            if (accion != null && accion.equalsIgnoreCase("guardar")) {

                try {

                    SimpleDateFormat sdfc = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat sdfl = 
                        new SimpleDateFormat("dd/MM/yyyy HH:mm");

                    if (request.getParameter("proStartDate") != null && 
                        !request.getParameter("proStartDate").toString().equalsIgnoreCase("")) {
                        java.util.Date date;
                        if (request.getParameter("proStartDate").toString().length() == 10) {
                            date = sdfc.parse(request.getParameter("proStartDate").toString());
                        } else {
                            date = sdfl.parse(request.getParameter("proStartDate").toString());
                        }
                        java.sql.Timestamp timest = 
                            new java.sql.Timestamp(date.getTime());
                        project.setProStartDate(timest);
                    }

                    if (request.getParameter("proFinishDate") != null && !request.getParameter("proFinishDate").toString().equalsIgnoreCase("")) {
                        java.util.Date date;
                        if (request.getParameter("proFinishDate").toString().length() == 10) {
                            date = sdfc.parse(request.getParameter("proFinishDate").toString());
                        } else {
                            date = sdfl.parse(request.getParameter("proFinishDate").toString());
                        }
                        java.sql.Timestamp timest =  new java.sql.Timestamp(date.getTime());
                        project.setProFinishDate(timest);
                    }
                    
                } catch (Exception e) {
                    request.setAttribute("mensaje", 
                                         "<script>alert('La fecha ingresada no es válida')</script>");
                    e.printStackTrace();
                    if(reenviar){ 
                    request.getRequestDispatcher("proyectos.jsp").forward(request, 
                                                                             response);
                        reenviar=false;                                                      
                    }
                }
                
                
                StatesTO stateProject= new StatesTO();
                
                stateProject.setStaId((request.getParameter("listaEstados") != null) ? 
                                   request.getParameter("listaEstados").toString() : 
                                   "");
                
                project.setStatesTO(stateProject);

                project.setProName((request.getParameter("proName") != null) ? 
                                   request.getParameter("proName").toString() : 
                                   "");
                                   
               
                project.setProStatus((request.getParameter("proStatus") != 
                                      null) ? 
                                     request.getParameter("proStatus").toString() : 
                                     "");
                if(request.getParameter("listaMoneda") != null && !request.getParameter("listaMoneda").toString().equalsIgnoreCase("")){ 
                                      
                    CurrencyTO currencyTO= new CurrencyTO();                      
                    currencyTO.setCurId(Long.parseLong(request.getParameter("listaMoneda")));
                    project.setCurrencyTO(currencyTO);
                         
                                         
                }
                                                              
                project.setUsersUsrId(this.getUser().getUsrId());

                try {

                    if (project.getProId() != null && project.getProId().toString().length() >  0) {
                        
                       twoWaysBDL.getServiceTwoWays().updateProject(project);
                       project =  twoWaysBDL.getServiceTwoWays().getProjectByOrdId(project.getOrdersTO().getOrdId());       

                    } else {
                       // project = 
                                twoWaysBDL.getServiceTwoWays().insertProject(project);
                        project =  twoWaysBDL.getServiceTwoWays().getProjectByOrdId(project.getOrdersTO().getOrdId());       
                    }
                  
                    Map <Long,ProjectAssignmentsTO>  projectAssignmentsTOMap = new HashMap<Long,ProjectAssignmentsTO>();      
                    Map <Long,ProAssigmentsDetailsTO>  proAssigmentsDetailsTOMap = new HashMap<Long,ProAssigmentsDetailsTO>();      
                   
                   for (  Enumeration enumeration =request.getParameterNames();enumeration.hasMoreElements();   ){
                   
                         String paramName =(enumeration.nextElement().toString());
                         
                         if(paramName.startsWith("padRate") || paramName.startsWith("padWCount") ){ 
                               
                              String [] arrayAux =   paramName.split("-");
                              Long id= 0L; 
                              if(arrayAux.length > 1) {
                                 id=Long.parseLong(arrayAux[1]);
                                 
                              
                              ProAssigmentsDetailsTO proAssigmentsDetailsTO =proAssigmentsDetailsTOMap.get(id);
                               
                              if(proAssigmentsDetailsTO==null){
                                  proAssigmentsDetailsTO = new ProAssigmentsDetailsTO();
                                  proAssigmentsDetailsTO.setPadId(id);
                                  proAssigmentsDetailsTOMap.put(id,proAssigmentsDetailsTO);
                              }
                              
                                 if(arrayAux[0].equals("padRate")){ 
                                    Double parametroValueD = (request.getParameter(paramName)!=null)?Double.parseDouble(request.getParameter(paramName).replaceAll(",",".")):null; 
                                    proAssigmentsDetailsTO.setPadRate(parametroValueD);
                                 }else  if(arrayAux[0].equals("padWCount")){ 
                                    Double parametroValue = (request.getParameter(paramName)!=null)?Long.parseLong(request.getParameter(paramName).replaceAll(",",".")):0D; 
                                    proAssigmentsDetailsTO.setPadWCount(parametroValue);
                                 }
                              
                              }
                          }else if( paramName.startsWith("praTotalAmount") ){ 
                                 
                             String [] arrayAux =   paramName.split("-");
                             Long id= 0L; 
                             if(arrayAux.length > 1) {
                                id=Long.parseLong(arrayAux[1]);
                                Double parametroValue = (request.getParameter(paramName)!=null)?Double.parseDouble(request.getParameter(paramName)):null; 
                             
                             ProjectAssignmentsTO projectAssignmentsTO =projectAssignmentsTOMap.get(id);
                             
                             
                             
                             if(projectAssignmentsTO==null){
                                 projectAssignmentsTO = new ProjectAssignmentsTO();
                                 projectAssignmentsTO.setPraId(id);
                                 projectAssignmentsTOMap.put(id,projectAssignmentsTO);
                             }
                                                          
                                projectAssignmentsTO.setPraTotalAmount(parametroValue);
                                                          
                            }
                         
                         }
                            
                   }
                   
                   Iterator iterator = proAssigmentsDetailsTOMap.values().iterator();
                   while	(iterator.hasNext()) {
                      ProAssigmentsDetailsTO detalle = (ProAssigmentsDetailsTO) iterator.next();
                       twoWaysBDL.getServiceTwoWays().updateProjectAssigmentDetailsByPadId(detalle);
                   }
                   
                   
                    iterator = projectAssignmentsTOMap.values().iterator();
                    while        (iterator.hasNext()) {
                       ProjectAssignmentsTO asignacion = (ProjectAssignmentsTO) iterator.next();
                        twoWaysBDL.getServiceTwoWays().updateProjectAssigmentFromDetails(asignacion);
                    }
                   
                   
                    
                   
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("script","<script>alert('Ocurrió un error : "+ e.getMessage()+"')</script>");
                    if(reenviar){ 
                    request.getRequestDispatcher("error.jsp").forward(request, 
                                                                             response);
                        reenviar=false;                                                      
                    }
                }
            }
            
            project.setProjectAssignmentsTOList(twoWaysBDL.getServiceTwoWays().getProjectAssignmentsByProId(project.getProId()));
            Map<Long,Double> costos = new HashMap<Long,Double>(); 
            Map costoMap = new HashMap();
          
            
            for (ProjectAssignmentsTO projectAssignmentsTO:project.getProjectAssignmentsTOList()){
                 
                projectAssignmentsTO.setProAssigmentsDetailsTO(twoWaysBDL.getServiceTwoWays().getProjectAssignmentsDetailsById(projectAssignmentsTO.getPraId()));                       
                
                cantidadPalabras+= (projectAssignmentsTO.getPraTotalAmount()!=null)?projectAssignmentsTO.getPraTotalAmount():0.0;
                
                
                if(projectAssignmentsTO.getProAssigmentsDetailsTO().size() ==0 ){
                    projectAssignmentsTO.setProAssigmentsDetailsTO(null);
                }else{
                    projectAssignmentsTO.setProjectsTO(project);
                    Map auxCostoMap=twoWaysBDL.getServiceTwoWays().getCostPA(projectAssignmentsTO);
                    Double value = (Double)auxCostoMap.get("costo");
                    costoTotalProyecto +=value;
                    costos.put(projectAssignmentsTO.getPraId(),value);
                    costoMap.putAll((Map)auxCostoMap.get("cotizaciones"));
                }          
            
            }
            
            request.setAttribute("costoTotalProyecto",costoTotalProyecto);
            request.setAttribute("cantidadPalabras",cantidadPalabras);
            request.setAttribute("cotizaciones",costoMap);
            request.setAttribute("listaMoneda",monedas);
            request.setAttribute("listaEstados",estadosList);
            request.setAttribute("costosMap",costos);
           
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("script","<script>alert('Ocurrió un error : "+ e.getMessage()+"')</script>");
            if(reenviar){ 
            request.getRequestDispatcher("error.jsp").forward(request, 
                                                                     response);
                reenviar=false;                                                      
            }
        }
        
        request.setAttribute("userId", this.getUser().getUsrId());
        request.setAttribute("script", script);
        request.setAttribute("project", project);
        if(reenviar){ 
        request.getRequestDispatcher("proyectos.jsp").forward(request, 
                                                                 response);
            reenviar=false;                                                      
        }
    }
    
    
}
