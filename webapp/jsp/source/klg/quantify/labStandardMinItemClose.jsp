<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp" %>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script type='text/javascript' src='<%=basePath%>/js/autocomplete/jquery.autocomplete.min.js'></script>
		<link rel="stylesheet" type="text/css" href="<%=basePath%>/js/autocomplete/jquery.autocomplete.css" />
		<style>
			.bodytable .oRight {
				padding-left: 0;
			}
			html {
				_overflow-x: hidden;
			}
		</style>
		<script>
			var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
			function closeMe(){
			  	api.close();
		 	}
			$(function(){
				closeMe();
			});
			
		</script>
	</head>
	<body>
		<input type="hidden" id="currentPage" name="currentPages" value="${request.currentPages}" />
		<input type="hidden" id="pageSize" name="pageSizes" value="${request.pageSizes}" />
		<input type="hidden" id="pagerMethod" name="pagerMethods" value="${request.pagerMethods}" />
		<input type="hidden" id="labSamTypeId" name="labStandardItemVo.labSamTypeId" value="${labStandardItemVo.labSamTypeId}" />
		<input type="hidden" id="name" name="labItemVo.name" value="${labItemVo.name}" />
	</body>
</html>		
