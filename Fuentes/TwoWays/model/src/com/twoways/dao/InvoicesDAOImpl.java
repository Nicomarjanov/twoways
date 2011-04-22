package com.twoways.dao;

import com.twoways.to.InvoicesTO;

import com.twoways.to.ItemsInvoicesTO;
import com.twoways.to.OrdersRatesTO;
import com.twoways.to.OrdersTO;
import com.twoways.to.PaymentsTO;
import com.twoways.to.ProjAssignPaysTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.util.Map;

import javax.sql.DataSource;

import org.springframework.dao.DataAccessException;

public class InvoicesDAOImpl extends AbstractDAO implements InvoiceDAO {
    public InvoicesDAOImpl() {
    }
      
    public Long insertarFactura(InvoicesTO factura) throws Exception{
        Long invId = (Long) getSqlMapClientTemplate().queryForObject("invoice.seq","");
        factura.setInvId(invId);  
        getSqlMapClientTemplate().insert("insertInvoice",factura);
        
        List itemInvoiceList = factura.getItemsInvoicesTOList();
        if (itemInvoiceList.size() > 0){                
             for(Object itemsInvoice: itemInvoiceList.toArray() ){
                 ItemsInvoicesTO auxItemsInvoicesTO = (ItemsInvoicesTO)itemsInvoice;  
                 auxItemsInvoicesTO.setInvoicesTO(factura);
                 Long itiId = (Long) getSqlMapClientTemplate().queryForObject("iteminvoice.seq","");
                 auxItemsInvoicesTO.setItiId(itiId);  
                 getSqlMapClientTemplate().insert("insertItemsInvoices",auxItemsInvoicesTO);
                 
                 OrdersRatesTO orderRateTO=new OrdersRatesTO();
                 orderRateTO.setOrdersTO(auxItemsInvoicesTO.getOrdersRatesTO().getOrdersTO());
                 orderRateTO.setRatesTO(auxItemsInvoicesTO.getOrdersRatesTO().getRatesTO());
                 getSqlMapClientTemplate().update("updateOrderRatePayDate",orderRateTO);
             }      
         }   
         return invId;
    }
    
    public InvoicesTO getInvoiceById(Long invId) throws Exception {
       InvoicesTO factura =  (InvoicesTO)getSqlMapClientTemplate().queryForObject("getInvoiceById",invId);
       return factura;
    }
    
    public List <InvoicesTO> findInvoices(Map employParameters) throws Exception{
        List results = new ArrayList();
        DataSource ds = this.getDataSource(); 
        Connection con = null;
        Statement stm = null;
        ResultSet rs= null ;
        String query ="select t.inv_id as invId,\n" + 
        "       i.iti_id as itiId,\n" + 
        "       c.cli_name as cliName,\n" + 
        "       o.ord_name as ordName,\n" + 
        "       t.inv_date as invDate,\n" + 
        "       o.ord_proj_id as po,\n" + 
        "       o.ord_job_id as jobId,\n" + 
        "       o.ord_wo_number as wo,\n" + 
        "       o.ord_job_name as jobName,\n" + 
        "       a.acc_name as accName,\n" + 
        "       y.cur_name as curName,\n" + 
        "       t.inv_total as invTotal,\n" + 
        "       t.inv_invoiced as invInvoiced\n" + 
        "from invoices t, items_invoices i, clients c, orders o, accounts a, currency y\n" + 
        "where t.inv_id = i.invoices_inv_id\n" + 
        "and i.orders_rates_orders_ord_id=o.ord_id\n" + 
        "and t.clients_cli_id=c.cli_id\n" + 
        "and t.accounts_acc_id=a.acc_id\n" + 
        "and t.currency_cur_id=y.cur_id";
        
        if (employParameters.get("invNumber") != null && employParameters.get("invNumber").toString().length() > 0){
            query +=" and t.inv_id= #invNumber#";
        }
        if (employParameters.get("listaClientes") != null && employParameters.get("listaClientes").toString().length() > 0){
            query +=" and c.cli_name= '#listaClientes#'";
        }
        if (employParameters.get("ordName") != null && employParameters.get("ordName").toString().length() > 0){
            query +=" and o.ord_name= '#ordName#'";
        }
        for (Iterator i = employParameters.keySet().iterator();i.hasNext();){
            String param = (String)i.next();
            query = query.replaceAll("#"+param+"#",employParameters.get(param).toString());
        }
        try {
            con = ds.getConnection();
            stm = con.createStatement();
           // System.out.println(query);
            rs = stm.executeQuery(query);
            
            while(rs.next()){
                InvoicesTO factura = new InvoicesTO();
                factura.setInvId(rs.getLong("invId"));
                if(rs.getTime("invDate") !=null ){ 
                    java.sql.Timestamp timest = rs.getTimestamp("invDate");
                    factura.setInvDate(timest);
                }
                factura.setInvTotal(Double.parseDouble("invTotal"));
            }
        } catch (SQLException e) {
         e.printStackTrace();
        }finally{
            try {
            rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try{
            stm.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try{
            con.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    return results;
    }
}
