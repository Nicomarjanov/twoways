package com.twoways.dao;

import com.twoways.to.ClientsTO;
import com.twoways.to.EmployeesTO;
import com.twoways.to.UsersTO;

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

    public EmployeesTO insertarEmployee(EmployeesTO employeesTO) throws Exception {
        
        Long empId = (Long) getSqlMapClientTemplate().queryForObject("employees.seq","");
        employeesTO.setEmpId(empId); 
        getSqlMapClientTemplate().insert("insertEmployee",employeesTO);
        
        return getEmpById(String.valueOf(empId)); 
        
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
    
}
