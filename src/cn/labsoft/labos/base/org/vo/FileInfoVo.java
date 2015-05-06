package cn.labsoft.labos.base.org.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

public class FileInfoVo extends BaseVo {
	private static final long serialVersionUID = 3835631627466452284L;
	private String busId;
	private String fileTypes;
	private String busType;
	private String showType;
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public String getFileTypes() {
		return fileTypes;
	}
	public void setFileTypes(String fileTypes) {
		this.fileTypes = fileTypes;
	}
	public String getBusType() {
		return busType;
	}
	public void setBusType(String busType) {
		this.busType = busType;
	}
	public String getShowType() {
		return showType;
	}
	public void setShowType(String showType) {
		this.showType = showType;
	}
}
