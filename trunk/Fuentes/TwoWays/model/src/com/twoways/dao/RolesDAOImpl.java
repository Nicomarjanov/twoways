package com.twoways.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public class RolesDAOImpl extends AbstractDAO  implements RolesDAO{
   
   
    public List obtenerRoles() throws Exception {
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("obtenerRoles","");
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
    }
}
