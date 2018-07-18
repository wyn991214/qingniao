<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<html>
<head>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expijsutils" content="0">    
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">


<title>青鸟运动购物平台-商品详情页</title>
<link rel="stylesheet" href="/jsutils/css/style.css" />
<script src="/jsutils/js/jquery.js"></script>
<script type="text/javascript" src="/jsutils/js/com.js"></script>
<style type="text/css">
.changToRed {
	border: 2px solid #e4393c;
	padding: 2px 4px;
	float: left;
	margin-right: 4px;
	text-decoration: none;
}
.changToWhite {
	border: 1px solid #ccc;
	padding: 2px 4px;
	float: left;
	margin-right: 4px;
	text-decoration: none;
}
.not-allow {
	cursor: not-allowed;
	color: #999;
	border: 1px dashed #ccc;
	padding: 2px 4px;
	float: left;
	margin-right: 4px;
	text-decoration: none;
}
</style>
</head>
<script type="text/javascript">

//颜色
var gpriceId;
var gskuId;
var gupperLimit;

//变换颜色
function colorToRed(obj,cid) {
	gpriceId = cid;	//保存价格到全局变量
	//清理所有颜色
	$("#colors a").each(function(){
		$(this).attr("class","changToWhite");
	});
	
	//清理所有尺码
	$("#sizes a").each(function(){
		$(this).attr("class","not-allow");
	});
	
	$(obj).attr("class","changToRed");
	
	
	//第一次循环的时候选中第一个尺码
	var flag = 1;
	//选中颜色对应的尺码 sku结果集里面
	<c:forEach items="${skus}" var="sku" >
		if(cid == '${sku.colorId}') {
			if(flag == 1) {
				$("#"+'${sku.size}').attr("class","changToRed");
				//设置价格
				$("#pprice").html("Y" + '${sku.price}');
				//市场价
				$("#pmarketprice").html("Y" + '${sku.marketPrice}');
				//运费
				$("#pdeliveFee").html('${sku.deliveFee}');
				//库存
				$("#pstock").html('${sku.stock}');
				//为了后面的购买商品保存skuId
				gskuId = '${sku.id}';
				// 保存购买限制
				gupperLimit = '${sku.upperLimit}';
				flag = 2;
			}else {
			$("#"+'${sku.size}').attr("class","changToWhite");
			}
		}
	</c:forEach>
	
}

// 选择尺码之前必须得选择颜色
function changtoSize(obj,s) {
	//如果是点击的不可选择的尺码那么就推出执行方法
	var ocls = $(obj).attr("class");
	if(ocls == "not-allow") {
		return;
	}
	
	//清理尺码
 	$("#sizes a").each(function(){
 		var cls = $(this).attr("class");
 		if(cls == "changToRed") {
 			$(this).attr("class","changToWhite");
 		}
	}); 
	
	$(obj).attr("class","changToRed");
	
	//显示价格 市场价 运费 库存等信息
	<c:forEach items="${skus}" var="sku" >
	if(s == '${sku.size}' && gpriceId == '${sku.colorId}') {
		//设置价格
		$("#pprice").html("Y" + '${sku.price}');
		//市场价
		$("#pmarketprice").html("Y" + '${sku.marketPrice}');
		//运费
		$("#pdeliveFee").html('${sku.deliveFee}');
		//库存
		$("#pstock").html('${sku.stock}');
		
		//为了后面的购买商品保存skuId
		gskuId = '${sku.id}';
	}
	</c:forEach>
}


//页面加减按钮
$(function(){
	//页面加载时、触发第一个颜色的onclick事件
	$("#colors a:first").trigger("click");
	
	// + 绑定事件
	$("#add").click(function(){
		var num = $("#num").val();
		num++;
		if(num>gupperLimit) {
			alert("超过了购买限制");
			return;
		}
		//更新购买数量
		$("#num").val(num);
		
	});
	
	// - 绑定事件
	$("#sub").click(function(){
		var num = $("#num").val();
		num--;
		if(num == 0) {
			alert("至少购买一件");
			return;
		}
		$("#num").val(num);
	})
});

