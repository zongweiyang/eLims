package cn.labsoft.labos.source.consumables.vo;

import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

/**
 * @title 耗材
 * @author Bill
 */
public class LabConsumablesVo extends BaseVo {

	@ExcelAnnotation(exportName = "ID")
	private String id;
	@ExcelAnnotation(exportName = "编号")
	private String code;
	@ExcelAnnotation(exportName = "耗材类型ID")
	private String consumablesTypeId;
	@ExcelAnnotation(exportName = "耗材类型名称")
	private String consumablesTypeName;
	@ExcelAnnotation(exportName = "耗材名称")
	private String name;
	@ExcelAnnotation(exportName = "耗材单位")
	private String unit;
	@ExcelAnnotation(exportName = "规格")
	private String size;
	@ExcelAnnotation(exportName = "有效期")
	private int safeDate;
	@ExcelAnnotation(exportName = "危险等级")
	private String dangerSize;
	@ExcelAnnotation(exportName = "保管科室")
	private String saveOrg;
	@ExcelAnnotation(exportName = "保管科室名称")
	private String saveOrgName;
	@ExcelAnnotation(exportName = "保管人")
	private String saveUser;
	@ExcelAnnotation(exportName = "警戒数量")
	private Double safeAmount;
	@ExcelAnnotation(exportName = "实际数量")
	private Double amount;

	@ExcelAnnotation(exportName = "备注")
	private String remark;
	@ExcelAnnotation(exportName = "是否删除")
	private String isDel;
	@ExcelAnnotation(exportName = "最小库存")
	private String maxSize;
	@ExcelAnnotation(exportName = "最大库存")
	private String minSize;

	private String ext01;
	private String isSafe;

	private List<LabConsumablesVo> labConsumablesList;
	private String specifications;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public List<LabConsumablesVo> getLabConsumablesList() {
		return labConsumablesList;
	}

	public void setLabConsumablesList(List<LabConsumablesVo> labConsumablesList) {
		this.labConsumablesList = labConsumablesList;
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

	public String getUnit() {
		return unit;
	}

	public String getSaveOrgName() {
		return saveOrgName;
	}

	public void setSaveOrgName(String saveOrgName) {
		this.saveOrgName = saveOrgName;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public int getSafeDate() {
		return safeDate;
	}

	public void setSafeDate(int safeDate) {
		this.safeDate = safeDate;
	}

	public Double getSafeAmount() {
		return safeAmount;
	}

	public void setSafeAmount(Double safeAmount) {
		this.safeAmount = safeAmount;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	@Override
	public String getIsDel() {
		return isDel;
	}

	@Override
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getConsumablesTypeId() {
		return consumablesTypeId;
	}

	public void setConsumablesTypeId(String consumablesTypeId) {
		this.consumablesTypeId = consumablesTypeId;
	}

	public String getConsumablesTypeName() {
		return consumablesTypeName;
	}

	public void setConsumablesTypeName(String consumablesTypeName) {
		this.consumablesTypeName = consumablesTypeName;
	}

	public String getIsSafe() {
		return isSafe;
	}

	public void setIsSafe(String isSafe) {
		this.isSafe = isSafe;
	}

	public String getDangerSize() {
		return dangerSize;
	}

	public void setDangerSize(String dangerSize) {
		this.dangerSize = dangerSize;
	}

	public String getSaveOrg() {
		return saveOrg;
	}

	public void setSaveOrg(String saveOrg) {
		this.saveOrg = saveOrg;
	}

	public String getSaveUser() {
		return saveUser;
	}

	public void setSaveUser(String saveUser) {
		this.saveUser = saveUser;
	}

	public String getExt01() {
		return ext01;
	}

	public void setExt01(String ext01) {
		this.ext01 = ext01;
	}

	public String getMaxSize() {
		return maxSize;
	}

	public void setMaxSize(String maxSize) {
		this.maxSize = maxSize;
	}

	public String getMinSize() {
		return minSize;
	}

	public void setMinSize(String minSize) {
		this.minSize = minSize;
	}

}
