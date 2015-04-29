package cn.labsoft.labos.common.workflow.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.workflow.dao.ILabWfVariableDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfVariable;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;


/**
 * 变量定义 数据访问层
 * @author MyEclipse Persistence Tools
 */
@Repository(value="labWfVariableDAO")
public class LabWfVariableDAOImpl extends BaseDAO implements ILabWfVariableDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<LabWfVariable> getLabWfVarListByBusId(String busid) throws GlobalException {
		String hql="FROM LabWfVariable WHERE processId='"+busid+"'";
		return super.find(hql);
	}

	@Override
	public LabWfVariable addLabWfVariable(LabWfVariable labWfVariable) throws GlobalException {
		try {
			super.save(labWfVariable);
			return labWfVariable;
		} catch (RuntimeException e) {
			Log4J.error("LabWfVariableDAOImpl", e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public boolean deleteLabWfVariable(LabWfVariable labWfVariable) throws GlobalException {
		try {
			super.delete(labWfVariable);
			return true;
		} catch (RuntimeException e) {
			Log4J.error("LabWfVariableDAOImpl", e);
			throw new GlobalException("" + e.getMessage());
		}
	}
	
	
}