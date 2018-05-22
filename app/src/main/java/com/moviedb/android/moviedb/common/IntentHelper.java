package com.moviedb.android.moviedb.common;

import android.content.Context;
import android.content.Intent;

import com.moviedb.android.moviedb.view.activity.MovieDetailActivity;

public class IntentHelper {

    private IntentHelper() {}

    public static Intent getMovieDetailActivity(Context context, int movieId) {
        Intent intent = new Intent(context, MovieDetailActivity.class);
        intent.putExtra(StaticVariables.MOVIE_ID_INTENT_KEY, movieId);
        return intent;
    }
}
