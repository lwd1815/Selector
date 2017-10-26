package com.ethereal.deepstatelayout;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.HashMap;
import java.util.Map;

/**
 * 1.
 * <style name="AppTheme" parent="Theme.AppCompat.Light.DarkActionBar">
 * ...
 * <item name="styleDStateLayout">@style/DStateLayoutStyle</item>
 * </style>
 *
 * <style name="DStateLayoutStyle" parent="DStateLayout.Style">
 * <item name="llEmptyImage">@mipmap/empty</item>
 * <item name="llErrorImage">@mipmap/error</item>
 * ...
 * </style>
 *
 *
 * 2.1
 * 用法一：在布局中使用
 * <com.ethereal.deepstatelayout.DStateLayout
 * android:id="@+id/loading"
 * android:layout_width="match_parent"
 * android:layout_height="match_parent">
 * <TextView
 * android:layout_width="match_parent"
 * android:layout_height="match_parent"
 * android:gravity="center"
 * android:text="This is Content"/>
 * </com.ethereal.deepstatelayout.DStateLayout>
 *
 * 2.2
 * 用法二：包裹并替换内容元素
 * @Override protected void onCreate(Bundle savedInstanceState) {
 * super.onCreate(savedInstanceState);
 * setContentView(R.layout.activity_main);
 * // ...
 * dStateLayout = DStateLayout.wrap(this);
 * dStateLayout.showContent();
 * // ...
 * }
 *
 *
 * 3.API
 * // 显示 content/loading/empty/error 布局
 * showContent()
 * showLoading()
 * showEmpty()
 * showError()
 *
 *
 *
 * // 设置 loading/empty/error 布局
 * setLoading(int resId)
 * setEmpty(int resId)
 * setError(int resId)
 *
 * // 设置空布局的图片与文本
 * setEmptyImage(int resId)
 * setEmptyText(String value)
 *
 * // 设置错误布局的图片与文本，
 * setErrorImage(int resId)
 * setErrorText(String value)
 *
 * // 设置重试按钮文本
 * setRetryText(String value)
 *
 * // 设置重试按钮的监听回调
 * setRetryListener(OnClickListener listener)
 */

public class DStateLayout extends FrameLayout {
  public interface OnInflateListener {
    void onInflate(View inflated);
  }

  public static DStateLayout wrap(Activity activity) {
    return wrap(((ViewGroup) activity.findViewById(android.R.id.content)).getChildAt(0));
  }

  public static DStateLayout wrap(Fragment fragment) {
    return wrap(fragment.getView());
  }

  public static DStateLayout wrap(View view) {
    if (view == null) {
      throw new RuntimeException("content view can not be null");
    }
    ViewGroup parent = (ViewGroup) view.getParent();
    if (parent == null) {
      throw new RuntimeException("parent view can not be null");
    }
    ViewGroup.LayoutParams lp = view.getLayoutParams();
    int index = parent.indexOfChild(view);
    parent.removeView(view);

    DStateLayout layout = new DStateLayout(view.getContext());
    parent.addView(layout, index, lp);
    layout.addView(view);
    layout.setContentView(view);
    return layout;
  }

  int mEmptyImage;
  CharSequence mEmptyText;

  int mErrorImage;
  CharSequence mErrorText, mRetryText;
  OnClickListener mRetryButtonClickListener = new OnClickListener() {
    @Override public void onClick(View v) {
      if (mRetryListener != null) {
        mRetryListener.onClick(v);
      }
    }
  };
  OnClickListener mRetryListener;

  OnInflateListener mOnEmptyInflateListener;
  OnInflateListener mOnErrorInflateListener;

  int mTextColor, mTextSize;
  int mButtonTextColor, mButtonTextSize;
  Drawable mButtonBackground;
  int mEmptyResId = NO_ID, mLoadingResId = NO_ID, mErrorResId = NO_ID;
  int mContentId = NO_ID;

  Map<Integer, View> mLayouts = new HashMap<>();

  public DStateLayout(Context context) {
    this(context, null, R.attr.styleDStateLayout);
  }

