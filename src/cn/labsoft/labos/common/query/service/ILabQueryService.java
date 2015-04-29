package cn.labsoft.labos.common.query.service;

import java.util.List;

import cn.labsoft.labos.common.query.vo.LabParameterVo;
import cn.labsoft.labos.common.query.vo.LabQueryVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;

public interface ILabQueryService {
	@SuppressWarnings("unchecked")
	public LabQueryVo addLabQuery(LabQueryVo labQueryVo) throws GlobalException;;
	
	
	public boolean deleteLabQuery(String[] ids) throws GlobalException;
	public boolean updateLabQuery4Del(String[] ids) throws GlobalException;
	
	public boolean updateLabQuery(LabQueryVo labQueryVo) throws GlobalException;
	
	public LabQueryVo getLabQuery(String id) throws GlobalException;
	
	public List<LabQueryVo> getLabQueryList(LabQueryVo labQueryVo) throws GlobalException;
	
	public PageResult getLabQueryPR(LabQueryVo labQueryVo,PageResult pageResult) throws GlobalException;
	
	public List<LabParameterVo> getProperty(LabQueryVo labQueryVo)throws GlobalException;

	public LabQueryVo runLabQueryJsp(LabQueryVo labQueryVo) throws GlobalException;
	
     }
