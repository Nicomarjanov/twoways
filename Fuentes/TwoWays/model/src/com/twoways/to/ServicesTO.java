package com.twoways.to;

public class ServicesTO {
    
    private String serId;
    private String serName; 
    private String serDescription;
    public ServicesTO() {
    }

    public void setSerId(String serId) {
        this.serId = serId;
    }

    public String getSerId() {
        return serId;
    }

    public void setSerName(String serName) {
        this.serName = serName;
    }

    public String getSerName() {
        return serName;
    }

    public void setSerDescription(String serDescription) {
        this.serDescription = serDescription;
    }

    public String getSerDescription() {
        return serDescription;
    }
}
