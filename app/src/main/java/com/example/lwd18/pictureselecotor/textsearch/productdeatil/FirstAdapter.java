package com.example.lwd18.pictureselecotor.textsearch.productdeatil;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.lwd18.pictureselecotor.ProductDetailEntity;
import com.example.lwd18.pictureselecotor.R;
import java.util.ArrayList;
import java.util.List;



/**
 * 创建者     李文东
 * 创建时间   2017/6/19 18:21
 * 描述	      ${TODO}
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class FirstAdapter extends RecyclerView.Adapter<FirstAdapter.MyViewHolder> {
  private List<ProductDetailEntity.DataBean.SameProductsBean> list;
  private int mI;

  public FirstAdapter() {
    list = new ArrayList<>();
  }

  @Override public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = View.inflate(parent.getContext(), R.layout.goods_items_first, null);
    return new MyViewHolder(view);
  }

  @Override public void onBindViewHolder(final MyViewHolder holder, final int position) {
    holder.priceTvMin.setText(list.get(position).getTitle());
    holder.titleProductFirst.setText(list.get(position).getPlatform());
    holder.priceTaobao.setText("¥"+list.get(position).getPrice());
    Glide.with(holder.itemView.getContext()).load("http:" + list.get(position).getPicUrl()).into(holder.iconProduct);
    holder.linout.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        Uri uri = Uri.parse(list.get(position).getDetailUrl());
        Intent it = new Intent(Intent.ACTION_VIEW,uri);
        holder.itemView.getContext().startActivity(it);
      }
    });

  }

  @Override public int getItemCount() {
    System.out.println("mi===" + mI + "");
    return list.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView iconProduct;
    TextView titleProductFirst;
    TextView titleProductComment;
    TextView priceTvMin;
    TextView prices;
    TextView priceTaobao;
    TextView priceMecheat;
    LinearLayout linout;
    public MyViewHolder(View itemView) {
      super(itemView);
      iconProduct = (ImageView) itemView.findViewById(R.id.icon_product);
      titleProductFirst = (TextView) itemView.findViewById(R.id.title_product_first);
      titleProductComment = (TextView) itemView.findViewById(R.id.title_product_comment);
      prices = (TextView) itemView.findViewById(R.id.prices);
      priceTvMin = (TextView) itemView.findViewById(R.id.price_tv_min);
      priceTaobao = (TextView) itemView.findViewById(R.id.price_taobao);
      priceMecheat = (TextView) itemView.findViewById(R.id.price_mecheat);
      linout= (LinearLayout) itemView.findViewById(R.id.li_out);
    }
  }

  public void addMoreItem(List<ProductDetailEntity.DataBean.SameProductsBean> newDatas) {
    list.addAll(newDatas);
    notifyDataSetChanged();
  }

  public void addItem(List<ProductDetailEntity.DataBean.SameProductsBean> mlists) {
    list.clear();
    mlists.addAll(list);
    list.removeAll(list);
    list.addAll(mlists);
    notifyDataSetChanged();
  }
}
