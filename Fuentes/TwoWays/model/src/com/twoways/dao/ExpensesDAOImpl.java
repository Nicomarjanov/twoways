package com.twoways.dao;

import com.twoways.to.ClientResponsableTO;
import com.twoways.to.ClientsRatesTO;
import com.twoways.to.CurrencyTO;
import com.twoways.to.ExpensesTO;

import com.twoways.to.ItemsExpensesTO;

import com.twoways.to.ItemsTO;

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

public class ExpensesDAOImpl extends AbstractDAO  implements ExpenseDAO {
    public ExpensesTO insertarExpense(ExpensesTO expensesTO) throws Exception {

    List<ItemsExpensesTO> oldItmExpenses = (List<ItemsExpensesTO>) getSqlMapClientTemplate().queryForList("getItemsExpensesByExpId",expensesTO); 
    
    Long expId = 0L;
    if(expensesTO.getExpId()!=null){
      expId=expensesTO.getExpId();
     getSqlMapClientTemplate().update("updateExpense",expensesTO); 
    }else{
     expId = (Long) getSqlMapClientTemplate().queryForObject("expense.seq","");
     expensesTO.setExpId(expId);  
     getSqlMapClientTemplate().insert("insertExpense",expensesTO);
    }
    
    List expItems = expensesTO.getItemsExpensesTOList();
    
    if (expItems.size() > 0 && oldItmExpenses.size()>0){         
       for(Object itemsExpensesTO: expItems.toArray() ){
           ItemsExpensesTO newIE = (ItemsExpensesTO)itemsExpensesTO;
           boolean insertar= true;
           for (Object oldItmExpTO: oldItmExpenses.toArray()){
               ItemsExpensesTO oldIE = (ItemsExpensesTO)oldItmExpTO;
               if(oldIE.getIteId().equals(newIE.getIteId())){
                   insertar = false;
                   break;
               }
           }
           if(insertar)
           {   ItemsExpensesTO auxItmExp = (ItemsExpensesTO)itemsExpensesTO;
               Long itmExpId = (Long) getSqlMapClientTemplate().queryForObject("item_expense.seq","");
               auxItmExp.setIteId(itmExpId);
               auxItmExp.setExpensesTO(expensesTO);
               getSqlMapClientTemplate().insert("insertItemsExpenses",auxItmExp);
    
           }else{
               //newIE.getExpensesTO().setExpId(expensesTO.getExpId());
               getSqlMapClientTemplate().insert("updateItemsExpenses",(ItemsExpensesTO)newIE);
           }  
       }
       for (Object oldItmExpTO: oldItmExpenses.toArray()){
           ItemsExpensesTO oldIE = (ItemsExpensesTO)oldItmExpTO;   
           boolean borrar= true;  
           for(Object itemsExpensesTO: expItems.toArray() ){
               ItemsExpensesTO newIE = (ItemsExpensesTO)itemsExpensesTO;
               if(oldIE.getIteId().equals(newIE.getIteId())){
               borrar=false;
               break;
               }
           }
           if(borrar)
           {
               getSqlMapClientTemplate().delete("deleteItemsExpenses",(ItemsExpensesTO)oldIE);
           }
       }
    }else if (expItems.size() > 0){
        for(Object itemsExpensesTO: expItems.toArray() ){
            ItemsExpensesTO auxItmExp = (ItemsExpensesTO)itemsExpensesTO;
            Long itmExpId = (Long) getSqlMapClientTemplate().queryForObject("item_expense.seq","");
            auxItmExp.setIteId(itmExpId);
            auxItmExp.setExpensesTO(expensesTO);
            getSqlMapClientTemplate().insert("insertItemsExpenses",auxItmExp);
        }
    }
    return getExpenseById(expId);           
   }
    
