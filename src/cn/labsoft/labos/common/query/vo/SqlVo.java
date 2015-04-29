package cn.labsoft.labos.common.query.vo;

public class SqlVo {
	private String column;
	private String tableName;
	private String where;
	private String[] whereSqls;
	public String[] getWhereSqls() {
		return whereSqls;
	}
	public void setWhereSqls(String[] whereSqls) {
		this.whereSqls = whereSqls;
	}
	public String getColumn() {
		return column;
	}
	public void setColumn(String column) {
		this.column = column;
	}
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	public String getWhere() {
		return where;
	}
	public void setWhere(String where) {
		this.where = where;
	}
}
