package cn.labsoft.labos.common.report.vo;

import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;

import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabReportVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	@ExcelAnnotation(exportName = "id")
	private String id;
	@ExcelAnnotation(exportName = "类型")
	private String type;
	@ExcelAnnotation(exportName = "报告名称")
	private String title;
	@ExcelAnnotation(exportName = "业务功能id")
	private String busId;
	@ExcelAnnotation(exportName = "业务实例id")
	private String busInsId;
	@ExcelAnnotation(exportName = "业务名称")
	private String busName;//业务
	@ExcelAnnotation(exportName = "业务类型")
	private String busType;
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	@ExcelAnnotation(exportName = "编辑类型")
	private String editType;  //0为父编辑模板，1为业务编辑子模板  2 为只读模版
	//非table数据存储
	@ExcelAnnotation(exportName = "非table数据存储")
	private List<LabReportTagVo> dataList;
	
	@ExcelAnnotation(exportName = "文件字符串内容")
	private String file;
	private String path;
	@ExcelAnnotation(exportName = "业务编码")
	private String busCode;//业务编码
	@ExcelAnnotation(exportName = "xls文件")
	private byte[] xslFile;
	@ExcelAnnotation(exportName = "html文件")
	private byte[] htmlFile;
	
	private String isNew;
	
	public String getId() {
		return this.id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBusId() {
		return this.busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getBusType() {
		return this.busType;
	}

	public void setBusType(String busType) {
		this.busType = busType;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}

	public List<LabReportTagVo> getDataList() {
		return dataList;
	}

	public void setDataList(List<LabReportTagVo> dataList) {
		this.dataList = dataList;
	}
	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}



	public String getBusCode() {
		return busCode;
	}

	public void setBusCode(String busCode) {
		this.busCode = busCode;
	}

	public byte[] getXslFile() {
		return xslFile;
	}

	public void setXslFile(byte[] xslFile) {
		this.xslFile = xslFile;
	}

	public byte[] getHtmlFile() {
		return htmlFile;
	}

	public void setHtmlFile(byte[] htmlFile) {
		this.htmlFile = htmlFile;
	}

	public String getBusInsId() {
		return busInsId;
	}

	public void setBusInsId(String busInsId) {
		this.busInsId = busInsId;
	}

	public String getEditType() {
		return editType;
	}

	public void setEditType(String editType) {
		this.editType = editType;
	}

	public String getIsNew() {
		return isNew;
	}

	public void setIsNew(String isNew) {
		this.isNew = isNew;
	}
	
}
