package com.example.lwd18.pictureselecotor;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import com.deepbaytech.deeplibrary.utils.InternetUtils;
import me.yokeyword.fragmentation.SupportFragment;
import rx.Subscription;

/**
 * Created by EtherealPatrick on 2017/2/23.
 */

public class BaseFragment extends SupportFragment implements NetEvevt{
  private Activity activity;
  protected Subscription subscription;
    private int netMobile;
  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
  }

  @Override public void onDestroy() {
    super.onDestroy();
  }

  public Context getMContext() {
    if (activity == null) {
      return MyApplication.getContext();
    }
    return activity;
  }

  @Override public void onAttach(Activity activity) {
    super.onAttach(activity);
    activity = getActivity();
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unsubscribe();
  }

  protected void unsubscribe() {
    if (subscription != null && !subscription.isUnsubscribed()) {
      subscription.unsubscribe();
    }
  }

  /**
   * 初始化时判断有没有网络
   */

  public boolean inspectNet() {
    this.netMobile = InternetUtils.getNetWorkState(getContext());

    return isNetConnect();

    // if (netMobile == NetUtil.NETWORK_WIFI) {
    // System.out.println("inspectNet：连接wifi");
    // } else if (netMobile == NetUtil.NETWORK_MOBILE) {
    // System.out.println("inspectNet:连接移动数据");
    // } else if (netMobile == NetUtil.NETWORK_NONE) {
    // System.out.println("inspectNet:当前没有网络");
    //
    // }
  }
  /**
   * 判断有无网络 。
   *
   * @return true 有网, false 没有网络.
   */
  public boolean isNetConnect() {
    if (netMobile == InternetUtils.NETWORK_WIFI) {
      return true;
    } else if (netMobile == InternetUtils.NETWORK_MOBILE) {
      return true;
    } else if (netMobile == InternetUtils.NETWORK_NONE) {
      return false;

    }
    return false;
  }

  @Override
  public void onNetChange(int netMobile) {
    this.netMobile = netMobile;
    isNetConnect();
  }
}
