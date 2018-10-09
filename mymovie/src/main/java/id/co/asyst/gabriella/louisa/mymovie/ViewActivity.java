package id.co.asyst.gabriella.louisa.mymovie;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import id.co.asyst.gabriella.louisa.mymovie.model.Movie;
import id.co.asyst.gabriella.louisa.mymovie.utility.DateUtils;

public class ViewActivity extends AppCompatActivity {
    ImageView imageView;
    TextView tvTitle, tvDate, tvOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        imageView = findViewById(R.id.imageViewTampil);
        tvTitle = findViewById(R.id.textViewTitleTampil);
        tvDate = findViewById(R.id.textViewDateTampil);
        tvOverview = findViewById(R.id.textViewOverViewTampil);
        if (getIntent().getExtras() != null) {
            Movie movie = getIntent().getExtras().getParcelable("movie");
            tvTitle.setText(movie.getTitle());
            tvDate.setText(DateUtils.formatDate("yyyy-MM-dd", "dd MMMM yyyy", movie.getDate()));
            tvOverview.setText(movie.getOverview());
            RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.image_3).error(R.drawable.image_3);
            Glide.with(ViewActivity.this).load("https://image.tmdb.org/t/p/w350_and_h196_bestv2" + movie.getBackDropPath()).apply(requestOptions).into(imageView);
        }
    }
}
