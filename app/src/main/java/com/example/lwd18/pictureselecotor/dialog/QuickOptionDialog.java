package com.example.lwd18.pictureselecotor.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.LinearInterpolator;
import android.widget.LinearLayout;
import android.widget.Toast;
import com.example.lwd18.pictureselecotor.R;
import com.example.lwd18.pictureselecotor.bigphotobrows.ImageBrowsePresenter;


/**
 * 创建者     李文东
 * 创建时间   2017/8/11 18:39
 * 描述	      ${TODO}
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class QuickOptionDialog extends Dialog implements View.OnClickListener {

  private LinearLayout mShare, saveLocal, collection;
  private ImageBrowsePresenter mPresent;
  private Context              mContext;
  private Activity mActivity;
  private Animation mOperatingAnim;

  public QuickOptionDialog(Activity activity, Context context) {
    this(context, R.style.quick_option_dialog);
    this.mContext = context;
    this.mActivity = activity;
  }

  private QuickOptionDialog(Context context, int theme) {

    super(context, theme);

    View contentView = getLayoutInflater().inflate(R.layout.quick_option_dialog, null);

    mShare = (LinearLayout) contentView.findViewById(R.id.share_friend);
    saveLocal = (LinearLayout) contentView.findViewById(R.id.save_phone);
    collection = (LinearLayout) contentView.findViewById(R.id.collection);


    mOperatingAnim = AnimationUtils.loadAnimation(getContext(), R.anim.quick_option_close);

    LinearInterpolator lin = new LinearInterpolator();

    mOperatingAnim.setInterpolator(lin);

    contentView.setOnTouchListener(new View.OnTouchListener() {

      @Override

      public boolean onTouch(View v, MotionEvent event) {

        QuickOptionDialog.this.dismiss();

        return true;

      }

    });

    super.setContentView(contentView);

  }


  @Override

  protected void onCreate(Bundle savedInstanceState) {

    super.onCreate(savedInstanceState);

    getWindow().setGravity(Gravity.BOTTOM);
    //getWindow().setNavigationBarColor(Color.TRANSPARENT);
    WindowManager windowManager = getWindow().getWindowManager();

    Display display = windowManager.getDefaultDisplay();
    WindowManager.LayoutParams layoutParams = getWindow().getAttributes();
    layoutParams.width = display.getWidth();
    getWindow().setAttributes(layoutParams);

    mShare.setOnClickListener(this);
    saveLocal.setOnClickListener(this);
    collection.setOnClickListener(this);
    // mPresent = new ImageBrowsePresenter(this);
  }

  @Override
  public void onClick(View v) {
    switch (v.getId()) {
      case R.id.share_friend:
        mShare.startAnimation(mOperatingAnim);
        QuickOptionDialog.this.dismiss();
        //弹出分享界面
        Toast.makeText(getContext(), "分享", Toast.LENGTH_SHORT).show();
        break;
      case R.id.save_phone:
        if(mOnClick!=null)
          mOnClick.saveclick();
        saveLocal.startAnimation(mOperatingAnim);
        QuickOptionDialog.this.dismiss();
        break;
      case R.id.collection:
        collection.startAnimation(mOperatingAnim);
        QuickOptionDialog.this.dismiss();
        break;
    }
  }

  private OnClick mOnClick;

  public void setOnClick(OnClick onClick) {
    this.mOnClick = onClick;
  }

  public interface OnClick {
    void saveclick();
  }
}
