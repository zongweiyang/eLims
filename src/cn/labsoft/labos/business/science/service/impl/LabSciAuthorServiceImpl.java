package cn.labsoft.labos.business.science.service.impl;

import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import cn.labsoft.labos.business.science.dao.ILabSciAuthorDAO;
import cn.labsoft.labos.business.science.entity.LabSciAuthor;
import cn.labsoft.labos.business.science.entity.LabSciScience;
import cn.labsoft.labos.business.science.service.ILabSciAuthorService;
import cn.labsoft.labos.business.science.vo.LabSciAuthorVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.utils.BeanUtils;
import cn.labsoft.labos.utils.StrUtils;
@Service("labSciAuthorService")
public class LabSciAuthorServiceImpl extends BaseService implements
		ILabSciAuthorService {

	private ILabSciAuthorDAO labSciAuthorDAO;
	
	@Resource
	public void setLabSciAuthorDAO(ILabSciAuthorDAO labSciAuthorDAO) {
		this.labSciAuthorDAO = labSciAuthorDAO;
	}
    
	public LabSciAuthorVo poToVo(LabSciAuthor labSciAuthor)throws GlobalException
	{
       LabSciAuthorVo labSciAuthorVo=new LabSciAuthorVo();
       BeanUtils.copyProperties(labSciAuthor, labSciAuthorVo, new String[]{"labSciScience"});
       if (null!=labSciAuthor.getLabSciScience()) {
		labSciAuthorVo.setLabSciScienceId(labSciAuthor.getLabSciScience().getId());
	   }
       return labSciAuthorVo;
	}
	
	public LabSciAuthor voTOpo(LabSciAuthorVo labSciAuthorVo)throws GlobalException
	{
		LabSciAuthor labSciAuthor;
	  if (StrUtils.isBlankOrNull(labSciAuthorVo.getId())) {
		labSciAuthor=new LabSciAuthor();
	  }else {
		  labSciAuthor= (LabSciAuthor) labSciAuthorDAO.findById(LabSciAuthor.class, labSciAuthorVo.getId());
	  }
	  BeanUtils.copyProperties(labSciAuthorVo, labSciAuthor, new String[]{"isDel","createTime","tenantId","createUserId"});
	  if(!StrUtils.isBlankOrNull(labSciAuthorVo.getLabSciScienceId()))
	  {
		  LabSciScience labSciScience=(LabSciScience) labSciAuthorDAO.findById(LabSciScience.class, labSciAuthorVo.getLabSciScienceId());
		  labSciAuthor.setLabSciScience(labSciScience);
	  }
	  return labSciAuthor;
	}
	
	@Override
	public LabSciAuthorVo addLabSciAuthor(LabSciAuthorVo labSciAuthorVo)
			throws GlobalException {	
		return poToVo(labSciAuthorDAO.addLabSciAuthor(voTOpo(labSciAuthorVo)))       ;
	}

	@Override
	public boolean updateLabSciAuthor(LabSciAuthorVo labSciAuthorVo)
			throws GlobalException {
		return labSciAuthorDAO.updateLabSciAuthor(voTOpo(labSciAuthorVo));
	}

}
