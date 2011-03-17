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
            params.put("empFirstName",request.getParameter("empFirstName"));  
            params.put("empLastName",request.getParameter("empLastName"));  
            params.put("ProjId",request.getParameter("ProjId"));
            params.put("Traductor",request.getParameter("Traductor"));
            params.put("Editor",request.getParameter("Editor"));
            params.put("Revisor",request.getParameter("Revisor"));
            params.put("Maquetador",request.getParameter("Maquetador"));
            params.put("PDTP",request.getParameter("PDTP"));
            params.put("Proofer",request.getParameter("Proofer"));
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
                      
           int maxPage = (int)(empleados.size() / 10);
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
