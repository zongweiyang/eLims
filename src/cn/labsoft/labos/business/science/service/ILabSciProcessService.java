package cn.labsoft.labos.business.science.service;

import java.util.List;
import cn.labsoft.labos.business.science.vo.LabSciProcessVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

public interface ILabSciProcessService {
	@SuppressWarnings("unchecked")
	public LabSciProcessVo addLabSciProcess(LabSciProcessVo labSciProcessVo) throws GlobalException;;
	
	public boolean deleteLabSciProcess (String[] ids) throws GlobalException;
	
	public boolean updateLabSciProcess4Del(String[] ids) throws GlobalException;
	
	public boolean updateLabSciProcess(LabSciProcessVo labSciProcessVo) throws GlobalException;
	
	public LabSciProcessVo getLabSciProcess(String id) throws GlobalException;
	
	public List<LabSciProcessVo> getLabSciProcessList(LabSciProcessVo labSciProcessVo) throws GlobalException;
	
	public PageResult getLabSciProcessPR(LabSciProcessVo labSciProcessVo,PageResult pageResult) throws GlobalException;
	
     }
