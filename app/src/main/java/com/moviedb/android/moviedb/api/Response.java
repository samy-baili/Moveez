package com.moviedb.android.moviedb.api;

import com.google.gson.annotations.SerializedName;
import com.moviedb.android.moviedb.common.StaticVariables;
import com.moviedb.android.moviedb.model.Movie;

import java.util.List;

public class Response {

    @SerializedName(StaticVariables.PAGE_SERIALIZED_NAME)
    public int page;

    @SerializedName(StaticVariables.TOTAL_PAGES_SERIALIZED_NAME)
    public int totalPages;

    @SerializedName(StaticVariables.RESULTS_SERIALIZED_NAME)
    public List<Movie> movies;
}
