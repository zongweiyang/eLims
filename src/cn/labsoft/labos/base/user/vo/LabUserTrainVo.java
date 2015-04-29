package cn.labsoft.labos.base.user.vo;

import java.util.List;
import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabUserTrainVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	@ExcelAnnotation(exportName = "标题")
	private String title;
	@ExcelAnnotation(exportName = "部门")
	private String orgId;
	@ExcelAnnotation(exportName = "部门")
	private String orgName;
	@ExcelAnnotation(exportName = "创建人")
	private String userId;
	@ExcelAnnotation(exportName = "创建人")
	private String userName;
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
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	@ExcelAnnotation(exportName = "被培训人")
	private String studentIds;
	@ExcelAnnotation(exportName = "被培训人")
	private String studentNames;
	@ExcelAnnotation(exportName = "消息通知")
	private String isMsg;//是否消息
	@ExcelAnnotation(exportName = "短信通知")
	private String isSms;//短信通知
	@ExcelAnnotation(exportName = "培训记录集合")
	private List<LabUserTrainRecordVo> recordList;
	public String getStudentIds() {
		return studentIds;
	}
	public void setStudentIds(String studentIds) {
		this.studentIds = studentIds;
	}
	public String getStudentNames() {
		return studentNames;
	}
	public void setStudentNames(String studentNames) {
		this.studentNames = studentNames;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public List<LabUserTrainRecordVo> getRecordList() {
		return recordList;
	}
	public void setRecordList(List<LabUserTrainRecordVo> recordList) {
		this.recordList = recordList;
	}
	public String getIsMsg() {
		return isMsg;
	}
	public void setIsMsg(String isMsg) {
		this.isMsg = isMsg;
	}
	public String getIsSms() {
		return isSms;
	}
	public void setIsSms(String isSms) {
		this.isSms = isSms;
	}
	
	
	
}
