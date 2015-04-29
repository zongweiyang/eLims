package cn.labsoft.labos.common.query.vo;


public class QueryVo {
	private String name;
	private String demo;
	private String value;
	private String isVague;
	private String isVagueValue;
	private String isSort;
	private String isSortValue;
	private String parameterId;
	private String showType;//类型  文本  日期  时间
	private String comboxValue;
	private Integer queryIndex;
	private String queryType;//查询类型
	private String  dialogJson;
	private String diaLogColum;
	private String id;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getParameterId() {
		return parameterId;
	}
	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}
	public String getIsSort() {
		return isSort;
	}
	public void setIsSort(String isSort) {
		this.isSort = isSort;
	}
	public String getIsSortValue() {
		return isSortValue;
	}
	public void setIsSortValue(String isSortValue) {
		this.isSortValue = isSortValue;
	}
	public String getIsVagueValue() {
		return isVagueValue;
	}
	public void setIsVagueValue(String isVagueValue) {
		this.isVagueValue = isVagueValue;
	}
	public String getIsVague() {
		return isVague;
	}
	public void setIsVague(String isVague) {
		this.isVague = isVague;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	public String getShowType() {
		return showType;
	}
	public void setShowType(String showType) {
		this.showType = showType;
	}
	public String getComboxValue() {
		return comboxValue;
	}
	public void setComboxValue(String comboxValue) {
		this.comboxValue = comboxValue;
	}
	public Integer getQueryIndex() {
		return queryIndex;
	}
	public void setQueryIndex(Integer queryIndex) {
		this.queryIndex = queryIndex;
	}
	public String getQueryType() {
		return queryType;
	}
	public void setQueryType(String queryType) {
		this.queryType = queryType;
	}
	public String getDialogJson() {
		return dialogJson;
	}
	public void setDialogJson(String dialogJson) {
		this.dialogJson = dialogJson;
	}
	public String getDiaLogColum() {
		return diaLogColum;
	}
	public void setDiaLogColum(String diaLogColum) {
		this.diaLogColum = diaLogColum;
	}
	
}
