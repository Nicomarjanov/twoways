package com.twoways.to;

import java.sql.Timestamp;


//(name = "USERS")
public class UsersTO {
    //(name="USR_BIRTH")
    private Timestamp usrBirth;
    private String usrPass;
    //(name="USR_FIRST_NAME", nullable = false)
    private String usrFirstName;
    //(name="USR_ID", nullable = false)
    private String usrId;
    //(name="USR_LAST_NAME")
    private String usrLastName;
    //(name="USR_MAIL")
    private String usrMail;
    //(name="USR_MOBILE_NUMBER")
    private Long usrMobileNumber;
    //(name="USR_OFFICE_NUMBER")
    private Long usrOfficeNumber;
    //(name="USR_PHONE_NUMBER")
    private Long usrPhoneNumber;
    private RolesTO rolesTO;

    public UsersTO() {
    }


    public Timestamp getUsrBirth() {
        return usrBirth;
    }

    public void setUsrBirth(Timestamp usrBirth) {
        this.usrBirth = usrBirth;
    }

    public String getUsrFirstName() {
        return usrFirstName;
    }

    public void setUsrFirstName(String usrFirstName) {
        this.usrFirstName = usrFirstName;
    }

    public String getUsrId() {
        return usrId;
    }

    public void setUsrId(String usrId) {
        this.usrId = usrId;
    }

    public String getUsrPass() {
        return usrId;
    }

    public void setUsrPass(String usrPass) {
        this.usrPass = usrPass;
    }
    public String getUsrLastName() {
        return usrLastName;
    }

    public void setUsrLastName(String usrLastName) {
        this.usrLastName = usrLastName;
    }

    public String getUsrMail() {
        return usrMail;
    }

    public void setUsrMail(String usrMail) {
        this.usrMail = usrMail;
    }

    public Long getUsrMobileNumber() {
        return usrMobileNumber;
    }

    public void setUsrMobileNumber(Long usrMobileNumber) {
        this.usrMobileNumber = usrMobileNumber;
    }

    public Long getUsrOfficeNumber() {
        return usrOfficeNumber;
    }

    public void setUsrOfficeNumber(Long usrOfficeNumber) {
        this.usrOfficeNumber = usrOfficeNumber;
    }

    public Long getUsrPhoneNumber() {
        return usrPhoneNumber;
    }

    public void setUsrPhoneNumber(Long usrPhoneNumber) {
        this.usrPhoneNumber = usrPhoneNumber;
    }

    public RolesTO getRolesTO() {
        return rolesTO;
    }

    public void setRolesTO(RolesTO rolesTO) {
        this.rolesTO = rolesTO;
    }
}
