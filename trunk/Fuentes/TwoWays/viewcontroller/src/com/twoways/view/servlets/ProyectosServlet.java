package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ClientsTO;
import com.twoways.to.OrdersDocsTO;
import com.twoways.to.OrdersRatesTO;
import com.twoways.to.OrdersTO;
import com.twoways.to.ProjectAssignmentsTO;
import com.twoways.to.ProjectsTO;
import com.twoways.to.RateTypesTO;
import com.twoways.to.RatesTO;
import com.twoways.to.ServicesTO;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import org.apache.commons.fileupload.FileItem;
import org.apache.log4j.Logger;

public class ProyectosServlet extends AutorizacionServlet {
    private static final String CONTENT_TYPE = 
        "text/html; charset=windows-1252";
    private static Logger log;
   
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        log = Logger.getRootLogger();
        List roles = new ArrayList();
        roles.add("Administrador");
        this.setRolesValidos(roles);
        log.info("Inicia OrdenTrabajoServlet");


    }


    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, 
                                                           IOException {

        super.doGet(request, response);
        ProjectsTO project=null;


        String accion = 
            (request.getParameter("accion") != null) ? request.getParameter("accion").toString() : 
            "";

        TwoWaysBDL twoWaysBDL = null;

        try {
            twoWaysBDL = new TwoWaysBDL();
             
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
                project.setProStartDate(project.getOrdersTO().getOrdDate());
            }

            if (accion != null && accion.equalsIgnoreCase("guardar")) {

                


                try {

                    SimpleDateFormat sdfc = new SimpleDateFormat("dd/MM/yyyy");
                    SimpleDateFormat sdfl = 
                        new SimpleDateFormat("dd/MM/yyyy HH:mm");

                    if (request.getParameter("proStartDate") != null && 
                        !request.getParameter("proStartDate").toString().equalsIgnoreCase("")) {
                        java.util.Date date;
                        if (request.getParameter("proStartDate").toString().length() == 
                            10) {
                            date = 
sdfc.parse(request.getParameter("proStartDate").toString());
                        } else {
                            date = 
sdfl.parse(request.getParameter("proStartDate").toString());
                        }
                        java.sql.Timestamp timest = 
                            new java.sql.Timestamp(date.getTime());
                        project.setProStartDate(timest);
                    }

                    if (request.getParameter("proFinishDate") != null && 
                        !request.getParameter("proFinishDate").toString().equalsIgnoreCase("")) {
                        java.util.Date date;
                        if (request.getParameter("proFinishDate").toString().length() == 
                            10) {
                            date = 
sdfc.parse(request.getParameter("proFinishDate").toString());
                        } else {
                            date = 
sdfl.parse(request.getParameter("proFinishDate").toString());
                        }
                        java.sql.Timestamp timest = 
                            new java.sql.Timestamp(date.getTime());
                        project.setProFinishDate(timest);
                    }


                } catch (Exception e) {
                    request.setAttribute("mensaje", 
                                         "<script>alert('La fecha ingresada no es valida')</script>");
                    e.printStackTrace();
                    request.getRequestDispatcher("proyectos.jsp").forward(request, 
                                                                             response);
                }

                project.setProName((request.getParameter("proName") != null) ? 
                                   request.getParameter("proName").toString() : 
                                   "");
                                   
                /*                   
                project.setCurrencyCotizationsCucId(Long.parseLong(((request.getParameter("currencyCotizationsCucId") != 
                                                                     null && 
                                                                     request.getParameter("currencyCotizationsCucId").toString().length() > 
                                                                     0) ? 
                                                                    request.getParameter("currencyCotizationsCucId").toString() : 
                                                                    "0")));
                project.setProDescription((request.getParameter("proDescription") != 
                                           null) ? 
                                          request.getParameter("proDescription").toString() : 
                                          "");
                */                          
                project.setProStatus((request.getParameter("proStatus") != 
                                      null) ? 
                                     request.getParameter("proStatus").toString() : 
                                     "");
                /*
                project.setProTotalCliWcount(Long.parseLong(((request.getParameter("proTotalCliWcount") != 
                                                              null && 
                                                              request.getParameter("proTotalCliWcount").toString().length() > 
                                                              0) ? 
                                                             request.getParameter("proTotalCliWcount").toString() : 
                                                             "0")));
                project.setProTotalClient(Long.parseLong(((request.getParameter("proTotalClient") != 
                                                           null && 
                                                           request.getParameter("proTotalClient").toString().length() > 
                                                           0) ? 
                                                          request.getParameter("proTotalClient").toString() : 
                                                          "0")));
                project.setProTotalTranWcount(Long.parseLong(((request.getParameter("proTotalTranWcount") != 
                                                               null && 
                                                               request.getParameter("proTotalTranWcount").toString().length() > 
                                                               0) ? 
                                                              request.getParameter("proTotalTranWcount").toString() : 
                                                              "0")));
                project.setProTotalTranslator(Long.parseLong(((request.getParameter("proTotalTranslator") != 
                                                               null && 
                                                               request.getParameter("proTotalTranslator").toString().length() > 
                                                               0) ? 
                                                              request.getParameter("proTotalTranslator").toString() : 
                                                              "0")));
                                                              
                */                                              
                project.setUsersUsrId(this.getUser().getUsrId());

                try {

                    if (project.getProId() != null && 
                        project.getProId().toString().length() > 
                        0) {
                        ProjectsTO projectOld = 
                                twoWaysBDL.getServiceTwoWays().getProjectById(Long.parseLong(request.getParameter("proId").toString()));
                       
                        //project = 
                                twoWaysBDL.getServiceTwoWays().updateProject(project);
                        project =  twoWaysBDL.getServiceTwoWays().getProjectByOrdId(project.getOrdersTO().getOrdId());       

                    } else {
                       // project = 
                                twoWaysBDL.getServiceTwoWays().insertProject(project);
                        project =  twoWaysBDL.getServiceTwoWays().getProjectByOrdId(project.getOrdersTO().getOrdId());       
                    }

                    
                
                   

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            
            project.setProjectAssignmentsTOList(twoWaysBDL.getServiceTwoWays().getProjectAssignmentsByProId(project.getProId()));
            
            for (ProjectAssignmentsTO projectAssignmentsTO:project.getProjectAssignmentsTOList()){
                 
                 
                projectAssignmentsTO.setProAssigmentsDetailsTO(twoWaysBDL.getServiceTwoWays().getProjectAssignmentsDetailsById(projectAssignmentsTO.getPraId()));                       
                if(projectAssignmentsTO.getProAssigmentsDetailsTO().size() ==0 ){
                    projectAssignmentsTO.setProAssigmentsDetailsTO(null);
                }
            
            
            }
            
            
           
        } catch (Exception e) {
            e.printStackTrace();
        }
        request.setAttribute("project", project);
        request.getRequestDispatcher("proyectos.jsp").forward(request, 
                                                              response);
    }
    
    
}
