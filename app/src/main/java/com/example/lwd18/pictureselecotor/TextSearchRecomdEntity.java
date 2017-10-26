package com.example.lwd18.pictureselecotor;

import java.util.List;

/**
 * 创建者     李文东
 * 创建时间   2017/10/18 17:10
 * 更新者     $Author$
 * 更新时间   $Date$
 */

public class TextSearchRecomdEntity {

  /**
   * id : 38570897
   * updateTime : 25088970
   * state : 0
   * stateMsg : 成功
   * data : {"pageId":1,"pageTotal":2,"sum":12,"items":[{"id":"563602","title":"【1月】荷兰原装进口风车牧场全脂200ml*24盒高钙早餐晚餐纯牛奶","price":103,"platform":"淘宝","pfromId":"550158449270","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=550158449270","picUrl":"http://g-search2.alicdn.com/img/bao/uploaded/i4/i5/TB1B.kYPpXXXXXraXXXtqVBFFXX_122831.jpg"},{"id":"563609","title":"【9月】荷兰原装进口风车牧场半脂 200ml*24盒高钙部分脱脂纯牛奶","price":103,"platform":"淘宝","pfromId":"550860481897","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=550860481897","picUrl":"http://g-search2.alicdn.com/img/bao/uploaded/i4/i7/TB1_AtbPVXXXXXBXpXXRr209pXX_042047.jpg"},{"id":"562359","title":"【2月】荷兰原装进口风车牧场半脂 200ml*24盒高钙部分脱脂纯牛奶","price":105,"platform":"淘宝","pfromId":"551225280964","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=551225280964","picUrl":"http://g-search3.alicdn.com/img/bao/uploaded/i4/i7/TB1_AtbPVXXXXXBXpXXRr209pXX_042047.jpg"},{"id":"564124","title":"【1月】荷兰原装进口风车牧场全脂200ml*24盒高钙早餐晚餐纯牛奶","price":108,"platform":"淘宝","pfromId":"550424944509","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=550424944509","picUrl":"http://g-search3.alicdn.com/img/bao/uploaded/i4/i5/TB1B.kYPpXXXXXraXXXtqVBFFXX_122831.jpg"},{"id":"561502","title":"【9月】荷兰原装进口风车牧场纯牛奶200ml*24盒半脂 高钙","price":109,"platform":"淘宝","pfromId":"546487464468","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=546487464468","picUrl":"http://g-search1.alicdn.com/img/bao/uploaded/i4/i4/TB1P_E7QVXXXXaiXpXXXXXXXXXX_!!0-item_pic.jpg"},{"id":"564151","title":"【1月】荷兰原装进口风车牧场全脂200ml*24盒高钙早餐晚餐纯牛奶","price":109,"platform":"淘宝","pfromId":"551290745964","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=551290745964","picUrl":"http://g-search2.alicdn.com/img/bao/uploaded/i4/i1/TB1qqXnRpXXXXbpXXXXXXXXXXXX_!!0-item_pic.jpg"},{"id":"564148","title":"【2月】荷兰原装进口风车牧场半脂 200ml*24盒高钙部分脱脂纯牛奶","price":109,"platform":"淘宝","pfromId":"551357191804","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=551357191804","picUrl":"http://g-search3.alicdn.com/img/bao/uploaded/i4/i2/TB1x.ANRXXXXXavaXXXXXXXXXXX_!!0-item_pic.jpg"},{"id":"561615","title":"【2月】荷兰原装进口风车牧场半脂 200ml*24盒高钙部分脱脂纯牛奶","price":119,"platform":"淘宝","pfromId":"551372618790","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=551372618790","picUrl":"http://g-search1.alicdn.com/img/bao/uploaded/i4/i7/TB1_AtbPVXXXXXBXpXXRr209pXX_042047.jpg"},{"id":"561610","title":"【1月】荷兰原装进口风车牧场全脂200ml*24盒高钙早餐晚餐纯牛奶","price":119,"platform":"淘宝","pfromId":"551409535485","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=551409535485","picUrl":"http://g-search1.alicdn.com/img/bao/uploaded/i4/i5/TB1B.kYPpXXXXXraXXXtqVBFFXX_122831.jpg"},{"id":"563588","title":"【1月】荷兰原装进口风车牧场全脂200ml*24盒高钙早餐晚餐纯牛奶","price":119,"platform":"淘宝","pfromId":"550105808143","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=550105808143","picUrl":"http://g-search3.alicdn.com/img/bao/uploaded/i4/i5/TB1B.kYPpXXXXXraXXXtqVBFFXX_122831.jpg"}]}
   */

  private int id;
  private int updateTime;
  private int state;
  private String stateMsg;
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

  public String getStateMsg() {
    return stateMsg;
  }

  public void setStateMsg(String stateMsg) {
    this.stateMsg = stateMsg;
  }

  public DataBean getData() {
    return data;
  }

  public void setData(DataBean data) {
    this.data = data;
  }

