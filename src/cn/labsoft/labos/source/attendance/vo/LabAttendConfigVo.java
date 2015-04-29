package cn.labsoft.labos.source.attendance.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabAttendConfigVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	@ExcelAnnotation(exportName = "工作日")
	private String workDay;
	@ExcelAnnotation(exportName = "上班时间")
	private String startTime;
	@ExcelAnnotation(exportName = "下班时间")
	private String endTime;
	@ExcelAnnotation(exportName = "开始执行日期")
	private String startDay;
	@ExcelAnnotation(exportName = "结束执行日期")
	private String endDay;
	@ExcelAnnotation(exportName = "标题")
	private String title;
	@ExcelAnnotation(exportName = "备注")
	private String remark;

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getWorkDay() {
		return this.workDay;
	}

	public void setWorkDay(String workDay) {
		this.workDay = workDay;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getStartDay() {
		return this.startDay;
	}

	public void setStartDay(String startDay) {
		this.startDay = startDay;
	}

	public String getEndDay() {
		return this.endDay;
	}

	public void setEndDay(String endDay) {
		this.endDay = endDay;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
