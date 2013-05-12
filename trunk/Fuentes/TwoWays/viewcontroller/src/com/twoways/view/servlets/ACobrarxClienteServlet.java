package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ClientsTO;
import com.twoways.to.CurrencyTO;
import com.twoways.to.InvoicesTO;

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

import java.util.TreeMap;

import javax.servlet.*;
import javax.servlet.http.*;

public class ACobrarxClienteServlet extends AutorizacionServlet {
    private static final String CONTENT_TYPE = "text/html; charset=ISO-8859-1";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        List roles= new ArrayList();
        roles.add("Administrador");
        this.setRolesValidos(roles);
    }

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, 
                                                           IOException {
        super.doGet(request,response);
        TwoWaysBDL twoWaysBDL=null;
        String accion = request.getParameter("accion");    
        List<CurrencyTO> monedas = null;
        List<ClientsTO> clientes = null;
            
        try{
            twoWaysBDL = new TwoWaysBDL();                
            clientes = twoWaysBDL.getServiceTwoWays().obtenerClientes();
            request.setAttribute("listaClientes",clientes);  
            
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
    
            if(request.getParameter("listaClientes") != null && request.getParameter("listaClientes").length() > 0  ){             
              Long cliId= Long.parseLong(request.getParameter("listaClientes"));    
              params.put("cliId",cliId);
              request.setAttribute("cliId",cliId);             
            }
            else{
              request.setAttribute("cliId","0"); 
            }
            
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

            if(request.getParameter("cobrado") != null &&  request.getParameter("noCobrado") != null){
                  params.put("getIt","sino");
                  request.setAttribute("noCobrado",request.getParameter("noCobrado"));              
                  request.setAttribute("cobrado",request.getParameter("cobrado"));
            } else {if(request.getParameter("cobrado") != null){
                    params.put("getIt","si");
                    request.setAttribute("cobrado",request.getParameter("cobrado"));
                   }else if(request.getParameter("noCobrado") != null){
                        params.put("getIt","no");    
                        request.setAttribute("noCobrado",request.getParameter("noCobrado"));              
                    }
            }
        
        
            Long curId=null;
            if( request.getParameter("curId")!= null ){ 
              curId = Long.parseLong(request.getParameter("curId"));
              request.setAttribute("curId",request.getParameter("curId")); 
            } 

                
            try{
               List <List> ingresosxCliente =  twoWaysBDL.getServiceTwoWays().findFutureIncomesByClient(params);
               Map grillaIngresos = new TreeMap<Integer,ArrayList>();
               ArrayList totalIngresosCli = new ArrayList(); 
            
               if (ingresosxCliente.size()>0){
                   request.setAttribute("ingresosxCliente",ingresosxCliente);
                   BigDecimal totalEne = new BigDecimal("0");
                   BigDecimal totalFeb = new BigDecimal("0");
                   BigDecimal totalMar = new BigDecimal("0");
                   BigDecimal totalAbr = new BigDecimal("0");
                   BigDecimal totalMay = new BigDecimal("0");
                   BigDecimal totalJun = new BigDecimal("0");
                   BigDecimal totalJul = new BigDecimal("0");
                   BigDecimal totalAgo = new BigDecimal("0");
                   BigDecimal totalSep = new BigDecimal("0");
                   BigDecimal totalOct = new BigDecimal("0");
                   BigDecimal totalNov = new BigDecimal("0");
                   BigDecimal totalDic = new BigDecimal("0");  
                   BigDecimal total =  new BigDecimal("0");

                   Long cliId;    
                   String cliName;
                   Long anteriorCliId = null;
                   Double monto = 0D;
                   BigDecimal montoTotal = new BigDecimal("0");
                   int anteriorMes = 0;
                   int mesCelda = 0;

                   ArrayList meses = new ArrayList<Double>();
                   SimpleDateFormat mesf = new SimpleDateFormat("MM"); 
                   SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                   
                   for(int i=0;i<ingresosxCliente.size();i++){                                   
                       Iterator aux = ingresosxCliente.get(i).iterator();                  
                       cliId = Long.parseLong(aux.next().toString());
                       cliName = aux.next().toString();
                       Double montoACob = Double.parseDouble(aux.next().toString());                        
                       Long curIdDesde = Long.parseLong(aux.next().toString());
                       Date fecha = formatoDeFecha.parse(aux.next().toString());
                       mesCelda = Integer.parseInt(mesf.format(fecha));                    
                       Timestamp timestamp = new Timestamp(fecha.getTime());
                       monto = twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curIdDesde , curId,montoACob );
                       
                       if (!cliId.equals(anteriorCliId)){
                          meses = new ArrayList<Double>();
                          montoTotal = new BigDecimal("0");
                          for (int j=0;j<12;j++){
                               meses.add(0L);                                
                          }
                       }

                       if (cliId.equals(anteriorCliId) && anteriorMes == mesCelda){
                           montoTotal = montoTotal.add(BigDecimal.valueOf(monto)).setScale(2,BigDecimal.ROUND_UP);
                           meses.remove(mesCelda-1);
                           meses.add(mesCelda-1,montoTotal);
                       }else {
                           meses.remove(mesCelda-1);
                           meses.add(mesCelda-1,BigDecimal.valueOf(monto).setScale(2,BigDecimal.ROUND_UP));
                           montoTotal = BigDecimal.valueOf(monto);
                       }

                       grillaIngresos.put(cliName,meses);
                       anteriorCliId = cliId;
                       anteriorMes = mesCelda;
                            
                   }
                   
                   for (int j=0;j<13;j++){
                    totalIngresosCli.add(0D); 
                   }             
                  

                   Set <String> grillaKey = grillaIngresos.keySet();
                   
                       for(String key:grillaKey){
                           totalIngresosCli = new ArrayList();

                           for (int j=0;j<13;j++){
                                totalIngresosCli.add(0D); 
                           }
                           BigDecimal totalCli = new BigDecimal("0");
                           for(int i=0;i<((List)grillaIngresos.get(key)).size();i++){
                              totalCli = totalCli.add(BigDecimal.valueOf(Double.valueOf(((List)grillaIngresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));                            
                              totalIngresosCli.remove(i+1);
                               switch(i){
                               case 0:
                                   totalEne = totalEne.add(BigDecimal.valueOf(Double.valueOf(((List)grillaIngresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                                   totalIngresosCli.add(i+1,totalEne);
                                   break;
                               case 1:
                                   totalFeb = totalFeb.add(BigDecimal.valueOf(Double.valueOf(((List)grillaIngresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                                   totalIngresosCli.add(i+1,totalFeb);
                                   break;
                               case 2:
                                   totalMar = totalMar.add(BigDecimal.valueOf(Double.valueOf(((List)grillaIngresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                                   totalIngresosCli.add(i+1,totalMar);
                                   break;
                               case 3:
                                   totalAbr = totalAbr.add(BigDecimal.valueOf(Double.valueOf(((List)grillaIngresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                                   totalIngresosCli.add(i+1,totalAbr);
                                   break;
                               case 4:
                                   totalMay = totalMay.add(BigDecimal.valueOf(Double.valueOf(((List)grillaIngresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                                   totalIngresosCli.add(i+1,totalMay);
                                   break;
                               case 5:
                                   totalJun = totalJun.add(BigDecimal.valueOf(Double.valueOf(((List)grillaIngresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                                   totalIngresosCli.add(i+1,totalJun);
                                   break;
                               case 6:
                                   totalJul = totalJul.add(BigDecimal.valueOf(Double.valueOf(((List)grillaIngresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                                   totalIngresosCli.add(i+1,totalJul);
                                   break;
                               case 7:
                                   totalAgo = totalAgo.add(BigDecimal.valueOf(Double.valueOf(((List)grillaIngresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                                   totalIngresosCli.add(i+1,totalAgo);
                                   break;
                               case 8:
                                   totalSep = totalSep.add(BigDecimal.valueOf(Double.valueOf(((List)grillaIngresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                                   totalIngresosCli.add(i+1,totalSep);
                                   break;
                               case 9:
                                   totalOct = totalOct.add(BigDecimal.valueOf(Double.valueOf(((List)grillaIngresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                                   totalIngresosCli.add(i+1,totalOct);
                                   break;
                               case 10:
                                   totalNov = totalNov.add(BigDecimal.valueOf(Double.valueOf(((List)grillaIngresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                                   totalIngresosCli.add(i+1,totalNov);
                                   break;
                               case 11:
                                   totalDic = totalDic.add(BigDecimal.valueOf(Double.valueOf(((List)grillaIngresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                                   totalIngresosCli.add(i+1,totalDic);
                                   break;                
                               }
                           }
                           total = total.add(totalCli.setScale(2,BigDecimal.ROUND_UP)); 
                           ((List)grillaIngresos.get(key)).add(totalCli);
                       }
                       totalIngresosCli.remove(0); 
                       totalIngresosCli.add(0,"Total a cobrar");     
                       totalIngresosCli.add(total);               
                   }else{
                       for (int j=0;j<14;j++){
                        totalIngresosCli.add(0D); 
                       }
                   }
                
               request.setAttribute("ingresosxCliente",grillaIngresos);   
               request.setAttribute("totalIngresosCliente",totalIngresosCli);                                  
            
               }catch(Exception e){
                   e.printStackTrace(); 
                }
            }
            
        request.getRequestDispatcher("acobrarxcliente.jsp").forward(request,response);
        }
        
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType(CONTENT_TYPE);
            doGet(request,response); 
            }
    }
