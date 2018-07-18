<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/console/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>qingniao-list</title>
<script type="text/javascript">
	function getTableForm() {
		return document.getElementById('tableForm');
	}
	function optDelete() {
		if (Pn.checkedCount('ids') <= 0) {
			alert("请至少选择一个!");
			return;
		}
		if (!confirm("确定删除吗?")) {
			return;
		}
		var f = getTableForm();
		f.action = "/product/onSale.do";
		f.submit();
	}
	
	//商品上架
	function onSale() {
		var length = $("input[name='ids']:checked").size();
		if(length == 0) {
			alert("至少选择一个")
		}
		if(!confirm("确定上架吗?")) {
			return;
		}
		$("#tableForm").attr("action","/product/onSale.do").submit();
	}
	
	//商品下架
	function sellOut() {
		var length = $("input[name='ids']:checked").size();
		if(length==0) {
			alert("请至少选择一个")
		}
		if(!confirm("确定下架吗?")) {
			return;
		}
		$("#tableForm").attr("action","/product/sellOut.do").submit();
	}
	
</script>
</head>
<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 商品管理 - 列表</div>
		<form class="ropt">
			<input class="add" type="button" value="添加"
				onclick="javascript:window.location.href='/product/add.do'" />
		</form>
		<div class="clear"></div>
	</div>
	<div class="body-box">
		<form action="/product/list.do" method="post" style="padding-top: 5px;">
			名称: <input type="text" name="name" value="${name }" /> <select
				name="brandId">
				<option value="">请选择品牌</option>
				<c:forEach items="${brands }" var="brand">
					<option value="${brand.id }"
						<c:if test="${brand.id == brandId}">selected="selected"</c:if>>${brand.name }</option>
				</c:forEach>
			</select> <select name="isShow">
				<option value="true"
					<c:if test="${isShow}">selected="selected"</c:if>>上架</option>
				<option value="false"
					<c:if test="${!isShow}">selected="selected"</c:if>>下架</option>
			</select> <input type="submit" class="query" value="查询" />
		</form>
		<form method="post" id="tableForm">
			<table cellspacing="1" cellpadding="0" width="100%" border="0"
				class="pn-ltable">
				<thead class="pn-lthead">
					<tr>
						<th width="20"><input type="checkbox"  onclick="Pn.checkbox('ids',this.checked)" /></th>
						<th>商品名称</th>
						<th>图片</th>
						<th width="4%">新品</th>
						<th width="4%">热卖</th>
						<th width="4%">推荐</th>
						<th width="4%">上下架</th>
						<th width="12%">操作选项</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">
					<c:forEach items="${pageInfo.list}" var="product">
						<tr bgcolor="#ffffff" onmouseover="this.bgColor='#eeeeee'"
							onmouseout="this.bgColor='#ffffff'">
							<td><input type="checkbox" name="ids" value="${product.id }" /></td>
							<td align="center">${product.name }</td>
							<td align="center"><img width="50" height="50"
								src="${product.img.url }" /></td>
							<td align="center">是</td>
							<td align="center">是</td>
							<td align="center">是</td>
							<td align="center">下架</td>
							<td align="center"><a href="#" class="pn-opt">查看</a> | <a
								href="#" class="pn-opt">修改</a> | <a href="#"
								onclick="if(!confirm('您确定删除吗？')) {return false;}" class="pn-opt">删除</a>
								| <a href="/sku/list.do?productId=${product.id }" class="pn-opt">库存</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
			<div class="page pb15">
				<span class="r inb_a page_b"> <c:forEach
						items="${pageInfo.pageView }" var="page">
			${page }
		</c:forEach>
				</span>
			</div>
			<div style="margin-top: 15px;">
				<input class="del-button" type="button" value="删除"
					onclick="optDelete();" /><input class="add" type="button"
					value="上架" onclick="onSale();" /><input class="del-button"
					type="button" value="下架" onclick="sellOut();" />
			</div>
		</form>
	</div>
</body>
</html>