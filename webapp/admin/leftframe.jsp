<%@page import="org.apache.struts.Globals"%>
<%@ page language="java" import="java.util.*,cn.labsoft.labos.utils.tree.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path + "/";
	request.setAttribute("basePath", basePath);
	Locale locale = (Locale)request.getSession().getAttribute(Globals.LOCALE_KEY);
	if(locale == null)locale = Locale.getDefault();
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="<%=basePath%>utils/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<script type="text/javascript" src="<%=basePath%>utils/ztree/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="<%=basePath%>utils/ztree/jquery.ztree.core-3.5.js"></script>
		<style type="text/css">
html,body {
	margin: 0;
	padding: 0;
	border: 0;
	outline: 0;
	font-weight: inherit;
	font-style: inherit;
	font-size: 100%;
	font-family: inherit;
	vertical-align: baseline;
}

body {
	color: #2f332a;
	font: 15px/ 21px Arial, Helvetica, simsun, sans-serif;
	background-color: none;
}

div.content_wrap {
	width: 600px;
	height: 380px;
}

div.content_wrap div.left {
	float: left;
	width: 250px;
}

div.content_wrap div.right {
	float: right;
	width: 340px;
}

div.zTreeDemoBackground {
	width: 250px;
	height: 362px;
	text-align: left;
}

.ztree {
	padding: 0px;
}
.ztree LI{
	width: 100%;
	padding-left:0px;
	padding-right: 0px;
}
.ztree LI UL{
	padding-left:0px;
}
.ztree LI SPAN.button{
	display:none;
}
.ztree LI a:hover{
	text-decoration:none;
}
.ztree li a.level0 {
	width: 100%; 
	height: 25px;
	text-align: center;
	padding-left:10px;
	display: block;
	background-color: #EEEEE0;
	border: 1px silver solid;
}

.ztree li a.level1 {
	width: 100%;
	height: 25px;
	text-align: center;
	padding-left:10px;
	background-color: #E0EEEE;
	border: 1px silver solid;
}

.ztree li a.cur {
	width: 100%;
	height: 25px;
	text-align: left;
	background-color: #FFA07A;
	border: 1px silver solid;
}
.ztree li a.cur span {
	display: block;
	color: black;
	padding-top: 2px;
	font-size: 12px;
	font-weight: normal;
	word-spacing: 2px;
}


.ztree li a.level2 {
	width: 100%; 
	height : 25px;
	text-align: left;
	background-color: #EEEEE0;
	border: 1px silver solid;
	height: 25px;
}

.ztree li a.level0 span {
	display: block;
	color: black;
	padding-top: 5px;
	font-size: 12px;
	font-weight: normal;
	word-spacing: 2px;
}

.ztree li a.level0 span.button {
	float: right;
	margin-left: 10px;
	visibility: visible;
	display: none;
}

.ztree li span.button.switch.level0 {
	display: none;
}

.ztree li a.level1 span {
	display: block;
	color: black;
	padding-top: 2px;
	font-size: 12px;
	font-weight: normal;
	word-spacing: 2px;
}
</style>
		<SCRIPT type="text/javascript">
		var curMenu = null;
		var localeStr = '<%=locale%>';
		var setting = {
				async: {
					enable: true,
					url:"<%=basePath%>admin/function/labFunction/ajaxTreeLabFunction4Admin.action?labFunctionVo.id=${labFunctionVo.id}",
					autoParam:["treeNid"],
					dataFilter: filter
				},
				view: {
					showIcon:false,
					showLine:false,
					dblClickExpand: false,
					selectedMulti: false,
					fontCss:{}
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					beforeExpand: beforeExpand,
					onExpand: onExpand,
					onClick: onClick,
					onAsyncSuccess: onChangeText
				}
		};
		
		var curExpandNode = null;
		function beforeExpand(treeId, treeNode) {
			var pNode = curExpandNode ? curExpandNode.getParentNode():null;
			var treeNodeP = treeNode.parentTId ? treeNode.getParentNode():null;
			var zTree = $.fn.zTree.getZTreeObj("zTree");
			for(var i=0, l=!treeNodeP ? 0:treeNodeP.children.length; i<l; i++ ) {
				if (treeNode !== treeNodeP.children[i]) {
					zTree.expandNode(treeNodeP.children[i], false);
				}
			}
			while (pNode) {
				if (pNode === treeNode) {
					break;
				}
				pNode = pNode.getParentNode();
			}
			if (!pNode) {
				singlePath(treeNode);
			}
 
		}
		function singlePath(newNode) {
			if (newNode === curExpandNode) return;
			if (curExpandNode && curExpandNode.open==true) {
				var zTree = $.fn.zTree.getZTreeObj("zTree");
				if (newNode.parentTId === curExpandNode.parentTId) {
					zTree.expandNode(curExpandNode, false);
				} else {
					var newParents = [];
					while (newNode) {
						newNode = newNode.getParentNode();
						if (newNode === curExpandNode) {
							newParents = null;
							break;
						} else if (newNode) {
							newParents.push(newNode);
						}
					}
					if (newParents!=null) {
						var oldNode = curExpandNode;
						var oldParents = [];
						while (oldNode) {
							oldNode = oldNode.getParentNode();
							if (oldNode) {
								oldParents.push(oldNode);
							}
						}
						if (newParents.length>0) {
							zTree.expandNode(oldParents[Math.abs(oldParents.length-newParents.length)-1], false);
						} else {
							zTree.expandNode(oldParents[oldParents.length-1], false);
						}
					}
				}
			}
			curExpandNode = newNode;
		}
 
		function onExpand(event, treeId, treeNode) {
			curExpandNode = treeNode;
		}
 		function onChangeText(event, treeId, treeNode, msg){
 			if(localeStr == 'en_US'){
	 			$('.ztree li a.level0').css('text-align','left');
	 			$('.ztree li a.level1').css('text-align','left');
	 			$('.ztree li a.level2').css('text-align','left');
	 			$('.ztree li a.cur').css('text-align','center');
 			}
 		}
		function onClick(e,treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("zTree");
			zTree.expandNode(treeNode, null, null, null, true);
			
			if(treeNode.isParent==true || treeNode.isParent=="true"){
			}else{
				$('#MianFrame',parent.document).attr({'src':'<%=basePath%>'+treeNode.curl});
		   	}
   			$('.cur').each(function(){
	   			if(null!=curMenu&&curMenu.level == 0 ){
	   				$(this).removeClass('cur');
	   				$(this).addClass('level0');
	   			}else {
		   			$(this).removeClass('cur');
		   			$(this).addClass('level1');
	   			}
	   		});
		   	if(treeNode.isParent == false){
		   		var a = $('#'+treeNode.tId+'_a');
			   	a.removeClass('level0');
			   	a.addClass('cur');
			   	curMenu=treeNode;
		   	}
		}
		
		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}
 
		$(document).ready(function(){
			$.fn.zTree.init($("#zTree"), setting);
		});
		
	</SCRIPT>
	</head>
	<body>
		<div>
			<ul id="zTree" class="ztree" style="width: 100%"></ul>
		</div>
	</body>
</html>