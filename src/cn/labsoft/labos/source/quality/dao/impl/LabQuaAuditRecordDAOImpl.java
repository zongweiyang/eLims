package cn.labsoft.labos.source.quality.dao.impl;

import java.util.List;
import org.springframework.stereotype.Repository;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.quality.dao.ILabQuaAuditRecordDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaAuditRecord;
import cn.labsoft.labos.utils.DateUtils;



@Repository(value="labQuaAuditRecordDAO")
public class LabQuaAuditRecordDAOImpl extends BaseDAO implements ILabQuaAuditRecordDAO{

	@Override
	public LabQuaAuditRecord getLabQuaAuditRecord(String id) throws GlobalException {
		try {
		LabQuaAuditRecord labQuaAuditRecord=(LabQuaAuditRecord)super.findById(LabQuaAuditRecord.class,id);
		return labQuaAuditRecord;
		} catch (Exception ex) {
			Log4J.error("LabQuaAuditRecordDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean deleteLabQuaAuditRecord(LabQuaAuditRecord labQuaAuditRecord)throws GlobalException{
		try {
			super.update(labQuaAuditRecord);
		} catch (Exception ex) {
			Log4J.error("LabQuaAuditRecordDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@Override
	public LabQuaAuditRecord addLabQuaAuditRecord(LabQuaAuditRecord labQuaAuditRecord)
			throws GlobalException {
		try {
			labQuaAuditRecord.setCreateTime(DateUtils.getCurrDateTimeStr());
			labQuaAuditRecord.setIsDel(Constants_Source.N);
			super.save(labQuaAuditRecord);
			return labQuaAuditRecord;
		} catch (Exception ex) {
			Log4J.error("LabQuaAuditRecordDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabQuaAuditRecord(LabQuaAuditRecord labQuaAuditRecord)
			throws GlobalException {
		try {
			super.update(labQuaAuditRecord);
			return true;
		} catch (Exception ex) {
			Log4J.error("LabQuaAuditRecordDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
	@Override
	public PageResult getLabQuaAuditRecordPR(String hql, PageResult pageResult)throws GlobalException{
		try {
		pageResult=super.getPageResult(hql,pageResult);
		return pageResult;
		} catch (Exception ex) {
			Log4J.error("LabQuaAuditRecordDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public List<LabQuaAuditRecord> getLabQuaAuditRecordByHQL(String hql)throws GlobalException{
		return super.find(hql);
		}
	
}
