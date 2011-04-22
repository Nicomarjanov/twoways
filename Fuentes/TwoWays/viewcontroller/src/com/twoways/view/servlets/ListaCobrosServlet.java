package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;

import com.twoways.to.EmployeesTO;

import com.twoways.to.InvoicesTO;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

public class ListaCobrosServlet extends AutorizacionServlet {
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
            if (request.getParameter("invNumber") != null && request.getParameter("invNumber").length() > 0) params.put("invNumber",request.getParameter("invNumber")); 
            if (request.getParameter("listaClientes") != null && request.getParameter("listaClientes").length() > 0) params.put("listaClientes",request.getParameter("listaClientes")); 
            if (request.getParameter("ordName") != null && request.getParameter("ordName").length() > 0) params.put("ordName",request.getParameter("ordName")); 
            
            try{
               List<InvoicesTO> facturas =  twoWaysBDL.getServiceTwoWays().findInvoices(params);
               int  pageTop=(page+1)*10 ;
               int  minPage=(page)*10 ;
               List subfacturas = null;
                
                
               if(facturas.size() > pageTop){ 
                 subfacturas= (facturas.size() > pageTop )?facturas.subList(minPage,pageTop):facturas.subList(minPage,facturas.size());       
               }else{
                   subfacturas=facturas;
                   pageTop =subfacturas.size();
                   minPage=(pageTop/10)*10;
                   if(pageTop==minPage){
                       minPage=pageTop-10;
                   }
                   if(minPage > 0){ 
                      subfacturas = (facturas.size() > pageTop )?facturas.subList(minPage,pageTop):facturas.subList(minPage,facturas.size());       
                   }else{
                       pageTop=1;
                       minPage=1; 
                       page=0;
                       
                   }
               }
                          
               int maxPage = (int)(facturas.size() / 10);
               request.setAttribute("listaFacturas",subfacturas);
               request.setAttribute("maxPage",maxPage);
               request.setAttribute("page",page);          
              
               request.setAttribute("pageId",page); 
               
            }catch(Exception e){
               e.printStackTrace(); 
            }        
            }
            
            request.getRequestDispatcher("listaFacturas.jsp").forward(request,response);
            }
            }
