package cn.labsoft.labos.common.page.vo;

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
public class LabPageEditorVo extends BaseVo {
	private static final long serialVersionUID = 1130846654182136596L;
	@ExcelAnnotation(exportName = "ID")
	private String id;
	@ExcelAnnotation(exportName = "页面中文名")
	private String name;
	@ExcelAnnotation(exportName = "页面路径")
	private String url;
	@ExcelAnnotation(exportName = "页面对象名")
	private String objName;
	@ExcelAnnotation(exportName = "页面对象路径")
	private String objUrl;
	@ExcelAnnotation(exportName = "文件名")
	private String fileName;
	@ExcelAnnotation(exportName = "文件字符串内容")
	private String file;
	@ExcelAnnotation(exportName = "文件类型")
	private String type;
	@ExcelAnnotation(exportName = "页面的URL")
	private String showUrl;
	@ExcelAnnotation(exportName = "上级文件路径")
	private String parentUrl;
	@ExcelAnnotation(exportName = "上上级文件路径")
	private String grandUrl;
	@ExcelAnnotation(exportName = "URL数组")
	private String[] urls;
	@ExcelAnnotation(exportName = "跳转参数")
	private String param;
	@ExcelAnnotation(exportName = "备注")
	private String remark;

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getGrandUrl() {
		return grandUrl;
	}

	public void setGrandUrl(String grandUrl) {
		this.grandUrl = grandUrl;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getShowUrl() {
		return showUrl;
	}

	public void setShowUrl(String showUrl) {
		this.showUrl = showUrl;
	}

	public String getParentUrl() {
		return parentUrl;
	}

	public void setParentUrl(String parentUrl) {
		this.parentUrl = parentUrl;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getObjName() {
		return objName;
	}

	public void setObjName(String objName) {
		this.objName = objName;
	}

	public String getObjUrl() {
		return objUrl;
	}

	public void setObjUrl(String objUrl) {
		this.objUrl = objUrl;
	}

	@Override
	public String getFileName() {
		return fileName;
	}

	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public String[] getUrls() {
		return urls;
	}

	public void setUrls(String[] urls) {
		this.urls = urls;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
