package com.example.lwd18.pictureselecotor.textsearch.utils;

import com.example.lwd18.pictureselecotor.TextsSearchEntity;
import com.example.lwd18.pictureselecotor.textsearch.vo.SaleAttributeVo;
import java.util.List;

/**
 * 创建者     李文东
 * 创建时间   2017/7/7 16:16
 * 描述	      ${TODO}
 * 更新者     $Author$
 * 更新时间   $Date$
 * 更新描述   ${TODO}
 */

public class FilterUtils {
  public static int getIndex(List<TextsSearchEntity.DataBean.FiltersBean> filterlist,
      int position) {
    int index = position;
    if (filterlist.size() == 1) {
      index = position - 1;
    } else if (filterlist.size() == 2) {
      if (position < 6) {
        index = position - 1;
      } else {
        index = position - 2;
      }
    } else if (filterlist.size() == 3) {
      if (position < 6) {
        index = position - 1;
      } else if (position > 6 && position < 12) {
        index = position - 2;
      } else {
        index = position - 3;
      }
    } else if (filterlist.size() == 4) {
      if (position < 6) {
        index = position - 1;
      } else if (position > 6 && position < 12) {
        index = position - 2;
      } else if (position > 12 && position < 18) {
        index = position - 3;
      } else {
        index = position - 4;
      }
    } else if (filterlist.size() == 5) {
      if (position < 6) {
        index = position - 1;
      } else if (position > 6 && position < 12) {
        index = position - 2;
      } else if (position > 12 && position < 18) {
        index = position - 3;
      } else if (position > 18 && position < 24) {
        index = position - 4;
      } else {
        index = position - 5;
      }
    } else if (filterlist.size() == 6) {
      if (position < 6) {
        index = position - 1;
      } else if (position > 6 && position < 12) {
        index = position - 2;
      } else if (position > 12 && position < 18) {
        index = position - 3;
      } else if (position > 18 && position < 24) {
        index = position - 4;
      } else if (position > 24 && position < 30) {
        index = position - 5;
      } else {
        index = position - 6;
      }
    } else if (filterlist.size() == 7) {
      if (position < 6) {
        index = position - 1;
      } else if (position > 6 && position < 12) {
        index = position - 2;
      } else if (position > 12 && position < 18) {
        index = position - 3;
      } else if (position > 18 && position < 24) {
        index = position - 4;
      } else if (position > 24 && position < 30) {
        index = position - 5;
      } else if (position > 30 && position < 36) {
        index = position - 6;
      } else {
        index = position - 7;
      }
    } else if (filterlist.size() == 8) {
      if (position < 6) {
        index = position - 1;
      } else if (position > 6 && position < 12) {
        index = position - 2;
      } else if (position > 12 && position < 18) {
        index = position - 3;
      } else if (position > 18 && position < 24) {
        index = position - 4;
      } else if (position > 24 && position < 30) {
        index = position - 5;
      } else if (position > 30 && position < 36) {
        index = position - 6;
      } else if (position > 36 && position < 42) {
        index = position - 7;
      } else {
        index = position - 8;
      }
    } else if (filterlist.size() == 9) {
      if (position < 6) {
        index = position - 1;
      } else if (position > 6 && position < 12) {
        index = position - 2;
      } else if (position > 12 && position < 18) {
        index = position - 3;
      } else if (position > 18 && position < 24) {
        index = position - 4;
      } else if (position > 24 && position < 30) {
        index = position - 5;
      } else if (position > 30 && position < 36) {
        index = position - 6;
      } else if (position > 36 && position < 42) {
        index = position - 7;
      } else if (position > 42 && position < 48) {
        index = position - 8;
      } else {
        index = position - 9;
      }
    } else if (filterlist.size() == 10) {
      if (position < 6) {
        index = position - 1;
      } else if (position > 6 && position < 12) {
        index = position - 2;
      } else if (position > 12 && position < 18) {
        index = position - 3;
      } else if (position > 18 && position < 24) {
        index = position - 4;
      } else if (position > 24 && position < 30) {
        index = position - 5;
      } else if (position > 30 && position < 36) {
        index = position - 6;
      } else if (position > 36 && position < 42) {
        index = position - 7;
      } else if (position > 42 && position < 48) {
        index = position - 8;
      } else if (position > 48 && position < 54) {
        index = position - 9;
      } else {
        index = position - 10;
      }
    }
    return index;
  }

