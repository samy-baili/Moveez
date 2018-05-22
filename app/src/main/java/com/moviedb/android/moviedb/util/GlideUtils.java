package com.moviedb.android.moviedb.util;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.IntDef;
import android.support.v4.content.ContextCompat;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.moviedb.android.moviedb.common.StaticVariables;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public class GlideUtils {

    private final RequestManager glide;
    private Context context;

    public GlideUtils(Context context) {
        this.context = context;
        this.glide = Glide.with(context);
    }

    public void loadImage(@DrawableRes int resource, @IMAGE_SIZE_ENUM final int size, final String url, final ImageView imageView) {
        loadImage(ContextCompat.getDrawable(context, resource), size, url, imageView);
    }

    public void loadImage(Drawable drawable, @IMAGE_SIZE_ENUM final int size, final String url, final ImageView imageView) {
        if (url != null) {
            glide.applyDefaultRequestOptions(new RequestOptions().placeholder(drawable)).load(createURL(size, url)).transition(DrawableTransitionOptions.withCrossFade()).into(imageView);
            return;
        }
        glide.load(drawable).into(imageView);
    }

    private String createURL(@IMAGE_SIZE_ENUM final int size, final String url) {
        return StaticVariables.IMAGE_BASE_URL + String.valueOf(size) + url;
    }

    @IntDef({
            StaticVariables.POSTER_DEFAULT_SIZE,
            StaticVariables.BANNER_DEFAULT_SIZE
    })
    @Retention(RetentionPolicy.SOURCE)
    public @interface IMAGE_SIZE_ENUM {}
}
