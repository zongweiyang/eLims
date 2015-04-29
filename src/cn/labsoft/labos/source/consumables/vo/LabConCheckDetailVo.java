package cn.labsoft.labos.source.consumables.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabConCheckDetailVo extends BaseVo {

	@ExcelAnnotation(exportName = "ID")
	private String id;
	@ExcelAnnotation(exportName = "盘点main")
	private String labConCheckMainId;
	@ExcelAnnotation(exportName = "盘点主题")
	private String labConCheckMainName;
	@ExcelAnnotation(exportName = "耗材id")
	private String labConsumablesId;
	@ExcelAnnotation(exportName = "耗材名称")
	private String labConsumablesName;
	@ExcelAnnotation(exportName = "耗材规格")
	private String consumablesSize;
	@ExcelAnnotation(exportName = "耗材单位")
	private String consumablesUnit;

	@ExcelAnnotation(exportName = "耗材库存")
	private String thisAmount;

	@ExcelAnnotation(exportName = "上次盘点数量")
	private String lastAmount;
	@ExcelAnnotation(exportName = "本期入库数量")
	private String thisInAmount;
	@ExcelAnnotation(exportName = "本期出库数量")
	private String thisOutAmount;
	@ExcelAnnotation(exportName = "耗材数量")
	private String amount;
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	@ExcelAnnotation(exportName = "是否删除")
	private String isDel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	

	public String getIsDel() {
		return isDel;
	}

	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public String getLabConCheckMainId() {
		return labConCheckMainId;
	}

	public void setLabConCheckMainId(String labConCheckMainId) {
		this.labConCheckMainId = labConCheckMainId;
	}

	public String getLabConCheckMainName() {
		return labConCheckMainName;
	}

	public void setLabConCheckMainName(String labConCheckMainName) {
		this.labConCheckMainName = labConCheckMainName;
	}

	public String getLabConsumablesId() {
		return labConsumablesId;
	}

	public void setLabConsumablesId(String labConsumablesId) {
		this.labConsumablesId = labConsumablesId;
	}

	public String getLabConsumablesName() {
		return labConsumablesName;
	}

	public void setLabConsumablesName(String labConsumablesName) {
		this.labConsumablesName = labConsumablesName;
	}

	public String getConsumablesSize() {
		return consumablesSize;
	}

	public void setConsumablesSize(String consumablesSize) {
		this.consumablesSize = consumablesSize;
	}

	public String getConsumablesUnit() {
		return consumablesUnit;
	}

	public void setConsumablesUnit(String consumablesUnit) {
		this.consumablesUnit = consumablesUnit;
	}

	public String getLastAmount() {
		return lastAmount;
	}

	public void setLastAmount(String lastAmount) {
		this.lastAmount = lastAmount;
	}

	public String getThisInAmount() {
		return thisInAmount;
	}

	public void setThisInAmount(String thisInAmount) {
		this.thisInAmount = thisInAmount;
	}

	public String getThisOutAmount() {
		return thisOutAmount;
	}

	public void setThisOutAmount(String thisOutAmount) {
		this.thisOutAmount = thisOutAmount;
	}

	public String getThisAmount() {
		return thisAmount;
	}

	public void setThisAmount(String thisAmount) {
		this.thisAmount = thisAmount;
	}

}