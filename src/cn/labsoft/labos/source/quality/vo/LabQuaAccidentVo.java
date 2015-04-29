package cn.labsoft.labos.source.quality.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabQuaAccidentVo  extends BaseVo{
	
	private String id;
	private String accUnitId;
	@ExcelAnnotation(exportName = "事故单位")
    private String accUnit;
	@ExcelAnnotation(exportName = "检验事故原因、经过、责任人")
	private String accDecs;
	@ExcelAnnotation(exportName = "事故原因分析以及处理意见")
	private String accAna;
	@ExcelAnnotation(exportName = "事故处理结果")
	private String procOpin;
	@ExcelAnnotation(exportName = "室负责人")
	private String repPeople;
	@ExcelAnnotation(exportName = "质量负责人")
	private String chePeople;
	@ExcelAnnotation(exportName = "中心主任")
	private String resPeople;
	@ExcelAnnotation(exportName = "日期(室负责人)")
	private String repTime;//日期(室负责人)
	@ExcelAnnotation(exportName = "日期(质量负责人)")
	private String cheTime;//日期(质量负责人)
	@ExcelAnnotation(exportName = "日期(中心主任)")
	private String procTime;//日期(中心主任)
	@ExcelAnnotation(exportName = "事故类别")
	private String accType;
	@ExcelAnnotation(exportName = "事故发生时间")
	private String accTime;
	@ExcelAnnotation(exportName = "事故负责人")
	private String accPeople;
	private String accStatus;
	private String unitOrgId;
	private String unitOrgName;
	private String unitOrgSearch;
	private String accUnitSearch;
	private String repPeopleSearch;
	private String otherId;
	private String otherName;
	private String other;
	
	public String getOtherId() {
		return otherId;
	}
	public void setOtherId(String otherId) {
		this.otherId = otherId;
	}
	public String getOtherName() {
		return otherName;
	}
	public void setOtherName(String otherName) {
		this.otherName = otherName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAccDecs() {
		return accDecs;
	}
	public void setAccDecs(String accDecs) {
		this.accDecs = accDecs;
	}
	public String getAccAna() {
		return accAna;
	}
	public void setAccAna(String accAna) {
		this.accAna = accAna;
	}
	public String getProcOpin() {
		return procOpin;
	}
	public void setProcOpin(String procOpin) {
		this.procOpin = procOpin;
	}
	public String getRepPeople() {
		return repPeople;
	}
	public void setRepPeople(String repPeople) {
		this.repPeople = repPeople;
	}
	public String getChePeople() {
		return chePeople;
	}
	public void setChePeople(String chePeople) {
		this.chePeople = chePeople;
	}
	public String getResPeople() {
		return resPeople;
	}
	public void setResPeople(String resPeople) {
		this.resPeople = resPeople;
	}
	
	public String getRepTime() {
		return repTime;
	}
	public void setRepTime(String repTime) {
		this.repTime = repTime;
	}
	public String getCheTime() {
		return cheTime;
	}
	public void setCheTime(String cheTime) {
		this.cheTime = cheTime;
	}
	public String getProcTime() {
		return procTime;
	}
	public void setProcTime(String procTime) {
		this.procTime = procTime;
	}
	public String getRepPeopleSearch() {
		return repPeopleSearch;
	}
	public void setRepPeopleSearch(String repPeopleSearch) {
		this.repPeopleSearch = repPeopleSearch;
	}
	public String getAccUnit() {
		return accUnit;
	}
	public void setAccUnit(String accUnit) {
		this.accUnit = accUnit;
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
	public String getUnitOrgSearch() {
		return unitOrgSearch;
	}
	public void setUnitOrgSearch(String unitOrgSearch) {
		this.unitOrgSearch = unitOrgSearch;
	}
	public String getAccUnitSearch() {
		return accUnitSearch;
	}
	public void setAccUnitSearch(String accUnitSearch) {
		this.accUnitSearch = accUnitSearch;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public String getAccTime() {
		return accTime;
	}
	public void setAccTime(String accTime) {
		this.accTime = accTime;
	}
	public String getAccPeople() {
		return accPeople;
	}
	public void setAccPeople(String accPeople) {
		this.accPeople = accPeople;
	}
	public String getAccStatus() {
		return accStatus;
	}
	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}
	public String getAccUnitId() {
		return accUnitId;
	}
	public void setAccUnitId(String accUnitId) {
		this.accUnitId = accUnitId;
	}
	public String getOther() {
		return other;
	}
	public void setOther(String other) {
		this.other = other;
	}
	
}
