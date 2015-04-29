package cn.labsoft.labos.base.code.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import cn.labsoft.labos.base.code.dao.ILabCodeDAO;
import cn.labsoft.labos.base.code.entity.LabCode;
import cn.labsoft.labos.base.code.entity.LabType;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * 
 * @author 浣滆�� bill
 * @version LABOS V1.0
 */
@Repository(value = "labCodeDAO")
public class LabCodeDAOImpl extends BaseDAO implements ILabCodeDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<LabCode> getLabCodeByTypeId(String id) throws GlobalException {
		String hql = "From LabCode po where po.type.id='" + id
				+ "' order by po.sort";
		List<LabCode> poList = super.find(hql);
		return poList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getLcztMapping(String flag, String code, String value) throws GlobalException {
		LabType po = new LabType();
		List<LabType> poList = super.find("From LabType po where code='" + code
				+ "' order by po.sort");
		LabCode commcode = new LabCode();
		if (poList.size() > 0) {
			po = poList.get(0);
			String hql = "From LabCode po where type.id='" + po.getId()
					+ "'  order by po.sort";
			List<LabCode> cpoList = super.find(hql);
			if (cpoList.size() > 0) {
				commcode = cpoList.get(0);
			}
			if (flag.equals("0")) {
				return commcode.getName();
			} else if (flag.equals("1")) {
				return commcode.getCode();
			} else {
				return "";
			}
		} else {
			return "";
		}

	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabCode> getLabCodeByTypeCode(String code) throws GlobalException {
		LabType po = new LabType();
		List<LabType> poList = super.find("From LabType po where code='" + code
				+ "' order by po.sort");
		List<LabCode> list = null;
		if (poList.size() > 0) {
			po = poList.get(0);
			String hql = "From LabCode po where po.labType.id='" + po.getId()
					+ "'  order by po.sort";
			list = super.find(hql);
		}
		return list;
	}


	@Override
	public LabCode addLabCode(LabCode labCode) {
		save(labCode);
		//super.save(labCode);
		return labCode;
	}

	@Override
	public LabCode deleteLabCode(LabCode labCode) {
		labCode.setIsDel(Constants_Common.Y);
		super.update(labCode);
		return labCode;
	}

	@Override
	public LabCode updateLabCode(LabCode labCode) {
		super.update(labCode);
		return labCode;
	}

	@Override
	public LabCode getLabCode(String id) {
		LabCode code = (LabCode) super.findById(LabCode.class, id);
		return code;
	}
}
