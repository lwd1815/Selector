package com.example.lwd18.pictureselecotor.bigphotobrows;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.example.lwd18.pictureselecotor.R;
import com.example.lwd18.pictureselecotor.bigphotobrows.utils.WindowUtil;
import com.example.lwd18.pictureselecotor.dialog.QuickOptionDialog;
import java.util.List;
import uk.co.senab.photoview.PhotoViewAttacher;



/**
 * 创建者     李文东
 * 创建时间   2017/5/24 9:45
 * 描述	      ${TODO}
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */
public class ViewPageAdapter extends PagerAdapter {
    private Context context;
    private List<String> images;
    private SparseArray<View> cacheView;
    private ViewGroup containerTemp;
    private Activity mActivity;
    public ViewPageAdapter(Activity activity,Context context, List<String> images) {
        this.context = context;
        this.images = images;
        this.mActivity=activity;
        cacheView = new SparseArray<>(images.size());
    }

    @Override
    public Object instantiateItem(final ViewGroup container, int position) {
        if(containerTemp == null) containerTemp = container;

        View view = cacheView.get(position);

        if(view == null){
            view = LayoutInflater.from(context).inflate(R.layout.vp_item_image,container,false);
            view.setTag(position);
            ImageView image = (ImageView) view.findViewById(R.id.image);
            PhotoViewAttacher photoViewAttacher = new PhotoViewAttacher(image);


            Glide.with(context).asBitmap().load(images.get(position)).into(new MyTarget(photoViewAttacher));
            photoViewAttacher.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    /**
                     * 长按之后弹出dialog
                     */
                    //showQuickOption();
                    mShowDialog.show();
                    return true;
                }
            });
            photoViewAttacher.setOnPhotoTapListener(new PhotoViewAttacher.OnPhotoTapListener() {
                @Override
                public void onPhotoTap(View view, float x, float y) {
                    Activity activity = (Activity) context;
                    activity.finish();
                }
            });
            cacheView.put(position,view);
        }
        container.addView(view);
        return view;
    }
    private void showQuickOption() {
        final QuickOptionDialog dialog = new QuickOptionDialog(mActivity,context);
        dialog.setCancelable(true);
        dialog.setCanceledOnTouchOutside(true);
        dialog.show();
    }
    @Override
    public int getCount() {
        return images.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View view = (View) object;
        container.removeView(view);
    }

    private class MyTarget extends SimpleTarget<Bitmap>{

        private PhotoViewAttacher viewAttacher;

        public MyTarget(PhotoViewAttacher viewAttacher){
            this.viewAttacher = viewAttacher;
        }

        @Override
        public void onResourceReady(Bitmap resource, Transition<? super Bitmap> transition) {
            int width = resource.getWidth();
            int height = resource.getHeight();

            int newWidth = width;
            int newHeight = height;

            int screenWidth = WindowUtil.getInstance().getScreenWidth((Activity) context);
            int screenHeight = WindowUtil.getInstance().getScreenHeight((Activity) context);

            if(width > screenWidth){
                newWidth = screenWidth;
            }

            if(height > screenHeight){
                newHeight = screenHeight;
            }


            if(newWidth == width && newHeight == height){
                viewAttacher.getImageView().setImageBitmap(resource);
                viewAttacher.update();
                return;
            }

            //计算缩放比例
            float scaleWidth = ((float) newWidth) / width;
            float scaleHeight = ((float)newHeight) / height;

            Matrix matrix = new Matrix();
            matrix.postScale(scaleWidth,scaleHeight);

            Log.v("size",width + "");
            Log.v("size",height + "");

            Bitmap resizeBitmap = Bitmap.createBitmap(resource,0,0,width,height,matrix,true);

            viewAttacher.getImageView().setImageBitmap(resizeBitmap);
            viewAttacher.update();
        }
    }
    private ShowDialog mShowDialog;
    public void setShowDialog(ShowDialog showDialog){
        this.mShowDialog=showDialog;
    }
    public interface ShowDialog{
        void show();
    }

}
