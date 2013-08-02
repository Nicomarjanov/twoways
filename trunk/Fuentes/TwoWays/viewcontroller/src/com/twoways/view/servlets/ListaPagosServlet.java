package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ClientsTO;
import com.twoways.to.EmployeesTO;
import com.twoways.to.ItemsInvoicesTO;
import com.twoways.to.PaymentsTO;

import java.io.IOException;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ListaPagosServlet extends AutorizacionServlet {
    private static final String CONTENT_TYPE = "text/html; charset=windows-1252";
    
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        List roles= new ArrayList();
        roles.add("Administrador");
        this.setRolesValidos(roles);
    }

    public void doGet(HttpServletRequest request, 
                      HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
        super.doGet(request,response);
        TwoWaysBDL twoWaysBDL=null;
        String accion = request.getParameter("accion");
        
        List optionList= new ArrayList();        
        optionList.add("=");
        optionList.add("<=");
        optionList.add(">=");
        List<ClientsTO> empleados = null;
        
        int page =(request.getParameter("pageId")  != null && request.getParameter("pageId").length() >0  )?Integer.parseInt(request.getParameter("pageId")): 0 ;  
        
        try{
            twoWaysBDL = new TwoWaysBDL();    
            request.setAttribute("optionList",optionList); 
            
            empleados = twoWaysBDL.getServiceTwoWays().obtenerEmpleados();
            request.setAttribute("listaEmpleados",empleados);   
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        if(accion !=null && accion.equalsIgnoreCase("buscar")){
            request.setAttribute("accion",accion);
            PaymentsTO pago = new PaymentsTO();   
            SimpleDateFormat sdfsh = new SimpleDateFormat("dd/MM/yyyy");            
            Map params= new  HashMap();          
            java.util.Date date;
            
            if (request.getParameter("payNumber") != null && request.getParameter("payNumber").length() > 0){
                params.put("payNumber",request.getParameter("payNumber")); 
                pago.setPayId(Long.parseLong(request.getParameter("payNumber")));
                request.setAttribute("payNumber",request.getParameter("payNumber")); 
            }
            EmployeesTO empleado = new EmployeesTO(); 
            if(request.getParameter("listaEmpleados") != null && request.getParameter("listaEmpleados").length() > 0  ){ 
              
              Long empId= Long.parseLong(request.getParameter("listaEmpleados"));
              empleado.setEmpId(empId);
              params.put("empId",empId);
              request.setAttribute("empId",empId); 
              
            /*}else{
              params.put("cliId",0);*/
            }
            pago.setEmployeeTO(empleado);
            
            if(request.getParameter("fecha") !=null &&( request.getParameter("fecha").length() == 10 )){ 
                 try {
                     date = sdfsh.parse(request.getParameter("fecha"));
                     java.sql.Timestamp timest = new java.sql.Timestamp(date.getTime()); 
                     pago.setPayDate(timest);
                     params.put("fecha",request.getParameter("fecha"));
                     params.put("payDateOpt",request.getParameter("payDateOpt"));
                     request.setAttribute("fecha",request.getParameter("fecha")); 
                     request.setAttribute("payDateOpt",request.getParameter("payDateOpt")); 
                 } catch (ParseException e) {
                     e.printStackTrace();
                 }
            }            
            try{
               List<PaymentsTO> ipagosList =  twoWaysBDL.getServiceTwoWays().findPayments(params);
               int  pageTop=(page+1)*10 ;
               int  minPage=(page)*10 ;
               List subpagos = null;
                
                
               if(ipagosList.size() > pageTop){ 
                 subpagos= (ipagosList.size() > pageTop )?ipagosList.subList(minPage,pageTop):ipagosList.subList(minPage,ipagosList.size());       
               }else{
                   subpagos=ipagosList;
                   pageTop =subpagos.size();
                   minPage=(pageTop/10)*10;
                   if(pageTop==minPage){
                       minPage=pageTop-10;
                   }
                   if(minPage > 0){ 
                      subpagos = (ipagosList.size() > pageTop )?ipagosList.subList(minPage,pageTop):ipagosList.subList(minPage,ipagosList.size());       
                   }else{
                       pageTop=1;
                       minPage=1; 
                       page=0;
                       
                   }
               }
               int maxPage = 0;
               if (ipagosList.size()== 10) maxPage =1;
               else maxPage =(int)(ipagosList.size() / 10) + 1;
               request.setAttribute("listaPagos",subpagos);
               request.setAttribute("maxPage",maxPage);
               request.setAttribute("page",page);          
              
               request.setAttribute("pageId",page); 
               
                }catch(Exception e){
                   e.printStackTrace(); 
                }        
            }else if (accion!=null && accion.equalsIgnoreCase("imprimir")){
                Long payId = Long.parseLong(request.getParameter("payId"));  
                
                try{
                    twoWaysBDL = new TwoWaysBDL();
                    
                    List<ItemsInvoicesTO> itemsPagoList =  twoWaysBDL.getServiceTwoWays().obtenerItemsPago(payId);
                    //ListaPagosServlet.createPdf(request,response,itemsPagoList);
                    
                }catch(Exception e){
                   e.printStackTrace(); 
                }
            }
            
        request.getRequestDispatcher("listaPagos.jsp").forward(request,response);
        }

}
