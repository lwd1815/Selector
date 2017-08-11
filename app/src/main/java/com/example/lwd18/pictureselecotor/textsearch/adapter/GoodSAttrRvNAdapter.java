package com.example.lwd18.pictureselecotor.textsearch.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.lwd18.pictureselecotor.R;
import com.example.lwd18.pictureselecotor.textsearch.Eventutil.Eventil;
import com.example.lwd18.pictureselecotor.textsearch.utils.FilterUtils;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;



/**
 * 创建者     李文东
 * 创建时间   2017/7/10 15:38
 * 描述	      ${TODO}
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class GoodSAttrRvNAdapter extends RecyclerView.Adapter<GoodSAttrRvNAdapter.MyAdapter> {
  private Context context;
  private List<String> mlist;
  private int mposition;
  private List<String> selectedlist;
  final StringBuffer stringBuffer = new StringBuffer();
  private List<String> sblist;
  private boolean isclick=false;
  public GoodSAttrRvNAdapter(Context context) {
    this.context = context;
    mlist = new ArrayList<>();
    selectedlist=new ArrayList<>();
    sblist = new ArrayList<>();
  }

  @Override
  public GoodSAttrRvNAdapter.MyAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
    //View view = View.inflate(parent.getContext(), R.layout.item_goods_attrs, null);
    View view = View.inflate(parent.getContext(), R.layout.gv_right_sideslip_child_layout, null);
    return new MyAdapter(view);
  }

  @Override
  public void onBindViewHolder(final GoodSAttrRvNAdapter.MyAdapter holder, final int position) {
    holder.attr.setText(mlist.get(position));
    /**
     * 根据选中状态来设置item的背景和字体颜色
     * 新思路:将选中的保存到集合中,再点击时,先和集合中的做对比,如果没有,就保存,如果有就删除(有的设置为红色,没有的设置为白色)
     */
    holder.attr.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        System.out.println("点击了1");
        if (selectedlist.size()<=0){
          selectedlist.clear();
          selectedlist.add(mlist.get(position));
          holder.attr.setBackgroundResource(R.drawable.goods_attr_selected_shape);
          holder.attr.setTextColor(Color.WHITE);
          EventBus.getDefault().post(new Eventil(mlist.get(position)));
          System.out.println("item第一次点击,走到此处");
        }else {
          if (!selectedlist.contains(mlist.get(position))){
            selectedlist.add(mlist.get(position));
            holder.attr.setBackgroundResource(R.drawable.goods_attr_selected_shape);
            holder.attr.setTextColor(Color.WHITE);
            EventBus.getDefault().post(new Eventil(mlist.get(position)));
            System.out.println("item被点击选中,走到了此处");
          }else {
            selectedlist.remove(mlist.get(position));
            holder.attr.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
            holder.attr.setTextColor(Color.GRAY);
            System.out.println("item被点击取消,走到了此处");
          }
        }
      }
    });
  }

  //去除重复数据
  public List<String> removeDuplicate(List<String> list) {
    HashSet h = new HashSet(list);
    list.clear();
    list.addAll(h);
    return list;
  }

  @Override public int getItemCount() {
    System.out.println("mlist========" + mlist.size());
    return mlist == null ? 0 : mlist.size();
  }

  public class MyAdapter extends RecyclerView.ViewHolder {
    public TextView attr;

    public MyAdapter(View itemView) {
      super(itemView);
      attr = (TextView) itemView.findViewById(R.id.attr_name);
    }
  }

  public void notifyDataSetChanged(boolean isUnfold, final List<String> tempData, int position) {
    if (tempData == null || 0 == tempData.size()) {
      return;
    }
    // 如果是展开的，则加入全部data，反之则只显示3条
    if (isUnfold) {
      mlist.clear();
      mlist.addAll(tempData);
    } else {
      mlist.clear();
      FilterUtils.setNumberFilter(tempData, mlist);
    }
    notifyDataSetChanged();
  }

  public void notifyDataSetChangeds(boolean isUnfold, final List<String> tempData, int position) {
    if (tempData == null || 0 == tempData.size()) {
      return;
    }
    // 如果是展开的，则加入全部data，反之删除
    if (isUnfold) {
      mlist.clear();
      mlist.addAll(tempData);
    } else {
      //不展示
      for (int i = 0; i < mlist.size(); i++) {
        int currentPosition = mposition + i;
        mlist.remove(tempData);
        notifyItemRemoved(currentPosition);
      }
      notifyDataSetChanged();
    }
  }

  //添加数据
  public void addItem(List<String> newDatas) {
    mlist.clear();
    newDatas.addAll(mlist);
    mlist.removeAll(mlist);
    mlist.addAll(newDatas);
    notifyDataSetChanged();
  }

  //设置保存图片到本地的开关按钮的点击事件所调用的方法
  public boolean mIsToggle;

  public void setToggleOn(boolean isToggle, GoodSAttrRvNAdapter.MyAdapter holder, int position) {
    mIsToggle = isToggle;
    if (isToggle) {
      holder.attr.setBackgroundResource(R.drawable.goods_attr_selected_shape);
      holder.attr.setTextColor(Color.WHITE);
    } else {
      holder.attr.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
      holder.attr.setTextColor(Color.GRAY);
    }
  }

  public boolean isToggle() {
    return mIsToggle;
  }

  public void toggle(GoodSAttrRvNAdapter.MyAdapter holder, int position) {
    setToggleOn(!mIsToggle, holder, position);
  }
}
