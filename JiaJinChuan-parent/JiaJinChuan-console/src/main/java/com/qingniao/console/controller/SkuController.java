package com.qingniao.console.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.language.bm.Rule.RPattern;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.qingniao.core.pojo.product.Sku;
import com.qingniao.core.pojo.product.SkuCriteria;
import com.qingniao.core.service.SkuService;

@Controller
public class SkuController {

	@Autowired
	SkuService skuService;
	//sku显示
	@RequestMapping("/sku/list.do")
	public String skuList(Long productId, Model model) {
		SkuCriteria skuCriteria = new SkuCriteria();
		skuCriteria.createCriteria().andProductIdEqualTo(productId);
		List<Sku> skuList = skuService.selectByExample(skuCriteria);
		model.addAttribute("skus", skuList);
		return "sku/list";
	}

	// sku 修改
	@RequestMapping("/sku/update.do")
	public void skuUpdate(Sku sku, HttpServletResponse response) throws IOException {
		skuService.updateByPrimaryKeySelective(sku);
		JSONObject jo = new JSONObject();
		jo.put("message", "保存成功");
		response.getWriter().write(jo.toString());

	}

}
