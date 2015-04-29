package cn.labsoft.labos.common.encoder.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.encoder.dao.ILabEncoderDAO;
import cn.labsoft.labos.common.encoder.entity.LabEncoder;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;

@Repository(value="labEncoderDAO")
public class LabEncoderDAOImpl extends BaseDAO implements ILabEncoderDAO {
	@Override
	public LabEncoder addLabEncoder(LabEncoder labEncoder) throws GlobalException {
		try {
			labEncoder.setIsDel(Constants_Common.N);
			labEncoder.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labEncoder);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labEncoderDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labEncoder;
	}

	@Override
	public boolean deleteLabEncoder(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabEncoder(id));
			}
		} catch (Exception ex) {
			Log4J.error("labEncoderDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabEncoder getLabEncoder(String id) throws GlobalException {
		try {
			LabEncoder labEncoder = (LabEncoder) super.findById(LabEncoder.class, id);
			return labEncoder;
		} catch (Exception ex) {
			Log4J.error("labEncoderDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabEncoder(LabEncoder labEncoder) throws GlobalException {
		try {
			super.update(labEncoder);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labEncoderDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabEncoder> getLabEncoderList(String hql) throws GlobalException {
	try {
		List<LabEncoder> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		ex.printStackTrace();
		Log4J.error("labEncoderDAOImpl error...." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabEncoderPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labEncoderDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
}
