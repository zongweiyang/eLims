package cn.labsoft.labos.source.attendance.entity;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
import javax.persistence.Transient;

import javax.persistence.Entity;
/**
 * 用户考勤配置持久化对象
 * @author Quinn
 */
@Entity
public class LabAttendConfig extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private String workDay;//工作日
	private String startTime;//上班时间
	private String endTime;//下班时间
	private String startDay;//开始执行日期
	private String endDay;//结束执行日期
	private String title;//标题
	private String remark;//备注

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

	@Transient
	@Override
	public String getModelName() {
		return "考勤管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "考勤配置";
	}
}
