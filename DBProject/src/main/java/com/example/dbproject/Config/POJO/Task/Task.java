package com.example.dbproject.Config.POJO.Task;

import java.sql.Date;

public class Task {
    int id;
    String name;
    String status;
    Date start;
    Date end;
    String description;

    public Task(int id, String name, String status, Date start, Date end, String description) {
        this.id = id;
        this.name = name;
        this.status = status;
        this.start = start;
        this.end = end;
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getStart() {
        return start;
    }

    public void setStart(Date start) {
        this.start = start;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
