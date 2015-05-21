<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ include file="/jsp/include/common.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title></title>
		<script type="text/javascript">
	 function submitvalue(actionstr){
	       if(check('labApparaVo.ids'))
	       {
	    	var path = '${basePath}';
            var df = document.forms[0];
            df.action = path+actionstr;
		    df.submit();
		  	}
     }
     function check(name){
				var el = document.getElementsByTagName('input');     
				var len = el.length; 
				var m = 0;    
				for(var i=0; i<len; i++)
				{         
					if((el[i].type=="checkbox") && (el[i].name==name))         
					{             
						if(el[i].checked == true){
					    	m = m + 1;
					 	}      
					}     
				}  
				if(m<1){
					alert('<s:property value="getText('plseselectapp')"/>');
					return false;
				}else{
					return true;
				}
	}
       function importExcel(action){
       		 var url='<%=basePath%>appara/excel/'+action;
			$.dialog({
				id:'power',
				content:'url:'+url,
				title:'<s:property value="getText('applistmulup')"/>',
				maxBtn:false,
				rang: true,
				drag: true,
				resize: false,
				bgcolor:'#000',
				opacity:0.4,
				width:100, 
				iconTitle:false,
				btnBar:false,
				height:80,
				lock:true,
				max:false,
				min:false,
				autoPos:{left:'center',top:'center'}
			 });
       }
       function ajax2TwoCode(){
		   if(check('labApparaVo.ids')){
			var ids=new Array();
				$(".appCoder:checked").each(function(i){
					ids+=$(this).closest('tr').find('input[name*="ids"]').val()+',';
				});
				var url  = '${basePath}appara/showLabApp4printTwoCode.action?labApparaVo.id='+ids;
				$.dialog({
					id:'roleId',
					content:'url:'+url,
					title:'<s:property value="getText('print.2d')"/>',
					opacity:0.4,
					width:600,
					height:400,
					lock:true
				 });
			 }	
		  }
		 function ajax2BarCode(){
			if(check('labApparaVo.ids')){
				var ids=new Array();
				$(".appCoder:checked").each(function(i){
				ids+=$(this).closest('tr').find('input[name*="ids"]').val()+',';
				});
				var url  = '${basePath}appara/showLabApp4printBarCode.action?labApparaVo.id='+ids;
				$.dialog({
					id:'roleId',
					content:'url:'+url,
					title:'<s:property value="getText('print.bar')"/>',
					opacity:0.4,
					width:600,
					height:400,
					lock:true
				 });
			  }	
		  }
		  //测试
function uploadFile(busId,busType){
	   fileTypes = '*.*';
	   var url='<%=basePath%>jsp/source/appara/labUpLoads.jsp?busId='+busId+'&fileTypes='
	   +fileTypes+'&busType='+busType+'&urls=/appara/listLabAppara.action';
	   $.dialog({
			id:'id',
			content:'url:'+url,
			title:'<s:property value="getText('import.app.list')"/>',
			opacity:0.4,
			width:300, 
			height:300,
			lock:true,
			max:false,
			min:false,
			resize:false
		 });
	}//多附件labUpLoads.jsp height:300, 
	function deleteApp(id){
		if(confirm('<s:property value="getText('lab.confirm.delete')"/>')){
		    window.location.href='${basePath}appara/delLabApp.action?labApparaVo.ids='+id;
		}
	}
