package cn.labsoft.labos.common.formula.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import cn.labsoft.labos.common.page.entity.LabPageEditor;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;
@Entity
public class LabFormulaApply extends AbstractBasePo {
	private static final long serialVersionUID = 1L;
	public static final String WRITEINPUT="0";//写入
	public static final String WRITEOUTPUT="1";//删除
	private LabFormula labFormula;
	private String demo;//公式内容
	private String parameterId;//参数对应Id
	private LabPageEditor labPageEditor;//应用页面路径
	private String valueId;//对应页面显示值ID
	public String getValueId() {
		return valueId;
	}
	public void setValueId(String valueId) {
		this.valueId = valueId;
	}
	@ManyToOne
	@JoinColumn(name="labPageEditor_id")
	public LabPageEditor getLabPageEditor() {
		return labPageEditor;
	}
	public void setLabPageEditor(LabPageEditor labPageEditor) {
		this.labPageEditor = labPageEditor;
	}
		
	@Transient
	@Override
	public String getTableName() {
		return "组件管理";
	}
	@Transient
	@Override
	public String getModelName() {
		return "公式应用";
	}
	

	public String getDemo() {
		return demo;
	}
	public void setDemo(String demo) {
		this.demo = demo;
	}
	public String getParameterId() {
		return this.parameterId;
	}

	public void setParameterId(String parameterId) {
		this.parameterId = parameterId;
	}
	@ManyToOne
	@JoinColumn(name="formula_id")
	public LabFormula getLabFormula() {
		return labFormula;
	}
	public void setLabFormula(LabFormula labFormula) {
		this.labFormula = labFormula;
	}

}
