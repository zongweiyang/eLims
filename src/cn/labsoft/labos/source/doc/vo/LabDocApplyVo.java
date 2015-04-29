package cn.labsoft.labos.source.doc.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

public class LabDocApplyVo extends BaseVo{
	/** 
	 * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么) 
	 */ 
	
	private static final long serialVersionUID = 1L;
	private String id;
	private LabDocVo labDocVo;
	private String applyTime;//申请时间
	private String endTime;//退还时间
	private String applyName;//申请人
	private String applyId;
	private String modifyTime;//修改时间
	private String modifyName;//修改人
	private String approveTime;//批准时间
	private String status;
	private String flag;
	private String isDel;
	
	private String ext1;
	private String ext2;
	private String ext3;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	public LabDocVo getLabDocVo() {
		return labDocVo;
	}
	public void setLabDocVo(LabDocVo labDocVo) {
		this.labDocVo = labDocVo;
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
	@Override
	public String getStatus() {
		return status;
	}
	@Override
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
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Override
	public String getIsDel() {
		return isDel;
	}
	@Override
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	
	
}
