package com.twoways.dao;

import com.twoways.to.DocTypes;

import java.sql.SQLException;

import java.util.List;

public interface DocTypesDAO {
    public List<DocTypes> obtenerTipoDocumentos() throws Exception;
}
