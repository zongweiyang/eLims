package cn.labsoft.labos.business.samtest.vo;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabSamTestBeatchVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	private String id;
	@ExcelAnnotation(exportName = "批次名称")
	private String name;//
	@ExcelAnnotation(exportName = "任务ID")
	private String taskIds;
	@ExcelAnnotation(exportName = "检测项目")
	private String itemNames;
	@ExcelAnnotation(exportName = "检测项目Id")
	private String itemIds;
	@ExcelAnnotation(exportName = "样品个数")
	private Integer sampNum;
	@ExcelAnnotation(exportName = "温度")
	private String temperature;
	@ExcelAnnotation(exportName = "湿度")
	private String humidity;
	private String isBack;
	@ExcelAnnotation(exportName = "模板地址")
	private String downPath;
	@ExcelAnnotation(exportName = "检测项目数量")
	private String itemNum;
	@ExcelAnnotation(exportName = "仪器使用id")
	private String apparaUserId;//
	@ExcelAnnotation(exportName = "试剂出库iD")
	private String reagentOutId;//
	@ExcelAnnotation(exportName = "标准品出库ID")
	private String referenceOutId;//
	@ExcelAnnotation(exportName = "环境ID")
	private String ambientId;//
	
	public String getItemNum() {
		return itemNum;
	}
	public void setItemNum(String itemNum) {
		this.itemNum = itemNum;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		if(name==null)
			name="";
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getTaskIds() {
		return taskIds;
	}
	public void setTaskIds(String taskIds) {
		this.taskIds = taskIds;
	}
	public String getItemNames() {
		return itemNames;
	}
	public void setItemNames(String itemNames) {
		this.itemNames = itemNames;
	}
	public String getItemIds() {
		return itemIds;
	}
	public void setItemIds(String itemIds) {
		this.itemIds = itemIds;
	}
	
	public Integer getSampNum() {
		return sampNum;
	}
	public void setSampNum(Integer sampNum) {
		this.sampNum = sampNum;
	}
	public String getTemperature() {
		return temperature;
	}
	public void setTemperature(String temperature) {
		this.temperature = temperature;
	}
	public String getHumidity() {
		return humidity;
	}
	public void setHumidity(String humidity) {
		this.humidity = humidity;
	}
	public String getIsBack() {
		return isBack;
	}
	public void setIsBack(String isBack) {
		this.isBack = isBack;
	}
	public String getDownPath() {
		return downPath;
	}
	public void setDownPath(String downPath) {
		this.downPath = downPath;
	}
	public String getApparaUserId() {
		return apparaUserId;
	}
	public void setApparaUserId(String apparaUserId) {
		this.apparaUserId = apparaUserId;
	}
	public String getReagentOutId() {
		return reagentOutId;
	}
	public void setReagentOutId(String reagentOutId) {
		this.reagentOutId = reagentOutId;
	}
	public String getReferenceOutId() {
		return referenceOutId;
	}
	public void setReferenceOutId(String referenceOutId) {
		this.referenceOutId = referenceOutId;
	}
	public String getAmbientId() {
		return ambientId;
	}
	public void setAmbientId(String ambientId) {
		this.ambientId = ambientId;
	}
	
}
