package com.moviedb.android.moviedb.controller;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.widget.Toast;

import com.moviedb.android.moviedb.R;
import com.moviedb.android.moviedb.api.NetworkManager;
import com.moviedb.android.moviedb.api.Response;
import com.moviedb.android.moviedb.common.IntentHelper;
import com.moviedb.android.moviedb.common.StaticVariables;
import com.moviedb.android.moviedb.listener.ControllerListener;
import com.moviedb.android.moviedb.model.Movie;
import com.moviedb.android.moviedb.util.RealmUtils;
import com.moviedb.android.moviedb.view.adapter.MovieAdapter;

import java.util.List;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;
import retrofit2.Call;
import retrofit2.Callback;

public class MovieListViewModel implements MovieAdapter.OnMovieSelectedListener, MovieAdapter.OnBottomListReached, ControllerListener {

    private Context context;
    private Realm realm;
    private NetworkManager networkManager;
    private MovieAdapter adapter;

    private Call<Response> call;

    private int currentPage;
    private String currentYear;

    private boolean allPageRequested;

    private OnDisplayLoaderListener listener;

    public MovieListViewModel(Context context) {
        this.context = context;
        this.realm = Realm.getDefaultInstance();
        this.currentPage = 1;
        this.allPageRequested = false;
        this.networkManager = new NetworkManager();
        this.currentYear = context.getString(R.string.year_default_value);
        this.adapter = createAdapter(currentYear);
    }

    /**
     * Init the movies adapter
     * @param year The year selected
     */
    private MovieAdapter createAdapter(@NonNull String year) {
        MovieAdapter adapter = new MovieAdapter(context, createMovieResult(year));
        adapter.setOnMovieSelectedListener(this);
        adapter.setOnBottomListReachedListener(this);
        return adapter;
    }

    /**
     * Called each time a new year is selected
     * @param year The new year selected
     */
    public void yearSelected(@NonNull String year) {
        if (!TextUtils.isDigitsOnly(year) || year.length() != 4)
            return;

        launchProcess(year);
    }

    /**
     * Launch this process each time a new year is selected
     *
     * @param year
     */
    private void launchProcess(@NonNull String year) {
        this.currentPage = 1;
        this.currentYear = year;
        this.allPageRequested = false;

        cancelRequest(call);

        adapter.updateData(createMovieResult(year));

        checkMoviesAvailable(year);
    }

    /**
     * Check if there is movies available in local for the specific year
     *
     * Launch the getMovies query if there is not movies in local
     *
     * @param year
     */
    private void checkMoviesAvailable(String year) {
        if (RealmUtils.getMoviesByYear(realm, year).count() == 0) {
            checkDisplayLoaderListener(true);
            launchGetMoviesQuery(currentPage, year);
        }
    }

    /**
     * Add a change listener on the realm, in order to get the last page selected from the API
     * @param year The year selected
     * @return
     */
    private RealmResults<Movie> createMovieResult(final String year) {
        final RealmResults<Movie> results = RealmUtils.createMovieByYearQuery(realm, year).findAllAsync();
        results.addChangeListener(new RealmChangeListener<RealmResults<Movie>>() {
            @Override
            public void onChange(RealmResults<Movie> movies) {
                int size = movies.size();
                if (size > 0)
                    currentPage = (int) Math.ceil((double) size / StaticVariables.DICOVERY_QUERY_PAGINATION) + 1;

                movies.removeChangeListener(this);
            }
        });
        return results;
    }

    /**
     * Request the movies from the API, sorted by popularity and filtered by year
     *
     * @param page Get page movies from the API
     * @param year Get year movies from the API
     */
    private void launchGetMoviesQuery(int page, @NonNull String year) {
        this.call = networkManager.getMovies(page, Integer.valueOf(year));
        call.enqueue(new Callback<Response>() {
            @Override
            public void onResponse(@NonNull Call<Response> call, @NonNull retrofit2.Response<Response> response) {
                Response movieResponse = response.body();

                if (response.code() == 200 && movieResponse != null) {
                    allPageRequested = movieResponse.totalPages == currentPage;

                    List<Movie> movies = movieResponse.movies;

                    if (movies.size() > 0) {
                        currentPage = movieResponse.page + 1;
                        saveDataRealm(movies);
                    }
                }
                else
                    displayErrorMessage();

                checkDisplayLoaderListener(false);
            }

            @Override
            public void onFailure(@NonNull Call<Response> call, @NonNull Throwable t) {
                checkDisplayLoaderListener(false);
                displayErrorMessage();
                t.printStackTrace();
            }
        });
    }

    /**
     * Save the data in Realm
     *
     * @param movies The movies to save in local
     */
    private void saveDataRealm(final List<Movie> movies) {
        realm.executeTransactionAsync(new Realm.Transaction() {
            @Override
            public void execute(@NonNull Realm realm) {
                realm.copyToRealmOrUpdate(movies);
            }
        });
    }

    /**
     * Cancel the running request
     *
     * @param call
     */
    private void cancelRequest(Call<Response> call) {
        if (call != null)
            call.cancel();
    }

    /**
     * Display a toast error message, if the request failed
     */
    private void displayErrorMessage() {
        Toast.makeText(context, R.string.network_error_message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Called each time a movie is selected
     *
     * @param movie The movie selected
     */
    @Override
    public void movieSelected(Movie movie) {
        context.startActivity(IntentHelper.getMovieDetailActivity(context, movie.id));
    }

    /**
     * Called each time the bottom movie list is reached
     */
    @Override
    public void bottomReached() {
        if (!allPageRequested)
            launchGetMoviesQuery(currentPage, currentYear);
    }

    /**
     * Check if the display listener is not null
     */
    private void checkDisplayLoaderListener(boolean display) {
        if (listener != null)
            listener.displayLoader(display);
    }

    /**
     * Set the display loader listener
     *
     * @param listener Listener to detect each time the loader should be displayed or not
     */
    public void setOnDisplayLoaderListener(OnDisplayLoaderListener listener) {
        this.listener = listener;
    }

    public void setAdapter(RecyclerView recyclerView) {
        recyclerView.setAdapter(adapter);
    }

    @Override
    public void onCreate() {

    }

    @Override
    public void onDestroy() {
        cancelRequest(call);
    }

    @Override
    public void onResume() {

    }

    @Override
    public void onPause() {

    }

    public interface OnDisplayLoaderListener {
        void displayLoader(boolean display);
    }
}
