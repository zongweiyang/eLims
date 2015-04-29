package cn.labsoft.labos.source.quality.vo;


import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;



public class LabQuaCusVisitVo extends BaseVo {

	
	private String id;
	@ExcelAnnotation(exportName = "登记编号")
	private String no;
	private String cusUnitId;
	@ExcelAnnotation(exportName = "客户单位")
    private String cusUnit;
	@ExcelAnnotation(exportName = "姓名")
	private String name;
	@ExcelAnnotation(exportName = "客户意见及建议")
	private String suggestion;
	@ExcelAnnotation(exportName = "意见处理")
	private String handle;
	@ExcelAnnotation(exportName = "客户电话")
	private String phone;
	@ExcelAnnotation(exportName = "回访时间")
	private String visitTime;
	private String cusUnitSearch;
	private String nameSearch;
	@ExcelAnnotation(exportName = "单位")
	private String unitOrgId;
	private String unitOrgName;
	private String unitOrgIdSearch;
	@ExcelAnnotation(exportName = "实验室")
	private String labOrgId;
	private String labOrgName;
	private String labOrgIdSearch;
	private String accStatus;
	
	public String getAccStatus() {
		return accStatus;
	}
	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public String getHandle() {
		return handle;
	}
	public void setHandle(String handle) {
		this.handle = handle;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getVisitTime() {
		return visitTime;
	}
	public void setVisitTime(String visitTime) {
		this.visitTime = visitTime;
	}
	public String getNameSearch() {
		return nameSearch;
	}
	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}
	public String getCusUnit() {
		return cusUnit;
	}
	public void setCusUnit(String cusUnit) {
		this.cusUnit = cusUnit;
	}
	public String getUnitOrgId() {
		return unitOrgId;
	}
	public void setUnitOrgId(String unitOrgId) {
		this.unitOrgId = unitOrgId;
	}
	public String getUnitOrgName() {
		return unitOrgName;
	}
	public void setUnitOrgName(String unitOrgName) {
		this.unitOrgName = unitOrgName;
	}
	public String getUnitOrgIdSearch() {
		return unitOrgIdSearch;
	}
	public void setUnitOrgIdSearch(String unitOrgIdSearch) {
		this.unitOrgIdSearch = unitOrgIdSearch;
	}
	public String getLabOrgId() {
		return labOrgId;
	}
	public void setLabOrgId(String labOrgId) {
		this.labOrgId = labOrgId;
	}
	public String getLabOrgName() {
		return labOrgName;
	}
	public void setLabOrgName(String labOrgName) {
		this.labOrgName = labOrgName;
	}
	public String getLabOrgIdSearch() {
		return labOrgIdSearch;
	}
	public void setLabOrgIdSearch(String labOrgIdSearch) {
		this.labOrgIdSearch = labOrgIdSearch;
	}
	public String getCusUnitSearch() {
		return cusUnitSearch;
	}
	public void setCusUnitSearch(String cusUnitSearch) {
		this.cusUnitSearch = cusUnitSearch;
	}
	public String getCusUnitId() {
		return cusUnitId;
	}
	public void setCusUnitId(String cusUnitId) {
		this.cusUnitId = cusUnitId;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	
}