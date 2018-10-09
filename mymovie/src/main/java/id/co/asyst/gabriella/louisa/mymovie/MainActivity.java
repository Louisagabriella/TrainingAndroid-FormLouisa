package id.co.asyst.gabriella.louisa.mymovie;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import id.co.asyst.gabriella.louisa.mymovie.adapter.MovieAdapter;
import id.co.asyst.gabriella.louisa.mymovie.constant.Constant;
import id.co.asyst.gabriella.louisa.mymovie.fragment.SearchByFilterFragment;
import id.co.asyst.gabriella.louisa.mymovie.fragment.SearchSortByFragment;
import id.co.asyst.gabriella.louisa.mymovie.model.Movie;
import id.co.asyst.gabriella.louisa.mymovie.response.MovieResponse;
import id.co.asyst.gabriella.louisa.mymovie.retrofit.ApiClient;
import id.co.asyst.gabriella.louisa.mymovie.retrofit.ApiService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity implements SearchByFilterFragment.OnSubmitButtonClickListener, SearchSortByFragment.OnSubmitButtonListener {

    ProgressBar progressBarScroll;
    RecyclerView recyclerView;
    MovieAdapter movieAdapter;
    ArrayList<Movie> listMovie = new ArrayList<>();
    String query = "";
    String year = "";
    String sort_by = "";
    TextView tvError;
    boolean isLoading = false;
    int totalPage, page = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        tvError = findViewById(R.id.textViewError);
        progressBarScroll = findViewById(R.id.progressbarScroll);

        movieAdapter = new MovieAdapter(this, listMovie, new MovieAdapter.OnItemClickListener() {
            @Override
            public void OnItemCick(Movie movie) {
                Intent intent = new Intent(MainActivity.this, ViewActivity.class);
                intent.putExtra("movie", movie);
                startActivity(intent);
            }
        });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());

        recyclerView.setAdapter(movieAdapter);
        retrofit();

        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!recyclerView.canScrollVertically(1)) {
                    if (!isLoading) {
                        if (totalPage >= page) {
                            isLoading = true;
                            if (query.equalsIgnoreCase("")) {
                                retrofit();
                            } else {
                                searchData();
                            }
                            progressBarScroll.setVisibility(View.VISIBLE);
                        }
                    }
                }
            }

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
            }
        });
    }

    public void retrofit() {
        ApiService apiService = ApiClient.newInstance(getApplicationContext()).create(ApiService.class);
        Call<MovieResponse> call = apiService.getMovies(Constant.KEY_API, year, page, sort_by);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                isLoading = false;
                if (response.body() != null) {
                    if (response.body().getResults().size() > 0) {
                        listMovie.addAll(response.body().getResults());
                        movieAdapter.notifyDataSetChanged();
                        page = response.body().getPage() + 1;
                        totalPage = response.body().getTotalPages();
                        tvError.setVisibility(View.GONE);
                        progressBarScroll.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                recyclerView.setVisibility(View.GONE);
                progressBarScroll.setVisibility(View.GONE);
            }
        });
    }

    public void searchData() {
        ApiService apiService = ApiClient.newInstance(getApplicationContext()).create(ApiService.class);
        Call<MovieResponse> call = apiService.getMovies(Constant.KEY_API, query, page);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                isLoading = false;
                if (response.body() != null) {
                    if (response.body().getResults().size() > 0) {
                        listMovie.addAll(response.body().getResults());
                        movieAdapter.notifyDataSetChanged();
                        page = response.body().getPage() + 1;
                        totalPage = response.body().getTotalPages();
                        tvError.setVisibility(View.GONE);
                        progressBarScroll.setVisibility(View.GONE);
                    }
                }
            }

            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                recyclerView.setVisibility(View.GONE);
                progressBarScroll.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case R.id.main_menu_filter_by_name:
                SearchSortByFragment searchSortByFragment = new SearchSortByFragment();
                searchSortByFragment.show(getSupportFragmentManager(), null);
                break;
            case R.id.main_menu_filter_by_year:
                SearchByFilterFragment searchByYearFragment = SearchByFilterFragment.newInstance(this.year, this.sort_by);
                searchByYearFragment.show(getSupportFragmentManager(), null);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onSubmitButton(String year, String search) {
        query = "";
        this.year = year;
        this.sort_by = search;
        listMovie.clear();
        movieAdapter.notifyDataSetChanged();
        page = 1;
        retrofit();
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public void onClickSubmit(String keyWord) {
        this.query = keyWord;
        listMovie.clear();
        movieAdapter.notifyDataSetChanged();
        page = 1;
        searchData();
    }
}
