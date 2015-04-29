package cn.labsoft.labos.common.workflow.dao.impl;


import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.workflow.dao.ILabWfPathInsDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfPathIns;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;



/**
 * 条件路径定义实例数据访问层
 * @author MyEclipse Persistence Tools
 */
@Repository(value="labWfPathInsDAO")
public class LabWfPathInsDAOImpl extends BaseDAO implements ILabWfPathInsDAO{


	@Override
	public LabWfPathIns getWfPathInsByIds(String insId,String pathId) throws GlobalException {
		String hql="FROM LabWfPathIns WHERE processInsId='"+insId+"' AND path.id='"+pathId+"'";
		LabWfPathIns po=(LabWfPathIns)super.find(hql, 0);
		return po;
	}
	
}