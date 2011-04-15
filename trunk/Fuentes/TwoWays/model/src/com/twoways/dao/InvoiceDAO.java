package com.twoways.dao;

import com.twoways.to.InvoicesTO;

import java.util.List;

public interface InvoiceDAO {

    public Long insertarFactura(InvoicesTO factura) throws Exception;
    public InvoicesTO getInvoiceById(Long invId) throws Exception;
}
