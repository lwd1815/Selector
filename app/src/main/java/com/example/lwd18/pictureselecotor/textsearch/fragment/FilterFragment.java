package com.example.lwd18.pictureselecotor.textsearch.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.alibaba.fastjson.JSON;
import com.deepbaytech.deeplibrary.utils.InternetUtils;
import com.ethereal.deepstatelayout.DStateLayout;
import com.example.lwd18.pictureselecotor.ApiConstants;
import com.example.lwd18.pictureselecotor.BaseFragment;
import com.example.lwd18.pictureselecotor.R;
import com.example.lwd18.pictureselecotor.TextsSearchEntity;
import com.example.lwd18.pictureselecotor.textsearch.resultadapter.FilterAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import okhttp3.Call;

/**
 * 创建者     李文东
 * 创建时间   2017/6/19 11:09
 * 描述
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述
 */

public class FilterFragment extends BaseFragment {
  private static final String TAG = "ComprehensiveFragment";
  @BindView(R.id.rv_product_detail) RecyclerView mRvProductDetail;
  @BindView(R.id.sr_product_detail) SwipeRefreshLayout mSrProductDetail;
  @BindView(R.id.te_no_product_detail) TextView mTeNoProductDetail;
  @BindView(R.id.No_re_product_detail) RelativeLayout mNoReProductDetail;
  Unbinder unbinder;
  @BindView(R.id.tv_net_state_wifi) TextView tvNetStateWifi;
  @BindView(R.id.rl_net_states) RelativeLayout rlNetStates;
  private String mExittext;
  private LinearLayoutManager mLinearLayoutManager;
  /**
   * 下拉刷新相关
   */
  private int currentPage = 1;
  boolean isloading;
  private List<TextsSearchEntity.DataBean.ItemsBean> list;
  private List<Integer> sumpageid;
  //记录下拉刷新次数
  private int position = 1;
  private List<TextsSearchEntity.DataBean.ItemsBean> newlist;
  private Handler mHandler = new Handler();
  private DStateLayout stateLayout;
  boolean isNetCollect = true;
  private FilterAdapter adapter;
  private TextsSearchEntity textSearch;

  private List<TextsSearchEntity.DataBean.FiltersBean> filterlist;
  private List<TextsSearchEntity.DataBean.FiltersBean> newfilterlist;

  public static FilterFragment newInstance(String text) {
    Bundle args = new Bundle();
    args.putString("exittext", text);
    FilterFragment fragment = new FilterFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mExittext = getArguments().getString("exittext");
    /**
     * 注册eventbus用来接收搜索的关键字
     */
  }

