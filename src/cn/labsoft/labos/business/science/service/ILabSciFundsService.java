package cn.labsoft.labos.business.science.service;

import java.util.List;
import java.util.Map;

import cn.labsoft.labos.business.science.vo.LabSciFundsVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.IBaseService;

public interface ILabSciFundsService{
	
	public PageResult getLabSciFundsPR(LabSciFundsVo labSciFundsVo,PageResult pageResult)throws GlobalException;
	
	public List<LabSciFundsVo> getLabSciFundsList(LabSciFundsVo labSciFundsVo)throws GlobalException;
	
	public LabSciFundsVo addLabSciFunds(LabSciFundsVo labSciFundsVo)throws GlobalException;
	
	public boolean deleteLabSciFunds(String[] ids)throws GlobalException;
	
	public boolean updateLabSciFunds(LabSciFundsVo labSciFundsVo)throws GlobalException;
	
	public LabSciFundsVo getLabSciFundsVoById(String id)throws GlobalException;
	
	/**
	 * 统计一个项目的支出明目
	 * @param labSicScienceId
	 * @return
	 * @throws GlobalException
	 */
	public String[] getQueryLabSciFunds(String labSicScienceId)throws GlobalException;

}
