package com.deepbaytech.deeplibrary.chart;

import android.content.Context;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.PathEffect;
import android.util.DisplayMetrics;
import android.util.TypedValue;

import com.deepbaytech.deeplibrary.R;
import com.deepbaytech.deeplibrary.utils.DisplayUrils;

import java.util.ArrayList;
public class Line {

    private ArrayList<LinePoint> mPoints = new ArrayList<LinePoint>();
    private Paint mPaint;
    private Paint mFilledPaint;
    private Path mPath = new Path();
    private boolean isFilled = false;
    private Path mFilledPath = new Path();
    private DisplayMetrics displayMetrics;
    private String mName = "Default";

    /**
     * get line name
     * @return name
     */
    public String getName() {
        return mName;
    }

    /**
     * Set line name
     * @param name
     */
    public void setName(String name) {
        this.mName = name;
    }

    /**
     * Getter for points which creates line.
     * 
     * @return points
     */
    public ArrayList<LinePoint> getPoints() {
        return mPoints;
    }

    /**
     * Instantiates a new line.
     * 
     * @param context
     *            context
     */
    public Line(Context context) {
        init(context);
    }

    private void init(Context context) {
        if (mPaint == null) {
            mPaint = new Paint();
            mPaint.setColor(context.getResources().getColor(R.color.chart_point_line_color));
            // TODO: 2016/10/9 增加阴影效果 下一行控制线的粗细
            mPaint.setShadowLayer(DisplayUrils.dip2px(context,3), 0, DisplayUrils.dip2px(context,2), context.getResources().getColor(R.color.chart_shade_color));
            mPaint.setStyle(Style.STROKE);
            mPaint.setStrokeWidth(DisplayUrils.dip2px(context,2));
            mPaint.setAntiAlias(true);
//            mPaint.setStrokeWidth(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 2, context.getResources()
//                                                                                                   .getDisplayMetrics()));
        }
        if (mFilledPaint == null) {
            mFilledPaint = new Paint();
            mFilledPaint.setColor(0x440099cc);
            mFilledPaint.setStyle(Style.FILL);
        }
        displayMetrics = context.getResources().getDisplayMetrics();
    }

    private void buildPath() {
        mPath.reset();
        mPath.moveTo(mPoints.get(0).getX(), mPoints.get(0).getY());
        for (int i = 1; i < mPoints.size(); i += 1) {
            // mPath.quadTo(mPoints.get(i).getX(), mPoints.get(i).getY(),
            // (mPoints.get(i).getX() + mPoints.get(i + 1)
            // .getX()) / 2, ( mPoints.get(i).getY()+mPoints.get(i +
            // 1).getY())/2);
            mPath.lineTo(mPoints.get(i).getX(), mPoints.get(i).getY());
        }
        if (isFilled) {
            mFilledPath = new Path(mPath);
            mFilledPath.lineTo(mPoints.get(mPoints.size() - 1).getX(), 0);
            mFilledPath.lineTo(mPoints.get(0).getX(), 0);
            mFilledPath.close();
        }
    }

    /**
     * Smooth line with spline interpolation. Don't adds new points to line, just smooth path for drawing.
     * 
     * @param subPoints
     *            count of sub points
     * @return this
     */
    public Line smoothLine(int subPoints) {
        double[] dataX = new double[mPoints.size()];
        double[] dataY = new double[mPoints.size()];
        for (int i = 0; i < mPoints.size(); i++) {
            dataX[i] = mPoints.get(i).getX();
            dataY[i] = mPoints.get(i).getY();
        }
        mPath.reset();
        mPath.moveTo(mPoints.get(0).getX(), mPoints.get(0).getY());
        Spline sp = new Spline(dataX, dataY);
        for (int i = 0; i < mPoints.size() - 1; i += 1) {
            double step = (dataX[i + 1] - dataX[i]) / (subPoints + 1);
            for (int j = 1; j <= subPoints; j++) {
                double x = dataX[i] + step * j;
                mPath.lineTo((float) x, (float) sp.spline_value(x));
            }
            mPath.lineTo((float) dataX[i + 1], (float) dataY[i + 1]);
        }
        if (isFilled) {
            mFilledPath = new Path(mPath);
            mFilledPath.lineTo(mPoints.get(mPoints.size() - 1).getX(), 0);
            mFilledPath.lineTo(mPoints.get(0).getX(), 0);
            mFilledPath.close();
        }
        return this;
    }

    /**
     * Gets the {@link {@link Path Path}. For line drawing.
     * 
     * @return path
     */
    Path getPath() {
        return mPath;
    }

    /**
     * Gets {@link {@link Path Path} for filling underline space.
     * 
     * @return the filled path
     */
    Path getFilledPath() {
        return mFilledPath;
    }

    /**
     * Sets the points.
     * 
     * @param points
     *            points
     * @return this
     */
    public Line setPoints(ArrayList<LinePoint> points) {
        this.mPoints = points;
        buildPath();
        return this;
    }

    /**
     * Adds the point at the end of the line.
     * 
     * @param point
     *            the point
     * @return this
     */
    public Line addPoint(LinePoint point) {
        LinePoint p;
        for (int i = 0; i < mPoints.size(); i++) {
            p = mPoints.get(i);
            if (point.getX() < p.getX()) {
                mPoints.add(i, point);
                return this;
            }
        }
        mPoints.add(point);
        buildPath();
        return this;

    }

    /**
     * Removes the point.
     * 
     * @param point
     *            point from line
     * @return this
     */
    public Line removePoint(LinePoint point) {
        mPoints.remove(point);
        buildPath();
        return this;
    }

