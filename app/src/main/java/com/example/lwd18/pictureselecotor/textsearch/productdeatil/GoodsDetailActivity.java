package com.example.lwd18.pictureselecotor.textsearch.productdeatil;

import android.content.Intent;
import android.net.Uri;
import android.support.design.widget.AppBarLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
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
import com.example.lwd18.pictureselecotor.ApiConstants;
import com.example.lwd18.pictureselecotor.BaseDeepActivity;
import com.example.lwd18.pictureselecotor.ProductDetailEntity;
import com.example.lwd18.pictureselecotor.R;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;



public class GoodsDetailActivity extends BaseDeepActivity implements View.OnClickListener{

  @BindView(R.id.top_news_viewpager_ll) LinearLayout mTopNewsViewpagerLl;
  @BindView(R.id.dots_ll) LinearLayout mDotsLl;
  @BindView(R.id.appbar) AppBarLayout mAppbar;
  @BindView(R.id.comment) TextView mComment;
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
  @BindView(R.id.detail_back) ImageView mDetailBack;
  @BindView(R.id.more) TextView mMore;
  @BindView(R.id.webview) WebView mWebview;
  @BindView(R.id.scrollview_first) ScrollView scrollviewFirst;
  @BindView(R.id.tv_net_state_wifi) TextView tvNetStateWifi;
  @BindView(R.id.rl_net_states) RelativeLayout rlNetStates;
  //存放指示点的集合
  private List<ImageView> pointList = new ArrayList<>();
  //上一个指示点
  private int lastPosition = 0;
  private RollViewPagers rollViewPager;
  private List<String> list;
  private List<ProductDetailEntity.DataBean.SameProductsBean> proList;
  private List<ProductDetailEntity.DataBean.SameProductsBean> newlist;
  private FirstAdapter mFirstAdapter;
  private String productId;
  boolean isNetCollect = true;
  private int position=1;
  private int price;
  private String lv;
  @Override protected void initActivity() {
    setContentView(R.layout.activity_goods_detail);
    ButterKnife.bind(this);
    Intent intent = getIntent();
    productId = intent.getStringExtra("productId");
    price = intent.getIntExtra("price", 0);
    lv = intent.getStringExtra("lv");
    initAppBar();
    getData(1);
    proList=new ArrayList<>();
    newlist=new ArrayList<>();
    initFirstRv();
    initSecondRv();
    mCommentSimplePrice.setText("¥"+price);
    mCommentSimplePrices.setText("¥"+lv);
    initClick();
  }

