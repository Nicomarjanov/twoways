package com.twoways.dao;

import java.util.List;

import org.springframework.dao.DataAccessException;

public class LanguaguesDAOImpl extends AbstractDAO  implements LanguagueDAO {
    public List obtenerIdioma() throws Exception {
        List ret= null;
        try {
            ret = getSqlMapClientTemplate().queryForList("obtenerIdioma","");
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
    }
}
