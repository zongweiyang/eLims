<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
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

<script language=javascript> 
  	function submitvalue(actionstr){
	  	if($("div[class='Formtable oneEachDiv']").css("display")=="block"){
	  		if($("#labReportTagTitle").val()==''){
	  			alert('<s:property value="getText('tagshouldnot')"/>');
	  			return false;
	  		}
	  		if($("#labReportTagCode").val()==''){
	  			alert('<s:property value="getText('expsonnotexm')"/>');
	  			return false;
	  		}
	  	}
		$('form').attr('action','<%=basePath%>'+actionstr);
		$('form').submit();
	}
	function eachToShow(obj){
		if($(obj).val()=='for-each'){
			$('.oneEachDiv').css('display','block'); 
		}else{
			$('.oneEachDiv').css('display','none'); 
		}
		
	}
	//第一层循环数据序号改变事件
	function eachToChangeIndex(obj){
		var index=$(obj).val();
		var oldIndex=$(obj).attr('key');
		var key=$(obj).parent().parent().attr('key');
		//判断序号是否存在，若存在不修改
		var isHas=false;
		$(obj).parent().parent().parent().find('input[name*=".index"]').each(function(){
			if($(this).attr('key')==index){
				isHas=true;
				return false;
			}	
		})
		if(isHas){
			validate.tip('序号已经存在.',$(obj));
			$(obj).val(oldIndex);
			return false;
		}
		$('#TwoEachDiv'+key).find('.TwoEachIndex').html(index);
		//父循环数据修改原始值
		$(obj).attr('key',index);
	}
	//第一层循环数据类型改变事件
	function oneEachToShow(obj){
		if($(obj).val()=='for-each'){
			var twoDiv=$('#TwoEachDiv').clone();
			var key=$(obj).parent().parent().attr('key');
			var index=$(obj).parent().parent().find('input[name*="index"]').val();
			twoDiv.attr('id','TwoEachDiv'+key);//顶层div的id
			twoDiv.find('.TwoEachIndex').html(index);//title层font的calss
			twoDiv.css('display','block'); //设置显示状态
			twoDiv.find('input').each(function(n){ //给input的name赋值
				if(n==0){
					$(this).attr('name','labReportTagVo.contentList['+key+'].contentList[0].index')
				}else if(n==1){
					$(this).attr('name','labReportTagVo.contentList['+key+'].contentList[0].title')
				}else if(n==2){
					$(this).attr('name','labReportTagVo.contentList['+key+'].contentList[0].code')
				}else if(n==3){
					$(this).attr('name','labReportTagVo.contentList['+key+'].contentList[0].type')
				}
			});
			$('div #twoDiv').append(twoDiv);
		}else{
			var key=$(obj).parent().parent().attr('key');
			$('div #TwoEachDiv'+key).remove();
		}
		
	}
	//第一层循环增加行
	function addTwoEachRows(obj){
		var tabObj=$(obj).parent().parent().next();
	    var tempTr=tabObj.find('tr').eq(0).clone();
		var i=tabObj.find('tr').length;
		i=parseInt(tabObj.find('tr').eq(i-1).attr('key'))+1;
		if(isNaN(i)){
			i=0;
		}
		tempTr.find('input').each(function(n){
			var name = $(this).attr('name').replace('].contentList[0]','].contentList['+i+']');
			$(this).attr('name',name);
			if(n==0){
				$(this).val(i);
			}else if(n==3){
				$(this).val('value-of');
			}else{
				$(this).val('');;
			}
		});
		tempTr.attr('key',i);
		tempTr.find('td').eq(8).append('<a href="javascript:;" onclick="deleteOne(this);return false;"><s:text name="lab.code.del"/></a>');
		tabObj.append(tempTr);
	}
	function addOneEachRows(){
	    var tempTr=$('#oneEach').find('tr').eq(0).clone();
		var i=$('#oneEach').find('tr').length;
		i=parseInt($('#oneEach').find('tr').eq(i-1).attr('key'))+1;
		if(isNaN(i)){
			i=0;
		}
		tempTr.find('input').each(function(n){
			var name = $(this).attr('name').replace('contentList[0]','contentList['+i+']');
			$(this).attr('name',name);
			if(n==0){
				$(this).val(i);
				$(this).attr('key',i);
			}else{
				$(this).val('');
			}
		});
		tempTr.find('select').each(function(n){
			var name = $(this).attr('name').replace('contentList[0]','contentList['+i+']');
			$(this).attr('name',name);
			$(this).val('value-of');
		});
		tempTr.attr('key',i);
		tempTr.find('td').eq(8).append('<a href="javascript:;" onclick="deleteOne(this);return false;"><s:text name="lab.code.del"/></a>');
		$('#oneEach').append(tempTr);
	}
	function deleteOne(obj){
		if(confirm('<s:property value="getText('msg.confirm.del')"/>')){
			$(obj).parent().parent().remove();
			var key=$(obj).parent().parent().attr('key');
			var flag=$(obj).parent().parent().find('select').length;
			if(flag==1){
				$('#TwoEachDiv'+key).remove();
			}
		}
	};
		
