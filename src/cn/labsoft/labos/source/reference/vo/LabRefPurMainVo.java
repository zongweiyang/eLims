package cn.labsoft.labos.source.reference.vo;

import java.util.List;
import org.apache.commons.lang.builder.ToStringBuilder;
import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

/**
 * 
 * <strong>Title : LabRefPurMain </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Crefte on : Feb 22, 2014 2:33:39 PM </strong>. <br>
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
public class LabRefPurMainVo extends BaseVo {

	/**
	 * @Fields serialVersionUID : TODO(标准品采购)
	 */

	private static final long serialVersionUID = 1L;
	@ExcelAnnotation(exportName = "ID")
	private String id;
	@ExcelAnnotation(exportName = "采购人")
	private String purchaser;// 采购人
	@ExcelAnnotation(exportName = "采购时间")
	private String purTime;// 采购时间
	@ExcelAnnotation(exportName = "申请人")
	private String applicant;// 申请人
	@ExcelAnnotation(exportName = "单据号")
	private String receiptno;// 单据号
	@ExcelAnnotation(exportName = "类型")
	private String type;
	@ExcelAnnotation(exportName = "入库人")
	private String inPerson;// 入库人
	@ExcelAnnotation(exportName = "入库时间")
	private String inTime;// 入库时间
	@ExcelAnnotation(exportName = "标准品采购详情集合")
	private List<LabRefPurDetailVo> labRefPurDetailVoList;
	private String referenceId;
	private String remark; // 备注

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPurchaser() {
		return purchaser;
	}

	public void setPurchaser(String purchaser) {
		this.purchaser = purchaser;
	}

	public String getApplicant() {
		return applicant;
	}

	public void setApplicant(String applicant) {
		this.applicant = applicant;
	}

	public String getPurTime() {
		return purTime;
	}

	public void setPurTime(String purTime) {
		this.purTime = purTime;
	}

	public String getReceiptno() {
		return receiptno;
	}

	public void setReceiptno(String receiptno) {
		this.receiptno = receiptno;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getInPerson() {
		return inPerson;
	}

	public void setInPerson(String inPerson) {
		this.inPerson = inPerson;
	}

	public String getInTime() {
		return inTime;
	}

	public void setInTime(String inTime) {
		this.inTime = inTime;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(null);

		if (purchaser != null) {
			builder.append("采购人", this.getPurchaser());
		}
		if (purTime != null) {
			builder.append("采购时间", this.getPurTime());
		}
		return builder.toString();
	}

	public List<LabRefPurDetailVo> getLabRefPurDetailVoList() {
		return labRefPurDetailVoList;
	}

	public void setLabRefPurDetailVoList(
			List<LabRefPurDetailVo> labRefPurDetailVoList) {
		this.labRefPurDetailVoList = labRefPurDetailVoList;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReferenceId() {
		return referenceId;
	}

	public void setReferenceId(String referenceId) {
		this.referenceId = referenceId;
	}

}
