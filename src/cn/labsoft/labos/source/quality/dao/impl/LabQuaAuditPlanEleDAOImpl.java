package cn.labsoft.labos.source.quality.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;




import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.dao.ILabQuaAuditPlanEleDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaAuditPlanEle;
import cn.labsoft.labos.utils.DateUtils;

@Repository(value="labQuaAuditPlanEleDAO")
public class LabQuaAuditPlanEleDAOImpl extends BaseDAO implements ILabQuaAuditPlanEleDAO{

	@Override
	public LabQuaAuditPlanEle getLabQuaAuditPlanEle(String id) throws GlobalException {
		try {
			LabQuaAuditPlanEle quaAuditPlanEle=(LabQuaAuditPlanEle)super.findById(LabQuaAuditPlanEle.class,id);
			return quaAuditPlanEle;
		} catch (Exception ex) {
			Log4J.error("LabQuaAuditPlanEleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean deleteLabQuaAuditPlanEle(LabQuaAuditPlanEle labQuaAuditPlanEle)throws GlobalException{
		try {
			super.update(labQuaAuditPlanEle);
		} catch (Exception ex) {
			Log4J.error("LabQuaAuditPlanEleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
		
	}
	
	@Override
	public LabQuaAuditPlanEle addLabQuaAuditPlanEle(LabQuaAuditPlanEle labQuaAuditPlanEle)
			throws GlobalException {
		try {
			labQuaAuditPlanEle.setIsDel(Constants_Source.N);
			labQuaAuditPlanEle.setUpdateDate(DateUtils.getCurrDateTimeStr());
			super.save(labQuaAuditPlanEle);
		} catch (Exception ex) {
			Log4J.error("LabQuaAuditPlanEleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labQuaAuditPlanEle;
	}

	@Override
	public boolean updateLabQuaAuditPlanEle(LabQuaAuditPlanEle labQuaAuditPlanEle)
			throws GlobalException {
		try {
			labQuaAuditPlanEle.setUpdateDate(DateUtils.getCurrDateTimeStr());
			super.update(labQuaAuditPlanEle);
		} catch (Exception ex) {
			Log4J.error("LabQuaAuditPlanEleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@Override
	public PageResult getLabQuaAuditPlanElePR(String hql, PageResult pageResult)throws GlobalException{
		try {
			pageResult=super.getPageResult(hql,pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("LabQuaAuditPlanEleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public List<LabQuaAuditPlanEle> getLabQuaAuditPlanEleByHQL(String hql)throws GlobalException{
		return super.find(hql);
		}
}