    public ExpensesTO actualizarExpense(ExpensesTO expensesTO) throws Exception {
        
        List<ItemsExpensesTO> oldItmExpenses = (List<ItemsExpensesTO>) getSqlMapClientTemplate().queryForList("getItemsExpensesByExpId",expensesTO); 

        //getSqlMapClientTemplate().update("updateExpense",expensesTO); 
        
        List expItems = expensesTO.getItemsExpensesTOList();
        
        if (expItems.size() > 0){         
            for(Object itemsExpensesTO: expItems.toArray() ){
                ItemsExpensesTO newIE = (ItemsExpensesTO)itemsExpensesTO;
                boolean insertar= true;
                for (Object oldItmExpTO: oldItmExpenses.toArray()){
                    ItemsExpensesTO oldIE = (ItemsExpensesTO)oldItmExpTO;
                    if(oldIE.getIteId().equals(newIE.getIteId())){
                        insertar = false;
                        break;
                    }
                }
                if(insertar)
                {   ItemsExpensesTO auxItmExp = (ItemsExpensesTO)itemsExpensesTO;
                    Long itmExpId = (Long) getSqlMapClientTemplate().queryForObject("item_expense.seq","");
                    auxItmExp.setIteId(itmExpId);
                    auxItmExp.setExpensesTO(expensesTO);
                    getSqlMapClientTemplate().insert("insertItemsExpenses",auxItmExp);

                }else{
                    //newIE.getExpensesTO().setExpId(expensesTO.getExpId());
                    getSqlMapClientTemplate().insert("updateItemsExpenses",(ItemsExpensesTO)newIE);
                }  
            }
            for (Object oldItmExpTO: oldItmExpenses.toArray()){
                ItemsExpensesTO oldIE = (ItemsExpensesTO)oldItmExpTO;   
                boolean borrar= true;  
                for(Object itemsExpensesTO: expItems.toArray() ){
                    ItemsExpensesTO newIE = (ItemsExpensesTO)itemsExpensesTO;
                    if(oldIE.getIteId().equals(newIE.getIteId())){
                    borrar=false;
                    break;
                    }
                }
                if(borrar)
                {
                    getSqlMapClientTemplate().delete("deleteItemsExpenses",(ItemsExpensesTO)oldIE);
                }
            }
        }
        return getExpenseById(expensesTO.getExpId());
    }        
        
    public ExpensesTO getExpenseById(Long expId) throws Exception {
       ExpensesTO gasto =  (ExpensesTO)getSqlMapClientTemplate().queryForObject("getExpenseById",expId);
       return gasto;
    }
    
    public ItemsExpensesTO getItemsExpenseByExpId(Long expId) throws Exception {
       ItemsExpensesTO itemExp =  (ItemsExpensesTO)getSqlMapClientTemplate().queryForObject("getItemsExpenseByExpId",expId);
       return itemExp;
    }
    
    public boolean deleteExpense(String expId)  throws Exception {
       int res =  getSqlMapClientTemplate().update("deleteExpense",Long.parseLong(expId));
       return (res > 0); 
    }
    
    public List getItemsExpenseByDate(String mesId,String anioId) throws Exception{
 
        String fecha = mesId+anioId;
     
        List ret= null;
        try {
            ret = getSqlMapClientTemplate().queryForList("getItemsExpenseByDate",fecha);
            } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
        }
      
    public List <ItemsExpensesTO>findExpenses(Map expensesParameters) throws Exception{
        List results = new ArrayList();
        DataSource ds = this.getDataSource(); 
        Connection con = null;
        Statement stm = null;
        ResultSet rs= null ;
        String query ="select m.itm_id as itmId, m.itm_name as itmName, sum(i.ite_value) as total, i.currency_cur_id as curId, e.exp_date as expDate \n" + 
        " from items_expenses i, expenses e, items m\n" + 
        " where i.expenses_exp_id=e.exp_id\n" + 
        " and i.items_itm_id=m.itm_id";
            
        if (expensesParameters.get("mesId") != null && expensesParameters.get("mesId").toString().length() > 0){
            query +=" and to_char(e.exp_date,'mm')=#mesId#";
        }      
        if (expensesParameters.get("anioId") != null && expensesParameters.get("anioId").toString().length() > 0){
            query +=" and to_char(e.exp_date,'yyyy')=#anioId#";
        }              
        query +=" group by m.itm_id, m.itm_name, i.currency_cur_id , e.exp_date \n"+
                " order by 1,5";
                
        for (Iterator i = expensesParameters.keySet().iterator();i.hasNext();){
            String param = (String)i.next();
            query = query.replaceAll("#"+param+"#",expensesParameters.get(param).toString());
        }
        try {
            con = ds.getConnection();
            stm = con.createStatement();
            System.out.println(query);
            rs = stm.executeQuery(query);

            while(rs.next()){
                ItemsExpensesTO itemGastos = new ItemsExpensesTO();
                ExpensesTO gastos = new ExpensesTO();
                if(rs.getTime("expDate") !=null ){ 
                    java.sql.Timestamp timest = rs.getTimestamp("expDate");
                    gastos.setExpDate(timest);
                }     
                gastos.setExpTotal(rs.getDouble("total"));
                itemGastos.setExpensesTO(gastos);
                
                ItemsTO items = new ItemsTO();
                items.setItmId(rs.getLong("itmId"));
                items.setItmName(rs.getString("itmName"));
                itemGastos.setItemsTO(items);
                
                CurrencyTO moneda = new CurrencyTO();
                moneda.setCurId(rs.getLong("curId"));
                itemGastos.setCurrencyTO(moneda);  
                
                results.add(itemGastos);
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
