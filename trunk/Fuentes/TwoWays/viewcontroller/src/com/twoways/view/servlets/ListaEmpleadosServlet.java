package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.EmployeesTO;
import com.twoways.to.OrdersTO;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

public class ListaEmpleadosServlet extends AutorizacionServlet {
    private static final String CONTENT_TYPE = "text/html; charset=ISO-8859-1";

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
        
        try{
            twoWaysBDL = new TwoWaysBDL();    
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if(accion !=null && accion.equalsIgnoreCase("buscar")){
            request.setAttribute("accion",accion);
           // EmployeesTO employeeTO = new EmployeesTO();            
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
        try{
           List<EmployeesTO> empleados =  twoWaysBDL.getServiceTwoWays().findEmployees(params);
           int  pageTop=(page+1)*10 ;
           int  minPage=(page)*10 ;
           List subempleados = null;
            
            
           if(empleados.size() > pageTop){ 
             subempleados = (empleados.size() > pageTop )?empleados.subList(minPage,pageTop):empleados.subList(minPage,empleados.size());       
           }else{
               subempleados=empleados;
               pageTop =subempleados.size();
               minPage=(pageTop/10)*10;
               if(pageTop==minPage){
                   minPage=pageTop-10;
               }
               if(minPage > 0){ 
                  subempleados = (empleados.size() > pageTop )?empleados.subList(minPage,pageTop):empleados.subList(minPage,empleados.size());       
               }else{
                   pageTop=1;
                   minPage=1; 
                   page=0;
                   
               }
           }
            int maxPage = 0;
            if (empleados.size()== 10) maxPage =1;
            else maxPage =(int)(empleados.size() / 10) + 1;
            request.setAttribute("listaEmpleados",subempleados);
            request.setAttribute("maxPage",maxPage);
            request.setAttribute("page",page);          
          
            request.setAttribute("pageId",page); 
           
        }catch(Exception e){
           e.printStackTrace(); 
        }        
    }
        
    request.getRequestDispatcher("listaEmpleados.jsp").forward(request,response);
    }  
}
