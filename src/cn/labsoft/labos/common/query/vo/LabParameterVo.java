package cn.labsoft.labos.common.query.vo;

import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;

public class LabParameterVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	private String name;//参数名称
	private String sqlExpression;//表达式
	private String nameChin;//参数中文名
	private String isVague;//是否模糊查询
	private String showType;//显示方式 0文本框，1：日期控件，2：时间控件,3:下拉框,4:弹出层
	private String labQueryId;
	private Integer sort;//顺序
	private String isSort;
	private String selectDemo;
	private String combox;
	private String comboxValue;//下拉框值
	private Integer queryIndex;
	private List<DemoVo> listDemo;
	private String dialogJson;
	private String diaLogColum;//比对字段
	private String diaLogAction;//访问地址
	private String selectSql;//区分 查询sql 还是统计
	public String getSelectSql() {
		return selectSql;
	}
	public void setSelectSql(String selectSql) {
		this.selectSql = selectSql;
	}
	public String getDiaLogAction() {
		return diaLogAction;
	}
	public void setDiaLogAction(String diaLogAction) {
		this.diaLogAction = diaLogAction;
	}
	public String getDialogJson() {
		return dialogJson;
	}
	public void setDialogJson(String dialogJson) {
		this.dialogJson = dialogJson;
	}
	public String getLabQueryId() {
		return labQueryId;
	}
	public void setLabQueryId(String labQueryId) {
		this.labQueryId = labQueryId;
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
	public String getNameChin() {
		return nameChin;
	}
	public void setNameChin(String nameChin) {
		this.nameChin = nameChin;
	}
	public String getIsVague() {
		return isVague;
	}
	public void setIsVague(String isVague) {
		this.isVague = isVague;
	}
	public String getShowType() {
		return showType;
	}
	public void setShowType(String showType) {
		this.showType = showType;
	}
	
	public Integer getSort() {
		return sort;
	}
	public void setSort(Integer sort) {
		this.sort = sort;
	}
	public String getIsSort() {
		return isSort;
	}
	public void setIsSort(String isSort) {
		this.isSort = isSort;
	}
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	public List<DemoVo> getListDemo() {
		return listDemo;
	}
	public void setListDemo(List<DemoVo> listDemo) {
		this.listDemo = listDemo;
	}
	public String getSelectDemo() {
		return selectDemo;
	}
	public void setSelectDemo(String selectDemo) {
		this.selectDemo = selectDemo;
	}
	public String getSqlExpression() {
		return sqlExpression;
	}
	public void setSqlExpression(String sqlExpression) {
		this.sqlExpression = sqlExpression;
	}
	public String getComboxValue() {
		return comboxValue;
	}
	public void setComboxValue(String comboxValue) {
		this.comboxValue = comboxValue;
	}
	public String getCombox() {
		return combox;
	}
	public void setCombox(String combox) {
		this.combox = combox;
	}
	public Integer getQueryIndex() {
		return queryIndex;
	}
	public void setQueryIndex(Integer queryIndex) {
		this.queryIndex = queryIndex;
	}
	public String getDiaLogColum() {
		return diaLogColum;
	}
	public void setDiaLogColum(String diaLogColum) {
		this.diaLogColum = diaLogColum;
	}
	

}
