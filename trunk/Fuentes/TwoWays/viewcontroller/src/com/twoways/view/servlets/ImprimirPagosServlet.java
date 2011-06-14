package com.twoways.view.servlets;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.GrayColor;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class ImprimirPagosServlet extends HttpServlet {

    public static final String RESOURCE = "C:\\Users\\Nico\\Documents\\TwoWays\\viewcontroller\\public_html\\img\\print_img.png";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, IOException {
        
        try{
            Document document = new Document(PageSize.A4_LANDSCAPE);
            // step 2
            response.setContentType("application/pdf");
            response.setHeader("Cache-Control", "must-revalidate, post-check=0, pre-check=0");
            PdfWriter.getInstance(document, response.getOutputStream());

            //PdfWriter.getInstance(document, baos);
            // step 3
            document.open();
            // step 4
            
            document.add(createTable(request));
            
            // step 5
            document.close();
            
        }
        catch (DocumentException de) {
            throw new IOException(de.getMessage());
        }
    }


    public static PdfPTable createTable(HttpServletRequest request) throws IOException, DocumentException{
          
        String empProjAssignment[] = request.getParameterValues("print-pago-hidden");        
        String payAmount = request.getParameter("payAmount");    
        String empName = request.getParameter("listaEmpleados");
        
        PdfPTable table = new PdfPTable(6);
        PdfPCell cell; 
        //imagen
        table.setWidthPercentage(100f);
        cell = new PdfPCell(Image.getInstance(RESOURCE),true);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setColspan(2);
        cell.setPadding(20);
        table.addCell(cell);
        //titulo
        cell = new PdfPCell();
        //Phrase ph = new Phrase();

        Paragraph p = new Paragraph ("Recibo de pago de "+empName);
        p.setAlignment(Element.ALIGN_CENTER);
        p.setSpacingBefore(5);
        p.setSpacingAfter(5);
        cell.addElement(p);
        cell.setBorder(PdfPCell.NO_BORDER);
        cell.setColspan(6);
        table.addCell(cell);
        
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
                
         for(String aux:empProjAssignment){               
             String atribs[]= aux.split("#");
             cell = new PdfPCell(new Phrase(atribs[0]));
             cell.setVerticalAlignment(Element.ALIGN_LEFT);
             table.addCell(cell);
             cell = new PdfPCell(new Phrase(atribs[1]));
             cell.setVerticalAlignment(Element.ALIGN_LEFT);
             table.addCell(cell);
             cell = new PdfPCell(new Phrase(atribs[2]));
             cell.setVerticalAlignment(Element.ALIGN_LEFT);
             table.addCell(cell);
             cell = new PdfPCell(new Phrase(atribs[3]));
             cell.setVerticalAlignment(Element.ALIGN_RIGHT);
             table.addCell(cell);
             cell = new PdfPCell(new Phrase(atribs[4]));
             cell.setVerticalAlignment(Element.ALIGN_RIGHT);
             table.addCell(cell);
             cell = new PdfPCell(new Phrase(atribs[5]));
             cell.setVerticalAlignment(Element.ALIGN_RIGHT);
             table.addCell(cell);             
         }

        }
        return table;
            
    }
    
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType("application/pdf");
            doGet(request,response); 
            }
    
}
