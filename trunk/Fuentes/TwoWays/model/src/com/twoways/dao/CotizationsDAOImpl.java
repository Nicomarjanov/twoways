package com.twoways.dao;

import com.twoways.to.CotizationsTO;

import java.util.List;

import org.springframework.dao.DataAccessException;

public class CotizationsDAOImpl extends AbstractDAO  implements CotizationDAO {
    public CotizationsDAOImpl() {
    }
    
    public CotizationsTO insertarCotizacion(CotizationsTO cotizationsTO) throws Exception {
        Long cucId = (Long) getSqlMapClientTemplate().queryForObject("currency_cot.seq","");
        cotizationsTO.setCucId(cucId);  
        getSqlMapClientTemplate().insert("insertarCotizacion",cotizationsTO);
        return cotizationsTO;    
    }
    
    public List buscarCotizaciones(String search) throws Exception{
        List ret= null;
        try {
            ret =
            getSqlMapClientTemplate().queryForList("buscarCotizaciones",search);
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
    }  

    public boolean eliminarCotizacion(CotizationsTO cotizationsTO) throws Exception {
        int res =  getSqlMapClientTemplate().delete("deleteCotizacion",cotizationsTO);
        return (res > 0);
    }    
    
}
