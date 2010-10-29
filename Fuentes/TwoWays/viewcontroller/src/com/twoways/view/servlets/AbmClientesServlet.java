package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ClientsTO;
import com.twoways.to.CurrencyTO;
import com.twoways.to.EmployeesTO;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class AbmClientesServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, IOException {
                      
        response.setContentType(CONTENT_TYPE);
        String accion=request.getParameter("accion");
        List<CurrencyTO> monedas = null;
        ClientsTO cliente = new ClientsTO(); 
        String cliId = request.getParameter("cliId"); 
        TwoWaysBDL twoWaysBDL=null;
        
        try {
           twoWaysBDL = new TwoWaysBDL();
           twoWaysBDL.getServiceTwoWays().obtenerMonedas();
           monedas =  twoWaysBDL.getServiceTwoWays().obtenerMonedas();
           request.setAttribute("listaMoneda",monedas);
           
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        
        if (accion!=null && accion.equalsIgnoreCase("guardar")){
             try {
                 
                
                 
                 if(cliId != null && cliId.length() > 0 ) 
                      cliente =  twoWaysBDL.getServiceTwoWays().getClientById(cliId);
                  
                 
             } catch (Exception e) {
                 e.printStackTrace();
             }
            
          
            
            CurrencyTO currency= new CurrencyTO(); 
            currency.setCurId((request.getParameter("listaMoneda")!= null && request.getParameter("listaMoneda").length() > 0 )?Long.parseLong(request.getParameter("listaMoneda")):0);
            cliente.setCliAddress((request.getParameter("dirCliente")!= null )?request.getParameter("dirCliente"):"");
            cliente.setCliCountry((request.getParameter("paisCliente")!= null )?request.getParameter("paisCliente"):"");
            cliente.setCliDescription((request.getParameter("descCliente")!= null )?request.getParameter("descCliente"):"");
            cliente.setCliMail((request.getParameter("mailCliente")!= null )?request.getParameter("mailCliente"):"");
            cliente.setCliName((request.getParameter("nomCliente")!= null )?request.getParameter("nomCliente"):"");
            cliente.setCliPhone((request.getParameter("telCliente")!= null )?request.getParameter("telCliente"):"");
            cliente.setCliPostalCode((request.getParameter("cpCliente")!= null && request.getParameter("cpCliente").length() > 0 )?Long.parseLong(request.getParameter("cpCliente")):0);
            cliente.setCurrencyTO(currency);

            try {
                
                if(cliId != null && cliId.length() > 0 ){ 
                    twoWaysBDL.getServiceTwoWays().updateCliente(cliente);
                    request.setAttribute("cliente",cliente);
                   
                }else{
                    twoWaysBDL.getServiceTwoWays().insertarCliente(cliente);
                    
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
           
            request.setAttribute("mensaje","<script>alert('El cliente se guardo con exito')</script>");
            
            
            
        }
        else  if(cliId != null && cliId.length() > 0  && (accion == null || (accion!=null && !accion.equalsIgnoreCase("cancelar")) )){
          
       
             try {
                     cliente =  twoWaysBDL.getServiceTwoWays().getClientById(cliId);
                     request.setAttribute("cliente",cliente);
                     if(cliente == null){
                         request.setAttribute("mensaje","<script>alert('El cliente no existe')</script>"); 
                     }
                 
             } catch (Exception e) {
                 request.setAttribute("mensaje","<script>alert('Error al cargar el cliente')</script>"); 
                 e.printStackTrace();
             }
        }
        
        request.getRequestDispatcher("cliente.jsp").forward(request,response);
    }
    
    public void doPost(HttpServletRequest request, 
                       HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
        doGet(request,response); 
    }
}