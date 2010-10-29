package com.twoways.service;

import com.twoways.to.ClientsTO;

import java.util.List;

public interface TW_SystemService {

    public List obtenerClientes() throws Exception;
    
    public List obtenerEmpleados() throws Exception;
    
    public List obtenerMonedas() throws Exception;
    
    public void insertarCliente(ClientsTO clientsTO) throws Exception ;
    
    public void updateCliente(ClientsTO clientsTO) throws Exception ;
    
    public ClientsTO getClientById(String cliId)  throws Exception;

}
