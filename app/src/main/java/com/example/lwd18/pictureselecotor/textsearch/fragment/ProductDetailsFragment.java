package com.example.lwd18.pictureselecotor.textsearch.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.example.lwd18.pictureselecotor.BaseFragment;
import com.example.lwd18.pictureselecotor.NoScrollViewPager;
import com.example.lwd18.pictureselecotor.R;
import com.example.lwd18.pictureselecotor.SetIndicator;
import com.example.lwd18.pictureselecotor.textsearch.Eventutil.EventUtil;
import com.example.lwd18.pictureselecotor.textsearch.adapter.ViewPagerAdapter;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.List;


/**
 * 创建者     李文东
 * 创建时间   2017/6/16 16:36
 * 描述	      ${TODO}
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class ProductDetailsFragment extends BaseFragment {
  private static final int TAB_MARGIN_DIP = 84;
  @BindView(R.id.topic_tabLayout) TabLayout topicTabLayout;
  @BindView(R.id.vp_topic) NoScrollViewPager vpTopic;
  private View mView;
  private ViewPagerAdapter adapter;
  List<BaseFragment> list_fragment;
  List<String> list_table;
  private String mExittext;

  public static ProductDetailsFragment newInstance(String text) {
    Bundle args = new Bundle();
    args.putString("exittext", text);
    ProductDetailsFragment productDetailsFragment = new ProductDetailsFragment();
    productDetailsFragment.setArguments(args);
    return productDetailsFragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mExittext = getArguments().getString("exittext");
    EventBus.getDefault().register(this);
  }

  public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    mView = View.inflate(getContext(), R.layout.fragment_product_detail, null);
    ButterKnife.bind(this, mView);
    initTab();
    // TODO: 2017/5/27 有待优化
    /**
     * 强制设置viewpage的子页面是4,防止滑动之后销毁
     */
    vpTopic.setOffscreenPageLimit(4);
    vpTopic.setNoScroll(true);
    return mView;
  }

  @Override public void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
  }

  private int[] imageResId = {
      0, 0, R.drawable.search_icon_price_normal, R.mipmap.cate_filter
  };

  private void initTab() {
    list_fragment = new ArrayList<>();
    ComprehensiveFragment comprehensiveFragment = ComprehensiveFragment.newInstance(mExittext);
    SaleNumberFragment saleNumberFragment = SaleNumberFragment.newInstance(mExittext);
    PriceFragment priceFragment = PriceFragment.newInstance(mExittext);
    FilterFragment filterFragment = FilterFragment.newInstance(mExittext);
    list_fragment.add(comprehensiveFragment);
    list_fragment.add(saleNumberFragment);
    list_fragment.add(priceFragment);
    list_fragment.add(filterFragment);
    list_table = new ArrayList<>();
    list_table.add("综合");
    list_table.add("销量");
    list_table.add("价格");
    list_table.add("筛选");
    topicTabLayout.setTabMode(TabLayout.MODE_FIXED);
    topicTabLayout.addTab(topicTabLayout.newTab().setText(list_table.get(0)));
    topicTabLayout.addTab(topicTabLayout.newTab().setText(list_table.get(1)));
    topicTabLayout.addTab(topicTabLayout.newTab()
        .setText(list_table.get(2))
        .setIcon(R.drawable.search_icon_price_down));
    topicTabLayout.addTab(
        topicTabLayout.newTab().setText(list_table.get(3)).setIcon(R.mipmap.cate_filter));
    adapter =
        new ViewPagerAdapter(getContext(), getChildFragmentManager(), list_fragment, list_table);
    vpTopic.setAdapter(adapter);
    topicTabLayout.setupWithViewPager(vpTopic);
    //        for (int i = 0; i < list_fragment.size(); i++) {
    //            TabLayout.Tab tabAt = topicTabLayout.getTabAt(i);
    //            tabAt.setCustomView(adapter.getTabView(i));
    //        }
    setupTabIcons();
    topicTabLayout.post(new Runnable() {
      @Override public void run() {
        SetIndicator.setIndicator(topicTabLayout, 33, 29);
      }
    });
  }

  private void setupTabIcons() {
    topicTabLayout.getTabAt(0).setCustomView(getTabView(0));
    topicTabLayout.getTabAt(1).setCustomView(getTabView(1));
    topicTabLayout.getTabAt(2).setCustomView(getTabView(2));
    topicTabLayout.getTabAt(3).setCustomView(getTabView(3));
  }

  public View getTabView(int position) {
    View view = View.inflate(getContext(), R.layout.custom_tabtitle, null);
    TextView tv = (TextView) view.findViewById(R.id.title_tv);
    ImageView iv = (ImageView) view.findViewById(R.id.title_iv);
    iv.setImageResource(imageResId[position]);
    tv.setText(list_table.get(position));
    return view;
  }

  /**
   * 用来接收消息
   */
  public void onEventMainThread(EventUtil event) {
    String msglog = event.getMsg();
    mExittext = msglog;
  }
}
