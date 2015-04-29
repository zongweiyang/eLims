package cn.labsoft.labos.base.logs.vo;

import java.util.Date;
import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

@SuppressWarnings("serial")
public class LabLogRecordVo extends BaseVo {
	@ExcelAnnotation(exportName = "ID")
	private String id;
	@ExcelAnnotation(exportName = "操作人ID")
	private String operatorid;
	@ExcelAnnotation(exportName = "操作者")
	private String operator;
	@ExcelAnnotation(exportName = "日期时间")
	private Date dateTime;
	@ExcelAnnotation(exportName = "日志内容")
	private String content;
	@ExcelAnnotation(exportName = "业务ID")
	private String workId;
	@ExcelAnnotation(exportName = "业务")
	private String workTable;
	@ExcelAnnotation(exportName = "模块")
	private String module;
	@ExcelAnnotation(exportName = "方法")
	private String method;
	@ExcelAnnotation(exportName = "操作者IP")
	private String ip;
	@ExcelAnnotation(exportName = "地址")
	private String url;
	@ExcelAnnotation(exportName = "查询开始时间")
	private String startTime;
	@ExcelAnnotation(exportName = "查询结束时间")
	private String endTime;
	private List<LabLogRecordVo> labLogrecordVoList;

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getOperatorid() {
		return operatorid;
	}

	public void setOperatorid(String operatorid) {
		this.operatorid = operatorid;
	}

	public String getOperator() {
		return operator;
	}

	public void setOperator(String operator) {
		this.operator = operator;
	}

	public Date getDateTime() {
		return dateTime;
	}

	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getWorkId() {
		return workId;
	}

	public void setWorkId(String workId) {
		this.workId = workId;
	}

	public String getWorkTable() {
		return workTable;
	}

	public void setWorkTable(String workTable) {
		this.workTable = workTable;
	}

	public String getModule() {
		return module;
	}

	public void setModule(String module) {
		this.module = module;
	}

	public String getMethod() {
		return method;
	}

	public void setMethod(String method) {
		this.method = method;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public List<LabLogRecordVo> getLabLogrecordVoList() {
		return labLogrecordVoList;
	}

	public void setLabLogrecordVoList(List<LabLogRecordVo> labLogrecordVoList) {
		this.labLogrecordVoList = labLogrecordVoList;
	}

}
