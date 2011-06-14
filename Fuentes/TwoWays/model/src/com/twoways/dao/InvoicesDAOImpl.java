package com.twoways.dao;

import com.twoways.to.AccountsTO;
import com.twoways.to.ClientsTO;
import com.twoways.to.CurrencyTO;
import com.twoways.to.InvoicesTO;

import com.twoways.to.ItemsInvoicesTO;
import com.twoways.to.OrdersRatesTO;
import com.twoways.to.OrdersTO;
import com.twoways.to.PaymentsTO;
import com.twoways.to.ProjAssignPaysTO;

import com.twoways.to.UsersTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.HashMap;
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
    
    public List <InvoicesTO> findInvoices(Map invoiceParameters) throws Exception{
        List results = new ArrayList();
        DataSource ds = this.getDataSource(); 
        Connection con = null;
        Statement stm = null;
        ResultSet rs= null ;
        String query ="select t.inv_id as invId, \n" + 
        "               c.cli_id as cliId, \n" +        
        "               c.cli_name as cliName, \n" + 
        "               t.inv_date as invDate, \n" + 
        "               a.acc_name as accName, \n" + 
        "               y.cur_name as curName, \n" + 
        "               t.inv_total as invTotal, \n" + 
        "               t.inv_invoiced as invInvoiced,\n" + 
        "               u.usr_id as usrId,\n" +
        "               a.acc_id as accId,\n" +
        "               y.cur_symbol as curSymbol\n" +
        "        from invoices t,  clients c, accounts a, currency y, users u\n" + 
        "        where t.clients_cli_id=c.cli_id \n" + 
        "        and t.accounts_acc_id=a.acc_id \n" + 
        "        and t.currency_cur_id=y.cur_id\n" + 
        "        and t.users_usr_id=u.usr_id";
        
        if (invoiceParameters.get("invNumber") != null && invoiceParameters.get("invNumber").toString().length() > 0){
            query +=" and t.inv_id= #invNumber#";
        }
        if (invoiceParameters.get("cliId") != null && invoiceParameters.get("cliId").toString().length() > 0){
            query +=" and c.cli_id= '#cliId#'";
        }
        if(invoiceParameters.get("invDate") != null && invoiceParameters.get("invDate").toString().length() > 0){      
            query +=" and t.inv_date "+ invoiceParameters.get("invDateOpt").toString()+"  to_date('#invDate#','dd/mm/yyyy')";
        }


        for (Iterator i = invoiceParameters.keySet().iterator();i.hasNext();){
            String param = (String)i.next();
            query = query.replaceAll("#"+param+"#",invoiceParameters.get(param).toString());
        }
        try {
            con = ds.getConnection();
            stm = con.createStatement();
            //System.out.println(query);
            rs = stm.executeQuery(query);
            
            while(rs.next()){
 
                InvoicesTO factura = new InvoicesTO();
                factura.setInvId(rs.getLong("invId"));
                
                if(rs.getTime("invDate") !=null ){ 
                    java.sql.Timestamp timest = rs.getTimestamp("invDate");
                    factura.setInvDate(timest);
                }
                factura.setInvTotal(rs.getDouble("invTotal"));
                factura.setInvInvoiced(rs.getString("invInvoiced")); 
                
                ClientsTO cliente = new ClientsTO();
                cliente.setCliName(rs.getString("cliName"));
                cliente.setCliId(Long.parseLong(rs.getString("cliId")));
                factura.setClientsTO(cliente);
                
                AccountsTO cuenta = new AccountsTO();
                cuenta.setAccName(rs.getString("accName"));
                cuenta.setAccId(rs.getLong("accId"));
                factura.setAccountsTO(cuenta);
                
                CurrencyTO moneda = new CurrencyTO();
                moneda.setCurName(rs.getString("curName"));
                moneda.setCurSymbol(rs.getString("curSymbol"));
                factura.setCurrencyTO(moneda);            
                
                UsersTO usuario = new UsersTO();
                usuario.setUsrId(rs.getString("usrId"));
                factura.setUsersTO(usuario);
                
                results.add(factura);
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
    
    public void actualizarFactura(InvoicesTO factura) throws Exception{
        try{
            getSqlMapClientTemplate().insert("updateInvoice",factura.getInvId());
        }catch (DataAccessException dae) {

           dae.printStackTrace();
        }
    }
    
    public List obtenerItemsFactura(Long invId) throws Exception {
       List itemFactura =  getSqlMapClientTemplate().queryForList("getItemsInvoiceByInvId",invId);
       return itemFactura;
    }
    
    public List <InvoicesTO> findIncomesByClient(Map invoiceParameters) throws Exception{
        List results = new ArrayList();
        DataSource ds = this.getDataSource(); 
        Connection con = null;
        Statement stm = null;
        ResultSet rs= null ;
        String query ="select c.cli_id as cliId," +
        "                     c.cli_name as cliName," +
        "                     sum(i.inv_total) as total," +
        "                     i.currency_cur_id as curId, " +
        "                     i.inv_date as invDate \n" + 
                        "from invoices i, clients c \n" + 
                        "where i.clients_cli_id=c.cli_id \n";
        
        if (invoiceParameters.get("cliId") != null && invoiceParameters.get("cliId").toString().length() > 0){
            query +=" and c.cli_Id= #cliId#";
        }        
        if (invoiceParameters.get("mesId") != null && invoiceParameters.get("mesId").toString().length() > 0){
            query +=" and to_char(i.inv_date,'mmyyyy')=#mesId##anioId#";
        }      
        if (invoiceParameters.get("anioId") != null && invoiceParameters.get("anioId").toString().length() > 0){
            query +=" and to_char(i.inv_date,'yyyy')=#anioId#";
        }              
        query +=" group by c.cli_id,c.cli_name, i.currency_cur_id,i.inv_date\n" + 
                " order by 1,5";
                
        for (Iterator i = invoiceParameters.keySet().iterator();i.hasNext();){
            String param = (String)i.next();
            query = query.replaceAll("#"+param+"#",invoiceParameters.get(param).toString());
        }
        try {
            con = ds.getConnection();
            stm = con.createStatement();
            System.out.println(query);
            rs = stm.executeQuery(query);

            while(rs.next()){
            
                InvoicesTO factura = new InvoicesTO();
                if(rs.getTime("invDate") !=null ){ 
                    java.sql.Timestamp timest = rs.getTimestamp("invDate");
                    factura.setInvDate(timest);
                }
                factura.setInvTotal(rs.getDouble("total"));  
                
                ClientsTO cliente = new ClientsTO();
                cliente.setCliName(rs.getString("cliName"));
                cliente.setCliId(Long.parseLong(rs.getString("cliId")));
                factura.setClientsTO(cliente);

                CurrencyTO moneda = new CurrencyTO();
                moneda.setCurId(Long.parseLong(rs.getString("curId")));
                factura.setCurrencyTO(moneda);                 
                
                results.add(factura);
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
    
    public List findIncomes(Map invoiceParameters) throws Exception{

        List salida = new ArrayList();
        DataSource ds = this.getDataSource(); 
        Connection con = null;
        Statement stm = null;
        ResultSet rs= null ;
        String query ="select cur_id,cur_name,enero,febrero,marzo,abril,mayo,junio,julio,agosto,septiembre,octubre,noviembre,diciembre,\n" + 
        "enero+febrero+marzo+abril+mayo+junio+julio+agosto+septiembre+octubre+noviembre+diciembre as total\n" + 
        "from (select y.cur_id, y.cur_name,\n" + 
        "        decode(sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '01' then\n" + 
        "              i.inv_total\n" + 
        "           end),'',0,sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '01' then\n" + 
        "              i.inv_total\n" + 
        "           end)) enero,\n" + 
        "        decode(sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '02' then\n" + 
        "              i.inv_total\n" + 
        "           end),'',0,sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '02' then\n" + 
        "              i.inv_total\n" + 
        "           end)) febrero,\n" + 
        "         decode(sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '03' then\n" + 
        "              i.inv_total\n" + 
        "           end),'',0,sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '03' then\n" + 
        "              i.inv_total\n" + 
        "           end)) marzo,\n" + 
        "           decode(sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '04' then\n" + 
        "              i.inv_total\n" + 
        "           end),'',0,sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '04' then\n" + 
        "              i.inv_total\n" + 
        "           end)) abril,\n" + 
        "           decode(sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '05' then\n" + 
        "              i.inv_total\n" + 
        "           end),'',0,sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '05' then\n" + 
        "              i.inv_total\n" + 
        "           end)) mayo,\n" + 
        "           decode(sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '06' then\n" + 
        "              i.inv_total\n" + 
        "           end),'',0,sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '06' then\n" + 
        "              i.inv_total\n" + 
        "           end)) junio,\n" + 
        "           decode(sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '07' then\n" + 
        "              i.inv_total\n" + 
        "           end),'',0,sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '07' then\n" + 
        "              i.inv_total\n" + 
        "           end)) julio,\n" + 
        "           decode(sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '08' then\n" + 
        "              i.inv_total\n" + 
        "           end),'',0,sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '08' then\n" + 
        "              i.inv_total\n" + 
        "           end)) agosto,\n" + 
        "          decode(sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '09' then\n" + 
        "              i.inv_total\n" + 
        "           end),'',0,sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '09' then\n" + 
        "              i.inv_total\n" + 
        "           end)) septiembre,\n" + 
        "           decode(sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '10' then\n" + 
        "              i.inv_total\n" + 
        "           end),'',0,sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '10' then\n" + 
        "              i.inv_total\n" + 
        "           end)) octubre,\n" + 
        "            decode(sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '11' then\n" + 
        "              i.inv_total\n" + 
        "           end),'',0,sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '11' then\n" + 
        "              i.inv_total\n" + 
        "           end)) noviembre,\n" + 
        "           decode(sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '12' then\n" + 
        "              i.inv_total\n" + 
        "           end),'',0,sum(case\n" + 
        "             when to_char(i.inv_date, 'mm') = '12' then\n" + 
        "              i.inv_total\n" + 
        "           end)) diciembre\n" + 
        "  from invoices i, currency y \n" +
        " where i.currency_cur_id = y.cur_id" ;        
      
        if (invoiceParameters.get("mesId") != null && invoiceParameters.get("mesId").toString().length() > 0){
            query +=" and to_char(i.inv_date,'mmyyyy')=#mesId##anioId#";
        }      
        if (invoiceParameters.get("anioId") != null && invoiceParameters.get("anioId").toString().length() > 0){
            query +=" and to_char(i.inv_date,'yyyy')=#anioId#";
        }              
        query +="    group by y.cur_id,y.cur_name)";
                
        for (Iterator i = invoiceParameters.keySet().iterator();i.hasNext();){
            String param = (String)i.next();
            query = query.replaceAll("#"+param+"#",invoiceParameters.get(param).toString());
        }
        try {
            con = ds.getConnection();
            stm = con.createStatement();
            System.out.println(query);
            rs = stm.executeQuery(query);
            while(rs.next()){
                List results = new ArrayList();
                results.add(rs.getString("cur_id"));            
                results.add(rs.getString("cur_name"));   
                results.add(rs.getString("enero")); 
                results.add(rs.getString("febrero")); 
                results.add(rs.getString("marzo")); 
                results.add(rs.getString("abril"));
                results.add(rs.getString("mayo")); 
                results.add(rs.getString("junio"));
                results.add(rs.getString("julio")); 
                results.add(rs.getString("agosto"));
                results.add(rs.getString("septiembre")); 
                results.add(rs.getString("octubre"));
                results.add(rs.getString("noviembre")); 
                results.add(rs.getString("diciembre"));                
                results.add(rs.getString("total")); 
                salida.add(results);
                
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
            return salida;
        
    }
    
    
}
