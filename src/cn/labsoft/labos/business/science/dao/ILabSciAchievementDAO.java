package cn.labsoft.labos.business.science.dao;

import cn.labsoft.labos.business.science.entity.LabSciAchievement;
import cn.labsoft.labos.framework.common.dao.IBaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;

public interface ILabSciAchievementDAO extends IBaseDAO {
	
	public LabSciAchievement addLabSciAchievement(LabSciAchievement labSciAchievement)throws GlobalException;
	
	public boolean deleteLabSciAchievement(String id)throws GlobalException;
	
	public boolean updateLabSciAchievement(LabSciAchievement labSciAchievement)throws GlobalException;
	

}
