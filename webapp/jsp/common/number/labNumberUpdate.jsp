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

.TabTable {
	padding-top: 0;
}

.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>

		<script language=javascript> 
		  	function submitvalue(actionstr){
					$('form').attr('action','<%=basePath%>'+actionstr);
					$('form').submit();
			}
		    function openThis(){
		    	$("#defineTab").html('');
		    	var number=$("#part").val();
		    	if(number==0){
		    		validate.tip('请填写编号组成部分数',$("#part"),1)
		    		return false;
		    	}else{
		    		var html='';
		    		for(var i=0;i<$("#part").val();i++){
		    			html+='<tr>';
		    			html+='<td width="250">数据类型:</td>';
		    			html+='<td width="350"><select name="labNumberVo.listLabNumberParVo['+i+'].type" onchange="selectCheck(this)"><option value="0">字符</option><option value="1">日期</option><option value="2">数字</option></select></td>';
		    			html+='<td width="250"><input type="radio" onclick="selectInput(this)" value="0"   checked="checked" name="ss'+i+'">固定字符<input type="radio" onclick="selectInput(this)" value="1"  name="ss'+i+'"/>动态字符</td>';
		    			html+='<td><input type="text" name="labNumberVo.listLabNumberParVo['+i+'].expression" valType="required" /></td>';
		    			html+='</tr>';
		    		}
		    		$("#defineTab").html(html);
		    	}
		    }
		    function selectCheck(obj){
		    	obj=$(obj);
		    	var html='';
		    	var htmled='';
		    	var i=obj.closest('tr').index();
		    	if(obj.val()=='0'){
		    			html+='<input type="radio" onclick="selectInput(this)" value="0"   checked="checked" name="ss'+obj.closest('tr').index()+'">固定字符<input type="radio" onclick="selectInput(this)" value="1"  name="ss'+obj.closest('tr').index()+'"/>动态字符';
		    			htmled+='<input type="text" valType="required" name="labNumberVo.listLabNumberParVo['+i+'].expression" />';
		    		obj.parent("td").next("td").html(html);
	    			obj.parent("td").next("td").next("td").html(htmled);
		    	}else if(obj.val()=='1'){
		    		html='<label>日期表达式</label>';
		    		htmled='<input type="text" valType="required" name="labNumberVo.listLabNumberParVo['+i+'].expression" title="yyyyMMdd"/>';
		    		obj.parent("td").next("td").html(html);
    				obj.parent("td").next("td").next("td").html(htmled);
		    	}else if(obj.val()=='2'){
		    		html='<label>数字表达式</label>';
		    		htmled='<input type="text" valType="required" name="labNumberVo.listLabNumberParVo['+i+'].expression" titile="####0"/>';
		    		obj.parent("td").next("td").html(html);
    				obj.parent("td").next("td").next("td").html(htmled);
		    	}
		    }
		    function selectInput(obj){
		    	obj=$(obj);
		    	if(obj.attr('checked')=='checked'&&obj.val()=='0'){
		    		 var htmled='<input type="text" name="labNumberVo.listLabNumberParVo['+obj.closest('tr').index()+'].expression" valType="required"  />';
	    	 			obj.parent("td").next("td").html(htmled);
		    	}else{
		    		 obj.parent("td").next("td").html('');
		    	}
		    }
	</script>
	</head>
	<body>
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
					<div class="myworkingbox">
						<div class="myworkingboxttitle">
							<h2>
								${funName}：
								<span><s:text name="lab.code.modify"/></span>
							</h2>
						</div>
						<form action="" method="post" name="studentForm">
							<s:token></s:token>
							<input type="hidden" name="labNumberVo.id" value="${labNumberVo.id}" />
							<input type="hidden" name="labNumberVo.sort" value="${labNumberVo.sort}" />
							<div class="TabTable">
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
															<l:a uri="number/labNumber/updateLabNumber.action" img="/img/add.gif" value="lab.code.save" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span>${funName}<s:text name="information"/></span>
									</div>
									<table class="FormtableCon">
											<tr>
												<td><label><s:text name="config.name"/>：</label></td>
												<td><input type="text" name="labNumberVo.name" value="${labNumberVo.name}" id="name" valType="required" /></td>
												<td><label><s:text name="several.part"/>：</label></td>
												<td>
													<input type="text" name="labNumberVo.part" id="part" value="${labNumberVo.part}" valType="number" />
													<input type="hidden" name="labNumberVo.path" id="path" value="${labNumberVo.path}" />
												</td>
											</tr>
											<tr>
												<td><lable><s:text name="contor"/>：</lable></td>
												<td><input type="text" value="${labNumberVo.connector}" name="labNumberVo.connector"/> </td>
												<td><label><s:text name="reg.type"/>：</label></td>
												<td>
													<s:select list="#request.listLabCode" cssStyle="width:135px" theme="simple" value="'${labNumberVo.type}'" name="labNumberVo.type" listKey="code" listValue="name"></s:select>
												</td>
											</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="no.reg"/></span>
										<label id="xs" style="margin-left:  817px;color: #0088ee;cursor: pointer;" title="显示该信息" onclick="openThis();"><s:text name="refreshed"/></label>
									</div>
									<table class="FormtableCon" id="defineTab">
									<s:set value="labNumberVo.listLabNumberParVo" name="alllist"/>
									<s:iterator value="#alllist" status="st">
										<tr>
							    			<td width="100"><s:text name="data.type"/>:</td>
							    			<td width="350"><s:select name="labNumberVo.listLabNumberParVo[${st.index}].type" list="#{'0':getText('charstr'),'1':getText('msg.date'),'2':getText('numexpss')}"  onchange="selectCheck(this)" value="'${type}'" theme="simple"></s:select> </td>
							    			<s:if test="${type=='0'}">
							    				<td width="250"><input type="radio" onclick="selectInput(this)" value="0" <s:if test="${expression}!=''">  checked="checked"  </s:if>  name="ss${st.index}"><s:text name="fixed.char"></s:text><input type="radio" onclick="selectInput(this)" value="1" <s:if test="${expression==null}">  checked="checked"  </s:if>  name="ss${st.index}"/>动态字符</td>
							    			</s:if>
							    			<s:elseif test="${type=='1'}">
							    				<td width="250"><label><s:text name="date.exp"/>：</label></td>
							    			</s:elseif>
							    			<s:else>
							    				<td width="250"><label><s:text name="num.exp"/>：</label></td>
							    			</s:else>
							    			<td>
							    				<s:if test="${expression!=null}">
							    					<input type="text" name="labNumberVo.listLabNumberParVo[${st.index}].expression" valType="required" value="${expression}" />
							    				</s:if>
						    				</td>
							    		</tr>
						    		</s:iterator>
									</table>
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
		<div class="clear"></div>
	</body>
</html>
