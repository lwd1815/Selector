package com.example.lwd18.pictureselecotor.textsearch.resultadapter;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.lwd18.pictureselecotor.R;
import com.example.lwd18.pictureselecotor.TextsSearchEntity;
import com.example.lwd18.pictureselecotor.textsearch.Eventutil.FilterUtils;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建者     李文东
 * 创建时间   2017/6/19 11:29
 * 描述
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述
 */

public class FilterAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private int HEAD = 1;
  private int NORMAL = 2;
  private List<TextsSearchEntity.DataBean.ItemsBean> list;
  private List<TextsSearchEntity.DataBean.FiltersBean> filterlist;
  private List<String> filters;

  public FilterAdapter() {
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

  @Override public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
    int positions = (filterlist.size() - 1) * 6;

    if (holder instanceof NormalViewHolder) {
      //如果filst为空,不会出现越界异常情况//最多两个
      int index = FilterUtils.getIndex(filterlist,position);
      ((NormalViewHolder) holder).titleProduct.setText(list.get(index).getTitle());
      Glide.with(holder.itemView.getContext())
          .load(list.get(index).getPicUrl())
          .into(((NormalViewHolder) holder).iconProduct);
      ((NormalViewHolder) holder).price.setText("¥" + list.get(index).getPrice());
      ((NormalViewHolder) holder).priceAllNet.setText(
          "全网销量" + list.get(index).getViewSales() + "万");
      ((NormalViewHolder) holder).priceTaobao.setText("¥" + list.get(index).getFromat());
      ((NormalViewHolder) holder).priceMecheat.setText(
          list.get(index).getShopCount() + "电商100商家比价");
    } else if (holder instanceof HeadViewHolder) {
      //int newpositions = position / 6;
      //filters.clear();
      //filters.addAll(filterlist.get(newpositions).getFilterOption());
      //if (filters.size() >= 5) {
      //  ((HeadViewHolder) holder).five.setVisibility(View.VISIBLE);
      //} else if (filters.size() >= 4 && filters.size() < 5) {
      //  ((HeadViewHolder) holder).five.setVisibility(View.INVISIBLE);
      //  ((HeadViewHolder) holder).four.setVisibility(View.VISIBLE);
      //  ((HeadViewHolder) holder).thrid.setVisibility(View.VISIBLE);
      //  ((HeadViewHolder) holder).second.setVisibility(View.VISIBLE);
      //  ((HeadViewHolder) holder).first.setVisibility(View.VISIBLE);
      //
      //  ((HeadViewHolder) holder).first.setText(filters.get(0));
      //  ((HeadViewHolder) holder).second.setText(filters.get(1));
      //  ((HeadViewHolder) holder).thrid.setText(filters.get(2));
      //  ((HeadViewHolder) holder).four.setText(filters.get(3));
      //} else if (filters.size() >= 3 && filters.size() < 4) {
      //  ((HeadViewHolder) holder).five.setVisibility(View.INVISIBLE);
      //  ((HeadViewHolder) holder).four.setVisibility(View.INVISIBLE);
      //  ((HeadViewHolder) holder).thrid.setVisibility(View.VISIBLE);
      //  ((HeadViewHolder) holder).second.setVisibility(View.VISIBLE);
      //  ((HeadViewHolder) holder).first.setVisibility(View.VISIBLE);
      //
      //  ((HeadViewHolder) holder).first.setText(filters.get(0));
      //  ((HeadViewHolder) holder).second.setText(filters.get(1));
      //  ((HeadViewHolder) holder).thrid.setText(filters.get(2));
      //} else if (filters.size() >= 2 && filters.size() < 3) {
      //  ((HeadViewHolder) holder).five.setVisibility(View.INVISIBLE);
      //  ((HeadViewHolder) holder).four.setVisibility(View.INVISIBLE);
      //  ((HeadViewHolder) holder).thrid.setVisibility(View.INVISIBLE);
      //  ((HeadViewHolder) holder).second.setVisibility(View.VISIBLE);
      //  ((HeadViewHolder) holder).first.setVisibility(View.VISIBLE);
      //
      //  ((HeadViewHolder) holder).first.setText(filters.get(0));
      //  ((HeadViewHolder) holder).second.setText(filters.get(1));
      //} else if (filters.size() >= 1 && filters.size() < 2) {
      //  ((HeadViewHolder) holder).five.setVisibility(View.INVISIBLE);
      //  ((HeadViewHolder) holder).four.setVisibility(View.INVISIBLE);
      //  ((HeadViewHolder) holder).thrid.setVisibility(View.INVISIBLE);
      //  ((HeadViewHolder) holder).second.setVisibility(View.INVISIBLE);
      //  ((HeadViewHolder) holder).first.setVisibility(View.VISIBLE);
      //
      //  ((HeadViewHolder) holder).first.setText(filters.get(0));
      //} else if (filters.size() < 1) {
      //  ((HeadViewHolder) holder).five.setVisibility(View.INVISIBLE);
      //  ((HeadViewHolder) holder).four.setVisibility(View.INVISIBLE);
      //  ((HeadViewHolder) holder).thrid.setVisibility(View.INVISIBLE);
      //  ((HeadViewHolder) holder).second.setVisibility(View.INVISIBLE);
      //  ((HeadViewHolder) holder).first.setVisibility(View.INVISIBLE);
      //}
    }
  }

  @Override public int getItemCount() {
    int positions = (filterlist.size() - 1) * 6;
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
    }
  }

  public class HeadViewHolder extends RecyclerView.ViewHolder {
   // Button first, second, thrid, four, five;

    public HeadViewHolder(View itemView) {
      super(itemView);
      //first = (Button) itemView.findViewById(R.id.first);
      //second = (Button) itemView.findViewById(R.id.second);
      //thrid = (Button) itemView.findViewById(R.id.thrid);
      //four = (Button) itemView.findViewById(R.id.four);
      //five = (Button) itemView.findViewById(R.id.five);
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
