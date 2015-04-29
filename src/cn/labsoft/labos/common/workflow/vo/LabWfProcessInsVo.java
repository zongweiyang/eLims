package cn.labsoft.labos.common.workflow.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;


/**
 * WfProcessIns entity.
 * 流程实例
 * @author Quinn
 */

public class LabWfProcessInsVo extends BaseVo{

	// Fields

	/**
	 * 
	 */
	private static final long serialVersionUID = 2812781750784788330L;
	/**
	 * 
	 */
	private String id;
	private String parInsId;
	private String parBusId;
	private String processId;
	@ExcelAnnotation(exportName = "流程名称")
	private String processName;
	private String busId;   
	@ExcelAnnotation(exportName = "业务类型")
	private String type;   
	private String orgId;
	private String orgName;
	@ExcelAnnotation(exportName = "创建者")
	private String userId;
	@ExcelAnnotation(exportName = "创建者名称")
	private String userName;
	@ExcelAnnotation(exportName = "开始时间")
	private String startDate;
	@ExcelAnnotation(exportName = "结束时间")
	private String endDate;
	@ExcelAnnotation(exportName = "状态")
	private String status;    //审批状态
	@ExcelAnnotation(exportName = "内容")
	private String content;   //审批意见
	
	private String exp1;
	private String exp2;
	private String exp3;
	private String flowXml;
	
	private String viewHeight;   //视图高
	
	
	public String getFlowXml() {
		return flowXml;
	}
	public void setFlowXml(String flowXml) {
		this.flowXml = flowXml;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getBusId() {
		return busId;
	}
	public void setBusId(String busId) {
		this.busId = busId;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
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
	@Override
	public String getStatus() {
		return status;
	}
	@Override
	public void setStatus(String status) {
		this.status = status;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getOrgName() {
		return orgName;
	}
	public void setOrgName(String orgName) {
		this.orgName = orgName;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	@Override
	public String getStartDate() {
		return startDate;
	}
	@Override
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	@Override
	public String getEndDate() {
		return endDate;
	}
	@Override
	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}
	public String getExp1() {
		return exp1;
	}
	public void setExp1(String exp1) {
		this.exp1 = exp1;
	}
	public String getExp2() {
		return exp2;
	}
	public void setExp2(String exp2) {
		this.exp2 = exp2;
	}
	public String getExp3() {
		return exp3;
	}
	public void setExp3(String exp3) {
		this.exp3 = exp3;
	}
	public String getParInsId() {
		return parInsId;
	}
	public void setParInsId(String parInsId) {
		this.parInsId = parInsId;
	}
	public String getViewHeight() {
		return viewHeight;
	}
	public void setViewHeight(String viewHeight) {
		this.viewHeight = viewHeight;
	}
	public String getParBusId() {
		return parBusId;
	}
	public void setParBusId(String parBusId) {
		this.parBusId = parBusId;
	}
	

}