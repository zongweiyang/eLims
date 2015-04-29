<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html style="overflow: hidden">
<head>
<!-- 代码来指定浏览器使用特定的文档模式，必须在除 title 外的其他 meta 之前使用 -->
<meta http-equiv="X-UA-Compatible" content="IE=8"/>
<!-- 将浏览器限定到固定版本 -->
<meta http-equiv="x-ua-compatible" content="IE=EmulateIE8" >

<%@ include file="/jsp/include/common.jsp"%>

<link rel="icon" href="favicon.ico" type="image/x-icon" /> 
<link rel=stylesheet href="<%=basePath%>js/qqmessage/css/message.css" type="text/css">

<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title><s:text name="login.labossystem"/></title>
<style>
* { padding:0; margin:0; }
html, body{ height:100%; border:none 0; ie6PngRepair:true;}
iframe{ width:100%; height:100%; border:none 0; }
#helpOnline{
	background:red;width:100px;height:100px;position:absolute;top:0px;right:0px;z-index:1950;
}
#helpOnline .help-left{
	background:blue;width:80px;height:100%;float:left;cursor:pointer;
}
#helpOnline .help-right{
	background:green;width:20px;height:100%;float:left;cursor:move;
}
* {
list-style-type: none;
margin: 0px;
padding: 0px;
border: thin none;
}

#nav {
font-size: 13px;
}
#nav a {
display: block;
line-height:15px;
width: 120px;
height: 15px;
padding: 7px 5px 7px 5px;
background:none;
color: #fff;
font-weight:bold;
text-decoration: none;
border:none;
}
#nav a:hover {background:#81B900;color:#fff;}
#nav li.current a {background:#81B900;color:#fff;}
#nav li.current a:hover {background:#81B900;color:#fff;}
#nav .secmenu {
	position:relative;
	background:#085E8E;
}
#nav .secmenu li a {font-size:12px;font-weight:normal;background:#085E8E;color:#fff;border:1px solid #fff;border-top:0;width:120px;}
#nav .secmenu li a:hover {font-weight:normal;background:#81B900;color:#fff;}

#nav .thrmenu li a {background:#81B900;color:#fff;width:150px;} 
#nav .thrmenu li a:hover {background:#F9E3A7;color:#333;} 


#nav li li{
float: left;
clear: left;
width: 120px;background:red;
background:none;

border:0;
}
#nav ul{
position: relative;
}
#nav ul ul{
position: absolute;
margin-left: 130px;
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
.Headertabsbar{
	position:absolute;left:36px;top:74px;
}
</style>
<meta http-equiv="X-UA-Compatible" content="IE=EmulateIE7" />
<script>
	$(function(){
		$('#instantMessagingMin').click(function(){
			$(this).hide();
			$('#instantMessagingMain').show();		
		});
		$('#closeInstantMessagingMain').click(function(){
			$('#instantMessagingMain').hide();
			$('#instantMessagingMin').show();		
		});
		$('.instantMessagingMin2').click(function(){
			var url='<%=basePath%>helpDoc/help_index.jsp';
			var help=$('#main_help').val();
			if(typeof(help)!='undefined'&&help.length>0){
				url+="#"+help;
			}
			var dg=new J.dialog({ id:'help',page:url,rang:true,title:'LabOS使用帮助', drag: true,iconTitle:false,width:825,btnBar:false,cancelBtn:false,height:500,fixed:true});
			dg.ShowDialog();
		});
		var curOrgId='${SessionContainer.orgId}';
		if(curOrgId==''){
			$(document).bind("contextmenu",function(e){   
	            return false;   
	        });   
			var url  = '${basePath}coreextend/extend/showLabOrg4Select.action?labUserVo.id=${labUserVo.id}';
			$.dialog({
				id:'id',
				content:'url:'+url,
				title:'欢迎登陆！',
				opacity:0.4,
				max: false,                  
		        min: false,
		        cancel: false,   
				width:300,
				height:200,
				lock:true
			 });
		}
	});
	function closeTishi(){
		$('#lhgdgCover,#lhgdlg_tishi',parent.document).remove();
	}
	function okSubmitTishi() {
		$("#lhgfrm_Survey").contents().find("#tryOutForm").attr('action','<%=basePath%>sample/tryout/commitTryOut.action?StepName=ALL');
		$("#lhgfrm_Survey").contents().find("#tryOutForm").submit();
	}
