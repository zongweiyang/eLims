package cn.labsoft.labos.business.science.service;

import java.util.List;

import cn.labsoft.labos.business.science.vo.LabSciLearnVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.IBaseService;

public interface ILabSciLearnService{
	
	public PageResult getLabSciLearnPR(LabSciLearnVo labSciLearnVo,PageResult pageResult)throws GlobalException;
	
	public List<LabSciLearnVo> getLabSciLearnList(LabSciLearnVo labSciLearnVo)throws GlobalException;
	
	public LabSciLearnVo addLabSciLearn(LabSciLearnVo labSciLearnVo)throws GlobalException;
	
	public boolean updateLabSciLearn(LabSciLearnVo labSciLearnVo)throws GlobalException;
	
	public boolean deleteLabSciLearn(String[] ids)throws GlobalException;
	
	public LabSciLearnVo getLabSciLearn(LabSciLearnVo labSciLearnVo)throws GlobalException;
}
