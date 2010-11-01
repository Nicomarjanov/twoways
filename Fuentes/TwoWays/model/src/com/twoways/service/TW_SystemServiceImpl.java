package com.twoways.service;

import com.twoways.dao.ClientDAO;
import com.twoways.dao.CurrencyDAO;
import com.twoways.dao.EmployeeDAO;
import com.twoways.dao.RateDAO;

import com.twoways.to.ClientsTO;
import com.twoways.to.CurrencyTO;
import com.twoways.to.RatesTO;



import java.util.Collections;
import java.util.List;

public class TW_SystemServiceImpl implements TW_SystemService{
    
    private ClientDAO clientDao;
    private EmployeeDAO employeeDao;
    private CurrencyDAO currencyDao;
    private RateDAO rateDao;
     
    public TW_SystemServiceImpl() {
    }

    public void setClientDao(ClientDAO clientDao) {
        this.clientDao = clientDao;
    }

    public ClientDAO getClientDao() {
        return clientDao;
    }
    
    public List obtenerClientes() throws Exception
    {
    return this.clientDao.obtenerClientes();
    }

    public void setEmployeeDao(EmployeeDAO employeeDao){
        this.employeeDao = employeeDao;
    }
    
    public EmployeeDAO getEmployeeDao(){
        return employeeDao;
    }

    public List obtenerEmpleados() throws Exception {
        return this.employeeDao.obtenerEmpleados();
    }
    
    public void setCurrencyDao(CurrencyDAO currencyDao){
        this.currencyDao = currencyDao;
    }
    
    public CurrencyDAO getCurrencyDao(){
        return currencyDao;
    }

    public List obtenerMonedas() throws Exception {
        return this.currencyDao.obtenerMonedas();
    }
    
    public void insertarCliente(ClientsTO clientsTO) throws Exception {
        this.clientDao.insertarCliente(clientsTO);
    }

    public void updateCliente(ClientsTO clientsTO) throws Exception {
        this.clientDao.updateCliente(clientsTO);
    }
    
    public ClientsTO getClientById(String cliId)  throws Exception{
        return  this.clientDao.getClientById(cliId);
    }


    public List buscarClientes(String search) throws Exception {
        return this.clientDao.buscarClientes(search);
    }
    
    public boolean  deleteClients(ClientsTO client)  throws Exception{
        return this.clientDao.deleteClients(client);
    }


    public List obtenerTarifas() throws Exception{
        return this.rateDao.obtenerTarifas();
    }

    public void insertarTarifa(RatesTO ratesTO) throws Exception{
        this.rateDao.insertarTarifa(ratesTO);
    }
    
    public void actualizarTarifa(RatesTO ratesTO) throws Exception {
        this.rateDao.actualizarTarifa(ratesTO);
    }

    public void setRateDao(RateDAO rateDao) {
        this.rateDao = rateDao;
    }

    public RateDAO getRateDao() {
        return rateDao;
    }
    
    public CurrencyTO getCurrencyById(String curId)  throws Exception{
        
        return this.currencyDao.getCurrencyById(curId);
        
    }
    
    
}