//加入购物车
function addCart(){
	alert("添加购物车成功!");
}

//立即购买
function buy(){
	window.location.href='/shopping/buyerCart.html?skuId='+gskuId+"&amount=" + $("#num").val();
}
</script>
</head>
<body>
<div class="bar"><div class="bar_w">

	
	<p class="l">
		<span class="l">
			收藏本网站！北京<a href="#" title="更换">[更换]</a>
		</span>
	</p>
	<ul class="r uls">
		<li class="dev">
			您好,欢迎来到青鸟运动购物平台！
		</li>
	<li class="dev"><a href="javascript:void(0)" onclick="login()"  title="登陆">[登陆]</a></li>
	<li class="dev"><a href="javascript:void(0)" onclick="register()" title="免费注册">[免费注册]</a></li>
	<li class="dev"><a href="javascript:void(0)" onclick="logout()" title="退出">[退出]</a></li>
	<li class="dev"><a href="javascript:void(0)" onclick="myOrder()" title="我的订单">我的订单</a></li>
	<li class="dev"><a href="#" title="在线客服">在线客服</a></li>
	<li class="dev after"><a href="#" title="English">English</a></li>
	</ul>
</div></div>
<div class="w loc">
	<div class="h-title">
		<div class="h-logo"><a href="http://localhost:8080"><img src="/jsutils/img/pic/logo-3.png" /></a></div>
	    <div class="h-search">
	      	<input type="text" />
	        <div class="h-se-btn"><a href="#">搜索</a></div>
	    </div>
	</div>
	<dl id="cart" class="cart r">
		<dt><a href="#" title="结算">结算</a>购物车:<b id="">123</b>件</dt>
		<dd class="hidden">
			<p class="alg_c hidden">购物车中还没有商品，赶紧选购吧！</p>
			<h3 title="最新加入的商品">最新加入的商品</h3>
			<ul class="uls">
				<li>
					<a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">
					<img src="/jsutils/img/pic/p50x50.jpg" alt="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元" /></a>
					<p class="dt"><a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元</a></p>
					<p class="dd">
						<b><var>¥128</var><span>x1</span></b>
						<a href="javascript:void(0);" title="删除" class="del">删除</a>
					</p>
				</li>
				<li>
					<a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">
					<img src="/jsutils/img/pic/p50x50.jpg" alt="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元" /></a>
					<p class="dt"><a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元</a></p>
					<p class="dd">
						<b><var>¥128</var><span>x1</span></b>
						<a href="javascript:void(0);" title="删除" class="del">删除</a>
					</p>
				</li>
				<li>
					<a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">
					<img src="/jsutils/img/pic/p50x50.jpg" alt="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元" /></a>
					<p class="dt"><a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元</a></p>
					<p class="dd">
						<b><var>¥128</var><span>x1</span></b>
						<a href="javascript:void(0);" title="删除" class="del">删除</a>
					</p>
				</li>
				<li>
					<a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">
					<img src="/jsutils/img/pic/p50x50.jpg" alt="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元" /></a>
					<p class="dt"><a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元</a></p>
					<p class="dd">
						<b><var>¥128</var><span>x1</span></b>
						<a href="javascript:void(0);" title="删除" class="del">删除</a>
					</p>
				</li>
				<li>
					<a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">
					<img src="/jsutils/img/pic/p50x50.jpg" alt="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元" /></a>
					<p class="dt"><a href="#" title="依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元">依琦莲2014瑜伽服套装新 瑜珈健身服三件套 广场舞蹈服装 女瑜伽服送胸垫 长袖紫色 M全场支持货到付款 全网最低价 千人超高好评瑜伽服赶紧抢！全五分好评截图联系客服还返现五元</a></p>
					<p class="dd">
						<b><var>¥128</var><span>x1</span></b>
						<a href="javascript:void(0);" title="删除" class="del">删除</a>
					</p>
				</li>
			</ul>
			<div>
				<p>共<b>5</b>件商品&nbsp;&nbsp;&nbsp;&nbsp;共计<b class="f20">¥640.00</b></p>
				<a href="#" title="去购物车结算" class="inb btn120x30c">去购物车结算</a>
			</div>
		</dd>
	</dl>
