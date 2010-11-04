package com.twoways.view.service;

import com.twoways.to.RatesTO;

import org.apache.log4j.Logger;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.ClientsTO;

import java.util.List;

public class ServiceTW_System {
    
    private TwoWaysBDL twoWaysBDL;
    private Logger log = Logger.getLogger(this.getClass()) ;
    
    public ServiceTW_System() {
    
       
        try {
            twoWaysBDL = new TwoWaysBDL();
           
            
        } catch (Exception e) {
            log.error(e,e);
            e.printStackTrace(); log.error(e,e);
        }
    
    }

   public List buscarClientes(String search)  {
       try {
           return twoWaysBDL.getServiceTwoWays().buscarClientes(search);
       } catch (Exception e) {
           e.printStackTrace(); log.error(e,e);
       }
       return null;
   
   }
    
   public List  obtenerClientes(){
        try {
            return twoWaysBDL.getServiceTwoWays().obtenerClientes();
        } catch (Exception e) {
            e.printStackTrace(); log.error(e,e);
        }
        return null;
    }
    
    
    public boolean deleterCliente(Long cliId){
         try {
         
             ClientsTO clienteDelete= new ClientsTO();
             clienteDelete.setCliId(cliId); 
             return twoWaysBDL.getServiceTwoWays().deleteClients(clienteDelete);
         } catch (Exception e) {
             
             e.printStackTrace(); log.error(e,e);
         }
         return false;
     }

     
    public List  obtenerTarifas(){
         try {
             return twoWaysBDL.getServiceTwoWays().obtenerTarifas();
         } catch (Exception e) {
             e.printStackTrace(); log.error(e,e);
         }
         return null;
     }     
     
     public boolean deleterTarifa(Long ratId){
          try {
          
              RatesTO tarifaDelete= new RatesTO();
              tarifaDelete.setRatId(ratId); 
              return twoWaysBDL.getServiceTwoWays().deleteRate(tarifaDelete);
          } catch (Exception e) {
              
              e.printStackTrace(); log.error(e,e);
          }
          return false;
      }

    public List buscarTarifas(String search)  {
        try {
            return twoWaysBDL.getServiceTwoWays().buscarTarifas(search);
        } catch (Exception e) {
            e.printStackTrace(); log.error(e,e);
        }
        return null;
    
    }
    
    public void setTwoWaysBDL(TwoWaysBDL twoWaysBDL) {
        this.twoWaysBDL = twoWaysBDL;
    }

    public TwoWaysBDL getTwoWaysBDL() {
        return twoWaysBDL;
    }
}
