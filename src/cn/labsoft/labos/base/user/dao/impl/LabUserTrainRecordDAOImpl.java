package cn.labsoft.labos.base.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.base.user.entity.LabUserTrainRecord;
import cn.labsoft.labos.base.user.dao.ILabUserTrainRecordDAO;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;

@Repository(value="labUserTrainRecordDAO")
public class LabUserTrainRecordDAOImpl extends BaseDAO implements ILabUserTrainRecordDAO {
	
	@Override
	public LabUserTrainRecord addLabUserTrainRecord(LabUserTrainRecord labUserTrainRecord) throws GlobalException {
		try {
			labUserTrainRecord.setIsDel(Constants_Common.N);
			labUserTrainRecord.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labUserTrainRecord);
		} catch (Exception ex) {
			Log4J.error("labUserTrainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labUserTrainRecord;
	}

	@Override
	public boolean deleteLabUserTrainRecord(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabUserTrainRecord(id));
			}
		} catch (Exception ex) {
			Log4J.error("labUserTrainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabUserTrainRecord getLabUserTrainRecord(String id) throws GlobalException {
		try {
			LabUserTrainRecord labUserTrain = (LabUserTrainRecord) super.findById(LabUserTrainRecord.class, id);
			return labUserTrain;
		} catch (Exception ex) {
			Log4J.error("labUserTrainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabUserTrainRecord(LabUserTrainRecord labUserTrainRecord) throws GlobalException {
		try {
			super.update(labUserTrainRecord);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labUserTrainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabUserTrainRecord> getLabUserTrainRecordList(String hql) throws GlobalException {
		try {
			List<LabUserTrainRecord> userList = super.find(hql);
			return userList;
		} catch (Exception ex) {
			Log4J.error("labUserTrainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public PageResult getLabUserTrainRecordPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("labUserTrainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean deleteLabUserTrainRecord(
			LabUserTrainRecord labUserTrainRecord) throws GlobalException {
		try {
			super.delete(labUserTrainRecord);
		} catch (Exception ex) {
			Log4J.error("labUserTrainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
}