</div>

<div class="w ofc mt">
	<div class="l">
		<div class="showPro">
			<div class="big"><a id="showImg" class="cloud-zoom" href="${product.img.realUrl }" rel="adjustX:10,adjustY:-1"><img alt="" src="${product.img.realUrl }"></a></div>
		</div>
	</div>
	<div class="r" style="width: 640px">
		<ul class="uls form">
			<li><h2>${product.name }</h2></li>
			<li><label>价 格：</label><span class="word"><b class="f14 red mr" id="pprice" >￥128.00</b>(市场价:<del id="pmarketprice" >￥150.00</del>)</span></li>
			<li><label>商品评价：</label><span class="word"><span class="val_no val3d4" title="4分">4分</span><var class="blue">(已有888人评价)</var></span></li>
			<li><label>运　　费：</label><span class="word" id="pdeliveFee" >10元</span></li>
			<li><label>库　　存：</label><span class="word" id="pstock">100</span><span class="word" >件</span></li>
			<li><label>选择颜色：</label>
				<div id="colors" class="pre spec">
					<c:forEach items="${colors}" var="color" >
					<a onclick="colorToRed(this,'${color.id}')" href="javascript:void(0)" title="${color.name }" class="changToWhite"><img width="25" height="25" data-img="1" src="/jsutils/img/pic/ppp00.jpg" alt="${color.name } "><i>${color.name }</i></a>
					</c:forEach>
					
				</div>
			</li>
			<li id="sizes"><label>尺　　码：</label>
						<a href="javascript:void(0)" class="not-allow" onclick="changtoSize(this,'S');" id="S">S</a>
						<a href="javascript:void(0)" class="not-allow" onclick="changtoSize(this,'M');" id="M">M</a>
						<a href="javascript:void(0)" class="not-allow" onclick="changtoSize(this,'L');" id="L">L</a>
						<a href="javascript:void(0)" class="not-allow" onclick="changtoSize(this,'XL');" id="XL">XL</a>
						<a href="javascript:void(0)" class="not-allow" onclick="changtoSize(this,'XXL');" id="XXL">XXL</a>
			</li>
			<li><label>我 要 买：</label>
				<a id="sub" class="inb arr" style="border: 1px solid #919191;width: 10px;height: 10px;line-height: 10px;text-align: center;" title="减" href="javascript:void(0);" >-</a>
				<input id="num" type="text" value="1" name="" size="1" readonly="readonly">
				<a id="add" class="inb arr" style="border: 1px solid #919191;width: 10px;height: 10px;line-height: 10px;text-align: center;" title="加" href="javascript:void(0);">+</a></li>
			<li class="submit"><input type="button" value="" class="hand btn138x40" onclick="buy();"/><input type="button" value="" class="hand btn138x40b" onclick="addCart()"/></li>
		</ul>
	</div>
