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
    
    public EmployeesTO updateEmpleado(EmployeesTO employeesTO) throws Exception {
        
       try{
           List<EmployeesRatesTO> oldEmpRates = (List<EmployeesRatesTO>) getSqlMapClientTemplate().queryForList("getEmployeeRatesByCliId",employeesTO); 
             
           getSqlMapClientTemplate().update("updateEmployees",employeesTO);
          
           List empRates = employeesTO.getEmployeesRatesTOList();
           
          // insertar las nuevas 
          
           for(Object employeesRatesTO: empRates.toArray() ){
               
              EmployeesRatesTO newER = (EmployeesRatesTO)employeesRatesTO;
             boolean insertar= true;   
              
              for(Object oldEmpRate: oldEmpRates.toArray() ){
                 
                  EmployeesRatesTO oldER = (EmployeesRatesTO)oldEmpRate;  
                  if( oldER.getEmployeesTO().getEmpId().equals(newER.getEmployeesTO().getEmpId()) && oldER.getRatesTO().getRatId().equals(newER.getRatesTO().getRatId())  ){
                      insertar=false;
                      break;
                  }
                  
              }     
               
              if(insertar)
              {
                  getSqlMapClientTemplate().insert("insertEmployeesRates",(EmployeesRatesTO)employeesRatesTO);
              }else{
                  getSqlMapClientTemplate().insert("updateEmployeesRates",(EmployeesRatesTO)employeesRatesTO);
              }  
           }
          
          //borrar las viejas
           for(Object oldEmpRate: oldEmpRates.toArray() ){
                 EmployeesRatesTO oldER = (EmployeesRatesTO)oldEmpRate;   
                 boolean borrar= true;   
                 for(Object employeesRatesTO: empRates.toArray() ){
                 
                     EmployeesRatesTO newER = (EmployeesRatesTO)employeesRatesTO;
                     
                     if( oldER.getEmployeesTO().getEmpId().equals(newER.getEmployeesTO().getEmpId()) && oldER.getRatesTO().getRatId().equals(newER.getRatesTO().getRatId())  ){
                         borrar=false;
                         break;
                     }
                     
                 }     
                  
                 if(borrar)
                 {
                     getSqlMapClientTemplate().delete("deleteEmployeesRates",(EmployeesRatesTO)oldER);
                 }  
             }                                    
                 
        } catch (Exception ex)  {
               //  getSqlMapClientTemplate().getSqlMapClient().endTransaction();
                 ex.printStackTrace();
                 
        } finally  {
        }
        //borrar las viejas 
           
        return getEmpById(String.valueOf(employeesTO.getEmpId()));     
        
    }
}
