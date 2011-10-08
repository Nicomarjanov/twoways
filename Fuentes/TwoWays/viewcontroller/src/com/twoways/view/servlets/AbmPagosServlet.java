package com.twoways.view.servlets;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
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
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.AccountsTO;
import com.twoways.to.CurrencyTO;
import com.twoways.to.EmployeeTypeTO;

import com.twoways.to.EmployeesTO;

import com.twoways.to.ExpensesTO;
import com.twoways.to.ItemsExpensesTO;
import com.twoways.to.ItemsTO;
import com.twoways.to.PaymentsTO;
import com.twoways.to.ProAssigmentsDetailsTO;
import com.twoways.to.ProjAssignPaysTO;
import com.twoways.to.ProjectAssignmentsTO;

import com.twoways.to.ProjectsTO;

import com.twoways.to.UsersTO;

import java.io.FileOutputStream;
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

import sun.font.FontFamily;

public class AbmPagosServlet extends AutorizacionServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    public static final String EURO = "C:\\apache-tomcat-7.0.5\\webapps\\img\\euro.png";
    public static final String RESOURCE = "C:\\apache-tomcat-7.0.5\\webapps\\img\\print_img.png";
    public static final String POUND = "C:\\apache-tomcat-7.0.5\\webapps\\img\\money_pound.png";
    
    public AbmPagosServlet(){
    
    }
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
        List<EmployeesTO> empleados = null;
        
        String empId = request.getParameter("empId");
        String mesId = request.getParameter("mesId");  
        String anioId = request.getParameter("anioId");
        String empName = request.getParameter("empName");
            
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
        request.setAttribute("payDate",fecha); 
        
        TwoWaysBDL twoWaysBDL=null;
        
        try {
            twoWaysBDL = new TwoWaysBDL();
            monedas =  twoWaysBDL.getServiceTwoWays().obtenerMonedas();
            request.setAttribute("listaMoneda",monedas);            
            
            cuentas =  twoWaysBDL.getServiceTwoWays().obtenerAccount();
            request.setAttribute("listaCuentas",cuentas); 
            
            empleados =  twoWaysBDL.getServiceTwoWays().obtenerEmpleados();
            request.setAttribute("listaEmpleados",empleados);
            
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (accion!=null && accion.equalsIgnoreCase("buscarAsignaciones") && empId != null){
            try{
                List projAssignEmpId = null; 
                projAssignEmpId = twoWaysBDL.getServiceTwoWays().getProjectAssignmentsByEmpId(Long.parseLong(empId),mesId,anioId);
                
                if (projAssignEmpId != null && projAssignEmpId.size() > 0){
                    String curIdOrigen = null;
                    request.setAttribute("projectAssignnments",projAssignEmpId);
                    Double auxAmount= 0.0;
                    SimpleDateFormat formatoDeFecha = new SimpleDateFormat("yyyy-MM-dd");
    
                    Map auxMap = new HashMap();
                    Iterator iterador = projAssignEmpId.listIterator();
                    while( iterador.hasNext() ) {
                        auxMap =(HashMap)iterador.next();
                        if (Double.parseDouble(auxMap.get("TOTAL").toString()) > 0.0){
                            Date fechaAss = formatoDeFecha.parse(auxMap.get("ASSIGNDATE").toString());
                            Timestamp timestamp = new Timestamp(fechaAss.getTime());
                            String listaMoneda = request.getParameter("listaMoneda");
                            if (listaMoneda != null) {
                                String atribs[]= listaMoneda.split("#");
                                curIdOrigen = atribs[0];
                                auxAmount += twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, Long.parseLong(auxMap.get("CURID").toString()), Long.parseLong(curIdOrigen) ,Double.parseDouble(auxMap.get("TOTAL").toString()));
                            }
                        }
                        //auxAmount = auxAmount + Double.parseDouble(auxMap.get("PRATOTAL").toString());
                    }

                    request.setAttribute("empId",empId);
                    request.setAttribute("mesId",mesId);
                    request.setAttribute("anioId",anioId);
                    request.setAttribute("empName",empName);
                    
                    if (auxAmount > 0.0){
                        NumberFormat formatter = new DecimalFormat("#0.00");
                        request.setAttribute("payAmount",formatter.format(auxAmount));
                    }
                   /* empleados =  twoWaysBDL.getServiceTwoWays().obtenerEmpleados();
                    request.setAttribute("listaEmpleados",empleados);*/
                }else {

                    request.setAttribute("mensaje","<script>alert('No se encontraron asignaciones para ese empleado')</script>"); 
                }
                
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
        else if (accion!=null && accion.equalsIgnoreCase("guardar") && empId != null){
            PaymentsTO pago = new PaymentsTO();
            try {              
                if(request.getParameter("payDate")!= null && !request.getParameter("payDate").equalsIgnoreCase("") ){ 
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date date = sdf.parse(request.getParameter("payDate"));
                    java.sql.Timestamp timest = new java.sql.Timestamp(date.getTime()); 
                    pago.setPayDate(timest);
                    request.setAttribute("payDate",request.getParameter("payDate"));
                }
            
            } catch (Exception e) {
                request.setAttribute("mensaje","<script>alert('La fecha ingresada no es válida')</script>"); 
                e.printStackTrace();
                request.getRequestDispatcher("pagos.jsp").forward(request,response);
            }   
            
            request.setAttribute("mesId",mesId);
            request.setAttribute("anioId",anioId);        
            request.setAttribute("empId",empId);
            request.setAttribute("empName",empName);
            
            EmployeesTO empIdTO = new EmployeesTO();
            String empIdAux[] = request.getParameter("empId").split("#");
            empIdTO.setEmpId(Long.parseLong(empIdAux[0]));
            pago.setEmployeeTO(empIdTO);
            
            String listaCuentas= request.getParameter("listaCuentas");

            if( listaCuentas  != null){ 
                    String atribs[]= listaCuentas.split("#");                    
                    AccountsTO accIdTO = new AccountsTO();                    
                    accIdTO.setAccId(Long.parseLong(atribs[0]));
                    pago.setAccountsTO(accIdTO);
                    request.setAttribute("accId",listaCuentas);
            }
            
            String listaMoneda = request.getParameter("listaMoneda");

            if (listaMoneda != null) {
                String atribs[]= listaMoneda.split("#");  
                CurrencyTO curIdTO = new CurrencyTO();                
                curIdTO.setCurId(Long.parseLong(atribs[0]));
                pago.setCurrencyTO(curIdTO);
                request.setAttribute("curSymbol",listaMoneda);
                request.setAttribute("curId",atribs[0]);
            }
            
            pago.setPayAmount(Double.parseDouble(request.getParameter("payAmount").replace(",",".")));
            request.setAttribute("payAmount",request.getParameter("payAmount"));
            pago.setPayDescription((request.getParameter("payDescription")!= null )?request.getParameter("payDescription"):"");
            request.setAttribute("payDescription",request.getParameter("payDescription"));
            pago.setPayObservation((request.getParameter("payObservation")!= null )?request.getParameter("payObservation"):""); 
            request.setAttribute("payObservation",request.getParameter("payObservation"));            
    
            String empProjAssignment[]=request.getParameterValues("item-pago-hidden");
            List<ProjAssignPaysTO> projAssTOList = new ArrayList<ProjAssignPaysTO>();
            
            if( empProjAssignment  != null){ 
                                  
               for(String aux:empProjAssignment){               
                   ProjAssignPaysTO projAssPayTO = new ProjAssignPaysTO();
                   ProAssigmentsDetailsTO proAssigmentsDetailsTO = new ProAssigmentsDetailsTO();
                   proAssigmentsDetailsTO.setPadId(Long.parseLong(aux));
                   projAssPayTO.setProAssigmentsDetailsTO(proAssigmentsDetailsTO);            
                   projAssTOList.add(projAssPayTO); 
               }
                pago.setProjAssignTOList(projAssTOList);
            }
            
            UsersTO userTO= (UsersTO)request.getSession().getAttribute("userSession"); 
            pago.setUserTO(userTO);
            
            try {       
                   
                    Long payId = twoWaysBDL.getServiceTwoWays().insertarPago(pago); 
                    request.setAttribute("payId",payId);
                    //Insertar gasto de pago de sueldo en la tabla de gastos
                    ItemsExpensesTO itmExpTO = new ItemsExpensesTO(); 
                    itmExpTO.setAccountsTO(pago.getAccountsTO());
                    itmExpTO.setCurrencyTO(pago.getCurrencyTO());
                    itmExpTO.setIteDate(pago.getPayDate());
                    ItemsTO itmTO = new ItemsTO();
                    itmTO.setItmId(Long.parseLong("20"));
                    itmExpTO.setItemsTO(itmTO);//Sueldo Empleados
                    itmExpTO.setIteValue(pago.getPayAmount());                   
                    itmExpTO.setUsersTO(pago.getUserTO());
                    
                    itmExpTO.setPaymentsTO(pago);
                    
                    ExpensesTO expTO = new ExpensesTO();   
                    List<ItemsExpensesTO> itmExpTOList = new ArrayList<ItemsExpensesTO>();
                    
                    List itmExpDate = null;     
                    String auxExpId = null;        
                    
                    mesId = request.getParameter("payDate").substring(3,5);
                    anioId = request.getParameter("payDate").substring(6);                    
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
                    
                    List printEmpProjAssignment = null;
                    printEmpProjAssignment = twoWaysBDL.getServiceTwoWays().obtenerItemsPago(payId);
                    request.setAttribute("projectAssignnments",printEmpProjAssignment);
                    
                    accion="imprimir";
                    request.setAttribute("mensaje","<script>alert('El registro del pago se guardó con éxito')</script>");

            } catch (Exception e) {
                e.printStackTrace();
            } 

        }
            else if (accion!=null && accion.equalsIgnoreCase("imprimirPago") && empId != null){
                    
                try {
                    Long payId= Long.parseLong(request.getParameter("payId"));
                    createPdf(request,response, payId);           
                }
                 catch (Exception e) {
                        request.setAttribute("mensaje","<script>alert('Error al crea el PDF')</script>"); 
                        e.printStackTrace();
                   }               

                }
        request.setAttribute("accion",accion);
        request.getRequestDispatcher("pagos.jsp").forward(request,response);
       
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType(CONTENT_TYPE);
            doGet(request,response); 
            }
            
    public void createPdf(HttpServletRequest request,HttpServletResponse response, Long payId)
           throws IOException, DocumentException {
           // step 1
            Document document = new Document(PageSize.A4.rotate());
           // step 2
           response.setContentType("application/pdf");
           response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
           String disposition = "attachment; filename=" + request.getParameter("empName")+"_"+request.getParameter("mesId")+request.getParameter("anioId")+".pdf";
           response.setHeader("Content-disposition", disposition); 
           PdfWriter.getInstance(document, response.getOutputStream());
           // step 3
           document.open();
           // step 4
           document.add(createTable(request, payId));
           // step 5
           document.close();

       }
       
    public PdfPTable createTable(HttpServletRequest request,Long payId) throws IOException, DocumentException{
          
        //String empProjAssignment[] = request.getParameterValues("print-pago-hidden");        
        List empProjAssignment = null;
        TwoWaysBDL twoWaysBDL=null;
        try{
            
            twoWaysBDL = new TwoWaysBDL();
            empProjAssignment = twoWaysBDL.getServiceTwoWays().obtenerItemsPago(payId);
            }catch (Exception e) {
                e.printStackTrace();
            } 
        String payAmount = request.getParameter("payAmount");    
        String empName = request.getParameter("empName");
        String mesId = request.getParameter("mesId");
        String payDate = request.getParameter("payDate");
       // String curId = request.getParameter("curIdOrigen");
        String curSymbol[] =request.getParameter("listaMoneda").split("#");        
        
        PdfPTable table = new PdfPTable(6);
        PdfPCell cell; 
        BaseFont bf = BaseFont.createFont();   
        Font ft = new Font(bf,15,Font.BOLD);         
        //imagen
        table.setWidthPercentage(100f);
        cell = new PdfPCell(Image.getInstance(RESOURCE),true);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setColspan(2);
        cell.setPadding(20);
        table.addCell(cell);
        
        //fecha de pago
        cell = new PdfPCell(new Phrase("Fecha de pago: "+payDate,ft));    
        //cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setColspan(4);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);
        
        //titulo
        //cell = new PdfPCell();

        Font f = new Font(bf,24,Font.BOLD,new BaseColor(0xC2,0x33,0x30));        
        Paragraph p = new Paragraph ("Recibo de pago de "+empName,f);//+" del mes de "+mesId,f);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingBefore(5);
        p.setSpacingAfter(5);
        cell.addElement(p);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setColspan(6);
        cell.setPadding(15);
        table.addCell(cell);
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

        if( empProjAssignment  != null){ 
            Map auxMap = new HashMap();    
            Iterator iterador = empProjAssignment.listIterator();
            while( iterador.hasNext() ) {
                 auxMap =(HashMap)iterador.next();
                 cell = new PdfPCell(new Phrase((auxMap.get("ASSIGNDATE")!=null)?auxMap.get("ASSIGNDATE").toString():""));
                 cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                 table.addCell(cell);
                 cell = new PdfPCell(new Phrase((auxMap.get("PRONAME")!=null)?auxMap.get("PRONAME").toString():""));
                 cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                 table.addCell(cell);
                 cell = new PdfPCell(new Phrase((auxMap.get("RATNAME")!=null)?auxMap.get("RATNAME").toString():""));
                 cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                 table.addCell(cell);
                 String auxCur = (auxMap.get("CURSYMBOL")!=null)?auxMap.get("CURSYMBOL").toString():"";
                 String auxRate = (auxMap.get("RATE")!=null)?auxMap.get("RATE").toString():"";
                 cell = new PdfPCell(new Phrase(auxCur+' '+auxRate));
                 cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                 table.addCell(cell);
                 cell = new PdfPCell(new Phrase((auxMap.get("WCOUNT")!=null)?auxMap.get("WCOUNT").toString():"0"));
                 cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                 table.addCell(cell);   
                 cell = new PdfPCell(new Phrase((auxMap.get("TOTAL")!=null)?auxMap.get("TOTAL").toString():"0"));
                 cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                 table.addCell(cell);          

            }
         
         
            //Total a pagar
            cell = new PdfPCell(new Phrase("Total a pagar: ",ft));           
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setColspan(4);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell); 
            if (curSymbol[0] != null){
                if(curSymbol[0].equalsIgnoreCase("2")){
                    Image euro =Image.getInstance(EURO);
                    euro.scalePercent(70f);
                    cell = new PdfPCell();
                    cell.addElement(euro);
                
                }else if (curSymbol[0].equalsIgnoreCase("3")){
                    Image pound =Image.getInstance(POUND);
                    pound.scalePercent(55f);
                    cell = new PdfPCell();
                    cell.addElement(pound);
                }
                else cell = new PdfPCell(new Phrase(curSymbol[1],ft));
                cell.setBorder(PdfPCell.NO_BORDER);
                cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
                //cell.setColspan(2);
                table.addCell(cell);  
            }
            cell = new PdfPCell(new Phrase(payAmount,ft));
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_LEFT);
            table.addCell(cell);
            
        }
        return table;
            
    }
}