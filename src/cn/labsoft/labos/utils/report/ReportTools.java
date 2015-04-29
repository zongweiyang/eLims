package cn.labsoft.labos.utils.report;

import java.util.List;

public class ReportTools {
	/**
	 * 
	 * TODO
	 * 
	 * @param reportDataList
	 *            封装了报表数据的视图对象
	 * @return 返回类型 String
	 */
	public static String makeReport(List<ReportData> reportDataList) {
		StringBuffer result = new StringBuffer();
		if (reportDataList == null || reportDataList.size() == 0)
			return result.toString();
		for (ReportData reportData : reportDataList) {
			result.append("{");
			result.append("name: '" + reportData.getName() + "', ");
			result.append(makeData(reportData.getData()));
			result.append("}, ");
		}
		result.deleteCharAt(result.lastIndexOf(","));
		return result.toString();
	}

	private static String makeData(List<String> dataList) {
		StringBuffer result = new StringBuffer();
		if (null == dataList || dataList.size() == 0)
			return result.toString();
		result.append("data: [");
		for (String data : dataList) {
			result.append(data + ",");
		}
		result.deleteCharAt(result.lastIndexOf(","));
		result.append("]");
		return result.toString();
	}

	public static String makeReportForPie(List<ReportData> reportDataList) {
		StringBuffer result = new StringBuffer();
		if (reportDataList == null || reportDataList.size() == 0)
			return result.toString();
		for (ReportData reportData : reportDataList) {
			result.append("['" + reportData.getName() + "',");
			result.append(reportData.getData().get(0) + "],");
		}
		result.deleteCharAt(result.lastIndexOf(","));
		return result.toString();
	}

	// public static void main(String[] args){
	// List<Double> dataList = new ArrayList<Double>();
	// dataList.add(111d);
	// ("11111111"+makeData(dataList));
	// }
}
