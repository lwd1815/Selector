package com.example.lwd18.pictureselecotor.textsearch.itemadapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.lwd18.pictureselecotor.R;
import com.example.lwd18.pictureselecotor.textsearch.DataPresenter;
import com.example.lwd18.pictureselecotor.textsearch.Eventutil.Eventil;
import com.example.lwd18.pictureselecotor.textsearch.Eventutil.FilterUtils;
import com.example.lwd18.pictureselecotor.textsearch.Eventutil.SecondEventil;
import com.example.lwd18.pictureselecotor.textsearch.Eventutil.TranstEventil;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.List;



/**
 * 创建者     李文东
 * 创建时间   2017/7/10 15:38
 * 描述
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述
 */

public class GoodSAttrRvNAdapter12 extends RecyclerView.Adapter<GoodSAttrRvNAdapter12.MyAdapter> {
  private Context context;
  private List<String> mlist;
  private List<String> selectedlist;
  public GoodSAttrRvNAdapter12(Context context) {
    this.context = context;
    mlist=new ArrayList<>();
    selectedlist = DataPresenter.getSingleTon().getSelectList();
    EventBus.getDefault().register(this);
  }

  @Override
  public GoodSAttrRvNAdapter12.MyAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = View.inflate(parent.getContext(), R.layout.item_goods_attrs, null);
    return new MyAdapter(view);
  }

  @Override public void onBindViewHolder(final GoodSAttrRvNAdapter12.MyAdapter holder, final int position) {
    holder.attr.setText(mlist.get(position));
    //强制禁止recycleview复用
    holder.setIsRecyclable(false);
    //用来接收综合界面传递过来的选择
    for (int i = 0; i < selectedlist.size(); i++) {
      if (selectedlist.contains(mlist.get(position))) {
        holder.attr.setBackgroundResource(R.drawable.goods_attr_selected_shape);
        holder.attr.setTextColor(Color.WHITE);
      }
    }
    /**
     * 根据选中状态来设置item的背景和字体颜色
     */
    holder.attr.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (selectedlist.size() <= 0) {
          holder.attr.setBackgroundResource(R.drawable.goods_attr_selected_shape);
          holder.attr.setTextColor(Color.WHITE);
          EventBus.getDefault().post(new Eventil(mlist.get(position)));

        } else {
          if (!selectedlist.contains(mlist.get(position))) {
            holder.attr.setBackgroundResource(R.drawable.goods_attr_selected_shape);
            holder.attr.setTextColor(Color.WHITE);
            EventBus.getDefault().post(new Eventil(mlist.get(position)));
          } else {
            holder.attr.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
            holder.attr.setTextColor(Color.BLACK);
          }
        }
        //保存
        DataPresenter.getSingleTon().saveSelect(mlist.get(position));
        EventBus.getDefault().post(new SecondEventil());
      }
    });
  }

  @Override public int getItemCount() {
    return mlist==null?0: mlist.size();
  }

  public class MyAdapter extends RecyclerView.ViewHolder {
    public TextView attr;

    public MyAdapter(View itemView) {
      super(itemView);
      attr = (TextView) itemView.findViewById(R.id.attr_name);
    }
  }
  public void notifyDataSetChanged(boolean isUnfold, final List<String> tempData,int position) {
    if (tempData == null || 0 == tempData.size()) {
      return;
    }
    // 如果是展开的，则加入全部data，反之则只显示3条
    mlist.clear();
    if (isUnfold) {
      mlist.addAll(tempData);
    } else {
      FilterUtils.setNumberFilter(tempData,mlist);
    }
   notifyDataSetChanged();
  }
  public void notifyDataSetChangeds(boolean isUnfold, final List<String> tempData,int position) {
    if (tempData == null || 0 == tempData.size()) {
      return;
    }
    // 如果是展开的，则加入全部data，反之删除
    if (isUnfold) {
      mlist.clear();
      mlist.addAll(tempData);
    } else {
      //不展示
     mlist.clear();
    }
    notifyDataSetChanged();
  }

  //添加数据
  public void addItem(List<String> newDatas) {
    mlist.clear();
    newDatas.addAll(mlist);
    mlist.removeAll(mlist);
    mlist.addAll(newDatas);
    notifyDataSetChanged();
  }
  /**
   * 用来接收筛选框中的消息
   */
  public void onEventMainThread(TranstEventil event) {
    notifyDataSetChanged();
  }
}
