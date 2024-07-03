package com.example.dbproject.Config.POJO.Income;

import java.sql.Date;

public class Income {
    int id;
    String name;
    float amount;
    int count;
    Date begin;
    Date end;

    public Income(int id, String name, float amount, int count, Date begin, Date end) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.count = count;
        this.begin = begin;
        this.end = end;
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

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getBegin() {
        return begin;
    }

    public void setBegin(Date begin) {
        this.begin = begin;
    }

    public Date getEnd() {
        return end;
    }

    public void setEnd(Date end) {
        this.end = end;
    }
}
