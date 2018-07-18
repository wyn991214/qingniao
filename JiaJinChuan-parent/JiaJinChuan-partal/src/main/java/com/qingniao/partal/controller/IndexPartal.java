package com.qingniao.partal.controller;



import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexPartal {
	
	
	@RequestMapping("index.html")
	public String indext(){
		
	return "product/product";
	}
	

}
