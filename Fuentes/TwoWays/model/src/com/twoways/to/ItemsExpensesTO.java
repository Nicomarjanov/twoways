package com.twoways.to;


//(name = "ITEMS_EXPENSES")
//Class(ItemsExpensesTOPK.class)
public class ItemsExpensesTO {
    //
    //(name="EXPENSES_EXP_ID", nullable = false, insertable = false, 
    private Long expensesExpId;
    private Long itemsItmId;
    //
    //(name="ITE_ID", nullable = false)
    private Long iteId;
    //(name="ITE_VALUE")
    private Long iteValue;
    private AccountsTO accountsTO;
    private CurrencyTO currencyTO;
    private ExpensesTO expensesTO;
    private ItemsTO itemsTO;

    public ItemsExpensesTO() {
    }


    public Long getExpensesExpId() {
        return expensesExpId;
    }

    public void setExpensesExpId(Long expensesExpId) {
        this.expensesExpId = expensesExpId;
    }

    public Long getItemsItmId() {
        return itemsItmId;
    }

    public void setItemsItmId(Long itemsItmId) {
        this.itemsItmId = itemsItmId;
    }

    public Long getIteId() {
        return iteId;
    }

    public void setIteId(Long iteId) {
        this.iteId = iteId;
    }

    public Long getIteValue() {
        return iteValue;
    }

    public void setIteValue(Long iteValue) {
        this.iteValue = iteValue;
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
}
