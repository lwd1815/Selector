package com.example.lwd18.pictureselecotor.textsearch;

import com.example.lwd18.pictureselecotor.TextsSearchEntity;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

/**
 * 创建者     李文东
 * 创建时间   2017/9/27 10:26
 * 描述:用来保存数据降低耦合性
 */

public class DataPresenter {
  private static DataPresenter singleTon = null;
  //用来记录筛选框中的数据
  private List<TextsSearchEntity.DataBean.FiltersBean> list;
  //用来记录综合界面被选中的标签
  private List<String> selectList;
  //用来记录筛选框中被选中的标签
  private List<String> filterSelectlist;
  //用来记录搜索出来的数据
  private List<TextsSearchEntity.DataBean.ItemsBean> Searchlist;
  private DataPresenter() {
    list = new ArrayList<>();
    selectList = new ArrayList<>();
    filterSelectlist = new ArrayList<>();
    Searchlist=new ArrayList<>();
  }

  public static DataPresenter getSingleTon() {
    if (singleTon == null) {
      singleTon = new DataPresenter();
    }
    return singleTon;
  }

  /**
   * 用来记录筛选框中的数据
   */
  public void saveData(List<TextsSearchEntity.DataBean.FiltersBean> newDatas) {
    list.clear();
    list.addAll(newDatas);
  }

  public List<TextsSearchEntity.DataBean.FiltersBean> getData() {
    return list;
  }

  /**
   * 用来记录被选中的item
   */
  public void saveSelect(String s) {
    if (selectList.size() <= 0) {
      selectList.clear();
      selectList.add(s);
    } else {
      if (!selectList.contains(s)) {
        selectList.add(s);
      } else {
        selectList.remove(s);
      }
    }
  }

  public List<String> getSelectList() {
    return selectList;
  }

  /**
   * 用来记录搜素到的数据
   */
  public void saveSearch(List<TextsSearchEntity.DataBean.ItemsBean> list){
    HashSet h = new HashSet(list);
    list.clear();
    list.addAll(h);
    Searchlist.clear();
    Searchlist.addAll(list);
  }

  public List<TextsSearchEntity.DataBean.ItemsBean> getSearchlist(){
    return Searchlist;
  }
}
