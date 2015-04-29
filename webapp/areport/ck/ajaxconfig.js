/*调用后台ajax*/
function ajaxConfig(_v) {
	var url = {
		/*获取数据层url*/
		getData : '/report/labReportTag/ajaxLabReportTag4List.action',
		/*保存模板url*/
		saveModule : '/report/labReport/updateReportModel.action',
		/*保存业务带内容的模板*/
		saveReport4bus : '/report/labReport/updateLabReport4Bus.action',
		/*获取表格数据的所有信息*/
		getTableInfo : '/report/labReportTag/ajaxLabReportTag4Table.action'
	}
	return eval('url.'+_v);
}