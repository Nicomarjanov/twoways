package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;

import com.twoways.to.EmployeesTO;


import com.twoways.to.TranslatorsTO;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class AbmTraductoresServlet extends AutorizacionServlet {
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
            
            response.setContentType(CONTENT_TYPE);
            String accion=request.getParameter("accion");

            TranslatorsTO traductor = new TranslatorsTO(); 
            String empId = request.getParameter("empId"); 
            String traId = request.getParameter("traId");
           
            TwoWaysBDL twoWaysBDL=null;
            
        if (accion!=null && accion.equalsIgnoreCase("guardar")){
             try {
                  if(traId != null && traId.length() > 0 ) 
                      traductor =  twoWaysBDL.getServiceTwoWays().getTraById(traId);                                           
             } catch (Exception e) {
                 e.printStackTrace();
             }
             
             
        }                                
    }
}
