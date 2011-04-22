package com.twoways.to;

import java.sql.Timestamp;

import java.util.List;


//(name = "INVOICES")
public class InvoicesTO {
    //(name="INV_DATE", nullable = false)
    private Timestamp invDate;
    //
    //(name="INV_ID", nullable = false)
    private Long invId;
    //(name="INV_TOTAL")
    private Double invTotal;
    private String invInvoiced;

    private UsersTO usersTO;    
    private CurrencyTO currencyTO;
    private AccountsTO accountsTO;
    private ClientsTO clientsTO;

    private List<ItemsInvoicesTO> itemsInvoicesTOList;
    private List<IncomesTO> paymentsTOList;

    public InvoicesTO() {
    }


    public Timestamp getInvDate() {
        return invDate;
    }

    public void setInvDate(Timestamp invDate) {
        this.invDate = invDate;
    }

    public Long getInvId() {
        return invId;
    }

    public void setInvId(Long invId) {
        this.invId = invId;
    }

    public Double getInvTotal() {
        return invTotal;
    }

    public void setInvTotal(Double invTotal) {
        this.invTotal = invTotal;
    }


    public CurrencyTO getCurrencyTO() {
        return currencyTO;
    }

    public void setCurrencyTO(CurrencyTO currencyTO) {
        this.currencyTO = currencyTO;
    }

    public List<ItemsInvoicesTO> getItemsInvoicesTOList() {
        return itemsInvoicesTOList;
    }

    public void setItemsInvoicesTOList(List<ItemsInvoicesTO> itemsInvoicesTOList) {
        this.itemsInvoicesTOList = itemsInvoicesTOList;
    }

    public ItemsInvoicesTO addItemsInvoicesTO(ItemsInvoicesTO itemsInvoicesTO) {
        getItemsInvoicesTOList().add(itemsInvoicesTO);
        itemsInvoicesTO.setInvoicesTO(this);
        return itemsInvoicesTO;
    }

    public ItemsInvoicesTO removeItemsInvoicesTO(ItemsInvoicesTO itemsInvoicesTO) {
        getItemsInvoicesTOList().remove(itemsInvoicesTO);
        itemsInvoicesTO.setInvoicesTO(null);
        return itemsInvoicesTO;
    }

    public List<IncomesTO> getPaymentsTOList() {
        return paymentsTOList;
    }

    public void setPaymentsTOList(List<IncomesTO> paymentsTOList) {
        this.paymentsTOList = paymentsTOList;
    }

    public IncomesTO addPaymentsTO(IncomesTO paymentsTO) {
        getPaymentsTOList().add(paymentsTO);
        paymentsTO.setInvoicesTO(this);
        return paymentsTO;
    }

    public IncomesTO removePaymentsTO(IncomesTO paymentsTO) {
        getPaymentsTOList().remove(paymentsTO);
        paymentsTO.setInvoicesTO(null);
        return paymentsTO;
    }

    public AccountsTO getAccountsTO() {
        return accountsTO;
    }

    public void setAccountsTO(AccountsTO accountsTO) {
        this.accountsTO = accountsTO;
    }

    public void setClientsTO(ClientsTO clientsTO) {
        this.clientsTO = clientsTO;
    }

    public ClientsTO getClientsTO() {
        return clientsTO;
    }

    public void setUsersTO(UsersTO usersTO) {
        this.usersTO = usersTO;
    }

    public UsersTO getUsersTO() {
        return usersTO;
    }

    public void setInvInvoiced(String invInvoiced) {
        this.invInvoiced = invInvoiced;
    }

    public String getInvInvoiced() {
        return invInvoiced;
    }
}
