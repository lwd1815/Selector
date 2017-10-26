package com.example.lwd18.pictureselecotor.productdetail;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.bumptech.glide.Glide;
import com.example.lwd18.pictureselecotor.R;
import com.example.lwd18.pictureselecotor.TextSearchRecomdEntity;
import java.util.ArrayList;
import java.util.List;



/**
 * 创建者     李文东
 * 创建时间   2017/6/19 18:21
 * 描述
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述
 */

public class SecondAdapter extends RecyclerView.Adapter<SecondAdapter.MyViewHolder> {
  private List<TextSearchRecomdEntity.DataBean.ItemsBean> list;
  public SecondAdapter(){
    list=new ArrayList<>();
  }
  @Override public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View view = View.inflate(parent.getContext(), R.layout.good_item_second, null);
    return new MyViewHolder(view);
  }

  @Override public void onBindViewHolder(final MyViewHolder holder, final int position) {
    holder.priceTvMin.setText(list.get(position).getTitle());
    holder.titleProductFirst.setText(list.get(position).getPlatform());
    holder.prices.setText("¥"+list.get(position).getPrice());
    Glide.with(holder.itemView.getContext()).load(list.get(position).getPicUrl()).into(holder.iconProduct);
    holder.linout.setOnClickListener(new View.OnClickListener() {
      @Override public void onClick(View v) {
        //Uri uri = Uri.parse(list.get(position).getDetailUrl());
        //Intent it = new Intent(Intent.ACTION_VIEW,uri);
        //holder.itemView.getContext().startActivity(it);
        //holder.itemView.getContext().startActivity(
        //    new Intent(holder.itemView.getContext(), WareActivity.class).putExtra("productId", list.get(position).getPfromId())
        //        .putExtra("productType", 2));
      }
    });
  }

  @Override public int getItemCount() {
    return list.size();
  }

  public class MyViewHolder extends RecyclerView.ViewHolder {
    ImageView iconProduct;
    TextView titleProductFirst;
    TextView titleProductComment;
    TextView priceTvMin;
    TextView prices;
    TextView pricesingle;
    TextView priceMecheat;
    LinearLayout linout;
    RelativeLayout dpyhj;
    TextView dpyhjtv;
    LinearLayout llAmount;
    TextView amountPrice;

    public MyViewHolder(View itemView) {
      super(itemView);
      iconProduct = (ImageView) itemView.findViewById(R.id.icon_product);
      titleProductFirst = (TextView) itemView.findViewById(R.id.title_product_first);
      titleProductComment = (TextView) itemView.findViewById(R.id.title_product_comment);
      prices = (TextView) itemView.findViewById(R.id.prices);
      priceTvMin = (TextView) itemView.findViewById(R.id.price_tv_min);
      pricesingle = (TextView) itemView.findViewById(R.id.price_single);
      priceMecheat = (TextView) itemView.findViewById(R.id.price_mecheat);
      linout= (LinearLayout) itemView.findViewById(R.id.li_out);
      llAmount = (LinearLayout) itemView.findViewById(R.id.ll_ware_item_amount);
      amountPrice = (TextView) itemView.findViewById(R.id.tv_amount_price);
      dpyhj= (RelativeLayout) itemView.findViewById(R.id.ll_ware_item_textsearch);
      dpyhjtv= (TextView) itemView.findViewById(R.id.tv_rebate_price_textsearch);
    }
  }

  public void addMoreItem(List<TextSearchRecomdEntity.DataBean.ItemsBean> newDatas) {
    list.addAll(newDatas);
    notifyDataSetChanged();
  }

  public void addItem(List<TextSearchRecomdEntity.DataBean.ItemsBean> mlists) {
    list.clear();
    mlists.addAll(list);
    list.removeAll(list);
    list.addAll(mlists);
    notifyDataSetChanged();
  }
}
