package cn.labsoft.labos.source.quality.entity;



import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import cn.labsoft.labos.framework.common.po.AbstractBasePo;


/**质量监督抽查详细表
 */

@Entity
@Table(name="lab_qua_control_detail")
public class LabQuaControlDetail  extends AbstractBasePo{

	private LabQuaControl  labQuaControl;
	private String comCodeId; 
	private String comCodeName;
    private String statusDesc;
	private String proResult;
	
	@Transient
	@Override
	public String getModelName() {
		// TODO Auto-generated method stub
		return "质量管理";
	}


	@Transient
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "质量监督表(详细)";
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

	@ManyToOne
	@JoinColumn(name="lab_qua_control_id")
	public LabQuaControl getLabQuaControl() {
		return labQuaControl;
	}


	public void setLabQuaControl(LabQuaControl labQuaControl) {
		this.labQuaControl = labQuaControl;
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
