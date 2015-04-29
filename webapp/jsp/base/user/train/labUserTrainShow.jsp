<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<style>
* {
	font-size: 9pt;
	line-height: 20px
}

.title {
	font-size: 16px;
	color: #0000FF;
	font-weight: bold
}

.con {
	margin-left: 15px;
}

.STYLE1 {
	color: #FF0000
}

.STYLE2 {
	color: #0000FF
}

.STYLE3 {
	color: #009900
}
</style>



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
 	 function submitvalue(actionstr){
		$('form').attr('action','<%=basePath%>'+actionstr);
		$('form').submit();
	}
	function ajax2UserList(){
		var url  = '${basePath}/user/labUserTrain/showLabUser4Select.action';
		$.dialog({
			id:'id',
			content:'url:'+url,
			title:'<s:property value="getText('user.list')"/>',
			opacity:0.4,
			width:800,
			height:400,
			lock:true
		 });
	}
			</script>
	<body>
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td class="TLimg"></td>
				<td class="TMimg"></td>
				<td class="TRimg"></td>
			</tr>
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
						<form theme="simple" action="" method="post" name="labUserTrainForm">
							<input type="hidden" name="labUserTrainVo.id" value="${labUserTrainVo.id}" />
							<div class="TabTable">
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
								<div class="Formtable" id="stone">
									<div class="Formtabletitle">
										<span><s:text name="plan.info"/></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td class="w100 c">
												<label>
													<s:text name="msg.depart"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												${labUserTrainVo.orgName}
											</td>
											<td class="w100 c">
												<label>
													<s:text name="msg.subject"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												${labUserTrainVo.title}
											</td>
										</tr>
										<tr>
											<td class="c">
												<label>
													<s:text name="teacher"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td>
												${labUserTrainVo.teacher}
											</td>
											<td class="c">
												<label>
													<s:text name="training.place"/>：
												</label>
											</td>
											<td>
												${labUserTrainVo.station}
											</td>
										</tr>
										<tr>
											<td class="c">
												<label>
													<s:text name="training.time"/>：
												</label>
											</td>
											<td>
												${labUserTrainVo.tdate}
											</td>
											<td class="c">
												<label>
													<s:text name="training.site"/>：
												</label>
											</td>
											<td>
												${labUserTrainVo.address}
											</td>
										</tr>
										<tr>
											<td class="c">
												<label>
													<s:text name="training.content"/>：
												</label>
											</td>
											<td colspan="3" style="height: 54px;">
												${labUserTrainVo.content}
											</td>
										</tr>
										<tr>
											<td class="c">
												<label>
													<s:text name="remark"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
												</label>
											</td>
											<td colspan="3" style="height: 54px;">
												${labUserTrainVo.remark}
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable" id="stone">
									<div class="Formtabletitle">
										<span><s:text name="teach.record"/></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<th>
												<s:text name="msg.depart"/>
											</th>
											<th>
												<s:text name="nike.name"/>
											</th>
											<th>
												<s:text name="learn.score"/>
											</th>
											<th>
												<s:text name="training.date"/>
											</th>
											<th>
												<s:text name="remark"/>
											</th>
										</tr>
										<s:iterator value="labUserTrainVo.recordList" status="st">
											<tr>
												<td class="w100 c">
													${orgName}
												</td>
												<td class="w100 c">
													${labUserName}
												</td>
												<td class="c">
													${result}
												</td>
												<td class="c">
													${tdate}
												</td>
												<td class="l">
													${remark}
												</td>
											</tr>
										</s:iterator>
									</table>
								</div>
							</div>
						</form>
					</div>
				<td class="MRimg"></td>
			</tr>
			<tr>
				<td class="FLimg"></td>
				<td class="FMimg"></td>
				<td class="FRimg"></td>
			</tr>
		</table>
		<div class="clear"></div>
	</body>
</html>
