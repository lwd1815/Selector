package com.example.lwd18.pictureselecotor.textsearch.adapter;

import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.example.lwd18.pictureselecotor.R;
import com.example.lwd18.pictureselecotor.textsearch.DataPresenter;
import com.example.lwd18.pictureselecotor.textsearch.Eventutil.TextEventUtil;
import de.greenrobot.event.EventBus;
import java.util.ArrayList;
import java.util.List;



/**
 * 创建者     李文东
 * 创建时间   2017/7/12 16:53
 * 描述	      文字搜索综合界面筛选项的adapter
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述
 */

public class FilterHeadAdapter extends RecyclerView.Adapter<FilterHeadAdapter.MyAdapter> {

  private List<String> list;
  private final List<String> selectList;

  public FilterHeadAdapter(List<String> mlist) {
    list = new ArrayList<>();
    list.clear();
    list.addAll(mlist);
    selectList = DataPresenter.getSingleTon().getSelectList();
  }

  @Override public MyAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = View.inflate(parent.getContext(), R.layout.filter_head, null);
    return new MyAdapter(view);
  }

  @Override public void onBindViewHolder(final MyAdapter holder, final int position) {
    for (int i = 0; i < selectList.size(); i++) {
      System.out.println("被点击的数据有==="+selectList.size()+"====分别为===="+selectList.get(i));
      if (selectList.contains(list.get(position))&&position<4) {
        holder.button.setBackgroundResource(R.drawable.goods_attr_selected_shape);
        holder.button.setTextColor(Color.WHITE);
      }
    }

    if (position == 4) {
      holder.button.setText("更多");
      holder.button.setPadding(0, 0, 10, 0);
      Drawable drawable =
          holder.itemView.getContext().getResources().getDrawable(R.mipmap.cat_more_11);
      drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
      holder.button.setCompoundDrawables(null, null, drawable, null);
      holder.button.setCompoundDrawablePadding(-30);
      holder.button.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          EventBus.getDefault().post(new TextEventUtil(""));
        }
      });
    } else {
      holder.button.setText(list.get(position));
      holder.button.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          if (selectList.size() <= 0) {
            holder.button.setBackgroundResource(R.drawable.goods_attr_selected_shape);
            holder.button.setTextColor(Color.WHITE);
            EventBus.getDefault().post(new TextEventUtil(list.get(position)));
          } else {
            if (!selectList.contains(list.get(position))) {
              holder.button.setBackgroundResource(R.drawable.goods_attr_selected_shape);
              holder.button.setTextColor(Color.WHITE);
              EventBus.getDefault().post(new TextEventUtil(list.get(position)));

            } else {
              holder.button.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
              holder.button.setTextColor(Color.BLACK);
            }
          }
          //保存
          DataPresenter.getSingleTon().saveSelect(list.get(position));
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
}
