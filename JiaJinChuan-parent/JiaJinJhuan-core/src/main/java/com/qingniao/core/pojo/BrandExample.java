package com.qingniao.core.pojo;

import com.qingniao.core.common.Server_URL;

public class BrandExample {
	private long id;
	private String name;
	private String description;  //品牌描述
	private String logo;  //品牌logo
	private String url;  //品牌的官方网站
	private Integer status; // 1在售 2停售 3删除
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLogo() {
		return logo;
	}
	public void setLogo(String logo) {
		this.logo = logo;
	}
	public String getUrl() {
		return url;
	}
	public void setUrl(String url) {
		this.url = url;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "BrandExample [id=" + id + ", name=" + name + ", description=" + description + ", logo=" + logo
				+ ", url=" + url + ", status=" + status + "]";
	}
	
	/**
	 * 分页
	 * pageNo 	      当前页 (从页面传递数据)
	 * pageSize   每页显示多少条记录（设置每页显示十条）
	 * StartRow   起始页（通过算法算出来）
	 */
	
	private Integer pageNo;
	private Integer pageSize=5;
	private Integer startRow;
	public Integer getPageNo() {
		return pageNo;
	}
	public void setPageNo(Integer pageNo) {
		startRow = (pageNo-1)*pageSize;
		this.pageNo = pageNo;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		startRow = (pageNo-1)*pageSize;
		this.pageSize = pageSize;
	}
	public Integer getStartRow() {
		return startRow;
	}
	public void setStartRow(Integer startRow) {
		this.startRow = startRow;
	}
	
	



}
