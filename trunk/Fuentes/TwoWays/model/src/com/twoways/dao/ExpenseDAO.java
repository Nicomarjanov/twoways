package com.twoways.dao;

import com.twoways.to.ExpensesTO;

import com.twoways.to.ItemsExpensesTO;

import java.util.List;
import java.util.Map;

public interface ExpenseDAO {
    public Long insertarExpense(ExpensesTO expensesTO) throws Exception;
    public Long actualizarExpense(ExpensesTO expensesTO) throws Exception;  
    public boolean deleteExpense(Long expId)  throws Exception; 
    public ExpensesTO getExpenseById(Long expId) throws Exception;    
    public ItemsExpensesTO getItemsExpenseByExpId(ExpensesTO expensesTO) throws Exception;
    public List getItemsExpenseByDate(String mesId,String anioId) throws Exception;
    public List getItemsExpenseList(Long expId) throws Exception;
    public List <ItemsExpensesTO>findExpenses(Map expensesParameters) throws Exception;   
  
}
