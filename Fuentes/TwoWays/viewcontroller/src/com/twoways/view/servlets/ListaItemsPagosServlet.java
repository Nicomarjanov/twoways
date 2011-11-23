package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ItemsInvoicesTO;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class ListaItemsPagosServlet extends AutorizacionServlet {
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
            
            int page =(request.getParameter("pageId")  != null && request.getParameter("pageId").length() >0  )?Integer.parseInt(request.getParameter("pageId")): 0 ;  
            
                     
                try{
                   twoWaysBDL = new TwoWaysBDL();
                   Long payId= Long.parseLong(request.getParameter("payId"));
                   List<ItemsInvoicesTO> itemsPagoList =  twoWaysBDL.getServiceTwoWays().obtenerItemsPago(payId);
                   int  pageTop=(page+1)*10 ;
                   int  minPage=(page)*10 ;
                   List subpagos = null;
                    
                    
                   if(itemsPagoList.size() > pageTop){ 
                     subpagos= (itemsPagoList.size() > pageTop )?itemsPagoList.subList(minPage,pageTop):itemsPagoList.subList(minPage,itemsPagoList.size());       
                   }else{
                       subpagos=itemsPagoList;
                       pageTop =subpagos.size();
                       minPage=(pageTop/10)*10;
                       if(pageTop==minPage){
                           minPage=pageTop-10;
                       }
                       if(minPage > 0){ 
                          subpagos = (itemsPagoList.size() > pageTop )?itemsPagoList.subList(minPage,pageTop):itemsPagoList.subList(minPage,itemsPagoList.size());       
                       }else{
                           pageTop=1;
                           minPage=1; 
                           page=0;
                           
                       }
                   }
                    int maxPage = 0;
                    if (itemsPagoList.size()== 10) maxPage =1;
                    else maxPage =(int)(itemsPagoList.size() / 10) + 1;                             
                    request.setAttribute("listaItemsPagos",subpagos);
                    request.setAttribute("maxPage",maxPage);
                    request.setAttribute("page",page);                    
                    request.setAttribute("pageId",page); 
                    request.setAttribute("payId",payId);
                }catch(Exception e){
                   e.printStackTrace(); 
                }        
                
                
             request.getRequestDispatcher("listaItemsPagos.jsp").forward(request,response);
        }
    }
