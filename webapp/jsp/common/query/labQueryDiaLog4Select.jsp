<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script language="JavaScript">
			var api = frameElement.api, W = api.opener, D = W.document; // api.opener 为载加lhgdialog.min.js文件的页面的window对象
			var id='';
			var jsonVlue='';
			var index='';
			var columValue='';
			function closeMe(){
			  	api.close();
		 	}
		 	function showColom(){
		 		var value=$("#valueId").val();
		 		if(value!=null&&value!=''){
		 			$.ajax({
						url:'<%=basePath%>'+value,
						type:'post',
						dataType:'text',
						success:function (data){
							var json='';
							try{
								json=eval('(' +data+ ')');
							}catch(e){
								validate.tip('请检查访问路径',$("#valueId"))
								$("#showFormtableCon").html('');
								return;
							}
							var html='';
							var checkColum='';
							try{
								 for(var key in json.tableValue[0]){  
										html+='<tr><td><label>显示字段：</label> </td>';
										html+='<td>'+key+'</td>';
		               			 		html+='<td><label>是否为匹配条件：</label></td>';
		               			 		if(columValue!=''&&columValue==key){
											checkColum='checked="checked"';	
										}
		               			 		html+='<td><input type="radio" name="diaLogColum" '+checkColum+' value="'+key+'" /> </td></tr>';
		               			 		checkColum='';
		          				 } 
							}catch(e){
								alert('<s:property value="getText('jsonformate')"/>');
								return ;
							}
								jsonVlue=data;
								$("#showDiv table").html('');
								$("#showDiv table").append(html);
								$("#showDiv").show();
					   	  },error: function(XMLHttpRequest, textStatus, errorThrown) {
                        	alert('<s:property value="getText('chekcpath')"/>');
                 		 }
		 			 });
		 			
		 		}
		 	}
		 	$(function(){
		 		 index=$("#comboBoxId",D).val();
		 		 columValue=$("#diaLogColum"+index,D).val();
	 		 	 var diaLogAction=$("#diaLogAction"+index,D).val();
	 		 	 if(diaLogAction!=''){
	 		 	 	$("#valueId").val(diaLogAction);
	 		 	 	showColom();
	 		 	 }
	 			
		 	});
		 	function submit(){
				$("#dialogJson"+index,D).val(jsonVlue);
				$("#diaLogColum"+index,D).val($("input[name='diaLogColum']:checked").val());
				$("#diaLogAction"+index,D).val($("#valueId").val());
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
		</style>

	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 98%">
		<div class="Formtable">
			<div class="Formtabletitle">
				<span><s:text name="data.source"/></span>
			</div>
			<table class="FormtableCon">
				<tr>
					<td colspan="4">
						<l:a uri="query/labQuery/listLabQuery.action" onclick="showColom();" value="get.data.source" />
						<l:a uri="query/labQuery/addLabQuery.action" onclick="submit();" value="page.confirm" />
					</td>
				</tr>
				<tr>
					<td>
						<label><s:text name="ajax.addr"/>：</label>
					</td>
					<td>
						<input id="valueId" type="text" style="width: 200px"/>
					</td>
				</tr>
			</table>
		</div>
		<div class="Formtable" style="display: none;" id="showDiv">
			<div class="Formtabletitle">
				<span><s:text name="show.col"/></span>
			</div>
			<table class="FormtableCon" id="showFormtableCon">
				
			</table>
		</div>
	</body>
</html>
