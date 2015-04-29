package cn.labsoft.labos.base.code.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.base.code.dao.ILabTypeDAO;
import cn.labsoft.labos.base.code.entity.LabType;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;

/**
 * 
 * @author 作者 bill
 * @version LABCORE V 1.0
 */
@Repository(value = "labTypeDAO")
public class LabTypeDAOImpl extends BaseDAO implements ILabTypeDAO {

	@SuppressWarnings("unchecked")
	@Override
	public LabType getLabTypeByTypeCode(String typeCode) throws GlobalException {
		LabType po = new LabType();
		List<LabType> poList = super.find("From LabType codetype where codetype.code='" + typeCode + "'");
		if (poList.size() > 0) {
			po = poList.get(0);
		}
		return po;
	}

	public LabType addLabType(LabType labType) {
		super.save(labType);
		return labType;
	}

	@Override
	public boolean deleteLabType(LabType labType) throws GlobalException {
		try {
			labType.setIsDel(Constants_Common.Y);
			super.update(labType);
			return true;
		} catch (RuntimeException e) {
			Log4J.error("LabTypeDAOImpl...", e);
			throw new GlobalException("" + e.getMessage());
		}		
	}

	@Override
	public LabType updateLabType(LabType labType) {
		super.update(labType);
		return labType;
	}

	@Override
	public LabType getLabType(String id) {
		LabType type = (LabType) super.findById(LabType.class, id);
		return type;
	}
}
