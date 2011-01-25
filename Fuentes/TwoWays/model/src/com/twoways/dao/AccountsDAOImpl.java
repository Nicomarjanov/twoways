package com.twoways.dao;

import com.twoways.to.AccountsTO;

import java.util.List;

import org.springframework.dao.DataAccessException;

public class AccountsDAOImpl extends AbstractDAO  implements AccountDAO {
   
    public AccountsTO insertarAccount(AccountsTO AccountsTO) throws Exception {
           Long accId = (Long) getSqlMapClientTemplate().queryForObject("accounts.seq","");
           AccountsTO.setAccId(accId);  
           getSqlMapClientTemplate().insert("insertAccount",AccountsTO);
           return getAccountById(String.valueOf(accId));           
        }
    
    public AccountsTO actualizarAccount(AccountsTO AccountsTO)throws Exception {
        
        getSqlMapClientTemplate().insert("updateAccount",AccountsTO);   
        return getAccountById(String.valueOf(AccountsTO.getAccId()));
    }        
        
    public AccountsTO getAccountById(String accId) throws Exception {
       AccountsTO Account =  (AccountsTO)getSqlMapClientTemplate().queryForObject("getAccountById",accId);
       return Account;
    }
    
    public boolean  deleteAccount(AccountsTO Account) throws Exception {
       int res =  getSqlMapClientTemplate().delete("deleteAccount",Account);
       return (res > 0); 
    }

    public List obtenerAccount() throws Exception {
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("obtenerAccounts","");
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
    }

    public List buscarAccounts(String search) throws Exception {
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("buscarAccounts",search);
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
        }        
}

