<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
	<%@ include file="/jsp/include/common.jsp"%>
    <title>控件</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<style>
	
	</style>
	<script>
		var config = {
			/**
			 Array
			 自定义的树
			 默认值: [];
			 数组内为对象(可多个)
			 {
			 	title:树标题
			 	iurl:初始化树的时候执行的地址
				curl:单击树节点的时候执行的地址
				name:单击往后台传的参数名,多个用空格分开
				value:单击往后台传的参数值,多个用空格分开，要传树结点的名称和Id如下:name id
			 }
			*/
			itmes: [{
				title:"树标题1",
			 	iurl:"<%=basePath%>common/component/ajaxLabOrgTree.action",
				curl:"<%=basePath%>common/component/ajaxListLabUser4Org.action",
				name:"labOrgVo.id",
				value:"id"
			}],
			
			/**
			 Array | Boolean
			 默认值: true;
			 如果是true,则显示默认的树;
			 如果是false,则不显示默认的树;
			 如果是数组[1,3],则第一个和第三个显示
			*/
			display: true,
			/**
			 设置回调函数
			*/
			callback:callback,
			/**
			 初始化已经选择的用户
			 init为查询已经选择的用户的url
			*/
			init:""
		};
		var userWin = null;
		$(function(){
			$("#shared").click(function(){
				if (userWin != null) {
					userWin.show();
				} else {
					userWin = $.dialog({
						id:'id',
						content:'url:'+'<%=basePath %>utils/chooseuser/showLabUser4Select.jsp',
						title:'选人',
						opacity:0.4,
						width:840,
						lock:true,
						max:false,
						min:false,
						close:function(){
							this.hide();
							return false;
						}
					 });
				}
			});
			shared.click();
			$.dialog.data("config",config);
		});
		function callback(data) {
			/**
			 data对象里有
			 json: 所有的用户信息json数组 
			 ids: 所有的用户id,用","分开的
			 names: 所有的用户name,用","分开的
			 */
			$("#shared").val(data.names);
			$("#userIds").val(data.ids);
		}
	</script>
  </head>
	<body>
		<input type="text" id="shared">
		<input type="hidden" id="userIds" name=""/>
	</body>
</html>
