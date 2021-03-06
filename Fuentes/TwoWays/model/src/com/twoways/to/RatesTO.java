package com.twoways.to;

import java.sql.Timestamp;

import java.util.List;


//(name = "RATES")
public class RatesTO {
    //(name="RAT_DESCRIPTION")
    private String ratDescription;
    //
    //(name="RAT_ID", nullable = false)
    private Long ratId;
    //(name="RAT_NAME", nullable = false)
    private String ratName;
    //(name="RAT_TYPE", nullable = false)
    private String ratType;
    //(name="RAT_VALUE", nullable = false)
    private String ratValue;
    private Timestamp ratEraseDate;

    private CurrencyTO currencyTO;
    private RateTypesTO rateTypesTO;

    public RatesTO() {
    }

    public String getRatDescription() {
        return ratDescription;
    }

    public void setRatDescription(String ratDescription) {
        this.ratDescription = ratDescription;
    }

    public Long getRatId() {
        return ratId;
    }

    public void setRatId(Long ratId) {
        this.ratId = ratId;
    }

    public String getRatName() {
        return ratName;
    }

    public void setRatName(String ratName) {
        this.ratName = ratName;
    }

    public String getRatType() {
        return ratType;
    }

    public void setRatType(String ratType) {
        this.ratType = ratType;
    }

    public String getRatValue() {
        return ratValue;
    }

    public void setRatValue(String ratValue) {
        this.ratValue = ratValue;
    }

    public CurrencyTO getCurrencyTO() {
        return currencyTO;
    }

    public void setCurrencyTO(CurrencyTO currencyTO) {
        this.currencyTO = currencyTO;
    }
   
    public RateTypesTO getRateTypesTO() {
        return rateTypesTO;
    }

    public void setRateTypesTO(RateTypesTO rateTypesTO) {
        this.rateTypesTO = rateTypesTO;
    }

    public void setRatEraseDate(Timestamp ratEraseDate) {
        this.ratEraseDate = ratEraseDate;
    }

    public Timestamp getRatEraseDate() {
        return ratEraseDate;
    }
}
