package cn.labsoft.labos.source.reference.dao.impl;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.reference.dao.ILabRefCheckDetailDAO;
import cn.labsoft.labos.source.reference.entity.LabRefCheckDetail;

@Repository(value="labRefCheckDetailDAO")
public class LabRefCheckDetailDAOImpl extends BaseDAO implements
		ILabRefCheckDetailDAO {

	@Override
	public LabRefCheckDetail addLabRefCheckDetail(
			LabRefCheckDetail labRefCheckDetail) throws GlobalException {
		try {
			super.save(labRefCheckDetail);
		} catch (Exception ex) {
			Log4J.error("LabRefCheckDetailDAOImpl add error...."
					+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labRefCheckDetail;
	}

}
