/**
 * Copyright (C), 2007, 厦门亿力吉奥信息科技有限公司
 * File Name: Utils.js
 * Encoding UTF-8
 * Version: 1.0
 * Date: Sep 12, 2006
 * History:	
 */
 
/**
 * 常用工具类，提供了大量静态常用方法。
 *
 * @author  陈谋坤 (chenmoukun@pdpower.com)
 * @version Revision: 1.00 Date: Sep 12, 2006
 */
Utils = Class.create();


/**
 * 设置常量
 */
Constant = Class.create();

/**
 * 价格常量，只要涉及到价格的都将其限制在该范围内
 * add by chenmk 2007.3.14
 */
Constant.PRICE = 1000000.00;

/**
 * 判断对象是否为对象
 * 
 * @param Object obj 欲判断的对象
 * @return boolean true - 是对象，false - 不是对象
 */
Utils.isObject = function(obj){
	return ( obj == null || typeof(obj) == "undefined" ) ? false : true;
}

/**
 * 判断输入是否为货币数值
 * 
 * @param float money
 * @return boolean
 */
Utils.isMoney = function(money){

//	return /^[1-9][0-9]*\.[0-9]{2}/.test(money) ||
//		   /^0\.[0-9]{2}/.test(money);
	
//	/*
//	 * change by chenmk 2006.11.29
//	 * 上面的方法有错误,如88.88.88
//	 */
//	return Utils.isFloatValue(money);

	return /^(([1-9]\d*)|0)(\.\d{1,2})?$/.test(money);
}

/**
 * 判断一个对象是不是函数对象
 * 
 * @param fun 对象
 * @return boolean
 */
Utils.isFunction = function(fun){
	return (fun != null && typeof(fun) == "function");
}

/**
 * 判断是否为整数
 */
Utils.isInteger = function(num){
 	return /^[1-9][0-9]*$/.test(num);
}

