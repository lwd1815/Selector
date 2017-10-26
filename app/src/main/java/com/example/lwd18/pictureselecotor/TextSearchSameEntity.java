package com.example.lwd18.pictureselecotor;

import java.util.List;

/**
 * 创建者     李文东
 * 创建时间   2017/10/17 18:09
 * 更新者     $Author$
 * 更新时间   $Date$
 */

public class TextSearchSameEntity {

  /**
   * id : 600283361
   * updateTime : 25006119
   * state : 0
   * stateMsg : 成功
   * data : {"pageId":1,"pageTotal":1,"sum":10,"items":[{"id":"564152","title":"【12月】荷兰进口 乐荷部分脱脂200ml*24盒有机低脂高钙纯牛奶","price":179,"platform":"淘宝","pfromId":"551319942844","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=551319942844","picUrl":"http://g-search1.alicdn.com/img/bao/uploaded/i4/i3/TB140RmRpXXXXc5XXXXXXXXXXXX_!!0-item_pic.jpg","amountType":2,"amountMoney":5},{"id":"562364","title":"【1月】荷兰原装进口乐荷全脂200ML*24盒有机高钙早餐晚餐纯牛奶","price":179,"platform":"淘宝","pfromId":"550505202252","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=550505202252","picUrl":"http://g-search3.alicdn.com/img/bao/uploaded/i4/i1/TB1Xzx1KFXXXXclXpXXd3dC9XXX_034323.jpg","amountType":2,"amountMoney":5},{"id":"564146","title":"【1月】荷兰原装进口乐荷全脂200ML*24盒有机高钙早餐晚餐纯牛奶","price":179,"platform":"淘宝","pfromId":"551320010895","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=551320010895","picUrl":"http://g-search2.alicdn.com/img/bao/uploaded/i4/i4/TB1nFoSRXXXXXafaXXXXXXXXXXX_!!0-item_pic.jpg","amountType":2,"amountMoney":5},{"id":"562360","title":"【12月】荷兰进口 乐荷部分脱脂200ml*24盒有机低脂高钙纯牛奶","price":179,"platform":"淘宝","pfromId":"551282193400","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=551282193400","picUrl":"http://g-search3.alicdn.com/img/bao/uploaded/i4/i2/749469635/TB2fhXJgetTMeFjSZFOXXaTiVXa_!!749469635.jpg","amountType":2,"amountMoney":5},{"id":"564245","title":"【辉鑫食品】荷兰原装进口乐荷全脂有机纯牛奶200ML*24盒高钙","price":179,"platform":"淘宝","pfromId":"545922103558","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=545922103558","picUrl":"http://g-search3.alicdn.com/img/bao/uploaded/i4/i1/TB1g7l1QVXXXXc_XXXXXXXXXXXX_!!0-item_pic.jpg","amountType":1,"amountMoney":5},{"id":"564380","title":"【12月】荷兰进口 乐荷部分脱脂200ml*24盒有机低脂高钙纯牛奶","price":179,"platform":"淘宝","pfromId":"549420719981","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=549420719981","picUrl":"http://g-search2.alicdn.com/img/bao/uploaded/i4/i2/TB1MB0DQVXXXXcXXFXXXXXXXXXX_!!0-item_pic.jpg","amountType":2,"amountMoney":5},{"id":"563610","title":"【1月】荷兰原装进口乐荷全脂200ML*24盒有机高钙早餐晚餐纯牛奶","price":189,"platform":"淘宝","pfromId":"550926419978","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=550926419978","picUrl":"http://g-search1.alicdn.com/img/bao/uploaded/i4/i1/TB1Xzx1KFXXXXclXpXXd3dC9XXX_034323.jpg","amountType":2,"amountMoney":5},{"id":"562530","title":"【12月】荷兰进口 乐荷部分脱脂200ml*24盒有机低脂高钙纯牛奶","price":189,"platform":"淘宝","pfromId":"550157933818","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=550157933818","picUrl":"http://g-search1.alicdn.com/img/bao/uploaded/i4/i2/749469635/TB2fhXJgetTMeFjSZFOXXaTiVXa_!!749469635.jpg","amountType":2,"amountMoney":5},{"id":"563586","title":"【12月】荷兰进口 乐荷部分脱脂200ml*24盒有机低脂高钙纯牛奶","price":189,"platform":"淘宝","pfromId":"550219483671","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=550219483671","picUrl":"http://g-search3.alicdn.com/img/bao/uploaded/i4/i2/749469635/TB2fhXJgetTMeFjSZFOXXaTiVXa_!!749469635.jpg","amountType":2,"amountMoney":5},{"id":"563717","title":"【乐荷有机】11月生产日期荷兰进口有机纯牛奶全脂高钙200ml*24","price":199,"platform":"淘宝","pfromId":"547407372589","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=547407372589","picUrl":"http://g-search1.alicdn.com/img/bao/uploaded/i4/i2/TB1eWkbOXXXXXXiXFXXXXXXXXXX_!!0-item_pic.jpg","amountType":1,"amountMoney":5}]}
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
     * pageTotal : 1
     * sum : 10
     * items : [{"id":"564152","title":"【12月】荷兰进口 乐荷部分脱脂200ml*24盒有机低脂高钙纯牛奶","price":179,"platform":"淘宝","pfromId":"551319942844","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=551319942844","picUrl":"http://g-search1.alicdn.com/img/bao/uploaded/i4/i3/TB140RmRpXXXXc5XXXXXXXXXXXX_!!0-item_pic.jpg","amountType":2,"amountMoney":5},{"id":"562364","title":"【1月】荷兰原装进口乐荷全脂200ML*24盒有机高钙早餐晚餐纯牛奶","price":179,"platform":"淘宝","pfromId":"550505202252","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=550505202252","picUrl":"http://g-search3.alicdn.com/img/bao/uploaded/i4/i1/TB1Xzx1KFXXXXclXpXXd3dC9XXX_034323.jpg","amountType":2,"amountMoney":5},{"id":"564146","title":"【1月】荷兰原装进口乐荷全脂200ML*24盒有机高钙早餐晚餐纯牛奶","price":179,"platform":"淘宝","pfromId":"551320010895","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=551320010895","picUrl":"http://g-search2.alicdn.com/img/bao/uploaded/i4/i4/TB1nFoSRXXXXXafaXXXXXXXXXXX_!!0-item_pic.jpg","amountType":2,"amountMoney":5},{"id":"562360","title":"【12月】荷兰进口 乐荷部分脱脂200ml*24盒有机低脂高钙纯牛奶","price":179,"platform":"淘宝","pfromId":"551282193400","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=551282193400","picUrl":"http://g-search3.alicdn.com/img/bao/uploaded/i4/i2/749469635/TB2fhXJgetTMeFjSZFOXXaTiVXa_!!749469635.jpg","amountType":2,"amountMoney":5},{"id":"564245","title":"【辉鑫食品】荷兰原装进口乐荷全脂有机纯牛奶200ML*24盒高钙","price":179,"platform":"淘宝","pfromId":"545922103558","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=545922103558","picUrl":"http://g-search3.alicdn.com/img/bao/uploaded/i4/i1/TB1g7l1QVXXXXc_XXXXXXXXXXXX_!!0-item_pic.jpg","amountType":1,"amountMoney":5},{"id":"564380","title":"【12月】荷兰进口 乐荷部分脱脂200ml*24盒有机低脂高钙纯牛奶","price":179,"platform":"淘宝","pfromId":"549420719981","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=549420719981","picUrl":"http://g-search2.alicdn.com/img/bao/uploaded/i4/i2/TB1MB0DQVXXXXcXXFXXXXXXXXXX_!!0-item_pic.jpg","amountType":2,"amountMoney":5},{"id":"563610","title":"【1月】荷兰原装进口乐荷全脂200ML*24盒有机高钙早餐晚餐纯牛奶","price":189,"platform":"淘宝","pfromId":"550926419978","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=550926419978","picUrl":"http://g-search1.alicdn.com/img/bao/uploaded/i4/i1/TB1Xzx1KFXXXXclXpXXd3dC9XXX_034323.jpg","amountType":2,"amountMoney":5},{"id":"562530","title":"【12月】荷兰进口 乐荷部分脱脂200ml*24盒有机低脂高钙纯牛奶","price":189,"platform":"淘宝","pfromId":"550157933818","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=550157933818","picUrl":"http://g-search1.alicdn.com/img/bao/uploaded/i4/i2/749469635/TB2fhXJgetTMeFjSZFOXXaTiVXa_!!749469635.jpg","amountType":2,"amountMoney":5},{"id":"563586","title":"【12月】荷兰进口 乐荷部分脱脂200ml*24盒有机低脂高钙纯牛奶","price":189,"platform":"淘宝","pfromId":"550219483671","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=550219483671","picUrl":"http://g-search3.alicdn.com/img/bao/uploaded/i4/i2/749469635/TB2fhXJgetTMeFjSZFOXXaTiVXa_!!749469635.jpg","amountType":2,"amountMoney":5},{"id":"563717","title":"【乐荷有机】11月生产日期荷兰进口有机纯牛奶全脂高钙200ml*24","price":199,"platform":"淘宝","pfromId":"547407372589","detailUrl":"http://h5.m.taobao.com/awp/core/detail.htm?id=547407372589","picUrl":"http://g-search1.alicdn.com/img/bao/uploaded/i4/i2/TB1eWkbOXXXXXXiXFXXXXXXXXXX_!!0-item_pic.jpg","amountType":1,"amountMoney":5}]
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
       * id : 564152
       * title : 【12月】荷兰进口 乐荷部分脱脂200ml*24盒有机低脂高钙纯牛奶
       * price : 179
       * platform : 淘宝
       * pfromId : 551319942844
       * detailUrl : http://h5.m.taobao.com/awp/core/detail.htm?id=551319942844
       * picUrl : http://g-search1.alicdn.com/img/bao/uploaded/i4/i3/TB140RmRpXXXXc5XXXXXXXXXXXX_!!0-item_pic.jpg
       * amountType : 2
       * amountMoney : 5
       */

      private String id;
      private String title;
      private int price;
      private String platform;
      private String pfromId;
      private String detailUrl;
      private String picUrl;
      private int amountType;
      private int amountMoney;

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

      public int getAmountType() {
        return amountType;
      }

      public void setAmountType(int amountType) {
        this.amountType = amountType;
      }

      public int getAmountMoney() {
        return amountMoney;
      }

      public void setAmountMoney(int amountMoney) {
        this.amountMoney = amountMoney;
      }
    }
  }
}
