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
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
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
import com.example.lwd18.pictureselecotor.textsearch.DataPresenter;
import com.example.lwd18.pictureselecotor.textsearch.Eventutil.SecondEventil;
import com.example.lwd18.pictureselecotor.textsearch.resultadapter.SaleNumberAdapter;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import de.greenrobot.event.EventBus;
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

public class SaleNumberFragment extends BaseFragment {
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
  private String msg="";
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
  private SaleNumberAdapter adapter;
  private TextsSearchEntity textSearch;
  private int y=0;
  private List<TextsSearchEntity.DataBean.FiltersBean> filterlist;
  private List<TextsSearchEntity.DataBean.FiltersBean> newfilterlist;

  public static SaleNumberFragment newInstance(String text) {
    Bundle args = new Bundle();
    args.putString("exittext", text);
    SaleNumberFragment fragment = new SaleNumberFragment();
    fragment.setArguments(args);
    return fragment;
  }

  @Override public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    mExittext = getArguments().getString("exittext");
    /**
     * 注册eventbus用来接收搜索的关键字
     */
    EventBus.getDefault().register(this);
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
    getData(currentPage,msg);
    list.clear();
    newlist.clear();
    filterlist.clear();
    newfilterlist.clear();
    //用来记录currentpage
    mSrProductDetail.setEnabled(false);
    mLinearLayoutManager = new LinearLayoutManager(getContext());
    mLinearLayoutManager.setOrientation(OrientationHelper.VERTICAL);
    mRvProductDetail.setLayoutManager(mLinearLayoutManager);
    //添加分隔线
    adapter = new SaleNumberAdapter();
    mRvProductDetail.setAdapter(adapter);
    mRvProductDetail.addOnScrollListener(new RecyclerView.OnScrollListener() {
      @Override public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
      }

      @Override public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
        super.onScrolled(recyclerView, dx, dy);
        int lastVisibleItemPosition = mLinearLayoutManager.findLastVisibleItemPosition();
        if (lastVisibleItemPosition + 1 == adapter.getItemCount()) {
          boolean isRefreshing = mSrProductDetail.isRefreshing();
          if (isRefreshing) {
            adapter.notifyItemRemoved(adapter.getItemCount());
            return;
          }
          if (isloading && dy >= 0) {

          }
          //搜索之后,关键字为空时,上拉加载
          if (!isloading) {
            isloading = true;
            currentPage++;

            if (currentPage > sumpageid.get(0)) {
              currentPage = sumpageid.get(0);
              return;
            }
            mHandler.postDelayed(new Runnable() {
              @Override public void run() {
                getData(currentPage,msg);
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
        getData(1,msg);
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

  private void getData(int index, final String msg) {
    Log.w(TAG, "销量倒序");
    OkHttpUtils.get()
        .url(ApiConstants.TEXTSEARCHS)
        .addParams("text", mExittext)
        .addParams("pageId", index+"")
        .addParams("sortId",3+"")
        .addParams("params",msg)
        .build()
        .execute(new StringCallback() {

          @Override public void onError(Call call, Exception e, int id) {
            if (isNetCollect) {
              stateLayout.setEmptyImage(R.mipmap.wenzisousuo_v2_2x);
              stateLayout.setEmptyText("抱歉,  没有找到相关商品请试试其他搜索词");
              stateLayout.showEmpty();
            } else {
              stateLayout.showError();
            }
          }

          @Override public void onResponse(String response, int id) {
            Log.w("刚获取数据时", response);
            textSearch = JSON.parseObject(response, TextsSearchEntity.class);
            System.out.println("TextSearchResponse====="+response);
            //记录总页数
            if (textSearch.getData()!=null&&textSearch.getState() == 0) {
              sumpageid.clear();
              sumpageid.add(textSearch.getData().getPageTotal());
              stateLayout.showContent();
              if (rlNetStates!=null) {
                rlNetStates.setVisibility(View.GONE);
              }
              list.clear();
              newlist.clear();
              filterlist.clear();
              newfilterlist.clear();

              list.addAll(textSearch.getData().getItems());
              newlist.addAll(list);
              if (textSearch.getData().getFilters()!=null) {
                filterlist.addAll(textSearch.getData().getFilters());
                newfilterlist.addAll(filterlist);
                adapter.addFilterItem(newfilterlist);
              }
              System.out.println("position====" + position);
              if (position == 1) {
                adapter.addItem(newlist);
                adapter.notifyDataSetChanged();
              } else {
                adapter.addMoreItem(newlist);
              }
            } else if (textSearch.getState() == 3) {
              // TODO: 2017/8/8 如果没有查询到数据,以前数据不变.如果有数据,累加(感觉有问题,既是筛选就应该搜什么就显示什么,不应该累加)
              if (DataPresenter.getSingleTon().getSearchlist().size()>0) {
                adapter.addItem(DataPresenter.getSingleTon().getSearchlist());
              }else {
                stateLayout.setEmptyImage(R.mipmap.wenzisousuo_v2_2x);
                stateLayout.setEmptyText("抱歉,  没有找到相关商品请试试其他搜索词");
                stateLayout.showEmpty();
              }
              Toast.makeText(getMContext(), "很抱歉!没有找到相关商品,换个搜索词试试看?", Toast.LENGTH_SHORT).show();
              stateLayout.setOnTouchListener(new View.OnTouchListener() {
                @Override public boolean onTouch(View v, MotionEvent event) {
                  switch (event.getAction()){
                    case MotionEvent.ACTION_DOWN:
                      System.out.println("按下时的坐标======"+event.getY());
                      y= (int) event.getY();
                      break;
                    case MotionEvent.ACTION_UP:
                      System.out.println("抬起时的坐标======"+event.getY());
                      if (event.getY()-y>=100){
                        stateLayout.showContent();
                        mSrProductDetail.setRefreshing(true);//刷新完毕!
                        mHandler.postDelayed(new Runnable() {
                          @Override public void run() {
                            position = 1;
                            getData(1,msg);
                            mSrProductDetail.setRefreshing(false);//刷新完毕!
                            isloading = false;
                            currentPage = 1;
                          }
                        }, 1000);
                      }
                      break;
                  }
                  return true;
                }
              });
            } else {
              stateLayout.setEmptyImage(R.mipmap.wenzisousuo_v2_2x);
              stateLayout.setEmptyText("抱歉,  没有找到相关商品请试试其他搜索词");
              stateLayout.showEmpty();
            }
          }
        });
  }

  /**
   * 用来接收筛选框中传递过来的消息
   */
  public void onEventMainThread(SecondEventil event) {
    //List<String> filter = event.getFilter();
    List<String> filter=DataPresenter.getSingleTon().getSelectList();
    System.out.println("接收到筛选框确定后发来的消息了====" + filter.size());
    List<String> newlist = new ArrayList<>();
    newlist.clear();
    newlist.addAll(filter);
    for (String s : newlist) {
      msg += s + "#";
      System.out.println("接收到筛选框确定后发来的消息了====" + s);
    }
    msg = msg.substring(0, msg.length()>=1? msg.length()- 1:0);
    System.out.println("接收到筛选框确定msg====" + msg);
    getData(1, msg);
    msg = "";
    mSrProductDetail.setRefreshing(false);//刷新完毕!
    isloading = false;
    currentPage = 1;
    position = 1;
  }

  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }
}
