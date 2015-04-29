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
	function panduan(num,index)
	    {
     	  var thisnum=$('#amount'+index).val();
          if(eval(thisnum)>eval(num))
          {
            validate.tip("库存不够！",$('#functionId'));
            $('#amount'+index).val('');
          }
	    }
			
		function deleteEntity(obj){
			  var bln = window.confirm("<s:property value="getText('lab.confirm.delete')"/>");   
  			 if(bln){
				 $(obj).parent().parent().remove();
  			 }
		}
			
		function addReagent(){
		   var ids=$('#ids').val();
	       var url  = '/reagent/labReagent/showLabRea4Select.action?ids='+ids;
			$.dialog({
				id:'userId',
				content:'url:'+url,
				title:'试剂列表',
				opacity:0.4,
				width:650,
				height:400,
				max:false,
				min:false,
				lock:true
			 });
			}
 	    function flushThisPage(url){
			var url='samtest/labSamTest/preUpdateSamTest4Reagent.action?reagentIds='+url+'&labSamTestBeatchVo.id='+$("#labSamTestBeatchVoId").val();
			var labReaOutMainId=$("#labReaOutMainId").val();
			if(labReaOutMainId!=''){
				url+='&labReaOutMainVo.id='+labReaOutMainId;
			}
			window.location.href='${basePath}'+url;
	 	}
	function submitValue(url){
		$('form').attr('action','${basePath}'+url);
		$('form').submit();
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
										<input type="hidden" value="${labSamTestBeatchVo.id}" id="labSamTestBeatchVoId" name="labSamTestBeatchVo.id" />
									    <input type="hidden" name="labReaOutMainVo.id" id="labReaOutMainId" value="${labReaOutMainVo.id}" />
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
																	<l:a uri="samtest/labSamTest/updateSamTest4Reagent.action" value="lab.code.save" img="/img/icon/filesave.gif"/>
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
														<a href="javascript:;" onclick="submitValue('samtest/labSamTest/preUpdateLabSamTest.action?tabNum=3')"><span><s:text name="check.item.info"/></span> </a>
													</li>
													<li class="">
														<a href="javascript:;" onclick="submitValue('samtest/labSamTest/preUpdateLabSamTest4Appara.action?tabNum=3');"><span><s:text name="app.use"/></span> </a>
													</li>
													<li class="currenttab">
														<a href="javascript:;" onclick="submitValue('samtest/labSamTest/preUpdateSamTest4Reagent.action?tabNum=3');"><span><s:text name="rea.use"/></span> </a>
													</li>
													<li class="">
														<a href="javascript:;" onclick="submitValue('samtest/labSamTest/preUpdateSamTest4Reference.action?tabNum=3');"><span><s:text name="std.use"/></span> </a>
													</li>
													<li class="">
														<a href="javascript:;" onclick="submitValue('samtest/labSamTest/preUpdateSamTest4Ambient.action?tabNum=3');"><span><s:text name="env.use"/></span>
														</a>
													</li>
													<li class="">
														<a href="javascript:;" onclick="submitValue('samtest/labSamTest/preUpdateSamTest4Data.action?tabNum=3');"><span><s:text name="data.in"/></span> </a>
													</li>
												</ul>
											</div>
											<div class="TabTableBOX01 b" id="Tab01">
												<div class="TabTable"
											style="padding-top: 0; margin: 0; background: none;">
											<div class="Formtable">
												<div class="Formtabletitle">
													<span><s:text name="base.info"/></span>
												</div>
												<table class="FormtableCon">
													<tr>
														<td>
															<label>
																<s:text name="bill.no"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labReaOutMainVo.receiptno"
																valType="required,strLength" max="32"
																strLength-msg="单据号长度不能超过32位" msg="单据号不能为空"
																value="${labReaOutMainVo.receiptno}" />
															<input type="hidden" id="reagentIds"
																value="${reagentIds}" />
														</td>
														<td>
															<label>
																<s:text name="out.savinger"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labReaOutMainVo.outstorer"
																valType="required,strLength" max="64"
																strLength-msg="出库人长度不能超过64位" msg="出库人不能为空"
																value="${labReaOutMainVo.outstorer}" />
														</td>
														<td>
															<label>
																<s:text name="out.time"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labReaOutMainVo.outstoreDate"
																value="${labReaOutMainVo.outstoreDate}" class="Wdate"
																size="20"
																onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" />
														</td>
													</tr>
												</table>
											</div>
												<div class="Formtable">
												<div class="Formtabletitle">
													<span><s:text name="use.record"/></span>
													<a href="javascript:;" onclick="addReagent()"
														style="color: red;">&nbsp;&nbsp;+&nbsp;<s:text name="add.reagent"/></a>
												</div>
												<table class="FormtableCon">
													<tr>
														<th>
															<img src="<%=basePath%>img/icon_drag.gif" />
														</th>
														<th>
															<s:text name="rea.name"/>
														</th>
														<th>
															<s:text name="regular.no"/>
														</th>
														<th>
															<s:text name="pure"/>
														</th>
														<th>
															<s:text name="real.stock"/>
														</th>
														<th>
															<s:text name="out.number"/>
														</th>
														<th>
															<s:text name="remark"/>
														</th>
														<th>
															<s:text name="lab.code.ops"/>
														</th>
													</tr>
													<s:iterator value="labReaOutMainVo.labReaOutDetailVoList"
														status="st">
														<tr>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																${reagentName}
																	<input type="hidden" name="labReaOutMainVo.labReaOutDetailVoList[${st.index}].id" value="${id}" />
																<input type="hidden" id="id"
																	name="labReaOutMainVo.labReaOutDetailVoList[${st.index}].reagentId"
																	value="${reagentId }" />
																<input type="hidden" name="reagentids" value="${reagentId}" />
															</td>
															<td class="l">
																${size}
																<input type="hidden"
																	name="labReaOutMainVo.labReaOutDetailVoList[${st.index}].size"
																	value="${size}" />
															</td>
															<td class="l">
																${purity}
																<input type="hidden"
																	name="labReaOutMainVo.labReaOutDetailVoList[${st.index}].purity"
																	value="${purity}" />
															</td>
															<td class="c">
																${num}
																<input type="hidden"
																	name="labReaOutMainVo.labReaOutDetailVoList[${st.index}].num"
																	value="${num}" />
															</td>
															<td class="c">
																<s:if test="${amount}!=''">
																	${amount}
																	<input type="hidden"
																	name="labReaOutMainVo.labReaOutDetailVoList[${st.index}].amount"
																	id="amount${st.index}" value="${amount}" />
																</s:if>
																<s:else>
																	<input type="text"
																	onblur="panduan('${num}','${st.index}');return false;"
																	name="labReaOutMainVo.labReaOutDetailVoList[${st.index}].amount"
																	valType="required,strLength" max="16"
																	strLength-msg="出库量长度不能超过16位" msg="出库量不能为空"
																	id="amount${st.index}" value="${amount}" />
																</s:else>
																
															</td>

															<td class="c">
															<s:if test="${id!=null}">${remark}</s:if>
															<s:else>
																<input type="text"
																	name="labReaOutMainVo.labReaOutDetailVoList[${st.index}].remark"
																	valType="strLength" max="512" style="width: 200px;"
																	strLength-msg="备注长度不能超过512位" value="${remark}" />
															</s:else>
															</td>
															<td class="c">
																<s:if test="${id!=null}"></s:if>
																<s:else>
																	<hr:jhref hrAttribute="href:javascript"
																		hrFunctionType="delete" hrFunctionUrl=""
																		hrFunctionValue="config.del"
																		hrJsFunctions="onclick:deleteEntity(this);return false;"
																		hrIsInterval="false" />
																</s:else>
															</td>
														</tr>
													</s:iterator>
												</table>
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
</html>
