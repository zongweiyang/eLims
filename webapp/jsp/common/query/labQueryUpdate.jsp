<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<style>
* {
	font-size: 9pt;
	line-height: 20px
}

.title {
	font-size: 16px;
	color: #0000FF;
	font-weight: bold
}

.con {
	margin-left: 15px;
}

.STYLE1 {
	color: #FF0000
}

.STYLE2 {
	color: #0000FF
}

.STYLE3 {
	color: #009900
}
</style>



		<style>
.TabTable {
	padding-top: 0;
}

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

.myworkingboxttable tr {
	text-align: center;
}
</style>
	</head>
	<script language=javascript> 
			  	function submitvalue(actionstr){
					$('form').attr('action',actionstr);
					$('form').submit();
				}
				function showParameter(){
					var sql=$("#sqlInfo").val();
					if($("#parameTable tr").length>0){
						var obj=$("#parameTable tr").eq(0).clone();   
						$("#parameTable").html('');
						obj.appendTo("#parameTable");
					}
					$.ajax({
						url:'<%=basePath%>/query/labQuery/ajaxColumProperty.action',
						data:{'labQueryVo.name':sql,'labQueryVo.sqlType':$('input:radio[name="labQueryVo.sqlType"]:checked').val()},
						type:'post',
						dataType:'json',
						success:function (data){
							var html="";
							var index=0;
							   $.each(data,function(a,b){
							   			 html+="<tr>";
							   			 html+='<td>'+b.name+'<input type="hidden" value="'+b.name+'" name="labQueryVo.listLabParameter['+index+'].name"/></td>';
							   			 html+='<td><input  name="labQueryVo.listLabParameter['+index+'].nameChin" type="text"/></td>';
							   			 html+='<td><input name="labQueryVo.listLabParameter['+index+'].isVague" type="checkBox" '+b.isVague+' value="1"/></td>';
							   			 html+='<td><select onchange="changeSelect(this)" name="labQueryVo.listLabParameter['+index+'].showType"><option value="0">文本框</option><option value="1">日期控件</option><option value="2">时间控件</option><option value="3">下拉框</option><option value="4">弹出层</option></select></td>';
							   			 html+='<td><input type="text" value="'+(index+1)+'" name="labQueryVo.listLabParameter['+index+'].sort"/></td>';
							   			 html+='<td><input name="labQueryVo.listLabParameter['+index+'].isSort" type="checkBox" value="1"/></td>';
							   			 html+='</tr>';
							   			 index++;
         						 } );  
							  $("#parameTable").append(html);
					   	  }
		 			 });
				}
				function addRow(){
					$("#parameTable tr").eq(0).clone().appendTo("#parameTable");   
				}
				function delRow(obj){
					obj=$(obj);
					var rowNum=obj.closest("tr").index();
					$("#parameTable tr").eq(rowNum).remove();
				}
					function checkFun(obj){
						$('#funId').val("");
						$('#funName').val("");
						var thisVal = $(obj).val();
						var name=$(obj).val();;
						if(name=='0'){
							return false;
						}
						var oo=name.split('|');
						$('#funId').val(oo[0]);
						$('#funName').val(oo[1]);
					}
				function checkComQuery(obj){
					obj=$(obj);
					if(obj.attr("checked")=='checked'&&obj.val()=='1'){
						$("#comQuery td:gt(1)").hide();
					}else{
						$("#comQuery td").show();
					}
				}
				$(function(){
					if($("#allQuery").attr("checked")=='checked'&&$("#allQuery").val()=='1'){
						$("#comQuery td:gt(1)").hide();
					}else{
						$("#comQuery td").show();
					}
				
				});
				function changeSelect(obj){
					obj=$(obj);
					var index=obj.closest('tr').index()-1;
					if(obj.val()=='3'){
						var htmlTest='<input type="hidden" name="labQueryVo.listLabParameter['+index+'].comboxValue" id="comboxValue'+index+'" />';
							htmlTest+='<input type="hidden" name="labQueryVo.listLabParameter['+index+'].combox" id="combox'+index+'" />';
							htmlTest+='<a onclick="showValue('+index+')" style="cursor: hand;"><s:text name="show.value"/></a>';
						obj.parent().append(htmlTest);
						showValue(index);
					}else if(obj.val()=='4'){
						obj.siblings().remove();
						var htmlTest='<input type="text" name="labQueryVo.listLabParameter['+index+'].dialogJson"  id="dialogJson'+index+'"/>';
							htmlTest+='<input type="text" name="labQueryVo.listLabParameter['+index+'].diaLogColum"  id="diaLogColum'+index+'" />';
							htmlTest+='<input type="text" id="diaLogAction'+index+'" name="labQueryVo.listLabParameter['+index+'].diaLogAction"  />';
						obj.parent().append(htmlTest);
						showDiaLog(index);
					}else{
						obj.siblings().remove()
					}
				}
				function showValue(index){
					$("#comboBoxId").val(index);
					var url  = '${basePath}jsp/common/query/labQueryComBox4Select.jsp';
						$.dialog({
							id:'ids',
							content:'url:'+url,
							title:'下拉框显示值',
							opacity:0.4,
							width:500,
							height:300,
							lock:true,
							max:false,
							min:false
						 });
				}
				function showDiaLog(index){
					$("#comboBoxId").val(index);
					var url  = '${basePath}/query/labQuery/showLabQueryDiaLog4Select.action';
					$.dialog({
							id:'ids',
							content:'url:'+url,
							title:'弹出层显示信息',
							opacity:0.4,
							width:500,
							height:300,
							lock:true,
							max:false,
							min:false
						 });
				}
				function showCountParameter(){
					if($("#countParameTable tr").length>0){
						var obj=$("#countParameTable tr").eq(0).clone();   
						$("#countParameTable").html('');
						obj.appendTo("#countParameTable");
					}
					var countSql=$("#countSql").val();
					$.ajax({
						url:'<%=basePath%>/query/labQuery/ajaxColumProperty.action',
						data:{'labQueryVo.name':countSql,'labQueryVo.selectSql':'1'},
						type:'post',
						dataType:'json',
						success:function (data){
							var html="";
							var index=0;
							   $.each(data,function(a,b){
							   		html+='<tr>';
							   		html+='<td>'+b.name+' <input type="hidden" value='+b.name+' name="labQueryVo.listLabParameterCountVo['+index+'].name" /></td>';
							   		html+='<td><input type="text" name="labQueryVo.listLabParameterCountVo['+index+'].nameChin"  />';
							   		html+='<input type="hidden" value="1" name="labQueryVo.listLabParameterCountVo['+index+'].selectSql" /></td>';
							   		html+='</tr>';
							   		index++;
         						 } );  
							 $('#countParameTable').append(html);
					   	  }
		 			 });
				}
			</script>
	<body>
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td class="TLimg"></td>
				<td class="TMimg"></td>
				<td class="TRimg"></td>
			</tr>
			<tr>
				<td class="MLimg"></td>
				<td id="bodyCell" class="oRight">
					<div class="myworkingbox">
						<div class="myworkingboxttitle">
							<h2>
								${funName}：
								<span><s:text name="admin.add"/></span>
							</h2>
						</div>
						<form theme="simple" action="" method="post" name="labQueryForm">
							<input type="hidden" id="comboBoxId" />
							<input type="hidden" id="funName" name="labQueryVo.id" value="${labQueryVo.id}" />
							<div class="TabTable">
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<l:a uri="back" value="msg.back" />
															<l:a uri="query/labQuery/updateLabQuery.action" img="/img/add.gif" value="lab.code.save" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="base.info"/></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td>
												<label>
													<s:text name="function.name"/>：
												</label>
											</td>
											<td>
												<s:select list="#request.listLabFunction" cssStyle="width:153px" headerKey="0" headerValue="------请选择-----" onchange="checkFun(this)" theme="simple" value="'${labQueryVo.funId}'+'|'+'${labQueryVo.funName}'" listValue="name" listKey="id+'|'+name"></s:select>
												<input type="hidden" id="funName" name="labQueryVo.funName" value="${labQueryVo.funName}" />
												<input type="hidden" id="funId" name="labQueryVo.funId" value="${labQueryVo.funId}" valType="required" msg="请选择功能名称" />
											</td>
											<td>
												<label>
													<s:text name="sql.type"/>：
												</label>
											</td>
											<td>
												<input type="radio" name="labQueryVo.type" <s:if test="${labQueryVo.type == 'N'}"> checked="checked"</s:if> value="N" id="isQuery" />
												<s:text name="simple.sql"/>
												<input type="radio" name="labQueryVo.type" value="N" <s:if test="${labQueryVo.type == '1'}"> checked="checked" </s:if> id="isQueryY" />
												<s:text name="store.pro"/>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="query.type"/>：
												</label>
											</td>
											<td>
												<input type="radio" name="labQueryVo.queryType" onclick="checkComQuery(this)" <s:if test="${labQueryVo.queryType == '0'}"> checked="checked"</s:if> value="0" />
												<s:text name="ord.query"/>
												<input type="radio" name="labQueryVo.queryType" id="allQuery" <s:if test="${labQueryVo.queryType == '1'}"> checked="checked"</s:if> value="1" onclick="checkComQuery(this)" />
												<s:text name="advanced.query"/>
											</td>
											<td></td>
											<td></td>
										</tr>
										<tr id="comQuery">
											<td>
												<label>
													<s:text name="query.type"/>
												</label>
											</td>
											<td>
												<input type="radio" name="labQueryVo.sqlType" value="0" <s:if test="${labQueryVo.sqlType == '0'}"> checked="checked"</s:if> />
												<s:text name="single.query"/>
												<input type="radio" name="labQueryVo.sqlType" value="1" <s:if test="${labQueryVo.sqlType == '1'}"> checked="checked"</s:if> />
												<s:text name="multi.query"/>
											</td>
											<td>
												<label>
													<s:text name="sev.row"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labQueryVo.rowNum" value="${labQueryVo.rowNum}" />
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="def.info"/></span>
										<span style="color: red;"><s:text name="key.like"/></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td colspan="6" style="text-align: center;">
												<textarea rows="8" id="sqlInfo" cols="120" name="labQueryVo.name">${labQueryVo.name}</textarea>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<label style="float: right; padding-right: 10px;" onclick="$(this).parent().next().toggle();">
											[&nbsp;
											<font color="blue"><s:text name="open.close"/></font>&nbsp;]
										</label>
										<span><a onclick="showParameter()" style="cursor: hand" title="点击获取显示列表"><s:text name="param.info.k"/></a> </span>
									</div>
									<div style="margin: 10px 50px 10px 50px; display: none;">
										<table border="0" class="myworkingboxttable" width="100%" id="parameTable">
											<tr id="titileId">
												<th>
													<s:text name="perference"/>
												</th>
												<th>
													<s:text name="cn.name"/>
												</th>
												<th>
													<s:text name="fuzzy.query"/>
												</th>
												<th>
													<s:text name="show.style"/>
												</th>
												<th>
													<s:text name="query.order"/>
												</th>
												<th>
													<s:text name="order.or.not"/>
												</th>
											</tr>
											<s:set name="alllist" value="labQueryVo.listLabParameter" />
											<s:iterator value="#alllist" status="st">
												<tr>
													<td>
														<input type="hidden" value="${name}" name="labQueryVo.listLabParameter[${st.index}].name" />
														${name}
													</td>
													<td>
														<input value="${nameChin}" name="labQueryVo.listLabParameter[${st.index}].nameChin" type="text" />
													</td>
													<td>
														<input disabled="disabled" name="labQueryVo.listLabParameter[${st.index}].isVague" type="checkBox" <s:if test="${isVague}==1">checked="checked"</s:if> value="1" />
														<s:hidden name="labQueryVo.listLabParameter[${st.index}].isVague" value="%{isVague}"></s:hidden>
													</td>
													<td>
														<s:select onchange="changeSelect(this)" name="labQueryVo.listLabParameter[${st.index}].showType" value="${showType}" theme="simple" list="#{'0':'文本框','1':'日期控件','2':'时间控件','3':'下拉框','4':'弹出层'}"></s:select>
														<s:if test="${showType=='3'}">
															<input type="hidden" name="labQueryVo.listLabParameter[${st.index}].comboxValue" value="<s:property value="comboxValue" escape="true"/> " id="comboxValue${st.index}" />
															<input type="hidden" name="labQueryVo.listLabParameter[${st.index}].combox" value="${combox}" id="combox${st.index}" />
															<a onclick="showValue('${st.index}')" style="cursor: hand;"><s:text name="show.value"/></a>
														</s:if>
														<s:if test="${showType=='4'}">
															<input type="hidden" name="labQueryVo.listLabParameter[${st.index}].dialogJson" id="dialogJson${st.index}" value="<s:property value="dialogJson" escape="true"/>" />
															<input type="hidden" id="diaLogColum${st.index}" name="labQueryVo.listLabParameter[${st.index}].diaLogColum" value="<s:property value="diaLogColum" escape="true"/>" />
															<input type="hidden" id="diaLogAction${st.index}" name="labQueryVo.listLabParameter[${st.index}].diaLogAction" value="${diaLogAction}" />
															<s:if test="${diaLogAction!=''}">
																<a onclick="showDiaLog('${st.index}')" style="cursor: hand;"><s:text name="show.value"/></a>
															</s:if>
														</s:if>
													</td>
													<td>
														<input type="hidden" value="${sort}" name="labQueryVo.listLabParameter[${st.index}].sort" />
														${sort}
													</td>
													<td>
														<input type="checkbox" name="labQueryVo.listLabParameter[${st.index}].isSort" <s:if test="${isSort}==1">checked="checked"</s:if> value="1" />
													</td>
												</tr>
											</s:iterator>
										</table>
									</div>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<label style="float: right; padding-right: 10px;" onclick="$(this).parent().next().toggle();">
											[&nbsp;
											<font color="blue"><s:text name="open.close"/></font>&nbsp;]
										</label>
										<span><s:text name="count.info"/></span>
										<span style="color: red;"><s:text name="user.query"/></span>
									</div>
									<table class="FormtableCon" style="display: none;">
										<tr>
											<td colspan="6" style="text-align: center;">
												<textarea rows="8" id="countSql" cols="120" name="labQueryVo.countSql">${labQueryVo.countSql}</textarea>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<label style="float: right; padding-right: 10px;" onclick="$(this).parent().next().toggle();">
											[&nbsp;
											<font color="blue"><s:text name="open.close"/></font>&nbsp;]
										</label>
										<span><a onclick="showCountParameter()" style="cursor: hand" title="点击获取显示列表"><s:text name="param.info.k"/></a> </span>
									</div>
									<div style="margin: 10px 50px 10px 50px; display: none;">
										<table border="0" class="myworkingboxttable" width="100%" id="countParameTable">
											<tr id="titileId">
												<th>
													<s:text name="perference"/>
												</th>
												<th>
													<s:text name="show.cn.name"/>
												</th>
											</tr>
											<s:set name="alllist1" value="labQueryVo.listLabParameterCountVo" />
											<s:iterator value="#alllist1" status="st">
												<tr>
													<td>
														<input type="hidden" value="1" name="labQueryVo.listLabParameterCountVo[${st.index}].selectSql" />
														<input type="hidden" value="${name}" name="labQueryVo.listLabParameterCountVo[${st.index}].name" />
														${name}
													</td>
													<td>
														<input type="text" value="${nameChin}" name="labQueryVo.listLabParameterCountVo[${st.index}].nameChin" />
													</td>
												</tr>
											</s:iterator>
										</table>
									</div>
								</div>
							</div>
						</form>
					</div>
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
