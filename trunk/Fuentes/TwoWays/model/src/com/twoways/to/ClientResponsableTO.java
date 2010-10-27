package com.twoways.to;


public class ClientResponsableTO {
    //(name="CRE_EMAIL")
    private String creEmail;
    //(name="CRE_FIRST_NAME", nullable = false)
    private String creFirstName;
    
    //(name="CRE_ID", nullable = false)
    private Long creId;
    //(name="CRE_LAST_NAME")
    private String creLastName;
    //(name="CRE_MOBILE_NUMBER")
    private Long creMobileNumber;
    //(name="CRE_PHONE_NUMBER")
    private Long crePhoneNumber;
    private ClientsTO clientsTO;

    public ClientResponsableTO() {
    }


    public String getCreEmail() {
        return creEmail;
    }

    public void setCreEmail(String creEmail) {
        this.creEmail = creEmail;
    }

    public String getCreFirstName() {
        return creFirstName;
    }

    public void setCreFirstName(String creFirstName) {
        this.creFirstName = creFirstName;
    }

    public Long getCreId() {
        return creId;
    }

    public void setCreId(Long creId) {
        this.creId = creId;
    }

    public String getCreLastName() {
        return creLastName;
    }

    public void setCreLastName(String creLastName) {
        this.creLastName = creLastName;
    }

    public Long getCreMobileNumber() {
        return creMobileNumber;
    }

    public void setCreMobileNumber(Long creMobileNumber) {
        this.creMobileNumber = creMobileNumber;
    }

    public Long getCrePhoneNumber() {
        return crePhoneNumber;
    }

    public void setCrePhoneNumber(Long crePhoneNumber) {
        this.crePhoneNumber = crePhoneNumber;
    }

    public ClientsTO getClientsTO() {
        return clientsTO;
    }

    public void setClientsTO(ClientsTO clientsTO) {
        this.clientsTO = clientsTO;
    }
}
