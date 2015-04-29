package cn.labsoft.labos.business.science.service;

import cn.labsoft.labos.business.science.vo.LabSciAuthorVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.service.IBaseService;

public interface ILabSciAuthorService {
	
	public LabSciAuthorVo addLabSciAuthor(LabSciAuthorVo labSciAuthorVo)throws GlobalException;
	
	public boolean updateLabSciAuthor(LabSciAuthorVo labSciAuthorVo)throws GlobalException;

}
