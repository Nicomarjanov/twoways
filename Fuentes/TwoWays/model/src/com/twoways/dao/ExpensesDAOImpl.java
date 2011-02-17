package com.twoways.dao;

import com.twoways.to.ClientResponsableTO;
import com.twoways.to.ExpensesTO;

import com.twoways.to.ItemsExpensesTO;

import java.util.List;

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
        
        getSqlMapClientTemplate().update("updateExpense",expensesTO);   
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
    
    public boolean deleteExpense(ExpensesTO gasto)  throws Exception {
       int res =  getSqlMapClientTemplate().delete("deleteExpense",gasto);
       return (res > 0); 
    }


}
