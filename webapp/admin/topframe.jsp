<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../jsp/include/common.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
		<script type="text/javascript">
		function changeLang(){
			var language = '<%=lang%>';
			
			if(language=='CN'){
				$.get('<%=basePath%>langAdmin.action?request_locale=en_US');
			}
			else if(language =='US'){
				$.get('<%=basePath%>langAdmin.action?request_locale=zh_CN');
			}
			window.top.location.reload();
		}
		function menu(obj){
			var linkNum=0;
			$("a[id*='MENUA_HREF']").each(function (linkNum){
				$(this).removeAttr("class","current");
				linkNum++;
			});
			$(obj).addClass("current");
		}
		$(function(){
			menu();
		});
	</script>
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

#nav li li {
	float: left;
	clear: left;
	width: 120px;
	background: #ccc;
	padding-bottom: 3px;
}

#nav ul {
	position: absolute;
}

#nav ul ul {
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

.float {
	float: left;
}

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
	</head>
	<body>
		<div id="Header">
			<table class="Headertoolsbar" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="left">
						<%-- <img src="<%=basePath%>admin/adminlogo.png"  /> --%>
						<div id="locales">
							<ul >
								<li >
								 <a href="<%=basePath%>langAdmin.action?request_locale=zh_CN" onclick="window.top.location.reload();" style="font-family:微软雅黑;"> 简体中文</a>    
								</li>
								<li> 
									 <a href="<%=basePath%>langAdmin.action?request_locale=en_US" onclick="window.top.location.reload();" style="font-weight:bold;" class="last"> English</a>     
								</li>
							</ul>
							    <div class="clear"></div>
						</div>
					</td>
					<td class="right">
						<div class="headertool">
							<ul class="topicon">
								<li>
									<a class="ti_lang" href="" onclick="changeLang();"></a>
								</li>
								<li>
									<a class="ti_loginout" href="<%=basePath%>admin/coreextend/extend/logout.action" target="_top"></a>
								</li>
							</ul>
							<input type="hidden" id="help" value="" />
							<input type="hidden" id="counts" value="0" />
						</div>
					</td>
				</tr>
			</table>
			<div id="lineid" class="sh st2">
				<div id="links" class="hsubmenu">
					<s:iterator value="submenuList" status="st">
						<s:if test="#st.index==0">
							<a href="<%=basePath%><s:property value="funurl"/>" class="" target="workarea"><s:property value="funname" /> </a>
						</s:if>
						<s:else>
							<a href="<%=basePath%><s:property value="funurl"/>" class="" target="workarea"><s:property value="funname" /> </a>
						</s:else>
					</s:iterator>
				</div>
			</div>
		</div>
	</body>
</html>