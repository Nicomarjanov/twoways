package com.twoways.view.servlets;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;


import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.AccountsTO;
import com.twoways.to.ClientsTO;
import com.twoways.to.CurrencyTO;
import com.twoways.to.EmployeesTO;
import com.twoways.to.ExpensesTO;
import com.twoways.to.InvoicesTO;
import com.twoways.to.ItemsExpensesTO;
import com.twoways.to.ItemsInvoicesTO;
import com.twoways.to.ItemsTO;
import com.twoways.to.OrdersRatesTO;
import com.twoways.to.OrdersTO;
import com.twoways.to.PaymentsTO;
import com.twoways.to.ProAssigmentsDetailsTO;
import com.twoways.to.ProjAssignPaysTO;
import com.twoways.to.RatesTO;
import com.twoways.to.UsersTO;

import java.io.IOException;
import java.io.PrintWriter;

import java.sql.Timestamp;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

public class AbmFacturacionServlet extends AutorizacionServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    //public static final String RESULT = "C:\\WINDOWS\\Temp\\pagos.pdf";
   

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
        List<AccountsTO> cuentas = null;          
        List<ClientsTO> clientes = null;
        List<ItemsTO> items = null;
        
        String cliId = request.getParameter("cliId");
        String invoiceId = request.getParameter("invoiceId");
        String facturar = request.getParameter("facturar");
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
        String fecha=(""+dia+"/"+mes+"/"+annio);
        request.setAttribute("auxDate",fecha); 
        
        TwoWaysBDL twoWaysBDL=null;
        
        try {
            twoWaysBDL = new TwoWaysBDL();
            monedas =  twoWaysBDL.getServiceTwoWays().obtenerMonedas();
            request.setAttribute("listaMoneda",monedas);            
            
            cuentas =  twoWaysBDL.getServiceTwoWays().obtenerAccount();
            request.setAttribute("listaCuentas",cuentas);   
            
            clientes = twoWaysBDL.getServiceTwoWays().obtenerClientes();
            request.setAttribute("listaClientes",clientes);   
            
            /*items =  twoWaysBDL.getServiceTwoWays().obtenerItem("Ingresos");
            request.setAttribute("listaItems",items);   */
            
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (accion!=null && accion.equalsIgnoreCase("buscarOrdenes") && cliId != null){
            try{
                List ordersEmpId = null; 

                ordersEmpId = twoWaysBDL.getServiceTwoWays().getOrdersByCliId(Long.parseLong(cliId),mesId,anioId);
                
                if (ordersEmpId != null && ordersEmpId.size() > 0){
                    String curIdOrigen = null;
                    request.setAttribute("finishedOrders",ordersEmpId);
                    Double auxAmount= 0.0;
                    SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                    Map auxMap = new HashMap();
                    Iterator iterador = ordersEmpId.listIterator();
                    while( iterador.hasNext() ) {
                        auxMap =(HashMap)iterador.next();
                        if (Double.parseDouble(auxMap.get("ORDTOTAL").toString()) > 0.0){    
                            Date fechaAss = formatoDeFecha.parse(auxMap.get("ORDFINISHDATE").toString());
                            Timestamp timestamp = new Timestamp(fechaAss.getTime());
                            String listaMoneda = request.getParameter("listaMoneda");
                            if (listaMoneda != null) {
                                String atribs[]= listaMoneda.split("#");
                                curIdOrigen = atribs[0];
                                if (curIdOrigen != auxMap.get("CURID").toString()){
                                    auxAmount += twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, Long.parseLong(auxMap.get("CURID").toString()), Long.parseLong(curIdOrigen) ,Double.parseDouble(auxMap.get("ORDTOTAL").toString()));
                                }else {
                                    auxAmount += Double.parseDouble(auxMap.get("ORDTOTAL").toString());
                                }
                            }

                        }
                    }
                    if (auxAmount > 0.0){
                        NumberFormat formatter = new DecimalFormat("#0.00");
                        request.setAttribute("invTotal",formatter.format(auxAmount));
                    }
                    request.setAttribute("cliId",cliId);
                    request.setAttribute("mesId",mesId);
                    request.setAttribute("anioId",anioId);
                    request.setAttribute("facturar",facturar);
                    
                }else {
                    request.setAttribute("mensaje","<script>alert('No se encontraron �rdenes para ese cliente')</script>");                     
                }
                
            } catch (Exception e) {
               e.printStackTrace();
            }
        request.getRequestDispatcher("facturacion.jsp").forward(request,response);
        }
        else if (accion!=null && accion.equalsIgnoreCase("facturarOrdenes") && cliId != null && invoiceId != null){
                try{
                    List ordersEmpId = null; 
                    
                    ordersEmpId = twoWaysBDL.getServiceTwoWays().getOrdersByCliIdInvId(Long.parseLong(cliId),Long.parseLong(invoiceId));
                    
                    if (ordersEmpId != null && ordersEmpId.size() > 0){
                        String curIdOrigen = null;
                        request.setAttribute("finishedOrders",ordersEmpId);
                        Double auxAmount= 0.0;
                        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
                        Map auxMap = new HashMap();
                        Iterator iterador = ordersEmpId.listIterator();
                        while( iterador.hasNext() ) {
                            auxMap =(HashMap)iterador.next();
                            if (Double.parseDouble(auxMap.get("ORDTOTAL").toString()) > 0.0){    
                                Date fechaAss = formatoDeFecha.parse(auxMap.get("ORDFINISHDATE").toString());
                                Timestamp timestamp = new Timestamp(fechaAss.getTime());
                                String listaMoneda = request.getParameter("listaMoneda");
                                if (listaMoneda != null) {
                                    String atribs[]= listaMoneda.split("#");
                                    curIdOrigen = atribs[0];
                                    if (curIdOrigen != auxMap.get("CURID").toString()){
                                        auxAmount += twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, Long.parseLong(auxMap.get("CURID").toString()), Long.parseLong(curIdOrigen) ,Double.parseDouble(auxMap.get("ORDTOTAL").toString()));
                                    }else {
                                        auxAmount += Double.parseDouble(auxMap.get("ORDTOTAL").toString());
                                    }
                                }

                            }
                        }
                        if (auxAmount > 0.0){
                            NumberFormat formatter = new DecimalFormat("#0.00");
                            request.setAttribute("invTotal",formatter.format(auxAmount));
                        }
                        request.setAttribute("cliId",cliId);
                        request.setAttribute("facturar",facturar);
                        request.setAttribute("invoiceId",invoiceId);
                        request.setAttribute("invoiceAcc",request.getParameter("invoiceAcc"));
                        request.setAttribute("invoiceTotal",request.getParameter("invoiceTotal"));
                        String fechaCompleta=request.getParameter("invoiceDate");
                        String anioInv = fechaCompleta.substring(0,4);
                        String mesInv = fechaCompleta.substring(5,7);
                        String diaInv = fechaCompleta.substring(8,10);
                        request.setAttribute("invoiceDate",diaInv+'/'+mesInv+'/'+anioInv);
                        request.setAttribute("invoiceCur",request.getParameter("invoiceCur"));

                    }else {
                        request.setAttribute("mensaje","<script>alert('No se encontraron �rdenes para ese cliente')</script>"); 
                    
                    }
                    
                } catch (Exception e) {
                   e.printStackTrace();
                }
         request.getRequestDispatcher("facturacion.jsp").forward(request,response);
        } else if (accion!=null && accion.equalsIgnoreCase("guardar") && cliId != null){
             
            InvoicesTO factura = new InvoicesTO();
            if (invoiceId == null || invoiceId.length()==0){       
                try {              
                    if(request.getParameter("invDate")!= null && !request.getParameter("invDate").equalsIgnoreCase("") ){ 
                        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                        java.util.Date date = sdf.parse(request.getParameter("invDate"));
                        java.sql.Timestamp timest = new java.sql.Timestamp(date.getTime()); 
                        factura.setInvDate(timest);
                    }
                
                } catch (Exception e) {
                    request.setAttribute("mensaje","<script>alert('La fecha ingresada no es v�lida')</script>"); 
                    e.printStackTrace();
                    request.getRequestDispatcher("facturacion.jsp").forward(request,response);
                }   
                request.setAttribute("cliId",cliId);
                request.setAttribute("facturar",facturar);
                
                ClientsTO cliIdTO = new ClientsTO();
                cliIdTO.setCliId(Long.parseLong(cliId));
                factura.setClientsTO(cliIdTO);
                
                String listaCuentas= request.getParameter("listaCuentas");
                if( listaCuentas  != null){ 
                        String atribs[]= listaCuentas.split("#");                    
                        AccountsTO accIdTO = new AccountsTO();
                        accIdTO.setAccId(Long.parseLong(atribs[0]));
                        factura.setAccountsTO(accIdTO);    
                }
                
                String listaMoneda = request.getParameter("listaMoneda");
                if (listaMoneda != null) {
                    String atribs[]= listaMoneda.split("#");  
                    CurrencyTO curIdTO = new CurrencyTO();
                    curIdTO.setCurId(Long.parseLong(atribs[0]));
                    factura.setCurrencyTO(curIdTO);                    
                }
                
                factura.setInvTotal(Double.parseDouble(request.getParameter("invTotal").replace(",",".")));
                factura.setInvInvoiced(facturar);
                
                String ordClients[]=request.getParameterValues("item-ordenes-hidden");
                List<ItemsInvoicesTO> itemsFacturaList = new ArrayList<ItemsInvoicesTO>();
                
                if( ordClients  != null){ 
            
                   for(String aux:ordClients){ 
                       String auxArray[] = aux.split("#"); 
                       OrdersTO orderTO = new OrdersTO();
                       orderTO.setOrdId(Long.parseLong(auxArray[0]));
                       OrdersRatesTO orderRateTO = new OrdersRatesTO();
                       orderRateTO.setOrdersTO(orderTO);
                       
                       RatesTO rateTO = new RatesTO();
                       rateTO.setRatId(Long.parseLong(auxArray[1]));
                       orderRateTO.setRatesTO(rateTO);
                       ItemsInvoicesTO itemFacturaTO = new ItemsInvoicesTO();
                       
                       itemFacturaTO.setOrdersRatesTO(orderRateTO);     
                       
                       CurrencyTO currencyTO = new CurrencyTO();
                       currencyTO.setCurId(Long.parseLong(auxArray[2]));
                       itemFacturaTO.setCurrencyTO(currencyTO);
                       
                       itemFacturaTO.setItiValue(Double.parseDouble(auxArray[3]));                       
                       itemsFacturaList.add(itemFacturaTO); 
                   }
                    factura.setItemsInvoicesTOList(itemsFacturaList);
                }
            }

            UsersTO userTO= (UsersTO)request.getSession().getAttribute("userSession"); 
            factura.setUsersTO(userTO);
            
            try {   
                    Long invId = null;
                    String imprimir = request.getParameter("imprimir");    
                    if (invoiceId != null && invoiceId.length() > 0){
                        factura.setInvId(Long.parseLong(invoiceId));
                        twoWaysBDL.getServiceTwoWays().actualizarFactura(factura);     
                        request.setAttribute("invId",invoiceId);
                        invId = Long.parseLong(invoiceId);
                    }
                    else{

                        invId = twoWaysBDL.getServiceTwoWays().insertarFactura(factura); 
                        request.setAttribute("invId",invId);
                        //Registro el cobro en la tabla de ingresos(gastos)
                        ItemsExpensesTO itmExpTO = new ItemsExpensesTO();
                        itmExpTO.setAccountsTO(factura.getAccountsTO());
                        itmExpTO.setCurrencyTO(factura.getCurrencyTO());
                        itmExpTO.setUsersTO(factura.getUsersTO());
                        itmExpTO.setIteDate(factura.getInvDate());
                        itmExpTO.setIteValue(factura.getInvTotal());
                        ItemsTO itmTO = new ItemsTO();
                        itmTO.setItmId(Long.parseLong("8"));//Facturas
                        itmExpTO.setItemsTO(itmTO);
                        
                        itmExpTO.setInvoicesTO(factura);
                        
                        ExpensesTO expTO = new ExpensesTO();   
                        List<ItemsExpensesTO> itmExpTOList = new ArrayList<ItemsExpensesTO>();
                        
                        List itmExpDate = null;     
                        String auxExpId = null;        
                        
                        mesId = request.getParameter("invDate").substring(3,5);
                        anioId = request.getParameter("invDate").substring(6);                    
                        itmExpDate =  twoWaysBDL.getServiceTwoWays().getItemsExpenseByDate(mesId,anioId);                      
                        if(itmExpDate != null && itmExpDate.size() > 0){
                            auxExpId= ((HashMap)itmExpDate.get(0)).get("EXP_ID").toString();
                            expTO.setExpId(Long.parseLong(auxExpId));
                        }       
                        itmExpTOList.add(itmExpTO);
                        expTO.setItemsExpensesTOList(itmExpTOList);

                        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMM");
                        java.util.Date date = sdf.parse(anioId+mesId);
                        java.sql.Timestamp timest = new java.sql.Timestamp(date.getTime());
                        expTO.setExpDate(timest);
                        
                        twoWaysBDL.getServiceTwoWays().insertarExpenseExtra(expTO);
                         
                    }
                    
                    if (imprimir!=null && imprimir.equalsIgnoreCase("imprimirFactura") && cliId != null && facturar.equalsIgnoreCase("si")){
                            
                            //try {
                                request.setAttribute("accion",accion);
                                
                                ServletContext contextServlet = getServletConfig().getServletContext(); 
                                imprimirFacturaServlet  impFact =(imprimirFacturaServlet)contextServlet.getServlet("imprimirFacturaServlet");
                                RequestDispatcher rd = contextServlet.getRequestDispatcher("/imprimirFacturaServlet");
                                rd.forward(request,response);
                               // imprimirFacturaServlet.createPdf(request,response,invId);
                                
                               // request.getRequestDispatcher("imprimirFactura.jsp").forward(request,response);
                                 //AbmFacturacionServlet.createPdf(request,response,invId);
                                                                  
                            /*}
                             catch (Exception e) {
                                    request.setAttribute("mensaje","<script>alert('Error al crea el PDF')</script>"); 
                                    e.printStackTrace();
                                    
                                }  */             
                           
                    }else{
                        request.setAttribute("mensaje","<script>alert('El registro de cobro se guard� con �xito')</script>"); 
                        request.getRequestDispatcher("facturacion.jsp").forward(request,response);
                    }
            } catch (Exception e) {
                e.printStackTrace();
            }            
         }else{
             request.getRequestDispatcher("facturacion.jsp").forward(request,response);
         }
        }

        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType(CONTENT_TYPE);
            doGet(request,response); 
            }
            
    
}
