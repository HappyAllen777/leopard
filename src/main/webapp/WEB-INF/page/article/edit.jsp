<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<jsp:include page="/WEB-INF/page/system/source.jsp" />
<html>
<head>

<title>编辑文章</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no">
<link rel="shortcut icon" href="${project}/static/image/common/favicon.ico" />
<link rel="stylesheet" type="text/css" href="${project}/static/oss/css/article/common.css">
<link rel="stylesheet" type="text/css" href="${project}/static/oss/css/article/form.css">
<link rel="stylesheet" type="text/css" href="${project}/static/oss/css/article/create.css">
<link rel="stylesheet" type="text/css" href="${project}/static/oss/css/form/common.css">
<link rel="stylesheet" type="text/css" href="${project}/static/oss/css/common/icon.css">
<style>
.component {
	position: relative;
}

.component-paragraph {
	font-size: 1em; line-height: 28px;
}

.component-menu {
	position: absolute; right: 20; bottom: 0; height: 30px;
	line-height: 30px; text-indent: 0;
	background-color: rgb(14, 144, 210); display: none
}

.component-menu-button {
	font-size: 16px; font-weight: normal; padding: 0 5px;
	color: rgb(255, 255, 255)
}

.component-menu-button:hover {
	color: rgb(255, 255, 255)
}

.file-upload-button {
	position: relative; display: block; background: rgb(53, 53, 53);
	border-radius: 3px; padding: 4px 12px; overflow: hidden;
	color: rgb(255, 255, 255); text-decoration: none; text-indent: 0;
	line-height: 150px; height: 150px;
}

.file-upload-button input {
	position: absolute; font-size: 100px; right: 0; top: 0; opacity: 0;
}

.file-upload-button:hover {
	background: rgb(160, 160, 160);
}

.button {
	display: inline-block; padding: 6px 18px; vertical-align: middle;
	text-align: center; -webkit-appearance: none;
	-webkit-user-select: none;
}

.button-default,.button-default:FOCUS {
	color: rgb(255, 255, 255); background-color: rgb(53, 53, 53);
}

.button-default:HOVER {
	color: rgb(255, 255, 255); background-color: rgb(160, 160, 160);
}

.component:last-child{
	margin-bottom:50px;
}
</style>
</head>

