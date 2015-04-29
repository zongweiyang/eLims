package cn.labsoft.labos.source.reagent.vo;

import java.util.List;
import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabReaOutMainVo extends BaseVo {

	@ExcelAnnotation(exportName = "ID")
	private String id;
	@ExcelAnnotation(exportName = "单据号")
	private String receiptno; // 单据号
	@ExcelAnnotation(exportName = "出库人")
	private String outstorer; // 出库人
	@ExcelAnnotation(exportName = "出库时间")
	private String outstoreDate;// 出库时间
	@ExcelAnnotation(exportName = "是否删除")
	private String isDel;
	@ExcelAnnotation(exportName = "试剂出库详情列表")
	private List<LabReaOutDetailVo> labReaOutDetailVoList;

	public void setOutstoreDate(String outstoreDate) {
		this.outstoreDate = outstoreDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getReceiptno() {
		return receiptno;
	}

	public void setReceiptno(String receiptno) {
		this.receiptno = receiptno;
	}

	public String getOutstorer() {
		return outstorer;
	}

	public void setOutstorer(String outstorer) {
		this.outstorer = outstorer;
	}

	public String getOutstoreDate() {
		return outstoreDate;
	}

	@Override
	public String getIsDel() {
		return isDel;
	}

	@Override
	public void setIsDel(String isDel) {
		this.isDel = isDel;
	}

	public List<LabReaOutDetailVo> getLabReaOutDetailVoList() {
		return labReaOutDetailVoList;
	}

	public void setLabReaOutDetailVoList(
			List<LabReaOutDetailVo> labReaOutDetailVoList) {
		this.labReaOutDetailVoList = labReaOutDetailVoList;
	}

}
