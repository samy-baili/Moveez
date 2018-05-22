package com.moviedb.android.moviedb.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;

import com.moviedb.android.moviedb.model.Movie;
import com.moviedb.android.moviedb.util.GlideUtils;
import com.moviedb.android.moviedb.view.ui.MovieCardView;

import io.realm.OrderedRealmCollection;
import io.realm.RealmRecyclerViewAdapter;

public class MovieAdapter extends RealmRecyclerViewAdapter<Movie, MovieAdapter.MovieViewHolder> {

    private Context context;
    private GlideUtils glideUtils;
    private OnMovieSelectedListener movieSelectedlistener;
    private OnBottomListReached bottomReachedlistener;

    public MovieAdapter(Context context, @Nullable OrderedRealmCollection<Movie> data) {
        super(data, true);
        this.context = context;
        this.glideUtils = new GlideUtils(context);
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MovieViewHolder(new MovieCardView(context));
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        holder.configData(getItem(position));

        if (bottomReachedlistener != null && bottomIsReached(position))
            bottomReachedlistener.bottomReached();
    }

    private boolean bottomIsReached(int position) {
        int totalCount = getItemCount();
        return (totalCount > 4) && (position == totalCount - 4);
    }

    public void setOnMovieSelectedListener(OnMovieSelectedListener listener) {
        this.movieSelectedlistener = listener;
    }

    public void setOnBottomListReachedListener(OnBottomListReached listener) {
        this.bottomReachedlistener = listener;
    }

    class MovieViewHolder extends RecyclerView.ViewHolder {

        MovieViewHolder(View view) {
            super(view);
        }

        void configData(final Movie movie) {
            MovieCardView movieCardView = (MovieCardView) itemView;

            movieCardView.setTitleValue(movie.title);
            movieCardView.setOverviewValue(movie.overview);
            movieCardView.setVoteAverageValue(movie.voteAverage);

            movieCardView.setPosterPicture(glideUtils, movie.posterPath);
            movieCardView.setBannerPicture(glideUtils, movie.backdropPath);

            movieCardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (movieSelectedlistener != null)
                        movieSelectedlistener.movieSelected(movie);
                }
            });
        }
    }

    public interface OnMovieSelectedListener {
        void movieSelected(Movie movie);
    }

    public interface OnBottomListReached {
        void bottomReached();
    }
}
