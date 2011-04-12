package com.twoways.dao;

import com.twoways.to.ClientResponsableTO;
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
    
    public ClientsTO getClientByName(String nombre){
        
        ClientsTO cliente =  (ClientsTO)getSqlMapClientTemplate().queryForObject("getClientByName",nombre);
        return cliente;
    }
    
    public ClientsTO getClientById(String cliId)  throws Exception{
       ClientsTO cliente =  (ClientsTO)getSqlMapClientTemplate().queryForObject("getClientById",cliId);
       cliente.setClientsRatesTOList((List<ClientsRatesTO>) getSqlMapClientTemplate().queryForList("getClientRatesByCliId",cliente) );
       cliente.setClientResponsableTOList((List<ClientResponsableTO>) getSqlMapClientTemplate().queryForList("getClientResponsableByCliId",cliente) );
       return cliente;
    }
    
    
    public List<ClientsRatesTO> getTarifaClienteById(Long cliId)  throws Exception{
    
       ClientsTO cliente = new ClientsTO (); 
       cliente.setCliId(cliId);
       return  getSqlMapClientTemplate().queryForList("getClientRatesByCliId",cliente) ;
       
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
        if (cliRates.size() > 0){
            for(Object clientsRatesTO: cliRates.toArray() ){
           
                 getSqlMapClientTemplate().insert("insertClientsRates",(ClientsRatesTO)clientsRatesTO);
            }
        }
        
        List cliResponsable = clientsTO.getClientResponsableTOList();
        if (cliResponsable.size() > 0){
            
            for(Object clientsResponsableTO: cliResponsable.toArray() ){
                ClientResponsableTO auxResp=(ClientResponsableTO)clientsResponsableTO;
                Long creId = (Long) getSqlMapClientTemplate().queryForObject("client_responsable.seq","");
                auxResp.setCreId(creId);
                getSqlMapClientTemplate().insert("insertClientsResponsable",auxResp);
            }      
        }
        return getClientById(String.valueOf(cliId)); 
        
    }

    public ClientsTO updateCliente(ClientsTO clientsTO) throws Exception {
        
       try{
          List<ClientsRatesTO> oldCliRates = (List<ClientsRatesTO>) getSqlMapClientTemplate().queryForList("getClientRatesByCliId",clientsTO); 
          List<ClientResponsableTO> oldCliRespo = (List<ClientResponsableTO>) getSqlMapClientTemplate().queryForList("getClientResponsableByCliId",clientsTO); 
          
          getSqlMapClientTemplate().update("updateClients",clientsTO);
          
          List cliRates = clientsTO.getClientsRatesTOList();
          List cliRespo = clientsTO.getClientResponsableTOList();
          // insertar tarifas nuevas 
          
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
        //insertar responsables 
             Long creIdAux = null;
             for(Object clientsResponsableTO: cliRespo.toArray() ){                 
                ClientResponsableTO newCRes = (ClientResponsableTO)clientsResponsableTO;
                boolean insertar= true;                     
                for(Object oldCliResp: oldCliRespo.toArray() ){               
                     ClientResponsableTO oldCRes = (ClientResponsableTO)oldCliResp;  
                     if( oldCRes.getClientsTO().getCliId().equals(newCRes.getClientsTO().getCliId()) && oldCRes.getCreFirstName().equals(newCRes.getCreFirstName()) && oldCRes.getCreLastName().equals(newCRes.getCreLastName()) ){
                         insertar=false;
                         creIdAux = oldCRes.getCreId();
                         break;
                     }                        
                 }                           
                 if(insertar)
                 {  
                     ClientResponsableTO auxResp=(ClientResponsableTO)clientsResponsableTO;
                     Long creId = (Long) getSqlMapClientTemplate().queryForObject("client_responsable.seq","");
                     auxResp.setCreId(creId);
                     getSqlMapClientTemplate().insert("insertClientsResponsable",auxResp);
                 }else{
                     ClientResponsableTO updateResp=(ClientResponsableTO)clientsResponsableTO;
                     updateResp.setCreId(creIdAux);
                     getSqlMapClientTemplate().insert("updateClientsResponsable",updateResp);
                 }  
             }
           //borrar los viejos responsables
             for(Object oldCliResp: oldCliRespo.toArray() ){
                    ClientResponsableTO oldCResp = (ClientResponsableTO)oldCliResp;   
                    boolean borrar= true;   
                    for(Object clientsResponsableTO: cliRespo.toArray() ){
                    
                        ClientResponsableTO newCResp = (ClientResponsableTO)clientsResponsableTO;
                        
                        if( oldCResp.getClientsTO().getCliId().equals(newCResp.getClientsTO().getCliId()) && oldCResp.getCreId().equals(newCResp.getCreId())  ){
                            borrar=false;
                            break;
                        }
                        
                    }     
                     
                    if(borrar)
                    {
                        getSqlMapClientTemplate().delete("deleteClientsResponsable",(ClientResponsableTO)oldCResp);
                    }  
                }
         } catch (Exception ex)  {
           //  getSqlMapClientTemplate().getSqlMapClient().endTransaction();
             ex.printStackTrace();
             
         } finally  {
         }          
        return getClientById(String.valueOf(clientsTO.getCliId()));     
        
    }
    
    public List getClientResponsableByCliId(ClientsTO clientsTO){
        List ret= null;
        try {
            ret  = getSqlMapClientTemplate().queryForList("getClientResponsableByCliId",clientsTO); 
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
        }
        
}
