function getUUID() {
    function S4() {
       return (((1+Math.random())*0x10000)|0).toString(16).substring(1);
    }
    return (S4()+S4()+S4()+S4()+S4()+S4()+S4()+S4());
}

/**
 * 此方法依赖lrz.mobile.min.js,将图片转成base64字符串
 * 
 * @author Allen
 * @param input :
 *            文件选择控件
 * @param callbackFunc :
 *            上传完成之后的回调
 * 
 */
function encodeImgToStr(input, callbackFunc){
	var file = input.files[0];
	if(!file){
		showInfo("请选择图片");
		return;
	}
	var size = file.size;
	var maxSize = $(input).attr("maxl") * 1;
	if(size > maxSize){
		showInfo("图片不能超过" + maxSize/1024 + "kb");
		return;
	}
	lrz(file, {
        before: function() {
            console.log("压缩开始");
        },
        fail: function(err) {
            console.error(err);
        },
        always: function() {
            console.log("压缩结束");
        },
        done: function (result) {
           // 你需要的数据都在这里，可以以字符串的形式传送base64给服务端转存为图片。
        	uploadImgStr(result, input, callbackFunc);
        }
    });
}

// 把图片的base64码提交
function uploadImgStr(result, input, callbackFunc){
	var base64 = result.base64;
	var clearBase64 = base64.substr(base64.indexOf(",") + 1 );
	$.ajax({
		url: url + "/wechat/PropertyMaintenance!upLoadImage.action",
		type: "POST", dataType: "text",
		data:{a:Math.random(),imgStr:clearBase64},
		error: function(){
			showInfo("上传出错");
		},
		success: function(data){
			if(callbackFunc){
				callbackFunc(data, input);
			} else {
				showInfo("上传成功");
			}
		}
	}); 
}

function isBlank(str){
	if (str == null || str.length == 0) {
		return true;
	}
	if ($.trim(str) == "") {
		return true;
	} else {
		return false;
	}
}

function isNotBlank(str){
	return !isBlank(str);
}

/**
 * 入参是秒数,往指定的元素填充剩余时间
 */
function setRemainTime(remainSeconds, zeroCallback){
	if(remainSeconds == 0 && zeroCallback){
		zeroCallback();
	}
	if(remainSeconds <= 0){
		return;
	}
	var remainMinutes = parseInt(remainSeconds / 60);
	$("#remainMinutes").text(remainMinutes);
	var seconds = remainSeconds - 60*remainMinutes;
	$("#remainSeconds").text(seconds);
}


