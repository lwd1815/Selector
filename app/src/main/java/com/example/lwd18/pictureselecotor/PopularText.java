package com.example.lwd18.pictureselecotor;

import android.support.annotation.Keep;
import java.util.List;

/**
 * Created by EtherealPatrick on 2017/4/17.
 */
@Keep
public class PopularText {

  /**
   * id : -1708299264
   * updateTime : 24555017
   * state : 0
   * stateMsg : 成功
   * data : {"popularText":"纯牛奶","texts":["人字拖","大裤衩","花衬衫","黑色","薄外套","春季新款"]}
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
     * popularText : 纯牛奶
     * texts : ["人字拖","大裤衩","花衬衫","黑色","薄外套","春季新款"]
     */

    private String popularText;
    private List<String> texts;

    public String getPopularText() {
      return popularText;
    }

    public void setPopularText(String popularText) {
      this.popularText = popularText;
    }

    public List<String> getTexts() {
      return texts;
    }

    public void setTexts(List<String> texts) {
      this.texts = texts;
    }
  }
}
