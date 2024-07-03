package com.example.dbproject.Config.POJO.ProductAndOffice;

public class OfficeEquipment {
    int id;
    String name;
    int amount;
    int count;

    public OfficeEquipment(int id, String name, int amount, int count) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.count = count;
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

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
