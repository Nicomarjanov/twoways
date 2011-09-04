package com.twoways.view.service;

import com.twoways.to.RatesTO;
import com.twoways.to.ClientsTO;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.AccountsTO;
import com.twoways.to.ClientResponsableTO;
import com.twoways.to.ClientsRatesTO;
import com.twoways.to.CotizationsTO;
import com.twoways.to.EmployeesRatesTO;
import com.twoways.to.EmployeesTO;

import com.twoways.to.InvoicesTO;
import com.twoways.to.ItemsTO;
import com.twoways.to.OrdersTO;
import com.twoways.to.ProAssigmentsDetailsTO;
import com.twoways.to.TranslatorsLanguaguesTO;
import com.twoways.to.TranslatorsTO;
import com.twoways.to.UsersTO;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.List;

public class ServiceTW_System {

    private TwoWaysBDL twoWaysBDL;
    private Logger log = Logger.getRootLogger();

    public ServiceTW_System() {


        try {
            twoWaysBDL = new TwoWaysBDL();


        } catch (Exception e) {
            log.error(e, e);
            e.printStackTrace();
            log.error(e, e);
        }
    }

    public List buscarClientes(String search) {
        try {
            return twoWaysBDL.getServiceTwoWays().buscarClientes(search);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e, e);
        }
        return null;

    }

    public List obtenerClientes() {
        try {
            return twoWaysBDL.getServiceTwoWays().obtenerClientes();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e, e);
        }
        return null;
    }


    public List<ClientsRatesTO> getTarifaClienteById(Long cliId) {
        try {
            return twoWaysBDL.getServiceTwoWays().getTarifaClienteById(cliId);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e, e);
        }
        return null;
    }

    public boolean deleterCliente(Long cliId) {
        try {

            ClientsTO clienteDelete = new ClientsTO();
            clienteDelete.setCliId(cliId);
            return twoWaysBDL.getServiceTwoWays().deleteClients(clienteDelete);
        } catch (Exception e) {

            e.printStackTrace();
            log.error(e, e);
        }
        return false;
    }


    public List obtenerTarifas() {
        try {
            return twoWaysBDL.getServiceTwoWays().obtenerTarifas();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e, e);
        }
        return null;
    }

    public boolean deleterTarifa(Long ratId) {
        try {

            RatesTO tarifaDelete = new RatesTO();
            tarifaDelete.setRatId(ratId);
            return twoWaysBDL.getServiceTwoWays().deleteRate(tarifaDelete);
        } catch (Exception e) {

            e.printStackTrace();
            log.error(e, e);
        }
        return false;
    }

    public List buscarTarifas(String search) {
        try {
            return twoWaysBDL.getServiceTwoWays().buscarTarifas(search);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e, e);
        }
        return null;

    }

    public List buscarUsuarios(String search) {
        try {
            return twoWaysBDL.getServiceTwoWays().buscarUsuario(search);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e, e);
        }
        return null;

    }

    public List buscarUsuariosId(String search) {
        try {
            return twoWaysBDL.getServiceTwoWays().buscarUsuarioId(search);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e, e);
        }
        return null;

    }

    public List obtenerUsers() {
        try {
            return twoWaysBDL.getServiceTwoWays().obtenerUsuarios();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e, e);
        }
        return null;
    }


    public boolean deleterUsuario(String usrId) {
        try {

            UsersTO usuarioDelete = new UsersTO();
            usuarioDelete.setUsrId(usrId);
            return twoWaysBDL.getServiceTwoWays().deleteUsers(usuarioDelete);
        } catch (Exception e) {

            e.printStackTrace();
            log.error(e, e);
        }
        return false;
    }

    public List buscarEmpleados(String search) {
        try {
            return twoWaysBDL.getServiceTwoWays().buscarEmpleados(search);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e, e);
        }
        return null;

    }

    public List obtenerEmpleados() {
        try {
            return twoWaysBDL.getServiceTwoWays().obtenerEmpleados();
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e, e);
        }
        return null;
    }


    public boolean buscarAsignacionesEmpleado(String empId) {
        try {
            return twoWaysBDL.getServiceTwoWays().buscarAsignacionesEmpleado(empId);
        } catch (Exception e) {

            e.printStackTrace();
            log.error(e, e);
        }
        return false;
    }


    public boolean deleterItem(Long itmId) {
        try {

            ItemsTO itemDelete = new ItemsTO();
            itemDelete.setItmId(itmId);
            return twoWaysBDL.getServiceTwoWays().deleteItem(itemDelete);
        } catch (Exception e) {

            e.printStackTrace();
            log.error(e, e);
        }
        return false;
    }

    public List buscarItems(String search) {
        try {
            return twoWaysBDL.getServiceTwoWays().buscarItems(search);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e, e);
        }
        return null;

    }

    public boolean deleterAccount(Long accId) {
        try {

            AccountsTO accDelete = new AccountsTO();
            accDelete.setAccId(accId);
            return twoWaysBDL.getServiceTwoWays().deleteAccount(accDelete);
        } catch (Exception e) {

            e.printStackTrace();
            log.error(e, e);
        }
        return false;
    }

    public List buscarCuentas(String search) {
        try {
            return twoWaysBDL.getServiceTwoWays().buscarAccounts(search);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e, e);
        }
        return null;
    }

    public List getEmpByRatesName(String rateName) {
        try {
            return twoWaysBDL.getServiceTwoWays().getEmpByRatesName(rateName);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e, e);
        }
        return null;

    }

    public List<TranslatorsLanguaguesTO> getTranslatorsLanguaguesTOByEmpId(String empId) {
        try {
            TranslatorsTO translatorTO = 
                twoWaysBDL.getServiceTwoWays().getTraByEmpId(empId);
            if (translatorTO != null) {

                List result = 
                    twoWaysBDL.getServiceTwoWays().getLangByEmpId(Long.parseLong(empId));
                return result;
            }
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e, e);
        }
        return null;

    }

    public Long existeAssignacion(String praDate, Long emp, String serv, 
                                  String praId) throws Exception {

        return twoWaysBDL.getServiceTwoWays().buscarAssignacion(praDate, emp, 
                                                                serv, praId);
    }
    
    
    public Long existeDisponbilidad(String praDate, Long emp) throws Exception {

        return twoWaysBDL.getServiceTwoWays().buscarAssignacion(praDate, emp);
    }


    public Long existenTarifas(Long empId, String serv) {

        List<EmployeesRatesTO> emps = new ArrayList();
        try {
            emps = twoWaysBDL.getServiceTwoWays().getEmpRatesByEmpId(empId,serv);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e, e);
        }

        return (long)emps.size();
    }

    public String quitarDetalle(String odoId, String praId) {
        try {
            Map params = new HashMap();
            params.put("praId", praId);
            params.put("odoId", odoId);
            twoWaysBDL.getServiceTwoWays().deleteProjectAssigmentDetailsByPraId(params);
            return "";
        } catch (Exception e) {
            
            e.printStackTrace();
            log.error(e, e);
            return e.getMessage();
        }
    }


    public String quitarAsignacion(String uaid, String praId,String proId){
        try {
            Map params = new HashMap();
            params.put("praId", praId);
            params.put("proId", proId);
            /* Debo averiguar si existe un pago de la asignacion(metodo nuevo). Antes tengo q preguntar si realmente quiere enviar el mail o 
             * averiguar de alguna manera si ya se le envio uno cuando se asignó
            List<ProAssigmentsDetailsTO> result = twoWaysBDL.getServiceTwoWays().getProjectAssignmentsDetailsById(Long.parseLong(praId));
            if(result.size() == 0){
                UsersTO user= getTwoWaysBDL().getServiceTwoWays().getUserById(uaid);
                getTwoWaysBDL().getServiceTwoWays().enviarMailDesasignacion(Long.parseLong(praId),user);*/
                twoWaysBDL.getServiceTwoWays().deleteProjectAssigment(params);
                return "";
            /*}else{
                return "Existe un pago realizado por éste proyecto. El registro no se puede eliminar.";
            }*/
        } catch (Exception e) {
            
            e.printStackTrace();
            log.error(e, e);
            if (e.getMessage().contains("ORA-02292")){
                return "Existe un pago realizado por éste proyecto. El registro no se puede eliminar.";
            }else
                return e.getMessage();
        }
    }
    
    
    public String enviarAsignacion(Long praId,String message,String userId,String otrosDestinatarios ){
        try {
        
            UsersTO user= getTwoWaysBDL().getServiceTwoWays().getUserById(userId);
            getTwoWaysBDL().getServiceTwoWays().enviarMailAsignacion(praId,message,user,otrosDestinatarios);
            return "El email fue marcado para ser enviado";
        }catch (NullPointerException x) {
                     x.printStackTrace();
                     log.error(x, x);
                     return "El email no pudo ser enviado por el siguiente motivo: Mail del empleado vacío. ";
        }catch (Exception e) {
             e.printStackTrace();
             log.error(e, e);
             return "El email no pudo ser enviado por el siguiente motivo "+ e.getMessage();
        }
    }


    public List buscarListaEmpleados(String search) {
        try {
            return twoWaysBDL.getServiceTwoWays().buscarListaEmpleados(search);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e, e);
        }
        return null;

    } 
    
    public void setTwoWaysBDL(TwoWaysBDL twoWaysBDL) {
        this.twoWaysBDL = twoWaysBDL;
    }

    public TwoWaysBDL getTwoWaysBDL() {
        return twoWaysBDL;
    }
    
    public Double cotizarPago(String mes, String anio, Long curIdDesde, Long curIdHasta, Double value){
    try {
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaAss = formatoDeFecha.parse("01/"+mes+"/"+anio);
        Timestamp timestamp = new Timestamp(fechaAss.getTime());
        Double cotization = twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curIdDesde, curIdHasta, value);
        return cotization;
    
    } catch (Exception e) {
        e.printStackTrace();
        log.error(e, e);
    }
    return null;
    }

    public List buscarCotizaciones(String search) {
        try {
            return twoWaysBDL.getServiceTwoWays().buscarCotizaciones(search);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e, e);
        }
        return null;

    }    
    
    public boolean deleteCotizacion(String cucId) {
        try {

            CotizationsTO cotizationTO = new CotizationsTO();
            cotizationTO.setCucId(Long.parseLong(cucId));
            return twoWaysBDL.getServiceTwoWays().eliminarCotizacion(cotizationTO);
        } catch (Exception e) {

            e.printStackTrace();
            log.error(e, e);
        }
        return false;
    }    
    
    public Double cotizar(String fecha, Long curIdDesde, Long curIdHasta, Double value){
    try {
    
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("dd/MM/yyyy");
        Date fechaAss = formatoDeFecha.parse(fecha);
        Timestamp timestamp = new Timestamp(fechaAss.getTime());
        Double cotization = twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp, curIdDesde, curIdHasta, value);
        return cotization;
    
    } catch (Exception e) {
        e.printStackTrace();
        log.error(e, e);
    }
    return null;
    }
    
    public List<ClientResponsableTO> getClientResponsableByCliId(Long cliId) {
        try {
            ClientsTO clienteTO = new ClientsTO();
            clienteTO.setCliId(cliId);
            return twoWaysBDL.getServiceTwoWays().getClientResponsableByCliId(clienteTO);
        } catch (Exception e) {
            e.printStackTrace();
            log.error(e, e);
        }
        return null;
    }
    
    public String deleteOrder(String ordId) {

        try {
            OrdersTO ordersTO = new OrdersTO();
            ordersTO.setOrdId(Long.parseLong(ordId));
            twoWaysBDL.getServiceTwoWays().eliminarOrden(ordersTO);
            twoWaysBDL.getServiceTwoWays().eliminarProyecto(ordersTO.getOrdId());
            return "ok";
            } catch (Exception e) {
                
                e.printStackTrace();
                log.error(e, e);
                if (e.getMessage().contains("ORA-02292: restricción de integridad (TWOWAYS.PROJ_ASSINGMENTS_DETAILS_FK3")){
                    return "Existen asignaciones del proyecto de la orden que se quiere eliminar. El registro no se puede eliminar hasta que no se borren todas las asignaciones.";
                }else
                    return e.getMessage();
            }
       // return "ok";
    }    
    
    public List<OrdersTO> getOrdersByOrdName(String ordName){
    
            try{
                Map params= new  HashMap();

                params.put("ordName",ordName);
                params.put("cliId","0");
                params.put("ordProjId","");
                params.put("ordenes","si");
                List<OrdersTO> orders =  twoWaysBDL.getServiceTwoWays().findOrders(params);
                return orders;
            }catch(Exception e){
               e.printStackTrace();
               return null;
            }
                
        }
        
    public String anularFactura(String invId) {
        try{
            twoWaysBDL.getServiceTwoWays().anularFactura(Long.parseLong(invId));            
            return "ok";
            } catch (Exception e) {
                
                e.printStackTrace();
                log.error(e, e);
                 return e.getMessage();
            }
    }
}