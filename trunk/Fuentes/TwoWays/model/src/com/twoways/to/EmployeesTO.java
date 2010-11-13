package com.twoways.to;

import java.sql.Timestamp;

import java.util.List;



//(name = "EMPLOYEES")
public class EmployeesTO {
    //(name="EMP_ADDRESS")
    private String empAddress;
    //(name="EMP_AVAILABILITY")
    private String empAvailability;
    //(name="EMP_BIRTH")
    private Timestamp empBirth;
    //(name="EMP_FIRST_NAME", nullable = false)
    private String empFirstName;
    //
    //(name="EMP_ID", nullable = false)
    private Long empId;
    //(name="EMP_LAST_NAME")
    private String empLastName;
    //(name="EMP_LOCATION")
    private String empLocation;
    //(name="EMP_MAIL")
    private String empMail;
    //(name="EMP_MOBILE_NUMBER")
    private Long empMobileNumber;
    //(name="EMP_MSN")
    private String empMsn;
    //(name="EMP_OBSERVATIONS")
    private String empObservations;
    //(name="EMP_PHONE_NUMBER")
    private Long empPhoneNumber;
    private List<TranslatorsTO> translatorsTOList;
    private List<ExpensesTO> expensesTOList;
    private List<ProjectAssignmentsTO> projectAssignmentsTOList;
    private List<EmployeesRatesTO> employeesRatesTOList;
    private List<EmployeesTypesTO> employeesTypesTOList;

    private RatesTO ratesTO;
   
    public EmployeesTO() {
    }

    public String getEmpAddress() {
        return empAddress;
    }

    public void setEmpAddress(String empAddress) {
        this.empAddress = empAddress;
    }

    public String getEmpAvailability() {
        return empAvailability;
    }

    public void setEmpAvailability(String empAvailability) {
        this.empAvailability = empAvailability;
    }

    public Timestamp getEmpBirth() {
        return empBirth;
    }

    public void setEmpBirth(Timestamp empBirth) {
        this.empBirth = empBirth;
    }

    public String getEmpFirstName() {
        return empFirstName;
    }

    public void setEmpFirstName(String empFirstName) {
        this.empFirstName = empFirstName;
    }

    public Long getEmpId() {
        return empId;
    }

    public void setEmpId(Long empId) {
        this.empId = empId;
    }

    public String getEmpLastName() {
        return empLastName;
    }

    public void setEmpLastName(String empLastName) {
        this.empLastName = empLastName;
    }

    public String getEmpLocation() {
        return empLocation;
    }

    public void setEmpLocation(String empLocation) {
        this.empLocation = empLocation;
    }

    public String getEmpMail() {
        return empMail;
    }

    public void setEmpMail(String empMail) {
        this.empMail = empMail;
    }

    public Long getEmpMobileNumber() {
        return empMobileNumber;
    }

    public void setEmpMobileNumber(Long empMobileNumber) {
        this.empMobileNumber = empMobileNumber;
    }

    public String getEmpMsn() {
        return empMsn;
    }

    public void setEmpMsn(String empMsn) {
        this.empMsn = empMsn;
    }

    public String getEmpObservations() {
        return empObservations;
    }

    public void setEmpObservations(String empObservations) {
        this.empObservations = empObservations;
    }

    public Long getEmpPhoneNumber() {
        return empPhoneNumber;
    }

    public void setEmpPhoneNumber(Long empPhoneNumber) {
        this.empPhoneNumber = empPhoneNumber;
    }

    public List<TranslatorsTO> getTranslatorsTOList() {
        return translatorsTOList;
    }

    public void setTranslatorsTOList(List<TranslatorsTO> translatorsTOList) {
        this.translatorsTOList = translatorsTOList;
    }

    public TranslatorsTO addTranslatorsTO(TranslatorsTO translatorsTO) {
        getTranslatorsTOList().add(translatorsTO);
        translatorsTO.setEmployeesTO(this);
        return translatorsTO;
    }

    public TranslatorsTO removeTranslatorsTO(TranslatorsTO translatorsTO) {
        getTranslatorsTOList().remove(translatorsTO);
        translatorsTO.setEmployeesTO(null);
        return translatorsTO;
    }

    public List<ExpensesTO> getExpensesTOList() {
        return expensesTOList;
    }

    public void setExpensesTOList(List<ExpensesTO> expensesTOList) {
        this.expensesTOList = expensesTOList;
    }

    public ExpensesTO addExpensesTO(ExpensesTO expensesTO) {
        getExpensesTOList().add(expensesTO);
        expensesTO.setEmployeesTO(this);
        return expensesTO;
    }

    public ExpensesTO removeExpensesTO(ExpensesTO expensesTO) {
        getExpensesTOList().remove(expensesTO);
        expensesTO.setEmployeesTO(null);
        return expensesTO;
    }

    public List<ProjectAssignmentsTO> getProjectAssignmentsTOList() {
        return projectAssignmentsTOList;
    }

    public void setProjectAssignmentsTOList(List<ProjectAssignmentsTO> projectAssignmentsTOList) {
        this.projectAssignmentsTOList = projectAssignmentsTOList;
    }

    public ProjectAssignmentsTO addProjectAssignmentsTO(ProjectAssignmentsTO projectAssignmentsTO) {
        getProjectAssignmentsTOList().add(projectAssignmentsTO);
        projectAssignmentsTO.setEmployeesTO(this);
        return projectAssignmentsTO;
    }

    public ProjectAssignmentsTO removeProjectAssignmentsTO(ProjectAssignmentsTO projectAssignmentsTO) {
        getProjectAssignmentsTOList().remove(projectAssignmentsTO);
        projectAssignmentsTO.setEmployeesTO(null);
        return projectAssignmentsTO;
    }

    public List<EmployeesRatesTO> getEmployeesRatesTOList() {
        return employeesRatesTOList;
    }

    public void setEmployeesRatesTOList(List<EmployeesRatesTO> employeesRatesTOList) {
        this.employeesRatesTOList = employeesRatesTOList;
    }

    public EmployeesRatesTO addEmployeesRatesTO(EmployeesRatesTO employeesRatesTO) {
        getEmployeesRatesTOList().add(employeesRatesTO);
        employeesRatesTO.setEmployeesTO(this);
        return employeesRatesTO;
    }

    public EmployeesRatesTO removeEmployeesRatesTO(EmployeesRatesTO employeesRatesTO) {
        getEmployeesRatesTOList().remove(employeesRatesTO);
        employeesRatesTO.setEmployeesTO(null);
        return employeesRatesTO;
    }
    
    public List<EmployeesTypesTO> getEmployeesTypesTOList() {
        return employeesTypesTOList;
    }
    
    public void setEmployeesTypesTOList(List<EmployeesTypesTO> employeesTypesTOList) {
        this.employeesTypesTOList = employeesTypesTOList;
    }

    public EmployeesTypesTO addEmployeesTypesTO(EmployeesTypesTO employeesTypesTO) {
        getEmployeesTypesTOList().add(employeesTypesTO);
        employeesTypesTO.setEmployeesTO(this);
        return employeesTypesTO;
    }

    public EmployeesTypesTO removeEmployeesTypesTO(EmployeesTypesTO employeesTypesTO) {
        getEmployeesTypesTOList().remove(employeesTypesTO);
        employeesTypesTO.setEmployeesTO(null);
        return employeesTypesTO;
    }    
}
