package com.example.dbproject.Config.POJO.ProductAndOffice;

public class Product {
    private int id;
    private String name;
    private int price;
    private int amount;
    private String category;
    private String provider;

    public Product(int id, String name, int price, int amount, String category, String provider) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.amount = amount;
        this.category = category;
        this.provider = provider;
    }

    public int getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }
}
