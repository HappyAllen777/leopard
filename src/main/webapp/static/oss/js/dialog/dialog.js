function openDialog(dialogId, dialogMsg) {
	var $dialog = $("#" + dialogId);
	if ($dialog.length == 0) {
		console.log("no dialog was found by dialogId: " + dialogId);
		return;
	}
	if (dialogMsg && dialogMsg.length > 0) {
		$dialog.find(".dialog-body").html(dialogMsg);
	}
	$dialog.fadeIn(300);
}

function openConfirm(dialogId, msg, param){
	var $dialog = $("#" + dialogId);
	if ($dialog.length == 0) {
		console.log("no dialog was found by dialogId: " + dialogId);
		return;
	}
	$dialog.fadeIn(300);
	if (msg && msg.length > 0) {
		$dialog.find(".dialog-body").html(msg);
	}
	if (param && param.length > 0) {
		$dialog.find(".dialog-param").val(param);
	}
}

function closeDialog(dialogId) {
	var $dialog = $("#" + dialogId);
	$dialog.hide(300);
}

function showInfo(msg) {
	var $info = $("#info");
	if ($info.length == 0) {
		console.log("no info was found by infoId: info, alert instead");
		alert(msg);
		return;
	}
	$("#info_msg").html(msg);
	$info.show();
}

function closeInfo() {
	$("#info_msg").html(null);
	$("#info").hide();
}

function showLoading(loadingMsg, autoCloseMills) {
	var $loadingToast = $("#loading");
	if ($loadingToast.length == 0) {
		console.log("no loadingToast was found by: loading");
		return;
	}

	loadingMsg = loadingMsg || "正在处理请稍候。。。"
	$loadingToast.find(".loading-msg").html(loadingMsg);
	$loadingToast.show();
	autoCloseMills = autoCloseMills || 2000;
	setTimeout(function() {
		closeLoading();
	}, autoCloseMills);
}

function closeLoading(){
	var $loadingToast = $("#loading");
	$loadingToast.hide();
}