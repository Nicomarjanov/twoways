package com.twoways.dao;

import com.twoways.to.RateTypesTO;

import java.util.List;
import org.springframework.dao.DataAccessException;

public class RateTypesDAOImpl  extends AbstractDAO  implements RateTypesDAO {
   
    public List obtenerTipoTarifas() throws Exception {
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("obtenerTipoTarifas","");
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
    }
    
    
    public List obtenerTipoServicios() throws Exception {
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("obtenerServicios","");
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
    }



}
