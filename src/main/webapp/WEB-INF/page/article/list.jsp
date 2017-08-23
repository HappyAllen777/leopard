<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="/WEB-INF/page/system/source.jsp" />
<jsp:include page="/WEB-INF/page/system/template.jsp" />
<html>
<head>

<title>文章列表</title>
<link rel="stylesheet" href="${project}/static/oss/amazeui/amazeui.min.css">
</head>

<body>
	<div class="am-cf admin-main">
		<!-- content start -->
		<div class="admin-content">
			<div class="am-cf am-padding">
				<div class="am-fl am-cf">
					<strong class="am-text-primary am-text-lg">文章列表</strong>
				</div>
			</div>
			<div class="toolbar">
				<a class="toolbutton" href="${project}/article/create.html" target="_blank">
					<span class="am-icon-plus"></span> 新增
				</a>
			</div>
			<div class="searchbar">
				<div class="am-input-group am-input-group-sm">
					<input type="text" class="am-form-field" style="z-index:0"> <span class="am-input-group-btn">
						<button class="am-btn am-btn-default searchbutton" type="button"
						onclick="loadDataIntoTable('${project}/article/list?pn=${param.pn}&ps=${param.ps}')"><span class="am-icon-search">搜索
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
								<th>标题</th>
								<th>类别</th>
								<th>作者</th>
								<th>修改日期</th>
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
<script type="text/javascript" src="${project}/static/oss/js/form/form-check.js"></script>
<script type="text/javascript" src="${project}/static/oss/juicer/juicer.js"></script>
<script type="text/javascript" src="${project}/static/oss/js/table/table-data-handler.js"></script>
<script id="list-template" type="text/template">
{@each list as it,index}
<tr>
	<td>%{++index}</td>
	<td>%{it.title}</a></td>
	<td>暂无分类</td>
	<td>%{it.author}</td>
	<td>%{it.createTime}</td>
	<td>
		<div class="am-btn-toolbar">
			<div class="am-btn-group am-btn-group-xs">
				<a href="${project}/article/view.html?id=%{it.id}" class="itembutton" target="_blank">
					<span class="am-icon-eye"></span> 查看
				</a>
				<a href="${project}/article/edit.html?id=%{it.id}" class="itembutton" target="_blank">
					<span class="am-icon-pencil"></span> 编辑
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
	var dataUrl = "${project}/article/list?pn=${param.pn}&ps=${param.ps}";
	loadDataIntoTable(dataUrl);
	
	function deleteObject(){
		var objectId = $("#delete-dialog").find(".dialog-param").val();
		var deleteUrl = "${project}/article/delete";
		deleteObjectAjax(deleteUrl, objectId);
	}
</script>
</html>
