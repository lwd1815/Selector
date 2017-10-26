package com.example.lwd18.pictureselecotor.textsearch;

import com.example.lwd18.pictureselecotor.TextSearchRecomdEntity;
import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * 创建者     李文东
 * 创建时间   2017/7/13 15:24
 * 更新描述
 */

public interface ApiNet {


 /**
  * 聚类商品推荐
  * @param pid
  * @param pageId
  * @return
  */
 @GET("product-recommend-search")
 Observable<TextSearchRecomdEntity> getRecomProduct(@Query("pid") String pid,
     @Query("pageId") int pageId);
}
