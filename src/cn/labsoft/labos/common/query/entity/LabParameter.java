package cn.labsoft.labos.common.query.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
import cn.labsoft.labos.i18n.annotation.Translator;
@Entity
public class LabParameter extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private String name;//参数名称
	@Translator
	private String nameChin;//参数中文名
	private String isVague;//是否模糊查询
	private String showType;//显示方式 0文本框，1：日期控件，2：时间控件,3:下拉框,4:弹出层
	
	private String isSort;
	@Translator(isHtml = true)
	private String comboxValue;//下拉框值
	private String combox;
	private LabQuery labQuery; 
	private Integer queryIndex;
	private String dialogJson;//弹出层 数据源
	private String diaLogColum;//比对字段
	private String diaLogAction;//访问地址
	private String selectSql;//类型  0  查询 1 统计
	
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
	public String getDiaLogColum() {
		return diaLogColum;
	}
	public void setDiaLogColum(String diaLogColum) {
		this.diaLogColum = diaLogColum;
	}
	public String getDialogJson() {
		return dialogJson;
	}
	public void setDialogJson(String dialogJson) {
		this.dialogJson = dialogJson;
	}
	@Column(name="queryIndex")
	public Integer getQueryIndex() {
		return queryIndex;
	}
	public void setQueryIndex(Integer queryIndex) {
		this.queryIndex = queryIndex;
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
	@Column(name="showType")
	public String getShowType() {
		return showType;
	}
	public void setShowType(String showType) {
		this.showType = showType;
	}
	

	public String getIsSort() {
		return isSort;
	}
	public void setIsSort(String isSort) {
		this.isSort = isSort;
	}
	@Transient
	@Override
	public String getModelName() {
		// TODO Auto-generated method stub
		return "组件管理";
	}
	@Transient
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "查询定义器";
	}
	
	@ManyToOne
	@JoinColumn(name="labQuery_id")
	public LabQuery getLabQuery() {
		return labQuery;
	}
	public void setLabQuery(LabQuery labQuery) {
		this.labQuery = labQuery;
	}
	@Column(name="comboxValue")
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
	


}
