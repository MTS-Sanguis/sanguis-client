package ru.mts.sanguis_client.mvp.models;

public class EventModel {

    private String title;
    private String subtitle;

    public EventModel(String title, String subtitle) {
        this.title = title;
        this.subtitle = subtitle;
    }

    public String getTitle() {
        return title;
    }

    public String getSubtitle() {
        return subtitle;
    }
}
