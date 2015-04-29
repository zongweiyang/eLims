package cn.labsoft.labos.common.query.service.impl;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.common.query.dao.ILabParameterDAO;
import cn.labsoft.labos.common.query.entity.LabParameter;
import cn.labsoft.labos.common.query.service.ILabParameterService;
import cn.labsoft.labos.common.query.vo.LabParameterVo;
import cn.labsoft.labos.framework.common.exception.GlobalException;
@Service("labParameterService")
public class LabParameterServiceImpl implements ILabParameterService {
	private ILabParameterDAO labParameterDAO;
	@Override
	public LabParameterVo getLabParameter(String id) throws GlobalException {
		// TODO Auto-generated method stub
		LabParameterVo labParameterVo=new LabParameterVo();
		
		return po2vo(labParameterDAO.getLabParameter(id),labParameterVo);
	}
	public LabParameterVo po2vo(LabParameter labParameter,LabParameterVo labParameterVo){
		BeanUtils.copyProperties(labParameter, labParameterVo,new String[]{"labQuery"});
		if(labParameter.getLabQuery()!=null){
			labParameterVo.setLabQueryId(labParameter.getLabQuery().getId());
  		}
		return labParameterVo;
	}
	@Resource
	public void setLabParameterDAO(ILabParameterDAO labParameterDAO) {
		this.labParameterDAO = labParameterDAO;
	}

	
}
