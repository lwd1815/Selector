package com.example.lwd18.pictureselecotor.textsearch;
import com.example.lwd18.pictureselecotor.TextSearchRecomdEntity;
import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * 创建者     李文东
 * 创建时间   2017/7/13 15:17
 * 描述
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述
 */

public class HttpMethodLogin {
  //正式环境
  public static final String BASE_URL="http://api.deepbaytech.com/mobile/api/v0.0.1/";
  //测试环境
  //public static final String BASE_URL="http://beta.deepbaytech.com/mobile/api/v0.0.1/";
  private static final int DEFAULT_TIMEOUT=10;
  private Retrofit mRetrofit;
  private ApiNet mApiNet;

  private HttpMethodLogin(){
    OkHttpClient.Builder httpClientBuilder = new OkHttpClient().newBuilder();
    httpClientBuilder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

    mRetrofit = new Retrofit.Builder()
        .client(httpClientBuilder.build())
        //.addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
        .addConverterFactory(FastJsonConverterFactory.create())
        .baseUrl(BASE_URL)
        .build();

    mApiNet=mRetrofit.create(ApiNet.class);

  }

  private static class singletonHolder{
    private static final HttpMethodLogin INSTANCE = new HttpMethodLogin();
  }

  public static HttpMethodLogin getInstance(){
    return singletonHolder.INSTANCE;
  }


  public void getTextSearchRecomd(Subscriber<TextSearchRecomdEntity> subscriber,String pid,Integer pageId){
    mApiNet.getRecomProduct(pid,pageId)
        .subscribeOn(Schedulers.io())
        .unsubscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(subscriber);
  }
}
