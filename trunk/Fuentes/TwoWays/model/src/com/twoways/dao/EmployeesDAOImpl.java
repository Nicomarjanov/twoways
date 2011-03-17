package com.twoways.dao;

import com.twoways.to.EmployeeTypeTO;
import com.twoways.to.EmployeesRatesTO;
import com.twoways.to.EmployeesTO;
import com.twoways.to.EmployeesTypesTO;
import com.twoways.to.OrdersTO;
import com.twoways.to.ProjectAssignmentsTO;
import com.twoways.to.ProjectsTO;
import com.twoways.to.RateTypesTO;
import com.twoways.to.RatesTO;
import com.twoways.to.StatesTO;
import com.twoways.to.TranslatorsLanguaguesTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.sql.DataSource;

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
        
        
    public List getEmpByRatesName(String rateName) throws Exception{
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("getEmpByRatesName",rateName);
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
        
        List transLanguagues = employeesTO.getTransLanguaguesTOList();
        if ( transLanguagues != null && transLanguagues.size() > 0 ){
            for(Object transLanguaguesTO: transLanguagues.toArray() ){
                 Long tlaId = (Long) getSqlMapClientTemplate().queryForObject("trans_lang.seq","");
                 TranslatorsLanguaguesTO auxTrans = (TranslatorsLanguaguesTO)transLanguaguesTO;
                 auxTrans.setTlaId(tlaId);
                 auxTrans.setEmployeesTO(employeesTO);
                 getSqlMapClientTemplate().insert("insertTransLanguagues",auxTrans);
            }
        }        
        
        return getEmpById(String.valueOf(empId)); 
        
    }
    
    public List<EmployeesRatesTO> getEmpRatesByEmpId(EmployeesTO employeesTO)throws Exception {
    
       return  (List<EmployeesRatesTO>) getSqlMapClientTemplate().queryForList("getEmpRatesByEmpId",employeesTO); 
    
    }
    
    public EmployeesTO updateEmpleado(EmployeesTO employeesTO) throws Exception {
        
       try{
                      
           List<EmployeesRatesTO> oldEmpRates = (List<EmployeesRatesTO>) getSqlMapClientTemplate().queryForList("getEmpRatesByEmpId",employeesTO); 
           
           List<EmployeesTypesTO> oldEmpTypes = (List<EmployeesTypesTO>) getSqlMapClientTemplate().queryForList("getEmpTypesByEmpId",employeesTO); 
            
           getSqlMapClientTemplate().update("updateEmployees",employeesTO);
          
           List empRates = employeesTO.getEmployeesRatesTOList();
           
           List empTypes = employeesTO.getEmployeesTypesTOList();
           
           List transLang = employeesTO.getTransLanguaguesTOList();
           
           List<TranslatorsLanguaguesTO> oldTransLang = (List<TranslatorsLanguaguesTO>) getSqlMapClientTemplate().queryForList("getOldTransLangByEmpId",employeesTO.getEmpId()); 
           
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
            //borrar tipos viejos
             for(Object oldEmpType: oldEmpTypes.toArray() ){
                   getSqlMapClientTemplate().delete("deleteEmployeesTypes",(EmployeesTypesTO)oldEmpType);
             }        
            // insertar tipos nuevos 
            
             for(Object employeesTypesTO: empTypes.toArray() ){
                 getSqlMapClientTemplate().insert("insertEmployeesTypes",(EmployeesTypesTO)employeesTypesTO);
             }
            //borrar lenguajes viejos
             //Long traId=employeesTO.getEmpId();
             for(Object oldTranLang: oldTransLang.toArray() ){
                   getSqlMapClientTemplate().delete("deleteTranslatorsLangs",(TranslatorsLanguaguesTO)oldTranLang);
             }    
            // insertar lenguajes nuevos 
            
             for(Object transLangTO: transLang.toArray() ){
                 Long tlaId = (Long) getSqlMapClientTemplate().queryForObject("trans_lang.seq","");
                 TranslatorsLanguaguesTO auxTrans = (TranslatorsLanguaguesTO)transLangTO;
                 auxTrans.setTlaId(tlaId);
                 auxTrans.setEmployeesTO(employeesTO);
                 getSqlMapClientTemplate().insert("insertTransLanguagues",auxTrans);
             }
                 
        } catch (Exception ex)  {
               //  getSqlMapClientTemplate().getSqlMapClient().endTransaction();
                 ex.printStackTrace();
                 
        } finally  {
        }
        return getEmpById(String.valueOf(employeesTO.getEmpId()));     
        
    }

    public List<EmployeesRatesTO> getEmpRatesByEmpIdRate(EmployeesTO employeesTO, 
                                                         RateTypesTO rt) {
                                                         
        EmployeesRatesTO employeesRatesTO = new EmployeesRatesTO(); 
        employeesRatesTO.setEmployeesTO(employeesTO);
        employeesRatesTO.setRatesTO(new RatesTO());
        employeesRatesTO.getRatesTO().setRateTypesTO(rt);
        return  (List<EmployeesRatesTO>) getSqlMapClientTemplate().queryForList("getEmpRatesByEmpRate",employeesRatesTO); 
    }
    
    public List getLangByEmpId(Long empId) throws Exception{
        List ret= null;
        try {
            ret = getSqlMapClientTemplate().queryForList("getTransLangByEmpId",empId);
            } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
        }

    public List buscarListaEmpleados(String search) throws Exception{
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("buscarListaEmpleados",search);
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
    }
    public List <EmployeesTO> findEmployees(Map employParameters) {
        List results = new ArrayList();
        DataSource ds = this.getDataSource(); 
        Connection con = null;
        Statement stm = null;
        ResultSet rs= null ;
        String query = "select empId,\n" + 
        "               empFirstName,\n" + 
        "               empLastName,\n" + 
        "               empMail,\n" + 
        "               empMobile,\n" + 
        "               empType,\n" + 
        "               pa.states_sta_id         as empState,\n" + 
        "               pa.pra_assign_date       as empAssDate,\n" + 
        "               pa.pra_finish_date       as empFinDate,\n" + 
        "               p.pro_name               as projName,\n" + 
        "               p.states_sta_id          as projState,\n" + 
        "               p.pro_start_date         as projStartDate\n" + 
        "        from (select e.emp_id           as empId,\n" + 
        "               e.emp_first_name         as empFirstName,\n" + 
        "               e.emp_last_name          as empLastName,\n" + 
        "               e.emp_mail               as empMail,\n" + 
        "               e.emp_mobile_number      as empMobile,\n" + 
        "               t.employee_type_ety_name as empType\n" + 
        "               from employees e, employees_types t \n" +
        "               where e.emp_id = t.employees_emp_id";
        
        if ((employParameters.get("Traductor") != null && employParameters.get("Traductor").toString().length() > 0) ||
            (employParameters.get("Editor") != null && employParameters.get("Editor").toString().length() > 0) ||
            (employParameters.get("Revisor") != null && employParameters.get("Revisor").toString().length() > 0) ||
            (employParameters.get("Maquetador") != null && employParameters.get("Maquetador").toString().length() > 0) ||
            (employParameters.get("PDTP") != null && employParameters.get("PDTP").toString().length() > 0) ||
            (employParameters.get("Proofer") != null && employParameters.get("Proofer").toString().length() > 0)){
                 query += " and t.employee_type_ety_name in (";
             if (employParameters.get("Traductor") != null && employParameters.get("Traductor").toString().length() > 0) {
                 query += "'#Traductor#'";
             }
             if (employParameters.get("Editor") != null && employParameters.get("Editor").toString().length() > 0) {
                 query += ",'#Editor#'";
             }
             if (employParameters.get("Revisor") != null && employParameters.get("Revisor").toString().length() > 0) {
                 query += ",'#Revisor#'";
             }
            if (employParameters.get("PDTP") != null && employParameters.get("PDTP").toString().length() > 0) {
                query += ",'#PDTP#'";
            }    
            if (employParameters.get("Proofer") != null && employParameters.get("Proofer").toString().length() > 0) {
                query += ",'#Proofer#'";
            }
        }                            
                            
        if(employParameters.get("empFirstName") != null && employParameters.get("empFirstName").toString().length() > 0){
            if(employParameters.get("empLastName") != null && employParameters.get("empLastName").toString().length() > 0){
                query += " and ((upper(EMP_FIRST_NAME) like '%' || upper('#empFirstName#') || '%') or (upper(EMP_LAST_NAME) like '%' || upper('#empLastName#') || '%'))";
            }else {
                query += " and (upper(EMP_FIRST_NAME) like '%' || upper('#empFirstName#') || '%')";
            }
        }else if(employParameters.get("empLastName") != null && employParameters.get("empLastName").toString().length() > 0){
                query += " and (upper(EMP_LAST_NAME) like '%' || upper('#empLastName#') || '%')";
        }
        query +=" ) q left outer join \n" + 
        "project_assignments pa \n" + 
        "on q.empid = pa.employees_emp_id \n" + 
        "and q.empType = pa.services_ser_id \n" + 
        "left outer join projects p \n" + 
        "on pa.projects_pro_id = p.pro_id";
        
        for (Iterator i = employParameters.keySet().iterator();i.hasNext();){
            String param = (String)i.next();
            query = query.replaceAll("#"+param+"#",employParameters.get(param).toString());
        }
        try {
            con = ds.getConnection();
            stm = con.createStatement();
            System.out.println(query);
            System.out.println(query);
            rs = stm.executeQuery(query);
            
            while(rs.next()){
                EmployeesTO employee = new EmployeesTO();
                employee.setEmpId(rs.getLong("empId"));
                employee.setEmpFirstName(rs.getString("empFirstName"));
                employee.setEmpLastName(rs.getString("empLastName"));
                employee.setEmpMail(rs.getString("empMail"));
                employee.setEmpMobileNumber(rs.getLong("empMobile"));
                
                EmployeeTypeTO empType = new EmployeeTypeTO();
                empType.setEtyName(rs.getString("empType"));
                employee.setEmployeeTypeTO(empType);
                
                ProjectAssignmentsTO projAss = new ProjectAssignmentsTO();
                StatesTO state = new StatesTO();
                state.setStaId(rs.getString("empState"));
                projAss.setStatesTO(state);
                if(rs.getTime("empAssDate") !=null ){ 
                       java.sql.Timestamp timest = rs.getTimestamp("empAssDate"); 
                        projAss.setPraAssignDate(timest);                                     
                }
                if(rs.getTime("empFinDate") !=null ){ 
                       java.sql.Timestamp timest = rs.getTimestamp("empFinDate"); 
                        projAss.setPraFinishDate(timest);                                     
                }   
                employee.setProjectAssignmentsTO(projAss);
                
                ProjectsTO project = new ProjectsTO();
                project.setProName(rs.getString("projName"));
                
                StatesTO proState = new StatesTO();
                proState.setStaId(rs.getString("projState"));
                project.setStatesTO(proState);
                if(rs.getTime("projStartDate") !=null ){ 
                       java.sql.Timestamp timest = rs.getTimestamp("projStartDate"); 
                        project.setProStartDate(timest);                                     
                }
                results.add(employee);    
               // results.add(project);
            }
    
        } catch (SQLException e) {
         e.printStackTrace();
        }finally{
            try {
            rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try{
            stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try{
            con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    return results;
    }
}
