package com.twoways.to;

import java.util.List;

public class ItemsTO {

    private String itmDescription;
    private Long itmId;
    private String itmName;
    private String itmType;
    private List<ItemsInvoicesTO> itemsInvoicesTOList;
    private List<ItemsExpensesTO> itemsExpensesTOList;

    public ItemsTO() {
    }
    
    public String getItemDescription() {
            return itmDescription;
        }

    public void setItemDescription(String itmDescription) {
        this.itmDescription = itmDescription;
    }

    public Long getItemId() {
        return itmId;
    }

    public void setItemId(Long itmId) {
        this.itmId = itmId;
    }

    public String getItemName() {
        return itmName;
    }

    public void setItemName(String itmName) {
        this.itmName = itmName;
    }
    
    public String getItemType() {
        return itmType;
    }

    public void setItemType(String itmType) {
        this.itmType = itmType;
    }

    public List<ItemsInvoicesTO> getItemsInvoicesTOList() {
        return itemsInvoicesTOList;
    }

    public void setItemsInvoicesTOList(List<ItemsInvoicesTO> itemsInvoicesTOList) {
        this.itemsInvoicesTOList = itemsInvoicesTOList;
    }

    public ItemsInvoicesTO addItemsInvoicesTO(ItemsInvoicesTO itemsInvoicesTO) {
        getItemsInvoicesTOList().add(itemsInvoicesTO);
        itemsInvoicesTO.setItemsTO(this);
        return itemsInvoicesTO;
    }

    public ItemsInvoicesTO removeItemsInvoicesTO(ItemsInvoicesTO itemsInvoicesTO) {
        getItemsInvoicesTOList().remove(itemsInvoicesTO);
        itemsInvoicesTO.setItemsTO(null);
        return itemsInvoicesTO;
    }

    public List<ItemsExpensesTO> getItemsExpensesTOList() {
        return itemsExpensesTOList;
    }

    public void setItemsExpensesTOList(List<ItemsExpensesTO> itemsExpensesTOList) {
        this.itemsExpensesTOList = itemsExpensesTOList;
    }

    public ItemsExpensesTO addItemsExpensesTO(ItemsExpensesTO itemsExpensesTO) {
        getItemsExpensesTOList().add(itemsExpensesTO);
        itemsExpensesTO.setItemsTO(this);
        return itemsExpensesTO;
    }

    public ItemsExpensesTO removeItemsExpensesTO(ItemsExpensesTO itemsExpensesTO) {
        getItemsExpensesTOList().remove(itemsExpensesTO);
        itemsExpensesTO.setItemsTO(null);
        return itemsExpensesTO;
    }    
}
