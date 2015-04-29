<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
		<script language="JavaScript">
			function submitvalue(actionstr){
		       	var df = document.sampleEnterForm;
		 		df.action=actionstr;
		  		df.submit();
		  	}
	function goToNextAction(url){
			$('form').attr('action','${basePath}'+url);
			$('form').submit();
	}
	function check(val){
		if("1"==val){
			 $("[type='checkbox']").each(function(){
                    $(this).attr("checked","checked");
              });
		}else{
			 $("[type='checkbox']:checked").each(function(){
                     $(this).removeAttr("checked");
              });
		}
		
	}
	function addQu(){
		var trtd="<tr><td><a onclick=\"delQu(this);\"><img height=\"20\" width=\"20\" src=\"${basePath}img/jian.gif\" /></a></td>";
				trtd+="<td>";
					trtd+="<select name=\"labDocVo.ext2\" style=\"width\:100px;\">";
						trtd+="<option value=\"all\">请选择</option>";
						trtd+="<option value=\"author\">作者</option>";
						trtd+="<option value=\"keyWord\">关键字</option>";
						trtd+="<option value=\"summary\">摘要</option>";
						trtd+="<option value=\"remark\">备注</option>";
					trtd+="</select>";
				trtd+="</td>";
				trtd+="<td>";
					trtd+="<select name=\"labDocVo.ext3\" style=\"width\:80px;\">";
						trtd+="<option value=\"模糊\">模糊</option>";
						trtd+="<option value=\"精确\">精确</option>";
					trtd+="</select>";
				trtd+="</td>";
				trtd+="<td>";
					trtd+="<input type=\"text\" name=\"labDocVo.ext4\" style=\"width\: 320px;\" />";
				trtd+="</td>";
				trtd+="<td>";
					trtd+="<select name=\"labDocVo.ext5\" style=\"width\:50px;\">";
						trtd+="<option value=\"and\">与</option>";
						trtd+="<option value=\"or\">或</option>";
						trtd+="<option value=\"<>\">非</option>";
					trtd+="</select>";
				trtd+="</td>";
				trtd+="<td></td>";
			trtd+="</tr>";
			$("#tableQu").append(trtd);
	}
	function delQu(obj){
		 var row = obj.parentNode.parentNode;
		var tbl = obj.parentNode.parentNode.parentNode;
		var index = row.rowIndex;
		tbl.deleteRow(index);
	}
	$(function(){
		var literatureList =${literatureJSONList};
		if(literatureList.length>0){
			$("[type='checkbox']").each(function(){
					for(var i =0;i<literatureList.length;i++){
						var val = literatureList[i].replace(/(^\s*)|(\s*$)/g,'')
						if(val==$(this).val()){
							 $(this).attr("checked","true");
						}
					}
             });
		}		

	})
