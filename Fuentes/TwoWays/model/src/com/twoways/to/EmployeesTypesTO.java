package com.twoways.to;

public class EmployeesTypesTO {

    private Long empId;
    private String etyName;
    private EmployeeTypeTO employeeTypeTO;
    private EmployeesTO employeesTO;


    public EmployeesTypesTO() {
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


    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEtyName(String etyName) {
        this.etyName = etyName;
    }

    public String getEtyName() {
        return etyName;
    }
}
