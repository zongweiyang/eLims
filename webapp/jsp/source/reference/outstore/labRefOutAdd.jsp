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

.myworkingboxttable th {
	background: #F5F5F5;
	color: #000000;
	border-top: #C0C0C0 1px solid;
	border-left: #C0C0C0 1px solid;
	border-right: #C0C0C0 1px solid;
}
</style>
		<script>
		
		function panduan(num,index)
	    {
     	  var thisnum=$('#amount'+index).val();
          if(eval(thisnum)>eval(num))
          {
            validate.tip('<s:property value="getText('stocknotco')"/>',$('#functionId'));
            $('#amount'+index).val('');
          }
	    }
			
		function deleteEntity(obj){
			var flag = confirm('<s:property value="getText('fimrtodel')"/>');
			 if(flag){
				 $(obj).parent().parent().remove();
			 }
		}
			
		function addReference(){
		   var ids=$('#ids').val();
	       var url  = '/reference/labReference/showLabRef4Select.action?ids='+ids;
			$.dialog({
				id:'userId',
				content:'url:'+url,
				title:'<s:property value="getText('standardllist')"/>',
				opacity:0.4,
				width:600,
				height:400,
				max:false,
				min:false,
				lock:true
			 });
			}
 	    function flushThisPage(url){
			var url='/reference/labRefOutMain/preAddLabRefOutMain.action?referenceIds='+url;
			window.location.href='${basePath}'+url;
	 	}
		</script>
	</head>


	<body class="" id="mainid">
		<form action="" method="post" name="form">
			<input type="hidden" name="labRefOutMainVo.id"
				value="${labRefOutMainVo.id}" />
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
				cellpadding="0" border="0">
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
												<span><s:text name="admin.add"/></span>
											</h2>
										</div>
										<!-- 按钮条 开始 -->
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<l:a uri="back" value="msg.back" />
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn" onclick="submitvalue('/reference/labRefOutMain/preAddLabRefOutMain.action');return false;"><img height="20" width="20" src="<%=basePath%>img/chakan.gif" /><b><s:text name="msg.back"/></b> </a>
																	<!-- 
																	<l:a uri="reference/labRefOutMain/addLabRefOutMain.action" 
																		value="lab.code.save" />
																	 -->
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
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
																<s:text name="bill.no"/>
															</label>
														</td>
														<td>
															<input type="text" name="labRefOutMainVo.receiptno"
																valType="required,strLength" max="32"
																strLength-msg='<s:property value="getText('danjunotover32')"/>' msg='<s:property value="getText('danjuidnotem')"/>'
																value="${labRefOutMainVo.receiptno}" />
															<input type="hidden" id="referenceIds"
																value="${referenceIds}" />
														</td>
														<td>
															<label>
																<s:text name="out.savinger"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labRefOutMainVo.outstorer"
																valType="required,strLength" max="64"
																strLength-msg='<s:property value="getText('outboundnot64')"/>' msg='<s:property value="getText('outboudnnotemp')"/>'
																value="${labRefOutMainVo.outstorer}" />
														</td>
														<td>
															<label>
																<s:text name="out.time"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labRefOutMainVo.outstoreDate"  value="${labRefOutMainVo.outstoreDate}" class="Wdate" size="20"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" />
														</td>
													</tr>
												</table>
											</div>
											<div class="Formtable">
												<div class="Formtabletitle">
													<span><s:text name="out.info"/></span>
													<a href="javascript:;" onclick="addReference()"
														style="color: red;">&nbsp;&nbsp;+&nbsp;<s:text name="add.std"/></a>
												</div>
												<table class="myworkingboxttable" cellspacing="0"
													cellpadding="0">
													<tr>
														<th>
															<img src="<%=basePath%>img/icon_drag.gif" />
														</th>
														<th>
															<s:text name="stdandard.name"/>
														</th>
														<th>
															<s:text name="stdandard.regular"/>
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
													<s:iterator value="labRefOutMainVo.labRefOutDetailVoList"
														status="st">
														<tr>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																${referenceName}
																<input type="hidden" id="id"
																	name="labRefOutMainVo.labRefOutDetailVoList[${st.index}].referenceId"
																	value="${referenceId }" />
																<input type="hidden" name="referenceids" value="${referenceId}" />
															</td>
															<td class="l">
																${size}
																<input type="hidden"
																	name="labRefOutMainVo.labRefOutDetailVoList[${st.index}].size"
																	value="${size}" />
															</td>
															<td class="l">
																${purity}
																<input type="hidden"
																	name="labRefOutMainVo.labRefOutDetailVoList[${st.index}].purity"
																	value="${purity}" />
															</td>
															<td class="l">
																${num}
																<input type="hidden"
																	name="labRefOutMainVo.labRefOutDetailVoList[${st.index}].num"
																	value="${num}" />
															</td>
															<td class="c">
																<input type="text"
																	onblur="panduan('${num}','${st.index}');return false;"
																	name="labRefOutMainVo.labRefOutDetailVoList[${st.index}].amount"
																	valType="required,strLength" max="16"
																	strLength-msg='<s:property value="getText('oubputlennot16')"/>' msg='<s:property value="getText('outputnoatmeu')"/>'
																	id="amount${st.index}" value="${amount}" />
															</td>

															<td class="c">
																<input type="text"
																	name="labRefOutMainVo.labRefOutDetailVoList[${st.index}].remark"
																	style="width: 200px;" valType="strLength" max="512"
																	strLength-msg='<s:property value="getText('remakrnot512')"/>' value="${remark}" />
															</td>
															<td class="c">

																<hr:jhref hrAttribute="href:javascript"
																	hrFunctionType="delete" hrFunctionUrl=""
																	hrFunctionValue="lab.code.del"
																	hrJsFunctions="onclick:deleteEntity(this);return false;"
																	hrIsInterval="false" />
															</td>
														</tr>
													</s:iterator>
												</table>
											</div>
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
