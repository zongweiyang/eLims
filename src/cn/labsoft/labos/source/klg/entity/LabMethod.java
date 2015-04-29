package cn.labsoft.labos.source.klg.entity;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;


import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@Entity
@Table(name="lab_klg_method")
public class LabMethod extends AbstractBasePo {
	
	private String name;
	private String code;
	private Double price;
	private String context;//方法内容
	private String remark;
    private String recordID;//记录标识
	private String fileName;//模板名称
	private String coordinate;//坐标
	private String filterName;//过滤字段
	private String demo1;//不确定度
	private String demo2;
	private String labApparaId;
	private String labApparaName;
	private int sort; //序号
	
	
	@Transient
	@Override
	public String getModelName() {
		return "标准管理";
	}
	@Transient
	@Override
	public String getTableName() {
		return "方法标准";
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
	public String getContext() {
		return context;
	}
	public void setContext(String context) {
		this.context = context;
	}
	public String getRecordID() {
		return recordID;
	}
	public void setRecordID(String recordID) {
		this.recordID = recordID;
	}
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
}
