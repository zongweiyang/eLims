package cn.labsoft.labos.common.makecode.vo;

import java.util.List;

public class Packagee {
	String name;
	String nameChinese;
	Clazz clazz;
	String namePath;
	String isFlow;
	String flowId;
	String flowCode;
	List<String> stepNameList;
	public String getIsFlow() {
		return isFlow;
	}

	public void setIsFlow(String isFlow) {
		this.isFlow = isFlow;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		
		this.name = name.trim();
	}

	public String getNameChinese() {
		return nameChinese;
	}

	public void setNameChinese(String nameChinese) {
		this.nameChinese = nameChinese;
	}

	public Clazz getClazz() {
		return clazz;
	}

	public void setClazz(Clazz clazz) {
		this.clazz = clazz;
	}

	public String getNamePath() {
		return namePath;
	}

	public void setNamePath(String namePath) {
		this.namePath = namePath;
	}

	public List<String> getStepNameList() {
		return stepNameList;
	}

	public void setStepNameList(List<String> stepNameList) {
		this.stepNameList = stepNameList;
	}

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public String getFlowCode() {
		return flowCode;
	}

	public void setFlowCode(String flowCode) {
		this.flowCode = flowCode;
	}


}
