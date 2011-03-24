package com.twoways.view.service;

import com.twoways.to.RatesTO;
import com.twoways.to.ClientsTO;

import java.util.ArrayList;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.AccountsTO;
import com.twoways.to.ClientsRatesTO;
import com.twoways.to.EmployeesRatesTO;
import com.twoways.to.EmployeesTO;

import com.twoways.to.ItemsTO;
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


    public boolean deleterEmpleado(Long empId) {
        try {

            EmployeesTO empleadoDelete = new EmployeesTO();
            empleadoDelete.setEmpId(empId);
            return twoWaysBDL.getServiceTwoWays().deleteEmployees(empleadoDelete);
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


    public String quitarAsignacion(String praId,String proId){
        try {
            Map params = new HashMap();
            params.put("praId", praId);
            params.put("proId", proId);
            
            twoWaysBDL.getServiceTwoWays().deleteProjectAssigment(params);
            return "";
        } catch (Exception e) {
            
            e.printStackTrace();
            log.error(e, e);
            return e.getMessage();
        }
    }
    
    
    public String enviarAsignacion(Long praId,String message,String userId,String otrosDestinatarios ){
        try {
        
            UsersTO user= getTwoWaysBDL().getServiceTwoWays().getUserById(userId);
            getTwoWaysBDL().getServiceTwoWays().enviarMailAsignacion(praId,message,user,otrosDestinatarios);
            return "El email fue marcado para ser enviado";
        } catch (Exception e) {
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
    
    public Double cotizar(String mes, String anio, Long curId, Double value){
    try {
        SimpleDateFormat formatoDeFecha = new SimpleDateFormat("d/M/y");
        Date fechaAss = formatoDeFecha.parse("01/"+mes+"/"+anio);
        System.out.println(fechaAss);
        Timestamp timestamp = new Timestamp(fechaAss.getTime());
        Double cotization = twoWaysBDL.getServiceTwoWays().getCurrencyCotizationValue(timestamp,curId,value);
        return value * cotization;
    
    } catch (Exception e) {
        e.printStackTrace();
        log.error(e, e);
    }
    return null;
    }
    
}