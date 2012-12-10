package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ClientsTO;
import com.twoways.to.CurrencyTO;
import com.twoways.to.EmployeesTO;
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

import javax.servlet.*;
import javax.servlet.http.*;

public class APagarxEmpleadoServlet extends AutorizacionServlet {
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
        List<EmployeesTO> empleados = null;
                
        try{
            twoWaysBDL = new TwoWaysBDL();                
            empleados = twoWaysBDL.getServiceTwoWays().obtenerEmpleados();
            request.setAttribute("listaEmpleados",empleados);  
            
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
 
            if(request.getParameter("listaEmpleados") != null && request.getParameter("listaEmpleados").length() > 0  ){    
              Long empId= Long.parseLong(request.getParameter("listaEmpleados"));
              params.put("empId",empId);
              request.setAttribute("empId",empId);              
            }
            else{
              request.setAttribute("empId","0"); 
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
          
            Long curId=null;
            if( request.getParameter("curId")!= null ){ 
              curId = Long.parseLong(request.getParameter("curId"));
              request.setAttribute("curId",request.getParameter("curId")); 
            } 
                
                try{
                   List <List> pagosxEmpleado =  twoWaysBDL.getServiceTwoWays().findFuturePayments(params);
                   HashMap grillaPagos = new HashMap<Integer,ArrayList>();
                   ArrayList totalPagosEmp = new ArrayList(); 
                
                   if (pagosxEmpleado.size()>0){
                       request.setAttribute("pagosxEmpleado",pagosxEmpleado);
                       SimpleDateFormat mf = new SimpleDateFormat("MMyyyy"); 
                       double totalEne = 0;
                       double totalFeb = 0;
                       double totalMar = 0;
                       double totalAbr = 0;
                       double totalMay = 0;
                       double totalJun = 0;
                       double totalJul = 0;
                       double totalAgo = 0;
                       double totalSep = 0;
                       double totalOct = 0;
                       double totalNov = 0;
                       double totalDic = 0;
                       double total =  0;

                       Long empId;    
                       String empFirstName;
                       //String empLastName;
                       Long anteriorEmpId = null;
                       double monto = 0;
                       double montoTotal = 0;
                       int anteriorMes = 0;
                       int mesCelda = 0;

                       ArrayList meses = new ArrayList<Double>();
                       SimpleDateFormat mesf = new SimpleDateFormat("MM"); 
                       SimpleDateFormat diaf = new SimpleDateFormat("dd");
                       SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                       
                       for(int i=0;i<pagosxEmpleado.size();i++){                                   
                           Iterator aux = pagosxEmpleado.get(i).iterator();
                           while(aux.hasNext()){
                           
                               empId = Long.parseLong(aux.next().toString());
                               empFirstName = aux.next().toString() + ' '+ aux.next().toString();
                               double montoAPag = Double.parseDouble(aux.next().toString());                        
                               Long curIdDesde = Long.parseLong(aux.next().toString());
                               Date fecha = formatoDeFecha.parse(aux.next().toString());
                               mesCelda = Integer.parseInt(mesf.format(fecha));     
                               //if (Integer.parseInt(diaf.format(fecha)) > 25)mesCelda++;
                               Timestamp timestamp = new Timestamp(fecha.getTime());
                               monto = twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curIdDesde , curId,montoAPag);
                               
                               BigDecimal bd = new BigDecimal(Double.toString(monto));
                               bd = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
                               monto= bd.doubleValue();
                               
                               if (anteriorEmpId == null && !empId.equals(anteriorEmpId)){
                                  meses = new ArrayList<Double>();
                                  montoTotal =0;
                                  for (int j=0;j<12;j++){
                                       meses.add(0D);                                
                                  }
                               }
                
                               if (anteriorEmpId != null && empId.equals(anteriorEmpId) && anteriorMes == mesCelda){
                                   montoTotal = montoTotal+monto;
                                   BigDecimal bdm = new BigDecimal(Double.toString(montoTotal));
                                   bdm = bdm.setScale(2, BigDecimal.ROUND_HALF_UP);
                                   montoTotal=bdm.doubleValue();
                                   meses.remove(mesCelda-1);
                                   meses.add(mesCelda-1,montoTotal);
                               }else {
                                   meses.remove(mesCelda-1);
                                   meses.add(mesCelda-1,monto);
                                   montoTotal = monto;
                               }
                
                               grillaPagos.put(empFirstName,meses);
                               anteriorEmpId = empId;
                               anteriorMes = mesCelda;
                                
                            }
                       }
                       
                       totalPagosEmp.add("Total en Pesos");
                       totalPagosEmp.add(totalEne);
                       totalPagosEmp.add(totalFeb);
                       totalPagosEmp.add(totalMar);
                       totalPagosEmp.add(totalAbr);
                       totalPagosEmp.add(totalMay);
                       totalPagosEmp.add(totalJun);
                       totalPagosEmp.add(totalJul);
                       totalPagosEmp.add(totalAgo);
                       totalPagosEmp.add(totalSep);
                       totalPagosEmp.add(totalOct);
                       totalPagosEmp.add(totalNov);
                       totalPagosEmp.add(totalDic);
                       totalPagosEmp.add(total);
                       pagosxEmpleado.add(totalPagosEmp);
                       request.setAttribute("pagosxEmpleado",pagosxEmpleado);
                       }else{
                           for (int j=0;j<14;j++){
                            totalPagosEmp.add(0D); 
                           }
                       }
                    

                   Set <String> grillaKey = grillaPagos.keySet();
                   double totalEnero = 0;
                   double totalFeb = 0;
                   double totalMar = 0;     
                   double totalAbr = 0;               
                   double totalMay = 0;
                   double totalJun = 0;
                   double totalJul = 0;     
                   double totalAgo = 0;               
                   double totalSep = 0;
                   double totalOct = 0;     
                   double totalNov = 0;  
                   double totalDic = 0;  
                   double totalTotal = 0;     
                   
                   for(String key:grillaKey){
                       totalPagosEmp = new ArrayList();

                       for (int j=0;j<13;j++){
                            totalPagosEmp.add(0D); 
                       }
                       double totalCli = 0;
                       for(int i=0;i<((List)grillaPagos.get(key)).size();i++){
                          totalCli = totalCli + Double.valueOf(((List)grillaPagos.get(key)).get(i).toString());                            
                          totalPagosEmp.remove(i+1);
                           switch(i){
                           case 0:
                               totalEnero = totalEnero+ Double.valueOf(((List)grillaPagos.get(key)).get(i).toString());
                               totalPagosEmp.add(i+1,totalEnero);
                               break;
                           case 1:
                               totalFeb = totalFeb+Double.valueOf(((List)grillaPagos.get(key)).get(i).toString());
                               totalPagosEmp.add(i+1,totalFeb);
                               break;
                           case 2:
                               totalMar = totalMar+Double.valueOf(((List)grillaPagos.get(key)).get(i).toString());
                               totalPagosEmp.add(i+1,totalMar);
                               break;
                           case 3:
                               totalAbr = totalAbr+Double.valueOf(((List)grillaPagos.get(key)).get(i).toString());
                               totalPagosEmp.add(i+1,totalAbr);
                               break;
                           case 4:
                               totalMay = totalMay+Double.valueOf(((List)grillaPagos.get(key)).get(i).toString());
                               totalPagosEmp.add(i+1,totalMay);
                               break;
                           case 5:
                               totalJun = totalJun+Double.valueOf(((List)grillaPagos.get(key)).get(i).toString());
                               totalPagosEmp.add(i+1,totalJun);
                               break;
                           case 6:
                               totalJul = totalJul+Double.valueOf(((List)grillaPagos.get(key)).get(i).toString());
                               totalPagosEmp.add(i+1,totalJul);
                               break;
                           case 7:
                               totalAgo = totalAgo+Double.valueOf(((List)grillaPagos.get(key)).get(i).toString());
                               totalPagosEmp.add(i+1,totalAgo);
                               break;
                           case 8:
                               totalSep = totalSep+Double.valueOf(((List)grillaPagos.get(key)).get(i).toString());
                               totalPagosEmp.add(i+1,totalSep);
                               break;
                           case 9:
                               totalOct = totalOct+Double.valueOf(((List)grillaPagos.get(key)).get(i).toString());
                               totalPagosEmp.add(i+1,totalOct);
                               break;
                           case 10:
                               totalNov = totalNov+Double.valueOf(((List)grillaPagos.get(key)).get(i).toString());
                               totalPagosEmp.add(i+1,totalNov);
                               break;
                           case 11:
                               totalDic = totalDic+Double.valueOf(((List)grillaPagos.get(key)).get(i).toString());
                               totalPagosEmp.add(i+1,totalDic);
                               break;                
                           }
                       }
                       totalTotal = totalTotal+totalCli; 
                       ((List)grillaPagos.get(key)).add(totalCli);
                   }
                   totalPagosEmp.remove(0); 
                   totalPagosEmp.add(0,"Total a pagar");     
                   totalPagosEmp.add(totalTotal);               
                   request.setAttribute("pagosxEmpleado",grillaPagos);   
                   request.setAttribute("totalPagosEmpleado",totalPagosEmp);                                  
                 
                   }catch(Exception e){
                       e.printStackTrace(); 
                    }
                }
                
                request.getRequestDispatcher("apagarxempleado.jsp").forward(request,response);
                }
                
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType(CONTENT_TYPE);
        doGet(request,response); 
    }
}