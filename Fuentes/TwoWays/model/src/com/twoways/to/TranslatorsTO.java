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


    public TranslatorsTO() {
    }

    public Long getTraId() {
        return traId;
    }

    public void setTraId(Long traId) {
        this.traId = traId;
    }

    public EmployeesTO getEmployeesTO() {
        return employeesTO;
    }

    public void setEmployeesTO(EmployeesTO employeesTO) {
        this.employeesTO = employeesTO;
    }

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

}
