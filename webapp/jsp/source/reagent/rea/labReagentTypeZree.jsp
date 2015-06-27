<%@ page language="java" import="java.util.*,cn.labsoft.labos.utils.tree.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
request.setAttribute("basePath",basePath);
%>
<!DOCTYPE html>
<html>
  <head>
  	
  	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
  	<link rel="stylesheet" href="<%=basePath %>utils/ztree/css/demo.css" type="text/css">
	<link rel="stylesheet" href="<%=basePath %>utils/ztree/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="<%=basePath %>utils/ztree/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="<%=basePath %>utils/ztree/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=basePath%>utils/validate/validate-1.0.js"/></script>
	<script type="text/javascript" src="${basePath}utils/dialog/lhgcore.min.js"></script>
	<script type="text/javascript" src="${basePath}utils/dialog/lhgdialog.min.js?skin=blue"></script>
<style>
UL.ztree {
	margin-top: 0px;
}
.ztree li span.button.switch.level0 {
	visibility:hidden; 
	width:1px;
	}
.ztree li ul.level0 {
	padding:0; 
	background:none;
	}

div#rMenu {
	position:absolute; 
	visibility:hidden; 
	background-color: #33CC00;
	text-align: left;
	padding-left: 1px;
	padding-right: 1px;
	}
