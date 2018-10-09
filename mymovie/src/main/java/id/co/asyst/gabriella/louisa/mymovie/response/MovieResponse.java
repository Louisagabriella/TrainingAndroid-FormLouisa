package id.co.asyst.gabriella.louisa.mymovie.response;


import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

import id.co.asyst.gabriella.louisa.mymovie.model.Movie;

public class MovieResponse {
    int page;
    @SerializedName("total_results")
    int totalResults;
    @SerializedName("total_pages")
    int totalPages;
    @SerializedName("results")
    ArrayList<Movie> results;

    public int getPage() {
        return page;
    }

    public int getTotalResults() {
        return totalResults;
    }

    public int getTotalPages() {
        return totalPages;
    }

    public ArrayList<Movie> getResults() {
        return results;
    }

    public void setResults(ArrayList<Movie> results) {
        this.results = results;
    }
}
