package com.moviedb.android.moviedb.util;

import android.content.Context;
import android.util.TypedValue;

public class DensityUtils {

    /**
     * Convert density pixel to pixel
     *
     * @param context The current context
     * @param dp The value in dp
     * @return the value in pixel
     */
    public static int dpToPx(Context context, int dp) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
    }
}
