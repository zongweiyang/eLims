package cn.labsoft.labos.source.reagent.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reagent.dao.ILabReaTypeDAO;
import cn.labsoft.labos.source.reagent.entity.LabReaType;
@Repository(value="labReaTypeDAO")
public class LabReaTypeDAOImpl extends BaseDAO implements ILabReaTypeDAO {

	private static Log log = LogFactory.getLog(LabReaTypeDAOImpl.class);

	@Override
	public LabReaType addLabReaType(LabReaType labReagentType) throws GlobalException {
		try {
			super.save(labReagentType);
		} catch (Exception ex) {
			log.error("LabReaTypeDAOImpl add error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labReagentType;
	}

	@Override
	public LabReaType updateLabReaType(LabReaType labReaType) throws GlobalException {
		try {
			super.update(labReaType);
		} catch (Exception ex) {
			log.error("LabReaTypeDAOImpl add error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labReaType;
	}
}
