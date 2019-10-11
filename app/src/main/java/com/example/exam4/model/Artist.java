package com.example.exam4.model;

public class Artist {
    private String id;
    private String name;
    private String tot_count;

    public String getTot_count() {
        return tot_count;
    }

    public void setTot_count(String tot_count) {
        this.tot_count = tot_count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
