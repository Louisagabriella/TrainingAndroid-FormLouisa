package id.co.asyst.gabriella.louisa.mymovie.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Movie implements Parcelable {
    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };
    @SerializedName("title")
    String title;
    @SerializedName("overview")
    String overview;
    @SerializedName("poster_path")
    String image;
    @SerializedName("backdrop_path")
    String backDropPath;
    @SerializedName("release_date")
    String date;

    protected Movie(Parcel in) {
        title = in.readString();
        overview = in.readString();
        image = in.readString();
        date = in.readString();
        backDropPath = in.readString();
    }

    public static Creator<Movie> getCREATOR() {
        return CREATOR;
    }

    public String getBackDropPath() {
        return backDropPath;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(overview);
        dest.writeString(image);
        dest.writeString(date);
        dest.writeString(backDropPath);
    }
}
