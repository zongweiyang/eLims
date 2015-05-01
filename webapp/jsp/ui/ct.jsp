<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="../include/common.jsp"%>
		<title>SaaS For LIMS - LabSoft</title>
		<meta content="text/html; charset=utf-8" http-equiv="Content-Type" />

		<style>
.bodytable .oRight {
	padding-left: 0;
}

html {
	_overflow-x: hidden;
}
</style>
	</head>

	<body class="" id="mainid">
		<table id="bodyTable" class="bodytable" width="100%" cellspacing="0" cellpadding="0" border="0">
			<tr>
				<td class="TLimg"></td>
				<td class="TMimg"></td>
				<td class="TRimg"></td>
			</tr>
			<tr>
				<td class="MLimg"></td>

				<td id="bodyCell" class="oRight">
					<table class="workingBody" cellspacing="0" cellpadding="0" border="0">
						<tr>
							<td>
								<div class="myworkingbox">

									<div class="myworkingboxttitle">
										<h2>
											UI模板：
											<span>通用模板</span>
										</h2>
									</div>
									<!-- 模板内容页面 开始 -->

									<!-- 通用标题 开始 用于显示一些单据的标题 -->
									<div class="commontitle">
										<h2>
											XXXXXXXXXXXXXXXX<s:text name="shunyibiao"/>
										</h2>
									</div>
									<!-- 通用标题 结束 用于显示一些单据的标题 -->

									<!-- 按钮条 开始 -->
									<div class="FUNCIONBARNEW">
										<table>
											<tr>
												<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
													<table cellspacing="0" cellpadding="0" border="0">
														<tr>
															<td>
																<label>
																	<s:text name="textandtitle"/>：
																	<label>
															</td>
															<td>
																<label>
																	<s:text name="selectbox"/>
																	<label>
																		<select>
																			<option>
																				<s:text name="optionone"/>
																			</option>
																			<option>
																				<s:text name="optiontwo"/>
																			</option>
																		</select>
															</td>
															<td>
																<label>
																	<s:text name="pageshow"/>
																	<label>
																		<select>
																			<option>
																				5
																			</option>
																			<option>
																				10
																			</option>
																		</select>
																		<label>
																			<s:text name="page.record"/>
																			<label>
															</td>
															<td>
																<input type="text" value="" />
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick="submitvalueforlist('addComType.action');"><img height="20" width="20" src="${basePath}img/chakan.gif" /><b><s:text name="fun.query"/></b> </a>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</div>
									<!-- 按钮条 结束 -->

									<!-- 按钮条 开始 -->
									<div class="FUNCIONBARNEW">
										<table>
											<tr>
												<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
													<table cellspacing="0" cellpadding="0" border="0">
														<tr>
															<td>
																<label>
																	<s:text name="buttonstdemo"/>：
																	<label>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/bianji.gif" /><b><s:text name="toyong"/></b> </a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/xinjian.gif" /><b><s:text name="newbuild"/></b> </a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/shanchu.gif" /><b><s:text name="lab.code.del"/></b> </a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/chakan.gif" /><b><s:text name="fun.query"/></b> </a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/bianji.gif" /><b><s:text name="lab.code.modify"/></b> </a>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</div>
									<!-- 按钮条 结束 -->

									<!-- 按钮条 开始 -->
									<div class="FUNCIONBARNEW">
										<table>
											<tr>
												<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
													<table cellspacing="0" cellpadding="0" border="0">
														<tr>
															<td>
																<label>
																	<s:text name="buttonstdemo"/>：
																	<label>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/tijiao.gif" /><b><s:text name="post.commit"/></b> </a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/tongyi.gif" /><b><s:text name="agereend"/></b> </a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/fanhui.gif" /><b><s:text name="lab.back.list"/></b> </a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/filesave.gif" /><b><s:text name="lab.code.save"/></b> </a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/filesavenew.gif" /><b><s:text name="saveandnews"/></b> </a>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</div>
									<!-- 按钮条 结束 -->

									<!-- 按钮条 开始 -->
									<div class="FUNCIONBARNEW">
										<table>
											<tr>
												<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
													<table cellspacing="0" cellpadding="0" border="0">
														<tr>
															<td>
																<label>
																	<s:text name="buttonstdemo"/>：
																	<label>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/shuaxin.gif" /><b><s:text name="msg.refresh"/></b> </a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/zhuanyi.gif" /><b><s:text name="transeredse"/></b> </a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/fuzhi.gif" /><b><s:text name="copy"/></b> </a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/shoufei.gif" /><b><s:text name="charge.fee"/></b> </a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/dayin.gif" /><b><s:text name="print"/></b> </a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/zhuanyi.gif" /><b><s:text name="export"/></b> </a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/zhongzhi.gif" /><b><s:text name="deadednd"/></b> </a>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</div>
									<!-- 按钮条 结束 -->

									<!-- 按钮条 开始 -->
									<div class="FUNCIONBARNEW">
										<table>
											<tr>
												<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
													<table cellspacing="0" cellpadding="0" border="0">
														<tr>
															<td>
																<label>
																	<s:text name="buttonstdemo"/>：
																	<label>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/youjian.gif" /><b>邮件</b> </a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/xieyoujian.gif" /><b>写邮件</b> </a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/xinjianyoujian.gif" /><b>新建邮件</b> </a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/xinzengfujian.gif" /><b>新增附件</b> </a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/shanchuyoujian.gif" /><b>删除邮件</b> </a>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</div>
									<!-- 按钮条 结束 -->

									<!-- 按钮条 开始 -->
									<div class="FUNCIONBARNEW">
										<table>
											<tr>
												<td class="blockTd" style="padding: 6px 10px; vertical-align: center;">
													<table cellspacing="0" cellpadding="0" border="0">
														<tr>
															<td>
																<label>
																	<s:text name="buttonstdemo"/>：
																	<label>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/youjianlianjie.gif" /><b>邮件链接</b> </a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/fsyoujian.gif" /><b>发送邮件</b> </a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/weiduyoujian.gif" /><b><s:text name="msg.unread"/></b> </a>
															</td>
															<td>
																<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/yiduxinxi.gif" /><b><s:text name="msg.readed"/></b> </a>
															</td>
														</tr>
													</table>
												</td>
											</tr>
										</table>
									</div>
									<!-- 按钮条 结束 -->

									<!-- 提示型表格 开始 -->
									<div class="Formtable tips">
										<div class="Formtabletitle_tips">
											<span><s:text name="unpassinfolist"/>：</span>
										</div>
										<table class="FormtableCon">
											<tr>
												<td>
											<s:text name="contentdesd"/>：内容内容内容内容内容内容内容内容内容
												</td>
											</tr>
										</table>
									</div>
									<!-- 提示型表格 结束 -->

									<!-- 数据型表格 开始 -->
									<table class="myworkingboxttable" cellspacing="1" cellpadding="0">
										<thead>
											<tr>
												<th>
													<input type="checkbox" id="allCheckBox" key="comTypeVo.typeIds" />
												</th>
												<th>
													<img src="${basePath}img/icon_drag.gif" />
												</th>
												<th>
													公共代码类型名称
												</th>
												<th>
													公共代码类型编码
												</th>
												<th>
													<s:text name="lab.code.ops"/>
												</th>
											</tr>
										</thead>
										<tbody>
											<tr>
												<td class="c">
													<input type="checkbox" name="" value="" />
												</td>
												<td class="c">
													1
												</td>
												<td>
													<s:text name="config.name"/><s:text name="config.name"/>名称名称名称名称名称名称名称名称
												</td>
												<td>
													<s:text name="codessafd"/>编码编码编码编码编码编码编码编码编码
												</td>
												<td class="c">
													<s:text name="modifdelmain"/>
												</td>
											</tr>
											<tr>
												<td class="c">
													<input type="checkbox" name="" value="" />
												</td>
												<td class="c">
													1
												</td>
												<td>
													<s:text name="config.name"/><s:text name="config.name"/>名称名称名称名称名称名称名称名称
												</td>
												<td>
													<s:text name="codessafd"/>编码编码编码编码编码编码编码编码编码
												</td>
												<td class="c">
													<s:text name="modifdelmain"/>
												</td>
											</tr>
										</tbody>
									</table>
									<!-- 数据型表格 结束 -->

									<!-- 表单型表格（用于新增/修改页面） 开始 -->
									<div class="Formtable">
										<div class="Formtabletitle">
											<span>表单型表格（用于新增/修改页面）</span>
										</div>
										<table class="FormtableCon">
											<tr>
												<td>
													<label>
														<s:text name="config.name"/>：
													</label>
												</td>
												<td>
													<input name="" id="name" type="text" size="20" value="" />
												</td>
												<td>
													<label>
														<s:text name="code.number"/>：
													</label>
												</td>
												<td>
													<input name="" id="code" type="text" size="20" value="" />
													<input name="" id="remark" type="hidden" size="20" value="0" />
												</td>
												<td>
													<label>
														<s:text name="lab.sequence"/>：
													</label>
												</td>
												<td>
													<input name="" id="sort" type="text" size="20" value="" />
												</td>
											</tr>
											<tr>
												<td>
													<label>
												<s:text name="theme.sex"/>：
													</label>
												</td>
												<td>
													<input name="" id="name" type="radio" value="" />
													<label style="padding: 0 20px 0 5px;">
														男
													</label>
													<input name="" id="name" type="radio" value="" />
													<label style="padding: 0 20px 0 5px;">
														女
													</label>
												</td>
												<td>
													<label>
														<s:text name="config.type"/>：
													</label>
												</td>
												<td>
													<select>
														<option>
															<s:text name="optionone"/>
														</option>
														<option>
															<s:text name="optiontwo"/>
														</option>
													</select>
												</td>
												<td>
													<label>
														<s:text name="remark"/>：
													</label>
												</td>
												<td>
													<textarea></textarea>
												</td>
											</tr>
										</table>
									</div>
									<!-- 表单型表格（用于新增/修改页面） 结束 -->


									<!-- 表单型表格（用于查看页面） 开始 -->
									<div class="Formtable">
										<div class="Formtabletitle">
											<span>表单型表格（用于查看页面）</span>
										</div>
										<table class="FormtableCon">
											<tr>
												<td>
													<label>
														<s:text name="config.name"/>：
													</label>
												</td>
												<td>
													张三
												</td>
												<td>
													<label>
														<s:text name="code.number"/>：
													</label>
												</td>
												<td>
													123123
												</td>
												<td>
													<label>
														<s:text name="lab.sequence"/>：
													</label>
												</td>
												<td>
													100
												</td>
											</tr>
											<tr>
												<td>
													<label>
												<s:text name="theme.sex"/>：
													</label>
												</td>
												<td>
													男
												</td>
												<td>
													<label>
														<s:text name="config.type"/>：
													</label>
												</td>
												<td>
													<s:text name="config.type"/>一
												</td>
												<td>
													<label>
														<s:text name="remark"/>：
													</label>
												</td>
												<td>
													<s:text name="remark"/>内容备注内容备注内容备注内容
												</td>
											</tr>
										</table>
									</div>
									<!-- 表单型表格（用于查看页面） 结束 -->

									<!-- 模板内容页面 结束 -->


								</div>


							</td>
						</tr>
						<!-- 分页 开始 -->
						<tr>
							<td align="center"><jsp:include page="../include/page.jsp?actionparam=listComType.action&formName=commtypeform" flush="true" /></td>
						</tr>
						<!-- 分页 结束 -->
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

	</body>
</html>
