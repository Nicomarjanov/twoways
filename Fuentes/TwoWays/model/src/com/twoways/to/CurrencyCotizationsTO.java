package com.twoways.to;

import java.sql.Timestamp;


//(name = "CURRENCY_COTIZATIONS")
//Class(CurrencyCotizationsTOPK.class)
public class CurrencyCotizationsTO {
    //(name="CUC_DATE", nullable = false)
    private Timestamp cucDate;
    //
    //(name="CUC_ID", nullable = false)
    private Long cucId;
    //(name="CUC_VALUE", nullable = false)
    private Long cucValue;
    //
    //(name="CURRENCY_CUR_ID", nullable = false, insertable = false, 
    private Long currencyCurId;
   
    private CurrencyTO currencyTO;

    public CurrencyCotizationsTO() {
    }

    public Timestamp getCucDate() {
        return cucDate;
    }

    public void setCucDate(Timestamp cucDate) {
        this.cucDate = cucDate;
    }

    public Long getCucId() {
        return cucId;
    }

    public void setCucId(Long cucId) {
        this.cucId = cucId;
    }

    public Long getCucValue() {
        return cucValue;
    }

    public void setCucValue(Long cucValue) {
        this.cucValue = cucValue;
    }

    public Long getCurrencyCurId() {
        return currencyCurId;
    }

    public void setCurrencyCurId(Long currencyCurId) {
        this.currencyCurId = currencyCurId;
    }

    public CurrencyTO getCurrencyTO() {
        return currencyTO;
    }

    public void setCurrencyTO(CurrencyTO currencyTO) {
        this.currencyTO = currencyTO;
    }
}
