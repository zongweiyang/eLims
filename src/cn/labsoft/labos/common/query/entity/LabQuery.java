package cn.labsoft.labos.common.query.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import cn.labsoft.labos.base.function.entity.LabFunction;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;
@Entity
public class LabQuery extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private String name;//定义sql
	private String funName;
	private LabFunction function;
	private String type;//sql种类
	private String sqlType;//sql类型
	private String queryType;//查询类型
	private Integer rowNum;//几行一换
	private String countSql;//用于统计sql
	private String isCount;//是否统计数据
	private String isCartogram;//是否生成统计图
	private String cartogramSql;//统计图用于sql
	public String getCartogramSql() {
		return cartogramSql;
	}
	public void setCartogramSql(String cartogramSql) {
		this.cartogramSql = cartogramSql;
	}
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
	public Integer getRowNum() {
		return rowNum;
	}
	public void setRowNum(Integer rowNum) {
		this.rowNum = rowNum;
	}
	@Column(name="queryType")
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	@Transient
	@Override
	public String getTableName() {
		return "查询定义";
	}
	@Transient
	@Override
	public String getModelName() {
		return "组件管理";
	}
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getFunName() {
		return funName;
	}
	public void setFunName(String funName) {
		this.funName = funName;
	}
	@ManyToOne
	@JoinColumn(name="fun_id")
	public LabFunction getFunction() {
		return function;
	}
	public void setFunction(LabFunction function) {
		this.function = function;
	}
	@Column(name="sqlType")
	public String getSqlType() {
		return sqlType;
	}
	public void setSqlType(String sqlType) {
		this.sqlType = sqlType;
	}
	public String getCountSql() {
		return countSql;
	}
	public void setCountSql(String countSql) {
		this.countSql = countSql;
	}
	


}