</div>
<div class="w ofc mt">
<div class="l wl">
	<h2 class="h2 h2_l mt"><em title="销量排行榜">销量排行榜</em><cite></cite></h2>
	<div class="box bg_white">
		<ul class="uls x_50x50">
			<li>
				<var class="sfont">1</var>
				<a href="#" title="富一代" target="_blank" class="pic"><img src="/jsutils/img/pic/ppp.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
				<dl>
					<!-- dt 8个文字+... -->
					<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
					<dd class="orange">￥120 ~ ￥150</dd>
				</dl>
			</li>
			<li>
				<var class="sfont">2</var>
				<a href="#" title="富一代" target="_blank" class="pic"><img src="/jsutils/img/pic/ppp1.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
				<dl>
					<!-- dt 8个文字+... -->
					<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
					<dd class="orange">￥120 ~ ￥150</dd>
				</dl>
			</li>
			<li>
				<var class="sfont">3</var>
				<a href="#" title="富一代" target="_blank" class="pic"><img src="/jsutils/img/pic/ppp2.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
				<dl>
					<!-- dt 8个文字+... -->
					<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
					<dd class="orange">￥120 ~ ￥150</dd>
				</dl>
			</li>
			<li>
				<a href="#" title="富一代" target="_blank" class="pic"><img src="/jsutils/img/pic/ppp3.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
				<dl>
					<!-- dt 8个文字+... -->
					<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
					<dd class="orange">￥120 ~ ￥150</dd>
				</dl>
			</li>
			<li>
				<a href="#" title="富一代" target="_blank" class="pic"><img src="/jsutils/img/pic/ppp4.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
				<dl>
					<!-- dt 8个文字+... -->
					<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
					<dd class="orange">￥120 ~ ￥150</dd>
				</dl>
			</li>
			<li>
				<a href="#" title="富一代" target="_blank" class="pic"><img src="/jsutils/img/pic/ppp5.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
				<dl>
					<!-- dt 8个文字+... -->
					<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
					<dd class="orange">￥120 ~ ￥150</dd>
				</dl>
			</li>
			<li>
				<a href="#" title="富一代" target="_blank" class="pic"><img src="/jsutils/img/pic/ppp6.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
				<dl>
					<!-- dt 8个文字+... -->
					<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
					<dd class="orange">￥120 ~ ￥150</dd>
				</dl>
			</li>
		</ul>
	</div>
	<h2 class="h2 h2_l mt"><em title="我的浏览记录">我的浏览记录</em><cite></cite></h2>
	<div class="box bg_white">
		<ul class="uls x_50x50">
			<li>
				<a href="#" title="富一代" target="_blank" class="pic"><img src="/jsutils/img/pic/ppp.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
				<dl>
					<!-- dt 8个文字+... -->
					<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
					<dd class="orange">￥120 ~ ￥150</dd>
				</dl>
			</li>
			<li>
				<a href="#" title="富一代" target="_blank" class="pic"><img src="/jsutils/img/pic/ppp1.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
				<dl>
					<!-- dt 8个文字+... -->
					<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
					<dd class="orange">￥120 ~ ￥150</dd>
				</dl>
			</li>
			<li>
				<a href="#" title="富一代" target="_blank" class="pic"><img src="/jsutils/img/pic/ppp2.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
				<dl>
					<!-- dt 8个文字+... -->
					<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
					<dd class="orange">￥120 ~ ￥150</dd>
				</dl>
			</li>
			<li>
				<a href="#" title="富一代" target="_blank" class="pic"><img src="/jsutils/img/pic/ppp3.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
				<dl>
					<!-- dt 8个文字+... -->
					<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
					<dd class="orange">￥120 ~ ￥150</dd>
				</dl>
			</li>
			<li>
				<a href="#" title="富一代" target="_blank" class="pic"><img src="/jsutils/img/pic/ppp4.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
				<dl>
					<!-- dt 8个文字+... -->
					<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
					<dd class="orange">￥120 ~ ￥150</dd>
				</dl>
			</li>
			<li>
				<a href="#" title="富一代" target="_blank" class="pic"><img src="/jsutils/img/pic/ppp5.jpg" alt="依琦莲2014瑜伽服套装新" /></a>
				<dl>
					<!-- dt 8个文字+... -->
					<dt><a href="#" title="依琦莲2014瑜伽服套装新" target="_blank">依琦莲2014瑜伽服套装新</a></dt>
					<dd class="orange">￥120 ~ ￥150</dd>
				</dl>
			</li>
		</ul>
	</div>
	
	<h2 class="h2 h2_l mt"><em title="商家精选">商家精选</em><cite></cite></h2>
	<img src="/jsutils/img/pic/ad200x75.jpg" alt="" />
