package com.twoways.to;



public class ItemsInvoicesTO {
    //
    //(name="ITI_ID", nullable = false)
    private Long itiId;
    //(name="ITI_VALUE")
    private Long itiValue;
    private InvoicesTO invoicesTO;
    private ItemsTO itemsTO;
    private AccountsTO accountsTO;

    public ItemsInvoicesTO() {
    }


    public Long getItiId() {
        return itiId;
    }

    public void setItiId(Long itiId) {
        this.itiId = itiId;
    }

    public Long getItiValue() {
        return itiValue;
    }

    public void setItiValue(Long itiValue) {
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

    public AccountsTO getAccountsTO() {
        return accountsTO;
    }

    public void setAccountsTO(AccountsTO accountsTO) {
        this.accountsTO = accountsTO;
    }
}
