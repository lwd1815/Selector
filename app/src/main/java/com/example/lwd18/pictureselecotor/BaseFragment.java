package com.example.lwd18.pictureselecotor;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;

import me.yokeyword.fragmentation.SupportFragment;
import rx.Subscription;

/**
 * Created by EtherealPatrick on 2017/2/23.
 */

public class BaseFragment extends SupportFragment{
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
}