</script>
	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form action="" method="post" name="customeractivitiesForm" theme="simple">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="MLimg"></td>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName }：
												<span>[<font color="blue">${labApparaVo.apparaTypeName}</font>]</span>
											</h2>
										</div>

										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<label>
																		<s:text name="app.name"/>：
																	</label>
																</td>
																<td>
																	<input name="labApparaVo.name" id="name" value="${labApparaVo.name}" type="text" />
																</td>
																<td>
																	<label>
																		<s:text name="app.no"/>：
																	</label>
																</td>
																<td>
																	<input name="labApparaVo.no" value="${labApparaVo.no}" type="text" />
																</td>
																<td style="vertical-align: center;">
																	<label>
																		<s:text name="curr.state"/>：
																	</label>
																</td>
																<td>
																	<s:select theme="simple" cssStyle="width:80px;" name="labApparaVo.status" headerKey="" headerValue="" list="#{'0':getText('common'),'1':getText('finished.check'),'2':getText('report.repair'),'3':getText('stoped'),'4':getText('dropped')}" value="${labApparaVo.status}" />
																</td>
																<td>
																	<l:a uri="/appara/listLabApparaItems.action" value="fun.query" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																<td>
																	<s:if test="${labApparaVo.typeId != '0' && labApparaVo.typeId != null}">
																		<l:a uri="appara/preAddLabAppara.action?typeId=${labApparaVo.typeId}" value="lab.code.add" />
																	</s:if>
																</td>
																<td>
																	<l:a uri="appara/delLabApp.action" onclick="submitvalue('delLabApp.action');return false;" value="lab.code.deleteall" />
																</td>
																<td>
																	<!-- 
																	<l:a uri="appara/preAddLabAppara.action" onclick="submitvalue('exportLabAppExcel.action?labApparaVo.typeId=${labApparaVo.typeId}');return false;" value="export.app.list" />
																	 -->
																	 
																	 <l:export params="labReagentVo,pageResult" type="excel" source="${labApparaVo.filePath}" target="${funName}-${now}.xls" value="导出${funName}" />
																</td>
																<td>
																	<l:a uri="appara/preAddLabAppara.action" onclick="importExcel('getLabAppExcel.action?labApparaVo.typeId=${labApparaVo.typeId}');return false;" value="import.app.list" />
																</td>
																<td>
																	<l:a uri="appara/preAddLabAppara.action" onclick="ajax2TwoCode();return false;" value="print.2d" />
																</td>
																<td>
																	<l:a uri="appara/preAddLabAppara.action" onclick="ajax2BarCode();return false;" value="print.bar" />
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="1" cellpadding="0">
											<thead>
												<tr>
													<th width="40">
														<input type="checkbox" id="ids" onclick="if(this.checked==true) { checkAll('labApparaVo.ids'); } else { clearAll('labApparaVo.ids'); }" />
													</th>
													<th width="40">
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<th property="name">
														<s:text name="app.name"/>
													</th>
													<th property="no">
														<s:text name="app.no"/>
													</th>
													<th property="code">
														<s:text name="build.no"/>
													</th>
													<th property="spec">
														<s:text name="regular.sys"/>
													</th>
													<th>
														<s:text name="sam.state"/>
													</th>
													<th>
														<s:text name="lab.code.ops"/>
													</th>
												</tr>
											</thead>
											<tbody>
												<s:if test="pageResult!=null">
													<s:if test="pageResult.resultList!=null">
														<s:set name="alllist" value="pageResult.resultList" />
														<s:iterator value="#alllist" status="st">
															<s:if test="alarm==1">
																<tr style="background-color: #ffdee0">
															</s:if>

															<tr>
																<td class="c">
																	<input type="checkbox" name="labApparaVo.ids" id="ids<s:property value="#st.index+1" />" value="${id}" class="appCoder" />
																</td>
																<td class="c">
																	${pageSizex* currenPagex+st.index+1}
																</td>
																<td class="l">
																	<s:property value="name" />
																</td>
																<td class="l">
																	<s:property value="no" />
																</td>
																<td class="l">
																	<s:property value="code" />
																</td>
																<td class="l">
																	<s:property value="spec" />
																</td>
																<td class="c">
																	<s:if test="status==0"><s:text name="common"/></s:if>
																	<s:if test="status==1"><s:text name="finished.check"/></s:if>
																	<s:if test="status==2"><s:text name="report.repair"/></s:if>
																	<s:if test="status==3"><s:text name="stoped"/></s:if>
																	<s:if test="status==4"><s:text name="dropped"/></s:if>
																	<s:if test="status==6"><s:text name="faulted"/></s:if>
																	<s:if test="status==7"><s:text name="borrowed"/></s:if>
																</td>
																<td class="c">
																	<s:if test="${status == '1'}">
																		<l:a href="#" uri="appara/showLabAppara.action?labApparaVo.typeId=${labApparaVo.typeId}&labApparaVo.id=${id}" value="look.check" />
																	</s:if>
																	<s:else>
																		<l:a href="#" uri="appara/showLabAppara.action?labApparaVo.typeId=${labApparaVo.typeId}&labApparaVo.id=${id}" value="look.check" />
																		<l:a href="#" uri="appara/preUpdateLabApp.action?labApparaVo.id=${id}" value="theme.modify" />
																		<l:a href="#" uri="appara/delLabApp.action?labApparaVo.id=${id}" onclick="deleteApp('${id}');" value="config.del" />
																	</s:else>
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
								<td align="center"><jsp:include page="/jsp/include/page.jsp?formName=customeractivitiesForm" flush="true" /></td>
							</tr>
						</table>
					</td>
					<td class="MRimg"></td>
				</tr>
			</table>
		</form>
	</body>
</html>
