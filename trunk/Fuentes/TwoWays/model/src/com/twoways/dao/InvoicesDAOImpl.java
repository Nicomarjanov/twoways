package com.twoways.dao;

import com.twoways.to.InvoicesTO;

import com.twoways.to.ItemsInvoicesTO;
import com.twoways.to.PaymentsTO;
import com.twoways.to.ProjAssignPaysTO;

import java.util.List;

import org.springframework.dao.DataAccessException;

public class InvoicesDAOImpl extends AbstractDAO implements InvoiceDAO {
    public InvoicesDAOImpl() {
    }
    

    
    public Long insertarFactura(InvoicesTO factura) throws Exception{
        Long invId = (Long) getSqlMapClientTemplate().queryForObject("invoice.seq","");
        factura.setInvId(invId);  
        getSqlMapClientTemplate().insert("insertInvoice",factura);
        
        List itemInvoiceList = factura.getItemsInvoicesTOList();
        if (itemInvoiceList.size() > 0){                
             for(Object itemsInvoice: itemInvoiceList.toArray() ){
                 ItemsInvoicesTO auxItemsInvoicesTO = (ItemsInvoicesTO)itemsInvoice;  
                 auxItemsInvoicesTO.setInvoicesTO(factura);
                 getSqlMapClientTemplate().insert("insertItemsInvoices",auxItemsInvoicesTO);
             }      
         }   
         return invId;
    }
    
    public InvoicesTO getInvoiceById(Long invId) throws Exception {
       InvoicesTO factura =  (InvoicesTO)getSqlMapClientTemplate().queryForObject("getInvoiceById",invId);
       return factura;
    }
    
}
