package cn.labsoft.labos.business.science.dao.impl;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.business.science.dao.ILabSciFundsDAO;
import cn.labsoft.labos.business.science.entity.LabSciFunds;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;

@Repository(value="labSciFundsDAO")
public class LabSciFundsDAOImpl extends BaseDAO implements ILabSciFundsDAO {

	@Override
	public LabSciFunds addLabSciFunds(LabSciFunds labSciFunds)
			throws GlobalException {
		try {
			super.save(labSciFunds);
			return labSciFunds;
		} catch (Exception ex) {
			Log4J.error("LabSciFundsDAOImpl add error...."
					+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean deleteLabSciFunds(String id)
			throws GlobalException {
		try {
			LabSciFunds labSciFunds=(LabSciFunds) super.findById(LabSciFunds.class,id);
			labSciFunds.setIsDel(Constants_Business.Y);
			super.update(labSciFunds);
			return true;
		} catch (Exception ex) {
			Log4J.error("LabSciFundsDAOImpl delete error...."
					+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabSciFunds(LabSciFunds labSciFunds)
			throws GlobalException {
		try {
			super.update(labSciFunds);
			return true;
		} catch (Exception ex) {
			Log4J.error("LabSciFundsDAOImpl update error...."
					+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

}
