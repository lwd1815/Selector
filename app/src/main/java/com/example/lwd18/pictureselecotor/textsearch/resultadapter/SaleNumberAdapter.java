package com.example.lwd18.pictureselecotor.textsearch.resultadapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.lwd18.pictureselecotor.R;
import com.example.lwd18.pictureselecotor.TextsSearchEntity;
import com.example.lwd18.pictureselecotor.textsearch.DataPresenter;
import com.example.lwd18.pictureselecotor.textsearch.Eventutil.FilterUtils;
import com.example.lwd18.pictureselecotor.textsearch.adapter.FilterHeadAdapter;
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

public class SaleNumberAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
  private int HEAD = 1;
  private int NORMAL = 2;
  private List<TextsSearchEntity.DataBean.ItemsBean> list;
  private List<TextsSearchEntity.DataBean.FiltersBean> filterlist;
  private List<String> filters;
  private Context context;
  private FilterHeadAdapter adapter;
  public SaleNumberAdapter() {
    list = new ArrayList<>();
    filterlist = new ArrayList<>();
    filters = new ArrayList<>();
    if (DataPresenter.getSingleTon().getData().size()>0){
      filterlist.clear();
      filterlist.addAll(DataPresenter.getSingleTon().getData());
      notifyDataSetChanged();
    }
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
    context = parent.getContext();
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
      //如果filst为空,不会出现越界异常情况//最多两个
      final int index = FilterUtils.getIndex(filterlist,position);
      final TextsSearchEntity.DataBean.ItemsBean ware = list.get(index);
     NormalViewHolder wareHolder = (NormalViewHolder) holder;
      //单品
      if (ware.getType() == 2) {
        wareHolder.dpout.setVisibility(View.VISIBLE);
        wareHolder.lineout.setVisibility(View.GONE);
        if (ware.getAmountType() == 1) {
          wareHolder.llAmount.setVisibility(View.VISIBLE);
          wareHolder.dpyhj.setVisibility(View.GONE);
          wareHolder.amountPrice.setText(ware.getAmountMoney() + "");
        }else if (ware.getAmountType()==2){
          wareHolder.llAmount.setVisibility(View.GONE);
          wareHolder.dpyhj.setVisibility(View.VISIBLE);
         // wareHolder.dpyhjtv.setText(ware.getAmountMoney()+"");
          wareHolder.dpyhjtv.setText("满100减50");
        }
        ((NormalViewHolder) holder).titleProduct.setText(list.get(index).getTitle());
        Glide.with(holder.itemView.getContext())
            .load(list.get(index).getPicUrl())
            .into(((NormalViewHolder) holder).dPIV);
        wareHolder.dpfrom.setText(list.get(index).getPlatform());
        wareHolder.dpEvaluate.setText("好评率98%");
        wareHolder.dpjianjie.setText(list.get(index).getTitle());
        wareHolder.dpPrice.setText("¥" + list.get(index).getPrice());
        wareHolder.dpsingPrice.setText("¥100/L");
        wareHolder.dpcount.setText("销量" + list.get(index).getViewSales() + "万");

        wareHolder.dpout.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            context.startActivity(
                new Intent(context, WareActivity.class).putExtra("productId", ware.getPfromId())
                    .putExtra("productType", 2));
          }
        });
        //聚类
      }else if (ware.getType() == 1){
        wareHolder.dpout.setVisibility(View.GONE);
        wareHolder.lineout.setVisibility(View.VISIBLE);
        ((NormalViewHolder) holder).titleProduct.setText(list.get(index).getTitle());
        Glide.with(holder.itemView.getContext())
            .load(list.get(index).getPicUrl())
            .into(((NormalViewHolder) holder).iconProduct);
        ((NormalViewHolder) holder).price.setText("¥" + list.get(index).getPrice());
        ((NormalViewHolder) holder).priceAllNet.setText(
            "全网销量" + list.get(index).getViewSales() + "万");
        ((NormalViewHolder) holder).priceTaobao.setText("¥" + list.get(index).getFromat());
        ((NormalViewHolder) holder).priceMecheat.setText(list.get(index).getShopCount() + "商家比价");

        wareHolder.lineout.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            Intent intent = new Intent(holder.itemView.getContext(), GoodsDetailActivity.class);
            intent.putExtra("productId", list.get(index).getId());
            intent.putExtra("price", list.get(position).getPrice());
            intent.putExtra("lv", list.get(index).getFromat());
            holder.itemView.getContext().startActivity(intent);
          }
        });
      }
    } else if (holder instanceof HeadViewHolder) {
      //int newpositions = position / 6;
      //filters.clear();
      //filters.addAll(filterlist.get(newpositions).getFilterOption());
      //LinearLayoutManager
      //    line = new LinearLayoutManager(holder.itemView.getContext(), LinearLayoutManager.HORIZONTAL, false);
      //((HeadViewHolder) holder).rv.setLayoutManager(line);
      //adapter = new FilterHeadAdapter(filters);
      //((HeadViewHolder) holder).rv.setAdapter(adapter);
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
    LinearLayout lineout;
    LinearLayout llAmount;
    TextView amountPrice;
    //单品局部
    //单品最外层
    LinearLayout dpout;
    ImageView dPIV;
    TextView dpfrom,dpEvaluate,dpjianjie,dpPrice,dpsingPrice,dpcount;
    RelativeLayout dpyhj;
    TextView dpyhjtv;
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
      lineout = (LinearLayout) itemView.findViewById(R.id.com_out_ll);
      llAmount = (LinearLayout) itemView.findViewById(R.id.ll_ware_item_amount);
      amountPrice = (TextView) itemView.findViewById(R.id.tv_amount_price);
      //单品布局
      dpout= (LinearLayout) itemView.findViewById(R.id.li_out_dp);
      dPIV = (ImageView) itemView.findViewById(R.id.icon_product_dp);
      dpfrom= (TextView) itemView.findViewById(R.id.title_product_dp);
      dpEvaluate=(TextView) itemView.findViewById(R.id.title_product_comment_dp);
      dpjianjie=(TextView) itemView.findViewById(R.id.price_tv_min_dp);
      dpPrice=(TextView) itemView.findViewById(R.id.prices_dp);
      dpsingPrice=(TextView) itemView.findViewById(R.id.price_single_dp);
      dpcount=(TextView) itemView.findViewById(R.id.price_mecheat_dp);
      llAmount = (LinearLayout) itemView.findViewById(R.id.ll_ware_item_amount);
      amountPrice = (TextView) itemView.findViewById(R.id.tv_amount_price);
      dpyhj= (RelativeLayout) itemView.findViewById(R.id.ll_ware_item_textsearch);
      dpyhjtv= (TextView) itemView.findViewById(R.id.tv_rebate_price_textsearch);
    }
  }

  public class HeadViewHolder extends RecyclerView.ViewHolder {
    public RecyclerView rv;
    public HeadViewHolder(View itemView) {
      super(itemView);
      rv = (RecyclerView) itemView.findViewById(R.id.filter_rv);
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
