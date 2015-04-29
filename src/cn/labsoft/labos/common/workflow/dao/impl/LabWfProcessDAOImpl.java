package cn.labsoft.labos.common.workflow.dao.impl;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.workflow.dao.ILabWfProcessDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfProcess;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
/**
 *流程定义 数据访问层
 * @author MyEclipse Persistence Tools
 */
@Repository(value="labWfProcessDAO")
public class LabWfProcessDAOImpl extends BaseDAO implements ILabWfProcessDAO{

	@Override
	public LabWfProcess addWfProcess(LabWfProcess po) throws GlobalException {
		try {
			super.save(po);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return po;
	}

	@Override
	public boolean delWfProcess(LabWfProcess po) throws GlobalException {
		try {
			super.delete(po);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

	@Override
	public LabWfProcess getWfProcess(String id) throws GlobalException {
		LabWfProcess po=null;
		try {
			po=(LabWfProcess)super.findById(LabWfProcess.class, id);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return po;
	}

	@Override
	public boolean updateWfProcess(LabWfProcess po) throws GlobalException {
		try {
			super.update(po);
		} catch (RuntimeException e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

}