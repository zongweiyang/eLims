package cn.labsoft.labos.common.workflow.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.workflow.dao.ILabWfPathDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfPath;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;

/**
 * 条件路径定义数据访问层
 * @author MyEclipse Persistence Tools
 */
@Repository(value="labWfPathDAO")
public class LabWfPathDAOImpl  extends BaseDAO implements ILabWfPathDAO{

	@Override
	public LabWfPath addPath(LabWfPath po) throws GlobalException {
		try {
			super.save(po);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return po;
	}

	@Override
	public boolean delPath(LabWfPath po) throws GlobalException {
		try {
			super.delete(po);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}



	@SuppressWarnings("unchecked")
	@Override
	public List<LabWfPath> getPathListByBusId(String busid) throws GlobalException {
		String hql="FROM LabWfPath WHERE processId='"+busid+"' ";
		List<LabWfPath> poList=super.find(hql);
		return poList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabWfPath> getPathList(String startStepId,String endStepId) throws GlobalException {
		String hql="FROM LabWfPath WHERE 1=1";
		if(null!=startStepId&&!"".equals(startStepId)){
			hql+=" AND startStep.id='"+startStepId+"'";
		}
		if(null!=endStepId&&!"".equals(endStepId)){
			hql+=" AND endStep.id='"+endStepId+"'";
		}
		List<LabWfPath> poList=super.find(hql);
		return poList;
	}

}