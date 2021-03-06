package com.twoways.to;

import java.sql.Timestamp;

import java.util.List;


//(name = "CLIENTS")
public class ClientsTO {
    //(name="CLI_ADDRESS")
    private String cliAddress;
    //(name="CLI_COUNTRY")
    private String cliCountry;
    //(name="CLI_DESCRIPTION")
    private String cliDescription;
    //(name="CLI_ID", nullable = false)
    private Long cliId;
    //(name="CLI_MAIL")
    private String cliMail;
    //(name="CLI_NAME")
    private String cliName;
    //(name="CLI_PHONE")
    private String cliPhone;
    //(name="CLI_POSTAL_CODE")
    private String cliPostalCode;
    private List<ClientResponsableTO> clientResponsableTOList;
    private List<OrdersTO> ordersTOList;
    private List<IncomesTO> paymentsTOList;
    private CurrencyTO currencyTO;
    private List<ClientsRatesTO> clientsRatesTOList;
    private Timestamp cliEraseDate;    

    public ClientsTO() {
    }

    public String getCliAddress() {
        return cliAddress;
    }

    public void setCliAddress(String cliAddress) {
        this.cliAddress = cliAddress;
    }

    public String getCliCountry() {
        return cliCountry;
    }

    public void setCliCountry(String cliCountry) {
        this.cliCountry = cliCountry;
    }

    public String getCliDescription() {
        return cliDescription;
    }

    public void setCliDescription(String cliDescription) {
        this.cliDescription = cliDescription;
    }

    public Long getCliId() {
        return cliId;
    }

    public void setCliId(Long cliId) {
        this.cliId = cliId;
    }

    public String getCliMail() {
        return cliMail;
    }

    public void setCliMail(String cliMail) {
        this.cliMail = cliMail;
    }

    public String getCliName() {
        return cliName;
    }

    public void setCliName(String cliName) {
        this.cliName = cliName;
    }

    public String getCliPhone() {
        return cliPhone;
    }

    public void setCliPhone(String cliPhone) {
        this.cliPhone = cliPhone;
    }

    public String getCliPostalCode() {
        return cliPostalCode;
    }

    public void setCliPostalCode(String cliPostalCode) {
        this.cliPostalCode = cliPostalCode;
    }


    public List<ClientResponsableTO> getClientResponsableTOList() {
        return clientResponsableTOList;
    }

    public void setClientResponsableTOList(List<ClientResponsableTO> clientResponsableTOList) {
        this.clientResponsableTOList = clientResponsableTOList;
    }

    public ClientResponsableTO addClientResponsableTO(ClientResponsableTO clientResponsableTO) {
        getClientResponsableTOList().add(clientResponsableTO);
        clientResponsableTO.setClientsTO(this);
        return clientResponsableTO;
    }

    public ClientResponsableTO removeClientResponsableTO(ClientResponsableTO clientResponsableTO) {
        getClientResponsableTOList().remove(clientResponsableTO);
        clientResponsableTO.setClientsTO(null);
        return clientResponsableTO;
    }

    public List<OrdersTO> getOrdersTOList() {
        return ordersTOList;
    }

    public void setOrdersTOList(List<OrdersTO> ordersTOList) {
        this.ordersTOList = ordersTOList;
    }

    public OrdersTO addOrdersTO(OrdersTO ordersTO) {
        getOrdersTOList().add(ordersTO);
        ordersTO.setClientsTO(this);
        return ordersTO;
    }

    public OrdersTO removeOrdersTO(OrdersTO ordersTO) {
        getOrdersTOList().remove(ordersTO);
        ordersTO.setClientsTO(null);
        return ordersTO;
    }

    public List<IncomesTO> getPaymentsTOList() {
        return paymentsTOList;
    }

    public void setPaymentsTOList(List<IncomesTO> paymentsTOList) {
        this.paymentsTOList = paymentsTOList;
    }

    public IncomesTO addPaymentsTO(IncomesTO paymentsTO) {
        getPaymentsTOList().add(paymentsTO);
        paymentsTO.setClientsTO(this);
        return paymentsTO;
    }

    public IncomesTO removePaymentsTO(IncomesTO paymentsTO) {
        getPaymentsTOList().remove(paymentsTO);
        paymentsTO.setClientsTO(null);
        return paymentsTO;
    }

    public CurrencyTO getCurrencyTO() {
        return currencyTO;
    }

    public void setCurrencyTO(CurrencyTO currencyTO) {
        this.currencyTO = currencyTO;
    }
    
    public List<ClientsRatesTO> getClientsRatesTOList() {
        return clientsRatesTOList;
    }

    public void setClientsRatesTOList(List<ClientsRatesTO> clientsRatesTOList) {
        this.clientsRatesTOList = clientsRatesTOList;
    }

    public ClientsRatesTO addClientsRatesTO(ClientsRatesTO clientsRatesTO) {
        getClientsRatesTOList().add(clientsRatesTO);
        clientsRatesTO.setClientsTO(this);
        return clientsRatesTO;
    }

    public ClientsRatesTO removeClientsRatesTO(ClientsRatesTO clientsRatesTO) {
        getClientsRatesTOList().remove(clientsRatesTO);
        clientsRatesTO.setClientsTO(null);
        return clientsRatesTO;
    }

    public void setCliEraseDate(Timestamp cliEraseDate) {
        this.cliEraseDate = cliEraseDate;
    }

    public Timestamp getCliEraseDate() {
        return cliEraseDate;
    }
}
