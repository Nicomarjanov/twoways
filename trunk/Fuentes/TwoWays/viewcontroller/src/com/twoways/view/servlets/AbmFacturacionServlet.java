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
import com.twoways.to.InvoicesTO;
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
    public static final String RESOURCE = "C:\\apache-tomcat-7.0.5\\webapps\\img\\print_img.png";
    

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
            
            items =  twoWaysBDL.getServiceTwoWays().obtenerItem("Ingresos");
            request.setAttribute("listaItems",items);   
            
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (accion!=null && accion.equalsIgnoreCase("buscarOrdenes") && cliId != null){
            try{
                List ordersEmpId = null; 
                String auxCliId[] = cliId.split("#");
                ordersEmpId = twoWaysBDL.getServiceTwoWays().getOrdersByCliId(Long.parseLong(auxCliId[0]));
                
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
                            Date fechaAss = formatoDeFecha.parse(auxMap.get("ORDDATE").toString());
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
                       // request.setAttribute("curIdOrigen",curIdOrigen);
                    }
                    
                    request.setAttribute("cliId",auxCliId[0]);
                    
                }else {
                    request.setAttribute("mensaje","<script>alert('No se encontraron ordenes para ese cliente')</script>"); 
                }
                
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
        else if (accion!=null && accion.equalsIgnoreCase("guardar") && cliId != null){
            
            String bandera = null;        
            InvoicesTO factura = new InvoicesTO();
            try {              
                if(request.getParameter("invDate")!= null && !request.getParameter("invDate").equalsIgnoreCase("") ){ 
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date date = sdf.parse(request.getParameter("invDate"));
                    java.sql.Timestamp timest = new java.sql.Timestamp(date.getTime()); 
                    factura.setInvDate(timest);
                }
            
            } catch (Exception e) {
                request.setAttribute("mensaje","<script>alert('La fecha ingresada no es válida')</script>"); 
                e.printStackTrace();
                request.getRequestDispatcher("facturacion.jsp").forward(request,response);
            }   
            String auxCliId[] = cliId.split("#");        
            request.setAttribute("cliId",auxCliId[0]);
            
            ClientsTO cliIdTO = new ClientsTO();
            cliIdTO.setCliId(Long.parseLong(auxCliId[0]));
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
                   
                   String nomLista = "listaItems"+auxArray[0]+auxArray[1];
                   String itemLista = request.getParameter(nomLista);
                   ItemsTO item = new ItemsTO();
                   item.setItmId(Long.parseLong(itemLista));                   
                   itemFacturaTO.setItemsTO(item);
                   
                   itemsFacturaList.add(itemFacturaTO); 
               }
                if (bandera == null)
                    factura.setItemsInvoicesTOList(itemsFacturaList);
            }
            if (bandera == null){
                UsersTO userTO= (UsersTO)request.getSession().getAttribute("userSession"); 
                factura.setUsersTO(userTO);
                
                try {       
                        String imprimir = request.getParameter("imprimir");
                        Long invId = twoWaysBDL.getServiceTwoWays().insertarFactura(factura); 
                        request.setAttribute("invId",invId);
                        request.setAttribute("mensaje","<script>alert('El registro del pago se guardó con éxito')</script>");
    
                        if (imprimir!=null && imprimir.equalsIgnoreCase("imprimirFactura") && cliId != null){
                                
                                try {
                                     AbmFacturacionServlet.createPdf(request,response);
                                     request.getRequestDispatcher("facturacion.jsp").forward(request,response);                                 
                                }
                                 catch (Exception e) {
                                        request.setAttribute("mensaje","<script>alert('Error al crea el PDF')</script>"); 
                                        e.printStackTrace();
                                        request.getRequestDispatcher("facturacion.jsp").forward(request,response);
                                    }               
    
                        }          
                } catch (Exception e) {
                    e.printStackTrace();
                } 
            }
        }

        request.getRequestDispatcher("facturacion.jsp").forward(request,response);
        
        }

        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType(CONTENT_TYPE);
            doGet(request,response); 
            }
            
        public static void createPdf(HttpServletRequest request,HttpServletResponse response)
           throws IOException, DocumentException {
           // step 1
            Document document = new Document(PageSize.A4.rotate());
           // step 2
           response.setContentType("application/pdf");
           response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
           String cliName[]= request.getParameter("cliId").split("#");
           
           SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
           java.util.Date fechaImp = null;
            try {
                fechaImp = sdf.parse(request.getParameter("invDate"));
            } catch (Exception e) {
                e.printStackTrace();
            }

           String disposition = "attachment; filename= Invoice_" + cliName[1]+"_"+fechaImp+".pdf";
           response.setHeader("Content-disposition", disposition); 
           PdfWriter.getInstance(document, response.getOutputStream());
           // step 3
           document.open();
           // step 4
           document.add(createTable(request));
           // step 5
           document.close();
        }
        
        public static PdfPTable createTable(HttpServletRequest request) throws IOException, DocumentException{
          
        String printOrden[] = request.getParameterValues("print-orden-hidden");        
        String invTotal = request.getParameter("invTotal");    
        String empName = request.getParameter("empName");
        String invDate = request.getParameter("invDate");
        String invId = request.getParameter("invId");
       // String curId = request.getParameter("curIdOrigen");
        String curSymbol[] =request.getParameter("listaMoneda").split("#");        
        String accId =request.getParameter("listaCuentas");
        
        PdfPTable table = new PdfPTable(6);
        PdfPCell cell; 
        BaseFont bf = BaseFont.createFont();   
        Font ft = new Font(bf,15,Font.BOLD);         
        //imagen
        table.setWidthPercentage(100f);
        cell = new PdfPCell(Image.getInstance(RESOURCE),true);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setColspan(6);
        cell.setPadding(20);
        table.addCell(cell);
        
        //fecha de pago
        cell = new PdfPCell(new Phrase("Date: "+invDate,ft));    
        //cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setColspan(3);
        //cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);
        
        //Numero de factura
        cell = new PdfPCell(new Phrase("Invoice #: "+invId,ft));    
        //cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setColspan(3);
        //cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);        
        
        //Datos de cuenta
         TwoWaysBDL twoWaysBDL=null;
         AccountsTO cuenta = new AccountsTO(); 
         try {
             twoWaysBDL = new TwoWaysBDL();
             cuenta =   twoWaysBDL.getServiceTwoWays().getAccountById(accId);
             request.setAttribute("listaMoneda",cuenta); 
            } catch (Exception e) {
                e.printStackTrace();
            } 
        if (cuenta != null){
            cell = new PdfPCell(new Phrase(cuenta.getAccHolder()));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
         
       

        //Font f = new Font(bf,24,Font.BOLD,new BaseColor(0xC2,0x33,0x30));        
       /* Paragraph p = new Paragraph ("Recibo de pago de "+empName+" del mes de "+mesId,f);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingBefore(5);
        p.setSpacingAfter(5);
        cell.addElement(p);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setColspan(6);
        cell.setPadding(15);
        table.addCell(cell);*/
        //

        table.getDefaultCell().setPadding(3);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setColspan(1);
        table.getDefaultCell().setBackgroundColor(new GrayColor(0.825f));
        
        table.addCell("Fecha asignación");
        table.addCell("Nombre proyecto");
        table.addCell("Tipo tarifa");
        table.addCell("Monto tarifa");
        table.addCell("Total unidades");
        table.addCell("Total asignación");

        table.getDefaultCell().setBackgroundColor(null);

        if( printOrden  != null){ 
                
         for(String aux:printOrden){               
             String atribs[]= aux.split("#");
             cell = new PdfPCell(new Phrase(atribs[0]));
             cell.setHorizontalAlignment(Element.ALIGN_LEFT);
             table.addCell(cell);
             cell = new PdfPCell(new Phrase(atribs[1]));
             cell.setHorizontalAlignment(Element.ALIGN_LEFT);
             table.addCell(cell);
             cell = new PdfPCell(new Phrase(atribs[2]));
             cell.setHorizontalAlignment(Element.ALIGN_LEFT);
             table.addCell(cell);
             cell = new PdfPCell(new Phrase(atribs[6]+" "+atribs[3]));
             cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
             table.addCell(cell);
             cell = new PdfPCell(new Phrase(atribs[4]));
             cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
             table.addCell(cell);
             cell = new PdfPCell(new Phrase(atribs[5]));
             cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
             table.addCell(cell);             
         }
         
            //Total a pagar
            cell = new PdfPCell(new Phrase("Total: ",ft));           
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setColspan(5);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell); 
            if (curSymbol[1] != null){
                cell = new PdfPCell(new Phrase(curSymbol[1]+" "+invTotal,ft));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                table.addCell(cell);  
            }
        }
        return table;
            
        }
}
