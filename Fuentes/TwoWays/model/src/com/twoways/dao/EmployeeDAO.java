package com.twoways.dao;

import com.twoways.to.EmployeesRatesTO;
import com.twoways.to.EmployeesTO;

import com.twoways.to.OrdersTO;
import com.twoways.to.RateTypesTO;

import java.util.List;
import java.util.Map;

public interface EmployeeDAO {
    public List obtenerEmpleados() throws Exception;
    public EmployeesTO getEmpById(String empId)  throws Exception;
    public EmployeesTO insertarEmployee(EmployeesTO employeesTO) throws Exception;
    public EmployeesTO updateEmpleado(EmployeesTO employeesTO) throws Exception;
    public List buscarEmpleados(String search) throws Exception;
    public List getEmpByRatesName(String rateName) throws Exception;
    public boolean deleteEmployee(EmployeesTO employee)  throws Exception;    
    public List obtenerTipoEmpleado() throws Exception;
    public List obtenerTipoEmpleadoById(String empId) throws Exception;    
    public List<EmployeesRatesTO> getEmpRatesByEmpId(EmployeesTO employeesTO)throws Exception;

    public List<EmployeesRatesTO> getEmpRatesByEmpIdRate(EmployeesTO employeesTO, 
                                                         RateTypesTO rt)throws Exception;
    
    public List getLangByEmpId(Long empId) throws Exception;
    public List buscarListaEmpleados(String search) throws Exception;
    public List <EmployeesTO> findEmployees(Map employParameters) throws Exception;
}
