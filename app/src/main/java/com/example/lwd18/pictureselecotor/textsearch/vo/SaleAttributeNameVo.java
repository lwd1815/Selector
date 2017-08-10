package com.example.lwd18.pictureselecotor.textsearch.vo;

import com.example.lwd18.pictureselecotor.TextsSearchEntity;
import java.io.Serializable;
import java.util.List;

public class SaleAttributeNameVo implements Serializable {

	private String name;
	private String nameId;
	private List<SaleAttributeVo> saleVo;
	private String saleAttr;
	private boolean nameIsChecked;
	private List<TextsSearchEntity.DataBean.FiltersBean> saleVos;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getNameId() {
		return nameId;
	}

	public void setNameId(String nameId) {
		this.nameId = nameId;
	}

	public List<SaleAttributeVo> getSaleVo() {
		return saleVo;
	}
	public List<TextsSearchEntity.DataBean.FiltersBean> getSaleVos() {
		return saleVos;
	}
	public void setSaleVos(List<TextsSearchEntity.DataBean.FiltersBean> saleVos) {
		this.saleVos = saleVos;
	}
	public void setSaleVo(List<SaleAttributeVo> saleVo) {
		this.saleVo = saleVo;
	}

	public String getSaleAttr() {
		return saleAttr;
	}

	public void setSaleAttr(String saleAttr) {
		this.saleAttr = saleAttr;
	}

	public boolean isNameIsChecked() {
		return nameIsChecked;
	}

	public void setNameIsChecked(boolean nameIsChecked) {
		this.nameIsChecked = nameIsChecked;
	}

}
