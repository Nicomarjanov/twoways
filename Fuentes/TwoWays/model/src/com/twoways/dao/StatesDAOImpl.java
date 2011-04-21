package com.twoways.dao;

import com.twoways.to.StatesTO;

import java.util.List;

import org.springframework.dao.DataAccessException;

public class StatesDAOImpl extends AbstractDAO implements StatesDAO {
    public StatesDAOImpl() {
    }
    
    public List<StatesTO> getStatesByType(String type) {
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("getStatesByType",type);
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
    }
}
