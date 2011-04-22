package com.twoways.dao;

import com.twoways.to.EmployeesTO;
import com.twoways.to.InvoicesTO;

import java.util.List;
import java.util.Map;

public interface InvoiceDAO {

    public Long insertarFactura(InvoicesTO factura) throws Exception;
    public InvoicesTO getInvoiceById(Long invId) throws Exception;
    public List <InvoicesTO> findInvoices(Map employParameters) throws Exception;
}
