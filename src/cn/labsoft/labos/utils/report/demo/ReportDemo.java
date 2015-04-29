package cn.labsoft.labos.utils.report.demo;

import java.util.ArrayList;
import java.util.List;

import cn.labsoft.labos.framework.common.action.BaseAction;
import cn.labsoft.labos.utils.report.ReportConfigure;
import cn.labsoft.labos.utils.report.ReportConstants;
import cn.labsoft.labos.utils.report.ReportData;
import cn.labsoft.labos.utils.report.ReportTools;
import cn.labsoft.labos.utils.report.ReportVo;

public class ReportDemo extends BaseAction {

	private static final long serialVersionUID = 1L;

	private ReportVo lineVo;
	private ReportVo columnVo;
	private ReportVo pieVo;

	// private ICustRegisterService custRegisterService = (ICustRegisterService)
	// SystemInstance
	// .getInstance().getBean(ICustRegisterService.class);

	public String reportDemo() {
		lineVo = new ReportVo();
		columnVo = new ReportVo();
		pieVo = new ReportVo();
		ReportConfigure line = new ReportConfigure();
		ReportConfigure column = new ReportConfigure();
		ReportConfigure pie = new ReportConfigure();

		// -------------------------------------------------曲线图

		line.setTitle("海创亿邦客户来源分析"); // 设置分析图的主标题
		line.setSubTitle("曲线图"); // 设置分析图的副标题
		line.setType(ReportConstants.LINE); // 设置分析图的类型
		List<ReportData> lineData = new ArrayList<ReportData>();
		StringBuffer linexAxis = new StringBuffer();
		makeData(lineData, linexAxis); // 数据处理
		// 设置分析图的X轴
		line.setxAxis(linexAxis.toString());
		// 设置分析图的Y轴
		line.setyAxis("客户数(位)");
		lineVo.setReportConfigure(line);
		lineVo.setSeries(ReportTools.makeReport(lineData));

		// -------------------------------------------------柱状图

		column.setTitle("海创亿邦客户来源分析"); // 设置分析图的主标题
		column.setSubTitle("柱状图"); // 设置分析图的副标题
		column.setType(ReportConstants.COLUMN); // 设置分析图的类型
		List<ReportData> columnData = new ArrayList<ReportData>();
		StringBuffer columnxAxis = new StringBuffer();
		makeData(columnData, columnxAxis); // 数据处理
		// 设置分析图的X轴
		column.setxAxis(columnxAxis.toString());
		// 设置分析图的Y轴
		column.setyAxis("客户数(位)");
		columnVo.setReportConfigure(column);
		columnVo.setSeries(ReportTools.makeReport(columnData));

		// -------------------------------------------------饼图

		pie.setTitle("海创亿邦客户来源分析"); // 设置分析图的主标题
		pie.setSubTitle("饼图"); // 设置分析图的副标题
		pie.setType(ReportConstants.PIE); // 设置分析图的类型
		List<ReportData> pieData = new ArrayList<ReportData>();
		makeDataForPie(pieData);
		pieVo.setReportConfigure(pie);
		pieVo.setSeries(ReportTools.makeReportForPie(pieData));
		return SUCCESS;
	}

