package com.twoways.dao;

import com.twoways.to.OrdersDocsTO;
import com.twoways.to.OrdersTO;

import java.io.IOException;

public interface OrdersDAO {
   public OrdersTO getOrderById(Long ordId) throws Exception;
   public OrdersTO insertarOrder(OrdersTO ordersTO) throws Exception;
   public OrdersDocsTO getOrdersDocById(Long docId)  throws Exception;
}
