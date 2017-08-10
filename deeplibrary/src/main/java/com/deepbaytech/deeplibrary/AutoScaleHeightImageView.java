package com.deepbaytech.deeplibrary;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

public class AutoScaleHeightImageView extends ImageView {

	public AutoScaleHeightImageView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// 创建一个测量规格
		// int measureSpec = MeasureSpec.makeMeasureSpec(100, MeasureSpec.EXACTLY);
		
		// 打印一下父容器传过来的测量规格
//		System.out.println("父容器传过来的测量规格");
		MeasureSpecUtil.printMeasureSpec(widthMeasureSpec, heightMeasureSpec);
		
		Drawable drawable = getDrawable();
		
		if (drawable != null) {
			int picWidth = drawable.getMinimumWidth();	// 获取图片的宽
			int picHeight = drawable.getMinimumHeight();// 获取图片的高
			float scale = (float) picHeight / picWidth;	// 最终要算什么样的值，这个值做为被除数
			
			int width = MeasureSpec.getSize(widthMeasureSpec);
			int height = (int) (width * scale);
			heightMeasureSpec = MeasureSpec.makeMeasureSpec(height, MeasureSpec.EXACTLY);
		}
		
		super.onMeasure(widthMeasureSpec, heightMeasureSpec);
		
//		System.out.println("ImageView测量出来的自己的宽高为：" + getMeasuredWidth() + " x " + getMeasuredHeight());
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
//		System.out.println("ImageView的真实的宽和高：" + getWidth() + "x" + getHeight());
		super.onDraw(canvas);
	}

}
