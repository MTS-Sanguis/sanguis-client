package ru.mts.sanguis_client.mvp.models;

public class NewsModel{

    private String title;
    private String description;

    public NewsModel(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }
}