  public static int getSize(List<TextsSearchEntity.DataBean.ItemsBean> list) {
    if (list.size() > 0 && list.size() < 5) {
    } else if (list.size() < 10 && list.size() >= 5) {
      return list.size() + 2;
    } else if (list.size() >= 10 && list.size() < 15) {
      return list.size() + 3;
    } else if (list.size() >= 15 && list.size() < 20) {
      return list.size() + 4;
    } else if (list.size() >= 20 && list.size() < 25) {
      return list.size() + 5;
    } else if (list.size() >= 25 && list.size() < 30) {
      return list.size() + 6;
    } else if (list.size() >= 30 && list.size() < 35) {
      return list.size() + 7;
    } else if (list.size() >= 35 && list.size() < 40) {
      return list.size() + 8;
    } else if (list.size() >= 40 && list.size() < 45) {
      return list.size() + 9;
    } else if (list.size() >= 45 && list.size() < 50) {
      return list.size() + 10;
    } else {
      return list.size() + 11;
    }
    return 0;
  }

  //public static void setVisibile(RecyclerView.ViewHolder holder, int newpositions,
  //    List<TextsSearchEntity.DataBean.FiltersBean> filterlist, final List<String> filters) {
  //  filters.clear();
  //  filters.addAll(filterlist.get(newpositions).getFilterOption());
  //  if (filters.size() >= 5) {
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).five.setVisibility(View.VISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).four.setVisibility(View.VISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).thrid.setVisibility(View.VISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).second.setVisibility(View.VISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).first.setVisibility(View.VISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).first.setText(filters.get(0));
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).second.setText(filters.get(1));
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).thrid.setText(filters.get(2));
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).four.setText(filters.get(3));
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).five.setText("更多");
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
  //      @Override public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
  //        switch (checkedId){
  //          case R.id.first:
  //            EventBus.getDefault().post(new TextEventUtil(filters.get(0)));
  //            break;
  //          case R.id.second:
  //            EventBus.getDefault().post(new TextEventUtil(filters.get(1)));
  //            break;
  //          case R.id.thrid:
  //            EventBus.getDefault().post(new TextEventUtil(filters.get(2)));
  //            break;
  //          case R.id.four:
  //            EventBus.getDefault().post(new TextEventUtil(filters.get(3)));
  //            break;
  //          case R.id.five:
  //            EventBus.getDefault().post(new TextEventUtil(""));
  //            break;
  //        }
  //      }
  //    });
  //    //((ComprehensiveAdapter.HeadViewHolder) holder).first.setOnClickListener(new View.OnClickListener() {
  //    //  @Override public void onClick(View v) {
  //    //    EventBus.getDefault().post(new TextEventUtil(filters.get(0)));
  //    //  }
  //    //});
  //    //((ComprehensiveAdapter.HeadViewHolder) holder).second.setOnClickListener(new View.OnClickListener() {
  //    //  @Override public void onClick(View v) {
  //    //    EventBus.getDefault().post(new TextEventUtil(filters.get(1)));
  //    //  }
  //    //});
  //    //((ComprehensiveAdapter.HeadViewHolder) holder).thrid.setOnClickListener(new View.OnClickListener() {
  //    //  @Override public void onClick(View v) {
  //    //    EventBus.getDefault().post(new TextEventUtil(filters.get(2)));
  //    //  }
  //    //});
  //    //((ComprehensiveAdapter.HeadViewHolder) holder).four.setOnClickListener(new View.OnClickListener() {
  //    //  @Override public void onClick(View v) {
  //    //    EventBus.getDefault().post(new TextEventUtil(filters.get(3)));
  //    //  }
  //    //});
  //    //((ComprehensiveAdapter.HeadViewHolder) holder).five.setOnClickListener(new View.OnClickListener() {
  //    //  @Override public void onClick(View v) {
  //    //    EventBus.getDefault().post(new TextEventUtil(""));
  //    //
  //    //  }
  //    //});
  //  } else if (filters.size() >= 4 && filters.size() < 5) {
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).five.setVisibility(View.INVISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).four.setVisibility(View.VISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).thrid.setVisibility(View.VISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).second.setVisibility(View.VISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).first.setVisibility(View.VISIBLE);
  //
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).first.setText(filters.get(0));
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).second.setText(filters.get(1));
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).thrid.setText(filters.get(2));
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).four.setText(filters.get(3));
  //
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
  //      @Override public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
  //        switch (checkedId){
  //          case R.id.first:
  //            EventBus.getDefault().post(new TextEventUtil(filters.get(0)));
  //            break;
  //          case R.id.second:
  //            EventBus.getDefault().post(new TextEventUtil(filters.get(1)));
  //            break;
  //          case R.id.thrid:
  //            EventBus.getDefault().post(new TextEventUtil(filters.get(2)));
  //            break;
  //          case R.id.four:
  //            EventBus.getDefault().post(new TextEventUtil(filters.get(3)));
  //            break;
  //        }
  //      }
  //    });
  //    //((ComprehensiveAdapter.HeadViewHolder) holder).first.setOnClickListener(new View.OnClickListener() {
  //    //  @Override public void onClick(View v) {
  //    //    EventBus.getDefault().post(new TextEventUtil(filters.get(0)));
  //    //  }
  //    //});
  //    //((ComprehensiveAdapter.HeadViewHolder) holder).second.setOnClickListener(new View.OnClickListener() {
  //    //  @Override public void onClick(View v) {
  //    //    EventBus.getDefault().post(new TextEventUtil(filters.get(1)));
  //    //  }
  //    //});
  //    //((ComprehensiveAdapter.HeadViewHolder) holder).thrid.setOnClickListener(new View.OnClickListener() {
  //    //  @Override public void onClick(View v) {
  //    //    EventBus.getDefault().post(new TextEventUtil(filters.get(2)));
  //    //  }
  //    //});
  //    //((ComprehensiveAdapter.HeadViewHolder) holder).four.setOnClickListener(new View.OnClickListener() {
  //    //  @Override public void onClick(View v) {
  //    //    EventBus.getDefault().post(new TextEventUtil(filters.get(3)));
  //    //  }
  //    //});
  //
  //  } else if (filters.size() >= 3 && filters.size() < 4) {
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).five.setVisibility(View.INVISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).four.setVisibility(View.INVISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).thrid.setVisibility(View.VISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).second.setVisibility(View.VISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).first.setVisibility(View.VISIBLE);
  //
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).first.setText(filters.get(0));
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).second.setText(filters.get(1));
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).thrid.setText(filters.get(2));
  //
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
  //      @Override public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
  //        switch (checkedId){
  //          case R.id.first:
  //            EventBus.getDefault().post(new TextEventUtil(filters.get(0)));
  //            break;
  //          case R.id.second:
  //            EventBus.getDefault().post(new TextEventUtil(filters.get(1)));
  //            break;
  //          case R.id.thrid:
  //            EventBus.getDefault().post(new TextEventUtil(filters.get(2)));
  //            break;
  //        }
  //      }
  //    });
  //    //((ComprehensiveAdapter.HeadViewHolder) holder).first.setOnClickListener(new View.OnClickListener() {
  //    //  @Override public void onClick(View v) {
  //    //    EventBus.getDefault().post(new TextEventUtil(filters.get(0)));
  //    //  }
  //    //});
  //    //((ComprehensiveAdapter.HeadViewHolder) holder).second.setOnClickListener(new View.OnClickListener() {
  //    //  @Override public void onClick(View v) {
  //    //    EventBus.getDefault().post(new TextEventUtil(filters.get(1)));
  //    //  }
  //    //});
  //    //((ComprehensiveAdapter.HeadViewHolder) holder).thrid.setOnClickListener(new View.OnClickListener() {
  //    //  @Override public void onClick(View v) {
  //    //    EventBus.getDefault().post(new TextEventUtil(filters.get(2)));
  //    //  }
  //    //});
  //
  //  } else if (filters.size() >= 2 && filters.size() < 3) {
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).five.setVisibility(View.INVISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).four.setVisibility(View.INVISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).thrid.setVisibility(View.INVISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).second.setVisibility(View.VISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).first.setVisibility(View.VISIBLE);
  //
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).first.setText(filters.get(0));
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).second.setText(filters.get(1));
  //
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
  //      @Override public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
  //        switch (checkedId){
  //          case R.id.first:
  //            EventBus.getDefault().post(new TextEventUtil(filters.get(0)));
  //            break;
  //          case R.id.second:
  //            EventBus.getDefault().post(new TextEventUtil(filters.get(1)));
  //            break;
  //        }
  //      }
  //    });
  //    //((ComprehensiveAdapter.HeadViewHolder) holder).first.setOnClickListener(new View.OnClickListener() {
  //    //  @Override public void onClick(View v) {
  //    //    EventBus.getDefault().post(new TextEventUtil(filters.get(0)));
  //    //  }
  //    //});
  //    //((ComprehensiveAdapter.HeadViewHolder) holder).second.setOnClickListener(new View.OnClickListener() {
  //    //  @Override public void onClick(View v) {
  //    //    EventBus.getDefault().post(new TextEventUtil(filters.get(1)));
  //    //  }
  //    //});
  //
  //  } else if (filters.size() >= 1 && filters.size() < 2) {
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).five.setVisibility(View.INVISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).four.setVisibility(View.INVISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).thrid.setVisibility(View.INVISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).second.setVisibility(View.INVISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).first.setVisibility(View.VISIBLE);
  //
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).first.setText(filters.get(0));
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).rg.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
  //      @Override public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
  //        switch (checkedId){
  //          case R.id.first:
  //            EventBus.getDefault().post(new TextEventUtil(filters.get(0)));
  //            break;
  //        }
  //      }
  //    });
  //    //
  //    //((ComprehensiveAdapter.HeadViewHolder) holder).first.setOnClickListener(new View.OnClickListener() {
  //    //  @Override public void onClick(View v) {
  //    //    EventBus.getDefault().post(new TextEventUtil(filters.get(0)));
  //    //  }
  //    //});
  //  } else if (filters.size() < 1) {
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).five.setVisibility(View.INVISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).four.setVisibility(View.INVISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).thrid.setVisibility(View.INVISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).second.setVisibility(View.INVISIBLE);
  //    ((ComprehensiveAdapter.HeadViewHolder) holder).first.setVisibility(View.INVISIBLE);
  //  }
  //}

  public static void setNumber(List<SaleAttributeVo> tempData, List<SaleAttributeVo> data) {
    if (tempData.size() == 1) {
      data.add(tempData.get(0));
    } else if (tempData.size() == 2) {
      data.add(tempData.get(0));
      data.add(tempData.get(1));
    } else if (tempData.size() == 3) {
      data.add(tempData.get(0));
      data.add(tempData.get(1));
      data.add(tempData.get(2));
    } else if (tempData.size() == 4) {
      data.add(tempData.get(0));
      data.add(tempData.get(1));
      data.add(tempData.get(2));
      data.add(tempData.get(3));
    } else if (tempData.size() == 5) {
      data.add(tempData.get(0));
      data.add(tempData.get(1));
      data.add(tempData.get(2));
      data.add(tempData.get(3));
      data.add(tempData.get(4));
    } else if (tempData.size() == 6) {
      data.add(tempData.get(0));
      data.add(tempData.get(1));
      data.add(tempData.get(2));
      data.add(tempData.get(3));
      data.add(tempData.get(4));
      data.add(tempData.get(5));
    } else {
      data.add(tempData.get(0));
      data.add(tempData.get(1));
      data.add(tempData.get(2));
      data.add(tempData.get(3));
      data.add(tempData.get(4));
      data.add(tempData.get(5));
    }
  }
  public static void setNumberFilter(List<String> tempData, List<String> data) {
    if (tempData.size() == 1) {
      data.add(tempData.get(0));
    } else if (tempData.size() == 2) {
      data.add(tempData.get(0));
      data.add(tempData.get(1));
    } else if (tempData.size() == 3) {
      data.add(tempData.get(0));
      data.add(tempData.get(1));
      data.add(tempData.get(2));
    } else if (tempData.size() == 4) {
      data.add(tempData.get(0));
      data.add(tempData.get(1));
      data.add(tempData.get(2));
      data.add(tempData.get(3));
    } else if (tempData.size() == 5) {
      data.add(tempData.get(0));
      data.add(tempData.get(1));
      data.add(tempData.get(2));
      data.add(tempData.get(3));
      data.add(tempData.get(4));
    } else if (tempData.size() == 6) {
      data.add(tempData.get(0));
      data.add(tempData.get(1));
      data.add(tempData.get(2));
      data.add(tempData.get(3));
      data.add(tempData.get(4));
      data.add(tempData.get(5));
    } else {
      data.add(tempData.get(0));
      data.add(tempData.get(1));
      data.add(tempData.get(2));
      data.add(tempData.get(3));
      data.add(tempData.get(4));
      data.add(tempData.get(5));
    }
  }
}
