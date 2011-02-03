package com.twoways.to;

import java.sql.Timestamp;




//(name = "PROJECT_ASSIGNMENTS")
//Class(ProjectAssignmentsTOPK.class)
public class ProjectAssignmentsTO {
    
    private Timestamp praAssignDate;
    private Timestamp praFinishDate;
    private Long praId;
    private Long projectsProId;
    private EmployeesTO employeesTO;
    private ProjectsTO projectsTO;
    private ServicesTO serviceTO;
    private ProAssigmentsDetailsTO proAssigmentsDetailsTO;

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

    public void setProjectsProId(Long projectsProId) {
        this.projectsProId = projectsProId;
    }

    public Long getProjectsProId() {
        return projectsProId;
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

   
    public void setServiceTO(ServicesTO serviceTO) {
        this.serviceTO = serviceTO;
    }

    public ServicesTO getServiceTO() {
        return serviceTO;
    }

    public void setProAssigmentsDetailsTO(ProAssigmentsDetailsTO proAssigmentsDetailsTO) {
        this.proAssigmentsDetailsTO = proAssigmentsDetailsTO;
    }

    public ProAssigmentsDetailsTO getProAssigmentsDetailsTO() {
        return proAssigmentsDetailsTO;
    }
}
