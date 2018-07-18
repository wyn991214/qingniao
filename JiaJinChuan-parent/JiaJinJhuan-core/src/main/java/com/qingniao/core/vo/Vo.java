package com.qingniao.core.vo;

import java.util.List;

import com.qingniao.core.pojo.Brand;

public class Vo {
	private Brand brand;	
	private List<Brand> listBrand;
	public Brand getBrand() {
		return brand;
	}
	public void setBrand(Brand brand) {
		this.brand = brand;
	}
	public List<Brand> getListBrand() {
		return listBrand;
	}
	public void setListBrand(List<Brand> listBrand) {
		this.listBrand = listBrand;
	}
	@Override
	public String toString() {
		return "Vo [brand=" + brand + ", listBrand=" + listBrand + "]";
	}
	
}
