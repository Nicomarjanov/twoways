package com.twoways.dao;


import java.util.List;

import org.springframework.dao.DataAccessException;

public class ServiceDAOImpl extends AbstractDAO implements ServiceDAO{
    public ServiceDAOImpl() {
    }

    public List obtenerServices() {
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("obtenerServices","");
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
    }
}
