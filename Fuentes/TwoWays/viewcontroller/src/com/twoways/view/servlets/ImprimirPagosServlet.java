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

import com.twoways.to.EmployeesTO;
import com.twoways.to.ItemsInvoicesTO;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;


public class ImprimirPagosServlet extends AutorizacionServlet {

//    public static final String RESOURCE = "/home/resources/img/two-ways_print.jpg";    
 //public static final String RESOURCE = "C://Users//Nico//Documents//TwoWays//Fuentes//viewcontroller//public_html//img//two-ways_print.jpg";
//    public static final String EURO = "/home/resources/img/euro.png";   
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        List roles= new ArrayList();
        roles.add("Administrador");
        this.setRolesValidos(roles);
    }

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, IOException {
                      

       super.doGet(request,response);
       TwoWaysBDL twoWaysBDL=null;
       String payId= request.getParameter("payId");
       String empId= request.getParameter("empId");
       String payAmount= request.getParameter("payAmount"); 
       String curId= request.getParameter("currencyId");
       String curSymbol= request.getParameter("currencySymbol"); 
       
       try{
            twoWaysBDL = new TwoWaysBDL();
            
            List<ItemsInvoicesTO> itemsPagoList =  twoWaysBDL.getServiceTwoWays().obtenerItemsPago(Long.parseLong(payId));
            
        
            EmployeesTO empleado = new EmployeesTO();
    
    
            Map auxMap = new HashMap();
            Iterator iterador = itemsPagoList.listIterator();
            auxMap =(HashMap)iterador.next();
            String mesId = auxMap.get("ASSIGNDATE").toString().substring(5,7);
            String anioId = auxMap.get("ASSIGNDATE").toString().substring(0,4);
            
    
               // twoWaysBDL = new TwoWaysBDL();
            empleado = twoWaysBDL.getServiceTwoWays().getEmpById(empId);     
       
            // step 1
             Document document = new Document(PageSize.A4.rotate());
            // step 2
            response.setContentType("application/pdf");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            String disposition = "attachment; filename=" + empleado.getEmpFirstName()+empleado.getEmpLastName()+"_"+mesId+anioId+".pdf";
            response.setHeader("Content-Disposition", disposition); 
            PdfWriter.getInstance(document, response.getOutputStream());
            // step 3
            document.open();
            // step 4
            Image image = Image.getInstance(Image.getInstance(getServletContext().getRealPath("WEB-INF/static/img/two-ways_print.jpg")));
            document.add(image);        
            document.add(createTable(itemsPagoList, empId, payAmount,curId, curSymbol));
            // step 5
            document.close();
        
        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    public PdfPTable createTable(List itemsPagoList, String empId, String payAmount, String curId, String curSymbol) throws IOException, DocumentException{
    
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        
        // String curId = request.getParameter("curIdOrigen");
        //String curency[] =curSymbol.toString().split("#");        
        
       
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
            if (curId != null){
                if(curId.equalsIgnoreCase("2")){
                    Image euro =Image.getInstance(Image.getInstance(getServletContext().getRealPath("WEB-INF/static/img/euro.png")));
                    euro.scalePercent(70f);
                    cell = new PdfPCell();
                    cell.addElement(euro);
                
                }
                else cell = new PdfPCell(new Phrase(curSymbol,ft));
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