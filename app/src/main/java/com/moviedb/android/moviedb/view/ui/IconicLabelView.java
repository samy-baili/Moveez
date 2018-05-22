package com.moviedb.android.moviedb.view.ui;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.mikepenz.iconics.view.IconicsTextView;
import com.moviedb.android.moviedb.R;

public class IconicLabelView extends FrameLayout {

    private IconicsTextView iconicsTextView;
    private TextView labelTextView;

    public IconicLabelView(@NonNull Context context) {
        super(context);

        initView(context);
    }

    public IconicLabelView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        initView(context);
        initAttr(context, attrs);
    }

    public IconicLabelView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        initView(context);
        initAttr(context, attrs);
    }

    private void initView(Context context) {
        inflate(context, R.layout.iconic_label_view_layout, this);

        this.iconicsTextView = findViewById(R.id.icon_text_view);
        this.labelTextView = findViewById(R.id.label_text_view);
    }

    private void initAttr(Context context, AttributeSet attrs) {
        Resources res = context.getResources();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.IconicLabelView);
        setIcon(a.getString(R.styleable.IconicLabelView_iconFont));
        setLabel(a.getString(R.styleable.IconicLabelView_label));
        setIconSize(TypedValue.COMPLEX_UNIT_PX, a.getDimensionPixelSize(R.styleable.IconicLabelView_iconSize, (int) res.getDimension(R.dimen.icon_default_size)));
        setLabelSize(TypedValue.COMPLEX_UNIT_PX, a.getDimensionPixelSize(R.styleable.IconicLabelView_labelSize, (int) res.getDimension(R.dimen.label_default_size)));
        a.recycle();
    }

    public void setIcon(String icon) {
        iconicsTextView.setText(icon);
    }

    public void setLabel(String label) {
        labelTextView.setText(label);
    }

    public void setIconSize(int unit, float size) {
        iconicsTextView.setTextSize(unit, size);
    }

    public void setLabelSize(int unit, float size) {
        labelTextView.setTextSize(unit, size);
    }
}
