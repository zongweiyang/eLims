<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/WEB-INF/tld/struts/struts-tags.tld"%>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<link rel="stylesheet" href="<%=basePath%>utils/elementTable/thickbox.css" type="text/css" media="screen" />
		<link rel="stylesheet" href="<%=basePath%>utils/kindeditor/themes/default/default.css" />
		<link rel="stylesheet" href="<%=basePath%>utils/kindeditor/plugins/code/prettify.css" />
		<script charset="utf-8" src="<%=basePath%>utils/kindeditor/kindeditor.js"></script>
		<script charset="utf-8" src="<%=basePath%>utils/kindeditor/lang/zh_CN.js"></script>
		<script charset="utf-8" src="<%=basePath%>utils/kindeditor/plugins/code/prettify.js"></script>
		
		<title>SaaS For LIMS - LabSoft</title>
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
	<script>
		var editor;
		KindEditor.ready(function(K) {
			editor = K.create('#content', {
				autoHeightMode : true,
				uploadJson : '/LabUploadServlet?busId=${labMsgMainVo.id}&busType=kindEditor',
				afterCreate : function() {
						this.loadPlugin('autoheight');
					}
			});
		});
		function syncValue(){
			var html=editor.html();
			$('#content').html(htmlspecialchars(html));
		}
		function htmlspecialchars(str) {
			str = str.replace(/\&/g,"&amp;");
			str = str.replace(/\</g,"&lt;");
			str = str.replace(/\>/g,"&gt;");
			str = str.replace(/\\/g,"&quot;");
			return str;
		}
	    function submitvalue(actionstr){
	    	syncValue();
			var df = document.sysMessageForm;
		 	if(check()){
			 	df.action=actionstr;
			  	df.submit();
			}
		};
	    function check(){
            if ($("#receiverName").val()=='' || $("#receiverName").val()== null) {
                alert('<s:property value="getText('msg.select.rev')"/>');
			    return false;
            } else {
            return true;
            }
         };
		function submitvaluecaogao(actionstr){
		    var df = document.sysMessageForm;
			 	df.action=actionstr;
			  	df.submit();
			  	$('#left',parent.document).find("iframe").attr('src','<%=basePath%>coreextend/extend/leftframe.action');
	    };		
	 function ajax2UserList(){
		var url  = '${basePath}message/messageMain/showLabUser4Select.action';
		$.dialog({
			id:'roleId',
			content:'url:'+url,
			title:'<s:property value="getText('msg.user.list')"/>',
			opacity:0.4,
			min:false,
			max:false,
			width:800,
			height:400,
			lock:true
		 });
	}
	function uploadFile(busId,busType){
	   fileTypes = '*.*;';
	   var url='<%=basePath%>utils/upload/labUpLoads.jsp?busId='+busId+'&fileTypes='
	   +fileTypes+'&busType='+busType;
	   $.dialog({
			id:'id',
			content:'url:'+url,
			title:'<s:property value="getText('msg.add.attachment')"/>',
			opacity:0.4,
			width:300, 
			height:300,
			lock:true,
			max:false,
			resize:false
		 });
	}
	function deleteUploadFile(obj,id){
       if(confirm('<s:property value="getText('msg.confirm.del')"/>')){
	       	$.ajax({
				   type: "POST",
				   url:"<%=basePath %>/LabUploadServlet?delFlag=Y&fileId="+id,
				   data:"",
				   async: false,
				   success: function(data){
					   if(data==true||data=="true")
					   {
						   alert('<s:property value="getText('msg.del.success')"/>');
						   $(obj).parent().remove();
					   }
				   }
			});
       }
   	}
	</script>
	<body>
		<form action="" method="post" name="sysMessageForm" id="form">
			<input value="0" type="hidden" name="labMsgMainVo.type" />
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
									<s:text name="msg.manage"/>：
									<span><s:text name="msg.write.msg"></s:text></span>
								</h2>
							</div>

							<div class="TabTable">
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<l:a uri="/message/messageMain/addLabMsg.action" onclick="submitvalue('addLabMsg.action?labMsgMainVo.position=0');return false;" value="msg.send.msg" />
														</td>
														<td>
															<l:a uri="/message/messageMain/addLabMsg.action" onclick="submitvalue('addLabMsg.action?labMsgMainVo.position=2');return false;" value="msg.save.drfat" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="msg.msginfo"></s:text></span>
									</div>
									<table class="FormtableCon">
										<tr>
											<td>
												<label>
													<s:text name="msg.subject"></s:text>：
												</label>
											</td>
											<td>
												<input style="height: 26px; line-height: 26px;" name="labMsgMainVo.subject" id="subject" value="${labMsgMainVo.subject }" type="text" size="80" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="msg.reveiver"></s:text>：
												</label>
											</td>
											<td>
												<textarea rows="2" cols="90" id="receiverName" name="labMsgMainVo.receiverNames" readonly="readonly">${labMsgMainVo.receiverNames}</textarea>
												<input type="hidden" id="receiverId" name="labMsgMainVo.receiverIds" value="${labMsgMainVo.receiverIds}" />
												<a href="javascript:;" onclick="ajax2UserList()" name="xuanze" title='<s:property value="getText('selected.pls')"/>' class="thickbox"><s:text name="msg.add.receiver"/></a>
												<font color="red">*</font>
											</td>
										</tr>

										<tr>
											<td>
												<label>
													<s:text name="msg.attachment"></s:text>：
												</label>
											</td>
											<td>
												<input type="hidden" value="${labMsgMainVo.uuid }" name="labMsgMainVo.uuid" />
												<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="uploadFile('${labMsgMainVo.uuid }','msg');" height="20" width="20"><img height="20" width="20" src="<%=basePath%>img/icon/xinzengfujian.gif" /><b><s:text name="msg.add.attach"></s:text></b> </a>
												<s:iterator value="#request.loadList" status="st">
														<span id="${id}">${name }<a href="javascript:;" onclick="deleteUploadFile(this,'${id }')"><img src="<%=basePath%>img/zhongzhi.gif" /> </a> </span>&nbsp;&nbsp;&nbsp;
												</s:iterator>
												<div id="upfiles"></div>
											</td>
										</tr>
										<tr>
											<td colspan="2">
												<textarea name="labMsgMainVo.content" id="content" style="width:100%;height:300px;visibility:hidden;">${labMsgMainVo.content}</textarea>
											</td>
										</tr>
									</table>
								</div>
							</div>
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
		</form>
	</body>
</html>