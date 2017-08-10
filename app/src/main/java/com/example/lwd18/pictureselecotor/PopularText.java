package com.example.lwd18.pictureselecotor;

import java.util.List;

/**
 * Created by EtherealPatrick on 2017/4/17.
 */

public class PopularText {

  /**
   * id : 1767979372
   * updateTime : 5107225
   * state : 0
   * stateSmg : 成功
   * items : ["春季新款","薄外套","黑色","花衬衫","大裤衩","人字拖"]
   */

  private int id;
  private int updateTime;
  private int state;
  private String stateSmg;
  private List<String> items;


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(int updateTime) {
    this.updateTime = updateTime;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public String getStateSmg() {
    return stateSmg;
  }

  public void setStateSmg(String stateSmg) {
    this.stateSmg = stateSmg;
  }

  public List<String> getItems() {
    return items;
  }

  public void setItems(List<String> items) {
    this.items = items;
  }
}
