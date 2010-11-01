package com.twoways.dao;

import com.twoways.to.CurrencyTO;

import java.util.List;

public interface CurrencyDAO {
    public List obtenerMonedas() throws Exception;
    public CurrencyTO getCurrencyById(String curId)  throws Exception;
}
