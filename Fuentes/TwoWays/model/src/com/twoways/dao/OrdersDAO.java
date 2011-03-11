package com.twoways.dao;

import com.twoways.to.OrdersDocsTO;
import com.twoways.to.OrdersTO;

import java.io.IOException;

import java.util.List;
import java.util.Map;

public interface OrdersDAO {
   public OrdersTO getOrderById(Long ordId) throws Exception;
   public OrdersTO insertarOrder(OrdersTO ordersTO) throws Exception;
   public OrdersTO updateOrder(OrdersTO ordersTO) throws Exception;
   public OrdersDocsTO getOrdersDocById(Long docId)  throws Exception;
   public List <OrdersTO> findOrders(Map orderParameters) throws Exception;
    
}
