<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@page isELIgnored="false"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
		<meta http-equiv="Pragma" contect="no-cache" />
		<%@ include file="/jsp/include/common.jsp"%>
		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}

#roletext {
	width: 70px;
	overflow: hidden;
	white-space: nowrap;
	text-overflow: ellipsis; /* 支持 IE */
}
</style>
		<script>
		var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
		function submitvalue(actionstr){
			var df = document.labRefForm;	
				  df.action=actionstr;
				  df.submit();
		}
		var ids = "";  
		function check_all()
		{
		    var checkboxs = $('input[name="labReferenceVo.ids"]');
		    var len = checkboxs.length; 
			for(var i=0; i<len; i++){     
				if((checkboxs[i].type=="checkbox") && (checkboxs[i].name=="labReferenceVo.ids")){   
				if(checkboxs[i].checked == true){
					if(ids.length==0){
				    	ids += $(checkboxs[i]).attr('value');
				    	}else{
				    	ids +=","+$(checkboxs[i]).attr('value');
				    	}
				 }else{
				
				 if(ids.indexOf($(checkboxs[i]).attr('value'))> 0){
				 if(ids.indexOf(",")>=0){
	    				ids = ids.replace(","+$(checkboxs[i]).attr('value'),"");
	    			}else{
	    				ids = ids.replace($(checkboxs[i]).attr('value'),"");
	    			}
				 }else{
					if(ids.indexOf(",")>=0){
				    	ids = ids.replace($(checkboxs[i]).attr('value')+",","");
					}else{
					    ids = ids.replace($(checkboxs[i]).attr('value'),"");
					}
				}     
				}     
			   }
			}
		};
		
		function submitvalueforlist(){
		   var back = $("#back").val();
		   ids+=","+back;
	       W.flushThisPage(ids);
	       closeMe();
		};
		function checkId(index){
			if(!$('input[id="ids'+index+'"]').is(':checked')){
				var id  = $('input[id="ids'+index+'"]').val();
				var back = $("#back").val();
				var backs = back.split(",");
				for(var i =0;i<backs.length;i++){
					if(id==backs[i]){
						backs[i]="";
					}
				}
				back="";
				for(var i =0;i<backs.length;i++){
					back+=backs[i]+",";
				}
				$("#back").val(back);
			}
		};
		function closeMe(){
			  	api.close();
		 	}
		$(function(){
			$('input[name="referenceids"]',D).each(function(){
				$('input[value="'+$(this).val()+'"]').prop('checked',true);
				var back = $("#back").val();
				back+=$(this).val()+",";
				$("#back").val(back);
			});
		});
		</script>
	</head>
	<body class="" id="mainid">
		<s:form theme="simple" action="" method="post" name="labRefForm">
			<table id="bodyTable" class="bodytable" width="98%" cellspacing="0"
				cellpadding="0" border="0" style="min-height: 200px;">
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
									<div class="myworkingbox" style="min-height: 200px;">

										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>
																		<s:text name="stdtype"/>：
																	</label>
																</td>
																<td>
																	<s:select name="labReferenceVo.referenceTypeId"
																		list="#request.labRefTypeVoList" listValue="name"
																		listKey="id" theme="simple">
																	</s:select>
																</td>
																<td>
																	<label>
																		<s:text name="stdandard.name"/>：
																	</label>
																</td>
																<td>
																	<input type="text" name="labReferenceVo.name" id="name"
																		value="${labReferenceVo.name}" />
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="javascript:;"
																		onclick="submitvalue('showLabRef4Select.action');"><img
																			height="20" width="20"
																			src="${basePath}img/chakan.gif" /><b><s:text name="fun.query"/></b> </a>
																</td>
																<td>
																	<a id="BtnPreview" class="zPushBtn" href="javascript:;"
																		onclick="submitvalueforlist();return false;"><img
																			height="20" width="20"
																			src="<%=basePath%>img/accept.gif" /><b><s:text name="page.confirm"/></b> </a>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="0"
											cellpadding="0" width="600">
											<thead>
												<tr>
													<th>
														<input type="checkbox" id="allCheckBox"
															onclick="if(this.checked==true) { checkAll('labReferenceVo.ids'); } else { clearAll('labReferenceVo.ids'); } check_all();" />
													</th>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th property="name">
														<s:text name="stdandard.name"/>
													</th>
													<th property="referenceType.id">
														<s:text name="stdtype"/>
													</th>
													<th property="size"> 
													<s:text name="std.reg"/>
													</th>
													<th property="amount">
														<s:text name="storenumber"/>
													</th>
													<th property="unit">
														<s:text name="theme.depart"/>
													</th>
												</tr>
											</thead>
											<tbody>
												<s:if test="pageResult!=null">
													<s:if test="pageResult.resultList!=null">
														<s:set name="alllist" value="pageResult.resultList" />
														<s:iterator value="#alllist" status="st">
															<tr>
																<td class="c">
																	<input type="checkbox" name="labReferenceVo.ids"
																		id="ids${st.index}" value="${id}" onclick="check_all();checkId('${st.index}');" />
																</td>
																<td class="c">
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td>
																	${name }
																</td>
																<td>
																	${referenceTypeName }
																</td>
																<td class="1">
																	${size }
																</td>
																<td class="1">
																	${amount }
																</td>
																<td class="1">
																	${unit}
																</td>
															</tr>
														</s:iterator>
													</s:if>
												</s:if>
												<s:else>
													<tr>
														<td colspan="7" align="center" valign="middle">
															<font color="red"><s:text name="lab.msg.none"/></font>
														</td>
													</tr>
												</s:else>
											</tbody>
										</table>
									</div>
								</td>
							</tr>
							<tr>
								<td align="center"><jsp:include
										page="/jsp/include/page.jsp?formName=labRefForm" flush="true" /></td>
							</tr>
						</table>
					</td>
					<td class="MRimg"></td>
					<input type="text" id="back"  value="" / >
				</tr>
				<tr>
					<td class="FLimg"></td>
					<td class="FMimg"></td>
					<td class="FRimg"></td>
				</tr>
			</table>
		</s:form>
	</body>
</html>
