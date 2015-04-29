package cn.labsoft.labos.base.code.vo;

import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

/**
 * @Title 公共代码类型Vo
 * @author Bill
 * @time 2014.02.08
 */
public class LabTypeVo extends BaseVo {

	private static final long serialVersionUID = -2416808374937853390L;

	@ExcelAnnotation(exportName = "ID")
	private String id;

	@ExcelAnnotation(exportName = "公共代码类型名称")
	private String name;

	@ExcelAnnotation(exportName = "公共代码类型编码")
	private String code;

	@ExcelAnnotation(exportName = "公共代码显示位置")
	private String showType;// Y后台 N前台

	@ExcelAnnotation(exportName = "备注")
	private String remark;

	@ExcelAnnotation(exportName = "序号")
	private Integer sort;
	
	private String type; 
	@ExcelAnnotation(exportName = "IDS")
	private String[] typeIds;

	private List codeList;
	public String[] getTypeIds() {
		return typeIds;
	}

	public void setTypeIds(String[] typeIds) {
		this.typeIds = typeIds;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public List getCodeList() {
		return codeList;
	}

	public void setCodeList(List codeList) {
		this.codeList = codeList;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