</script>
	</head>
	<body>
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0"
			cellpadding="0" border="0">
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
								${funName}：
								<span>【<font color="red">${labReportTagVo.labReportTitle}</font>】<s:text name="tag.add"/></span>
							</h2>
						</div>
						<form action="" method="post" name="studentForm">
							<input type="hidden" name="labReportTagVo.labReportTitle" value="${labReportTagVo.labReportTitle}"/>
							<input type="hidden" name="labReportTagVo.labReportId" value="${labReportTagVo.labReportId}"/>
							<s:token></s:token>
							<div class="TabTable">
								<div class="FUNCIONBARNEW">
									<table>
										<tr>
											<td class="blockTd"
												style="padding: 6px 10px; vertical-align: center;">
												<table cellspacing="0" cellpadding="0" border="0">
													<tr>
														<td>
															<l:a uri="back" value="msg.back" />
														</td>
														<td>
															<l:a uri="report/labReportTag/addLabReportTag.action" onclick="submitvalue('report/labReportTag/addLabReportTag.action');return false;" value="lab.code.save" />
														</td>
													</tr>
												</table>
											</td>
										</tr>
									</table>
								</div>
								<div class="Formtable">
									<div class="Formtabletitle">
										<span><s:text name="tag.info"/></span>
									</div>
									<table class="FormtableCon">
											<tr>
												<td><label><s:text name="seq.large"/>：</label></td>
												<td><input size="10" type="text" name="labReportTagVo.index" value="${labReportTagVo.index}" id="index" valType="number,required,strLength" max="8" strLength-msg="长度不能超过8位"/></td>
												<td><label><s:text name="tag.name"/>：</label></td>
												<td><input size="60" type="text" name="labReportTagVo.title" value="${labReportTagVo.title}" id="title" valType="required,strLength"  max="64" strLength-msg="长度不能超过64位"/></td>
											</tr>
											<tr>
												<td><label><s:text name="type.large"/>：</label></td>
												<td>
													<s:select theme="simple" list="{'value-of','for-each'}" name="labReportTagVo.type" value="%{labReportTagVo.type}" onchange="eachToShow(this);"></s:select>
												</td>
												<td><label><s:text name="exp.large"/>：</label></td>
												<td><input size="60" type="text" name="labReportTagVo.code" value="${labReportTagVo.code}" id="code" valType="required,strLength"  max="128" strLength-msg="长度不能超过128位"/></td>
												
											</tr>
											<tr>
												<td><label><s:text name="remark"/>：</label></td>
												<td colspan="3">
													<textarea name="labReportTagVo.remark" cols="60" rows="3" id="remark"  valType="strLength"  max="512" strLength-msg="长度不能超过512位">${labReportTagVo.remark}</textarea>
												</td>
											</tr>
									</table>
								</div>
								<div class="Formtable oneEachDiv" style="display: none;">
									<div class="Formtabletitle">
										<span><s:text name="level1.cir"/></span>
										<label style="float: right;padding-right: 10px;"><a href="javascript:;" onclick="addOneEachRows();return false;">&nbsp;&nbsp;+&nbsp;<s:text name="config.add.row"/></a></label>
									</div>
									<table class="FormtableCon" id="oneEach">
											<tr key="0">
												<td><label><s:text name="lab.sequence"/>：</label></td>
												<td>
													<input onchange="eachToChangeIndex(this);" size="5" type="text" name="labReportTagVo.contentList[0].index" key="0" value="0"  valType="number,required,strLength" max="8" strLength-msg="长度不能超过8位"/>
												</td>
												<td><label><s:text name="tag.name"/>：</label></td>
												<td><input size="30" type="text" name="labReportTagVo.contentList[0].title" value="" id="labReportTagTitle"/><font color="red">&nbsp;*</font></td>
												<td><label><s:text name="expresion"/>：</label></td>
												<td><input size="40" type="text" name="labReportTagVo.contentList[0].code" value="" id="labReportTagCode"/><font color="red">&nbsp;*</font></td>
												<td><label><s:text name="config.type"/>：</label></td>
												<td>
													<s:select theme="simple" list="{'value-of','for-each'}" name="labReportTagVo.contentList[0].type"  onchange="oneEachToShow(this);"></s:select>
												</td>
												<td width="30">
												</td>
											</tr>
									</table>
								</div>
								<div id="twoDiv">
								
								</div>
							</div>
						</form>
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
		<div class="Formtable" id="TwoEachDiv" style="display: none;">
			<div class="Formtabletitle">
				<span><s:text name="level2.cir"/>：</span>
				[<font class="TwoEachIndex" color="red"></font>]
				<label style="float: right;padding-right: 10px;"><a href="javascript:;" onclick="addTwoEachRows(this);return false;">&nbsp;&nbsp;+&nbsp;<s:text name="config.add.row"/></a></label>
			</div>
			<table class="FormtableCon">
					<tr key="0">
						<td><label><s:text name="lab.sequence"/>：</label></td>
						<td><input size="5" type="text" name="" value="0"  valType="number,required,strLength" max="8" strLength-msg="长度不能超过8位"/></td>
						<td><label><s:text name="tag.name"/>：</label></td>
						<td><input size="30" type="text" name="" value="" valType="required,strLength"  max="64" strLength-msg="长度不能超过64位"/></td>
						<td><label><s:text name="expresion"/>：</label></td>
						<td><input size="40" type="text" name="" value="" valType="required,strLength"  max="128" strLength-msg="长度不能超过128位"/></td>
						<td><label><s:text name="config.type"/>：</label></td>
						<td>
							<input size="8" type="text" name="" value="value-of" readonly="readonly" style="background-color: #DDDDDD;"/>
						</td>
						<td width="30">
						</td>
					</tr>
			</table>
		</div>
		
	</body>
</html>
