package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;

import java.awt.Color;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;

public class GraficoPalabrasxClienteServlet extends AutorizacionServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    private static final String[] nombreMes={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        List roles= new ArrayList();
        roles.add("Administrador");
        roles.add("Usuario");
        this.setRolesValidos(roles);
    }  
     
    private DefaultCategoryDataset generarDatos(String anio){
        TwoWaysBDL twoWaysBDL=null;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //dataset = null;
        try{
            twoWaysBDL = new TwoWaysBDL();
            List <List>consultaList = twoWaysBDL.getServiceTwoWays().obtenerPalabrasxCliente(anio);
            if (consultaList.size() > 0){
                for(int i=0;i<consultaList.size();i++){
                    List aux = consultaList.get(i);
                    for(int h=1;h<13;h++){
                        dataset.setValue(Long.parseLong(aux.get(h).toString()),aux.get(0).toString(),nombreMes[h-1]);
                    }
                    
                }
            }         
        }
        
        catch(Exception e){
            e.printStackTrace();
        }        
        return dataset;        
    }
    
    
    private static JFreeChart generarGrafico(DefaultCategoryDataset setDatos,String anio) {
        
     JFreeChart jfreechart =  ChartFactory.createBarChart(
     "Palabras por cliente - "+anio, "Meses", "Cantidad de palabras",
     setDatos, PlotOrientation.VERTICAL,
     true, true, false);

     jfreechart.setBackgroundPaint(Color.lightGray);
     
     //GradientPaint gradientpaint0 = new GradientPaint(0.0F, 0.0F, new Color(209, 228, 246), 0.0F, 0.0F, new Color(82, 141, 201));
     StandardBarPainter standarpaint = new StandardBarPainter();
     
     // get a reference to the plot for further customisation...
     final CategoryPlot plot = jfreechart.getCategoryPlot();
     plot.setNoDataMessage("Sin Datos!");
     plot.getDomainAxis().setCategoryMargin(0.4);
     plot.getDomainAxis().setCategoryLabelPositions(CategoryLabelPositions.UP_45);;
     
     BarRenderer r = (BarRenderer)plot.getRenderer();
    // r.setSeriesPaint(0, gradientpaint0);
     r.setItemMargin(0.0);
     r.setBarPainter(standarpaint);
     r.setShadowVisible(false);
     r.setDrawBarOutline(false);
     

     plot.setRenderer(r);
    
     return jfreechart;
     }     
     
     
    
     
    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, IOException {
        super.doGet(request,response);

        //String anios[]={"2011","2012","2013"};
        String anio= request.getParameter("anio");
       // List anios=new ArrayList();
        
        response.setContentType("image/jpeg");
        OutputStream out = response.getOutputStream();

        // Creamos y rellenamos el modelo de datos
        DefaultCategoryDataset setDatos = generarDatos(anio);
        if(setDatos != null){
            JFreeChart grafico = generarGrafico(setDatos,anio);
           // String fileName = "bar.png";
            
            //String file = getServletContext().getRealPath("/") + fileName;

            try
            {
              //  FileOutputStream fileOut = new FileOutputStream(file);
               ChartUtilities.writeChartAsPNG(out, grafico, 1600, 1400);
            } catch(Exception e){
                   e.printStackTrace(); 
            }                
        }
        out.close();        
                
    }
    

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType(CONTENT_TYPE);
            doGet(request,response); 
            }
            

    }
