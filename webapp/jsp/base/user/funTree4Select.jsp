<%@ page language="java"
	import="java.util.*,cn.labsoft.labos.utils.tree.*,cn.labsoft.labos.base.function.entity.*"
	pageEncoding="utf-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
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
	 	var basePath = '${basePath}';
		function submitvalueforlist(){
			var isOrg='';
			$('input[id*="orgId"]').each(function(){
				var isCheck=$(this).attr('checked');
				if(isCheck==true||isCheck=='checked'){
					isOrg=$(this).val()+",";
				}
			});
			if(isOrg.length<=1){
				alert('<s:property value="getText('fun.right')"/>');
				return ;
			}else{
				isOrg=isOrg.substring(0,isOrg.length-1);
			}
			var zTree = $.fn.zTree.getZTreeObj("zTree"),
			nodes = zTree.getCheckedNodes(true),
			funIds="";
			for (var i=0, l=nodes.length; i < l; i++) {
				funIds += nodes[i].treeNid + ",";
			}
			$('form').attr('action',basePath+'user/labUser/addLabUserFun.action?labUserVo.id =${labUserVo.id}&labUserVo.funId=' + funIds+'&labUserVo.orgId='+isOrg);
		    $('form').submit();
		}
		var msg='${messageInfo}';
		if(msg.length>0){
			alert(msg);
			W.flushPage();
		    closeMe();
		}
</script>
	<body  class="" id="mainid"  style="overflow-y: auto; height: 100%">  
		<div id="popup">
			<form name="listForm" action="#" method="post">
				<div class="">
					<div class="FUNCIONBARNEW">
						<table>
							<tr>
								<td class="blockTd"
									style="padding: 6px 10px; vertical-align: center;">
									<table cellspacing="0" cellpadding="0" border="0">
										<tr>
											<td>
												<l:a uri="user/labUser/addLabUsert.action" onclick="submitvalueforlist();" value="page.confirm"/>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					<table class="FormtableCon">
						<tr>
							<td><s:text name="msg.depart"/>：
								<s:iterator value="labUserVo.userOrgList" status="st">
									<input type="checkbox"   id="orgId${st.index}" value="${orgId}" checked="checked"/>  ${orgName}
								</s:iterator>
							</td>
						</tr>
						<tr>
							<td ><s:text name="fun.tree"/>：</td>
						</tr>
						<tr>
							<td >
								<div id="loading" style="margin-left:500px; margin-top:130px;font-size: 15px"><img  src="${basePath}/img/loading_16x16.gif" align="absmiddle">  数据正在加载中...</div>
								<div style="top:95px;left:135px; PADDING-BOTTOM: 5px; PADDING-LEFT: 50px; PADDING-RIGHT: 0px; PADDING-TOP: 5px; overflow:auto;">
									<ul id="zTree" turl="${basePath}/user/labUser/treeLabFunction.action" checkbox="true"></ul>
								</div>
							</td>
						</tr>
					</table>
				</div>
			</form>
		</div>
	</body>
</html>