</script>

		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>

	</head>
	<body class="" id="mainid" style="overflow-y: auto; height: 100%">
		<s:form theme="simple" action="" method="post" name="sampleEnterForm">
			<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
				<tr>
					<td class="MLimg"></td>
					<td id="bodyCell" class="oRight">
						<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
							<tr>
								<td>
									<div class="myworkingbox">
										<div class="myworkingboxttitle">
											<h2>
												${funName}
												<span></span>
											</h2>
										</div>
										<div class="TabTable">
											<div class="TabTableNAV">
												<ul>
													<li id="li01" class="currenttab">
														<a href="javascript:;"><span><s:text name="advsearch"/></span> </a>
													</li>
													<li id="li02" class="">
														<a href="javascript:;" onclick="javascript:window.location.href='<%=basePath%>doc/labDoc/listLabDocSolr.action';return false;"><span>专业检索</span> </a>
													</li>
												</ul>
											</div>

											<!-- pageResule query -->
											<div class="TabTableBOX01 b" id="Tab01">
												<div class="tabtablebox">
													<div class="FUNCIONBARNEW">
														<table>
															<tr>
																<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
																	<table cellspacing="0" cellpadding="0" border="0">
																		<tr>
																			<td colspan="4" style="text-align: center;">
																				<font><s:text name="selected"/>文献类型</font>
																				<br />
																				<center style="padding-left: 10px;">
																					<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="check('1')"><b>全选</b> </a>
																					<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="check('0')"><b>清除</b> </a>
																				</center>
																			</td>
																		</tr>
																		<s:if test="docTypeList!=null">
																			<s:set name="alllist" value="docTypeList" />
																			<s:set name="isurgent" value="0" />
																			<tr>
																				<s:iterator value="#alllist" status="st">
																					<td>
																						<input type="checkbox" value="${name}" name="labDocVo.literature" id="id${st.index}" />
																					</td>
																					<td width="30">
																						<label>
																							${name}
																						</label>
																					</td>
																					<s:if test="${(st.index+1)%2==0}">
																			</tr>
																			<tr>
																		</s:if>
																		</s:iterator>
																		</s:if>
																	</table>
																</td>
																<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
																	<s:if test="${labDocVoList!=null && labDocVoList!=''}">
																		<table id="tableQu" cellspacing="0" cellpadding="0" border="0">
																			<s:set name="alllist" value="labDocVoList" />
																			<s:iterator value="#alllist" status="st">
																				<tr>
																					<td>
																						<s:if test="${st.index==0}">
																							<a onclick="addQu();"><img height="20" width="20" src="${basePath}img/jia.gif" />
																							</a>
																						</s:if>
																						<s:else>
																							<a onclick="delQu(this);"><img height="20" width="20" src="${basePath}img/jian.gif" />
																							</a>
																						</s:else>
																					</td>
																					<td>
																						<s:select list="#{'all':'请选择','author':'作者','keyWord':'关键字','summary':'摘要','remark':'备注'}" headerValue="${ext2=='all'?'请选择':ext2=='place'?'编号':ext2=='author'?'作者':ext2=='authorUnit'?'作者单位':ext2=='keyWord'?'关键字':ext2=='ext1'?'文件柜':ext2=='summary'?'摘要':'备注'}" headerKey="${ext2}" name="labDocVo.ext2" cssStyle="width:100px;"></s:select>
																					</td>
																					<td>
																						<s:select list="#{'模糊':'模糊','精确':'精确'}" name="labDocVo.ext3" headerValue="${ext3}" headerKey="${ext3}" cssStyle="width:80px;"></s:select>
																					</td>
																					<td>
																						<input type="text" name="labDocVo.ext4" style="width: 320px;" value="${ext4}" />
																					</td>
																					<td>
																						<s:select list="#{'and':'与','or':'或','<>':'非'}" name="labDocVo.ext5" headerValue="${ext5=='and'?'与':ext5=='or'?'或':'非'}" headerKey="${ext5}" cssStyle="width:50px;"></s:select>
																					</td>
																					<td>
																						<s:if test="${st.index==0}">
																							<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="goToNextAction('doc/labDoc/listLabDocSolr2db.action');return false;"><img height="20" width="20" src="${basePath}img/chakan.gif" /><b>搜索</b> </a>
																						</s:if>
																					</td>
																				</tr>
																			</s:iterator>
																		</table>
																	</s:if>
																	<s:else>
																		<table id="tableQu" cellspacing="0" cellpadding="0" border="0">
																			<tr>
																				<td>
																					<a onclick="addQu();"><img height="20" width="20" src="${basePath}img/jia.gif" /> </a>
																				</td>
																				<td>
																					<s:select list="#{'all':'请选择','author':'作者','keyWord':'关键字','summary':'摘要','remark':'备注'}" name="labDocVo.ext2" cssStyle="width:100px;"></s:select>
																				</td>
																				<td>
																					<s:select list="#{'模糊':'模糊','精确':'精确'}" name="labDocVo.ext3" cssStyle="width:80px;"></s:select>
																				</td>
																				<td>
																					<input type="text" name="labDocVo.ext4" style="width: 320px;" />
																				</td>
																				<td>
																					<s:select list="#{'and':'与','or':'或','<>':'非'}" name="labDocVo.ext5" cssStyle="width:50px;"></s:select>
																				</td>
																				<td>
																					<a id="BtnPreview" class="zPushBtn" href="javascript:;" onclick="goToNextAction('doc/labDoc/listLabDocSolr2db.action');return false;"><img height="20" width="20" src="${basePath}img/chakan.gif" /><b>搜索</b> </a>
																				</td>
																			</tr>
																		</table>
																	</s:else>
																</td>
															</tr>
														</table>
													</div>
												</div>
												<s:if test="pageResult!=null">
													<table class="myworkingboxttable" cellspacing="0" cellpadding="0">
														<tr>
															<th class="c">
																<s:text name="file.name"/>
															</th>
															<th class="c">
																<s:text name="lab.code.ops"/>者
															</th>
															<th class="c">
																<s:text name="remark"/>
															</th>
															<th class="c">
																<s:text name="key.word"/>
															</th>
															<th class="c">
																<s:text name="abstract"/>
															</th>
															<th class="c">
																<s:text name="lab.code.ops"/>
															</th>
														</tr>
														<s:if test="pageResult.resultList!=null">
															<s:set name="alllist" value="pageResult.resultList" />
															<s:iterator value="#alllist" status="st" id="one">
																<tr>
																	<td>
																		${fileName}
																	</td>
																	<td>
																		${author}
																	</td>
																	<td>
																		${remark}
																	</td>
																	<td>
																		${keyWord}
																	</td>
																	<td>
																		${summary}
																	</td>
																	<td class="c">
																		<s:iterator value="#one.labUploadList" status="st1" id="two">
																			<a href="<%=basePath%>utils/upload/down.jsp?fileName=${name}&amp;fileUrl=${path}" style="font-size: 14px; cursor: hand; display：block; margin-top: 4px;">${two.name}</a>
																		</s:iterator>
																	</td>
																</tr>
															</s:iterator>
														</s:if>
													</table>
												</s:if>
											</div>
										</div>
										<jsp:include page="/jsp/include/page.jsp?formName=sampleEnterForm" flush="true" />
									</div>
								</td>
							</tr>
						</table>
						<!-- pageResule query end -->
					</td>
				</tr>
			</table>
		</s:form>
		<%@ include file="/jsp/include/foot.jsp"%>
	</body>
</html>
