<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN">
<html>
	<head>
		<%@ include file="/jsp/include/common.jsp"%>
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


	<body>
		<!-- 模板内容页面 开始 -->

		<!-- 通用标题 开始 用于显示一些单据的标题 -->
		<div class="commontitle">
			<h2>
				XXXXXXXXXXXXXXXX损益表
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
										文字或标题：
										<label>
								</td>
								<td>
									<label>
										<s:text name="selected"/>框
										<label>
											<select>
												<option>
													选项一
												</option>
												<option>
													选项二
												</option>
											</select>
								</td>
								<td>
									<label>
										每页显示
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
												条
												<label>
								</td>
								<td>
									<input type="text" value="" />
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick="submitvalueforlist('addComType.action');"><img height="20" width="20" src="${basePath}img/chakan.gif" /><b><s:text name="fun.query"/></b>
									</a>
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
										按钮类型演示：
										<label>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/bianji.gif" /><b>通用</b>
									</a>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/xinjian.gif" /><b>新建</b>
									</a>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/shanchu.gif" /><b>删除</b>
									</a>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/chakan.gif" /><b><s:text name="fun.query"/></b>
									</a>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/bianji.gif" /><b>修改</b>
									</a>
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
										按钮类型演示：
										<label>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/tijiao.gif" /><b><s:text name="post.commit"/></b>
									</a>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/tongyi.gif" /><b>同意</b>
									</a>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/fanhui.gif" /><b>返回列表</b>
									</a>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/filesave.gif" /><b><s:text name="lab.code.save"/></b>
									</a>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/filesavenew.gif" /><b>保存并新增</b>
									</a>
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
										按钮类型演示：
										<label>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/shuaxin.gif" /><b>刷新</b>
									</a>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/zhuanyi.gif" /><b>转移</b>
									</a>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/fuzhi.gif" /><b>复制</b>
									</a>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/shoufei.gif" /><b><s:text name="charge.fee"/></b>
									</a>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/dayin.gif" /><b><s:text name="print"/></b>
									</a>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/zhuanyi.gif" /><b>导出</b>
									</a>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/zhongzhi.gif" /><b>终止</b>
									</a>
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
										按钮类型演示：
										<label>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/youjian.gif" /><b>邮件</b>
									</a>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/xieyoujian.gif" /><b>写邮件</b>
									</a>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/xinjianyoujian.gif" /><b>新建邮件</b>
									</a>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/xinzengfujian.gif" /><b>新增附件</b>
									</a>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/shanchuyoujian.gif" /><b>删除邮件</b>
									</a>
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
										按钮类型演示：
										<label>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/youjianlianjie.gif" /><b>邮件链接</b>
									</a>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/fsyoujian.gif" /><b>发送邮件</b>
									</a>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/weiduyoujian.gif" /><b>未读消息</b>
									</a>
								</td>
								<td>
									<a id="BtnPreview" class="zPushBtn" href="#" onclick=""><img height="20" width="20" src="${basePath}img/yiduxinxi.gif" /><b>已读消息</b>
									</a>
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
						内容内容内容内容内容内容内容内容内容内容
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
						编码编码编码编码编码编码编码编码编码编码
					</td>
					<td class="c">
						修改&nbsp;|&nbsp;删除&nbsp;|&nbsp;代码维护
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
						编码编码编码编码编码编码编码编码编码编码
					</td>
					<td class="c">
						修改&nbsp;|&nbsp;删除&nbsp;|&nbsp;代码维护
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
							编号：
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
							类型：
						</label>
					</td>
					<td>
						<select>
							<option>
								选项一
							</option>
							<option>
								选项二
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
							编号：
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
						类型：
					</label>
				</td>
				<td>
					类型一
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

	</body>
</html>
