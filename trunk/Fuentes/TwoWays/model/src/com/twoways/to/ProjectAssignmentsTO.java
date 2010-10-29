package com.twoways.to;

import java.sql.Timestamp;




//(name = "PROJECT_ASSIGNMENTS")
//Class(ProjectAssignmentsTOPK.class)
public class ProjectAssignmentsTO {
      private Long employeesEmpId;
    //(name="PRA_ASSIGN_DATE", nullable = false)
    private Timestamp praAssignDate;
    //(name="PRA_CLASSIFICATION")
    private String praClassification;
    //(name="PRA_DEADLINE_DATE")
    private Timestamp praDeadlineDate;
    //(name="PRA_FINISH_DATE")
    private Timestamp praFinishDate;
    //
    //(name="PRA_ID", nullable = false)
    private Long praId;

    private Long projectsProId;
    private EmployeesTO employeesTO;
    private ProjectsTO projectsTO;

    public ProjectAssignmentsTO() {
    }

    public Long getEmployeesEmpId() {
        return employeesEmpId;
    }

    public void setEmployeesEmpId(Long employeesEmpId) {
        this.employeesEmpId = employeesEmpId;
    }

    public Timestamp getPraAssignDate() {
        return praAssignDate;
    }

    public void setPraAssignDate(Timestamp praAssignDate) {
        this.praAssignDate = praAssignDate;
    }

    public String getPraClassification() {
        return praClassification;
    }

    public void setPraClassification(String praClassification) {
        this.praClassification = praClassification;
    }

    public Timestamp getPraDeadlineDate() {
        return praDeadlineDate;
    }

    public void setPraDeadlineDate(Timestamp praDeadlineDate) {
        this.praDeadlineDate = praDeadlineDate;
    }

    public Timestamp getPraFinishDate() {
        return praFinishDate;
    }

    public void setPraFinishDate(Timestamp praFinishDate) {
        this.praFinishDate = praFinishDate;
    }

    public Long getPraId() {
        return praId;
    }

    public void setPraId(Long praId) {
        this.praId = praId;
    }

    public Long getProjectsProId() {
        return projectsProId;
    }

    public void setProjectsProId(Long projectsProId) {
        this.projectsProId = projectsProId;
    }

    public EmployeesTO getEmployeesTO() {
        return employeesTO;
    }

    public void setEmployeesTO(EmployeesTO employeesTO) {
        this.employeesTO = employeesTO;
    }

    public ProjectsTO getProjectsTO() {
        return projectsTO;
    }

    public void setProjectsTO(ProjectsTO projectsTO) {
        this.projectsTO = projectsTO;
    }
}
