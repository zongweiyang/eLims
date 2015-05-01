<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<style>
			.bodytable .oRight {
				padding-left: 0;
			}
			html {
				_overflow-x: hidden;
			}
		</style>
		<script>
			function ajaxIsExistName(obj){//验证编号重复
				var name=$(obj).val();
				if(name.length==0){
					return ;
				}
				$.ajax({
					url:'${basePath}/klg/labItem/ajaxIsExistName.action',
					type:'POST',
					data:{'name':name},
					dataType:'text',
					success:function(data){
						if(data=='1'&& name!=$(obj).val()){
							alert('<s:property value="getText('itemnameisexis')"/>');
							$(obj).val("");
							$(obj).select();
						}
					},
					error:function(){
						alert('<s:property value="getText('theme.net.fail')"/>');
					}
				});
			}
			function selectApp(appId){
				var appId = $('#appId').val();
				$.dialog({
					id:'id',
					content:'url:'+'<%=basePath %>klg/labItem/showLabAppara4select.action?labItemVo.appId='+appId,
					title:'<s:property value="getText('apprllist')"/>',
					opacity:0.4,
					width:800, 
					height:500,
					lock:true,
					max:false,
					min:false
				 });
			}
			function checkDemo1(){
				var demo1 = $("#demo1").val();
				if(!/^[0-9]*$/.test(demo1)){
					alert('<s:property value="getText('stdvalueinput')"/>');
					$("#demo1").select();
				}
			}
		</script>
	</head>
	<body class="" id="mainid">
		<form action="" method="post" name="form" id="form">
			<input type="hidden" name="labItemVo.id" value="${labItemVo.id}"/>
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="TLimg"></td>
					<td class="TMimg"></td>
					<td class="TRimg"></td>
				</tr>
              	<tr>
					<td class="MLimg"></td>
		           	<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0"
								border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName }：
												<span><s:text name="lab.code.modify"/></span>
											</h2>
										</div>
										<div class="FUNCIONBARNEW">
                                            <table>
                                                <tr>
                                                 	<td class="blockTd" style="padding: 6px 10px;vertical-align:center;">
                                                  		<table cellspacing="0" cellpadding="0" border="0">
                                                   			<tr>
                                                      			<td>
                                                      				<l:a uri="back" value="msg.back"/> 
                                                      			</td>
                                                      			<td>
                                                      				<l:a uri="klg/labItem/updateLabItem.action" img="/img/add.gif" onclick="goAction('updateLabItem.action');" value="lab.code.save"/>
                                                      			</td>
                                                    		</tr>
                                                		</table>
                                             		</td>
                                             	</tr>
                                             </table>
                                        </div>		
										<!-- 表单型表格（用于新增/修改页面） 开始 -->
										<div class="Formtable">
											<div class="Formtabletitle">
												<span>${funName }[<font color="blue">${labItemVo.name}</font>]</span>
											</div>
											<table class="FormtableCon">
												<tr>
													<td><label><s:text name="item.name"/>：</label></td>
													<td>
														<input name="labItemVo.name"  onblur="ajaxIsExistName(this);" valType="required"  msg="项目名称不能为空" id="name" type="text" size="40" value="${labItemVo.name}" />
													</td>
													<td><label><s:text name="itemunit"/>：</label></td>
													<td>
														<input name="labItemVo.unit" id="name" type="text" size="40" value="${labItemVo.unit}" />
													</td>
												</tr>
												<tr>
													<td><label><s:text name="usingappsed"/>：</label></td>
													<td>
														<input name="labItemVo.appName" id="appName" type="text" size="40" value="${labItemVo.appName}" onclick="selectApp();" />
														<input type="hidden" name="labItemVo.appId" value="${labItemVo.appId }" id="appId" />
													</td>
													<td><label><s:text name="biaozhunfen"/>：</label></td>
													<td>
														<input valType="required" msg="<s:text name="biaozhunfen"/>不能为空" name="labItemVo.demo1" id="demo1" type="text" size="40" onblur="checkDemo1();" value="${labItemVo.demo1}" />
													</td>
												</tr>
												<tr>
													<td><label><s:text name="unithoure"/>：</label></td>
													<td>
														<input valType="number" name="labItemVo.demo2" id="name" type="text" size="40" value="${labItemVo.demo2}" />
													</td>
													<td><label><s:text name="charge.fee"/>：</label></td>
													<td>
														<input valType="number" name="labItemVo.price" id="name" type="text" size="40" value="${labItemVo.demo5}" />
													</td>
												</tr>
												<tr>
													<td><label><s:text name="feesutyle"/>：</label></td>
													<td>
														<s:radio list="#{'0':getText('bytimesdf'),'1':getText('bynusmbere')}" value="${labItemVo.priceType}" name="labItemVo.priceType" theme="simple"></s:radio>
													</td>
												</tr>
												<tr>
													<td><label><s:text name="lab.remark"/>：</label></td>
													<td colspan="3">
														<textarea cols="50" rows="3" name="labItemVo.remark">${labItemVo.remark}</textarea>
													</td>
												</tr>
											</table>
										</div>
		                  				<!-- 表单型表格（用于新增/修改页面） 结束 -->
									</div>
								</td>
							</tr>
						</table>
				    </td>
			 		<td class="MRimg"></td>
				</tr>
				<tr>
					<td class="FLimg"></td>
					<td class="FMimg"></td>
					<td class="FRimg"></td>
        		</tr>
			</table>
		</form>
	 <%@ include file="/jsp/include/foot.jsp"%> 
	</body>
	<script>
		var num=$('#indexTable tr').length;
	</script>
</html>		
