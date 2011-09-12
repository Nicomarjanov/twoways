package com.twoways.to;

import java.sql.Timestamp;


//(name = "ITEMS_EXPENSES")
//Class(ItemsExpensesTOPK.class)
public class ItemsExpensesTO {
    //
    //(name="EXPENSES_EXP_ID", nullable = false, insertable = false, 
    //
    //(name="ITE_ID", nullable = false)
    private Long iteId;
    //(name="ITE_VALUE")
    private Double iteValue;
    private Timestamp iteDate;
    
    private AccountsTO accountsTO;
    private CurrencyTO currencyTO;
    private ExpensesTO expensesTO;
    private ItemsTO itemsTO;
    private UsersTO usersTO;

    public ItemsExpensesTO() {
    }


     public Long getIteId() {
        return iteId;
    }

    public void setIteId(Long iteId) {
        this.iteId = iteId;
    }

    public AccountsTO getAccountsTO() {
        return accountsTO;
    }

    public void setAccountsTO(AccountsTO accountsTO) {
        this.accountsTO = accountsTO;
    }

    public CurrencyTO getCurrencyTO() {
        return currencyTO;
    }

    public void setCurrencyTO(CurrencyTO currencyTO) {
        this.currencyTO = currencyTO;
    }

    public ExpensesTO getExpensesTO() {
        return expensesTO;
    }

    public void setExpensesTO(ExpensesTO expensesTO) {
        this.expensesTO = expensesTO;
    }

    public ItemsTO getItemsTO() {
        return itemsTO;
    }

    public void setItemsTO(ItemsTO itemsTO) {
        this.itemsTO = itemsTO;
    }

    public void setUsersTO(UsersTO usersTO) {
        this.usersTO = usersTO;
    }

    public UsersTO getUsersTO() {
        return usersTO;
    }

    public void setIteValue(Double iteValue) {
        this.iteValue = iteValue;
    }

    public Double getIteValue() {
        return iteValue;
    }

    public void setIteDate(Timestamp iteDate) {
        this.iteDate = iteDate;
    }

    public Timestamp getIteDate() {
        return iteDate;
    }
}
