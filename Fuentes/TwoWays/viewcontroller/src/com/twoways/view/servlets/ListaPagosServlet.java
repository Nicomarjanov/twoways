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
import com.twoways.to.EmployeesTO;
import com.twoways.to.InvoicesTO;
import com.twoways.to.ItemsInvoicesTO;

import com.twoways.to.PaymentsTO;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.Date;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

public class ListaPagosServlet extends AutorizacionServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
   // public static final String RESOURCE = "C:\\apache-tomcat-7.0.5\\webapps\\twoways\\img\\print_img.png";    
   // public static final String EURO = "C:\\apache-tomcat-7.0.5\\webapps\\img\\euro.png";
    public static final String RESOURCE = "/home/resources/img/print_img.png";    
    public static final String EURO = "/home/resources/img/euro.png";
    public static final String POUND = "/home/resources/img/money_pound.png";
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        List roles= new ArrayList();
        roles.add("Administrador");
        this.setRolesValidos(roles);
    }

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
        super.doGet(request,response);
        TwoWaysBDL twoWaysBDL=null;
        String accion = request.getParameter("accion");
        
        List optionList= new ArrayList();        
        optionList.add("=");
        optionList.add("<=");
        optionList.add(">=");
        List<ClientsTO> empleados = null;
        
        int page =(request.getParameter("pageId")  != null && request.getParameter("pageId").length() >0  )?Integer.parseInt(request.getParameter("pageId")): 0 ;  
        
        try{
            twoWaysBDL = new TwoWaysBDL();    
            request.setAttribute("optionList",optionList); 
            
            empleados = twoWaysBDL.getServiceTwoWays().obtenerEmpleados();
            request.setAttribute("listaEmpleados",empleados);   
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if(accion !=null && accion.equalsIgnoreCase("buscar")){
            request.setAttribute("accion",accion);
            PaymentsTO pago = new PaymentsTO();   
            SimpleDateFormat sdfsh = new SimpleDateFormat("dd/MM/yyyy");            
            Map params= new  HashMap();          
            java.util.Date date;
            
            if (request.getParameter("payNumber") != null && request.getParameter("payNumber").length() > 0){
                params.put("payNumber",request.getParameter("payNumber")); 
                pago.setPayId(Long.parseLong(request.getParameter("payNumber")));
                request.setAttribute("payNumber",request.getParameter("payNumber")); 
            }
            EmployeesTO empleado = new EmployeesTO(); 
            if(request.getParameter("listaEmpleados") != null && request.getParameter("listaEmpleados").length() > 0  ){ 
              
              Long empId= Long.parseLong(request.getParameter("listaEmpleados"));
              empleado.setEmpId(empId);
              params.put("empId",empId);
              request.setAttribute("empId",empId); 
              
            /*}else{
              params.put("cliId",0);*/
            }
            pago.setEmployeeTO(empleado);
            
            if(request.getParameter("fecha") !=null &&( request.getParameter("fecha").length() == 10 )){ 
                 try {
                     date = sdfsh.parse(request.getParameter("fecha"));
                     java.sql.Timestamp timest = new java.sql.Timestamp(date.getTime()); 
                     pago.setPayDate(timest);
                     params.put("fecha",request.getParameter("fecha"));
                     params.put("payDateOpt",request.getParameter("payDateOpt"));
                     request.setAttribute("fecha",request.getParameter("fecha")); 
                     request.setAttribute("payDateOpt",request.getParameter("payDateOpt")); 
                 } catch (ParseException e) {
                     e.printStackTrace();
                 }
            }            
            try{
               List<PaymentsTO> ipagosList =  twoWaysBDL.getServiceTwoWays().findPayments(params);
               int  pageTop=(page+1)*10 ;
               int  minPage=(page)*10 ;
               List subpagos = null;
                
                
               if(ipagosList.size() > pageTop){ 
                 subpagos= (ipagosList.size() > pageTop )?ipagosList.subList(minPage,pageTop):ipagosList.subList(minPage,ipagosList.size());       
               }else{
                   subpagos=ipagosList;
                   pageTop =subpagos.size();
                   minPage=(pageTop/10)*10;
                   if(pageTop==minPage){
                       minPage=pageTop-10;
                   }
                   if(minPage > 0){ 
                      subpagos = (ipagosList.size() > pageTop )?ipagosList.subList(minPage,pageTop):ipagosList.subList(minPage,ipagosList.size());       
                   }else{
                       pageTop=1;
                       minPage=1; 
                       page=0;
                       
                   }
               }
               int maxPage = 0;
               if (ipagosList.size()== 10) maxPage =1;
               else maxPage =(int)(ipagosList.size() / 10) + 1;
               request.setAttribute("listaPagos",subpagos);
               request.setAttribute("maxPage",maxPage);
               request.setAttribute("page",page);          
              
               request.setAttribute("pageId",page); 
               
                }catch(Exception e){
                   e.printStackTrace(); 
                }        
            }else if (accion!=null && accion.equalsIgnoreCase("imprimir")){
                Long payId = Long.parseLong(request.getParameter("payId"));  
                
                try{
                    twoWaysBDL = new TwoWaysBDL();
                    
                    List<ItemsInvoicesTO> itemsPagoList =  twoWaysBDL.getServiceTwoWays().obtenerItemsPago(payId);
                    ListaPagosServlet.createPdf(request,response,itemsPagoList);
                    
                }catch(Exception e){
                   e.printStackTrace(); 
                }
            }
            
        request.getRequestDispatcher("listaPagos.jsp").forward(request,response);
        }


    public static void createPdf(HttpServletRequest request,HttpServletResponse response, List itemsPagoList)
       throws IOException, DocumentException {
       
        String empId= request.getParameter("empId");
        EmployeesTO empleado = new EmployeesTO();
        TwoWaysBDL twoWaysBDL=null;

        Map auxMap = new HashMap();
        Iterator iterador = itemsPagoList.listIterator();
        auxMap =(HashMap)iterador.next();
        String mesId = auxMap.get("ASSIGNDATE").toString().substring(5,7);
        String anioId = auxMap.get("ASSIGNDATE").toString().substring(0,4);
        
        try{
            twoWaysBDL = new TwoWaysBDL();
            empleado = twoWaysBDL.getServiceTwoWays().getEmpById(empId);     
            } catch (Exception e) {
                e.printStackTrace();
            }    
        // step 1
         Document document = new Document(PageSize.A4.rotate());
        // step 2
        response.setContentType("application/pdf");
        response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
        String disposition = "attachment; filename=" + empleado.getEmpFirstName()+empleado.getEmpLastName()+"_"+mesId+anioId+".pdf";
        response.setHeader("Content-disposition", disposition); 
        PdfWriter.getInstance(document, response.getOutputStream());
        // step 3
        document.open();
        // step 4
        Image image = Image.getInstance(RESOURCE);
        document.add(image);        
        document.add(createTable(request,itemsPagoList));
        // step 5
        document.close();
    }
    
    public static PdfPTable createTable(HttpServletRequest request,List itemsPagoList) throws IOException, DocumentException{
    
        String payAmount = request.getParameter("payAmount");    

        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        // String curId = request.getParameter("curIdOrigen");
        String curSymbol[] =request.getParameter("curSymbol").split("#");        
        
        String empId= request.getParameter("empId");
        EmployeesTO empleado = new EmployeesTO();
        TwoWaysBDL twoWaysBDL=null;

        try{
            twoWaysBDL = new TwoWaysBDL();
            empleado = twoWaysBDL.getServiceTwoWays().getEmpById(empId);     
            } catch (Exception e) {
                e.printStackTrace();
            }    
                
        PdfPTable table = new PdfPTable(6);
        PdfPCell cell; 
        BaseFont bf = BaseFont.createFont();   
        Font ft = new Font(bf,15,Font.BOLD);  

        /*imagen
        table.setWidthPercentage(100f);
        cell = new PdfPCell(Image.getInstance(RESOURCE),true);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setColspan(1);
        cell.setPadding(20);
        table.addCell(cell);
        */
        //fecha de pago
        cell = new PdfPCell(new Phrase("Fecha de pago: "+sdf.format(cal.getTime()),ft));    
        //cell = new PdfPCell();
        cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
        cell.setVerticalAlignment(Element.ALIGN_BOTTOM);
        cell.setColspan(6);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);
        
        //titulo
        //cell = new PdfPCell();

        Font f = new Font(bf,24,Font.BOLD,new BaseColor(0xC2,0x33,0x30));        
        Paragraph p = new Paragraph ("Recibo de pago de "+empleado.getEmpFirstName()+" "+empleado.getEmpLastName(),f);//+" del mes de "+mesId,f);
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
        
        if( itemsPagoList  != null){ 
            Map auxMap = new HashMap();
            Iterator iterador = itemsPagoList.listIterator();
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
                
                }/*else if (curSymbol[0].equalsIgnoreCase("3")){
                    Image pound =Image.getInstance(POUND);
                    pound.scalePercent(55f);
                    cell = new PdfPCell();
                    cell.addElement(pound);
                }*/
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
