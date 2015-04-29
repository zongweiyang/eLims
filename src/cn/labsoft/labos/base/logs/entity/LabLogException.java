package cn.labsoft.labos.base.logs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

import cn.labsoft.labos.framework.common.po.BasePo;
/**
 * 系统异常持久化对象
 * @author Quinn
 */
@Entity
@Table(name = "lab_log_exception")
public class LabLogException extends BasePo {

	
	private static final long serialVersionUID = 546264888380301584L;
	private String id;
	private String clazz;
	private String method;
	private String funId;
	private String lineNum;
	private String exception;
	private String uri;
	private String userInfo;

	@Override
	@Id
	@Column(length = 32, nullable = true)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return id;
	}

	@Override
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

	public String getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(String userInfo) {
		this.userInfo = userInfo;
	}

	public String getUri() {
		return uri;
	}

	public void setUri(String uri) {
		this.uri = uri;
	}

}
