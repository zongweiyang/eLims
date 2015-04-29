package cn.labsoft.labos.source.quality.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;


import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.dao.ILabQuaCusVisitDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaCusVisit;


@Repository(value="labQuaCusVisitDAO")
public class LabQuaCusVisitDAOImpl extends BaseDAO implements ILabQuaCusVisitDAO{

	@Override
	public LabQuaCusVisit getLabQuaCusVisit(String id) throws GlobalException {
		try {
		LabQuaCusVisit labQuaCusVisit=(LabQuaCusVisit)super.findById(LabQuaCusVisit.class,id);
		return labQuaCusVisit;
		} catch (Exception ex) {
			Log4J.error("LabQuaCusVisitDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean deleteLabQuaCusVisit(LabQuaCusVisit labQuaCusVisit)throws GlobalException{
		try {
			super.update(labQuaCusVisit);
		} catch (Exception ex) {
			Log4J.error("LabQuaCusVisitDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@Override
	public LabQuaCusVisit addLabQuaCusVisit(LabQuaCusVisit labQuaCusVisit)
			throws GlobalException {
		try {
			labQuaCusVisit.setIsDel(Constants_Source.N);
			super.save(labQuaCusVisit);
			return labQuaCusVisit;
		} catch (Exception ex) {
			Log4J.error("LabQuaCusVisitDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabQuaCusVisit(LabQuaCusVisit labQuaCusVisit)
			throws GlobalException {
		try {
			super.update(labQuaCusVisit);
			return true;
		} catch (Exception ex) {
			Log4J.error("LabQuaCusVisitDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
	@Override
	public PageResult getLabQuaCusVisitPR(String hql, PageResult pageResult)throws GlobalException{
		try {
		pageResult=super.getPageResult(hql,pageResult);
		return pageResult;
		} catch (Exception ex) {
			Log4J.error("LabQuaCusVisitDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public List<LabQuaCusVisit> getLabQuaCusVisitByHQL(String hql)throws GlobalException{
		return super.find(hql);
		}

}
