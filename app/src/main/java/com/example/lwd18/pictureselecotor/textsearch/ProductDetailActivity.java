package com.example.lwd18.pictureselecotor.textsearch;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alibaba.fastjson.JSON;
import com.deepbaytech.deeplibrary.ActivityManager;
import com.example.lwd18.pictureselecotor.ApiConstants;
import com.example.lwd18.pictureselecotor.BaseDeepActivity;
import com.example.lwd18.pictureselecotor.BaseFragment;
import com.example.lwd18.pictureselecotor.NoScrollViewPager;
import com.example.lwd18.pictureselecotor.R;
import com.example.lwd18.pictureselecotor.TextsSearchEntity;
import com.example.lwd18.pictureselecotor.textsearch.Eventutil.FinishEventUtil;
import com.example.lwd18.pictureselecotor.textsearch.Eventutil.SecondEventil;
import com.example.lwd18.pictureselecotor.textsearch.Eventutil.TextEventUtil;
import com.example.lwd18.pictureselecotor.textsearch.Eventutil.TranstEventil;
import com.example.lwd18.pictureselecotor.textsearch.fragment.ComprehensiveFragment;
import com.example.lwd18.pictureselecotor.textsearch.fragment.PriceFragment;
import com.example.lwd18.pictureselecotor.textsearch.fragment.SaleNumberFragment;
import com.example.lwd18.pictureselecotor.textsearch.resultadapter.ViewPagerAdapter;
import com.example.lwd18.pictureselecotor.textsearch.view.FilterFragments;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;



public class ProductDetailActivity extends BaseDeepActivity implements View.OnClickListener {

  @BindView(R.id.tv_search_text_result) TextView tvSearchTextResult;
  @BindView(R.id.ll_search_text_result) LinearLayout llSearchTextResult;
  @BindView(R.id.rl_search_text_result) RelativeLayout rlSearchTextResult;
  @BindView(R.id.tv_search_text_result_back) TextView tvSearchTextResultBack;
  @BindView(R.id.search_toolbar_product_detail) Toolbar searchToolbarProductDetail;
  @BindView(R.id.topic_tabLayout) TabLayout topicTabLayout;
  @BindView(R.id.textsearch_filter) TextView textsearchFilter;
  @BindView(R.id.textsearch_filter_out) RelativeLayout textsearchFilterOut;
  @BindView(R.id.vp_topic) NoScrollViewPager vpTopic;
  @BindView(R.id.topic_zero_contain_product_detail) FrameLayout topicZeroContainProductDetail;
  @BindView(R.id.topic_zero_content_new_out) LinearLayout topicZeroContentNewOut;
  @BindView(R.id.nav_view) LinearLayout navView;
  @BindView(R.id.drawer_layout) DrawerLayout drawerLayout;
  private ViewPagerAdapter adapter;
  private int priceSortMode = 0;
  private TextsSearchEntity textSearch;
  private List<TextsSearchEntity.DataBean.FiltersBean> filterlist;
  List<BaseFragment> list_fragment;
  List<String> list_table;
  private FilterFragments filterFragment;
  private String mSearchkey;
  private ImageView img_title;

