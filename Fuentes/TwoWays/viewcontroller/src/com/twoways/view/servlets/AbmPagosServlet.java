package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.AccountsTO;
import com.twoways.to.CurrencyTO;
import com.twoways.to.EmployeeTypeTO;

import com.twoways.to.EmployeesTO;

import com.twoways.to.ItemsExpensesTO;
import com.twoways.to.PaymentsTO;
import com.twoways.to.ProjAssignPaysTO;
import com.twoways.to.ProjectAssignmentsTO;

import com.twoways.to.ProjectsTO;

import java.io.IOException;
import java.io.PrintWriter;

import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class AbmPagosServlet extends AutorizacionServlet {
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
        List<CurrencyTO> monedas = null;
        List<AccountsTO> cuentas = null;          
        List<EmployeesTO> empleados = null;
        
        String empId = request.getParameter("empId");
            
        Calendar c = new GregorianCalendar();
        String dia;
        String mes;
        String annio;
        dia = Integer.toString(c.get(Calendar.DATE));
        mes = Integer.toString(c.get(Calendar.MONTH)+1);
        annio = Integer.toString(c.get(Calendar.YEAR));
        if (dia.length()==1){
            dia="0"+dia;
        }
        if (mes.length()==1){
            mes="0"+mes;
        }
        String fecha=(""+dia+"/"+mes+"/"+annio);
        request.setAttribute("auxDate",fecha); 
        
        TwoWaysBDL twoWaysBDL=null;
        
        try {
            twoWaysBDL = new TwoWaysBDL();
            monedas =  twoWaysBDL.getServiceTwoWays().obtenerMonedas();
            request.setAttribute("listaMoneda",monedas);            
            
            cuentas =  twoWaysBDL.getServiceTwoWays().obtenerAccount();
            request.setAttribute("listaCuentas",cuentas);        
            
        } catch (Exception e) {
           e.printStackTrace();
        }
        if (accion!=null && accion.equalsIgnoreCase("buscarAsignaciones") && empId != null){
            try{
                List projAssignEmpId = null; 
                projAssignEmpId = twoWaysBDL.getServiceTwoWays().getProjectAssignmentsByEmpId(Long.parseLong(empId));
                request.setAttribute("projectAssignnments",projAssignEmpId);
                request.setAttribute("empId",empId);
                empleados =  twoWaysBDL.getServiceTwoWays().obtenerEmpleados();
                request.setAttribute("listaEmpleados",empleados);
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
        else if (accion!=null && accion.equalsIgnoreCase("guardar") && empId != null){
            PaymentsTO pago = new PaymentsTO();
            try {              
                if(request.getParameter("payDate")!= null && !request.getParameter("payDate").equalsIgnoreCase("") ){ 
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                    java.util.Date date = sdf.parse(request.getParameter("payDate"));
                    java.sql.Timestamp timest = new java.sql.Timestamp(date.getTime()); 
                    pago.setPayDate(timest);
                }
            
            } catch (Exception e) {
                request.setAttribute("mensaje","<script>alert('La fecha ingresada no es valida')</script>"); 
                e.printStackTrace();
                request.getRequestDispatcher("pagos.jsp").forward(request,response);
            }                           
            EmployeesTO empIdTO = new EmployeesTO();
            empIdTO.setEmpId(Long.parseLong(request.getParameter("empId")));
            pago.setEmployeeTO(empIdTO);
            
            String listaCuentas= request.getParameter("listaCuentas");
            if( listaCuentas  != null){ 
                    String atribs[]= listaCuentas.split("#");                    
                    AccountsTO accIdTO = new AccountsTO();
                    accIdTO.setAccId(Long.parseLong(atribs[0]));
                    pago.setAccountsTO(accIdTO);

            }
            
            String listaMoneda = request.getParameter("listaMoneda");
            if (listaMoneda != null) {
                String atribs[]= listaMoneda.split("#");  
                CurrencyTO curIdTO = new CurrencyTO();
                curIdTO.setCurId(Long.parseLong(atribs[0]));
                pago.setCurrencyTO(curIdTO);
            }
            
            pago.setPayAmount(Long.parseLong(request.getParameter("payAmount")));
            pago.setPayDescription((request.getParameter("payDescription")!= null )?request.getParameter("payDescription"):"");
            pago.setPayObservation((request.getParameter("payObservation")!= null )?request.getParameter("payObservation"):"");        
    
            String empProjAssignment[]=request.getParameterValues("item-pago-hidden");
            List<ProjAssignPaysTO> projAssTOList = new ArrayList<ProjAssignPaysTO>();
            
            if( empProjAssignment  != null){ 
                                  
               for(String aux:empProjAssignment){               
                   String atribs[]= aux.split("#");
                   ProjAssignPaysTO projAssPayTO = new ProjAssignPaysTO();
                   ProjectAssignmentsTO projAssTO = new ProjectAssignmentsTO();
                   projAssTO.setPraId(Long.parseLong(atribs[0]));
                   EmployeesTO empTO = new EmployeesTO();
                   empTO.setEmpId(Long.parseLong(atribs[1]));
                   projAssTO.setEmployeesTO(empTO);
                   ProjectsTO projTO = new ProjectsTO();        
                   projTO.setProId(Long.parseLong(atribs[2]));
                   projAssTO.setProjectsTO(projTO);
                   projAssPayTO.setProjectAssignmentsTO(projAssTO);            
                   projAssTOList.add(projAssPayTO); 
               }
                pago.setProjAssignTOList(projAssTOList);
            }
            try {             
                    twoWaysBDL.getServiceTwoWays().insertarPago(pago); 
                    request.setAttribute("mensaje","<script>alert('El registro del pago se guardó con éxito')</script>");
                
            } catch (Exception e) {
                e.printStackTrace();
            }            
            
        }
            else if ((empId == null || empId.equalsIgnoreCase("")) && (accion==null || accion.equalsIgnoreCase("cancelar"))){
            try{
                empleados =  twoWaysBDL.getServiceTwoWays().obtenerEmpleados();
                request.setAttribute("listaEmpleados",empleados);
            } catch (Exception e) {
               e.printStackTrace();
            }
        }
        request.getRequestDispatcher("pagos.jsp").forward(request,response);
        
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            response.setContentType(CONTENT_TYPE);
            doGet(request,response); 
            }
    }