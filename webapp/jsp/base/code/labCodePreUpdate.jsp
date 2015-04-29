<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
<html>
	<head>
		<%@ include file="../include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>


		<script language="JavaScript"
			src="<%=basePath%>js/portal/commonutil.js"></script>

		<!-- dwr -->
		<script type="text/javascript" src="<%=basePath%>utils/dwr/engine.js"></script>
		<script type="text/javascript" src="<%=basePath%>utils/dwr/util.js"></script>
		<script type="text/javascript"
			src="<%=basePath%>dwr/interface/ComCodeService.js"></script>
		<script type="text/javascript" src="<%=basePath%>/js/validate.js"></script>
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
</style>
	</head>
	<script language=javascript> 
	  function shadowdiv(id){
	     	var tem = eval(id); 
			  if(tem == 1){
			  	 document.getElementById('Tab01').className = "TabTableBOX01 b";
				 document.getElementById('li01').className = "currenttab";
				 document.getElementById('Tab02').className = "TabTableBOX02";
				 document.getElementById('li02').className = "";
				 document.getElementById('Tab03').className = "TabTableBOX03";
				 document.getElementById('li03').className = "";
			  }else if(tem == 2){
			  	 document.getElementById('Tab01').className = "TabTableBOX01";
				 document.getElementById('li01').className = "";
				 document.getElementById('Tab02').className = "TabTableBOX02 b";
				 document.getElementById('li02').className = "currenttab";
				 document.getElementById('Tab03').className = "TabTableBOX03";
				 document.getElementById('li03').className = "";
			  }else if(tem == 3){
			     document.getElementById('Tab01').className = "TabTableBOX01";
				 document.getElementById('li01').className = "";
				 document.getElementById('Tab02').className = "TabTableBOX02";
				 document.getElementById('li02').className = "";
				 document.getElementById('Tab03').className = "TabTableBOX03 b";
				 document.getElementById('li03').className = "currenttab";
			  }
	  }
   
 
		
		var checkFlag = true;
		function checkNameIsExist(checkStr,flag)
		{
			DWREngine.setAsync(false);
			ComCodeService.isExsitComCodeByCode(checkStr,flag,function getDate(retrueStr){
				if(retrueStr=="1"){
					if(flag=="0"){
						alert('<s:property value="getText('lab.common.repeatname')"/>');
						document.getElementById("name").focus();
						checkFlag = false;
						return false;
					}else{
						checkFlag = false;
						return true;
					}
					
				}else{
					checkFlag = true;
					if(document.getElementById("code").value!=document.getElementById("codex").value){
						checkCodeIsExist(document.getElementById("code").value,
								           document.getElementById("typeid").value,"1");
					}
					return true;
				}
				
			});
			DWREngine.setAsync(true);
					
		}
		function checkCodeIsExist(checkStr,typeid,flag)
		{
			DWREngine.setAsync(false);
			ComCodeService.isExsitComCodeByCode(checkStr,typeid,flag,function getDate(retrueStr){
				if(retrueStr=="1"){
					if(flag=="1"){
						alert('<s:property value="getText('lab.common.repeatname')"/>');
						document.getElementById("code").focus();
						checkFlag = false;
						return false;
					}else{
						checkFlag = false;
						return true;
					}
					
				}else{
					checkFlag = true;
					
					return true;
				}
				
			});
			DWREngine.setAsync(true);
					
		}			
		function submitvalueforlist(actionstr){
				var df = document.commcodeform;
				checkFlag = true;
				if(document.getElementById("name").value!=document.getElementById("namex").value){
					checkNameIsExist(document.getElementById("name").value,"0");
				}
				if(validateForm(df)){
					if(checkFlag){
					  df.action = actionstr;
					  df.submit();
				  	}
				}
		}	
			
</script>
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
								<s:text name="lab.common.code"/>：
								<span><s:text name="lab.code.modify"></s:text></span>
							</h2>
						</div>
						<form action="" method="post" name="commcodeform">
							<input type="hidden" name="comCodeVo.typeid" id="typeid"
								size="20" value="${comCodeVo.typeid }" />
							<input type="hidden" name="comCodeVo.id" id="id" size="20"
								value="${comCodeVo.id }" />
							<div class="FUNCIONBARNEW">
								<table>
									<tr>
										<td class="blockTd"
											style="padding: 6px 10px; vertical-align: center;">
											<table cellspacing="0" cellpadding="0" border="0">
												<tr>
													<td>
														<a id="BtnPreview" class="zPushBtn"
															href="javascript:void();"
															onclick="submitthiswithoutcheck('listComCode.action');return false;"><img
																height="20" width="20" src="<%=basePath%>img/fanhui.gif" /><b><s:text name="lab.back.list"></s:text></b>
														</a>
													</td>
													<td>
														<a id="BtnPreview" class="zPushBtn"
															href="javascript:void();"
															onclick="submitvalueforlist('updateComCode.action');return false;"><img
																height="20" width="20" src="<%=basePath%>img/chakan.gif" /><b><s:text name="lab.code.modify"></s:text></b>
														</a>
													</td>
												</tr>
											</table>
										</td>
									</tr>
								</table>
							</div>
							<table class="myworkingboxttable" cellspacing="0"
											cellpadding="0">
											<thead>
												<tr>

													<th>
													
														<s:text name="lab.commoncode.name"></s:text>
													
													</th>

													<th>
													
														<s:text name="lab.commoncode.id"></s:text>
														
													</th>
													
													<th>
														<s:text name="lab.remark.desc"></s:text>
													</th>
													<th>
														<s:text name="lab.sequence"></s:text>
													</th>
											</thead>
											<tbody>
												<tr>
													<td>
														
													<input name="comCodeVo.name" id="name" type="text"
														size="20" value="${comCodeVo.name }" />
													<input name="comCodeVo.namex" id="namex" type="hidden"
														size="20" value="${comCodeVo.name }" />
												
													</td>
													<td>
													<input name="comCodeVo.code" id="code" type="text"
														size="20" value="${comCodeVo.code }" />
													<input name="comCodeVo.codex" id="codex" type="hidden"
														size="20" value="${comCodeVo.code }" />
													</td>
													<td>
													<input name="comCodeVo.remark" id="remark" type=""
														size="20" value="${comCodeVo.remark}" />
													</td>
													<td>
													<input name="comCodeVo.sort" id="sort" type="text"
														size="20" value="${comCodeVo.sort}" />
													</td>
													<input name="typeid" id="sort" type="hidden"
														size="20" value="${comCodeVo.typeid}" />
												</tr>
												
											</tbody>
										</table>
								</td>
							</tr>
							<tr>
							<tr>
							</tr>
							</tr>
					
											
					</td>
					<td class="MRimg"></td>
				</tr>
				<tr>
					<td class="FLimg"></td>
					<td class="FMimg"></td>
					<td class="FRimg"></td>
				</tr>

			</table>
		<s:fielderror cssStyle="color: red"></s:fielderror>
		<div class="clear"></div>
	</body>
	<script language="javascript" type="text/javascript">
		function required ()  
		{
			 this.a = new Array("name", '<s:property value="getText('lab.inputcode.name')"/>',   new Function ("varName", " return this[varName];"));	
			 this.b = new Array("code", '<s:property value="getText('lab.inputcode.id')"/>',   new Function ("varName", " return this[varName];"));	
		}
		function maxlength(){
			
		}
		function DateTimeValidations(){
			
		}
		function email(){
		
		}
		function FloatValidations(){
			
		}
		function IntegerValidations(){
			
		}
		function DateValidations () {
		    
		}
	</script>
</html>
