package com.example.lwd18.pictureselecotor.textsearch;

import android.content.Intent;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.alibaba.fastjson.JSON;
import com.deepbaytech.deeplibrary.ActivityManager;
import com.deepbaytech.deeplibrary.OneKeyClearEditText;
import com.deepbaytech.deeplibrary.manager.CacheManager;
import com.example.lwd18.pictureselecotor.ApiConstants;
import com.example.lwd18.pictureselecotor.BaseDeepActivity;
import com.example.lwd18.pictureselecotor.FlowLayout;
import com.example.lwd18.pictureselecotor.PopularText;
import com.example.lwd18.pictureselecotor.R;
import com.example.lwd18.pictureselecotor.dialog.ConfirmDialog;
import com.example.lwd18.pictureselecotor.event.SearchHistoryEvent;
import com.example.lwd18.pictureselecotor.textsearch.Eventutil.SearchEvent;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import okhttp3.Call;



public class DuTextSearchActivity extends BaseDeepActivity {

  @BindView(R.id.et_search) OneKeyClearEditText etSearch;
  @BindView(R.id.tv_search_back) TextView tvSearchBack;
  @BindView(R.id.search_toolbar) Toolbar searchToolbar;
  @BindView(R.id.iv_search_history) ImageView ivSearchHistory;
  @BindView(R.id.iv_del_history) ImageView ivDelHistory;
  @BindView(R.id.fl_history) FlowLayout flHistory;
  @BindView(R.id.iv_search_hot) ImageView ivSearchHot;
  @BindView(R.id.fl_hot) FlowLayout flHot;
  @BindView(R.id.ll_search_text_ware) LinearLayout llSearchTextWare;
  @BindView(R.id.activity_text_search) RelativeLayout activityTextSearch;
  @BindView(R.id.rl_search_history) RelativeLayout rlSearchHistory;
  @BindView(R.id.ll_search_history) LinearLayout llSearchHistory;

  //edit 输入的搜索词
  private String query;

  private List<String> hotList;
  private List<String> historyList;
  int fragmentTab;

  private boolean hisFlag = false;

  private ConfirmDialog dialog;
  private int margin;
  @Override protected void initActivity() {
    setContentView(R.layout.activity_text_search);
    ButterKnife.bind(this);
    EventBus.getDefault().register(this);
    fragmentTab = getIntent().getIntExtra("fragmentTab", 0);
    initToolBar();
    initFlowLayout();
  }

  private void initFlowLayout() {
    OkHttpUtils.get()
        .url(ApiConstants.POPULAR_TEXT)
        .addParams("type",1+"")
        .build().execute(new StringCallback() {
      @Override public void onError(Call call, Exception e, int id) {

      }

      @Override public void onResponse(String response, int id) {
        PopularText popularText = JSON.parseObject(response, PopularText.class);
        if (popularText.getState() == 0) {
          hotList = popularText.getData().getTexts();
          addChildText(flHot, hotList);
        }
      }
    });
    historyList = CacheManager.getInstance().getSearchHistory();
    if (historyList != null && historyList.size() > 0) {
      rlSearchHistory.setVisibility(View.VISIBLE);
      llSearchHistory.setVisibility(View.VISIBLE);
      hisFlag = true;
      addChildText(flHistory, historyList);
    }
  }

  /**
   *
   * @param flowLayout
   * @param list
   */
  private void addChildText(FlowLayout flowLayout, List<String> list) {
    if (list != null) {
      Iterator<String> iterator = list.iterator();
      while (iterator.hasNext()) {
        String text = iterator.next();
        addText(flowLayout, text, false);
      }
    }
  }

