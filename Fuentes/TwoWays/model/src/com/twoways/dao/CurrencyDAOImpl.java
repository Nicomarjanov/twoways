package com.twoways.dao;

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
}
