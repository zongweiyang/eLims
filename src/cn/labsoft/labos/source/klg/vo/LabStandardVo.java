package cn.labsoft.labos.source.klg.vo;

import java.util.List;

import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;
/**
 * 
 * @title 产品标准 LabStandardVo
 * @author QUINN
 */
@SuppressWarnings({ "unchecked", "serial","unused","static-access" })
public class LabStandardVo extends BaseVo {

	private String id;
	@ExcelAnnotation(exportName = "标准名称")
	private String name;  
	private String ename;  //英文名称
	@ExcelAnnotation(exportName = "标准编号")
	private String code;   
	
	private String searchName;  //检索名称
	private String searchCode;   //检索标准号
	
	private String standTypeId;//标准类别
	private String standTypeName;//标准类别
	private String standIndexIds;//产品分类或级别
	private String standIndexNames;//产品分类或级别
	private String standStatus;//标准状态 
	@ExcelAnnotation(exportName = "是否启用")
	private String isUsed; //是否启用
	
	private String fileurl;  //标准文件路径
	private String filename; //标准文件名字
	private String filetruename; //标准文件路径
	private String remake;
	@ExcelAnnotation(exportName = "颁布日期")
	private String releaseDate;
	@ExcelAnnotation(exportName = "实施日期")
	private String materialDate;
	@ExcelAnnotation(exportName = "代替标准")
	private String replaceName;
	private String replaceIds;
	private String type;//标准类型
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	private int sort;
	@ExcelAnnotation(exportName = "类别")
	private String standIndex;
	
	private String demo1;
	private String demo2;
	private String demo3;
	
	private String itemIsDefault;
	private List<LabStandardIndexVo> indexList;
	private List<LabStandardItemVo> standItemList;
	protected List list;
	
	public List getList() {
		return list;
	}
	public void setList(List list) {
		this.list = list;
	}
	public String getItemIsDefault() {
		return itemIsDefault;
	}
	public void setItemIsDefault(String itemIsDefault) {
		this.itemIsDefault = itemIsDefault;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getReplaceName() {
		return replaceName;
	}
	public void setReplaceName(String replaceName) {
		this.replaceName = replaceName;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getMaterialDate() {
		return materialDate;
	}
	public void setMaterialDate(String materialDate) {
		this.materialDate = materialDate;
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
	public String getFileurl() {
		return fileurl;
	}
	public void setFileurl(String fileurl) {
		this.fileurl = fileurl;
	}
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	public String getFiletruename() {
		return filetruename;
	}
	public void setFiletruename(String filetruename) {
		this.filetruename = filetruename;
	}
	public String getRemake() {
		return remake;
	}
	public void setRemake(String remake) {
		this.remake = remake;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public String getEname() {
		return ename;
	}
	public void setEname(String ename) {
		this.ename = ename;
	}
	public String getStandTypeId() {
		return standTypeId;
	}
	public void setStandTypeId(String standTypeId) {
		this.standTypeId = standTypeId;
	}
	public String getStandTypeName() {
		return standTypeName;
	}
	public void setStandTypeName(String standTypeName) {
		this.standTypeName = standTypeName;
	}
	public String getStandIndexIds() {
		return standIndexIds;
	}
	public void setStandIndexIds(String standIndexIds) {
		this.standIndexIds = standIndexIds;
	}
	public String getStandIndexNames() {
		return standIndexNames;
	}
	public void setStandIndexNames(String standIndexNames) {
		this.standIndexNames = standIndexNames;
	}
	public List<LabStandardIndexVo> getIndexList() {
		return indexList;
	}
	public void setIndexList(List<LabStandardIndexVo> indexList) {
		this.indexList = indexList;
	}
	public String getSearchName() {
		return searchName;
	}
	public void setSearchName(String searchName) {
		this.searchName = searchName;
	}
	public String getSearchCode() {
		return searchCode;
	}
	public void setSearchCode(String searchCode) {
		this.searchCode = searchCode;
	}
	public List<LabStandardItemVo> getStandItemList() {
		return standItemList;
	}
	public void setStandItemList(List<LabStandardItemVo> standItemList) {
		this.standItemList = standItemList;
	}
	public String getStandIndex() {
		return standIndex;
	}
	public void setStandIndex(String standIndex) {
		this.standIndex = standIndex;
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
	public String getDemo3() {
		return demo3;
	}
	public void setDemo3(String demo3) {
		this.demo3 = demo3;
	}
	public String getStandStatus() {
		return standStatus;
	}
	public void setStandStatus(String standStatus) {
		this.standStatus = standStatus;
	}
	public String getReplaceIds() {
		return replaceIds;
	}
	public void setReplaceIds(String replaceIds) {
		this.replaceIds = replaceIds;
	}
	public String getIsUsed() {
		return isUsed;
	}
	public void setIsUsed(String isUsed) {
		this.isUsed = isUsed;
	}

	
}