<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="/WEB-INF/page/system/source.jsp" />
<jsp:include page="/WEB-INF/page/system/template.jsp" />
<html>
<head>
<title>分类管理</title>
<link rel="stylesheet" href="${project}/static/oss/amazeui/amazeui.min.css">
</head>

<body>
	<div class="am-cf admin-main">
		<!-- content start -->
		<div class="admin-content">
			<div class="am-cf am-padding">
				<div class="am-fl am-cf">
					<strong class="am-text-primary am-text-lg">分类列表</strong>
				</div>
			</div>
			<div class="toolbar">
				<a class="toolbutton" href="${project}/category/create.html"> <span class="am-icon-plus"></span> 新增
				</a>
			</div>
			<div class="searchbar">
				<div class="am-input-group am-input-group-sm">
					<input type="text" class="am-form-field" style="z-index:0"> <span class="am-input-group-btn">
						<button class="am-btn am-btn-default searchbutton" type="button"
						onclick="loadDataIntoTable('${project}/category/list?pn=${param.pn}&ps=${param.ps}')"><span class="am-icon-search">搜索
						</button>
					</span>
				</div>
			</div>
			<div class="am-g">
				<div class="am-u-sm-12">
					<table class="am-table am-table-striped am-table-hover table-main">
						<thead>
							<tr>
								<th>序号</th>
								<th>名称</th>
								<th>类型</th>
								<th>创建时间</th>
								<th>备注</th>
								<th>操作</th>
							</tr>
						</thead>
						<tbody id="data-container">
						</tbody>
					</table>
					<div class="am-cf pagefoot">
						<div class="am-fr">
							<ul class="am-pagination" id="pagination-container">
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
		<!-- content end -->
	</div>
</body>
<script id="list-template" type="text/template">
{@each list as it,index}
<tr>
	<td>%{++index}</td>
	<td>%{it.name}</a></td>
	<td>%{it.typeName}</td>
	<td>%{it.createTime}</td>
	<td>%{it.remarks}</td>
	<td>
		<div class="am-btn-toolbar">
			<div class="am-btn-group am-btn-group-xs">
				<a href="${project}/category/edit.html?id=%{it.id}" class="itembutton">
					<span class="am-icon-pencil-square-o"></span> 编辑
				</a>
				<a href="javascript:;" class="itembutton am-text-danger" onclick="openConfirm('delete-dialog','删除该数据后无法恢复，确认继续删除吗？','%{it.id}')">
					<span class="am-icon-trash-o"></span> 删除
				</a>
			</div>
		</div>
	</td>
</tr>
{@/each}
</script>
<script>
	var dataUrl = "${project}/category/list?pn=${param.pn}&ps=${param.ps}";
	loadDataIntoTable(dataUrl);
	
	function deleteObject(){
		var categoryId = $("#delete-dialog").find(".dialog-param").val();
		var deleteUrl = "${project}/category/delete";
		deleteObjectAjax(deleteUrl,categoryId);
	}
</script>
</html>
