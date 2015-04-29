package cn.labsoft.labos.source.consumables.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

/**
 * @title 耗材类型
 * @author Bill
 */
public class LabConTypeVo extends BaseVo {

	@ExcelAnnotation(exportName = "ID")
	private String id;

	@ExcelAnnotation(exportName = "父类型ID")
	private String LabConConsumablesTypeId;

	@ExcelAnnotation(exportName = "父类型名称")
	private String LabConConsumablesTypeName;

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

	public String getLabConConsumablesTypeId() {
		return LabConConsumablesTypeId;
	}

	public void setLabConConsumablesTypeId(String labConConsumablesTypeId) {
		LabConConsumablesTypeId = labConConsumablesTypeId;
	}

	public String getLabConConsumablesTypeName() {
		return LabConConsumablesTypeName;
	}

	public void setLabConConsumablesTypeName(String labConConsumablesTypeName) {
		LabConConsumablesTypeName = labConConsumablesTypeName;
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
