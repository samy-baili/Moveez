package com.moviedb.android.moviedb.view.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.CardView;
import android.util.AttributeSet;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.moviedb.android.moviedb.R;
import com.moviedb.android.moviedb.common.StaticVariables;
import com.moviedb.android.moviedb.util.DensityUtils;
import com.moviedb.android.moviedb.util.GlideUtils;

public class MovieCardView extends CardView {

    private ImageView backdropImageView;
    private ImageView posterImageView;

    private TextView titleView;
    private TextView overviewView;
    private IconicLabelView iconicsLabelView;

    public MovieCardView(@NonNull Context context) {
        super(context);

        init(context);
    }

    public MovieCardView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        init(context);
        initAttr(context, attrs);
    }

    public MovieCardView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        init(context);
        initAttr(context, attrs);
    }

    private void init(Context context) {
        initCardView(context);
        initView(context);
    }

    private void initCardView(Context context) {
        setCardBackgroundColor(Color.WHITE);
        setRadius(context.getResources().getDimensionPixelSize(R.dimen.movie_card_corner_radius));
        setUseCompatPadding(true);

        int margin = DensityUtils.dpToPx(context, 5);
        FrameLayout.LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(margin, margin / 2, margin, margin / 2);
        setLayoutParams(params);
    }

    private void initView(Context context) {
        inflate(context, R.layout.movie_card_view_layout, this);

        this.backdropImageView = findViewById(R.id.backdrop_image_view);
        this.posterImageView = findViewById(R.id.poster_image_view);

        this.titleView = findViewById(R.id.title_text_view);
        this.overviewView = findViewById(R.id.overview_text_view);
        this.iconicsLabelView = findViewById(R.id.iconics_label_view);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MovieCardView);
        setTitleValue(a.getString(R.styleable.MovieCardView_title));
        setOverviewValue(a.getString(R.styleable.MovieCardView_overview));
        setVoteAverageValue(a.getFloat(R.styleable.MovieCardView_vote_average, 0));
        a.recycle();
    }

    public void setTitleValue(String title) {
        titleView.setText(title);
    }

    public void setOverviewValue(String overview) {
        overviewView.setText(overview);
    }

    public void setVoteAverageValue(float voteAverage) {
        iconicsLabelView.setLabel(String.valueOf(voteAverage));
    }

    public void setBannerPicture(GlideUtils glideUtils, String path) {
        setPicture(glideUtils, R.drawable.banner_placeholder, StaticVariables.BANNER_DEFAULT_SIZE, path, backdropImageView);
    }

    public void setPosterPicture(GlideUtils glideUtils, String path) {
        setPicture(glideUtils, R.drawable.poster_placeholder, StaticVariables.POSTER_DEFAULT_SIZE, path, posterImageView);
    }

    private void setPicture(GlideUtils glideUtils, @DrawableRes int drawableRes, @GlideUtils.IMAGE_SIZE_ENUM int size, String path, ImageView imageView) {
        glideUtils.loadImage(drawableRes, size, path, imageView);
    }
}
