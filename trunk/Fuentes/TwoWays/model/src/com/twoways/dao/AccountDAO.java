package com.twoways.dao;

import com.twoways.to.AccountsTO;

import java.util.List;

public interface AccountDAO {
    public AccountsTO insertarAccount(AccountsTO AccountsTO) throws Exception;
    public AccountsTO actualizarAccount(AccountsTO AccountsTO) throws Exception;  
    public List obtenerAccount() throws Exception; 
    public boolean deleteAccount(AccountsTO Account)  throws Exception; 
    public AccountsTO getAccountById(String accId) throws Exception;
    public List buscarAccounts(String search) throws Exception;     
}
