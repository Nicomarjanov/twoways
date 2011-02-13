package com.twoways.to;

public class OrdersRatesTO {
    //
    private Double clrValue;
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
}
