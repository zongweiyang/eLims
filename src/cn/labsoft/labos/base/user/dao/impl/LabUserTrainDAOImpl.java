package cn.labsoft.labos.base.user.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.base.user.entity.LabUserTrain;
import cn.labsoft.labos.base.user.dao.ILabUserTrainDAO;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;

@Repository(value="labUserTrainDAO")
public class LabUserTrainDAOImpl extends BaseDAO implements ILabUserTrainDAO {
	
	@Override
	public LabUserTrain addLabUserTrain(LabUserTrain labUserTrain) throws GlobalException {
		try {
			labUserTrain.setIsDel(Constants_Common.N);
			labUserTrain.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labUserTrain);
		} catch (Exception ex) {
			Log4J.error("labUserTrainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labUserTrain;
	}

	@Override
	public boolean deleteLabUserTrain(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				LabUserTrain labUserTrain=	this.getLabUserTrain(id);
				labUserTrain.setIsDel(Constants_Common.Y);
				super.update(labUserTrain);
			}
		} catch (Exception ex) {
			Log4J.error("labUserTrainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabUserTrain getLabUserTrain(String id) throws GlobalException {
		try {
			LabUserTrain labUserTrain = (LabUserTrain) super.findById(LabUserTrain.class, id);
			return labUserTrain;
		} catch (Exception ex) {
			Log4J.error("labUserTrainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabUserTrain(LabUserTrain labUserTrain) throws GlobalException {
		try {
			super.update(labUserTrain);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labUserTrainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabUserTrain> getLabUserTrainList(String hql) throws GlobalException {
		try {
			List<LabUserTrain> userList = super.find(hql);
			return userList;
		} catch (Exception ex) {
			Log4J.error("labUserTrainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public PageResult getLabUserTrainPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("labUserTrainDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
}