    /**
     * Gets the point by index.
     * 
     * @param index
     *            index of the point
     * @return point with <i>index</i>
     */
    public LinePoint getPoint(int index) {
        return mPoints.get(index);
    }

    /**
     * Gets the point with coords.
     * 
     * @param x
     *            x
     * @param y
     *            y
     * @return point
     */
    public LinePoint getPoint(float x, float y) {
        LinePoint p;
        for (int i = 0; i < mPoints.size(); i++) {
            p = mPoints.get(i);
            if (p.getX() == x && p.getY() == y) return p;
        }
        return null;
    }

    /**
     * Points count.
     * 
     * @return count
     */
    public int getPointsCount() {
        return mPoints.size();
    }

    /**
     * {@link Paint Paint} for line drawing.
     * 
     * @return paint
     */
    public Paint getPaint() {
        return mPaint;
    }

    /**
     * Sets {@link Paint Paint} for line drawing.
     * 
     * @param paint
     *            paint
     * @return this
     */
    public Line setPaint(Paint paint) {
        this.mPaint = paint;
        return this;
    }

    /**
     * Show or hide underline space fill with {@link Paint Paint} setted by {@link #setFilledPaint}.
     * 
     * @param isFilled
     *            is need fill underline space.
     * @return this
     */
    public Line setFilled(boolean isFilled) {
        this.isFilled = isFilled;
        buildPath();
        return this;
    }

    /**
     * Checks if underline space filled.
     * 
     * @return true, if need fill underline space
     */
    public boolean isFilled() {
        return isFilled;
    }

    /**
     * @return {@link Paint Paint} to underline space fill
     */
    public Paint getFilledPaint() {
        return mFilledPaint;
    }

    /**
     * Sets {@link Paint Paint} for underline space fill.
     * 
     * @param paint
     *            the paint
     * @return the line
     */
    public Line setFilledPaint(Paint paint) {
        this.mFilledPaint = paint;
        return this;
    }

    /**
     * Sets the color for Line.
     * 
     * @param color
     *            color in #AARRGGBB
     * @return this
     */
    public Line setColor(int color) {
        getPaint().setColor(color);
        return this;
    }

    /**
     * Sets color for underline fill.
     * 
     * @param color
     *            color in #AARRGGBB
     * @return this
     */
    public Line setFilledColor(int color) {
        mFilledPaint.setColor(color);
        return this;
    }

    /**
     * Sets the path effect.
     * 
     * @param pe
     *            {@link PathEffect PathEffect} for line drawing
     * @return this
     */
    public Line setPathEffect(PathEffect pe) {
        mPaint.setPathEffect(pe);
        return this;
    }

    /**
     * Sets line stroke width.
     * 
     * @param widthDp
     *            width in dp
     * @return this
     */
    public Line setStrokeWidth(float widthDp) {
        mPaint.setStrokeWidth(TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, widthDp, displayMetrics));
        return this;
    }

    private static double[] linSolve(double[][] matrix) {
        double[] results = new double[matrix.length];
        int[] order = new int[matrix.length];
        for (int i = 0; i < order.length; ++i) {
            order[i] = i;
        }
        for (int i = 0; i < matrix.length; ++i) {
            // partial pivot
            int maxIndex = i;
            for (int j = i + 1; j < matrix.length; ++j) {
                if (Math.abs(matrix[maxIndex][i]) < Math.abs(matrix[j][i])) {
                    maxIndex = j;
                }
            }
            if (maxIndex != i) {
                // swap order
                {
                    int temp = order[i];
                    order[i] = order[maxIndex];
                    order[maxIndex] = temp;
                }
                // swap matrix
                for (int j = 0; j < matrix[0].length; ++j) {
                    double temp = matrix[i][j];
                    matrix[i][j] = matrix[maxIndex][j];
                    matrix[maxIndex][j] = temp;
                }
            }
            if (Math.abs(matrix[i][i]) < 1e-15) {
                throw new RuntimeException("Singularity detected");
            }
            for (int j = i + 1; j < matrix.length; ++j) {
                double factor = matrix[j][i] / matrix[i][i];
                for (int k = i; k < matrix[0].length; ++k) {
                    matrix[j][k] -= matrix[i][k] * factor;
                }
            }
        }
        for (int i = matrix.length - 1; i >= 0; --i) {
            // back substitute
            results[i] = matrix[i][matrix.length];
            for (int j = i + 1; j < matrix.length; ++j) {
                results[i] -= results[j] * matrix[i][j];
            }
            results[i] /= matrix[i][i];
        }
        double[] correctResults = new double[results.length];
        for (int i = 0; i < order.length; ++i) {
            // switch the order around back to the original order
            correctResults[order[i]] = results[i];
        }
        return results;
    }

    public static float polyInterpolate(float[] dataX, float[] dataY, double x, int power) {
        int xIndex = 0;
        while (xIndex < dataX.length - (1 + power + (dataX.length - 1) % power) && dataX[xIndex + power] < x) {
            xIndex += power;
        }

        double matrix[][] = new double[power + 1][power + 2];
        for (int i = 0; i < power + 1; ++i) {
            for (int j = 0; j < power; ++j) {
                matrix[i][j] = Math.pow(dataX[xIndex + i], (power - j));
            }
            matrix[i][power] = 1;
            matrix[i][power + 1] = dataY[xIndex + i];
        }
        double[] coefficients = linSolve(matrix);
        double answer = 0;
        for (int i = 0; i < coefficients.length; ++i) {
            answer += coefficients[i] * Math.pow(x, (power - i));
        }
        return (float) answer;
    }
}
