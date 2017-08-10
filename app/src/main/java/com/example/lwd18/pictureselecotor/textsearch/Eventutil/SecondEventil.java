package com.example.lwd18.pictureselecotor.textsearch.Eventutil;

import java.util.List;

/**
 * 创建者     李文东
 * 创建时间   2017/8/3 18:26
 * 描述	      ${TODO}
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class SecondEventil {
  private List<String> filter;
  public SecondEventil() {
  }

  public SecondEventil(List<String> filter) {
   this.filter=filter;
  }

  public List<String> getFilter(){
    return this.filter;
  }

}
