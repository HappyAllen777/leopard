// 段落标题部分开始
function confirmAddTitle() {
	var form = formCheck("title-add");
	if (form) {
		addParagraphTitletoEditbox(form.paragraphTitle);
		closeDialog("title-add-dialog");
		
		$("#title-add-dialog").find("input").val(null);
	}
}

function openParagraphTitleEdit(componentId) {
	var $editDialog = $("#title-edit-dialog");
	var $component = $("#" + componentId);
	var content = getComponentContent($component);

	$editDialog.find(".dialog-input").val(content);
	
	openConfirm("title-edit-dialog", null, componentId);
}

function confirmEditTitle(){
	var form = formCheck("title-edit");
	if (form) {
		var componentId = form.componentId;
		var content = form.paragraphTitle;
		
		updateParagraphTitletoEditBox(content, componentId);
		
		closeDialog("title-edit-dialog");
	}
}

function confirmAddParagraph() {
	var form = formCheck("paragraph-add");
	if (form) {
		console.log(form);
		addParagraphtoEditbox(form.paragraph ,form.textIndent);
		closeDialog("paragraph-add-dialog");
	}
}

function confirmEditParagraph(){
	var form = formCheck("paragraph-edit");
	if (form) {
		console.log(form);
		var componentId = form.componentId;
		var content = form.paragraph;
		var textIndent = form.textIndent;
		updateParagraphtoEditBox(content, textIndent, componentId);
		
		closeDialog("paragraph-edit-dialog");
	}
}

function addParagraphTitletoEditbox(title) {
	var componentId = getUUID();
	$("#editbox").append(
			"<div id=\"" + componentId + "\" data-type=\"paragraph-title\" class=\"component paragraph-title-box-1\" onmouseover=\"showComponentMenu(this)\" onmouseout=\"hideComponentMenu(this)\">" +
				"<div class=\"component-menu\">" +
					"<a href=\"javascript:;\" onclick=\"openParagraphTitleEdit('" + componentId + "')\" class=\"component-menu-button\"><span class=\"icon-pencil\"></span>编辑</a>" +
					"<a href=\"javascript:;\" class=\"component-menu-button\" onclick=\"openConfirm('delete-component-dialog','确认删除该组件吗？删除后不可还原','" + componentId + "')\"><span class=\"icon-remove\"></span>删除</a>" +
				"</div>" +
				"<div class=\"component-content paragraph-title-content-1\">" + title + "</div>" +
			"</div>");
}

function addParagraphtoEditbox(paragraph, textIndent) {
	var componentId = getUUID();
	var textIndentClass = textIndent?"ti2":"";
	$("#editbox").append(
			"<div id=\"" + componentId + "\" data-type=\"paragraph\" class='component paragraph-box' onmouseover=\"showComponentMenu(this)\" onmouseout=\"hideComponentMenu(this)\">" +
				"<div class=\"component-menu\">" +
					"<a href=\"javascript:;\" onclick=\"openParagraphEdit('" + componentId + "')\" class=\"component-menu-button\"><i class=\"icon-pencil\"></i>编辑</a>" +
					"<a href=\"javascript:;\" class=\"component-menu-button\" onclick=\"openConfirm('delete-component-dialog','确认删除该组件吗？删除后不可还原','" + componentId + "')\"><i class=\"icon-remove\"></i>删除</a>" +
				"</div>" +
				"<pre class=\"component-content paragraph-pre " + textIndentClass + "\">" + paragraph + "</pre>" +
			"</div>");
}

function addPicture(pictureUri) {
	var componentId = getUUID();
	$("#editbox").append(
			"<div id=\"" + componentId + "\" data-type='picture' class='component picture-box' onmouseover='showComponentMenu(this)' onmouseout='hideComponentMenu(this)'>" +
				"<div class=\"component-menu\">" +
					"<a href=\"javascript:;\" class=\"component-menu-button\" onclick=\"openConfirm('delete-component-dialog','确认删除该组件吗？删除后不可还原','" + componentId + "')\"><i class=\"icon-remove\"></i>删除</a>" +
				"</div>" +
				"<img class=\"component-content\" src=\"" + pictureUri + "\"/>" +
			"</div>");
}

