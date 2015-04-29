package cn.labsoft.labos.source.appara.vo;

import java.util.List;

import cn.labsoft.labos.common.upload.vo.LabUploadVo;
import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

/**
 * <strong>Title : LabApparaVo </strong>. <br>
 * <strong>Description : TODO�</strong> <br>
 * <strong>Create on : Mar 10, 2011 9:50:48 AM  </strong>. <br>
 * <p>
 * <strong>Copyright (C) Labsoft Pte,Ltd.</strong> <br>
 * </p>
 *
 * @author Charles Xi<br>
 * @version <strong>LabOSM1 v 1.0.0</strong> <br>
 *          <br>
 *          <strong>修改历史: .</strong> <br>
 *          修改人� 修改日期 修改描述<br>
 *          -------------------------------------------<br>
 *          <br>
 *          <br>
 */

public class LabApparaVo extends BaseVo {
	private static final long serialVersionUID = 1L;
	@ExcelAnnotation(exportName = "ID")
	private String id;
	@ExcelAnnotation(exportName = "仪器类型Id")
	private String typeId; //仪器类型Id
	@ExcelAnnotation(exportName = "仪器名称")
	private String name;//仪器名称
	@ExcelAnnotation(exportName = "仪器编号")
	private String no;//编号
	@ExcelAnnotation(exportName = "出厂编号")
	private String code;//出厂编号
	@ExcelAnnotation(exportName = "供应商")
	private String supplier;//供应商
	private String model;
	@ExcelAnnotation(exportName = "规格/型号")
	private String spec;//规格/型号
	@ExcelAnnotation(exportName = "制造厂商")
	private String producer;
	@ExcelAnnotation(exportName = "价格")
	private Double price;//价格
	@ExcelAnnotation(exportName = "制造时间")
	private String purTime;
	private String verPeriod;
	private String verPeriodStr;
	private String verPeriodUnit;
	private String verLastTime;
	private String verNextTime;
	private String runNextTime;
	private String runPeriod;
	private String runPeriodStr;
	private String runLastTime;
	private String appType;
	private String lastTime;
	private String nextTime;
	private String document;
	private String typeName;
	private String comment;
	private String ext01;
	private String ext03;
	private String ext04;
	private String status;
	private String mcountry;
	private String keeperId;
	private String modPath;//仪器模板路径
	private String modName;//仪器模板名称
	@ExcelAnnotation(exportName = "保管人")
	private String keeper;//仪器保管人
	@ExcelAnnotation(exportName = "使用费用")
	private String cost;//使用费用
	@ExcelAnnotation(exportName = "是否验收")
	private String isCheck;//是否验收
	private String effectDate;
	private String verDate;
	private String alarm;
	private String apparaTypeName;//分类名称
	//Add by Kiven 
	@ExcelAnnotation(exportName = "历史记录内容")
	private String content;  //历史记录内容（样品名称+检测项目）
	@ExcelAnnotation(exportName = "使用时间")
	private String useDate; //使用时间
	@ExcelAnnotation(exportName = "使用人")
	private String useUser;  //使用人
	@ExcelAnnotation(exportName = "仪器状态")
	private String useStatus;//仪器状态（缺省正常）
	private String testProperty;
	private List<LabUploadVo> fileList;//所属文件
	private List<LabApparaVo> labApparaVoList; //该仪器名称下的所有编号
	
	private String isRunOrVer;
	
	public String getIsRunOrVer() {
		return isRunOrVer;
	}

	public void setIsRunOrVer(String isRunOrVer) {
		this.isRunOrVer = isRunOrVer;
	}

	public String getExt01() {
		return ext01;
	}

	public void setExt01(String ext01) {
		this.ext01 = ext01;
	}


	public String getExt03() {
		return ext03;
	}

	public void setExt03(String ext03) {
		this.ext03 = ext03;
	}

	public String getExt04() {
		return ext04;
	}

	public void setExt04(String ext04) {
		this.ext04 = ext04;
	}


	public List<LabApparaVo> getLabApparaVoList() {
		return labApparaVoList;
	}

	public void setLabApparaVoList(List<LabApparaVo> labApparaVoList) {
		this.labApparaVoList = labApparaVoList;
	}

	public String getId() {
		return id;
	}

	public String getNo() {
		return no;
	}

