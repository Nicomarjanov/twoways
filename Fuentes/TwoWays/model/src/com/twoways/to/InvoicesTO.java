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
    private Long invTotal;
    private CurrencyTO currencyTO;
    private List<ItemsInvoicesTO> itemsInvoicesTOList;
    private List<IncomesTO> paymentsTOList;
    private OrdersTO ordersTO;

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

    public Long getInvTotal() {
        return invTotal;
    }

    public void setInvTotal(Long invTotal) {
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

    public OrdersTO getOrdersTO() {
        return ordersTO;
    }

    public void setOrdersTO(OrdersTO ordersTO) {
        this.ordersTO = ordersTO;
    }
}
