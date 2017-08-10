package com.example.lwd18.pictureselecotor.textsearch.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.lwd18.pictureselecotor.R;
import com.example.lwd18.pictureselecotor.textsearch.vo.SaleAttributeVo;
import java.util.ArrayList;
import java.util.List;


/**
 * 创建者     李文东
 * 创建时间   2017/7/10 15:38
 * 描述	      ${TODO}
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class GoodSAttrRvFAdapter extends RecyclerView.Adapter<GoodSAttrRvFAdapter.MyAdapter> {
  private Context context;
  private List<SaleAttributeVo> data = new ArrayList<>();

  public GoodSAttrRvFAdapter(Context context) {
    this.context = context;
  }

  @Override
  public GoodSAttrRvFAdapter.MyAdapter onCreateViewHolder(ViewGroup parent, int viewType) {
    //View view = View.inflate(parent.getContext(), R.layout.item_goods_attrs, null);R.layout.gv_right_sideslip_child_layout
    View view = View.inflate(parent.getContext(), R.layout.gv_right_sideslip_child_layout, null);
    return new MyAdapter(view);
  }

  @Override public void onBindViewHolder(GoodSAttrRvFAdapter.MyAdapter holder, final int position) {
    holder.attr.setText(data.get(position).getValue());
    /**
     * 根据选中状态来设置item的背景和字体颜色
     */
    if (data.get(position).isChecked()) {
      holder.attr.setBackgroundResource(R.drawable.goods_attr_selected_shape);
      holder.attr.setTextColor(Color.WHITE);
    } else {
      holder.attr.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
      holder.attr.setTextColor(Color.GRAY);
    }
    holder.attr.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        if (onClickListener!=null){
          onClickListener.itemClick(position);
        }
      }
    });
  }

  @Override public int getItemCount() {
    return data == null ? 0 : data.size();
  }

  public class MyAdapter extends RecyclerView.ViewHolder {
    public TextView attr;

    public MyAdapter(View itemView) {
      super(itemView);
      attr = (TextView) itemView.findViewById(R.id.item_frameRb);
    }
  }

  public void notifyDataSetChanged(boolean isUnfold, final List<SaleAttributeVo> tempData) {
    if (tempData == null || 0 == tempData.size()) {
      return;
    }
    data.clear();
    // 如果是展开的，则加入全部data，反之则只显示3条
    if (isUnfold) {
      data.addAll(tempData);
    } else {
      data.add(tempData.get(0));
      data.add(tempData.get(1));
      data.add(tempData.get(2));
    }
    notifyDataSetChanged();
  }

  private OnClickListener onClickListener;
  public void setOnClickListeners(OnClickListener clickListener){
    this.onClickListener=clickListener;
  }
  public interface OnClickListener {
    void itemClick(int position);
  }
}
