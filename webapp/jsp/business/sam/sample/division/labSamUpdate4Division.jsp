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
v
html {
	_overflow-x: hidden;
}
</style>
<script>
		function addOneEachRows(obj){
		
			var temp=$(obj).closest('tr').find('input[name$="seqNum"]');
			var tempIndex=temp.attr('id');
			var num=$(obj).parent().parent().parent().find('tr').length;
			var newIndex=parseInt($(obj).parent().parent().parent().find('tr').eq(num-1).attr('key'));
			var i=$(obj).closest('tr').find('input[name="sampnum"]').val();
			if(isNaN(i)||i==''){
				return false;
			}
			if(isNaN(newIndex)){
				return false;
			}
			for(var j=0;j<i;j++){
				newIndex++;
				var tempTr=$(obj).closest('tr').clone();
				tempTr.find('input[name^="labSamMainVo.labSamVoList"]').each(function(n){
				    var name = $(this).attr('name').replace('labSamVoList['+tempIndex+']','labSamVoList['+newIndex +']');
					$(this).attr('name',name);
					$(this).attr('id',tempIndex+j);
				});
				tempTr.find('select[name^="labSamMainVo.labSamVoList"]').each(function(){
				 var name = $(this).attr('name').replace('labSamVoList['+tempIndex+']','labSamVoList['+newIndex+']');
					$(this).attr('name',name);
				});
				tempTr.find('input[name$="seqNum"]').each(function(n){
				if(i<10){
					$(this).val($(this).val()+"-0"+j);
					$(this).attr('key',$(this).val()+"-0"+j);
				}else{
					$(this).val($(this).val()+"-"+j);
					$(this).attr('key',$(this).val()+"-"+j);
				}
			});
			var str=tempTr.find('td').eq(6);
			tempTr.find('td').eq(6).html('<a href="javascript:;" onclick="deleteOne(this);return false;"><s:text name="lab.code.del"/></a>');
			tempTr.attr('key',newIndex);
			$('#oneEach').append(tempTr);
			}
			$(obj).closest('tr').remove();
		}
		
		function deleteOne(obj){
		if(confirm('<s:property value="getText('msg.confirm.del')"/>')){
			$(obj).parent().parent().remove();
		}
	};
</script>
	</head>
	<body id="mainid">
		<form method="post" name="form" enctype="multipart/form-data" id="form">
		<input name="labSamMainVo.id" value="${labSamMainVo.id }" type="hidden" />
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
												<span><s:text name="details.info"/></span>
											</h2>
										</div>
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
																	<l:a uri="sam/labSam/updateLabSam4Division.action" value="lab.code.save" onclick="javascript:if(confirm('确认要保存吗?')){goAction('updateLabSam4Division.action');}return false;" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle" onclick="$(this).next().toggle()" style="cursor: pointer;" title='<s:property value="getText('lab.click.here')"/>'>
												<span><s:text name="base.info"/></span>
											</div>
											<table class="FormtableCon">
												<tr>
													<td>
														<label>
															<s:text name="task.no"/>：
														</label>
													</td>
													<td>
														${labSamMainVo.taskCode}
													</td>
													<td>
														<label>
															<s:text name="sam.number"/>：
														</label>
													</td>
													<td>
														${labSamMainVo.num }
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="cust.name"/>：
														</label>
													</td>
													<td>
														${labSamMainVo.customer}
													</td>
													<td>
														<label>
															<s:text name="call.people"/>：
														</label>
													</td>
													<td>
														${labSamMainVo.contacts }
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="called.info"/>：
														</label>
													</td>
													<td>
														${labSamMainVo.contactPhone }
													</td>
													<td>
														<label>
															<s:text name="sam.if"/>：
														</label>
													</td>
													<td>
													<s:if test="${labSamMainVo.isDivision == 'Y'}">
														<s:text name="lab.yes"/>
													</s:if>
													<s:if test="${labSamMainVo.isDivision == 'N'}">
														<s:text name="lab.no"/>
													</s:if>
													</td>
												</tr>
												<tr>
													<td>
														<label>
															<s:text name="msg.attachment"/>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;：
														</label>
													</td>
													<td colspan="">
														<s:if test="${fn:length(loadList)>0}">
															<s:iterator status="st2" value="#request.loadList" id="">
																<span>
																${name}
																<s:if test="${pdfName!=null}">
																	&nbsp;&nbsp;<a  target="_blank" href="<%=basePath%>utils/onlinepdf/index.jsp?doc=${pdfName}""><img src="<%=basePath %>/img/query.gif"/></a>
																</s:if>
																&nbsp;&nbsp;<a href="<%=basePath%>utils/upload/down.jsp?fileUrl=${path}&fileName=${name}"><img src="<%=basePath %>/img/export.gif"/></a>
															</span>
															</s:iterator>&nbsp;&nbsp;&nbsp;
														</s:if>
													</td>
												</tr>
											</table>
										</div>
										<div class="Formtable oneEachDiv" >
										<div class="Formtabletitle">
											<span><s:text name="sam.info"/></span>
											<label style="float: right;padding-right: 10px;"></label>
										</div>
										<table class="FormtableCon" id="oneEach">
										<tr id="titleEach">
											<th><label><s:text name="origin.no"/></label></th>
											<th><label><s:text name="sam.name"/></label></th>
											<th><label><s:text name="sam.classify"/></label></th>
											<th><label><s:text name="regular"/></label></th>
											<th><label><s:text name="remark.next"/></label></th>
											<th><label><s:text name="assigned.large"/></label></th>
											<th><label><s:text name="sam.number"/></label></th>
										</tr>
										<s:iterator value="labSamMainVo.labSamVoList" status="st">
											<tr key="${st.index}">
												<input type="hidden" value="${id}" name="labSamMainVo.labSamVoList[${st.index }].id"/>
												<td class="c">
													<input style="background-color: #DDDDDD;" readonly="readonly" onchange="eachToChangeIndex(this);" size="5" id="${st.index }" type="text" name="labSamMainVo.labSamVoList[${st.index }].seqNum" key="0" value="YP0${st.index }" />
												</td>
												<td class="c">
													<input style="background-color: #DDDDDD;" readonly="readonly" size="10" type="text" name="labSamMainVo.labSamVoList[${st.index }].name" value="${name}" />
												</td>
												<td class="c">
													<input readonly="readonly" size="10" type="hidden" name="labSamMainVo.labSamVoList[${st.index }].samTypeId" value="${samTypeId}" />
													<input style="background-color: #DDDDDD;" readonly="readonly" size="10" type="text" name="labSamMainVo.labSamVoList[${st.index }].samTypeName" value="${samTypeName}"  />
												</td>
												<td class="c">
													<input  size="10" type="text" name="labSamMainVo.labSamVoList[${st.index }].specifications" value="${specifications }" />
												</td>
												<td class="c">
													<input  type="text" size="15" name="labSamMainVo.labSamVoList[${st.index }].remark" value="${remark }"/>
												</td>
												<td class="c">
													<select name="labSamMainVo.labSamVoList[${st.index }].isDestory" style="width: 80px;">
														<option value="N"><s:text name="sample.check"/></option>
														<option value="Y"><s:text name="left.sam"/></option>
													</select>
												</td> 
												<td class="c">
													<input type="text" name="sampnum" size="3" onblur="addOneEachRows(this);return false;"/>
												</td>
											</tr>
										</s:iterator>
										</table>
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
	<script language="javascript" type="text/javascript">
		function required ()  
		{
			
		}
	</script>
</html>
