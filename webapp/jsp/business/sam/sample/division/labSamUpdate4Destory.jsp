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
</style>
<script>
		function addOneEachRows(obj){
		
			var temp=$(obj).closest('tr').find('input[name$="seqNum"]');
			var tempIndex=temp.attr('id');
			var i=$(obj).closest('tr').find('input[name="sampnum"]').val();
			if(isNaN(i)||i==''){
				return false;
			}
			for(var j=0;j<i;j++){
				var tempTr=$(obj).closest('tr').clone();
				tempTr.find('input[name^="labSamMainVo.labSamVoList"]').each(function(n){
				    var name = $(this).attr('name').replace('labSamVoList['+tempIndex+']','labSamVoList['+tempIndex+''+ j +']');
					$(this).attr('name',name);
					$(this).attr('id',tempIndex+j);
				   
				});
				tempTr.find('input[name$="seqNum"]').each(function(n){
				if(i<10){
					$(this).val($(this).val()+"-0"+j);
					$(this).attr($(this).val()+"-0"+j);
					}else{
					$(this).val($(this).val()+"-"+j);
					$(this).attr('key',$(this).val()+"-"+j);}
			
			});
			tempTr.find('td').eq(14).remove();
			tempTr.find('td').eq(14).remove();
			tempTr.append('<td><label> <s:text name="sam.number"/> </label></td> <td><input type="text" size="2" name="labSamMainVo.labSamVoList['+tempIndex+''+ j +'].total" /> </td> ');
			tempTr.find('td').eq(15).append('<a href="javascript:;" onclick="deleteOne(this);return false;"><s:text name="lab.code.del"/></a>');
			$('#oneEach').append(tempTr);
			}
			$(obj).closest('tr').remove();
		}
		
		function deleteOne(obj){
		if(confirm('<s:propery value="getText('msg.confirm.del')"/>')){
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
																	<l:a uri="sam/labSam/updateLabSam4Destory.action" value="post.commit" onclick="javascript:if(confirm('确认要提交吗?')){goAction('updateLabSam4Destory.action');}return false;" />
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
										<div class="Formtable oneEachDiv" style="display: black;">
										<div class="Formtabletitle">
											<span><s:text name="sam.info"/></span>
											<label style="float: right;padding-right: 10px;"></label>
										</div>
										<table class="FormtableCon" id="oneEach">
										<tr key="0">
												<th><label><s:text name="sam.info"/>：</label></th>
												
												<th><label><s:text name="sam.name"/>：</label></th>
												
												<th><label><s:text name="regular.no"/>：</label></td>
												
												<th><label><s:text name="sam.number"/>：</label></th>
												
												<th><label><s:text name="save.style"/>：</label></th>
												
												<th><label><s:text name="sam.site"/>：</label></th>
												
												<th><label><s:text name="sam.classify"/>：</label></th>
												
												<th><label><s:text name="left.sam.select"/>：</label></th>
												
											</tr>
										<s:iterator status="st" value="labSamMainVo.labSamVoList">
											<tr>
											<input name="labSamMainVo.labSamVoList[${st.index }].seqNum" value="${seqNum }" id="${st.index }" size="15" type="hidden"/>
											<td class="c">${sampCode }<input type="hidden" name="labSamMainVo.labSamVoList[${st.index }].id" value="${id }"/></td>
											<td>${name }<input value="${name }" name="labSamMainVo.labSamVoList[${st.index }].name" type="hidden"/></td>
											<td>${specifications }<input value="${specifications }" name="labSamMainVo.labSamVoList[${st.index }].specifications" type="hidden"/></td>
											<td class="c">${total }<input value="${total }" name="labSamMainVo.labSamVoList[${st.index }].total" type="hidden"/></td>
											<td>${saveMode }<input value="${saveMode }" name="labSamMainVo.labSamVoList[${st.index }].saveMode" type="hidden" /></td>
											<td>${sampAddr }<input value="${sampAddr }" name="labSamMainVo.labSamVoList[${st.index }].sampAddr" type="hidden"/></td>
											<td>${samTypeName }
												<input value="${samTypeId}" name="labSamMainVo.labSamVoList[${st.index }].samTypeId" type="hidden" readonly="readonly"/>
												<input value="${samTypeName}" name="labSamMainVo.labSamVoList[${st.index }].samTypeName" type="hidden" readonly="readonly"/>
											</td>
											<td class="c"><input type="checkbox" name="labSamMainVo.labSamVoList[${st.index }].isDestory"/> </td>
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
