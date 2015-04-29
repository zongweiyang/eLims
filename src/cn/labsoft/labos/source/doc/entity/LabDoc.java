package cn.labsoft.labos.source.doc.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

@SuppressWarnings("serial")
@Entity
@Table(name = "lab_doc")
public class LabDoc extends AbstractBasePo {

	// FildsO
	private LabDoc labDoc;
	@ExcelAnnotation(exportName = "文件名称")
	private String fileName;//文件名称
	@ExcelAnnotation(exportName = "文件创建者")
	private String creatorName;//文件创建者Name
	private String creatorId;//文件创建者ID
	private String isDir;//0-文件夹 1-文档 
	@ExcelAnnotation(exportName = "作者")
	private String author;
	@ExcelAnnotation(exportName = "文件类型")
	private String docType;//文件类型
	private String docSize;//文件大小
	private String versionNum;//版本号
	private String modifyTime;//修改日期
	private String modifier;//修改人
	private String orgName;//科室名称
	private String orgId;//科室ID
	private String title;

	private String docIcon;//文件图标
	private String docDepict;//文档描述
	
	private String power;//权限 ALL指所有，ONLY 仅自己
	private String remark;//备注
	private String keyWord;//关键字
	private String summary;//摘要
	private String literature;//文件类型
	private String ext01;
	private String ext02;

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getCreatorName() {
		return creatorName;
	}

	public void setCreatorName(String creatorName) {
		this.creatorName = creatorName;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getDocType() {
		return docType;
	}

	public void setDocType(String docType) {
		this.docType = docType;
	}

	public String getDocSize() {
		return docSize;
	}

	public void setDocSize(String docSize) {
		this.docSize = docSize;
	}

	public String getVersionNum() {
		return versionNum;
	}

	public void setVersionNum(String versionNum) {
		this.versionNum = versionNum;
	}

	public String getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}

	public String getModifier() {
		return modifier;
	}

	public void setModifier(String modifier) {
		this.modifier = modifier;
	}

	public String getOrgName() {
		return orgName;
	}

	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getIsDir() {
		return isDir;
	}

	public void setIsDir(String isDir) {
		this.isDir = isDir;
	}

	public String getDocIcon() {
		return docIcon;
	}

	public void setDocIcon(String docIcon) {
		this.docIcon = docIcon;
	}

	public String getDocDepict() {
		return docDepict;
	}

	public void setDocDepict(String docDepict) {
		this.docDepict = docDepict;
	}

	@ManyToOne
	@JoinColumn(name = "pid")
	public LabDoc getLabDoc() {
		return labDoc;
	}

	public void setLabDoc(LabDoc labDoc) {
		this.labDoc = labDoc;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getExt01() {
		return ext01;
	}

	public void setExt01(String ext01) {
		this.ext01 = ext01;
	}

	public String getExt02() {
		return ext02;
	}

	public void setExt02(String ext02) {
		this.ext02 = ext02;
	}
	
	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	@Override
	@Transient
	public String getModelName() {
		return "文档管理";
	}

	@Override
	@Transient
	public String getTableName() {
		return "我的文档";
	}

	public String getKeyWord() {
		return keyWord;
	}

	public void setKeyWord(String keyWord) {
		this.keyWord = keyWord;
	}

	public String getSummary() {
		return summary;
	}

	public void setSummary(String summary) {
		this.summary = summary;
	}

	public String getLiterature() {
		return literature;
	}

	public void setLiterature(String literature) {
		this.literature = literature;
	}

}
