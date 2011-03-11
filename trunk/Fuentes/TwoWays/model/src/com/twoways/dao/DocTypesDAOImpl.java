package com.twoways.dao;

import com.twoways.to.DocTypes;

import java.sql.SQLException;

import java.util.List;

public class DocTypesDAOImpl  extends AbstractDAO implements DocTypesDAO {
    public DocTypesDAOImpl() {
    }
    
    
    public List<DocTypes> obtenerTipoDocumentos() throws Exception {
       
         return this.getSqlMapClientTemplate().queryForList("obtenerTiposDocumentos","");
    
    }
}
