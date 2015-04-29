package cn.labsoft.labos.base.code.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

/**
 * @title 公共代码Vo
 * @author Bill
 * @time 2014.02.08
 */
public class LabCodeVo extends BaseVo {

	private static final long serialVersionUID = -4487695021778767093L;

	@ExcelAnnotation(exportName = "ID")
	private String id;

	@ExcelAnnotation(exportName = "公共代码类型ID")
	private String typeid;

	@ExcelAnnotation(exportName = "公共代码类型名称")
	private String typename;

	@ExcelAnnotation(exportName = "公共代码名称")
	private String name;
	private String namex;

	@ExcelAnnotation(exportName = "公共代码编码")
	private String code;

	@ExcelAnnotation(exportName = "公共代码备注")
	private String remark;

	@ExcelAnnotation(exportName = "公共代码显示位置")
	private String showType;// Y后台 N前台

	@ExcelAnnotation(exportName = "序号")
	private Integer sort;

	@ExcelAnnotation(exportName = "IDS")
	private String[] codeIds;

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String[] getCodeIds() {
		return codeIds;
	}

	public void setCodeIds(String[] codeIds) {
		this.codeIds = codeIds;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getTypeid() {
		return typeid;
	}

	public void setTypeid(String typeid) {
		this.typeid = typeid;
	}

	public String getTypename() {
		return typename;
	}

	public void setTypename(String typename) {
		this.typename = typename;
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

	public String getShowType() {
		return showType;
	}

	public void setShowType(String showType) {
		this.showType = showType;
	}

	public String getNamex() {
		return namex;
	}

	public void setNamex(String namex) {
		this.namex = namex;
	}

}
