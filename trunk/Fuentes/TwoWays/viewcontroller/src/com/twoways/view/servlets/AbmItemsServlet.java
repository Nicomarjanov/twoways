package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ItemsTO;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class AbmItemsServlet extends AutorizacionServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        List roles= new ArrayList();
        roles.add("Administrador");
        this.setRolesValidos(roles);
    }

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, IOException {
                      
        super.doGet(request,response);
        
        response.setContentType(CONTENT_TYPE);
        String accion=request.getParameter("accion");
        ItemsTO item = new ItemsTO(); 
        //List tipoItem= null; 
        String itmId = request.getParameter("itmId"); 
        
        TwoWaysBDL twoWaysBDL=null;
        
        try {
           twoWaysBDL = new TwoWaysBDL();
                         
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        if (accion!=null && accion.equalsIgnoreCase("guardar")){
                                
//            currency.setCurId((request.getParameter("listaMoneda")!= null && request.getParameter("listaMoneda").length() > 0 )?Long.parseLong(request.getParameter("listaMoneda")):0);
            item.setItemName((request.getParameter("itmNombre")!= null )?request.getParameter("itmNombre"):"");
            //rateType.setRtyName((request.getParameter("tipoitem")!= null )?request.getParameter("tipoitem"):"");
            item.setItemDescription((request.getParameter("descItem")!= null )?request.getParameter("descItem"):"");
            item.setItemType((request.getParameter("tipoItem")!= null )?request.getParameter("tipoItem"):"");

            try {
                
                if(itmId != null && itmId.length() > 0 ){ 
                    item.setItemId(Long.parseLong(itmId));
                    twoWaysBDL.getServiceTwoWays().actualizarItem(item);
                    
                    request.setAttribute("item",item);
                   
                }else{
                    twoWaysBDL.getServiceTwoWays().insertarItem(item);
                    
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
           
            request.setAttribute("mensaje","<script>alert('El item se guardo con exito')</script>");                    
            
        }
            else  if(itmId != null && itmId.length() > 0  && (accion == null || (accion!=null && !accion.equalsIgnoreCase("cancelar")) ))
            {
              
            
                 try {
                         item =  twoWaysBDL.getServiceTwoWays().getItemById(itmId);
                         request.setAttribute("item",item);
                         if(item == null){
                             request.setAttribute("mensaje","<script>alert('El item no existe')</script>"); 
                         }
                     
                 } catch (Exception e) {
                     request.setAttribute("mensaje","<script>alert('Error al cargar el item')</script>"); 
                     e.printStackTrace();
                 }
            }          

        request.getRequestDispatcher("items.jsp").forward(request,response);    
    }
    
    public void doPost(HttpServletRequest request, 
                       HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
        doGet(request,response); 
    }
}

