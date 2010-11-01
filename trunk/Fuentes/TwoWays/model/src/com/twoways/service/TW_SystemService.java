package com.twoways.service;

import com.twoways.to.ClientsTO;
import com.twoways.to.RatesTO;

import java.util.List;

public interface TW_SystemService {

    public List obtenerClientes() throws Exception;
    
    public List obtenerEmpleados() throws Exception;
    
    public List obtenerMonedas() throws Exception;
    
    public List obtenerTarifas() throws Exception;
    public void insertarCliente(ClientsTO clientsTO) throws Exception ;
    
    public void updateCliente(ClientsTO clientsTO) throws Exception ;
    
    public ClientsTO getClientById(String cliId)  throws Exception;
    
    public List buscarClientes(String search) throws Exception;
    
    public boolean  deleteClients(ClientsTO client)  throws Exception;
    
    public void insertarTarifa(RatesTO ratesTO) throws Exception ;

    public void actualizarTarifa(RatesTO ratesTO) throws Exception ;    



}