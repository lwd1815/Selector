package com.example.lwd18.pictureselecotor.textsearch.model;

import java.io.Serializable;
import java.util.List;

/**
 * 创建者     李文东
 * 创建时间   2017/7/21 9:05
 * 描述	      ${TODO}
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class FilterList implements Serializable {
//filters : [{"filterName":"国产&进口","filterOption":["国产","进口","其他"]},{"filterName":"脂肪含量","filterOption":["低脂","全脂","脱脂","其他"]}]
    /**
     * filterName : 国产&进口
     * filterOption : ["国产","进口","其他"]
     */

    //private String filterName;
    //private List<String> filterOption;
  private List<Attr> attr;

  public List getAttr() {
    return attr;
  }

  public void setAttr(List attr) {
    this.attr = attr;
  }

  public static class Attr implements Serializable {
    //属性值的title
    private String filterName;
    //属性值的title对应的数据
    private List<Vals> vals;
    //设置选中的数据
    private List<Vals> SelectVals;
    //状态是否打开
    private boolean isoPen = false;
    private String showStr = "";


    public boolean isoPen() {
      return isoPen;
    }

    public void setIsoPen(boolean isoPen) {
      this.isoPen = isoPen;
    }

    public List<Vals> getSelectVals() {
      return SelectVals;
    }

    public void setSelectVals(List<Vals> selectVals) {
      SelectVals = selectVals;
    }

    public String getShowStr() {
      return showStr;
    }

    public void setShowStr(String showStr) {
      this.showStr = showStr;
    }

    public List<Vals> getVals() {
      return vals;
    }

    public void setVals(List<Vals> vals) {
      this.vals = vals;
    }

    public String getKey() {
      return filterName;
    }

    public void setKey(String filterName) {
      this.filterName = filterName;
    }


    public static class Vals implements Serializable {
      private String filterOption;
      private boolean isChick;

      public boolean isChick() {
        return isChick;
      }

      public void setChick(boolean chick) {
        isChick = chick;
      }

      public String getV() {
        return filterOption;
      }

      public void setV(String filterOption) {
        this.filterOption = filterOption;
      }
    }
  }
}
