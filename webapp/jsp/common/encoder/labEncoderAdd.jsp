<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<meta http-equiv="content-type" content="text/html; charset=UTF-8" />
		<title>SaaS For LIMS - LabSoft</title>
		<style>
* {
	font-size: 9pt;
	line-height: 20px
}

.title {
	font-size: 16px;
	color: #0000FF;
	font-weight: bold
}

.con {
	margin-left: 15px;
}

.STYLE1 {
	color: #FF0000
}

.STYLE2 {
	color: #0000FF
}

.STYLE3 {
	color: #009900
}
</style>



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
	<script type="text/javascript" src="../../../js/colorPicker/jquery.colorpicker.js"></script>
	<script language=javascript> 
			function deleteOne(obj){
				$(obj).parent().parent().remove();
			}
			function ajax4CheckBusId(obj){
			var funId=$(obj).val();
			$.ajax({
				url:'${basePath}encoder/labEncoder/ajax4CheckBusId.action',
				type:'POST',
				data:{'funId':funId},
				dataType:'text',
				success:function(date){
				if(date=='0'){
				ajaxToContent(obj);
				}else{
				$("#busId").val('');
				alert("该业务已经定义过！");
				return false;
				}
				}
			  });
			}
			function ajaxToContent(obj){
				var funId=$(obj).val();
				if(funId.length>0){
					$.ajax({
						url:'${basePath}encoder/labEncoder/ajax2Content4Select.action',
						type:'POST',
						data:{'funId':funId},
						dataType:'text',
						success:function(date){
							var result=eval('('+date+')');
							//条形码
							$('#content4OneXz').remove();
							$('#content4OneTr').html('');
							$('#content4OneTr').append(
	       						$('<tr>').append($('<td>').addClass('c').append('条形码属性名称'))
	       								 .append($('<td>').addClass('c').append('条形码属性赋值'))
	       								 .append($('<td>').addClass('c').append('排序'))
	       								 .append($('<td>').addClass('c').append('是否显示'))
	       								 .append($('<td>').addClass('c').append('操作'))
	       					);
		       				for(var i=0;i<result.length;i++){
		       					$('#content4OneTr').append(
		       						$('<tr>').append($('<td>').addClass('c').append('<input type="text" name="labEncoderVo.content4One['+i+'].key" value="'+result[i].key+'" id="key'+i+'" size="40" />'))
		       								 .append($('<td>').addClass('c').append('<input readonly="readonly" type="text" name="labEncoderVo.content4One['+i+'].value" value="'+result[i].value+'" id="value'+i+'" size="40"  style="background-color: #DDDDDD;"/>'))
		       								 .append($('<td>').addClass('c').append('<input type="text" name="labEncoderVo.content4One['+i+'].sort" value="'+i+'" id="sort'+i+'" size="20" />'))
		       								 .append($('<td>').addClass('c').append('<input type="checkbox" class="txmCheckbox" onclick="check(this)" name="labEncoderVo.content4One['+i+'].isDisplay" id="isDisplay'+i+'" />'))
		       								 .append($('<td>').addClass('c').append('<a href="javascript:;" onclick="deleteOne(this)"><s:text name="lab.code.del"/></a>'))
		       					);
		       				}
		       				//二维码
		       				$('#content4TwoXz').remove();
							$('#content4TwoTr').html('');
							$('#content4TwoTr').append(
	       						$('<tr>').append($('<td>').addClass('c').append('二维码属性名称'))
	       								 .append($('<td>').addClass('c').append('二维码属性赋值'))
	       								 .append($('<td>').addClass('c').append('排序'))
	       								 .append($('<td>').addClass('c').append('是否显示'))
	       								 .append($('<td>').addClass('c').append('操作'))
	       					);
		       				for(var i=0;i<result.length;i++){
		       					$('#content4TwoTr').append(
		       						$('<tr>').append($('<td>').addClass('c').append('<input type="text" name="labEncoderVo.content4Two['+i+'].key" value="'+result[i].key+'" id="key'+i+'" size="40" />'))
		       								 .append($('<td>').addClass('c').append('<input readonly="readonly" type="text" name="labEncoderVo.content4Two['+i+'].value" value="'+result[i].value+'" id="value'+i+'" size="40"  style="background-color: #DDDDDD;"/>'))
		       								 .append($('<td>').addClass('c').append('<input type="text" name="labEncoderVo.content4Two['+i+'].sort" value="'+i+'" id="sort'+i+'" size="20" />'))
		       								 .append($('<td>').addClass('c').append('<input type="checkbox" class="ewmCheckbox" onclick="check(this)" name="labEncoderVo.content4Two['+i+'].isDisplay" id="isDisplay'+i+'"/>'))
		       								 .append($('<td>').addClass('c').append('<a href="javascript:;" onclick="deleteOne(this)"><s:text name="lab.code.del"/></a>'))
		       					);
		       				}
		       				//编号
		       				$('#num4One').html('');
		       				for(var i=0;i<result.length;i++){
		       					$('#num4One').append(
		       						$('<option value='+result[i].value+'>'+result[i].key+'</option>')
		       					);
		       				}
						},
						error:function(){
							alert('网络不通.');
						}
					});
				}else{
					var htmlstr='<tr>'+
									'<td class="c">'+
										'条形码属性名称'+
									'</td>'+
									'<td class="c">'+
										'条形码属性赋值'+
									'</td>'+
								'</tr>'+
								'<tr id="content4OneXz">'+
									'<td colspan="2">'+
										'<font color="red">请先选择业务．．．</font>'+
									'</td>'+
								'</tr>';
					$('#content4OneTr').html(htmlstr);
					htmlstr='<tr>'+
									'<td class="c">'+
										'二维码属性名称'+
									'</td>'+
									'<td class="c">'+
										'二维码属性赋值'+
									'</td>'+
								'</tr>'+
								'<tr id="content4TwoXz">'+
									'<td colspan="2">'+
										'<font color="red">请先选择业务．．．</font>'+
									'</td>'+
								'</tr>';
					$('#content4TwoTr').html(htmlstr);
				}
			}
			 $(function(){
				 $("#cp5").colorpicker({
			            fillcolor:true,
			            event:'click',
			            target:$("#frontColor4Two")
			     });
		        $("#backColeor").colorpicker({
		            fillcolor:true,
		            event:'click',
		            target:$("#backColor4Two")
		        });
			        
			 });
			    
				/* 条码类型选中 */
				$(function(){
					$.each($("input[name='labEncoderVo.busType']"),function(){
						$("input[name='labEncoderVo.busType']").attr("checked","checked");
					})
				});	
				
				function checkBarCode(obj){
					if($(obj).attr("checked")!="checked"){
						if($(obj).attr("id")=="labEncoderVo.busType-1"){
							$("#barcode").css("display","none");
						}
						if($(obj).attr("id")=="labEncoderVo.busType-2"){
						  	$("#twocode").css("display","none");
						}
					}else{
						if($(obj).attr("id")=="labEncoderVo.busType-1"){
							$("#barcode").css("display","block");
						}
						if($(obj).attr("id")=="labEncoderVo.busType-2"){
							$("#twocode").css("display","block");
						}
					}
				}
				var m=0;
				var n=0
				function check(obj){
					if($(obj).attr("type")=="checkbox"){ 
				 		if($(obj).attr("name").indexOf("content4One")>-1){
							if($(obj).attr("checked")=="checked"){
								m = m + 1;
								}else{
								m = m - 1;
								}
						}else{
							if($(obj).attr("checked")=="checked"){
								n = n + 1;
								}else{
								n = n - 1;
								}
						
						}
					}
					if(m>3){
						alert("只能选择三个属性！");
						$(obj).attr("checked",false);
						m = m - 1;
						return false;
					}else if(n>3){
						alert("只能选择三个属性！");
						$(obj).attr("checked",false);
						n = n - 1;
						return false;
					}else{
						return true;
					}
			  }
				
			function printEncoder(url){
				if((m>0&&url=='${basePath }encoder/labEncoder/showLabEncoder4BarCode.action')||n>0){
				goAction(url);
				}else{
				alert("请选择属性！");
				}
			}
			
			function ajax2BarCode(){
			var barcodeKey='';
			var barcodeValue='';
				$(".txmCheckbox:checked").each(function(i){
				barcodeKey+=$(this).closest('tr').find('input[name$="key"]').val()+',';
				barcodeValue+=$(this).closest('tr').find('input[name$="value"]').val()+',';
				});
				var size4One=$("#size4One").val();
				var type4One=$("#type4One").val();
				var num4One=$("#num4One").val();
				var numLay4One=$("#numLay4One").val();
				var url='${basePath }encoder/labEncoder/showLabEncoder4BarCode.action?labEncoderVo.size4One='+encodeURIComponent(size4One)+'&labEncoderVo.type4One='+encodeURIComponent(type4One)+'&labEncoderVo.num4One='+encodeURIComponent(num4One)+'&labEncoderVo.numLay4One='+encodeURIComponent(numLay4One)+'&labEncoderVo.barcodeKey='+encodeURIComponent(barcodeKey)+'&labEncoderVo.barcodeValue='+encodeURIComponent(barcodeValue);
				if(m>0){
				$.dialog({
					content:'url:'+url,
					title:'<s:property value="getText('print.bar')"/>',
					opacity:0.4,
					width:450,
					height:300,
					min:false,
					max:false,
					lock:true
				 });
				 }else{
				alert("请选择属性！");
				}
		    }
		    function ajax2TwoCode(){
			var barcodeKey='';
			var barcodeValue='';
				$(".ewmCheckbox:checked").each(function(i){
				barcodeKey+=$(this).closest('tr').find('input[name$="key"]').val()+',';
				barcodeValue+=$(this).closest('tr').find('input[name$="value"]').val()+',';
				});
				var rc4Two=$("#rc4Two").val();
				var size4Two=$("#size4Two").val();
				var frontColor4Two=$("#frontColor4Two").val();
				var backColeor=$("#backColeor").val();
				var busId=$("#busId").val();
				var url='${basePath }encoder/labEncoder/showLabEncoder4TwoCode.action?labEncoderVo.rc4Two='+encodeURIComponent(rc4Two)+'&labEncoderVo.size4Two='+encodeURIComponent(size4Two)+'&labEncoderVo.frontColor4Two='+encodeURIComponent(frontColor4Two)+'&labEncoderVo.backColor4Two='+encodeURIComponent(backColeor)+'&labEncoderVo.barcodeKey='+encodeURIComponent(barcodeKey)+'&labEncoderVo.barcodeValue='+encodeURIComponent(barcodeValue)+'&labEncoderVo.busId='+encodeURIComponent(busId);
				if(n>0){
				$.dialog({
					content:'url:'+url,
					title:'<s:property value="getText('print.2d')"/>',
					opacity:0.4,
					width:450,
					height:300,
					min:false,
					max:false,
					lock:true
				 });
				 }else{
				alert("请选择属性！");
				}
		    }
		    function submitValue(url){
			    var busId=$("#busId").val();
			    if(busId!=''&&busId!==null){
			    	goAction(url);
			    }else{
			    alert('请选择业务!');
			    }
		    }
			</script>
	<body>
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
								${funName }：
								<span><s:text name="admin.add"/></span>
							</h2>
						</div>
						<s:form theme="simple" action="" method="post" name="labEncoderForm">

							<div class="TabTable">
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<l:a uri="back" value="msg.back" />
														</td>
														<td>
															<l:a uri="encoder/labEncoder/addLabEncoder.action" onclick="submitValue('${basePath}encoder/labEncoder/addLabEncoder.action');return false"  value="lab.code.save" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="biz.info"/></span>
									</div>
									<table class="FormtableCon_line">
										<tr>
											<td>
												<label>
													<s:text name="biz.selected"/>：
												</label>
											</td>
											<td>
												<s:select list="#request.funcList" listKey="id" listValue="name" name="labEncoderVo.busId" id="busId" headerKey="" headerValue="请选择业务..." theme="simple" onchange="ajax4CheckBusId(this);"></s:select>
												<font color="red">*</font>
											</td>
											<td>
												<label>
													<s:text name="select.bar"/>：
												</label>
											</td>
											<td>
												<s:checkboxlist list="#{'1':getText('bardoded'),'2':getText('2dcoded')}" theme="simple" name="labEncoderVo.busType" id="busType" cssStyle="checked=checked" onclick="checkBarCode(this)"/>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="remark"/>：
												</label>
											</td>
											<td colspan="3">
												<textarea name="labEncoderVo.remark" rows="2" cols="60"></textarea>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable" id="barcode">
									<div class="Formtabletitle">
										<span><s:text name="barcode.def"/></span> <label style="float: right;"> <a href="javascript:;" onclick="ajax2BarCode()"><s:text name="barcode.preview"/></a> &nbsp;&nbsp;&nbsp;&nbsp; </label>
									</div>
									<table class="FormtableCon_line">
										<tr>
											<td>
												<label>
													<s:text name="inch"/></a>：
												</label>
											</td>
											<td>
												<select name="labEncoderVo.size4One" id="size4One" style="width: 90px">
													<option value="1cm">
														<s:text name="small"/></a>
													</option>
													<option value="2cm" selected="selected">
														<s:text name="medium"/></a>
													</option>
													<option value="3cm">
														<s:text name="large"/></a>
													</option>
												</select>
											</td>
											<td>
												<label>
													<s:text name="bar.code"/></a>：
												</label>
											</td>
											<td>
												<s:select list="#{'code39':'code39','code128':'code128','pdf417':'pdf417'}" name="labEncoderVo.type4One" id="type4One" theme="simple" cssStyle="width: 80px"></s:select>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="code.number"/></a>：
												</label>
											</td>
											<td>
												<select name="labEncoderVo.num4One" id="num4One" style="width: 90px"></select>
											</td>
											<td>
												<label>
													<s:text name="code.number"/></a><s:text name="pos.site"/></a>
												</label>
											</td>
											<td>
												<s:select id="numLay4One" list="#{'bottom':getText('xia'),'top':getText('shang'),'none':getText('wu')}" value="%{numLay4One}" name="labEncoderVo.numLay4One" theme="simple" cssStyle="width:80px"></s:select>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="show.content.or.not"/>：
												</label>
											</td>
											<td>
											<input type="radio" name="labEncoderVo.isContentTxm" value="N" id="isContentTxm" /><s:text name="lab.no"/>
											<input type="radio" name="labEncoderVo.isContentTxm" value="Y" id="isContentTxm" checked="checked"/><s:text name="lab.yes"/>
											</td>
											<td>
												
											</td>
											<td>
											</td>
										</tr>
										<tr>
											<td colspan="4">
												<table class="FormtableCon_line" id="content4OneTr">
													<tr>
														<td class="c">
															<s:text name="barcode.shuxing.name"/>
														</td>
														<td class="c">
															<s:text name="barcode.value"/>
														</td>
														<td class="c">
															<s:text name="sorted"/>
														</td>
														<td class="c">
															<s:text name="lab.yes"/><s:text name="lab.no"/><s:text name="show"/>
														</td>
														<td class="c">
															<s:text name="lab.code.ops"/>
														</td>
													</tr>
													<tr id="content4OneXz">
														<td colspan="5">
															<font color="red"><s:text name="select.biz"/></font>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>

								<div class="Formtable" id="twocode">
									<div class="Formtabletitle">
										<span><s:text name="2d.def"/></span><label style="float: right;"> <a href="javascript:;" onclick="ajax2TwoCode()"><s:text name="2d.preview"/>&nbsp;&nbsp;&nbsp;&nbsp;</a></label>
									</div>
									<table class="FormtableCon_line">
										<tr>
											<td>
												<label>
													<s:text name="terl.fault"/>：
												</label>
											</td>
											<td>
												<s:select list="#request.faultList" name="labEncoderVo.rc4Two" id="rc4Two" listKey="code" listValue="name" cssStyle="width:80px" theme="simple"></s:select>
											</td>
											<td>
												<label>
													<s:text name="inch"/></a>：
												</label>
											</td>
											<td>
												<s:select list="#request.sizeList" name="labEncoderVo.size4Two" id="size4Two" listKey="code" listValue="name" cssStyle="width:80px" theme="simple"></s:select>
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="front.color"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labEncoderVo.frontColor4Two" id="frontColor4Two" />
												<img src="${basePath }img/colorpicker.png" id="cp5" style="cursor: pointer" />
											</td>
											<td>
												<label>
													<s:text name="background.color"/>：
												</label>
											</td>
											<td>
												<input type="text" name="labEncoderVo.backColor4Two" id="backColor4Two" />
												<img src="${basePath }img/colorpicker.png" id="backColeor" style="cursor: pointer" />
											</td>
										</tr>
										<tr>
											<td>
												<label>
													<s:text name="show.content.or.not"/>：
												</label>
											</td>
											<td>
											<input type="radio" name="labEncoderVo.isContentEwm" value="N" id="isContentEwm" checked="checked"/><s:text name="lab.no"/>
											<input type="radio" name="labEncoderVo.isContentEwm" value="Y" id="isContentEwm" /><s:text name="lab.yes"/>
											</td>
											<td>
												
											</td>
											<td>
											</td>
										</tr>
										<tr>
											<td colspan="4">
												<table class="FormtableCon_line" id="content4TwoTr">
													<tr>
														<td class="c">
															<s:text name="2d.shuxing.name"/>
														</td>
														<td class="c">
															<s:text name="2d.shuxing.value"/>
														</td>
													</tr>
													<tr id="content4TwoXz">
														<td colspan="2">
															<font color="red"><s:text name="select.biz"/></font>
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>

							</div>
							<s:fielderror cssStyle="color: red"></s:fielderror>
					</div>
					</s:form>
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
	</body>
</html>
