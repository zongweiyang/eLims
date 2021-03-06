<%@ page language="java"
	import="java.util.*,cn.labsoft.labos.utils.tree.*" pageEncoding="UTF-8"%>
	<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<%@page isELIgnored="false"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	request.setAttribute("basePath", basePath);
%>
<!DOCTYPE html>
<html>
	<head>

		<meta http-equiv="content-type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="<%=basePath%>utils/ztree/css/demo.css"
			type="text/css">
		<link rel="stylesheet"
			href="<%=basePath%>utils/ztree/css/zTreeStyle/zTreeStyle.css"
			type="text/css">
		<script type="text/javascript"
			src="<%=basePath%>utils/ztree/jquery-1.4.4.min.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>utils/ztree/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>utils/ztree/jquery.ztree.excheck-3.5.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>utils/ztree/jquery.ztree.exedit-3.5.js"></script>

	</head>
	<SCRIPT type="text/javascript">
		var setting = {
			async: {
				enable: true,
				url:"<%=basePath%>lab/reagent/ajaxLabReagentZtree.action",
				autoParam:["treeNid"],
				dataFilter: filter
			},
			view: {
				expandSpeed:"",
				addHoverDom: addHoverDom,
				removeHoverDom: removeHoverDom,
				selectedMulti: false
			},
			edit: {
				enable: true
			},
			data: {
				simpleData: {
					enable: true
				}
				
			},
			callback: {
				beforeRemove: beforeRemove,
				beforeRename: beforeRename,
				onClick: zTreeOnClick,
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
		    $('#treeMain',parent.document).attr({'src':'<%=basePath%>lab/reagent/listLabReagent.action?labReagentVo.reagentTypeId = '+treeNode.treeNid});
		};
		
		function filter(treeId, parentNode, childNodes) {
			if (!childNodes) return null;
			for (var i=0, l=childNodes.length; i<l; i++) {
				childNodes[i].name = childNodes[i].name.replace(/\.n/g, '.');
			}
			return childNodes;
		}
		function beforeRemove(treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("zTree");
			zTree.selectNode(treeNode);
			if(confirm('<s:property value="getText('confirmdeleclassi')"/>' + treeNode.name)){
				$.ajax({
					url:'${basePath}lab/reagentType/ajaxNode4Delete.action',
					type:'POST',
					data:{'id':treeNode.treeNid},
					dataType:'text',
					success:function(data){
						if(data=="false"){
					        validate.tip('<s:property value="getText('delete.fail')"/>',$('#functionId'));
						}
					},
					error:function(){
					   validate.tip('<s:property value="getText('theme.net.fail')"/>',$('#functionId'));
					}
				});
			}
		}
				
		function beforeRename(treeId, treeNode, newName) {
			if (newName.length == 0) {
				validate.tip('<s:property value="getText('classifynamenotempty')"/>',$('#functionId'));
				return false;
			}
			$.ajax({
				url:'${basePath}lab/reagentType/ajaxNode4Update.action',
				type:'POST',
				data:{'id':treeNode.treeNid,'name':newName},
				dataType:'text',
				success:function(data){
					if(data=="false"){
					    validate.tip('<s:property value="getText('modify.fail')"/>',$('#functionId'));
					}
				},
				error:function(){
					validate.tip('<s:property value="getText('theme.net.fail')"/>',$('#functionId'));
				}
			});
			return true;
		}
 
		var newCount = 1;
		function addHoverDom(treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
			if ($("#addBtn_"+treeNode.id).length>0) return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.id
				+ "' title='<s:property value="getText('admin.add')"/>' onfocus='this.blur();'></span>";
			sObj.append(addStr);
			var btn = $("#addBtn_"+treeNode.id);
			if (btn) btn.bind("click", function(){
				$.ajax({
					url:'${basePath}lab/reagentType/ajaxNode4Add.action',
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
			});
		};
		function removeHoverDom(treeId, treeNode) {
			$("#addBtn_"+treeNode.id).unbind().remove();
		};

 
		$(document).ready(function(){
			$.fn.zTree.init($("#zTree"), setting);
		});
	</SCRIPT>
	<body>
		<div>
			<ul id="zTree" class="ztree" style="width: 190px;"></ul>
		</div>
	</body>
</html>