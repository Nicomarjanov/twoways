package com.twoways.dao;

import com.twoways.to.PaymentsTO;

import java.util.List;
import java.util.Map;

public interface PaymentDAO {

    public PaymentsTO insertarPago(PaymentsTO paymentTO)  throws Exception;
    public PaymentsTO getPaymentById(Long payId) throws Exception;
    public List <PaymentsTO> findPayments(Map paymentParameters) throws Exception;
    public List obtenerItemsPago(Long payId) throws Exception;
    public void erasePayment(Long payId) throws Exception;
    public List findFutureIncomesByClient (Map params) throws Exception;
    public List findFuturePayments (Map params) throws Exception;
}
