package com.twoways.to;

import java.util.List;


//(name = "CURRENCY")
public class CurrencyTO {
    //
    //(name="CUR_ID", nullable = false)
    private Long curId;
    //(name="CUR_NAME", nullable = false)
    private String curName;
    //(name="CUR_ORIGIN")
    private String curOrigin;
    private List<PaymentsTO>  paymentsTOList;
    private List<InvoicesTO> invoicesTOList;
    private List<TranslatorsTO> translatorsTOList; 
    private List<ClientsTO> clientsTOList;
    private List<ItemsExpensesTO> itemsExpensesTOList;
    private List<ProjectsTO> projectsTOList;
    private List<CurrencyCotizationsTO> currencyCotizationsTOList; 
    private List<RatesTO> ratesTOList; 
    
    public CurrencyTO() {
    }

    public Long getCurId() {
        return curId;
    }

    public void setCurId(Long curId) {
        this.curId = curId;
    }

    public String getCurName() {
        return curName;
    }

    public void setCurName(String curName) {
        this.curName = curName;
    }

    public String getCurOrigin() {
        return curOrigin;
    }

    public void setCurOrigin(String curOrigin) {
        this.curOrigin = curOrigin;
    }

    public List<PaymentsTO> getPaymentsTOList() {
        return paymentsTOList;
    }

    public void setPaymentsTOList(List<PaymentsTO> paymentsTOList) {
        this.paymentsTOList = paymentsTOList;
    }

    public PaymentsTO addPaymentsTO(PaymentsTO paymentsTO) {
        getPaymentsTOList().add(paymentsTO);
        paymentsTO.setCurrencyTO(this);
        return paymentsTO;
    }

    public PaymentsTO removePaymentsTO(PaymentsTO paymentsTO) {
        getPaymentsTOList().remove(paymentsTO);
        paymentsTO.setCurrencyTO(null);
        return paymentsTO;
    }

    public List<InvoicesTO> getInvoicesTOList() {
        return invoicesTOList;
    }

    public void setInvoicesTOList(List<InvoicesTO> invoicesTOList) {
        this.invoicesTOList = invoicesTOList;
    }

    public InvoicesTO addInvoicesTO(InvoicesTO invoicesTO) {
        getInvoicesTOList().add(invoicesTO);
        invoicesTO.setCurrencyTO(this);
        return invoicesTO;
    }

    public InvoicesTO removeInvoicesTO(InvoicesTO invoicesTO) {
        getInvoicesTOList().remove(invoicesTO);
        invoicesTO.setCurrencyTO(null);
        return invoicesTO;
    }

    public List<TranslatorsTO> getTranslatorsTOList() {
        return translatorsTOList;
    }

    public void setTranslatorsTOList(List<TranslatorsTO> translatorsTOList) {
        this.translatorsTOList = translatorsTOList;
    }

    public TranslatorsTO addTranslatorsTO(TranslatorsTO translatorsTO) {
        getTranslatorsTOList().add(translatorsTO);
        translatorsTO.setCurrencyTO(this);
        return translatorsTO;
    }

    public TranslatorsTO removeTranslatorsTO(TranslatorsTO translatorsTO) {
        getTranslatorsTOList().remove(translatorsTO);
        translatorsTO.setCurrencyTO(null);
        return translatorsTO;
    }

    public List<ClientsTO> getClientsTOList() {
        return clientsTOList;
    }

    public void setClientsTOList(List<ClientsTO> clientsTOList) {
        this.clientsTOList = clientsTOList;
    }

    public ClientsTO addClientsTO(ClientsTO clientsTO) {
        getClientsTOList().add(clientsTO);
        clientsTO.setCurrencyTO(this);
        return clientsTO;
    }

    public ClientsTO removeClientsTO(ClientsTO clientsTO) {
        getClientsTOList().remove(clientsTO);
        clientsTO.setCurrencyTO(null);
        return clientsTO;
    }

    public List<ItemsExpensesTO> getItemsExpensesTOList() {
        return itemsExpensesTOList;
    }

    public void setItemsExpensesTOList(List<ItemsExpensesTO> itemsExpensesTOList) {
        this.itemsExpensesTOList = itemsExpensesTOList;
    }

    public ItemsExpensesTO addItemsExpensesTO(ItemsExpensesTO itemsExpensesTO) {
        getItemsExpensesTOList().add(itemsExpensesTO);
        itemsExpensesTO.setCurrencyTO(this);
        return itemsExpensesTO;
    }

    public ItemsExpensesTO removeItemsExpensesTO(ItemsExpensesTO itemsExpensesTO) {
        getItemsExpensesTOList().remove(itemsExpensesTO);
        itemsExpensesTO.setCurrencyTO(null);
        return itemsExpensesTO;
    }

    public List<ProjectsTO> getProjectsTOList() {
        return projectsTOList;
    }

    public void setProjectsTOList(List<ProjectsTO> projectsTOList) {
        this.projectsTOList = projectsTOList;
    }

    public ProjectsTO addProjectsTO(ProjectsTO projectsTO) {
        getProjectsTOList().add(projectsTO);
        projectsTO.setCurrencyTO(this);
        return projectsTO;
    }

    public ProjectsTO removeProjectsTO(ProjectsTO projectsTO) {
        getProjectsTOList().remove(projectsTO);
        projectsTO.setCurrencyTO(null);
        return projectsTO;
    }

    public List<CurrencyCotizationsTO> getCurrencyCotizationsTOList() {
        return currencyCotizationsTOList;
    }

    public void setCurrencyCotizationsTOList(List<CurrencyCotizationsTO> currencyCotizationsTOList) {
        this.currencyCotizationsTOList = currencyCotizationsTOList;
    }

    public CurrencyCotizationsTO addCurrencyCotizationsTO(CurrencyCotizationsTO currencyCotizationsTO) {
        getCurrencyCotizationsTOList().add(currencyCotizationsTO);
        currencyCotizationsTO.setCurrencyTO(this);
        return currencyCotizationsTO;
    }

    public CurrencyCotizationsTO removeCurrencyCotizationsTO(CurrencyCotizationsTO currencyCotizationsTO) {
        getCurrencyCotizationsTOList().remove(currencyCotizationsTO);
        currencyCotizationsTO.setCurrencyTO(null);
        return currencyCotizationsTO;
    }

    public List<RatesTO> getRatesTOList() {
        return ratesTOList;
    }

    public void setRatesTOList(List<RatesTO> ratesTOList) {
        this.ratesTOList = ratesTOList;
    }

    public RatesTO addRatesTO(RatesTO ratesTO) {
        getRatesTOList().add(ratesTO);
        ratesTO.setCurrencyTO(this);
        return ratesTO;
    }

    public RatesTO removeRatesTO(RatesTO ratesTO) {
        getRatesTOList().remove(ratesTO);
        ratesTO.setCurrencyTO(null);
        return ratesTO;
    }
}
