package com.twoways.dao;

import com.twoways.to.ClientResponsableTO;
import com.twoways.to.ClientsRatesTO;
import com.twoways.to.ExpensesTO;

import com.twoways.to.ItemsExpensesTO;

import java.util.HashMap;
import java.util.List;

import java.util.Map;

import org.springframework.dao.DataAccessException;

public class ExpensesDAOImpl extends AbstractDAO  implements ExpenseDAO {
    public ExpensesTO insertarExpense(ExpensesTO expensesTO) throws Exception {
           Long expId = (Long) getSqlMapClientTemplate().queryForObject("expense.seq","");
           expensesTO.setExpId(expId);  
           getSqlMapClientTemplate().insert("insertExpense",expensesTO);
           
           List expItems = expensesTO.getItemsExpensesTOList();
           if (expItems.size() > 0){                
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
        
        List<ItemsExpensesTO> oldItmExpenses = (List<ItemsExpensesTO>) getSqlMapClientTemplate().queryForList("getItemsExpensesByItmId",expensesTO); 

        getSqlMapClientTemplate().update("updateExpense",expensesTO); 
        
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
    
    public List getItemsExpenseByDate(String itmFecha) throws Exception{
            
        List ret= null;
        try {
            ret = getSqlMapClientTemplate().queryForList("getItemsExpenseByDate",itmFecha);
            } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
        }
        

}
