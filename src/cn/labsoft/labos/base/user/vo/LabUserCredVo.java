package cn.labsoft.labos.base.user.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabUserCredVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	@ExcelAnnotation(exportName = "名称")
	private String name;
	@ExcelAnnotation(exportName = "编号")
	private String code;
	@ExcelAnnotation(exportName = "起始日期")
	private String startDate;
	@ExcelAnnotation(exportName = "结束日期")
	private String endDate;
	@ExcelAnnotation(exportName = "用户Id")
	private String userId;
	@ExcelAnnotation(exportName = "用户名称")
	private String userName;
	@ExcelAnnotation(exportName = "单位")
	private String unit;
	@ExcelAnnotation(exportName = "类型")
	private String type;
	@ExcelAnnotation(exportName = "备注")
	private String remark;

	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	@Override
	public String getStartDate() {
		return this.startDate;
	}

	@Override
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	@Override
	public String getEndDate() {
		return this.endDate;
	}

	@Override
	public void setEndDate(String endDate) {
		this.endDate = endDate;
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

	public String getUnit() {
		return this.unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

}
