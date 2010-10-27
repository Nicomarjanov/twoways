package com.twoways.service;

import com.twoways.core.bdl.TwoWaysBDL;
import com.twoways.dao.ClientDAO;

import java.util.List;

public class TW_SystemServiceImpl implements TW_SystemService{
    
    private ClientDAO clientDao;
    
    public TW_SystemServiceImpl() {
     
    }

    public void setClientDao(ClientDAO clientDao) {
        this.clientDao = clientDao;
    }

    public ClientDAO getClientDao() {
        return clientDao;
    }
    
    public List obtenerClientes() throws Exception
    {
    return this.clientDao.obtenerClientes();
    }
    

}
