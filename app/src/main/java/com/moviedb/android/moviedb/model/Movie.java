package com.moviedb.android.moviedb.model;

import com.google.gson.annotations.SerializedName;
import com.moviedb.android.moviedb.common.StaticVariables;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Movie extends RealmObject {

    @PrimaryKey
    @SerializedName(StaticVariables.ID_SERIALIZED_NAME)
    public int id;

    @SerializedName(StaticVariables.RELEASE_DATE_SERIALIZED_NAME)
    public String releaseDate;

    @SerializedName(StaticVariables.POSTER_PATH_SERIALIZED_NAME)
    public String posterPath;

    @SerializedName(StaticVariables.OVERVIEW_SERIALIZED_NAME)
    public String overview;

    @SerializedName(StaticVariables.TITLE_SERIALIZED_NAME)
    public String title;

    @SerializedName(StaticVariables.ORIGINAL_TITLE_SERIALIZED_NAME)
    public String originalTitle;

    @SerializedName(StaticVariables.ORIGINAL_LANGUAGE_SERIALIZED_NAME)
    public String originalLanguage;

    @SerializedName(StaticVariables.BACKDROP_PATH_SERIALIZED_NAME)
    public String backdropPath;

    @SerializedName(StaticVariables.VOTE_AVERAGE_SERIALIZED_NAME)
    public float voteAverage;

    @SerializedName(StaticVariables.VOTE_COUNT_SERIALIZED_NAME)
    public int voteCount;

    @SerializedName(StaticVariables.POPULARITY_SERIALIZED_NAME)
    public float popularity;

    @SerializedName(StaticVariables.ADULT_SERIALIZED_NAME)
    public boolean adult;

    @SerializedName(StaticVariables.VIDEO_SERIALIZED_NAME)
    public boolean video;
}
