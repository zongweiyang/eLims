<%@ page language="java"
	import="java.util.*,cn.labsoft.labos.utils.tree.*" pageEncoding="UTF-8"%>
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
				url:"<%=basePath%>reference/labReference/ajaxLabReferenceZtree.action",
				autoParam:["treeNid"],
				dataFilter: filter
			},
			view: {expandSpeed:"",
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
		              
		                 //var childNodes = zTree.transformToArray(nodes[0]);  
		                 //zTree.expandNode(childNodes[1], true);  
		                // zTree.selectNode(childNodes[1]);  
		                // var childNodes1 = zTree.transformToArray(childNodes[1]);  
		                // zTree.checkNode(childNodes1[1], true, true);  
		                 firstAsyncSuccessFlag = 1;  
		           } catch (err) {  
		              
		           }  
		              
		     }  
		}
		function zTreeOnClick(event,treeId,treeNode) {
		    $('#treeMain',parent.document).attr({'src':'<%=basePath%>reference/labReference/listLabReference.action?labReferenceVo.referenceTypeId='+treeNode.treeNid});
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
			if(confirm("确认删除分类 " + treeNode.name + " 吗？")){
				$.ajax({
					url:'${basePath}reference/labRefType/ajaxNode4Delete.action',
					type:'POST',
					data:{'id':treeNode.treeNid},
					dataType:'text',
					success:function(data){
						if(data=="false"){
							validate.tip("删除失败.",$('#functionId'));
						}
					},
					error:function(){
						validate.tip("网络不通.",$('#functionId'));
					}
				});
			}
			else{
			    return false;
			}
		}
		
				
		function beforeRename(treeId, treeNode, newName) {
			if (newName.length == 0) {
				validate.tip("分类名称不能为空.",$('#functionId'));
				return false;
			}
			$.ajax({
				url:'${basePath}reference/labRefType/ajaxNode4Update.action',
				type:'POST',
				data:{'id':treeNode.treeNid,'name':newName},
				dataType:'text',
				success:function(data){
					if(data=="false"){
					validate.tip("修改失败.",$('#functionId'));
					}
				},
				error:function(){
					validate.tip("网络不通.",$('#functionId'));
				}
			});
			return true;
		}
 
		var newCount = 1;
		function addHoverDom(treeId, treeNode) {
			var sObj = $("#" + treeNode.tId + "_span");
			if ($("#addBtn_"+treeNode.id).length>0) return;
			var addStr = "<span class='button add' id='addBtn_" + treeNode.id
				+ "' title='新增' onfocus='this.blur();'></span>";
			sObj.append(addStr);
			var btn = $("#addBtn_"+treeNode.id);
			if (btn) btn.bind("click", function(){
				$.ajax({
					url:'${basePath}reference/labRefType/ajaxNode4Add.action',
					type:'POST',
					data:{'treeNid':treeNode.treeNid},
					dataType:'text',
					success:function(data){
						if(data.length==32){
							var zTree = $.fn.zTree.getZTreeObj("zTree");
								zTree.addNodes(treeNode, {id:'addBtn_'+ treeNode.tId,treeNid:data, pId:treeNode.id, name:"未命名"});
						}else{
						  validate.tip("增加异常.",$('#functionId'));
						}
					},
					error:function(){
						validate.tip("网络不通.",$('#functionId'));
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