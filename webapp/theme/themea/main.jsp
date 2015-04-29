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
				});
				setWorkWidthHeight();
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
				var curOrgId='${SessionContainer.orgId}';
				if(curOrgId==''){
					$(document).bind("contextmenu",function(e){   
			            return false;   
			        });   
					var url  = '${basePath}coreextend/extend/showLabOrg4Select.action?labUserVo.id=${labUserVo.id}';
					$.dialog({
						id:'id',
						content:'url:'+url,
						title:'<s:property value="getText('theme.hello.login')"/>',
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
				$('#work').height($('body').height() - $('#top').height());
				$('#workarea').width($('#work').width() - $('#left').width());
			}
		</script>
	</head>
	<body>
		<div id="top">
			<iframe scrolling="no" frameborder="0" src="<%=basePath%>coreextend/extend/topframe.action"></iframe>
		</div>
		<div id="work">
			<div id="left">
				<iframe scrolling="no" frameborder="0" src="<%=basePath%>coreextend/extend/leftframe.action"></iframe>
			</div>
			<div id="workarea">
				<iframe name="workarea" scrolling="yes" frameborder="0" src="<%=basePath%>jsp/include/progress_bar.jsp?url=/portlets/showPortlets.action"></iframe>
			</div>
		</div>
	</body>
</html>