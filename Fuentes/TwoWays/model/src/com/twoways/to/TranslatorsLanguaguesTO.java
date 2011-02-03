package com.twoways.to;

public class TranslatorsLanguaguesTO {
    
    private Long tlaId;
    private Long translatorsTraId;
    private Long languaguesLanId;
    private Long languaguesLanId1;
    private String langAcronLaaAcronym;
    private String langAcronLaaAcronym1;
    private TranslatorsTO translatorsTO;
    private LanguaguesAcronymsTO langAcronymsTO;
    private LanguaguesAcronymsTO langAcronymsTO1;

    
    public TranslatorsLanguaguesTO() {
    }

    public void setTranslatorsTO(TranslatorsTO translatorsTO) {
        this.translatorsTO = translatorsTO;
    }

    public TranslatorsTO getTranslatorsTO() {
        return translatorsTO;
    }

    public void setTranslatorsTraId(Long translatorsTraId) {
        this.translatorsTraId = translatorsTraId;
    }

    public Long getTranslatorsTraId() {
        return translatorsTraId;
    }

    public void setLanguaguesLanId(Long languaguesLanId) {
        this.languaguesLanId = languaguesLanId;
    }

    public Long getLanguaguesLanId() {
        return languaguesLanId;
    }

    public void setLanguaguesLanId1(Long languaguesLanId1) {
        this.languaguesLanId1 = languaguesLanId1;
    }

    public Long getLanguaguesLanId1() {
        return languaguesLanId1;
    }

    public void setTlaId(Long tlaId) {
        this.tlaId = tlaId;
    }

    public Long getTlaId() {
        return tlaId;
    }

    public void setLangAcronLaaAcronym(String langAcronLaaAcronym) {
        this.langAcronLaaAcronym = langAcronLaaAcronym;
    }

    public String getLangAcronLaaAcronym() {
        return langAcronLaaAcronym;
    }

    public void setLangAcronLaaAcronym1(String langAcronLaaAcronym1) {
        this.langAcronLaaAcronym1 = langAcronLaaAcronym1;
    }

    public String getLangAcronLaaAcronym1() {
        return langAcronLaaAcronym1;
    }

    public void setLangAcronymsTO(LanguaguesAcronymsTO langAcronymsTO) {
        this.langAcronymsTO = langAcronymsTO;
    }

    public LanguaguesAcronymsTO getLangAcronymsTO() {
        return langAcronymsTO;
    }

    public void setLangAcronymsTO1(LanguaguesAcronymsTO langAcronymsTO1) {
        this.langAcronymsTO1 = langAcronymsTO1;
    }

    public LanguaguesAcronymsTO getLangAcronymsTO1() {
        return langAcronymsTO1;
    }
}
