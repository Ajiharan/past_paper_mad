package com.example.exam4.model;

import android.graphics.Bitmap;

public class Photograph {

    private String id;
    private String photo_name;
    private String artist_name;
    private String photo_cat;
    private Bitmap bitmap;
    private String artist_id;
    private String count_tot;

    public String getCount_tot() {
        return count_tot;
    }

    public void setCount_tot(String count_tot) {
        this.count_tot = count_tot;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPhoto_name() {
        return photo_name;
    }

    public void setPhoto_name(String photo_name) {
        this.photo_name = photo_name;
    }

    public String getArtist_name() {
        return artist_name;
    }

    public void setArtist_name(String artist_name) {
        this.artist_name = artist_name;
    }

    public String getPhoto_cat() {
        return photo_cat;
    }

    public void setPhoto_cat(String photo_cat) {
        this.photo_cat = photo_cat;
    }

    public Bitmap getBitmap() {
        return bitmap;
    }

    public void setBitmap(Bitmap bitmap) {
        this.bitmap = bitmap;
    }

    public String getArtist_id() {
        return artist_id;
    }

    public void setArtist_id(String artist_id) {
        this.artist_id = artist_id;
    }
}
