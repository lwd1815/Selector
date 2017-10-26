package com.example.lwd18.pictureselecotor;

import java.util.ArrayList;

/**
 * Created by x on 2016/11/4.
 */

public class TextSearchEntity {
  private int id;

  private int state;

  private int updateTime;

  private int sortType;

  private int pageTotal;


  private ArrayList<SortItem> sortItem;

  private ArrayList<ProductItemBean> productItems;

  public int getPageTotal() {
    return pageTotal;
  }

  public void setPageTotal(int pageTotal) {
    this.pageTotal = pageTotal;
  }


  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public ArrayList<ProductItemBean> getProductItems() {
    return productItems;
  }

  public void setProductItems(ArrayList<ProductItemBean> productItems) {
    this.productItems = productItems;
  }

  public ArrayList<SortItem> getSortItem() {
    return sortItem;
  }

  public void setSortItem(ArrayList<SortItem> sortItem) {
    this.sortItem = sortItem;
  }

  public int getSortType() {
    return sortType;
  }

  public void setSortType(int sortType) {
    this.sortType = sortType;
  }

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public int getUpdateTime() {
    return updateTime;
  }

  public void setUpdateTime(int updateTime) {
    this.updateTime = updateTime;
  }
}
