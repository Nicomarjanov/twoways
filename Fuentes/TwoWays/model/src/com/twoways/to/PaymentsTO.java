package com.twoways.to;

import java.sql.Timestamp;

import java.util.List;

public class PaymentsTO {
    private Long payId;
    private Timestamp payDate;
    private String payDescription;
    private Double payAmount;
    private String payObservation;
    
    private EmployeesTO employeeTO;
    private CurrencyTO currencyTO;
    private AccountsTO accountsTO;
    private UsersTO userTO;
    private List<ProjAssignPaysTO> projAssignTOList;
    
    public PaymentsTO() {
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayDate(Timestamp payDate) {
        this.payDate = payDate;
    }

    public Timestamp getPayDate() {
        return payDate;
    }

    public void setPayDescription(String payDescription) {
        this.payDescription = payDescription;
    }

    public String getPayDescription() {
        return payDescription;
    }

    public void setPayAmount(Double payAmount) {
        this.payAmount = payAmount;
    }

    public Double getPayAmount() {
        return payAmount;
    }

    public void setPayObservation(String payObservation) {
        this.payObservation = payObservation;
    }

    public String getPayObservation() {
        return payObservation;
    }

    public void setCurrencyTO(CurrencyTO currencyTO) {
        this.currencyTO = currencyTO;
    }

    public CurrencyTO getCurrencyTO() {
        return currencyTO;
    }

    public void setAccountsTO(AccountsTO accountsTO) {
        this.accountsTO = accountsTO;
    }

    public AccountsTO getAccountsTO() {
        return accountsTO;
    }

    public void setEmployeeTO(EmployeesTO employeeTO) {
        this.employeeTO = employeeTO;
    }

    public EmployeesTO getEmployeeTO() {
        return employeeTO;
    }

    public void setProjAssignTOList(List<ProjAssignPaysTO> projAssignTOList) {
        this.projAssignTOList = projAssignTOList;
    }

    public List<ProjAssignPaysTO> getProjAssignTOList() {
        return projAssignTOList;
    }

    public void setUserTO(UsersTO userTO) {
        this.userTO = userTO;
    }

    public UsersTO getUserTO() {
        return userTO;
    }
}
