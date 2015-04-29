<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<!-- 代码来指定浏览器使用特定的文档模式，必须在除 title 外的其他 meta 之前使用 -->
		<meta http-equiv="X-UA-Compatible" content="IE=8"/>
		<link rel="icon" href="favicon.ico" type="image/x-icon" /> 
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<%@ include file="/jsp/include/common.jsp"%>
		<title><s:text name="login.labossystem"/></title>
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
			#nav {
				font-size: 13px;background:url(<%=basePath%>img/navrepeat.png) repeat-x left top;
				height:29px;font-family:"微软雅黑";font-weight:normal;
			}
			#nav a {
				display: block;
				line-height:15px;
				width: 100px;
				height: 15px;
				padding: 7px 5px 7px 5px;
				background:none;
				color: #000;
				font-weight:normal;
				text-decoration: none;
				border:none;
			}
			#nav a:hover {background:#F9E3A7;color:#000;}
			#nav li.current a {background:#F9E3A7;color:#000;font-weight:bold}
			#nav li.current a:hover {background:#F9E3A7;color:#000;}
			#nav .secmenu {
				position:relative;
				background:#085E8E;
			}
			#nav .secmenu li a {
				font-size:12px;font-weight:normal;
				background:url(<%=basePath%>img/navrepeat.png) repeat-x left top;
				color:#000;border:1px solid #5BB538;
				border-top:0;width:100px;
			}
			#nav .secmenu li a:hover {font-weight:normal;background:#F9E3A7;color:#000;}
			
			#nav .thrmenu li a {
				background:url(<%=basePath%>img/navrepeat.png) repeat-x left top;
				color:#000;width:100px;
			} 
			#nav .thrmenu li a:hover {background:#F9E3A7;color:#000;} 

			#nav li li{
				float: left;
				clear: left;
				width: 100px;
				background:none;
				border:0;
			}
			#nav ul{
				position: relative;
			}
			#nav ul ul{
				position: absolute;
				margin-left: 110px;
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
				position:absolute;left:31px;
			}
			iframe{
				width:100%;height:100%;
			}
			#top{
				height:90px;
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
				width:190px;
			}
			iframe {
				width:100%;height:100%;
				border:0px;
			}
		</style>
		<script>
			window.onbeforeunload = onbeforeunload_handler; 
			function onbeforeunload_handler(){ 
				 window.location.href="<%=basePath%>coreextend/extend/closeWin.action";
			}
			function logOut(){ 
				 window.location.href="<%=basePath%>coreextend/extend/logout.action";
			}
			$(function() {
				$(window).resize(function() {
					setWorkWidthHeight();
					setNavWidth();
				});
				$('#nav>li:eq(0)').addClass('current');
				$('#nav>li a').click(function() {
					$('#nav>li.current').removeClass('current');
					$(this).closest('.float').addClass('current');
				});
				setNavWidth();
				setWorkWidthHeight();
				$('#nav li').hover(
					function(){
						$(this).children('ul').show();
					},
					function(){
						$(this).children('ul').hide();
					}
				);
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
			function setWorkWidthHeight() {
				$('#work').height($('body').height() - $('#nav').height() - $('#top').height());
				$('#workarea').width($('#work').width() - $('#left').width());
			}
			//设置导航外层的宽度用于 菜单内容过多的时候 添加滚动效果
			function setNavWidth() {
				var tableLeft = $('table.Headertabsbar').offset().left;
				var _width = $('body').width() - tableLeft;
				
				var obj = $('table.Headertabsbar tr td');
				obj.eq(0).children().width(_width-obj.eq(1).width());
				$('table.Headertabsbar').width(_width);
				
			}
		</script>
	</head>
	<body>
		<div id="top">
			<iframe scrolling="no" frameborder="0" src="<%=basePath%>coreextend/extend/topframe.action"></iframe>
		</div>
		<div >
			<table class="Headertabsbar" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td><!--
						<div>
							<ul id="nav" style="width:10000px;">
								<s:iterator value="labUserVo.funVoList" id="first" status="st1">
									<li class="float">
										<s:if test="${fn:length(first.subFunctionList)>0}">
											<a href="javascript:;" target="workarea" style="text-align:center;">${name}</a>
											<ul class="secmenu">
												<s:iterator value="#first.subFunctionList" id="sec" status="st2">
													<li>
														<s:if test="${fn:length(sec.subFunctionList)>0}">
															<a href="javascript:void();" target="workarea">${sec.name}</a>
															<ul class="thrmenu">
																<s:iterator value="#sec.subFunctionList" id="the" status="st3">
																	<li>
																		<s:if test="${fn:length(the.url)>0}">
																			<a href="${the.url}" target="workarea">${the.name}</a>
																		</s:if>
																		<s:else><a href="javascript:void();" target="workarea">${the.name}</a></s:else>
																	</li>
																</s:iterator>
															</ul>
														</s:if>
														<s:else>
															<a href="${sec.url}" target="workarea">${sec.name}</a>
														</s:else>
													</li>
												</s:iterator>
											</ul>
										</s:if>
										<s:else>
											<a href="${url}" target="workarea" style="text-align:center;">${name}</a>
										</s:else>
									</li>
								</s:iterator>
							</ul>
						</div>
					--></td>
				</tr>
			</table>
		</div>
		<div id="work">
			<div id="left" style="display:block;">
				<iframe name="leftFrame"   scrolling="no" frameborder="0" src="<%=basePath%>coreextend/extend/leftframe.action"></iframe>
			</div>
			<div id="workarea">
				<iframe name="workarea" scrolling="yes" frameborder="0" src="<%=basePath%>jsp/include/progress_bar.jsp?url=/portlets/showPortlets.action"></iframe>
			</div>
		</div>
	</body>
</html>