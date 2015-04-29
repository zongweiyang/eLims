package cn.labsoft.labos.source.reference.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

/**
 * @title 标准品类型
 * @author Bill
 */
public class LabRefTypeVo extends BaseVo {

	@ExcelAnnotation(exportName = "ID")
	private String id;

	@ExcelAnnotation(exportName = "父类型ID")
	private String LabRefReferenceTypeId;

	@ExcelAnnotation(exportName = "父类型名称")
	private String LabRefReferenceTypeName;

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

	public String getLabRefReferenceTypeId() {
		return LabRefReferenceTypeId;
	}

	public void setLabRefReferenceTypeId(String labRefReferenceTypeId) {
		LabRefReferenceTypeId = labRefReferenceTypeId;
	}

	public String getLabRefReferenceTypeName() {
		return LabRefReferenceTypeName;
	}

	public void setLabRefReferenceTypeName(String labRefReferenceTypeName) {
		LabRefReferenceTypeName = labRefReferenceTypeName;
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
