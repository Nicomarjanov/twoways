package com.twoways.to;

import java.sql.Timestamp;

import java.util.List;


public class AccountsTO {
   //(name="ACC_DESCRIPTION")
    private String accDescription;
   //(name="ACC_DETAILS")
    private String accDetails;
   //(name="ACC_ID", nullable = false)
    private Long accId;
   //(name="ACC_NAME", nullable = false)
    private String accName;
    private String accNumber;  
    
    private List<ItemsExpensesTO> itemsExpensesTOList;
    private List<PaymentsTO>  paymentsTOList;
    private List<ItemsInvoicesTO> itemsInvoicesTOList;    
    private Timestamp accEraseDate;
  

    public AccountsTO() {
    }

    public String getAccDescription() {
        return accDescription;
    }

    public void setAccDescription(String accDescription) {
        this.accDescription = accDescription;
    }

    public String getAccDetails() {
        return accDetails;
    }

    public void setAccDetails(String accDetails) {
        this.accDetails = accDetails;
    }

    public Long getAccId() {
        return accId;
    }

    public void setAccId(Long accId) {
        this.accId = accId;
    }

    public String getAccName() {
        return accName;
    }

    public void setAccName(String accName) {
        this.accName = accName;
    }

    public List<ItemsExpensesTO> getItemsExpensesTOList() {
        return itemsExpensesTOList;
    }

    public void setItemsExpensesTOList(List<ItemsExpensesTO> itemsExpensesTOList) {
        this.itemsExpensesTOList = itemsExpensesTOList;
    }

    public ItemsExpensesTO addItemsExpensesTO(ItemsExpensesTO itemsExpensesTO) {
        getItemsExpensesTOList().add(itemsExpensesTO);
        itemsExpensesTO.setAccountsTO(this);
        return itemsExpensesTO;
    }

    public ItemsExpensesTO removeItemsExpensesTO(ItemsExpensesTO itemsExpensesTO) {
        getItemsExpensesTOList().remove(itemsExpensesTO);
        itemsExpensesTO.setAccountsTO(null);
        return itemsExpensesTO;
    }

    public List<PaymentsTO> getPaymentsTOList() {
        return paymentsTOList;
    }

    public void setPaymentsTOList(List<PaymentsTO> paymentsTOList) {
        this.paymentsTOList = paymentsTOList;
    }

    public PaymentsTO addPaymentsTO(PaymentsTO paymentsTO) {
        getPaymentsTOList().add(paymentsTO);
        paymentsTO.setAccountsTO(this);
        return paymentsTO;
    }

    public PaymentsTO removePaymentsTO(PaymentsTO paymentsTO) {
        getPaymentsTOList().remove(paymentsTO);
        paymentsTO.setAccountsTO(null);
        return paymentsTO;
    }

    public List<ItemsInvoicesTO> getItemsInvoicesTOList() {
        return itemsInvoicesTOList;
    }

    public void setItemsInvoicesTOList(List<ItemsInvoicesTO> itemsInvoicesTOList) {
        this.itemsInvoicesTOList = itemsInvoicesTOList;
    }

    public ItemsInvoicesTO addItemsInvoicesTO(ItemsInvoicesTO itemsInvoicesTO) {
        getItemsInvoicesTOList().add(itemsInvoicesTO);
        itemsInvoicesTO.setAccountsTO(this);
        return itemsInvoicesTO;
    }

    public ItemsInvoicesTO removeItemsInvoicesTO(ItemsInvoicesTO itemsInvoicesTO) {
        getItemsInvoicesTOList().remove(itemsInvoicesTO);
        itemsInvoicesTO.setAccountsTO(null);
        return itemsInvoicesTO;
    }

    public void setAccEraseDate(Timestamp accEraseDate) {
        this.accEraseDate = accEraseDate;
    }

    public Timestamp getAccEraseDate() {
        return accEraseDate;
    }


    public void setAccNumber(String accNumber) {
        this.accNumber = accNumber;
    }

    public String getAccNumber() {
        return accNumber;
    }
}
