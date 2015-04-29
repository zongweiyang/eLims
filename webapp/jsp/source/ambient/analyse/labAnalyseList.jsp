<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../../../../jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script language="JavaScript">
			function submitvalue(actionstr){
		       	var df = document.labAmbientInfoForm;
		 		df.action=actionstr;
		  		df.submit();
		  	}
		  	function census(){
		  		var id=$("#paraId").val();
		  		var startTime = $('#startTime').val();
		  		var endTime = $('#endTime').val();
		  		if(id==''){
		  			alert("选择参数名称");
		  		}else{
		  		var url="${basePath}/ambient/labAmbientInfo/preJFreeChart.action?labAmbientInfoVo.labAmbientId="+id+'&labAmbientInfoVo.startTime='+startTime+'&labAmbientInfoVo.endTime='+endTime;
	  				$.dialog({
						id:'id',
						content:'url:'+url,
						title:'<s:text name="tjinfo"/>：',
						opacity:0.4,
						width:600, 
						height:500,
						lock:true,
						max:false,
						min:false
					 });
		  		}
		  	}
	  	 	function checkTime(){
		  		var startTime=$("#startTime").val();
		  		var endTime=$("#endTime").val();
		  		if(endTime<startTime){
		  			validate.tip('结束时间不能小于起始时间',$("#endTime"))
					return;
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
		</style>

	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<s:form theme="simple" action="" method="post" name="labAmbientInfoForm">
		
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
			cellpadding="0" border="0">
		<tr><td class="TLimg"></td><td class="TMimg"></td><td class="TRimg"></td></tr>
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
												<span><s:text name="top.index"/></span>
											</h2>
										</div>
												<div class="FUNCIONBARNEW">
					                      	<table>
					                      		<tr>
					                      			<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
					                      				<table cellspacing="0" cellpadding="0" border="0">
					                      					<tr>
					                      						<td><label><s:text name="param.name"/>：</label></td>
					                      						<td><s:select id="paraId" list="#request.listLabAmbientInfoVo" cssStyle="width:130px" name="labAmbientInfoVo.labAmbientId" value="'${labAmbientInfoVo.labAmbientId}'"  headerValue="--------全部---------" headerKey=""   theme="simple" listValue="labAmbientName" listKey="labAmbientId" onchange="submitAction(); return false;"></s:select> </td>
					                      						<td><label><s:text name="start.time"/>：</label></td>
					                      						<td>
					                      							<input type="text" id="startTime"  name="labAmbientInfoVo.startTime" value="${labAmbientInfoVo.startTime}"  class="Wdate" size="20"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,maxDate:'#F{$dp.$D(\'endTime\')}'});" />
					                      						</td>
															   	<td><label><s:text name="end.time"/>：</label></td>
					                      						<td>
					                      							<input type="text" name="labAmbientInfoVo.endTime" value="${labAmbientInfoVo.endTime}" id="endTime" class="Wdate" size="20"  onFocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true,minDate:'#F{$dp.$D(\'startTime\')}'});" onchange="checkTime()" />
					                      						</td>
										                      </tr>
									                    </table>
									                </td>
								                </tr>
					                      	</table>
					                    </div>
					                    <div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																	<td><a id="BtnPreview" class="zPushBtn" href="javascript:void(0);" onclick="submitAction(); return false;"><img height="20" width="20" src="${basePath}img/chakan.gif"/><b><s:text name="fun.query"/></b></a></td>
																	<td><a id="BtnPreview"  class="zPushBtn" href="javascript:void(0);" onclick="census();"><img height="20" width="20" src="${basePath}img/chakan.gif"/><b><s:text name="build.chart"/></b></a></td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="0"
											cellpadding="0">
											<thead>
												<tr>  
													<th>
														<img src="<%=basePath%>img/icon_drag.gif"/>
													</th>
													<th><s:text name="param.name"/></th>
													<th property="value"><s:text name="numeric"/></th>
													<th><s:text name="address"/></th>
													<th><s:text name="room"/></th>
													<th property="time"><s:text name="env.time"/></th>
												</tr>
											</thead>
											<tbody>
												<s:if test="pageResult!=null">
													<s:if test="pageResult.resultList!=null">
														<s:set name="alllist" value="pageResult.resultList" />
														<s:iterator value="#alllist" status="st">
													
															<tr>
																<td>
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td>${labAmbientName}</td>
																<td>${value}</a></td>
																<td>${address}</td>
																<td>${room}</td>
																<td class="c">${time}</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="7" align="center" valign="middle">
															<font color="red"><s:text name="lab.msg.none"/></font>
														</td>
													</tr>
												</s:else>
											</table>
										</div>
								</td>
							</tr>
							<tr>
					      <tr>
						<td align="center"><jsp:include
								page="/jsp/include/page.jsp?formName=labAmbientInfoForm"
								flush="true" /></td>
					       </tr>
				          </tr>
						</table>
				    </td>
			 <td class="MRimg"></td>
				</tr>
				<tr>
				<td class="FLimg"></td><td class="FMimg"></td><td class="FRimg"></td>
        </tr>
				
			</table>
		</s:form>
	 <%@ include file="../../../../jsp/include/foot.jsp"%> 
	</body>
</html>
