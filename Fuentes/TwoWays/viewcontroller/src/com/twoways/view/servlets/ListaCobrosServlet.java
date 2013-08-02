package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ClientsTO;
import com.twoways.to.InvoicesTO;

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


public class ListaCobrosServlet extends AutorizacionServlet {
    
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
        
        List optionList= new ArrayList();        
        optionList.add("=");
        optionList.add("<=");
        optionList.add(">=");
        List<ClientsTO> clientes = null;
        
        int page =(request.getParameter("pageId")  != null && request.getParameter("pageId").length() >0  )?Integer.parseInt(request.getParameter("pageId")): 0 ;  
        
        try{
            twoWaysBDL = new TwoWaysBDL();    
            request.setAttribute("optionList",optionList); 
            
            clientes = twoWaysBDL.getServiceTwoWays().obtenerClientes();
            request.setAttribute("listaClientes",clientes);   
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if(accion !=null && accion.equalsIgnoreCase("buscar")){
            request.setAttribute("accion",accion);
            InvoicesTO factura = new InvoicesTO();   
            SimpleDateFormat sdfsh = new SimpleDateFormat("dd/MM/yyyy");            
            Map params= new  HashMap();          
            java.util.Date date;
            
            if (request.getParameter("invNumber") != null && request.getParameter("invNumber").length() > 0){
                params.put("invNumber",request.getParameter("invNumber")); 
                factura.setInvId(Long.parseLong(request.getParameter("invNumber")));
                request.setAttribute("invNumber",request.getParameter("invNumber")); 
            }
            ClientsTO cliente = new ClientsTO(); 
            if(request.getParameter("listaClientes") != null && request.getParameter("listaClientes").length() > 0  ){ 
              
              Long cliId= Long.parseLong(request.getParameter("listaClientes"));
              cliente.setCliId(cliId);
              params.put("cliId",cliId);
              request.setAttribute("cliId",cliId); 
              
            /*}else{
              params.put("cliId",0);*/
            }
            factura.setClientsTO(cliente);
            
            if(request.getParameter("searchDate") !=null &&( request.getParameter("searchDate").length() == 10 )){ 
                 try {
                     date = sdfsh.parse(request.getParameter("searchDate"));
                     java.sql.Timestamp timest = new java.sql.Timestamp(date.getTime()); 
                     factura.setInvDate(timest);
                     params.put("searchDate",request.getParameter("searchDate"));
                     params.put("invDateOpt",request.getParameter("invDateOpt"));
                     request.setAttribute("searchDate",request.getParameter("searchDate")); 
                     request.setAttribute("invDateOpt",request.getParameter("invDateOpt")); 
                 } catch (ParseException e) {
                     e.printStackTrace();
                 }
            }            
            try{
               List<InvoicesTO> ifacturasList =  twoWaysBDL.getServiceTwoWays().findInvoices(params);
               int  pageTop=(page+1)*10 ;
               int  minPage=(page)*10 ;
               List subfacturas = null;
                
                
               if(ifacturasList.size() > pageTop){ 
                 subfacturas= (ifacturasList.size() > pageTop )?ifacturasList.subList(minPage,pageTop):ifacturasList.subList(minPage,ifacturasList.size());       
               }else{
                   subfacturas=ifacturasList;
                   pageTop =subfacturas.size();
                   minPage=(pageTop/10)*10;
                   if(pageTop==minPage){
                       minPage=pageTop-10;
                   }
                   if(minPage > 0){ 
                      subfacturas = (ifacturasList.size() > pageTop )?ifacturasList.subList(minPage,pageTop):ifacturasList.subList(minPage,ifacturasList.size());       
                   }else{
                       pageTop=1;
                       minPage=1; 
                       page=0;
                       
                   }
               }
               int maxPage = 0;
               if (ifacturasList.size()== 10) maxPage =1;
               else maxPage =(int)(ifacturasList.size() / 10) + 1;
               request.setAttribute("listaFacturas",subfacturas);
               request.setAttribute("maxPage",maxPage);
               request.setAttribute("page",page);          
              
               request.setAttribute("pageId",page); 
               
                }catch(Exception e){
                   e.printStackTrace(); 
                }        
            }/*else if (accion!=null && accion.equalsIgnoreCase("imprimir")){
                Long invId = Long.parseLong(request.getParameter("invId"));  
                
                try{
                    twoWaysBDL = new TwoWaysBDL();
                    String cliId= request.getParameter("cliId");
                    List<ItemsInvoicesTO> itemsFacturaList =  twoWaysBDL.getServiceTwoWays().obtenerItemsFactura(invId);
                    
                }catch(Exception e){
                   e.printStackTrace(); 
                }
            }*/
            
        request.getRequestDispatcher("listaFacturas.jsp").forward(request,response);
        }
        
}
