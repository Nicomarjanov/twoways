package com.twoways.dao;

import com.twoways.to.ItemsTO;

import java.util.List;

public interface ItemDAO {

    public void insertarItem(ItemsTO itemsTO) throws Exception;
    public void actualizarItem(ItemsTO itemsTO) throws Exception;  
    public List obtenerItem() throws Exception; 
    public boolean deleteItem(ItemsTO item)  throws Exception; 
    public ItemsTO getItemById(String itmId) throws Exception;
}
