package cn.labsoft.labos.source.consumables.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.consumables.dao.ILabConTypeDAO;
import cn.labsoft.labos.source.consumables.entity.LabConType;
@Repository(value="labConTypeDAO")
public class LabConTypeDAOImpl extends BaseDAO implements ILabConTypeDAO {

	private static Log log = LogFactory.getLog(LabConTypeDAOImpl.class);

	@Override
	public LabConType addLabConType(LabConType labConsumablesType) throws GlobalException {
		try {
			super.save(labConsumablesType);
		} catch (Exception ex) {
			log.error("LabConTypeDAOImpl add error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labConsumablesType;
	}

	@Override
	public LabConType updateLabConType(LabConType labConsumablesType) throws GlobalException {
		try {
			super.update(labConsumablesType);
		} catch (Exception ex) {
			log.error("labConsumablesType update error...." + ex.getMessage(),
					ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labConsumablesType;
	}

}
