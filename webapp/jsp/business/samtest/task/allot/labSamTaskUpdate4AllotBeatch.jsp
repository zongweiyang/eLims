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

.Formtable {
	border: none;
}

.FormtableCon input {
	width: 200px;
}

.FormtableCon textarea {
	width: 600px;
}

.FormtableCon select {
	width: 200px;
}

.myworkingboxttable th {
	background: #F5F5F5;
	color: #000000;
	border-top: #C0C0C0 1px solid;
	border-left: #C0C0C0 1px solid;
	border-right: #C0C0C0 1px solid;
}
</style>
	</head>
	<script language=javascript>
		var orgId=''; 
		var selectCheck='0';
		function checkAll(obj){
			obj=$(obj);
			$(".myworkingboxttable input[type=checkbox]").each(function(){
				if(obj.attr("checked")){
						$(this).attr("checked",true);
				}else{
					$(this).attr("checked",false);
				}
			
			});
		}
		function checkSampNum(sampNum,obj){
			obj=$(obj);
			if(parseInt(obj.val())>sampNum){
				obj.val("");
				validate.tip('<s:property value="getText('pleaseinputcornumber')"/>',obj);
			}else{
				$(".myworkingboxttable tr:gt("+obj.closest('tr').index()+")").find("input[type*=checkbox]").each(function(){
					if($(this).attr("checked")){
						var cIndex=$(this).closest('tr').find('input[name*=sampNum]').closest('td').index();
						var noSampNo=parseInt($(this).closest('tr').find('td').eq(cIndex-1).text());
						if(noSampNo-parseInt(obj.val())>=0){
							$(this).closest('tr').find('input[name*=sampNum]').val(obj.val());
						}
					}
				});
			}
		}
		function copy(name){
			var value="";
				$(".myworkingboxttable tr:gt(0)").find("input[type*=checkbox]").each(function(){
					if($(this).attr("checked")){
						if(value==""){
							if($(this).val()!='0'){
								value=$(this).closest('tr').find("select[name*="+name+"]").val();
							}
						}
						if(value!=""){
							$(this).closest('tr').find("select[name*="+name+"]").val(value);
							$(this).closest('tr').find("select[name*="+name+"]").parent('td').css('background-color','yellow');
						}
					}
				});
		}	
		function showTestInfo(){
			var key=true;
			var itemNames="";
			var itemSamNum="";
			var testMan="";
			var checkMan="";
			var taskIds="";
			$(".myworkingboxttable tr:gt(0)").each(function(){
				if($(this).find("input[type=checkbox]").attr("checked")){
					var rIndex=$(this).closest('tr').index()+1;
					itemNames+=$(this).find("input[type=checkbox]").val();
					itemNames+=",";
					if($(this).find('input[name*=sampNum]').val()==''){
							validate.tip('<s:property value="getText('pleaseinpuasdftnumber')"/>',$(this).find('input[name*=sampNum]'));
							key=false;
							return false;
					}
					itemSamNum+=$(this).find('input[name*=sampNum]').val();
					itemSamNum+=",";
					if($(this).find('select[name*=testManId]').val()=='0'){
						validate.tip('<s:property value="getText('inputcheknam')"/>',$(this).find('select[name*=testManId]'));
							key=false;
							return false;
					}
					testMan+=$(this).find('select[name*=testManId]').val();
					testMan+=",";
					taskIds+=$(this).find('input[name=taskId]').val();
					taskIds+=",";
						if($(this).find('select[name*=checkManId]').val()=='0'){
							validate.tip('<s:property value="getText('selecheckman')"/>',$(this).find('select[name*=checkManId]'));
							key=false;
							return false;
					}
					checkMan+=$(this).find('select[name*=checkManId]').val();
					checkMan+=",";
				}
			});
			if(itemNames.length>0)itemNames=encodeURI(encodeURI(itemNames));
			if(testMan.length>0)testMan=testMan.substr(0,testMan.length-1);
			testMan=encodeURI(encodeURI(testMan));
			if(checkMan.length>0)checkMan=checkMan.substr(0,checkMan.length-1);
			checkMan=encodeURI(encodeURI(checkMan));
			if(taskIds.length>0)taskIds=taskIds.substr(0,checkMan.length-1);
			if(key==true){
				$.dialog({
					id:'parentFunction',
					content:'url:'+'<%=basePath%>samtest/labSamTask/showlabSamTaskAllot4Select.action?labSamTestVo.itemName='+itemNames+'&labSamTestVo.demo='+itemSamNum+'&labSamTestVo.testManName='+testMan+'&labSamTestVo.checkManName='+checkMan+'&labSamTestVo.taskId='+taskIds,
					title:'<s:property value="getText('fenpeiinfo')"/>',
					opacity:0.4,
					width:800, 
					height:600,
					lock:true,
					max:false,
					min:false
			 	});
		 	}
		}
		function refresh(){
			location.reload();
		}
		function checkTd(obj){
				obj=$(obj);
				if(selectCheck=='0'&&orgId!=''){
					obj.find('select').find('option').each(function(){
							if($(this).val()==orgId){
								$(this).attr('selected','true');
								if(orgId!='0'){
										obj.css('background-color','yellow');
									}else{
										obj.css('background-color','');
									}	
							}
					});
				}
				selectCheck='0';
			}
		function clikSelect(){
			selectCheck='1';
		}
		function checkOrg(obj){
			obj=$(obj);
			if(obj.val()=='0'){
				orgId=obj.val();
				obj.parent().css('background-color','');
			}else{
				orgId=obj.val();
				obj.closest('td').css('background-color','yellow');
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
								${funName}：
								<span><s:text name="lab.code.modify"/></span>
							</h2>
						</div>
						<form theme="simple" action="" method="post" name="labSamTestAllot" id="labSamTestAllot">
							<s:token></s:token>
							<input type="hidden" id="taskId" value="${labSamTestVo.taskId}" name="labSamTestVo.taskId"/>
							<div class="TabTable">
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
															<l:a uri="samtest/labSamTask/updateLabSamTask4Allot.action" onclick="showTestInfo();"  value="post.commit" img="/img/icon/filesave.gif"/>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
								<div class="Formtabletitle">
									<span><s:text name="item.info"/></span>
										<label style="float: right; margin-right: 20px;" onclick="javascript:$(this).parent().next().toggle();">
											<font color="red">[<s:text name="open.close"/></font>]
										</label>
								</div>
									<table class="myworkingboxttable" id="orgTable" >
										<thead>
											<tr>
												<th width="30">
													<img src="<%=basePath%>img/icon_drag.gif" />
												</th>
												<th><s:text name="task.no"/></th>
												<th><input type="checkbox" onclick="checkAll(this)"/></th>
												<th>
													<s:text name="checking.item"/>
												</th>
												<th>
													<s:text name="sam.number"/>
												</th>
												<th>
													<s:text name="assign.no"/>
												</th>
												<th>
													<s:text name="assign.info"/>
												</th>
												<th>
													<s:text name="checker"/>
													<span style="margin-left: 10px;" > <img src="/img/lefttorightselect/arrow_dwn.gif" onclick="copy('testManId')"/></span>
												</th>
												<th>
													<s:text name="tester"/>
													<span style="margin-left: 10px;" > <img src="/img/lefttorightselect/arrow_dwn.gif" onclick="copy('checkManId')"/></span>
												</th>
											</tr>
										</thead>
										<s:iterator value="#request.listLabSamTestVo" status="st" id="vo">
												<tr>
													<td rowspan="${spanRow}">
														${st.index+1}
													</td>
													<td rowspan="${spanRow}" class="c" width="170px;">
														${taskNo}
													</td>
													<s:iterator value="#vo.listLabSamTest">
														<td class="c">
															<input type="checkbox" name="listLabSamTestVo[${st.index}].itemId" value="${itemName}+'|'+${itemId}" />
														</td>
														<td>
															${itemName}
														</td>
														<td width="60px;">
															${sampNum}
														</td>
														<td width="60px;">
															${sampNum-sampNumTask}
														</td>
														<td class="c">
															<input type="text" style="width: 45px;" name="listLabSamTestVo[${st.index}].sampNum" value="${sampNum-sampNumTask}" onblur="checkSampNum('${sampNum-sampNumTask}',this)" />
															<font color="red" >*(<s:text name="assign.number"></s:text>)</font>
														</td>
														<td style="text-align: center;" onclick="checkTd(this)">
															<input type="hidden" id="labSamTaskId" name="taskId" value="${taskId}"  />
															<s:select theme="simple" headerKey="0"  onclick="clikSelect()" onchange="checkOrg(this)" headerValue="" name="listLabSamTestVo[${st.index}].testManId" list="#request.listLabUserTest" listKey="id+'|'+name" listValue="name"></s:select>
														</td>
														<td style="text-align: center;" onclick="checkTd(this)">
															<s:select theme="simple" headerKey="0" headerValue=""  onclick="clikSelect()" onchange="checkOrg(this)" name="listLabSamTestVo[${st.index}].checkManId" list="#request.listLabUserCheck" listKey="id+'|'+name" listValue="name"></s:select>
														</td>
														</tr>
													</s:iterator>
											</s:iterator>
									</table>
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
		<div class="clear"></div>
	</body>
	<script language=javascript>
		$('.myworkingboxttable input[type=checkbox]').each(function(){
			$(this).attr('checked',true);
		});
	</script>
</html>
