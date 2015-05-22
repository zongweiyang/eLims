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
            var path = '${basePath}';
            df.action = path+actionstr;
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
								${funName }：
								<span><s:text name="look.check"/></span>
							</h2>
						</div>
						<form action="" method="post" name="appForm" id="form">
							<input type="hidden" name="labApparaVo.typeId" value="${labApparaVo.typeId}" />
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
										<li id="li01" class="currenttab">
											<a href="#"><span><s:text name="base.info"/></span> </a>
										</li>
										<li id="li11" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/accept/listLabAppAccept4View.action?labApparaAcceptVo.typeId=${labApparaVo.typeId}&labApparaAcceptVo.appId=${labApparaVo.id}');return false;"><span><s:text name="checking.info"/></span> </a>
										</li>
										<li id="li02" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/prov/listApparaProv4View.action?labApparaProvVo.typeId=${labApparaVo.typeId}&labApparaProvVo.labAppId=${labApparaVo.id}');return false;"><span><s:text name="accid.recd"/></span> </a>
										</li>
										<li id="li12" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/test/listLabApparaTest4View.action?labApparaTestVo.typeId=${labApparaVo.typeId}&labApparaTestVo.appId=${labApparaVo.id}');return false;"><span><s:text name="check.recod"/></span> </a>
										</li>
										<li id="li04" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/edit/listLabApparaEdit4View.action?labApparaEditVo.typeId=${labApparaVo.typeId}&labApparaEditVo.appId=${labApparaVo.id}');return false;"><span><s:text name="repair.recd"/></span> </a>
										</li>
										<li id="li05" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/useing/listLabAppUse4View.action?labApparaUseVo.typeId=${labApparaVo.typeId}&labApparaUseVo.appId=${labApparaVo.id}');return false;"><span><s:text name="use.record"/></span> </a>
										</li>
										<li id="li06" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/open/listLabApparaOpen4View.action?labApparaOpenVo.typeId=${labApparaVo.typeId}&labApparaOpenVo.appId=${labApparaVo.id}');return false;"><span><s:text name="start.record"/></span> </a>
										</li>
										<li id="li07" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/stop/listLabLabApparaStop4View.action?labApparaStopVo.typeId=${labApparaVo.typeId}&labApparaStopVo.appId=${labApparaVo.id}');return false;"><span><s:text name="stop.record"/></span> </a>
										</li>
										<li id="li08" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/borrow/listLabApparaBor4View.action?labApparaBorVo.typeId=${labApparaVo.typeId}&labApparaBorVo.appId=${labApparaVo.id}');return false;"><span><s:text name="borrow.record"/></span> </a>
										</li>
										<li id="li09" class="">
											<a href="#" onclick="doUrl('<%=basePath%>appara/drop/listLabApparaDrop4View.action?labApparaDropVo.typeId=${labApparaVo.typeId}&labApparaDropVo.appId=${labApparaVo.id}');return false;"><span><s:text name="drop.record"/></span> </a>
										</li>
									</ul>
								</div>
								<div class="TabTableBOX01 b" id="Tab01">
									<table class="FormtableCon">
										<tr>
											<td>
												<label>
													<s:text name="app.no"/>：
												</label>
											</td>
											<td>
												<input disabled="disabled" value="${labApparaVo.no}" id="no" type="text" size="20" />
											</td>
											<td>
												<label>
													<s:text name="build.no"/>：
												</label>
											</td>
											<td>
												<input disabled="disabled" value="${labApparaVo.code}" id="code" type="text" size="20" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="app.name"/>：
												</label>
											</td>
											<td>
												<input disabled="disabled" value="${labApparaVo.name}" id="name" type="text" size="20" />
											</td>
											<td>
												<label>
													<s:text name="supplier"/>：
												</label>
											</td>
											<td>
												<input disabled="disabled" value="${labApparaVo.supplier}" id="code" type="text" size="20" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="making.com"/>：
												</label>
											</td>
											<td>
												<input disabled="disabled" type="text" value="${labApparaVo.producer}" size="20" id="producer" />
											</td>
											<td>
												<label>
													<s:text name="checking.config"/>：
												</label>
											</td>
											<td>
												<input value="${labApparaVo.testProperty}" disabled="disabled" type="text" size="20" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="regular.sys"/>：
												</label>
											</td>
											<td>
												<input disabled="disabled" value="${labApparaVo.spec}" id="specification" type="text" size="20" />
											</td>
											<td>
												<label>
													<s:text name="place.site"/>：
												</label>
											</td>
											<td>
												<input disabled="disabled" id="ext01" value="${labApparaVo.ext01 }" type="text" size="20" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="bug.date"/>：
												</label>
											</td>
											<td>
												<input id="effectiveDate" disabled="disabled" class="Wdate" type="text" value="${labApparaVo.purTime}" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" readonly="readonly" />
											</td>
											<td>
												<label>
													<s:text name="price.number"/>（<s:text name="theme.yuan"/>）：
												</label>
											</td>
											<td>
												<input disabled="disabled" value="${labApparaVo.price}" msg="请输入数字" valType="number" type="text" size="20" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="sam.state"/>：
												</label>
											</td>
											<td>
												<s:if test="${labApparaVo.status == '0'}">
													<input disabled="disabled" value='<s:property value="getText('common')"/>' type="text" size="20" />
												</s:if>
												<s:elseif test="${labApparaVo.status == '7'}">
													<input disabled="disabled" value="废弃" type="text" size="20" />
												</s:elseif>
											</td>
											<td>
												<label>
													<s:text name="use.price"/>：
												</label>
											</td>
											<td>
												<input disabled="disabled" valType="number" msg="请输入数字" value="${labApparaVo.cost}" type="text" size="20" />
												<s:text name="yuan.hour"/>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="check.cir"/>：
												</label>
											</td>
											<td>
												<input disabled="disabled" value="${labApparaVo.verPeriod}" id="verificationPeriod" type="text" size="20" onchange="isNoNatureNum(this);" />
												<s:select disabled="disabled" value="%{labApparaVo.verPeriodStr}" headerKey="年" headerValue="年" list="#{'月':getText('month'),'天':getText('dayday')}" theme="simple" />
											</td>
											<td>
												<label>
													<s:text name="make.country"/>：
												</label>
											</td>
											<td>
												<input disabled="disabled" value="${labApparaVo.mcountry}" id="mcountry" type="text" size="20" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="app.saver"/>：
												</label>
											</td>
											<td>
												<input disabled="disabled" value="${labApparaVo.keeper}" id="keeper" type="text" size="20" onclick="showUser()" />
											</td>
											<td>
												<label>
													<s:text name="lab.yes"/><s:text name="lab.no"/><s:text name="finished.check"/>：
												</label>
											</td>
											<td>
												<s:if test="${labApparaVo.isCheck=='5'}">
													<input type="radio" value="1" disabled="disabled" name="labApparaVo.isCheck">
														<s:text name="lab.yes"/> 
													<input type="radio" value="5" disabled="disabled" name="labApparaVo.isCheck" checked="checked">
														<s:text name="lab.no"/> 
												</s:if>
												<s:else>
													<input type="radio" value="1" disabled="disabled" checked="checked" name="labApparaVo.isCheck">
														<s:text name="lab.yes"/> 
													<input type="radio" value="5" disabled="disabled" name="labApparaVo.isCheck">
													<s:text name="lab.no"/>
												</s:else>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="remark"/>：
												</label>
											</td>
											<td colspan="3">
												<textarea rows="4" cols="60" disabled="disabled">${labApparaVo.comment}</textarea>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="msg.attachment"/>：：
												</label>
											</td>
											<td colspan="3">
												<s:if test="${fn:length(labApparaVo.fileList)>0}">
													<s:iterator status="st2" value="labApparaVo.fileList" id="">
														<span> ${name} <s:if test="${pdfName!=null}">
																&nbsp;&nbsp;<a target="_blank" href="<%=basePath%>utils/onlinepdf/index.jsp?doc=${pdfName}""><img src="<%=basePath%>/img/query.gif" />
																</a>
															</s:if> &nbsp;&nbsp;<a href="<%=basePath%>utils/upload/down.jsp?fileUrl=${path}&fileName=${name}"><img src="<%=basePath%>/img/export.gif" />
														</a> </span>&nbsp;&nbsp;&nbsp;
													</s:iterator>
												</s:if>
											</td>
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
