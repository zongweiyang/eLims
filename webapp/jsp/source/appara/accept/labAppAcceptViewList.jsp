<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script type="text/javascript">
        function submitvalue(actionstr){
            var df = document.forms[0];
            df.action = actionstr;
            df.submit();
        }
	function doUrl(url){
	  	window.location.href=url;	
	  }
	  </script>
		<style>
.ww100 {
	width: 120px;
}
</style>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td class="MLimg"></td>
				<td id="bodyCell" class="oRight">
					<div class="myworkingbox">
						<div class="myworkingboxttitle">
							<h2>
								${funName}：
								<span><s:text name="look.check"/></span>
							</h2>
						</div>
						<form action="" method="post" name="customeractivitiesForm" id="form">
							<div class="FUNCIONBARNEW">
								<table>
									<tr>
										<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
											<table cellspacing="0" cellpadding="0" border="0">
												<tr>
													<td>
														<a id="BtnPreview" class="zPushBtn" onclick="submitvalue('/appara/listLabAppara.action');return false;"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"/></b> </a>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
							<div class="TabTable">
								<div class="TabTableNAV">
									<ul>
										<li id="li01" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/showLabAppara.action?labApparaVo.typeId=${labApparaAcceptVo.typeId}&labApparaVo.id=${labApparaAcceptVo.appId}');return false;"><span><s:text name="base.info"/></span> </a>
										</li>
										<li id="li11" class="currenttab">
											<a href="#"><span><s:text name="checking.info"/></span> </a>
										</li>
										<li id="li02" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/prov/listApparaProv4View.action?labApparaProvVo.typeId=${labApparaAcceptVo.typeId}&labApparaProvVo.labAppId=${labApparaAcceptVo.appId}');return false;"><span><s:text name="accid.recd"/></span> </a>
										</li>
										<li id="li12" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/test/listLabApparaTest4View.action?labApparaTestVo.typeId=${labApparaAcceptVo.typeId}&labApparaTestVo.appId=${labApparaAcceptVo.appId}');return false;"><span><s:text name="check.recod"/></span> </a>
										</li>
										<li id="li04" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/edit/listLabApparaEdit4View.action?labApparaEditVo.typeId=${labApparaAcceptVo.typeId}&labApparaEditVo.appId=${labApparaBorVo.appId}');return false;"><span><s:text name="repair.recd"/></span> </a>
										</li>
										<li id="li05" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/useing/listLabAppUse4View.action?labApparaUseVo.typeId=${labApparaAcceptVo.typeId}&labApparaUseVo.appId=${labApparaAcceptVo.appId}');return false;"><span><s:text name="use.record"/></span> </a>
										</li>
										<li id="li06" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/open/listLabApparaOpen4View.action?labApparaOpenVo.typeId=${labApparaAcceptVo.typeId}&labApparaOpenVo.appId=${labApparaAcceptVo.appId}');return false;"><span><s:text name="start.record"/></span> </a>
										</li>
										<li id="li07" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/stop/listLabLabApparaStop4View.action?labApparaStopVo.typeId=${labApparaAcceptVo.typeId}&labApparaStopVo.appId=${labApparaAcceptVo.appId}');return false;"><span><s:text name="stop.record"/></span> </a>
										</li>
										<li id="li08" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/borrow/listLabApparaBor4View.action?labApparaBorVo.typeId=${labApparaAcceptVo.typeId}&labApparaBorVo.appId=${labApparaAcceptVo.appId}');return false;"><span><s:text name="borrow.record"/></span> </a>
										</li>
										<li id="li09" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/drop/listLabApparaDrop4View.action?labApparaDropVo.typeId=${labApparaAcceptVo.typeId}&labApparaDropVo.appId=${labApparaAcceptVo.appId}');return false;"><span><s:text name="drop.record"/></span> </a>
										</li>
									</ul>
								</div>
								<div class="TabTableBOX01 b" id="Tab01">
									<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
									<input type="hidden" name="labApparaVo.typeId" value="${labApparaAcceptVo.typeId}"/>
										<tr>
											<td>
												<div>
													<table class="myworkingboxttable" cellspacing="1" cellpadding="0">
														<thead>
															<tr>
																<th>
																	<s:text name="lab.sequence"/>
																</th>
																<th>
																	<s:text name="app.name"/>
																</th>
																<th>
																	<s:text name="app.no"/>
																</th>
																<th>
																	<s:text name="app.style"/>
																</th>
																<th>
																<s:text name="lab.code.ops"/>
																</th>
															</tr>
														</thead>
														<tbody>
															<s:if test="pageResult!=null">
																<s:if test="pageResult.resultList!=null">
																	<s:set name="alllist" value="pageResult.resultList" />
																	<s:iterator value="#alllist" status="st">

																		<tr>
																			<td class="c">
																				<s:property value="#st.index+1" />
																			</td>
																			<td class="l">
																				<s:property value="appName" />
																			</td>
																			<td class="c">
																				<s:property value="appNo" />
																			</td>
																			<td class="c">
																				<s:property value="spec" />
																			</td>
																			<td class="c">
																				<l:a href="#" uri="appara/accept/showLabAppAccept.action?labApparaAcceptVo.id=${id}&labApparaAcceptVo.typeId=${labApparaAcceptVo.typeId}" value="look.check" />
																			</td>
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
														</tbody>
													</table>
												</div>
											</td>
										</tr>
										<tr>
											<td align="center"><jsp:include page="/jsp/include/page.jsp?formName=customeractivitiesForm" flush="true" /></td>
										</tr>
									</table>
								</div>
							</div>
						</form>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>
