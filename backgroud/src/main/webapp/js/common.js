function setMsg(input, msg) {
	var lb = input + "_msg";
	document.getElementById(input).focus();
	document.getElementById(lb).innerText = msg;
}

// 操作提示方法
function message(msg) {
	if (msg.status == "FAIL") {
		$.messager.alert('操作提示', msg.content + msg.detailMsg, 'error');
	} else {
		$.messager.alert('操作提示', msg.content, 'info');
	}
}

// 验证数字
function isDigit(arg0) {
	var input = document.getElementById(arg0);
	var value = input.value.replace(/^\s+|\s+$/g, "");
	input.value = value;
	var reg = /^(0|[1-9][0-9]*)$/;
	var flag = reg.test(value);
	return flag;
}

// 验证数字
function isNum(arg0) {
	var input = document.getElementById(arg0);
	var value = input.value.replace(/^\s+|\s+$/g, "");
	input.value = value;
	var reg = /^\-[1-9][0-9]*$/;
	var flag = reg.test(value);
	return flag;
}



function isTellphone(arg0) {
	var input = document.getElementById(arg0);
	var value = input.value.replace(/^\s+|\s+$/g, "");
	input.value = value;
	var reg = /^(\(\d{3,4}\)|\d{3,4})?\d{7,8}$/;
	var flag = reg.test(value);
	return flag;
}

function checkUrl(arg0) {
	var input = document.getElementById(arg0);
	var value = input.value.replace(/^\s+|\s+$/g, "");
	input.value = value;
	var strRegex = "^(https|http|ftp|rtsp|mms)?://"
			+ "?(([0-9a-z_!~*'().&=+$%-]+: )?[0-9a-z_!~*'().&=+$%-]+@)?" // ftp的user@
			+ "(([0-9]{1,3}\.){3}[0-9]{1,3}" // IP形式的URL- 199.194.52.184
			+ "|" // 允许IP和DOMAIN（域名）
			+ "([0-9a-z_!~*'()-]+\.)*" // 域名- www.
			+ "([0-9a-z][0-9a-z-]{0,61})?[0-9a-z]\." // 二级域名
			+ "[a-z]{2,6})" // first level domain- .com or .museum
			+ "(:[0-9]{1,4})?" // 端口- :80
			+ "((/?)|" // a slash isn't required if there is no file name
			+ "(/[0-9a-z_!~*'().;?:@&=+$,%#-]+)+/?)$";
	var re = new RegExp(strRegex);
	return re.test(value);
}

// 非空验证
function checkNull(arg0) {
	var flag = false;
	var input = document.getElementById(arg0);
	var value = input.value.replace(/^\s+|\s+$/g, "");
	input.value = value;
	if (value.length > 0) {
		flag = true;
	}
	return flag;
}

// 检查密码输入是否一致;
function checkPwd(arg0, arg1) {
	var flag = false;
	var pwd1 = document.getElementById(arg0).value;
	var pwd2 = document.getElementById(arg1).value;
	if (pwd1 == pwd2) {
		flag = true;
	}

	return flag;
}

// arg0 控件id ,arg1最小长度, arg2最大长度
// 检查控件输入内容长度
function checkLength(arg0, arg1, arg2) {

	var val = "";
	var input = document.getElementById(arg0);
	val = input.value.replace(/^\s+|\s+$/g, "");
	if (val.length >= arg1 && val.length <= arg2) {
		return true;
	}
	return false;
}

// 检查密码是否包含空格;
function checkPwdTrim(arg0) {
	var flag = true;
	var input = document.getElementById(arg0);
	var value = input.value;
	if (value.indexOf(" ") != -1) {
		flag = false;
	}
	return flag;

}

// 去前后空格
function trim(arg) {
	var input = document.getElementById(arg);
	var value = input.value.replace(/^\s+|\s+$/g, "");
	return value;
}

function get1970Date(seconds){
	var day = new Date();
	Year = day.setFullYear(1970);
	Month = day.setMonth(0);
	Day = day.setDate(1);

	Hour = day.setHours(0);
	Minute = day.setMinutes(0);
	Second = day.setSeconds(0);
	day.setSeconds(day.getSeconds()+seconds);
	return formatDate(day);
}

