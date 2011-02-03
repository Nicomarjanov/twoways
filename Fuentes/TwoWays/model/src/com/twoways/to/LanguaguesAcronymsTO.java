package com.twoways.to;

import java.util.List;

public class LanguaguesAcronymsTO {

    private String laaAcronym;
    private String laaDescription;
    private Long languaguesLanId;
    private LanguaguesTO languaguesTO;
    
    public LanguaguesAcronymsTO() {
    }

    public void setLaaAcronym(String laaAcronym) {
        this.laaAcronym = laaAcronym;
    }

    public String getLaaAcronym() {
        return laaAcronym;
    }

    public void setLaaDescription(String laaDescription) {
        this.laaDescription = laaDescription;
    }

    public String getLaaDescription() {
        return laaDescription;
    }


    public void setLanguaguesTO(LanguaguesTO languaguesTO) {
        this.languaguesTO = languaguesTO;
    }

    public LanguaguesTO getLanguaguesTO() {
        return languaguesTO;
    }

    public void setLanguaguesLanId(Long languaguesLanId) {
        this.languaguesLanId = languaguesLanId;
    }

    public Long getLanguaguesLanId() {
        return languaguesLanId;
    }
}
