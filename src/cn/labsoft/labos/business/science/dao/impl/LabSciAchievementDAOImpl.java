package cn.labsoft.labos.business.science.dao.impl;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.business.science.dao.ILabSciAchievementDAO;
import cn.labsoft.labos.business.science.entity.LabSciAchievement;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
@Repository(value="labSciAchievementDAO")
public class LabSciAchievementDAOImpl extends BaseDAO implements
		ILabSciAchievementDAO {

	@Override
	public LabSciAchievement addLabSciAchievement(
			LabSciAchievement labSciAchievement) throws GlobalException {
		try {
			super.save(labSciAchievement);
			return labSciAchievement;
		} catch (Exception ex) {
			Log4J.error("LabSciAchievementDAOImpl add error...."
					+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean deleteLabSciAchievement(String id) throws GlobalException {
		try {
			LabSciAchievement labSciAchievement=(LabSciAchievement) super.findById(LabSciAchievement.class,id);
			labSciAchievement.setIsDel(Constants_Business.Y);
			super.update(labSciAchievement);
			return true;
		} catch (Exception ex) {
			Log4J.error("LabSciAchievementDAOImpl delete error...."
					+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabSciAchievement(LabSciAchievement labSciAchievement)
			throws GlobalException {
		try {
			super.update(labSciAchievement);
			return true;
		} catch (Exception ex) {
			Log4J.error("LabSciAchievementDAOImpl update error...."
					+ ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

}
