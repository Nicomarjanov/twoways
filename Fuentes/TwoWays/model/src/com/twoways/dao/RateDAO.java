package com.twoways.dao;

import com.twoways.to.RatesTO;
import java.util.List;

public interface RateDAO {
    public List obtenerTarifas() throws Exception;
    public void insertarTarifa(RatesTO ratesTO) throws Exception;
    public void actualizarTarifa(RatesTO ratesTO) throws Exception;    
}
