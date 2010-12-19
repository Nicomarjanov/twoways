package com.twoways.dao;

import com.twoways.to.ClientsRatesTO;
import com.twoways.to.ClientsTO;

import com.twoways.to.OrdersDocsTO;
import com.twoways.to.OrdersRatesTO;
import com.twoways.to.OrdersTO;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.List;


import javax.naming.InitialContext;
import javax.naming.NamingException;

import javax.sql.DataSource;

import org.apache.commons.fileupload.FileItem;

public class OrdersDAOImpl extends AbstractDAO implements OrdersDAO {
    public OrdersDAOImpl() {
    }
    
    
    private void insertFiles(OrdersTO order) throws IOException {
   
    List<FileItem> files = order.getFiles();
    
    try {
    
    for(Object fileObj: files.toArray()){ 
     Blob doc= null;
     FileItem file=(FileItem)fileObj;
    //statement= con.createStatement(); 
     OrdersDocsTO ordersDocsTO = new OrdersDocsTO();
    
        ordersDocsTO.setOdoName(file.getName().substring(file.getName().lastIndexOf("\\") +1 ));
       
        Long odoId = (Long) getSqlMapClientTemplate().queryForObject("order.seq","");
        ordersDocsTO.setOrdersOrdId(order.getOrdId());
        ordersDocsTO.setOdoId(odoId);
      
     try {
         byte [] b = new byte[(int)file.getSize()];
         
         file.getInputStream().read(b);
         //out.write(b);
          ordersDocsTO.setOdoDoc(b);
         
         } catch (FileNotFoundException e) {
         System.out.println("File Not Found.");
         e.printStackTrace();
         }
         
         getSqlMapClientTemplate().insert("insertOrderDoc" ,ordersDocsTO);
     
    }    
         
    }catch (IOException e1)
    {
         System.out.println("Error Upload The File.");
         e1.printStackTrace();
     }
     
    catch(Exception e){
    
        System.out.println("Error The File.");
        e.printStackTrace();
        
    }
    
    
    
    }
     
    public OrdersTO getOrderById(Long ordId)  throws Exception{
       OrdersTO ordersTO =  (OrdersTO)getSqlMapClientTemplate().queryForObject("getOrderById",ordId);
      // 0rdersTO.setClientsRatesTOList((List<ClientsRatesTO>) getSqlMapClientTemplate().queryForList("getClientRatesByCliId",cliente) );
       ordersTO.setOrdersDocsTOList((List<OrdersDocsTO>) getSqlMapClientTemplate().queryForList("getOrderDocByOrder",ordId) );
      
       return ordersTO;
    }
    
    
    public OrdersDocsTO getOrdersDocById(Long docId)  throws Exception{
      OrdersDocsTO ordersDocsTO =  (OrdersDocsTO)getSqlMapClientTemplate().queryForObject("getOrderDocById",docId);
     
      return ordersDocsTO;
    }
    
    
    public OrdersTO insertarOrder(OrdersTO ordersTO) throws Exception {
        
        Long ordId = (Long) getSqlMapClientTemplate().queryForObject("ordersdoc.seq","");
        ordersTO.setOrdId(ordId); 
       
       
        getSqlMapClientTemplate().insert("insertOrders",ordersTO);
       
        List ordRates = ordersTO.getOrderRatesTOList();
        
        for(Object ordRate: ordRates.toArray() ){
        
             getSqlMapClientTemplate().insert("inseOrdersRates",(OrdersRatesTO)ordRate);
        }
        
        
        insertFiles(ordersTO);
        
        return  getOrderById(ordersTO.getOrdId());
      
       
        
    }
    
}
