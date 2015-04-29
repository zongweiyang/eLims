package cn.labsoft.labos.base.function.vo;

import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

/**
 * 系统功能VO
 * 
 * @author Carl Yang
 * @since 8.0
 * @version 8.0
 * 
 */
@SuppressWarnings("serial")
public class LabFunctionVo extends BaseVo {
	@ExcelAnnotation(exportName = "ID")
	private String id;
	@ExcelAnnotation(exportName = "功能名称")
	private String name;
	@ExcelAnnotation(exportName = "功能编码")
	private String code;
	@ExcelAnnotation(exportName = "流程编码")
	private String wfcode;
	@ExcelAnnotation(exportName = "功能路径")
	private String url;
	@ExcelAnnotation(exportName = "图片路径")
	private String imageUrl;
	@ExcelAnnotation(exportName = "父功能ID")
	private String parentId;
	@ExcelAnnotation(exportName = "父功能名称")
	private String parentName;
	@ExcelAnnotation(exportName = "功能类型")
	private String type;
	@ExcelAnnotation(exportName = "子功能集合")
	private List<LabFunctionVo> subFunctionList;
	@ExcelAnnotation(exportName = "是否显示")
	private String isDisplay;
	@ExcelAnnotation(exportName = "序号")
	private Integer sort;
	@ExcelAnnotation(exportName = "是否是流程")
	private String isProcess;
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	@ExcelAnnotation(exportName = "功能是否有下级")
	private String isHaveSub;
	@ExcelAnnotation(exportName = "数量")
	private String count;
	@ExcelAnnotation(exportName = "是否关联报告")
	private String isReport; // 是否关联报告
	@ExcelAnnotation(exportName = "是否关联条码")
	private String isBarCode; // 是否支持条码
	@ExcelAnnotation(exportName = "是否为查询功能")
	private String isQuery;//是否为查询定义
	@ExcelAnnotation(exportName = "是否快捷功能")
	private String isQuickFun;
	@ExcelAnnotation(exportName = "是否关联导出模板")
	private String isTemplate;
	@ExcelAnnotation(exportName = "是否前端显示")
	private String isFront;
	@ExcelAnnotation(exportName = "是否后台显示")
	private String isBack;
	@ExcelAnnotation(exportName = "数据权限")
	private String dataStr;//org部门级 user用户级
	private String valStr; //若为role时的roleId

	public String getIsBack() {
		return isBack;
	}

	public void setIsBack(String isBack) {
		this.isBack = isBack;
	}

	public String getIsFront() {
		return isFront;
	}

	public void setIsFront(String isFront) {
		this.isFront = isFront;
	}

	public String getIsBarCode() {
		return isBarCode;
	}

	public void setIsBarCode(String isBarCode) {
		this.isBarCode = isBarCode;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getParentName() {
		return parentName;
	}

	public void setParentName(String parentName) {
		this.parentName = parentName;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<LabFunctionVo> getSubFunctionList() {
		return subFunctionList;
	}

	public void setSubFunctionList(List<LabFunctionVo> subFunctionList) {
		this.subFunctionList = subFunctionList;
	}

	public Integer getSort() {
		return sort;
	}

	public void setSort(Integer sort) {
		this.sort = sort;
	}

	public String getIsProcess() {
		return isProcess;
	}

	public void setIsProcess(String isProcess) {
		this.isProcess = isProcess;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getIsDisplay() {
		return isDisplay;
	}

	public void setIsDisplay(String isDisplay) {
		this.isDisplay = isDisplay;
	}

	public String getWfcode() {
		return wfcode;
	}

	public void setWfcode(String wfcode) {
		this.wfcode = wfcode;
	}

	public String getIsHaveSub() {
		return isHaveSub;
	}

	public void setIsHaveSub(String isHaveSub) {
		this.isHaveSub = isHaveSub;
	}

	public String getIsReport() {
		return isReport;
	}

	public void setIsReport(String isReport) {
		this.isReport = isReport;
	}

	public String getIsQuery() {
		return isQuery;
	}

	public void setIsQuery(String isQuery) {
		this.isQuery = isQuery;
	}

	public String getIsQuickFun() {
		return isQuickFun;
	}

	public void setIsQuickFun(String isQuickFun) {
		this.isQuickFun = isQuickFun;
	}

	public String getIsTemplate() {
		return isTemplate;
	}

	public void setIsTemplate(String isTemplate) {
		this.isTemplate = isTemplate;
	}

	public String getDataStr() {
		return dataStr;
	}

	public void setDataStr(String dataStr) {
		this.dataStr = dataStr;
	}

	public String getValStr() {
		return valStr;
	}

	public void setValStr(String valStr) {
		this.valStr = valStr;
	}
}
