package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ClientResponsableTO;
import com.twoways.to.ClientsRatesTO;
import com.twoways.to.ClientsTO;
import com.twoways.to.CurrencyTO;
import com.twoways.to.EmployeeTypeTO;
import com.twoways.to.EmployeesRatesTO;
import com.twoways.to.EmployeesTypesTO;
import com.twoways.to.RateTypesTO;
import com.twoways.to.RatesTO;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

public class AbmClientesServlet extends AutorizacionServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        List roles= new ArrayList();
        roles.add("Administrador");
        roles.add("Usuario");
        this.setRolesValidos(roles);
    }

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, IOException {
                              
        super.doGet(request,response);
        
        response.setContentType(CONTENT_TYPE);
        String accion=request.getParameter("accion");
        List<CurrencyTO> monedas = null;
        ClientsTO cliente = new ClientsTO(); 
        String cliId = request.getParameter("cliId"); 

        List<RatesTO> tarifas = null;
        
        TwoWaysBDL twoWaysBDL=null;
        
        try {
           twoWaysBDL = new TwoWaysBDL();
           monedas =  twoWaysBDL.getServiceTwoWays().obtenerMonedas();
           request.setAttribute("listaMoneda",monedas);
           /*
           RateTypesTO rateType = new RateTypesTO();
           rateType.setRtyName("Cliente");*/
           tarifas =  twoWaysBDL.getServiceTwoWays().getRateByType();
           request.setAttribute("listaTarifa",tarifas);
           
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
            
            String tarifasHidden[]=request.getParameterValues("tarifas-hidden");
            List<ClientsRatesTO> clientsRatesTOList = new   ArrayList<ClientsRatesTO>();
            
            if( tarifasHidden  != null){ 
                               
                for(String aux:tarifasHidden){
                    
                    String atribs[]= aux.split("#");
                    
                    ClientsRatesTO clientsRatesTO = new ClientsRatesTO();
                    clientsRatesTO.setClrValue(Float.parseFloat(atribs[1].replaceAll(",",".")));
                    RatesTO rtTO= new RatesTO();
                    rtTO.setRatId(Long.parseLong(atribs[0]));
                    clientsRatesTO.setRatesTO(rtTO);
                    clientsRatesTOList.add(clientsRatesTO);
                    clientsRatesTO.setClientsTO(cliente);
                }
            }  
            String responsableHidden[]=request.getParameterValues("responsable-hidden");
            List<ClientResponsableTO> clientsResponsableTOList = new   ArrayList<ClientResponsableTO>();            
            if( responsableHidden  != null){ 

                for(String auxiliar:responsableHidden){
                    
                    String atributos[]= auxiliar.split("#");
                    
                    ClientResponsableTO clientResponsableTO = new ClientResponsableTO();
                    clientResponsableTO.setCreFirstName(atributos[0]);
                    clientResponsableTO.setCreLastName(atributos[1]);
                    clientResponsableTO.setCreEmail((atributos[2] != null && atributos[2].length()> 0)?atributos[2]:"");
                    clientResponsableTO.setCrePhoneNumber((atributos[3]!= null && atributos[3].length()> 0)?atributos[3]:"");
                    clientResponsableTO.setCreMsn((atributos[4]!= null && atributos[4].length()> 0)?atributos[4]:"");
                    clientResponsableTO.setCreSkype((atributos[5]!= null && atributos[5].length()> 0)?atributos[5]:"");
                   // clientResponsableTO.setCreId(Long.parseLong((atributos[6]!= null || atributos[6].length()== 0)?atributos[6]:""));
                    clientsResponsableTOList.add(clientResponsableTO);
                    clientResponsableTO.setClientsTO(cliente);
                }
            }
            
            CurrencyTO currency= new CurrencyTO(); 
            //RatesTO rate= new RatesTO();
            //rate.setRatId((request.getParameter("listaTarifa")!= null && request.getParameter("listaTarifa").length() > 0 )?Long.parseLong(request.getParameter("listTarifa")):0);
            currency.setCurId((request.getParameter("listaMoneda")!= null && request.getParameter("listaMoneda").length() > 0 )?Long.parseLong(request.getParameter("listaMoneda")):0);
            cliente.setCliAddress((request.getParameter("dirCliente")!= null )?request.getParameter("dirCliente"):"");
            cliente.setCliCountry((request.getParameter("paisCliente")!= null )?request.getParameter("paisCliente"):"");
            cliente.setCliDescription((request.getParameter("descCliente")!= null )?request.getParameter("descCliente"):"");
            cliente.setCliMail((request.getParameter("mailCliente")!= null )?request.getParameter("mailCliente"):"");
            cliente.setCliName((request.getParameter("nomCliente")!= null )?request.getParameter("nomCliente"):"");
            cliente.setCliPhone((request.getParameter("telCliente")!= null )?request.getParameter("telCliente"):"");
            cliente.setCliPostalCode((request.getParameter("cpCliente")!= null )?request.getParameter("cpCliente"):"");
            cliente.setCurrencyTO(currency);
            cliente.setClientsRatesTOList(clientsRatesTOList);
            cliente.setClientResponsableTOList(clientsResponsableTOList);

            try {
                
                if(cliId != null && cliId.length() > 0 ){ 
                    cliente =twoWaysBDL.getServiceTwoWays().updateCliente(cliente);                    
                    //request.setAttribute("cliente",cliente);                    
                   
                }else{
                    String nomCli = request.getParameter("nomCliente");
                    ClientsTO resultado = twoWaysBDL.getServiceTwoWays().getClientByName(nomCli);
                    if (resultado == null) {
                        cliente =twoWaysBDL.getServiceTwoWays().insertarCliente(cliente);
                    }else{
                        request.setAttribute("mensaje","<script>alert('Existe un cliente con ese nombre')</script>");
                        request.getRequestDispatcher("cliente.jsp").forward(request,response);
                    }
                   // request.setAttribute("cliente",cliente);
                   
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("mensaje","<script>alert('El cliente no pudo guardarse')</script>"); 
                request.getRequestDispatcher("cliente.jsp").forward(request,response);
            }
            request.setAttribute("script","<script>init();</script>");
            request.setAttribute("mensaje","<script>alert('El cliente se guard� con �xito')</script>");
   
        }
        else if(cliId != null && cliId.length() > 0  && (accion!=null && accion.equalsIgnoreCase("eliminar")) ){
            try {
                 if(cliId != null && cliId.length() > 0 ) 
                     cliente =  twoWaysBDL.getServiceTwoWays().getClientById(cliId);                                           
            } catch (Exception e) {
                e.printStackTrace();
            }
            String tarifasHidden[]=request.getParameterValues("tarifas-hidden");
            List<ClientsRatesTO> clientsRatesTOList = new   ArrayList<ClientsRatesTO>();
            
            if( tarifasHidden  != null){ 
                                   
                for(String aux:tarifasHidden){
                    
                    String atribs[]= aux.split("#");
                    
                    ClientsRatesTO clientsRatesTO = new ClientsRatesTO();
                    clientsRatesTO.setClrValue(Float.parseFloat(atribs[1].replaceAll(",",".")));
                    RatesTO rtTO= new RatesTO();
                    rtTO.setRatId(Long.parseLong(atribs[0]));
                    clientsRatesTO.setRatesTO(rtTO );
                    clientsRatesTOList.add(clientsRatesTO);
                    clientsRatesTO.setClientsTO(cliente);
                }
            }     
            
            try {
                cliente.setClientsRatesTOList(clientsRatesTOList);
                twoWaysBDL.getServiceTwoWays().deleteClients(cliente);
                }                    
            catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("mensaje","<script>alert('Ocurri� un error al eliminar el cliente')</script>");
                request.getRequestDispatcher("cliente.jsp").forward(request,response);
            }
            request.setAttribute("mensaje","<script>alert('El cliente se elimin� con �xito')</script>");
        }
        else  if(cliId != null && cliId.length() > 0  && (accion == null || (accion!=null && !accion.equalsIgnoreCase("cancelar")) )){
          
       
             try {
                     cliente =  twoWaysBDL.getServiceTwoWays().getClientById(cliId);
                     request.setAttribute("cliente",cliente);

                     if(cliente == null){
                         request.setAttribute("mensaje","<script>alert('El cliente no existe')</script>"); 
                     }else{
                         request.setAttribute("script","<script>init();</script>");
                     }
                 
             } catch (Exception e) {
                 request.setAttribute("mensaje","<script>alert('Error al cargar el cliente')</script>"); 
                 e.printStackTrace();
                 
             }
        }
        
        request.getRequestDispatcher("cliente.jsp").forward(request,response);
    }
    
}
