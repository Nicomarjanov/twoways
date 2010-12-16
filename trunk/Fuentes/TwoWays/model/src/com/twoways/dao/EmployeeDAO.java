package com.twoways.dao;

import com.twoways.to.EmployeesTO;

import java.util.List;

public interface EmployeeDAO {
    public List obtenerEmpleados() throws Exception;
    public EmployeesTO getEmpById(String empId)  throws Exception;
    public EmployeesTO insertarEmployee(EmployeesTO employeesTO) throws Exception;
    public EmployeesTO updateEmpleado(EmployeesTO employeesTO) throws Exception;
    public List buscarEmpleados(String search) throws Exception;
    public boolean deleteEmployee(EmployeesTO employee)  throws Exception;    
    public List obtenerTipoEmpleado() throws Exception;
    public List obtenerTipoEmpleadoById(String empId) throws Exception;    
}