function formatDate(date) {
	var day = date;
	var Year = 0;
	var Month = 0;
	var Day = 0;
	var Hour = 0;
	var Minute = 0;
	var Second = 0;

	var CurrentDate = "";
	// 初始化时间
	Year = day.getFullYear();
	Month = day.getMonth() + 1;
	Day = day.getDate();

	Hour = day.getHours();
	Minute = day.getMinutes();
	Second = day.getSeconds();

	CurrentDate += Year + "-";
	if (Month >= 10) {
		CurrentDate += Month + "-";
	} else {
		CurrentDate += "0" + Month + "-";
	}

	if (Day >= 10) {
		CurrentDate += Day;
	} else {
		CurrentDate += "0" + Day;
	}

	if (Hour >= 10) {
		CurrentDate += " " + Hour + ":";
	} else {
		CurrentDate += " 0" + Hour + ":";
	}
	if (Minute >= 10) {
		CurrentDate += Minute + ":";
	} else {
		CurrentDate += "0" + Minute + ":";
	}
	if (Second >= 10) {
		CurrentDate += Second;
	} else {
		CurrentDate += "0" + Second;
	}

	return CurrentDate;

}

function formatDate1(date) {
	date.setDate((date.getDate() - 1));
	date.setHours(date.getHours() + 10);
	var day = date;
	var Year = 0;
	var Month = 0;
	var Day = 0;
	var Hour = 0;
	var Minute = 0;
	var Second = 0;

	var CurrentDate = "";
	// 初始化时间
	Year = day.getFullYear();
	Month = day.getMonth() + 1;
	Day = day.getDate();

	Hour = day.getHours();
	Minute = day.getMinutes();
	Second = day.getSeconds();

	CurrentDate += Year + "-";
	if (Month >= 10) {
		CurrentDate += Month + "-";
	} else {
		CurrentDate += "0" + Month + "-";
	}

	if (Day >= 10) {
		CurrentDate += Day;
	} else {
		CurrentDate += "0" + Day;
	}

	if (Hour >= 10) {
		CurrentDate += " " + Hour + ":";
	} else {
		CurrentDate += " 0" + Hour + ":";
	}

	if (Minute >= 10) {
		CurrentDate += Minute + ":";
	} else {
		CurrentDate += "0" + Minute + ":";
	}

	if (Second >= 10) {
		CurrentDate += Second;
	} else {
		CurrentDate += "0" + Second;
	}

	return CurrentDate;

}

// 格式化时间 yyyy-MM-dd 或者 yyyy-MM-dd HH:mm:SS
function formattime(time) {
	var value = "";
	var year = time.substring(0, 4);
	var month = time.substring(4, 6);
	var day = time.substring(6, 8);

	value = year + "-" + month + "-" + day;

	if (time.length >= 14) {
		var hour = time.substring(8, 10);
		var minute = time.substring(10, 12);
		var second = time.substring(12, 14);
		value = value + " " + hour + ":" + minute + ":" + second;
	}
	return value;
}

// 获取yyyyMMdd
function getDate(date) {
	var time = "";
	var year = date.substring(0, 4);
	var month = date.substring(5, 7);
	var day = date.substring(8, 10);
	time = "" + year + month + day;
	return time;
}

// 获取yyyyMMddHHmmSS
function getTime(date) {
	var time = "";
	var year = date.substring(0, 4);
	var month = date.substring(5, 7);
	var day = date.substring(8, 10);
	var hour = date.substring(11, 13);
	var minute = date.substring(14, 16);
	var second = date.substring(17, 19);
	time = "" + year + month + day + hour + minute + second;
	return time;
}

/**
 * 时间比较,返回false，就是发布时间大于当前时间
 */
function comptime(publishTime) {
	var currentTime = formatDate(new Date()); // 当前时间

	var beginTimes = publishTime.substring(0, 10).split('-');
	var endTimes = currentTime.substring(0, 10).split('-');

	publishTime = beginTimes[1] + '-' + beginTimes[2] + '-' + beginTimes[0]
			+ ' ' + publishTime.substring(10, 19);
	currentTime = endTimes[1] + '-' + endTimes[2] + '-' + endTimes[0] + ' '
			+ currentTime.substring(10, 19);

	var a = (Date.parse(currentTime) - Date.parse(publishTime)) / 3600 / 1000;

	if (a < 0) {
		return false;
	} else {
		return true;
	}
}

function getSysCode(url, data) {
	var obj = {};
	$.ajax({
		url : url,
		data : {
			'sysCode' : data
		},
		async : false,
		dataType : 'json',
		type : "post",
		success : function(data) {
			obj = data;
		}
	});
	return obj;
}

String.prototype.replaceAll = function(s1, s2) {
	return this.replace(new RegExp(s1, "gm"), s2);
};
