<%@ page language="java" import="java.util.*,cn.labsoft.labos.utils.tree.*,cn.labsoft.labos.base.function.entity.*" pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<html>
	<head>
		<%@ include file="../../include/common.jsp"%>
		<title></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta name="Org list" content="PSPad" />
		<link rel="stylesheet" href="${basePath}utils/ztree/zTreeStyle.css" type="text/css">
		<script type="text/javascript" src="${basePath}utils/ztree/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="${basePath}utils/ztree/jquery.ztree.excheck-3.5.js">
		</script>
		<style>
.TabTable {
	padding-top: 0;
}

.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
	</head>
	<SCRIPT src="<%=basePath%>utils/tree/js/xtree.js">
	
	
	
	<script type="text/javascript" src="/Test/script/jquery.ztree.excheck-3.5.min.js">
	</script>
	</SCRIPT>
	<script language=javascript> 
	var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
	function closeMe(){
	  	api.close();
 	}
	function submitvalueforlist(){
		var parentId = '';
		var zTree = $.fn.zTree.getZTreeObj("zTree");
		nodes = zTree.getCheckedNodes(true);
		if(nodes.length==1){
			parentId=nodes[0].treeNid;
			$('#labFunctionVo_parentId').val(parentId);
			$('#parentFunForm').attr('action','<%=basePath%>function/labFunction/updateLabFunction4Parent.action');
			$('#parentFunForm').submit();
			W.refeshPage();
		}else{
			alert('<s:property value="getText('select.transition')"/>');
		}
		
	}
</script>
	<body class="" id="mainid" style="overflow-y: auto;">
		<div id="popup">
			<form name="parentFunForm" id="parentFunForm" action="" method="post">
				<input type="hidden" name="labFunctionVo.id" value="${labFunctionVo.id}">
				<input type="hidden" name="labFunctionVo.parentId" value="" id="labFunctionVo_parentId">
				<div class="">
					<div class="FUNCIONBARNEW">
						<table>
							<tr>
								<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
									<table cellspacing="0" cellpadding="0" border="0">
										<tr>
											<td>
												<l:a uri="function/labFunction/updateLabFunction4Parent.action" onclick="submitvalueforlist();" value="page.confirm" />
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					<table class="FormtableCon">
						<tr>
							<td>
								<div id="loading" style="margin-left: 135px; margin-top: 90px; font-size: 15px">
									<img src="${basePath}/img/loading_16x16.gif" align="absmiddle">
										<s:text name="data.loading"></s:text>
								</div>
								<div style="top: 95px; left: 135px; PADDING-BOTTOM: 5px; PADDING-LEFT: 50px; PADDING-RIGHT: 0px; PADDING-TOP: 5px; overflow: auto;">
									<ul id="zTree" turl="<%=basePath%>function/labFunction/ajaxTreeParent.action?labFunctionVo.id=${labFunctionVo.id}" radio="true" radioType="all">
									</ul>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</body>
</html>
