package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import java.util.TreeMap;

import javax.servlet.*;
import javax.servlet.http.*;

import org.jfree.data.category.DefaultCategoryDataset;

public class PalabrasxAnioServlet extends AutorizacionServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    private static final String[] nombreMes={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        super.init(config);
        List roles= new ArrayList();
        roles.add("Administrador");
        roles.add("Usuario");
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
            twoWaysBDL = new TwoWaysBDL();
            List <List>consultaList = twoWaysBDL.getServiceTwoWays().obtenerPalabrasxMes(anios);
            Map grillaTabla = new TreeMap<String,ArrayList>();
            String etiquetaCol = null;

            String anio=null;
            if (consultaList.size() > 0){

                for(int j=0;j<anios.size();j++){
                    ArrayList meses = new ArrayList<String>();
                    for(int i=0;i<consultaList.size();i++){
                        Iterator aux = consultaList.get(i).iterator();
                        etiquetaCol = aux.next().toString();
                        anio = etiquetaCol.substring(0,4);
                        if (anio.equalsIgnoreCase(anios.get(j).toString())){
                            meses.add(aux.next().toString());                           
                        }
                    }
                    grillaTabla.put(anios.get(j).toString(),meses);
                   
                }
              
              request.setAttribute("tablaxAnio",grillaTabla);                
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }        
        request.getRequestDispatcher("tablaPalabrasxAnio.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType(CONTENT_TYPE);
            doGet(request,response); 
            }
}
