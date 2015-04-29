package cn.labsoft.labos.source.reference.dao.impl;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.reference.dao.ILabRefOutMainDAO;
import cn.labsoft.labos.source.reference.entity.LabRefOutMain;

@Repository(value="labRefOutMainDAO")
public class LabRefOutMainDAOImpl extends BaseDAO implements ILabRefOutMainDAO {


	@Override
	public LabRefOutMain addLabRefOutMain(LabRefOutMain labRefOutMain) throws GlobalException {
		try {
			super.save(labRefOutMain);
		} catch (Exception ex) {
			Log4J.error("LabRefOutMainDAOImpl add error...." + ex.getMessage(),
					ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labRefOutMain;
	}

	@Override
	public LabRefOutMain updateLabRefOutMain(LabRefOutMain po) throws GlobalException {
		try {
			super.update(po);
		} catch (Exception ex) {
			Log4J.error(
					"LabRefOutMainDAOImpl update error...." + ex.getMessage(),
					ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

}
