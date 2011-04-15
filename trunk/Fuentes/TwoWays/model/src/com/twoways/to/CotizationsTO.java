package com.twoways.to;

import java.sql.Timestamp;

public class CotizationsTO {
    private Long cucId;
    private Timestamp cucDate;
    private Double cucValue;
    private CurrencyTO currencyTO;
    
    public CotizationsTO() {
    }

    public void setCucId(Long cucId) {
        this.cucId = cucId;
    }

    public Long getCucId() {
        return cucId;
    }

    public void setCucDate(Timestamp cucDate) {
        this.cucDate = cucDate;
    }

    public Timestamp getCucDate() {
        return cucDate;
    }

    public void setCucValue(Double cucValue) {
        this.cucValue = cucValue;
    }

    public Double getCucValue() {
        return cucValue;
    }

    public void setCurrencyTO(CurrencyTO currencyTO) {
        this.currencyTO = currencyTO;
    }

    public CurrencyTO getCurrencyTO() {
        return currencyTO;
    }
}
