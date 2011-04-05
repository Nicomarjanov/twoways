package com.twoways.to;

public class ProjAssignPaysTO {   
    
    private ProAssigmentsDetailsTO proAssigmentsDetailsTO;
    private PaymentsTO paymentsTO;

    public ProjAssignPaysTO() {
    }

    public void setPaymentsTO(PaymentsTO paymentsTO) {
        this.paymentsTO = paymentsTO;
    }

    public PaymentsTO getPaymentsTO() {
        return paymentsTO;
    }

    public void setProAssigmentsDetailsTO(ProAssigmentsDetailsTO proAssigmentsDetailsTO) {
        this.proAssigmentsDetailsTO = proAssigmentsDetailsTO;
    }

    public ProAssigmentsDetailsTO getProAssigmentsDetailsTO() {
        return proAssigmentsDetailsTO;
    }
}
