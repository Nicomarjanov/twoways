package com.twoways.service;

import com.twoways.to.ClientsTO;
import com.twoways.to.RateTypesTO;
import com.twoways.to.RatesTO;

import com.twoways.to.UsersTO;

import java.util.List;

public interface TW_SystemService {

    public List obtenerClientes() throws Exception;
    
    public ClientsTO insertarCliente(ClientsTO clientsTO) throws Exception ;
    
    public void updateCliente(ClientsTO clientsTO) throws Exception ;
    
    public ClientsTO getClientById(String cliId)  throws Exception;
    
    public List buscarClientes(String search) throws Exception;
    
    public boolean  deleteClients(ClientsTO client)  throws Exception;
    
    public List obtenerEmpleados() throws Exception;
    
    public List obtenerMonedas() throws Exception;
    
    public List obtenerTarifas() throws Exception;
    
    public List obtenerTipoTarifas() throws Exception;
    
    public void insertarTarifa(RatesTO ratesTO) throws Exception ;

    public void actualizarTarifa(RatesTO ratesTO) throws Exception ;    

    public RatesTO getRateById(String ratId)  throws Exception;
    
    public boolean deleteRate(RatesTO rate)  throws Exception;

    public List buscarTarifas(String search) throws Exception;
    
    public  List getRateByType(RateTypesTO rateTypesTO) throws Exception;

    public List obtenerUsuarios() throws Exception;
    
    public void insertarUsuario(UsersTO usersTO) throws Exception ;
    
    public void updateUsuario(UsersTO usersTO) throws Exception ;
    
    public UsersTO getUserById(String usrId)  throws Exception;
    
    public List buscarUsuario(String search) throws Exception;
    
    public boolean  deleteUsers(UsersTO user)  throws Exception;
    
    public List obtenerRoles() throws Exception;
    
    public UsersTO getLogin(String userId, String pass) throws Exception ;
    
}
