package cn.labsoft.labos.source.klg.vo;

import cn.labsoft.labos.framework.common.po.AbstractBasePo;
import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;
/**
 * 
 * @title 方法标准 LabMethodVo
 * @author 
 */
@SuppressWarnings({ "unchecked", "serial","unused" })
public class LabMethodVo extends BaseVo {
	
	private String id;
	@ExcelAnnotation(exportName = "检测方法名称")
	private String name;
	@ExcelAnnotation(exportName = "检测方法编号")
	private String code;
	@ExcelAnnotation(exportName = "费用")
	private Double price;
	private String isDel; //是否被删除 0正常 1已被删除
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	private int sort;
	private String demo1;
	private String demo2;
	@ExcelAnnotation(exportName = "内容")
	private String context;
	private String appApparatusName;
	private String appApparatusNo;
	private String reaReagentName;
	private String reaReagentNo;
	private String labItemName;
	private String labItemCode;
    private String recordID;//记录标识
	private String fileName;//模板名称
	private String coordinate;//坐标
	private String filterName;//过滤字段
	private String labApparaId;
	private String labApparaName;
	public String getLabApparaId() {
		return labApparaId;
	}
	public void setLabApparaId(String labApparaId) {
		this.labApparaId = labApparaId;
	}
	public String getLabApparaName() {
		return labApparaName;
	}
	public void setLabApparaName(String labApparaName) {
		this.labApparaName = labApparaName;
	}
	public String getCoordinate() {
		return coordinate;
	}
	public void setCoordinate(String coordinate) {
		this.coordinate = coordinate;
	}
	public String getFilterName() {
		return filterName;
	}
	public void setFilterName(String filterName) {
		this.filterName = filterName;
	}
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getAppApparatusName() {
		return appApparatusName;
	}
	public void setAppApparatusName(String appApparatusName) {
		this.appApparatusName = appApparatusName;
	}
	public String getAppApparatusNo() {
		return appApparatusNo;
	}
	public void setAppApparatusNo(String appApparatusNo) {
		this.appApparatusNo = appApparatusNo;
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
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	@Override
	public String getIsDel() {
		return isDel;
	}
	@Override
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getDemo1() {
		return demo1;
	}
	public void setDemo1(String demo1) {
		this.demo1 = demo1;
	}
	public String getDemo2() {
		return demo2;
	}
	public void setDemo2(String demo2) {
		this.demo2 = demo2;
	}
	public String getReaReagentName() {
		return reaReagentName;
	}
	public void setReaReagentName(String reaReagentName) {
		this.reaReagentName = reaReagentName;
	}
	public String getReaReagentNo() {
		return reaReagentNo;
	}
	public void setReaReagentNo(String reaReagentNo) {
		this.reaReagentNo = reaReagentNo;
	}
	public String getRecordID() {
		return recordID;
	}
	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}
	@Override
	public String getFileName() {
		return fileName;
	}
	@Override
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public String getLabItemName() {
		return labItemName;
	}
	public void setLabItemName(String labItemName) {
		this.labItemName = labItemName;
	}
	public String getLabItemCode() {
		return labItemCode;
	}
	public void setLabItemCode(String labItemCode) {
		this.labItemCode = labItemCode;
	}
	

}
