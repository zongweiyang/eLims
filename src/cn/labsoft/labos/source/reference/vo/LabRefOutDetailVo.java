package cn.labsoft.labos.source.reference.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.source.reference.entity.LabRefOutMain;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabRefOutDetailVo extends BaseVo {

	@ExcelAnnotation(exportName = "ID")
	private String id;
	@ExcelAnnotation(exportName = "出库单号")
	private LabRefOutMain main;
	@ExcelAnnotation(exportName = "标准品ID")
	private String referenceId;
	@ExcelAnnotation(exportName = "标准品名称")
	private String referenceName;
	@ExcelAnnotation(exportName = "标准品规格")
	private String size;
	@ExcelAnnotation(exportName = "标准品纯度")
	private String purity;
	@ExcelAnnotation(exportName = "标准品库存")
	private String num;
	@ExcelAnnotation(exportName = "出库数量")
	private Double amount;
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	@ExcelAnnotation(exportName = "是否删除")
	private String isDel;
	@ExcelAnnotation(exportName = "规格")
	private String specifications;

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getPurity() {
		return purity;
	}

	public void setPurity(String purity) {
		this.purity = purity;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LabRefOutMain getMain() {
		return main;
	}

	public void setMain(LabRefOutMain main) {
		this.main = main;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

	public String getReferenceName() {
		return referenceName;
	}

	public void setReferenceName(String referenceName) {
		this.referenceName = referenceName;
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

	public String getNum() {
		return num;
	}

	public void setNum(String num) {
		this.num = num;
	}
}
