package com.example.lwd18.pictureselecotor.textsearch.view;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ListView;

public class NoScrollListview extends ListView {

    public NoScrollListview(Context context) {
        super(context);
    }

    public NoScrollListview(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public NoScrollListview(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int mExpandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
                MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, mExpandSpec);
    }


}
