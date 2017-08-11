package com.example.lwd18.pictureselecotor;

import android.content.Context;
import android.graphics.Point;
import android.support.multidex.MultiDex;
import android.support.multidex.MultiDexApplication;
import android.util.Log;
import android.view.WindowManager;
import com.deepbaytech.deeplibrary.utils.SharedPreferencesUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.cookie.CookieJarImpl;
import com.zhy.http.okhttp.cookie.store.MemoryCookieStore;
import com.zhy.http.okhttp.log.LoggerInterceptor;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

/**
 * Created by EtherealPatrick on 2016/9/8.
 */
public class MyApplication extends MultiDexApplication {
    private static  int windowWidth;
    private static  int windowHeight;

    private static Context context;
    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
        Fresco.initialize(this);
        CookieJarImpl cookieJar1 = new CookieJarImpl(new MemoryCookieStore());
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                .addInterceptor(new LoggerInterceptor("TAG"))
                .cookieJar(cookieJar1)
                .build();
        OkHttpUtils.initClient(okHttpClient);




        MultiDex.install(this);

        //开启debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        //Config.DEBUG = true;

        initXY(context);
        //Thread.setDefaultUncaughtExceptionHandler(crashHandler);
        SharedPreferencesUtil.init(context,"deepSP",MODE_PRIVATE);
        /**
         * 初始化imageloader
         */
        ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
        ImageLoader.getInstance().init(configuration);
    }

    private void initXY(Context context) {
        Point size = new Point();
        WindowManager manager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        manager.getDefaultDisplay().getSize(size);
        windowWidth = size.x;
        windowHeight = size.y;
    }

    public static int getWindowWidth(){
        return windowWidth;
    }

    public static int getWindowHeight(){
        return windowHeight;
    }

    public static Context getContext(){
        return context;
    }

    private Thread.UncaughtExceptionHandler crashHandler = new Thread.UncaughtExceptionHandler(){
        @Override public void uncaughtException(Thread thread, Throwable ex) {
            Log.i("UncaughtException","未能捕获的异常:,Thread"+ thread.getName()+",Threadable:"+ex);
        }
    };

}
