package com.twoways.dao;

import com.twoways.to.CotizationsTO;

import com.twoways.to.CurrencyCotizationsTO;

import java.util.List;

public interface CotizationDAO {
    public List buscarCotizaciones(String search) throws Exception;
    public CotizationsTO insertarCotizacion(CotizationsTO cotizationsTO) throws Exception; 
    public boolean eliminarCotizacion(CurrencyCotizationsTO currencyCotizationsTO) throws Exception;
}
