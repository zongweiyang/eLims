package cn.labsoft.labos.common.workflow.entity;



import javax.persistence.Entity;
import javax.persistence.Transient;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;

/**
 * 步骤定义
 * @author Quinn
 * @version 8.0
 * @since 8.0
 */
@Entity
public class LabWfStep extends AbstractBasePo{

	private static final long serialVersionUID = -1950408889148705324L;
	private String uuid;
	private String processId;    //流程
	private String processName;  //流程
	private Integer num;         //排序
	private String name;
	private String code;  //编码
	private String type;  //节点类型start,note,end
	private String operType;//操作者类型role,user
	private String url;
	private Long overDate; //本环节超期天数
	private String comment;
	private String status;  // 0初始  1 未完成 2完成 
	private String leftx;
	private String topx;
	private String amounts;
	private String isControl;
	
	// Constructors
	
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getLeftx() {
		return leftx;
	}
	public void setLeftx(String leftx) {
		this.leftx = leftx;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getProcessName() {
		return processName;
	}
	public void setProcessName(String processName) {
		this.processName = processName;
	}
	public String getTopx() {
		return topx;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public void setTopx(String topx) {
		this.topx = topx;
	}

	public String getAmounts() {
		return amounts;
	}
	public String getOperType() {
		return operType;
	}
	public void setOperType(String operType) {
		this.operType = operType;
	}
	public void setAmounts(String amounts) {
		this.amounts = amounts;
	}
	public String getIsControl() {
		return isControl;
	}
	public void setIsControl(String isControl) {
		this.isControl = isControl;
	}
	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		this.num = num;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Long getOverDate() {
		return overDate;
	}

	public void setOverDate(Long overDate) {
		this.overDate = overDate;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getUuid() {
		return uuid;
	}
	public void setUuid(String uuid) {
		this.uuid = uuid;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	@Transient
	@Override
	public String getModelName() {
		return "组件管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "节点信息";
	}
}