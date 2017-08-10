package com.deepbaytech.deeplibrary.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaScannerConnection;
import android.os.Handler;
import android.os.Message;
import android.support.v4.util.LruCache;
import android.util.Log;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by EtherealPatrick on 2016/7/11.
 */
public class ImageCacheUtils {


    private static final int SUCCESS = 100;
    private static final int FAIL = 101;
    private LruCache<String, Bitmap> lruCache;
    private File cacheDir;
    private ExecutorService newFixedThreadPool;

    private Handler mHandler;


    public ImageCacheUtils(Context context, Handler handler){
        int maxSize = (int) (Runtime.getRuntime().maxMemory()/8);
        //maxSize : 缓存的最大空间，一般都是内存的八分之一
        lruCache = new LruCache<String, Bitmap>(maxSize){
            //获取移除图片的大小
            protected int sizeOf(String key, Bitmap value) {
                //getRowBytes() : 图片一行占用的空间
                return value.getRowBytes()*value.getHeight();
            }
        };
        cacheDir = context.getCacheDir();
        //nThreads : 线程池中的线程的数量
        newFixedThreadPool = Executors.newFixedThreadPool(5);
        this.mHandler = handler;
    }

    /**
     * 根据图片的路径获取相应的图片
     * @return
     */
    public Bitmap getBitmap(String url,int position){
        //1.从内存获取图片
        //LruCache<K, V> ;//key：表示图片的名称，value：图片
        //lruCache.put(key, value);//保存图片到缓存中，key:表示图片的名称，value：图片
        Bitmap bitmap = lruCache.get(url);//根据key获取对应的bitmap图片
        if (bitmap!=null) {
            return bitmap;
        }
        //2.从本地缓存中获取图片，并保存到内存中
        bitmap = getBitmapFromLocal(url);
        if (bitmap!=null) {
            return bitmap;
        }
        //3.从网络获取图片，并保存到本地缓存和内存中
        getBitmapFromNet(url,position);
        return null;
    }
    /**
     * 从网络获取图片
     * @param url
     */
    private void getBitmapFromNet(String url,int position) {
        newFixedThreadPool.execute(new RunnableTask(url,position));
    }

    private class RunnableTask implements Runnable{

        private String mUrl;
        private int mPosition;

        public RunnableTask(String url,int position){
            this.mUrl = url;
            this.mPosition = position;
        }

        @Override
        public void run() {
            Message message = Message.obtain();
            try {
                //根据图片的url路径获取图片
                URL url = new URL(mUrl);
                HttpURLConnection con = (HttpURLConnection) url.openConnection();
                con.setConnectTimeout(3000);//连接超时时间
                con.setReadTimeout(3000);//读取超时时间
                InputStream inputStream = con.getInputStream();//服务返回的数据，是以流的形式返回的
                Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                //将获取的图片保存到本地缓存和内存中
                setBitmapToLocal(mUrl, bitmap);
                lruCache.put(mUrl, bitmap);

                message.what=SUCCESS;
                message.obj=bitmap;
                message.arg1 = mPosition;
                mHandler.sendMessage(message);

                return;//表示线程结束
            } catch (Exception e) {
                e.printStackTrace();
            }
            message.what=FAIL;
            mHandler.sendMessage(message);
        }
    }

    /**
     * 获取本地缓存的图片
     * @param url
     */
    private Bitmap getBitmapFromLocal(String url) {
        try {
            //从本地缓存获取了图片
            File file = new File(cacheDir, MD5Utils.md5(url).substring(10));
            Bitmap bitmap = BitmapFactory.decodeFile(file.getAbsolutePath());
            //保存到内存中
            lruCache.put(url, bitmap);
            return bitmap;
        } catch (Exception e) {
        }
        return null;
    }
    /**
     * 将图片保存到本地缓存中
     * @param url
     * @param bitmap
     */
    private void setBitmapToLocal(String url,Bitmap bitmap){
        try {

            File file = new File(cacheDir, MD5Utils.md5(url).substring(10));
            FileOutputStream outputStream = new FileOutputStream(file);
            //format : 图片的格式
            //quality : 图片质量，百分比，png格式，参数失效
            //stream : 将图片保存到本地的什么位置
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, outputStream);
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }


    public static Bitmap convertToBitmap(String path) {
        int w = 500;
        int h = 500;
        BitmapFactory.Options opts = new BitmapFactory.Options();
        opts.inJustDecodeBounds = true;
        opts.inPreferredConfig = Bitmap.Config.ARGB_8888;
        BitmapFactory.decodeFile(path, opts);
        int width = opts.outWidth;
        int height = opts.outHeight;
        Log.i("***************bitmap", "width = " + width + "height=" + height);
        float scaleWidth = 0.f, scaleHeight = 0.f;
        if (width > w || height > h) {
            scaleWidth = ((float) width) / w;
            scaleHeight = ((float) height) / h;
        }
        opts.inJustDecodeBounds = false;
        opts.inDither = false;
        opts.inPreferredConfig = null;
        opts.inPurgeable = true;
        opts.inInputShareable = true;
        float scale = Math.max(scaleWidth, scaleHeight);
        Log.i("*******daxiao*", (int) scale + "");
        opts.inSampleSize = (int) scale;
        Bitmap mBt = BitmapFactory.decodeFile(path, opts);
        if (mBt==null)
            return null;
        return Bitmap.createScaledBitmap(mBt, w, h, true);
    }




    public static String saveToSDCard(byte[] data,Context context,String path) throws IOException {
        Date date = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
        String filename = "IMG_" + format.format(date) + ".jpg";
        File fileFolder = new File(path);
        if (!fileFolder.exists()) {
            fileFolder.mkdirs();
        }
        File jpgFile = new File(fileFolder, filename);
        FileOutputStream outputStream = new FileOutputStream(jpgFile); //
        //刷新相册
        MediaScannerConnection.scanFile(context,
                new String[]{jpgFile.getAbsolutePath()}, null, null);

        outputStream.write(data);
        outputStream.close();
//        Intent intent = new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
//        Uri uri = Uri.fromFile(new File(Environment
//                .getExternalStorageDirectory() + "/DeepbayPicture/" + filename));
//        intent.setData(uri);
//        mContext.sendBroadcast(intent);
        return jpgFile.getAbsolutePath();
    }












}
