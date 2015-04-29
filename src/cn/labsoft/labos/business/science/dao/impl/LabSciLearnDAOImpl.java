package cn.labsoft.labos.business.science.dao.impl;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.business.science.dao.ILabSciLearnDAO;
import cn.labsoft.labos.business.science.entity.LabSciLearn;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
@Repository(value="labSciLearnDAO")
public class LabSciLearnDAOImpl extends BaseDAO implements ILabSciLearnDAO {

	@Override
	public LabSciLearn addLabSciLearn(LabSciLearn labSciLearn)
			throws GlobalException {
		try {
			super.save(labSciLearn);
			return labSciLearn;
		} catch (Exception ex) {
			Log4J.error("LabSciLearnDAOImpl add error...."
					+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean deleteLabSciLearn(String id) throws GlobalException {
		try {
			LabSciLearn labSciLearn=(LabSciLearn) super.findById(LabSciLearn.class,id);
			labSciLearn.setIsDel(Constants_Business.Y);
			super.update(labSciLearn);
			return true;
		} catch (Exception ex) {
			Log4J.error("LabSciLearnDAOImpl delete error...."
					+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabSciLearn(LabSciLearn labSciLearn)
			throws GlobalException {
		try {
			super.update(labSciLearn);
			return true;
		} catch (Exception ex) {
			Log4J.error("LabSciLearnDAOImpl update error...."
					+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

}
