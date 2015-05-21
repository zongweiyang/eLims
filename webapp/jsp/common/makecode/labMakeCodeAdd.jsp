<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="<%=basePath%>jsp/common/makecode/css/demo.css" type="text/css">
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

.TabTable {
	padding-top: 0;
}

.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}

li {
	list-style: none;
	font-size: 12px;
}

li.title {
	list-style: none;
}

ul.list {
	margin-left: 17px;
}
	.myworkingboxttable th{
			background: #F5F5F5;
			color: #000000;
			border-top: #C0C0C0 1px solid;
			border-left: #C0C0C0 1px solid;
			border-right: #C0C0C0 1px solid;
		}
	.myworkingboxttable input {border:1px solid #66B3E9;vertical-align:middle;height:22px;line-height:22px;padding-left:2px;}	
</style>
	</head>
	<script language=javascript> 
			  	function submitvalue(actionstr){
					$('form').attr('action',actionstr);
					$('form').submit();
				}
		function addRow(){
			var index=$("#tableValue tr").length;
			var html="<tr><td><label>属性名：</label></td><td><input type=\"text\"  name=\"packagee.clazz.prop[INDEX].name\"/> </td>";
				html+="<td><label>中文名：</label></td><td><input type=\"text\" name=\"packagee.clazz.prop[INDEX].nameChinese\" /> </td>";
				html+="<td><label>是否列表显示：</label></td><td><input type=\"checkbox\" checked=\"checked\" value=\"1\" name=\"packagee.clazz.prop[INDEX].isShowOnList\"/> </td>";
				html+='<td><label>是否列表排序：</label></td><td><input type="checkbox" checked="checked" value="1" name="packagee.clazz.prop[INDEX].isSortList"/> </td>';
				html+="<td><label>接受域类型：</label></td><td><select name=\"packagee.clazz.prop[INDEX].showType\"><option value=\"1\" selected=\"selected\">文本框</option>";
				html+="<option value=\"2\">日期控件</option><option value=\"3\">时间控件</option><option value=\"4\">文本域</option></select></td>";
				html+='<td><label>验证类型：</label></td>';
				html+='<td><select name="packagee.clazz.prop[INDEX].validateName" >';
				html+='<option value="">---请选择---</option>';
				<s:iterator value="#request.YZLXList">
					html+='<option value="${code}">${name}</option>';
				</s:iterator>
				html+='</select></td>';
				html=html.split("INDEX").join(index);
				html+='<td><img style="margin-bottom: -5px" onclick="delRowThis(this)" src="<%=basePath%>utils/gmt/stop.png" /></td>';
				$("#tableValue").append(html);
				$("#columNumber").val(parseInt($("#columNumber").val())+1);
		}
		function delRow(){
			if($("#tableValue tr:last").index()==0){
				validate.tip('<s:property value="getText('opscorrentdetail')"/>',$("#columNumber"),1)
			}else{
				$("#tableValue tr:last").remove();
				$("#columNumber").val(parseInt($("#columNumber").val())-1);
			} 
		}
		function delRowThis(obj){
			obj=$(obj);
			$("#tableValue tr").eq(obj.closest('tr').index()).remove();
			$("#columNumber").val(parseInt($("#columNumber").val())-1);
		}
		function checkJspName(obj){
			obj=$(obj);
			if(obj.val().indexOf("/")>-1){
				$("#jspName").show();
			}else{
				$("#jspName").hide();
			}
		}
		function addBetchRow(obj){
			obj=$(obj);
			var tableRows='';
			if(!isNaN(obj.val())){ 
				var num=parseInt(obj.val());
				$("#tableValue").html('');
				  for(var  i=0;i<parseInt(obj.val());i++){
						var html="<tr><td><label>属性名：</label></td><td><input type=\"text\"  name=\"packagee.clazz.prop[INDEX].name\"/> </td>";
						html+="<td><label>中文名：</label></td><td><input type=\"text\" name=\"packagee.clazz.prop[INDEX].nameChinese\" /> </td>";
						html+="<td><label>是否列表显示：</label></td><td><input type=\"checkbox\" checked=\"checked\" value=\"1\" name=\"packagee.clazz.prop[INDEX].isShowOnList\"/> </td>";
						html+='<td><label>是否列表排序：</label></td><td><input type="checkbox" checked="checked" value="1" name="packagee.clazz.prop[INDEX].isSortList"/> </td>';
						html+="<td><label>接受域类型：</label></td><td><select name=\"packagee.clazz.prop[INDEX].showType\"><option value=\"1\" selected=\"selected\">文本框</option>";
						html+="<option value=\"2\">日期控件</option><option value=\"3\">时间控件</option><option value=\"4\">文本域</option></select></td>";
						html+='<td><label>验证类型：</label></td>';
						html+='<td><select name="packagee.clazz.prop[INDEX].validateName" >';
						html+='<option value="">---请选择---</option>';
						<s:iterator value="#request.YZLXList">
							html+='<option value="${code}">${name}</option>';
						</s:iterator>
						html+='</select></td>';
						html=html.split("INDEX").join(i);
						if(i!=0){
							html+='<td><img style="margin-bottom: -5px" onclick="delRowThis(this)" src="<%=basePath%>utils/gmt/stop.png" /></td>';
						}
						tableRows+=html;
					}
					$("#tableValue").append(tableRows);
			}else{
				obj.val(1);
		      validate.tip('<s:property value="getText('corprosnum')"/>',obj,1)	
		     }
		}
		function toClazzName(obj){
			obj=$(obj);
			if(obj.attr('en')=='en'||obj.attr('en')=='En'){
				var _cv = obj.val().replace(/[^A-Za-z0-9_]/g,'');//  /^[\u4e00-\u9fa5]+$/
				if(_cv != obj.val()){
					obj.val(_cv);
					  validate.tip('不能为中文',obj,1)	
				}
				
				var _v1 = obj.val().substring(0,1);
				var _v2 = obj.val().substring(1);
				obj.val(_v1.toUpperCase()+_v2);
			}
		}
		function checkList(obj){
			obj=$(obj);
			var stepHtml='';
			$("#flowId").val(obj.val());
			$.ajax({
				url:'${basePath}/makecode/labMakeCode/ajaxLabWfFunStep4Select.action',
				type:'POST',
				data:{'processDefVo.id':obj.val()},
				dataType:'json',
				success:function(date){
					var index=0;
				   $.each(date,function(a,b){
						stepHtml+='<tr><td>'+b.stepName+'</td><td class="c"><input type="text" name="packagee.stepNameList['+index+']" size="40"   /></td></tr>';   
						index=index+1;		
        			} ); 
        			$('.myworkingboxttable tr:gt(0)').remove();
        			$('.myworkingboxttable').append(stepHtml);
				},
				error:function(){
					alert('<s:property value="getText('network.error')"/>');
				}
			});
			
		}
		function checkFlow(obj){
			obj=$(obj);
			if(obj.val()=='Y'){
				$("#flowDiv").show();
			}else if(obj.val()=='N'){
				$("#flowDiv").hide();
			}
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
								${funName}
								<span><s:text name="admin.add"/></span>
							</h2>
						</div>
						<form action="" method="post" name="labMakeCodeForm">
							<div class="TabTable">
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="submitvalue('addLabMakeCode.action');return false;"><img height="20" width="20" src="<%=basePath%>img/filesave.gif" /><b><s:text name="lab.code.save"></s:text></b>
															</a>
														</td>
														<td>
															<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="location.href= 'listLabMakeCode.action';return false;"><img height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="msg.back"></s:text></b>
															</a>
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
													<s:text name="module.name"/>：
												</label>
											</td>
											<td>
												<input type="text" id="name" name="packagee.name" onchange="checkJspName(this)" valType="required" msg="请输入模块名" /><font color="red">&nbsp;&nbsp;<s:text name="ifed"/>：(common/number)</font>
											</td>
											<td>
												<label>
													<s:text name="module.cn.name"/>：
												</label>
											</td>
											<td>
												<input type="text" name="packagee.nameChinese" valType="required" msg="请输入模块中文名" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="class.name"/>：
												</label>
											</td>
											<td>
												<input type="text" en="en" name="packagee.clazz.clazzName" valType="required" msg="请输入类名" onblur="toClazzName(this)" />
											</td>
											<td>
												<label>
													<s:text name="class.cn.name"/>：
												</label>
											</td>
											<td>
												<input type="text" name="packagee.clazz.clazzCommon" valType="required" msg="请输入类中文名" />
											</td>
											
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="row.change"/>：
												</label>
											</td>
											<td>
												<input type="text" name="packagee.clazz.addColEnterNum" valType="number" />
											</td>
											<td>
												<label>
													<s:text name="page.file.name"/>
												</label>
											</td>
											<td>
												<input id="jspName" style="display: none;" type="text" name="packagee.clazz.jspName"  />
											</td>
										</tr>
											<tr>
												<td>
													<label>
														<s:text name="per.number"/>：
													</label>
												</td>
												<td>
													<input type="text" id="columNumber" value="1" valType="number" onchange="addBetchRow(this)"  />
												</td>
												<td>
													<label><s:text name="related.flow"/>：</label>
												</td>
												<td>
													<s:radio theme="simple" value="'N'" onclick="checkFlow(this);" name="packagee.isFlow" list="#{'Y':getText('lab.yes'),'N':getText('lab.no')}"  ></s:radio>
													<input type="hidden" id="flowId" name="packagee.flowId"  />
												</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="build.details"/></span>
										&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
										<img style="margin-bottom: -5px" onclick="addRow()" src="<%=basePath%>utils/gmt/maximize.png" />
										&nbsp;&nbsp;&nbsp;&nbsp;
										<img style="margin-bottom: -5px" onclick="delRow()" src="<%=basePath%>utils/gmt/stop.png" />
									</div>
									<table class="FormtableCon" id="tableValue">
										<tr>
											<td>
												<label>
													<s:text name="per.name"/>：
												</label>
											</td>
											<td>
												<input type="text" name="packagee.clazz.prop[0].name" valType="required" msg="请输入属性名" />
											</td>
											<td>
												<label>
													<s:text name="cn.name"/>：
												</label>
											</td>
											<td>
												<input type="text" name="packagee.clazz.prop[0].nameChinese"  />
											</td>
											<td>
												<label>
													<s:text name="list.show"/>：
												</label>
											</td>
											<td>
												<input type="checkbox" checked="checked" name="packagee.clazz.prop[0].isShowOnList" value="1" />
											</td>
											<td>
												<label>
													<s:text name="list.sort"/>：
												</label>
											</td>
											<td>
												<input type="checkbox" checked="checked" name="packagee.clazz.prop[0].isSortList" value="1" />
											</td>
											<td>
												<label>
													<s:text name="rev.type"/>：
												</label>
											</td>
											<td>
												<select name="packagee.clazz.prop[0].showType">
													<option value="1" selected="selected">
														<s:text name="text.type"/>
													</option>
													<option value="2">
														<s:text name="date.btn"/>
													</option>
													<option value="3">
														<s:text name="time.btn"/>
													</option>
													<option value="4">
														<s:text name="text.pan"/>
													</option>
												</select>
											</td>
											<td>
												<label>
													<s:text name="check.type"/>：
												</label>
											</td>
											<td>
												<s:select list="#request.YZLXList" headerKey="" headerValue="" name="packagee.clazz.prop[0].validateName" listKey="code" listValue="name" theme="simple"></s:select>
											</td>
										</tr>
									</table>
								</div>
								<div  id="flowDiv" class="Formtable" style="display: none;">
									<div class="Formtabletitle">
										<span><s:text name="flow.info.config"/></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td>
												<label><s:text name="flow.select"/>：</label>
											</td>
											<td colspan="3" style="text-align: left;">
												<s:select list="#request.processList" onchange="checkList(this)" headerKey="" headerValue="" listKey="id" theme="simple" listValue="name"></s:select>
											</td>		
										</tr>
									</table>
									<table class="myworkingboxttable" >
										<thead>
											<tr>
												<th>
													<s:text name="step.name"/>
												</th>
												<th>
													<s:text name="method.name"/>
												</th>
											</tr>
										</thead>
									</table>
								</div>

							</div>
						</form>
					</div>
				</td>
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
