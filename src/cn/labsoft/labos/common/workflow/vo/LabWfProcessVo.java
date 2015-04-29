package cn.labsoft.labos.common.workflow.vo;

import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;


/**
 * WfProcessDef entity.
 * 流程定义
 * @author Quinn
 */

public class LabWfProcessVo extends BaseVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7667957543783463729L;
	@ExcelAnnotation(exportName = "主键Id")
	private String id;
	@ExcelAnnotation(exportName = "功能Id")
	private String funId;
	@ExcelAnnotation(exportName = "功能名称")
	private String funName;
	private String uuId;
	@ExcelAnnotation(exportName = "流程名称")
	private String name;
	@ExcelAnnotation(exportName = "流程编码")
	private String code;
	@ExcelAnnotation(exportName = "创建人Id")
	private String userId;
	@ExcelAnnotation(exportName = "创建人名称")
	private String userName;
	@ExcelAnnotation(exportName = "创建日期")
	private String createDate;
	@ExcelAnnotation(exportName = "修改日期")
	private String modifyDate;
	@ExcelAnnotation(exportName = "启用日期")
	private String startDate; //启用时间
	@ExcelAnnotation(exportName = "截止日期")
	private String endDate;   //截止时间
	@ExcelAnnotation(exportName = "备注")
	private String comment;
	private String flowXml;
	@ExcelAnnotation(exportName = "流程定义状态")
	private String status;  //0编辑状态  1只读状态
	private String initStatus;
	private int county;
	private int countn;
	private int count;
	
	@ExcelAnnotation(exportName = "定义步骤集合")
	private List<LabWfFunStepVo> funStepList;
	@ExcelAnnotation(exportName = "定义变量集合")
	private List<LabWfVariableVo> varList;
	
	
	private List<LabWfProcessVo> subProcessList;
	private String parProcessId;//父流程
	private String parProcessName;//父流程
	private String isStop;     //干预性：Y表示等待子流程 Ｎ表示正常流转
	private String isSubWf;    //是否还有子流程
	public List<LabWfFunStepVo> getFunStepList() {
		return funStepList;
	}
	public void setFunStepList(List<LabWfFunStepVo> funStepList) {
		this.funStepList = funStepList;
	}
	@Override
	public String getStatus() {
		return status;
	}
	@Override
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUuId() {
		if(uuId==null)
			uuId=java.util.UUID.randomUUID().toString().replace("-","");
		return uuId;
	}
	public void setUuId(String uuId) {
		this.uuId = uuId;
	}
	public String getFlowXml() {
		return flowXml;
	}
	public void setFlowXml(String flowXml) {
		this.flowXml = flowXml;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
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
	public String getCreateDate() {
		return createDate;
	}
	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
	public String getModifyDate() {
		return modifyDate;
	}
	public void setModifyDate(String modifyDate) {
		this.modifyDate = modifyDate;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public List<LabWfVariableVo> getVarList() {
		return varList;
	}
	public void setVarList(List<LabWfVariableVo> varList) {
		this.varList = varList;
	}
	public String getFunId() {
		return funId;
	}
	public void setFunId(String funId) {
		this.funId = funId;
	}
	public String getFunName() {
		return funName;
	}
	public void setFunName(String funName) {
		this.funName = funName;
	}
	public String getInitStatus() {
		return initStatus;
	}
	public void setInitStatus(String initStatus) {
		this.initStatus = initStatus;
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
	public int getCounty() {
		return county;
	}
	public void setCounty(int county) {
		this.county = county;
	}
	public int getCountn() {
		return countn;
	}
	public void setCountn(int countn) {
		this.countn = countn;
	}
	public int getCount() {
		return count;
	}
	public void setCount(int count) {
		this.count = count;
	}
	public String getParProcessId() {
		return parProcessId;
	}
	public void setParProcessId(String parProcessId) {
		this.parProcessId = parProcessId;
	}
	public String getParProcessName() {
		return parProcessName;
	}
	public void setParProcessName(String parProcessName) {
		this.parProcessName = parProcessName;
	}
	public String getIsStop() {
		return isStop;
	}
	public void setIsStop(String isStop) {
		this.isStop = isStop;
	}
	public String getIsSubWf() {
		return isSubWf;
	}
	public void setIsSubWf(String isSubWf) {
		this.isSubWf = isSubWf;
	}
	public List<LabWfProcessVo> getSubProcessList() {
		return subProcessList;
	}
	public void setSubProcessList(List<LabWfProcessVo> subProcessList) {
		this.subProcessList = subProcessList;
	}
	
}