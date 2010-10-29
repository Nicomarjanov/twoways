package com.twoways.to;


//(name = "PROJECTS_RATES")
//Class(ProjectsRatesTOPK.class)
public class ProjectsRatesTO {
    //
    //(name="PROJECTS_PRO_ID", nullable = false, insertable = false, 
    private Long projectsProId;
    //
    //(name="RATES_RAT_ID", nullable = false, insertable = false, 
    private Long ratesRatId;
    private ProjectsTO projectsTO;
    private RatesTO ratesTO;

    public ProjectsRatesTO() {
    }

    public Long getProjectsProId() {
        return projectsProId;
    }

    public void setProjectsProId(Long projectsProId) {
        this.projectsProId = projectsProId;
    }

    public Long getRatesRatId() {
        return ratesRatId;
    }

    public void setRatesRatId(Long ratesRatId) {
        this.ratesRatId = ratesRatId;
    }

    public ProjectsTO getProjectsTO() {
        return projectsTO;
    }

    public void setProjectsTO(ProjectsTO projectsTO) {
        this.projectsTO = projectsTO;
    }

    public RatesTO getRatesTO() {
        return ratesTO;
    }

    public void setRatesTO(RatesTO ratesTO) {
        this.ratesTO = ratesTO;
    }
}
