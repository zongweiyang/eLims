package cn.labsoft.labos.common.workflow.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;



/**
 * WfStepDef entity.
 * 步骤定义
 * @author Quinn
 */

public class LabWfStepVo extends BaseVo{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5983052298798936114L;
	// Fields
	@ExcelAnnotation(exportName = "主键Id")
	private String id;
	private String processId;  //模板
	private String processName;  //模板
	private String num;
	@ExcelAnnotation(exportName = "节点名称")
	private String name;
	@ExcelAnnotation(exportName = "节点编码")
	private String code;  //编码
	@ExcelAnnotation(exportName = "节点类型")
	private String type;          //节点类型
	private String operType;      //操作者类型role,user
	@ExcelAnnotation(exportName = "节点状态")
	private String status;  // 0初始  1 未完成 2完成 
	@ExcelAnnotation(exportName = "节点url")
	private String url;
	private String overDate;
	private String comment;
	@ExcelAnnotation(exportName = "x坐标")
	private String leftx;
	@ExcelAnnotation(exportName = "y坐标")
	private String topx;
	@ExcelAnnotation(exportName = "点击数量")
	private String amounts;
	private String isControl;
	
	
	public String getOperType() {
		return operType;
	}
	public void setOperType(String operType) {
		this.operType = operType;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	@Override
	public String getStatus() {
		return status;
	}
	@Override
	public void setStatus(String status) {
		this.status = status;
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
	public String getNum() {
		return num;
	}
	public void setNum(String num) {
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getOverDate() {
		return overDate;
	}
	public void setOverDate(String overDate) {
		this.overDate = overDate;
	}
	public String getLeftx() {
		return leftx;
	}
	public void setLeftx(String leftx) {
		this.leftx = leftx;
	}
	public String getTopx() {
		return topx;
	}
	public void setTopx(String topx) {
		this.topx = topx;
	}
	public String getAmounts() {
		return amounts;
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
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	

}