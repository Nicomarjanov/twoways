package com.twoways.dao;

import com.twoways.to.EmployeesTO;
import com.twoways.to.InvoicesTO;

import com.twoways.to.ItemsInvoicesTO;

import java.util.List;
import java.util.Map;

public interface InvoiceDAO {

    public Long insertarFactura(InvoicesTO factura) throws Exception;
    public InvoicesTO getInvoiceById(Long invId) throws Exception;
    public List <InvoicesTO> findInvoices(Map invoiceParameters) throws Exception;
    public void actualizarFactura(InvoicesTO factura) throws Exception;
    public List<ItemsInvoicesTO> obtenerItemsFactura(Long invId) throws Exception;
    public List <InvoicesTO> findIncomesByClient(Map invoiceParameters) throws Exception;
    //public List findIncomes(Map invoiceParameters) throws Exception;
    public void eraseInvoice(Long invId)throws Exception;
}
