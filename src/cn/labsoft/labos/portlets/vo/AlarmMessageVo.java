package cn.labsoft.labos.portlets.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

public class AlarmMessageVo extends BaseVo{
	private String index;
	private String type;
	private String message;
	public String getIndex() {
		return index;
	}
	public void setIndex(String index) {
		this.index = index;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
