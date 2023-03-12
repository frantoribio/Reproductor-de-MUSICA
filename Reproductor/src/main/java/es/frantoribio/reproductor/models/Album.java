package es.frantoribio.reproductor.models;

import java.io.Serializable;

public class Album implements Serializable {
    private Artist artist;
    private Integer id;
    private String picture;

    @Override
    public String toString() {
        return "Album{" +
                "artist=" + artist +
                ", id=" + id +
                ", picture='" + picture + '\'' +
                '}';
    }

    public Artist getArtist() {
        return artist;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}

