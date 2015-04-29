<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />

		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
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
<script>
	function submitValue(url){
		$('form').attr('action','${basePath}'+url);
		$('form').submit();
	}
	function extendsDate(obj){
		obj=$(obj);
		var rIndex=obj.closest('tr').index();
		var cIndex=obj.closest('td').index();
		$("#appTab tr:gt("+rIndex+")").each(function(){
			$(this).find('td').eq(cIndex).find('input').val(obj.val());
			
		});
	}
</script>
	<body class="" id="mainid">
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
											<span><s:text name="admin.add"/></span>
										</h2>
									</div>
									<form action="" method="post" name="studentForm">
										<input type="hidden" value="${labSamTestBeatchVo.id}" name="labSamTestBeatchVo.id"/>
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
																	<l:a uri="samtest/labSamTest/updateLabSamTest4Appara.action"  value="lab.code.save" img="/img/icon/filesave.gif"/>
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
													<li id="li01" >
														<a href="javascript:;" onclick="submitValue('samtest/labSamTest/preUpdateLabSamTest.action?tabNum=2')"><span><s:text name="check.item.info"/></span> </a>
													</li>
													<li  class="currenttab">
														<a href="javascript:;" ><span><s:text name="app.use"/></span> </a>
													</li>
													<li  class="">
														<a href="javascript:;"  onclick="submitValue('samtest/labSamTest/preUpdateSamTest4Reagent.action?tabNum=2');"><span><s:text name="rea.use"/></span> </a>
													</li>
													<li  class="">
														<a href="javascript:;"  onclick="submitValue('samtest/labSamTest/preUpdateSamTest4Reference.action?tabNum=2');"><span><s:text name="std.use"/></span> </a>
													</li>
													<li  class="">
														<a href="javascript:;"  onclick="submitValue('samtest/labSamTest/preUpdateSamTest4Ambient.action');"><span><s:text name="env.info"/></span></a>
													</li>
													<li  class="">
														<a href="javascript:;"  onclick="submitValue('samtest/labSamTest/preUpdateSamTest4Data.action?tabNum=5');"><span><s:text name="data.in"/></span> </a>
													</li>
												</ul>
											</div>
											<div class="TabTableBOX01 b" id="Tab01">
												<div class="Formtable">
													<div class="Formtabletitle" >
														<span onclick="$(this).parent().next().toggle();" style="cursor: hand;"><s:text name="check.item.info"/></span>
														<label style="float: right;padding-right: 10px;" onclick="$(this).parent().next().toggle();">[&nbsp;<font color="blue"><s:text name="open.close"/></font>&nbsp;]</label>
													</div>
													<div>
														<table class="myworkingboxttable">
															<tr>
																<th width="100">
																	<img src="<%=basePath%>img/icon_drag.gif" />
																</th>
																<th style="width: 20%;">
																	<s:text name="checking.item"/>
																</th>
																<th  style="width: 20%;">
																	<s:text name="chk.method"/>
																</th>
																<th>
																	<s:text name="app.check"/>
																</th>
															</tr>
															<s:iterator value="#request.listLabSamTestVo" status="st" id="vo">
																	<tr>
																		<td>${st.index+1}</td>
																	<td	>
																		${itemName}
																	</td>
																	<td>
																		${methodName}
																	</td>
																	<td>
																		${apparaName}
																	</td>
																	</tr>
															</s:iterator>
														</table>
													</div>
												</div>
												<div class="Formtable">
													<div class="Formtabletitle" >
														<span ><s:text name="app.use.info"/></span>
														<label style="float: right;padding-right: 10px;" onclick="$(this).parent().next().toggle();">[&nbsp;<font color="blue"><s:text name="open.close"/></font>&nbsp;]</label>
													</div>
													<div>
														<table class="myworkingboxttable" id="appTab">
															<tr>
																<th width="100">
																	<img src="<%=basePath%>img/icon_drag.gif" />
																</th>
																<th style="width:40%;">
																	<s:text name="app.check"/>
																</th>
																<th>
																	<s:text name="tempture"/>
																</th>
																<th>
																	<s:text name="shidu"/>
																</th>
																<th style="width: 150">
																	<s:text name="start.time"/>
																</th>
																<th style="width: 130">
																	<s:text name="end.time"/>
																</th>
															</tr>
															<s:iterator value="#request.listLabSamTestVo" status="st" id="vo">
																	<tr>
																		<td>${st.index+1}</td>
																	<td>
																		<input type="hidden" name="listLabApparaUseVo[${st.index}].ext01" value="${itemId}" />
																		${apparaName}
																		<input type="hidden" value="${apparaId}" name="listLabApparaUseVo[${st.index}].appId" />
																	</td>
																	<td>
																		${temperature}<span style="color: red;">（℃）</span> 
																		<input type="hidden" name="listLabApparaUseVo[${st.index}].wenDu" value="${temperature}" />
																	</td>
																	<td>
																		${humidity}<span style="color: red;">（℃）</span>
																		<input type="hidden" name="listLabApparaUseVo[${st.index}].shiDu" value="${humidity}" />
																	</td>
																	<td>
																		<input onchange="extendsDate(this)" type="text" name="listLabApparaUseVo[${st.index}].beStartDate" valType="required" msg="请输入开始时间"  value="${startDate}" id="sampDate"  class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true})" />
																	</td>
																	<td>
																		<input onchange="extendsDate(this)" type="text" name="listLabApparaUseVo[${st.index}].beEndDate" valType="required" msg="请输入结束时间" value="${endDate}" id="sampDate"  class="Wdate" onFocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm',alwaysUseStartDate:true})" />
																	</td>
																	</tr>
															</s:iterator>
														</table>
													</div>
												</div>
											</div>
										</div>
									</form>
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
	</body>
	<script>
		$(function(){
			$('.myworkingboxttable tr td select[id*=standsDesc] option').each(function(){
				var standId=$(this).parent('select').next().val();
				var itemId=$(this).closest('tr').find('input[name*=itemId]').val();
				var monthId=$(this).closest('tr').find('input[id*=methodId]').val();
				var monthSelect=$(this).closest('tr').find('select[name*=methodId]');
				if(standId!=''){
					if($(this).val().indexOf(standId)!=-1){
						$(this).attr("selected",true);
						$.ajax({
							url:'<%=basePath%>samtest/labSamTest/ajaxLabSamTest4Method.action',
							data:{'labSamTestVo.itemId':itemId,'labSamTestVo.standardId':standId},
							type:'post',
							dataType:'json',
							success:function (data){
									var options='';
									for(var i=0;i<data.length;i++){
										if(monthId==data[i].id){
											options+='<option  selected="selected" value='+data[i].id+'&'+data[i].name+'>'+data[i].name+'</option>';
										}else{
											options+='<option  value='+data[i].id+'&'+data[i].name+'>'+data[i].name+'</option>';
										}
									}
									monthSelect.html(options);
						   	  }
		 				 });
					}
				}
			});
		});
	</script>
</html>
