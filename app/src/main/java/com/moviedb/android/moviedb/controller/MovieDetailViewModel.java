package com.moviedb.android.moviedb.controller;

import android.databinding.BindingAdapter;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.moviedb.android.moviedb.common.StaticVariables;
import com.moviedb.android.moviedb.util.DateUtils;
import com.moviedb.android.moviedb.util.GlideUtils;
import com.moviedb.android.moviedb.view.ui.IconicLabelView;

import java.text.DateFormat;
import java.util.Date;

public class MovieDetailViewModel {

    @BindingAdapter({StaticVariables.URL_BINDING_KEY, StaticVariables.GLIDE_BINDING_KEY, StaticVariables.SIZE_BINDING_KEY, StaticVariables.RES_BINDING_KEY})
    public static void setImageUrl(ImageView imageView, String url, GlideUtils glideUtils, @GlideUtils.IMAGE_SIZE_ENUM int size, Drawable res) {
        glideUtils.loadImage(res, size, url, imageView);
    }

    @BindingAdapter({StaticVariables.DATE_BINDING_KEY})
    public static void setDate(IconicLabelView labelView, String dateText) {
        Date date = DateUtils.convertToDate(dateText, StaticVariables.DEFAULT_DATE_FORMAT);
        if (date != null)
            labelView.setLabel(DateUtils.formatDate(date, DateFormat.LONG).toUpperCase());
    }
}
