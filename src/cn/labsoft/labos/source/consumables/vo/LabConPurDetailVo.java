package cn.labsoft.labos.source.consumables.vo;

import org.apache.commons.lang.builder.ToStringBuilder;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

/**
 * 
 * <strong>Title : LabConPurDetail </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Cconte on : Feb 22, 2014 2:33:14 PM </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 * 
 * @author danlee Li <br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */
public class LabConPurDetailVo extends BaseVo {

	/**
	 * @Fields serialVersionUID : TODO(耗材采购列表)
	 */

	private static final long serialVersionUID = 1L;
	@ExcelAnnotation(exportName = "ID")
	private String id;
	@ExcelAnnotation(exportName = "单据ID")
	private String mainId;
	@ExcelAnnotation(exportName = "耗材ID")
	private String consumablesId;
	@ExcelAnnotation(exportName = "耗材名称")
	private String consumablesName;
	@ExcelAnnotation(exportName = "采购数量")
	private Double num; // 采购数量
	@ExcelAnnotation(exportName = "采购人")
	private String purchaser;
	@ExcelAnnotation(exportName = "采购时间")
	private String purTime;// 采购时间
	@ExcelAnnotation(exportName = "申请人")
	private String applicant;// 申请人
	@ExcelAnnotation(exportName = "申请时间")
	private String createTime;// 申请人
	@ExcelAnnotation(exportName = "备注")
	private String remark; // 备注

	@ExcelAnnotation(exportName = "耗材单位")
	private String unit;
	@ExcelAnnotation(exportName = "规格")
	private String size;
	@ExcelAnnotation(exportName = "规格")
	private String specifications;
	@ExcelAnnotation(exportName = "实际数量")
	private Double amount;

	public String getSpecifications() {
		return specifications;
	}

	public void setSpecifications(String specifications) {
		this.specifications = specifications;
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

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public Double getNum() {
		return num;
	}

	public void setNum(Double num) {
		this.num = num;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
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

	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public String getPurTime() {
		return purTime;
	}

	public void setPurTime(String purTime) {
		this.purTime = purTime;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	@Override
	public String getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(null);
		if (num != null) {
			builder.append("采购数量", this.getNum());
		}
		if (remark != null) {
			builder.append("备注", this.getRemark());
		}
		return builder.toString();
	}

}
