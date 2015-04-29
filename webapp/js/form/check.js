
function test() {
	alert("test");
}
function trim(str) {
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
function isIP(ipstr){ 
  ips=ipstr.split(".");   
  if   (ips.length!=4){ 
	  alert("IP必须是四位");
	  return true;
  }   
  for(i=0;i<ips.length;i++){   
        ipa=ips[i]*1;  
        if (isNaN(ipa)   ||   ipa<0   ||   ipa>255   ||   ipa   +"0"!=ips[i]+"0") {
			alert("IP每位必须是0—255间的整数");
			return true;
		}   
  }   
  return false;   
}   


function isNoIp(obj) {
	var regexp = /^(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])(\.(\d{1,2}|1\d\d|2[0-4]\d|25[0-5])){3}$/;
	if (!regexp.test(obj.value)) {
		alert("IP地址不合法!");
		obj.select();
		obj.focus();
		return true;
	}
	return false;
}
function isNoEmail(obj) {
	var strReg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/i;
	if (!strReg.test(obj.value)) {
		return false;
	}
	return true;
}
function isNoNatureNum(obj) {
	var strReg = /^[1-9]d*|0$/;
	if (!strReg.test(obj.value)) {
		return false;
	}
	return true;
}
function isOverLength(obj, length, info) {
	strLen = 0;
	for (i = 0; i < obj.value.length; i++) {
		if (obj.value.charCodeAt(i) > 255) {
			strLen += 2;
		} else {
			strLen++;
		}
	}
	if (strLen > length) {
		obj.select();
		obj.focus();
		alert(info);
		return true;
	}
	return false;
}
function isNaN(value){
	var myRegExp = /^\d*(\.\d+)?$/;
	if(value!==""){
		if (!myRegExp.test(value)) {
			return true;
		}
		return false;
	}
}
function isNoNum(obj, info) {
	var myRegExp = /^\d*(\.\d+)?$/;
	if(obj.value!==""){
		if (!myRegExp.test(obj.value)) {
			alert(info);
			obj.select();
			obj.focus();
			return true;
		}
		return false;
	}
}
//charles 08-05-28
function isNoBelowZero(obj, info) {
	//var myRegExp = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
	var myRegExp =/^\d+(\.\d+)?$/;
	if(obj.value!=""){
		if (!myRegExp.test(obj.value)) {
			obj.value="";
			obj.select();
			obj.focus();
			alert(info);
			return true;
		}
		return false;
	}
}
function isDecimal2(obj, info) {
	//var myRegExp = /^(([0-9]+\.[0-9]*[1-9][0-9]*)|([0-9]*[1-9][0-9]*\.[0-9]+)|([0-9]*[1-9][0-9]*))$/;
	var myRegExp =/^\d+\.\d{2}$/;    
	if(obj.value!=""){
		if (!myRegExp.test(obj.value)) {
			obj.select();
			obj.focus();
			alert(info);
			return true;
		}
		return false;
	}
}

function isNull(obj, info) {
	if (trim(obj.value) == "") {
		alert(info);
		obj.select();
		obj.focus();
		return true;
	}
	return false;
}
function inputNumOnly(obj,info) {
	var myRegExp = /^\d*(\.\d+)?$/;
	if(obj.value!=null){
		if (!myRegExp.test(obj.value)) {
			alert(info);
			obj.select();
			obj.focus();
			return true;
		}
	}
}
//Charles 08-06-05
//it is can be Reg 2002-12-10 | 2002-12-10 9:35 | 2002-12-10 19:35
function checkTimerule(obj, info) {

	var myRegExp =/^(([1-2]\d{3})-([0]?[1-9]|1[0-2])-([0-2]?[0-9]|3[0-1]))? ?((([0-1]?\d)|(2[0-3])):[0-5]\d)?$/;
	if (!myRegExp.test(obj.value)) {
		alert(info);
		obj.select();
		obj.focus();
		return true;
	}
	return false;
}

function showOpenWindow(url,name,width,height) {
	var iTop = (window.screen.availHeight-30-height)/2;       //获得窗口的垂直位置;
	var iLeft = (window.screen.availWidth-10-width)/2;           //获得窗口的水平位置;
   var oWin = window.open(url,name,'height='+height+'px,width='+width+'px,top='+iTop+',left='+iLeft+',toolbar=no,menubar=no,resizable=yes, scrollbars=no, location=no, status=no');
   oWin.document.title = name;
}


function getCount(url,obj) {
	alert("sss");
	http_request = false;
	if (window.XMLHttpRequest) {
		http_request = new XMLHttpRequest();
		if (http_request.overrideMimeType) {
			http_request.overrideMimeType("text/xml");
		}
	} else {
		if (window.ActiveXObject) {
			try {
				http_request = new ActiveXObject("Msxml2.XMLHTTP");
			}
			catch (e) {
				try {
					http_request = new ActiveXObject("Microsoft.XMLHTTP");
				}
				catch (e) {
				}
			}
		}
	}
	if (!http_request) {
		alert("Your brower can not suport Ajax,it's version must be higher than 5.0!");
		return false;
	}
	http_request.onreadystatechange = function() 
	{
		if (http_request.readyState == 4) {
			makeResponse(obj);
		}
	};
	ajaxUrl = url;
	http_request.open("get", ajaxUrl, true);
	http_request.setRequestHeader("If-Modified-Since", "0");
	http_request.send(null);
	//setTimeout(getCount(url,obj),1000);
	//window.setInterval("sendRequest(url,obj)", 1000);
	
}
function makeResponse(obj) {
	if (http_request.readyState == 4) {
		if (http_request.status == 0 || http_request.status == 200) {
			var result = http_request.responseText;
			if (result == "") {
				result = "System time failed!";
			}
			obj.innerHTML=result;
		} else {
		
		}
	}
}
		
		function subSomething()
		{
			if(document.readyState == "complete"){ //当页面加载状态为完全结束时进入
				// window.location.href="<%=path%>/storage/progress_bar.jsp";//这是你的操作;
				try
				  {   
				      var div =document.getElementById("progressbar");  
				      if(div !==null)
				      {
				          div.parentNode.removeChild(div);   
				          div=null;    
				          CollectGarbage(); 
				      }  
				  }
				  catch(e)
				  {   
				         alert("删除ID为"+nodeId+"的节点出现异常");
				  }   
				
			}
		}	


	