<body>
	<div class="EDIT-AREA def-shadow" style="width: 500px;height:100%;float:left;overflow-y:scroll">
		<div style="height:10px;"></div>
		<div style="width:96%;height:40px;margin:0 2%">
			<input type="text" fd="article" dt="text" name="title" tip="文章标题" value="${baseModel.title}" data-type="title" placeHolder="请输入文章标题" class="component def-input-text" style="padding: 3px 10px" />
			<input type="hidden" fd="article" dt="text" name="id" tip="文章id" value="${baseModel.id}"/>
		</div>
		<div style="height:10px;"></div>
		<div id="editbox" class="article-box" style="width:500px;min-width:500px;min-height: 0px;">
		</div>
		<div class="TOOL-BAR">
			<a class="button45 ADD-TITLE" onclick="openDialog('title-add-dialog')"><span class="icon-plus"></span>&nbsp;段落标题</a> <a
				class="button45 ADD-PARAGRAPH" onclick="openDialog('paragraph-add-dialog')"><span class="icon-plus"></span>&nbsp;段落</a> <a
				class="button45 ADD-PICTURE" onclick="openDialog('picture-add-dialog')"><span class="icon-plus"></span>&nbsp;图片</a> <a
				class="button45 ADD-PICTURE" onclick="preview()"><span class="icon-eye"></span>&nbsp;预览</a> <a class="button45 ADD-PICTURE"
				onclick="save('${project}/article/update')"><span class="icon-save"></span>&nbsp;保存</a>
		</div>
	</div>
	<div id="preview" class="article-box" style="margin-left:500px;height:100%;overflow-y:scroll;">${baseModel.content}</div>

	<!-- 删除确认框 -->
	<div id="delete-component-dialog" style="display: none;">
		<div class="dialog-mask"></div>
		<div class="dialog">
			<div class="blank-line-20"></div>
			<div class="dialog-head">
				<span><span class="icon-warning"></span>删除组件</span>
			</div>
			<div class="blank-line-10"></div>
			<div class="dialog-body" style="line-height: 40px"></div>
			<input type="hidden" class="dialog-param" />
			<div class="dialog-foot">
				<a href="javascript:;" class="dialog-btn" onclick="deleteComponent()">确认删除</a> <a href="javascript:;" class="dialog-btn close"
					onclick="closeDialog('delete-component-dialog')">取消</a>
			</div>
		</div>
	</div>

	<!-- 添加段落标题框 -->
	<div id="title-add-dialog" style="display: none;">
		<div class="dialog-mask"></div>
		<div class="dialog">
			<div class="blank-line-20"></div>
			<div class="dialog-head">
				<span>添加段落标题</span>
			</div>
			<div class="blank-line-10"></div>
			<div class="dialog-body">
				<div style="height:44px;">
					<input type="text" class="def-input-text" fd="title-add" dt="text" tip="段落标题" name="paragraphTitle" />
				</div>
			</div>
			<div class="blank-line-10"></div>
			<div class="dialog-foot">
				<a href="javascript:;" class="dialog-btn" onclick="confirmAddTitle()">确认</a> <a href="javascript:;" class="dialog-btn close"
					onclick="closeDialog('title-add-dialog')">取消</a>
			</div>
		</div>
	</div>

	<!-- 编辑段落标题框 -->
	<div id="title-edit-dialog" style="display: none;">
		<div class="dialog-mask"></div>
		<div class="dialog">
			<div class="blank-line-20"></div>
			<div class="dialog-head">
				<span>编辑段落标题</span>
			</div>
			<div class="blank-line-10"></div>
			<div class="dialog-body">
				<div style="height:44px;">
					<input type="text" class="dialog-input def-input-text" fd="title-edit" dt="text" tip="段落标题" name="paragraphTitle" /> <input
						type="hidden" class="dialog-param" fd="title-edit" dt="text" tip="段落标题id" name="componentId" />
				</div>
			</div>
			<div class="blank-line-10"></div>
			<div class="dialog-foot">
				<a href="javascript:;" class="dialog-btn" onclick="confirmEditTitle()">确认</a> <a href="javascript:;" class="dialog-btn close"
					onclick="closeDialog('title-edit-dialog')">取消</a>
			</div>
		</div>
	</div>

	<!-- 添加段落框 -->
	<div id="paragraph-add-dialog" style="display: none;">
		<div class="dialog-mask"></div>
		<div class="dialog">
			<div class="blank-line-20"></div>
			<div class="dialog-head">
				<span>添加段落</span>
			</div>
			<div class="blank-line-10"></div>
			<div id="dialog_msg" class="dialog-body">
				<div>
					<textarea fd="paragraph-add" name="paragraph" dt="text" tip="段落内容" class="def-textarea"></textarea>
				</div>
				<div>
					<label class="def-label"> <input type="checkbox" class="def-checkbox" name="textIndent" value="true" fd="paragraph-add" dt="text">首行缩进
					</label>
				</div>
			</div>

			<div class="dialog-foot">
				<a href="javascript:;" class="dialog-btn" onclick="confirmAddParagraph()">确认</a> <a href="javascript:;" class="dialog-btn close"
					onclick="closeDialog('paragraph-add-dialog')">取消</a>
			</div>
		</div>
	</div>

	<!-- 编辑段落框 -->
	<div id="paragraph-edit-dialog" style="display: none;">
		<div class="dialog-mask"></div>
		<div class="dialog">
			<div class="blank-line-20"></div>
			<div class="dialog-head">
				<span>编辑段落</span>
			</div>
			<div class="blank-line-10"></div>
			<div id="dialog_msg" class="dialog-body">
				<div>
					<textarea fd="paragraph-edit" name="paragraph" dt="text" tip="段落内容" class="def-textarea"></textarea> <input
						type="hidden" class="dialog-param" fd="paragraph-edit" dt="text" tip="段落id" name="componentId" />
				</div>
				<div>
					<label class="def-label"> <input type="checkbox" class="def-checkbox" name="textIndent" value="true" fd="paragraph-edit" dt="text">首行缩进
					</label>
				</div>
			</div>
			<div class="dialog-foot">
				<a href="javascript:;" class="dialog-btn" onclick="confirmEditParagraph()">确认</a> <a href="javascript:;" class="dialog-btn close"
					onclick="closeDialog('paragraph-edit-dialog')">取消</a>
			</div>
		</div>
	</div>

	<div id="picture-add-dialog" style="display: none;">
		<div class="dialog-mask"></div>
		<div class="dialog">
			<div class="blank-line-20"></div>
			<div class="dialog-head">
				<span>插入图片</span>
			</div>
			<div class="blank-line-10"></div>
			<div id="dialog_msg" class="dialog-body">
				<div style="height:44px;">
					<input id="pictureFileName" type="text" class="def-input-text" placeHolder="文件名" name="fileName" fd="picture" dt="text" tip="文件名" style="margin-top:1px"/> <input
						type="hidden" name="category" fd="picture" dt="text" tip="图片分类" value="article" />
				</div>
				<div class="blank-line-10"></div>
				<div>
					<a href="javascript:;" class="file-upload-button"> <span class="icon-plus"></span>添加图片 <input type="file" onchange="encodeImg(this)" class="img-input"
						accept="image/*" maxl="${3072*1024}" />
					</a>
				</div>
			</div>
			<div class="dialog-foot">
				<a href="javascript:;" class="dialog-btn"
					onclick="uploadBase64Data('${project}/attachment/base64PictureUpload.html',pictureUploadSuccessCallback)">确认</a> <a href="javascript:;"
					class="dialog-btn close" onclick="closeDialog('picture-add-dialog')">取消</a>
			</div>
		</div>
	</div>


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
</body>
<script type="text/javascript" src="${project}/static/oss/js/picture/lrz.mobile.min.js"></script>
<script type="text/javascript" src="${project}/static/oss/js/picture/picture-upload.js"></script>
<script type="text/javascript" src="${project}/static/oss/js/system/article.js"></script>
<script type="text/javascript" src="${project}/static/oss/js/form/form-util.js"></script><script type="text/javascript">
$(document).ready(function(){
	fromPreviewtoEdit();
});
</script>
</html>