function deleteComponent(){
	var componentId = $("#delete-component-dialog").find(".dialog-param").val();
	$("#" + componentId).remove();
	closeDialog("delete-component-dialog");
}

function updateParagraphTitletoEditBox(title, componentId) {
	$("#" + componentId).find(".component-content").html(title);
}

function updateParagraphtoEditBox(content, textIndent, componentId) {
	var $pre = $("#" + componentId).find("pre");
	$pre.text(content);
	if (textIndent) {
		$pre.addClass("ti2");
	} else {
		$pre.removeClass("ti2");
	}
}



function openParagraphEdit(componentId) {
	var $editDialog = $("#paragraph-edit-dialog");
	var $component = $("#" + componentId);
	var content = getComponentContent($component);
	$editDialog.find("textarea").val(content);
	
	openConfirm("paragraph-edit-dialog", null, componentId);
}

function pictureUploadSuccessCallback(result) {
	addPicture(result.errMsg);
	closeDialog("picture-add-dialog");
}

function preview() {
	var $previewBox = $("#preview");
	$previewBox.html(null);
	var $componentList = $(".component");
	for (var i = 0; i < $componentList.length; i++) {
		var $component = $($componentList[i]);
		var componentType = $component.data("type");
		var content = getComponentContent($component);
		if (componentType == "paragraph-title") {
			$previewBox.append("<div class=\"paragraph-title-box-1\"><div class=\"paragraph-title-content-1\">" + content + "</div></div>");
		} else if (componentType == "paragraph") {
			var textIndent = $component.find(".paragraph-pre").hasClass("ti2");
			var textIndentClass = textIndent?"ti2":"ti";
			$previewBox.append("<div class=\"paragraph-box\"><pre class=\"paragraph-pre " + textIndentClass + "\">" + content + "</pre></div>");
		} else if (componentType == "picture") {
			$previewBox.append("<div class=\"picture-box\"><img src=\"" + content
					+ "\"/></div>");
		} else if (componentType == "title") {
			$previewBox.append("<div class=\"title-box-1\"><div class=\"title-content-1\">" + content + "</div></div>");
		}
	}
}

function fromPreviewtoEdit(){
	var $previewBox = $("#preview");
	var $editBox = $("#editbox");
	
	var $elements = $previewBox.children();
	
	if ($elements.length > 0) {
		for (var i = 0;i < $elements.length;i++) {
			var $element = $($elements[i]);
			if ($element.hasClass("paragraph-title-box-1")) {
				var paragraphTitle = $element.find(".paragraph-title-content-1").html();
				addParagraphTitletoEditbox(paragraphTitle);
			} else if ($element.hasClass("paragraph-box")) {
				var paragraph = $element.find(".paragraph-pre").html();
				var textIndent = $element.find(".paragraph-pre").hasClass("ti2");
				addParagraphtoEditbox(paragraph, textIndent);
			} else if ($element.hasClass("picture-box")) {
				var pictureUri = $element.find("img").attr("src");
				addPicture(pictureUri);
			}
		}
	}
	
	console.log($("#preview").find(".paragraph-title-content-1").text());
}

function showComponentMenu(component) {
	$(component).find(".component-menu").show();
}

function hideComponentMenu(component) {
	$(component).find(".component-menu").hide();
}

//获取组件内容
function getComponentContent($component) {
	var content = "";
	var componentType = $component.data("type");
	if (componentType == "paragraph-title") {
		return $component.find(".component-content").html();
	} else if (componentType == "paragraph") {
		return $component.find(".component-content").text();
	} else if (componentType == "title") {
		return $component.val();
	} else if (componentType == "picture") {
		return $component.find(".component-content").attr("src");
	}
}

function save(saveUrl) {
	var form = formCheck("article");
	if (form) {
		preview();
		var content = $("#preview").html();
		createJsonWithAdd(form, "content", content);
		formSubmit(form, saveUrl);
	}
}

