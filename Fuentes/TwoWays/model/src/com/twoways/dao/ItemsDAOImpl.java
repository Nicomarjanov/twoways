package com.twoways.dao;

import com.twoways.to.ItemsTO;

import java.util.List;

import org.springframework.dao.DataAccessException;

public class ItemsDAOImpl extends AbstractDAO  implements ItemDAO {
   
    public ItemsTO insertarItem(ItemsTO itemsTO) throws Exception {
           Long itmId = (Long) getSqlMapClientTemplate().queryForObject("items.seq","");
           itemsTO.setItmId(itmId);  
           getSqlMapClientTemplate().insert("insertItem",itemsTO);
           return getItemById(String.valueOf(itmId));           
        }
    
    public ItemsTO actualizarItem(ItemsTO itemsTO)throws Exception {
        
        getSqlMapClientTemplate().insert("updateItem",itemsTO);   
        return getItemById(String.valueOf(itemsTO.getItmId()));
    }        
        
    public ItemsTO getItemById(String itmId) throws Exception {
       ItemsTO item =  (ItemsTO)getSqlMapClientTemplate().queryForObject("getItemById",itmId);
       return item;
    }
    
    public boolean  deleteItem(ItemsTO item) throws Exception {
       int res =  getSqlMapClientTemplate().delete("deleteItem",item);
       return (res > 0); 
    }

    public List obtenerItem(String itmType) throws Exception {
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("obtenerItems",itmType);
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
    }

    public List buscarItems(String search) throws Exception {
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("buscarItems",search);
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
        }        
               
}

