package com.moviedb.android.moviedb.common;

public class StaticVariables {

    private StaticVariables() {}

    /************************ API ************************/

    public final static String DISCOVER_MOVIE_API = "discover/movie";
    public final static String DEFAULT_LANGUAGE = "en-US";
    public final static String DEFAULT_SORT = "popularity.desc";
    public final static int DICOVERY_QUERY_PAGINATION = 20;
    public final static int READ_TIME_OUT = 30;
    public final static int CONNECTION_TIME_OUT = 30;



    /************************ PARAM ************************/

    public final static String API_KEY_PARAM = "api_key";
    public final static String LANGUAGE_PARAM = "language";
    public final static String PAGE_PARAM = "page";
    public final static String PRIMARY_RELEASE_YEAR_PARAM = "primary_release_year";
    public final static String SORT_BY_PARAM = "sort_by";
    public final static String INCLUDE_ADULT_PARAM = "include_adult";



    /************************ SERIALIZED NAME ************************/

    public final static String RESULTS_SERIALIZED_NAME = "results";
    public final static String PAGE_SERIALIZED_NAME = "page";
    public final static String TOTAL_PAGES_SERIALIZED_NAME = "total_pages";
    public final static String POSTER_PATH_SERIALIZED_NAME = "poster_path";
    public final static String RELEASE_DATE_SERIALIZED_NAME = "release_date";
    public final static String ORIGINAL_TITLE_SERIALIZED_NAME = "original_title";
    public final static String ORIGINAL_LANGUAGE_SERIALIZED_NAME = "original_language";
    public final static String VOTE_COUNT_SERIALIZED_NAME = "vote_count";
    public final static String VOTE_AVERAGE_SERIALIZED_NAME = "vote_average";
    public final static String ID_SERIALIZED_NAME = "id";
    public final static String OVERVIEW_SERIALIZED_NAME = "overview";
    public final static String TITLE_SERIALIZED_NAME = "title";
    public final static String POPULARITY_SERIALIZED_NAME = "popularity";
    public final static String ADULT_SERIALIZED_NAME = "adult";
    public final static String VIDEO_SERIALIZED_NAME = "video";
    public final static String BACKDROP_PATH_SERIALIZED_NAME = "backdrop_path";



    /************************ REALM KEY ************************/

    public final static String RELEASE_DATE_REALM_KEY = "releaseDate";
    public final static String POPULARITY_REALM_KEY = "popularity";
    public final static String ID_REALM_KEY = "id";



    /************************ INTENT KEY ************************/

    public final static String MOVIE_ID_INTENT_KEY = "movie_id";



    /************************ BINDING KEY ************************/

    public final static String GLIDE_BINDING_KEY = "glide";
    public final static String SIZE_BINDING_KEY = "size";
    public final static String RES_BINDING_KEY = "res";
    public final static String URL_BINDING_KEY = "url";
    public final static String DATE_BINDING_KEY = "date";



    /************************ IMAGE ************************/

    public final static String IMAGE_BASE_URL = "http://image.tmdb.org/t/p/w";
    public final static int POSTER_DEFAULT_SIZE = 200;
    public final static int BANNER_DEFAULT_SIZE = 500;



    /************************ DATE FORMAT ************************/

    public final static String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";
}
