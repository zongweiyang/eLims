package cn.labsoft.labos.source.reagent.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reagent.dao.ILabReaOutMainDAO;
import cn.labsoft.labos.source.reagent.entity.LabReaOutMain;
@Repository(value="labReaOutMainDAO")
public class LabReaOutMainDAOImpl extends BaseDAO implements ILabReaOutMainDAO {

	private static Log log = LogFactory.getLog(LabReaOutMainDAOImpl.class);

	@Override
	public LabReaOutMain addLabReaOutMain(LabReaOutMain labReaOutMain) throws GlobalException {
		try {
			super.save(labReaOutMain);
			return labReaOutMain;
		} catch (Exception ex) {
			log.error("LabReaOutMainDAOImpl save...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public LabReaOutMain updateLabReaOutMain(LabReaOutMain po) throws GlobalException {
		try {
			super.update(po);
			return po;
		} catch (Exception ex) {
			log.error("LabReaOutMainDAOImpl update...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

}
