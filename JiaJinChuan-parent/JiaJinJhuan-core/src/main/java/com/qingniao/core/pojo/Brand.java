package com.qingniao.core.pojo;

import com.qingniao.core.common.Server_URL;

/**
 * 
 * 品牌pojo
 * 
 * @author Wang.YN
 *
 */
public class Brand {
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
	
	public String getimgUrl(){
		return Server_URL.IMG_SERVER+logo;
	}

	@Override
	public String toString() {
		return "Brand [id=" + id + ", name=" + name + ", description=" + description + ", logo=" + logo + ", url=" + url
				+ ", status=" + status + "]";
	}



}
