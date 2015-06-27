<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<head>
<%@ include file="/jsp/include/common.jsp"  %>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title><s:text name="login.labossystem"/></title>
</head>

<style>

* {
list-style-type: none;
margin: 0px;
padding: 0px;
border: thin none;
}
#nav {
font-size: 12px;
opacity: 0.8;
}
#nav a {
display: block;
width: 100px;
height: 15px;
padding: 3px 5px 12px;
background: #666;
color: #fff;
text-decoration: none;
}
#nav a:hover {
background: #333;
}
#nav li li{
float: left;
clear: left;
width: 120px;
background: #ccc;
padding-bottom: 3px;
}
#nav ul{
position: absolute;
}
#nav ul ul{
position: absolute;
margin-left: 115px;
margin-top: -30px;
}
#nav ul {
display: none;
}
#nav li.show ul {
display: block;
}
#nav li.show li ul {
display: none;
}
#nav li li.show ul {
display: block;
}
.float{
	float:left;
}
</style>
<script type="text/javascript">
function changeLang(){
	var language = '<%=lang%>';
	
	if(language=='CN'){
		$.get('<%=basePath%>lang.action?request_locale=en_US');
	}
	else if(language =='US'){
		$.get('<%=basePath%>lang.action?request_locale=zh_CN');
	}
	window.top.location.reload();
}
function hideAndShow(){
    var size = window.parent.document.getElementById("bottomid").cols;
	if(size == '190,*'){
		$('.Headertoolsbar').hide();
		$('#Header').css('margin-top','-74px');
		$('.Headertabsbar',parent.parent.document).css('top','0px');
		$('.sfbtn').css('top','-1px');
		window.parent.document.getElementById("top").rows = '30,*';
		window.parent.document.getElementById("bottomid").cols = '0,*';
	}else{
		$('.Headertoolsbar').show();
		$('#Header').css('margin-top','0px');
		$('.Headertabsbar',parent.parent.document).css('top','74px');
		$('.sfbtn').css('top','74px');
		window.parent.document.getElementById("top").rows = '104,*';
	    window.parent.document.getElementById("bottomid").cols = '190,*';
	}
}
function closeWindow(){
	if(confirm('<s:property value="getText('theme.confirm.logout')"/>')){
		var ua=navigator.userAgent 
		var ie=navigator.appName=="Microsoft Internet Explorer"?true:false 
		if(ie){
			var IEversion=parseFloat(ua.substring(ua.indexOf("MSIE ")+5,ua.indexOf(";",ua.indexOf("MSIE ")))) 
			if(IEversion< 5.5){ 
			    var str  = '<object id=noTipClose classid="clsid:ADB880A6-D8FF-11CF-9377-00AA003B7A11">'; 
			    str += '<param name="Command" value="Close"></object>'; 
			    document.body.insertAdjacentHTML("beforeEnd", str); 
			    document.all.noTipClose.Click(); 
		    }else{ 
			    window.parent.parent.opener =null; 
			    window.parent.parent.close(); 
		    } 
		}else{ 
			window.close() 
		} 
	}
}

function openHelpDoc(){
	window.open("<%=basePath%>helpDoc/index.html");
}

function changeStyle(t,count,id) {
	var tem = eval(t);
	for(var i=1;i<=count;i++){
		var temTab = "Tab"+i;
	    if(i != tem){ 
		   document.getElementById(temTab).className = "";
		}else{
			document.getElementById(temTab).className = "current";
		}
		
	}
	$('#help').val('');
	$(window.parent.parent.document.body).find('#main_help').val('');
	$setMenuClass(id);
}
</script>
<script language="JavaScript">
	function menu(obj){
		var linkNum=0;
		$("a[id*='MENUA_HREF']").each(function (linkNum){
			$(this).removeAttr("class","current");
			linkNum++;
		});
		$(obj).addClass("current");
	}
</script>
<style>

/*Language related*/
#locales{
	width:170px;
	margin-top:10px;
	text-align:right;
	float:right;
    font-size:14px;
}
#locales ul{
    float:right;
    list-style:none;
    margin:0px;
    padding:0px;
}
#locales li{
    float:left;
    vertical-align:middle;
    padding:2px;
}
#locales li a{
    display: block;
	padding:0 10px;
	line-height: 12px;
    _line-height: 15px;
	border-right:1px solid #444;
	color:black;
}
*+html #locales li a{
    line-height: 15px !important;
}
#locales li a.last{
	border-right:none;
    padding-right:0px;
}
.clear{
	clear:both;
}
</style>
<script language="JavaScript">
	menu();
</script>
<body>
<div id="Header">
<table class="Headertoolsbar" cellspacing="0" cellpadding="0" border="0">
  <tr>			<td class="left">
					<%-- <img src="<%=basePath%>img/logo/1398701782241.png" /> --%>
  				</td>
				<td class="left">
					<%--  <img src="<%=basePath%>${session.SessionContainer.logoUrl}" /> --%>
				</td>

				<td class="right">
      <div class="headertool">
        <ul class="topicon">
        	<li><a  href="" onclick="changeLang();" class="ti_lang"></a></li>
			<li><a  href="<%=basePath%>portlets/showPortlets.action" onclick="" class="ti_index" target="workarea"></a></li>
          <li>
        <s:if test="${sessionScope.isDD==true}">
        	<a class="ti_loginout" href="javascript:void(0);" onclick="closeWindow();" target="_top"></a>
        </s:if>
        <s:else>
        	 <a class="ti_loginout" href="<%=basePath%>coreextend/extend/logout.action" target="_top"></a>
        </s:else>
        </li>
        </ul>
         <input type="hidden" id="help" value="" />
         <input type="hidden" id="counts" value="0" />
         <form action="#" target="workarea" method="post">

         </form>
      </div>
    </td>
  </tr>
</table>
	<div id="lineid" class="sh st2" >
		<div id="links" class="hsubmenu"> 
          		<s:iterator value="submenuList" status="st">
          			<s:if test="#st.index==0">
          				<a href="<%=basePath %><s:property value="funurl"/>" class="" target="workarea"><s:property value="funname"/></a>
          			</s:if>
          			<s:else>
          				<a href="<%=basePath %><s:property value="funurl"/>" class="" target="workarea"><s:property value="funname"/></a>
          			</s:else>
          		</s:iterator>
		</div>
	</div>
	<a class="sfbtn" onclick="hideAndShow();" ></a>
</div>
</body>
</html>