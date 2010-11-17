package com.twoways.view.servlets;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ClientsTO;
import com.twoways.to.CurrencyTO;
import com.twoways.to.RateTypesTO;
import com.twoways.to.RatesTO;

import com.twoways.to.ServicesTO;

import java.io.IOException;
import java.io.PrintWriter;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.*;
import javax.servlet.http.*;

public class OrdenTrabajoServlet extends AutorizacionServlet {
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
        
        response.setContentType(CONTENT_TYPE);
        String accion=request.getParameter("accion");
        List<ClientsTO> clientes = null;
        List<ServicesTO> services = null;
       /* ClientsTO cliente = new Clients
        * TO(); 
        String cliId = request.getParameter("cliId"); 
*/
        List<RatesTO> tarifas = null;
        
        TwoWaysBDL twoWaysBDL=null;
        
        try {
           twoWaysBDL = new TwoWaysBDL();
           RateTypesTO rateType = new RateTypesTO();
           rateType.setRtyName("Cliente");
           tarifas =  twoWaysBDL.getServiceTwoWays().getRateByType(rateType);
           clientes= twoWaysBDL.getServiceTwoWays().obtenerClientes();
           services= twoWaysBDL.getServiceTwoWays().obtenerServicios();
           request.setAttribute("listaTarifa",tarifas);
           request.setAttribute("listaCliente",clientes);
            request.setAttribute("listaService",services);
           
           
        } catch (Exception e) {
           e.printStackTrace();
        }
        request.getRequestDispatcher("ordentrabajo.jsp").forward(request,response);
    }

    public void doPost(HttpServletRequest request, 
                       HttpServletResponse response) throws ServletException, IOException {response.setContentType(CONTENT_TYPE);
       
       doGet(request,response);
    }
}
