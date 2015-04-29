package cn.labsoft.labos.business.sam.vo;

import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabSamTypeVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	@ExcelAnnotation(exportName = "类型id")
	private String id;
	@ExcelAnnotation(exportName = "类型名称")
	private String name;//样品分类名称
	@ExcelAnnotation(exportName = "类型编号")
	private String code;//样品分类编号
	@ExcelAnnotation(exportName = "父id")
	private String pid;//上级ID
	private String demo;
    private String isDel;
    private String where;
    private List<LabSamTypeVo> labSamTypeVoList;//父节点下的list列表
    private String isDefault;
    private String itemName;
	private String itemId;
	@ExcelAnnotation(exportName = "查询名称")
	private String searchName;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getPid() {
		return pid;
	}
	public void setPid(String pid) {
		this.pid = pid;
	}
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	@Override
	public String getIsDel() {
		return isDel;
	}
	@Override
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public List<LabSamTypeVo> getLabSamTypeVoList() {
		return labSamTypeVoList;
	}
	public void setLabSamTypeVoList(List<LabSamTypeVo> labSamTypeVoList) {
		this.labSamTypeVoList = labSamTypeVoList;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
	public String getIsDefault() {
		return isDefault;
	}
	public void setIsDefault(String isDefault) {
		this.isDefault = isDefault;
	}
	public String getItemName() {
		return itemName;
	}
	public void setItemName(String itemName) {
		this.itemName = itemName;
	}
	public String getItemId() {
		return itemId;
	}
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
    

}
