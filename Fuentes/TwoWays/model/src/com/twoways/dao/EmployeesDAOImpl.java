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
       employee.setEmployeesRatesTOList((List<EmployeesRatesTO>) getSqlMapClientTemplate().queryForList("getEmpRatesByEmpId",employee) );
       employee.setEmployeesTypesTOList((List<EmployeesTypesTO>) getSqlMapClientTemplate().queryForList("getEmpTypesByEmpId",employee) );       
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

    public boolean deleteEmployee(EmployeesTO employeesTO)  throws Exception{      
       
        List empRates = employeesTO.getEmployeesRatesTOList();
        if (empRates != null && empRates.size() > 0){
            for(Object employeeRatesTO: empRates.toArray() ){
            
                 getSqlMapClientTemplate().delete("deleteEmployeesRates",(EmployeesRatesTO)employeeRatesTO);
            }
        }
        List empTypes = employeesTO.getEmployeesTypesTOList();
        if (empTypes != null && empTypes.size() > 0){
            for(Object employeesTypesTO: empTypes.toArray() ){
            
                 getSqlMapClientTemplate().delete("deleteEmployeesTypes",(EmployeesTypesTO)employeesTypesTO);
            } 
        }
        int res =  getSqlMapClientTemplate().delete("deleteEmployee",employeesTO);
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
    
    public List obtenerTipoEmpleadoById(String empId){

        List ret = null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("obtenerTipoEmpleadoByEmpId",empId);   
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
           List<EmployeesRatesTO> oldEmpRates = (List<EmployeesRatesTO>) getSqlMapClientTemplate().queryForList("getEmpRatesByEmpId",employeesTO); 
           
           List<EmployeesTypesTO> oldEmpTypes = (List<EmployeesTypesTO>) getSqlMapClientTemplate().queryForList("getEmpTypesByEmpId",employeesTO); 
            
           getSqlMapClientTemplate().update("updateEmployees",employeesTO);
          
           List empRates = employeesTO.getEmployeesRatesTOList();
           
           List empTypes = employeesTO.getEmployeesTypesTOList();
           
          // insertar tarifas nuevas 
          
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
          
          //borrar tarifas viejas
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
           
            // insertar tipos nuevos 
            
             for(Object employeesTypesTO: empTypes.toArray() ){
                 
                EmployeesTypesTO newET = (EmployeesTypesTO)employeesTypesTO;
               boolean insertar= true;   
                
                for(Object oldEmpType: oldEmpTypes.toArray() ){
                   
                    EmployeesTypesTO oldET = (EmployeesTypesTO)oldEmpType;  
                    if( oldET.getEmployeesTO().getEmpId().equals(newET.getEmployeesTO().getEmpId()) && oldET.getEmployeeTypeTO().getEtyName().equals(newET.getEmployeeTypeTO().getEtyName())){
                        insertar=false;
                        break;
                    }
                    
                }     
                 
                if(insertar)
                {
                    getSqlMapClientTemplate().insert("insertEmployeesTypes",(EmployeesTypesTO)employeesTypesTO);
                }else{
                    getSqlMapClientTemplate().insert("updateEmployeesTypes",(EmployeesTypesTO)employeesTypesTO);
                }  
             }
            
            //borrar tipos viejos
             for(Object oldEmpType: oldEmpTypes.toArray() ){
                   EmployeesTypesTO oldET = (EmployeesTypesTO)oldEmpType;   
                   boolean borrar= true;   
                   for(Object employeesTypesTO: oldEmpTypes.toArray() ){
                   
                       EmployeesTypesTO newET = (EmployeesTypesTO)employeesTypesTO;
                       
                       if( oldET.getEmployeesTO().getEmpId().equals(newET.getEmployeesEmpId()) && oldET.getEmployeeTypeTO().getEtyName().equals(newET.getEtyName())){
                           borrar=false;
                           break;
                       }
                       
                   }     
                    
                   if(borrar)
                   {
                       getSqlMapClientTemplate().delete("deleteEmployeesTypes",(EmployeesTypesTO)oldET);
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
