package com.moviedb.android.moviedb.view.activity;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.MenuItem;
import android.widget.TextView;

import com.moviedb.android.moviedb.R;
import com.moviedb.android.moviedb.common.StaticVariables;
import com.moviedb.android.moviedb.databinding.ActivityMovieDetailBinding;
import com.moviedb.android.moviedb.model.Movie;
import com.moviedb.android.moviedb.util.GlideUtils;

import io.realm.Realm;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Movie movie = extractMovie();

        if (movie == null) {
            finish();
            return;
        }

        initBinding(movie);

        initView();
    }

    /**
     * Extract the movie id from the intent
     *
     * @return Return the movie id or null if the movie id doesn't exist
     */
    private Movie extractMovie() {
        Intent intent = getIntent();
        if (intent != null) {
            int movieID = intent.getIntExtra(StaticVariables.MOVIE_ID_INTENT_KEY, -1);
            if (movieID != -1)
                return getMovie(movieID);
        }
        return null;
    }

    /**
     * Get the movie from the realm database
     *
     * @param movieID The movie id of the selected movie
     * @return The movie selected
     */
    private Movie getMovie(int movieID) {
        return Realm.getDefaultInstance().where(Movie.class).equalTo(StaticVariables.ID_REALM_KEY, movieID).findFirst();
    }

    /**
     * Init the data binding with the movie
     *
     * @param movie The movie selected
     */
    private void initBinding(Movie movie) {
        ActivityMovieDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_movie_detail);

        binding.setGlideUtils(new GlideUtils(this));
        binding.setMovie(movie);
    }

    /**
     * Init activity UI
     */
    private void initView() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setDisplayHomeAsUpEnabled(true);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            TextView overviewTextView = findViewById(R.id.overview_text_view);
            overviewTextView.setJustificationMode(Layout.JUSTIFICATION_MODE_INTER_WORD);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home)
            finish();

        return super.onOptionsItemSelected(item);
    }
}
