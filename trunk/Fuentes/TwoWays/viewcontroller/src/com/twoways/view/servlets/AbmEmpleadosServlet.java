package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.EmployeesTO;
import com.twoways.to.RatesTO;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class AbmEmpleadosServlet extends AutorizacionServlet {
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
            List<RatesTO> tarifas = null;
            EmployeesTO empleado = new EmployeesTO(); 
            String empId = request.getParameter("empId"); 
           
            TwoWaysBDL twoWaysBDL=null;
            
            try {
               twoWaysBDL = new TwoWaysBDL();
               tarifas =  twoWaysBDL.getServiceTwoWays().obtenerTarifas();
               request.setAttribute("listaTarifas",tarifas);
               
               
            } catch (Exception e) {
               e.printStackTrace();
            }
            
            
            if (accion!=null && accion.equalsIgnoreCase("guardar")){
                 try {
                      if(empId != null && empId.length() > 0 ) 
                          empleado =  twoWaysBDL.getServiceTwoWays().getEmpById(empId);                                           
                 } catch (Exception e) {
                     e.printStackTrace();
                 }
                                    
                RatesTO tarifa= new RatesTO(); 
                tarifa.setRatId((request.getParameter("listaTarifas")!= null && request.getParameter("listaTarifas").length() > 0 )?Long.parseLong(request.getParameter("listaTarifas")):0);        
                empleado.setEmpFirstName((request.getParameter("empFirstName")!= null )?request.getParameter("empFirstName"):"");
                empleado.setEmpLastName((request.getParameter("empLastName")!= null )?request.getParameter("empLastName"):"");
                empleado.setEmpMail((request.getParameter("empMail")!= null )?request.getParameter("empMail"):"");
                empleado.setEmpMsn((request.getParameter("empMsn")!= null )?request.getParameter("empMsn"):"");
                empleado.setEmpMobileNumber(Long.valueOf((request.getParameter("empMobileNumber")!= null )?request.getParameter("empMobileNumber"):""));
                empleado.setEmpPhoneNumber(Long.valueOf((request.getParameter("empPhoneNumber")!= null )?request.getParameter("empPhoneNumber"):""));
                empleado.setEmpAddress((request.getParameter("empAddress")!= null )?request.getParameter("empAddress"):"");
                empleado.setEmpLocation((request.getParameter("empLocation")!= null )?request.getParameter("empLocation"):"");                
                empleado.setEmpLocation((request.getParameter("empObservations")!= null )?request.getParameter("empObservations"):"");                                

                try {
                if(request.getParameter("empBirth")!= null ){ 
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date date = sdf.parse(request.getParameter("empBirth"));
                    java.sql.Timestamp timest = new java.sql.Timestamp(date.getTime()); 
                    empleado.setEmpBirth(timest);
                }
                
                } catch (Exception e) {
                    request.setAttribute("mensaje","<script>alert('La fecha ingresada no es valida')</script>"); 
                    e.printStackTrace();
                    request.getRequestDispatcher("empleados.jsp").forward(request,response);
                }
                empleado.setRatesTO(tarifa);

                try {
                    
                    if(empId != null && empId.length() > 0 ){ 
                        twoWaysBDL.getServiceTwoWays().updateEmpleado(empleado);
                        request.setAttribute("empleado",empleado);
                       
                    }else{
                        twoWaysBDL.getServiceTwoWays().insertarEmpleado(empleado);
                        
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                }
               
                request.setAttribute("mensaje","<script>alert('El empleado se guardo con exito')</script>");
                
                
                
            }
            else  if(empId != null && empId.length() > 0  && (accion == null || (accion!=null && !accion.equalsIgnoreCase("cancelar")) )){
              
            
                 try {
                         empleado =  twoWaysBDL.getServiceTwoWays().getEmpById(empId);
                         request.setAttribute("empleado",empleado);
                         if(empleado == null){
                             request.setAttribute("mensaje","<script>alert('El empleado no existe')</script>"); 
                         }
                     
                 } catch (Exception e) {
                     request.setAttribute("mensaje","<script>alert('Error al cargar el empleado')</script>"); 
                     e.printStackTrace();
                 }
            }
            
            request.getRequestDispatcher("empleados.jsp").forward(request,response);
            }
            
            public void doPost(HttpServletRequest request,
                           HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
            doGet(request,response); 
            }
            }
