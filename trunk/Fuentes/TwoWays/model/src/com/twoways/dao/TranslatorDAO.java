package com.twoways.dao;

import com.twoways.to.ClientsTO;
import com.twoways.to.TranslatorsTO;

import java.util.List;

public interface TranslatorDAO {

    public List obtenerTraductores();
    public TranslatorsTO getTraByEmpId(String empId)  throws Exception;
    public TranslatorsTO getTraById(String traId)  throws Exception;    
    public List buscarTraductores(String search) throws Exception;
    public TranslatorsTO insertarTraductor(TranslatorsTO translatorsTO) throws Exception;
    public TranslatorsTO updateTraductor(TranslatorsTO translatorsTO) throws Exception;
    public boolean deleteTraductor(TranslatorsTO translatorsTO)  throws Exception;
    public List obtenerEspecializaciones();
    public List getLangByTradId(Long tradId) throws Exception;
}
