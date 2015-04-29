package cn.labsoft.labos.business.science.service;
import java.util.List;

import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.IBaseService;
import cn.labsoft.labos.business.science.entity.LabSciScience;
import cn.labsoft.labos.business.science.vo.LabSciScienceVo;

public interface ILabSciScienceService{

	/**
	 * 
	 * @return
	 * @throws GlobalException
	 */
	public PageResult getLabSciSciencePR(PageResult pageResult,LabSciScienceVo labSciScienceVo)throws GlobalException;
	
	public List<LabSciScienceVo> getLabSciScienceList(LabSciScience labSciScienceVo)throws GlobalException;
	
	public LabSciScienceVo getLabSciScienceVoById(String id)throws GlobalException;
	
	public LabSciScienceVo addLabSciScience(LabSciScienceVo labSciScienceVo)throws GlobalException;
	
	public boolean updateLabSciScience(LabSciScienceVo labSciScienceVo)throws GlobalException;
	
	public boolean deleteLabSciScience(String[] ids)throws GlobalException;

	public boolean updateLabSciScience4Audit(LabSciScienceVo labSciScienceVo) throws GlobalException;
	
	public PageResult getLabSciSciencePR4IsApply(PageResult pageResult,
			LabSciScienceVo labSciScienceVo) throws GlobalException;
}
