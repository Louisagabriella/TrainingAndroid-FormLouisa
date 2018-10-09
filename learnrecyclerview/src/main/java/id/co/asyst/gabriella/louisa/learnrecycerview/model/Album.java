package id.co.asyst.gabriella.louisa.learnrecycerview.model;

import com.google.gson.annotations.SerializedName;

public class Album {

    @SerializedName("title")
    String title;
    String artist;
    String image;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