	@SuppressWarnings("unchecked")
	private void makeData(List<ReportData> lineData, StringBuffer xAxis) {
		// 数据处理部分
		//List<String> dateList = creatDateInMonth("2010", "04", 2, 13);
		ReportData reportData1 = new ReportData();
		ReportData reportData2 = new ReportData();
		ReportData reportData3 = new ReportData();
		ReportData reportData4 = new ReportData();
		ReportData reportData5 = new ReportData();
		reportData1.setName("营销员");
		reportData2.setName("上门客户");
		reportData3.setName("电话咨询");
		reportData4.setName("网络");
		reportData5.setName("其他");
		//List<String> reporList1 = new ArrayList<String>();
		//List<String> reporList2 = new ArrayList<String>();
		//List<String> reporList3 = new ArrayList<String>();
		//List<String> reporList4 = new ArrayList<String>();
		//List<String> reporList5 = new ArrayList<String>();
		// for(String date : dateList){
		// CustRegisterVo vo = new CustRegisterVo();
		// vo.setRegistetime(date);
		// List<Object[]> result =
		// custRegisterService.getCustRegisterReportData(vo);
		// if(null != result && result.size() > 0){
		// // 1-营销员 2-上门客户 3-电话咨询 4-网络 5-其他
		// Object[] objs = result.get(0);
		// if(null != objs[0]){
		// reporList1.add(objs[0].toString());
		// }
		// if(null != objs[1]){
		// reporList2.add(objs[1].toString());
		// }
		// if(null != objs[2]){
		// reporList3.add(objs[2].toString());
		// }
		// if(null != objs[3]){
		// reporList4.add(objs[3].toString());
		// }
		// if(null != objs[4]){
		// reporList5.add(objs[4].toString());
		// }
		// }
		// xAxis.append("'" + date + "',");
		// }
		// xAxis.deleteCharAt(xAxis.lastIndexOf(","));
		// reportData1.setData(reporList1);
		// reportData2.setData(reporList2);
		// reportData3.setData(reporList3);
		// reportData4.setData(reporList4);
		// reportData5.setData(reporList5);
		//		
		// lineData.add(reportData1);
		// lineData.add(reportData2);
		// lineData.add(reportData3);
		// lineData.add(reportData4);
		// lineData.add(reportData5);
	}

	private void makeDataForPie(List<ReportData> lineData) {
		ReportData reportData1 = new ReportData();
		ReportData reportData2 = new ReportData();
		ReportData reportData3 = new ReportData();
		ReportData reportData4 = new ReportData();
		ReportData reportData5 = new ReportData();
		reportData1.setName("营销员");
		reportData2.setName("上门客户");
		reportData3.setName("电话咨询");
		reportData4.setName("网络");
		reportData5.setName("其他");
		List<String> reporList1 = new ArrayList<String>();
		List<String> reporList2 = new ArrayList<String>();
		List<String> reporList3 = new ArrayList<String>();
		List<String> reporList4 = new ArrayList<String>();
		List<String> reporList5 = new ArrayList<String>();
		// CustRegisterVo vo = new CustRegisterVo();
		// List<Object[]> result =
		// custRegisterService.getCustRegisterReportData(vo);
		// if(null != result && result.size() > 0){
		// // 1-营销员 2-上门客户 3-电话咨询 4-网络 5-其他
		// Object[] objs = result.get(0);
		// if(null != objs[0]){
		// reporList1.add(objs[0].toString());
		// }
		// if(null != objs[1]){
		// reporList2.add(objs[1].toString());
		// }
		// if(null != objs[2]){
		// reporList3.add(objs[2].toString());
		// }
		// if(null != objs[3]){
		// reporList4.add(objs[3].toString());
		// }
		// if(null != objs[4]){
		// reporList5.add(objs[4].toString());
		// }
		// }
		reportData1.setData(reporList1);
		reportData2.setData(reporList2);
		reportData3.setData(reporList3);
		reportData4.setData(reporList4);
		reportData5.setData(reporList5);

		lineData.add(reportData1);
		lineData.add(reportData2);
		lineData.add(reportData3);
		lineData.add(reportData4);
		lineData.add(reportData5);
	}

//	private List<String> creatDateInYear(String year) {
//		List<String> result = new ArrayList<String>();
//		for (int i = 1; i <= 12; i++) {
//			String date = year + "-";
//			if (String.valueOf(i).length() < 2) {
//				date += "0" + i;
//			} else {
//				date += i;
//			}
//			result.add(date.toString());
//		}
//		return result;
//	}

//	private List<String> creatDateInMonth(String year, String month, int start,
//			int end) {
//		List<String> result = new ArrayList<String>();
//		for (int i = start; i <= end; i++) {
//			String date = year + "-" + month + "-";
//			if (String.valueOf(i).length() < 2) {
//				date += "0" + i;
//			} else {
//				date += i;
//			}
//			result.add(date.toString());
//		}
//		return result;
//	}

	public ReportVo getPieVo() {
		return pieVo;
	}

	public void setPieVo(ReportVo pieVo) {
		this.pieVo = pieVo;
	}

	public ReportVo getColumnVo() {
		return columnVo;
	}

	public void setColumnVo(ReportVo columnVo) {
		this.columnVo = columnVo;
	}

	public ReportVo getLineVo() {
		return lineVo;
	}

	public void setLineVo(ReportVo lineVo) {
		this.lineVo = lineVo;
	}

}
