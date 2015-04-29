package cn.labsoft.labos.base.logs.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;

@SuppressWarnings("serial")
public class LabLogExceptionVo extends BaseVo {

	private String id;
	private String clazz;
	private String funId;
	private String lineNum;
	private String method;
	private String exception;
	private String userInfo;
	private String uri;

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getFunId() {
		return funId;
	}

	public void setFunId(String funId) {
		this.funId = funId;
	}

	public String getLineNum() {
		return lineNum;
	}

	public void setLineNum(String lineNum) {
		this.lineNum = lineNum;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public String getException() {
		return exception;
	}

	public void setException(String exception) {
		this.exception = exception;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

}
