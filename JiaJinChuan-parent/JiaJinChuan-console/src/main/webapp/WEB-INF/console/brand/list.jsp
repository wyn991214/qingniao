<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/console/head.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>qingniao-list</title>
<script type="text/javascript">
	//全选全不选
	function checkBox(ids, checked) {
		$("input[name='" + ids + "']").attr("checked", checked);
		
	}

	//批量删除
	function batchDelete(pageNo, name, status) {
		alert(name);
		alert(pageNo);
		alert(status);
		
		var size = $("input[name='ids']:checked").size();
		//必须选择一个
		if (size > 0) {
			if (confirm("确定要删除吗?")) {
				$("#delForm").attr(
						"action",
						"/brand/batchDelete.do?name=" + name + "&status=" + status
								+ "&pageNo=" + pageNo).submit();
			}
		} else {
			return;
		}
	}
</script>
</head>

<body>
	<div class="box-positon">
		<div class="rpos">当前位置: 品牌管理 - 列表</div>
		<form class="ropt">
			<input class="add" type="button" value="添加"
				onclick="javascript:window.location.href='/brand/add.do'" />
		</form>
		<div class="clear"></div>
	</div>
	<div class="body-box">
		<form action="/brand/list.do" method="post" style="padding-top: 5px;">
			<input type="text" name="name" placeholder="请输入名称" value="${name }" />
			&nbsp;&nbsp;&nbsp; 状态: <select name="status">
				<option value="1"
					<c:if test="${status==1}">selected="selected"</c:if>>在售</option>
				<option value="2"
					<c:if test="${status==2}">selected="selected"</c:if>>停售</option>
			</select> &nbsp;&nbsp;&nbsp;<input type="submit" class="query" value="查询" />
		</form>
		<form id="delForm" method="post">
			<table cellspacing="1" cellpadding="0" border="0" width="100%"
				class="pn-ltable">
				<thead class="pn-lthead">
					<tr>
						<th width="20"><input type="checkbox"
							onclick="checkBox('ids',this.checked)" /></th>
						<th>品牌名称</th>
						<th>LOGO</th>
						<th>描述</th>
						<th>官方网站</th>
						<th>状态</th>
						<th>选项</th>
					</tr>
				</thead>
				<tbody class="pn-ltbody">
					<c:forEach items="${pageInfo.list}" var="brand">
						<tr bgcolor="#ffffff" onmouseout="this.bgColor='#ffffff'"
							onmouseover="this.bgColor='#eeeeee'">
							<td><input type="checkbox" value="${brand.id}" name="ids" /></td>
							<td align="center">${brand.name }</td>
							<td align="center"><img width="50" eight="50"
								src="${brand.imgUrl}" /></td>
							<td align="center">${brand.description }</td>
							<td align="center"><a href="http://${brand.url }"
								target="_blank"> <font color="green">${brand.url }</font> <a></a></td>
							<td align="center"><c:if test="${brand.status==1}">在售</c:if>
								<c:if test="${brand.status==2}">停售</c:if></td>
							<td align="center"><a class="pn-opt"
								href="/brand/editBrand.do?id=${brand.id}">修改</a> | <a class="pn-opt"
								onclick="if(!confirm('您确定删除吗？')) {return false;}"
								href="/brand/batchDelete.do?id=${brand.id}&name=${name}&pageNo=${pageNo}&status=${status}">删除</a>
							</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</form>
		<div class="page pb15">
			<span class="r inb_a page_b">
			 <c:forEach items="${pageInfo.pageView}" var="page">
			       ${page}
		       </c:forEach>
			</span>
		</div>
		<div style="margin-top: 15px;">
			<input class="del-button" type="button" value="删除"
				onclick="batchDelete('${pageNo}','${name}','${status}');" />
		</div>
	</div>
</body>

</html>