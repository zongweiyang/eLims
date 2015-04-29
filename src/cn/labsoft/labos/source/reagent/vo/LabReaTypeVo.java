package cn.labsoft.labos.source.reagent.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

/**
 * @title 试剂类型
 * @author Bill
 */
public class LabReaTypeVo extends BaseVo {

	@ExcelAnnotation(exportName = "ID")
	private String id;

	@ExcelAnnotation(exportName = "父类型ID")
	private String LabReaReagentTypeId;

	@ExcelAnnotation(exportName = "父类型名称")
	private String LabReaReagentTypeName;

	@ExcelAnnotation(exportName = "类型名称")
	private String name;

	@ExcelAnnotation(exportName = "是否删除")
	private String isDel;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLabReaReagentTypeId() {
		return LabReaReagentTypeId;
	}

	public void setLabReaReagentTypeId(String labReaReagentTypeId) {
		LabReaReagentTypeId = labReaReagentTypeId;
	}

	public String getLabReaReagentTypeName() {
		return LabReaReagentTypeName;
	}

	public void setLabReaReagentTypeName(String labReaReagentTypeName) {
		LabReaReagentTypeName = labReaReagentTypeName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
