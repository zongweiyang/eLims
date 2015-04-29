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
		<script type="text/javascript" src="${basePath}utils/ztree/jquery.ztree.excheck-3.5.js"></script>
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
	<SCRIPT src="<%=basePath%>utils/tree/js/xtree.js"></SCRIPT>
	<script language=javascript> 
	var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
		function closeMe(){
		  	api.close();
	 	}
	 	var basePath = '${basePath}';
		function submitvalueforlist(){
			var zTree = $.fn.zTree.getZTreeObj("zTree");
			nodes = zTree.getCheckedNodes(true);
	 		var funIds ='';
			for (var i=0, l=nodes.length; i < l; i++) {
				funIds+=nodes[i].treeNid;
				funIds+=',';
			}
			if(funIds.length>0){
				$("#funIds").val(funIds);
			}
			var type="0";
			if($("#synchro").attr("checked")=='checked'){
				type="1";
			}
			
			var df = document.listForm;
			df.action=basePath+"role/labRole/updateLabRoleTree.action?labRoleVo.isSynchro="+type ;
		    df.submit();
		}
	 	
		
</script>
	<body class="" id="mainid" style="overflow-y: auto;">
		<div id="popup">
			<form name="listForm" action="#" method="post">
				<input type="hidden" id="funIds" name="labRoleVo.funIds" />
				<div class="">
					<div class="FUNCIONBARNEW">
						<table>
							<tr>
								<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
									<table cellspacing="0" cellpadding="0" border="0">
										<tr>
											<td>
												<l:a uri="role/labRole/updateLabRoleTree.action" onclick="submitvalueforlist();" img="/img/filesave.gif" value="page.confirm" />
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
								<input type="checkbox" id="synchro"  />
								<s:text name="samepace.show"></s:text>(<font color="red"><s:text name="perference.user"></s:text></font>)
							</td>
						</tr>
						<tr>
							<td>
								<div id="loading" style="margin-left: 135px; margin-top: 90px; font-size: 15px">
									<img src="${basePath}/img/loading_16x16.gif" align="absmiddle">
									<s:text name="data.loading"></s:text>
								</div>
								<div style="top: 95px; left: 135px; PADDING-BOTTOM: 5px; PADDING-LEFT: 50px; PADDING-RIGHT: 0px; PADDING-TOP: 5px; overflow: auto;">
									<ul id="zTree" turl="${basePath}/role/labRole/treeLabRoleFun.action" checkbox="true"></ul>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</body>
</html>
