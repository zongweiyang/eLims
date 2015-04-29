package cn.labsoft.labos.source.quality.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;




import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.quality.dao.ILabQuaAuditPlanEleDetailDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaAuditPlanEleDetail;
import cn.labsoft.labos.utils.DateUtils;

@Repository(value="labQuaAuditPlanEleDetailDAO")
public class LabQuaAuditPlanEleDetailDAOImpl extends BaseDAO implements ILabQuaAuditPlanEleDetailDAO{

	@Override
	public List<LabQuaAuditPlanEleDetail> getLabQuaAuditPlanEleDetailByHQL(String hql)
			throws GlobalException {
		return super.find(hql);
	}

	@Override
	public LabQuaAuditPlanEleDetail getLabQuaAuditPlanEleDetail(String id)
			throws GlobalException {
		try {
			LabQuaAuditPlanEleDetail quaAuditPlanEleDetail=(LabQuaAuditPlanEleDetail)super.findById(LabQuaAuditPlanEleDetail.class,id);
			return quaAuditPlanEleDetail;
		} catch (Exception ex) {
			Log4J.error("LabQuaAuditPlanEleDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public LabQuaAuditPlanEleDetail addLabQuaAuditPlanEleDetail(
			LabQuaAuditPlanEleDetail labQuaAuditPlanEleDetail)
			throws GlobalException {
		// TODO Auto-generated method stub
		try {
			labQuaAuditPlanEleDetail.setIsDel(Constants_Source.N);
			labQuaAuditPlanEleDetail.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labQuaAuditPlanEleDetail);
		} catch (Exception ex) {
			Log4J.error("LabQuaAuditPlanEleDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labQuaAuditPlanEleDetail;
	}

	@Override
	public boolean updateLabQuaAuditPlanEleDetail(
			LabQuaAuditPlanEleDetail labQuaAuditPlanEleDetail)
			throws GlobalException {
		// TODO Auto-generated method stub
		try {
			super.update(labQuaAuditPlanEleDetail);
		} catch (Exception ex) {
			Log4J.error("QuaAuditPlanEleDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

}
