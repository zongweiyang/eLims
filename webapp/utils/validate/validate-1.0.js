$(function() {
	window.validate = {};
	/**
	 * 校验失败默认提示信息
	 */
	var validateTips = {
		required : "该字段为必填项",
		strLength : "字符串长度必须在{min}到{max}之间",
		ajax : "ajax校验不合法",
		notNumber : "该项不是合法的数字",
		numBetweenMax : "该项值必须小于{max}",
		numBetweenMin : "该项值必须大于{min}",
		email : "请填写正确的邮箱格式",
		reg : "正则表达式校验失败",
		suffix : "后缀必须为{suffix}",
		number : "必须为数字",
		"double" : "必须为浮点小数", 
		phone : "请填写有效电话号码",
		zipCode : "请正确填写邮编",
		fax : "请填写正确的传真格式",
		url : "url不能包含汉字"
	};
	/**
	 * 校验失败后置方法
	 */
	var fail = window.validate.tip = function(message, $obj ,date) {
		lhgdialog.setting.zIndex = 9999;
		date = date || 2;
		$.dialog.tips(message,date,'alert.gif',function(){
			if($obj!=null && $obj.attr("type")!="hidden" && (!$obj.attr("disabled"))){
				$obj.focus();
			}
			
		});
	};
	
	/* 校验成功后置方法 */
	var success = function($obj) {
		
	}

	/**
	 * 获取提示消息
	 */
	var getMessage = window.validate.getMessage = function($obj, validateType, validateTip) {
		var message = $obj.attr(validateType + "-msg") || $obj.attr("msg")
				|| validateTips[validateTip];
		return message;
	}
	/**
	 * 校验方法列表
	 */
	var validateTypes = window.validate.validateTypes = {
		/* 必填项校验 */
		required : function($obj) {
			var value = $.trim($obj.val());
			var message = $obj.attr("msg");
			if (value == null || value == "") {
				message = getMessage($obj, "required", "required")
				fail(message, $obj);
				return false;
			} else {
				success($obj);
				return true;
			}
		},
		/* 字符串长度校验 */
		strLength : function($obj) {
			var max = $obj.attr("max");
			var min = $obj.attr("min");
			var value = $.trim($obj.val());
			if ((value == null) || (value == "")) {
				success($obj);
				return true;
			}
			function valmax() {
				if (value.length > parseInt(max)) {
					var message = getMessage($obj, "strLength", "strLength")
							.replace("{max}", max).replace("{min}", min);
					fail(message, $obj);
					return false;
				}
				return true;
			}
			function valmin() {
				if (value.length < parseInt(min)) {
					message = getMessage($obj, "strLength", "strLength")
							.replace("{max}", max).replace("{min}", min);
					fail(message, $obj);

					return false;
				}
				return true;
			}
			var isok = max == null
					? (min == null ? true : valmin())
					: (min == null ? valmax() : (valmax() && valmin()));
			return isok;
		},
		ajax : function($obj) {
			var url = $obj.attr("ajaxUrl");
			var paramName = $obj.attr("paramName") || $obj.attr("name");
			var value = $obj.val();
			if ((value == null) || (value == "")) {
				success($obj);
				return true;
			}
			var successVal = ($obj.attr("successVal") == null || $obj
					.attr("successVal") == "") ? "true" : $obj
					.attr("successVal");
			if ((value == null) || (value == "")) {
				success($obj);
				return true;
			}
			var params = {};
			params[paramName] = value;
			var isOk = true;
			$.ajax({
						type : "post",
						dataType : "text",
						data : params,
						url : url,
						async : false,
						success : function(msg) {
							if (msg == successVal) {
								success($obj);
							} else {
								var message = getMessage($obj, "ajax", "ajax");
								fail(message, $obj);
								isOk = false;
							}
						},
						error : function(data) {
							fail("请求错误", $obj);
							isOk = false;
						}
					});
			return isOk;
		},
		/* 数字范围校验 */
		numBetween : function($obj) {
			var sMax = $obj.attr("max");
			var sMin = $obj.attr("min");
			var sValue = $obj.val();
			if ((sValue == null) || (sValue == "")) {
				success($obj);
				return true;
			}

			var iMax = null;
			var iMin = null;
			var iValue = null;
			if ((sMax != null) && (sMax != "") && (!(isNaN(sMax)))) {
				iMax = parseInt(sMax);
			}
			if ((sMin != null) && (sMin != "") && (!(isNaN(sMin)))) {
				iMin = parseInt(sMin);
			}
			if ((!(isNaN(sValue)))) {
				iValue = parseInt(sValue);
			}

			/* iMax & iMin同时不合法 */
			if (iMax == null && iMin == null) {
				/* 属性max必须为一个有效合法数字 */
				return false;
			}
			if (iMax < iMin) {
				/* 属性max必须大于或者等于min */
				return false;
			}
			/* 输入值非数字 */
			if (iValue == null) {
				fail(validateTips["notNumber"], $obj);
				return false;
			}
			/* 输入值大于最大值 */
			if (iMax != null && iValue > iMax) {
				var sMessage = getMessage($obj, "numBetween", "numBetweenMax")
						.replace("{max}", iMax == null ? "-" : iMax);
				sMessage = sMessage.replace("{min}", iMin == null ? "-" : iMin);
				fail(sMessage, $obj);
				return false;
			}
			/* 输入值小于最小值 */
			if (iMin != null && iValue < iMin) {
				var sMessage = getMessage($obj, "numBetween", "numBetweenMin")
						.replace("{max}", iMax == null ? "-" : iMax);
				sMessage = sMessage.replace("{min}", iMin == null ? "-" : iMin);
				fail(sMessage, $obj);
				return false;
			}
			success($obj);
			return true;
		},
		/* 邮箱 */
		email : function($obj) {
			var emailStr = $obj.val();
			if ((emailStr == null) || (emailStr == "")) {
				success($obj);
				return true;
			}
			var sMessage = getMessage($obj, "email", "email");
			var emailPat = /^(.+)@(.+)$/;
			var specialChars = "\\(\\)<>@,;:\\\\\\\"\\.\\[\\]";
			var validChars = "\[^\\s" + specialChars + "\]";
			var quotedUser = "(\"[^\"]*\")";
			var ipDomainPat = /^(\d{1,3})[.](\d{1,3})[.](\d{1,3})[.](\d{1,3})$/;
			var atom = validChars + '+';
			var word = "(" + atom + "|" + quotedUser + ")";
			var userPat = new RegExp("^" + word + "(\\." + word + ")*$");
			var domainPat = new RegExp("^" + atom + "(\\." + atom + ")*$");
			var matchArray = emailStr.match(emailPat);
			if (matchArray == null) {
				fail(sMessage, $obj);
				return false;
			}
			var user = matchArray[1];
			var domain = matchArray[2];
			if (user.match(userPat) == null) {
				fail(sMessage, $obj);
				return false;
			}
			var IPArray = domain.match(ipDomainPat);
			if (IPArray != null) {
				for (var i = 1; i <= 4; i++) {
					if (IPArray[i] > 255) {
						fail(sMessage, $obj);
						return false;
					}
				}
				success($obj);
				return true;
			}
			var domainArray = domain.match(domainPat);
			if (domainArray == null) {
				fail(sMessage, $obj);
				return false;
			}
			var atomPat = new RegExp(atom, "g");
			var domArr = domain.match(atomPat);
			var len = domArr.length;
			if ((domArr[domArr.length - 1].length < 2)
					|| (domArr[domArr.length - 1].length > 3)) {
				fail(sMessage, $obj);
				return false;
			}

			if (len < 2) {
				fail(sMessage, $obj);
				return false;
			}
			success($obj);
			return true;
		},
		/* 正则表达式校验 */
		reg : function($obj) {
			var expr = $obj.attr("expr");
			var sValue = $obj.val();
			if (sValue == null || sValue.length < 1) {
				success($obj);
				return true;
			}
			var reg = new RegExp(expr);
			if (sValue.match(reg)) {
				success($obj);
				return true;
			} else {
				var sMessage = getMessage($obj, "reg", "reg");
				fail(sMessage);
				return false;
			}
		},
		suffix : function($obj) {
			var value = $obj.val();
			if ((value == null) || (value == "")) {
				success($obj);
				return true;
			}
			var suffix = $obj.attr("suffix");
			var _suffixs = suffix.split(",");
			for(var iIndex = 0;iIndex<_suffixs.length;iIndex++){
				var _suffix = _suffixs[iIndex];
				var suffixLen = _suffix.length;
				var valSuffix = value.substring(value.length - suffixLen,
					value.length);
				if(valSuffix != _suffix){
					continue;
				}else{
					return true;
				}
			}
			var sMessage = getMessage($obj, "suffix", "suffix").replace("{suffix}", suffix);
			fail(sMessage,$obj);
			return false;
		},
		number : function($obj){
			var value = $obj.val();
			if ((value == null) || (value == "")) {
				success($obj);
				return true;
			}
			if(isNaN(value)){
				var sMessage = getMessage($obj,"number","number");
				fail(sMessage,$obj);
				return false;
			}else{
				return true;
			}
		},
		phone : function($obj){
			var value = $obj.val();
			if(value==null || value==""){
				return true;
			}else{
				var reg = /((\d{11})|^((\d{7,8})|(\d{4}|\d{3})-(\d{7,8})|(\d{4}|\d{3})-(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1})|(\d{7,8})-(\d{4}|\d{3}|\d{2}|\d{1}))$)/;
				if(reg.test(value)){
					return true;
				}else{
					var sMessage = getMessage($obj,"phone","phone");
					fail(sMessage,$obj);
					return false;
				}
			}
		},
		zipCode : function($obj){
			var value = $obj.val();
			if(null==value || ""==value){
				return true;
			}else{
				var reg = /^\d{6}$/;
				if(reg.test(value)){
					return true;
				}else{
					var sMessage = getMessage($obj,"zipCode","zipCode");
					fail(sMessage,$obj);
					return false;
				}
			}
		},
		fax : function($obj){
			var value = $obj.val();
			if(null==value||""==value){
				return true;
			}else{
				var reg=/^\d{3}-\d{8}$/;
				if(reg.test(value)){
					return true;
				}else{
					var sMessage = getMessage($obj,"fax","fax");
					fail(sMessage,$obj);
					return false;
				}
			}
		},
		url : function($obj){
			var value = $obj.val();
			if(null==value || ""==value){
				return true;
			}else{
				var reg=/^([a-zA-Z]||:||\.||\/||\d)*$/g;
				if(reg.test(value)){
					return true;
				}else{
					var sMessage = getMessage($obj,"url","url");
					fail(sMessage,$obj);
					return false;
				}
			}
		},
		
		"double" : function($obj){
			var value = $obj.val();
			if(null==value || ""==value){
				return true;
			}else{
				var reg = /^\d+\.{1}\d+$/g;
				if(reg.test(value)){
					return true;
				}else{
					var sMessage = getMessage($obj,"double","double");
					fail(sMessage,$obj);
					return false;
				}
			}
		}
	};
	/**
	 * 对单个元素进行校验
	 */
	var validate = validate || function($obj) {
		var validateTypes2 = $obj.attr("valType");
		validateTypes2 = validateTypes2.replace("，",",");
		if (validateTypes2 == null || typeof(validateTypes2) == 'undefined') {
			return true;
		} else {
			validateTypes2 = validateTypes2.split(",");
		}
		isOk = true;
		for(iIndex=0;iIndex<validateTypes2.length;iIndex++){
			if(typeof(validateTypes2[iIndex])){}
			if(validateTypes2[iIndex]==''){
				return true;
			}
			if(validateTypes[validateTypes2[iIndex]]($obj)){
				continue;
			}else {
				return false;
			}
		}
		
		return isOk;
	};
	/**
	 * 对整个校验链进行校验
	 */
	var validateAll = function($obj) {
		if ($obj == null) {
			return true;
		}
		for (;;) {
			var isOk = validate($obj);
			if (isOk) {
				var nextObj = $obj.data("nextObj");
				if (nextObj == null) {
					return true;
				} else {
					$obj = nextObj;
					continue;
				}
			} else {
				return false;
			}
		}
	};
	/* 获取到所有的需要验证的表单 */
	var validateFormArray = $("form");
	var DOMParse = {
		required : function($obj) {
			$obj.after(
			$("<span>").addClass("hongxin").append($("<font>").html("*"))
			);
		}
	}
	/* 绑定校验链，第一环默认为第一个input */
	$.each(validateFormArray, function() {
				var $form = $(this);
				var a = this;
				a.submit2 = this.submit;
				var validateDatas = $form.find(":input[valType]");
					var $validateObj = $form;
					$.each(validateDatas, function() {
						$validateObj = $(this);
						var _validateTypes = $(this).attr("valType").split(",");
						$.each(_validateTypes, function() {
							if(DOMParse[this]!=null){
								DOMParse[this]($validateObj);
							}
						});
					});
				this.submit = function() {
					validateDatas = $form.find(":input[valType]");
					var $validateObj = $form;
					$.each(validateDatas, function() {
						$validateObj.data("nextObj", $(this));
						$validateObj = $(this);
					});
					if (validateAll($(this).data("nextObj"))) {
						a.submit2();
						try{
						 	$('body').shadow();
						}catch(e){
						 	shandow('body');
						}
					}
				}
				return true;
			});
});
