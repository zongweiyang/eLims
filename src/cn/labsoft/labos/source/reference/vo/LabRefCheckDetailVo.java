package cn.labsoft.labos.source.reference.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabRefCheckDetailVo extends BaseVo {

	@ExcelAnnotation(exportName = "ID")
	private String id;
	@ExcelAnnotation(exportName = "盘点main")
	private String labRefCheckMainId;
	@ExcelAnnotation(exportName = "盘点主题")
	private String labRefCheckMainName;
	@ExcelAnnotation(exportName = "标准品id")
	private String labReferenceId;
	@ExcelAnnotation(exportName = "标准品名称")
	private String labReferenceName;
	@ExcelAnnotation(exportName = "标准品规格")
	private String referenceSize;
	@ExcelAnnotation(exportName = "标准品单位")
	private String referenceUnit;

	@ExcelAnnotation(exportName = "标准品库存")
	private String thisAmount;
	@ExcelAnnotation(exportName = "标准品纯度")
	private String referencepurity;

	@ExcelAnnotation(exportName = "上次盘点数量")
	private String lastAmount;
	@ExcelAnnotation(exportName = "本期入库数量")
	private String thisInAmount;
	@ExcelAnnotation(exportName = "本期出库数量")
	private String thisOutAmount;
	@ExcelAnnotation(exportName = "标准品数量")
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

	public String getLabRefCheckMainId() {
		return labRefCheckMainId;
	}

	public void setLabRefCheckMainId(String labRefCheckMainId) {
		this.labRefCheckMainId = labRefCheckMainId;
	}

	public String getLabRefCheckMainName() {
		return labRefCheckMainName;
	}

	public void setLabRefCheckMainName(String labRefCheckMainName) {
		this.labRefCheckMainName = labRefCheckMainName;
	}

	public String getLabReferenceId() {
		return labReferenceId;
	}

	public void setLabReferenceId(String labReferenceId) {
		this.labReferenceId = labReferenceId;
	}

	public String getLabReferenceName() {
		return labReferenceName;
	}

	public void setLabReferenceName(String labReferenceName) {
		this.labReferenceName = labReferenceName;
	}

	public String getReferenceSize() {
		return referenceSize;
	}

	public void setReferenceSize(String referenceSize) {
		this.referenceSize = referenceSize;
	}

	public String getReferenceUnit() {
		return referenceUnit;
	}

	public void setReferenceUnit(String referenceUnit) {
		this.referenceUnit = referenceUnit;
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

	public String getReferencepurity() {
		return referencepurity;
	}

	public void setReferencepurity(String referencepurity) {
		this.referencepurity = referencepurity;
	}

}