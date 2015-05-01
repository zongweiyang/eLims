<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld" %>
<html>
<head>
<title><s:text name="page.info"/></title>
</head>
<body> 
      <%
      	String param=request.getParameter("actionparam");
      	String formName = request.getParameter("formName");
      %>
      <input type="hidden" name="formName" id="formName" value="<%=formName %>"/>
      <input type="hidden" name="currentPage" id="currentPage" value="${pageResult.pageBean.currentPage }"/>
      <input type="hidden" name="pagerMethod" id="pagerMethod"/>
      <table border="0" cellPadding="2" align="center" cellSpacing="0" width="100%" bgcolor="#FFFFFF">           
             <tr>
					 <td align="center"  class="tou"  colspan="5">											
							  <div class="pagecontrol">
                				  
							  <s:if test="pageResult!=null&&pageResult.resultList!=null&&pageResult.pageBean.totalRows!=0">
							  					        
						                  <!-- 第<FONT color="red">
						                  <s:property value="pageResult.pageBean.currentPage"/></FONT>&nbsp;页/
						                  共&nbsp;<FONT color="red"><s:property value="pageResult.pageBean.getTotalPages()"/></FONT>&nbsp;页&nbsp;						                  
						                 记录总数:&nbsp;<FONT color="red"><s:property value="pageResult.pageBean.totalRows"/></FONT>&nbsp;条&nbsp;
					           (每页<s:property value="pageResult.pageBean.pageSize"/>条)&nbsp;&nbsp;
					                --> <s:if test="pageResult.pageBean.currentPage==1">
					                                                 <s:text name="top.index"/> &nbsp;上一页



					                </s:if> 
					                <s:else>
					                  <a href="javascript:void('jumpPage');" onclick="javascript:doAction('<%=param %>','first');return false;"><s:text name="page.first"/></a>
						    		   <a href="javascript:void('jumpPage');" onclick="javascript:doAction('<%=param %>','previous');return false;"><s:text name="page.before"/></a>
					                </s:else>
					    			<s:if test="pageResult.pageBean.currentPage==pageResult.pageBean.totalPages">
					               		   下一页 &nbsp;尾页
					                </s:if> 
					                <s:else>
					                   <a href="javascript:void('jumpPage');" onclick="javascript:doAction('<%=param %>','next');return false;"><s:text name="page.next"/></a>
						    			<a href="javascript:void('jumpPage');" onclick="javascript:doAction('<%=param %>','last');return false;"><s:text name="page.tail"/></a>			                   
					                </s:else>
					            
							  </s:if>
							 
                </div>
		       </td>
		</tr>
               
</table>  
          

</body>
</html>
<script>
	function doAction(url,method){
	   var myFormName = document.getElementById("formName").value;
	   document.getElementById("pagerMethod").value=method;
	   document.forms[myFormName].action = url;
	   document.forms[myFormName].submit();
	}
</script>