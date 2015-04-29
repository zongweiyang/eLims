package cn.labsoft.labos.business.science.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabSciFundsVo extends BaseVo{
	private static final long serialVersionUID = 1L;
	@ExcelAnnotation(exportName = "项目ID")
	private String labSciScienceId;
	@ExcelAnnotation(exportName = "项目名称")
	private String labSciScienceName;
	@ExcelAnnotation(exportName = "项目编码")
	private String sciCode;
	@ExcelAnnotation(exportName = "项目负责人")
	private String sciMastor;

	
	@ExcelAnnotation(exportName = "id")
	private String id;
	@ExcelAnnotation(exportName = "资金名称")
	private String name;//资金名称
	@ExcelAnnotation(exportName = "费用数量")
	private double money;//费用数量
	@ExcelAnnotation(exportName = "原来费用数量")
	private double oldMoney;//原来费用数量
	@ExcelAnnotation(exportName = "使用人员")
	private String user;//使用人员
	@ExcelAnnotation(exportName = "使用时间")
	private String useDate;//使用时间
	@ExcelAnnotation(exportName = "类型")
	private String type;//类型 ：收入为0 支出为1  预算为2
	@ExcelAnnotation(exportName = "备注")
	private String remark;//备注
	@ExcelAnnotation(exportName = "是否删除")
	private String isDel;//备注
	
	@Override
	public String getIsDel() {
		return isDel;
	}
	@Override
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public double getMoney() {
		return money;
	}
	public void setMoney(double money) {
		this.money = money;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getUseDate() {
		return useDate;
	}
	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getSciCode() {
		return sciCode;
	}
	public void setSciCode(String sciCode) {
		this.sciCode = sciCode;
	}
	public String getSciMastor() {
		return sciMastor;
	}
	public void setSciMastor(String sciMastor) {
		this.sciMastor = sciMastor;
	}
	public double getOldMoney() {
		return oldMoney;
	}
	public void setOldMoney(double oldMoney) {
		this.oldMoney = oldMoney;
	}
	
}
