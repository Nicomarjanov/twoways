package com.twoways.to;

import java.sql.Timestamp;



public class PaymentsTO {
    //(name="PAY_DATE", nullable = false)
    private Timestamp payDate;
    //(name="PAY_DESCRIPTION")
    private String payDescription;
    //
    //(name="PAY_ID", nullable = false)
    private Long payId;
    //(name="PAY_TOTAL")
    private Long payTotal;
    private CurrencyTO currencyTO;
    private AccountsTO accountsTO;
    private ClientsTO clientsTO;
    private InvoicesTO invoicesTO;

    public PaymentsTO() {
    }


    public Timestamp getPayDate() {
        return payDate;
    }

    public void setPayDate(Timestamp payDate) {
        this.payDate = payDate;
    }

    public String getPayDescription() {
        return payDescription;
    }

    public void setPayDescription(String payDescription) {
        this.payDescription = payDescription;
    }

    public Long getPayId() {
        return payId;
    }

    public void setPayId(Long payId) {
        this.payId = payId;
    }

    public Long getPayTotal() {
        return payTotal;
    }

    public void setPayTotal(Long payTotal) {
        this.payTotal = payTotal;
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
