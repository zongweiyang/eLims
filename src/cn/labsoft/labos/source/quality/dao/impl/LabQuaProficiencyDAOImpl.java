package cn.labsoft.labos.source.quality.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;


import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.dao.ILabQuaProficiencyDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaProficiency;



@Repository(value="labQuaProficiencyDAO")
public class LabQuaProficiencyDAOImpl extends BaseDAO implements ILabQuaProficiencyDAO{

	@Override
	public LabQuaProficiency getLabQuaProficiency(String id) throws GlobalException {
		try {
			LabQuaProficiency labQuaProficiency=(LabQuaProficiency)super.findById(LabQuaProficiency.class,id);
			return labQuaProficiency;
		} catch (Exception ex) {
			// TODO: handle exception
			Log4J.error("LabQuaProficiencyDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean deleteLabQuaProficiency(LabQuaProficiency labQuaProficiency)throws GlobalException{
		try {
			super.update(labQuaProficiency);
		} catch (Exception ex) {
			Log4J.error("LabQuaProficiencyDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@Override
	public LabQuaProficiency addLabQuaProficiency(LabQuaProficiency labQuaProficiency)
			throws GlobalException {
		try {
			super.save(labQuaProficiency);
			return labQuaProficiency;
		} catch (Exception ex) {
			// TODO: handle exception
			Log4J.error("LabQuaProficiencyDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabQuaProficiency(LabQuaProficiency labQuaProficiency)
			throws GlobalException {
		try {
			super.update(labQuaProficiency);
			return true;
		} catch (Exception ex) {
			Log4J.error("LabQuaProficiencyDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
	@Override
	public PageResult getLabQuaProficiencyPR(String hql, PageResult pageResult)throws GlobalException{
		try {
			pageResult=super.getPageResult(hql,pageResult);
			return pageResult;
		} catch (Exception ex) {
			// TODO: handle exception
			Log4J.error("LabQuaProficiencyDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public List<LabQuaProficiency> getLabQuaProficiencyByHQL(String hql)throws GlobalException{
		return super.find(hql);
		}

}
