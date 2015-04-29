package cn.labsoft.labos.common.upload.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

/**
 * SysUser entity.
 * 
 * @author MyEclipse Persistence Tools
 */

@SuppressWarnings("serial")
public class LabUploadVo extends BaseVo {

	// Fields
	
	private String id;        //主键
	private String uuId;
	private String name;     //文件原名称
	private String vname;    //存储名称
	private String path;      //文件存储路径（相对路径）
	private String size;      //文件大小
	private String type;      //文件类型
	
	private String saveType;  // 存储类型 file代表文件存储 data数据流存储
	private String content;
	
	private String busId;     //业务Id
	private String busType;   //业务类型
	private String creater;   //上传人名称
	private String remark;
	private String pdfName;//转pdf名称
	private String pdfPath;//转pdf路径
	public String getPdfName() {
		return pdfName;
	}
	public void setPdfName(String pdfName) {
		this.pdfName = pdfName;
	}
	public String getPdfPath() {
		return pdfPath;
	}
	public void setPdfPath(String pdfPath) {
		this.pdfPath = pdfPath;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
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
	public String getUuId() {
		return uuId;
	}
	public void setUuId(String uuId) {
		this.uuId = uuId;
	}    
	
	
	
	
}