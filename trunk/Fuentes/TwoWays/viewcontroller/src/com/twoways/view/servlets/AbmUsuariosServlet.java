package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;

import com.twoways.to.RolesTO;
import com.twoways.to.UsersTO;

import java.io.IOException;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class AbmUsuariosServlet extends AutorizacionServlet {
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
            List<RolesTO> roles = null;
            UsersTO usuario = new UsersTO(); 
            String userId = request.getParameter("userId"); 
           
            TwoWaysBDL twoWaysBDL=null;
            
            try {
               twoWaysBDL = new TwoWaysBDL();
               roles =  twoWaysBDL.getServiceTwoWays().obtenerRoles();
               request.setAttribute("listaRoles",roles);
               
               
            } catch (Exception e) {
               e.printStackTrace();
            }
            
            
            if (accion!=null && accion.equalsIgnoreCase("guardar")){
                 try {
                      if(userId != null && userId.length() > 0 ) 
                          usuario =  twoWaysBDL.getServiceTwoWays().getUserById(userId);                                           
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
                                  
                RolesTO rol= new RolesTO(); 
                rol.setRolId((request.getParameter("listaRoles")!= null && request.getParameter("listaRoles").length() > 0 )?Long.parseLong(request.getParameter("listaRoles")):0);        
                usuario.setUsrId((request.getParameter("usrId")!= null )?request.getParameter("usrId"):"");                
                usuario.setUsrPass((request.getParameter("usrPass")!= null )?request.getParameter("usrPass"):"");                 
                usuario.setUsrFirstName((request.getParameter("usrFirstName")!= null )?request.getParameter("usrFirstName"):"");
                usuario.setUsrLastName((request.getParameter("usrLastName")!= null )?request.getParameter("usrLastName"):"");
                usuario.setUsrMail((request.getParameter("usrMail")!= null )?request.getParameter("usrMail"):"");
                try{
                    if (request.getParameter("usrMobileNumber")!=null && !request.getParameter("usrMobileNumber").equalsIgnoreCase("")){
                        usuario.setUsrMobileNumber(Long.valueOf(request.getParameter("usrMobileNumber")));
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                    request.setAttribute("mensaje","<script>alert('El número de Teléfono movil ingresado no es valido')</script>"); 
                    request.getRequestDispatcher("usuario.jsp").forward(request,response);
                }
                try{
                    if (request.getParameter("usrOfficeNumber")!=null && !request.getParameter("usrOfficeNumber").equalsIgnoreCase("")){
                        usuario.setUsrMobileNumber(Long.valueOf(request.getParameter("usrOfficeNumber")));
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                    request.setAttribute("mensaje","<script>alert('El número de Teléfono de la oficina ingresado no es valido')</script>"); 
                    request.getRequestDispatcher("usuario.jsp").forward(request,response);
                }
                try{
                    if (request.getParameter("usrPhoneNumber")!=null && !request.getParameter("usrPhoneNumber").equalsIgnoreCase("")){
                        usuario.setUsrMobileNumber(Long.valueOf(request.getParameter("usrPhoneNumber")));
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                    request.setAttribute("mensaje","<script>alert('El número de Teléfono particular ingresado no es valido')</script>"); 
                    request.getRequestDispatcher("usuario.jsp").forward(request,response);
                }
                try {
                if(request.getParameter("usrBirth")!= null && !request.getParameter("usrBirth").equalsIgnoreCase("") ){ 
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date date = sdf.parse(request.getParameter("usrBirth"));
                    java.sql.Timestamp timest = new java.sql.Timestamp(date.getTime()); 
                    usuario.setUsrBirth(timest);
                }
                
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("mensaje","<script>alert('La fecha ingresada no es valida')</script>"); 
                    request.getRequestDispatcher("usuario.jsp").forward(request,response);
                }
                usuario.setRolesTO(rol);

                try {
                    
                    if(userId != null && userId.length() > 0 ){ 
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
            else  if(userId != null && userId.length() > 0  && (accion == null || (accion!=null && !accion.equalsIgnoreCase("cancelar")) )){
              
            
                 try {
                         usuario =  twoWaysBDL.getServiceTwoWays().getUserById(userId);
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
            
  
}
