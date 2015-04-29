<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
  <head>
	<%@ include file="/jsp/include/jquery.jsp"%>
    <title>控件</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" href="${basePath}utils/ztree/zTreeStyle.css" type="text/css">
	<link rel=stylesheet href="${basePath}style/global.css" type="text/css">
	<script type="text/javascript" src="${basePath}utils/ztree/jquery.ztree.core-3.5.js"></script>
	<style>
		* {
			padding: 0;
			margin: 0;
		}
		html,body{
			width:100%;height:100%;
		}
		.clear:after {
			display: table;
			content: "";
			line-height: 0;
			clear: both;
		}
		.footer {
			text-align: center;
			background-color: #eee;
			border-top: 1px solid #aaa;
			padding:10px 0px;
			width:100%;
		}
		.panel_1, .panel_2, .panel_3{
			background-color: white;
			float: left;
			overflow:auto;
		}
		.container {
			background-color: #ccc;
		}
		.panel_1 {
			width: 33%;
		}
		.panel_2 {
			width: 33%;
			margin: 0 1px;
		}
		.panel_3 {
		
		}
		.fold_title{
			height: 15px;
			padding: 5px;
			border-bottom:1px solid #ccc;
			background-color: rgb(235,235,235);
		}
		.gridPanel th {
			height: 15px;
			padding: 5px;
			background-color: rgb(235,235,235);
		}
		.gridPanel td {
			height: 15px;
			padding: 5px;
			text-align:center;
		}
		.gridPanel tr:hover {
			cursor: pointer;
			background-color: #eee;
		}
		.fold_title:hover {
			cursor: pointer;
			background-color: #ccc;
		}
		.fold_body {
			height:361px;
		}
		.gridPanel {
			width: 100%;
			border-collapse:collapse;
		}
		.gridPanel th, .gridPanel td {
			border-bottom: 1px solid #ccc;
		}
		.new_btn {
			display: inline-block;
			width: 60px;
			height: 30px;
			line-height: 30px;
			border: 1px solid #ccc;
			border-radius: 5px;
			background-color: white;
		}
		.new_btn:hover{
			background-color: #eee;
		}
		.new_btn:active{
			background-color: #ccc;
		}
		.tree {
			width: 90%;
			margin: 0 auto;
			text-align: left;
		}
		.tree li {
			padding: 5px;
		}
	</style>
	<script><!--
		/**********[start]给js Array对象添加方法**********/
		Array.prototype.indexOf = function(val) {            
			for (var i = 0; i < this.length; i++) {
				if (this[i] == val) return i;
			}
			return -1;
		};
		Array.prototype.remove = function(val) {
			var index = this.indexOf(val);
			if (index > -1) {
				this.splice(index, 1);
			}
		};
		/**********[end]给js Array对象添加方法**********/
		
		//api.opener 为载加lhgdialog.min.js文件的页面的window对象
		var api = frameElement.api, W = api.opener, D = W.document; 
		var config = W.$.dialog.data("config");
		//选中的用户id
		var userIds = [];
		//树配置	
		var setting = {
			async: {
				enable: true,
				url:"",
				autoParam:["id"],
			},
			data: {
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: 0,
				}
			},
			callback: {
				onClick: ""
			}
		};
		/**
			单击树节点的事件
			event:事件对象
			treeId:树id
			treeNode:树节点
			clickFlag:单击标识
			url:请求的地址
			obj:传的参数	
			url: ""
		*/	
		function treeClick(url, names, values) {
			return function(event, treeId, treeNode, clickFlag){
				var data = {};
				if (typeof names == "string") {
					names = names.split(" ");
					values = values.split(" ");
				}
				if (names instanceof Array) {
					var len = names.length,
					    i = 0;
					while (i < len) {
						data[names[i]] = values[i]=="id" ? treeNode.id : ( values[i]=="name" ? treeNode.name : values[i]);
						i++;
					}
				}
				$.ajax({
					url: url,
					type: "post",
					dataType: "json",
					data: data,
					success: function(result){
						addPreUser(result);
					}
				});
			}
		}
		//添加预选用户
		function addPreUser(jsonArray){
			var tbody = $("#preUser tbody").empty(),
			    tr = $("<tr>"),
			    td = $("<td>"),
			    checkBox = $("<input>",{
			    	"type": "checkbox",
			    	"name": "",
			    	"data-key": "preCheckAll"
			    }),
		    	i = 0, 
				len = jsonArray.length;
			if (jsonArray instanceof Array && len > 0) {
				do {
					var item = jsonArray[i],
						tr1 = tr.clone().attr({"data-id":item.id,"data-name":item.name}),
						td1 = td.clone(),
						td2 = td.clone(),
						td3 = td.clone(),
						checkBox1 = checkBox.clone().val(item.id),
						td2_str = item.name,
						td3_str = [],
						item_array = item.orgVoList,
						j = 0, 
						len1 = item_array.length;
						if (userIds.indexOf(item.id) != -1) {
							checkBox1.attr("checked",true);
						}
					if (item_array instanceof Array && len1 > 0) { 
					    do {
							td3_str.push($.trim(item_array[j].name));
							j++;
					    } while(j < len1);
					}
					td1.html(checkBox1);
					td2.html(td2_str);
					td3.html(td3_str.join());
					tr1.append(td1)
					    .append(td2)				
					    .append(td3);
					//tr1.hide().appendTo(tbody).show(200);
					tbody.append(tr1);
					i++;
				}while(i < len);
			}
		}	
		//添加用户
		function addUser(tr) {
			tr = $(tr);
			var id = tr.find("input:checkbox").val();
			if ($.inArray(id,userIds)!= -1) {
				return;
			}
			tr.find("input:checkbox").attr("data-key","selectCheckAll");
			$("#selectUsre tbody").append(tr);
			//tr.hide().appendTo($("#selectUsre tbody")).show(200);
			userIds.push(id);
		}
		//初始化已选择用户
		function initUser(){
			$.ajax({
				url: config.init,
				data: {},
				dataType: "json",
				type: "post",
				success: function(result) {
					var jsonArray = result;
					var tr = $("<tr>"),
					    td = $("<td>"),
					    checkBox = $("<input>",{
					    	"type": "checkbox",
					    	"name": "",
					    	"data-key": "preCheckAll"
					    }),
				    	i = 0, 
						len = jsonArray.length;
					if (jsonArray instanceof Array && len > 0) {
						do {
							var item = jsonArray[i],
								tr1 = tr.clone().attr({"data-id":item.id,"data-name":item.name}),
								td1 = td.clone(),
								td2 = td.clone(),
								td3 = td.clone(),
								checkBox1 = checkBox.clone().val(item.id),
								td2_str = item.name,
								td3_str = [],
								item_array = item.orgVoList,
								j = 0, 
								len1 = item_array.length;
								if (userIds.indexOf(item.id) != -1) {
									checkBox1.attr("checked",true);
								}
							if (item_array instanceof Array && len1 > 0) { 
							    do {
									td3_str.push($.trim(item_array[j].name));
									j++;
							    } while(j < len1);
							}
							td1.html(checkBox1);
							td2.html(td2_str);
							td3.html(td3_str.join());
							tr1.append(td1)
							    .append(td2)				
							    .append(td3);
							addUser(tr1);
							i++;
						}while(i < len);
					}
				}
			});
		}
		//改变窗口的时候树高度
		function resizeHeight(){
			var bodyHeight = $("body").height();
			var footHeight = $("#footer").outerHeight();
			$(".container,.panel_1,.panel_2,.panel_3").height(bodyHeight-footHeight);
			var foldTitleHeight = $(".fold_title").eq(0).outerHeight();
			var foldTitleSize = $(".fold_title").size();
			$(".fold_body").height($(".container").height()-foldTitleHeight*foldTitleSize);
			var panel1Width = $(".panel_1").outerWidth();
			var panel2Width = $(".panel_2").outerWidth();
			var containerWidth = $(".container").outerWidth();
			$(".panel_3").width(containerWidth-panel1Width-panel2Width-4);
		}
		//将选择的用户信息转成json
		function getChooseUser(){
			var tr = $("#selectUsre tbody tr");
			var jsonStr = [];
			var ids = "";
			var names = "";
			for (var i = 0, len = tr.length; i < len; i++) {
				var obj = new Object;
				obj.id =  tr.eq(i).attr("data-id");
				obj.name =  tr.eq(i).attr("data-name");
				jsonStr[i] = obj;
				ids += tr.eq(i).attr("data-id") + ",";
				names += tr.eq(i).attr("data-name") + ",";
			}
			names = names.substr(0,names.length-1);
			ids = ids.substr(0,ids.length-1);
			var data = new Object;
			data.json = jsonStr;
			data.ids = ids;
			data.names = names;
			return data;
		}
		//初始化配置
		function init(){
			var itmes = config.itmes;
			var display = config.display;
			if (display === false) {
				$(".panel_1").empty();
			} else if (display.length > 0) {
				$(".fold").hide();
				$.each(display,function(i,n){
					$(".fold").eq(this-1).show();
				});
				$(".fold:hidden").remove();
			} 
			$.each(itmes,function(i,n){
				var ulAttr = {
					"id": "ztree-"+i,
					"data-iurl" : this.iurl,
				    "data-curl" : this.curl,
				    "data-name" : this.name,
				    "data-value" : this.value
				};
				var div = $("<div>")
					.addClass("fold")
					.append(
						$("<div>")
						.addClass("fold_title")
						.html(itmes[i].title||"标题")
					)
					.append(
						$("<div>")
						.addClass("fold_body")
						.css({"display":"none"})
						.append(
							$("<ul>",ulAttr)
							.addClass("ztree")
						)
					)
				$(".panel_1").append(div);
			});
			//初始化树节结
			$(".ztree").each(function(){
				var than = $(this);
				//设置初始化url
				setting.async.url = than.attr("data-iurl");
				//设置单击事件
				setting.callback.onClick = treeClick(than.attr("data-curl"),
					than.attr("data-name"),
					than.attr("data-value"));
				//初始化树结构
				$.fn.zTree.init(than, setting);
			});
			$(".fold_body").eq(0).show();
			
		}
		$(function(){
			//折叠效果
			$(".panel_1").on("click",".fold_title",function(){
				var curBody = $(this).next();
				$(".fold_title").next().not(curBody).hide();
				curBody.toggle();
			});
			//清空选择的用户
			$("#clear").click(function(){
				$("#selectUsre tbody tr").remove();
				userIds = [];
			});
			//关闭窗口
			$("#close").click(function(){
				api.close();
			});
			//删除选中的用户
			$("#remove").click(function(){
				$("input[data-key=selectCheckAll]:checked").each(function(){
					var than = $(this);
					userIds.remove(than.val());
					than.parents("tr").remove();
				});
			});
			//返回结果
			$("#confirm").click(function(){
				if (typeof config.callback == "function") {
					var data = getChooseUser();
					config.callback(data);
				}
				$("#close").click();
			});
			//全选或全不选
			$(".checkAll").click(function(){
				var check = this.checked;
				var checkbox = $("input[data-key="+this.id+"]:checkbox").attr("checked",check);
				if (this.id == "preCheckAll") {
					if (check) {
						var tr = checkbox.parents("tr").clone();
						for(var i = 0,len = tr.length; i < len; i++) {
							addUser(tr[i]);
						}
					} else {
						for(var i = 0,len = checkbox.length; i < len; i++) {
							var id = checkbox.eq(i).val();
							$("#selectUsre tbody tr[data-id="+id+"]").remove();
							userIds.remove(id);
						}
					}
				} 
			});
			$("#preUser tbody").on("click","input:checkbox",function(){
				var check = this.checked;
				if (check) {
					var tr = $(this).parents("tr").clone();
					addUser(tr);
				} else {
					var id = $(this).val();
					$("#selectUsre tbody tr[data-id="+id+"]").remove();
					userIds.remove(id);
				}
			});
			init();
			initUser();
			$(window).resize(resizeHeight);
		});
	--></script>
  </head>
	<body>
			<div>
				<div class="container clear">
					<div class="panel_1">
						<div class="fold">
							<div class="fold_title">按部门</div>
							<div class="fold_body" style="display: none;">
								<!-- data-iurl:初始化树的时候执行的地址 -->
								<!-- data-curl:单击树节点的时候执行的地址 -->
								<!-- data-name:单击往后台传的参数名,多个用空格分开-->
								<!-- data-value:单击往后台传的参数值,多个用空格分开，要传树结点的名称和Id如下:name id-->
								<ul id="zTreeOrg" class="ztree" 
									data-iurl="<%=basePath%>common/component/ajaxLabOrgTree.action"
									data-curl="<%=basePath%>common/component/ajaxListLabUser4Org.action"
									data-name="labOrgVo.id" data-value="id"></ul>
							</div>
						</div>					
						<div class="fold">
							<div class="fold_title">按岗位</div>
							<div class="fold_body"  style="display: none;">
								<ul id="zTreeJob" class="ztree" 
									data-iurl="<%=basePath%>common/component/ajaxJobsTree.action"
									data-curl="<%=basePath%>common/component/ajaxListLabUser4Jobs.action"
									data-name="labUserVo.duty" data-value="name"></ul>
							</div>
						</div>					
						<div class="fold">
							<div class="fold_title">按角色</div>
							<div class="fold_body"  style="display: none;">
								<ul id="zTreeRole" class="ztree" 
									data-iurl="<%=basePath%>common/component/ajaxLabRoleTree.action"
									data-curl="<%=basePath%>common/component/ajaxListLabUser4Role.action"
									data-name="labRoleVo.name" data-value="name"></ul>
							</div>
						</div>					
					</div>
					<div class="panel_2">
						<table id="preUser" class="gridPanel" cellpadding="0" border="0" cellspacing="0">
							<thead>
								<tr>
									<th style="width:15px;"><input type="checkbox" id="preCheckAll" class="checkAll"/></th>
									<th style="width:70px;">姓名</th>
									<th>部门</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
					<div class="panel_3">
						<table id="selectUsre" class="gridPanel" cellpadding="0" border="0" cellspacing="0">
							<thead>
								<tr>
									<th style="width:15px;"><input type="checkbox" id="selectCheckAll" class="checkAll"/></th>
									<th style="width:70px;">姓名</th>
									<th >部门</th>
								</tr>
							</thead>
							<tbody>
							</tbody>
						</table>
					</div>
				</div>
				<div class="footer" id="footer">
					<a id="confirm" href="javascript:;" class="new_btn">确定</a>
					<a id="close" href="javascript:;" class="new_btn">取消</a>
					<a id="remove"href="javascript:;" class="new_btn">删除</a>
					<a id="clear" href="javascript:;" class="new_btn">清空</a>
				</div>
			</div>
	</body>
</html>
