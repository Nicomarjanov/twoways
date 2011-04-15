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
    private String accBank;
    private String accHolder;
    private String accSwiftCode;
    private String accWireTransfer;
    private String accDirection;
    private String accZipCode;
    private String accCountry;
    private String accCity;
    
    private List<ItemsExpensesTO> itemsExpensesTOList;
    private List<IncomesTO>  paymentsTOList;
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

    public List<IncomesTO> getPaymentsTOList() {
        return paymentsTOList;
    }

    public void setPaymentsTOList(List<IncomesTO> paymentsTOList) {
        this.paymentsTOList = paymentsTOList;
    }

    public IncomesTO addPaymentsTO(IncomesTO paymentsTO) {
        getPaymentsTOList().add(paymentsTO);
        paymentsTO.setAccountsTO(this);
        return paymentsTO;
    }

    public IncomesTO removePaymentsTO(IncomesTO paymentsTO) {
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

  /*  public ItemsInvoicesTO addItemsInvoicesTO(ItemsInvoicesTO itemsInvoicesTO) {
        getItemsInvoicesTOList().add(itemsInvoicesTO);
        itemsInvoicesTO.setAccountsTO(this);
        return itemsInvoicesTO;
    }

    public ItemsInvoicesTO removeItemsInvoicesTO(ItemsInvoicesTO itemsInvoicesTO) {
        getItemsInvoicesTOList().remove(itemsInvoicesTO);
        itemsInvoicesTO.setAccountsTO(null);
        return itemsInvoicesTO;
    }
*/
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

    public void setAccBank(String accBank) {
        this.accBank = accBank;
    }

    public String getAccBank() {
        return accBank;
    }

    public void setAccHolder(String accHolder) {
        this.accHolder = accHolder;
    }

    public String getAccHolder() {
        return accHolder;
    }

    public void setAccSwiftCode(String accSwiftCode) {
        this.accSwiftCode = accSwiftCode;
    }

    public String getAccSwiftCode() {
        return accSwiftCode;
    }

    public void setAccWireTransfer(String accWireTransfer) {
        this.accWireTransfer = accWireTransfer;
    }

    public String getAccWireTransfer() {
        return accWireTransfer;
    }

    public void setAccDirection(String accDirection) {
        this.accDirection = accDirection;
    }

    public String getAccDirection() {
        return accDirection;
    }

    public void setAccZipCode(String accZipCode) {
        this.accZipCode = accZipCode;
    }

    public String getAccZipCode() {
        return accZipCode;
    }

    public void setAccCountry(String accCountry) {
        this.accCountry = accCountry;
    }

    public String getAccCountry() {
        return accCountry;
    }

    public void setAccCity(String accCity) {
        this.accCity = accCity;
    }

    public String getAccCity() {
        return accCity;
    }
}
