package id.co.asyst.gabriella.louisa.learnrecycerview.retrofit;

import java.util.ArrayList;

import id.co.asyst.gabriella.louisa.learnrecycerview.model.Album;
import retrofit2.Call;
import retrofit2.http.GET;

//getDataWithRetrofit
public interface ApiServices {
    @GET("music_albums")
    Call<ArrayList<Album>> getAlbums();
}
