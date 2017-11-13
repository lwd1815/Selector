package com.example.lwd18.pictureselecotor;

import android.os.Bundle;

/**
 * Created by EtherealPatrick on 2017/2/13.
 */

public abstract class BaseDeepActivity extends BaseActivity {

  /**
   * hook listener
   */
  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    try {
      initActivity();
    } catch (Exception e) {
      e.printStackTrace();
    }

  }


  protected abstract void initActivity();


  @Override protected void onDestroy() {
    super.onDestroy();
  }
}
