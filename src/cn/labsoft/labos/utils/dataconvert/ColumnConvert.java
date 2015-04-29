package cn.labsoft.labos.utils.dataconvert;

public class ColumnConvert {
	
	private String fromColumn;
	private String toColumn;
	@SuppressWarnings("unused")
	private int fromColumnCount;
	 
	public ColumnConvert(String fromColumn, String toColumn) {
		super();
		this.fromColumn = fromColumn;
		this.toColumn = toColumn;
	}
	
	
	public String getFromColumn() {
		return fromColumn;
	}
	public void setFromColumn(String fromColumn) {
		this.fromColumn = fromColumn;
	}
	public String getToColumn() {
		return toColumn;
	}
	public void setToColumn(String toColumn) {
		this.toColumn = toColumn;
	}
}
