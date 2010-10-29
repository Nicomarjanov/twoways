package com.twoways.to;

import java.util.List;

public class LanguaguesTO {
    //(name="LAN_DESCRIPTION")
    private String lanDescription;
    //
    //(name="LAN_ID", nullable = false)
    private Long lanId;
    //(name="LAN_NAME")
    private String lanName;
    private List<TranslatorsTO> translatorsTOList;
    private List<TranslatorsTO> translatorsTOList1;
    private List<TranslatorsTO> translatorsTOList2;

    public LanguaguesTO() {
    }

    public String getLanDescription() {
        return lanDescription;
    }

    public void setLanDescription(String lanDescription) {
        this.lanDescription = lanDescription;
    }

    public Long getLanId() {
        return lanId;
    }

    public void setLanId(Long lanId) {
        this.lanId = lanId;
    }

    public String getLanName() {
        return lanName;
    }

    public void setLanName(String lanName) {
        this.lanName = lanName;
    }

    public List<TranslatorsTO> getTranslatorsTOList() {
        return translatorsTOList;
    }

    public void setTranslatorsTOList(List<TranslatorsTO> translatorsTOList) {
        this.translatorsTOList = translatorsTOList;
    }

    public TranslatorsTO addTranslatorsTO(TranslatorsTO translatorsTO) {
        getTranslatorsTOList().add(translatorsTO);
        translatorsTO.setLanguaguesTO(this);
        return translatorsTO;
    }

    public TranslatorsTO removeTranslatorsTO(TranslatorsTO translatorsTO) {
        getTranslatorsTOList().remove(translatorsTO);
        translatorsTO.setLanguaguesTO(null);
        return translatorsTO;
    }

    public List<TranslatorsTO> getTranslatorsTOList1() {
        return translatorsTOList1;
    }

    public void setTranslatorsTOList1(List<TranslatorsTO> translatorsTOList1) {
        this.translatorsTOList1 = translatorsTOList1;
    }

    public TranslatorsTO addTranslatorsTO1(TranslatorsTO translatorsTO) {
        getTranslatorsTOList1().add(translatorsTO);
        translatorsTO.setLanguaguesTO1(this);
        return translatorsTO;
    }

    public TranslatorsTO removeTranslatorsTO1(TranslatorsTO translatorsTO) {
        getTranslatorsTOList1().remove(translatorsTO);
        translatorsTO.setLanguaguesTO1(null);
        return translatorsTO;
    }

    public List<TranslatorsTO> getTranslatorsTOList2() {
        return translatorsTOList2;
    }

    public void setTranslatorsTOList2(List<TranslatorsTO> translatorsTOList2) {
        this.translatorsTOList2 = translatorsTOList2;
    }

    public TranslatorsTO addTranslatorsTO2(TranslatorsTO translatorsTO) {
        getTranslatorsTOList2().add(translatorsTO);
        translatorsTO.setLanguaguesTO2(this);
        return translatorsTO;
    }

    public TranslatorsTO removeTranslatorsTO2(TranslatorsTO translatorsTO) {
        getTranslatorsTOList2().remove(translatorsTO);
        translatorsTO.setLanguaguesTO2(null);
        return translatorsTO;
    }
}
