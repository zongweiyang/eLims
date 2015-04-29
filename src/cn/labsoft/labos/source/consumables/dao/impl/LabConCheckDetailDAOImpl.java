package cn.labsoft.labos.source.consumables.dao.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.consumables.dao.ILabConCheckDetailDAO;
import cn.labsoft.labos.source.consumables.entity.LabConCheckDetail;
@Repository(value="labConCheckDetailDAO")
public class LabConCheckDetailDAOImpl extends BaseDAO implements
		ILabConCheckDetailDAO {
	private static Log log = LogFactory.getLog(LabConCheckDetailDAOImpl.class);

	@Override
	public LabConCheckDetail addLabConCheckDetail(
			LabConCheckDetail labConCheckDetail) throws GlobalException {
		try {
			super.save(labConCheckDetail);
		} catch (Exception ex) {
			log.error("LabConCheckDetailDAOImpl add error...."
					+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labConCheckDetail;
	}

}