</script>
<script type="text/javascript">

window.onbeforeunload = onbeforeunload_handler; 
function onbeforeunload_handler(){ 
	 window.location.href="<%=basePath%>coreextend/extend/closeWin.action";
}
$(function(){
	$('#nav li').hover(
		function(){
			$(this).children('ul').show();
		},
		function(){
			$(this).children('ul').hide();
		}
	);	
});
function menuFix() {
var sfEls = document.getElementById("nav").getElementsByTagName("li");
for (var i=0; i<sfEls.length; i++) {
sfEls[i].onmouseover=function() {
this.className+=(this.className.length>0? " ": "") + "show";
}
sfEls[i].onmouseout=function() {
this.className=this.className.replace(new RegExp("( ?|^)show\\b"), "");
}
}
}
$(function() {
	setNavWidth();
	var resizeTimeout;
	$(window).resize(function() {
		clearTimeout(resizeTimeout);
		resizeTimeout = setTimeout(function(){
			resizeTimeout = null;
			setNavWidth();
		},50);
	});
});
//window.onload=menuFix;
function setNavWidth() {
	var tableLeft = $('table.Headertabsbar').offset().left;
	var _width = $('body').width() - tableLeft;
	
	var obj = $('table.Headertabsbar tr td');
	obj.eq(0).children().width(_width-obj.eq(1).width());
	$('table.Headertabsbar').width(_width);
	
	if($('#nav>li').length*$('#nav>li:eq(0)').width() > $('#nav').parent().width()){
		$('#suofang').children().show();
	}
	else{
		$('#suofang').children().hide();
	}
}
function moveNavLiLeft() {
	var obj = $('#nav').parent();
	obj.animate({'scrollLeft':obj.scrollLeft()+$('#nav>li:eq(0)').width()},500);
}
function moveNavLiRight() {
	var obj = $('#nav').parent();
	obj.animate({'scrollLeft':obj.scrollLeft()-$('#nav>li:eq(0)').width()},500);
}
</script>
</head>
<body >
<input type="hidden" id="main_help" value="" />
<table class="Headertabsbar" cellspacing="0" cellpadding="0" border="0">
	<tr>
		<td>
			<div>
				<ul id="nav" style="width:10000px;">
					<s:iterator value="labUserVo.funVoList" id="first" status="st1">
						<li class="float">
							<s:if test="${fn:length(first.subFunctionList)>0}">
								<a href="javascript:void();" target="workarea" style="text-align:center;">${name}</a>
								<ul class="secmenu">
									<s:iterator value="#first.subFunctionList" id="sec" status="st2">
										<li>
											<s:if test="${fn:length(sec.subFunctionList)>0}">
												<a href="javascript:void();" target="workarea">${sec.name}</a>
												<ul class="thrmenu">
													<s:iterator value="#sec.subFunctionList" id="the" status="st3">
														<li>
															<s:if test="${fn:length(the.url)>0}">
																<a href="<%=basePath%>${the.url}" target="workarea">${the.name}</a>
															</s:if>
															<s:else><a href="javascript:void();" target="workarea">${the.name}</a></s:else>
														</li>
													</s:iterator>
												</ul>
											</s:if>
											<s:else>
												<a href="<%=basePath%>${sec.url}" target="workarea">${sec.name}</a>
											</s:else>
										</li>
									</s:iterator>
								</ul>
							</s:if>
							<s:else>
								<a href="<%=basePath%>${url}" target="workarea" style="text-align:center;">${name}</a>
							</s:else>
						</li>
					</s:iterator>
				</ul>
			</div>
		</td>
		<td id="suofang" valign="top">
			<div class="left" onclick="moveNavLiLeft();"></div>
			<div class="right" onclick="moveNavLiRight();"></div>
		</td>
	</tr>
</table>
<iframe src="<%=basePath%>theme/${themeType}/main_body.jsp"></iframe>
 <script>
	$(function(){
		$('#nav>li:eq(0)').addClass('current');
		$('#nav>li a').click(function() {
			$('#nav>li.current').removeClass('current');
			$(this).closest('.float').addClass('current');
		});
	});
</script>
</body>
</noframes></html>