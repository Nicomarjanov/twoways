package com.twoways.to;

public class ProjAssignPaysTO {   
    
    private ProjectAssignmentsTO projectAssignmentsTO;
    private PaymentsTO paymentsTO;

    public ProjAssignPaysTO() {
    }

    public void setProjectAssignmentsTO(ProjectAssignmentsTO projectAssignmentsTO) {
        this.projectAssignmentsTO = projectAssignmentsTO;
    }

    public ProjectAssignmentsTO getProjectAssignmentsTO() {
        return projectAssignmentsTO;
    }

    public void setPaymentsTO(PaymentsTO paymentsTO) {
        this.paymentsTO = paymentsTO;
    }

    public PaymentsTO getPaymentsTO() {
        return paymentsTO;
    }
}
