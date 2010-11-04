package com.twoways.view.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class LoginServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
        
        
        HttpSession session = request.getSession(); 
        
        session.removeAttribute("userSession");
        session.removeAttribute("userRol");
        
        if(request.getParameter("logout")==null){
        
                
            if(request.getParameter("usuario") !=null && request.getParameter("password") !=null ){
            //TODO Validar Usuario y rol
               session.setAttribute("userSession",request.getParameter("usuario"));
               if(request.getParameter("usuario").equalsIgnoreCase("anabel")) 
                             session.setAttribute("userRol","Admin");
               else 
                     session.setAttribute("userRol","User"); 
                     response.sendRedirect("index.jsp");
            }
        }
        
        
        
        response.sendRedirect("login.jsp"); 
    }

    public void doPost(HttpServletRequest request, 
                       HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
        
        doGet(request,response);
    }
}
