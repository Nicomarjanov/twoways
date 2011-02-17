package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.AccountsTO;
import com.twoways.to.CurrencyTO;
import com.twoways.to.EmployeeTypeTO;
import com.twoways.to.EmployeesRatesTO;
import com.twoways.to.EmployeesTO;
import com.twoways.to.EmployeesTypesTO;
import com.twoways.to.ExpensesTO;
import com.twoways.to.ItemsExpensesTO;
import com.twoways.to.ItemsTO;

import com.twoways.to.LanguaguesAcronymsTO;
import com.twoways.to.LanguaguesTO;
import com.twoways.to.RateTypesTO;

import com.twoways.to.RatesTO;
import com.twoways.to.TranslatorsLanguaguesTO;

import com.twoways.to.UsersTO;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
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
        List<EmployeesTO> empleados = null;
        List<ItemsTO> items = null;   
        List<AccountsTO> cuentas = null;  
        ExpensesTO gasto = new ExpensesTO(); 

        String expId = request.getParameter("expId"); 
        
        TwoWaysBDL twoWaysBDL=null;
        
    try {
        twoWaysBDL = new TwoWaysBDL();
        monedas =  twoWaysBDL.getServiceTwoWays().obtenerMonedas();
        request.setAttribute("listaMoneda",monedas);
       
        empleados =  twoWaysBDL.getServiceTwoWays().obtenerEmpleados();
        request.setAttribute("listaEmpleados",empleados);
        
        items =  twoWaysBDL.getServiceTwoWays().obtenerItem("Gastos");
        request.setAttribute("listaItems",items);     
        
        cuentas =  twoWaysBDL.getServiceTwoWays().obtenerAccount();
        request.setAttribute("listaCuentas",cuentas);        

       
    } catch (Exception e) {
       e.printStackTrace();
    }
    
    if (accion!=null && accion.equalsIgnoreCase("guardar")){
        EmployeesTO empleado= new EmployeesTO();         
        empleado.setEmpId((request.getParameter("nombreEmp")!= null && request.getParameter("nombreEmp").length() > 0 )?Long.parseLong(request.getParameter("nombreEmp")):0);
        gasto.setEmployeesTO(empleado);          
        
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
               accTO.setAccId(Long.parseLong(atribs[2]));
               itmExpTO.setAccountsTO(accTO);    
               itmExpTO.setIteValue(Double.parseDouble(atribs[3].replaceAll(",",".")));                              
               UsersTO user= (UsersTO)request.getSession().getAttribute("userSession"); 
               itmExpTO.setUsersTO(user);
               itmExpTOList.add(itmExpTO);                 
           }                     
           gasto.setItemsExpensesTOList(itmExpTOList);
        }
        
        try {             
            if(expId != null && expId.length() > 0) {
                gasto.setExpId(Long.parseLong(expId));
                twoWaysBDL.getServiceTwoWays().updateGasto(gasto);
            }else{
                twoWaysBDL.getServiceTwoWays().insertarGasto(gasto);           
            }
                /* ItemsExpensesTO itmExps = new ItemsExpensesTO();
                 itmExps =  twoWaysBDL.getServiceTwoWays().getItemsExpenseByExpId(Long.parseLong(expId));        */          
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
      else if(expId != null && expId.length() > 0  && (accion!=null && accion.equalsIgnoreCase("eliminar")) ){
          try {
               if(expId != null && expId.length() > 0 ) 
                   gasto =  twoWaysBDL.getServiceTwoWays().getExpenseById(Long.parseLong(expId));                                           
          } catch (Exception e) {
              e.printStackTrace();
          }
          String itmGastosHidden[]=request.getParameterValues("item-gasto-hidden");
          List<ItemsExpensesTO> itmExpTOList = new ArrayList<ItemsExpensesTO>();
                                 
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
                  accTO.setAccId(Long.parseLong(atribs[2]));
                  itmExpTO.setAccountsTO(accTO);    
                  itmExpTO.setIteValue(Double.parseDouble(atribs[3].replaceAll(",",".")));                              
                  UsersTO user= (UsersTO)request.getSession().getAttribute("userSession"); 
                  itmExpTO.setUsersTO(user);
                  itmExpTOList.add(itmExpTO);       
          }
          try {
              gasto.setItemsExpensesTOList(itmExpTOList);
              twoWaysBDL.getServiceTwoWays().deleteGasto(gasto);

              }                    
          catch (Exception e) {
              e.printStackTrace();
              request.setAttribute("mensaje","<script>alert('Ocurrió un error al eliminar el item de gasto')</script>");
              request.getRequestDispatcher("empleados.jsp").forward(request,response);
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
              // request.getRequestDispatcher("empleados.jsp").forward(request,response);
           }
      }
      else {
          request.setAttribute("expId","");
      }        
      request.getRequestDispatcher("gastos.jsp").forward(request,response);

  }


    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType(CONTENT_TYPE);
            doGet(request,response); 
            }
}

