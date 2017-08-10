package com.example.lwd18.pictureselecotor.textsearch.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.lwd18.pictureselecotor.R;
import com.example.lwd18.pictureselecotor.textsearch.utils.TextEventUtil;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建者     李文东
 * 创建时间   2017/7/12 16:53
 * 描述	      文字搜索综合界面筛选项的adapter
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class FilterHeadAdapter extends RecyclerView.Adapter<FilterHeadAdapter.MyAdapter> {

  private List<String> list;

  public FilterHeadAdapter(List<String> mlist) {
    list = new ArrayList<>();
    list.clear();
    list.addAll(mlist);
  }

  @Override public MyAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = View.inflate(parent.getContext(), R.layout.filter_head, null);
    return new MyAdapter(view);
  }

  @Override public void onBindViewHolder(final MyAdapter holder, final int position) {
    if (position == 4) {
      holder.button.setText("更多");
      Drawable drawable = holder.itemView.getContext().getResources().getDrawable(R.mipmap.cat_more_11);
      drawable.setBounds(0,0,drawable.getMinimumWidth(),drawable.getMinimumHeight());
      holder.button.setCompoundDrawables(null,null,drawable,null);
      holder.button.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          toggle(holder,  position);

          EventBus.getDefault().post(new TextEventUtil(""));
        }
      });
    } else {
      holder.button.setText(list.get(position));
      holder.button.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          toggle(holder, position);

          EventBus.getDefault().post(new TextEventUtil(list.get(position)));
        }
      });
    }
  }

  @Override public int getItemCount() {
    return list.size() > 5 ? 5 : list.size();
  }

  public class MyAdapter extends RecyclerView.ViewHolder {
    Button button;

    public MyAdapter(View itemView) {
      super(itemView);
      button = (Button) itemView.findViewById(R.id.first_button);
    }
  }

  //public void addItem(List<TextsSearchEntity.DataBean.ItemsBean> newDatas) {
  //  list.clear();
  //  newDatas.addAll(list);
  //  list.removeAll(list);
  //  list.addAll(newDatas);
  //  notifyDataSetChanged();
  //}

  //设置保存图片到本地的开关按钮的点击事件所调用的方法
  public boolean mIsToggle;
  public void setToggleOn(boolean isToggle,MyAdapter holder,  int position){
    mIsToggle=isToggle;
    if (isToggle){
      holder.button.setBackgroundResource(R.drawable.goods_attr_selected_shape);
      holder.button.setTextColor(Color.WHITE);
    }else{
      holder.button.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
      holder.button.setTextColor(Color.GRAY);
    }
  }

  public boolean isToggle(){
    return mIsToggle;
  }

  public void toggle(MyAdapter holder,  int position) {
    setToggleOn(!mIsToggle,holder,position);
  }
}
