package cn.labsoft.labos.business.science.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabSciProcessVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	@ExcelAnnotation(exportName = "名称")
	private String name;
	@ExcelAnnotation(exportName = "类型")
	private String type;
	@ExcelAnnotation(exportName = "填写人")
	private String writeUser;
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	@ExcelAnnotation(exportName = "编号")
	private String no;
	@ExcelAnnotation(exportName = "内容")
	private String contents;
	@ExcelAnnotation(exportName = "开始时间")
	private String startTime;
	@ExcelAnnotation(exportName = "结束时间")
	private String endTime;
	@ExcelAnnotation(exportName = "原件")
	private String original;
	@ExcelAnnotation(exportName = "复印件")
	private String hardCopy ;
	private String labSciScienceId;
	private String labSciScienceName;
	
	private String sampRegisterId;//任务Id
	private String sampRegisterNum;
	private String sampRegisterSampNo;
	private String sampRegisterNo;   //任务编号

	public String getSampRegisterNo() {
		return sampRegisterNo;
	}
	public void setSampRegisterNo(String sampRegisterNo) {
		this.sampRegisterNo = sampRegisterNo;
	}
	public String getSampRegisterId() {
		return sampRegisterId;
	}
	public void setSampRegisterId(String sampRegisterId) {
		this.sampRegisterId = sampRegisterId;
	}
	public String getSampRegisterNum() {
		return sampRegisterNum;
	}
	public void setSampRegisterNum(String sampRegisterNum) {
		this.sampRegisterNum = sampRegisterNum;
	}
	public String getSampRegisterSampNo() {
		return sampRegisterSampNo;
	}
	public void setSampRegisterSampNo(String sampRegisterSampNo) {
		this.sampRegisterSampNo = sampRegisterSampNo;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getNo() {
		return this.no;
	}

	public void setNo(String no) {
		this.no = no;
	}

	public String getContents() {
		return this.contents;
	}

	public void setContents(String contents) {
		this.contents = contents;
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

	public String getOriginal() {
		return this.original;
	}

	public void setOriginal(String original) {
		this.original = original;
	}

	public String getHardCopy () {
		return this.hardCopy ;
	}

	public void setHardCopy (String hardCopy ) {
		this.hardCopy  = hardCopy ;
	}
	public String getLabSciScienceId() {
		return labSciScienceId;
	}
	public void setLabSciScienceId(String labSciScienceId) {
		this.labSciScienceId = labSciScienceId;
	}
	public String getLabSciScienceName() {
		return labSciScienceName;
	}
	public void setLabSciScienceName(String labSciScienceName) {
		this.labSciScienceName = labSciScienceName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getWriteUser() {
		return writeUser;
	}
	public void setWriteUser(String writeUser) {
		this.writeUser = writeUser;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

}
