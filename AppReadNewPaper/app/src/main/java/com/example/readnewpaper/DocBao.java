package com.example.readnewpaper;

public class DocBao {
    public String title;
    public String link;
    public String pubDate;
    public String image;

    public DocBao(String title, String link, String pubDate, String image) {
        this.title = title;
        this.link = link;
        this.pubDate = pubDate;
        this.image = image;
    }
}
