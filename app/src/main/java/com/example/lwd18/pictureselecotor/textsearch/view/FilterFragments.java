package com.example.lwd18.pictureselecotor.textsearch.view;

import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;
import com.alibaba.fastjson.JSON;
import com.example.lwd18.pictureselecotor.ApiConstants;
import com.example.lwd18.pictureselecotor.R;
import com.example.lwd18.pictureselecotor.TextsSearchEntity;
import com.example.lwd18.pictureselecotor.textsearch.Eventutil.Eventil;
import com.example.lwd18.pictureselecotor.textsearch.Eventutil.FinishEventUtil;
import com.example.lwd18.pictureselecotor.textsearch.Eventutil.SecondEventil;
import com.example.lwd18.pictureselecotor.textsearch.adapter.GoodSAttrRvAdatper;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.List;
import okhttp3.Call;

/**
 * 筛选商品属性选择的popupwindow
 */
public class FilterFragments extends FrameLayout {
  private View contentView;
  private RecyclerView selectionList;
  private TextView filterReset;
  private TextView filterSure;
  private GoodSAttrRvAdatper adapter;
  private LinearLayoutManager linearLayoutManager;
  private List<String> filtelist;
  List<String> newlist;
  private List<TextsSearchEntity.DataBean.FiltersBean> mlist = new ArrayList<>();
  private List<TextsSearchEntity.DataBean.FiltersBean> newlists = new ArrayList<>();
  //关键词
  String keyword;

  /**
   * 商品属性选择的popupwindow
   */
  public FilterFragments(Context context, String edditx) {
    super(context);
    EventBus.getDefault().registerSticky(this);
    keyword = edditx;
    init(context);
  }

  public FilterFragments(@NonNull Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    EventBus.getDefault().registerSticky(this);
    init(context);
  }

  public FilterFragments(@NonNull Context context, @Nullable AttributeSet attrs,
      @AttrRes int defStyleAttr) {
    super(context, attrs, defStyleAttr);
    EventBus.getDefault().registerSticky(this);
    init(context);
  }

  private void init(final Context context) {
    LayoutInflater inflater =
        (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    contentView = inflater.inflate(R.layout.popup_goods_details, null);
    selectionList = (RecyclerView) contentView.findViewById(R.id.selection_list);
    filterReset = (TextView) contentView.findViewById(R.id.filter_reset);
    filterSure = (TextView) contentView.findViewById(R.id.filter_sure);

    contentView.setOnKeyListener(new OnKeyListener() {
      @Override public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (event.getAction() == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_BACK) {
        }
        return true;
      }
    });
    //初始化筛选框的list
    filtelist = new ArrayList<>();
    newlist = new ArrayList<>();
    getData(keyword);
    linearLayoutManager =
        new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false);
    selectionList.setLayoutManager(linearLayoutManager);
    adapter = new GoodSAttrRvAdatper(context);
    selectionList.setAdapter(adapter);

    // 重置的点击监听，将所有选项全设为false
    filterReset.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        adapter = new GoodSAttrRvAdatper(context);
        selectionList.setAdapter(adapter);
        //通知综合界面刷新
        EventBus.getDefault().post(new SecondEventil(filtelist));
      }
    });
    // 确定的点击监听，将所有已选中项列出
    filterSure.setOnClickListener(new OnClickListener() {
      @Override public void onClick(View v) {
        //将筛选项发送过去
        EventBus.getDefault().post(new SecondEventil(filtelist));
        EventBus.getDefault().post(new FinishEventUtil());
        filtelist.clear();
      }
    });
    this.addView(contentView);
    ColorDrawable dw = new ColorDrawable(00000000);
    this.setBackgroundDrawable(dw);
    this.setFocusable(true);
  }

  /**
   * 用来接收筛选框中的消息
   */
  public void onEventMainThread(Eventil event) {
    String filter = event.getProviceName();
    System.out.println("接收到筛选框传递到fragment中的消息了==" + filter);
    if (filtelist.size() <= 0) {
      filtelist.clear();
      filtelist.add(filter);
    } else {
      if (!filtelist.contains(filter)) {
        filtelist.add(filter);
      } else {
        filtelist.remove(filter);
      }
    }
  }

  private void getData(String keyword) {
    OkHttpUtils.get()
        .url(ApiConstants.TEXTSEARCHS)
        .addParams("text", keyword)
        .addParams("pageId", 1 + "")
        .addParams("sortId", 0 + "")
        .addParams("params", "")
        .build()
        .execute(new StringCallback() {
          @Override public void onError(Call call, Exception e, int id) {

          }
          @Override public void onResponse(String response, int id) {
            TextsSearchEntity textSearch = JSON.parseObject(response, TextsSearchEntity.class);
            if (textSearch.getData() != null && textSearch.getData().getFilters() != null) {
              mlist.clear();
              newlists.clear();
              mlist.addAll(textSearch.getData().getFilters());
              newlists.addAll(mlist);
              adapter.addItem(mlist);
            }
          }
        });
  }
}
