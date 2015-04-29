package cn.labsoft.labos.source.reagent.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.reagent.dao.ILabReaCheckDetailDAO;
import cn.labsoft.labos.source.reagent.entity.LabReaCheckDetail;
@Repository(value="labReaCheckDetailDAO")
public class LabReaCheckDetailDAOImpl extends BaseDAO implements
		ILabReaCheckDetailDAO {
	private static Log log = LogFactory.getLog(LabReaCheckDetailDAOImpl.class);

	@Override
	public LabReaCheckDetail addLabReaCheckDetail(
			LabReaCheckDetail labReaCheckDetail) throws GlobalException {
		try {
			super.save(labReaCheckDetail);
			return labReaCheckDetail;
		} catch (Exception ex) {
			log.error("LabReaCheckDetailDAOImpl ADD error...."
					+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

}
