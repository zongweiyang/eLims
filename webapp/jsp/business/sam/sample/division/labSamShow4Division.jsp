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
<script>
		function ajax2TwoCode(){
				var id=$("#labSamMainId").val();
				var url  = '${basePath}sam/labSam/showLabSam4printTwoCode.action?labSamMainVo.id='+id;
				$.dialog({
					id:'roleId',
					content:'url:'+url,
					title:'<s:property value="getText('print.2d')"/>',
					opacity:0.4,
					width:600,
					height:400,
					max:false,
				    min:false,
					lock:true
				 });
			 }	
		 function ajax2BarCode(){
		 		var id=$("#labSamMainId").val();
				var url  = '${basePath}sam/labSam/showLabSam4printBarCode.action?labSamMainVo.id='+id;
				$.dialog({
					id:'roleId',
					content:'url:'+url,
					title:'<s:property value="getText('print.bar')"/>',
					opacity:0.4,
					width:600,
					height:400,
					max:false,
				    min:false,
					lock:true
				 });
			  }	
</script>
	</head>
	<body id="mainid">
		<form method="post" name="form" enctype="multipart/form-data" id="form">
		<input type="hidden" name="labSamMainVo.id" value="${labSamMainVo.id}" id="labSamMainId" />
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
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
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName}：
												<span><s:text name="look.check"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="back" value="msg.back" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable">
										<div class="Formtabletitle" onclick="$(this).next().toggle()" style="cursor: pointer;" title="点击此处，隐藏/显示该信息">
												<span><s:text name="base.info"/></span>
											</div>
											<table class="FormtableCon">
												<tr>
													<td>
														<label>
															<s:text name="task.no"/>：
														</label>
													</td>
													<td>
														${labSamMainVo.taskCode}
													</td>
													<td>
														<label>
															<s:text name="sam.number"/>：
														</label>
													</td>
													<td>
														${labSamMainVo.num }
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="cust.name"/>：
														</label>
													</td>
													<td>
														${labSamMainVo.customer}
													</td>
													<td>
														<label>
															<s:text name="call.people"/>：
														</label>
													</td>
													<td>
														${labSamMainVo.contacts }
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="called.info"/>：
														</label>
													</td>
													<td>
														${labSamMainVo.contactPhone }
													</td>
													<td>
														<label>
															<s:text name="sam.if"/>：
														</label>
													</td>
													<td>
													<s:if test="${labSamMainVo.isDivision == 'Y'}">
														<s:text name="lab.yes"/>
													</s:if>
													<s:if test="${labSamMainVo.isDivision == 'N'}">
														<s:text name="lab.no"/>
													</s:if>
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="upload.attachment"/>：
														</label>
													</td>
													<td colspan="">
														<s:if test="${fn:length(loadList)>0}">
															<s:iterator status="st2" value="#request.loadList" id="">
																<span>
																${name}
																<s:if test="${pdfName!=null}">
																	&nbsp;&nbsp;<a  target="_blank" href="<%=basePath%>utils/onlinepdf/index.jsp?doc=${pdfName}""><img src="<%=basePath %>/img/query.gif"/></a>
																</s:if>
																&nbsp;&nbsp;<a href="<%=basePath%>utils/upload/down.jsp?fileUrl=${path}&fileName=${name}"><img src="<%=basePath %>/img/export.gif"/></a>
															</span>&nbsp;&nbsp;&nbsp;&nbsp;
															</s:iterator>&nbsp;&nbsp;&nbsp;
														</s:if>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable oneEachDiv" >
										<div class="Formtabletitle">
											<span><s:text name="sam.info"/></span>
											<label style="float: right;padding-right: 10px;"></label>
										</div>
										<table class="FormtableCon" id="oneEach">
										<tr>
											<th><label><s:text name="origin.no"/></label></th>
											<th><label><s:text name="sam.no"/></label></th>
											<th><label><s:text name="sam.name"/></label></th>
											<th><label><s:text name="sam.classify"/></label></th>
											<th><label><s:text name="regular"/></label></th>
											<th><label><s:text name="save.style"/></label></th>
											<th><label><s:text name="sam.site"/></label></th>
											<th><label><s:text name="sam.performance"/></label></th>
											<th><label><s:text name="assigned"/></label></th>
											<th><label><s:text name="remark"/>&nbsp;&nbsp;&nbsp;&nbsp;</label></th>
										</tr>
										<s:iterator value="labSamMainVo.labSamVoList" status="st">
											<tr key="0">
												<td class="l">${seqNum }</td>
												<td class="c">${sampCode }</td>
												<td class="l">${name}</td>
												<td class="l">${samTypeName}</td>
												<td class="l">${specifications }</td>
												<td class="l">${saveMode }</td>
												<td class="l">${sampAddr }</td>
												<td class="l">${shape }</td>
												<td class="c">
													<s:if test="${isDestory=='Y'}"><s:text name="left.sam"/></s:if>
													<s:else><s:text name="sample.check"/></s:else>
												</td>
												<td class="l">${remark }</td>
											
											</tr>
										</s:iterator>
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
	<script language="javascript" type="text/javascript">
		function required ()  
		{
			
		}
	</script>
</html>
