package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ClientsTO;
import com.twoways.to.OrdersTO;
import com.twoways.to.ProjectsTO;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class GestionarProyectoServlet extends AutorizacionServlet {
   // private static final String CONTENT_TYPE = "text/html; charset=ISO-8859-1";

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
        super.doGet(request,response);
        TwoWaysBDL twoWaysBDL=null;
        String accion = request.getParameter("accion");
        int page =(request.getParameter("pageId")  != null && request.getParameter("pageId").length() >0  )?Integer.parseInt(request.getParameter("pageId")): 0 ;  

        List optionList= new ArrayList();
        
        optionList.add("=");
        optionList.add("<=");
        optionList.add(">=");
        
        
        try {
           twoWaysBDL = new TwoWaysBDL();
           List clientes= twoWaysBDL.getServiceTwoWays().obtenerClientes();
           request.setAttribute("listaCliente",clientes);
           request.setAttribute("optionList",optionList); 
           
        }catch(Exception e){
            request.getRequestDispatcher("error.jsp").forward(request,response);    
            request.setAttribute("exception",e);
        }
        
        //request.setAttribute("pageIr",1);
        
        if(accion !=null && accion.equalsIgnoreCase("buscar")){
            request.setAttribute("accion",accion);
            ProjectsTO projectTO = new ProjectsTO();
            SimpleDateFormat sdfch = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            SimpleDateFormat sdfsh = new SimpleDateFormat("dd/MM/yyyy");
            Map params= new  HashMap();
             
            java.util.Date date;

            if (request.getParameter("projName") != null && request.getParameter("projName").length() > 0) {
                projectTO.setProName(request.getParameter("projName"));
                params.put("projName",request.getParameter("projName"));
            }
            
            ClientsTO clienteTO = new ClientsTO(); 
            OrdersTO orderTO = new OrdersTO();
            if(request.getParameter("listaClientes") != null && request.getParameter("listaClientes").length() > 0  ){ 
             
              long cliId= Long.parseLong(request.getParameter("listaClientes"));
              clienteTO.setCliId(cliId);
              params.put("cliId",cliId);
              orderTO.setClientsTO(clienteTO);              
              projectTO.setOrdersTO(orderTO);
            }
            
            if (request.getParameter("ordName") != null && request.getParameter("ordName").length() > 0) {
                orderTO.setOrdName(request.getParameter("ordName"));
                projectTO.setOrdersTO(orderTO);
                params.put("ordName",request.getParameter("ordName"));
            }
            
            if (request.getParameter("Iniciado") != null && request.getParameter("Iniciado").length() > 0) {
                params.put("Iniciado",request.getParameter("Iniciado"));
                request.setAttribute("Iniciado",request.getParameter("Iniciado"));
            }
            if (request.getParameter("Finalizado") != null && request.getParameter("Finalizado").length() > 0) {
                params.put("Finalizado",request.getParameter("Finalizado"));
                request.setAttribute("Finalizado",request.getParameter("Finalizado"));
            }
            /*if (request.getParameter("POPendiente") != null && request.getParameter("POPendiente").length() > 0) {
                params.put("POPendiente",request.getParameter("POPendiente"));
                request.setAttribute("POPendiente",request.getParameter("POPendiente"));
            }*/
            if (request.getParameter("POEnviado") != null && request.getParameter("POEnviado").length() > 0) {
                params.put("POEnviado",request.getParameter("POEnviado"));
                request.setAttribute("POEnviado",request.getParameter("POEnviado"));
            }
            
            if(request.getParameter("projFinishDate") !=null &&( request.getParameter("projFinishDate").length() == 10 || request.getParameter("projFinishDate").length() == 16)){ 
                try {
                    
                    date = (request.getParameter("projFinishDate").length() ==10 )?sdfsh.parse(request.getParameter("projFinishDate")):sdfch.parse(request.getParameter("projFinishDate"));
                    java.sql.Timestamp timest = new java.sql.Timestamp(date.getTime()); 
                    orderTO.setOrdFinishDate(timest);
                    params.put("projFinishDate",request.getParameter("projFinishDate"));
                    params.put("projFinishDateOpt",request.getParameter("projFinishDateOpt"));
                    request.setAttribute("projFinishDate",request.getParameter("projFinishDate")); 
                    request.setAttribute("projFinishDateOpt",request.getParameter("projFinishDateOpt")); 
                } catch (ParseException e) {
                   e.printStackTrace();
                }
            }  
            if(request.getParameter("projDate") !=null &&( request.getParameter("projDate").length() == 10 || request.getParameter("projDate").length() == 16)){ 
                 try {
                     date = (request.getParameter("projDate").length() ==10 )?sdfsh.parse(request.getParameter("projDate")):sdfch.parse(request.getParameter("projDate"));
                     java.sql.Timestamp timest = new java.sql.Timestamp(date.getTime()); 
                     orderTO.setOrdDate(timest);
                     params.put("projDate",request.getParameter("projDate"));
                     params.put("projDateOpt",request.getParameter("projDateOpt"));
                     request.setAttribute("projDate",request.getParameter("projDate")); 
                     request.setAttribute("projDateOpt",request.getParameter("projDateOpt")); 
                 } catch (ParseException e) {
                     e.printStackTrace();
                 }
             }
             
            if (request.getParameter("pageIr") != null && request.getParameter("pageIr").length() > 0) {
                int pageIr = Integer.parseInt(request.getParameter("pageIr"));
                //if ( pageIr > 1 &&  pageIr > (page +1) ){
                    page = pageIr -1;
                //}
               request.setAttribute("pageIr",pageIr);
            }
             
            request.setAttribute("project",projectTO); 
            try{
               List<ProjectsTO> projects =  twoWaysBDL.getServiceTwoWays().findProjects(params);
               int  pageTop=(page+1)*10 ;
               int  minPage=(page)*10 ;
               List<ProjectsTO> subprojects = null;
                
                
               if(projects.size() > pageTop){ 
                 subprojects = (projects.size() > pageTop )?projects.subList(minPage,pageTop):projects.subList(minPage,projects.size());       
               }else{
                   subprojects=projects;
                   pageTop =subprojects.size();
                   minPage=(pageTop/10)*10;
                   if(pageTop==minPage){
                       minPage=pageTop-10;
                   }
                   if(minPage > 0){ 
                      subprojects = (projects.size() > pageTop )?projects.subList(minPage,pageTop):projects.subList(minPage,projects.size());       
                   }else{
                       pageTop=1;
                       minPage=1; 
                       page=0;
                       
                   }
               }
               
               
               int maxPage = (int)(projects.size() / 10);
               request.setAttribute("listaProyectos",subprojects);
               request.setAttribute("maxPage",maxPage);
               request.setAttribute("page",page);
               request.setAttribute("pageId",page);
               request.setAttribute("pageIr",page+1);
               
            }catch(Exception e){
               e.printStackTrace(); 
            }
            
        }
        request.getRequestDispatcher("gestionarProyectos.jsp").forward(request,response); 
    }

    
}
