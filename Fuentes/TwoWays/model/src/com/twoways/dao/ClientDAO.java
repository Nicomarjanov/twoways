package com.twoways.dao;

import com.twoways.to.ClientsTO;

import java.util.List;

public interface ClientDAO {
    public List obtenerClientes() throws Exception;
    public ClientsTO getClientById(String cliId)  throws Exception;
    public ClientsTO insertarCliente(ClientsTO clientsTO) throws Exception;
    public void updateCliente(ClientsTO clientsTO) throws Exception;
    public List buscarClientes(String search) throws Exception;
    public boolean  deleteClients(ClientsTO client)  throws Exception;
}
