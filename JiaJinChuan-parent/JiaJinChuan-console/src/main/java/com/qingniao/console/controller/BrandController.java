package com.qingniao.console.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qingniao.common.page.PageInfo;
import com.qingniao.core.dao.BrandMapper;
import com.qingniao.core.pojo.Brand;
import com.qingniao.core.pojo.BrandExample;
import com.qingniao.core.service.BrandService;

/**
 * 品牌controller
 * 
 * @author Wang.YN
 *
 */
@Controller
public class BrandController {

	@Autowired
	private BrandService brandService;

	// 进入品牌页面
	@RequestMapping("/brand/list.do")
	public String brandList(String name, Integer status, Integer pageNo, Model model) {
		BrandExample brandExample = new BrandExample();
		// 制作分页工具
		StringBuilder stringBuilder = new StringBuilder();
		if (pageNo != null) {
			brandExample.setPageNo(pageNo);
		} else {
			brandExample.setPageNo(1);// 如果是空，默认值为1；
		}

		if (name != null && name.trim().length() != 0) {

			brandExample.setName(name);
			model.addAttribute("name", name);
			stringBuilder.append("name=" + name);
		}
		if (status == null) {
			brandExample.setStatus(1);// 如果是null那么默认查询在售的商品

		} else {
			brandExample.setStatus(status);
			model.addAttribute("status", status);
			stringBuilder.append("&status=" + status);
			// 页面回显
		}

		String url = "/brand/list.do";
		PageInfo pageInfo = brandService.selectByExample(brandExample);
		pageInfo.pageView(url, stringBuilder.toString());
		model.addAttribute("pageInfo", pageInfo);
		// 回显当前页
		model.addAttribute("pageNo", pageNo);

		return "brand/list";
	}

	// 跳转到添加页面
	@RequestMapping("/brand/add.do")
	public String add() {
		return "brand/add";
	}

	// 批量删除
	@RequestMapping("/brand/batchDelete.do")
	public String batchDelete(Long[] ids, Integer pageNo, String name, Long id, Integer status, Model model) {

		if (pageNo != null) {
			model.addAttribute("pageNo", pageNo);
		}
		if (name != null) {
			model.addAttribute("name", name);
		}
		if (status != null) {
			model.addAttribute("status", status);
		}
		if (id != null) {
			ids = new Long[] { id };
		}
		brandService.batchDelect(ids);
		return "redirect:/brand/list.do";
	}

	// 添加方法
	@RequestMapping("/brand/save.do")
	public String addBrand(Brand brand) {
		brandService.insertBrand(brand);
		return "redirect:/brand/list.do";
	}

	// 查询一个brand 跳转到 /brand/edit.jsp
	@RequestMapping("/brand/editBrand.do")
	public String selectBrand(Integer id, Model model) {
		Brand brand = brandService.selectBrand(id);
		model.addAttribute(brand);
		return "brand/edit";
	}

	// 修改方法
	@RequestMapping("/brand/edit.do")
	public String updateBrand(Brand brand) {
		brandService.updateBrand(brand);
		return "redirect:/brand/list.do";
	}

}
