<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script language="JavaScript">
			function submitvalue(actionstr){
		      	$('form').attr('action',actionstr);
				$('form').submit();
		  	}
		  	function doAction(page){
		  		var pageNum=0;
		  		if(page=='start'){
		  			pageNum=0;
		  		}else if(page=='end'){
		  			pageNum=parseInt($("#countPage").val()-1);
		  		}else{
			  		if((parseInt($("#pages").val())+parseInt(page))<0){
		  				pageNum=0;
			  		}else{
			  			pageNum=(parseInt($("#pages").val())+parseInt(page));
			  		}
			  		if((parseInt($("#pages").val())+parseInt(page))>(parseInt($("#countPage").val()-1))){
		  				pageNum=parseInt($("#countPage").val())-1;
			  		}
		  		}
		  		$("#pages").val(pageNum);
		  		submitvalue('ACTIONPATH');
		  	}
		  	function addRow(obj){
		  		obj=$(obj);
		  		var index=obj.closest('tr').index()+1;
		  		var trObj=$("#whereTab tr").eq(0).clone();
		  		trObj.children().eq(0).html('');
	  			trObj.children().eq(2).html('');
  				trObj.children().eq(3).html('');
  				trObj.children().eq(4).html('');
  				var names='labQueryVo.listQuery['+index+'].id';
	  			trObj.children().eq(1).find("select").attr("name",names);
	  			trObj.children().eq(1).find("select").attr("value",1);
		  		$("#whereTab").append(trObj);
		  	}
		  	function delRow(obj){
		  		obj=$(obj);
		  		if(obj.closest('tr').index()>0){
		  			obj.parent().parent().remove();
		  		};
		  	}
		  	function checkName(obj){
		  		obj=$(obj);
		  		var rowIndex=obj.closest('tr').index();
		  		$.ajax({
						url:'<%=basePath%>/query/labQuery/ajaxWhere.action',
						data:{'labParameterVo.id':obj.val()},
						type:'post',
						dataType:'json',
						success:function (data){
							var likeHtml='';
							var orderHtml='';
							var html='<input type="hidden" value="'+data.nameChin+'" name="labQueryVo.listQuery['+rowIndex+'].demo"  />';
								html+='<input type="hidden" value="'+data.name+'" name="labQueryVo.listQuery['+rowIndex+'].name"/>';
								html+='<input type="hidden" value="'+data.id+'" name="labQueryVo.listQuery['+rowIndex+'].parameterId"/>';
								html+='<input type="hidden" value="'+data.queryIndex+'" name="labQueryVo.listQuery['+rowIndex+'].queryIndex" />'
								if(data.showType=='0'){
									html+='<input width="110" type="text" value="" name="labQueryVo.listQuery['+rowIndex+'].value"  />';
								}else if(data.showType=='1'){
									html+='<input type="text" name="labQueryVo.listQuery['+rowIndex+'].value" value="${value}" class="Wdate" size="15" onfocus="WdatePicker({dateFmt:\'yyyy-MM-dd\',alwaysUseStartDate:true});" />';
								}else if(data.showType=='2'){
									html+='<input type="text" name="labQueryVo.listQuery['+rowIndex+'].value" value="${value}" class="Wdate" size="15" onfocus="WdatePicker({dateFmt:\'yyyy-MM-dd HH:mm:ss\',alwaysUseStartDate:true});" />';
								}else if(data.showType=='3'){
									var selectHtml='<select name="labQueryVo.listQuery['+rowIndex+'].value"> ';
										selectHtml+=data.comboxValue.substr(data.comboxValue.indexOf("<option"),data.comboxValue.length);
										selectHtml+='<input type="hidden" name="labQueryVo.listQuery['+rowIndex+'].queryType" value="1" />';
									html+=selectHtml;
								}else if(data.showType=='4'){
									var jsonHtml=data.dialogJson;
									var nameChin=''+data.nameChin+'';
									jsonHtml=jsonHtml.replace(/\"/g,"'");
									var diaLog='<input type="text" onclick="showValue('+rowIndex+');" id="value'+rowIndex+'" name="labQueryVo.listQuery['+rowIndex+'].value"  value="${value}" />';
									    diaLog+='<input type="hidden" id="json'+rowIndex+'" name="labQueryVo.listQuery['+rowIndex+'].dialogJson" value="'+jsonHtml+'" /> ';
										diaLog+='<input type="hidden" id="colum'+rowIndex+'" name="labQueryVo.listQuery['+rowIndex+'].diaLogColum" value="'+data.diaLogColum+'" />';
									html+=diaLog;
								}	
								html+='<input type="hidden" value="'+data.showType+'" name="labQueryVo.listQuery['+rowIndex+'].showType" />';
				   	 		if(data.isVague!=null&&data.isVague=='1'){
				   	 			likeHtml+='<select name="labQueryVo.listQuery['+rowIndex+'].isVagueValue">';
				   	 			likeHtml+='<option value="LIKE">模糊查询</option>';
				   	 			likeHtml+='<option value="=">精确查询</option>';
				   	 			likeHtml+='</select>';
								likeHtml+='<input type="hidden" value="'+data.isVague+'" name="labQueryVo.listQuery['+rowIndex+'].isVague" />';
				   	 		}
			   	 			if(data.isSort!=null&&data.isSort=='1'){
				   	 			orderHtml+='<select name="labQueryVo.listQuery['+rowIndex+'].isSortValue">';
				   	 			orderHtml+='<option value="1">请选择</option>';
				   	 			orderHtml+='<option value="ASC">升序</option>';
				   	 			orderHtml+='<option value="DESC">降序</option>';
				   	 			orderHtml+='</select>';
								orderHtml+='<input type="hidden" value="'+data.isSort+'" name="labQueryVo.listQuery['+rowIndex+'].isSort" />';
				   	 		}
				   	 		obj.parent().parent().children().eq(4).html(orderHtml);
				   	 		obj.parent().parent().children().eq(3).html(likeHtml);
							obj.parent().parent().children().eq(2).html(html);
							
					   	  }
		 			 });
		  	}
		  	function showValue(index){
		  			$("#diaLogIndex").val(index);
					var url  = '${basePath}jsp/common/query/queryDialog4Select.jsp';
						$.dialog({
							id:'ids',
							content:'url:'+url,
							title:'<s:property value="getText('info.list')"/>',
							opacity:0.4,
							width:800,
							height:600,
							lock:true,
							max:false,
							min:false
						 });
				}
			$(function(){
				$("#queryDiv").queryDiv();
			});
		</script>

		<style>
			.bodytable .oRight {
				padding-left: 0;
			}
			
			html {
				_overflow-x: hidden;
			}
		</style>

	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<form theme="simple" action="" method="post" name="labQueryForm">
		<input type="hidden" value="${labQueryVo.id}" name="labQueryVo.id"/>
		<input  type="hidden" id="diaLogIndex"/>
		<input type="hidden" id="countPage" value="${labQueryVo.pageVo.countPages}" name="labQueryVo.pageVo.countPages"/>
		<input type="hidden" id="pages" value="${labQueryVo.pageVo.pages}" name="labQueryVo.pageVo.pages"/>
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
			cellpadding="0" border="0">
		<tr><td class="TLimg"></td><td class="TMimg"></td><td class="TRimg"></td></tr>
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
												${funName}
												<span><s:text name="top.index"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW" id="queryDiv">
					                      	<table>
					                      		<tr>
					                      			<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
					                      				<table cellspacing="0" cellpadding="0" border="0" id="whereTab">
					                      					<s:if test="labQueryVo.listQuerySenior==null">
				                      								<tr>
								                      						<td width="100"> <label><s:text name="query.state"/>：</label></td>
								                      						<td width="100">
								                      							<s:select headerKey="0" name="labQueryVo.listQuery[0].id" onchange="checkName(this)" headerValue="请选择条件" cssStyle="width:90px" theme="simple" list="#request.listQuery" listKey="parameterId" listValue="demo"></s:select>
							                      							</td>
								                      						<td width="120"></td>
								                      						<td width="80"></td>
								                      						<td width="50"></td>
							                      							<td width="40">
																				<img src="<%=basePath%>utils/gmt/maximize.png" onclick="addRow(this)" />
																			</td>
																			<td width="40">
																				<img src="<%=basePath%>utils/gmt/stop.png" onclick="delRow(this)" />
																			</td>
						                      						</tr>
					                      					</s:if>
															<s:else>
																<s:set name="alllist" value="labQueryVo.listQuerySenior"/>
																<s:iterator value="#alllist" status="st">	
																	<tr>
																		<td width="100"> <label><s:text name="query.state"/>：</label></td>
								                      						<td width="100">
								                      							<s:select headerKey="0" value="'${id}'" name="labQueryVo.listQuery[${st.index}].id"  onchange="checkName(this)" headerValue="请选择条件" cssStyle="width:90px" theme="simple" list="#request.listQuery" listKey="parameterId" listValue="demo"></s:select>
							                      							</td>
								                      						<td width="120">
								                      							<input type="hidden" value="${demo}" name="labQueryVo.listQuery[${st.index}].demo"  />	
							                      								<input type="hidden" value="${name}" name="labQueryVo.listQuery[${st.index}].name"/>
							                      								<input type="hidden" value="${showType}" name="labQueryVo.listQuery[${st.index}].showType"/>
							                      								<input type="hidden" value="${queryIndex}" name="labQueryVo.listQuery[${st.index}].queryIndex"/>
							                      								<input type="hidden" value="${id}" name="labQueryVo.listQuery[${st.index}].parameterId"/>
							                      								<s:if test="${showType=='0'}">
							                      									<input type="text" value="${value}" name="labQueryVo.listQuery[${st.index}].value"  />
							                      								</s:if>
							                      								<s:elseif test="${showType=='1'}">
							                      									<input type="text" name="labQueryVo.listQuery[${st.index}].value" value="${value}" class="Wdate" size="15" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',alwaysUseStartDate:true});" />
							                      								</s:elseif>
							                      								<s:elseif test="${showType=='2'}">
																						<input type="text" name="labQueryVo.listQuery[${st.index}].value" value="${value}" class="Wdate" size="15" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',alwaysUseStartDate:true});" />
							                      								</s:elseif>
							                      								<s:elseif test="${showType=='3'}">
							                      									<s:property value="comboxValue" escape="false"/>
																					<input type="hidden" name="labQueryVo.listQuery[${st.index}].comboxValue"  value="<s:property value="comboxValue" escape="true"/> "/>
																					<input type="hidden" name="labQueryVo.listQuery[${st.index}].queryType" value="1" />
							                      								</s:elseif>
							                      								<s:elseif test="${showType=='4'}">
							                      									<input type="text" onclick="showValue('${st.index}');" id="value${st.index}" name="labQueryVo.listQuery[${st.index}].value"  value="${value}" />
																					<input type="hidden" id="json${st.index}" name="labQueryVo.listQuery[${st.index}].dialogJson" value="<s:property value="dialogJson" escape="true"/>" />
																					<input type="hidden" id="colum${st.index}" name="labQueryVo.listQuery[${st.index}].diaLogColum" value="<s:property value="diaLogColum" escape="true"/>" />
							                      								</s:elseif>
							                      							</td>
								                      						<td width="80">
								                      							<s:if test="${isVague=='1'}">
								                      								<s:select theme="simple" value="'${isVagueValue}'" list="#{'LIKE':getText('mohu.query'),'=':getText('jingque.query')}" name="labQueryVo.listQuery[${st.index}].isVagueValue"></s:select>
								                      								<input type="hidden" value="${isVague}" name="labQueryVo.listQuery[${st.index}].isVague"/>
								                      							</s:if>
								                      						</td>
								                      						<td width="50">
								                      							<s:if test="${isSort=='1'}">
								                      								<s:select name="labQueryVo.listQuery[${st.index}].isSortValue" theme="simple" value="'${isSortValue}'" list="#{'1':getText('plseaseselect'),'ASC':getText('up.order')},'DESC':getText('down.order')}"></s:select>
								                      								<input type="hidden" value="1" name="labQueryVo.listQuery[${st.index}].isSort" />
								                      							</s:if>
								                      						</td>
							                      							<td width="40">
																				<img src="<%=basePath%>utils/gmt/maximize.png" onclick="addRow(this)" />
																			</td>
																			<td width="40">
																				<img src="<%=basePath%>utils/gmt/stop.png" onclick="delRow(this)" />
																			</td>
																		</tr>	
																</s:iterator>
															
															</s:else>				                      						
									                    </table>
									                </td>
								                </tr>
					                      	</table>
					                    </div>
					                    <div class="FUNCIONBARNEW">
											<table>
												<tr>
													<td class="blockTd"
														style="padding: 6px 10px; vertical-align: center;">
														<table cellspacing="0" cellpadding="0" border="0">
															<tr>
																
																<td>
																		<td><a id="BtnPreview" class="zPushBtn" href="javascript:void(0);" onclick="submitvalue('ACTIONPATH'); return false;"><img height="20" width="20" src="${basePath}img/chakan.gif"/><b><s:text name="fun.query"/></b></a></td>
																</td>
															</tr>
														</table>
													</td>
												</tr>
											</table>
										</div>
										<table class="myworkingboxttable" cellspacing="0"
											cellpadding="0">
											
											<s:if test="labQueryVo.listTitle!=null">
												<s:set name="alllist" value="labQueryVo.listTitle" />
												<tr>
													<th>
														<img src="<%=basePath%>img/icon_drag.gif" />
													</th>
													<s:iterator  value="#alllist" >
														<th>${name}</th>
													</s:iterator>
												</tr>
											</s:if>
											<s:if test="labQueryVo.listValue!=null">
												<s:set name="alllist" value="labQueryVo.listValue"/>
													<s:iterator id="one"  value="#alllist" status="st" >
														<tr>
															<td>
																	${st.index+1}
															</td>
															<s:iterator value="#one.listValue" id="two">
																<td>${two.name}</td>
															</s:iterator>
														</tr>
													</s:iterator>
											</s:if>
											</table>
										</div>
								</td>
							</tr>
							<tr>
					      <tr>
							<td align="center">
								<table border="0" cellPadding="2" align="center" cellSpacing="0" width="75%" bgcolor="#FFFFFF">
								<tr>
									<td align="center" class="tou" colspan="5">
										<div class="pagecontrol">
											<s:text name="page.current"/>${labQueryVo.pageVo.pages+1}<s:text name="page.page"/>|&nbsp;<s:text name="page.total"/>${labQueryVo.pageVo.countPages}<s:text name="page.page"/>&nbsp;
											<s:if test="${labQueryVo.pageVo.pages}==0">
												<s:text name="top.index"/>
											</s:if>
											<s:else>
												<a href="javascript:;" onclick="doAction('start');"><s:text name="top.index"/></a>
											</s:else>
											&nbsp;
											<s:if test="${labQueryVo.pageVo.pages}==0">
												<s:text name="page.before"/>
											</s:if>
											<s:else>
												<a href="javascript:;" onclick="doAction('-1');"><s:text name="page.before"/></a> 
											</s:else>
											&nbsp; 
											<s:if test="'${labQueryVo.pageVo.pages}'=='${labQueryVo.pageVo.countPages-1}'||'${labQueryVo.pageVo.countPages}'=='0'">
												<s:text name="page.next"/>
											</s:if>
											<s:else>
												<a href="javascript:;" onclick="doAction('1');"><s:text name="page.next"/></a>
											</s:else>
											 &nbsp;
											 <s:if test="'${labQueryVo.pageVo.pages}'=='${labQueryVo.pageVo.countPages-1}'||'${labQueryVo.pageVo.countPages}'=='0'">
											 	<s:text name="page.tail"/>
											 </s:if>
											 <s:else>
											 	<a href="javascript:;" onclick="doAction('end');"><s:text name="page.tail"/></a>
											 </s:else>
											 	
										</div>
									</td>
								</tr>
							</td>
					       </tr>
				          </tr>
						</table>
				    </td>
			 <td class="MRimg"></td>
				</tr>
				<tr>
				<td class="FLimg"></td><td class="FMimg"></td><td class="FRimg"></td>
        </tr>
				
			</table>
		</form>
	 <%@ include file="/jsp/include/foot.jsp"%> 
	</body>
</html>
