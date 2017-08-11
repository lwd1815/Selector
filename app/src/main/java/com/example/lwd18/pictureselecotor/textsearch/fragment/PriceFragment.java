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
import android.widget.Toast;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import com.alibaba.fastjson.JSON;
import com.example.lwd18.pictureselecotor.ApiConstants;
import com.example.lwd18.pictureselecotor.BaseFragment;
import com.example.lwd18.pictureselecotor.R;
import com.example.lwd18.pictureselecotor.TextsSearchEntity;
import com.example.lwd18.pictureselecotor.textsearch.Eventutil.SecondEventil;
import com.example.lwd18.pictureselecotor.textsearch.adapter.PriceAdapter;
import com.example.lwd18.pictureselecotor.textsearch.utils.TextPriceEventUtil;
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
 * 描述	      ${TODO}
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class PriceFragment extends BaseFragment {
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
  boolean isNetCollect = true;
  private PriceAdapter adapter;
  private TextsSearchEntity textSearch;
  private String msg="";
  private int sort=2;
  private List<TextsSearchEntity.DataBean.FiltersBean> filterlist;
  private List<TextsSearchEntity.DataBean.FiltersBean> newfilterlist;

  public static PriceFragment newInstance(String text) {
    Bundle args = new Bundle();
    args.putString("exittext", text);
    PriceFragment fragment = new PriceFragment();
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
    getData(currentPage,msg,sort);
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
    adapter = new PriceAdapter();
    mRvProductDetail.setAdapter(adapter);
    mSrProductDetail.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
      @Override public void onRefresh() {
        mHandler.postDelayed(new Runnable() {
          @Override public void run() {
            getData(1,msg,sort);
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
                getData(currentPage,msg,sort);
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

  }


  //从接口出验证sortid==1和sortid==2请求到的数据时一样的
  private void getData(int index,String msg,int sort) {
    Log.w(TAG, "价格倒序");
    OkHttpUtils.get()
        .url(ApiConstants.TEXTSEARCHS)
        .addParams("text", mExittext)
        .addParams("pageId", index + "")
        .addParams("sortId", sort + "")
        .addParams("params",msg)
        //.addParams("params","德国#全脂#进口")
        .build()
        .execute(new StringCallback() {

          @Override public void onError(Call call, Exception e, int id) {

          }

          @Override public void onResponse(String response, int id) {
            Log.w("刚获取数据时", response);
            textSearch = JSON.parseObject(response, TextsSearchEntity.class);
            System.out.println("TextSearchResponse=====" + response);
            //记录总页数
            if (textSearch.getData()!=null&&textSearch.getState() == 0) {
              sumpageid.clear();
              sumpageid.add(textSearch.getData().getPageTotal());

              if (rlNetStates!=null) {
                rlNetStates.setVisibility(View.GONE);
              }
              list.clear();
              newlist.clear();
              filterlist.clear();
              newfilterlist.clear();

              list.addAll(textSearch.getData().getItems());
              newlist.addAll(list);
              if (textSearch.getData().getFilters() != null) {
                filterlist.addAll(textSearch.getData().getFilters());
                newfilterlist.addAll(filterlist);
                adapter.addFilterItem(newfilterlist);
              }else {
                adapter.notifyDataSetChanged();
              }
              System.out.println("position====" + position);
              if (position == 1) {
                adapter.addItem(newlist);
                adapter.notifyDataSetChanged();
              } else {
                adapter.addMoreItem(newlist);
              }
            } else if (textSearch.getState() == 3) {
              Toast.makeText(getContext(), "查无数据", Toast.LENGTH_SHORT).show();
              adapter.notifyDataSetChanged();
            } else {

            }
          }
        });
  }

  /**
   * 用来接收筛选框中传递过来的消息
   */
  public void onEventMainThread(SecondEventil event) {
    List<String> filter = event.getFilter();
    System.out.println("接收到筛选框确定后发来的消息了====" + filter.size());
    List<String> newlist = new ArrayList<>();
    newlist.clear();
    newlist.addAll(filter);
    for (String s : newlist) {
      msg += s + "#";
      System.out.println("接收到筛选框确定后发来的消息了====" + s);
    }
    msg = msg.substring(0, msg.length() - 1);
    System.out.println("接收到筛选框确定msg====" + msg);
    getData(1, msg,sort);
    msg = "";
    mSrProductDetail.setRefreshing(false);//刷新完毕!
    isloading = false;
    currentPage = 1;
    position = 1;
  }

  ///**
  // * 用来接收综合界面筛选项点击时传递过来的消息
  // */
  //public void onEventMainThread(TextEventUtil event) {
  //  String msglog = event.getMsgs();
  //  System.out.println("综合界面筛选项点击时传递过来的消息====" + msglog);
  //  if ("".equals(msglog)){return;}
  //  msg = msglog;
  //  filterlist.clear();
  //  newfilterlist.clear();
  //  getData(1, msg,sort);
  //  mSrProductDetail.setRefreshing(false);//刷新完毕!
  //  isloading = false;
  //  currentPage = 1;
  //  position = 1;
  //}

  /**
   * 用来接收消息
   */
  public void onEventMainThread(TextPriceEventUtil event) {
    int sorts = event.getSort();
    sort=sorts;
    System.out.println("接收到消息了====" + sorts);
    getData(1, msg, this.sort);
    mSrProductDetail.setRefreshing(false);//刷新完毕!
    isloading = false;
    currentPage = 1;
    position = 1;
  }


  @Override public void onDestroyView() {
    super.onDestroyView();
    unbinder.unbind();
  }

  @Override public void onDestroy() {
    super.onDestroy();
    EventBus.getDefault().unregister(this);
  }

}
