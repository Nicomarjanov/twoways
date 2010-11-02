package com.twoways.view.servlets;


import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.CurrencyTO;
import com.twoways.to.RatesTO;
import com.twoways.to.RateTypesTO;

import java.io.IOException;

import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class AbmTarifasServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, IOException {
                      
        response.setContentType(CONTENT_TYPE);
        String accion=request.getParameter("accion");
        List<CurrencyTO> monedas = null;
        RatesTO tarifa = new RatesTO(); 
        List<RateTypesTO> tipoTarifa= null; 
        String ratId = request.getParameter("ratId"); 
        
        TwoWaysBDL twoWaysBDL=null;
        
        try {
           twoWaysBDL = new TwoWaysBDL();
           twoWaysBDL.getServiceTwoWays().obtenerMonedas();
           monedas =  twoWaysBDL.getServiceTwoWays().obtenerMonedas();
           request.setAttribute("listaMoneda",monedas);
           
           twoWaysBDL.getServiceTwoWays().obtenerTipoTarifas();
           tipoTarifa =  twoWaysBDL.getServiceTwoWays().obtenerTipoTarifas();
           request.setAttribute("tipoTarifa",tipoTarifa);
               
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        
        if (accion!=null && accion.equalsIgnoreCase("guardar")){
                                
            CurrencyTO currency= new CurrencyTO(); 
            RatesTO rate= new RatesTO();
            RateTypesTO rateType = new RateTypesTO();
//            rate.setRatId((request.getParameter("listaTarifa")!= null && request.getParameter("listaTarifa").length() > 0 )?Long.parseLong(request.getParameter("listTarifa")):0);
            currency.setCurId((request.getParameter("listaMoneda")!= null && request.getParameter("listaMoneda").length() > 0 )?Long.parseLong(request.getParameter("listaMoneda")):0);
            tarifa.setRatName((request.getParameter("nomTarifa")!= null )?request.getParameter("nomTarifa"):"");
            rateType.setRtyName((request.getParameter("tipoTarifa")!= null )?request.getParameter("tipoTarifa"):"");
            tarifa.setRatDescription((request.getParameter("descTarifa")!= null )?request.getParameter("descTarifa"):"");
            tarifa.setCurrencyTO(currency);

            try {
                
                if(ratId != null && ratId.length() > 0 ){ 
                    twoWaysBDL.getServiceTwoWays().actualizarTarifa(tarifa);
                    request.setAttribute("tarifa",tarifa);
                   
                }else{
                    twoWaysBDL.getServiceTwoWays().insertarTarifa(tarifa);
                    
                }
                
            } catch (Exception e) {
                e.printStackTrace();
            }
           
            request.setAttribute("mensaje","<script>alert('La tarifa se guardo con exito')</script>");                    
            
        }
        else          
            request.getRequestDispatcher("tarifa.jsp").forward(request,response);
    }
    
    public void doPost(HttpServletRequest request, 
                       HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
        doGet(request,response); 
    }
}

