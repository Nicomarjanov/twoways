package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ItemsExpensesTO;

import java.io.IOException;

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

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BalanceServlet extends AutorizacionServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

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
        String anioId = request.getParameter("anioId");        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy");
        request.setAttribute("anioId",sdf.format(cal.getTime())); 
                
        if(accion !=null && accion.equalsIgnoreCase("buscar")){
            request.setAttribute("accion",accion);       
            Map params= new  HashMap();          
            Map params2= new  HashMap();
            Map paramsNull= new  HashMap();
            
            if(!request.getParameter("mesId").equalsIgnoreCase("0")){ 
              params.put("mesId",request.getParameter("mesId"));
              //request.setAttribute("mesId",request.getParameter("listaMes")); 
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
            
            if (request.getParameter("Extracciones") != null && request.getParameter("Extracciones").length() > 0) {
                params2.put("Extracciones",request.getParameter("Extracciones"));
                request.setAttribute("Extracciones",request.getParameter("Extracciones"));
            }
            if (request.getParameter("Cambio") != null && request.getParameter("Cambio").length() > 0) {
                params2.put("Cambio",request.getParameter("Cambio"));
                request.setAttribute("Cambio",request.getParameter("Cambio"));
            }
            if (request.getParameter("Transferencia") != null && request.getParameter("Transferencia").length() > 0) {
                params2.put("Transferencia",request.getParameter("Transferencia"));
                request.setAttribute("Transferencia",request.getParameter("Transferencia"));
            }
           
            try{
               twoWaysBDL = new TwoWaysBDL(); 
                ArrayList totalIngresos = new ArrayList();
                ArrayList totalEgresos = new ArrayList();  
                ArrayList totalBeneficios = new ArrayList();  
                ArrayList totalSaldos = new ArrayList();                  
                ArrayList beneficioAcum = new ArrayList();                 
                paramsNull=null;
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
                
               List <List>saldoList =  twoWaysBDL.getServiceTwoWays().findIncomes(params,"Saldo inicial",paramsNull);
                if (saldoList.size() > 0){
                    request.setAttribute("listaSaldo",saldoList);
                    SimpleDateFormat mf = new SimpleDateFormat("MMyyyy"); 

                    //Iterator iterator = ingresosList.iterator();
                    Long curId = null;
                    for(int i=0;i<saldoList.size();i++){
                        Iterator aux = saldoList.get(i).iterator();
                        while(aux.hasNext()){
                        
                            curId = Long.parseLong(aux.next().toString());
                            aux.remove();
                            aux.next();
                            Double montoEne = Double.parseDouble(aux.next().toString());
                            if (montoEne != null){
                                Date fecha = mf.parse("02"+anioId);//tomo la cotizacion del primer dia del mes siguiente
                                Timestamp timestamp = new Timestamp(fecha.getTime());                       
                                BigDecimal pesosEne = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoEne));
                                totalEne = totalEne.add(pesosEne.setScale(2,BigDecimal.ROUND_UP));
                            }
                            Double montoFeb = Double.parseDouble(aux.next().toString());
                            if (montoFeb != null){
                                Date fecha = mf.parse("03"+anioId);
                                Timestamp timestamp = new Timestamp(fecha.getTime());                       
                                BigDecimal pesosFeb = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoFeb));
                                totalFeb = totalFeb.add(pesosFeb.setScale(2,BigDecimal.ROUND_UP));
                            }
                            Double montoMar = Double.parseDouble(aux.next().toString());
                            if (montoMar != null){
                                Date fecha = mf.parse("04"+anioId);
                                Timestamp timestamp = new Timestamp(fecha.getTime());                       
                                BigDecimal pesosMar = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoMar));
                                totalMar = totalMar.add(pesosMar.setScale(2,BigDecimal.ROUND_UP));
                            }
                            Double montoAbr = Double.parseDouble(aux.next().toString());
                            if (montoAbr != null){
                                Date fecha = mf.parse("05"+anioId);
                                Timestamp timestamp = new Timestamp(fecha.getTime());                       
                                BigDecimal pesosAbr = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoAbr));
                                totalAbr = totalAbr.add(pesosAbr.setScale(2,BigDecimal.ROUND_UP));
                            }
                            Double montoMay = Double.parseDouble(aux.next().toString());
                            if (montoMay != null){
                                Date fecha = mf.parse("06"+anioId);
                                Timestamp timestamp = new Timestamp(fecha.getTime());                       
                                BigDecimal pesosMay = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoMay));
                                totalMay = totalMay.add(pesosMay.setScale(2,BigDecimal.ROUND_UP));
                            }
                            Double montoJun = Double.parseDouble(aux.next().toString());
                            if (montoJun != null){
                                Date fecha = mf.parse("07"+anioId);
                                Timestamp timestamp = new Timestamp(fecha.getTime());                       
                                BigDecimal pesosJun = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoJun));
                                totalJun = totalJun.add(pesosJun.setScale(2,BigDecimal.ROUND_UP));
                            }      
                            Double montoJul = Double.parseDouble(aux.next().toString());
                            if (montoJul != null){
                                Date fecha = mf.parse("08"+anioId);
                                Timestamp timestamp = new Timestamp(fecha.getTime());                       
                                BigDecimal pesosJul = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoJul));
                                totalJul = totalJul.add(pesosJul.setScale(2,BigDecimal.ROUND_UP));
                            }
                            Double montoAgo = Double.parseDouble(aux.next().toString());
                            if (montoAgo != null){
                                Date fecha = mf.parse("09"+anioId);
                                Timestamp timestamp = new Timestamp(fecha.getTime());                       
                                BigDecimal pesosAgo = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoAgo));
                                totalAgo = totalAgo.add(pesosAgo.setScale(2,BigDecimal.ROUND_UP));
                            }
                            Double montoSep = Double.parseDouble(aux.next().toString());
                            if (montoSep != null){
                                Date fecha = mf.parse("10"+anioId);
                                Timestamp timestamp = new Timestamp(fecha.getTime());                       
                                BigDecimal pesosSep = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoSep));
                                totalSep = totalSep.add(pesosSep.setScale(2,BigDecimal.ROUND_UP));
                            }
                            Double montoOct = Double.parseDouble(aux.next().toString());
                            if (montoOct != null){
                                Date fecha = mf.parse("11"+anioId);
                                Timestamp timestamp = new Timestamp(fecha.getTime());                       
                                BigDecimal pesosOct = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoOct));
                                totalOct = totalOct.add(pesosOct.setScale(2,BigDecimal.ROUND_UP));
                            }
                            Double montoNov = Double.parseDouble(aux.next().toString());
                            if (montoNov != null){
                                Date fecha = mf.parse("12"+anioId);
                                Timestamp timestamp = new Timestamp(fecha.getTime());                       
                                BigDecimal pesosNov = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoNov));
                                totalNov = totalNov.add(pesosNov.setScale(2,BigDecimal.ROUND_UP));
                            }
                            Double montoDic = Double.parseDouble(aux.next().toString());
                            if (montoDic != null){
                                String nextAnio = String.valueOf(Long.parseLong(anioId)+1);
                                Date fecha = mf.parse("01"+nextAnio);
                                Timestamp timestamp = new Timestamp(fecha.getTime());                       
                                BigDecimal pesosDic = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoDic));
                                totalDic = totalDic.add(pesosDic.setScale(2,BigDecimal.ROUND_UP));
                            }            
                            Double montoTotal = Double.parseDouble(aux.next().toString());
                            if (montoTotal != null){
                                String nextAnio = String.valueOf(Long.parseLong(anioId)+1);
                                Date fecha = mf.parse("01"+nextAnio);
                                Timestamp timestamp = new Timestamp(fecha.getTime());                       
                                BigDecimal pesosTotal = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoTotal));
                                total = total.add(pesosTotal.setScale(2,BigDecimal.ROUND_UP));
                            }   
                         }
                    }
                    
                    totalSaldos.add("Total en Pesos");
                    totalSaldos.add(totalEne);
                    totalSaldos.add(totalFeb);
                    totalSaldos.add(totalMar);
                    totalSaldos.add(totalAbr);
                    totalSaldos.add(totalMay);
                    totalSaldos.add(totalJun);
                    totalSaldos.add(totalJul);
                    totalSaldos.add(totalAgo);
                    totalSaldos.add(totalSep);
                    totalSaldos.add(totalOct);                   
                    totalSaldos.add(totalNov);
                    totalSaldos.add(totalDic);
                    totalSaldos.add(total);        
                    saldoList.add(totalSaldos);
                    request.setAttribute("saldoPesos",saldoList);                   
                }else{
                    for (int j=0;j<14;j++){
                         totalSaldos.add(0D); 
                    }
                } 
                
               List <List>ingresosList =  twoWaysBDL.getServiceTwoWays().findIncomes(params, "Ingresos", params2);
               
                    
               if (ingresosList.size() > 0){
                   request.setAttribute("listaIngresos",ingresosList);
                   SimpleDateFormat mf = new SimpleDateFormat("MMyyyy"); 
                   totalEne = new BigDecimal("0");
                   totalFeb = new BigDecimal("0");
                   totalMar = new BigDecimal("0");
                   totalAbr = new BigDecimal("0");
                   totalMay = new BigDecimal("0");
                   totalJun = new BigDecimal("0");
                   totalJul = new BigDecimal("0");
                   totalAgo = new BigDecimal("0");
                   totalSep = new BigDecimal("0");
                   totalOct = new BigDecimal("0");
                   totalNov = new BigDecimal("0");
                   totalDic = new BigDecimal("0");  
                   total =  new BigDecimal("0");
                   //Iterator iterator = ingresosList.iterator();
                   Long curId = null;
                   for(int i=0;i<ingresosList.size();i++){
                       Iterator aux = ingresosList.get(i).iterator();
                       while(aux.hasNext()){
                       
                           curId = Long.parseLong(aux.next().toString());
                           aux.remove();
                           aux.next();
                           Double montoEne = Double.parseDouble(aux.next().toString());
                           if (montoEne != null){
                               Date fecha = mf.parse("02"+anioId);//tomo la cotizacion del primer dia del mes siguiente
                               Timestamp timestamp = new Timestamp(fecha.getTime());                       
                               BigDecimal pesosEne = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoEne));
                               totalEne = totalEne.add(pesosEne.setScale(2,BigDecimal.ROUND_UP));
                           }
                           Double montoFeb = Double.parseDouble(aux.next().toString());
                           if (montoFeb != null){
                               Date fecha = mf.parse("03"+anioId);
                               Timestamp timestamp = new Timestamp(fecha.getTime());                       
                               BigDecimal pesosFeb = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoFeb));
                               totalFeb = totalFeb.add(pesosFeb.setScale(2,BigDecimal.ROUND_UP));
                           }
                           Double montoMar = Double.parseDouble(aux.next().toString());
                           if (montoMar != null){
                               Date fecha = mf.parse("04"+anioId);
                               Timestamp timestamp = new Timestamp(fecha.getTime());                       
                               BigDecimal pesosMar = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoMar));
                               totalMar = totalMar.add(pesosMar.setScale(2,BigDecimal.ROUND_UP));
                           }
                           Double montoAbr = Double.parseDouble(aux.next().toString());
                           if (montoAbr != null){
                               Date fecha = mf.parse("05"+anioId);
                               Timestamp timestamp = new Timestamp(fecha.getTime());                       
                               BigDecimal pesosAbr = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoAbr));
                               totalAbr = totalAbr.add(pesosAbr.setScale(2,BigDecimal.ROUND_UP));
                           }
                           Double montoMay = Double.parseDouble(aux.next().toString());
                           if (montoMay != null){
                               Date fecha = mf.parse("06"+anioId);
                               Timestamp timestamp = new Timestamp(fecha.getTime());                       
                               BigDecimal pesosMay = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoMay));
                               totalMay = totalMay.add(pesosMay.setScale(2,BigDecimal.ROUND_UP));
                           }
                           Double montoJun = Double.parseDouble(aux.next().toString());
                           if (montoJun != null){
                               Date fecha = mf.parse("07"+anioId);
                               Timestamp timestamp = new Timestamp(fecha.getTime());                       
                               BigDecimal pesosJun = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoJun));
                               totalJun = totalJun.add(pesosJun.setScale(2,BigDecimal.ROUND_UP));
                           }      
                           Double montoJul = Double.parseDouble(aux.next().toString());
                           if (montoJul != null){
                               Date fecha = mf.parse("08"+anioId);
                               Timestamp timestamp = new Timestamp(fecha.getTime());                       
                               BigDecimal pesosJul = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoJul));
                               totalJul = totalJul.add(pesosJul.setScale(2,BigDecimal.ROUND_UP));
                           }
                           Double montoAgo = Double.parseDouble(aux.next().toString());
                           if (montoAgo != null){
                               Date fecha = mf.parse("09"+anioId);
                               Timestamp timestamp = new Timestamp(fecha.getTime());                       
                               BigDecimal pesosAgo = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoAgo));
                               totalAgo = totalAgo.add(pesosAgo.setScale(2,BigDecimal.ROUND_UP));
                           }
                           Double montoSep = Double.parseDouble(aux.next().toString());
                           if (montoSep != null){
                               Date fecha = mf.parse("10"+anioId);
                               Timestamp timestamp = new Timestamp(fecha.getTime());                       
                               BigDecimal pesosSep = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoSep));
                               totalSep = totalSep.add(pesosSep.setScale(2,BigDecimal.ROUND_UP));
                           }
                           Double montoOct = Double.parseDouble(aux.next().toString());
                           if (montoOct != null){
                               Date fecha = mf.parse("11"+anioId);
                               Timestamp timestamp = new Timestamp(fecha.getTime());                       
                               BigDecimal pesosOct = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoOct));
                               totalOct = totalOct.add(pesosOct.setScale(2,BigDecimal.ROUND_UP));
                           }
                           Double montoNov = Double.parseDouble(aux.next().toString());
                           if (montoNov != null){
                               Date fecha = mf.parse("12"+anioId);
                               Timestamp timestamp = new Timestamp(fecha.getTime());                       
                               BigDecimal pesosNov = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoNov));
                               totalNov = totalNov.add(pesosNov.setScale(2,BigDecimal.ROUND_UP));
                           }
                           Double montoDic = Double.parseDouble(aux.next().toString());
                           if (montoDic != null){
                               String nextAnio = String.valueOf(Long.parseLong(anioId)+1);
                               Date fecha = mf.parse("01"+nextAnio);
                               Timestamp timestamp = new Timestamp(fecha.getTime());                       
                               BigDecimal pesosDic = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoDic));
                               totalDic = totalDic.add(pesosDic.setScale(2,BigDecimal.ROUND_UP));
                           }            
                           Double montoTotal = Double.parseDouble(aux.next().toString());
                           if (montoTotal != null){
                               String nextAnio = String.valueOf(Long.parseLong(anioId)+1);
                               Date fecha = mf.parse("01"+nextAnio);
                               Timestamp timestamp = new Timestamp(fecha.getTime());                       
                               BigDecimal pesosTotal = BigDecimal.valueOf(twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curId , 4L, montoTotal));
                               total = total.add(pesosTotal.setScale(2,BigDecimal.ROUND_UP));
                           }   
                        }
                   }
                   
                   totalIngresos.add("Total en Pesos");
                   totalIngresos.add(totalEne);
                   totalIngresos.add(totalFeb);
                   totalIngresos.add(totalMar);
                   totalIngresos.add(totalAbr);
                   totalIngresos.add(totalMay);
                   totalIngresos.add(totalJun);
                   totalIngresos.add(totalJul);
                   totalIngresos.add(totalAgo);
                   totalIngresos.add(totalSep);
                   totalIngresos.add(totalOct);                   
                   totalIngresos.add(totalNov);
                   totalIngresos.add(totalDic);
                   totalIngresos.add(total);        
                   ingresosList.add(totalIngresos);
                   request.setAttribute("ingresosPesos",ingresosList);                   
               }else{
                   for (int j=0;j<14;j++){
                        totalIngresos.add(BigDecimal.ZERO); 
                   }
               }
               List <ItemsExpensesTO> egresosList =  twoWaysBDL.getServiceTwoWays().findExpenses(params,params2);               
               HashMap grillaEgresos = new HashMap<Integer,ArrayList>();
           
               if (egresosList.size()>0){
                   Long itmId = 0L; 
                   String itmName ="";    
                   Long anteriorItmId = null;                   
                   Double monto = 0D;
                   BigDecimal montoTotal = new BigDecimal("0");
                   int anteriorMes = 0;
                   int mesCelda = 0;

                   ArrayList meses = new ArrayList<BigDecimal>();
                   SimpleDateFormat mf = new SimpleDateFormat("MM"); 
                   SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                   
                   for(ItemsExpensesTO egresosTO:egresosList){
                        itmId = egresosTO.getItemsTO().getItmId();
                        itmName = egresosTO.getItemsTO().getItmName();
                        Date fechaGasto = formatoDeFecha.parse(egresosTO.getExpensesTO().getExpDate().toString());
                        mesCelda = Integer.parseInt(mf.format(fechaGasto));                    
                        Timestamp timestamp = new Timestamp(fechaGasto.getTime());
                        monto = twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, egresosTO.getCurrencyTO().getCurId() , 4L, egresosTO.getExpensesTO().getExpTotal());
                        if (!itmId.equals(anteriorItmId)){
                           meses = new ArrayList<Double>();
                           montoTotal = new BigDecimal("0");
                           for (int i=0;i<12;i++){
                                meses.add("0");                                
                           }
                        }
                        if (anteriorMes == mesCelda){
                            montoTotal = montoTotal.add(BigDecimal.valueOf(monto).setScale(2,BigDecimal.ROUND_UP));
                            meses.remove(mesCelda-1);
                            meses.add(mesCelda-1,montoTotal);
                        }else{
                            meses.remove(mesCelda-1);
                            meses.add(mesCelda-1,BigDecimal.valueOf(monto).setScale(2,BigDecimal.ROUND_UP));
                            montoTotal = new BigDecimal("0");

                        }
                    
                    grillaEgresos.put(itmName,meses);
                    anteriorItmId = itmId;
                    anteriorMes = mesCelda;
                    montoTotal = BigDecimal.valueOf(monto).setScale(2,BigDecimal.ROUND_UP);                   
                   }
     
                   Set <String> grillaKey = grillaEgresos.keySet();
                   BigDecimal totalItem = new BigDecimal("0");
                   totalEne = new BigDecimal("0");
                   totalFeb = new BigDecimal("0");
                   totalMar = new BigDecimal("0");     
                   totalAbr = new BigDecimal("0");               
                   totalMay = new BigDecimal("0");
                   totalJun = new BigDecimal("0");
                   totalJul = new BigDecimal("0");     
                   totalAgo = new BigDecimal("0");               
                   totalSep = new BigDecimal("0");
                   totalOct = new BigDecimal("0");     
                   totalNov = new BigDecimal("0");  
                   totalDic = new BigDecimal("0");  
                   total = new BigDecimal("0");                   
                   
                   for(String key:grillaKey){
                      
                       totalEgresos = new ArrayList();
    
                       for (int j=0;j<13;j++){
                            totalEgresos.add(0D); 
                       }
                       totalItem = new BigDecimal("0");
                       for(int i=0;i<((List)grillaEgresos.get(key)).size();i++){
    
                           totalItem = totalItem.add(BigDecimal.valueOf(Double.valueOf(((List)grillaEgresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                           totalEgresos.remove(i+1);
                           switch(i){
                           case 0:
                               totalEne = totalEne.add(BigDecimal.valueOf(Double.valueOf(((List)grillaEgresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                               totalEgresos.add(i+1,totalEne);
                               break;
                           case 1:
                               totalFeb = totalFeb.add(BigDecimal.valueOf(Double.valueOf(((List)grillaEgresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                               totalEgresos.add(i+1,totalFeb);
                               break;
                           case 2:
                               totalMar = totalMar.add(BigDecimal.valueOf(Double.valueOf(((List)grillaEgresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                               totalEgresos.add(i+1,totalMar);
                               break;
                           case 3:
                               totalAbr = totalAbr.add(BigDecimal.valueOf(Double.valueOf(((List)grillaEgresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                               totalEgresos.add(i+1,totalAbr);
                               break;
                           case 4:
                               totalMay = totalMay.add(BigDecimal.valueOf(Double.valueOf(((List)grillaEgresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                               totalEgresos.add(i+1,totalMay);
                               break;
                           case 5:
                               totalJun = totalJun.add(BigDecimal.valueOf(Double.valueOf(((List)grillaEgresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                               totalEgresos.add(i+1,totalJun);
                               break;
                           case 6:
                               totalJul = totalJul.add(BigDecimal.valueOf(Double.valueOf(((List)grillaEgresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                               totalEgresos.add(i+1,totalJul);
                               break;
                           case 7:
                               totalAgo = totalAgo.add(BigDecimal.valueOf(Double.valueOf(((List)grillaEgresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                               totalEgresos.add(i+1,totalAgo);
                               break;
                           case 8:
                               totalSep = totalSep.add(BigDecimal.valueOf(Double.valueOf(((List)grillaEgresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                               totalEgresos.add(i+1,totalSep);
                               break;
                           case 9:
                               totalOct = totalOct.add(BigDecimal.valueOf(Double.valueOf(((List)grillaEgresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                               totalEgresos.add(i+1,totalOct);
                               break;
                           case 10:
                               totalNov = totalNov.add(BigDecimal.valueOf(Double.valueOf(((List)grillaEgresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                               totalEgresos.add(i+1,totalNov);
                               break;
                           case 11:
                               totalDic = totalDic.add(BigDecimal.valueOf(Double.valueOf(((List)grillaEgresos.get(key)).get(i).toString())).setScale(2,BigDecimal.ROUND_UP));
                               totalEgresos.add(i+1,totalDic);
                               break;                
                           }
                       }
                       total = total.add(totalItem.setScale(2,BigDecimal.ROUND_UP)); 
                       ((List)grillaEgresos.get(key)).add(totalItem);
                   }
                   totalEgresos.remove(0); 
                   totalEgresos.add(0,"Total Egresos");     
                   totalEgresos.add(total);
                   request.setAttribute("listaEgresos",grillaEgresos);   
                   request.setAttribute("totalEgresos",totalEgresos);
               
               for (int j=0;j<14;j++){
                    totalBeneficios.add(0D); 
                    if (j != 13)
                        beneficioAcum.add(0D);
                }
               BigDecimal beneficio = new BigDecimal("0");
               BigDecimal benefAcum = new BigDecimal("0");
               for(int f=1; f < 14 ; f++){
                   beneficio = new BigDecimal("0");
                   
                   beneficio =(BigDecimal.valueOf(Double.valueOf(totalSaldos.get(f).toString())).setScale(2,BigDecimal.ROUND_UP)).add((BigDecimal.valueOf(Double.valueOf(totalIngresos.get(f).toString())).setScale(2,BigDecimal.ROUND_UP)).subtract(BigDecimal.valueOf(Double.valueOf(totalEgresos.get(f).toString())).setScale(2,BigDecimal.ROUND_UP)));
                   
                   totalBeneficios.remove(f);
                   totalBeneficios.add(f,beneficio);
                   if (f != 13){
                       benefAcum = benefAcum.add(beneficio);
                       beneficioAcum.remove(f);                       
                       beneficioAcum.add(f,benefAcum);
                   }
               }
               totalBeneficios.remove(0);
               totalBeneficios.add(0,"Total Beneficios");   
               beneficioAcum.remove(0);
               beneficioAcum.add(0,"Beneficio Acumulado");
               request.setAttribute("totalBeneficios",totalBeneficios); 
               request.setAttribute("benefAcum",beneficioAcum);
               }
            }catch(Exception e){
               e.printStackTrace(); 
            }        
        }
            
        request.getRequestDispatcher("balance.jsp").forward(request,response);
        }
        
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType(CONTENT_TYPE);
            doGet(request,response); 
            }
}

