package com.twoways.dao;

import com.twoways.to.ExpensesTO;
import com.twoways.to.PaymentsTO;
import com.twoways.to.ProjAssignPaysTO;

import java.util.List;

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
             }      
         }   
         return getPaymentById(payId);
    }

    public PaymentsTO getPaymentById(Long payId) throws Exception {
       PaymentsTO pago =  (PaymentsTO)getSqlMapClientTemplate().queryForObject("getExpenseById",payId);
       return pago;
    }
    
}
