package com.twoways.to;

import java.sql.Timestamp;

import java.util.List;




//(name = "PROJECTS")
public class ProjectsTO {
    //(name="CURRENCY_COTIZATIONS_CUC_ID")
    private Long currencyCotizationsCucId;
    //(name="PRO_DEADLINE_DATE")
    private Timestamp proDate;
    //(name="PRO_DESCRIPTION")
    private String proDescription;
    //(name="PRO_FINISH_DATE")
    private Timestamp proFinishDate;
    //
    //(name="PRO_ID", nullable = false)
    private Long proId;
    //(name="PRO_NAME", nullable = false)
    private String proName;
    //(name="PRO_START_DATE", nullable = false)
    private Timestamp proStartDate;
    //(name="PRO_STATUS")
    private String proStatus;
    //(name="PRO_TOTAL_CLIENT")
    private Long proTotalClient;
    //(name="PRO_TOTAL_CLI_WCOUNT")
    private Long proTotalCliWcount;
    //(name="PRO_TOTAL_TRANSLATOR")
    private Long proTotalTranslator;
    //(name="PRO_TOTAL_TRAN_WCOUNT")
    private Long proTotalTranWcount;
    //(name="USERS_USR_ID")
    private String usersUsrId;
    private List<ProjectsRatesTO> projectsRatesTOList;
    private OrdersTO ordersTO;
    private CurrencyTO currencyTO;
    private List<ProjectAssignmentsTO> projectAssignmentsTOList;
    private StatesTO statesTO;

    public ProjectsTO() {
    }

    public Long getCurrencyCotizationsCucId() {
        return currencyCotizationsCucId;
    }

    public void setCurrencyCotizationsCucId(Long currencyCotizationsCucId) {
        this.currencyCotizationsCucId = currencyCotizationsCucId;
    }


    
    public String getProDescription() {
        return proDescription;
    }

    public void setProDescription(String proDescription) {
        this.proDescription = proDescription;
    }

    public Timestamp getProFinishDate() {
        return proFinishDate;
    }

    public void setProFinishDate(Timestamp proFinishDate) {
        this.proFinishDate = proFinishDate;
    }

    public Long getProId() {
        return proId;
    }

    public void setProId(Long proId) {
        this.proId = proId;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public Timestamp getProStartDate() {
        return proStartDate;
    }

    public void setProStartDate(Timestamp proStartDate) {
        this.proStartDate = proStartDate;
    }

    public String getProStatus() {
        return proStatus;
    }

    public void setProStatus(String proStatus) {
        this.proStatus = proStatus;
    }

    public Long getProTotalClient() {
        return proTotalClient;
    }

    public void setProTotalClient(Long proTotalClient) {
        this.proTotalClient = proTotalClient;
    }

    public Long getProTotalCliWcount() {
        return proTotalCliWcount;
    }

    public void setProTotalCliWcount(Long proTotalCliWcount) {
        this.proTotalCliWcount = proTotalCliWcount;
    }

    public Long getProTotalTranslator() {
        return proTotalTranslator;
    }

    public void setProTotalTranslator(Long proTotalTranslator) {
        this.proTotalTranslator = proTotalTranslator;
    }

    public Long getProTotalTranWcount() {
        return proTotalTranWcount;
    }

    public void setProTotalTranWcount(Long proTotalTranWcount) {
        this.proTotalTranWcount = proTotalTranWcount;
    }

    public String getUsersUsrId() {
        return usersUsrId;
    }

    public void setUsersUsrId(String usersUsrId) {
        this.usersUsrId = usersUsrId;
    }

    public List<ProjectsRatesTO> getProjectsRatesTOList() {
        return projectsRatesTOList;
    }

    public void setProjectsRatesTOList(List<ProjectsRatesTO> projectsRatesTOList) {
        this.projectsRatesTOList = projectsRatesTOList;
    }

    public ProjectsRatesTO addProjectsRatesTO(ProjectsRatesTO projectsRatesTO) {
        getProjectsRatesTOList().add(projectsRatesTO);
        projectsRatesTO.setProjectsTO(this);
        return projectsRatesTO;
    }

    public ProjectsRatesTO removeProjectsRatesTO(ProjectsRatesTO projectsRatesTO) {
        getProjectsRatesTOList().remove(projectsRatesTO);
        projectsRatesTO.setProjectsTO(null);
        return projectsRatesTO;
    }

    public OrdersTO getOrdersTO() {
        return ordersTO;
    }

    public void setOrdersTO(OrdersTO ordersTO) {
        this.ordersTO = ordersTO;
    }

    public CurrencyTO getCurrencyTO() {
        return currencyTO;
    }

    public void setCurrencyTO(CurrencyTO currencyTO) {
        this.currencyTO = currencyTO;
    }

    public List<ProjectAssignmentsTO> getProjectAssignmentsTOList() {
        return projectAssignmentsTOList;
    }

    public void setProjectAssignmentsTOList(List<ProjectAssignmentsTO> projectAssignmentsTOList) {
        this.projectAssignmentsTOList = projectAssignmentsTOList;
    }

    public ProjectAssignmentsTO addProjectAssignmentsTO(ProjectAssignmentsTO projectAssignmentsTO) {
        getProjectAssignmentsTOList().add(projectAssignmentsTO);
        projectAssignmentsTO.setProjectsTO(this);
        return projectAssignmentsTO;
    }

    public ProjectAssignmentsTO removeProjectAssignmentsTO(ProjectAssignmentsTO projectAssignmentsTO) {
        getProjectAssignmentsTOList().remove(projectAssignmentsTO);
        projectAssignmentsTO.setProjectsTO(null);
        return projectAssignmentsTO;
    }

    public void setProDate(Timestamp proDate) {
        this.proDate = proDate;
    }

    public Timestamp getProDate() {
        return proDate;
    }

    public void setStatesTO(StatesTO statesTO) {
        this.statesTO = statesTO;
    }

    public StatesTO getStatesTO() {
        return statesTO;
    }
}
