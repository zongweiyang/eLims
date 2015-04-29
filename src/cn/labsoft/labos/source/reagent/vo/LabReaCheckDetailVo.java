package cn.labsoft.labos.source.reagent.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabReaCheckDetailVo extends BaseVo {

	@ExcelAnnotation(exportName = "ID")
	private String id;
	@ExcelAnnotation(exportName = "盘点main")
	private String labReaCheckMainId;
	@ExcelAnnotation(exportName = "盘点主题")
	private String labReaCheckMainName;
	@ExcelAnnotation(exportName = "试剂id")
	private String labReagentId;
	@ExcelAnnotation(exportName = "试剂名称")
	private String labReagentName;
	@ExcelAnnotation(exportName = "试剂规格")
	private String reagentSize;
	@ExcelAnnotation(exportName = "试剂单位")
	private String reagentUnit;

	@ExcelAnnotation(exportName = "试剂库存")
	private String thisAmount;
	@ExcelAnnotation(exportName = "试剂纯度")
	private String reagentpurity;

	@ExcelAnnotation(exportName = "上次盘点数量")
	private String lastAmount;
	@ExcelAnnotation(exportName = "本期入库数量")
	private String thisInAmount;
	@ExcelAnnotation(exportName = "本期出库数量")
	private String thisOutAmount;
	@ExcelAnnotation(exportName = "试剂数量")
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

	public String getLabReaCheckMainId() {
		return labReaCheckMainId;
	}

	public void setLabReaCheckMainId(String labReaCheckMainId) {
		this.labReaCheckMainId = labReaCheckMainId;
	}

	public String getLabReaCheckMainName() {
		return labReaCheckMainName;
	}

	public void setLabReaCheckMainName(String labReaCheckMainName) {
		this.labReaCheckMainName = labReaCheckMainName;
	}

	public String getLabReagentId() {
		return labReagentId;
	}

	public void setLabReagentId(String labReagentId) {
		this.labReagentId = labReagentId;
	}

	public String getLabReagentName() {
		return labReagentName;
	}

	public void setLabReagentName(String labReagentName) {
		this.labReagentName = labReagentName;
	}

	public String getReagentSize() {
		return reagentSize;
	}

	public void setReagentSize(String reagentSize) {
		this.reagentSize = reagentSize;
	}

	public String getReagentUnit() {
		return reagentUnit;
	}

	public void setReagentUnit(String reagentUnit) {
		this.reagentUnit = reagentUnit;
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

	public String getReagentpurity() {
		return reagentpurity;
	}

	public void setReagentpurity(String reagentpurity) {
		this.reagentpurity = reagentpurity;
	}

}