	public void setNo(String no) {
		this.no = no;
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

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getSpec() {
		return spec;
	}

	public void setSpec(String spec) {
		this.spec = spec;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getLastTime() {
		return lastTime;
	}

	public void setLastTime(String lastTime) {
		this.lastTime = lastTime;
	}

	public String getNextTime() {
		return nextTime;
	}

	public void setNextTime(String nextTime) {
		this.nextTime = nextTime;
	}

	public String getDocument() {
		return document;
	}

	public void setDocument(String document) {
		this.document = document;
	}

	@Override
	public String getTypeId() {
		return typeId;
	}

	@Override
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}

	public String getTypeName() {
		return typeName;
	}

	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}

	public String getKeeperId() {
		return keeperId;
	}

	public void setKeeperId(String keeperId) {
		this.keeperId = keeperId;
	}


	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	@Override
	public String getStatus() {
		return status;
	}

	@Override
	public void setStatus(String status) {
		this.status = status;
	}

	public String getPurTime() {
		return purTime;
	}

	public void setPurTime(String purTime) {
		this.purTime = purTime;
	}
	
	public String getVerPeriod() {
		return verPeriod;
	}

	public void setVerPeriod(String verPeriod) {
		this.verPeriod = verPeriod;
	}

	public String getVerPeriodStr() {
		return verPeriodStr;
	}

	public void setVerPeriodStr(String verPeriodStr) {
		this.verPeriodStr = verPeriodStr;
	}

	public String getVerPeriodUnit() {
		return verPeriodUnit;
	}

	public void setVerPeriodUnit(String verPeriodUnit) {
		this.verPeriodUnit = verPeriodUnit;
	}

	public String getVerLastTime() {
		return verLastTime;
	}

	public void setVerLastTime(String verLastTime) {
		this.verLastTime = verLastTime;
	}

	public String getVerNextTime() {
		return verNextTime;
	}

	public void setVerNextTime(String verNextTime) {
		this.verNextTime = verNextTime;
	}

	public String getRunNextTime() {
		return runNextTime;
	}

	public void setRunNextTime(String runNextTime) {
		this.runNextTime = runNextTime;
	}

	public String getRunPeriod() {
		return runPeriod;
	}

	public void setRunPeriod(String runPeriod) {
		this.runPeriod = runPeriod;
	}

	public String getRunPeriodStr() {
		return runPeriodStr;
	}

	public void setRunPeriodStr(String runPeriodStr) {
		this.runPeriodStr = runPeriodStr;
	}

	public String getRunLastTime() {
		return runLastTime;
	}

	public void setRunLastTime(String runLastTime) {
		this.runLastTime = runLastTime;
	}

	public String getEffectDate() {
		return effectDate;
	}

	public void setEffectDate(String effectDate) {
		this.effectDate = effectDate;
	}

	public String getVerDate() {
		return verDate;
	}

	public void setVerDate(String verDate) {
		this.verDate = verDate;
	}

	public String getAlarm() {
		return alarm;
	}

	public void setAlarm(String alarm) {
		this.alarm = alarm;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getUseDate() {
		return useDate;
	}

	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}

	public String getUseUser() {
		return useUser;
	}

	public void setUseUser(String useUser) {
		this.useUser = useUser;
	}

	public String getUseStatus() {
		return useStatus;
	}

	public void setUseStatus(String useStatus) {
		this.useStatus = useStatus;
	}
	
	public String getAppType() {
		return appType;
	}

	public void setAppType(String appType) {
		this.appType = appType;
	}

	public String getMcountry() {
		return mcountry;
	}

	public void setMcountry(String mcountry) {
		this.mcountry = mcountry;
	}

	public String getTestProperty() {
		return testProperty;
	}

	public void setTestProperty(String testProperty) {
		this.testProperty = testProperty;
	}
	public String getKeeper() {
		return keeper;
	}

	public void setKeeper(String keeper) {
		this.keeper = keeper;
	}

	public List<LabUploadVo> getFileList() {
		return fileList;
	}

	public void setFileList(List<LabUploadVo> fileList) {
		this.fileList = fileList;
	}

	public String getCost() {
		return cost;
	}

	public void setCost(String cost) {
		this.cost = cost;
	}

	public String getIsCheck() {
		return isCheck;
	}

	public void setIsCheck(String isCheck) {
		this.isCheck = isCheck;
	}

	public String getModPath() {
		return modPath;
	}

	public void setModPath(String modPath) {
		this.modPath = modPath;
	}

	public String getModName() {
		return modName;
	}

	public void setModName(String modName) {
		this.modName = modName;
	}

	public String getApparaTypeName() {
		return apparaTypeName;
	}

	public void setApparaTypeName(String apparaTypeName) {
		this.apparaTypeName = apparaTypeName;
	}



	
}