  @Override protected void initActivity() {
    setContentView(R.layout.activity_product_details);
    ButterKnife.bind(this);
    EventBus.getDefault().register(this);
    if (getIntent() != null && getIntent().getStringExtra("searchKey") != null) {
      mSearchkey = getIntent().getStringExtra("searchKey");
    }

    if (tvSearchTextResult != null) tvSearchTextResult.setText(mSearchkey);
    /**
     * 刚进来隐藏软键盘
     */
    getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
    list_fragment = new ArrayList<>();
    list_table = new ArrayList<>();
    filterlist = new ArrayList<>();

    rlSearchTextResult.setOnClickListener(this);
    tvSearchTextResultBack.setOnClickListener(this);
    getData(mSearchkey);
    initTab();
    initEvent();
    vpTopic.setOffscreenPageLimit(4);
    vpTopic.setNoScroll(true);
    textsearchFilterOut.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        textsearchFilter.setTextColor(getResources().getColor(R.color.colorB3));
        Drawable drawable = getResources().getDrawable(R.drawable.img_search_filter_n);
        drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
        textsearchFilter.setCompoundDrawables(null, null, drawable, null);
        openMenu();
      }
    });
    drawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED, Gravity.RIGHT);
    filterFragment = new FilterFragments(ProductDetailActivity.this,mSearchkey);
    navView.addView(filterFragment);
  }

  /**
   * 操作软键盘
   */
  @Override public boolean dispatchKeyEvent(KeyEvent event) {
    if (event.getKeyCode() == KeyEvent.KEYCODE_ENTER) {
      InputMethodManager inputMethodManager =
          (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
      if (inputMethodManager.isActive()) {
        //EventBus.getDefault().post(new EventUtil(keyword));
        inputMethodManager.hideSoftInputFromWindow(
            ProductDetailActivity.this.getCurrentFocus().getWindowToken(), 0);
      }
      //通知接受
      return true;
    }

    return super.dispatchKeyEvent(event);
  }

  public void closeMenu() {
    drawerLayout.closeDrawer(GravityCompat.END);
  }

  public void openMenu() {
    //刷新筛选框中的数据
    EventBus.getDefault().post(new TranstEventil());
    drawerLayout.openDrawer(GravityCompat.END);
  }

  private void getData(String mExittext) {
    OkHttpUtils.get()
        .url(ApiConstants.TEXTSEARCHS)
        .addParams("text", mExittext)
        .addParams("pageId", 1 + "")
        .addParams("sortId", 0 + "")
        .build()
        .execute(new StringCallback() {

          @Override public void onError(Call call, Exception e, int id) {
            e.printStackTrace();
          }

          @Override public void onResponse(String response, int id) {
            textSearch = JSON.parseObject(response, TextsSearchEntity.class);
            //记录总页数
            if (textSearch.getData() != null && textSearch.getState() == 0) {
              filterlist.clear();
              filterlist.addAll(textSearch.getData().getFilters());
              DataPresenter.getSingleTon().saveData(filterlist);
              //保存数据
            } else if (textSearch.getState() == 3) {

            } else {

            }
          }
        });
  }

  @Override public void onClick(View v) {
    switch (v.getId()) {
      case R.id.tv_search_text_result_back:
        ActivityManager.getInstance().finishActivity(ProductDetailActivity.class);
        break;
      case R.id.rl_search_text_result:
        ActivityManager.getInstance().finishActivity(ProductDetailActivity.class);
        break;
    }
  }
  private void initTab() {
    list_fragment = new ArrayList<>();
    ComprehensiveFragment comprehensiveFragment = ComprehensiveFragment.newInstance(mSearchkey);
    SaleNumberFragment saleNumberFragment = SaleNumberFragment.newInstance(mSearchkey);
    PriceFragment priceFragment = PriceFragment.newInstance(mSearchkey);
    list_fragment.add(comprehensiveFragment);
    list_fragment.add(saleNumberFragment);
    list_fragment.add(priceFragment);
    list_table = new ArrayList<>();
    list_table.add("综合");
    list_table.add("销量");
    list_table.add("价格");
    topicTabLayout.setTabMode(TabLayout.MODE_FIXED);
    topicTabLayout.setSelectedTabIndicatorHeight(0);
    adapter = new ViewPagerAdapter(this, getSupportFragmentManager(), list_fragment, list_table);
    vpTopic.setAdapter(adapter);
    topicTabLayout.setupWithViewPager(vpTopic);
    /**
     * 调用本方法中的gettabview
     */
    for (int i = 0; i < topicTabLayout.getTabCount(); i++) {
      TabLayout.Tab tab = topicTabLayout.getTabAt(i);
      if (tab != null) {
        /**
         * 调用adapter中的gettabview
         */
        tab.setCustomView(adapter.getTabView(i));
        if (tab.getCustomView() != null) {
          View tabView = (View) tab.getCustomView().getParent();
          tabView.setTag(i);
          tabView.setOnClickListener(mTabOnClickListener);
        }
      }
    }

    vpTopic.setCurrentItem(1);
    vpTopic.setCurrentItem(0);
  }

  private View.OnClickListener mTabOnClickListener = new View.OnClickListener() {
    @Override public void onClick(View view) {
      int pos = (int) view.getTag();
      if (pos == 2) {
        TabLayout.Tab tab = topicTabLayout.getTabAt(pos);
        /**
         * 设置升序和降序的数据
         */
        setImage(tab);
      } else {
        TabLayout.Tab tab = topicTabLayout.getTabAt(pos);
        if (tab != null) {
          tab.select();
        }
        if (img_title!=null)
        img_title.setImageResource(R.drawable.search_icon_price_normal);
      }
    }
  };

  private void setImage(TabLayout.Tab tab) {

    switch (priceSortMode) {
      /** 从低到高的排序 */
      case 0:
        priceSortMode = 1;
        break;
      /** 当mode为1说明其已被选中，那么选择2，及从高到低 */
      case 1:
        priceSortMode = 2;
        break;
      /** 当mode为2说明其处于从高到低，那么选择1，从低到高 */
      case 2:
        priceSortMode = 1;
        break;
    }
    setPriceSortDrawable(priceSortMode, tab);
  }

  private void setPriceSortDrawable(int priceSortMode, TabLayout.Tab tab) {
    View view = tab.getCustomView();
    img_title = (ImageView) view.findViewById(R.id.title_iv);
    switch (priceSortMode) {
      case 0:
        img_title.setImageResource(R.drawable.search_icon_price_normal);
        break;
      case 1:
        img_title.setImageResource(R.drawable.search_icon_price_down);
        break;
      case 2:
        img_title.setImageResource(R.drawable.search_icon_price_up);
        break;
    }
  }

  private void initEvent() {
    topicTabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
      @Override public void onTabSelected(TabLayout.Tab tab) {
        changeTabSelect(tab);
        vpTopic.setCurrentItem(tab.getPosition());
      }

      @Override public void onTabUnselected(TabLayout.Tab tab) {
        changeTabNormal(tab);
      }

      @Override public void onTabReselected(TabLayout.Tab tab) {

      }
    });
  }

  private void changeTabSelect(TabLayout.Tab tab) {
    View view = tab.getCustomView();
    ImageView img_title = (ImageView) view.findViewById(R.id.title_iv);
    TextView txt_title = (TextView) view.findViewById(R.id.title_tv);
    txt_title.setTextColor(this.getResources().getColor(R.color.deep_main_color));
    if (txt_title.getText().toString().equals("综合")) {
      vpTopic.setCurrentItem(0);
      priceSortMode=0;
    } else if (txt_title.getText().toString().equals("销量")) {
      vpTopic.setCurrentItem(1);
      priceSortMode=0;
    } else if (txt_title.getText().toString().equals("价格")) {
      vpTopic.setCurrentItem(2);
    }else {
      priceSortMode=0;
    }
  }

  private void changeTabNormal(TabLayout.Tab tab) {
    View view = tab.getCustomView();
    ImageView img_title = (ImageView) view.findViewById(R.id.title_iv);
    TextView txt_title = (TextView) view.findViewById(R.id.title_tv);
    txt_title.setTextColor(Color.BLACK);
    if (txt_title.getText().toString().equals("价格")) {

    } else {

    }
  }

  @Override protected void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
  }

  public void onEventMainThread(TextEventUtil event) {
    String msglog = event.getMsgs();
    if ("".equals(msglog)) {
      openMenu();
    }
  }

  /**
   * 完成监听
   */
  public void onEventMainThread(FinishEventUtil event) {
    closeMenu();
  }
  /**
   * 完成监听,筛选变化
   */
  public void onEventMainThread(SecondEventil event) {
    List<String> filter = event.getFilter();
    if (filter.size()>0){
      textsearchFilter.setTextColor(getResources().getColor(R.color.deep_main_color));
      Drawable drawable = getResources().getDrawable(R.drawable.img_search_filter_f);
      drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
      textsearchFilter.setCompoundDrawables(null, null, drawable, null);
    }else {
      textsearchFilter.setTextColor(getResources().getColor(R.color.colorB3));
      Drawable drawable = getResources().getDrawable(R.drawable.img_search_filter_n);
      drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
      textsearchFilter.setCompoundDrawables(null, null, drawable, null);
    }
  }

  @Override protected void onStop() {
    super.onStop();
    //清空被选中的集合
    System.out.println("文字搜索的onstop走了");
    DataPresenter.getSingleTon().getSelectList().clear();
    DataPresenter.getSingleTon().getSearchlist().clear();
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // TODO: add setContentView(...) invocation
    ButterKnife.bind(this);
  }
}

