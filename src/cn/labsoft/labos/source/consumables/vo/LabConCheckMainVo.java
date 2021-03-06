package cn.labsoft.labos.source.consumables.vo;

import java.util.List;
import cn.labsoft.labos.framework.common.vo.BaseVo;
import cn.labsoft.labos.utils.exportexcel.ExcelAnnotation;

public class LabConCheckMainVo extends BaseVo {

	private static final long serialVersionUID = 1L;
	@ExcelAnnotation(exportName = "ID")
	private String id;
	@ExcelAnnotation(exportName = "盘点号")
	private String checkno;
	@ExcelAnnotation(exportName = "盘点主题")
	private String name;
	@ExcelAnnotation(exportName = "盘点人")
	private String checker;
	@ExcelAnnotation(exportName = "盘点时间")
	private String checktime;
	@ExcelAnnotation(exportName = "备注")
	private String remark;
	@ExcelAnnotation(exportName = "盘点耗材列表")
	private List<LabConCheckDetailVo> labConCheckDetailVoList;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCheckno() {
		return checkno;
	}

	public void setCheckno(String checkno) {
		this.checkno = checkno;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public String getChecktime() {
		return checktime;
	}

	public void setChecktime(String checktime) {
		this.checktime = checktime;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<LabConCheckDetailVo> getLabConCheckDetailVoList() {
		return labConCheckDetailVoList;
	}

	public void setLabConCheckDetailVoList(
			List<LabConCheckDetailVo> labConCheckDetailVoList) {
		this.labConCheckDetailVoList = labConCheckDetailVoList;
	}

}