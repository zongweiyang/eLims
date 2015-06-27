package cn.labsoft.labos.common.workflow.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.i18n.annotation.Translator;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;



/**
 * WfFunStep entity.
 * 功能步骤持久
 * @author Quinn
 */

public class LabWfFunStepVo extends BaseVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4516764191327186933L;
	// Fields
	@ExcelAnnotation(exportName = "主键Id")
	private String id;
	@ExcelAnnotation(exportName = "流程定义Id")
	private String processId;  //流程定义Id
	@ExcelAnnotation(exportName = "流程定义名称")
	private String processName; //流程定义Id
	@ExcelAnnotation(exportName = "节点定义Id")
	private String stepId;//该步骤对应的功能Id
	@ExcelAnnotation(exportName = "节点定义名称")
	@Translator
	private String stepName;
	@ExcelAnnotation(exportName = "节点编码")
	private String stepCode;
	@ExcelAnnotation(exportName = "节点url")
	private String stepUrl;
	@ExcelAnnotation(exportName = "创建时间")
	private String createTime;
	@ExcelAnnotation(exportName = "创建人")
	private String userId;
	private String stepType;
	
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
	public String getStepName() {
		return stepName;
	}
	public void setStepName(String stepName) {
		this.stepName = stepName;
	}
	public String getStepCode() {
		return stepCode;
	}
	public void setStepCode(String stepCode) {
		this.stepCode = stepCode;
	}
	@Override
	public String getCreateTime() {
		return createTime;
	}
	@Override
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getStepId() {
		return stepId;
	}
	public void setStepId(String stepId) {
		this.stepId = stepId;
	}
	public String getStepUrl() {
		return stepUrl;
	}
	public void setStepUrl(String stepUrl) {
		this.stepUrl = stepUrl;
	}
	public String getStepType() {
		return stepType;
	}
	public void setStepType(String stepType) {
		this.stepType = stepType;
	}
	
	
}