  @Nullable @Override
  public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
      @Nullable Bundle savedInstanceState) {
    View view = View.inflate(getContext(), R.layout.fragment_item_product, null);
    unbinder = ButterKnife.bind(this, view);
    initview();
    return view;
  }

  private void initview() {
    list = new ArrayList<>();
    newlist = new ArrayList<>();
    filterlist = new ArrayList<>();
    newfilterlist = new ArrayList<>();

    sumpageid = new Vector<>();
    getData(currentPage);
    list.clear();
    newlist.clear();
    filterlist.clear();
    newfilterlist.clear();
    //用来记录currentpage
    mSrProductDetail.setProgressBackgroundColorSchemeResource(android.R.color.white);
    mSrProductDetail.setColorSchemeResources(android.R.color.holo_blue_light,
        android.R.color.holo_red_light, android.R.color.holo_orange_light,
        android.R.color.holo_green_light);
    mSrProductDetail.setProgressViewOffset(false, 0,
        (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 24,
            getResources().getDisplayMetrics()));
    mLinearLayoutManager = new LinearLayoutManager(getContext());
    mLinearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
    mRvProductDetail.setLayoutManager(mLinearLayoutManager);
    //添加分隔线
    //mUtc.addItemDecoration(new AdvanceDecoration(getContext(), OrientationHelper.VERTICAL));
    adapter = new FilterAdapter();
    mRvProductDetail.setAdapter(adapter);
    mSrProductDetail.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
          @Override public void run() {
            getData(1);
            mSrProductDetail.setRefreshing(false);//刷新完毕!
            isloading = false;
            currentPage = 1;
            position = 1;
          }
        }, 500);
      }
    });
    mRvProductDetail.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
      }

      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
        Log.v("lastVisibleItemPosition", lastVisibleItemPosition + "");
        Log.v("mAdapter.getItemCount()", adapter.getItemCount() + "");

        if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {
          boolean isRefreshing = mSrProductDetail.isRefreshing();
          if (isRefreshing) {
            adapter.notifyItemRemoved(adapter.getItemCount());
            return;
          }
          Log.v("isloading", isloading + "");
          if (isloading && dy >= 0) {
            //Toast.makeText(getContext(), "没有更多数据了", Toast.LENGTH_SHORT).show();
          }
          //搜索之后,关键字为空时,上拉加载
          System.out.println("isloadiong====" + isloading);
          if (!isloading) {
            Log.v("isloading2", isloading + "");
            Log.v("sumpageid", sumpageid.get(0) + "");
            isloading = true;
            currentPage++;

            if (currentPage > sumpageid.get(0)) {
              currentPage = sumpageid.get(0);
              return;
            }
            mHandler.postDelayed(new Runnable() {
              @Override public void run() {
                getData(currentPage);
                System.out.println("current111==" + currentPage);
                Log.v("currentpag++", currentPage + "");
                isloading = false;
                position = 2;
              }
            }, 1000);
          }
        }
      }
    });
    rlNetStates.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Intent wifiSettingsIntent = new Intent(Settings.ACTION_SETTINGS);
        startActivity(wifiSettingsIntent);
      }
    });
    initStatu();
  }
  /**
   * 网络状态
   */
  private void initStatu() {
    stateLayout = DStateLayout.wrap(mSrProductDetail);
    stateLayout.setRetryListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        //initData(productId);
        getData(1);
      }
    });
    stateLayout.showLoading();

    isNetCollect = inspectNet();
    if (!isNetCollect) {
      rlNetStates.setVisibility(View.VISIBLE);
    } else {
      rlNetStates.setVisibility(View.GONE);
    }
  }

  @Override public void onNetChange(int netMobile) {
    super.onNetChange(netMobile);
    //网络状态变化时的操作
    if (netMobile == InternetUtils.NETWORK_NONE) {
      rlNetStates.setVisibility(View.VISIBLE);
      isNetCollect = false;
    } else {
      rlNetStates.setVisibility(View.GONE);
      isNetCollect = true;
    }
  }

  private void getData(int index) {
    Log.w(TAG, "开始重新请求数据");
    OkHttpUtils.get()
        .url(ApiConstants.TEXTSEARCHS)
        .addParams("text", mExittext)
        .addParams("pageId", index+"")
        .addParams("sortId",0+"")
        //.addParams("params","德国#全脂#进口")
        .build()
        .execute(new StringCallback() {

          @Override public void onError(Call call, Exception e, int id) {
            if (isNetCollect) {
              stateLayout.setEmptyImage(R.mipmap.wenzisousuo_v2_2x);
              stateLayout.setEmptyText("抱歉,没有找到相关商品请试试其他搜索词");
              stateLayout.showEmpty();
            } else {
              stateLayout.showError();
            }
          }

          @Override public void onResponse(String response, int id) {
            textSearch = JSON.parseObject(response, TextsSearchEntity.class);
            //记录总页数
            if (textSearch.getState() == 0) {
              sumpageid.clear();
              sumpageid.add(textSearch.getData().getPageTotal());
              stateLayout.showContent();
              rlNetStates.setVisibility(View.GONE);
              list.clear();
              newlist.clear();
              filterlist.clear();
              newfilterlist.clear();

              list.addAll(textSearch.getData().getItems());
              newlist.addAll(list);
              if (textSearch.getData().getFilters()!=null) {
                filterlist.addAll(textSearch.getData().getFilters());
                newfilterlist.addAll(filterlist);
              }
              if (position == 1) {
                adapter.addItem(newlist);
                adapter.addFilterItem(newfilterlist);
              } else {
                adapter.addMoreItem(newlist);
              }
            } else if (textSearch.getState() == 3) {
              stateLayout.setEmptyImage(R.mipmap.wenzisousuo_v2_2x);
              stateLayout.setEmptyText("抱歉,没有找到相关商品请试试其他搜索词");
              stateLayout.showEmpty();
            } else {
              stateLayout.showError();
            }
          }
        });
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }
}
