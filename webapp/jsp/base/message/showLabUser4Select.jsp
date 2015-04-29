<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
		<meta http-equiv="Pragma" contect="no-cache" />
		<%@ include file="/jsp/include/common.jsp"%>
		<script>
			var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
			
			function closeMe(){
			  	api.close();
		 	}
		 	function ok(){
		 	var str=$("#txtListContentStrs").val();
		 	var ids=$("#txtListContentIds").val();
		 		var receiverId=$("#receiverId",D).val(ids);
		 		var receiverName=$("#receiverName",D).val(str);
		 		closeMe();
		 	};
		 	
		 	function doSelectStr(name,username,obj){
		 	var content = $("#txtListContentStrs").val();
			var objflag = obj.checked;
			var returnString;
		
	    	if(objflag){
	    		if(content.length > 0){
	    			returnString = content.concat("," ,name);
	    		}else{
	    			returnString = name;
	    			}
	    	}else{
	
	    		if(content.indexOf(name)> 0){
	    			if(content.indexOf(",")>=0){
					
	    				returnString = content.replace(","+name,"");
	    			}else{
	    				returnString = content.replace(name,"");
	    			}
	    		}else{
					if(content.indexOf(",")>=0){
				    	returnString = content.replace(name+",","");
					}else{
					    returnString = content.replace(name,"");
					}
				}
	 	}
	 	$("#txtListContentStrs").val(returnString);	
	 	}
	 	function doSelectId(name,userid,obj){
		 	var content = $("#txtListContentIds").val();
			var objflag = obj.checked;
			var returnString;
		
	    	if(objflag){
	    		if(content.length > 0){
	    			returnString = content.concat("," ,userid);
	    		}else{
	    			returnString = userid;
	    		}
	    	}else{
	
	    		if(content.indexOf(userid)> 0){
	    			if(content.indexOf(",")>=0){
					
	    				returnString = content.replace(","+userid,"");
	    			}else{
	    				returnString = content.replace(userid,"");
	    			}
	    		}else{
					if(content.indexOf(",")>=0){
				    	returnString = content.replace(userid+",","");
					}else{
					    returnString = content.replace(userid,"");
					}
				}
	    	}
	    	$("#txtListContentIds").val(returnString);	
	    		      
			
	 	}	
	 	
	 	$().ready(function(){
		var txtListContentIds = $("#receiverId",D).val();
		$("#txtListContentIds").val($("#receiverId",D).val());	
		$("#txtListContentStrs").val($("#receiverName",D).val());	
		var attIds = txtListContentIds.split(',');
		for(var i=0;i<attIds.length;i++){
			$('input[name="labUserVo.ids"]').each(function(){
				if($(this).val() == attIds[i]){
					$(this).prop('checked',true);
				}
			});
		 }
		});
		 function check_all(obj)
			{
				$("#txtListContentIds").val("");
			    $("#txtListContentStrs").val("");	
			    var checkboxs = $('input[name="labUserVo.ids"]');
			    for(var i=0;i<checkboxs.length;i++)
			    {
			      doSelectStr($(checkboxs[i]).attr('strName'),$(checkboxs[i]).attr('strName'),checkboxs[i]);
			      doSelectId($(checkboxs[i]).attr('strId'),$(checkboxs[i]).attr('strId'),checkboxs[i]);
			    }
		}	
		</script>
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}

#roletext {
	width: 70px;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis; /* 支持 IE */
}
</style>

	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="labRoleForm">
			<table id="bodyTable" class="bodytable_pop" width="98%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
				<tr>
					<td class="MLimg"></td>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="">
										<!-- 按钮条 结束 -->
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="ok();return false;"><img height="20" width="20" src="<%=basePath%>img/accept.gif" /><b><s:text name="msg.confirm"></s:text></b> </a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="listTable_pop" cellspacing="0" cellpadding="0" width="800">
											<thead>
												<tr>
													<th>
														<input type="checkbox" id="allCheckBox" name="labUserVo.ids" onclick="if(this.checked==true) { checkAll('labUserVo.ids'); } else { clearAll('labUserVo.ids'); } check_all(this);" />
													</th>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th>
														<s:text name="msg.depart"></s:text>
													</th>
													<th>
														<s:text name="msg.username"/>
													</th>
													<th>
														&nbsp;
													</th>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th>
														<s:text name="msg.depart"></s:text>
													</th>
													<th>
														<s:text name="msg.username"/>
													</th>
												</tr>
											</thead>
											<tbody>
												<s:iterator value="#request.userList" status="st">
													<s:if test="${(st.index+1)%2!=0}">
														<tr>
													</s:if>
													<td class="c">
														<input type="checkbox" name="labUserVo.ids" id="ids" value="${id}" strName="${name }" strId="${id}" onclick="doSelectStr('<s:property value="name" />','<s:property value="name" />',this);doSelectId('<s:property value="id" />','<s:property value="id" />',this);" />
													</td>
													<td class="c">
														${pageSizex* currenPagex+st.index+1}
													</td>
													<td>
														${orgName}
													</td>
													<td class="1">
														${name}
													</td>
													<s:if test="${(st.index+1)%2==0}">
														</tr>
														<tr>
													</s:if>

												</s:iterator>
												<tr>
													<td align="center" colspan="8">
														<textarea rows="3" cols="100" name="txtListContentStrs" id="txtListContentStrs" style="color: #333; font-size: 12px; padding: 5px;" readonly></textarea>
														<input type="hidden" name="txtListContentIds" id="txtListContentIds" />
													</td>
												</tr>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
						</table>
					</td>
					<td class="MRimg"></td>
				</tr>
				<tr>
					<td class="FLimg"></td>
					<td class="FMimg"></td>
					<td class="FRimg"></td>
				</tr>
			</table>
		</form>
	</body>
</html>
