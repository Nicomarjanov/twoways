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
    
    public List buscarClientes(String search) throws Exception {
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("buscarClientes",search);
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
    }
    
    
    public ClientsTO getClientById(String cliId)  throws Exception{
       ClientsTO cliente =  (ClientsTO)getSqlMapClientTemplate().queryForObject("getClientById",cliId);
       return cliente;
    }
    
    public boolean  deleteClients(ClientsTO client)  throws Exception{
       int res =  getSqlMapClientTemplate().delete("deleteClients",client);
       return (res > 0); 
    }
    
    
    
    public ClientsTO insertarCliente(ClientsTO clientsTO) throws Exception {
        
        Long cliId = (Long) getSqlMapClientTemplate().queryForObject("clients.seq","");
        clientsTO.setCliId(cliId); 
        getSqlMapClientTemplate().insert("insertClients",clientsTO);
        
        return getClientById(String.valueOf(cliId)); 
        
    }

    public void updateCliente(ClientsTO clientsTO) {
        
        
            getSqlMapClientTemplate().insert("updateClients",clientsTO);
        
    }
}
