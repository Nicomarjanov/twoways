package com.twoways.dao;

import com.twoways.to.ClientsTO;
import com.twoways.to.RateTypesTO;
import com.twoways.to.RatesTO;
import java.util.List;

public interface RateDAO {
    public List obtenerTarifas() throws Exception;
    public void insertarTarifa(RatesTO ratesTO) throws Exception;
    public void actualizarTarifa(RatesTO ratesTO) throws Exception;   
    public RatesTO getRateById(String ratId) throws Exception;
    public boolean  deleteRate(RatesTO rate)  throws Exception; 
    public List buscarTarifas(String search) throws Exception; 
    public List getRatesByType(RateTypesTO rateTypesTO) throws Exception;
    
}
