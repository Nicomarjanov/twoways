package com.twoways.to;

import java.sql.Timestamp;

import java.util.List;


//(name = "ORDERS")
public class OrdersTO {
    //(name="ORD_DATE", nullable = false)
    private Timestamp ordDate;
    //(name="ORD_DESCRIPTION")
    private String ordDescription;
    //
    //(name="ORD_ID", nullable = false)
    private Long ordId;
    //(name="ORD_JOB_DESCRIPTION")
    private String ordJobDescription;
    //(name="ORD_JOB_ID")
    private String ordJobId;
    //(name="ORD_JOB_NAME")
    private String ordJobName;
    //(name="ORD_NAME", nullable = false)
    private String ordName;
    //(name="ORD_PROJ_ID")
    private String ordProjId;
    //(name="ORD_WO_NUMBER")
    private Long ordWoNumber;
    private List<OrdersDocsTO> ordersDocsTOList;
    private ClientsTO clientsTO;
    private List<ProjectsTO> projectsTOList;
    private List<InvoicesTO> invoicesTOList;

    public OrdersTO() {
    }


    public Timestamp getOrdDate() {
        return ordDate;
    }

    public void setOrdDate(Timestamp ordDate) {
        this.ordDate = ordDate;
    }

    public String getOrdDescription() {
        return ordDescription;
    }

    public void setOrdDescription(String ordDescription) {
        this.ordDescription = ordDescription;
    }

    public Long getOrdId() {
        return ordId;
    }

    public void setOrdId(Long ordId) {
        this.ordId = ordId;
    }

    public String getOrdJobDescription() {
        return ordJobDescription;
    }

    public void setOrdJobDescription(String ordJobDescription) {
        this.ordJobDescription = ordJobDescription;
    }

    public String getOrdJobId() {
        return ordJobId;
    }

    public void setOrdJobId(String ordJobId) {
        this.ordJobId = ordJobId;
    }

    public String getOrdJobName() {
        return ordJobName;
    }

    public void setOrdJobName(String ordJobName) {
        this.ordJobName = ordJobName;
    }

    public String getOrdName() {
        return ordName;
    }

    public void setOrdName(String ordName) {
        this.ordName = ordName;
    }

    public String getOrdProjId() {
        return ordProjId;
    }

    public void setOrdProjId(String ordProjId) {
        this.ordProjId = ordProjId;
    }

    public Long getOrdWoNumber() {
        return ordWoNumber;
    }

    public void setOrdWoNumber(Long ordWoNumber) {
        this.ordWoNumber = ordWoNumber;
    }

    public List<OrdersDocsTO> getOrdersDocsTOList() {
        return ordersDocsTOList;
    }

    public void setOrdersDocsTOList(List<OrdersDocsTO> ordersDocsTOList) {
        this.ordersDocsTOList = ordersDocsTOList;
    }

    public OrdersDocsTO addOrdersDocsTO(OrdersDocsTO ordersDocsTO) {
        getOrdersDocsTOList().add(ordersDocsTO);
        ordersDocsTO.setOrdersTO(this);
        return ordersDocsTO;
    }

    public OrdersDocsTO removeOrdersDocsTO(OrdersDocsTO ordersDocsTO) {
        getOrdersDocsTOList().remove(ordersDocsTO);
        ordersDocsTO.setOrdersTO(null);
        return ordersDocsTO;
    }

    public ClientsTO getClientsTO() {
        return clientsTO;
    }

    public void setClientsTO(ClientsTO clientsTO) {
        this.clientsTO = clientsTO;
    }

    public List<ProjectsTO> getProjectsTOList() {
        return projectsTOList;
    }

    public void setProjectsTOList(List<ProjectsTO> projectsTOList) {
        this.projectsTOList = projectsTOList;
    }

    public ProjectsTO addProjectsTO(ProjectsTO projectsTO) {
        getProjectsTOList().add(projectsTO);
        projectsTO.setOrdersTO(this);
        return projectsTO;
    }

    public ProjectsTO removeProjectsTO(ProjectsTO projectsTO) {
        getProjectsTOList().remove(projectsTO);
        projectsTO.setOrdersTO(null);
        return projectsTO;
    }

    public List<InvoicesTO> getInvoicesTOList() {
        return invoicesTOList;
    }

    public void setInvoicesTOList(List<InvoicesTO> invoicesTOList) {
        this.invoicesTOList = invoicesTOList;
    }

    public InvoicesTO addInvoicesTO(InvoicesTO invoicesTO) {
        getInvoicesTOList().add(invoicesTO);
        invoicesTO.setOrdersTO(this);
        return invoicesTO;
    }

    public InvoicesTO removeInvoicesTO(InvoicesTO invoicesTO) {
        getInvoicesTOList().remove(invoicesTO);
        invoicesTO.setOrdersTO(null);
        return invoicesTO;
    }
}
