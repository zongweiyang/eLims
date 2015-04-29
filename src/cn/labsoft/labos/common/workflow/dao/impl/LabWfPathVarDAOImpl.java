package cn.labsoft.labos.common.workflow.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.workflow.dao.ILabWfPathVarDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfPathVar;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;


/**
 * 迁移变量关联定义 数据访问层
 * @author MyEclipse Persistence Tools
 */
@Repository(value="labWfPathVarDAO")
public class LabWfPathVarDAOImpl extends BaseDAO implements ILabWfPathVarDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<LabWfPathVar> getPathVarDefList(String pathId) throws GlobalException {
		String hql="FROM LabWfPathVar WHERE pathId='"+pathId+"'";
		return super.find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabWfPathVar> getPathVarListByBusId(String busId) throws GlobalException {
		String hql="FROM LabWfPathVar WHERE processId='"+busId+"'";
		return super.find(hql);
	}

	@Override
	public LabWfPathVar addLabWfPathVar(LabWfPathVar labWfPathVar) throws GlobalException {
		try {
			super.save(labWfPathVar);
			return labWfPathVar;
		} catch (RuntimeException e) {
			Log4J.error("LabWfPathVarDAOImpl", e);
			throw new GlobalException("" + e.getMessage());
		}
	}

	@Override
	public boolean deleteLabWfPathVar(LabWfPathVar labWfPathVar) throws GlobalException {
		try {
			super.delete(labWfPathVar);
			return true;
		} catch (RuntimeException e) {
			Log4J.error("LabWfPathVarDAOImpl", e);
			throw new GlobalException("" + e.getMessage());
		}
	}
	
}