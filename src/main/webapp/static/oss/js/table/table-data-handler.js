var pagefoot = $("#pagination-container");
function drawPageFoot(data) {
	var totalSize = data.totalSize;
	var pageSize = data.pageSize
	var totalPageNumber = Math.ceil(totalSize / pageSize);
	var pageNumber = data.pageNumber;
	var dataUrl = data.dataUrl;
	$("#totalSize").text(data.totalSize);

	var template = document.getElementById("pagination").innerHTML;
	var paginationHtml = juicer(template, data);
	pagefoot.html(paginationHtml);
}

function jumpPage(dataUrl, pageSize) {
	var form = formCheck("pagination");
	if (form) {
		var pageNumber = form.pageNumber;
		window.location.href = dataUrl + "?pn=" + pageNumber + "&ps="
				+ pageSize;
	}
}

function loadDataIntoTable(dataUrl) {
	showLoading("正在加载数据...");
	getAjaxData(dataUrl, null, null, null, dataHandler);
}

function dataHandler(data) {
	console.log(data);
	var template = document.getElementById("list-template").innerHTML;
	var html = juicer(template, data);
	$("#data-container").html(null).append(html);
	drawPageFoot(data);
}