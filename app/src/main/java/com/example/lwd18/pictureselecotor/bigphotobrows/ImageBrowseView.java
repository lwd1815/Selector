package com.example.lwd18.pictureselecotor.bigphotobrows;

import android.content.Context;
import android.content.Intent;
import java.util.List;

/**
 * 创建者     李文东
 * 创建时间   2017/5/24 9:45
 * 描述	      ${TODO}
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */
public interface ImageBrowseView {

    Intent getDataIntent();

    void setImageBrowse(List<String> images, int position);

    Context getMyContext();
}
