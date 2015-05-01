<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

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

.Formtable {
	border: none;
}

.FormtableCon input {
	width: 200px;
}

.FormtableCon textarea {
	width: 600px;
}

.FormtableCon select {
	width: 200px;
}

.myworkingboxttable th {
	background: #F5F5F5;
	color: #000000;
	border-top: #C0C0C0 1px solid;
	border-left: #C0C0C0 1px solid;
	border-right: #C0C0C0 1px solid;
}
</style>
	</head>
	<script language=javascript>
				var orgId=''; 
				var selectCheck='0';
		  		function submitvalue(actionstr){
		  			if(orgId!=''){
		  				if(confirm('<s:property value="getText('confirmconmmit')"/>')){
			  	 			$('form').attr('action',actionstr);
							$('form').submit();
						}
		  	 		}else{
		  	 			validate.tip('请选择检测科室！');
		  	 			return false;
		  	 		}
				}
				function clikSelect(){
					selectCheck='1';
				}
				function checkOrg(obj){
					obj=$(obj);
					if(obj.val()=='0'){
						orgId=obj.val();
						obj.parent().css('background-color','');
					}else{
						orgId=obj.val();
						obj.closest('td').css('background-color','yellow');
					}
				}
				function checkTd(obj){
					obj=$(obj);
					if(selectCheck=='0'&&orgId!=''){
						obj.find('select').find('option').each(function(){
								if($(this).val()==orgId){
									$(this).attr('selected','true');
								}
						});
						if(orgId!='0'){
							obj.css('background-color','yellow');
						}else{
							obj.css('background-color','');
						}		
					}
					selectCheck='0';
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
								<span><s:text name="config.modify"/></span>
							</h2>
						</div>
						<form theme="simple" action="" method="post" name="labSupplierForm">
							<s:token></s:token>
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
														<td>
															<l:a uri="samtest/labSamTask/updateLabSamTask4Issued.action" onclick="submitvalue('samtest/labSamTask/updateLabSamTask4Issued.action');return false;" value="post.commit" img="/img/icon/filesave.gif"/>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="item.info"/></span>
										<label style="float: right; margin-right: 20px;" onclick="javascript:$(this).parent().next().toggle();">
											<font color="red">[<s:text name="open.close"/></font>]
										</label>
									</div>
										<table class="myworkingboxttable" id="orgTable">
											<thead>
												<tr>
													<th width="100">
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th>
														<s:text name="checking.item"/>
													</th>
													<th width="250px">
														<s:text name="test.sample.num"/>
													</th>
													<th width="220px">
														<s:text name="test.room"/>
													</th>
												</tr>
											</thead>
											<s:iterator value="#request.listLabSamTestVo" status="st">
												<tr>
													<td>
														${st.index+1}
													</td>
													<td>
														${itemName}
														<input type="hidden" value="${itemId}" name="listLabSamTestVo[${st.index}].itemId" />
														<input type="hidden" value="${itemName}" name="listLabSamTestVo[${st.index}].itemName" />
														<input type="hidden" value="${taskId}" name="listLabSamTestVo[${st.index}].taskId" />
													</td>
													<td>
														${sampNum}
													</td>
													<td style="text-align: center;" onclick="checkTd(this)">
														<s:select theme="simple" headerKey="0" headerValue="--请选择--" name="listLabSamTestVo[${st.index}].orgId" list="#request.listOrgVo" listKey="id+'&'+name" listValue="name" onclick="clikSelect()" onchange="checkOrg(this)"></s:select>
													</td>
												</tr>
											</s:iterator>
										</table>
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
