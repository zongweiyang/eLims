package cn.labsoft.labos.source.doc.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@SuppressWarnings("serial")
@Entity
@Table(name = "lab_doc_apply")
public class LabDocApply extends AbstractBasePo {

	private LabDoc labDoc;
	private String applyTime;//申请时间
	private String endTime;//退还时间
	private String applyName;//申请人
	private String applyId;
	private String modifyTime;//修改时间
	private String modifyName;//修改人
	private String approveTime;//批准时间
	private String status;

	private String ext1;
	private String ext2;
	private String ext3;

	@ManyToOne
	@JoinColumn(name = "file_id")
	public LabDoc getLabDoc() {
		return labDoc;
	}

	public void setLabDoc(LabDoc labDoc) {
		this.labDoc = labDoc;
	}

	public String getApplyTime() {
		return applyTime;
	}

	public void setApplyTime(String applyTime) {
		this.applyTime = applyTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getApplyName() {
		return applyName;
	}

	public void setApplyName(String applyName) {
		this.applyName = applyName;
	}

	public String getApplyId() {
		return applyId;
	}

	public void setApplyId(String applyId) {
		this.applyId = applyId;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifyName() {
		return modifyName;
	}

	public void setModifyName(String modifyName) {
		this.modifyName = modifyName;
	}

	public String getApproveTime() {
		return approveTime;
	}

	public void setApproveTime(String approveTime) {
		this.approveTime = approveTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getExt1() {
		return ext1;
	}

	public void setExt1(String ext1) {
		this.ext1 = ext1;
	}

	public String getExt2() {
		return ext2;
	}

	public void setExt2(String ext2) {
		this.ext2 = ext2;
	}

	public String getExt3() {
		return ext3;
	}

	public void setExt3(String ext3) {
		this.ext3 = ext3;
	}

	@Override
	@Transient
	public String getModelName() {
		return "文档管理";
	}

	@Override
	@Transient
	public String getTableName() {
		return "我的申请";
	}
}
