package com.twoways.view.servlets;


import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.AccountsTO;

import java.io.IOException;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class AbmCuentasServlet extends AutorizacionServlet {
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
        AccountsTO cuenta = new AccountsTO(); 
        String accId = request.getParameter("accId"); 
        
        TwoWaysBDL twoWaysBDL=null;
        
        try {
           twoWaysBDL = new TwoWaysBDL();
                         
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        if (accion!=null && accion.equalsIgnoreCase("guardar")){
                                
//            currency.setCurId((request.getParameter("listaMoneda")!= null && request.getParameter("listaMoneda").length() > 0 )?Long.parseLong(request.getParameter("listaMoneda")):0);
            cuenta.setAccName((request.getParameter("accNombre")!= null )?request.getParameter("accNombre"):"");
            cuenta.setAccNumber((request.getParameter("accNumero")!= null )?request.getParameter("accNumero"):"");
            cuenta.setAccDescription((request.getParameter("descAcc")!= null )?request.getParameter("descAcc"):"");
            cuenta.setAccDetails((request.getParameter("detalleAcc")!= null )?request.getParameter("detalleAcc"):"");

            try {
                
                if(accId != null && accId.length() > 0 ){ 
                    cuenta.setAccId(Long.parseLong(accId));
                    cuenta = twoWaysBDL.getServiceTwoWays().actualizarAccount(cuenta);                    
                   
                }else{
                    cuenta = twoWaysBDL.getServiceTwoWays().insertarAccount(cuenta);
                    
                }
                request.setAttribute("cuenta",cuenta);
                
            } catch (Exception e) {
                e.printStackTrace();
            }
           
            request.setAttribute("mensaje","<script>alert('La cuenta se guardó con éxito')</script>");                    
            
        }
            else  if(accId != null && accId.length() > 0  && (accion == null || (accion!=null && !accion.equalsIgnoreCase("cancelar")) ))
            {
                 try {
                         cuenta =  twoWaysBDL.getServiceTwoWays().getAccountById(accId);
                         request.setAttribute("cuenta",cuenta);
                         if(cuenta == null){
                             request.setAttribute("mensaje","<script>alert('La cuenta no existe')</script>"); 
                         }
                     
                 } catch (Exception e) {
                     request.setAttribute("mensaje","<script>alert('Error al cargar la cuenta')</script>"); 
                     e.printStackTrace();
                 }
            }          

        request.getRequestDispatcher("cuentas.jsp").forward(request,response);    
    }
    
    
}

