package com.twoways.dao;

import com.twoways.to.AccountsTO;
import com.twoways.to.EmployeesTO;
import com.twoways.to.CurrencyTO;
import com.twoways.to.PaymentsTO;
import com.twoways.to.ProjAssignPaysTO;

import com.twoways.to.UsersTO;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

public class PaymentDAOImpl extends AbstractDAO  implements PaymentDAO{

    public PaymentDAOImpl(){    
    }

    public PaymentsTO insertarPago(PaymentsTO paymentTO)  throws Exception{
        Long payId = (Long) getSqlMapClientTemplate().queryForObject("payment.seq","");
        paymentTO.setPayId(payId);  
        getSqlMapClientTemplate().insert("insertPayment",paymentTO);
        
        List projAssList = paymentTO.getProjAssignTOList();
        if (projAssList.size() > 0){                
             for(Object projAssListTO: projAssList.toArray() ){
                 ProjAssignPaysTO auxProjAssPay = (ProjAssignPaysTO)projAssListTO;  
                 auxProjAssPay.setPaymentsTO(paymentTO);
                 getSqlMapClientTemplate().insert("insertProjAssignPay",auxProjAssPay);
                 Long padId = auxProjAssPay.getProAssigmentsDetailsTO().getPadId();
                 getSqlMapClientTemplate().update("updateProjAssign",padId);
             }      
         }   
         return getPaymentById(payId);
    }

    public PaymentsTO getPaymentById(Long payId) throws Exception {
       PaymentsTO pago =  (PaymentsTO)getSqlMapClientTemplate().queryForObject("getPaymentById",payId);
       return pago;
    }
    
    public List <PaymentsTO> findPayments(Map paymentParameters) throws Exception{
        List results = new ArrayList();
        DataSource ds = this.getDataSource(); 
        Connection con = null;
        Statement stm = null;
        ResultSet rs= null ;
        String query ="select t.pay_id as payId, \n" + 
        "       e.emp_id as empId, \n" + 
        "       e.emp_first_name as empFirstName, \n" + 
        "       e.emp_last_name as empLastName, \n" + 
        "       t.pay_date as payDate, \n" + 
        "       a.acc_name as accName, \n" + 
        "       y.cur_name as curName, \n" + 
        "       t.pay_amount as payAmount, \n" + 
        "       u.usr_id as usrId, \n" + 
        "       a.acc_id as accId, \n" + 
        "       y.cur_symbol as curSymbol \n" + 
        "from payments t, employees e, accounts a, currency y, users u \n" + 
        "where t.employees_emp_id= e.emp_id \n" + 
        "and t.accounts_acc_id = a.acc_id \n" + 
        "and t.currency_cur_id=y.cur_id \n" + 
        "and t.users_usr_id=u.usr_id";
        
        if (paymentParameters.get("invNumber") != null && paymentParameters.get("invNumber").toString().length() > 0){
            query +=" and t.pay_id= #invNumber#";
        }
        if (paymentParameters.get("empId") != null && paymentParameters.get("empId").toString().length() > 0){
            query +=" and e.emp_id= '#empId#'";
        }
        if(paymentParameters.get("payDate") != null && paymentParameters.get("payDate").toString().length() > 0){      
            query +=" and t.pay_date "+ paymentParameters.get("payDateOpt").toString()+"  to_date('#payDate#','dd/mm/yyyy')";
        }
        
        query +=" order by t.pay_id desc";

        for (Iterator i = paymentParameters.keySet().iterator();i.hasNext();){
            String param = (String)i.next();
            query = query.replaceAll("#"+param+"#",paymentParameters.get(param).toString());
        }
        try {
            con = ds.getConnection();
            stm = con.createStatement();
            //System.out.println(query);
            rs = stm.executeQuery(query);
            
            while(rs.next()){
    
                PaymentsTO pago = new PaymentsTO();
                pago.setPayId(rs.getLong("payId"));
                
                if(rs.getTime("payDate") !=null ){ 
                    java.sql.Timestamp timest = rs.getTimestamp("payDate");
                    pago.setPayDate(timest);
                }
                pago.setPayAmount(rs.getDouble("payAmount"));
 
                EmployeesTO empleado = new EmployeesTO();
                empleado.setEmpFirstName(rs.getString("empFirstName"));
                empleado.setEmpLastName(rs.getString("empLastName"));
                empleado.setEmpId(Long.parseLong(rs.getString("empId")));
                pago.setEmployeeTO(empleado);
                
                AccountsTO cuenta = new AccountsTO();
                cuenta.setAccName(rs.getString("accName"));
                cuenta.setAccId(rs.getLong("accId"));
                pago.setAccountsTO(cuenta);
                
                CurrencyTO moneda = new CurrencyTO();
                moneda.setCurName(rs.getString("curName"));
                moneda.setCurSymbol(rs.getString("curSymbol"));
                pago.setCurrencyTO(moneda);            
                
                UsersTO usuario = new UsersTO();
                usuario.setUsrId(rs.getString("usrId"));
                pago.setUserTO(usuario);
                
                results.add(pago);
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
    
    public List obtenerItemsPago(Long payId) throws Exception{
        List itemPago =  getSqlMapClientTemplate().queryForList("getItemsPaymentByPayId",payId);
        return itemPago;
    }
    
    public void erasePayment(Long payId) throws Exception{
        List<ProjAssignPaysTO> projAssignTOList = new ArrayList<ProjAssignPaysTO>();
        projAssignTOList = getSqlMapClientTemplate().queryForList("getProjAssignPaysByPayId",payId);
        
        if(projAssignTOList.size()>0){
            for(ProjAssignPaysTO projAssignPaysTO: projAssignTOList){
                getSqlMapClientTemplate().delete("eraseProjAssignPay",projAssignPaysTO);
                getSqlMapClientTemplate().update("updateProjAssignDetail",projAssignPaysTO.getProAssigmentsDetailsTO().getPadId());
            }
        }
        
        getSqlMapClientTemplate().delete("erasePayment",payId);
        
    }
}