</div>
<div class="r wr">
		<h2 class="h2 h2_ch mt"><em>
			<a href="javascript:void(0);" title="商品介绍" rel="#detailTab1" class="here">商品介绍</a>
			<a href="javascript:void(0);" title="规格参数" rel="#detailTab2">规格参数</a>
			<a href="javascript:void(0);" title="包装清单" rel="#detailTab3">包装清单</a></em><cite></cite></h2>
		<div class="box bg_white ofc">
			<div id="detailTab1" class="detail">
				<img src="/jsutils/img/pic/p800b.jpg" /><img src="/jsutils/img/pic/p800a.jpg" /><img src="/jsutils/img/pic/p800c.jpg" /><img src="/jsutils/img/pic/p800d.jpg" />
			</div>
			
			<div id="detailTab2" style="display:none">
				<strong>服务承诺：</strong><br>
	京东向您保证所售商品均为正品行货，京东自营商品开具机打发票或电子发票。凭质保证书及京东发票，可享受全国联保服务（奢侈品、钟表除外；奢侈品、钟表由京东联系保修，享受法定三包售后服务），与您亲临商场选购的商品享受相同的质量保证。京东还为您提供具有竞争力的商品价格和<a target="_blank" href="http://www.jd.com/help/kdexpjsutilss.aspx">运费政策</a>，请您放心购买！
	<br><br>
	注：因厂家会在没有任何提前通知的情况下更改产品包装、产地或者一些附件，本司不能确保客户收到的货物与商城图片、产地、附件说明完全一致。只能确保为原厂正货！并且保证与当时市场上同样主流新品一致。若本商城没有及时更新，请大家谅解！ <br/>
		<strong>权利声明：</strong><br>京东上的所有商品信息、客户评价、商品咨询、网友讨论等内容，是京东重要的经营资源，未经许可，禁止非法转载使用。
	<p><b>注：</b>本站商品信息均来自于合作方，其真实性、准确性和合法性由信息拥有者（合作方）负责。本站不提供任何保证，并不承担任何法律责任。</p>	
				
			</div>

			<div id="detailTab3" class="detail" style="display:none">

	<pre class="f14">
		上衣*1 裤子*1 抹胸*1 包装*1 
	</pre>

			</div>
		</div>

	</div>
</div>

<div class="mode">
	<div class="tl"></div><div class="tr"></div>
	<ul class="uls">
		<li class="first">
			<span class="guide"></span>
			<dl>
			<dt title="购物指南">购物指南</dt>
			<dd><a href="#" title="购物流程">购物流程</a></dd>
			<dd><a href="#" title="购物流程">购物流程</a></dd>
			<dd><a href="#" target="_blank" title="联系客服">联系客服</a></dd>
			<dd><a href="#" target="_blank" title="联系客服">联系客服</a></dd>
			</dl>
		</li>
		<li>
			<span class="way"></span>
			<dl>
			<dt title="支付方式">支付方式</dt>
			<dd><a href="#" title="货到付款">货到付款</a></dd>
			<dd><a href="#" title="在线支付">在线支付</a></dd>
			<dd><a href="#" title="分期付款">分期付款</a></dd>
			<dd><a href="#" title="分期付款">分期付款</a></dd>
			</dl>
		</li>
		<li>
			<span class="help"></span>
			<dl>
			<dt title="配送方式">配送方式</dt>
			<dd><a href="#" title="上门自提">上门自提</a></dd>
			<dd><a href="#" title="上门自提">上门自提</a></dd>
			<dd><a href="#" title="上门自提">上门自提</a></dd>
			<dd><a href="#" title="上门自提">上门自提</a></dd>
			</dl>
		</li>
		<li>
			<span class="service"></span>
			<dl>
			<dt title="售后服务">售后服务</dt>
			<dd><a href="#" target="_blank" title="售后策略">售后策略</a></dd>
			<dd><a href="#" target="_blank" title="售后策略">售后策略</a></dd>
			<dd><a href="#" target="_blank" title="售后策略">售后策略</a></dd>
			<dd><a href="#" target="_blank" title="售后策略">售后策略</a></dd>
			</dl>
		</li>
		<li>
			<span class="problem"></span>
			<dl>
			<dt title="特色服务">特色服务</dt>
			<dd><a href="#" target="_blank" title="夺宝岛">夺宝岛</a></dd>
			<dd><a href="#" target="_blank" title="夺宝岛">夺宝岛</a></dd>
			<dd><a href="#" target="_blank" title="夺宝岛">夺宝岛</a></dd>
			<dd><a href="#" target="_blank" title="夺宝岛">夺宝岛</a></dd>
			</dl>
		</li>
	</ul>
</div>
</body>
</html>