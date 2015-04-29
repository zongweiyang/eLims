package cn.labsoft.labos.business.science.dao.impl;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.business.science.dao.ILabSciScienceDAO;
import cn.labsoft.labos.business.science.entity.LabSciScience;
import cn.labsoft.labos.constants.Constants_Business;

@Repository(value="labSciScienceDAO")
public class LabSciScienceDAOImpl extends BaseDAO implements ILabSciScienceDAO{

	@Override
	public LabSciScience addLabSciScience(LabSciScience labSciScience)
			throws GlobalException {
		try {
			super.save(labSciScience);
			return labSciScience;
		} catch (Exception ex) {
			Log4J.error("LabSciScienceDAOImpl add error...."
					+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabSciScience(LabSciScience labSciScience)
			throws GlobalException {
		try {
			super.update(labSciScience);
			return true;
		} catch (Exception ex) {
			Log4J.error("LabSciScienceDAOImpl update error...."
					+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean deleteLabSciScience(String id) throws GlobalException {
		try {
			LabSciScience labSciScience=(LabSciScience) super.findById(LabSciScience.class,id);
			labSciScience.setIsDel(Constants_Business.Y);
			super.update(labSciScience);
			return true;
		} catch (Exception ex) {
			Log4J.error("LabSciScienceDAOImpl delete error...."
					+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
	

}
