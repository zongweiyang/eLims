<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/common.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
			.bodytable .oRight {
				padding-left: 0;
			}
			html {
				_overflow-x: hidden;
			}
		</style>
		<script>
			function submitforform(){
				$('form').submit();
			}
			function showLabItem4Select(){
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath %>sam/labSam/showLabItem4Select.action',
					title:'<s:property value="getText('cont.item.info')"/>',
					opacity:0.4,
					width:800, 
					height:500,
					lock:true,
					max:false,
					min:false
				 });
			}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="form">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
			<input type="hidden" name="labSamTypeVo.id" value="${labSamTypeVo.id }"/>
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
              	<tr>
					<td class="MLimg"></td>
		           	<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0"
								border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName}：
												<span><s:text name="lab.code.add"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
                                            <table>
                                                <tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<l:a uri="back" value="msg.back" />
													</td>
													<td>
														<l:a uri="sam/labSam/addLabSamTypeItem.action" value="lab.code.save" />
													</td>
												</tr>
                                             </table>
                                        </div>		
										<div class="Formtable">
											<div class="Formtabletitle">
												<span><s:text name="related.info"/></span>
											</div>
											<table class="FormtableCon">
												<tr>
													<td><label><s:text name="ans.item"/>：</label></td>
													<td><textarea rows="5" cols="60" name="labSamTypeVo.itemName" id="itemName" readonly="true" >${labSamTypeVo.itemName }</textarea><a href="javascript:;" onclick="showLabItem4Select()">选择项目</a> </td>
														<input type="hidden" id="itemId" name="labSamTypeVo.itemId" value="${labSamTypeVo.itemId }"/>
												</tr>
											</table>
										</div>
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
	 <%@ include file="/jsp/include/foot.jsp"%> 
	</body>
</html>		
