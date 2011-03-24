package com.twoways.dao;

import com.twoways.to.CurrencyTO;

import java.sql.Timestamp;

import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import java.util.Map;

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
    
    
    public Double getCurrencyValue(Timestamp date, Long curId)  throws Exception{
       
       Map params= new HashMap();
       params.put("curId",curId); 
       SimpleDateFormat sdf= new SimpleDateFormat("dd/MM/yyyy HH:mm"); 
       params.put("curDate",sdf.format(date));   
       List resultado = getSqlMapClientTemplate().queryForList("getCurrencyValue",params);
       
       return (resultado.size() > 0)?(Double) (resultado.get(0)):0L;  
      
    }
    

}
