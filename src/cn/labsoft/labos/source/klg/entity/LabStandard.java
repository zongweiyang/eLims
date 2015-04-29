package cn.labsoft.labos.source.klg.entity;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@Entity
@Table(name="lab_klg_standard")
public class LabStandard extends AbstractBasePo {
	
	private LabStandardType standType;
	private String standTypeName;
	private String name;   //名称
	private String ename;   //英文名称
	private String code;   //标准号
	private String fileUrl;
	private String fileName;
	private String filetrueName;
	private String releaseDate;//发布日期
	private String materialDate;//实施日期
	private String replaceName;//代替标准
	private String replaceIds;
	private String type;//标准类型
	private String standStatus;//标准状态 
	private String remark;//备注
	private String isUsed; //是否启用
	
	private String standIndex;
	
	private String itemIsDefault;//Y  默认  N 非默认
	private String demo1;
	private String demo2;
	private String demo3;
	
	
	public String getItemIsDefault() {
		return itemIsDefault;
	}
	public void setItemIsDefault(String itemIsDefault) {
		this.itemIsDefault = itemIsDefault;
	}
	@Column(name="standard_type")
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReplaceName() {
		return replaceName;
	}
	public void setReplaceName(String replaceName) {
		this.replaceName = replaceName;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getMaterialDate() {
		return materialDate;
	}
	public void setMaterialDate(String materialDate) {
		this.materialDate = materialDate;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getFileUrl() {
		return fileUrl;
	}
	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getFiletrueName() {
		return filetrueName;
	}
	public void setFiletrueName(String filetrueName) {
		this.filetrueName = filetrueName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	@Transient
	@Override
	public String getModelName() {
		return "标准管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "产品标准";
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	@ManyToOne
	@JoinColumn(name="stand_type", nullable=false)
	public LabStandardType getStandType() {
		return standType;
	}
	public void setStandType(LabStandardType standType) {
		this.standType = standType;
	}
	public String getStandTypeName() {
		return standTypeName;
	}
	public void setStandTypeName(String standTypeName) {
		this.standTypeName = standTypeName;
	}
	public String getStandIndex() {
		return standIndex;
	}
	public void setStandIndex(String standIndex) {
		this.standIndex = standIndex;
	}
	public String getDemo1() {
		return demo1;
	}
	public void setDemo1(String demo1) {
		this.demo1 = demo1;
	}
	public String getDemo2() {
		return demo2;
	}
	public void setDemo2(String demo2) {
		this.demo2 = demo2;
	}
	public String getDemo3() {
		return demo3;
	}
	public void setDemo3(String demo3) {
		this.demo3 = demo3;
	}
	public String getStandStatus() {
		return standStatus;
	}
	public void setStandStatus(String standStatus) {
		this.standStatus = standStatus;
	}
	public String getReplaceIds() {
		return replaceIds;
	}
	public void setReplaceIds(String replaceIds) {
		this.replaceIds = replaceIds;
	}
	public String getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}
}
