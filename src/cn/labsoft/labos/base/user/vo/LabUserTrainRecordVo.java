package cn.labsoft.labos.base.user.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;
public class LabUserTrainRecordVo extends BaseVo {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -1908790757053324627L;

	private String id;
	@ExcelAnnotation(exportName = "部门")
	private String orgId;
	@ExcelAnnotation(exportName = "部门")
	private String orgName;
	@ExcelAnnotation(exportName = "讲师")
	private String teacher;
	@ExcelAnnotation(exportName = "培训岗位")
	private String station;
	@ExcelAnnotation(exportName = "培训时间")
	private String tdate;
	@ExcelAnnotation(exportName = "培训地点")
	private String address;
	@ExcelAnnotation(exportName = "培训内容")
	private String content;
	@ExcelAnnotation(exportName = "培训结果")
	private String result; 
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	@ExcelAnnotation(exportName = "培训计划")
	private String labUserTrainId; //培训计划
	@ExcelAnnotation(exportName = "标题")
	private String title;
	@ExcelAnnotation(exportName = "被培训人")
	private String labUserId; //被培训人
	@ExcelAnnotation(exportName = "被培训人")
	private String labUserName; //被培训人
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

	public String getTeacher() {
		return this.teacher;
	}

	public void setTeacher(String teacher) {
		this.teacher = teacher;
	}

	public String getStation() {
		return this.station;
	}

	public void setStation(String station) {
		this.station = station;
	}

	public String getTdate() {
		return this.tdate;
	}

	public void setTdate(String tdate) {
		this.tdate = tdate;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getLabUserTrainId() {
		return labUserTrainId;
	}
	public void setLabUserTrainId(String labUserTrainId) {
		this.labUserTrainId = labUserTrainId;
	}
	public String getLabUserId() {
		return labUserId;
	}
	public void setLabUserId(String labUserId) {
		this.labUserId = labUserId;
	}
	public String getLabUserName() {
		return labUserName;
	}
	public void setLabUserName(String labUserName) {
		this.labUserName = labUserName;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	
	
}
