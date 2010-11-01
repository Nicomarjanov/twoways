package com.twoways.dao;

import com.twoways.to.ClientsTO;
import com.twoways.to.RatesTO;
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
    
    public void insertarTarifa(RatesTO ratesTO) throws Exception {
        
           getSqlMapClientTemplate().insert("insertRate",ratesTO);
        }
    
    public void actualizarTarifa(RatesTO ratesTO) {
        
            getSqlMapClientTemplate().insert("updateRate",ratesTO);    
    }        
}
