package cn.labsoft.labos.base.logs.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.GenericGenerator;

import cn.labsoft.labos.framework.common.po.BasePo;

/**
 * 系统日志持久化对象
 * @author Quinn
 */
@SuppressWarnings("serial")
@Entity
@Table(name="lab_log_record")
public class LabLogRecord extends BasePo {

	// Fields

	private String id;
	private String operatorid;//操作人ID
	private String operator;//操作人
	private Date dateTime;//操作时间
	private String content;//操作内容
	private String workId;//数据表ID
	private String workTable;//数据表
	private String module;//模块
	private String method;//操作
	private String ip;//ip地址
	private String url;

	// Constructors
	/** default constructor */
	public LabLogRecord() {
	}

	/** minimal constructor */
	public LabLogRecord(Date dateTime, String workId, String workTable,
			String module, String method, String ip, String url) {
		this.dateTime = dateTime;
		this.workId = workId;
		this.workTable = workTable;
		this.module = module;
		this.method = method;
		this.ip = ip;
		this.url = url;
	}

	/** full constructor */
	public LabLogRecord(String operatorid, String operator, Date dateTime,
			String content, String workId, String workTable, String module,
			String method, String ip, String url) {
		this.operatorid = operatorid;
		this.operator = operator;
		this.dateTime = dateTime;
		this.content = content;
		this.workId = workId;
		this.workTable = workTable;
		this.module = module;
		this.method = method;
		this.ip = ip;
		this.url = url;
	}

	// Property accessors

	@Override
	@Id
	@Column(length = 32, nullable = true)
	@GeneratedValue(generator = "uuid")
	@GenericGenerator(name = "uuid", strategy = "uuid")
	public String getId() {
		return this.id;
	}

	@Override
	public void setId(String id) {
		this.id = id;
	}

	public String getOperatorid() {
		return this.operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public String getOperator() {
		return this.operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getDateTime() {
		return this.dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getContent() {
		return this.content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWorkId() {
		return this.workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getWorkTable() {
		return this.workTable;
	}

	public void setWorkTable(String workTable) {
		this.workTable = workTable;
	}

	public String getModule() {
		return this.module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getMethod() {
		return this.method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getIp() {
		return this.ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUrl() {
		return this.url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		ToStringBuilder builder = new ToStringBuilder(null);

		if (this.getContent() != null) {
			builder.append("内容", this.getContent());
		}
		if (this.getIp() != null) {
			builder.append("Ip", this.getIp());
		}
		if (this.getUrl() != null) {
			builder.append("Url", this.getUrl());
		}
		if (this.getWorkId() != null) {
			builder.append("WorkId", this.getWorkId());
		}

		String str = builder.toString();
		if (7 < str.length()) {
			return str.substring(0, str.length() - 7);
		}
		return str;
	}
}