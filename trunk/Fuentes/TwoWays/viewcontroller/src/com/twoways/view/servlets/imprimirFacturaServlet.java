package com.twoways.view.servlets;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.AccountsTO;
import com.twoways.to.ClientsTO;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class imprimirFacturaServlet extends AutorizacionServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    public static final String RESOURCE = "C:\\apache-tomcat-7.0.5\\webapps\\img\\print_img.png";
    public static final String EURO = "C:\\apache-tomcat-7.0.5\\webapps\\img\\euro.png";    

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        List roles= new ArrayList();
        roles.add("Administrador");
        this.setRolesValidos(roles);
    }

    public static void main(HttpServletRequest request,HttpServletResponse response)
       throws IOException, DocumentException, ServletException {
    
       // step 1
        Document document = new Document(PageSize.A4.rotate());
       // step 2
       String content= new String("application/pdf".getBytes(),"windows-1252");
       response.setContentType(content);
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
       document.add(createTableEncabezado(request,cliente));
       document.add(createTable(request));
       // step 5
       document.close();

//       request.getRequestDispatcher("facturacion.jsp").forward(request,response);
       
    }
    
    public static PdfPTable createTableEncabezado(HttpServletRequest request,ClientsTO cliente) throws IOException, DocumentException{
      
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
    cell = new PdfPCell(new Phrase("Invoice #: "+0,ft));    
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
    BaseFont bfc = BaseFont.createFont(BaseFont.HELVETICA,"Cp1252",BaseFont.NOT_EMBEDDED);     
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
            if(curSymbol[0].equalsIgnoreCase("2")){
                Image euro =Image.getInstance(EURO);
                euro.scalePercent(80f);
                cell = new PdfPCell();
                cell.addElement(euro);
            
            }
            else cell = new PdfPCell(new Phrase(curSymbol[1],ft));
            cell.setBorder(PdfPCell.NO_BORDER);
            cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
            table.addCell(cell);  
        }
        cell = new PdfPCell(new Phrase(invTotal,ft));
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        table.addCell(cell);
    }
    return table;
        
    }

}
