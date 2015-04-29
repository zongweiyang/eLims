package cn.labsoft.labos.source.consumables.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.consumables.dao.ILabConCheckMainDAO;
import cn.labsoft.labos.source.consumables.entity.LabConCheckMain;
@Repository(value="labConCheckMainDAO")
public class LabConCheckMainDAOImpl extends BaseDAO implements
		ILabConCheckMainDAO {

	private static Log log = LogFactory.getLog(LabConCheckMainDAOImpl.class);

	@Override
	public LabConCheckMain addLabConCheckMain(LabConCheckMain labConCheckMain) throws GlobalException {
		try {
			super.save(labConCheckMain);
		} catch (Exception ex) {
			log.error("LabConCheckMainDAOImpl add error...." + ex.getMessage(),
					ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labConCheckMain;
	}

}
