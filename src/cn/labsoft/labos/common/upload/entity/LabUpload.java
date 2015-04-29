package cn.labsoft.labos.common.upload.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Transient;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * SysUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@Entity
public class LabUpload extends AbstractBasePo {
	private static final long serialVersionUID = -4816187960545065634L;
	// Fields
	private String uuId;
	private String name;     //文件原名称
	private String vname;    //存储名称
	private String path;      //文件存储路径（相对路径）
	private String size;      //文件大小
	private String type;      //文件类型
	
	private String saveType;  // 存储类型 0代表文件存储 1数据流存储
	private String content;
	
	private String busId;     //业务Id
	private String busType;   //业务类型
	private String creater;   //上传人名称
	private String remark;
	private String pdfName;//转pdf名称
	private String pdfPath;//转pdf路径
	@Column(name="pdfName")
	public String getPdfName() {
		return pdfName;
	}
	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}
	@Column(name="pdfPath")
	public String getPdfPath() {
		return pdfPath;
	}
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getVname() {
		return vname;
	}
	public void setVname(String vname) {
		this.vname = vname;
	}
	public String getPath() {
		return path;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public String getSize() {
		return size;
	}
	public void setSize(String size) {
		this.size = size;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Column(name="saveType")
	public String getSaveType() {
		return saveType;
	}
	public void setSaveType(String saveType) {
		this.saveType = saveType;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	@Column(name="busId")
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	@Column(name="busType")
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public String getCreater() {
		return creater;
	}
	public void setCreater(String creater) {
		this.creater = creater;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	@Column(name="uuid")
	public String getUuId() {
		return uuId;
	}
	public void setUuId(String uuId) {
		this.uuId = uuId;
	}    
	
	@Transient
	@Override
	public String getModelName() {
		return "组件管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "上传组件";
	}
}