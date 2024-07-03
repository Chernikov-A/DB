package com.example.dbproject.Config.POJO.expenses;

public class Expenses {
    int id;
    String name;
    float amount;
    int count;

    public Expenses(int id, String name, float amount, int count) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.count = count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
