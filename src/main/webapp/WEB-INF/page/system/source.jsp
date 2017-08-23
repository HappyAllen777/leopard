<%@ page language="java" pageEncoding="UTF-8"%>
<link rel="shortcut icon" href="${project}/static/image/common/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${project}/static/oss/css/dialog/dialog.css">
<link rel="stylesheet" type="text/css" href="${project}/static/oss/css/dialog/loading.css">
<link rel="stylesheet" type="text/css" href="${project}/static/oss/css/common/common.css">
<link rel="stylesheet" type="text/css" href="${project}/static/oss/css/common/table.css">

<script type="text/javascript" src="${project}/static/oss/amazeui/jquery.min.js"></script>
<script type="text/javascript" src="${project}/static/oss/amazeui/amazeui.min.js"></script>
<script type="text/javascript" src="${project}/static/oss/js/dialog/dialog.js"></script>
<script type="text/javascript" src="${project}/static/oss/js/form/form-check.js"></script>
<script type="text/javascript" src="${project}/static/oss/juicer/juicer.js"></script>
<script type="text/javascript" src="${project}/static/oss/js/table/table-data-handler.js"></script>
<script type="text/javascript" src="${project}/static/oss/js/table/common.js"></script>

<!-- 提示遮罩层 -->
<div id="info" style="display: none;">
	<div class="info-mask"></div>
	<div class="info">
		<div id="info_msg" class="info-body"></div>
		<div class="dialog-fl-foot">
			<a href="javascript:;" class="dialog-fl-btn dialog-fl-btn-main" onclick="closeInfo()">知道了</a>
		</div>
	</div>
</div>

<!-- 加载中遮罩层 -->
<div id="loading" style="display:none;">
	<div class="loading-mask"></div>
	<div class="loading-body">
		<i class="loading-icon"></i>
		<p class="loading-msg"></p>
	</div>
</div>

<div id="delete-dialog" style="display: none;">
	<div class="dialog-mask"></div>
	<div class="dialog">
		<div class="blank-line-20"></div>
		<div class="dialog-head">
			<span style="font-size: 1.2em;font-weight: 400">确认删除</span>
		</div>
		<div class="blank-line-10"></div>
		<div id="dialog_msg" class="dialog-body" style="padding-bottom:1.3em"></div>
		<input type="hidden" class="dialog-param" />
		<div class="dialog-foot">
			<a href="javascript:;" class="dialog-btn" onclick="deleteObject()">确认</a> <a href="javascript:;" class="dialog-btn close"
				onclick="closeDialog('delete-dialog')">取消</a>
		</div>
	</div>
</div>