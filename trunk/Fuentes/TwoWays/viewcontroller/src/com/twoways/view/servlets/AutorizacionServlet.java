package com.twoways.view.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.*;
import javax.servlet.http.*;

public class AutorizacionServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
       
       /* if(request.getSession() != null && request.getSession().getAttribute("userSession")== null){
            response.sendRedirect("login");
        }else if(request.getSession() != null && !request.getSession().getAttribute("userRol").equals("Admin")){
             request.setAttribute("AuthMsj","<b>No esta Autorizado a ver esta página</b>");
             request.getRequestDispatcher("error.jsp").forward(request,response);
         }
        */
    }

    public void doPost(HttpServletRequest request, 
                       HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
       doGet(request,response);
    }
}
