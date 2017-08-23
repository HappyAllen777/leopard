var lrzPictureResult = null;

function encodeImg(input) {
	var file = input.files[0];
	if (!file) {
		showInfo("请选择图片");
		return;
	}
	var fileName = file.name;
	$("#pictureFileName").val(fileName);
	var size = file.size;
	var maxSize = $(input).attr("maxl") * 1;
	if (size > maxSize) {
		showInfo("图片不能超过" + maxSize / 1024 + "kb");
		return;
	}
	lrz(file, {
		before : function() {
			console.log('压缩开始');
		},
		fail : function(err) {
			console.error(err);
		},
		always : function() {
			console.log('压缩结束');
		},
		done : function(result) {
			lrzPictureResult = result;
			// 你需要的数据都在这里，可以以字符串的形式传送base64给服务端转存为图片。
			console.log(lrzPictureResult);
		}
	});
}

// 把图片转换成base64码并提交
function uploadBase64Data(uploadUrl, uploadSuccessCallback) {
	if (lrzPictureResult == null) {
		showInfo("请选择图片");
		return;
	}
	var base64 = lrzPictureResult.base64;
	var clearBase64 = base64.substr(base64.indexOf(',') + 1);
	var form = formCheck("picture");
	if (form) {
		createJsonWithAdd(form, "base64PictureData", clearBase64);
		showLoading("正在上传图片，请稍候...");
		formSubmit(form, uploadUrl, uploadSuccessCallback);
	}
}

function uploadFile(fileId, uploadUrl){
	//获取文件对象
	var file = $("#" + fileId).get(0).files[0]; 

    // FormData 对象
    var form = new FormData();
    form.append("file", file); // 文件对象
    $.ajax({
        cache: false,
        type: "POST",
        url: uploadUrl,
        contentType: false, 
        processData: false, 
        data: form,
        xhr: function(){ //获取ajaxSettings中的xhr对象，为它的upload属性绑定progress事件的处理函数
            myXhr = $.ajaxSettings.xhr();
            if(progressFunction && myXhr.upload) { //检查进度函数和upload属性是否存在
                //绑定progress事件的回调函数
                myXhr.upload.addEventListener("progress",progressFunction, false);
            }
            return myXhr; //xhr对象返回给jQuery使用
        },
        error: function(request) {
            alert("Connection error");
        },
        success: function(data) {
        	console.log(data);
        }
    });
}

//进度条控制
function progressFunction(evt) {
    if (evt.lengthComputable) {
        var completePercent = Math.round(evt.loaded / evt.total * 100)+ "%";
        console.log("正在上传,进度：" + completePercent);
    }
}