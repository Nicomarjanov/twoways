package com.twoways.to;

public class TranslatorsSpecializationsTO {
   
   private Long translatorsTraId;
   private String specializationsSpeName;
   private TranslatorsTO translatorsTO;
   private SpecializationsTO specializationsTO;

    public TranslatorsSpecializationsTO() {
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

    public void setSpecializationsSpeName(String specializationsSpeName) {
        this.specializationsSpeName = specializationsSpeName;
    }

    public String getSpecializationsSpeName() {
        return specializationsSpeName;
    }

    public void setSpecializationsTO(SpecializationsTO specializationsTO) {
        this.specializationsTO = specializationsTO;
    }

    public SpecializationsTO getSpecializationsTO() {
        return specializationsTO;
    }
}
