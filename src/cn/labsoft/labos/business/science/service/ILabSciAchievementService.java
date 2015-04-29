package cn.labsoft.labos.business.science.service;

import java.util.List;

import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.user.vo.LabUserVo;
import cn.labsoft.labos.business.science.vo.LabSciAchievementVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.IBaseService;

public interface ILabSciAchievementService {

	public PageResult getLabSciAchievementPR(PageResult pageResult,LabSciAchievementVo labSciAchievementVo)throws GlobalException;
	
	public List<LabSciAchievementVo> getLabSciAchievementList(LabSciAchievementVo labSciAchievementVo)throws GlobalException;
	
	public LabSciAchievementVo addLabSciAchievement(LabSciAchievementVo labSciAchievementVo)throws GlobalException;
	
	public boolean deleteLabSciAchievement(String[] ids)throws GlobalException;
	
	public boolean updateLabSciAchievement(LabSciAchievementVo labSciAchievementVo)throws GlobalException;
	
	public LabSciAchievementVo getLabSciAchievementVo(LabSciAchievementVo labSciAchievementVo)throws GlobalException;
	
	/**
	 * 查询一个项目的不同类型的成果数
	 */
	public String[] getLabSciAchievementShowByType(String labSciScienceId) throws GlobalException;

	/**
	 * 查询一个人的科研成果
	 * @param treeNid
	 * @return
	 * @throws GlobalException
	 */
	public String[] getLabSciAchievementShowByUser(String userId)throws GlobalException;

	/**
	 * 查询所有人的发表的不同类型论文
	 * @return
	 */
	public String[] getLabSciAchievementShowByPaperType(List<LabUserVo> userList,List<LabCodeVo> labcodeList)throws GlobalException;
}
