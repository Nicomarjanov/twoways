package com.twoways.dao;

import com.twoways.to.CurrencyTO;

import java.util.List;

import org.springframework.dao.DataAccessException;

public class CurrencyDAOImpl extends AbstractDAO  implements CurrencyDAO{
   
   
    public List obtenerMonedas() throws Exception {
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("obtenerMonedas","");
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
    }
    
    
    public CurrencyTO getCurrencyById(String curId)  throws Exception{
       CurrencyTO currency =  (CurrencyTO)getSqlMapClientTemplate().queryForObject("getCurrencyById",curId);
       
        
       return currency;
    }
}
