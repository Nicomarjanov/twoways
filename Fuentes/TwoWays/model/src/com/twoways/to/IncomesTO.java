package com.twoways.to;

import java.sql.Timestamp;



public class IncomesTO {
    //(name="inc_DATE", nullable = false)
    private Timestamp incDate;
    //(name="inc_DESCRIPTION")
    private String incDescription;
    //
    //(name="inc_ID", nullable = false)
    private Long incId;
    //(name="inc_TOTAL")
    private Long incTotal;
    private CurrencyTO currencyTO;
    private AccountsTO accountsTO;
    private ClientsTO clientsTO;
    private InvoicesTO invoicesTO;

    public IncomesTO() {
    }


    public Timestamp getIncDate() {
        return incDate;
    }

    public void setIncDate(Timestamp incDate) {
        this.incDate = incDate;
    }

    public String getIncDescription() {
        return incDescription;
    }

    public void setIncDescription(String incDescription) {
        this.incDescription = incDescription;
    }

    public Long getIncId() {
        return incId;
    }

    public void setIncId(Long incId) {
        this.incId = incId;
    }

    public Long getIncTotal() {
        return incTotal;
    }

    public void setIncTotal(Long incTotal) {
        this.incTotal = incTotal;
    }

    public CurrencyTO getCurrencyTO() {
        return currencyTO;
    }

    public void setCurrencyTO(CurrencyTO currencyTO) {
        this.currencyTO = currencyTO;
    }

    public AccountsTO getAccountsTO() {
        return accountsTO;
    }

    public void setAccountsTO(AccountsTO accountsTO) {
        this.accountsTO = accountsTO;
    }

    public ClientsTO getClientsTO() {
        return clientsTO;
    }

    public void setClientsTO(ClientsTO clientsTO) {
        this.clientsTO = clientsTO;
    }

    public InvoicesTO getInvoicesTO() {
        return invoicesTO;
    }

    public void setInvoicesTO(InvoicesTO invoicesTO) {
        this.invoicesTO = invoicesTO;
    }
}
