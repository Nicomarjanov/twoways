package com.twoways.dao;

import com.twoways.to.TranslatorsTO;

import java.util.List;

import org.springframework.dao.DataAccessException;

public class TranslatorsDAOImpl extends AbstractDAO  implements TranslatorDAO{
    public List obtenerTraductores() {    
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("obtenerTraductores","");
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;        
    }
    
    public TranslatorsTO getTraByEmpId(String empId)  throws Exception{
       TranslatorsTO translator =  (TranslatorsTO)getSqlMapClientTemplate().queryForObject("getTraByEmpId",empId);
       return translator;
    }
    
    public TranslatorsTO getTraById(String traId)  throws Exception{
       TranslatorsTO translator =  (TranslatorsTO)getSqlMapClientTemplate().queryForObject("getTraById",traId);
       return translator;
    }

    public List buscarTraductores(String search) throws Exception{
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("buscarTraductores",search);
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
        }
        
    public TranslatorsTO insertarTraductor(TranslatorsTO translatorsTO) throws Exception {
        
        Long traId = (Long) getSqlMapClientTemplate().queryForObject("traductores.seq","");
        translatorsTO.setTraId(traId);        
        getSqlMapClientTemplate().insert("insertarTraductor",translatorsTO);            
        return getTraById(String.valueOf(traId)); 
        
    }

    public TranslatorsTO updateTraductor(TranslatorsTO translatorsTO) throws Exception {                    
          
        getSqlMapClientTemplate().update("updateTraductor",translatorsTO);        
        return getTraById(String.valueOf(translatorsTO.getTraId()));     
     }

    public boolean  deleteTraductor(TranslatorsTO translatorsTO) throws Exception {
       int res =  getSqlMapClientTemplate().delete("deleteTraductor",translatorsTO);
       return (res > 0); 
    }        

}