  public DStateLayout(Context context, AttributeSet attrs) {
    this(context, attrs, R.attr.styleDStateLayout);
  }

  public DStateLayout(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);

    mInflater = LayoutInflater.from(context);
    TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.DStateLayout, defStyleAttr,
        R.style.DStateLayout_Style);
    mEmptyImage = a.getResourceId(R.styleable.DStateLayout_llEmptyImage, NO_ID);
    mEmptyText = a.getString(R.styleable.DStateLayout_llEmptyText);

    mErrorImage = a.getResourceId(R.styleable.DStateLayout_llErrorImage, NO_ID);
    mErrorText = a.getString(R.styleable.DStateLayout_llErrorText);
    mRetryText = a.getString(R.styleable.DStateLayout_llRetryText);

    mTextColor = a.getColor(R.styleable.DStateLayout_llTextColor, 0xff999999);
    mTextSize = a.getDimensionPixelSize(R.styleable.DStateLayout_llTextSize, dp2px(16));

    mButtonTextColor = a.getColor(R.styleable.DStateLayout_llButtonTextColor, 0xff999999);
    mButtonTextSize = a.getDimensionPixelSize(R.styleable.DStateLayout_llButtonTextSize, dp2px(16));
    mButtonBackground = a.getDrawable(R.styleable.DStateLayout_llButtonBackground);

    mEmptyResId =
        a.getResourceId(R.styleable.DStateLayout_llEmptyResId, R.layout.d_state_layout_empty);
    mLoadingResId =
        a.getResourceId(R.styleable.DStateLayout_llLoadingResId, R.layout.d_state_layout_loading);
    mErrorResId =
        a.getResourceId(R.styleable.DStateLayout_llErrorResId, R.layout.d_state_layout_error);
    a.recycle();
  }

  int dp2px(float dp) {
    return (int) (getResources().getDisplayMetrics().density * dp);
  }

  LayoutInflater mInflater;

  @Override protected void onFinishInflate() {
    super.onFinishInflate();
    if (getChildCount() == 0) {
      return;
    }
    if (getChildCount() > 1) {
      removeViews(1, getChildCount() - 1);
    }
    View view = getChildAt(0);
    setContentView(view);
    showLoading();
  }

  private void setContentView(View view) {
    mContentId = view.getId();
    mLayouts.put(mContentId, view);
  }

  public DStateLayout setLoading(@LayoutRes int id) {
    if (mLoadingResId != id) {
      remove(mLoadingResId);
      mLoadingResId = id;
    }
    return this;
  }

  public DStateLayout setEmpty(@LayoutRes int id) {
    if (mEmptyResId != id) {
      remove(mEmptyResId);
      mEmptyResId = id;
    }
    return this;
  }

  public DStateLayout setOnEmptyInflateListener(OnInflateListener listener) {
    mOnEmptyInflateListener = listener;
    if (mOnEmptyInflateListener != null && mLayouts.containsKey(mEmptyResId)) {
      listener.onInflate(mLayouts.get(mEmptyResId));
    }
    return this;
  }

  public DStateLayout setOnErrorInflateListener(OnInflateListener listener) {
    mOnErrorInflateListener = listener;
    if (mOnErrorInflateListener != null && mLayouts.containsKey(mErrorResId)) {
      listener.onInflate(mLayouts.get(mErrorResId));
    }
    return this;
  }

  public DStateLayout setEmptyImage(@DrawableRes int resId) {
    mEmptyImage = resId;
    image(mEmptyResId, R.id.empty_image, mEmptyImage);
    return this;
  }

  public DStateLayout setEmptyText(CharSequence value) {
    mEmptyText = value;
    text(mEmptyResId, R.id.empty_text, mEmptyText);
    return this;
  }

  public DStateLayout setErrorImage(@DrawableRes int resId) {
    mErrorImage = resId;
    image(mErrorResId, R.id.error_image, mErrorImage);
    return this;
  }

  public DStateLayout setErrorText(CharSequence value) {
    mErrorText = value;
    text(mErrorResId, R.id.error_text, mErrorText);
    return this;
  }

  public DStateLayout setRetryText(CharSequence text) {
    mRetryText = text;
    text(mErrorResId, R.id.retry_button, mRetryText);
    return this;
  }

  public DStateLayout setRetryListener(OnClickListener listener) {
    mRetryListener = listener;
    return this;
  }

  //    public DStateLayout setTextColor(@ColorInt int color) {
  //        mTextColor = color;
  //        return this;
  //    }
  //    public DStateLayout setTextSize(@ColorInt int dp) {
  //        mTextColor = dp2px(dp);
  //        return this;
  //    }
  //    public DStateLayout setButtonTextColor(@ColorInt int color) {
  //        mButtonTextColor = color;
  //        return this;
  //    }
  //    public DStateLayout setButtonTextSize(@ColorInt int dp) {
  //        mButtonTextColor = dp2px(dp);
  //        return this;
  //    }
  //    public DStateLayout setButtonBackground(Drawable drawable) {
  //        mButtonBackground = drawable;
  //        return this;
  //    }

  public void showLoading() {
    show(mLoadingResId);
  }

  public void showEmpty() {
    show(mEmptyResId);
  }

  public void showError() {
    show(mErrorResId);
  }

  public void showContent() {
    show(mContentId);
  }

  private void show(int layoutId) {
    for (View view : mLayouts.values()) {
      view.setVisibility(GONE);
    }
    layout(layoutId).setVisibility(VISIBLE);
  }

  private void remove(int layoutId) {
    if (mLayouts.containsKey(layoutId)) {
      View vg = mLayouts.remove(layoutId);
      removeView(vg);
    }
  }

  @SuppressLint("NewApi") private View layout(int layoutId) {
    if (mLayouts.containsKey(layoutId)) {
      return mLayouts.get(layoutId);
    }
    View layout = mInflater.inflate(layoutId, this, false);
    layout.setVisibility(GONE);
    addView(layout);
    mLayouts.put(layoutId, layout);

    if (layoutId == mEmptyResId) {
      ImageView img = (ImageView) layout.findViewById(R.id.empty_image);
      if (img != null) {
        img.setImageResource(mEmptyImage);
      }
      TextView view = (TextView) layout.findViewById(R.id.empty_text);
      if (view != null) {
        view.setText(mEmptyText);
        view.setTextColor(mTextColor);
        view.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
      }
      if (mOnEmptyInflateListener != null) {
        mOnEmptyInflateListener.onInflate(layout);
      }
    } else if (layoutId == mErrorResId) {
      ImageView img = (ImageView) layout.findViewById(R.id.error_image);
      if (img != null) {
        img.setImageResource(mErrorImage);
      }
      TextView txt = (TextView) layout.findViewById(R.id.error_text);
      if (txt != null) {
        txt.setText(mErrorText);
        txt.setTextColor(mTextColor);
        txt.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
      }
      TextView btn = (TextView) layout.findViewById(R.id.retry_button);
      if (btn != null) {
        btn.setText(mRetryText);
        btn.setTextColor(mButtonTextColor);
        btn.setTextSize(TypedValue.COMPLEX_UNIT_PX, mButtonTextSize);
        btn.setBackground(mButtonBackground);
        btn.setOnClickListener(mRetryButtonClickListener);
      }
      if (mOnErrorInflateListener != null) {
        mOnErrorInflateListener.onInflate(layout);
      }
    }
    return layout;
  }

  private void text(int layoutId, int ctrlId, CharSequence value) {
    if (mLayouts.containsKey(layoutId)) {
      TextView view = (TextView) mLayouts.get(layoutId).findViewById(ctrlId);
      if (view != null) {
        view.setText(value);
      }
    }
  }

  private void image(int layoutId, int ctrlId, int resId) {
    if (mLayouts.containsKey(layoutId)) {
      ImageView view = (ImageView) mLayouts.get(layoutId).findViewById(ctrlId);
      if (view != null) {
        view.setImageResource(resId);
      }
    }
  }
}
