package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.AccountsTO;
import com.twoways.to.CurrencyTO;
import com.twoways.to.ExpensesTO;
import com.twoways.to.ItemsExpensesTO;
import com.twoways.to.ItemsTO;
import com.twoways.to.UsersTO;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class AbmGastosServlet extends AutorizacionServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    //private static final String TMP_DIR_PATH = System.getProperty("java.io.tmpdir");
    
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
      //String itmDate = request.getParameter("itmDate");
      String mesId = request.getParameter("mesId");  
      String anioId = request.getParameter("anioId");
        
        
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
     /* String fecha=(""+dia+"/"+mes+"/"+annio);
      request.setAttribute("auxDate",fecha);      */

        //String usuario = request.getSession().getAttribute("userSession");
        
        TwoWaysBDL twoWaysBDL=null;
        
    try {
        twoWaysBDL = new TwoWaysBDL();
        monedas =  twoWaysBDL.getServiceTwoWays().obtenerMonedas();
        request.setAttribute("listaMoneda",monedas);
             
        items =  twoWaysBDL.getServiceTwoWays().obtenerItem("");
        request.setAttribute("listaItemsAux",items);     
        
        cuentas =  twoWaysBDL.getServiceTwoWays().obtenerAccount();
        request.setAttribute("listaCuentas",cuentas);        
        
        UsersTO userTO= (UsersTO)request.getSession().getAttribute("userSession"); 
        request.setAttribute("expUsuario",userTO.getUsrId());
        
               
    } catch (Exception e) {
       e.printStackTrace();
    }
    
    if (accion!=null && accion.equalsIgnoreCase("guardar")){     
        
        request.setAttribute("mesId",mesId);
        request.setAttribute("anioId",anioId);        
        try {
        if(request.getParameter("expFecha")!= null && !request.getParameter("expFecha").equalsIgnoreCase("") ){
            String expFechaMes = request.getParameter("expFecha").substring(3,5);
            String expFechaAnio = request.getParameter("expFecha").substring(6);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
            java.util.Date date = sdf.parse(expFechaAnio+expFechaMes);
            //request.setAttribute("auxDate",date); 
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
               itmExpTO.setIteComment((atribs[5]!= null && atribs[5].length() > 0)?atribs[5]:"");

               UsersTO user= new UsersTO(); 
               user.setUsrId(atribs[4]);
               itmExpTO.setUsersTO(user);
               if(expId != null && expId.length() > 0) {
                    ExpensesTO expenses = new ExpensesTO();
                    expenses.setExpId(Long.parseLong(expId));
                    itmExpTO.setExpensesTO(expenses);
               }
               try{
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date fechaExp = sdf.parse(atribs[6]);
                    java.sql.Timestamp timest = new java.sql.Timestamp(fechaExp.getTime()); 
                    itmExpTO.setIteDate(timest);
                   } catch (Exception e) {
                      e.printStackTrace();
                   }
               //request.setAttribute("auxDate",date); 
               
               if(atribs.length == 8){
                   itmExpTO.setIteId(Long.parseLong(atribs[7]));
               }
               itmExpTOList.add(itmExpTO);
           }                     
           gasto.setItemsExpensesTOList(itmExpTOList);
    
           List itmExps = null;       
           try {             
                if(expId != null && expId.length() > 0) {
                    gasto.setExpId(Long.parseLong(expId));
                    twoWaysBDL.getServiceTwoWays().updateGasto(gasto);
                    itmExps =  twoWaysBDL.getServiceTwoWays().getItemsExpenseList(Long.parseLong(expId)); 
                    request.setAttribute("auxExpId",expId); 
                    request.setAttribute("mensaje","<script>alert('El item de egreso se actualizó con éxito')</script>");                
                }else{
                    Long auxExpId = twoWaysBDL.getServiceTwoWays().insertarGasto(gasto); 
                    itmExps =  twoWaysBDL.getServiceTwoWays().getItemsExpenseList(gasto.getExpId());
                    request.setAttribute("auxExpId",auxExpId); 
                    request.setAttribute("mensaje","<script>alert('El item de egreso se guardó con éxito')</script>");                     
                }
                                         
                if (itmExps != null){
                    request.setAttribute("itemsExpense",itmExps);
                }
                
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("mensaje","<script>alert('Ocurrió un error al guardar el item de egreso')</script>");
            }
        
          }else{
              try{
                  twoWaysBDL.getServiceTwoWays().deleteGasto(Long.parseLong(expId)); 
                  request.setAttribute("mensaje","<script>alert('El item de egreso se eliminó con éxito')</script>");
              } catch (Exception e) {
              e.printStackTrace();
              request.setAttribute("mensaje","<script>alert('Ocurrió un error al eliminar el item de egreso')</script>");
              }
          }
      }    

      else if(expId != null && expId.length() > 0  && (accion == null ))//|| (accion!=null && !accion.equalsIgnoreCase("cancelar")) ))
      {                        
           try {
                   gasto =  twoWaysBDL.getServiceTwoWays().getExpenseById(Long.parseLong(expId));
                   
                   request.setAttribute("gasto",gasto);
                   
                   List itmExps = null;
                   itmExps =  twoWaysBDL.getServiceTwoWays().getItemsExpenseList(Long.parseLong(expId));                        
                   if (itmExps != null){
                       request.setAttribute("itemsExpense",itmExps);
                   }
                   if(gasto == null){
                       request.setAttribute("mensaje","<script>alert('El item de egreso no existe')</script>"); 
                   }else{
                       request.setAttribute("script","<script>init();</script>");
                   }
               
           } catch (Exception e) {
               e.printStackTrace();                 
               request.setAttribute("mensaje","<script>alert('Error al cargar el item de egreso')</script>"); 
           }
      }
      else if(mesId != null && anioId != null  && (accion != null  && accion.equalsIgnoreCase("BuscarItemFecha") ))//|| (accion!=null && !accion.equalsIgnoreCase("cancelar")) ))
       {                        
            try {                                    
                List itmExpDate = null;                    
                String auxExpId = null;

                String auxFechaForm =  request.getParameter("expFecha");
                
                itmExpDate =  twoWaysBDL.getServiceTwoWays().getItemsExpenseByDate(mesId,anioId);  
                request.setAttribute("mesId",mesId);
                request.setAttribute("anioId",anioId);
                if (auxFechaForm != null)
                    request.setAttribute("auxDate",auxFechaForm);
              
                
                if(itmExpDate != null && itmExpDate.size() > 0){
                    request.setAttribute("itemsExpense",itmExpDate);
                    auxExpId= ((HashMap)itmExpDate.get(0)).get("EXP_ID").toString();
                    request.setAttribute("auxExpId",auxExpId);    
                    request.setAttribute("script","<script>init();</script>");
                }
                  
            } catch (Exception e) {
                e.printStackTrace();                 
                request.setAttribute("mensaje","<script>alert('Error al buscar el item de egreso')</script>"); 
            }                                    
       }
      /* else if (accion!=null && accion.equalsIgnoreCase("exportar")){ 
       try{
           String itmGastosHidden[]=request.getParameterValues("item-gasto-hidden");
           String expFechaMes = request.getParameter("mesId");
           String expFechaAnio = request.getParameter("anioId");

           String nombreArchivo=TMP_DIR_PATH+"/Ingresos_Egresos_"+expFechaAnio+"_"+expFechaMes+".csv";
           GenerarCSV.generarCsvFile(itmGastosHidden,nombreArchivo);
           request.setAttribute("mensaje","<script>alert('El archivo se exportó correctamente al disco C con el nombre "+ nombreArchivo +"')</script>");      
       } catch (Exception e){
           e.printStackTrace();                 
           request.setAttribute("mensaje","<script>alert('Error al exportar el archivo')</script>");           
       }
       }*/
      else {
          //request.setAttribute("expId","");
          try {     
              mesId = mes;
              request.setAttribute("mesId",mesId);
              anioId = annio;
              request.setAttribute("anioId",anioId);
              List itmExpDate = null;                    
              String auxExpId = null;
              itmExpDate =  twoWaysBDL.getServiceTwoWays().getItemsExpenseByDate(mesId,anioId);    
                             
              if(itmExpDate != null && itmExpDate.size() > 0){
                  request.setAttribute("itemsExpense",itmExpDate);
                  auxExpId= ((HashMap)itmExpDate.get(0)).get("EXP_ID").toString();
                  request.setAttribute("auxExpId",auxExpId);    
                  request.setAttribute("script","<script>init();</script>");
              }/*else{
                  request.setAttribute("mensaje","<script>alert('No existen resultados de gastos para esa fecha')</script>"); 
              }*/
              
          } catch (Exception e) {
              e.printStackTrace();                 
              request.setAttribute("mensaje","<script>alert('Error al buscar el item de egreso')</script>"); 
          }       
      }        
      request.getRequestDispatcher("gastos.jsp").forward(request,response);
  }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType(CONTENT_TYPE);
            doGet(request,response); 
            }
}

