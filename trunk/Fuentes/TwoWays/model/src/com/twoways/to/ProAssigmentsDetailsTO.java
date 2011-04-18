package com.twoways.to;

import java.sql.Timestamp;

public class ProAssigmentsDetailsTO {
    
    private Long padId; 
    private OrdersDocsTO ordersDocsTO;
    private Double padWCount; 
    private EmployeesRatesTO employeesRatesTO; 
    private TranslatorsLanguaguesTO pranslatorsLanguaguesTO;
    private Double padRate; 
    private ProjectsRatesTO projectsRatesTO;
    private ProjectAssignmentsTO projectAssignmentsTO;
    private Timestamp padPayDate;
    
    
    public ProAssigmentsDetailsTO() {
    
    
    }


    public void setOrdersDocsTO(OrdersDocsTO ordersDocsTO) {
        this.ordersDocsTO = ordersDocsTO;
    }

    public OrdersDocsTO getOrdersDocsTO() {
        return ordersDocsTO;
    }

   

    public void setEmployeesRatesTO(EmployeesRatesTO employeesRatesTO) {
        this.employeesRatesTO = employeesRatesTO;
    }

    public EmployeesRatesTO getEmployeesRatesTO() {
        return employeesRatesTO;
    }

    public void setPranslatorsLanguaguesTO(TranslatorsLanguaguesTO pranslatorsLanguaguesTO) {
        this.pranslatorsLanguaguesTO = pranslatorsLanguaguesTO;
    }

    public TranslatorsLanguaguesTO getPranslatorsLanguaguesTO() {
        return pranslatorsLanguaguesTO;
    }

    

  

    public void setPadId(Long padId) {
        this.padId = padId;
    }

    public Long getPadId() {
        return padId;
    }

    public void setProjectsRatesTO(ProjectsRatesTO projectsRatesTO) {
        this.projectsRatesTO = projectsRatesTO;
    }

    public ProjectsRatesTO getProjectsRatesTO() {
        return projectsRatesTO;
    }

    public void setProjectAssignmentsTO(ProjectAssignmentsTO projectAssignmentsTO) {
        this.projectAssignmentsTO = projectAssignmentsTO;
    }

    public ProjectAssignmentsTO getProjectAssignmentsTO() {
        return projectAssignmentsTO;
    }

    public void setPadRate(Double padRate) {
        this.padRate = padRate;
    }

    public Double getPadRate() {
        return padRate;
    }

    public void setPadWCount(Double padWCount) {
        this.padWCount = padWCount;
    }

    public Double getPadWCount() {
        return padWCount;
    }

    public void setPadPayDate(Timestamp padPayDate) {
        this.padPayDate = padPayDate;
    }

    public Timestamp getPadPayDate() {
        return padPayDate;
    }
}
