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
				var msg=true;
				$('input[id*="num"]').each(function(){
					if($(this).val().length==0){
						validate.tip("数量不能为空！",$(this));
						msg=false;
					}
				});
				if(!msg){
					return false;
				}
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
						validate.tip("请输入正确格式的的数据！<s:text name="ifed"/>：20",$('#functionId'));
						$("#num"+i).focus();
						return false;
					}
					
				}
			return true;
			};		
			
			function ajax2LabRefList(){
				var url  = '${basePath}reference/labRefPurMain/showLabRef4Select.action';
				$.dialog({
					id:'roleId',
					content:'url:'+url,
					title:'<s:property value="getText('standardllist')"/>',
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
						validate.tip("请输入数字",$('#functionId'));
						$(obj).val("0");
					}
				}
			}
		    function reLoadPage(ids){
			   	var url='reference/labRefPurMain/preUpdateLabRefPur.action?labRefPurMainVo.referenceId='+ids;
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
												<span><s:text name="lab.code.modify"/></span>
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
																	<l:a
																		uri="reference/labRefPurMain/updateLabRefPurMain.action?labRefPurMainVo.auditResult=0"
																		onclick="goToNextAction('reference/labRefPurMain/updateLabRefPurMain.action?labRefPurMainVo.auditResult=0');return false;"
																		value="lab.code.save" />
																</td>
																<td>
																	<l:a
																		uri="reference/labRefPurMain/updateLabRefPurMain.action?labRefPurMainVo.auditResult=1"
																		onclick="goToNextAction('reference/labRefPurMain/updateLabRefPurMain.action?labRefPurMainVo.auditResult=1');return false;"
																		value="save.commit" />
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
															<input type="text" name="labRefPurMainVo.receiptno"
																valType="required,strLength" max="32"
																strLength-msg="申请人长度不能超过32位" msg="申请人不能为空"
																value="${labRefPurMainVo.receiptno}" valType="" />
															<input type="hidden" name="labRefPurMainVo.id"
																value="${labRefPurMainVo.id}" id="labRefPurMainId" />
														</td>
														<td>
															<label>
																<s:text name="applier"/>：
															</label>
														</td>
														<td>
															<input type="text" name="labRefPurMainVo.applicant"
																valType="required,strLength" max="32"
																strLength-msg="申请人长度不能超过32位" msg="申请人不能为空"
																value="${labRefPurMainVo.applicant}" />
														</td>
														<td>
															<label>
																<s:text name="applytime"/>：
															</label>
														</td>
														<td>
															<input name="labRefPurMainVo.createTime"
																valType="required,strLength" max="64"
																strLength-msg="申请时间长度不能超过64位" msg="申请时间不能为空"
																value="${labRefPurMainVo.createTime}" id="name"
																class="Wdate" size="30"
																onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" />
														</td>
													</tr>
													<tr>
														<td>
															<label>
																<s:text name="remark"/>：
															</label>
														</td>
														<td rowspan="3" colspan="3">
															<textarea name="labRefPurMainVo.remark"
																valType="strLength" max="512"
																strLength-msg="备注长度不能超过512位"
																value="${labRefPurMainVo.remark}" size="20" rows="2"
																cols="60"> ${labRefPurMainVo.remark}</textarea>
														</td>
													</tr>
												</table>
											</div>
											<div class="Formtable">
												<div class="Formtabletitle">
													<span><s:text name="buyinginfo"/></span>
													<a href="javascript:;" onclick="ajax2LabRefList()"
														;
														style="color: red;">&nbsp;&nbsp;+&nbsp;<s:text name="add.std"/></a>
												</div>
												<table class="FormtableCon">
													<tr>
														<th>
															<img src="<%=basePath%>img/icon_drag.gif" />
														</th>
														<th>
															<s:text name="stdandard.name"/>
														</th>
														<th>
															<s:text name="regular.no"/>
														</th>
														<th>
															<s:text name="storenumber"/>
														</th>
														<th>
															<s:text name="buyingnumber"/>
														</th>
														<th>
															<s:text name="remark"/>
														</th>
														<th>
															<s:text name="lab.code.ops"/>
														</th>
													</tr>

													<s:iterator value="refPurDetailList" status="st">
														<tr>
															<td class="c">
																${pageSizex* currenPagex+st.index+1}
															</td>
															<td class="c">
																${referenceName }
																<input type="hidden"
																	name="labRefPurMainVo.labRefPurDetailVoList[${st.index }].referenceName"
																	value="${referenceName }" disabled="disabled" />
																<input type="hidden"
																	name="labRefPurMainVo.labRefPurDetailVoList[${st.index }].referenceId"
																	value="${referenceId }" />
															</td>
															<td class="c">
																${size }
																<input type="hidden"
																	name="labRefPurMainVo.labRefPurDetailVoList[${st.index }].size"
																	value="${size }" disabled="disabled" />
															</td>
															<td class="c">
																${amount }
																<input type="hidden"
																	name="labRefPurMainVo.labRefPurDetailVoList[${st.index }].amount"
																	value="${amount }" disabled="disabled" />
															</td>
															<td class="c">
																<input type="text"
																	name="labRefPurMainVo.labRefPurDetailVoList[${st.index }].num"
																	valType="strLength" max="32"
																	strLength-msg="采购数量长度不能超过16位"
																	onblur="checkTotal(this,'${st.index+1}');"
																	id="num${st.index }" } value="${num }" />
															</td>
															<td class="c">
																<input type="text" valType="strLength" max="512"
																	strLength-msg="备注长度不能超过32位"
																	name="labRefPurMainVo.labRefPurDetailVoList[${st.index }].remark"
																	value="${remark }" />
															</td>
															<td class="c">
																<a href="javascript:;" onclick="deleteEntity(this);"><s:text name="lab.code.del"/></a>
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
