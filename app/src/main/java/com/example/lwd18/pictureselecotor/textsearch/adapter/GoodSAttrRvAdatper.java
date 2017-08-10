package com.example.lwd18.pictureselecotor.textsearch.adapter;

import android.content.Context;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.lwd18.pictureselecotor.R;
import com.example.lwd18.pictureselecotor.TextsSearchEntity;
import com.example.lwd18.pictureselecotor.textsearch.itemadapter.GoodSAttrRvNAdapter1;
import com.example.lwd18.pictureselecotor.textsearch.itemadapter.GoodSAttrRvNAdapter10;
import com.example.lwd18.pictureselecotor.textsearch.itemadapter.GoodSAttrRvNAdapter11;
import com.example.lwd18.pictureselecotor.textsearch.itemadapter.GoodSAttrRvNAdapter12;
import com.example.lwd18.pictureselecotor.textsearch.itemadapter.GoodSAttrRvNAdapter2;
import com.example.lwd18.pictureselecotor.textsearch.itemadapter.GoodSAttrRvNAdapter3;
import com.example.lwd18.pictureselecotor.textsearch.itemadapter.GoodSAttrRvNAdapter4;
import com.example.lwd18.pictureselecotor.textsearch.itemadapter.GoodSAttrRvNAdapter5;
import com.example.lwd18.pictureselecotor.textsearch.itemadapter.GoodSAttrRvNAdapter6;
import com.example.lwd18.pictureselecotor.textsearch.itemadapter.GoodSAttrRvNAdapter7;
import com.example.lwd18.pictureselecotor.textsearch.itemadapter.GoodSAttrRvNAdapter8;
import com.example.lwd18.pictureselecotor.textsearch.itemadapter.GoodSAttrRvNAdapter9;
import com.example.lwd18.pictureselecotor.textsearch.vo.SaleAttributeVo;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建者     李文东
 * 创建时间   2017/7/10 12:41
 * 描述	      ${TODO}
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class GoodSAttrRvAdatper extends RecyclerView.Adapter<RecyclerView.ViewHolder>
    implements GoodSAttrRvFAdapter.OnClickListener{
  private Context context;
  private List<TextsSearchEntity.DataBean.FiltersBean> data;
  private String[] serviceStr = new String[] { "淘宝", "天猫", "京东", "国美", "苏宁", "当当", "亚马逊", "一号店" };
  private List<SaleAttributeVo> serviceList;
  private int HEAD = 0;
  private int Normal = 1;
  private int Foot = 2;
  private GoodSAttrRvFAdapter serviceAdapter;
  private GoodSAttrRvNAdapter normalAdapter;
  private GoodSAttrRvNAdapter1 normalAdapter1;
  private GoodSAttrRvNAdapter2 normalAdapter2;
  private GoodSAttrRvNAdapter3 normalAdapter3;
  private GoodSAttrRvNAdapter4 normalAdapter4;
  private GoodSAttrRvNAdapter5 normalAdapter5;
  private GoodSAttrRvNAdapter6 normalAdapter6;
  private GoodSAttrRvNAdapter7 normalAdapter7;
  private GoodSAttrRvNAdapter8 normalAdapter8;
  private GoodSAttrRvNAdapter9 normalAdapter9;
  private GoodSAttrRvNAdapter10 normalAdapter10;
  private GoodSAttrRvNAdapter11 normalAdapter11;
  private GoodSAttrRvNAdapter12 normalAdapter12;
  public GoodSAttrRvAdatper(Context context) {
    this.context = context;
    data = new ArrayList<>();
  }

  @Override public int getItemViewType(int position) {
    if (position == 0) {
      return HEAD;
    } else if (position == data.size() + 1) {
      return Foot;
    } else {
      return Normal;
    }
  }

  @Override public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
    View v;
    if (viewType == HEAD) {
      v = View.inflate(context, R.layout.item_goods_attrs_head, null);
      return new HeadViewHolder(v);
    } else if (viewType == Normal) {
      v = View.inflate(context, R.layout.item_goods_attr_list, null);
      return new NormalViewHolder(v);
    } else if (viewType == Foot) {
      v = View.inflate(context, R.layout.item_goods_attr_foot, null);
      return new FootViewHolder(v);
    }
    return null;
  }

  @Override public void onBindViewHolder(final RecyclerView.ViewHolder holder, final int position) {
    /**
     * 正常
     */
    // TODO: 2017/7/23 recycleview嵌套recycleview,由于每一个item都是一个recycleview,因此都有各自对应的adapte,如果设置一个adapter,每次数据变化时会有一系列的冲突
    // TODO: 2017/7/23 目前尚未找到更好的解决办法,因此只能使用最笨的办法以求稳定,索幸所选条目最多不会超过20条,因此此方法只能在group数量有限的情况下使用
    if (holder instanceof NormalViewHolder) {
      if (position==1){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.itemView.getContext(), 3, LinearLayoutManager.VERTICAL, false);
        ((NormalViewHolder) holder).Rv.setLayoutManager(gridLayoutManager);
        ((NormalViewHolder) holder).name.setText(data.get(position - 1).getFilterName());
        if (normalAdapter==null) {
          normalAdapter = new GoodSAttrRvNAdapter(context);
          ((NormalViewHolder) holder).Rv.setAdapter(normalAdapter);
            normalAdapter.notifyDataSetChanged(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
          if (data.get(position - 1).isChick()) {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_up);
          } else {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_down);
            data.get(position - 1).setChick(true);
          }
        }else {
          normalAdapter.notifyDataSetChanged(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
        }
        //点击将
        ((NormalViewHolder) holder).img.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            System.out.println("被点击的position==="+position);
            if (data.get(position - 1).isChick()) {
              ((ImageView) v).setImageResource(R.drawable.sort_common_up);
            } else {
              ((ImageView) v).setImageResource(R.drawable.sort_common_down);
            }
              normalAdapter.notifyDataSetChanged(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
            data.get(position - 1).setChick(!data.get(position - 1).isChick());
          }
        });
      }else if (position==2){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.itemView.getContext(), 3, LinearLayoutManager.VERTICAL, false);
        ((NormalViewHolder) holder).Rv.setLayoutManager(gridLayoutManager);
        ((NormalViewHolder) holder).name.setText(data.get(position - 1).getFilterName());
        if (normalAdapter1==null) {
          normalAdapter1 = new GoodSAttrRvNAdapter1(context);
          ((NormalViewHolder) holder).Rv.setAdapter(normalAdapter1);
          normalAdapter1.notifyDataSetChanged(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
          if (data.get(position - 1).isChick()) {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_up);
          } else {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_down);
            data.get(position - 1).setChick(true);
          }
        }else {
          normalAdapter1.notifyDataSetChanged(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
        }
        //点击将
        ((NormalViewHolder) holder).img.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            System.out.println("被点击的position==="+position);
            if (data.get(position - 1).isChick()) {
              ((ImageView) v).setImageResource(R.drawable.sort_common_up);
            } else {
              ((ImageView) v).setImageResource(R.drawable.sort_common_down);
            }
            normalAdapter1.notifyDataSetChanged(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
            data.get(position - 1).setChick(!data.get(position - 1).isChick());
          }
        });
      }else if (position==3){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.itemView.getContext(), 3, LinearLayoutManager.VERTICAL, false);
        ((NormalViewHolder) holder).Rv.setLayoutManager(gridLayoutManager);
        ((NormalViewHolder) holder).name.setText(data.get(position - 1).getFilterName());
        if (normalAdapter2==null) {
          normalAdapter2 = new GoodSAttrRvNAdapter2(context);
          ((NormalViewHolder) holder).Rv.setAdapter(normalAdapter2);
          normalAdapter2.notifyDataSetChanged(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
          if (data.get(position - 1).isChick()) {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_up);
          } else {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_down);
            data.get(position - 1).setChick(true);
          }
        }else {
          normalAdapter2.notifyDataSetChanged(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
        }
        //点击将
        ((NormalViewHolder) holder).img.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            System.out.println("被点击的position==="+position);
            if (data.get(position - 1).isChick()) {
              ((ImageView) v).setImageResource(R.drawable.sort_common_up);
            } else {
              ((ImageView) v).setImageResource(R.drawable.sort_common_down);
            }
            normalAdapter2.notifyDataSetChanged(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
            data.get(position - 1).setChick(!data.get(position - 1).isChick());
          }
        });
      }else if (position==4){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.itemView.getContext(), 3, LinearLayoutManager.VERTICAL, false);
        ((NormalViewHolder) holder).Rv.setLayoutManager(gridLayoutManager);
        ((NormalViewHolder) holder).name.setText(data.get(position - 1).getFilterName());
        if (normalAdapter3==null) {
          normalAdapter3 = new GoodSAttrRvNAdapter3(context);
          ((NormalViewHolder) holder).Rv.setAdapter(normalAdapter3);
          normalAdapter3.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
          if (data.get(position - 1).isChick()) {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_up);
          } else {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_down);
            data.get(position - 1).setChick(true);
          }
        }else {
          normalAdapter3.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
        }
        //点击将
        ((NormalViewHolder) holder).img.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            System.out.println("被点击的position==="+position);
            if (data.get(position - 1).isChick()) {
              ((ImageView) v).setImageResource(R.drawable.sort_common_up);
            } else {
              ((ImageView) v).setImageResource(R.drawable.sort_common_down);
            }
            normalAdapter3.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
            data.get(position - 1).setChick(!data.get(position - 1).isChick());
          }
        });
      }else if (position==5){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.itemView.getContext(), 3, LinearLayoutManager.VERTICAL, false);
        ((NormalViewHolder) holder).Rv.setLayoutManager(gridLayoutManager);
        ((NormalViewHolder) holder).name.setText(data.get(position - 1).getFilterName());
        if (normalAdapter4==null) {
          normalAdapter4 = new GoodSAttrRvNAdapter4(context);
          ((NormalViewHolder) holder).Rv.setAdapter(normalAdapter4);
          normalAdapter4.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
          if (data.get(position - 1).isChick()) {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_up);
          } else {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_down);
            data.get(position - 1).setChick(true);
          }
        }else {
          normalAdapter4.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
        }
        //点击将
        ((NormalViewHolder) holder).img.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            System.out.println("被点击的position==="+position);
            if (data.get(position - 1).isChick()) {
              ((ImageView) v).setImageResource(R.drawable.sort_common_up);
            } else {
              ((ImageView) v).setImageResource(R.drawable.sort_common_down);
            }
            normalAdapter4.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
            data.get(position - 1).setChick(!data.get(position - 1).isChick());
          }
        });
      }else if (position==6){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.itemView.getContext(), 3, LinearLayoutManager.VERTICAL, false);
        ((NormalViewHolder) holder).Rv.setLayoutManager(gridLayoutManager);
        ((NormalViewHolder) holder).name.setText(data.get(position - 1).getFilterName());
        if (normalAdapter5==null) {
          normalAdapter5 = new GoodSAttrRvNAdapter5(context);
          ((NormalViewHolder) holder).Rv.setAdapter(normalAdapter5);
          normalAdapter5.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
          if (data.get(position - 1).isChick()) {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_up);
          } else {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_down);
            data.get(position - 1).setChick(true);
          }
        }else {
          normalAdapter5.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
        }
        //点击将
        ((NormalViewHolder) holder).img.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            System.out.println("被点击的position==="+position);
            if (data.get(position - 1).isChick()) {
              ((ImageView) v).setImageResource(R.drawable.sort_common_up);
            } else {
              ((ImageView) v).setImageResource(R.drawable.sort_common_down);
            }
            normalAdapter5.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
            data.get(position - 1).setChick(!data.get(position - 1).isChick());
          }
        });
      }else if (position==7){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.itemView.getContext(), 3, LinearLayoutManager.VERTICAL, false);
        ((NormalViewHolder) holder).Rv.setLayoutManager(gridLayoutManager);
        ((NormalViewHolder) holder).name.setText(data.get(position - 1).getFilterName());
        if (normalAdapter6==null) {
          normalAdapter6 = new GoodSAttrRvNAdapter6(context);
          ((NormalViewHolder) holder).Rv.setAdapter(normalAdapter6);
          normalAdapter6.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
          if (data.get(position - 1).isChick()) {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_up);
          } else {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_down);
            data.get(position - 1).setChick(true);
          }
        }else {
          normalAdapter6.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
        }
        //点击将
        ((NormalViewHolder) holder).img.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            System.out.println("被点击的position==="+position);
            if (data.get(position - 1).isChick()) {
              ((ImageView) v).setImageResource(R.drawable.sort_common_up);
            } else {
              ((ImageView) v).setImageResource(R.drawable.sort_common_down);
            }
            normalAdapter6.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
            data.get(position - 1).setChick(!data.get(position - 1).isChick());
          }
        });
      }else if (position==8){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.itemView.getContext(), 3, LinearLayoutManager.VERTICAL, false);
        ((NormalViewHolder) holder).Rv.setLayoutManager(gridLayoutManager);
        ((NormalViewHolder) holder).name.setText(data.get(position - 1).getFilterName());
        if (normalAdapter7==null) {
          normalAdapter7 = new GoodSAttrRvNAdapter7(context);
          ((NormalViewHolder) holder).Rv.setAdapter(normalAdapter7);
          normalAdapter7.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
          if (data.get(position - 1).isChick()) {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_up);
          } else {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_down);
            data.get(position - 1).setChick(true);
          }
        }else {
          normalAdapter7.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
        }
        //点击将
        ((NormalViewHolder) holder).img.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            System.out.println("被点击的position==="+position);
            if (data.get(position - 1).isChick()) {
              ((ImageView) v).setImageResource(R.drawable.sort_common_up);
            } else {
              ((ImageView) v).setImageResource(R.drawable.sort_common_down);
            }
            normalAdapter7.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
            data.get(position - 1).setChick(!data.get(position - 1).isChick());
          }
        });
      }else if (position==9){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.itemView.getContext(), 3, LinearLayoutManager.VERTICAL, false);
        ((NormalViewHolder) holder).Rv.setLayoutManager(gridLayoutManager);
        ((NormalViewHolder) holder).name.setText(data.get(position - 1).getFilterName());
        if (normalAdapter8==null) {
          normalAdapter8 = new GoodSAttrRvNAdapter8(context);
          ((NormalViewHolder) holder).Rv.setAdapter(normalAdapter8);
          normalAdapter8.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
          if (data.get(position - 1).isChick()) {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_up);
          } else {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_down);
            data.get(position - 1).setChick(true);
          }
        }else {
          normalAdapter8.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
        }
        //点击将
        ((NormalViewHolder) holder).img.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            System.out.println("被点击的position==="+position);
            if (data.get(position - 1).isChick()) {
              ((ImageView) v).setImageResource(R.drawable.sort_common_up);
            } else {
              ((ImageView) v).setImageResource(R.drawable.sort_common_down);
            }
            normalAdapter8.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
            data.get(position - 1).setChick(!data.get(position - 1).isChick());
          }
        });
      }else if (position==10){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.itemView.getContext(), 3, LinearLayoutManager.VERTICAL, false);
        ((NormalViewHolder) holder).Rv.setLayoutManager(gridLayoutManager);
        ((NormalViewHolder) holder).name.setText(data.get(position - 1).getFilterName());
        if (normalAdapter9==null) {
          normalAdapter9 = new GoodSAttrRvNAdapter9(context);
          ((NormalViewHolder) holder).Rv.setAdapter(normalAdapter9);
          normalAdapter9.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
          if (data.get(position - 1).isChick()) {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_up);
          } else {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_down);
            data.get(position - 1).setChick(true);
          }
        }else {
          normalAdapter9.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
        }
        //点击将
        ((NormalViewHolder) holder).img.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            System.out.println("被点击的position==="+position);
            if (data.get(position - 1).isChick()) {
              ((ImageView) v).setImageResource(R.drawable.sort_common_up);
            } else {
              ((ImageView) v).setImageResource(R.drawable.sort_common_down);
            }
            normalAdapter4.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
            data.get(position - 1).setChick(!data.get(position - 1).isChick());
          }
        });
      }else if (position==11){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.itemView.getContext(), 3, LinearLayoutManager.VERTICAL, false);
        ((NormalViewHolder) holder).Rv.setLayoutManager(gridLayoutManager);
        ((NormalViewHolder) holder).name.setText(data.get(position - 1).getFilterName());
        if (normalAdapter10==null) {
          normalAdapter10 = new GoodSAttrRvNAdapter10(context);
          ((NormalViewHolder) holder).Rv.setAdapter(normalAdapter10);
          normalAdapter10.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
          if (data.get(position - 1).isChick()) {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_up);
          } else {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_down);
            data.get(position - 1).setChick(true);
          }
        }else {
          normalAdapter10.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
        }
        //点击将
        ((NormalViewHolder) holder).img.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            System.out.println("被点击的position==="+position);
            if (data.get(position - 1).isChick()) {
              ((ImageView) v).setImageResource(R.drawable.sort_common_up);
            } else {
              ((ImageView) v).setImageResource(R.drawable.sort_common_down);
            }
            normalAdapter10.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
            data.get(position - 1).setChick(!data.get(position - 1).isChick());
          }
        });
      }else if (position==12){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.itemView.getContext(), 3, LinearLayoutManager.VERTICAL, false);
        ((NormalViewHolder) holder).Rv.setLayoutManager(gridLayoutManager);
        ((NormalViewHolder) holder).name.setText(data.get(position - 1).getFilterName());
        if (normalAdapter11==null) {
          normalAdapter11 = new GoodSAttrRvNAdapter11(context);
          ((NormalViewHolder) holder).Rv.setAdapter(normalAdapter11);
          normalAdapter11.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
          if (data.get(position - 1).isChick()) {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_up);
          } else {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_down);
            data.get(position - 1).setChick(true);
          }
        }else {
          normalAdapter11.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
        }
        //点击将
        ((NormalViewHolder) holder).img.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            System.out.println("被点击的position==="+position);
            if (data.get(position - 1).isChick()) {
              ((ImageView) v).setImageResource(R.drawable.sort_common_up);
            } else {
              ((ImageView) v).setImageResource(R.drawable.sort_common_down);
            }
            normalAdapter11.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
            data.get(position - 1).setChick(!data.get(position - 1).isChick());
          }
        });
      }else if (position==13){
        GridLayoutManager gridLayoutManager = new GridLayoutManager(holder.itemView.getContext(), 3, LinearLayoutManager.VERTICAL, false);
        ((NormalViewHolder) holder).Rv.setLayoutManager(gridLayoutManager);
        ((NormalViewHolder) holder).name.setText(data.get(position - 1).getFilterName());
        if (normalAdapter12==null) {
          normalAdapter12 = new GoodSAttrRvNAdapter12(context);
          ((NormalViewHolder) holder).Rv.setAdapter(normalAdapter12);
          normalAdapter12.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
          if (data.get(position - 1).isChick()) {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_up);
          } else {
            ((NormalViewHolder) holder).img.setImageResource(R.drawable.sort_common_down);
            data.get(position - 1).setChick(true);
          }
        }else {
          normalAdapter12.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
        }
        //点击将
        ((NormalViewHolder) holder).img.setOnClickListener(new View.OnClickListener() {
          @Override public void onClick(View v) {
            System.out.println("被点击的position==="+position);
            if (data.get(position - 1).isChick()) {
              ((ImageView) v).setImageResource(R.drawable.sort_common_up);
            } else {
              ((ImageView) v).setImageResource(R.drawable.sort_common_down);
            }
            normalAdapter12.notifyDataSetChangeds(data.get(position - 1).isChick(), data.get(position - 1).getFilterOption(),position-1);
            data.get(position - 1).setChick(!data.get(position - 1).isChick());
          }
        });
      }

      /**
       * 头部
       */
    } else if (holder instanceof HeadViewHolder) {
      serviceList = new ArrayList<>();
      for (int i = 0; i < serviceStr.length; i++) {
        SaleAttributeVo vo = new SaleAttributeVo();
        vo.setValue(serviceStr[i]);
        serviceList.add(vo);
      }
      GridLayoutManager gridLayoutManager =
          new GridLayoutManager(holder.itemView.getContext(), 3, LinearLayoutManager.VERTICAL,
              false);
      ((HeadViewHolder) holder).serviceGrid.setLayoutManager(gridLayoutManager);
      serviceAdapter = new GoodSAttrRvFAdapter(context);
      serviceAdapter.setOnClickListeners(this);
      ((HeadViewHolder) holder).serviceGrid.setAdapter(serviceAdapter);
      serviceAdapter.notifyDataSetChanged(true, serviceList);
      /**
       * 尾部
       */
    } else if (holder instanceof FootViewHolder) {

    }
  }

  @Override public int getItemCount() {
    return data.size() == 0 ? 2 : data.size() + 2;
  }

  /**
   * 头部中的view的点击事件
   * @param position
   */
  @Override public void itemClick(int position) {
    //设置当前选中的位置的状态为非。
    serviceList.get(position).setChecked(!serviceList.get(position).isChecked());
    /**
     * 单选
     */
   for (int i = 0; i < serviceList.size(); i++) {
     //跳过已设置的选中的位置的状态
     if (i == position) {
       continue;
     }
     serviceList.get(i).setChecked(false);

   }
   serviceAdapter.notifyDataSetChanged(true, serviceList);
    /**
     * 多选
     */
    //serviceAdapter.notifyDataSetChanged(serviceList.get(position).isChecked(),serviceList);
  }

  public class HeadViewHolder extends RecyclerView.ViewHolder {
    private RecyclerView serviceGrid;

    public HeadViewHolder(View itemView) {
      super(itemView);
      serviceGrid = (RecyclerView) itemView.findViewById(R.id.yuguo_service);
    }
  }

  public class NormalViewHolder extends RecyclerView.ViewHolder {
    public TextView name,select;
    public ImageView img;
    public RecyclerView Rv;

    public NormalViewHolder(View itemView) {
      super(itemView);
      name = (TextView) itemView.findViewById(R.id.attr_list_name);
      img = (ImageView) itemView.findViewById(R.id.attr_list_img);
      Rv = (RecyclerView) itemView.findViewById(R.id.attr_list_grid);
      select = (TextView) itemView.findViewById(R.id.attr_list_select);
    }
  }

  public class FootViewHolder extends RecyclerView.ViewHolder {

    public FootViewHolder(View itemView) {
      super(itemView);
    }
  }

  //添加数据
  public void addItem(List<TextsSearchEntity.DataBean.FiltersBean> newDatas) {
    data.clear();
    newDatas.addAll(data);
    data.removeAll(data);
    data.addAll(newDatas);
    notifyDataSetChanged();
  }
}
