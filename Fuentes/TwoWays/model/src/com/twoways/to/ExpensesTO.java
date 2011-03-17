package com.twoways.to;

import java.sql.Timestamp;

import java.util.List;



//(name = "EXPENSES")
public class ExpensesTO {
    //(name="EXP_DATE", nullable = false)
    private Timestamp expDate;
    //
    //(name="EXP_ID", nullable = false)
    private Long expId;
    //(name="EXP_TOTAL")
    private Long expTotal;
    private List<ItemsExpensesTO> itemsExpensesTOList;

    public ExpensesTO() {
    }


    public Timestamp getExpDate() {
        return expDate;
    }

    public void setExpDate(Timestamp expDate) {
        this.expDate = expDate;
    }

    public Long getExpId() {
        return expId;
    }

    public void setExpId(Long expId) {
        this.expId = expId;
    }

    public Long getExpTotal() {
        return expTotal;
    }

    public void setExpTotal(Long expTotal) {
        this.expTotal = expTotal;
    }

    public List<ItemsExpensesTO> getItemsExpensesTOList() {
        return itemsExpensesTOList;
    }

    public void setItemsExpensesTOList(List<ItemsExpensesTO> itemsExpensesTOList) {
        this.itemsExpensesTOList = itemsExpensesTOList;
    }

    public ItemsExpensesTO addItemsExpensesTO(ItemsExpensesTO itemsExpensesTO) {
        getItemsExpensesTOList().add(itemsExpensesTO);
        itemsExpensesTO.setExpensesTO(this);
        return itemsExpensesTO;
    }

    public ItemsExpensesTO removeItemsExpensesTO(ItemsExpensesTO itemsExpensesTO) {
        getItemsExpensesTOList().remove(itemsExpensesTO);
        itemsExpensesTO.setExpensesTO(null);
        return itemsExpensesTO;
    }
}
