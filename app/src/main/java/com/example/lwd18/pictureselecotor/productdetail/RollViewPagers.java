package com.example.lwd18.pictureselecotor.productdetail;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.example.lwd18.pictureselecotor.R;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建者     李文东
 * 创建时间   2017/6/19 19:37
 * 描述
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述
 */

    public class RollViewPagers extends ViewPager {
    //传递过来的图片数组，这个必须更换，真实项目中有可能是一个集合
    private int[] imageUrls;
    private List<String> mimageUrlslist;
    private static final int NEXT=99;//切换下一张图片的标志
    private boolean isRunning=false;//是否自动轮播的标志，默认不自动轮播

    //    private BitmapUtils bitmapUtils;
    public RollViewPagers(Context context) {
        super(context);
        mimageUrlslist=new ArrayList<>();
    }

    public RollViewPagers(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setImageUrls(int[] imageUrls) {
        this.imageUrls = imageUrls;
        setAdapter(new MyRollViePagerAdatper());
    }

    public void setImageUrlsList(List<String> imageUrlslist) {

        mimageUrlslist.clear();
        mimageUrlslist.addAll(imageUrlslist);
        setAdapter(new MyRollViePagerAdatper());
    }
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){

                case NEXT:
                    if(isRunning==true){
                        //设置当前item+1；相当于设置下一个item，然后余图片数量；
                        // setCurrentItem(getCurrentItem()+1);
                        //然后发送空消息延时2秒
                        //handler.sendEmptyMessageDelayed(NEXT,2000);
                    }
                    break;
            }
        }
    };

    //开始轮播
    public void startRoll(){
        //开启轮播
        isRunning=true;
        //发送handler延时2秒
        handler.sendEmptyMessageDelayed(NEXT,2000);
    }

    class MyRollViePagerAdatper extends PagerAdapter {

        @Override
        public int getCount() {
            return Integer.MAX_VALUE;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            //这个必须取余数，不然会下标越界
            //position=position%imageUrls.length;
            position=position%mimageUrlslist.size();
            ImageView
                imageView = (ImageView) View.inflate(getContext(), R.layout.viewpager_item, null);
            //先获取网络中图片的url
            //           在真实项目中使用谷歌官方提供的Glide加载图片
            //            Glide.with(context).load(new File(path)).diskCacheStrategy(DiskCacheStrategy.ALL).centerCrop().into(vh.imageView);
            Glide.with(getContext())
                .load(mimageUrlslist.get(position))
                .apply(new RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL).placeholder(R.drawable.loading_z))
                .into(imageView);
            container.addView(imageView);
            return imageView;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }
    }

    private int downTime=0;//按下时间
    //按下的XY坐标
    private int downX=0;
    private int downY=0;

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()){

            case MotionEvent.ACTION_DOWN:
                downX= (int) ev.getX();
                downY= (int) ev.getY();
                downTime= (int) System.currentTimeMillis();
                //停止轮播
                isRunning=false;
                handler.removeMessages(NEXT);
                break;

            case MotionEvent.ACTION_UP:
                int upX= (int) ev.getX();
                int upY= (int) ev.getY();
                int disX= Math.abs(upX - downX);
                int disY= Math.abs(upY - downY);
                int upTime=(int) System.currentTimeMillis();
                if(upTime-downTime<500 && disX-disY<5 ){
                    if(onItemClickListener!=null){
                        onItemClickListener.onItemClick(getCurrentItem()%mimageUrlslist.size());//当前位置就是显示的条目
                    }
                }

                //开启轮播
                startRoll();
                break;

            case MotionEvent.ACTION_CANCEL:
                startRoll();
                break;

            default:
                break;
        }

        return super.onTouchEvent(ev);
    }

    @Override
    //当控件挂载到页面上会调用此方法
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
    }

    @Override
    //当控件从页面上移除的时候会调用此方法
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        isRunning=false;
        handler.removeMessages(NEXT);
    }

    private OnItemClickListener onItemClickListener;

    public interface OnItemClickListener{
      void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener){
        this.onItemClickListener=onItemClickListener;
    }

}
