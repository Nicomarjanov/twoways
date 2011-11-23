package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ClientsTO;
import com.twoways.to.InvoicesTO;

import com.twoways.to.ItemsInvoicesTO;

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

public class ListaItemsFacturaServlet extends AutorizacionServlet {
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
               Long invId= Long.parseLong(request.getParameter("invId"));
               List<ItemsInvoicesTO> itemsFacturaList =  twoWaysBDL.getServiceTwoWays().obtenerItemsFactura(invId);
               int  pageTop=(page+1)*10 ;
               int  minPage=(page)*10 ;
               List subfacturas = null;
                
                
               if(itemsFacturaList.size() > pageTop){ 
                 subfacturas= (itemsFacturaList.size() > pageTop )?itemsFacturaList.subList(minPage,pageTop):itemsFacturaList.subList(minPage,itemsFacturaList.size());       
               }else{
                   subfacturas=itemsFacturaList;
                   pageTop =subfacturas.size();
                   minPage=(pageTop/10)*10;
                   if(pageTop==minPage){
                       minPage=pageTop-10;
                   }
                   if(minPage > 0){ 
                      subfacturas = (itemsFacturaList.size() > pageTop )?itemsFacturaList.subList(minPage,pageTop):itemsFacturaList.subList(minPage,itemsFacturaList.size());       
                   }else{
                       pageTop=1;
                       minPage=1; 
                       page=0;
                       
                   }
               }
                          
               int maxPage = 0;
               if (itemsFacturaList.size()== 10) maxPage =1;
               else maxPage =(int)(itemsFacturaList.size() / 10) + 1;
               request.setAttribute("listaItemsFactura",subfacturas);
               request.setAttribute("maxPage",maxPage);
               request.setAttribute("page",page);                       
               request.setAttribute("pageId",page); 
               request.setAttribute("invId",invId);               
            }catch(Exception e){
               e.printStackTrace(); 
            }        
            
            
         request.getRequestDispatcher("listaItemsFactura.jsp").forward(request,response);
    }
}
            
