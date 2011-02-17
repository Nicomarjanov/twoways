package com.twoways.dao;

import com.twoways.to.ExpensesTO;

import com.twoways.to.ItemsExpensesTO;

import java.util.List;

public interface ExpenseDAO {
    public ExpensesTO insertarExpense(ExpensesTO expensesTO) throws Exception;
    public ExpensesTO actualizarExpense(ExpensesTO expensesTO) throws Exception;  
    public boolean deleteExpense(ExpensesTO gasto)  throws Exception; 
    public ExpensesTO getExpenseById(Long expId) throws Exception;    
    public ItemsExpensesTO getItemsExpenseByExpId(Long expId) throws Exception;
}
