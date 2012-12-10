package com.twoways.dao;

import com.twoways.to.AccountsTO;
import com.twoways.to.ClientsTO;
import com.twoways.to.CurrencyTO;
import com.twoways.to.EmployeesTO;
import com.twoways.to.InvoicesTO;
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

    public Long insertarPago(PaymentsTO paymentTO)  throws Exception{
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
         return payId;
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
        "       y.cur_symbol as curSymbol, \n" + 
        "       y.cur_id as curId \n" + 
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
                moneda.setCurId(rs.getLong("curId"));
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
    
    public List findFuturePayments (Map params) throws Exception{
    List salida = new ArrayList();
    DataSource ds = this.getDataSource(); 
    Connection con = null;
    Statement stm = null;
    ResultSet rs= null ;
    String query ="select pa.employees_emp_id as empId,\n" + 
    "               e.emp_first_name as firstName,\n" + 
    "               e.emp_last_name as lastName,\n" + 
    "               sum(decode(pd.pad_wcount,1,pd.pad_rate,null,0,(pd.pad_rate * pd.pad_wcount))) as praTotal,\n" + 
    "               c.cur_id as curId,\n" + 
    "               pa.pra_assign_date as assignDate\n" + 
    "          from project_assignments pa , projects p, proj_assignments_details pd, rates r, currency c, employees e\n" + 
    "         where pa.projects_pro_id = p.pro_id\n" + 
    "         and pd.project_assignments_pra_id = pa.pra_id\n" + 
    "         and pd.project_assignments_employees_ = pa.employees_emp_id\n" + 
    "         and pd.project_assignments_projects_p = pa.projects_pro_id\n" + 
    "         and r.rat_id = pd.employees_rates_rates_rat_id\n" + 
    "         and pa.employees_emp_id=e.emp_id\n" + 
    "         and r.currency_cur_id=c.cur_id\n" +
    "         and pd.pad_pay_date is null";
    
    if (params.get("empId") != null && params.get("empId").toString().length() > 0){
        query +=" and e.emp_Id= #empId#";
    }        
    if (params.get("mesId") != null && params.get("mesId").toString().length() > 0){
        query +=" and to_char(pa.pra_assign_date,'mmyyyy')=#mesId##anioId#";
         //query +=" and to_char(pa.pra_assign_date,'ddmmyyyy') between add_months(to_date('25'||'#mesId##anioId#'||' 00:00','ddMMyyyy hh24:mi')+1,-1) and to_date('25'||'#mesId##anioId#'||' 23:59','ddMMyyyy hh24:mi')";
    }      
    if (params.get("anioId") != null && params.get("anioId").toString().length() > 0){
        query +=" and to_char(pa.pra_assign_date,'yyyy')=#anioId#";
    }              
    query +=" group by pa.employees_emp_id,e.emp_first_name,e.emp_last_name,c.cur_id,pa.pra_assign_date\n" + 
            " order by 1,6";
            
    for (Iterator i = params.keySet().iterator();i.hasNext();){
        String param = (String)i.next();
        query = query.replaceAll("#"+param+"#",params.get(param).toString());
    }
    try {
        con = ds.getConnection();
        stm = con.createStatement();
        System.out.println(query);
        rs = stm.executeQuery(query);
        while(rs.next()){
            List results = new ArrayList();
            results.add(rs.getString("empId"));
            results.add(rs.getString("firstName"));  
            results.add(rs.getString("LastName"));     
            results.add(rs.getString("praTotal"));        
            results.add(rs.getString("curId"));                         
            results.add(rs.getString("assignDate"));            
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

    public List findFutureIncomesByClient (Map params) throws Exception{
    List salida = new ArrayList();
    DataSource ds = this.getDataSource(); 
    Connection con = null;
    Statement stm = null;
    ResultSet rs= null ;
    String query ="select c.cli_id as cliId,\n" + 
    "       c.cli_name as cliName,\n" + 
    "       sum(r.orr_value*r.orr_wcount) as total,\n" + 
    "       t.currency_cur_id as curId,\n" + 
    "       o.ord_finish_date as finishDate \n" + 
    "  from clients c, orders o, orders_rates r,rates t\n" + 
    " where o.ord_id =r.orders_ord_id\n" + 
    " and o.clients_cli_id = c.cli_id\n" + 
    " and r.rates_rat_id=t.rat_id\n" +
    " and r.orr_pay_date is null";
    
    if (params.get("cliId") != null && params.get("cliId").toString().length() > 0){
        query +=" and c.cli_Id= #cliId#";
    }        
    if (params.get("mesId") != null && params.get("mesId").toString().length() > 0){
        query +=" and to_char(o.ord_finish_date,'mmyyyy')=#mesId##anioId#";
    }      
    if (params.get("anioId") != null && params.get("anioId").toString().length() > 0){
        query +=" and to_char(o.ord_finish_date,'yyyy')=#anioId#";
    }              
    query +=" group by c.cli_id, c.cli_name, t.currency_cur_id, o.ord_finish_date\n" + 
            " order by 1,5";
            
    for (Iterator i = params.keySet().iterator();i.hasNext();){
        String param = (String)i.next();
        query = query.replaceAll("#"+param+"#",params.get(param).toString());
    }
    try {
        con = ds.getConnection();
        stm = con.createStatement();
        System.out.println(query);
        rs = stm.executeQuery(query);
        while(rs.next()){
            List results = new ArrayList();
            results.add(rs.getString("cliId"));
            results.add(rs.getString("cliName"));       
            results.add(rs.getString("total"));        
            results.add(rs.getString("curId"));                         
            results.add(rs.getString("finishDate"));            
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
