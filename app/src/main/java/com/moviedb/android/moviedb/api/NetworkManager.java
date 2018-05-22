package com.moviedb.android.moviedb.api;

import com.google.gson.Gson;
import com.moviedb.android.moviedb.BuildConfig;
import com.moviedb.android.moviedb.common.StaticVariables;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkManager {

    private ApiCall apiCall;

    public NetworkManager() {
        init();
    }

    private void init() {
        final OkHttpClient.Builder okHttpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(StaticVariables.CONNECTION_TIME_OUT, TimeUnit.SECONDS)
                .readTimeout(StaticVariables.READ_TIME_OUT, TimeUnit.SECONDS);

        this.apiCall = new Retrofit.Builder()
                .client(okHttpClientBuilder.build())
                .addConverterFactory(GsonConverterFactory.create(new Gson()))
                .baseUrl(BuildConfig.API_BASE_URL)
                .build()
                .create(ApiCall.class);
    }

    public Call<Response> getMovies(int page, int year) {
        return apiCall.getMovies(BuildConfig.MOVIE_API_KEY, page, year, StaticVariables.DEFAULT_LANGUAGE, StaticVariables.DEFAULT_SORT, false);
    }
}
