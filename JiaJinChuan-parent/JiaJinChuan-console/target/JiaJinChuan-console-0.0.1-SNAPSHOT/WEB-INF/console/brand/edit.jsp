<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/console/head.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

	<head>
		<title>qnsport-edit</title>
		<script type="text/javascript">
			//上传 （异步）
			function ImgUpload() {
				//本次 jquery.form.js
				var options = {
					url: "upload/uploadImg.do",
					type: "post",
					dataType: "json",
					success: function(data) {
						$("#allUrl").attr("src", data.url);
						$("#path").val(data.path);
					}
				};
				//使用form
				$("#jvForm").ajaxSubmit(options);

			}
		</script>
	</head>

	<body>
		<div class="box-positon">
			<div class="rpos">当前位置: 品牌管理 - 修改</div>
			<form class="ropt">
				<input type="submit" onclick="brand.do';" value="返回列表" class="return-button" />
			</form>
			<div class="clear"></div>
		</div>
		<div class="body-box" style="float:right">
			<form id="jvForm" action="/brand/edit.do" method="post">
				<input type="hidden" value="${brand.id }" name="id" />
				<table cellspacing="1" cellpadding="2" width="100%" border="0" class="pn-ftable">
					<tbody>
						<tr>
							<td width="20%" class="pn-flabel pn-flabel-h">
								<span class="pn-frequired">*</span> 品牌名称:
							</td>
							<td width="80%" class="pn-fcontent">
								<input type="text" class="required" name="name" value="${brand.name}" maxlength="100" />
							</td>
						</tr>
						<tr>
							<td width="20%" class="pn-flabel pn-flabel-h">
								<span class="pn-frequired">*</span> 品牌LOGO:
							</td>
							<td width="80%" class="pn-fcontent">
								注:该尺寸图片必须为90x150。
							</td>
						</tr>
						<tr>
							<td width="20%" class="pn-flabel pn-flabel-h"></td>
							<td width="80%" class="pn-fcontent">
								<img width="100" height="100" id="allUrl" src="${brand.realUrl}" />
								<input type="file" name="picture" onchange="ImgUpload();" />
								<input type="hidden" name="logo" id="path" />
							</td>
						</tr>
						<tr>
							<td width="20%" class="pn-flabel pn-flabel-h">
								品牌描述:</td>
							<td width="80%" class="pn-fcontent">
								<input type="text" class="required" value="${brand.description}" name="description" maxlength="80" size="60" />
							</td>
						</tr>

						<tr>
							<td width="20%" class="pn-flabel pn-flabel-h">
								官方网站:</td>
							<td width="80%" class="pn-fcontent">
								<input type="text" class="required" value="${brand.url}" name="url" maxlength="80" size="60" />
							</td>
						</tr>
						<tr>
							<td width="20%" class="pn-flabel pn-flabel-h">
								品牌的状态:</td>
							<td width="80%" class="pn-fcontent">
							
								<input type="radio" name="status" value="1" <c:if test="${brand.status==1 }">checked="checked"</c:if> />在售
								<input type="radio" name="status" value="0" <c:if test="${brand.status==0 }">checked="checked"</c:if> />停售
							</td>
						</tr>
					</tbody>
					<tbody>
						<tr>
							<td class="pn-fbutton" colspan="2">
								<input type="submit" class="submit" value="提交" /> &nbsp; <input type="jsutilset" class="jsutilset" value="重置" />
							</td>
						</tr>
					</tbody>
				</table>
			</form>
		</div>
	</body>

</html>