package com.example.lwd18.pictureselecotor.productdetail;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.view.View;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.alibaba.fastjson.JSON;
import com.deepbaytech.deeplibrary.utils.InternetUtils;
import com.ethereal.deepstatelayout.DStateLayout;
import com.example.lwd18.pictureselecotor.ApiConstants;
import com.example.lwd18.pictureselecotor.BaseDeepActivity;
import com.example.lwd18.pictureselecotor.ProductDetailEntity;
import com.example.lwd18.pictureselecotor.R;
import com.example.lwd18.pictureselecotor.TextSearchRecomdEntity;
import com.example.lwd18.pictureselecotor.TextSearchSameEntity;
import com.example.lwd18.pictureselecotor.bigphotobrows.ImageBrowseActivity;
import com.example.lwd18.pictureselecotor.textsearch.HttpMethodLogin;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;
import rx.Subscriber;



public class GoodsDetailActivity extends BaseDeepActivity
    implements View.OnClickListener , NestedScrollView.OnScrollChangeListener{

  @BindView(R.id.top_news_viewpager_ll) LinearLayout mTopNewsViewpagerLl;
  @BindView(R.id.dots_ll) LinearLayout mDotsLl;
  @BindView(R.id.appbar) AppBarLayout mAppbar;
  @BindView(R.id.comment_simple) TextView mCommentSimple;
  @BindView(R.id.comment_min) TextView mCommentMin;
  @BindView(R.id.comment_simple_price) TextView mCommentSimplePrice;
  @BindView(R.id.comment_simple_prices) TextView mCommentSimplePrices;
  @BindView(R.id.look) TextView mLook;
  @BindView(R.id.taobao) TextView mTaobao;
  @BindView(R.id.tao_price) TextView mTaoPrice;
  @BindView(R.id.tianmao) TextView mTianmao;
  @BindView(R.id.tm_price) TextView mTmPrice;
  @BindView(R.id.jd) TextView mJd;
  @BindView(R.id.jd_price) TextView mJdPrice;
  @BindView(R.id.guomei) TextView mGuomei;
  @BindView(R.id.guomei_price) TextView mGuomeiPrice;
  @BindView(R.id.SUnin) TextView mSUnin;
  @BindView(R.id.SUnin_price) TextView mSUninPrice;
  @BindView(R.id.dd) TextView mDd;
  @BindView(R.id.dd_price) TextView mDdPrice;
  @BindView(R.id.yamaxun) TextView mYamaxun;
  @BindView(R.id.yamaxun_price) TextView mYamaxunPrice;
  @BindView(R.id.yihaodian) TextView mYihaodian;
  @BindView(R.id.yihaodian_price) TextView mYihaodianPrice;
  @BindView(R.id.rv_goods) RecyclerView mRvGoods;
  @BindView(R.id.rv_goods_commend) RecyclerView mRvGoodsCommend;
  @BindView(R.id.click_more) TextView mMore;
  @BindView(R.id.webview) WebView mWebview;
  @BindView(R.id.scrollview_first) ScrollView scrollviewFirst;
  @BindView(R.id.tv_net_state_wifi) TextView tvNetStateWifi;
  @BindView(R.id.rl_net_states) RelativeLayout rlNetStates;
  @BindView(R.id.tv_amount_price) TextView tvAmountPrice;
  @BindView(R.id.ll_ware_item_amount) LinearLayout llWareItemAmount;
  @BindView(R.id.tv_rebate_price_textsearch) TextView tvRebatePriceTextsearch;
  @BindView(R.id.ll_ware_item_textsearch) RelativeLayout llWareItemTextsearch;
  @BindView(R.id.scroll1) NestedScrollView scroll1;
  @BindView(R.id.ware_toolbar_back) TextView wareToolbarBack;
  @BindView(R.id.tv_good_detail_title_good) TextView tvGoodDetailTitleGood;
  @BindView(R.id.view_line) View line;
  @BindView(R.id.ll_good_detail) RelativeLayout llGoodDetail;
  //存放指示点的集合
  private List<ImageView> pointList = new ArrayList<>();
  //上一个指示点
  private int lastPosition = 0;
  private RollViewPagers rollViewPager;
  private List<String> list;
  private List<TextSearchSameEntity.DataBean.ItemsBean> proList;
  private List<TextSearchSameEntity.DataBean.ItemsBean> newlist;
  private FirstAdapter mFirstAdapter;
  private String productId;
  private DStateLayout stateLayout;
  boolean isNetCollect = true;
  private int position = 1;
  private int price;
  private String lv;
  private int height=180;
  private SecondAdapter secondAdapter;

  @Override protected void initActivity() {
    setContentView(R.layout.activity_goods_detail);
    ButterKnife.bind(this);
    Intent intent = getIntent();
    productId = intent.getStringExtra("productId");
    price = intent.getIntExtra("price", 0);
    lv = intent.getStringExtra("lv");
    initAppBar();
    //获取商品基本信息
    getData();
    //获取相同商品数据
    getRecomdData(1);
    //获取推荐商品数据
    getRecommendData(1);
    proList = new ArrayList<>();
    newlist = new ArrayList<>();
    scroll1.setOnScrollChangeListener(GoodsDetailActivity.this);
    initFirstRv();
    //initSecondRv();
    mCommentSimplePrices.setText("¥" + lv);
    initClick();
  }



  private void initAppBar() {
    list = new ArrayList<>();
    wareToolbarBack.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        finish();
      }
    });
    mWebview.getSettings().setJavaScriptEnabled(true);
    mWebview.loadUrl("http://api.deepbaytech.com/mobile/prod-detail-chart.html");
  }

  private void initFirstRv() {
    LinearLayoutManager line = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
    mFirstAdapter = new FirstAdapter();
    mRvGoods.setLayoutManager(line);
    mRvGoods.setAdapter(mFirstAdapter);
  }

  private void initClick() {
    mMore.setOnClickListener(this);
    mTaobao.setOnClickListener(this);
    mTianmao.setOnClickListener(this);
    mJd.setOnClickListener(this);
    mGuomei.setOnClickListener(this);
    mSUnin.setOnClickListener(this);
    mDd.setOnClickListener(this);
    mYamaxun.setOnClickListener(this);
    mYihaodian.setOnClickListener(this);
  }

  private void initSecondRv() {
    GridLayoutManager line = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);
    secondAdapter = new SecondAdapter();
    mRvGoodsCommend.setLayoutManager(line);
    mRvGoodsCommend.setAdapter(secondAdapter);
  }

  private void initview() {
    lastPosition = 0;
    //第一步：添加轮播图（也可以直接将布局写成咱们自定义的viewpager）
    rollViewPager = new RollViewPagers(GoodsDetailActivity.this);
    mTopNewsViewpagerLl.addView(rollViewPager);
    //第二步：传递轮播图需要的图片url集合或者数组
    //List<SpecialDetailEntity> list = entity.getItems();
    rollViewPager.setImageUrlsList(list);

    //第三步：添加指示点
    addPoints();
  }

  private void initlistener() {
    //第四步：给轮播图添加界面改变监听，切换指示点
    rollViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
      @Override public void onPageSelected(int position) {
        position = position % list.size();
        //切换指示点
        pointList.get(lastPosition).setImageResource(R.mipmap.dot_normal);
        pointList.get(position).setImageResource(R.mipmap.dot_focus);
        lastPosition = position;
      }

      @Override
      public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
      }

      @Override public void onPageScrollStateChanged(int state) {
      }
    });

    //第六步：设置当前页面，最好不要写最大数除以2，其实写了50就足够了，谁没事无聊到打开app二话不说直接对着轮播图往相反方向不停的划几十下
    rollViewPager.setCurrentItem(50 - 50 % list.size());

    //第七步：设置完之后就可以轮播了：开启自动轮播
    rollViewPager.startRoll();
  }

  //        //第五步：轮播图点击监听
  private void onclickvp(final List<String> lists) {
    final List<String> list = new ArrayList<>();
    rollViewPager.setOnItemClickListener(new RollViewPagers.OnItemClickListener() {
      @Override public void onItemClick(int position) {
        //进入photoview
        for (int i = 0; i < lists.size(); i++) {
          list.add(lists.get(i));
        }
        ImageBrowseActivity.startActivity(getBaseContext(), (ArrayList<String>) list, position);
      }
    });
  }

  private void addPoints() {
    pointList.clear();
    mDotsLl.removeAllViews();
    for (int x = 0; x < list.size(); x++) {
      ImageView imageView = new ImageView(getBaseContext());
      imageView.setImageResource(R.mipmap.img_02_03);
      //导报的时候指示点的父View是什么布局就导什么布局的params,这里导的是线性布局
      LinearLayout.LayoutParams params =
          new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
              LinearLayout.LayoutParams.WRAP_CONTENT);
      //设置间隔
      params.leftMargin = 36;
      //添加到线性布局;params指定布局参数，不然点就按在一起了
      mDotsLl.addView(imageView, params);
      //把指示点存放到集合中
      pointList.add(imageView);
    }
  }

  /**
   * 网络状态
   */
  private void initStatu() {
    stateLayout = DStateLayout.wrap(scrollviewFirst);
    stateLayout.setRetryListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        //initData(productId);
        getData();
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

  private void getData() {
    OkHttpUtils.get().url(ApiConstants.PRODUCTSEARCH)
        .addParams("pid", productId)
        .build().execute(new StringCallback() {

      @Override public void onError(Call call, Exception e, int id) {
        if (isNetCollect) {
          stateLayout.showEmpty();
        } else {
          stateLayout.showError();
        }
      }

      @Override public void onResponse(String response, int id) {
        final ProductDetailEntity productDetailEntity =
            JSON.parseObject(response, ProductDetailEntity.class);
        //记录总页数
        if (productDetailEntity.getState() == 0) {
          //stateLayout.showContent();
          rlNetStates.setVisibility(View.GONE);
          list.clear();
          proList.clear();
          list.addAll(productDetailEntity.getData().getPicUrl());
          initview();
          initlistener();
          onclickvp(list);
          String s = "推荐商家 [ "
              + productDetailEntity.getData().getPlatform()
              + " ] "
              + productDetailEntity.getData().getTitle();
          SpannableStringBuilder style = new SpannableStringBuilder(s);
          style.setSpan(
              new ForegroundColorSpan(getResources().getColor(R.color.main_tab_select_color)), 0,
              productDetailEntity.getData().getPlatform().length() + 10,
              Spanned.SPAN_EXCLUSIVE_INCLUSIVE);
          mCommentSimple.setText(style);
          ProductDetailEntity.DataBean ware = productDetailEntity.getData();
          mCommentSimplePrice.setText("¥" + ware.getPrice());
          if (ware.getAmountType() > 0) {
            //1 返利  2 优惠券
            if (ware.getAmountType() == 1) {
              llWareItemAmount.setVisibility(View.VISIBLE);
              llWareItemTextsearch.setVisibility(View.GONE);
              tvAmountPrice.setText(ware.getAmountMoney() + "");
            } else if (ware.getAmountType() == 2) {
              llWareItemAmount.setVisibility(View.GONE);
              llWareItemTextsearch.setVisibility(View.VISIBLE);
             // tvRebatePriceTextsearch.setText(ware.getAmountMoney() + "");
              tvRebatePriceTextsearch.setText("满100减50");
            }
          }
          {
            mLook.setOnClickListener(new View.OnClickListener() {
              @Override public void onClick(View v) {
                //startActivity(
                //    new Intent(GoodsDetailActivity.this, WareActivity.class).putExtra("productId", productDetailEntity.getData().getPfromId())
                //        .putExtra("productType", 2));
              }
            });
          }
        } else if (productDetailEntity.getState() == 3) {
        } else {
        }
      }
    });
  }
  //获取相同商品
  private void getRecomdData(final int i) {
    OkHttpUtils.get().url(ApiConstants.PRODUCTSAMESEARCH)
        .addParams("pid", productId)
        .addParams("pageId",i+"")
        .build().execute(new StringCallback() {

      @Override public void onError(Call call, Exception e, int id) {
        if (isNetCollect) {
          stateLayout.showEmpty();
        } else {
          stateLayout.showError();
        }
        e.printStackTrace();
      }

      @Override public void onResponse(String response, int id) {
        final TextSearchSameEntity textSearchSameEntity =
            JSON.parseObject(response, TextSearchSameEntity.class);
        if (textSearchSameEntity.getState()==0){
          if (textSearchSameEntity.getData().getItems().size()>0){
            proList.clear();
            newlist.clear();
            proList.addAll(textSearchSameEntity.getData().getItems());
            newlist.addAll(proList);
            if (textSearchSameEntity.getData().getPageTotal()<=1) {
              mMore.setVisibility(View.GONE);
            }else {
              if (i<=textSearchSameEntity.getData().getPageTotal()) {
                mFirstAdapter.addMoreItem(newlist);
                if (i<textSearchSameEntity.getData().getPageTotal()){
                  mMore.setVisibility(View.VISIBLE);
                }else {
                  mMore.setVisibility(View.GONE);
                }
              }else {
                mMore.setVisibility(View.GONE);
                return;
              }
            }
          }else {
            mMore.setVisibility(View.GONE);
            return;
          }
        }else {
          mMore.setVisibility(View.GONE);
          return;
        }

      }
    });


  }

  //用来记录被点击的位置
  private int type = -1;

  @Override public void onClick(View v) {
    switch (v.getId()) {
      //case R.id.more:
      //  break;
      //淘宝
      case R.id.taobao:
        type = 0;
        toggle0();
        break;
      //天猫
      case R.id.tianmao:
        type = 1;
        toggle1();
        break;
      //京东
      case R.id.jd:
        type = 2;
        toggle2();
        break;
      //国美
      case R.id.guomei:
        type = 3;
        toggle3();
        break;
      //苏宁
      case R.id.SUnin:
        type = 4;
        toggle4();
        break;
      //当当
      case R.id.dd:
        type = 5;
        toggle5();
        break;
      //亚马逊
      case R.id.yamaxun:
        type = 6;
        toggle6();
        break;
      //一号店
      case R.id.yihaodian:
        type = 7;
        toggle7();
        break;
      //加载更多的点击事件
      case R.id.click_more:
        getRecomdData(++position);
        break;
    }
  }

  //设置淘宝的点击事件
  public boolean mIsToggle0;

  public void setToggleOn0(boolean isToggle) {
    mIsToggle0 = isToggle;
    if (isToggle) {
      mTaobao.setBackgroundResource(R.drawable.select_taobao);
      mTaobao.setTextColor(getResources().getColor(R.color.taobao));
    } else {
      mTaobao.setBackgroundResource(R.drawable.select_text_normal);
      mTaobao.setTextColor(getResources().getColor(R.color.normal));
    }
  }

  public boolean isToggle0() {
    return mIsToggle0;
  }

  public void toggle0() {
    setToggleOn0(!mIsToggle0);
  }

  //设置天猫的点击事件
  public boolean mIsToggle1;

  public void setToggleOn1(boolean isToggle) {
    mIsToggle1 = isToggle;
    if (isToggle) {
      mTianmao.setBackgroundResource(R.drawable.select_tianmao);
      mTianmao.setTextColor(getResources().getColor(R.color.tianmao));
    } else {
      mTianmao.setBackgroundResource(R.drawable.select_text_normal);
      mTianmao.setTextColor(getResources().getColor(R.color.normal));
    }
  }

  public boolean isToggle1() {
    return mIsToggle0;
  }

  public void toggle1() {
    setToggleOn1(!mIsToggle1);
  }

  //设置京东的点击事件
  public boolean mIsToggle2;

  public void setToggleOn2(boolean isToggle) {
    mIsToggle2 = isToggle;
    if (isToggle) {
      mJd.setBackgroundResource(R.drawable.select_jd);
      mJd.setTextColor(getResources().getColor(R.color.jd));
    } else {
      mJd.setBackgroundResource(R.drawable.select_text_normal);
      mJd.setTextColor(getResources().getColor(R.color.normal));
    }
  }

  public boolean isToggle2() {
    return mIsToggle2;
  }

  public void toggle2() {
    setToggleOn2(!mIsToggle2);
  }

  //设置国美的点击事件
  public boolean mIsToggle3;

  public void setToggleOn3(boolean isToggle) {
    mIsToggle3 = isToggle;
    if (isToggle) {
      mGuomei.setBackgroundResource(R.drawable.select_guomei);
      mGuomei.setTextColor(getResources().getColor(R.color.guomei));
    } else {
      mGuomei.setBackgroundResource(R.drawable.select_text_normal);
      mGuomei.setTextColor(getResources().getColor(R.color.normal));
    }
  }

  public boolean isToggle3() {
    return mIsToggle3;
  }

  public void toggle3() {
    setToggleOn3(!mIsToggle3);
  }

  //设置苏宁的点击事件
  public boolean mIsToggle4;

  public void setToggleOn4(boolean isToggle) {
    mIsToggle4 = isToggle;
    if (isToggle) {
      mSUnin.setBackgroundResource(R.drawable.select_suning);
      mSUnin.setTextColor(getResources().getColor(R.color.suning));
    } else {
      mSUnin.setBackgroundResource(R.drawable.select_text_normal);
      mSUnin.setTextColor(getResources().getColor(R.color.normal));
    }
  }

  public boolean isToggle4() {
    return mIsToggle4;
  }

  public void toggle4() {
    setToggleOn4(!mIsToggle4);
  }

  //设置当当的点击事件
  public boolean mIsToggle5;

  public void setToggleOn5(boolean isToggle) {
    mIsToggle5 = isToggle;
    if (isToggle) {
      mDd.setBackgroundResource(R.drawable.select_dangdang);
      mDd.setTextColor(getResources().getColor(R.color.dangdang));
    } else {
      mDd.setBackgroundResource(R.drawable.select_text_normal);
      mDd.setTextColor(getResources().getColor(R.color.normal));
    }
  }

  public boolean isToggle5() {
    return mIsToggle5;
  }

  public void toggle5() {
    setToggleOn5(!mIsToggle5);
  }

  //设置亚马逊的点击事件
  public boolean mIsToggle6;

  public void setToggleOn6(boolean isToggle) {
    mIsToggle6 = isToggle;
    if (isToggle) {
      mYamaxun.setBackgroundResource(R.drawable.select_yamaxun);
      mYamaxun.setTextColor(getResources().getColor(R.color.yamasun));
    } else {
      mYamaxun.setBackgroundResource(R.drawable.select_text_normal);
      mYamaxun.setTextColor(getResources().getColor(R.color.normal));
    }
  }

  public boolean isToggle6() {
    return mIsToggle6;
  }

  public void toggle6() {
    setToggleOn6(!mIsToggle6);
  }

  //设置一号店的点击事件
  public boolean mIsToggle7;

  public void setToggleOn7(boolean isToggle) {
    mIsToggle7 = isToggle;
    if (isToggle) {
      mYihaodian.setBackgroundResource(R.drawable.select_yihaodian);
      mYihaodian.setTextColor(getResources().getColor(R.color.yihaodian));
    } else {
      mYihaodian.setBackgroundResource(R.drawable.select_text_normal);
      mYihaodian.setTextColor(getResources().getColor(R.color.normal));
    }
  }

  public boolean isToggle7() {
    return mIsToggle7;
  }

  public void toggle7() {
    setToggleOn7(!mIsToggle7);
  }

  @Override protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    // TODO: add setContentView(...) invocation
    ButterKnife.bind(this);
  }

  @Override public void onScrollChange(NestedScrollView v, int x, int y, int oldx, int oldy) {
    if (y <= 0) {   //设置标题的背景颜色
      llGoodDetail.setBackgroundColor(Color.argb(0, 255, 255, 255));
      line.setVisibility(View.GONE);
      tvGoodDetailTitleGood.setTextColor(Color.TRANSPARENT);
    } else if (y > 0 && y <= height) { //滑动距离小于banner图的高度时，设置背景和字体颜色颜色透明度渐变
      float scale = (float) y / height;
      float alpha = (255 * scale);
      tvGoodDetailTitleGood.setTextColor(Color.argb((int) alpha, 1, 24, 28));
      llGoodDetail.setBackgroundColor(Color.argb((int) alpha, 255, 255, 255));
      line.setVisibility(View.GONE);
    } else {    //滑动到banner下面设置普通颜色
      llGoodDetail.setBackgroundColor(Color.argb(255, 255, 255, 255));
      line.setVisibility(View.VISIBLE);
    }
  }


  //获取推荐商品数据
  public void getRecommendData(int pageId) {
    Subscriber subscriber = new Subscriber<TextSearchRecomdEntity>() {
      @Override public void onCompleted() {

      }

      @Override public void onError(Throwable e) {
        e.printStackTrace();
      }

      @Override public void onNext(TextSearchRecomdEntity s) {
        System.out.println("response==="+s);
        if (s.getState()==0){
          //secondAdapter.addItem(s.getData().getItems());
        }
      }
    };
    HttpMethodLogin.getInstance().getTextSearchRecomd(subscriber, productId, pageId);
  }
}
