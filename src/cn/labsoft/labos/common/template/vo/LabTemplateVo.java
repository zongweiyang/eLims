package cn.labsoft.labos.common.template.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

/**
 * 页面管理VO
 * 
 * @author Carl Yang
 * @since 8.0
 * @version 8.0
 * 
 */
public class LabTemplateVo extends BaseVo {
	private static final long serialVersionUID = -7406909238975008921L;
	@ExcelAnnotation(exportName = "id")
	private String id;
	@ExcelAnnotation(exportName = "类型")
	private String type;
	@ExcelAnnotation(exportName = "模板名称")
	private String name;
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	@ExcelAnnotation(exportName = "保存文件的路径")
	private String path;
	@ExcelAnnotation(exportName = "关联业务的ID")
	private String busId;
	@ExcelAnnotation(exportName = "关联业务的名称")
	private String busName;
	@ExcelAnnotation(exportName = "对象的路径")
	private String objUrl;
	private String data;

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getBusId() {
		return busId;
	}

	public void setBusId(String busId) {
		this.busId = busId;
	}

	public String getObjUrl() {
		return objUrl;
	}

	public void setObjUrl(String objUrl) {
		this.objUrl = objUrl;
	}

	public String getBusName() {
		return busName;
	}

	public void setBusName(String busName) {
		this.busName = busName;
	}
}
