package com.example.json;

public class CustomItem {
    private String productName;
    private int price;
    private String description;
    private String imageUrl;

    public CustomItem(String productName, int price, String description, String imageUrl) {
        this.productName = productName;
        this.price = price;
        this.description = description;
        this.imageUrl = imageUrl;
    }

    public String getProductName() {
        return productName;
    }

    public int getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}

