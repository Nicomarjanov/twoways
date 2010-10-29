package com.twoways.to;


//(name = "TRANSLATORS")
public class TranslatorsTO {
    //(name="TRA_EDITOR")
    private String traEditor;
    //
    //(name="TRA_ID", nullable = false)
    private Long traId;
    //(name="TRA_SPECIALIZATION")
    private String traSpecialization;
    private LanguaguesTO languaguesTO;
    private LanguaguesTO languaguesTO1;
    private EmployeesTO employeesTO;
    private LanguaguesTO languaguesTO2;
    private CurrencyTO currencyTO;

    public TranslatorsTO() {
    }


    public String getTraEditor() {
        return traEditor;
    }

    public void setTraEditor(String traEditor) {
        this.traEditor = traEditor;
    }

    public Long getTraId() {
        return traId;
    }

    public void setTraId(Long traId) {
        this.traId = traId;
    }

    public String getTraSpecialization() {
        return traSpecialization;
    }

    public void setTraSpecialization(String traSpecialization) {
        this.traSpecialization = traSpecialization;
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

    public LanguaguesTO getLanguaguesTO2() {
        return languaguesTO2;
    }

    public void setLanguaguesTO2(LanguaguesTO languaguesTO2) {
        this.languaguesTO2 = languaguesTO2;
    }

    public CurrencyTO getCurrencyTO() {
        return currencyTO;
    }

    public void setCurrencyTO(CurrencyTO currencyTO) {
        this.currencyTO = currencyTO;
    }
}
