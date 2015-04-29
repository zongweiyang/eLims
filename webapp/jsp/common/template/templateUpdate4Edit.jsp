<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.io.*,java.text.*,java.util.*,java.sql.*,javax.servlet.*,javax.servlet.http.*,DBstep.iDBManager2000.*"%>
<%@ include file="/jsp/include/common.jsp"%>
<%
	String mHttpUrlName = request.getRequestURI();
	String mScriptName = request.getServletPath();
	String mServerName = "servlet/OfficeServer";
	String mServerUrl = "http://" + request.getServerName() + ":" + request.getServerPort() + mHttpUrlName.substring(0, mHttpUrlName.lastIndexOf(mScriptName)) + "/" + mServerName;
%>
<html>
	<head>
		<title>模板管理</title>
		<link rel='stylesheet' type='text/css' href='<%=basePath%>utils/iWebOffice/test.css'>
		<script language="javascript" for=WebOffice event="OnMenuClick(vIndex,vCaption)">
   if (vIndex==1){  //打开本地文件
      WebOpenLocal();
   }
   if (vIndex==2){  //保存本地文件
      WebSaveLocal();
   }
   if (vIndex==4){  //保存并退出
     SaveDocument();    //保存正文
     webform.submit();  //提交表单
   }
   if (vIndex==6){  //打印文档
      WebOpenPrint();
   }
</script>
		<script language=javascript>

//作用：显示操作状态
function StatusMsg(mString){
  StatusBar.innerText=mString;
}

//作用：载入iWebOffice
function Load(){
  //以下属性必须设置，实始化iWebOffice
  //WebUrl:系统服务器路径，与服务器文件交互操作，如保存、打开文档，重要文件
  webform.WebOffice.WebUrl="<%=mServerUrl%>";    //WebUrl:系统服务器路径，与服务器文件交互操作，如保存、打开文档，重要文件
  webform.WebOffice.ExtParam="file";             //saveType:打开以文件形式存储的文件
  webform.WebOffice.FileName="${labTemplateVo.path}";              //mFileUrl:文件虚拟路径

  //以下属性可以不要
  webform.WebOffice.ShowMenu="1";  //ShowMenu:1 显示菜单  0 隐藏菜单
  webform.WebOffice.AppendMenu("1","打开本地文件(&L)");
  webform.WebOffice.AppendMenu("2","保存本地文件(&S)");
  webform.WebOffice.AppendMenu("3","-");
  webform.WebOffice.AppendMenu("4","保存并退出(&E)");
  webform.WebOffice.AppendMenu("5","-");
  webform.WebOffice.AppendMenu("6","打印文档(&P)");
  webform.WebOffice.DisableMenu("宏;选项;帮助");  //禁止菜单
  webform.WebOffice.WebOpen(false);
  StatusMsg(webform.WebOffice.Status);
}

//作用：退出iWebOffice
function UnLoad(){
  try{
  if (!webform.WebOffice.WebClose()){
     StatusMsg(webform.WebOffice.Status);
  }else{
     StatusMsg("关闭文档...");
  }
  }catch(e){}
}


//作用：打开文档
function LoadDocument(){
  StatusMsg("正在打开文档...");
  if (!webform.WebOffice.WebOpen(false)){  //交互OfficeServer的OPTION="LOADTEMPLATE"
     StatusMsg(webform.WebOffice.Status);
  }else{
     StatusMsg(webform.WebOffice.Status);
  }
}

//作用：打印文档
function WebOpenPrint(){
  try{
    webform.WebOffice.WebOpenPrint();
    StatusMsg(webform.WebOffice.Status);
  }catch(e){}
}


//作用：存为本地文件
function WebSaveLocal(){
  try{
    webform.WebOffice.WebSaveLocal();
    StatusMsg(webform.WebOffice.Status);
  }catch(e){}
}

//作用：打开本地文件
function WebOpenLocal(){
	try{
	  	webform.WebOffice.WebOpenLocal();
	    StatusMsg(webform.WebOffice.Status);
	}catch(e){}
}

//作用：保存文档
function SavFile(){
	if (webform.WebOffice.WebSave()){
		StatusMsg(webform.WebOffice.Status);
     	doSubmit();
	}else{
		StatusMsg(webform.WebOffice.Status);
    	return false;
	}
}
function doSubmit(){
	var df = document.webform;
		df.action="${basePath}/template/labTemplate/updateLabTemplate4Edit.action";
		df.submit();
}
function getValue(obj){
	$('#valueStr').val($(obj).val());
}