  /**
   * 向FlowLayout中添加数据
   * xx 是否添加在最前面
   */
  private void addText(FlowLayout flowLayout, String text, boolean xx) {
    final TextView tvChild = (TextView) LayoutInflater.from(this)
        .inflate(R.layout.view_textview_flowflag, flowLayout, false);
    tvChild.setText(text);
    tvChild.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        String flowKey = tvChild.getText().toString();
        EventBus.getDefault().post(new SearchHistoryEvent(flowKey));
      }
    });
    if (xx) {
      flowLayout.addView(tvChild, 0);
    } else {
      flowLayout.addView(tvChild);
    }
  }

  /**
   * Toolbar
   * OneKeyClearEditText
   */
  private void initToolBar() {
    searchToolbar.setTitle("");
    setSupportActionBar(searchToolbar);

    if (getIntent() != null && getIntent().getStringExtra("searchKey") != null) {
      String searchKey = getIntent().getStringExtra("searchKey");
      etSearch.setText(searchKey);
      etSearch.setSelection(searchKey.length());
    }
    etSearch.setImeOptions(EditorInfo.IME_ACTION_SEARCH);
    etSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
      @Override public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
          query = etSearch.getText().toString().trim();
          if (!query.isEmpty()) {
            EventBus.getDefault().post(new SearchEvent(query));
            //goTextSearchResultActivity(query);
          } else {
            EventBus.getDefault().post(new SearchEvent(etSearch.getHint().toString()));
          }
        }
        return false;
      }
    });
  }

  private void goTextSearchResultActivity(String query) {
    //1.0版本的搜索结果页面(先注销掉)
    //Intent intent = new Intent(DuTextSearchActivity.this, TextSearchResultActivity.class);
    Intent intent = new Intent(DuTextSearchActivity.this, ProductDetailActivity.class);
    intent.putExtra("searchKey", query);
    startActivity(intent);
  }

  /**
   * 获取搜索历史
   */
  private void initSearchHistory(List<String> list) {

    if (list != null && list.size() > 0) {
      rlSearchHistory.setVisibility(View.VISIBLE);
      llSearchHistory.setVisibility(View.VISIBLE);
      hisFlag = true;
    } else {
      rlSearchHistory.setVisibility(View.GONE);
      llSearchHistory.setVisibility(View.GONE);
      hisFlag = false;
    }
  }

  /**
   * 删除搜索历史
   */
  private void clearSearchHistory() {
    CacheManager.getInstance().saveSearchHistory(null);
    initSearchHistory(null);
  }

  /**
   * 先添加flowlayout
   * 后保存搜索历史
   */
  private void saveSearchHistory(String query) {
    List<String> list = CacheManager.getInstance().getSearchHistory();

    boolean flag = false;

    if (list == null) {

      list = new ArrayList<>();
      list.add(0, query);
    } else {
      Iterator<String> iterator = list.iterator();
      while (iterator.hasNext()) {
        String item = iterator.next();
        if (TextUtils.equals(query, item)) {
          iterator.remove();
          flag = true;
        }
      }
      list.add(0, query);
    }

    if (flag == false) {
      addText(flHistory, query, true);
    }

    int size = list.size();
    if (size > 10) { // 最多保存20条
      for (int i = size - 1; i >= 10; i--) {
        list.remove(i);
      }
    }
    CacheManager.getInstance().saveSearchHistory(list);

    initSearchHistory(list);

    goTextSearchResultActivity(query);
  }

  /**
   * 点击flowlayout
   */
  public void onEvent(SearchHistoryEvent event) {
    etSearch.setText(event.getMsg());
    etSearch.setSelection(event.getMsg().toString().length());
    saveSearchHistory(event.getMsg());
  }

  /**
   * 键盘搜索按钮
   */
  public void onEvent(SearchEvent event) {
    //去重
    //List<String> list = CacheManager.getInstance().getSearchHistory();
    //Iterator<String> iterator = list.iterator();
    //boolean flag = false;
    //while (iterator.hasNext()) {
    //  String item = iterator.next();
    //  if (TextUtils.equals(event.getMsg(), item)) {
    //    flag = true;
    //  }
    //}
    //if (flag == false) {
    //  addText(flHistory, event.getMsg());
    //}

    saveSearchHistory(event.getMsg());
  }

  @OnClick({ R.id.tv_search_back, R.id.iv_del_history }) public void onClick(View view) {
    switch (view.getId()) {
      case R.id.tv_search_back:
        //Intent intent = new Intent(DuTextSearchActivity.this, MainActivity.class);
        //intent.putExtra("currentTab", fragmentTab);
        //startActivity(intent);
        ActivityManager.getInstance().finishActivity(DuTextSearchActivity.class);
        break;
      //删除搜索历史
      case R.id.iv_del_history:
        dialog = new ConfirmDialog(DuTextSearchActivity.this);
        dialog.setOnDeepConfirmListener(new ConfirmDialog.DeepConfirmListener() {
          @Override public void onSelectCancel() {
            dialog.dismiss();
          }

          @Override public void onSelectEnsure() {
            clearSearchHistory();
            flHistory.removeAllViews();
            dialog.dismiss();
          }
        });
        dialog.show();
        break;
    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
  }

  /**
   * 该方法回调时机为,Activity回退栈内Fragment的数量 小于等于1 时,默认finish Activity
   * 请尽量复写该方法,避免复写onBackPress(),以保证SupportFragment内的onBackPressedSupport()回退事件正常执行
   */
  @Override public void onBackPressedSupport() {
    super.onBackPressedSupport();
  }

}
