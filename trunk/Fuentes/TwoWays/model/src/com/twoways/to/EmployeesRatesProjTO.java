package com.twoways.to;

public class EmployeesRatesProjTO extends EmployeesRatesTO {

  private ProAssigmentsDetailsTO  projAssigmentsDetailsTO;    //
  private Long padWCount; 
  private Long padAmount; 

    public void setProjAssigmentsDetailsTO(ProAssigmentsDetailsTO projAssigmentsDetailsTO) {
        this.projAssigmentsDetailsTO = projAssigmentsDetailsTO;
    }

    public ProAssigmentsDetailsTO getProjAssigmentsDetailsTO() {
        return projAssigmentsDetailsTO;
    }

    public void setPadAmount(Long padAmount) {
        this.padAmount = padAmount;
    }

    public Long getPadAmount() {
        return padAmount;
    }

    public void setPadWCount(Long padWCount) {
        this.padWCount = padWCount;
    }

    public Long getPadWCount() {
        return padWCount;
    }
}
