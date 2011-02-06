package com.twoways.to;

import java.util.List;


//(name = "TRANSLATORS")
public class TranslatorsTO {
    //
    //(name="TRA_ID", nullable = false)
    private Long traId;
    //(name="TRA_SPECIALIZATION")
    private EmployeesTO employeesTO;
    private List<TranslatorsSpecializationsTO> transSpecializationTOList;
    private List<TranslatorsLanguaguesTO> transLanguaguesTOList;
    private LanguaguesTO languaguesTO;
    private LanguaguesTO languaguesTO1;
   /* private EmployeesTO employeesTO;
  */  private LanguaguesTO languaguesTO2;


    public TranslatorsTO() {
    }

    public Long getTraId() {
        return traId;
    }

    public void setTraId(Long traId) {
        this.traId = traId;
    }

    public LanguaguesTO getLanguaguesTO() {
        return languaguesTO;
    }

    public void setLanguaguesTO(LanguaguesTO languaguesTO) {
        this.languaguesTO = languaguesTO;
    }

    public LanguaguesTO getLanguaguesTO1() {
        return languaguesTO1;
    }

    public void setLanguaguesTO1(LanguaguesTO languaguesTO1) {
        this.languaguesTO1 = languaguesTO1;
    }

    public EmployeesTO getEmployeesTO() {
        return employeesTO;
    }

    public void setEmployeesTO(EmployeesTO employeesTO) {
        this.employeesTO = employeesTO;
    }
/*
    public LanguaguesTO getLanguaguesTO2() {
        return languaguesTO2;
    }

    public void setLanguaguesTO2(LanguaguesTO languaguesTO2) {
        this.languaguesTO2 = languaguesTO2;
    }
*/
    public void setTransSpecializationTOList(List<TranslatorsSpecializationsTO> transSpecializationTOList) {
        this.transSpecializationTOList = transSpecializationTOList;
    }

    public List<TranslatorsSpecializationsTO> getTransSpecializationTOList() {
        return transSpecializationTOList;
    }

    public void setTransLanguaguesTOList(List<TranslatorsLanguaguesTO> transLanguaguesTOList) {
        this.transLanguaguesTOList = transLanguaguesTOList;
    }

    public List<TranslatorsLanguaguesTO> getTransLanguaguesTOList() {
        return transLanguaguesTOList;
    }

    public void setLanguaguesTO2(LanguaguesTO languaguesTO2) {
        this.languaguesTO2 = languaguesTO2;
    }

    public LanguaguesTO getLanguaguesTO2() {
        return languaguesTO2;
    }
}
