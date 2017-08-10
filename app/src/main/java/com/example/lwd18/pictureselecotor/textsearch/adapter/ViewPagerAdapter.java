package com.example.lwd18.pictureselecotor.textsearch.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.lwd18.pictureselecotor.BaseFragment;
import com.example.lwd18.pictureselecotor.R;
import java.util.List;


/**
 * 创建者     李文东
 * 创建时间   2017/6/16 17:24
 * 描述	      ${TODO}
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private List<BaseFragment> mBaseFragmentList;
    private List<String> tablist;
    private Context mContext;
    private int[] imageResId = {
            0,0,
            R.drawable.search_icon_price_normal,
            R.mipmap.cate_filter
    };

    public ViewPagerAdapter(Context context, FragmentManager fm, List<BaseFragment> fragmentList, List<String> tablist) {
        super(fm);
        this.mContext =context;
        this.mBaseFragmentList = fragmentList;
        this.tablist = tablist;
    }

    @Override
    public Fragment getItem(int position) {
        return mBaseFragmentList.get(position);
    }

    @Override
    public int getCount() {
        return mBaseFragmentList.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tablist.get(position % tablist.size());
    }

    public View getTabView(int position){
        View view = LayoutInflater.from(mContext).inflate(R.layout.custom_tabtitle, null);
        TextView txt_title = (TextView) view.findViewById(R.id.title_tv);
        txt_title.setText(tablist.get(position));
        ImageView img_title = (ImageView) view.findViewById(R.id.title_iv);
        img_title.setImageResource(imageResId[position]);

        if (position == 0) {
            txt_title.setTextColor(mContext.getResources().getColor(R.color.deep_main_color));
            img_title.setImageResource(imageResId[position]);
        } else {
            txt_title.setTextColor(Color.BLACK);
            img_title.setImageResource(imageResId[position]);
        }
        return view;
    }
}
