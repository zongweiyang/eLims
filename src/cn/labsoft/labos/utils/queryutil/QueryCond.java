package cn.labsoft.labos.utils.queryutil;

public class QueryCond {
	private String fieldName;
	private String fieldVal;
	private String operate;
	private String fieldType;

	public QueryCond() {
	}

	public QueryCond(String fieldName, String fieldType, String operate,
			String fieldVal) {
		this.fieldName = fieldName;
		this.fieldType = fieldType;
		this.operate = operate;
		this.fieldVal = fieldVal;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldVal() {
		return fieldVal;
	}

	public void setFieldVal(String fieldVal) {
		this.fieldVal = fieldVal;
	}

	public String getOperate() {
		return operate;
	}

	public void setOperate(String operate) {
		this.operate = operate;
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

}
