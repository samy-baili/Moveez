package com.moviedb.android.moviedb.util;

import android.support.annotation.NonNull;

import com.moviedb.android.moviedb.common.StaticVariables;
import com.moviedb.android.moviedb.model.Movie;

import io.realm.Realm;
import io.realm.RealmQuery;
import io.realm.Sort;

public class RealmUtils {

    /**
     * Create a realm query to get movies from a specific year, sorted by popularity
     *
     * @param realm Realm instance
     * @param year The filtered year
     * @return The realm query filtered by year and sorted by popularity
     */
    public static RealmQuery<Movie> createMovieByYearQuery(Realm realm, @NonNull String year) {
        return getMoviesByYear(realm, year).sort(StaticVariables.POPULARITY_REALM_KEY, Sort.DESCENDING);
    }

    /**
     * Create a realm query to get movies from a specific year
     *
     * @param realm Realm instance
     * @param year The filtered year
     * @return The realm query filtered by year
     */
    public static RealmQuery<Movie> getMoviesByYear(Realm realm, @NonNull String year) {
        return realm.where(Movie.class).contains(StaticVariables.RELEASE_DATE_REALM_KEY, year);
    }
}
