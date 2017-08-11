package com.example.lwd18.pictureselecotor.textsearch.adapter;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.lwd18.pictureselecotor.R;
import com.example.lwd18.pictureselecotor.TextsSearchEntity;
import com.example.lwd18.pictureselecotor.textsearch.productdeatil.GoodsDetailActivity;
import com.example.lwd18.pictureselecotor.textsearch.utils.FilterUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建者     李文东
 * 创建时间   2017/6/19 11:29
 * 描述	      ${TODO}
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class ComprehensiveAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private int HEAD = 1;
  private int NORMAL = 2;
  private List<TextsSearchEntity.DataBean.ItemsBean> list;
  private List<TextsSearchEntity.DataBean.FiltersBean> filterlist;
  private List<String> filters;

  public ComprehensiveAdapter() {
    list = new ArrayList<>();
    filterlist = new ArrayList<>();
    filters = new ArrayList<>();
  }

  @Override public int getItemViewType(int position) {
    if (filterlist.size() == 0) {
      return NORMAL;
    } else {
      int positions = (filterlist.size() - 1) * 6;
      if (position == 0) {
        return HEAD;
      } else {
        if (position > positions) {
          return NORMAL;
        } else {
          if (position % 6 == 0) {
            return HEAD;
          } else {
            return NORMAL;
          }
        }
      }
    }
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    if (viewType == HEAD) {
      View view = View.inflate(parent.getContext(), R.layout.item_product_head, null);
      return new HeadViewHolder(view);
    } else if (viewType == NORMAL) {
      View view = View.inflate(parent.getContext(), R.layout.item_product_normal, null);
      return new NormalViewHolder(view);
    }
    return null;
  }

  @Override public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
    int positions = (filterlist.size() - 1) * 6;

    if (holder instanceof NormalViewHolder) {
     //如果filst为空,不会出现越界异常情况
     final int index = FilterUtils.getIndex(filterlist,position);
     ((NormalViewHolder) holder).titleProduct.setText(list.get(index).getTitle());
     Glide.with(holder.itemView.getContext())
         .load("http:" + list.get(index).getPicUrl())
         .into(((NormalViewHolder) holder).iconProduct);
     ((NormalViewHolder) holder).price.setText("¥" + list.get(index).getPrice());
     ((NormalViewHolder) holder).priceAllNet.setText(
         "全网销量" + list.get(index).getViewSales() + "万");
     ((NormalViewHolder) holder).priceTaobao.setText("¥" + list.get(index).getFromat());
     ((NormalViewHolder) holder).priceMecheat.setText(
         list.get(index).getShopCount() + "商家比价");

      ((NormalViewHolder) holder).linearLayoutout.setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View v) {
          Intent intent = new Intent(holder.itemView.getContext(), GoodsDetailActivity.class);
          intent.putExtra("productId",list.get(index).getId());
          intent.putExtra("price",list.get(position).getPrice());
          intent.putExtra("lv",list.get(index).getFromat());
          holder.itemView.getContext().startActivity(intent);
        }
      });
    } else if (holder instanceof HeadViewHolder) {
      int newpositions = position / 6;
      filters.clear();
      filters.addAll(filterlist.get(newpositions).getFilterOption());
      LinearLayoutManager
          line = new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL,false);
      ((HeadViewHolder) holder).rv.setLayoutManager(line);
      FilterHeadAdapter adapter = new FilterHeadAdapter(filters);
      ((HeadViewHolder) holder).rv.setAdapter(adapter);

    }

  }

  @Override public int getItemCount() {
    int positions = (filterlist.size() - 1) * 6;

    System.out.println("adapter中的数据===="+filterlist.size()+"list的数据=="+list.size());
    if (filterlist.size() == 0) {
      return list.size();
    } else if (list.size()<positions){
      return FilterUtils.getSize(list);
    }else {
      return filterlist.size() + list.size();
    }
  }

  public class NormalViewHolder extends RecyclerView.ViewHolder {
    ImageView iconProduct;
    TextView titleProduct;
    TextView priceTvMin;
    TextView price;
    TextView priceAllNet;
    ImageView priceIv;
    TextView priceTaobao;
    TextView priceMecheat;
    LinearLayout linearLayoutout;
    public NormalViewHolder(View itemView) {
      super(itemView);
      iconProduct = (ImageView) itemView.findViewById(R.id.icon_product);
      titleProduct = (TextView) itemView.findViewById(R.id.title_product);
      priceTvMin = (TextView) itemView.findViewById(R.id.price_tv_min);
      price = (TextView) itemView.findViewById(R.id.price);
      priceAllNet = (TextView) itemView.findViewById(R.id.price_all_net);
      priceIv = (ImageView) itemView.findViewById(R.id.price_iv);
      priceTaobao = (TextView) itemView.findViewById(R.id.price_taobao);
      priceMecheat = (TextView) itemView.findViewById(R.id.price_mecheat);
      linearLayoutout = (LinearLayout) itemView.findViewById(R.id.com_out_ll);
    }
  }

  public class HeadViewHolder extends RecyclerView.ViewHolder {

    public RecyclerView rv;
    public HeadViewHolder(View itemView) {
      super(itemView);

      rv= (RecyclerView) itemView.findViewById(R.id.filter_rv);
    }
  }

  //添加数据
  public void addItem(List<TextsSearchEntity.DataBean.ItemsBean> newDatas) {
    list.clear();
    newDatas.addAll(list);
    list.removeAll(list);
    list.addAll(newDatas);
    notifyDataSetChanged();
  }

  public void addMoreItem(List<TextsSearchEntity.DataBean.ItemsBean> newDatas) {
    list.addAll(newDatas);
    notifyDataSetChanged();
  }

  public void addFilterItem(List<TextsSearchEntity.DataBean.FiltersBean> mlists) {
   filterlist.clear();
   mlists.addAll(filterlist);
   filterlist.removeAll(filterlist);
   filterlist.addAll(mlists);
    notifyDataSetChanged();
  }
}
