package cn.labsoft.labos.business.samtest.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@Entity
public class LabSamTestBeatch extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	private String name;//批次名称
	private String taskIds;
	private String itemNames;
	private String itemIds;
	private String apparaUserId;//仪器使用id
	private String reagentOutId;//试剂出库iD
	private String referenceOutId;//标准品出库ID
	private String ambientId;//环境ID
	private LabWfProcessIns processIns;
	@ManyToOne
	public LabWfProcessIns getProcessIns() {
		return processIns;
	}

	public void setProcessIns(LabWfProcessIns processIns) {
		this.processIns = processIns;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getTaskIds() {
		return taskIds;
	}
	@Column(length=2000)
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
	@Transient
	@Override
	public String getModelName() {
		return "批次管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "数据批次表";
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
