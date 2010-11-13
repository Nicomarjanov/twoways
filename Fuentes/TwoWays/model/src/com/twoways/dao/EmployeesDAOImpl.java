package com.twoways.dao;

import com.twoways.to.ClientsRatesTO;
import com.twoways.to.ClientsTO;
import com.twoways.to.EmployeesTO;
import com.twoways.to.EmployeeTypeTO;

import com.twoways.to.EmployeesRatesTO;

import com.twoways.to.EmployeesTypesTO;

import java.util.List;

import org.springframework.dao.DataAccessException;

public class EmployeesDAOImpl  extends AbstractDAO  implements EmployeeDAO{

    public List obtenerEmpleados() {    
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("obtenerEmpleados","");
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;        
    }
    
    public EmployeesTO getEmpById(String empId)  throws Exception{
       EmployeesTO employee =  (EmployeesTO)getSqlMapClientTemplate().queryForObject("getEmpById",empId);
       return employee;
    }

    public void updateEmpleado(EmployeesTO employeesTO) {
        getSqlMapClientTemplate().insert("updateEmployee",employeesTO);
    }

    public List buscarEmpleados(String search) throws Exception{
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("buscarEmpleados",search);
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
        }

    public boolean deleteEmployee(EmployeesTO employee)  throws Exception{
       int res =  getSqlMapClientTemplate().delete("deleteEmployee",employee);
       return (res > 0); 
    }
    
    public List obtenerTipoEmpleado(){
        List ret = null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("obtenerTipoEmpleado","");
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;       
    }
    
    public EmployeesTO insertarEmployee(EmployeesTO employeesTO) throws Exception{
        
        Long empId = (Long) getSqlMapClientTemplate().queryForObject("employees.seq","");
        employeesTO.setEmpId(empId); 
       
        getSqlMapClientTemplate().insert("insertEmployee",employeesTO);
        
        List empRates = employeesTO.getEmployeesRatesTOList();
        
        for(Object employeeRatesTO: empRates.toArray() ){
       
             getSqlMapClientTemplate().insert("insertEmployeesRates",(EmployeesRatesTO)employeeRatesTO);
        }
        List empTypes = employeesTO.getEmployeesTypesTOList();
        
        for(Object employeesTypesTO: empTypes.toArray() ){
        
             getSqlMapClientTemplate().insert("insertEmployeesTypes",(EmployeesTypesTO)employeesTypesTO);
        }      
        
        return getEmpById(String.valueOf(empId)); 
        
    }

  /*  public ClientsTO updateCliente(ClientsTO clientsTO) throws Exception {
        
       try{
       /* getSqlMapClientTemplate().getSqlMapClient().startTransaction();
        getSqlMapClientTemplate().getSqlMapClient().startBatch(); 
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
                 
             } catch (Exception ex)  {
               //  getSqlMapClientTemplate().getSqlMapClient().endTransaction();
                 ex.printStackTrace();
                 
             } finally  {
             }
             
          
          //borrar las viejas 
           
        return getClientById(String.valueOf(clientsTO.getCliId()));     
        
    }*/
}
