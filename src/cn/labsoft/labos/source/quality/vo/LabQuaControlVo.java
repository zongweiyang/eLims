package cn.labsoft.labos.source.quality.vo;



import java.util.List;
import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

/**
 * 质量监督
 */

@SuppressWarnings("serial")
public class LabQuaControlVo  extends BaseVo{

	private String id;
	@ExcelAnnotation(exportName = "抽查编号")
    private String anaNo;
	@ExcelAnnotation(exportName = "地区")
	private String place;
	@ExcelAnnotation(exportName = "执行标准")
	private String standardId;
	private String standardName;
	@ExcelAnnotation(exportName = "仪器型号")
	private String appCode;
	private String appId;
	@ExcelAnnotation(exportName = "仪器编号")
	private String appNo;
	@ExcelAnnotation(exportName = "抽查时间")
	private String testTime;
	@ExcelAnnotation(exportName = "本次抽查综合评价")
	private String evaluation;
	@ExcelAnnotation(exportName = "抽查人")
	private String anaPeople;
	@ExcelAnnotation(exportName = "监督抽查人")
	private String conPeople;
	@ExcelAnnotation(exportName = "监督日期")
	private String conTime;
	@ExcelAnnotation(exportName = "单位")
	private String unitOrgId;
	private String unitOrgName;
	@ExcelAnnotation(exportName = "实验室")
	private String labOrgId;
	private String labOrgName;
	private String unitOrgSearch;
	private String labOrgSearch;
	private String anaNoSearch;
	private String appCodeSearch;
	private String accStatus;
	private List<LabQuaControlDetailVo> labQuaControlDetailVoList;
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getAnaNo() {
		return anaNo;
	}
	public void setAnaNo(String anaNo) {
		this.anaNo = anaNo;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
	}
	public String getStandardId() {
		return standardId;
	}
	public void setStandardId(String standardId) {
		this.standardId = standardId;
	}
	public String getStandardName() {
		return standardName;
	}
	public void setStandardName(String standardName) {
		this.standardName = standardName;
	}
	public String getAppCode() {
		return appCode;
	}
	public void setAppCode(String appCode) {
		this.appCode = appCode;
	}
	public String getAppId() {
		return appId;
	}
	public void setAppId(String appId) {
		this.appId = appId;
	}
	public String getAppNo() {
		return appNo;
	}
	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}
	public String getTestTime() {
		return testTime;
	}
	public void setTestTime(String testTime) {
		this.testTime = testTime;
	}
	public String getEvaluation() {
		return evaluation;
	}
	public void setEvaluation(String evaluation) {
		this.evaluation = evaluation;
	}
	public String getAnaPeople() {
		return anaPeople;
	}
	public void setAnaPeople(String anaPeople) {
		this.anaPeople = anaPeople;
	}
	public String getConPeople() {
		return conPeople;
	}
	public void setConPeople(String conPeople) {
		this.conPeople = conPeople;
	}
	public String getConTime() {
		return conTime;
	}
	public void setConTime(String conTime) {
		this.conTime = conTime;
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
	public String getUnitOrgSearch() {
		return unitOrgSearch;
	}
	public void setUnitOrgSearch(String unitOrgSearch) {
		this.unitOrgSearch = unitOrgSearch;
	}
	public String getLabOrgSearch() {
		return labOrgSearch;
	}
	public void setLabOrgSearch(String labOrgSearch) {
		this.labOrgSearch = labOrgSearch;
	}
	public String getAnaNoSearch() {
		return anaNoSearch;
	}
	public void setAnaNoSearch(String anaNoSearch) {
		this.anaNoSearch = anaNoSearch;
	}
	public String getAppCodeSearch() {
		return appCodeSearch;
	}
	public void setAppCodeSearch(String appCodeSearch) {
		this.appCodeSearch = appCodeSearch;
	}
	public List<LabQuaControlDetailVo> getLabQuaControlDetailVoList() {
		return labQuaControlDetailVoList;
	}
	public void setLabQuaControlDetailVoList(
			List<LabQuaControlDetailVo> labQuaControlDetailVoList) {
		this.labQuaControlDetailVoList = labQuaControlDetailVoList;
	}
	public String getAccStatus() {
		return accStatus;
	}
	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}
	
}
