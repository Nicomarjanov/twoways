package com.twoways.dao;

import com.twoways.to.ClientsTO;
import com.twoways.to.RateTypesTO;
import com.twoways.to.RatesTO;

import java.util.Collections;
import java.util.List;
import org.springframework.dao.DataAccessException;

public class RatesDAOImpl extends AbstractDAO  implements RateDAO {
   
    public List obtenerTarifas() throws Exception {
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("obtenerTarifas","");
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
    }
    
    public List getRatesByType(RateTypesTO rateTypesTO) throws Exception {
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("getRatesByType",rateTypesTO);
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
    }
    
    public void insertarTarifa(RatesTO ratesTO) throws Exception {
        
           getSqlMapClientTemplate().insert("insertRate",ratesTO);
        }
    
    public void actualizarTarifa(RatesTO ratesTO) {
        
            getSqlMapClientTemplate().insert("updateRate",ratesTO);    
    }        
        
    public RatesTO getRateById(String ratId) throws Exception {
       RatesTO tarifa =  (RatesTO)getSqlMapClientTemplate().queryForObject("getRateById",ratId);
       return tarifa;
    }
    
    public boolean  deleteRate(RatesTO rate) throws Exception {
       int res =  getSqlMapClientTemplate().delete("deleteRates",rate);
       return (res > 0); 
    }

    public List buscarTarifas(String search) throws Exception {
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("buscarTarifas",search);
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
        }
        
}
