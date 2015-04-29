package cn.labsoft.labos.utils.dataconvert;

public class TableConvert {
	
    private String fromTable;
    private String toTable;
    
    public TableConvert(String fromTable, String toTable) {
		super();
		this.fromTable = fromTable;
		this.toTable = toTable;
	}
    
	public String getFromTable() {
		return fromTable;
	}
	public void setFromTable(String fromTable) {
		this.fromTable = fromTable;
	}
	public String getToTable() {
		return toTable;
	}
	public void setToTable(String toTable) {
		this.toTable = toTable;
	}
    
}
