package com.example.lwd18.pictureselecotor.textsearch.vo;

import java.io.Serializable;

public class SaleAttributeVo implements Serializable {

	@Override
	public String toString() {
		return "SaleAttributeVo [goods=" + goods + ", value=" + value
				+ ", goodsAndValId=" + goodsAndValId + "]";
	}

	private String goods;
	private String value;
	private String goodsAndValId;
	private boolean isChecked;

	public String getGoodsAndValId() {
		return goodsAndValId;
	}

	public void setGoodsAndValId(String goodsAndValId) {
		this.goodsAndValId = goodsAndValId;
	}

	public String getGoods() {
		return goods;
	}

	public void setGoods(String goods) {
		this.goods = goods;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public boolean isChecked() {
		return isChecked;
	}

	public void setChecked(boolean isChecked) {
		this.isChecked = isChecked;
	}

}
