package cn.labsoft.labos.source.consumables.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.consumables.dao.ILabConsumablesDAO;
import cn.labsoft.labos.source.consumables.entity.LabConsumables;
@Repository(value="labConsumablesDAO")
public class LabConsumablesDAOImpl extends BaseDAO implements
		ILabConsumablesDAO {

	private static Log log = LogFactory.getLog(LabConsumablesDAOImpl.class);

	@Override
	public LabConsumables addLabConsumables(LabConsumables po) throws GlobalException {
		try {
			super.save(po);
		} catch (Exception ex) {
			log.error("LabConsumablesDAOImpl add error...." + ex.getMessage(),
					ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}

	@Override
	public LabConsumables updateLabConsumables(LabConsumables labConsumables) {
		try {
			super.update(labConsumables);
		} catch (Exception ex) {
			log.error("LabConsumablesDAOImpl update error...."
					+ ex.getMessage(), ex);
		}
		return labConsumables;
	}

}
