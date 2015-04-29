<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script language="JavaScript">
			var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
			var index='';
			var json='';
			var showColum='';
			var agoValue='';
			function closeMe(){
			  	api.close();
		 	}
		 	
		 	$(function(){
		 		index=$("#diaLogIndex",D).val();
		 		json=$("#json"+index,D).val();
		 		showColum=$("#colum"+index,D).val();
		 		agoValue=$("#value"+index,D).val();
		 		showTableTh(json);
		 	});
		 	function showTableTh(json){
		 		json=eval('('+json+')');
		 		var thHtml='<tr> <th width="100"><img src="<%=basePath%>img/icon_drag.gif" /></th>';
		 		var valueHtml='';
				 for(var key in json.tableValue[0]){  
         			 	thHtml+='<th>'+key+'</th>'; 
          		 } 
          		 thHtml+='</tr>';
				for(var i=0;i<json.tableValue.length;i++){
					valueHtml+='<tr><td style="text-align: center;">';
					var check='';
					if(agoValue!=''){
						for(var j=0;j<agoValue.split(',').length;j++){
							var name=agoValue.split(',')[j];
							if(name==json.tableValue[i][showColum]){
								check='checked="checked"';
								break;
							}
						}
					}
					valueHtml+='<input  type="checkBox" value="'+json.tableValue[i][showColum]+'" '+check+' name="showValueName"/> </td>';
					 for(var key in json.tableValue[i]){  
              			 	valueHtml+='<td>'+json.tableValue[i][key]+'</td>';  
         				 } 
       				valueHtml+='</tr>';
				}
          		 $(".myworkingboxttable").append(thHtml);
          		 $(".myworkingboxttable").append(valueHtml);
		 	}
		 	function goBack(){
		 		var showValue='';
		 		$(".myworkingboxttable tr td input[type='checkBox']").each(function(){
					if($(this).attr("checked")){
						showValue+=$(this).val();
						showValue+=',';
					}		
		 		});
		 		if(showValue!=''){
		 			showValue=showValue.substring(0,showValue.length-1);
		 		}
		 		$("#value"+index,D).val(showValue);
		 		closeMe();
		 	}
		</script>

		<style>
			.bodytable .oRight {
				padding-left: 0;
			}
			
			html {
				_overflow-x: hidden;
			}
			.Formtable{
				width:95%
			}
			.myworkingboxttable th{
				background: #F5F5F5;
				color: #000000;
				border-top: #C0C0C0 1px solid;
				border-left: #C0C0C0 1px solid;
				border-right: #C0C0C0 1px solid;
			}
			.myworkingboxttable{
				width: 100%;
				margin-left: 0px;
			}
		</style>

	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 98%">
			<div class="Formtable">
			<div class="Formtabletitle">
				<span><s:text name="info.list"/></span>
			</div>
			<table class="myworkingboxttable">
				<tr>
					<td colspan="4" >
						<l:a uri="query/labQuery/listLabQuery.action" onclick="goBack();" value="page.confirm" />
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
