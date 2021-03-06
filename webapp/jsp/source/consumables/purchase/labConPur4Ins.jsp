<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<%
			List list = (ArrayList) request.getAttribute("list");
			int listSize = 0;
			if (null != list && 0 < list.size()) {
				listSize = list.size();
			}
		%>
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
		<script>
			function goToNextAction(url){
				$('form').attr('action','${basePath}'+url);
				$('form').submit();
			}
			function deleteEntity(obj){
			 	if(confirm('<s:property value="getText('fimrtodel')"/>')){
					$(obj).parent().parent().remove();
				}
			}
			
			function checkAllItem(){
				var strReg = /^[1-9]d*|0$/;
				var myRegExp =/^\d+\.\d{2}$/;   
				for(var i=1;i<=<%=listSize%>;i++){
				    var num = $("#num"+i).val();
				  //  var concentrationx = document.getElementById('concentration'+i).value;
					
					if (!strReg.test(num)) {
						validate.tip('<s:property value="getText('inputcorddata')"/>',$('#functionId'));
						$("#num"+i).focus();
						return false;
					}
					
				}
			return true;
			};		
			
			function ajax2LabConList(){
				var url  = '${basePath}consumables/labConPurMain/ajax4ListLabPur.action';
				$.dialog({
					id:'roleId',
					content:'url:'+url,
					title:'<s:property value="getText('consulist')"/>',
					opacity:0.4,
					width:800,
					height:500,
					max:false,
				    min:false,
					lock:true
				 });
		    }
		    function BASEisNotFloat(theFloat){
				//判断是否为浮点数
				len=theFloat.length;
				dotNum=0;
				if (len==0) return true;
				for(var i=0;i<len;i++){
					oneNum=theFloat.substring(i,i+1);
					if (oneNum==".") dotNum++;
					if (((oneNum<"0" || oneNum>"9") && oneNum!=".") || dotNum>1) return true;
				}
				if (len>1 && theFloat.substring(0,1)=="0"){
					if (theFloat.substring(1,2)!=".") return true;
				}
					return false;
			}
			
			function checkTotal(obj,i){
				if($(obj).val().length>0){
					if(BASEisNotFloat($(obj).val())) {
						validate.tip('<s:property value="getText('theme.input.number')"/>',$('#functionId'));
						$(obj).val("0");
					}
				}
			}
		    function reLoadPage(ids){
			   	var url='consumables/labConPurMain/showLabConPur4Ins.action?labConPurMainVo.consumablesId='+ids;
			   	$('form').attr('action','${basePath}'+url);
				$('form').submit();
		   }
		</script>
	</head>


	<body class="" id="mainid">
		<form action="" method="post" name="form">
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
												${funName }：
												<span><s:text name="inbound.con"/></span>
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
																	<a id="BtnPreview" class="zPushBtn" href="javascript:;"
																		onclick="javascript:if(confirm('确认要提交吗?')){goAction('updateLabConPur4Ins.action?labConPurMainVo.auditResult=1');}return false;"><img
																			height="20" width="20"
																			src="${basePath}img/xinjian.gif" /><b><s:text name="confirmin"/></b> </a>
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
															<input type="text" name="labConPurMainVo.receiptno"
																value="${labConPurMainVo.receiptno}" />
															<input type="hidden" name="labConPurMainVo.id"
																value="${labConPurMainVo.id}" id="labConPurMainId" />
														</td>
														<td>
															<label>
																<s:text name="applier"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labConPurMainVo.applicant"
																value="${labConPurMainVo.applicant}" />
														</td>
														<td>
															<label>
																<s:text name="applytime"/>：
															</label>
														</td>
														<td>
															<input name="labConPurMainVo.createTime"
																value="${labConPurMainVo.createTime}" id="name"
																class="Wdate" size="20"
																onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" />
														</td>
													</tr>
												</table>
											</div>
											<div class="Formtable">
												<div class="Formtabletitle">
													<span><s:text name="buyinginfo"/></span>
												</div>
												<table class="FormtableCon">
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
															<s:text name="storenumber"/>
														</th>
														<th>
															采购数量
														</th>
														<th>
															<s:text name="remark"/>
														</th>
													</tr>

													<s:iterator value="conPurDetailList" status="st">
														<tr>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
																<input type="hidden" name="consumablesId"
																	value="${consumablesId}" />
															</td>
															<td class="c">
																${consumablesName }
																<input type="hidden"
																	name="labConPurMainVo.labConPurDetailVoList[${st.index }].consumablesName"
																	value="${consumablesName }" disabled="disabled" />
																<input type="hidden"
																	name="labConPurMainVo.labConPurDetailVoList[${st.index }].consumablesId"
																	value="${consumablesId }" />
															</td>
															<td class="c">
																${size }
																<input type="hidden"
																	name="labConPurMainVo.labConPurDetailVoList[${st.index }].size"
																	value="${size }" disabled="disabled" />
															</td>
															<td class="c">
																${amount }
																<input type="hidden"
																	name="labConPurMainVo.labConPurDetailVoList[${st.index }].amount"
																	value="${amount }" disabled="disabled" />
															</td>
															<td class="c">
																<input type="text"
																	name="labConPurMainVo.labConPurDetailVoList[${st.index }].num"
																	onblur="checkTotal(this,'${st.index+1}');"
																	id="num${st.index }" } value="${num }" />
															</td>
															<td class="c">
																<input type="text"
																	name="labConPurMainVo.labConPurDetailVoList[${st.index }].remark"
																	value="${remark }" />
															</td>
														</tr>
													</s:iterator>

												</table>
											</div>
										</div>
						</table>
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
