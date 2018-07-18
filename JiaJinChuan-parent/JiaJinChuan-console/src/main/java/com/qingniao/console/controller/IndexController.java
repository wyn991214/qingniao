package com.qingniao.console.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qingniao.core.pojo.Brand;
import com.qingniao.core.service.BrandService;
import com.qingniao.core.vo.Vo;

@Controller
public class IndexController {
	
	@Autowired
	private BrandService brandService;

	public IndexController(){
		System.out.println("00000");
	}
	@RequestMapping("/index.do")
	public String index(){
		return "index";
	}
	@RequestMapping("/top.do")
	public String top(){
		return "top";
	}
	@RequestMapping("/main.do")
	public String main(){
		return "main";
	}
	/**
	 * 加载首页的左面的菜单
	 * @return
	 */
	@RequestMapping("/left.do")
	public String left(){
		return "left";
	}
	/**
	 * 加载首页的右面的菜单
	 * @return
	 */
	@RequestMapping("/right.do")
	public String right(){
		return "right";
	}
	
	/**
	 * 跳转商品页面
	 */
	@RequestMapping("/productMain.do")
	public String productMain(){
		return "product/product_main";
	}
	/**
	 * 加载商品列
	 */
	@RequestMapping("/productLeft.do")
	public String productLrft(){
		return "product/product_left";
	}



	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}
