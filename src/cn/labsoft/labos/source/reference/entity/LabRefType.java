package cn.labsoft.labos.source.reference.entity;


import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;


import cn.labsoft.labos.framework.common.po.AbstractBasePo;

@Entity
@Table(name="lab_ref_type")
public class LabRefType extends AbstractBasePo{

	private LabRefType type;
	private String name;

	@ManyToOne
	@JoinColumn(name="type_id")
	public LabRefType getType() {
		return type;
	}

	public void setType(LabRefType type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Transient
	@Override
	public String getModelName() {
		// TODO Auto-generated method stub
		return "标准品管理";
	}

	@Transient
	@Override
	public String getTableName() {
		// TODO Auto-generated method stub
		return "标准品类型";
	}

}
