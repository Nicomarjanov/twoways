package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;

import com.twoways.to.UsersTO;


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
        boolean redirect= true;
        
        session.removeAttribute("userSession");
        session.removeAttribute("userRol");
        
        TwoWaysBDL twoWaysBDL=null;
        
        try {
           twoWaysBDL = new TwoWaysBDL();
           
           
        } catch (Exception e) {
           e.printStackTrace();
        }
        
        try{ 
        if(request.getParameter("logout")==null){
        
            
            if(request.getParameter("usuario") !=null && request.getParameter("password") !=null ){
            
               UsersTO user= twoWaysBDL.getServiceTwoWays().getLogin(request.getParameter("usuario"),request.getParameter("password"));
               if(user !=null){ 
                     session.setAttribute("userSession",user);
                     if(redirect){ 
                         response.sendRedirect("index.jsp");
                         redirect=!redirect;
                     }
                     
               } else{
                   request.setAttribute("mensajeError","El usuario o la clave no son correctos" );
                   if(redirect){ 
                       request.getRequestDispatcher("login.jsp").forward(request,response);
                       redirect=!redirect;
                   }
               }
            }
        }
        
        }catch(Exception e ){
            if(redirect){  
             request.setAttribute("mensajeError","No se puede iniciar la sessión" );
             request.getRequestDispatcher("login.jsp").forward(request,response);
             redirect=!redirect;
            }
            e.printStackTrace();
        }
        if(redirect){  
           response.sendRedirect("login.jsp");
           redirect=!redirect;
        }
    }

    public void doPost(HttpServletRequest request, 
                       HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
        
        doGet(request,response);
    }
}
