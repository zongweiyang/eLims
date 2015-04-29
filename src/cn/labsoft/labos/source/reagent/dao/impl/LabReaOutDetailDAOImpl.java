package cn.labsoft.labos.source.reagent.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reagent.dao.ILabReaOutDetailDAO;
import cn.labsoft.labos.source.reagent.entity.LabReaOutDetail;
@Repository(value="labReaOutDetailDAO")
public class LabReaOutDetailDAOImpl extends BaseDAO implements
		ILabReaOutDetailDAO {

	private static Log log = LogFactory.getLog(LabReaOutDetailDAOImpl.class);

	@Override
	public LabReaOutDetail addLabReaOutDetail(LabReaOutDetail labReaOutDetail) throws GlobalException {
		try {
			super.save(labReaOutDetail);
			return labReaOutDetail;
		} catch (Exception ex) {
			log.error("LabReaOutDetailDAOImpl save...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

}
