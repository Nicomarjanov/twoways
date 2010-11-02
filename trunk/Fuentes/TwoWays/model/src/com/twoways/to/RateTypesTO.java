package com.twoways.to;

public class RateTypesTO {

    private String rtyName;
    private String rtyDescription;
    
    public RateTypesTO() {    
    }
    
    public String getRtyName(){
        return rtyName;
    }
    
    public void setRtyName(String rtyName){
        this.rtyName = rtyName;
    }
    public String getRtyDescription(){
        return rtyDescription;
    }
    
    public void setRtyDescription(String rtyDescription){
        this.rtyDescription = rtyDescription;
    }    
}
