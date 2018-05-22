package com.moviedb.android.moviedb.api;

import com.moviedb.android.moviedb.common.StaticVariables;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiCall {

    @GET(StaticVariables.DISCOVER_MOVIE_API)
    Call<Response> getMovies(@Query(StaticVariables.API_KEY_PARAM) String apiKey, @Query(StaticVariables.PAGE_PARAM) int page,
                             @Query(StaticVariables.PRIMARY_RELEASE_YEAR_PARAM) int year, @Query(StaticVariables.LANGUAGE_PARAM) String language,
                             @Query(StaticVariables.SORT_BY_PARAM) String sort, @Query(StaticVariables.INCLUDE_ADULT_PARAM) boolean includeAdult);
}
