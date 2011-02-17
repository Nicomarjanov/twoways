package com.twoways.dao;

import com.twoways.to.ItemsTO;

import java.util.List;

public interface ItemDAO {

    public ItemsTO insertarItem(ItemsTO itemsTO) throws Exception;
    public ItemsTO actualizarItem(ItemsTO itemsTO) throws Exception;  
    public List obtenerItem(String itmType) throws Exception; 
    public boolean deleteItem(ItemsTO item)  throws Exception; 
    public ItemsTO getItemById(String itmId) throws Exception;
    public List buscarItems(String search) throws Exception;     
}
