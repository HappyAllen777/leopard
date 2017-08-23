function deleteObjectAjax(url, objectId, successCallback)
{
	var form = {"id" : objectId};
	if( successCallback == null || successCallback == undefined){
		successCallback = function(){
			defaultDeleteSuccessCallback();
		};
	}
	formSubmit(form, url, successCallback);
	
}

function defaultDeleteSuccessCallback()
{
	closeDialog("delete-dialog");
	showInfo("删除成功");
	setTimeout(function(){
		closeInfo();
		loadDataIntoTable(dataUrl);
	},1000);
}