  public static class DataBean {
    /**
     * pageId : 1
     * pageTotal : 2
     * sum : 12
     * items : [{"id":"563602","title":"【1月】荷兰原装进口风车牧场全脂200ml*24盒高钙早餐晚餐纯牛奶","price":103,"platform":"淘宝","pfromId":"550158449270","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=550158449270","picUrl":"http://g-search2.alicdn.com/img/bao/uploaded/i4/i5/TB1B.kYPpXXXXXraXXXtqVBFFXX_122831.jpg"},{"id":"563609","title":"【9月】荷兰原装进口风车牧场半脂 200ml*24盒高钙部分脱脂纯牛奶","price":103,"platform":"淘宝","pfromId":"550860481897","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=550860481897","picUrl":"http://g-search2.alicdn.com/img/bao/uploaded/i4/i7/TB1_AtbPVXXXXXBXpXXRr209pXX_042047.jpg"},{"id":"562359","title":"【2月】荷兰原装进口风车牧场半脂 200ml*24盒高钙部分脱脂纯牛奶","price":105,"platform":"淘宝","pfromId":"551225280964","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=551225280964","picUrl":"http://g-search3.alicdn.com/img/bao/uploaded/i4/i7/TB1_AtbPVXXXXXBXpXXRr209pXX_042047.jpg"},{"id":"564124","title":"【1月】荷兰原装进口风车牧场全脂200ml*24盒高钙早餐晚餐纯牛奶","price":108,"platform":"淘宝","pfromId":"550424944509","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=550424944509","picUrl":"http://g-search3.alicdn.com/img/bao/uploaded/i4/i5/TB1B.kYPpXXXXXraXXXtqVBFFXX_122831.jpg"},{"id":"561502","title":"【9月】荷兰原装进口风车牧场纯牛奶200ml*24盒半脂 高钙","price":109,"platform":"淘宝","pfromId":"546487464468","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=546487464468","picUrl":"http://g-search1.alicdn.com/img/bao/uploaded/i4/i4/TB1P_E7QVXXXXaiXpXXXXXXXXXX_!!0-item_pic.jpg"},{"id":"564151","title":"【1月】荷兰原装进口风车牧场全脂200ml*24盒高钙早餐晚餐纯牛奶","price":109,"platform":"淘宝","pfromId":"551290745964","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=551290745964","picUrl":"http://g-search2.alicdn.com/img/bao/uploaded/i4/i1/TB1qqXnRpXXXXbpXXXXXXXXXXXX_!!0-item_pic.jpg"},{"id":"564148","title":"【2月】荷兰原装进口风车牧场半脂 200ml*24盒高钙部分脱脂纯牛奶","price":109,"platform":"淘宝","pfromId":"551357191804","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=551357191804","picUrl":"http://g-search3.alicdn.com/img/bao/uploaded/i4/i2/TB1x.ANRXXXXXavaXXXXXXXXXXX_!!0-item_pic.jpg"},{"id":"561615","title":"【2月】荷兰原装进口风车牧场半脂 200ml*24盒高钙部分脱脂纯牛奶","price":119,"platform":"淘宝","pfromId":"551372618790","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=551372618790","picUrl":"http://g-search1.alicdn.com/img/bao/uploaded/i4/i7/TB1_AtbPVXXXXXBXpXXRr209pXX_042047.jpg"},{"id":"561610","title":"【1月】荷兰原装进口风车牧场全脂200ml*24盒高钙早餐晚餐纯牛奶","price":119,"platform":"淘宝","pfromId":"551409535485","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=551409535485","picUrl":"http://g-search1.alicdn.com/img/bao/uploaded/i4/i5/TB1B.kYPpXXXXXraXXXtqVBFFXX_122831.jpg"},{"id":"563588","title":"【1月】荷兰原装进口风车牧场全脂200ml*24盒高钙早餐晚餐纯牛奶","price":119,"platform":"淘宝","pfromId":"550105808143","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=550105808143","picUrl":"http://g-search3.alicdn.com/img/bao/uploaded/i4/i5/TB1B.kYPpXXXXXraXXXtqVBFFXX_122831.jpg"}]
     */

    private int pageId;
    private int pageTotal;
    private int sum;
    private List<ItemsBean> items;

    public int getPageId() {
      return pageId;
    }

    public void setPageId(int pageId) {
      this.pageId = pageId;
    }

    public int getPageTotal() {
      return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
      this.pageTotal = pageTotal;
    }

    public int getSum() {
      return sum;
    }

    public void setSum(int sum) {
      this.sum = sum;
    }

    public List<ItemsBean> getItems() {
      return items;
    }

    public void setItems(List<ItemsBean> items) {
      this.items = items;
    }

    public static class ItemsBean {
      /**
       * id : 563602
       * title : 【1月】荷兰原装进口风车牧场全脂200ml*24盒高钙早餐晚餐纯牛奶
       * price : 103
       * platform : 淘宝
       * pfromId : 550158449270
       * detailUrl : http://h5.m.taobao.com/awp/core/detail.htm?id=550158449270
       * picUrl : http://g-search2.alicdn.com/img/bao/uploaded/i4/i5/TB1B.kYPpXXXXXraXXXtqVBFFXX_122831.jpg
       */

      private String id;
      private String title;
      private int price;
      private String platform;
      private String pfromId;
      private String detailUrl;
      private String picUrl;

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

      public String getPlatform() {
        return platform;
      }

      public void setPlatform(String platform) {
        this.platform = platform;
      }

      public String getPfromId() {
        return pfromId;
      }

      public void setPfromId(String pfromId) {
        this.pfromId = pfromId;
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
