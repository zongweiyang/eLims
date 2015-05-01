<%@ page language="java" import="java.util.*,cn.labsoft.labos.utils.tree.*,cn.labsoft.labos.base.function.entity.*" pageEncoding="utf-8"%>

<html>
	<head>

		<%@ include file="/jsp/include/common.jsp"%>
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
	 	var funIds;
	 	var basePath = '${basePath}';
		
		function submitvalueforlist(){
			var zTree = $.fn.zTree.getZTreeObj("zTree");
			nodes = zTree.getCheckedNodes(true);
			var names='';
			var ids='';
			if(nodes.length>=1){
			for(var i=0;i<nodes.length-1;i++)
			{
			   names+=nodes[i].name+',';
			   ids+=nodes[i].treeNid+',';		   
			}}else{
			   alert('<s:property value="getText('selected.pls')"/>');
				return ;
			}
			names+=nodes[nodes.length-1].name;
			ids+=nodes[nodes.length-1].treeNid;
			$('#labOrgId',D).val(ids);
			$('#labOrgName',D).val(names);
			closeMe();
		}
		
		
</script>
	<body class="" id="mainid" style="overflow-y: auto;">
		<div id="popup">
			<form name="listForm" action="#" method="post" target="workarea">
				<input type="hidden" name="labOrgVo.id" value="${labOrgVo.id}" id="labOrgId" />
				<input type="hidden" name="labOrgVo.parentId" value="" id="labOrgVo_parentId" />
				<div class="">
					<div class="FUNCIONBARNEW">
						<table>
							<tr>
								<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
									<table cellspacing="0" cellpadding="0" border="0">
										<tr>
											<!-- <td>
												<a id="BtnPreview" class="zPushBtn" href="javascript:;"
												onclick="closeMe();return false;"><img
													height="20" width="20"
													src="<%=basePath%>img/zhongzhi.gif" /><b><s:text name="closednow"/></b> </a>
											</td> -->
											<td>
												<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="submitvalueforlist();return false;"><img height="20" width="20" src="<%=basePath%>img/accept.gif" /><b><s:text name="page.confirm"/></b> </a>
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
									<s:text name="data.loading"/>
								</div>
								<div style="top: 95px; left: 135px; PADDING-BOTTOM: 5px; PADDING-LEFT: 50px; PADDING-RIGHT: 0px; PADDING-TOP: 5px; overflow: auto; _height: 430px; min-height: 430px;">
									<ul id="zTree" turl="${basePath}/science/labSciLearn/treeLabOrg.action" checkbox="true" ></ul>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</body>
</html>
