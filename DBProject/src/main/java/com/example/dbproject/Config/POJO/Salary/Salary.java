package com.example.dbproject.Config.POJO.Salary;

public class Salary {
    int id;
    String name;
    float amount;
    float bid;
    String job;

    public Salary(int id, String name, float amount, float bid, String job) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.bid = bid;
        this.job = job;
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

    public float getBid() {
        return bid;
    }

    public void setBid(float bid) {
        this.bid = bid;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }
}
