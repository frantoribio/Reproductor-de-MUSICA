package es.frantoribio.reproductor.models;

import java.io.Serializable;

public class Song implements Serializable {
    private Integer id;
    private String title;
    private String picture;
    private String file;
    private String name;
    public Album album;
    public Artist artist;
    public Song() {
    }

    public Song(Integer id, String title, String picture, String file, String name,
                Album album, Artist artist) {

        picture = album.getPicture();
        name = artist.getName();

        this.id = id;
        this.title = title;
        this.picture = picture;
        this.file = file + ".mp3";
        this.name = name;


    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
    public Album getAlbum() {
        return this.album;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }


    @Override
    public String toString() {
        return "Song" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", picture=" + album.getPicture() +
                ", file='" + file + '\'' +
                ", name='" + album.getArtist().getName();
    }
}