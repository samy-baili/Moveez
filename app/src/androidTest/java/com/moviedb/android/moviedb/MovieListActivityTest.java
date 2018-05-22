package com.moviedb.android.moviedb;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.action.ViewActions;
import android.support.test.espresso.assertion.ViewAssertions;
import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;

import com.moviedb.android.moviedb.view.activity.MovieListActivity;

import org.hamcrest.CoreMatchers;
import org.hamcrest.Matcher;
import org.hamcrest.core.AllOf;
import org.hamcrest.core.Is;
import org.junit.FixMethodOrder;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@RunWith(AndroidJUnit4.class)
public class MovieListActivityTest  {

    @Rule
    public ActivityTestRule<MovieListActivity> activityRule = new ActivityTestRule<>(MovieListActivity.class);

    @Test
    public void testAClickYearSpinner() {
        Matcher<View> spinnerMatcher = ViewMatchers.withId(R.id.year_spinner_menu_item);

        selectSpinnerItem(spinnerMatcher, "2015");
        selectSpinnerItem(spinnerMatcher, "1991");
        selectSpinnerItem(spinnerMatcher, "2002");
        selectSpinnerItem(spinnerMatcher, "2018");

        try {
            Thread.sleep(1500);
        }
        catch (InterruptedException e) {e.printStackTrace();}
    }

    @Test
    public void testBOpenDetail() {
        Matcher<View> recyclerMatcher = ViewMatchers.withId(R.id.recycler_view);

        selectMovieItem(recyclerMatcher, 10);
        selectMovieItem(recyclerMatcher, 1);
        selectMovieItem(recyclerMatcher, 4);
    }

    @Test
    public void testCScrollBottom() {
        Matcher<View> recyclerMatcher = ViewMatchers.withId(R.id.recycler_view);
        scrollToMovieItem(recyclerMatcher, 20);
    }

    private void selectSpinnerItem(Matcher<View> spinnerMatcher, String year) {
        Espresso.onView(spinnerMatcher).perform(ViewActions.click());
        Espresso.onData(AllOf.allOf(Is.is(CoreMatchers.instanceOf(String.class)), Is.is(year))).perform(ViewActions.click());
        Espresso.onView(spinnerMatcher).check(ViewAssertions.matches(ViewMatchers.withSpinnerText(CoreMatchers.containsString(year))));
    }

    private void selectMovieItem(Matcher<View> recyclerMatcher, int position) {
        Espresso.onView(recyclerMatcher).perform(RecyclerViewActions.actionOnItemAtPosition(position, ViewActions.click()));
        Espresso.pressBack();
    }

    private void scrollToMovieItem(Matcher<View> recyclerMatcher, int position) {
        Espresso.onView(recyclerMatcher).perform(RecyclerViewActions.scrollToPosition(position));
    }
}
