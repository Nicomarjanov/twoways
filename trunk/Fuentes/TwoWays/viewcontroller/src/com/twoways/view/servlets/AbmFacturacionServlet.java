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
                    request.setAttribute("mensaje","<script>alert('No se encontraron órdenes para ese cliente')</script>"); 
                }
                
            } catch (Exception e) {
               e.printStackTrace();
            }
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
                        request.setAttribute("mensaje","<script>alert('No se encontraron órdenes para ese cliente')</script>"); 
                    }
                    
                } catch (Exception e) {
                   e.printStackTrace();
                }
            }
        else if (accion!=null && accion.equalsIgnoreCase("guardar") && cliId != null){
             
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
                    request.setAttribute("mensaje","<script>alert('La fecha ingresada no es válida')</script>"); 
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
                    }
                    if (imprimir!=null && imprimir.equalsIgnoreCase("imprimirFactura") && cliId != null && facturar.equalsIgnoreCase("si")){
                            
                            try {
                                
                                 AbmFacturacionServlet.createPdf(request,response,invId);
                                                                  
                            }
                             catch (Exception e) {
                                    request.setAttribute("mensaje","<script>alert('Error al crea el PDF')</script>"); 
                                    e.printStackTrace();
                                    
                                }               
                           
                    }   
                    request.setAttribute("mensaje","<script>alert('El registro de cobro se guardó con éxito')</script>"); 
                
            } catch (Exception e) {
                e.printStackTrace();
            }            
        }

        request.getRequestDispatcher("facturacion.jsp").forward(request,response);
        
        }

        public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType(CONTENT_TYPE);
            doGet(request,response); 
            }
            
        public static void createPdf(HttpServletRequest request,HttpServletResponse response, Long invId)
           throws IOException, DocumentException {
           // step 1
            Document document = new Document(PageSize.A4.rotate());
           // step 2
           response.setContentType("application/pdf");
           response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
           String cliId= request.getParameter("cliId");
           ClientsTO cliente = new ClientsTO();
           TwoWaysBDL twoWaysBDL=null;

           try{
               twoWaysBDL = new TwoWaysBDL();
               cliente = twoWaysBDL.getServiceTwoWays().getClientById(cliId);     
               } catch (Exception e) {
                   e.printStackTrace();
               }            
           
           String invDate = request.getParameter("invDate");
           invDate.replace("/","");

           String disposition = "attachment; filename= Invoice_" + cliente.getCliName()+"_"+invDate+".pdf";
           response.setHeader("Content-disposition", disposition); 
           PdfWriter.getInstance(document, response.getOutputStream());
           // step 3
           document.open();
           // step 4
           document.add(createTableEncabezado(request,invId,cliente));
           document.add(createTable(request));
           // step 5
           document.close();
        }
        
        public static PdfPTable createTableEncabezado(HttpServletRequest request, Long invId,ClientsTO cliente) throws IOException, DocumentException{
          
        String invDate = request.getParameter("invDate");   
        String accId =request.getParameter("listaCuentas");
        
        PdfPTable table = new PdfPTable(4);
        PdfPCell cell; 
        BaseFont bf = BaseFont.createFont();   
        Font ft = new Font(bf,15,Font.BOLD);         
        
        //imagen
        table.setWidthPercentage(100f);
        cell = new PdfPCell(Image.getInstance(RESOURCE),true);
        cell.setBorder(PdfPCell.NO_BORDER);
      //  cell.setColspan(1);
        //cell.setPadding(20);
        table.addCell(cell);
        cell = new PdfPCell(new Phrase(""));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setColspan(3);
        //cell.setPadding(20);
        table.addCell(cell);      
        
        //fecha de pago
        cell = new PdfPCell(new Phrase("Date: "+invDate,ft));    
        //cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setColspan(2);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingBottom(20);       
        cell.setPaddingTop(15);        
        table.addCell(cell);
        
        //Numero de factura
        cell = new PdfPCell(new Phrase("Invoice #: "+invId,ft));    
        //cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setColspan(2);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingBottom(20);
        cell.setPaddingTop(15);
        table.addCell(cell);        
        
        //Datos de cuenta y cliente
         TwoWaysBDL twoWaysBDL=null;
         AccountsTO cuenta = new AccountsTO(); 
        // ClientsTO cliente = new ClientsTO();
         try {
             twoWaysBDL = new TwoWaysBDL();
             cuenta = twoWaysBDL.getServiceTwoWays().getAccountById(accId);
            // cliente = twoWaysBDL.getServiceTwoWays().getClientById(cliId);
            } catch (Exception e) {
                e.printStackTrace();
            } 
        if (cuenta != null && cliente != null){
            cell = new PdfPCell(new Phrase((cuenta.getAccHolder()!=null)?"Account holder: "+cuenta.getAccHolder():""));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);
        
            cell = new PdfPCell(new Phrase("COMPANY INFORMATION"));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setColspan(2);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase((cuenta.getAccNumber()!=null)?"Checking account: "+cuenta.getAccNumber():""));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase((cliente.getCliName()!=null)?cliente.getCliName():""));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setColspan(2);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase((cuenta.getAccSwiftCode()!=null)?"SWIFT code: "+cuenta.getAccSwiftCode():""));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((cliente.getCliAddress()!=null)?cliente.getCliAddress():""));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setColspan(2);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(((cuenta.getAccBank()!=null)?cuenta.getAccBank():"")));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell); 
            
            cell = new PdfPCell(new Phrase((cliente.getCliPostalCode()!=null)?cliente.getCliPostalCode():""));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setColspan(2);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(((cuenta.getAccZipCode()!=null)?cuenta.getAccZipCode():"")));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(2);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);

            cell = new PdfPCell(new Phrase((cliente.getCliCountry()!=null)?cliente.getCliCountry():""));
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            cell.setColspan(2);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(((cuenta.getAccDirection()!=null)?cuenta.getAccDirection():"")));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(4);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);            
            
            cell = new PdfPCell(new Phrase(((cuenta.getAccCity()!=null)?cuenta.getAccCity():"")));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(4);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);
            
            cell = new PdfPCell(new Phrase(((cuenta.getAccCountry()!=null)?cuenta.getAccCountry():"")));
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            cell.setColspan(4);
            cell.setBorder(PdfPCell.NO_BORDER);
            table.addCell(cell);
        }
    /*Fila en blanco
     cell = new PdfPCell(new Phrase(""));
     cell.setHorizontalAlignment(Element.ALIGN_LEFT);
     cell.setColspan(2);
     cell.setBorder(PdfPCell.NO_BORDER);
     table.addCell(cell);
     */
    return table;
        
    }

     public static PdfPTable createTable(HttpServletRequest request) throws IOException, DocumentException{
       
        String printOrden[] = request.getParameterValues("print-ordenes-hidden");        
        String invTotal = request.getParameter("invTotal");    
        String curSymbol[] =request.getParameter("listaMoneda").split("#");     
            
        PdfPTable table = new PdfPTable(9);
        table.setWidthPercentage(100f);
        PdfPCell cell; 
        BaseFont bfc = BaseFont.createFont(BaseFont.HELVETICA,BaseFont.CP1250,BaseFont.NOT_EMBEDDED);     
        Font ft = new Font(bfc,15,Font.BOLD); 
        Font cf = new Font(bfc,10); 
        
        table.getDefaultCell().setPadding(3);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setColspan(1);
        table.getDefaultCell().setBackgroundColor(new GrayColor(0.825f));
        table.setSpacingBefore(10);
        
        table.addCell("PO #");
        table.addCell("Job #");
        table.addCell("WO #");
        table.addCell("Job Name");
        table.addCell("Job Description");
        //table.addCell("Item Description");
        table.addCell("# of Words");
        table.addCell("Per word rate");
        table.addCell("Total Due");            
        table.addCell("Project Manager"); 
        
        table.getDefaultCell().setBackgroundColor(null);

        if( printOrden  != null){ 
            Integer indice = 0;       
         for(String aux:printOrden){      

             String atribs[]= aux.split("#");
             indice +=1;

             cell = new PdfPCell(new Phrase(atribs[0],cf));
             cell.setHorizontalAlignment(Element.ALIGN_LEFT);
             table.addCell(cell);
             cell = new PdfPCell(new Phrase(atribs[1],cf));
             cell.setHorizontalAlignment(Element.ALIGN_LEFT);
             table.addCell(cell);
             cell = new PdfPCell(new Phrase(atribs[2],cf));
             cell.setHorizontalAlignment(Element.ALIGN_LEFT);
             table.addCell(cell);
             cell = new PdfPCell(new Phrase(atribs[3],cf));
             cell.setHorizontalAlignment(Element.ALIGN_LEFT);
             table.addCell(cell);
             cell = new PdfPCell(new Phrase(atribs[4],cf));
             cell.setHorizontalAlignment(Element.ALIGN_LEFT);
             table.addCell(cell);      
             cell = new PdfPCell(new Phrase(atribs[5],cf));
             cell.setHorizontalAlignment(Element.ALIGN_LEFT);
             table.addCell(cell);     
             cell = new PdfPCell(new Phrase(atribs[6],cf));
             cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
             table.addCell(cell);
             cell = new PdfPCell(new Phrase(atribs[7],cf));
             cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
             table.addCell(cell);
             cell = new PdfPCell(new Phrase(atribs[8],cf));
             cell.setHorizontalAlignment(Element.ALIGN_LEFT);
             table.addCell(cell);
         }
         
            //Total a pagar
            cell = new PdfPCell(new Phrase("Total: ",ft));           
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setColspan(7);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell); 
            if (curSymbol[1] != null){
                cell = new PdfPCell(new Phrase(curSymbol[1]+" "+invTotal,ft));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                cell.setColspan(2);
                table.addCell(cell);  
            }
        }
        return table;
            
        }
}
