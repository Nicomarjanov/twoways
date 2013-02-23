package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.CurrencyTO;
import com.twoways.to.ItemsExpensesTO;

import java.io.IOException;
import java.io.PrintWriter;

import java.math.BigDecimal;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;
import java.util.Set;

import javax.servlet.*;
import javax.servlet.http.*;

public class GananciasServlet extends AutorizacionServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        List roles= new ArrayList();
        roles.add("Administrador");
        this.setRolesValidos(roles);
    }

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
        super.doGet(request,response);
        TwoWaysBDL twoWaysBDL=null;
        String accion = request.getParameter("accion");
        String mesId = request.getParameter("mesId");  
        String anioId = request.getParameter("anioId");  
        List<CurrencyTO> monedas = null;


    try{
        twoWaysBDL = new TwoWaysBDL();                 
        monedas =  twoWaysBDL.getServiceTwoWays().obtenerMonedas();
        request.setAttribute("listaMoneda",monedas);  
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        request.setAttribute("anioId",sdf.format(cal.getTime())); 
        
        } catch (Exception e) {
        e.printStackTrace();
        }
        if(accion !=null && accion.equalsIgnoreCase("buscar")){
            request.setAttribute("accion",accion);
          
            Map params= new  HashMap();          
            
            if(!request.getParameter("listaMes").equalsIgnoreCase("0") &&( request.getParameter("listaMes").length() > 0 )){ 
              params.put("mesId",request.getParameter("listaMes"));
              request.setAttribute("mesId",request.getParameter("listaMes")); 
            } 

            else{
              request.setAttribute("mesId","0"); 
            }
            
            if(!request.getParameter("listaAnio").equalsIgnoreCase("0") &&( request.getParameter("listaAnio").length() > 0 )){ 
              params.put("anioId",request.getParameter("listaAnio"));
              request.setAttribute("anioId",request.getParameter("listaAnio")); 
            } 
            else{
              request.setAttribute("anioId","0"); 
            }            

            
            Long curId=null;
            if( request.getParameter("curId")!= null ){ 
              curId = Long.parseLong(request.getParameter("curId"));
              request.setAttribute("curId",request.getParameter("curId")); 
            } 
           
           
            try{
              twoWaysBDL = new TwoWaysBDL(); 
              ArrayList mesesIngresos = new ArrayList<BigDecimal>();
              ArrayList mesesEgresos = new ArrayList<BigDecimal>(); 
              ArrayList totalBeneficios = new ArrayList();              
              ArrayList beneficioAcum = new ArrayList();                 
                                          
              List <List> ingresosxCliente =  twoWaysBDL.getServiceTwoWays().findFutureIncomes(params);
                    
              if (ingresosxCliente.size()>0){
                  Double monto = 0D;
                  BigDecimal montoTotal = new BigDecimal("0");
                  BigDecimal totalIng = new BigDecimal("0");
                  int anteriorMes = 0;
                  int mesCelda = 0;

                  for (int j=0;j<13;j++){
                       mesesIngresos.add(0L);                                
                  }

                  SimpleDateFormat mesf = new SimpleDateFormat("MM"); 
                  SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                  
                  for(int i=0;i<ingresosxCliente.size();i++){                                   
                      Iterator aux = ingresosxCliente.get(i).iterator();
                      while(aux.hasNext()){                                              
                          Long curIdDesde = Long.parseLong(aux.next().toString());
                          Date fecha = formatoDeFecha.parse(aux.next().toString());
                          Double montoACob = Double.parseDouble(aux.next().toString());                          
                          mesCelda = Integer.parseInt(mesf.format(fecha));                    
                          Timestamp timestamp = new Timestamp(fecha.getTime());
                          monto = twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curIdDesde , curId,montoACob );
                          totalIng = totalIng.add(BigDecimal.valueOf(monto)).setScale(2,BigDecimal.ROUND_UP);            
                          if (anteriorMes == mesCelda){
                              montoTotal = montoTotal.add(BigDecimal.valueOf(monto)).setScale(2,BigDecimal.ROUND_UP);
                              mesesIngresos.remove(mesCelda-1);
                              mesesIngresos.add(mesCelda-1,montoTotal);
                          }else {
                              mesesIngresos.remove(mesCelda-1);
                              mesesIngresos.add(mesCelda-1,BigDecimal.valueOf(monto).setScale(2,BigDecimal.ROUND_UP));
                              montoTotal = BigDecimal.valueOf(monto);
                          }
                         
                         // grillaIngresos.put("Ingresos",meses);
                          anteriorMes = mesCelda;
                           
                       }
                      mesesIngresos.remove(12);
                      mesesIngresos.add(12,totalIng);
                  }
              }    
             
              List <List> egresosList =  twoWaysBDL.getServiceTwoWays().findFutureExpenses(params);               
           
               if (egresosList.size()>0){
                   Double monto = 0D;
                   BigDecimal montoTotal = new BigDecimal("0");
                   BigDecimal totalEg = new BigDecimal("0");                   
                   int anteriorMes = 0;
                   int mesCelda = 0;
                   for (int j=0;j<13;j++){
                        mesesEgresos.add(0L);                                
                   }
                   SimpleDateFormat mesf = new SimpleDateFormat("MM"); 
                   SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                   
                   for(int i=0;i<egresosList.size();i++){                                   
                       Iterator aux = egresosList.get(i).iterator();
                       while(aux.hasNext()){                                              
                           Long curIdDesde = Long.parseLong(aux.next().toString());
                           Date fecha = formatoDeFecha.parse(aux.next().toString());
                           Double montoAPag = Double.parseDouble(aux.next().toString());                          
                           mesCelda = Integer.parseInt(mesf.format(fecha));                    
                           Timestamp timestamp = new Timestamp(fecha.getTime());
                           monto = twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curIdDesde , curId,montoAPag );
                           totalEg = totalEg.add(BigDecimal.valueOf(monto)).setScale(2,BigDecimal.ROUND_UP);                                      
                           if (anteriorMes == mesCelda){
                               montoTotal = montoTotal.add(BigDecimal.valueOf(monto)).setScale(2,BigDecimal.ROUND_UP);
                               mesesEgresos.remove(mesCelda-1);
                               mesesEgresos.add(mesCelda-1,montoTotal);
                           }else {
                               mesesEgresos.remove(mesCelda-1);
                               mesesEgresos.add(mesCelda-1,BigDecimal.valueOf(monto).setScale(2,BigDecimal.ROUND_UP));
                               montoTotal = BigDecimal.valueOf(monto);
                           }
                   
                           //grillaIngresos.put("Egresos",meses);
                           anteriorMes = mesCelda;
                            
                        }
                       mesesEgresos.remove(12);
                       mesesEgresos.add(12,totalEg);                        
                   }
               }
               
                for (int j=0;j<14;j++){
                     totalBeneficios.add(0D); 
                     if (j != 13)
                         beneficioAcum.add(0D);
                 }
                BigDecimal beneficio = new BigDecimal("0");
                BigDecimal benefAcum = new BigDecimal("0");
                for(int f=0; f < 13 ; f++){
                    beneficio = new BigDecimal("0");
                   
                   beneficio =(BigDecimal.valueOf(Double.valueOf((mesesIngresos.get(f)!=null)?mesesIngresos.get(f).toString():"0")).setScale(2,BigDecimal.ROUND_UP)).subtract((BigDecimal.valueOf(Double.valueOf((mesesEgresos.get(f)!=null)?mesesEgresos.get(f).toString():"0")).setScale(2,BigDecimal.ROUND_UP)));
                   totalBeneficios.remove(f+1);
                   totalBeneficios.add(f+1,beneficio);
                   if (f != 12){
                       benefAcum = benefAcum.add(beneficio);
                       beneficioAcum.remove(f+1);                       
                       beneficioAcum.add(f+1,benefAcum);
                   }
                }
               totalBeneficios.remove(0);
               totalBeneficios.add(0,"Total Beneficios");
               beneficioAcum.remove(0);
               beneficioAcum.add(0,"Beneficio Acumulado");
               request.setAttribute("totalBeneficios",totalBeneficios);
               request.setAttribute("benefAcum",beneficioAcum); 
               request.setAttribute("mesesIngresos",mesesIngresos);
               request.setAttribute("mesesEgresos",mesesEgresos); 

            }catch(Exception e){
               e.printStackTrace(); 
            }        
        }
            
        request.getRequestDispatcher("ganancias.jsp").forward(request,response);
        }
        
        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType(CONTENT_TYPE);
            doGet(request,response); 
            }
        }

