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

    public Long insertarExpense(ExpensesTO expensesTO) throws Exception {

    List<ItemsExpensesTO> oldItmExpenses = (List<ItemsExpensesTO>) getSqlMapClientTemplate().queryForList("getItemsExpensesByExpId",expensesTO); 
    
    List expItems = expensesTO.getItemsExpensesTOList();
    Long expId = 0L;    

    if (expItems.size() > 0 && oldItmExpenses.size()>0){  

        if(expensesTO.getExpId()!=null){
            expId=expensesTO.getExpId();
            getSqlMapClientTemplate().update("updateExpense",expensesTO); 
        }else{
            expId = (Long) getSqlMapClientTemplate().queryForObject("expense.seq","");
            expensesTO.setExpId(expId);  
            getSqlMapClientTemplate().insert("insertExpense",expensesTO);
        }
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
        expId = (Long) getSqlMapClientTemplate().queryForObject("expense.seq","");
        expensesTO.setExpId(expId);  
        getSqlMapClientTemplate().insert("insertExpense",expensesTO);
        for(Object itemsExpensesTO: expItems.toArray() ){
            ItemsExpensesTO auxItmExp = (ItemsExpensesTO)itemsExpensesTO;
            Long itmExpId = (Long) getSqlMapClientTemplate().queryForObject("item_expense.seq","");
            auxItmExp.setIteId(itmExpId);
            auxItmExp.setExpensesTO(expensesTO);
            getSqlMapClientTemplate().insert("insertItemsExpenses",auxItmExp);
        }
    }
    return expId;           
   }
    
    public Long actualizarExpense(ExpensesTO expensesTO) throws Exception {
             
        List<ItemsExpensesTO> oldItmExpenses = (List<ItemsExpensesTO>) getSqlMapClientTemplate().queryForList("getItemsExpensesByExpId",expensesTO); 

        //getSqlMapClientTemplate().update("updateExpense",expensesTO); 
        
        List expItems = expensesTO.getItemsExpensesTOList();
        
        if (expItems != null){         
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
        return expensesTO.getExpId();
    }        
        
    public ExpensesTO getExpenseById(Long expId) throws Exception {
       ExpensesTO gasto =  (ExpensesTO)getSqlMapClientTemplate().queryForObject("getExpenseById",expId);
       return gasto;
    }
    
    public ItemsExpensesTO getItemsExpenseByExpId(ExpensesTO expensesTO) throws Exception {
       ItemsExpensesTO itemExp =  (ItemsExpensesTO)getSqlMapClientTemplate().queryForObject("getItemsExpensesByExpId",expensesTO);
       return itemExp;
    }
    
    public boolean deleteExpense(Long expId)  throws Exception {
       getSqlMapClientTemplate().delete("deleteAllItemsExpenses",expId);
       int res =  getSqlMapClientTemplate().delete("deleteExpense",expId);
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
    
    public List getItemsExpenseList(Long expId) throws Exception{
     
        List ret= null;
        try {
            ret = getSqlMapClientTemplate().queryForList("getItemsExpenseList",expId);
            } catch (DataAccessException dae) {

           dae.printStackTrace();
        }
        return ret;
        }
        
    public List <ItemsExpensesTO>findExpenses(Map expensesParameters, Map itemsParameters) throws Exception{
        List results = new ArrayList();
        DataSource ds = this.getDataSource(); 
        Connection con = null;
        Statement stm = null;
        ResultSet rs= null ;
        String query ="select m.itm_id as itmId, m.itm_name as itmName, sum(i.ite_value) as total, i.currency_cur_id as curId, e.exp_date as expDate \n" + 
        " from items_expenses i, expenses e, items m\n" + 
        " where i.expenses_exp_id=e.exp_id\n" + 
        " and i.items_itm_id=m.itm_id \n" +
        " and m.itm_type = 'Egresos' ";
            
        if (expensesParameters.get("mesId") != null && expensesParameters.get("mesId").toString().length() > 0){
            query +=" and to_char(e.exp_date,'mm')=#mesId#";
        }      
        if (expensesParameters.get("anioId") != null && expensesParameters.get("anioId").toString().length() > 0){
            query +=" and to_char(e.exp_date,'yyyy')=#anioId#";
        }    

        //Quitar de la consulta los items de extracciones,cambios de divisas y transferencias
        if (itemsParameters != null && itemsParameters.size()>0){
            if (itemsParameters.get("Extracciones") != null && itemsParameters.get("Extracciones").toString().length() > 0){
                query +=" and i.items_itm_id not in (64,65)";
            }                       
            if (itemsParameters.get("Cambio") != null && itemsParameters.get("Cambio").toString().length() > 0){
                query +=" and i.items_itm_id=104";
            }               
            if (itemsParameters.get("Transferencia") != null && itemsParameters.get("Transferencia").toString().length() > 0){
                query +=" and i.items_itm_id =107";
            }               
            if (itemsParameters.get("Sueldo") != null && itemsParameters.get("Sueldo").toString().length() > 0){
                query +=" and i.items_itm_id <> 9999";
            }                    
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

    public List findIncomes(Map invoiceParameters, String itemType, Map itemsParameters) throws Exception{

        List salida = new ArrayList();
        DataSource ds = this.getDataSource(); 
        Connection con = null;
        Statement stm = null;
        ResultSet rs= null ;
        String query ="select cur_id,cur_name,enero,febrero,marzo,abril,mayo,junio,julio,agosto,septiembre,octubre,noviembre,diciembre, \n" + 
        "        enero+febrero+marzo+abril+mayo+junio+julio+agosto+septiembre+octubre+noviembre+diciembre as total \n" + 
        "        from (select y.cur_id, y.cur_name, \n" + 
        "                decode(sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '01' then \n" + 
        "                      i.ite_value \n" + 
        "                   end),'',0,sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '01' then \n" + 
        "                      i.ite_value \n" + 
        "                   end)) enero, \n" + 
        "                decode(sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '02' then \n" + 
        "                      i.ite_value \n" + 
        "                   end),'',0,sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '02' then \n" + 
        "                      i.ite_value \n" + 
        "                   end)) febrero, \n" + 
        "                 decode(sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '03' then \n" + 
        "                      i.ite_value \n" + 
        "                   end),'',0,sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '03' then \n" + 
        "                      i.ite_value \n" + 
        "                   end)) marzo, \n" + 
        "                   decode(sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '04' then \n" + 
        "                      i.ite_value \n" + 
        "                   end),'',0,sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '04' then \n" + 
        "                      i.ite_value \n" + 
        "                   end)) abril, \n" + 
        "                   decode(sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '05' then \n" + 
        "                      i.ite_value \n" + 
        "                   end),'',0,sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '05' then \n" + 
        "                      i.ite_value \n" + 
        "                   end)) mayo, \n" + 
        "                   decode(sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '06' then \n" + 
        "                      i.ite_value \n" + 
        "                   end),'',0,sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '06' then \n" + 
        "                      i.ite_value \n" + 
        "                   end)) junio, \n" + 
        "                   decode(sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '07' then \n" + 
        "                      i.ite_value \n" + 
        "                   end),'',0,sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '07' then \n" + 
        "                      i.ite_value \n" + 
        "                   end)) julio, \n" + 
        "                   decode(sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '08' then \n" + 
        "                      i.ite_value \n" + 
        "                   end),'',0,sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '08' then \n" + 
        "                      i.ite_value \n" + 
        "                   end)) agosto, \n" + 
        "                  decode(sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '09' then \n" + 
        "                      i.ite_value \n" + 
        "                   end),'',0,sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '09' then \n" + 
        "                      i.ite_value \n" + 
        "                   end)) septiembre, \n" + 
        "                   decode(sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '10' then \n" + 
        "                      i.ite_value \n" + 
        "                   end),'',0,sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '10' then \n" + 
        "                      i.ite_value \n" + 
        "                   end)) octubre, \n" + 
        "                    decode(sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '11' then \n" + 
        "                      i.ite_value \n" + 
        "                   end),'',0,sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '11' then \n" + 
        "                      i.ite_value \n" + 
        "                   end)) noviembre, \n" + 
        "                   decode(sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '12' then \n" + 
        "                      i.ite_value \n" + 
        "                   end),'',0,sum(case \n" + 
        "                     when to_char(i.ite_date, 'mm') = '12' then \n" + 
        "                      i.ite_value \n" + 
        "                   end)) diciembre \n" + 
        "          from items_expenses i, currency y, items s\n" + 
        "         where i.currency_cur_id = y.cur_id\n" + 
        "         and i.items_itm_id = s.itm_id\n" + 
        "         and s.itm_type='"+itemType+"'";        
      
        if (invoiceParameters.get("mesId") != null && invoiceParameters.get("mesId").toString().length() > 0){
            query +=" and to_char(i.ite_date,'mmyyyy')=#mesId##anioId#";
        }      
        if (invoiceParameters.get("anioId") != null && invoiceParameters.get("anioId").toString().length() > 0){
            query +=" and to_char(i.ite_date,'yyyy')=#anioId#";
        }    
        //Quitar de la consulta los items de extracciones, cambios de divisas y transferencias
        if (itemsParameters != null && itemsParameters.size()>0){
            if (itemsParameters.get("Extracciones") != null && itemsParameters.get("Extracciones").toString().length() > 0){
                query +=" and i.items_itm_id not in (64,65)";
            }                       
            if (itemsParameters.get("Cambio") != null && itemsParameters.get("Cambio").toString().length() > 0){
                query +=" and i.items_itm_id not in (104,105)";
            }               
            if (itemsParameters.get("Transferencia") != null && itemsParameters.get("Transferencia").toString().length() > 0){
                query +=" and i.items_itm_id not in (107,108)";
            }          
            
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
    
    
    public void insertarExpenseExtra(ExpensesTO expensesTO) throws Exception {

    List expItems = expensesTO.getItemsExpensesTOList();
    Long expId = 0L;    

    if (expItems.size() > 0 ){  

        if(expensesTO.getExpId()!=null){
            expId=expensesTO.getExpId();
            getSqlMapClientTemplate().update("updateExpense",expensesTO); 
        }else{
            expId = (Long) getSqlMapClientTemplate().queryForObject("expense.seq","");
            expensesTO.setExpId(expId);  
            getSqlMapClientTemplate().insert("insertExpense",expensesTO);
        }
       for(Object itemsExpensesTO: expItems.toArray() ){
               ItemsExpensesTO auxItmExp = (ItemsExpensesTO)itemsExpensesTO;
               Long itmExpId = (Long) getSqlMapClientTemplate().queryForObject("item_expense.seq","");
               auxItmExp.setIteId(itmExpId);
               auxItmExp.setExpensesTO(expensesTO);
                              
               getSqlMapClientTemplate().insert("insertItemsExpenses",auxItmExp);
 
       }
    }
              
    }

    public void eraseInvoiceExpense(Long invId) throws Exception {
        getSqlMapClientTemplate().delete("eraseInvoiceExpense",invId);
    }
    
    public void erasePaymentExpense(Long payId) throws Exception{
        getSqlMapClientTemplate().delete("erasePaymentExpense",payId);
    }
    
    public List findFutureExpenses(Map expensesParameters){
        List salida = new ArrayList();
        DataSource ds = this.getDataSource(); 
        Connection con = null;
        Statement stm = null;
        ResultSet rs= null ;
        String query ="select i.currency_cur_id as curId, e.exp_date as expDate, sum(i.ite_value) as total  \n" + 
        "         from items_expenses i, expenses e, items m \n" + 
        "         where i.expenses_exp_id=e.exp_id \n" + 
        "         and i.items_itm_id=m.itm_id \n" + 
        "         and m.itm_type = 'Egresos'\n" + 
        "         and i.items_itm_id not in (64,65,104,107)";
        
        
        if (expensesParameters.get("mesId") != null && expensesParameters.get("mesId").toString().length() > 0){
            query +=" and to_char(i.ite_date,'mmyyyy')=#mesId##anioId#";
        }      
        if (expensesParameters.get("anioId") != null && expensesParameters.get("anioId").toString().length() > 0){
            query +=" and to_char(i.ite_date,'yyyy')=#anioId#";
        }           
        
        query +=" group by i.currency_cur_id,e.exp_date order by 2";
                
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
                List results = new ArrayList();
                results.add(rs.getString("curId"));
                results.add(rs.getString("expDate"));
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
