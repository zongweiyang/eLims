<%@ page language="java" import="java.util.*,cn.labsoft.labos.framework.common.sesseionutils.*" pageEncoding="UTF-8"%>
<%@page import="org.apache.struts2.ServletActionContext"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	SessionContainer son=(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
	
	String busId = request.getParameter("busId");
	String busType = request.getParameter("busType");
	String fileTypes = request.getParameter("fileTypes");
	String num= request.getParameter("num");
	String userId= son.getUserId();
%>
<script type="text/javascript">
	var basePath='<%=basePath %>';
</script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title><s:text name="fileuploadcon"/>GooUploader</title>
<link rel="stylesheet" type="text/css" href="<%=basePath %>codebase/GooUploader.css"/>
<script  type="text/javascript" src="<%=basePath %>codebase/jquery-1.3.2.min.js"></script>
<script  type="text/javascript" src="<%=basePath %>codebase/GooUploader.js"></script>
<script type="text/javascript" src="<%=basePath %>codebase/swfupload/swfupload.js"></script>
<script type="text/javascript">
var update;
var busId='<%=busId%>';
var busType='<%=busType%>';
var fileTypes='<%=fileTypes%>';
var userId='<%=userId%>';
var num='<%=num%>';
var  post_params = {'busId':busId,'busType':busType,'userId':userId};
var property={
	width:300,
	height:300,
	multiple:true,
	file_types:fileTypes,
    post_params:post_params,
    btn_add_text:'<s:property value="getText('added')"/>',
    btn_up_text:'<s:property value="getText('uploaded')"/>',
    btn_cancel_text:'<s:property value="getText('giveup')"/>',
    btn_clean_text:'<s:property value="getText('clearup')"/>',
    op_del_text:'<s:property value="getText('oneitemdelete')"/>',
    op_up_text:'<s:property value="getText('oneitemupload')"/>',
    op_fail_text:'<s:property value="getText('uploadfailed')"/>',
    op_ok_text:'<s:property value="getText('upload.success')"/>',
    op_no_text:'<s:property value="getText('cancelupload')"/>',
	upload_url:"<%=basePath %>/LabUploadServlet?busId="+busId+"&busType="+busType+"&userId="+userId
};
$(document).ready(function(){
  update=$.createGooUploader($("#update"),property)
});
var api=frameElement.api, W=api.opener, D=W.document;
function callbackFunction(){
	api.close();
}
/*
	上传成功后动态添加上传文件
*/
function callBackAddFileObject(data) {
	if(num!='1'){
		$('#upfiles',D).append(data);
	}else{
		$('#upfiles1',D).append(data);
	}
}
</script>
</head>

<body>
<div id="update"></div>
</body>
</html>