$(function(){
	if(null != $('#objUrl').val()){
		$.ajax({
			url:'<%=basePath%>page/labPageEditor/ajaxGetParameter.action',
			data:{'labPageEditorVo.objName':$('#objName').val(),'labPageEditorVo.objUrl':$('#objUrl').val()},
			type:'post',
			dataType:'text',
			success:function (data){
				if(null != data && data != '[]'){
					var result = eval('('+ data +')');
					$('#paramSelect').append(
						$('<option>').attr({'value':''}).html('请选择')
					)
					if(null != result && result.length > 0){
						for(var i = 0; i < result.length; i++){
							$('#paramSelect').append(
								$('<option>').attr({'value':'\${' + result[i].id + '}'}).html(result[i].name)
							)
						}
					}
				}else{
					$('#paramSelect').append(
						$('<option>').attr({'value':''}).html('请选择')
					)
				}
			}
		});
	}
});
function selectThis(obj){
	$('#paramInput').val($('#paramSelect').val());
}
</script>
	</head>
	<body bgcolor="#ffffff" onload="Load()" onunload="UnLoad()" style="overflow: hidden;">
		<div style="display: none;">
			<s:text name="tag.one"/>
			<br>
			<s:text name="tag.two"/>
			<br>
			<s:text name="tag.three"/>
			<a href="<%=basePath%>utils/iWebOffice/InstallClient.zip"><s:text name="tag.down"/></a>。
			<br>
		</div>
		<form name="webform" method="post" onsubmit="return SaveDocument();">
			<input type="hidden" value="${labTemplateVo.id}" name="labTemplateVo.id">
			<input type="hidden" name="labTemplateVo.objUrl" id="objUrl" value="${labTemplateVo.objUrl}" />
			<s:token></s:token>
			<!--保存iWebOffice后提交表单信息-->
			<div class="FUNCIONBARNEW">
				<table>
					<tr>
						<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
							<table cellspacing="0" cellpadding="0" border="0">
								<tr>
									<td>
										<label>
											<s:text name="can.used"/>：
										</label>
									</td>
									<td>
										<select id="paramSelect" onchange="selectThis(this);" style="width: 150px"></select>
									</td>
									<td>
										<input id="paramInput" size="40" />
									</td>
									<td>
										<a id="BtnPreview" class="zPushBtn" href="#" onclick="WebOpenPrint();return false;"> <img height="20" width="20" src="${basePath}img/dayin.gif" /><b><s:text name="print.doc"/></b> </a>
									</td>
									<td>
										<a id="BtnPreview" class="zPushBtn" href="#" onclick="LoadDocument();return false;"> <img height="20" width="20" src="${basePath}img/application_go.png" /><b><s:text name="readj.form"/></b> </a>
									</td>
									<!-- <td>
										<a id="BtnPreview" class="zPushBtn" href="#" onclick="WebOpenLocal();return false;">
											<img height="20" width="20" src="${basePath}img/image_add.png" /><b><s:text name="openlocale"/></b>
										</a>
									</td>
									<td>
										<a id="BtnPreview" class="zPushBtn" href="#" onclick="WebSaveLocal();return false;">
											<img height="20" width="20" src="${basePath}img/filesave.gif" /><b><s:text name="savelocal"/></b>
										</a>
									</td> -->
									<td>
										<a id="BtnPreview" class="zPushBtn" href="#" onclick="SavFile();return false;"> <img height="20" width="20" src="${basePath}img/filesave.gif" /><b><s:text name="lab.code.save"/></b> </a>
									</td>
									<td>
										<div id=StatusBar>
										</div>
									</td>
								</tr>
							</table>
						</td>
					</tr>
				</table>
			</div>
			<table border='0' cellspacing='0' cellpadding='0' width='100%' id="TBStyle" class='TBStyle'>
				<tr>
					<td class="TDStyle">
						<table border='0' cellspacing='0' cellpadding='0' width='100%' height='100%'>
							<tr>
								<td bgcolor="menu" height="100%">
									<script src="<%=basePath%>utils/iWebOffice/iWebOffice2003.js"></script>
								</td>
							</tr>
						</table>
					</td>
				</tr>
			</table>
		</form>
	</body>
	<script>
	$(function(){
		var height = window.screen.height-290;
		$('#TBStyle').css('height',height);
		$('#TBStyle').css('width','100%');
	});
</script>
</html>
