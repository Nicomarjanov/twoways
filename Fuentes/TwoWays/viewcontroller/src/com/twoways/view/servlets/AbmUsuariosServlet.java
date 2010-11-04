package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;

import com.twoways.to.RolesTO;

import com.twoways.to.UsersTO;

import java.io.IOException;

import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class AbmUsuariosServlet extends HttpServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";

    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, IOException {
        
            response.setContentType(CONTENT_TYPE);
            String accion=request.getParameter("accion");
            List<RolesTO> roles = null;
            UsersTO usuario = new UsersTO(); 
            String usrId = request.getParameter("usriId"); 
           
            TwoWaysBDL twoWaysBDL=null;
            
            try {
               twoWaysBDL = new TwoWaysBDL();
               twoWaysBDL.getServiceTwoWays().obtenerRoles();
               roles =  twoWaysBDL.getServiceTwoWays().obtenerRoles();
               request.setAttribute("listaRoles",roles);
               
               
            } catch (Exception e) {
               e.printStackTrace();
            }
            
            
            if (accion!=null && accion.equalsIgnoreCase("guardar")){
                 try {
                      if(usrId != null && usrId.length() > 0 ) 
                          usuario =  twoWaysBDL.getServiceTwoWays().getUserById(usrId);                                           
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
                                    
                RolesTO rol= new RolesTO(); 
                rol.setRolId((request.getParameter("listaRoles")!= null && request.getParameter("listaRoles").length() > 0 )?Long.parseLong(request.getParameter("listaRoles")):0);        
                usuario.setUsrFirstName((request.getParameter("usrFirstName")!= null )?request.getParameter("usrFirstName"):"");
                usuario.setUsrLastName((request.getParameter("usrLastName")!= null )?request.getParameter("usrLastName"):"");
                usuario.setUsrMail((request.getParameter("usrMail")!= null )?request.getParameter("usrMail"):"");
                usuario.setUsrMobileNumber(Long.valueOf((request.getParameter("usrMobileNumber")!= null )?request.getParameter("usrMobileNumber"):""));
                usuario.setUsrOfficeNumber(Long.valueOf((request.getParameter("usrOfficeNumber")!= null )?request.getParameter("usrOfficeNumber"):""));
                usuario.setUsrPhoneNumber(Long.valueOf((request.getParameter("usrPhoneNumber")!= null )?request.getParameter("usrPhoneNumber"):""));
                usuario.setUsrBirth((request.getParameter("usrBirth")!= null )?Long.parseLong(request.getParameter("usrBirth")):0);
                usuario.setRolesTO(rol);

                try {
                    
                    if(usrId != null && usrId.length() > 0 ){ 
                        twoWaysBDL.getServiceTwoWays().updateUsuario(usuario);
                        request.setAttribute("usuario",usuario);
                       
                    }else{
                        twoWaysBDL.getServiceTwoWays().insertarUsuario(usuario);
                        
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
               
                request.setAttribute("mensaje","<script>alert('El usuario se guardo con exito')</script>");
                
                
                
            }
            else  if(usrId != null && usrId.length() > 0  && (accion == null || (accion!=null && !accion.equalsIgnoreCase("cancelar")) )){
              
            
                 try {
                         usuario =  twoWaysBDL.getServiceTwoWays().getUserById(usrId);
                         request.setAttribute("usuario",usuario);
                         if(usuario == null){
                             request.setAttribute("mensaje","<script>alert('El usuario no existe')</script>"); 
                         }
                     
                 } catch (Exception e) {
                     request.setAttribute("mensaje","<script>alert('Error al cargar el usuario')</script>"); 
                     e.printStackTrace();
                 }
            }
            
            request.getRequestDispatcher("usuario.jsp").forward(request,response);
            }
            
            public void doPost(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
            doGet(request,response); 
            }
            }