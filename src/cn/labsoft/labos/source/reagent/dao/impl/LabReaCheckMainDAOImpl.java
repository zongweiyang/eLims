package cn.labsoft.labos.source.reagent.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reagent.dao.ILabReaCheckMainDAO;
import cn.labsoft.labos.source.reagent.entity.LabReaCheckMain;
@Repository(value="labReaCheckMainDAO")
public class LabReaCheckMainDAOImpl extends BaseDAO implements
		ILabReaCheckMainDAO {

	private static Log log = LogFactory.getLog(LabReaCheckMainDAOImpl.class);

	@Override
	public LabReaCheckMain addLabReaCheckMain(LabReaCheckMain labReaCheckMain) throws GlobalException {
		try {
			super.save(labReaCheckMain);
			return labReaCheckMain;
		} catch (Exception ex) {
			log.error("LabReaCheckMainDAOImpl ADD error...." + ex.getMessage(),
					ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

}
