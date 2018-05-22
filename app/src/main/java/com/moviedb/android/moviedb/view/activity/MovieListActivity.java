package com.moviedb.android.moviedb.view.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.ProgressBar;

import com.moviedb.android.moviedb.R;
import com.moviedb.android.moviedb.controller.MovieListViewModel;
import com.moviedb.android.moviedb.view.ui.YearSpinner;

public class MovieListActivity extends AppCompatActivity implements YearSpinner.OnSelectYearItemListener, MovieListViewModel.OnDisplayLoaderListener {

    private MovieListViewModel viewModel;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_list);

        init();
    }

    /**
     * Init activity views and viewModel
     */
    private void init() {
        this.recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        this.progressBar = findViewById(R.id.progress_bar);

        this.viewModel = new MovieListViewModel(this);
        viewModel.setOnDisplayLoaderListener(this);
        viewModel.setAdapter(recyclerView);

        setSupportActionBar((Toolbar) findViewById(R.id.toolbar));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        viewModel.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main_activity, menu);

        initSpinner(menu);

        return true;
    }

    /**
     * Called each time a year is selected via the spinner
     * @param year The current year selected
     */
    @Override
    public void yearItemSelected(String year) {
        viewModel.yearSelected(year);
        recyclerView.scrollToPosition(0);
    }

    /**
     * Init the spinner from the menu
     * @param menu Current menu activity
     */
    private void initSpinner(Menu menu) {
        YearSpinner spinner = (YearSpinner) menu.findItem(R.id.year_spinner_menu_item).getActionView();
        spinner.setOnSelectYearItemListener(this);
    }

    /**
     * Callback to display the loader
     */
    @Override
    public void displayLoader(boolean display) {
        progressBar.setVisibility(display ? View.VISIBLE : View.GONE);
    }
}
