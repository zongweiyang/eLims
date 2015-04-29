package cn.labsoft.labos.source.quality.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.dao.ILabQuaAuditRecordDetailDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaAuditRecordDetail;



@Repository(value="labQuaAuditRecordDetailDAO")
public class LabQuaAuditRecordDetailDAOImpl extends BaseDAO implements ILabQuaAuditRecordDetailDAO{

	@Override
	public LabQuaAuditRecordDetail getLabQuaAuditRecordDetail(String id) throws GlobalException {
		try {
		LabQuaAuditRecordDetail labQuaAuditRecordDetail=(LabQuaAuditRecordDetail)super.findById(LabQuaAuditRecordDetail.class,id);
		return labQuaAuditRecordDetail;
		} catch (Exception ex) {
			Log4J.error("LabQuaAuditRecordDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean deleteLabQuaAuditRecordDetail(LabQuaAuditRecordDetail labQuaAuditRecordDetail)throws GlobalException{
		try {
			super.update(labQuaAuditRecordDetail);
		} catch (Exception ex) {
			Log4J.error("LabQuaAuditRecordDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@Override
	public LabQuaAuditRecordDetail addLabQuaAuditRecordDetail(LabQuaAuditRecordDetail labQuaAuditRecordDetail)
			throws GlobalException {
		try {
			labQuaAuditRecordDetail.setIsDel(Constants_Source.N);
			super.save(labQuaAuditRecordDetail);
			return labQuaAuditRecordDetail;
		} catch (Exception ex) {
			Log4J.error("LabQuaAuditRecordDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabQuaAuditRecordDetail(LabQuaAuditRecordDetail labQuaAuditRecordDetail)
			throws GlobalException {
		try {
			super.update(labQuaAuditRecordDetail);
			return true;
		} catch (Exception ex) {
			Log4J.error("LabQuaAuditRecordDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
	@Override
	public PageResult getLabQuaAuditRecordDetailPR(String hql, PageResult pageResult)throws GlobalException{
		try {
		pageResult=super.getPageResult(hql,pageResult);
		return pageResult;
		} catch (Exception ex) {
			Log4J.error("LabQuaAuditRecordDetailDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public List<LabQuaAuditRecordDetail> getLabQuaAuditRecordDetailByHQL(String hql)throws GlobalException{
		return super.find(hql);
		}
	
}
