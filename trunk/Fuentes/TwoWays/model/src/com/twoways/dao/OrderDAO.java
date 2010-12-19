package com.twoways.dao;

import com.twoways.to.OrdersTO;

import java.io.IOException;

public interface OrderDAO {
   public OrdersTO getOrderById(String ordId) throws Exception;
   public OrdersTO insertarOrder(OrdersTO ordersTO) throws Exception;
}
