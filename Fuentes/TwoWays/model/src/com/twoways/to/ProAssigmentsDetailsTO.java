package com.twoways.to;

public class ProAssigmentsDetailsTO {
    
    
    private OrdersDocsTO ordersDocsTO;
    private long padWCount; 
    private EmployeesRatesTO employeesRatesTO; 
    private TranslatorsLanguaguesTO pranslatorsLanguaguesTO;
 
    public ProAssigmentsDetailsTO() {
    
    
    }


    public void setOrdersDocsTO(OrdersDocsTO ordersDocsTO) {
        this.ordersDocsTO = ordersDocsTO;
    }

    public OrdersDocsTO getOrdersDocsTO() {
        return ordersDocsTO;
    }

    public void setPadWCount(long padWCount) {
        this.padWCount = padWCount;
    }

    public long getPadWCount() {
        return padWCount;
    }

    public void setEmployeesRatesTO(EmployeesRatesTO employeesRatesTO) {
        this.employeesRatesTO = employeesRatesTO;
    }

    public EmployeesRatesTO getEmployeesRatesTO() {
        return employeesRatesTO;
    }

    public void setPranslatorsLanguaguesTO(TranslatorsLanguaguesTO pranslatorsLanguaguesTO) {
        this.pranslatorsLanguaguesTO = pranslatorsLanguaguesTO;
    }

    public TranslatorsLanguaguesTO getPranslatorsLanguaguesTO() {
        return pranslatorsLanguaguesTO;
    }
}
