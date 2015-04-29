<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html style="overflow: hidden">
	<head>
		<!-- 代码来指定浏览器使用特定的文档模式，必须在除 title 外的其他 meta 之前使用 -->
		<meta http-equiv="X-UA-Compatible" content="IE=8" />
		<!-- 将浏览器限定到固定版本 -->
		<meta http-equiv="x-ua-compatible" content="IE=EmulateIE8">

		<%@ include file="/jsp/include/common.jsp"%>

		<link rel="icon" href="favicon.ico" type="image/x-icon" />
		<link rel=stylesheet href="<%=basePath%>js/qqmessage/css/message.css" type="text/css">

		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

		<title><s:text name="login.labossystem" /></title>
		<style>
* {
	padding: 0;
	margin: 0;
}

html,body {
	height: 100%;
	border: none 0;
	ie6PngRepair: true;
}

iframe {
	width: 100%;
	height: 100%;
	border: none 0;
}

#helpOnline {
	background: red;
	width: 100px;
	height: 100px;
	position: absolute;
	top: 0px;
	right: 0px;
	z-index: 1950;
}

#helpOnline .help-left {
	background: blue;
	width: 80px;
	height: 100%;
	float: left;
	cursor: pointer;
}

#helpOnline .help-right {
	background: green;
	width: 20px;
	height: 100%;
	float: left;
	cursor: move;
}

* {
	list-style-type: none;
	margin: 0px;
	padding: 0px;
	border: thin none;
}

#nav {
	font-size: 13px;
	background: url(<%=basePath%>img/navrepeat.png) repeat-x left top;
	height: 29px;
	font-family: "微软雅黑";
	font-weight: normal;
}

#nav a {
	display: block;
	line-height: 15px;
	width: 120px;
	height: 15px;
	padding: 7px 5px 7px 5px;
	background: none;
	color: #000;
	font-weight: normal;
	text-decoration: none;
	border: none;
}

#nav a:hover {
	background: #F9E3A7;
	color: #000;
}

#nav li.current a {
	background: #F9E3A7;
	color: #000;
	font-weight: bold
}

#nav li.current a:hover {
	background: #F9E3A7;
	color: #000;
}

#nav .secmenu {
	position: relative;
	background: #085E8E;
}

#nav .secmenu li a {
	font-size: 12px;
	font-weight: normal;
	background: url(<%=basePath%>img/navrepeat.png) repeat-x left top;
	color: #000;
	border: 1px solid #5BB538;
	border-top: 0;
	width: 120px;
}

#nav .secmenu li a:hover {
	font-weight: normal;
	background: #F9E3A7;
	color: #000;
}

#nav .thrmenu li a {
	background: url(<%=basePath%>img/navrepeat.png) repeat-x left top;
	color: #000;
	width: 150px;
}

#nav .thrmenu li a:hover {
	background: #F9E3A7;
	color: #000;
}

#nav li li {
	float: left;
	clear: left;
	width: 120px;
	background: red;
	background: none;
	border: 0;
}

#nav ul {
	position: relative;
}

#nav ul ul {
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

.float {
	float: left;
}

.Headertabsbar {
	position: absolute;
	left: 31px;
	top: 74px;
}
.soniconlist {}
.soniconlist li {}
.soniconlist li .soniconbox {text-align:center;width:90px;padding:5px;margin: 5px;float:left;}
.soniconlist li .cur {text-align:center;width:90px;padding:5px;margin: 5px;float:left;background-color: #99FF99;}

.soniconbox_sublist {clear:both;}
.soniconbox_sublist li {display:block;margin:0 10px;float:left;width:90px;}
.soniconbox_sublist li .soniconbox_sub {text-align:center;width:90px;padding:5px;margin: 5px;float:left;}
</style>
		<script>
	$(function() {
		$(".soniconbox").mouseover(function(){
			$('.soniconbox').removeClass('cur');
            $(this).addClass("cur"); 
        });
        $(".soniconbox_sub").mouseover(function(){    
            $(this).removeClass("soniconbox_sub").addClass("cur"); 
        })    
        .mouseout(function(){    
            $(this).removeClass("cur").addClass("soniconbox_sub"); 
        });
        $('.soniconbox').mouseover(function() {
        	$('.soniconbox_sublist').hide();
        });
		$('.soniconbox1').mouseover(
			function() {
				var $obj = $('#subLi'+$(this).attr('key'));
				var top = $(this).offset().top + $('.soniconlist').parent().height();
				$obj.parent().css('top',top+'px');
				$obj.show();
			}
		);
	});
</script>
	</head>
	<body style="margin: 3px;border: 1px solid #5BB538;">
		<div style="overflow:hidden;">
			<ul class="soniconlist">
				<s:iterator value="submenuList" id="first" status="st1">
					<li>
						<s:if test="${fn:length(first.subFunctionList)>0}">
							<div class="soniconbox soniconbox1" key="${st1.index }">
								<a href="#" target="workarea"><img style="vertical-align:middle;width:60px;height:60px;padding:0;margin:0;border:0;overflow:hidden;" src="<%=basePath %>theme/${themeType}/img/${imageUrl }"/>
								<p>${name}</p></a>
							</div>
							<div style="position:absolute;left:0px;">
								<ul isHide="true" id="subLi${st1.index }" style="display:none;" class="soniconbox_sublist">
									<s:iterator value="#first.subFunctionList" id="st" status="st3">
										<li>
											<s:if test="${fn:length(st.url)>0}">
												<div class="soniconbox_sub"><a href="${st.url}" target="workarea"><img  style="vertical-align:middle;width:60px;height:60px;padding:0;margin:0;border:0;overflow:hidden;" src="<%=basePath %>theme/${themeType}/img/${imageUrl }"/><p>${st.name}</p></a></div>
											</s:if>
											<s:else>
												<a href="javascript:void();" target="workarea"></a>
											</s:else>
										</li>
									</s:iterator>
								</ul>
							</div>
						</s:if>
						<s:else>
							<div class="soniconbox">
								<a href="${url}" target="workarea" style="text-align:center;"><img style="vertical-align:middle;width:60px;height:60px;padding:0;margin:0;border:0;overflow:hidden;" src="<%=basePath %>theme/${themeType}/img/${imageUrl }"/><p>${name}</p></a>
							</div>
						</s:else>
					</li>						
				</s:iterator>
			</ul>
		</div>
	</body>
</html>