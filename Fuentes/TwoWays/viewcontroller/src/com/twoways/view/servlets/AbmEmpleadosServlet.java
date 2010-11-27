package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ClientsRatesTO;
import com.twoways.to.EmployeesTO;
import com.twoways.to.RateTypesTO;
import com.twoways.to.RatesTO;
import com.twoways.to.EmployeeTypeTO;

import com.twoways.to.EmployeesRatesTO;

import com.twoways.to.EmployeesTypesTO;

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
            List<EmployeeTypeTO> tipo = null;
            EmployeesTO empleado = new EmployeesTO(); 
            String empId = request.getParameter("empId"); 
            String paginaSiguiente = "empleados.jsp";
           
            TwoWaysBDL twoWaysBDL=null;
            
            try {
                twoWaysBDL = new TwoWaysBDL();
                //twoWaysBDL.getServiceTwoWays().obtenerMonedas();                             
                tipo =  twoWaysBDL.getServiceTwoWays().obtenerTipoEmpleado();
                request.setAttribute("listaTipoEmp",tipo);
                
                request.setAttribute("listaTipoEmpTar",tipo);
                
                tarifas = twoWaysBDL.getServiceTwoWays().obtenerTarifas();
                request.setAttribute("listaTarifa",tarifas);
               
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
                                  
                String tarifasHidden[]=request.getParameterValues("tarifas-hidden");
                List<EmployeesRatesTO> employeesRatesTOList = new   ArrayList<EmployeesRatesTO>();
                
                if( tarifasHidden  != null){ 
                                       
                    for(String aux:tarifasHidden){
                        
                        String atribs[]= aux.split("#");
                        
                        EmployeesRatesTO employeesRatesTO = new EmployeesRatesTO();
                        employeesRatesTO.setEmrValue(Float.parseFloat(atribs[1].replaceAll(",",".")));
                        RatesTO rtTO= new RatesTO();
                        rtTO.setRatId(Long.parseLong(atribs[0]));
                        employeesRatesTO.setRatesTO(rtTO );
                        employeesRatesTOList.add(employeesRatesTO);
                        employeesRatesTO.setEmployeesTO(empleado);
                    }
                }     
                
                String empleadosTipos[]=request.getParameterValues("listaItemsSelect");
                List<EmployeesTypesTO> employeesTypesTOList = new   ArrayList<EmployeesTypesTO>();
                
                if( empleadosTipos  != null){ 
                                       
                    for(String aux:empleadosTipos){
                                                                    
                        EmployeesTypesTO employeesTypesTO = new EmployeesTypesTO();
                        employeesTypesTO.setEmployeesTO(empleado);
                        EmployeeTypeTO etTO = new EmployeeTypeTO();
                        etTO.setEtyName(aux);
                        employeesTypesTO.setEmployeeTypeTO(etTO);              
                        employeesTypesTOList.add(employeesTypesTO);                        
                    }
                }  
                empleado.setEmpFirstName((request.getParameter("empFirstName")!= null )?request.getParameter("empFirstName"):"");
                empleado.setEmpLastName((request.getParameter("empLastName")!= null )?request.getParameter("empLastName"):"");
                empleado.setEmpMail((request.getParameter("empMail")!= null )?request.getParameter("empMail"):"");
                empleado.setEmpMsn((request.getParameter("empMsn")!= null )?request.getParameter("empMsn"):"");               
                empleado.setEmpAddress((request.getParameter("empAddress")!= null )?request.getParameter("empAddress"):"");
                empleado.setEmpLocation((request.getParameter("empLocation")!= null )?request.getParameter("empLocation"):"");                
                empleado.setEmpLocation((request.getParameter("empObservations")!= null )?request.getParameter("empObservations"):"");    
                empleado.setEmployeesRatesTOList(employeesRatesTOList);
                empleado.setEmployeesTypesTOList(employeesTypesTOList);
                try{
                    if (request.getParameter("empMobileNumber")!=null && !request.getParameter("empMobileNumber").equalsIgnoreCase("")){
                        empleado.setEmpMobileNumber(Long.valueOf(request.getParameter("empMobileNumber")));
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                    request.setAttribute("mensaje","<script>alert('El n�mero de Tel�fono movil ingresado no es valido')</script>"); 
                    request.getRequestDispatcher("usuario.jsp").forward(request,response);
                }
                try{
                    if (request.getParameter("empPhoneNumber")!=null && !request.getParameter("empPhoneNumber").equalsIgnoreCase("")){
                        empleado.setEmpPhoneNumber(Long.valueOf(request.getParameter("empPhoneNumber")));
                    }
                }
                catch(Exception e) {
                    e.printStackTrace();
                    request.setAttribute("mensaje","<script>alert('El n�mero de Tel�fono particular ingresado no es valido')</script>"); 
                    request.getRequestDispatcher("usuario.jsp").forward(request,response);
                }
                try {
                if(request.getParameter("empBirth")!= null && !request.getParameter("empBirth").equalsIgnoreCase("") ){ 
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

                try {
                    if(empId != null && empId.length() > 0 ){ 
                        twoWaysBDL.getServiceTwoWays().updateEmpleado(empleado);
                        request.setAttribute("empleado",empleado);
                       
                    }else{
                        twoWaysBDL.getServiceTwoWays().insertarEmpleado(empleado);
                        request.setAttribute("empleado",empleado);
                    }
                    
                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("mensaje","<script>alert('Ocurri� un error al guardar el empleado')</script>");  
                    request.getRequestDispatcher("empleados.jsp").forward(request,response);
                }
               
                request.setAttribute("mensaje","<script>alert('El empleado se guardo con exito')</script>");
                
                if( empleadosTipos  != null){ 
                                       
                    for(String aux:empleadosTipos){                                                                
                        if (aux.equalsIgnoreCase("Traductor")){
                            paginaSiguiente = "traductor.jsp";
                        }                       
                    }
                }                  
                               
            }
            else if(empId != null && empId.length() > 0  && (accion!=null && accion.equalsIgnoreCase("eliminar")) ){
                try {
                     if(empId != null && empId.length() > 0 ) 
                         empleado =  twoWaysBDL.getServiceTwoWays().getEmpById(empId);                                           
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String tarifasHidden[]=request.getParameterValues("tarifas-hidden");
                List<EmployeesRatesTO> employeesRatesTOList = new   ArrayList<EmployeesRatesTO>();
                
                if( tarifasHidden  != null){ 
                                       
                    for(String aux:tarifasHidden){
                        
                        String atribs[]= aux.split("#");
                        
                        EmployeesRatesTO employeesRatesTO = new EmployeesRatesTO();
                        employeesRatesTO.setEmrValue(Float.parseFloat(atribs[1].replaceAll(",",".")));
                        RatesTO rtTO= new RatesTO();
                        rtTO.setRatId(Long.parseLong(atribs[0]));
                        employeesRatesTO.setRatesTO(rtTO );
                        employeesRatesTOList.add(employeesRatesTO);
                        employeesRatesTO.setEmployeesTO(empleado);
                    }
                }     
                
                String empleadosTipos[]=request.getParameterValues("listaItemsSelect");
                List<EmployeesTypesTO> employeesTypesTOList = new   ArrayList<EmployeesTypesTO>();
                
                if( empleadosTipos  != null){ 
                                       
                    for(String aux:empleadosTipos){
                                                                    
                        EmployeesTypesTO employeesTypesTO = new EmployeesTypesTO();
                        employeesTypesTO.setEmployeesTO(empleado);
                        EmployeeTypeTO etTO = new EmployeeTypeTO();
                        etTO.setEtyName(aux);
                        employeesTypesTO.setEmployeeTypeTO(etTO);              
                        employeesTypesTOList.add(employeesTypesTO);                        
                    }
                }
                try {
                    empleado.setEmployeesRatesTOList(employeesRatesTOList);
                    empleado.setEmployeesTypesTOList(employeesTypesTOList);
                    twoWaysBDL.getServiceTwoWays().deleteEmployees(empleado);
                    //request.setAttribute("empleado",empleado);
                    }                    
                catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("mensaje","<script>alert('Ocurri� un error al eliminar el empleado')</script>");
                    request.getRequestDispatcher("empleados.jsp").forward(request,response);
                }
                request.setAttribute("mensaje","<script>alert('El empleado se elimin� con �xito')</script>");
            }
            else if(empId != null && empId.length() > 0  && (accion == null ))//|| (accion!=null && !accion.equalsIgnoreCase("cancelar")) ))
            {                        
                 try {
                         empleado =  twoWaysBDL.getServiceTwoWays().getEmpById(empId);
                         request.setAttribute("empleado",empleado);

                         if(empleado == null){
                             request.setAttribute("mensaje","<script>alert('El empleado no existe')</script>"); 
                         }else{
                         request.setAttribute("script","<script>init();</script>");
                         }
                     
                 } catch (Exception e) {
                     e.printStackTrace();                 
                     request.setAttribute("mensaje","<script>alert('Error al cargar el empleado')</script>"); 
                    // request.getRequestDispatcher("empleados.jsp").forward(request,response);
                 }
            }
                     
    request.getRequestDispatcher(paginaSiguiente).forward(request,response);
            
}
            
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType(CONTENT_TYPE);
            doGet(request,response); 
            }
}
