package cn.labsoft.labos.common.workflow.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.workflow.dao.ILabWfStepDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfStep;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;


/**
 * 步骤定义 数据访问层
 * @author MyEclipse Persistence Tools
 */
@Repository(value="labWfStepDAO")
public class LabWfStepDAOImpl extends BaseDAO implements ILabWfStepDAO{
	

	@Override
	public LabWfStep addWfStep(LabWfStep po) throws GlobalException {
		try {
			super.save(po);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return po;
	}

	@Override
	public boolean delWfStep(LabWfStep po) throws GlobalException {
		try {
			super.delete(po);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

	@Override
	public LabWfStep getWfStepById(String id) throws GlobalException {
		LabWfStep po=null;
		try {
			po=(LabWfStep)super.findById(LabWfStep.class, id);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return po;
	}
	@Override
	public boolean updateWfStep(LabWfStep po) throws GlobalException {
		try {
			super.update(po);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabWfStep> getStepAllListByBusId(String busid) throws GlobalException {
		String hql="FROM LabWfStep WHERE processId='"+busid+"' ORDER BY num ASC";
		List<LabWfStep> poList=super.find(hql);
		return poList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabWfStep> getStepListByBusId(String busid) throws GlobalException {
		String hql="FROM LabWfStep WHERE processId='"+busid+"' AND type='Node' ORDER BY num ASC";
		List<LabWfStep> poList=super.find(hql);
		return poList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public LabWfStep getWfStepBybusIdAndUuid(String busId, String uuid) throws GlobalException {
		String hql="FROM LabWfStep WHERE processId='"+busId+"' AND uuid='"+uuid+"'" +
				" ORDER BY num ASC";
		List<LabWfStep> poList=super.find(hql);
		if(poList!=null&&poList.size()>0){
			return poList.get(0);
		}else{
			return null;
		}
	}

}