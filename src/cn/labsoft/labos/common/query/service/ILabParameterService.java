package cn.labsoft.labos.common.query.service;

import cn.labsoft.labos.common.query.vo.LabParameterVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;

public interface ILabParameterService {
	@SuppressWarnings("unchecked")
	
	public LabParameterVo getLabParameter(String id) throws GlobalException;
	
 }
