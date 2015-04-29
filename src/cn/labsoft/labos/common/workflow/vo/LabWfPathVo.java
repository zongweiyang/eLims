package cn.labsoft.labos.common.workflow.vo;

import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;


/**
 * 线路定义
 * 
 * @author Quinn
 */

@SuppressWarnings("serial")
public class LabWfPathVo extends BaseVo{

	private String id;
	@ExcelAnnotation(exportName = "线路名称")
	private String name;
	@ExcelAnnotation(exportName = "流程id")
	private String processId;//流程定义
	@ExcelAnnotation(exportName = "结束节点")
	private String endStepId;  //指向步骤
	@ExcelAnnotation(exportName = "结束节点")
	private String endStepName;  //指向步骤
	@ExcelAnnotation(exportName = "开始节点")
	private String startStepId;      //起始步骤
	@ExcelAnnotation(exportName = "开始节点")
	private String startStepName;      //起始步骤
	@ExcelAnnotation(exportName = "备注")
	private String comment;
	@ExcelAnnotation(exportName = "排序")
	private String order;
	@ExcelAnnotation(exportName = "线路坐标")
	private String points;
	@ExcelAnnotation(exportName = "横轴坐标")
	private String textpos;
	@ExcelAnnotation(exportName = "状态")
	private String status;  // 0初始  1 未完成 2完成 
	private List<LabWfPathVarVo> pathVarList;
	
	public List<LabWfPathVarVo> getPathVarList() {
		return pathVarList;
	}
	public void setPathVarList(List<LabWfPathVarVo> pathVarList) {
		this.pathVarList = pathVarList;
	}
	@Override
	public String getStatus() {
		return status;
	}
	@Override
	public void setStatus(String status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getProcessId() {
		return processId;
	}
	public void setProcessId(String processId) {
		this.processId = processId;
	}
	public String getPoints() {
		return points;
	}
	public void setPoints(String points) {
		this.points = points;
	}
	public String getTextpos() {
		return textpos;
	}
	public void setTextpos(String textpos) {
		this.textpos = textpos;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getEndStepId() {
		return endStepId;
	}
	public void setEndStepId(String endStepId) {
		this.endStepId = endStepId;
	}
	public String getEndStepName() {
		return endStepName;
	}
	public void setEndStepName(String endStepName) {
		this.endStepName = endStepName;
	}
	public String getStartStepId() {
		return startStepId;
	}
	public void setStartStepId(String startStepId) {
		this.startStepId = startStepId;
	}
	public String getStartStepName() {
		return startStepName;
	}
	public void setStartStepName(String startStepName) {
		this.startStepName = startStepName;
	}
	public String getOrder() {
		return order;
	}
	public void setOrder(String order) {
		this.order = order;
	}
	
}