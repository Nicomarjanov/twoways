package com.twoways.to;

import java.sql.Timestamp;

import java.util.List;


//(name = "ITEMS")
public class ItemsTO {
    //(name="ITM_DESCRIPTION")
    private String itmDescription;
    //
    //(name="ITM_ID", nullable = false)
    private Long itmId;
    //(name="ITM_NAME", nullable = false)
    private String itmName;
    private String itmType;
    private Timestamp itmEraseDate;
   // private List<ItemsInvoicesTO> itemsInvoicesTOList;
    private List<ItemsExpensesTO> itemsExpensesTOList;

    public ItemsTO() {
    }





/*    public List<ItemsInvoicesTO> getItemsInvoicesTOList() {
        return itemsInvoicesTOList;
    }

    public void setItemsInvoicesTOList(List<ItemsInvoicesTO> itemsInvoicesTOList) {
        this.itemsInvoicesTOList = itemsInvoicesTOList;
    }

    public ItemsInvoicesTO addItemsInvoicesTO(ItemsInvoicesTO itemsInvoicesTO) {
        getItemsInvoicesTOList().add(itemsInvoicesTO);
//        itemsInvoicesTO.setItemsTO(this);
        return itemsInvoicesTO;
    }

    public ItemsInvoicesTO removeItemsInvoicesTO(ItemsInvoicesTO itemsInvoicesTO) {
        getItemsInvoicesTOList().remove(itemsInvoicesTO);
//        itemsInvoicesTO.setItemsTO(null);
        return itemsInvoicesTO;
    }
*/
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

    public void setItmId(Long itmId) {
        this.itmId = itmId;
    }

    public Long getItmId() {
        return itmId;
    }

    public void setItmName(String itmName) {
        this.itmName = itmName;
    }

    public String getItmName() {
        return itmName;
    }

    public void setItmDescription(String itmDescription) {
        this.itmDescription = itmDescription;
    }

    public String getItmDescription() {
        return itmDescription;
    }

    public void setItmType(String itmType) {
        this.itmType = itmType;
    }

    public String getItmType() {
        return itmType;
    }

    public void setItmEraseDate(Timestamp itmEraseDate) {
        this.itmEraseDate = itmEraseDate;
    }

    public Timestamp getItmEraseDate() {
        return itmEraseDate;
    }
}
