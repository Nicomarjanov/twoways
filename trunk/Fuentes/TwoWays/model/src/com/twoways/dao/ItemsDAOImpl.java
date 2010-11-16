package com.twoways.dao;

import com.twoways.to.ItemsTO;

import java.util.List;

import org.springframework.dao.DataAccessException;

public class ItemsDAOImpl extends AbstractDAO  implements ItemDAO {
   
    public void insertarItem(ItemsTO itemsTO) throws Exception {
        
           getSqlMapClientTemplate().insert("insertItem",itemsTO);
        }
    
    public void actualizarItem(ItemsTO itemsTO) {
        
            getSqlMapClientTemplate().insert("updateRate",itemsTO);    
    }        
        
    public ItemsTO getItemById(String itmId) throws Exception {
       ItemsTO item =  (ItemsTO)getSqlMapClientTemplate().queryForObject("getItemById",itmId);
       return item;
    }
    
    public boolean  deleteItem(ItemsTO item) throws Exception {
       int res =  getSqlMapClientTemplate().delete("deleteItems",item);
       return (res > 0); 
    }

    public List obtenerItem() throws Exception {
        List ret= null;
        try {
            ret = 
            getSqlMapClientTemplate().queryForList("obtenerItems","");
        } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
    }
        
}

