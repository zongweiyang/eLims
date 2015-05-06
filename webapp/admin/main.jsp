<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<!-- 代码来指定浏览器使用特定的文档模式，必须在除 title 外的其他 meta 之前使用 -->
		<meta http-equiv="X-UA-Compatible" content="IE=8"/>
		<link rel="icon" href="favicon.ico" type="image/x-icon" /> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<%@ include file="/jsp/include/common.jsp"%>
		<title></title>
		<style>
			html,body{
				width:100%;height:100%;
				overflow:hidden;
			}
			ul li {
				list-style-type: none;
				margin: 0px;
				padding: 0px;
				border: thin none;
				padding:0; margin:0; 
			}
			#top{
				height:104px;
			}
			#navgationbar{
				height:30px;
				background:#61BA30;
			}
			#left,#workarea{
				float:left;
				height:100%;
			}
		
			#left{
				width:150px;
			}
			
			iframe {
				width:100%;height:100%;
				/* border:0px; */
			}
			#nav {
				font-size: 13px;
				background: url(<%=basePath%>img/navrepeat.png) repeat-x left top;
				height: 29px;
				font-family: "微软雅黑";
				font-weight: normal;
			}
			#MianFrame{
				border-left:1px solid grey; 
				border-bottom: none;
				border-right: none;
				border-top: none;
			}
			#workarea{
				cursor: w-resize;
			}
			#nav a {
				display: block;
				line-height: 15px;
				width: 150px;
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
			.float {
				float: left;
			}
			.Headertabsbar {
				position: absolute;
				top: 74px;
			}
		</style>
		<script>
			window.onbeforeunload = onbeforeunload_handler; 
			function onbeforeunload_handler(){
				window.location.href="<%=basePath%>admin/coreextend/extend/closeWin.action";
			}
			
			$(function() {
				$(window).resize(function() {
					setWorkWidthHeight();
				});
				setWorkWidthHeight();
				var widths = false;
				$('#workarea').mousemove(function(e){
					
				}).mousedown(function(e){
					widths = true;
					var x = e.pageX;
					 $(window).mousemove(function(e) {
						var offset = e.offsetX;
						if(offset != 0 && x < e.pageX){
							var width=$('#left').width() + 20;
							$('#left').width(width);
							$('#workarea').width($('#work').width() - $('#left').width());
						}
						else if(offset != 0 && x > e.pageX){
							var width=$('#left').width() - 20;
							$('#left').width(width);
							$('#workarea').width($('#work').width() - $('#left').width());
						}
				        $(window).unbind("mousemove");
				     });
				}).mouseup(function(){
					widths = false;
				    $(window).unbind("mousemove");
				});
				//绑定左侧隐藏和显示
				$('.sfbtn').click(
					function() {
						if ( $("#left").is(":hidden") ) {
							$('#workarea').width($('#work').width() - $('#left').width());
							$("#left").show();
						}
						else {
							$('#workarea').width($('#work').width());
							$("#left").hide();
						}
					}
				);
			});
			function setWorkWidthHeight() {
				$('#work').height($('body').height() - $('#top').height());
				$('#workarea').width($('#work').width() - $('#left').width());
			}
		</script>
	</head>
	<body>
		<table class="Headertabsbar" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td>
					<div>
						<ul id="nav" style="width: 10000px;">
							<s:iterator value="labUserVo.funVoList" id="first" status="st1">
								<li class="float">
									<a href="<%=basePath%>admin/coreextend/extend/leftframe.action?labFunctionVo.id=${id}" target="leftframe" style="text-align: center;">${name}</a>
								</li>
							</s:iterator>
						</ul>
					</div>
				</td>
			</tr>
		</table>
		<div id="top">
			<iframe scrolling="no" frameborder="0" src="<%=basePath%>admin/coreextend/extend/topframe.action"></iframe>
		</div>
		<div id="work">
			<div id="left">
				<iframe name="leftframe" scrolling="no" frameborder="0" src="<%=basePath%>admin/coreextend/extend/leftframe.action"></iframe>
			</div>
			<div id="workarea">
				<iframe id="MianFrame" name="workarea" scrolling="yes" frameborder="1" src="<%=basePath%>admin/coreextend/extend/mainframe.action"></iframe>
			</div>
		</div>
	</body>
</html>

