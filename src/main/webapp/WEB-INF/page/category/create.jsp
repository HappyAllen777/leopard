<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="/WEB-INF/page/system/source.jsp" />
<html>
<head>

<title>创建分类</title>
<link rel="stylesheet" href="${project}/static/oss/amazeui/amazeui.min.css">
</head>

<body style="width:450px;">
	<div class="am-form" style="width: 450px;box-shadow:2px 3px 15px rgba(0,0,0,0.3);float:left">
		<fieldset>
			<legend>创建分类</legend>

			<div class="am-form-group">
				<label>名称</label> <input type="text" class="" placeholder="输入分类名称" name="name" fd="true" dt="text" minl="1" maxl="64" tip="分类名称" />
			</div>


			<div class="am-form-group">
				<label>备注</label> <textarea class="" rows="5" name="remarks" fd="true" dt="null" minl="1" maxl="128" tip="备注"></textarea>
			</div>

			<div class="am-form-group">
				<label>类型</label> <select name="type" fd="true" dt="text" minl="1" maxl="64" tip="类型">
					<option value="article">文章分类</option>
				</select> <span class="am-form-caret"></span>
			</div>
			<div class="am-form-group" style="text-align: center">
				<a href="javascript:;" class="button button-default" id="save-button"><span class="am-icon-save"></span>保存</a>
				<a href="javascript:history.go(-1);" class="button button-default"><span class="am-icon-close"></span>返回</a>
			</div>
		</fieldset>
	</div>

</body>
<script type="text/javascript">
	$(document).ready(function() {
		$("#save-button").bind("click", addCategory);

		function addCategory() {
			var form = formCheck();
			if (form) {
				formSubmit(form, "${project}/category/add", function(){
					showInfo("保存成功");
					setTimeout(function(){
						history.go(-1);
					},1000);
				});
			}
		}
	});
</script>
</html>
