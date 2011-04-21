package com.twoways.dao;

import com.twoways.to.StatesTO;

import java.util.List;

public interface StatesDAO {
    public List<StatesTO> getStatesByType(String type);
}
