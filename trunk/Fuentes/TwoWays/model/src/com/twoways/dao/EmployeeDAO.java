package com.twoways.dao;

import com.twoways.to.EmployeesTO;

import java.util.List;

public interface EmployeeDAO {
    public List obtenerEmpleados() throws Exception;
    public EmployeesTO getEmpById(String empId)  throws Exception;
    public EmployeesTO insertarEmployee(EmployeesTO employeesTO) throws Exception;
    public void updateEmpleado(EmployeesTO employeesTO) throws Exception;
    public List buscarEmpleados(String search) throws Exception;
    public boolean deleteEmployee(EmployeesTO employee)  throws Exception;    
}
