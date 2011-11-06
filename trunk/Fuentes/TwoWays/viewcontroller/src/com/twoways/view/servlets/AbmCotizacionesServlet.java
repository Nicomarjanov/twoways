package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.CotizationsTO;
import com.twoways.to.CurrencyTO;
import com.twoways.to.ItemsTO;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class AbmCotizacionesServlet extends AutorizacionServlet {
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
        List<CurrencyTO> monedas = null;
        CotizationsTO cotization = new CotizationsTO(); 
        CurrencyTO currency = new CurrencyTO();

        Calendar c = new GregorianCalendar();
        String dia;
        String mes;
        String annio;
        dia = Integer.toString(c.get(Calendar.DATE));
        mes = Integer.toString(c.get(Calendar.MONTH)+1);
        annio = Integer.toString(c.get(Calendar.YEAR));
        if (dia.length()==1){
            dia="0"+dia;
        }
        if (mes.length()==1){
            mes="0"+mes;
        }
        String fecha=(""+dia+"/"+mes+"/"+annio+" 00:00");
        request.setAttribute("auxDate",fecha); 
       
        TwoWaysBDL twoWaysBDL=null;
        
        try {
           twoWaysBDL = new TwoWaysBDL();
            monedas =  twoWaysBDL.getServiceTwoWays().obtenerMonedas();
            request.setAttribute("listaMoneda",monedas);
                                     
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        if (accion!=null && accion.equalsIgnoreCase("guardar")){
                                
            currency.setCurId((request.getParameter("listaMoneda")!= null && request.getParameter("listaMoneda").length() > 0 )?Long.parseLong(request.getParameter("listaMoneda")):0);
            cotization.setCurrencyTO(currency);
            cotization.setCucValue((request.getParameter("cotValue")!= null )?Double.parseDouble(request.getParameter("cotValue").replace(",",".")):0);
            try {
                if(request.getParameter("cotDate")!= null && !request.getParameter("cotDate").equalsIgnoreCase("") ){ 
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    java.util.Date date = sdf.parse(request.getParameter("cotDate"));
                    java.sql.Timestamp timest = new java.sql.Timestamp(date.getTime()); 
                    cotization.setCucDate(timest);
            }
            
            } catch (Exception e) {
                request.setAttribute("mensaje","<script>alert('La fecha ingresada no es valida')</script>"); 
                e.printStackTrace();                    
            }

            try {
                
                   twoWaysBDL.getServiceTwoWays().insertarCotizacion(cotization);
                    
                
            } catch (Exception e) {
                e.printStackTrace();
            }
           
            request.setAttribute("mensaje","<script>alert('La cotización de la moneda se guardó con éxito')</script>");                    
            
        }
         
        request.getRequestDispatcher("cotizaciones.jsp").forward(request,response);    
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType(CONTENT_TYPE);
            doGet(request,response); 
            }
}

