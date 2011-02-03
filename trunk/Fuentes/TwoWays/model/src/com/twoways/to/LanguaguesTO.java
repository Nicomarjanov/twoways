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
    private List<TranslatorsLanguaguesTO> translatorsLanguaguesTOList;
    private List<TranslatorsLanguaguesTO> translatorsLanguaguesTOList1;

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


    public void setTranslatorsLanguaguesTOList(List<TranslatorsLanguaguesTO> translatorsLanguaguesTOList) {
        this.translatorsLanguaguesTOList = translatorsLanguaguesTOList;
    }

    public List<TranslatorsLanguaguesTO> getTranslatorsLanguaguesTOList() {
        return translatorsLanguaguesTOList;
    }

    public void setTranslatorsLanguaguesTOList1(List<TranslatorsLanguaguesTO> translatorsLanguaguesTOList1) {
        this.translatorsLanguaguesTOList1 = translatorsLanguaguesTOList1;
    }

    public List<TranslatorsLanguaguesTO> getTranslatorsLanguaguesTOList1() {
        return translatorsLanguaguesTOList1;
    }
}
