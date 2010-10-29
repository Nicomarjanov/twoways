package com.twoways.to;


//(name = "EMPLOYEES_RATES")
//Class(EmployeesRatesTOPK.class)
public class EmployeesRatesTO {
    //
    private Long employeesEmpId;
    private Long emrValue;
    private Long ratesRatId;
    private RatesTO ratesTO;
    private EmployeesTO employeesTO;

    public EmployeesRatesTO() {
    }

    public Long getEmployeesEmpId() {
        return employeesEmpId;
    }

    public void setEmployeesEmpId(Long employeesEmpId) {
        this.employeesEmpId = employeesEmpId;
    }

    public Long getEmrValue() {
        return emrValue;
    }

    public void setEmrValue(Long emrValue) {
        this.emrValue = emrValue;
    }

    public Long getRatesRatId() {
        return ratesRatId;
    }

    public void setRatesRatId(Long ratesRatId) {
        this.ratesRatId = ratesRatId;
    }

    public RatesTO getRatesTO() {
        return ratesTO;
    }

    public void setRatesTO(RatesTO ratesTO) {
        this.ratesTO = ratesTO;
    }

    public EmployeesTO getEmployeesTO() {
        return employeesTO;
    }

    public void setEmployeesTO(EmployeesTO employeesTO) {
        this.employeesTO = employeesTO;
    }
}
