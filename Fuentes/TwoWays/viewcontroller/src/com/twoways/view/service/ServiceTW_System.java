package com.twoways.view.service;

import com.twoways.to.RatesTO;
import com.twoways.to.ClientsTO;

import org.apache.log4j.Logger;
import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.to.EmployeesTO;

import com.twoways.to.ItemsTO;
import com.twoways.to.UsersTO;

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

    public List buscarUsuarios(String search)  {
        try {
            return twoWaysBDL.getServiceTwoWays().buscarUsuario(search);
        } catch (Exception e) {
            e.printStackTrace(); log.error(e,e);
        }
        return null;
    
    }
    
    public List buscarUsuariosId(String search)  {
        try {
            return twoWaysBDL.getServiceTwoWays().buscarUsuarioId(search);
        } catch (Exception e) {
            e.printStackTrace(); log.error(e,e);
        }
        return null;
    
    }
     
    public List  obtenerUsers(){
         try {
             return twoWaysBDL.getServiceTwoWays().obtenerUsuarios();
         } catch (Exception e) {
             e.printStackTrace(); log.error(e,e);
         }
         return null;
     }
     
     
     public boolean deleterUsuario(String usrId){
          try {
          
              UsersTO usuarioDelete= new UsersTO();
              usuarioDelete.setUsrId(usrId); 
              return twoWaysBDL.getServiceTwoWays().deleteUsers(usuarioDelete);
          } catch (Exception e) {
              
              e.printStackTrace(); log.error(e,e);
          }
          return false;
      }

    public List buscarEmpleados(String search)  {
        try {
            return twoWaysBDL.getServiceTwoWays().buscarEmpleados(search);
        } catch (Exception e) {
            e.printStackTrace(); log.error(e,e);
        }
        return null;
    
    }
     
    public List  obtenerEmpleados(){
         try {
             return twoWaysBDL.getServiceTwoWays().obtenerEmpleados();
         } catch (Exception e) {
             e.printStackTrace(); log.error(e,e);
         }
         return null;
     }
     
     
     public boolean deleterEmpleado(Long empId){
          try {
          
              EmployeesTO empleadoDelete= new EmployeesTO();
              empleadoDelete.setEmpId(empId); 
              return twoWaysBDL.getServiceTwoWays().deleteEmployees(empleadoDelete);
          } catch (Exception e) {
              
              e.printStackTrace(); log.error(e,e);
          }
          return false;
      }


    public boolean deleterItem(Long itmId){
         try {
         
             ItemsTO itemDelete= new ItemsTO();
             itemDelete.setItemId(itmId); 
             return twoWaysBDL.getServiceTwoWays().deleteItem(itemDelete);
         } catch (Exception e) {
             
             e.printStackTrace(); log.error(e,e);
         }
         return false;
     }

    public List buscarItems(String search)  {
       try {
           return twoWaysBDL.getServiceTwoWays().buscarItems(search);
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