  private void initAppBar() {
    list = new ArrayList<>();
    mDetailBack.setOnClickListener(new View.OnClickListener() {
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
    SecondAdapter secondAdapter = new SecondAdapter();
    mRvGoodsCommend.setLayoutManager(line);
    mRvGoodsCommend.setAdapter(secondAdapter);
  }

  private void initview() {
    lastPosition = 0;
    //第一步：添加轮播图（也可以直接将布局写成咱们自定义的viewpager）
    rollViewPager =
        new RollViewPagers(GoodsDetailActivity.this);
    mTopNewsViewpagerLl.addView(rollViewPager);
    //第二步：传递轮播图需要的图片url集合或者数组
    //List<SpecialDetailEntity> list = entity.getItems();
    rollViewPager.setImageUrlsList(list);

    //第三步：添加指示点
    addPoints();



    //-------------------------
    //        Picasso.with(MainActivity.this).load(urls[0]).into(iv1);
    //        Picasso.with(MainActivity.this).load(urls[1]).transform(new CircleTransform()).into(iv2);
    //        Picasso.with(MainActivity.this).load(urls[2]).transform(new RangleTransform(30)).into(iv3);
    //        Picasso.with(MainActivity.this).load(urls[4]).transform(new RangleTransform(100)).into(iv4);

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
  private void onclickvp() {
    rollViewPager.setOnItemClickListener(new RollViewPagers.OnItemClickListener() {
      @Override
      public void onItemClick(int position) {
        //进入photoview
        // ImageBrowseActivity.startActivity(getBaseContext(), (ArrayList<String>) list,position);
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

  private void getData(int index) {
    OkHttpUtils.get()
        .url(ApiConstants.PRODUCTSEARCH)
        //.addParams("pageId", index + "")
        .addParams("pid", productId)
        //.addParams("params","德国#全脂#进口")
        .build()
        .execute(new StringCallback() {

          @Override public void onError(Call call, Exception e, int id) {

          }

          @Override public void onResponse(String response, int id) {
            Log.w("刚获取数据时", response);
            final ProductDetailEntity
                productDetailEntity = JSON.parseObject(response, ProductDetailEntity.class);
            System.out.println("TextSearchResponse=====" + response);
            //记录总页数
            if (productDetailEntity.getState() == 0) {
              //stateLayout.showContent();
              rlNetStates.setVisibility(View.GONE);
              list.clear();
              proList.clear();
              list.addAll(productDetailEntity.getData().getPicUrl());
              initview();
              initlistener();
              onclickvp();
              mComment.setText(productDetailEntity.getData().getTitle());
              mLook.setOnClickListener(new View.OnClickListener() {
                @Override public void onClick(View v) {
                  Uri uri = Uri.parse(productDetailEntity.getData().getDetailUrl());
                  Intent it = new Intent(Intent.ACTION_VIEW,uri);
                  startActivity(it);
                }
              });
              proList.addAll(productDetailEntity.getData().getSameProducts());
              newlist.addAll(proList);
              if (position == 1) {
                mFirstAdapter.addItem(newlist);
              } else {
                mFirstAdapter.addMoreItem(newlist);
              }
            } else if (productDetailEntity.getState() == 3) {
              //stateLayout.showEmpty();
            } else {
              //stateLayout.showError();
            }
          }
        });
  }
  //用来记录被点击的位置
  private int type=-1;
  @Override public void onClick(View v) {
    switch (v.getId()){
      case R.id.more:
        break;
      //淘宝
      case R.id.taobao:
        type=0;
        toggle0();
        break;
      //天猫
      case R.id.tianmao:
        type=1;
        toggle1();
        break;
      //京东
      case R.id.jd:
        type=2;
        toggle2();
        break;
      //国美
      case R.id.guomei:
        type=3;
        toggle3();
        break;
      //苏宁
      case R.id.SUnin:
        type=4;
        toggle4();
        break;
      //当当
      case R.id.dd:
        type=5;
        toggle5();
        break;
      //亚马逊
      case R.id.yamaxun:
        type=6;
        toggle6();
        break;
      //一号店
      case R.id.yihaodian:
        type=7;
        toggle7();
        break;
    }
  }

  //设置淘宝的点击事件
  public boolean mIsToggle0;
  public void setToggleOn0(boolean isToggle){
    mIsToggle0=isToggle;
    if (isToggle){
      mTaobao.setBackgroundResource(R.drawable.select_taobao);
      mTaobao.setTextColor(getResources().getColor(R.color.taobao));
    }else{
      mTaobao.setBackgroundResource(R.drawable.select_text_normal);
      mTaobao.setTextColor(getResources().getColor(R.color.normal));
    }
  }

  public boolean isToggle0(){
    return mIsToggle0;
  }

  public void toggle0() {
    setToggleOn0(!mIsToggle0);
  }
  //设置天猫的点击事件
  public boolean mIsToggle1;
  public void setToggleOn1(boolean isToggle){
    mIsToggle1=isToggle;
    if (isToggle){
      mTianmao.setBackgroundResource(R.drawable.select_tianmao);
      mTianmao.setTextColor(getResources().getColor(R.color.tianmao));
    }else{
      mTianmao.setBackgroundResource(R.drawable.select_text_normal);
      mTianmao.setTextColor(getResources().getColor(R.color.normal));
    }
  }

  public boolean isToggle1(){
    return mIsToggle0;
  }

  public void toggle1() {
    setToggleOn1(!mIsToggle1);
  }
  //设置京东的点击事件
  public boolean mIsToggle2;
  public void setToggleOn2(boolean isToggle){
    mIsToggle2=isToggle;
    if (isToggle){
      mJd.setBackgroundResource(R.drawable.select_jd);
      mJd.setTextColor(getResources().getColor(R.color.jd));
    }else{
      mJd.setBackgroundResource(R.drawable.select_text_normal);
      mJd.setTextColor(getResources().getColor(R.color.normal));
    }
  }

  public boolean isToggle2(){
    return mIsToggle2;
  }

  public void toggle2() {
    setToggleOn2(!mIsToggle2);
  }
  //设置国美的点击事件
  public boolean mIsToggle3;
  public void setToggleOn3(boolean isToggle){
    mIsToggle3=isToggle;
    if (isToggle){
      mGuomei.setBackgroundResource(R.drawable.select_guomei);
      mGuomei.setTextColor(getResources().getColor(R.color.guomei));
    }else{
      mGuomei.setBackgroundResource(R.drawable.select_text_normal);
      mGuomei.setTextColor(getResources().getColor(R.color.normal));
    }
  }

  public boolean isToggle3(){
    return mIsToggle3;
  }

  public void toggle3() {
    setToggleOn3(!mIsToggle3);
  }
  //设置苏宁的点击事件
  public boolean mIsToggle4;
  public void setToggleOn4(boolean isToggle){
    mIsToggle4=isToggle;
    if (isToggle){
      mSUnin.setBackgroundResource(R.drawable.select_suning);
      mSUnin.setTextColor(getResources().getColor(R.color.suning));
    }else{
      mSUnin.setBackgroundResource(R.drawable.select_text_normal);
      mSUnin.setTextColor(getResources().getColor(R.color.normal));
    }
  }

  public boolean isToggle4(){
    return mIsToggle4;
  }

  public void toggle4() {
    setToggleOn4(!mIsToggle4);
  }
  //设置当当的点击事件
  public boolean mIsToggle5;
  public void setToggleOn5(boolean isToggle){
    mIsToggle5=isToggle;
    if (isToggle){
      mDd.setBackgroundResource(R.drawable.select_dangdang);
      mDd.setTextColor(getResources().getColor(R.color.dangdang));
    }else{
      mDd.setBackgroundResource(R.drawable.select_text_normal);
      mDd.setTextColor(getResources().getColor(R.color.normal));
    }
  }

  public boolean isToggle5(){
    return mIsToggle5;
  }

  public void toggle5() {
    setToggleOn5(!mIsToggle5);
  }
  //设置亚马逊的点击事件
  public boolean mIsToggle6;
  public void setToggleOn6(boolean isToggle){
    mIsToggle6=isToggle;
    if (isToggle){
      mYamaxun.setBackgroundResource(R.drawable.select_yamaxun);
      mYamaxun.setTextColor(getResources().getColor(R.color.yamasun));
    }else{
      mYamaxun.setBackgroundResource(R.drawable.select_text_normal);
      mYamaxun.setTextColor(getResources().getColor(R.color.normal));
    }
  }

  public boolean isToggle6(){
    return mIsToggle6;
  }

  public void toggle6() {
    setToggleOn6(!mIsToggle6);
  }
  //设置一号店的点击事件
  public boolean mIsToggle7;
  public void setToggleOn7(boolean isToggle){
    mIsToggle7=isToggle;
    if (isToggle){
      mYihaodian.setBackgroundResource(R.drawable.select_yihaodian);
      mYihaodian.setTextColor(getResources().getColor(R.color.yihaodian));
    }else{
      mYihaodian.setBackgroundResource(R.drawable.select_text_normal);
      mYihaodian.setTextColor(getResources().getColor(R.color.normal));
    }
  }

  public boolean isToggle7(){
    return mIsToggle7;
  }

  public void toggle7() {
    setToggleOn7(!mIsToggle7);
  }
}
