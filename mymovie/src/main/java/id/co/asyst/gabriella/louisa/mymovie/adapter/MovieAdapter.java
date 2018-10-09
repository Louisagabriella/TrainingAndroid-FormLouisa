package id.co.asyst.gabriella.louisa.mymovie.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;

import java.util.ArrayList;

import id.co.asyst.gabriella.louisa.mymovie.R;
import id.co.asyst.gabriella.louisa.mymovie.constant.Constant;
import id.co.asyst.gabriella.louisa.mymovie.model.Movie;
import id.co.asyst.gabriella.louisa.mymovie.utility.DateUtils;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MyViewHolder> {

    Context mContext;
    ArrayList<Movie> mListMovie;
    OnItemClickListener mlistener;

    public MovieAdapter(Context context, ArrayList<Movie> listMovie, OnItemClickListener listener) {
        this.mContext = context;
        this.mListMovie = listMovie;
        this.mlistener = listener;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View ivItem = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_movie, parent, false);
        return new MovieAdapter.MyViewHolder(ivItem);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        final Movie movie = mListMovie.get(position);
        holder.tvTitle.setText(movie.getTitle());
        if (movie.getDate().equalsIgnoreCase("")) {
            holder.tvDate.setText("-");
        } else {
            holder.tvDate.setText(DateUtils.formatDate("yyyy-MM-dd", "dd MMMM yyyy", movie.getDate()));
        }
        holder.tvOverview.setText(movie.getOverview());
        RequestOptions requestOptions = new RequestOptions().placeholder(R.drawable.ic_image_black_24dp).error(R.drawable.image_4);
        Glide.with(mContext).load(Constant.KEY_LINK + movie.getImage()).apply(requestOptions).into(holder.ivMovie);
        holder.tvReadMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mlistener.OnItemCick(movie);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mListMovie.size();
    }

    public interface OnItemClickListener {
        void OnItemCick(Movie movie);
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        ImageView ivMovie;
        TextView tvTitle, tvDate, tvOverview, tvReadMore;
        CardView cardView;

        public MyViewHolder(View itemView) {
            super(itemView);
            ivMovie = itemView.findViewById(R.id.imageViewMovie);
            tvTitle = itemView.findViewById(R.id.textViewTitle);
            tvDate = itemView.findViewById(R.id.textViewDate);
            tvOverview = itemView.findViewById(R.id.textViewOverView);
            cardView = itemView.findViewById(R.id.cardView);
            tvReadMore = itemView.findViewById(R.id.textViewReadMore);
        }
    }
}
