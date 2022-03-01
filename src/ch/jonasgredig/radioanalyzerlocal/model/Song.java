package ch.jonasgredig.radioanalyzerlocal.model;

public class Song {
    private int id;
    private String artist;
    private String songName;
    private String timestamp;

    public Song(int id, String artist, String songName, String timestamp) {
        this.id = id;
        this.artist = artist;
        this.songName = songName;
        this.timestamp = timestamp;
    }

    public Song() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getSongName() {
        return songName;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }
}
