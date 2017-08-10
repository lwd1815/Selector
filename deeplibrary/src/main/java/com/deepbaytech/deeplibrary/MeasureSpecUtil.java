package com.deepbaytech.deeplibrary;

import android.view.View.MeasureSpec;

public class MeasureSpecUtil {

	/**
	 * 打印宽和高测量规格的具体信息（大小和模式)
	 * @param widthMeasureSpec 宽度测量规格
	 * @param heightMeasureSpec 宽度测量规格
	 */
	public static void printMeasureSpec(int widthMeasureSpec, int heightMeasureSpec) {
		String widthModeStr = getMode(widthMeasureSpec);
		String heightModeStr = getMode(heightMeasureSpec);
//		Log.i("MeasureSpecUtil", "宽模式：" + widthModeStr + ", 宽：" + MeasureSpec.getSize(widthMeasureSpec));
//		Log.i("MeasureSpecUtil", "高模式：" + heightModeStr+ ", 高：" + MeasureSpec.getSize(heightMeasureSpec));
	}

	/** 获取字符串模式 */
	private static String getMode(int measureSpec) {
		int mode = MeasureSpec.getMode(measureSpec);
		String modeStr = (mode == MeasureSpec.EXACTLY) 
				         ? "EXACTLY" : (mode == MeasureSpec.AT_MOST) ? "AT_MOST" : "UNSPECIFIED";
		return modeStr;
	}

}
