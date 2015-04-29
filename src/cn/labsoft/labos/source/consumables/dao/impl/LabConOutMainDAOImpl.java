package cn.labsoft.labos.source.consumables.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.consumables.dao.ILabConOutMainDAO;
import cn.labsoft.labos.source.consumables.entity.LabConOutMain;
@Repository(value="labConOutMainDAO")
public class LabConOutMainDAOImpl extends BaseDAO implements ILabConOutMainDAO {

	private static Log log = LogFactory.getLog(LabConOutMainDAOImpl.class);

	@Override
	public LabConOutMain addLabConOutMain(LabConOutMain labConOutMain) throws GlobalException {
		try {
			super.save(labConOutMain);
		} catch (Exception ex) {
			log.error("LabConOutMainDAOImpl add error...." + ex.getMessage(),
					ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labConOutMain;
	}

	@Override
	public LabConOutMain updateLabConOutMain(LabConOutMain labConOutMain) throws GlobalException {
		try {
			super.update(labConOutMain);
		} catch (Exception ex) {
			log.error(
					"LabConOutMainDAOImpl update error...." + ex.getMessage(),
					ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labConOutMain;
	}

}
