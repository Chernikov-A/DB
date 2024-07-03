package com.example.dbproject.Config.POJO.Document;

import java.sql.Date;

public class Document {
    // POJO таблицы document
    private int id;
    private String NameDocumenta;
    private String Description;
    private Date DataCreation;
    private Date ResponsePreparationЕime;
    private String Status;
    private String Prepared;
    private String Signed;
    private String Contact;
    private int Summa;
    private String TipDocumenta;
    private String Counterparty;


    public Document(int id, String nameDocumenta, String description, Date dataCreation, Date responsePreparationЕime, String status, String prepared, String signed, String contact, int summa, String tipDocumenta, String counterparty) {
        this.id = id;
        this.NameDocumenta = nameDocumenta;
        this.Description = description;
        this.DataCreation = dataCreation;
        this.ResponsePreparationЕime = responsePreparationЕime;
        this.Status = status;
        this.Prepared = prepared;
        this.Signed = signed;
        this.Contact = contact;
        this.Summa = summa;
        this.TipDocumenta = tipDocumenta;
        this.Counterparty = counterparty;
    }


    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameDocumenta() {
        return NameDocumenta;
    }

    public void setNameDocumenta(String nameDocumenta) {
        this.NameDocumenta = nameDocumenta;
    }

    public String getTipDocumenta() {
        return TipDocumenta;
    }

    public void setTipDocumenta(String tipDocumenta) {
        this.TipDocumenta = tipDocumenta;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        this.Status = status;
    }

    public Date getDataCreation() {
        return DataCreation;
    }

    public void setDataCreation(Date dataCreation) {
        this.DataCreation = dataCreation;
    }

    public Date getResponsePreparationЕime() {
        return ResponsePreparationЕime;
    }

    public void setResponsePreparationЕime(Date responsePreparationЕime) {
        this.ResponsePreparationЕime = responsePreparationЕime;
    }

    public String getPrepared() {
        return Prepared;
    }

    public void setPrepared(String prepared) {
        this.Prepared = prepared;
    }

    public String getCounterparty() {
        return Counterparty;
    }

    public void setCounterparty(String counterparty) {
        this.Counterparty = counterparty;
    }

    public String getSigned() {
        return Signed;
    }

    public void setSigned(String signed) {
        this.Signed = signed;
    }

    public String getContact() {
        return Contact;
    }

    public void setContact(String contact) {
        this.Contact = contact;
    }

    public int getSumma() {
        return Summa;
    }

    public void setSumma(Integer summa) {
        this.Summa = summa;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        this.Description = description;
    }
}
