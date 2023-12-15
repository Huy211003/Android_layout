package com.example.bookjson;

public class CustomItem {
    private String title;
    private String author;
    private String genre;
    private int year;
    private String imageUrl;

    public CustomItem(String title, String author, String genre, int year, String imageUrl) {
        this.title = title;
        this.author = author;
        this.genre = genre;
        this.year = year;
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getGenre() {
        return genre;
    }

    public int getYear() {
        return year;
    }

    public String getImageUrl() {
        return imageUrl;
    }
}
