package com.twoways.to;

import java.sql.Timestamp;

import java.util.List;


//(name = "PROJECT_ASSIGNMENTS")
//Class(ProjectAssignmentsTOPK.class)
public class ProjectAssignmentsTO {
    
    private Timestamp praAssignDate;
    private Timestamp praFinishDate;
    private Long praId;
    private EmployeesTO employeesTO;
    private ProjectsTO projectsTO;
    private RateTypesTO serviceTO;
    private Long praTotalAmount;
    private List <ProAssigmentsDetailsTO> proAssigmentsDetailsTO;

    public ProjectAssignmentsTO(){
        
    }

    public void setPraAssignDate(Timestamp praAssignDate) {
        this.praAssignDate = praAssignDate;
    }

    public Timestamp getPraAssignDate() {
        return praAssignDate;
    }

    public void setPraFinishDate(Timestamp praFinishDate) {
        this.praFinishDate = praFinishDate;
    }

    public Timestamp getPraFinishDate() {
        return praFinishDate;
    }

    public void setPraId(Long praId) {
        this.praId = praId;
    }

    public Long getPraId() {
        return praId;
    }

   
    public void setEmployeesTO(EmployeesTO employeesTO) {
        this.employeesTO = employeesTO;
    }

    public EmployeesTO getEmployeesTO() {
        return employeesTO;
    }

    public void setProjectsTO(ProjectsTO projectsTO) {
        this.projectsTO = projectsTO;
    }

    public ProjectsTO getProjectsTO() {
        return projectsTO;
    }

  

    public void setServiceTO(RateTypesTO serviceTO) {
        this.serviceTO = serviceTO;
    }

    public RateTypesTO getServiceTO() {
        return serviceTO;
    }

    public void setPraTotalAmount(Long praTotalAmount) {
        this.praTotalAmount = praTotalAmount;
    }

    public Long getPraTotalAmount() {
        return praTotalAmount;
    }

    public void setProAssigmentsDetailsTO(List<ProAssigmentsDetailsTO> proAssigmentsDetailsTO) {
        this.proAssigmentsDetailsTO = proAssigmentsDetailsTO;
    }

    public List<ProAssigmentsDetailsTO> getProAssigmentsDetailsTO() {
        return proAssigmentsDetailsTO;
    }
}
