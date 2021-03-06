package com.twoways.to;

import java.sql.Timestamp;

public class OrdersRatesTO {
    //
    private Double clrValue;
    private Float orrWcount= 0f;
    private Timestamp orrPayDate;
    private RatesTO ratesTO;
    private OrdersTO ordersTO;

    public OrdersRatesTO() {
    }


    public Double getClrValue() {
        return clrValue;
    }

    public void setClrValue(Double clrValue) {
        this.clrValue = clrValue;
    }


    public RatesTO getRatesTO() {
        return ratesTO;
    }

    public void setRatesTO(RatesTO ratesTO) {
        this.ratesTO = ratesTO;
    }


    public void setOrdersTO(OrdersTO ordersTO) {
        this.ordersTO = ordersTO;
    }

    public OrdersTO getOrdersTO() {
        return ordersTO;
    }

    public void setOrrWcount(Float orrWcount) {
        this.orrWcount = orrWcount;
    }

    public Float getOrrWcount() {
        return orrWcount;
    }

    public void setOrrPayDate(Timestamp orrPayDate) {
        this.orrPayDate = orrPayDate;
    }

    public Timestamp getOrrPayDate() {
        return orrPayDate;
    }
}
