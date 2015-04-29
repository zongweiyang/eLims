package cn.labsoft.labos.source.consumables.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.source.consumables.entity.LabConOutMain;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabConOutDetailVo extends BaseVo {

	@ExcelAnnotation(exportName = "ID")
	private String id;
	@ExcelAnnotation(exportName = "出库单号")
	private LabConOutMain main;
	@ExcelAnnotation(exportName = "耗材ID")
	private String consumablesId;
	@ExcelAnnotation(exportName = "耗材名称")
	private String consumablesName;
	@ExcelAnnotation(exportName = "耗材规格")
	private String size;
	@ExcelAnnotation(exportName = "耗材库存")
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public LabConOutMain getMain() {
		return main;
	}

	public void setMain(LabConOutMain main) {
		this.main = main;
	}

	public String getConsumablesId() {
		return consumablesId;
	}

	public void setConsumablesId(String consumablesId) {
		this.consumablesId = consumablesId;
	}

	public String getConsumablesName() {
		return consumablesName;
	}

	public void setConsumablesName(String consumablesName) {
		this.consumablesName = consumablesName;
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
