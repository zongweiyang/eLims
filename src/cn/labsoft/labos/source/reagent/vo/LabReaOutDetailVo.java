package cn.labsoft.labos.source.reagent.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.source.reagent.entity.LabReaOutMain;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabReaOutDetailVo extends BaseVo {

	@ExcelAnnotation(exportName = "ID")
	private String id;
	@ExcelAnnotation(exportName = "出库单号")
	private LabReaOutMain main;
	@ExcelAnnotation(exportName = "试剂ID")
	private String reagentId;
	@ExcelAnnotation(exportName = "试剂名称")
	private String reagentName;
	@ExcelAnnotation(exportName = "试剂规格")
	private String size;
	@ExcelAnnotation(exportName = "试剂纯度")
	private String purity;
	@ExcelAnnotation(exportName = "试剂库存")
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

	public LabReaOutMain getMain() {
		return main;
	}

	public void setMain(LabReaOutMain main) {
		this.main = main;
	}

	public String getReagentId() {
		return reagentId;
	}

	public void setReagentId(String reagentId) {
		this.reagentId = reagentId;
	}

	public String getReagentName() {
		return reagentName;
	}

	public void setReagentName(String reagentName) {
		this.reagentName = reagentName;
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
