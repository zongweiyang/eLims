<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title></title>
	</head>
	<body>
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

#Header .Headertoolsbar .left img {
	padding-top: 10px;
}

.menu {
	float: left;
}

.menu ul {
	padding: 0px;
	margin: 0px;
	padding-left: 20px;
}

.menu ul li {
	float: left;
	width: 85px;
	padding-bottom: 5px;
	margin-left:5px;
	text-align: center;
}
.menu ul .cur{
	background-color:#99CC66;
}
.menu ul .cur_click{
	background-color:#66CC99;
}

.menu ul li img {
	display: inline-block;
	width: 60px;
	height: 60px;
	padding-bottom: 2px;
}

#Header {
	padding-top: 12px;
}

.topicon {
	padding-top: 7px;
}
</style>
<script type="text/javascript">
$(function() {
	$(".menu_firstBtn").mouseover(function(){
		$('.menu_firstBtn').removeClass('cur');
        $(this).addClass("cur"); 
    }).mouseout(function(){
    	$(this).removeClass('cur');
    });
    $(".menu_firstBtn").click(function(){
    	$('.menu_firstBtn').removeClass('cur_click');
        $(this).addClass("cur_click"); 
    });
});
</script>

		<div id="Header">
			<table class="Headertoolsbar" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="left">
						<img src="<%=basePath%>${session.SessionContainer.logoUrl}"/>
					</td>
					<td class="right">
						<div class="menu">
							<ul>
								<s:iterator value="#session.SessionContainer.funList" id="fun" status="funs">
									<li class="menu_firstBtn">
										<a href="${fun.url}" target="workarea" style="text-align: center;">
											<img style="width: 60px; height: 60px;" src="<%=basePath%>theme/${themeType }/img/${fun.imageUrl }" />
											<p style="color: black;">
												${fun.name}
											</p>
										</a>
									</li>
								</s:iterator>
							</ul>
						</div>
						<div class="headertool">
							<ul class="topicon">
								<li>
									<a class="ti_loginout" href="<%=basePath%>coreextend/extend/logout.action" target="_top"></a>
								</li>
							</ul>
							<input type="hidden" id="help" value="" />
							<input type="hidden" id="counts" value="0" />
						</div>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>