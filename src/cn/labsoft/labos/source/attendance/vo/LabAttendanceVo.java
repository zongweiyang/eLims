package cn.labsoft.labos.source.attendance.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabAttendanceVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	@ExcelAnnotation(exportName = "部门")
	private String orgId;
	@ExcelAnnotation(exportName = "部门")
	private String orgName;
	@ExcelAnnotation(exportName = "用户Id")
	private String userId;
	@ExcelAnnotation(exportName = "姓名")
	private String userName;
	@ExcelAnnotation(exportName = "签到时间")
	private String startTime;
	@ExcelAnnotation(exportName = "是否迟到")
	private String startFlag;
	@ExcelAnnotation(exportName = "签退时间")
	private String endTime;
	@ExcelAnnotation(exportName = "是否早退")
	private String endFlag;
	@ExcelAnnotation(exportName = "是否补卡")
	private String afterFlag;
	@ExcelAnnotation(exportName = "是否正常")
	private String isOk;
	@ExcelAnnotation(exportName = "是否缺卡")
	private String noWork;
	@ExcelAnnotation(exportName = "加班")
	private String isGood;
	@ExcelAnnotation(exportName = "星期")
	private int week;
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	@ExcelAnnotation(exportName = "日")
	private int day;
	@ExcelAnnotation(exportName = "打卡日期")
	private String workDate;
	@ExcelAnnotation(exportName = "打卡月份")
	private String workMonth; 
	@ExcelAnnotation(exportName = "打卡年份")
	private String workYear; 
	private long endMin; //早退分钟数
	private long startMin; //迟到分钟数
	@ExcelAnnotation(exportName = "正常时间")
	private String standTimeAM; 
	@ExcelAnnotation(exportName = "正常时间")
	private String standTimePM; 
	public String getWorkDate() {
		return workDate;
	}
	public void setWorkDate(String workDate) {
		this.workDate = workDate;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getOrgId() {
		return this.orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getOrgName() {
		return this.orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getUserId() {
		return this.userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStartTime() {
		return this.startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getStartFlag() {
		return this.startFlag;
	}

	public void setStartFlag(String startFlag) {
		this.startFlag = startFlag;
	}

	public String getEndTime() {
		return this.endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getEndFlag() {
		return this.endFlag;
	}

	public void setEndFlag(String endFlag) {
		this.endFlag = endFlag;
	}

	public String getAfterFlag() {
		return this.afterFlag;
	}

	public void setAfterFlag(String afterFlag) {
		this.afterFlag = afterFlag;
	}

	public String getIsOk() {
		return this.isOk;
	}

	public void setIsOk(String isOk) {
		this.isOk = isOk;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getWorkMonth() {
		return workMonth;
	}
	public void setWorkMonth(String workMonth) {
		this.workMonth = workMonth;
	}
	public String getWorkYear() {
		return workYear;
	}
	public void setWorkYear(String workYear) {
		this.workYear = workYear;
	}
	public String getNoWork() {
		return noWork;
	}
	public void setNoWork(String noWork) {
		this.noWork = noWork;
	}
	public String getIsGood() {
		return isGood;
	}
	public void setIsGood(String isGood) {
		this.isGood = isGood;
	}
	public int getWeek() {
		return week;
	}
	public void setWeek(int week) {
		this.week = week;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}
	public long getEndMin() {
		return endMin;
	}
	public void setEndMin(long endMin) {
		this.endMin = endMin;
	}
	public long getStartMin() {
		return startMin;
	}
	public void setStartMin(long startMin) {
		this.startMin = startMin;
	}
	public String getStandTimeAM() {
		return standTimeAM;
	}
	public void setStandTimeAM(String standTimeAM) {
		this.standTimeAM = standTimeAM;
	}
	public String getStandTimePM() {
		return standTimePM;
	}
	public void setStandTimePM(String standTimePM) {
		this.standTimePM = standTimePM;
	}
	
}
