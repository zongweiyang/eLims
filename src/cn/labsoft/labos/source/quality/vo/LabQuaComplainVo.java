package cn.labsoft.labos.source.quality.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;


/**
 * 投诉处理表
 */

@SuppressWarnings("serial")
public class LabQuaComplainVo  extends BaseVo{

	private String id;
	@ExcelAnnotation(exportName = "投诉编号")
	private String no;
	private String unitId;
	@ExcelAnnotation(exportName = "投诉单位")
	private String unit;
	@ExcelAnnotation(exportName = "投诉人")
	private String name;
	@ExcelAnnotation(exportName = "投诉日期")
	private String appTime;
	@ExcelAnnotation(exportName = "投诉内容")
	private String content;
	@ExcelAnnotation(exportName = "处理结果")
	private String result;
	@ExcelAnnotation(exportName = "样品名称")
	private String sampName;
	@ExcelAnnotation(exportName = "处理措施")
	private String measures;
	@ExcelAnnotation(exportName = "实验室负责人")
	private String skillPerson;
	@ExcelAnnotation(exportName = "质量负责人")
	private String qualityPerson;
	@ExcelAnnotation(exportName = "投诉单位(人)意见")
	private String suggestion;
	@ExcelAnnotation(exportName = "日期(实验室负责人)")
	private String skillTime;
	@ExcelAnnotation(exportName = "日期(质量负责人)")
	private String qualityTime;
	@ExcelAnnotation(exportName = "检测项目")	
	private String itemId;
	private String itemName;
	private String nameSearch;
	private String unitSearch;
	private String accStatus;
	
	
	public String getAccStatus() {
		return accStatus;
	}
	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUnit() {
		return unit;
	}
	public void setUnit(String unit) {
		this.unit = unit;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAppTime() {
		return appTime;
	}
	public void setAppTime(String appTime) {
		this.appTime = appTime;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getSampName() {
		return sampName;
	}
	public void setSampName(String sampName) {
		this.sampName = sampName;
	}
	public String getMeasures() {
		return measures;
	}
	public void setMeasures(String measures) {
		this.measures = measures;
	}
	public String getSkillPerson() {
		return skillPerson;
	}
	public void setSkillPerson(String skillPerson) {
		this.skillPerson = skillPerson;
	}
	public String getQualityPerson() {
		return qualityPerson;
	}
	public void setQualityPerson(String qualityPerson) {
		this.qualityPerson = qualityPerson;
	}
	public String getSuggestion() {
		return suggestion;
	}
	public void setSuggestion(String suggestion) {
		this.suggestion = suggestion;
	}
	public String getSkillTime() {
		return skillTime;
	}
	public void setSkillTime(String skillTime) {
		this.skillTime = skillTime;
	}
	public String getQualityTime() {
		return qualityTime;
	}
	public void setQualityTime(String qualityTime) {
		this.qualityTime = qualityTime;
	}
	public String getNameSearch() {
		return nameSearch;
	}
	public void setNameSearch(String nameSearch) {
		this.nameSearch = nameSearch;
	}
	public String getUnitSearch() {
		return unitSearch;
	}
	public void setUnitSearch(String unitSearch) {
		this.unitSearch = unitSearch;
	}
	public String getNo() {
		return no;
	}
	public void setNo(String no) {
		this.no = no;
	}
	public String getUnitId() {
		return unitId;
	}
	public void setUnitId(String unitId) {
		this.unitId = unitId;
	}
	
	
}
