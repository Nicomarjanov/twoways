package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;

import java.awt.Color;
import java.awt.GradientPaint;

import java.io.IOException;
import java.io.OutputStream;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryLabelPositions;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.renderer.category.BarRenderer;
import org.jfree.chart.renderer.category.StandardBarPainter;
import org.jfree.data.category.DefaultCategoryDataset;


public class GraficoPalabrasxAnioServlet extends AutorizacionServlet  {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    private static final String[] nombreMes={"Enero","Febrero","Marzo","Abril","Mayo","Junio","Julio","Agosto","Septiembre","Octubre","Noviembre","Diciembre"};

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        List roles= new ArrayList();
        roles.add("Administrador");
        this.setRolesValidos(roles);
    }  
     
    private DefaultCategoryDataset generarDatos(List anios){
        TwoWaysBDL twoWaysBDL=null;
        DefaultCategoryDataset dataset = new DefaultCategoryDataset();
        //dataset = null;
        try{
            twoWaysBDL = new TwoWaysBDL();
            List <List>consultaList = twoWaysBDL.getServiceTwoWays().obtenerPalabrasxMes(anios);
            Double cantidad = null;
            String etiquetaCol = null;
            String mes = null;
            String anio=null;
            if (consultaList.size() > 0){
                for(int j=0;j<anios.size();j++){
                    for(int i=0;i<consultaList.size();i++){
                        Iterator aux = consultaList.get(i).iterator();
                        etiquetaCol = aux.next().toString();
                        cantidad = Double.parseDouble(aux.next().toString());
                        mes = etiquetaCol.substring(5,7);
                        anio = etiquetaCol.substring(0,4);
                        if (anio.equalsIgnoreCase(anios.get(j).toString()))dataset.setValue(cantidad,anios.get(j).toString(),nombreMes[Integer.parseInt(mes)-1]);
                    }
                }           
            }           
        }
        
        catch(Exception e){
            e.printStackTrace();
        }        
        return dataset;        
    }
    
    
    private static JFreeChart generarGrafico(DefaultCategoryDataset setDatos) {
    
   
    
     JFreeChart jfreechart =  ChartFactory.createBarChart(
     "Palabras por año", "Meses", "Cantidad de palabras",
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

       // String anios[]={"2011","2012","2013"};
        String anios= request.getParameter("anios");
        
        String aniosAux[]=anios.split(",");
        List aniosDatos=new ArrayList();
        for(String aux:aniosAux){
            aniosDatos.add(aux);
        }
        response.setContentType("image/jpeg");
        OutputStream out = response.getOutputStream();

        // Creamos y rellenamos el modelo de datos
        DefaultCategoryDataset setDatos = generarDatos(aniosDatos);
        if(setDatos != null){
            JFreeChart grafico = generarGrafico(setDatos);
           // String fileName = "bar.png";
            
            //String file = getServletContext().getRealPath("/") + fileName;

            try
            {
              //  FileOutputStream fileOut = new FileOutputStream(file);
               ChartUtilities.writeChartAsPNG(out, grafico, 1000, 800);
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
