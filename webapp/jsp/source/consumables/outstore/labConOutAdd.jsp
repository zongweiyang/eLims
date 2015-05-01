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
            validate.tip("库存不够",$('#functionId'));
            $('#amount'+index).val('');
          }
	    }
			
		function deleteEntity(obj){
			 var flag=confirm('<s:property value="getText('lab.confirm.delete')"/>');
			 if(flag){
				 $(obj).parent().parent().remove();
			 }
		}
			
		function addConsumables(){
		   var ids=$('#ids').val();
	       var url  = '/consumables/labConsumables/showLabCon4Select.action?ids='+ids;
			$.dialog({
				id:'userId',
				content:'url:'+url,
				title:'<s:property value="getText('consulist')"/>',
				opacity:0.4,
				width:600,
				height:400,
				max:false,
				min:false,
				lock:true
			 });
			}
 	    function flushThisPage(url){
			var url='/consumables/labConOutMain/preAddLabConOutMain.action?consumablesIds='+url;
			window.location.href='${basePath}'+url;
	 	}
		</script>
	</head>


	<body class="" id="mainid">
		<form action="" method="post" name="form">
			<input type="hidden" name="labConOutMainVo.id"
				value="${labConOutMainVo.id}" />
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
																	<l:a uri="consumables/labConOutMain/addLabConOutMain.action"
																		value="lab.code.save" />
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
															<input type="text" name="labConOutMainVo.receiptno"
																valType="required,strLength" max="32"
																strLength-msg="单据号长度不能超过32位" msg="单据号不能为空"
																value="${labConOutMainVo.receiptno}" />
															<input type="hidden" id="consumablesIds"
																value="${consumablesIds}" />
														</td>
														<td>
															<label>
																<s:text name="out.savinger"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labConOutMainVo.outstorer"
																valType="required,strLength" max="64"
																strLength-msg="出库人长度不能超过64位" msg="出库人不能为空"
																value="${labConOutMainVo.outstorer}" />
														</td>
														<td>
															<label>
																<s:text name="out.time"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labConOutMainVo.outstoreDate"
																value="${labConOutMainVo.outstoreDate}" class="Wdate"
																size="20"
																onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" />
														</td>
													</tr>
												</table>
											</div>
											<div class="Formtable">
												<div class="Formtabletitle">
													<span><s:text name="out.info"/></span>
													<a href="javascript:;" onclick="addConsumables()"
														style="color: red;">&nbsp;&nbsp;+&nbsp;<s:text name="addconsu"/></a>
												</div>
												<table class="myworkingboxttable" cellspacing="0"
													cellpadding="0">
													<tr>
														<th>
															<img src="<%=basePath%>img/icon_drag.gif" />
														</th>
														<th>
															<s:text name="conname"/>
														</th>
														<th>
															<s:text name="regular.no"/>
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
													<s:iterator value="labConOutMainVo.labConOutDetailVoList"
														status="st">
														<tr>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="l">
																${consumablesName}
																<input type="hidden" id="id"
																	name="labConOutMainVo.labConOutDetailVoList[${st.index}].consumablesId"
																	value="${consumablesId}" />
																<input type="hidden" name="consumablesids" value="${consumablesId}" />
															</td>
															<td class="l">
																${size}
																<input type="hidden"
																	name="labConOutMainVo.labConOutDetailVoList[${st.index}].size"
																	value="${size}" />
															</td>
															<td class="l">
																${num}
																<input type="hidden"
																	name="labConOutMainVo.labConOutDetailVoList[${st.index}].num"
																	value="${num}" />
															</td>
															<td class="c">
																<input type="text"
																	onblur="panduan('${num}','${st.index}');return false;"
																	name="labConOutMainVo.labConOutDetailVoList[${st.index}].amount"
																	valType="required,strLength" max="16"
																	strLength-msg="出库量长度不能超过16位" msg="出库量不能为空"
																	id="amount${st.index}" value="${amount}" />
															</td>

															<td class="c">
																<input type="text"
																	name="labConOutMainVo.labConOutDetailVoList[${st.index}].remark"
																	style="width: 200px;" valType="strLength" max="512"
																	strLength-msg="备注长度不能超过512位" value="${remark}" />
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
