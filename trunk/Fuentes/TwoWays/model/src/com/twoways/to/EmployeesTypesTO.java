package com.twoways.to;

import java.util.List;

public class EmployeesTypesTO {

    private Long employeesEmpId;
    private String etyName;
    private EmployeeTypeTO employeeTypeTO;
    private EmployeesTO employeesTO;


    public EmployeesTypesTO() {
    }

    public Long getEmployeesEmpId() {
        return employeesEmpId;
    }

    public void setEmployeesEmpId(Long employeesEmpId) {
        this.employeesEmpId = employeesEmpId;
    }

    public String getEtyName() {
        return etyName;
    }

    public void setEtyName(String etyName) {
        this.etyName = etyName;
    }

    public EmployeeTypeTO getEmployeeTypeTO() {
        return employeeTypeTO;
    }

    public void setEmployeeTypeTO(EmployeeTypeTO employeeTypeTO) {
        this.employeeTypeTO = employeeTypeTO;
    }

    public EmployeesTO getEmployeesTO() {
        return employeesTO;
    }

    public void setEmployeesTO(EmployeesTO employeesTO) {
        this.employeesTO = employeesTO;
    }
    

}
