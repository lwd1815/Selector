package com.example.lwd18.pictureselecotor.textsearch.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.lwd18.pictureselecotor.TextsSearchEntity;
import com.example.lwd18.pictureselecotor.textsearch.Eventutil.FilterUtils;
import com.example.lwd18.pictureselecotor.textsearch.vo.SaleAttributeVo;
import java.util.ArrayList;
import java.util.List;

import static com.networkbench.com.google.gson.internal.a.m.R;

/**
 * 子属性GridView的适配器
 */
public class GoodsAttrsAdapter extends BaseAdapter {

    private Context context;
    private List<SaleAttributeVo> data = new ArrayList<SaleAttributeVo>();
    private List<TextsSearchEntity.DataBean.FiltersBean> list = new ArrayList<>();
    public GoodsAttrsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return data == null ? 0 : data.size();
        //return list==null?0:list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View v, ViewGroup parent) {
        final MyView myView;
        if (v == null) {
            myView = new MyView();
            v = View.inflate(context, R.layout.item_goods_attrs, null);
            myView.attr = (TextView) v.findViewById(R.id.attr_name);
            v.setTag(myView);
        } else {
            myView = (MyView) v.getTag();
        }
        myView.attr.setText(data.get(position).getValue());
        /**
         * 根据选中状态来设置item的背景和字体颜色
         */
        if (data.get(position).isChecked()) {
            myView.attr.setBackgroundResource(R.drawable.goods_attr_selected_shape);
            myView.attr.setTextColor(Color.WHITE);
        } else {
            myView.attr.setBackgroundResource(R.drawable.goods_attr_unselected_shape);
            myView.attr.setTextColor(Color.GRAY);
        }
        return v;
    }

    static class MyView {
        public TextView attr;
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
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
            FilterUtils.setNumber(tempData,data);
        }
        notifyDataSetChanged();
    }

    public void notifyDataSetChangeds(boolean isUnfold, final List<SaleAttributeVo> tempData) {
        if (tempData == null || 0 == tempData.size()) {
            return;
        }
        data.clear();
        // 如果是展开的，则加入全部data，反之则只显示3条
        if (isUnfold) {
            data.addAll(tempData);
        } else {
            //不展示
            data.clear();
        }
        notifyDataSetChanged();
    }
    public void notifyDataSetChangedFilter(boolean isUnfold, final List<TextsSearchEntity.DataBean.FiltersBean> tempData) {
        if (tempData == null || 0 == tempData.size()) {
            return;
        }
        list.clear();
        // 如果是展开的，则加入全部data，反之则只显示3条
        if (isUnfold) {
            list.addAll(tempData);
        } else {
            //FilterUtils.setNumberFilter(tempData,list);
        }
        notifyDataSetChanged();
    }

    public void notifyDataSetChangedsFilter(boolean isUnfold, final List<TextsSearchEntity.DataBean.FiltersBean> tempData) {
        if (tempData == null || 0 == tempData.size()) {
            return;
        }
        list.clear();
        // 如果是展开的，则加入全部data，反之则只显示3条
        if (isUnfold) {
            list.addAll(tempData);
        } else {
            //不展示
            list.clear();
        }
        notifyDataSetChanged();
    }
}
