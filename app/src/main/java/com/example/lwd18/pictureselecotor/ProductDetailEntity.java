package com.example.lwd18.pictureselecotor;

import java.util.List;

/**
 * 创建者     李文东
 * 创建时间   2017/7/11 17:36
 * 描述
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述
 */

public class ProductDetailEntity {

  /**
   * id : -344997581
   * updateTime : 16536870
   * state : 0
   * stateSmg : 成功
   * data : {"id":"AV0IiqSROFuQj7og1tjh","title":"伊利金典纯牛奶/光明优+纯牛奶  250ml*12盒/箱全国包邮临期促销","price":18,"viewSales":0,"platform":"淘宝","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=549237149629","picUrl":["//g-search1.alicdn.com/img/bao/uploaded/i4/i2/2668770299/TB2KrNEmSBjpuFjSsplXXa5MVXa_!!2668770299.png"],"sameProducts":[{"title":"伊利金典纯牛奶特价/250ml*12盒全国包邮2017年1月份生产日期*/

  private int id;
  private int updateTime;
  private int state;
  private String stateSmg;
  private DataBean data;

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

  public DataBean getData() {
    return data;
  }

  public void setData(DataBean data) {
    this.data = data;
  }

  public static class DataBean {
    /**
     * id : AV0IiqSROFuQj7og1tjh
     * title : 伊利金典纯牛奶/光明优+纯牛奶  250ml*12盒/箱全国包邮临期促销
     * price : 18
     * viewSales : 0
     * platform : 淘宝
     * detailUrl : http://h5.m.taobao.com/awp/core/detail.htm?id=549237149629
     * picUrl : ["//g-search1.alicdn.com/img/bao/uploaded/i4/i2/2668770299/TB2KrNEmSBjpuFjSsplXXa5MVXa_!!2668770299.png"]
     * sameProducts : [{"title":"伊利金典纯牛奶特价/250ml*12盒全国包邮2017年1月份生产日期","price":26,"platform":"淘宝","detailUrl":"http://h5.m.taobao
     */

    private String id;
    private String title;
    private int price;
    private int viewSales;
    private String platform;
    private String detailUrl;
    private String pfromId;
    private String proCharts;
    private List<String> picUrl;
    private List<SameProductsBean> sameProducts;
    private int amountType;
    private double amountMoney;

    public String getPfromId() {
      return pfromId;
    }

    public void setPfromId(String pfromId) {
      this.pfromId = pfromId;
    }

    public int getAmountType() {
      return amountType;
    }

    public void setAmountType(int amountType) {
      this.amountType = amountType;
    }

    public double getAmountMoney() {
      return amountMoney;
    }

    public void setAmountMoney(double amountMoney) {
      this.amountMoney = amountMoney;
    }


    public String getId() {
      return id;
    }

    public void setId(String id) {
      this.id = id;
    }

    public String getTitle() {
      return title;
    }

    public void setTitle(String title) {
      this.title = title;
    }

    public int getPrice() {
      return price;
    }

    public void setPrice(int price) {
      this.price = price;
    }

    public int getViewSales() {
      return viewSales;
    }

    public void setViewSales(int viewSales) {
      this.viewSales = viewSales;
    }

    public String getPlatform() {
      return platform;
    }

    public void setPlatform(String platform) {
      this.platform = platform;
    }

    public String getDetailUrl() {
      return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
      this.detailUrl = detailUrl;
    }

    public String getProCharts() {
      return proCharts;
    }

    public void setProCharts(String proCharts) {
      this.proCharts = proCharts;
    }

    public List<String> getPicUrl() {
      return picUrl;
    }

    public void setPicUrl(List<String> picUrl) {
      this.picUrl = picUrl;
    }

    public List<SameProductsBean> getSameProducts() {
      return sameProducts;
    }

    public void setSameProducts(List<SameProductsBean> sameProducts) {
      this.sameProducts = sameProducts;
    }

    public static class SameProductsBean {
      /**
       * title : 伊利金典纯牛奶特价/250ml*12盒全国包邮2017年1月份生产日期
       * price : 26
       * platform : 淘宝
       * detailUrl : http://h5.m.taobao.com/awp/core/detail.htm?id=540721743049
       * picUrl : //g-search3.alicdn.com/img/bao/uploaded/i4/i1/2851289470/TB23uBPaH5K.eBjy0FnXXaZzVXa_!!2851289470.jpg
       */
      private String id;
      private String title;
      private int price;
      private int viewSales;
      private String platform;
      private int platformCount;
      private String detailUrl;
      private String keywords;
      private String fromat;
      private String picUrl;
      private int shopCount;
      private String pfromId;
      private int type;
      private int amountType;
      private double amountMoney;


      public String getPfromId() {
        return pfromId;
      }

      public void setPfromId(String pfromId) {
        this.pfromId = pfromId;
      }

      public int getType() {
        return type;
      }

      public void setType(int type) {
        this.type = type;
      }

      public int getAmountType() {
        return amountType;
      }

      public void setAmountType(int amountType) {
        this.amountType = amountType;
      }

      public double getAmountMoney() {
        return amountMoney;
      }

      public void setAmountMoney(double amountMoney) {
        this.amountMoney = amountMoney;
      }

      public String getId() {
        return id;
      }

      public void setId(String id) {
        this.id = id;
      }

      public int getViewSales() {
        return viewSales;
      }

      public void setViewSales(int viewSales) {
        this.viewSales = viewSales;
      }

      public int getPlatformCount() {
        return platformCount;
      }

      public void setPlatformCount(int platformCount) {
        this.platformCount = platformCount;
      }

      public String getKeywords() {
        return keywords;
      }

      public void setKeywords(String keywords) {
        this.keywords = keywords;
      }

      public String getFromat() {
        return fromat;
      }

      public void setFromat(String fromat) {
        this.fromat = fromat;
      }

      public int getShopCount() {
        return shopCount;
      }

      public void setShopCount(int shopCount) {
        this.shopCount = shopCount;
      }

      public String getTitle() {
        return title;
      }

      public void setTitle(String title) {
        this.title = title;
      }

      public int getPrice() {
        return price;
      }

      public void setPrice(int price) {
        this.price = price;
      }

      public String getPlatform() {
        return platform;
      }

      public void setPlatform(String platform) {
        this.platform = platform;
      }

      public String getDetailUrl() {
        return detailUrl;
      }

      public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
      }

      public String getPicUrl() {
        return picUrl;
      }

      public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
      }
    }
  }
}