div#rMenu ul li{
	margin: 1px 0;
	padding: 0 5px;
	cursor: pointer;
	list-style: none outside none;
	background-color:#DDDDDD;
}
</style>
  </head>
  <SCRIPT type="text/javascript">
		var setting = {
			async: {
				enable: true,
				url:"<%=basePath %>reagent/labReagent/ajaxLabReagentZtree.action",
				autoParam:["treeNid"],
				dataFilter: filter
			},
			view: {
				expandSpeed:"",
				selectedMulti: false
			},
			data: {
				simpleData: {
					enable: true
				}
				
			},
			callback: {
				beforeRename: beforeRename,
				onClick: zTreeOnClick,
				onRightClick: OnRightClick,
				onAsyncSuccess: zTreeOnAsyncSuccess
			}
		};
		var firstAsyncSuccessFlag = 0;
		function zTreeOnAsyncSuccess(event, treeId, msg) { 
			var zTree = $.fn.zTree.getZTreeObj("zTree"); 
			if (firstAsyncSuccessFlag == 0) {  
		          try {  
		                 //调用默认展开第一个结点  
		                 var selectedNode = zTree.getSelectedNodes();  
		                 var nodes = zTree.getNodes();  
		                 zTree.expandNode(nodes[0], true);  
		                 firstAsyncSuccessFlag = 1;  
		           } catch (err) {  
		              
		           }  
		              
		     }  
		}
		function zTreeOnClick(event,treeId,treeNode) {
			if($('#'+treeNode.tId+'_span .rename').val()=='undefined'||$('#'+treeNode.tId+'_span .rename').val()==undefined){
				$('#treeMain',parent.document).attr({'src':'<%=basePath %>reagent/labReagent/listLabReagent.action?labReagentVo.reagentTypeId='+treeNode.treeNid});
			}
		};
		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}
		function removeTreeNode() {
			hideRMenu();
			var nodes = zTree.getSelectedNodes();
			if (nodes && nodes.length>0) {
				if (nodes[0].children && nodes[0].children.length > 0) {
					var msg = '<s:property value="getText('fathernoteld')"/>';
					alert(msg);
					return false;
				} else {
					var treeNode=nodes[0];
					if(confirm('<s:property value="getText('confirmdeleclassi')"/>' + treeNode.name)){
						$.ajax({
							url:'${basePath}reagent/labReaType/ajaxNode4Delete.action',
							type:'POST',
							data:{'id':treeNode.treeNid},
							dataType:'text',
							success:function(data){
								if(data=="false"){
							        validate.tip('<s:property value="getText('delete.fail')"/>',$('#functionId'));
								}else{
									zTree.removeNode(nodes[0]);
								}
							},
							error:function(){
							   validate.tip('<s:property value="getText('theme.net.fail')"/>',$('#functionId'));
							}
						});
					}
					else{
					     return false;
					}
				}
			}
		}
		function renameTreeNode(){
			hideRMenu();
			var nodes = zTree.getSelectedNodes();
			if (nodes && nodes.length>0) {
				var treeNode=nodes[0];
				var inputstr='<input type="text" class="rename" value="'+treeNode.name+'" id="' + treeNode.tId +'_treeNode" onblur="beforeRename(this);">';
				$('#'+treeNode.tId+'_span').html(inputstr);
				$('#' + treeNode.tId +'_treeNode').focus();
			}
		}
		function beforeRename(obj) {
			var val=$(obj).val();
			var nodes = zTree.getSelectedNodes();
			if (nodes && nodes.length>0) {
				var treeNode=nodes[0];
				var title=$('#'+treeNode.tId+'_a').attr('title');
				if (val.length == 0) {
					validate.tip('<s:property value="getText('classifynamenotempty')"/>',$(obj));
					$('#'+treeNode.tId+'_span').html(title);
					return false;
				}else{
					$('#'+treeNode.tId+'_span').html(val);
				}
				$.ajax({
					url:'${basePath}reagent/labReaType/ajaxNode4Update.action',
					type:'POST',
					data:{'id':treeNode.treeNid,'name':val},
					dataType:'text',
					success:function(data){
						if(data=="false"){
						    validate.tip('<s:property value="getText('modify.fail')"/>',$(obj));
						}
					},
					error:function(){
						validate.tip('<s:property value="getText('theme.net.fail')"/>',$(obj));
					}
				});
			}
			return true;
		}
 
		var zTree, rMenu;
		$(document).ready(function(){
			$.fn.zTree.init($("#zTree"), setting);
			zTree = $.fn.zTree.getZTreeObj("zTree");
			rMenu = $("#rMenu");
		});
		
		function OnRightClick(event, treeId, treeNode) {
			if (treeNode && !treeNode.noR) {
				zTree.selectNode(treeNode);
				showRMenu("node", event.clientX, event.clientY);
			}
		}
		function showRMenu(type, x, y) {
			$("#rMenu ul").show();
			if (type=="root") {
				$("#m_del").hide();
			} else {
				$("#m_del").show();
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});
			$("body").bind("mousedown", onBodyMouseDown);
		}
		function hideRMenu() {
			if (rMenu) rMenu.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
				rMenu.css({"visibility" : "hidden"});
			}
		}
		function addTreeNode() {
			hideRMenu();
			var treeNode=zTree.getSelectedNodes()[0]
			$.ajax({
				url:'${basePath}reagent/labReaType/ajaxNode4Add.action',
				type:'POST',
				data:{'treeNid':treeNode.treeNid},
				dataType:'text',
				success:function(data){
					if(data.length==32){
						var zTree = $.fn.zTree.getZTreeObj("zTree");
							zTree.addNodes(treeNode, {id:'addBtn_'+ treeNode.tId,treeNid:data, pId:treeNode.id, name:'<s:property value="getText('appratus.unknow')"/>'});
					}else{
					  validate.tip('<s:property value="getText('addexcepton')"/>',$('#functionId'));
					}
				},
				error:function(){
				    validate.tip('<s:property value="getText('theme.net.fail')"/>',$('#functionId'));
				}
			});
		}
	</SCRIPT>
<body>
	<div>
		<ul id="zTree"  class="ztree" style="width: 190px;"></ul>
	</div>
	<div id="rMenu">
		<ul>
			<li id="m_add" onclick="addTreeNode();">增加节点</li>
			<li id="m_del" onclick="removeTreeNode();">删除节点</li>
			<li id="m_eidt" onclick="renameTreeNode();">修改节点</li>
		</ul>
	</div>
</body>
</html>