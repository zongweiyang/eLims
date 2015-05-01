<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
			.bodytable .oRight {
				padding-left: 0;
			}
			html {
				_overflow-x: hidden;
			}
		</style>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="labQuaInitAuditPlanForm" id="form">
			<input type="hidden" value="${labQuaInitAuditPlanVo.id}" name="labQuaInitAuditPlanVo.id" id="id" />
			<input type="hidden" value="${labQuaInitAuditPlanVo.parentId}" name="labQuaInitAuditPlanVo.parentId" id="parentId" />
			<input name="labQuaInitAuditPlanVo.key"  type="hidden" value="${labQuaInitAuditPlanVo.key}" />
			<input type="hidden" value="${labQuaInitAuditPlanVo.rank}" name="labQuaInitAuditPlanVo.rank" id="rank" />
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
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
												${funName }：
												<span>检查内容>>检查重点&nbsp;&nbsp;[<font color="red">修改</font>]</span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
                                            <table>
                                                <tr>
                                                 	<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
                                                  		<table cellspacing="0" cellpadding="0" border="0">
                                                   			<tr>
                                                      			<td>
                                                      				<a id="BtnEdit" class="zPushBtn" href="javascript:void(0);" onclick="window.location.href='<%=basePath%>quality/initPlan/listLabQuaInitAuditPlan4Child.action?labQuaInitAuditPlanVo.rank=3&labQuaInitAuditPlanVo.parentId=${labQuaInitAuditPlanVo.parentId }&labQuaInitAuditPlanVo.key=${labQuaInitAuditPlanVo.key }';"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"/></b> </a>                    
																</td>
                                                      			<td>
                                                      				<l:a uri="quality/initPlan/updateLabQuaInitAuditPlan4Child.action" onclick="goAction('updateLabQuaInitAuditPlan4Child.action');" value="lab.code.save"/>
                                                      			</td>
                                                    		</tr>
                                                		</table>
                                             		</td>
                                             	</tr>
                                             </table>
                                        </div>		
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span>初始化内部要素</span>
											</div>
											<table class="FormtableCon">
										 		<tr>
										 			<td class="r" width="120"><label>检查重点：</label></td>
										 			<td>
										 				<textarea cols="50" rows="4" name="labQuaInitAuditPlanVo.name" valType="required" msg="检查重点不能为空" id="name">${labQuaInitAuditPlanVo.name}</textarea>
										 			</td>
										 		</tr>
											</table>
										</div>
		                  				<!-- 表单型表格（用于新增/修改页面）结束 -->
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