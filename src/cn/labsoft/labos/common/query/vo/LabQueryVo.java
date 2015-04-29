package cn.labsoft.labos.common.query.vo;

import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;

import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabQueryVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	@ExcelAnnotation(exportName = "定义sql")
	private String name;
	private Integer rowNum;//几行一换
	@ExcelAnnotation(exportName = "sql类型")
	private String type;
	@ExcelAnnotation(exportName = "查询类型")
	private String queryType;
	@ExcelAnnotation(exportName = "功能名称")
	private String funName;
	private String sqlType;//sql类型
	private String funId;
	private String isNull;
	private String[] pName;
	private String[] pChName;
	private PageVo pageVo;
	private String countSql;//用于统计sql
	private String selectSql;
	private List<LabParameterVo> listLabParameter;
	private List<LabParameterVo> listLabParameterCountVo;
	private List<TitleVo> listTitle;
	private List<ValueVo> listValue;
	private List<QueryVo> listQuery;
	private List<QueryVo> listQuerySenior;
	private List<DemoVo> listCountValue;
	private List<DemoVo> listCartogram;
	private String cartogramSql;//统计图用于sql
	private String isCount;//是否统计数据
	private String isCartogram;//是否生成统计图
	
	public String getIsCount() {
		return isCount;
	}
	public void setIsCount(String isCount) {
		this.isCount = isCount;
	}
	public String getIsCartogram() {
		return isCartogram;
	}
	public void setIsCartogram(String isCartogram) {
		this.isCartogram = isCartogram;
	}
	public List<DemoVo> getListCountValue() {
		return listCountValue;
	}
	public void setListCountValue(List<DemoVo> listCountValue) {
		this.listCountValue = listCountValue;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getIsNull() {
		return isNull;
	}
	public void setIsNull(String isNull) {
		this.isNull = isNull;
	}
	public String getFunName() {
		return funName;
	}
	public void setFunName(String funName) {
		this.funName = funName;
	}
	public String getId() {
		return this.id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	
	public Integer getRowNum() {
		return rowNum;
	}
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}
	public String[] getPName() {
		return pName;
	}
	public void setPName(String[] name) {
		pName = name;
	}
	public String[] getPChName() {
		return pChName;
	}
	public void setPChName(String[] chName) {
		pChName = chName;
	}
	public List<TitleVo> getListTitle() {
		return listTitle;
	}
	public void setListTitle(List<TitleVo> listTitle) {
		this.listTitle = listTitle;
	}
	
	public List<ValueVo> getListValue() {
		return listValue;
	}
	public void setListValue(List<ValueVo> listValue) {
		this.listValue = listValue;
	}
	public List<QueryVo> getListQuery() {
		return listQuery;
	}
	public void setListQuery(List<QueryVo> listQuery) {
		this.listQuery = listQuery;
	}
	public String getFunId() {
		return funId;
	}
	public void setFunId(String funId) {
		this.funId = funId;
	}
	public PageVo getPageVo() {
		return pageVo;
	}
	public void setPageVo(PageVo pageVo) {
		this.pageVo = pageVo;
	}
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public List<LabParameterVo> getListLabParameter() {
		return listLabParameter;
	}
	public void setListLabParameter(List<LabParameterVo> listLabParameter) {
		this.listLabParameter = listLabParameter;
	}
	public String getSqlType() {
		return sqlType;
	}
	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}
	public List<QueryVo> getListQuerySenior() {
		return listQuerySenior;
	}
	public void setListQuerySenior(List<QueryVo> listQuerySenior) {
		this.listQuerySenior = listQuerySenior;
	}
	public String getCountSql() {
		return countSql;
	}
	public void setCountSql(String countSql) {
		this.countSql = countSql;
	}
	public String getSelectSql() {
		return selectSql;
	}
	public void setSelectSql(String selectSql) {
		this.selectSql = selectSql;
	}
	public List<LabParameterVo> getListLabParameterCountVo() {
		return listLabParameterCountVo;
	}
	public void setListLabParameterCountVo(List<LabParameterVo> listLabParameterCountVo) {
		this.listLabParameterCountVo = listLabParameterCountVo;
	}
	public String getCartogramSql() {
		return cartogramSql;
	}
	public void setCartogramSql(String cartogramSql) {
		this.cartogramSql = cartogramSql;
	}
	public List<DemoVo> getListCartogram() {
		return listCartogram;
	}
	public void setListCartogram(List<DemoVo> listCartogram) {
		this.listCartogram = listCartogram;
	}


}
