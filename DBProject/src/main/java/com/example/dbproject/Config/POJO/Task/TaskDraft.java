package com.example.dbproject.Config.POJO.Task;

import java.sql.Date;

public class TaskDraft {
    Integer id;
    String nameDraft;
    String statusDraft;
    Date startDraft;
    Date endDraft;
    String descriptionDraft;

    public TaskDraft(Integer id, String nameDraft, String statusDraft, Date startDraft, Date endDraft, String descriptionDraft) {
        this.id = id;
        this.nameDraft = nameDraft;
        this.statusDraft = statusDraft;
        this.startDraft = startDraft;
        this.endDraft = endDraft;
        this.descriptionDraft = descriptionDraft;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNameDraft() {
        return nameDraft;
    }

    public void setNameDraft(String nameDraft) {
        this.nameDraft = nameDraft;
    }

    public String getStatusDraft() {
        return statusDraft;
    }

    public void setStatusDraft(String statusDraft) {
        this.statusDraft = statusDraft;
    }

    public Date getStartDraft() {
        return startDraft;
    }

    public void setStartDraft(Date startDraft) {
        this.startDraft = startDraft;
    }

    public Date getEndDraft() {
        return endDraft;
    }

    public void setEndDraft(Date endDraft) {
        this.endDraft = endDraft;
    }

    public String getDescriptionDraft() {
        return descriptionDraft;
    }

    public void setDescriptionDraft(String descriptionDraft) {
        this.descriptionDraft = descriptionDraft;
    }
}
