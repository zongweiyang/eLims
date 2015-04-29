package cn.labsoft.labos.source.appara.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

/**
 * @title仪器类型 
 * @author Bill
 */
public class LabApparaTypeVo extends  BaseVo{

	private static final long serialVersionUID = 1L;

	@ExcelAnnotation(exportName = "ID")
	private String id;
	
	@ExcelAnnotation(exportName = "父类型ID")
	private String LabAppTypeId;
	
	@ExcelAnnotation(exportName = "父类型名称")
	private String LabAppTypeName;
	
	@ExcelAnnotation(exportName = "类型名称")
	private String appName;
	
	@ExcelAnnotation(exportName = "是否删除")
	private String isDel;
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabAppTypeId() {
		return LabAppTypeId;
	}

	public void setLabAppTypeId(String labAppTypeId) {
		LabAppTypeId = labAppTypeId;
	}

	public String getLabAppTypeName() {
		return LabAppTypeName;
	}

	public void setLabAppTypeName(String labAppTypeName) {
		LabAppTypeName = labAppTypeName;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
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
