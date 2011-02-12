package com.twoways.dao;

import com.twoways.to.EmployeesTypesTO;
import com.twoways.to.TranslatorsLanguaguesTO;
import com.twoways.to.TranslatorsSpecializationsTO;
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
      // String traId = translator.getTraId().toString();
       translator.setTransSpecializationTOList((List<TranslatorsSpecializationsTO>)getSqlMapClientTemplate().queryForList("getTransSpezialByTransId",translator.getTraId()) );
       return translator;
    }
    
    public TranslatorsTO getTraById(Long traId)  throws Exception{
       TranslatorsTO translator =  (TranslatorsTO)getSqlMapClientTemplate().queryForObject("getTraById",traId);
       //translator.setTransLanguaguesTOList((List<TranslatorsLanguaguesTO>)getSqlMapClientTemplate().queryForList("getTransLangByTransId",traId) );
       translator.setTransSpecializationTOList((List<TranslatorsSpecializationsTO>)getSqlMapClientTemplate().queryForList("getTransSpezialByTransId",traId) );
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
        
        List transLanguagues = translatorsTO.getTransLanguaguesTOList();
        if ( transLanguagues != null && transLanguagues.size() > 0 ){
            for(Object transLanguaguesTO: transLanguagues.toArray() ){
                 Long tlaId = (Long) getSqlMapClientTemplate().queryForObject("trans_lang.seq","");
                 TranslatorsLanguaguesTO auxTrans = (TranslatorsLanguaguesTO)transLanguaguesTO;
                 auxTrans.setTlaId(tlaId);
                 auxTrans.setTranslatorsTraId(traId);
                 getSqlMapClientTemplate().insert("insertTransLanguagues",auxTrans);
            }
        }
    
        List transSpezial = translatorsTO.getTransSpecializationTOList();
        if ( transSpezial != null && transSpezial.size() > 0 ){
            for(Object TransSpecializationTO: transSpezial.toArray() ){
            
                 getSqlMapClientTemplate().insert("insertTransSpecializations",(TranslatorsSpecializationsTO)TransSpecializationTO);
            }
        }        
        return getTraById(traId); 
        
    }

    public TranslatorsTO updateTraductor(TranslatorsTO translatorsTO) throws Exception {                    
        List<TranslatorsLanguaguesTO> oldTransLang = (List<TranslatorsLanguaguesTO>) getSqlMapClientTemplate().queryForList("getOldTransLangByTransId",translatorsTO.getTraId()); 
        List<TranslatorsSpecializationsTO> oldTransSpezial = (List<TranslatorsSpecializationsTO>) getSqlMapClientTemplate().queryForList("getTransSpezialByTransId",translatorsTO.getTraId()); 
        List transLang = translatorsTO.getTransLanguaguesTOList();
        List transSpezial = translatorsTO.getTransSpecializationTOList();
        
        //getSqlMapClientTemplate().update("updateTraductor",translatorsTO);        
        
         //borrar lenguajes viejos
          Long traId=translatorsTO.getTraId();
          for(Object oldTranLang: oldTransLang.toArray() ){
                getSqlMapClientTemplate().delete("deleteTranslatorsLangs",(TranslatorsLanguaguesTO)oldTranLang);
          }        
         // insertar lenguajes nuevos 
         
          for(Object transLangTO: transLang.toArray() ){
              Long tlaId = (Long) getSqlMapClientTemplate().queryForObject("trans_lang.seq","");
              TranslatorsLanguaguesTO auxTrans = (TranslatorsLanguaguesTO)transLangTO;
              auxTrans.setTlaId(tlaId);
              auxTrans.setTranslatorsTraId(traId);
              getSqlMapClientTemplate().insert("insertTransLanguagues",auxTrans);
          }
         //borrar especializaciones viejos
          for(Object oldTranSpezial: oldTransSpezial.toArray() ){
                getSqlMapClientTemplate().delete("deleteTranslatorsSpezials",(TranslatorsSpecializationsTO)oldTranSpezial);
          }        
         // insertar especializaciones nuevos 
         
          for(Object transSpezialTO: transSpezial.toArray() ){
              getSqlMapClientTemplate().insert("insertTransSpecializations",(TranslatorsSpecializationsTO)transSpezialTO);
          }        
        return getTraById(translatorsTO.getTraId());     
     }

    public boolean  deleteTraductor(TranslatorsTO translatorsTO) throws Exception {
       int res =  getSqlMapClientTemplate().delete("deleteTraductor",translatorsTO);
       return (res > 0); 
    }        
    
    public List obtenerEspecializaciones() {    
        List ret= null;
        try {
            ret = getSqlMapClientTemplate().queryForList("obtenerEspecializaciones","");
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;        
    }
    public List getLangByTradId(Long tradId) throws Exception{
        List ret= null;
        try {
            ret = getSqlMapClientTemplate().queryForList("getTransLangByTransId",tradId);
            } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
        }
        
   public Long obtenerTraductorByEmpId(String empId) throws Exception{
       Long ret = null;
       try {
           ret = (Long) getSqlMapClientTemplate().queryForObject("obtenerTraductorByEmpId",empId);
            } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
        }
   
    
    public List obtenerEspecializacionesByTraId(Long traId) throws Exception{    
        List ret= null;
        try {
            ret = getSqlMapClientTemplate().queryForList("obtenerEspecializacionesByTraId",traId);
        } catch (DataAccessException dae) {

            dae.printStackTrace();
        }
        return ret;        
    }
}
