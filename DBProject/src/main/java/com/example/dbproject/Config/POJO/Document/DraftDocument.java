package com.example.dbproject.Config.POJO.Document;

import java.sql.Date;

public class DraftDocument {
    private int idDraft;
    private String DraftNameDocumenta;
    private String DraftDescription;
    private Date DraftDataCreation;
    private Date DraftResponsePreparationЕime;
    private String DraftStatus;
    private String DraftPrepared;
    private String DraftSigned;
    private String DraftContact;
    private int DraftSumma;
    private String DraftTipDocumenta;
    private String DraftCounterparty;

    public DraftDocument(int idDraft, String draftNameDocumenta, String draftDescription, Date draftDataCreation, Date draftResponsePreparationЕime, String draftStatus, String draftPrepared, String draftSigned, String draftContact, int draftSumma, String draftTipDocumenta, String draftCounterparty) {
        this.idDraft = idDraft;
        this.DraftNameDocumenta = draftNameDocumenta;
        this.DraftDescription = draftDescription;
        this.DraftDataCreation = draftDataCreation;
        this.DraftResponsePreparationЕime = draftResponsePreparationЕime;
        this.DraftStatus = draftStatus;
        this.DraftPrepared = draftPrepared;
        this.DraftSigned = draftSigned;
        this.DraftContact = draftContact;
        this.DraftSumma = draftSumma;
        this.DraftTipDocumenta = draftTipDocumenta;
        this.DraftCounterparty = draftCounterparty;
    }

    public int getIdDraft() {
        return idDraft;
    }

    public void setIdDraft(int idDraft) {
        this.idDraft = idDraft;
    }

    public String getDraftNameDocumenta() {
        return DraftNameDocumenta;
    }

    public void setDraftNameDocumenta(String draftNameDocumenta) {
        DraftNameDocumenta = draftNameDocumenta;
    }

    public String getDraftDescription() {
        return DraftDescription;
    }

    public void setDraftDescription(String draftDescription) {
        DraftDescription = draftDescription;
    }

    public Date getDraftDataCreation() {
        return DraftDataCreation;
    }

    public void setDraftDataCreation(Date draftDataCreation) {
        DraftDataCreation = draftDataCreation;
    }

    public Date getDraftResponsePreparationЕime() {
        return DraftResponsePreparationЕime;
    }

    public void setDraftResponsePreparationЕime(Date draftResponsePreparationЕime) {
        DraftResponsePreparationЕime = draftResponsePreparationЕime;
    }

    public String getDraftStatus() {
        return DraftStatus;
    }

    public void setDraftStatus(String draftStatus) {
        DraftStatus = draftStatus;
    }

    public String getDraftPrepared() {
        return DraftPrepared;
    }

    public void setDraftPrepared(String draftPrepared) {
        DraftPrepared = draftPrepared;
    }

    public String getDraftSigned() {
        return DraftSigned;
    }

    public void setDraftSigned(String draftSigned) {
        DraftSigned = draftSigned;
    }

    public String getDraftContact() {
        return DraftContact;
    }

    public void setDraftContact(String draftContact) {
        DraftContact = draftContact;
    }

    public int getDraftSumma() {
        return DraftSumma;
    }

    public void setDraftSumma(int draftSumma) {
        DraftSumma = draftSumma;
    }

    public String getDraftTipDocumenta() {
        return DraftTipDocumenta;
    }

    public void setDraftTipDocumenta(String draftTipDocumenta) {
        DraftTipDocumenta = draftTipDocumenta;
    }

    public String getDraftCounterparty() {
        return DraftCounterparty;
    }

    public void setDraftCounterparty(String draftCounterparty) {
        DraftCounterparty = draftCounterparty;
    }
}
