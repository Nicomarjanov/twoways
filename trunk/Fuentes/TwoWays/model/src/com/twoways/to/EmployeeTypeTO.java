package com.twoways.to;

public class EmployeeTypeTO {

    private String etyName;
    private String etyDescription;

    public EmployeeTypeTO() {
    }
    
    public String getEtyName(){
        return etyName;
    }
    
    public void setEtyName(String etyName){
        this.etyName = etyName;
    }
    public String getEtyDescription(){
        return etyDescription;
    }
    
    public void setEtyDescription(String etyDescription){
        this.etyDescription = etyDescription;
    }    
    
}
