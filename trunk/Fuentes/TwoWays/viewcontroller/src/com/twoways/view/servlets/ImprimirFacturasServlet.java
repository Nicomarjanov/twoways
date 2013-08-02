package com.twoways.view.servlets;

import com.itextpdf.text.BaseColor;
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

import com.twoways.to.ItemsInvoicesTO;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.DecimalFormat;
import java.text.NumberFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.*;
import javax.servlet.http.*;

public class ImprimirFacturasServlet extends AutorizacionServlet {
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
         TwoWaysBDL twoWaysBDL=null;
         String cliId= request.getParameter("cliId");
         String invId= request.getParameter("invId");
         String accId= request.getParameter("accId");
         String invTotal= request.getParameter("invTotal");
         String currencyId= request.getParameter("curId");
         String currencySymbol= request.getParameter("curSymbol");         
         String datosAdicionales= request.getParameter("datos");         

        ClientsTO cliente = new ClientsTO();
        

        try{
            twoWaysBDL = new TwoWaysBDL();
            cliente = twoWaysBDL.getServiceTwoWays().getClientById(cliId);     
            List<ItemsInvoicesTO> itemsFacturaList =  twoWaysBDL.getServiceTwoWays().obtenerItemsFactura(Long.parseLong(invId));
            
            // step 1
             Document document = new Document(PageSize.A4.rotate());
            // step 2
            response.setContentType("application/pdf");
            response.setContentType(CONTENT_TYPE);
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
                  
            String invDate = request.getParameter("invDate");
            invDate.replace("/","");
    
            String disposition = "attachment; filename= Invoice_" + cliente.getCliName()+"_"+invDate+".pdf";
            response.setHeader("Content-Disposition", disposition); 
            PdfWriter.getInstance(document, response.getOutputStream());
            // step 3
            document.open();
            // step 4
            Image image = Image.getInstance(Image.getInstance(getServletContext().getRealPath("WEB-INF/static/img/two-ways_print.jpg")));
            document.add(image);
            document.add(createTableEncabezado(invId,cliente,datosAdicionales, invDate, accId));
            document.add(createTable(itemsFacturaList,invTotal, currencyId, currencySymbol));
            // step 5
            document.close();
        } catch (Exception e) {
            e.printStackTrace();
        } 
     }
        
     public PdfPTable createTableEncabezado(String invId,ClientsTO cliente, String datosAdicionales, String invDate, String accId) throws IOException, DocumentException{
               
        PdfPTable table = new PdfPTable(4);
        PdfPCell cell;
        BaseFont bf = BaseFont.createFont();
        Font ft = new Font(bf,15,Font.BOLD);
        
        
        table.setWidthPercentage(100f);
        /*  Image image = Image.getInstance(RESOURCE);
        
        cell = new PdfPCell(image,true);
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setColspan(1);
        cell.setPadding(20);
        table.addCell(cell);
        */
        //fecha de pago
        cell = new PdfPCell(new Phrase("Date: "+invDate,ft));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setVerticalAlignment(Element.ALIGN_TOP);
        cell.setColspan(3);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setPaddingBottom(20);
        cell.setPaddingTop(15);
        table.addCell(cell);
        
        //Numero de factura
        cell = new PdfPCell(new Phrase("Invoice #: "+invId,ft));
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
        cell = new PdfPCell(new Phrase((datosAdicionales !=null)?datosAdicionales:""));
        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
        cell.setColspan(4);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);
        /*Fila en blanco
        cell = new PdfPCell(new Phrase(""));
        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
        cell.setColspan(2);
        cell.setBorder(PdfPCell.NO_BORDER);
        table.addCell(cell);
        */
        return table;
        
        }

     public PdfPTable createTable(List itemsFacturaList,String invTotal, String curId, String curSymbol) throws IOException, DocumentException{
        
        NumberFormat formatter = new DecimalFormat("#0.00");
         
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

        Map auxMap = new HashMap();
        Iterator iterador = itemsFacturaList.listIterator();
        String bandera1 = "1";
        String bandera = null;
        String bandNom = null;
        Double subtotal = 0.0;
        while( iterador.hasNext() ) {
         auxMap =(HashMap)iterador.next();
         if(bandera != null && !bandera.equalsIgnoreCase(auxMap.get("ORDID").toString()) && bandera1.equalsIgnoreCase("0")){
             cell = new PdfPCell(new Phrase("Subtotal "+bandNom+":",cf));
             cell.setColspan(7);
             cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
             cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
             table.addCell(cell);      

             table.addCell(formatter.format(subtotal));
             cell = new PdfPCell();
             cell.setBorder(PdfPCell.NO_BORDER);        
             table.addCell(cell);
             subtotal = 0.0;
             bandera1 = "1";
         }
         subtotal += Double.valueOf(auxMap.get("ORDTOTAL").toString());
         bandera = auxMap.get("ORDID").toString();
         bandNom = auxMap.get("ORDNAME").toString();
         bandera1 ="0";
         
          cell = new PdfPCell(new Phrase((auxMap.get("PROJID")!=null)?auxMap.get("PROJID").toString():"",cf));
          cell.setHorizontalAlignment(Element.ALIGN_LEFT);
          table.addCell(cell);
          cell = new PdfPCell(new Phrase((auxMap.get("ORDJOBID")!=null)?auxMap.get("ORDJOBID").toString():"",cf));
          cell.setHorizontalAlignment(Element.ALIGN_LEFT);
          table.addCell(cell);
          cell = new PdfPCell(new Phrase((auxMap.get("ORDWONUMBER")!=null)?auxMap.get("ORDWONUMBER").toString():"",cf));
          cell.setHorizontalAlignment(Element.ALIGN_LEFT);
          table.addCell(cell);                      
          cell = new PdfPCell(new Phrase((auxMap.get("ORDJOBNAME")!=null)?auxMap.get("ORDJOBNAME").toString():"",cf));
          cell.setHorizontalAlignment(Element.ALIGN_LEFT);
          table.addCell(cell);
          cell = new PdfPCell(new Phrase((auxMap.get("JOBDESC")!=null)?auxMap.get("JOBDESC").toString():"",cf));
          cell.setHorizontalAlignment(Element.ALIGN_LEFT);
          table.addCell(cell);
         /* cell = new PdfPCell(new Phrase(auxMap.get("ITMNAME").toString(),cf));
          cell.setHorizontalAlignment(Element.ALIGN_LEFT);
          table.addCell(cell);*/
          cell = new PdfPCell(new Phrase((auxMap.get("ORRWCOUNT")!=null)?auxMap.get("ORRWCOUNT").toString():"",cf));
          cell.setHorizontalAlignment(Element.ALIGN_LEFT);
          table.addCell(cell);
          String cur = (auxMap.get("CURSYMBOL")!=null)?auxMap.get("CURSYMBOL").toString():"";
          String aux = (auxMap.get("ORRVALUE")!=null)?auxMap.get("ORRVALUE").toString():"";
          cell = new PdfPCell(new Phrase(cur+"  "+aux,cf));
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          table.addCell(cell);
          cell = new PdfPCell(new Phrase((auxMap.get("ORDTOTAL")!=null)?auxMap.get("ORDTOTAL").toString():"",cf));
          cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
          table.addCell(cell);
          cell = new PdfPCell(new Phrase((auxMap.get("CRENAME")!=null)?auxMap.get("CRENAME").toString():"",cf));
          cell.setHorizontalAlignment(Element.ALIGN_LEFT);
          table.addCell(cell);
        }
         //subtotal final
         cell = new PdfPCell(new Phrase("Subtotal "+bandNom+":",cf));
         cell.setColspan(7);
         cell.setBackgroundColor(BaseColor.LIGHT_GRAY);
         cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
         table.addCell(cell);      

         table.addCell(formatter.format(subtotal));     
         cell = new PdfPCell();
         cell.setBorder(PdfPCell.NO_BORDER);        
         table.addCell(cell);
         
         //Linea en blanco
         cell = new PdfPCell();
         cell.setBorder(PdfPCell.NO_BORDER);
         cell.setColspan(9);
         table.addCell(cell);
         //Total a pagar
         cell = new PdfPCell(new Phrase("Total: ",ft));           
         cell.setBorder(PdfPCell.NO_BORDER);
         cell.setColspan(7);
         cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
         table.addCell(cell); 
         if (curId != null){
             if(curId.equalsIgnoreCase("2")){
                 Image euro =Image.getInstance(Image.getInstance(getServletContext().getRealPath("WEB-INF/static/img/euro.png")));
                 euro.scalePercent(70f);
                 cell = new PdfPCell();
                 cell.addElement(euro);
             
             }/*else if (curSymbol[0].equalsIgnoreCase("3")){
                 Image pound =Image.getInstance(POUND);
                 pound.scalePercent(55f);
                 cell = new PdfPCell();
                 cell.addElement(pound);
             }*/
             else cell = new PdfPCell(new Phrase(curSymbol,ft));
             cell.setBorder(PdfPCell.NO_BORDER);
             cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
             //cell.setColspan(2);
             table.addCell(cell);  
         }
         cell = new PdfPCell(new Phrase(invTotal,ft));
         cell.setBorder(PdfPCell.NO_BORDER);
         cell.setHorizontalAlignment(Element.ALIGN_LEFT);
         table.addCell(cell);
        
        return table;
         
        }
}
