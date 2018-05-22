package com.moviedb.android.moviedb.view.ui;

import android.content.Context;
import android.support.v7.widget.AppCompatSpinner;
import android.util.AttributeSet;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import com.moviedb.android.moviedb.R;
import com.moviedb.android.moviedb.util.DensityUtils;

public class YearSpinner extends AppCompatSpinner {

    private OnSelectYearItemListener listener;

    public YearSpinner(Context context) {
        super(context);
        init(context);
    }

    public YearSpinner(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public YearSpinner(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        final String[] years = context.getResources().getStringArray(R.array.years_array);
        ArrayAdapter<String> yearAdapter = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, years);
        yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        setAdapter(yearAdapter);

        setDropDownWidth(DensityUtils.dpToPx(context, 100));

        setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (listener != null)
                    listener.yearItemSelected(years[position]);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {}
        });
    }

    public void setOnSelectYearItemListener(OnSelectYearItemListener listener) {
        this.listener = listener;
    }

    public interface OnSelectYearItemListener {
        void yearItemSelected(String year);
    }
}
