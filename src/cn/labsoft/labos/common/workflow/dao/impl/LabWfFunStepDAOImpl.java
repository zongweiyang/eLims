package cn.labsoft.labos.common.workflow.dao.impl;


import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.workflow.dao.ILabWfFunStepDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfFunStep;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;


/**
 * 步骤定义 数据访问层
 * @author MyEclipse Persistence Tools
 */
@Repository(value="labWfFunStepDAO")
public class LabWfFunStepDAOImpl extends BaseDAO implements ILabWfFunStepDAO{
	

	@Override
	public LabWfFunStep addLabWfFunStep(LabWfFunStep po) throws GlobalException {
		try {
			super.save(po);
		} catch (RuntimeException e) {
			Log4J.error("LabWfFunStepDAOImpl"+e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		return po;
	}

	@Override
	public boolean delLabWfFunStep(LabWfFunStep po) throws GlobalException {
		try {
			super.delete(po);
		} catch (RuntimeException e) {
			Log4J.error("LabWfFunStepDAOImpl"+e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

	@Override
	public LabWfFunStep getLabWfFunStepById(String id) throws GlobalException {
		LabWfFunStep po=null;
		try {
			po=(LabWfFunStep)super.findById(LabWfFunStep.class, id);
		} catch (RuntimeException e) {
			Log4J.error("LabWfFunStepDAOImpl"+e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		return po;
	}
	@Override
	public boolean updateLabWfFunStep(LabWfFunStep po) throws GlobalException {
		try {
			super.update(po);
		} catch (RuntimeException e) {
			Log4J.error("LabWfFunStepDAOImpl"+e.getMessage());
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}


}