/**
 * 判断字符串中是否含有特殊字符(不全)
 */
 Utils.isContainSpecialCharacter = function(str){
 	return /^[^\\\/\^\"\'\.\|\?\*@%#\(\)\s]+$/.test(str);
 }

/**
 * 根据传进来的一组复选框表单元素，判断该组复选框是否有被选中
 * add by chenmk 2007.1.10
 */
Utils.isAnyChecked = function(checkboxElements){
	
	if(checkboxElements == null || checkboxElements.length == 0)
		return false;
		
	for(var i=0; i<checkboxElements.length; i++){
		if(checkboxElements[i].checked)
			return true;
	}
	
	return false;
}

/**
 * 判断一个数组是否包含了特定的值
 * 
 * @param values
 * @param value
 */
Utils.contains = function(values, value) {
	for (var i=0; i<values.length; i++) {
		if (values[i] == value)
			return true;
	}
	
	return false;
}

/**
 * 判断一个数值（或字符串）是否为非负数。
 * 
 */
Utils.isNonNegative = function(num){
	
	return /^[1-9][0-9]*$/.test(num) || num == 0;
}

Utils.isCharacter = function(str){
    
    var reg = /^\w+$/;
    if (!reg.test(str)){
       return false;
    }
    return true;
}

/**
 * 验证字符串是否为空
 * 
 * @param 需要验证的字符串
 * @return 布尔值
 */
Utils.isEmpty = function(str){
    if (str == null || str.trim().length == 0)
        return true;
    else
        return false;
}

Utils.isOpera = (navigator.userAgent.toLowerCase().indexOf("opera") > -1);
Utils.isSafari = (/webkit|khtml/).test(navigator.userAgent.toLowerCase());
Utils.isIE = (!Utils.isOpera && navigator.userAgent.toLowerCase().indexOf("msie") > -1);
Utils.isIE5 = (Utils.isIE && /msie 5\.0/i.test(navigator.userAgent));
Utils.isIE7 = (!Utils.isOpera && navigator.userAgent.toLowerCase().indexOf("msie 7") > -1);
Utils.isGecko = (!Utils.isSafari && navigator.userAgent.toLowerCase().indexOf("gecko") > -1);
Utils.isKHtml = /Konqueror|Safari|KHTML/i.test(navigator.userAgent);


/**
 * 获取某HTML元素的符合指定样式名称的第一个父元素。
 * 
 * @param HTMLElement 需要匹配的HTML元素
 * @param String className 匹配的样式名称
 * @return HTMLElement 返回符合条件的父元素，或返回空
 */
Utils.getParentByClassName = function(el, className){
	if (!el)
		return null;
		
	var e = el.parentNode;
	if (e.className == className)
		return e;
		
	Utils.getParentByClassName(e, className);
}

/**
 * 获取某HTMLElement下指定样式的子元素集合
 * 
 * @param HTMLElment el
 * @param String className
 * @return Array
 */
Utils.getChildrenByClassName = function(el, className){
	if (!el)
		return null;
	
	var children = new Array();
	for (var i=0; i<el.childNodes.length; i++){
		if (el.childNodes[i].className == className)
			children.push(el.childNodes[i]);
	}
	
	return children;
}

/**
 * 
 */
Utils.ScriptFragmentRegExp = new RegExp(Prototype.ScriptFragment, 'img');

/**
 * 在指定的字符串中执行其中包含的脚本。
 * 
 * @param String str
 */
Utils.evalScripts = function(str){
	var scripts = str.match(Utils.ScriptFragmentRegExp);
	
	if (scripts) {
      	match = new RegExp(Prototype.ScriptFragment, 'im');
      	setTimeout((function() {
       		for (var i = 0; i < scripts.length; i++)
          		eval(scripts[i].match(match)[1]);
      	}).bind(this), 10);
   }
}

/**
 * 删除指定字符串中的所有脚本块。
 * 
 * @param String str
 * @return String
 */
Utils.omitScriptFragment = function(str){
    return str.replace(Utils.ScriptFragmentRegExp, '');
}

Utils.getComputedStyle = function(str){
	var reg = /[0-9]+/i;
	var value = str.match(reg);
	
	return (value == null) ? 0 : parseInt(value);
}

/**
 * 获取某个元素的绝对坐标。
 * 
 * @param HMTLElement el
 * @return Object
 */
Utils.getAbsolutePos = function(el) {
	var SL = 0, ST = 0;
	var is_div = /^div$/i.test(el.tagName);
	if (is_div && el.scrollLeft)
		SL = el.scrollLeft;
	if (is_div && el.scrollTop)
		ST = el.scrollTop;
	var r = { x: el.offsetLeft - SL, y: el.offsetTop - ST };
	if (el.offsetParent) {
		var tmp = this.getAbsolutePos(el.offsetParent);
		r.x += tmp.x;
		r.y += tmp.y;
	}
	
	return r;
}

/**
 * 验证是否为浮点数
 * 
 * @param String str 
 * @return boolean
 */
Utils.isFloatValue = function(floatValue){
    
    var reg = /(^((-|\+)?0\.)(\d*)$)|(^((-|\+)?[1-9])+\d*(\.\d*)?$)/;  
    
    return reg.test(floatValue);    
}

/**
 * HTML页面中比较龌龊的元素
 * 这里去掉了iframe ; update by chenmk
 */
Utils.TERRIABLE_ELEMENTS = new Array("applet",  "select");

/**
 * 屏蔽HTML页面中比较龌龊的元素，如applet、iframe、select。
 * 由于Div在显示在这些元素之上，会出现破坏Div元素的情形，因此将在Div元素下的这些龌龊的元素隐藏。
 * 
 * @param HTMLEelement el 需要屏蔽龌龊元素的HTMLElement对象
 */
Utils.hideShowCovered = function(el){
	function getVisib(obj){
		var value = obj.style.visibility;
		if (!value) {
			if (document.defaultView && typeof (document.defaultView.getComputedStyle) == "function") { // Gecko, W3C
				if (!Utils.isKHtml)
					value = document.defaultView.
						getComputedStyle(obj, "").getPropertyValue("visibility");
				else
					value = '';
			} else if (obj.currentStyle) { // IE
				value = obj.currentStyle.visibility;
			} else
				value = '';
		}
		return value;
	};

	var p = Utils.getAbsolutePos(el);
	var EX1 = p.x;
	var EX2 = el.offsetWidth + EX1;
	var EY1 = p.y;
	var EY2 = el.offsetHeight + EY1;

	for (var k = Utils.TERRIABLE_ELEMENTS.length; k > 0; ) {
		var ar = document.getElementsByTagName(Utils.TERRIABLE_ELEMENTS[--k]);
		var cc = null;

		for (var i = ar.length; i > 0;) {
			cc = ar[--i];

			p = Utils.getAbsolutePos(cc);
			var CX1 = p.x;
			var CX2 = cc.offsetWidth + CX1;
			var CY1 = p.y;
			var CY2 = cc.offsetHeight + CY1;

			if (self.hidden || (CX1 > EX2) || (CX2 < EX1) || (CY1 > EY2) || (CY2 < EY1)) {
				if (!cc.__msh_save_visibility) {
					cc.__msh_save_visibility = getVisib(cc);
				}
				cc.style.visibility = cc.__msh_save_visibility;
			} else {
				if (!cc.__msh_save_visibility) {
					cc.__msh_save_visibility = getVisib(cc);
				}
				cc.style.visibility = "hidden";
			}
		}
	}
}

/**
 * 
 * 
 */
Utils.calculateStringWidth = function(str){
	if (StringUtils.isEmpty(str))
		return null;
		
	var span = document.createElement("span");
	span.innerHTML = str;
	document.body.appendChild(span);
	
	var width = span.offsetWidth;
	document.body.removeChild(span);
	
	return width;
}

Utils.autoFitImage = function(img, width, height){
	
	if (!img || img.height == 0 || img.width == 0)
		return "&nbsp";
	
	var str = "<img src='" + img.src + "' ";
	
	if (img.width > width || img.height > height){
		var widthRate = img.width / width;
		var heightRate = img.height / height;
		if (widthRate > heightRate){
			str += "width='" + width + "' ";
			img.width = width;
		}else{
			str += "height='" + height + "' ";
			img.height = height;
		}
	}
		
	str += "/>";
	
	return str;
}

/**
 * 移除HTML元素对象中所有子元素
 * 
 * @param htmlObj HTML元素对象
 */
Utils.removeChildren = function(htmlObj) {
	if (!Utils.isObject(htmlObj) && !Utils.isObject($(htmlObj)))
		return;
		
	while(htmlObj.hasChildNodes()) {
		htmlObj.removeChild(htmlObj.childNodes[0]);
	}
}

/**
 * 更新HTML元素对象的innerHTML。
 * 在更新内容之前，删除其包含的所有子节点。
 * 
 * @param htmlObj
 * @param str
 */
Utils.updateElementInnerHTML = function(htmlObj, str) {
	
	if (!Utils.isObject(htmlObj) && !Utils.isObject($(htmlObj)))
		return;
		
	Utils.removeChildren(htmlObj);
	htmlObj.innerHTML = str;
}

/**
 * 在给定的URL后加上时间搓，防止页面没有重新加载，而是使用缓存的页面
 */
Utils.getUncacheUrl = function(url){

	if(url == null)
		return null;
		
	if(url.indexOf("?") != -1)
		url += "&" + (new Date()).getTime();
	else
		url += "?" + (new Date()).getTime();
		
	return url;
}


/*
 * 取得单选框选中的值 
 */
Utils.getRadioHtmlValue = function(oRadioObj){
	retValue = "";
	if(!Utils.isObject(oRadioObj)){
		alert("单选框Html对象参数为空");
		return "";
	}

	//判断单选按钮的长度是否为Null
	if(!Utils.isObject(oRadioObj.length)){
		if(oRadioObj.checked) retValue = oRadioObj.value;
	}else{
		for(var i=0;i<=oRadioObj.length-1;i++){
			if(oRadioObj[i].checked){
				retValue = oRadioObj[i].value;
				break;
			}
		}
	}
	return retValue;
}


/*
 * 取得复选框选中的值
 */
Utils.getCheckBoxHtmlValue = function(oCheckBoxObj,sSplitStr){
	retValue = "";
	if(!Utils.isObject(oCheckBoxObj)){
		alert("复选框Html对象参数为空");
		return "";
	}
	
	if(!Utils.isObject(sSplitStr)){
		sSplitStr = "|";
	}
	
	//判断单选按钮的长度是否为Null
	if(!Utils.isObject(oCheckBoxObj.length)){
		if(oCheckBoxObj.checked) retValue = oCheckBoxObj.value;
	}else{
		for(var i=0;i<=oCheckBoxObj.length-1;i++){
			if(oCheckBoxObj[i].checked){
				if(retValue == ""){
					retValue = oCheckBoxObj[i].value;
				}else{
					retValue = retValue + sSplitStr + oCheckBoxObj[i].value;
				}
			}
		}
	}
	
	return retValue;
}

/*
 * 动态加载js文件
 */
Utils.loadScriptFile = function(jsSrc){
	var oHead = document.getElementsByTagName('head')[0];
	var oScript = document.createElement('script');
		oScript.type = "text/javascript";
		oScript.id = jsSrc;
		oScript.src = jsSrc;
     oHead.appendChild(oScript);
}

/*------------------- String ------------------------------*/


/**
 * 去除字符串中的头尾空白字符
 * 
 */
if (!String.prototype.trim){
	String.prototype.trim  =  function(){
    	return  this.replace(/(^\s*)|(\s*$)/g,  "");
	}
}

/**
 * 根据显示字符串的容器的宽度，自动截取字符串。
 * 
 * @param int containerWidth 显示字符串的容器的宽度
 * @return String
 */
String.prototype.substrByContainerWidth = function(containerWidth){
	var titleWidth = Utils.calculateStringWidth(this);
	if (titleWidth > containerWidth){
		var length = parseInt(this.length * (containerWidth/titleWidth));
		return this.substr(0, length-1)
	}else 
		return this;
}

/**
 * 判断字符串是否以某个字符串开始
 * 
 * @param String prefix
 * @return boolean
 */
if (!String.startsWith){
	String.prototype.startsWith = function(prefix){
		if (StringUtils.isEmpty(prefix))
			return false;
			
		return (this.indexOf(prefix) > -1);
	}
}

/*----------------- StringUtils ----------------------------*/


var StringUtils = Class.create();

StringUtils.isEmpty = function(str){
	return (!Utils.isObject(str) || str.length == 0) ? true : false;
}

//把psSourceString中字符串中的psFromString字符串替换成psToString字符串
StringUtils.replaceSubstring = function(psSource,psFrom,psTo){
  var lpos=0;
  while (psSource.indexOf(psFrom)>-1){
  	lpos= psSource.indexOf(psFrom);
  	psSource=""+(psSource.substring(0,lpos)+psTo+psSource.substring((lpos+psFrom.length), psSource.length));
  }
  if(psSource.substring(psSource.length-psFrom.length,psSource.length)==" "+psFrom.substring(0,psFrom.length-1))
  	psSource=""+psSource.substring(0,psSource.length-psFrom.length+1)+psTo.substring(0,psTo.length-1);
  return psSource;
}

//返回空串
StringUtils.getNullBlankStr = function(psString){
	if(StringUtils.isEmpty(psString)){
		return "";
	}else{
		return psString
	}
 }

//获取符号byte长度
StringUtils.getByteLen = function(sStr) {
    var len = 0;

    if (StringUtils.isEmpty(sStr)) {
        return 0;
    }

    for (var i = 0; i < sStr.length; i++) {
        if (sStr.charCodeAt(i) > 255) {
            len += 2;
        }
        else {
            len++;
        }
    }
    return len;
}
/*---------------------- Date ------------------------------*/
 
var DateUtils = Class.create();

/**
 * 格式化日期
 * 
 * @param {} str 如2008160315
 * @param {} format 必须按java的格式 yyyy-MM-dd HH:mm:ss
 * @return {}
 */
DateUtils.format = function(str, format){
	
	if (!format) {
		format = "yyyy-MM-dd";
	}
	
	if(str == null || str.trim() == "")
		return "";
	var a = format.split(/\W+/);
	var result = format;
	var tmpStr = null;
	
	for(i =0; i<a.length; i++){
		switch (a[i]) {
			case "yyyy":
				result = result.replace("yyyy", str.substr(0,4) || "0000");
				str = str.substr(4);
				break;
			case "MM":
			    result = result.replace("MM", str.substr(0,2) || "00");
				str = str.substr(2);
				break;
			case "dd":
			    result = result.replace("dd", str.substr(0,2) || "00");
				str = str.substr(2);
				break;
			case "HH":
			    result = result.replace("HH", str.substr(0,2) || "00");
				str = str.substr(2);
				break;
			case "mm":
			    result = result.replace("mm", str.substr(0,2) || "00");
				str = str.substr(2);
				break;
			case "ss":
			    result = result.replace("ss", str.substr(0,2) || "00");
				str = str.substr(2);
				break;
		}
	}
	
	return result;
}

/** Date interval constant @static @type String */
DateUtils.SECOND = "s";
/** Date interval constant @static @type String */
DateUtils.MINUTE = "mi";
/** Date interval constant @static @type String */
DateUtils.HOUR = "h";
/** Date interval constant @static @type String */
DateUtils.DAY = "d";
/** Date interval constant @static @type String */
DateUtils.MONTH = "mo";
/** Date interval constant @static @type String */
DateUtils.YEAR = "y";

/**
 * 日期加减
 * 
 * @param {} str
 * @param {} interval
 * @param {} value
 * @return {String}
 */
DateUtils.add = function(str, interval, value){

	if(str == null || str.trim() == "")
		return "";
		
	str = str.trim();
	var length = str.length;	
	var dateObj = new Date();
	if(length >= 4)
		dateObj.setFullYear(parseInt(str.substr(0,4),10));	
	if(length >= 6)
		dateObj.setMonth(parseInt(str.substr(4,2),10)-1);
	if(length >= 8)
		dateObj.setDate(parseInt(str.substr(6,2),10));	
	if(length >= 10)
		dateObj.setHours(parseInt(str.substr(8,2),10));
	if(length >= 12)
		dateObj.setMinutes(parseInt(str.substr(10,2),10));	
	if(length >= 14)
		dateObj.setSeconds(parseInt(str.substr(12,2),10));	
	if(interval == DateUtils.YEAR)
		dateObj.setFullYear(dateObj.getFullYear()+value);
	if(interval == DateUtils.MONTH)
		dateObj.setMonth(dateObj.getMonth()+value);
	if(interval == DateUtils.DAY)
		dateObj.setDate(dateObj.getDate()+value);
	if(interval == DateUtils.HOUR)
		dateObj.setHours(dateObj.getHours()+value);
	if(interval == DateUtils.MINUTE)
		dateObj.setMinutes(dateObj.getMinutes()+value);
	if(interval == DateUtils.SECOND)
		dateObj.setSeconds(dateObj.getSeconds()+value);
		
	var result = "";
	if(length >= 4)
		result = dateObj.getFullYear();
	if(length >= 6){
		var mo = dateObj.getMonth()+1;
		if(mo<10)
			result += "0";			
		result += "" + mo;		
	}	
	if(length >= 8){
		var d = dateObj.getDate();
		if(d<10)
			result += "0";			
		result += "" + d;		
	}
	if(length >= 10){
		var h = dateObj.getHours();
		if(h<10)
			result += "0";			
		result += "" + h;	
	}
	if(length >= 12){
		var mi = dateObj.getMinutes();
		if(mi<10)
			result += "0";			
		result += "" + mi;	
	}
	if(length >= 14){
		var s = dateObj.getSeconds();
		if(s<10)
			result += "0";			
		result += "" + s;	
	}
	return result;
}

/**
 * 将已格式化的字符串，解析成日期对象
 * 
 * @param String str 已格式化的字符串
 * @param String fmt 格式化规则
 * @return Date
 */
if (!Date.parseDate){
	Date.parseDate = function (str, fmt) {
		var y = 0;
		var m = -1;
		var d = 0;
		var a = str.split(/\W+/);
		if (!fmt) {
			fmt = "%Y-%m-%d";
		}
		var b = fmt.match(/%./g);
		var i = 0, j = 0;
		var hr = 0;
		var min = 0;
		for (i = 0; i < a.length; ++i) {
			if (!a[i])
				continue;
			switch (b[i]) {
			    case "%d":
			    case "%e":
				d = parseInt(a[i], 10);
				break;
	
			    case "%m":
				m = parseInt(a[i], 10) - 1;
				break;
	
			    case "%Y":
			    case "%y":
				y = parseInt(a[i], 10);
				(y < 100) && (y += (y > 29) ? 1900 : 2000);
				break;
	
			    case "%H":
			    case "%I":
			    case "%k":
			    case "%l":
				hr = parseInt(a[i], 10);
				break;
	
			    case "%P":
			    case "%p":
				if (/pm/i.test(a[i]) && hr < 12)
					hr += 12;
				break;
	
			    case "%M":
				min = parseInt(a[i], 10);
				break;
			}
		}
		if (y != 0 && m != -1 && d != 0) {
			return new Date(y, m, d, hr, min, 0);
		}

	}	
}

/*------------------- SimpleListUtils 针对简要列表 ------------------------------*/

SimpleListUtils = Class.create();

/**
 * 判断简要列表是否有选中
 * 
 * @param simpleListObj 简要列表对象
 */
SimpleListUtils.hasSelected = function(simpleListObj){
	var selectedId = simpleListObj.getSelectedId();
	
	return (selectedId != null && selectedId != "");
}

/**
 * 判断简要列表是否是多选
 * 
 * @param simpleListObj 简要列表对象
 */
SimpleListUtils.isMultiSelected = function(simpleListObj){
	var selectedId = simpleListObj.getSelectedId();
	
	if(selectedId == null || selectedId == "")
		return false;
		
	return (selectedId.indexOf(",") > -1);
}

/**
 * 判断对简要列表单选进行验证
 * 
 * @param simpleListObj 简要列表对象
 */
SimpleListUtils.checkSingleSelection = function(simpleListObj){
	
	if(!SimpleListUtils.hasSelected(simpleListObj)){
		
		top.hintErrorMsg("请选择一条要操作的记录");
    	return false;
	}
    		
    if(SimpleListUtils.isMultiSelected(simpleListObj)){
    	top.hintErrorMsg("只能操作一条记录，您选择的记录数过多。");
    	return false;
    }
    		
    return true;
}
/**
 * 判断对简要列表进行验证
 *
 * @param simpleListObj 简要列表对象
 */
SimpleListUtils.checkSimpList = function(simpleListObj){

	if(!SimpleListUtils.hasSelected(simpleListObj)){

		top.hintErrorMsg("请选择一条要操作的记录");
    	return false;
	}
    return true;
}
/**
 * 取得简要列表的更新键
 * 注意：这要求该列显示在第一个列，该方法后期会更改
 * add by chenmk 2007.12.19
 * 
 * @param simpleListObj 简要列表对象
 */
SimpleListUtils.getUpdateKey = function(simpleListObj){
	return simpleListObj.getCellValues(simpleListObj.getSelectedId()-1,0);
}

/**
 * 取得选中行的ID，之所以提供该方法，是为了保证但点击链接列时，该行能自动选中
 * add by chenmk 2008.09.11
 * 
 * @param {} simpleListObj
 * @param {} primaryKey 主键列
 */
SimpleListUtils.getLinkSelectedId = function(simpleListObj, primaryKey){
	var result = null;
	var rowNum = simpleListObj.getRowsNum();
	var primaryColNum = simpleListObj.primaryCol;

	if(!primaryColNum || primaryColNum<0){
		alert("没有定义主键列");
       	return;
	}
	for(i=0; i<rowNum; i++){			
		var pk = simpleListObj.getCellValues(i, primaryColNum);
		if(pk == primaryKey){
			simpleListObj.setSelectedRow(i+1);  //先选中当行
			simpleListObj.selectAndCheck();     //再选中checkBOX
			result = simpleListObj.getSelectedId();
			break;
		}
	}	
	return result;
}

/**
 * 根据主键ID来取得选中的行
 * add by chenmk 2008.09.11
 * 
 * @param {} simpleListObj
 * @param {} objId
 */
SimpleListUtils.getSelectedIdByObjId = function(simpleListObj, objId){
	var rowNum = simpleListObj.getRowsNum();
	var primaryColNum = simpleListObj.primaryCol;

	if(!primaryColNum || primaryColNum<0){
		alert("没有定义主键列");
       	return;
	}
	for(i=0; i<rowNum; i++){			
		var pk = simpleListObj.getCellValues(i, primaryColNum);
		if(pk == objId)
			return i+1;
	}	
}

/**
 * 根据给定的列名来取得该列的值,该方法针对链接列的取值
 * add by chenmk 2008.09.11
 * 
 * @param {} simpleListObj
 * @param {} colName 列名
 * @return {}
 */
SimpleListUtils.getColumnValueByLink = function(simpleListObj, primaryKey,  colName){
	var colNameAndIndexMap = simpleListObj.colNameAndIndexRelate;
	var colIndex = colNameAndIndexMap[colName];
	var selectedId = SimpleListUtils.getLinkSelectedId(simpleListObj, primaryKey);
	return simpleListObj.getCellValues(selectedId-1, colIndex);
}

/**
 * 根据给定的列名来取得该列的值
 * add by chenmk 2008.09.11
 * 
 * @param {} simpleListObj
 * @param {} colName 列名
 * @return {}
 */
SimpleListUtils.getColumnValue = function(simpleListObj, colName){
	var colNameAndIndexMap = simpleListObj.colNameAndIndexRelate;
	var colIndex = colNameAndIndexMap[colName];
	var selectedId = simpleListObj.getSelectedId();
	return simpleListObj.getCellValues(selectedId-1, colIndex);
}

/**
 * 根据给定objID和列名来取得该列的值
 * add by chenmk 2008.09.11
 * 
 * @param {} simpleListObj
 * @param {} objId
 * @param {} colName
 * @return {}
 */
SimpleListUtils.getColumnValueByObjId = function(simpleListObj, objId, colName){
	var colNameAndIndexMap = simpleListObj.colNameAndIndexRelate;
	var colIndex = colNameAndIndexMap[colName];
	var selectedId = SimpleListUtils.getSelectedIdByObjId(simpleListObj, objId);
	return simpleListObj.getCellValues(selectedId-1, colIndex);
}



