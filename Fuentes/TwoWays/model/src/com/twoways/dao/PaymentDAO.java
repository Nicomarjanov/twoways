package com.twoways.dao;

import com.twoways.to.PaymentsTO;

public interface PaymentDAO {

    public PaymentsTO insertarPago(PaymentsTO paymentTO)  throws Exception;
    public PaymentsTO getPaymentById(Long payId) throws Exception;

}
