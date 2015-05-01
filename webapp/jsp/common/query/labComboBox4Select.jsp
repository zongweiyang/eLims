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
			function closeMe(){
			  	api.close();
		 	}
		 	function goBack(){
		 		var info=$("#info").val();
		 		var value="";
		 		var html='<select style="width:145px" name="labQueryVo.listQuery['+id+'].value" id="select'+id+'">';
		 		if(info==''){
		 			alert('<s:property value="getText('plseexpingo')"/>');
		 		}else{
					$("input").each(function(){
						value+=$(this).val();
						value+=',';
					}); 
					html+='<option value="">---请选择---</option>';
					if(value!=''&&value.length>2){
						for(var i=0;i<value.split(',').length;i=i+2){
							if(value.split(',')[i]!=''&&value.split(',')[i]!='undefined'){
								html+='<option value="'+value.split(',')[i+1]+'">'+value.split(',')[i]+'</option>';
							}
						}
						html+='</select>';
					}	
					html+="<script type=\"text/javascript\">$(\"#select"+id+"\").val('')<\/script>";
					$('#comboxValue'+id,D).val(html);
					$('#combox'+id,D).val(value);
					closeMe();
		 		}
		 	}
		 	$(function(){
		 		id=$('#comboBoxId',D).val();
	 			var valueD=$('#combox'+id,D).val();
	 			if(valueD!=''){
 					var html='';
					for(var i=0;i<valueD.split(',').length;i=i+2){
						if(valueD.split(',')[i]!=''&&valueD.split(',')[i]!='undefined'){
							html+='<tr>';
							html+='<td><label>显示值</label> </td>';
							html+='<td><input type="text" value="'+valueD.split(',')[i]+'" /></td>';
							html+='<td><label>传入值</label> </td>';
							html+='<td><input type="text" value="'+valueD.split(',')[i+1]+'" /></td>';
							html+='</tr>';
						}
					}
					$("table tr:gt(0)").remove();
					$("table").append(html);
	 			}
		 	});
		 	function addRow(){
				var row=$("table tr:last").clone();
				row.find("input").each(function(){
					$(this).val('');
				});
				$("table").append(row);
		 	}
		 	function deleteRow(){
		 		$("table tr:gt(1):last").remove();
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
				<span><s:text name="expresion"/></span>
			</div>
			<table class="FormtableCon">
				<tr>
					<td colspan="4">
						<l:a uri="role/labRole/updateLabRoleT.action" onclick="goBack();" value="page.confirm" />
						<l:a uri="role/labRole/addLabRoleT.action" onclick="addRow();" value="added" />
						<l:a uri="role/labRole/deleteLabRoleT.action" onclick="deleteRow();" value="lab.code.del" />
					</td>
				</tr>
				<tr>
					<td>
						<label><s:text name="show.value"/></label>
					</td>
					<td>
						<input type="text"/>
					</td>
					<td>
						<label><s:text name="inputvalue"/></label>
					</td>
					<td>
						<input type="text"/>
					</td>
				</tr>
			</table>
		</div>
	</body>
</html>
