package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.servlet.*;
import javax.servlet.http.*;

public class PalabrasxClienteServlet extends AutorizacionServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    private static final String[] nombreMes={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        super.init(config);
        List roles= new ArrayList();
        roles.add("Administrador");
        this.setRolesValidos(roles);
    }

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
        super.doGet(request,response);
        
        TwoWaysBDL twoWaysBDL=null;
        try{
            Integer anioInicial=2012;
            
            Calendar cal = Calendar.getInstance();
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
            Integer anioActual=Integer.parseInt(sdf.format(cal.getTime())); 
            List anios= new ArrayList();
            for(int i=0;;i++){
                if(anioInicial <= anioActual){
                    anios.add(String.valueOf(anioInicial));
                    anioInicial++;
                }else break; 
            }
            Map grillaTabla = new HashMap<String,Map>();
            for(int j=0;j<anios.size();j++){
                twoWaysBDL = new TwoWaysBDL();            
                List <List>consultaList = twoWaysBDL.getServiceTwoWays().obtenerPalabrasxCliente(anios.get(j).toString());
                
                if (consultaList.size() > 0){
                    Map cantClientes = new TreeMap<String,Long>();
                    for(int i=0;i<consultaList.size();i++){
                        List aux = consultaList.get(i);
                        
                        List cantidades=new ArrayList();
                        for(int h=1;h<13;h++){
                            cantidades.add(aux.get(h));
                        }
                        cantClientes.put(aux.get(0).toString(),cantidades);
                    }
                    grillaTabla.put(anios.get(j).toString(),cantClientes);
                }
            }
            request.setAttribute("tablaxCliente",grillaTabla);                
            
            
        }catch(Exception e){
            e.printStackTrace();
        }        
        request.getRequestDispatcher("tablaPalabrasxCliente.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType(CONTENT_TYPE);
            doGet(request,response); 
            }
}
