package com.example.lwd18.pictureselecotor;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v7.widget.AppCompatEditText;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * 一键清除的EditText
 */
public class OneKeyClearEditText extends AppCompatEditText
    implements View.OnFocusChangeListener, TextWatcher {

  private Drawable mClearDrawable;// 一键删除的按钮
  private int colorAccent;// 获得主题的颜色
  private int DrawableColor = 0;
  private boolean hasFocus;// 控件是否有焦点

  public OneKeyClearEditText(Context context) {
    this(context, null);
  }

  public OneKeyClearEditText(Context context, AttributeSet attrs) {
    this(context, attrs, R.attr.editTextStyle);
  }

  @SuppressLint("InlinedApi")
  public OneKeyClearEditText(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    TypedArray array =
        context.getTheme().obtainStyledAttributes(new int[] { android.R.attr.colorAccent });
    colorAccent = array.getColor(0, 0x999999);
    array.recycle();
    TypedArray array2 = context.obtainStyledAttributes(attrs, R.styleable.OneKeyClearEditText);
    DrawableColor = array2.getColor(R.styleable.OneKeyClearEditText_deletecolor, colorAccent);
    array2.recycle();
    initClearDrawable(context);
  }

  //	@SuppressLint("NewApi")
  private void initClearDrawable(Context context) {
    mClearDrawable = getCompoundDrawables()[2];// 获取EditText的DrawableRight,假如没有设置我们就使用默认的图片
    if (mClearDrawable == null) {
      //			mClearDrawable = getResources().getDrawable(R.drawable.search_cancel, context.getTheme());
      mClearDrawable = getResources().getDrawable(R.drawable.search_cancel);
    }
    DrawableCompat.setTint(mClearDrawable, DrawableColor);// 设置删除按钮的颜色和TextColor的颜色一致
    mClearDrawable.setBounds(0, 0, (int) getTextSize(),
        (int) getTextSize());// 设置Drawable的宽高和TextSize的大小一致
    setClearIconVisible(false);
    // 设置焦点改变的监听
    setOnFocusChangeListener(this);
    // 设置输入框里面内容发生改变的监听
    addTextChangedListener(this);
  }

  /**
   * 设置清除图标的显示与隐藏，调用setCompoundDrawables为EditText绘制上去
   */
  private void setClearIconVisible(boolean visible) {
    Drawable right = visible ? mClearDrawable : null;
    setCompoundDrawables(getCompoundDrawables()[0], getCompoundDrawables()[1], right,
        getCompoundDrawables()[3]);
  }

  @Override public boolean onTouchEvent(MotionEvent event) {

    if (mClearDrawable != null && event.getAction() == MotionEvent.ACTION_UP) {
      int x = (int) event.getX();
      // 判断触摸点是否在水平范围内
      boolean isInnerWidth =
          (x > (getWidth() - getTotalPaddingRight())) && (x < (getWidth() - getPaddingRight()));
      // 获取删除图标的边界，返回一个Rect对象
      Rect rect = mClearDrawable.getBounds();
      // 获取删除图标的高度
      int height = rect.height();
      int y = (int) event.getY();
      // 计算图标底部到控件底部的距离
      int distance = (getHeight() - height) / 2;
      // 判断触摸点是否在竖直范围内(可能会有点误差)
      // 触摸点的纵坐标在distance到（distance+图标自身的高度）之内，则视为点中删除图标
      boolean isInnerHeight = (y > distance) && (y < (distance + height));
      if (isInnerHeight && isInnerWidth) {
        this.setText("");
      }
    }
    return super.onTouchEvent(event);
  }

  /**
   * 当输入框里面内容发生变化的时候回调的方法
   */
  @Override public void onTextChanged(CharSequence text, int start, int lengthBefore,
      int lengthAfter) {
    if (hasFocus) {
      setClearIconVisible(text.length() > 0);
      //if (onEditFocusChangedListener != null){
      //  onEditFocusChangedListener.onEditHasFocus();
      //}
    }
  }

  @Override public void beforeTextChanged(CharSequence s, int start, int count, int after) {
    // TODO Auto-generated method stub

  }

  @Override public void afterTextChanged(Editable s) {
    // TODO Auto-generated method stub

  }

  @Override public void onFocusChange(View v, boolean hasFocus) {
    this.hasFocus = hasFocus;
    if (hasFocus) {
      setClearIconVisible(getText().length() > 0);
      //if (onEditFocusChangedListener != null){
      //  onEditFocusChangedListener.onEditHasFocus();
      //}

    } else {
      setClearIconVisible(false);
    }
  }

  //private OnEditFocusChangedListener onEditFocusChangedListener;
  //
  //public void setOnEditFocusChangedListener(OnEditFocusChangedListener onEditFocusChangedListener){
  //	this.onEditFocusChangedListener = onEditFocusChangedListener;
  //}
  //
  //public interface OnEditFocusChangedListener{
  //	void onEditHasFocus();
  //}
}

