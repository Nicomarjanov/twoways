package com.twoways.dao;

import com.twoways.to.CurrencyTO;

import java.sql.Timestamp;

import java.util.List;

public interface CurrencyDAO {
    public List obtenerMonedas() throws Exception;
    public CurrencyTO getCurrencyById(String curId)  throws Exception;
    public Double getCurrencyValue(Timestamp date, Long curId)  throws Exception;

}
