package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ClientsTO;
import com.twoways.to.OrdersTO;
import com.twoways.to.RateTypesTO;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

public class BuscarOrdenTrabajoServlet extends AutorizacionServlet {
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
        
        if(accion !=null && accion.equalsIgnoreCase("buscar")){
            request.setAttribute("accion",accion);
            OrdersTO orderTO = new OrdersTO();
            SimpleDateFormat sdfch = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            SimpleDateFormat sdfsh = new SimpleDateFormat("dd/MM/yyyy");
            Map params= new  HashMap();
             
            java.util.Date date;
            orderTO.setOrdName(request.getParameter("ordName"));
            params.put("ordName",request.getParameter("ordName"));
            params.put("ordProjId",request.getParameter("ordProjId"));
            orderTO.setOrdProjId(request.getParameter("ordProjId"));
            ClientsTO cliente = new ClientsTO(); 
            if(request.getParameter("listaClientes") != null && request.getParameter("listaClientes").length() > 0  ){ 
             
              long cliId= Long.parseLong(request.getParameter("listaClientes"));
              cliente.setCliId(cliId);
              params.put("cliId",cliId);
              
            }else{
              params.put("cliId",0);
            }
            orderTO.setClientsTO(cliente);
            
            if(request.getParameter("ordFinishDate") !=null &&( request.getParameter("ordFinishDate").length() == 10 || request.getParameter("ordFinishDate").length() == 16)){ 
                try {
                    
                    date = (request.getParameter("ordFinishDate").length() ==10 )?sdfsh.parse(request.getParameter("ordFinishDate")):sdfch.parse(request.getParameter("ordFinishDate"));
                    java.sql.Timestamp timest = new java.sql.Timestamp(date.getTime()); 
                    orderTO.setOrdFinishDate(timest);
                    params.put("ordFinishDate",request.getParameter("ordFinishDate"));
                    params.put("ordFinishDateOpt",request.getParameter("ordFinishDateOpt"));
                    request.setAttribute("ordFinishDate",request.getParameter("ordFinishDate")); 
                    request.setAttribute("ordFinishDateOpt",request.getParameter("ordFinishDateOpt")); 
                } catch (ParseException e) {
                   e.printStackTrace();
                }
            }
       System.out.println(request.getParameter("ordDate").length());     
            if(request.getParameter("ordDate") !=null &&( request.getParameter("ordDate").length() == 10 || request.getParameter("ordDate").length() == 16)){ 
                 try {
                     date = (request.getParameter("ordDate").length() ==10 )?sdfsh.parse(request.getParameter("ordDate")):sdfch.parse(request.getParameter("ordDate"));
                     java.sql.Timestamp timest = new java.sql.Timestamp(date.getTime()); 
                     orderTO.setOrdDate(timest);
                     params.put("ordDate",request.getParameter("ordDate"));
                     params.put("ordDateOpt",request.getParameter("ordDateOpt"));
                     request.setAttribute("ordDate",request.getParameter("ordDate")); 
                     request.setAttribute("ordDateOpt",request.getParameter("ordDateOpt")); 
                 } catch (ParseException e) {
                     e.printStackTrace();
                 }
             }
             
            request.setAttribute("order",orderTO); 
            try{
               List<OrdersTO> orders =  twoWaysBDL.getServiceTwoWays().findOrders(params);
               int  pageTop=(page+1)*10 ;
               int  minPage=(page)*10 ;
               List<OrdersTO> suborders = (orders.size() > pageTop )?orders.subList(minPage,pageTop):orders.subList(minPage,orders.size());       
               
               int maxPage = (int)(orders.size() / 10);
               request.setAttribute("listaOrden",suborders);
               request.setAttribute("maxPage",maxPage);
               request.setAttribute("page",page);
                  
               
              
               request.setAttribute("pageId",page); 
               
            }catch(Exception e){
               e.printStackTrace(); 
            }
            
        }
        
        
        
        request.getRequestDispatcher("buscarOrdendeTrabajo.jsp").forward(request,response); 
    }

    public void doPost(HttpServletRequest request, 
                       HttpServletResponse response) throws ServletException, 
                                                            IOException {
       doGet(request,response);
    }
}
