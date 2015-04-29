package cn.labsoft.labos.common.report.vo;

import java.util.List;
import cn.labsoft.labos.framework.common.vo.BaseVo;

import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabReportTagVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	@ExcelAnnotation(exportName = "序号")
	private String index;
	@ExcelAnnotation(exportName = "标签名称")
	private String title;
	@ExcelAnnotation(exportName = "表达式")
	private String code;
	@ExcelAnnotation(exportName = "值")
	private String value;
	@ExcelAnnotation(exportName = "类型")
	private String type;
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	@ExcelAnnotation(exportName = "该循环内容集合")
	private List<LabReportTagVo> contentList;
	@ExcelAnnotation(exportName = "标签模版id")
	private String labReportId; 
	@ExcelAnnotation(exportName = "标签模版名称")
	private String labReportTitle;
	
	private String parentId;
	private String parentTitle;
	
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getIndex() {
		return this.index;
	}

	public void setIndex(String index) {
		this.index = index;
	}

	public String getTitle() {
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getCode() {
		return this.code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return this.value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getType() {
		return this.type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getRemark() {
		return this.remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	public List<LabReportTagVo> getContentList() {
		return contentList;
	}
	public void setContentList(List<LabReportTagVo> contentList) {
		this.contentList = contentList;
	}
	public String getLabReportId() {
		return labReportId;
	}
	public void setLabReportId(String labReportId) {
		this.labReportId = labReportId;
	}
	public String getLabReportTitle() {
		return labReportTitle;
	}
	public void setLabReportTitle(String labReportTitle) {
		this.labReportTitle = labReportTitle;
	}
	public String getParentId() {
		return parentId;
	}
	public void setParentId(String parentId) {
		this.parentId = parentId;
	}
	public String getParentTitle() {
		return parentTitle;
	}
	public void setParentTitle(String parentTitle) {
		this.parentTitle = parentTitle;
	}
	
}
