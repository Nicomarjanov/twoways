package com.twoways.to;

//(name = "ORDERS_DOCS")
//Class(OrdersDocsTOPK.class)
public class OrdersDocsTO {

    private Long odoId;
    private String odoName;
    private Long ordersOrdId;
    private OrdersTO ordersTO;

    public OrdersDocsTO() {
    }

    public Long getOdoId() {
        return odoId;
    }

    public void setOdoId(Long odoId) {
        this.odoId = odoId;
    }

    public String getOdoName() {
        return odoName;
    }

    public void setOdoName(String odoName) {
        this.odoName = odoName;
    }

    public Long getOrdersOrdId() {
        return ordersOrdId;
    }

    public void setOrdersOrdId(Long ordersOrdId) {
        this.ordersOrdId = ordersOrdId;
    }

    public OrdersTO getOrdersTO() {
        return ordersTO;
    }

    public void setOrdersTO(OrdersTO ordersTO) {
        this.ordersTO = ordersTO;
    }
}
