package cn.labsoft.labos.common.makecode.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.common.makecode.dao.ILabMakeCodeDAO;
import cn.labsoft.labos.common.makecode.entity.LabMakeCode;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labMakeCodeDAO")
public class LabMakeCodeDAOImpl extends BaseDAO implements ILabMakeCodeDAO {
	
	@Override
	public LabMakeCode addLabMakeCode(LabMakeCode labMakeCode) throws GlobalException {
		try {
			labMakeCode.setIsDel(Constants_Base.N);
			labMakeCode.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labMakeCode);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labMakeCodeDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labMakeCode;
	}

	@Override
	public boolean deleteLabMakeCode(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabMakeCode(id));
			}
		} catch (Exception ex) {
			Log4J.error("labMakeCodeDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabMakeCode getLabMakeCode(String id) throws GlobalException {
		try {
			LabMakeCode labMakeCode = (LabMakeCode) super.findById(LabMakeCode.class, id);
			return labMakeCode;
		} catch (Exception ex) {
			Log4J.error("labMakeCodeDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabMakeCode(LabMakeCode labMakeCode) throws GlobalException {
		try {
			super.update(labMakeCode);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labMakeCodeDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabMakeCode> getLabMakeCodeList(String hql) throws GlobalException {
	try {
		List<LabMakeCode> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		ex.printStackTrace();
		Log4J.error("labMakeCodeDAOImpl error...." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabMakeCodePR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labMakeCodeDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
}
