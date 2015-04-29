package cn.labsoft.labos.source.klg.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.klg.dao.ILabStandardDAO;
import cn.labsoft.labos.source.klg.entity.LabStandard;
import cn.labsoft.labos.utils.DateUtils;

@Repository(value="labStandardDAO")
public class LabStandardDAOImpl extends BaseDAO implements ILabStandardDAO {
	@Override
	public LabStandard getLabStandard(String id) {
		return (LabStandard)super.findById(LabStandard.class, id);
	}
	
	@Override
	public LabStandard getLabStandardByName(String name) throws GlobalException {
		String hql = "FROM LabStandard WHERE name = '"+name.trim()+"' AND isDel = '"+Constants_Source.N+"' "; 
		List<LabStandard> list = super.find(hql);
		return null!=list&&list.size()>0?list.get(0):null;
	}
	

	@Override
	public LabStandard getLabStandardByCode(String code) throws GlobalException {
		String hql = "FROM LabStandard WHERE code = '"+code.trim()+"' AND isDel = '"+Constants_Source.N+"' "; 
		List<LabStandard> list = super.find(hql);
		return null!=list&&list.size()>0?list.get(0):null;
	}
	@Override
	public LabStandard addLabStandard(LabStandard labStandard) throws GlobalException {
		try {
			labStandard.setIsDel(Constants_Source.N);
			labStandard.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labStandard);
		} catch (Exception ex) {
			Log4J.error("labNumberDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labStandard;
	}
	@Override
	public boolean updateLabStandard(LabStandard labStandard) throws GlobalException {
		try {
			super.update(labStandard);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labNumberDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
}
