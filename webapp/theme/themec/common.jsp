<%@ page language="java" import="java.util.*,cn.labsoft.labos.framework.common.sesseionutils.SessionContainer" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld" %>
<%@ taglib uri="http://java.fckeditor.net" prefix="FCK" %>
<%@ taglib prefix="c" uri="/WEB-INF/tld/jstl/c.tld" %>
<%@ taglib uri="/WEB-INF/tld/jstl/fn.tld" prefix="fn"%>
<%@ taglib uri="/WEB-INF/tld/jstl/fmt.tld" prefix="fmt"%>



<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
SessionContainer sessionContainer = (SessionContainer)session.getAttribute(SessionContainer.Session_Container);
%>

<script language="javascript">
<s:set name="msg" value="actionErrors"/>
<s:iterator value="#msg">
alert('${msg}');
</s:iterator>
</script>	

<script language="javascript">
<s:set name="msg" value="actionMessages"/>
<s:iterator value="#msg">
alert('${msg}');
</s:iterator>
</script>	


<script language="javascript">
 function ResumeError() {
    return true;
}
window.onerror = ResumeError;	
	
</script>	






