package id.co.asyst.gabriella.louisa.learnrecycerview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.ArrayList;

import id.co.asyst.gabriella.louisa.learnrecycerview.adapter.AlbumAdapter;
import id.co.asyst.gabriella.louisa.learnrecycerview.model.Album;
import id.co.asyst.gabriella.louisa.learnrecycerview.retrofit.ApiClient;
import id.co.asyst.gabriella.louisa.learnrecycerview.retrofit.ApiServices;
import retrofit2.Call;
import retrofit2.Callback;

public class MainActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    AlbumAdapter albumAdapter;
    ArrayList<Album> listAlbum = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);

// getDataWithVolley 03
//        for (int i = 0 ; i < 10 ; i++ ){
//            Album album = new Album();
//            album.setTitle("Title "+i);
//            album.setArtist("Artist "+i);
//            album.setImage("https://images-na.ssl-images-amazon.com/images/I/61McsadO1OL.jpg");
//            listAlbum.add(album);
//        }

//        albumAdapter = new AlbumAdapter(this, listAlbum);
        albumAdapter = new AlbumAdapter(this, listAlbum, new AlbumAdapter.OnItemClickListener() {
            @Override
            public void OnItemClick(Album album) {
                Toast.makeText(getApplicationContext(), album.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(albumAdapter);

        // getDataWithVolley 02
//        getDataWithVolley();

        getDataWithRetrofit();

    }

    //getDataWhitVolley 01
    public void getDataWithVolley() {
        final RequestQueue requestQueue = Volley.newRequestQueue(this);
        String url = "http://rallycoding.herokuapp.com/api/music_albums";
        JsonArrayRequest jsonArrayRequest = new JsonArrayRequest(Request.Method.GET, url, null, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                Log.d("MovieResponse", response.toString());
                try {
                    for (int i = 0; i < response.length(); i++) {
                        Album album = new Gson().fromJson(response.getString(i), Album.class);
                        listAlbum.add(album);
                    }
                    albumAdapter.notifyDataSetChanged();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonArrayRequest);
    }

    //retrofit
    public void getDataWithRetrofit() {
        ApiServices apiServices = ApiClient.newInstance(getApplicationContext()).create(ApiServices.class);
        Call<ArrayList<Album>> call = apiServices.getAlbums();
        call.enqueue(new Callback<ArrayList<Album>>() {
            @Override
            public void onResponse(Call<ArrayList<Album>> call, retrofit2.Response<ArrayList<Album>> response) {
                //ifSuccess
                if (response.body() != null) {
                    if (response.body().size() > 0) {
                        listAlbum.addAll(response.body());
                        albumAdapter.notifyDataSetChanged();
                    }
                }
            }

            @Override
            public void onFailure(Call<ArrayList<Album>> call, Throwable t) {
                //ifFailed

            }
        });
    }
}
