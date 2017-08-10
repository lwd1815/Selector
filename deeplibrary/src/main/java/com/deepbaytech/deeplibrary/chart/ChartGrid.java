package com.deepbaytech.deeplibrary.chart;

import android.content.Context;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.util.SparseArray;
import android.util.TypedValue;

import com.deepbaytech.deeplibrary.R;
import com.deepbaytech.deeplibrary.utils.DisplayUrils;

class ChartGrid {

    /*
    切换横纵坐标间隔
     */
    int stepHor = 10;
    int stepVer = 10;

    int horSubLinesCount = 0;
    int verSubLinesCount = 0;

    boolean horMainLinesEnabled = true;
    boolean horSubLinesEnabled = true;
    boolean verMainLinesEnabled = true;
    boolean verSubLinesEnabled = true;
    boolean verMainValuesEnabled = true;
    boolean horMainValuesEnabled = true;

    Paint mainHorLinesPaint = new Paint();
    Paint mainVerLinesPaint = new Paint();
    Paint subHorLinesPaint = new Paint();
    Paint subVerLinesPaint = new Paint();

    Paint mainVerValuesPaint = new Paint();
    Paint mainHorValuesPaint = new Paint();

    int horValuesMarginBottom = 0;
    int horValuesMarginTop = 0;
    int horValuesMarginLeft = 0;
    int horValuesMarginRight = 0;

    int verValuesMarginBottom = 20;
    int verValuesMarginTop = 0;
    int verValuesMarginLeft = 10;
    int verValuesMarginRight = 10;

    int horValuesAlign = TextAlign.HORIZONTAL_CENTER | TextAlign.BOTTOM;
    int verValuesAlign = TextAlign.RIGHT | TextAlign.VERTICAL_CENTER;

    SparseArray<String> horValuesText = null;
    SparseArray<String> verValuesText = null;

    public ChartGrid(Context context) {
        mainVerLinesPaint.setColor(0xaa888888);
        subVerLinesPaint.setColor(0x44888888);
        mainHorLinesPaint.setColor(0xaa888888);
        subHorLinesPaint.setColor(0x44888888);

        // TODO: 2016/10/9 设置纵坐标值样式
        mainVerValuesPaint.setColor(context.getResources().getColor(R.color.chart_vh_text_color));
        mainVerValuesPaint.setAntiAlias(true);
        mainVerValuesPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                10,
                                                                 context.getResources().getDisplayMetrics()));
        // TODO: 2016/10/9 设置横坐标值样式,其中字体大小已为sp
        mainHorValuesPaint.setColor(context.getResources().getColor(R.color.chart_vh_text_color));
        mainHorValuesPaint.setAntiAlias(true);
        mainHorValuesPaint.setTextSize(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP,
                10,
                                                                 context.getResources().getDisplayMetrics()));
    }

}
