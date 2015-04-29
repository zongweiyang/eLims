package cn.labsoft.labos.source.quality.vo;





import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;


/**
 * 质量监督抽查详细表
 */

@SuppressWarnings("serial")
public class LabQuaControlDetailVo  extends BaseVo{

	private String id;
	@ExcelAnnotation(exportName = "监督内容")
	private String comCodeId; 
	private String comCodeName;
	@ExcelAnnotation(exportName = "状态描述")
    private String statusDesc;
	@ExcelAnnotation(exportName = "不合格原因及自理结果")
	private String proResult;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	public String getProResult() {
		return proResult;
	}
	public void setProResult(String proResult) {
		this.proResult = proResult;
	}
	public String getComCodeId() {
		return comCodeId;
	}
	public void setComCodeId(String comCodeId) {
		this.comCodeId = comCodeId;
	}
	public String getComCodeName() {
		return comCodeName;
	}
	public void setComCodeName(String comCodeName) {
		this.comCodeName = comCodeName;
	}
	
}
