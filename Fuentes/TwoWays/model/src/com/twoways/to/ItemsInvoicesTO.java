package com.twoways.to;



public class ItemsInvoicesTO {
    //
    //(name="ITI_ID", nullable = false)
    private Long itiId;
    //(name="ITI_VALUE")
    private Double itiValue;
    private InvoicesTO invoicesTO;
    private ItemsTO itemsTO;
    private OrdersRatesTO ordersRatesTO;
    private CurrencyTO currencyTO;    

    public ItemsInvoicesTO() {
    }


    public Long getItiId() {
        return itiId;
    }

    public void setItiId(Long itiId) {
        this.itiId = itiId;
    }

    public Double getItiValue() {
        return itiValue;
    }

    public void setItiValue(Double itiValue) {
        this.itiValue = itiValue;
    }

    public InvoicesTO getInvoicesTO() {
        return invoicesTO;
    }

    public void setInvoicesTO(InvoicesTO invoicesTO) {
        this.invoicesTO = invoicesTO;
    }

    public ItemsTO getItemsTO() {
        return itemsTO;
    }

    public void setItemsTO(ItemsTO itemsTO) {
        this.itemsTO = itemsTO;
    }

    public void setOrdersRatesTO(OrdersRatesTO ordersRatesTO) {
        this.ordersRatesTO = ordersRatesTO;
    }

    public OrdersRatesTO getOrdersRatesTO() {
        return ordersRatesTO;
    }

    public void setCurrencyTO(CurrencyTO currencyTO) {
        this.currencyTO = currencyTO;
    }

    public CurrencyTO getCurrencyTO() {
        return currencyTO;
    }
}
