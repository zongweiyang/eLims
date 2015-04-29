package cn.labsoft.labos.source.quality.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.dao.ILabQuaComplainDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaComplain;


@Repository(value="labQuaComplainDAO")
public class LabQuaComplainDAOImpl extends BaseDAO implements ILabQuaComplainDAO{

	@Override
	public LabQuaComplain getLabQuaComplain(String id) throws GlobalException {
		try {
			LabQuaComplain labQuaComplain=(LabQuaComplain)super.findById(LabQuaComplain.class,id);
			return labQuaComplain;
		} catch (Exception ex) {
			Log4J.error("LabQuaComplainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean deleteLabQuaComplain(LabQuaComplain labQuaComplain)throws GlobalException{
		try {
			super.update(labQuaComplain);
		} catch (Exception ex) {
			Log4J.error("LabQuaComplainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@Override
	public LabQuaComplain addLabQuaComplain(LabQuaComplain labQuaComplain)
			throws GlobalException {
		try {
			super.save(labQuaComplain);
			return labQuaComplain;
		} catch (Exception ex) {
			Log4J.error("LabQuaComplainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabQuaComplain(LabQuaComplain labQuaComplain)
			throws GlobalException {
		try {
			super.update(labQuaComplain);
			return true;
		} catch (Exception ex) {
			Log4J.error("LabQuaComplainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
	@Override
	public PageResult getLabQuaComplainPR(String hql,PageResult pageResult )throws GlobalException{
		try {
			pageResult=super.getPageResult(hql,pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("LabQuaComplainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public List<LabQuaComplain> getLabQuaComplainByHQL(String hql)throws GlobalException{
		return super.find(hql);
		}

}
