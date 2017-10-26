package com.example.lwd18.pictureselecotor.textsearch.Eventutil;

/**
 * 创建者     李文东
 * 创建时间   2017/8/3 18:26
 * 描述
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述
 */

public class Eventil {
  private String filter;
  private int position;
  public Eventil() {
  }

  public Eventil(String filter) {
   this.filter=filter;
  }

  public String getProviceName(){
    return this.filter;
  }

  public int getPosition() {
    return this.position;
  }
}
