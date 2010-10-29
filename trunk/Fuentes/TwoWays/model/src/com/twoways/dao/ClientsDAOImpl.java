package com.twoways.dao;

import com.twoways.to.ClientsTO;

import java.util.List;

import org.springframework.dao.DataAccessException;

public class ClientsDAOImpl extends AbstractDAO  implements ClientDAO{
   
   
    public List obtenerClientes() throws Exception {
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("obtenerClientes","");
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
    }
    
    public ClientsTO getClientById(String cliId)  throws Exception{
       ClientsTO cliente =  (ClientsTO)getSqlMapClientTemplate().queryForObject("getClientById",cliId);
       return cliente;
    }
    
    
    public void insertarCliente(ClientsTO clientsTO) throws Exception {
        
        
            getSqlMapClientTemplate().insert("insertClients",clientsTO);
        
        
    }

    public void updateCliente(ClientsTO clientsTO) {
        
            getSqlMapClientTemplate().insert("updateClients",clientsTO);
        
    }
}
