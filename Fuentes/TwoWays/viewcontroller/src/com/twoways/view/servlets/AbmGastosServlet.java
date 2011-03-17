package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.AccountsTO;
import com.twoways.to.CurrencyTO;
import com.twoways.to.EmployeesTO;
import com.twoways.to.ExpensesTO;
import com.twoways.to.ItemsExpensesTO;
import com.twoways.to.ItemsTO;

import com.twoways.to.UsersTO;

import java.io.IOException;

import java.math.BigDecimal;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AbmGastosServlet extends AutorizacionServlet {
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
        List<ItemsTO> items = null;   
        List<AccountsTO> cuentas = null;  
        ExpensesTO gasto = new ExpensesTO(); 

        String expId = request.getParameter("expId"); 
        String itmDate = request.getParameter("itmDate");
        
        
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
      String fecha=(""+dia+"/"+mes+"/"+annio);
      request.setAttribute("auxDate",fecha); 

        //String usuario = request.getSession().getAttribute("userSession");
        
        TwoWaysBDL twoWaysBDL=null;
        
    try {
        twoWaysBDL = new TwoWaysBDL();
        monedas =  twoWaysBDL.getServiceTwoWays().obtenerMonedas();
        request.setAttribute("listaMoneda",monedas);
             
        items =  twoWaysBDL.getServiceTwoWays().obtenerItem("Gastos");
        request.setAttribute("listaItems",items);     
        
        cuentas =  twoWaysBDL.getServiceTwoWays().obtenerAccount();
        request.setAttribute("listaCuentas",cuentas);        
        
        UsersTO userTO= (UsersTO)request.getSession().getAttribute("userSession"); 
        request.setAttribute("expUsuario",userTO.getUsrId());
        
               
    } catch (Exception e) {
       e.printStackTrace();
    }
    
    if (accion!=null && accion.equalsIgnoreCase("guardar")){     
        
        try {
        if(request.getParameter("expFecha")!= null && !request.getParameter("expFecha").equalsIgnoreCase("") ){ 
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            java.util.Date date = sdf.parse(request.getParameter("expFecha"));
            java.sql.Timestamp timest = new java.sql.Timestamp(date.getTime()); 
            gasto.setExpDate(timest);
        }
        
        } catch (Exception e) {
            request.setAttribute("mensaje","<script>alert('La fecha ingresada no es valida')</script>"); 
            e.printStackTrace();
            request.getRequestDispatcher("gastos.jsp").forward(request,response);
        }                           
        
        String itmGastosHidden[]=request.getParameterValues("item-gasto-hidden");
        List<ItemsExpensesTO> itmExpTOList = new ArrayList<ItemsExpensesTO>();
        
        if( itmGastosHidden  != null){ 
                              
           for(String aux:itmGastosHidden){               
               String atribs[]= aux.split("#");
               ItemsExpensesTO itmExpTO = new ItemsExpensesTO();
               ItemsTO itmTO = new ItemsTO();
               itmTO.setItmId(Long.parseLong(atribs[0]));
               itmExpTO.setItemsTO(itmTO);
               CurrencyTO curTO = new CurrencyTO();
               curTO.setCurId(Long.parseLong(atribs[1]));
               itmExpTO.setCurrencyTO(curTO);
               AccountsTO accTO = new AccountsTO();
               accTO.setAccId(Long.parseLong(atribs[3]));
               itmExpTO.setAccountsTO(accTO);    
               itmExpTO.setIteValue(Double.parseDouble(atribs[2].replaceAll(",",".")));                              
               UsersTO user= new UsersTO(); 
               user.setUsrId(atribs[4]);
               itmExpTO.setUsersTO(user);
               if(expId != null && expId.length() > 0) {
                    ExpensesTO expenses = new ExpensesTO();
                    expenses.setExpId(Long.parseLong(expId));
                    itmExpTO.setExpensesTO(expenses);
               }
              // System.out.print(atribs[5]);
               
               if(atribs.length == 6){
                   itmExpTO.setIteId(Long.parseLong(atribs[5]));
               }
               itmExpTOList.add(itmExpTO);
           }                     
           gasto.setItemsExpensesTOList(itmExpTOList);
        }
        
        try {             
            if(expId != null && expId.length() > 0) {
                gasto.setExpId(Long.parseLong(expId));
                twoWaysBDL.getServiceTwoWays().updateGasto(gasto);
                request.setAttribute("mensaje","<script>alert('El item de gasto se guardó con éxito')</script>");                
            }else{
                twoWaysBDL.getServiceTwoWays().insertarGasto(gasto); 
                request.setAttribute("mensaje","<script>alert('El item de gasto se guardó con éxito')</script>");
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
      }    
      else if(expId != null && expId.length() > 0  && (accion!=null && accion.equalsIgnoreCase("eliminar")) ){

          try {
              twoWaysBDL.getServiceTwoWays().deleteGasto(expId);
              }                    
          catch (Exception e) {
              e.printStackTrace();
              request.setAttribute("mensaje","<script>alert('Ocurrió un error al eliminar la planilla de gasto')</script>");
          }
          request.setAttribute("mensaje","<script>alert('El item de gasto se eliminó con éxito')</script>");
      }
      else if(expId != null && expId.length() > 0  && (accion == null ))//|| (accion!=null && !accion.equalsIgnoreCase("cancelar")) ))
      {                        
           try {
                   gasto =  twoWaysBDL.getServiceTwoWays().getExpenseById(Long.parseLong(expId));
                   
                   request.setAttribute("gasto",gasto);
                   
                   ItemsExpensesTO itmExps = new ItemsExpensesTO();
                   itmExps =  twoWaysBDL.getServiceTwoWays().getItemsExpenseByExpId(Long.parseLong(expId));                        
                   if (itmExps != null){
                       request.setAttribute("itemsExpense",itmExps);
                   }
                   if(gasto == null){
                       request.setAttribute("mensaje","<script>alert('El item de gasto no existe')</script>"); 
                   }else{
                       request.setAttribute("script","<script>init();</script>");
                   }
               
           } catch (Exception e) {
               e.printStackTrace();                 
               request.setAttribute("mensaje","<script>alert('Error al cargar el item de gasto')</script>"); 
           }
      }
      else if(itmDate != null && itmDate.length() > 0  && (accion != null  && accion.equalsIgnoreCase("BuscarItemFecha") ))//|| (accion!=null && !accion.equalsIgnoreCase("cancelar")) ))
       {                        
            try {                                    
                List itmExpDate = null;                    
                String auxExpId = null;
                itmExpDate =  twoWaysBDL.getServiceTwoWays().getItemsExpenseByDate(itmDate);  
                               
                if(itmExpDate != null && itmExpDate.size() > 0){
                    request.setAttribute("itemsExpense",itmExpDate);
                    auxExpId= ((HashMap)itmExpDate.get(0)).get("EXP_ID").toString();
                    request.setAttribute("auxExpId",auxExpId);    
                    request.setAttribute("script","<script>init();</script>");
                }else{
                    request.setAttribute("mensaje","<script>alert('No existen resultados de gastos para esa fecha')</script>"); 
                }
                request.setAttribute("auxDate",itmDate);                    
            } catch (Exception e) {
                e.printStackTrace();                 
                request.setAttribute("mensaje","<script>alert('Error al buscar el item de gasto')</script>"); 
            }                                    
       }
      else {
          //request.setAttribute("expId","");
          try {                                    
              List itmExpDate = null;                    
              String auxExpId = null;
              itmExpDate =  twoWaysBDL.getServiceTwoWays().getItemsExpenseByDate(fecha);  
                             
              if(itmExpDate != null && itmExpDate.size() > 0){
                  request.setAttribute("itemsExpense",itmExpDate);
                  auxExpId= ((HashMap)itmExpDate.get(0)).get("EXP_ID").toString();
                  request.setAttribute("auxExpId",auxExpId);    
                  request.setAttribute("script","<script>init();</script>");
              }/*else{
                  request.setAttribute("mensaje","<script>alert('No existen resultados de gastos para esa fecha')</script>"); 
              }*/
              request.setAttribute("auxDate",fecha);                    
          } catch (Exception e) {
              e.printStackTrace();                 
              request.setAttribute("mensaje","<script>alert('Error al buscar el item de gasto')</script>"); 
          }       
      }        
      request.getRequestDispatcher("gastos.jsp").forward(request,response);
  }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType(CONTENT_TYPE);
            doGet(request,response); 
            }
}

