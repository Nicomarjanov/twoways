package com.twoways.to;

import java.sql.Blob;

//(name = "ORDERS_DOCS")
//Class(OrdersDocsTOPK.class)
public class OrdersDocsTO {

    private Long odoId;
    private String odoName;
    private Long ordersOrdId;
    private byte[] odoDoc; 
    private DocTypes docType;
    

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


 

    public void setOdoDoc(byte[] odoDoc) {
        this.odoDoc = odoDoc;
    }

    public byte[] getOdoDoc() {
        return odoDoc;
    }

    public void setDocType(DocTypes docType) {
        this.docType = docType;
    }

    public DocTypes getDocType() {
        return docType;
    }
}
