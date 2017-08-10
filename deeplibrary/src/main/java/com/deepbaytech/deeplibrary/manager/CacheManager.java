package com.deepbaytech.deeplibrary.manager;

import com.deepbaytech.deeplibrary.utils.SharedPreferencesUtil;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by EtherealPatrick on 2016/10/9.
 */

public class CacheManager {
  private static CacheManager manager;
    private  static List<String> list = new ArrayList<>();

  public static CacheManager getInstance() {
   list.add("牛奶");
    return manager == null ? (manager = new CacheManager()) : manager;
  }



  /**
   * 搜索历史
   */
  public List<String> getSearchHistory() {

      return SharedPreferencesUtil.getInstance().getObject(getSearchHistoryKey(), List.class)==null?list:SharedPreferencesUtil.getInstance().getObject(getSearchHistoryKey(), List.class);
  }

  public void saveSearchHistory(Object obj) {
      SharedPreferencesUtil.getInstance().putObject(getSearchHistoryKey(), obj);
  }

  private String getSearchHistoryKey() {
    return "searchHistory";
  }

  ///**
  // * 话题选择历史
  // */
  //public List<String> getTopicHistory(){
  //    return SharedPreferencesUtil.getInstance().getObject(getTopicHistoryKey(), List.class);
  //}

  public void saveTopicHistory(Object obj) {
    SharedPreferencesUtil.getInstance().putObject(getTopicHistoryKey(), obj);
  }

  private String getTopicHistoryKey() {
    return "topicHistory";
  }
}
