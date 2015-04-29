<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
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
	<script language=javascript> 
	var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
		function closeMe(){
		  	api.close();
	 	}
	 	var basePath = '${basePath}';
		function submitvalueforlist(){
		var labSamType=$("#samTypeId").val();
		     var file=$('input[type="file"]').val();
		   if(file==''||file==null){ 
		      validate.tip('<s:property value="getText('file.nulled')"/>',$('#functionId'));
		   }
		    else{
		        var df=document.listForm;
			    df.action=basePath+'/sam/labSam/importLabSam.action?labSamVo.samTypeId='+labSamType;
		        df.submit();
		        closeMe();
		     }
		}
	 	
		
</script>
	<body  class="" id="mainid" style="overflow-y:auto;"  >  
		<div id="popup">
			<form name="listForm" action="#" method="post" target="workarea" enctype="multipart/form-data">
			<input type="text" value="${labSamVo.samTypeId}" name="labSamVo.samTypeId" id="samTypeId"/>
				<div class="">
					<div class="FUNCIONBARNEW">
						<table>
							<tr>
								<td class="blockTd"
									style="padding: 6px 10px; vertical-align: center;">
									<table cellspacing="0" cellpadding="0" border="0">
										<tr>
											<td>
												<a id="BtnPreview" class="zPushBtn" href="javascript:;"
												onclick="submitvalueforlist();return false;"><img
													height="20" width="20"
													src="<%=basePath%>img/accept.gif" /><b><s:text name="import"/></b> </a>
											</td>
										    <td ></td>
											<td>
												<a id="BtnPreview"  href="<%=basePath%>utils/upload/down.jsp?filename=导入模板.xls&url=${fileUrl}"><img
													height="20" width="20"
													src="<%=basePath%>img/icon_excel.gif" /><b><s:text name="module.download"/></b> </a>
											</td>
										</tr>
									</table>
								</td>
							</tr>
						</table>
					</div>
					<table class="FormtableCon">
					  <tr><td></td></tr>
					   <tr><td></td></tr>
						<tr>
						  <td>
						 <input type="file"  name="upload" id="file"/>
					      </td>
					   </tr>
					</table>
				</div>
			</form>
		</div>
	</body>
</html>
