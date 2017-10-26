package com.example.lwd18.pictureselecotor.textsearch.Eventutil;

import com.example.lwd18.pictureselecotor.TextsSearchEntity;
import java.util.List;

/**
 * 创建者     李文东
 * 创建时间   2017/7/11 9:18
 * 描述
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述
 */

public class FilterEventUtil {
  private List<TextsSearchEntity.DataBean.FiltersBean> list;
  public FilterEventUtil(){

  }
  public FilterEventUtil(List<TextsSearchEntity.DataBean.FiltersBean> list){
    this.list=list;
  }
  public List<TextsSearchEntity.DataBean.FiltersBean> getList(){
    return this.list;
  }
}
