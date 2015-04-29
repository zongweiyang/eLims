package cn.labsoft.labos.base.configs.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import cn.labsoft.labos.base.configs.dao.ILabConfigDAO;
import cn.labsoft.labos.base.configs.entity.LabConfig;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
@Repository(value="labConfigDAO")
public class LabConfigDAOImpl extends BaseDAO implements ILabConfigDAO{

	@Override
	public LabConfig addLabConfig(LabConfig po) throws GlobalException {
		try {
			super.save(po);
		} catch (Exception ex) {
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public LabConfig getLabConfigByCode(String code)throws GlobalException{
		LabConfig po=new LabConfig();
		String sql="FROM LabConfig WHERE 1=1 AND code='"+code+"'";
		List<LabConfig> list=super.find(sql);
		if(null!=list&&list.size()>0){
			po=list.get(0);
		}
		return po;
	}

	@Override
	public boolean deleteLabConfig(LabConfig po) throws GlobalException {
		try {
			super.delete(po);
			return true;
		} catch (Exception ex) {
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public LabConfig getLabConfig(String id) throws GlobalException {
		try {
			LabConfig config=(LabConfig)super.findById(LabConfig.class, id);
			return config;
		} catch (Exception ex) {
			Log4J.error("LabConfigDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public LabConfig updateLabConfig(LabConfig po) throws GlobalException {
		try {
			super.update(po);
		} catch (Exception ex) {
			Log4J.error("LabConfigDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return po;
	}
}
