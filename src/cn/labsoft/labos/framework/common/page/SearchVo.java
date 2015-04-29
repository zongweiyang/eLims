package cn.labsoft.labos.framework.common.page;

public class SearchVo{
	private String searchBy; //查找字段
	private String keyword;// 查找关键字
	private String sign;// 查找关键字和字段的对应
	
	public String getSearchBy() {
		return searchBy;
	}
	public void setSearchBy(String searchBy) {
		this.searchBy = searchBy;
	}
	public String getKeyword() {
		return keyword;
	}
	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}
	public String getSign() {
		return sign;
	}
	public void setSign(String sign) {
		this.sign = sign;
	}
}