package com.twoways.dao;

import com.twoways.to.ClientsRatesTO;
import com.twoways.to.ClientsTO;

import java.sql.SQLException;

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
       cliente.setClientsRatesTOList((List<ClientsRatesTO>) getSqlMapClientTemplate().queryForList("getClientRatesByCliId",cliente) );
       return cliente;
    }
    
    public boolean  deleteClients(ClientsTO client)  throws Exception{
       
        client= getClientById(String.valueOf(client.getCliId()));  
        
        for(Object cli : client.getClientsRatesTOList().toArray()){
            getSqlMapClientTemplate().delete("deleteClientsRates",(ClientsRatesTO)cli); 
        }
       
       int res =  getSqlMapClientTemplate().delete("deleteClients",client);
       
       return (res > 0); 
    }
    
    
    
    public ClientsTO insertarCliente(ClientsTO clientsTO) throws Exception {
        
        Long cliId = (Long) getSqlMapClientTemplate().queryForObject("clients.seq","");
        clientsTO.setCliId(cliId); 
       
       
        getSqlMapClientTemplate().insert("insertClients",clientsTO);
        
        List cliRates = clientsTO.getClientsRatesTOList();
        
        for(Object clientsRatesTO: cliRates.toArray() ){
       
             getSqlMapClientTemplate().insert("insertClientsRates",(ClientsRatesTO)clientsRatesTO);
        }
      
        
        return getClientById(String.valueOf(cliId)); 
        
    }

    public ClientsTO updateCliente(ClientsTO clientsTO) throws Exception {
        
       try{
       /* getSqlMapClientTemplate().getSqlMapClient().startTransaction();
        getSqlMapClientTemplate().getSqlMapClient().startBatch(); */
           List<ClientsRatesTO> oldCliRates = (List<ClientsRatesTO>) getSqlMapClientTemplate().queryForList("getClientRatesByCliId",clientsTO); 
             
          getSqlMapClientTemplate().update("updateClients",clientsTO);
          
          List cliRates = clientsTO.getClientsRatesTOList();
          
          // insertar las nuevas 
          
          for(Object clientsRatesTO: cliRates.toArray() ){
               
              ClientsRatesTO newCR = (ClientsRatesTO)clientsRatesTO;
             boolean insertar= true;   
              
              for(Object oldCliRate: oldCliRates.toArray() ){
                 
                  ClientsRatesTO oldCR = (ClientsRatesTO)oldCliRate;  
                  if( oldCR.getClientsTO().getCliId().equals(newCR.getClientsTO().getCliId()) && oldCR.getRatesTO().getRatId().equals(newCR.getRatesTO().getRatId())  ){
                      insertar=false;
                      break;
                  }
                  
              }     
               
              if(insertar)
              {
                  getSqlMapClientTemplate().insert("insertClientsRates",(ClientsRatesTO)clientsRatesTO);
              }else{
                  getSqlMapClientTemplate().insert("updateClientsRates",(ClientsRatesTO)clientsRatesTO);
              }  
          }
          
          //borrar las viejas
          for(Object oldCliRate: oldCliRates.toArray() ){
                 ClientsRatesTO oldCR = (ClientsRatesTO)oldCliRate;   
                 boolean borrar= true;   
                 for(Object clientsRatesTO: cliRates.toArray() ){
                 
                     ClientsRatesTO newCR = (ClientsRatesTO)clientsRatesTO;
                     
                     if( oldCR.getClientsTO().getCliId().equals(newCR.getClientsTO().getCliId()) && oldCR.getRatesTO().getRatId().equals(newCR.getRatesTO().getRatId())  ){
                         borrar=false;
                         break;
                     }
                     
                 }     
                  
                 if(borrar)
                 {
                     getSqlMapClientTemplate().delete("deleteClientsRates",(ClientsRatesTO)oldCR);
                 }  
             }
             
            
                /* getSqlMapClientTemplate().getSqlMapClient().executeBatch();
                 getSqlMapClientTemplate().getSqlMapClient().commitTransaction();
                 */
             } catch (Exception ex)  {
               //  getSqlMapClientTemplate().getSqlMapClient().endTransaction();
                 ex.printStackTrace();
                 
             } finally  {
             }
             
          
          //borrar las viejas 
           
        return getClientById(String.valueOf(clientsTO.getCliId()));     
        
    }
}
