package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.OrdersDocsTO;
import com.twoways.to.OrdersTO;
import com.twoways.to.RateTypesTO;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.*;
import javax.servlet.http.*;

import oracle.jdbc.OracleDriver;

public class DownloadFileServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, IOException {
                      
                    
       
        String docId =  request.getParameter("docId");
        
    
        try {
       
            TwoWaysBDL   twoWaysBDL = new TwoWaysBDL();
            OrdersDocsTO doc =  twoWaysBDL.getServiceTwoWays().getOrdersDocById(Long.parseLong(docId) );
             
               
               
            byte[] datos = doc.getOdoDoc();
            
            response.setContentType("application/octet-stream");
            response.setHeader("Content-Disposition","attachment;filename="+doc.getOdoName());
            
            ServletOutputStream ouputStream = response.getOutputStream();
            ouputStream.write(datos);
            ouputStream.flush();
            ouputStream.close();
           // inStream.close(); 
        
        
        
        }catch(Exception e){
            System.out.println(e);
            e.printStackTrace();
        }
        
       
          
    }
    
  

    public void doPost(HttpServletRequest request, 
                       HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
       doGet(request,response); 
    }
}
