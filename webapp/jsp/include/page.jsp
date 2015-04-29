<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<html>
	<head>
		<title><s:text name="page.info"></s:text></title>
	</head>
	<body>
		<input type="hidden" id="formName" value="<%=request.getParameter("formName")%>"/>
		<input type="hidden" name="pageResult.currentPage" id="currentPage" value="${pageResult.pageBean.currentPage }" />
		<input type="hidden" name="pageResult.pagerMethod" id="pagerMethod" />
		<input type="hidden" name="pageResult.order" id="order" value="${pageResult.order}"/>
		<input type="hidden" name="pageResult.orderColumn" id="orderColumn" value="${pageResult.orderColumn}"/>
		<input type="hidden" id="param" value="${pageResult.action}"/>
		<table border="0" cellPadding="2" align="center" cellSpacing="0" width="100%" bgcolor="#FFFFFF">
			<tr>
				<td align="center" class="tou" colspan="5">
					<div class="pagecontrol">
						<s:if test="pageResult!=null&&pageResult.resultList!=null&&pageResult.pageBean.totalRows!=0">
				            <s:text name="page.current"/><FONT color="red"> <s:property value="pageResult.pageBean.currentPage" /> </FONT>&nbsp;<s:text name="page.page"/>/
				                  <s:text name="page.total"/>&nbsp;<FONT color="red"><s:property value="pageResult.pageBean.getTotalPages()" /> </FONT>&nbsp;<s:text name="page.page"/>&nbsp;						                  
				                  <s:text name="page.all.records"/>:&nbsp;<FONT color="red"><s:property value="pageResult.pageBean.totalRows" /> </FONT>&nbsp;<s:text name="page.record"/>&nbsp;
				           		(<s:text name="page.ever"/>
				           			<s:select onchange="changePageSize();" theme="simple" name="pageResult.pageSize" list="#{'10':'10','30':'30','50':'50','100':'100','0':getText('page.all')}"></s:select>
				           		<s:text name="page.record"/>)&nbsp;&nbsp;
			                <s:if test="pageResult.pageBean.currentPage==1">
								<s:text name="page.first"/> &nbsp;<s:text name="page.before"/>
			                </s:if>
							<s:else>
								<a href="javascript:;" onclick="javascript:doAction('first');return false;"><s:text name="page.first"/></a>
								<a href="javascript:;" onclick="javascript:doAction('previous');return false;"><s:text name="page.before"/></a>
							</s:else>
							<s:if test="pageResult.pageBean.currentPage==pageResult.pageBean.totalPages">
					            <s:text name="page.next"/>&nbsp;<s:text name="page.tail"/>
					        </s:if>
							<s:else>
								<a href="javascript:;" onclick="javascript:doAction('next');return false;"><s:text name="page.next"/></a>
								<a href="javascript:;" onclick="javascript:doAction('last');return false;"><s:text name="page.tail"/></a>
							</s:else>
								<input type="" size="1" id="jumpPage" value="" />
								<a href="javascript:;" onclick="javascript:doJumpPage();return false;"><s:text name="page.confirm"/></a>
							</s:if>
						<s:else>
					        <s:text name="page.total"/>0<s:text name="page.row"/>&nbsp;   <s:text name="page.current"/>0<s:text name="page.page"/>|&nbsp;<s:text name="page.total"/>0<s:text name="page.page"/>&nbsp; <s:text name="page.first"/> &nbsp;<s:text name="page.before"/> &nbsp; <s:text name="page.next"/>&nbsp;<s:text name="page.tail"/>							  
						</s:else>
					</div>
				</td>
			</tr>
		</table>
	</body>
</html>
<script>
	function submitAction(){
	   var myFormName = document.getElementById("formName").value;	
	   //var action = '${SessionContainer.lastUrl}';
	   var action = '${pageResult.action}';
	   <% String action = request.getParameter("actionparam");
	     	if(null!=action&&action.length()>0){
	   %>
	   		action = '<%=action%>';
	   <%}%>
	   document.forms[myFormName].action = action;
	   document.forms[myFormName].submit();
	}

	function doAction(method){
	   document.getElementById("pagerMethod").value=method;
	   submitAction();
	}
	function doJumpPage(){
		var jumpPage=document.getElementById("jumpPage").value;
		if(isNaN(jumpPage)){
			document.getElementById("jumpPage").focus();
			document.getElementById("jumpPage").select();
			alert('<s:property value="getText('correct.page.no')"/>');
			return false;
		}
		document.getElementById("currentPage").value=jumpPage;
		submitAction();
	}
	function changePageSize(){
		submitAction();
	}
$(function(){
	var _orderAttr = {
		attrName : null,
		defOrder : "ASC"
	};
	var orderColumn = $("#orderColumn");
	var orderType = $("#order");
	var orderThs = $("th[property]");
	orderThs.each(function(){
		var span = $("<span>").addClass("orderIcon");
		var spanAttrName = $(this).attr("property");
		if(spanAttrName == '${pageResult.orderColumn}'){
			var defOrder = '${pageResult.order}';
			if(defOrder=="ASC"){
				span.append('<img src="${basePath}img/px_u.gif" />');
			}else if(defOrder=="DESC"){
				span.append('<img src="${basePath}img/px_d.gif" />');
			}
		}
		$(this).append(span);
		$(this).bind("click",function(){
			orderType.val(defOrder=="ASC"?"DESC":"ASC");
			orderColumn.val(spanAttrName);
			var formName = $("#formName").val();
			submitAction();
		});
	});
	 $("#pageSelectId").append("<option value='${pageResult.pageBean.totalRows}'>"+'<s:property value="getText('page.all')"/>'+"</option>");
	 if(${pageResult.pageSize==pageResult.pageBean.totalRows}){
	 	$("#pageSelectId option[value='${pageResult.pageBean.totalRows}']").attr("selected", true); 
	 } 
})
</script>