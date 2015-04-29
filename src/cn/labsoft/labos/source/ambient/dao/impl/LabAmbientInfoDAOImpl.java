package cn.labsoft.labos.source.ambient.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.ambient.dao.ILabAmbientInfoDAO;
import cn.labsoft.labos.source.ambient.entity.LabAmbientInfo;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labAmbientInfoDAO")
public class LabAmbientInfoDAOImpl extends BaseDAO implements ILabAmbientInfoDAO {
	
	@Override
	public LabAmbientInfo addLabAmbientInfo(LabAmbientInfo labAmbientInfo) throws GlobalException {
		try {
			labAmbientInfo.setIsDel(Constants_Source.N);
			labAmbientInfo.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labAmbientInfo);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labAmbientInfoDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labAmbientInfo;
	}

	@Override
	public boolean deleteLabAmbientInfo(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabAmbientInfo(id));
			}
		} catch (Exception ex) {
			Log4J.error("labAmbientInfoDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	@Override
	public boolean deleteAllLabAmbientInfo(List<LabAmbientInfo> listLabAmbientInfo) throws GlobalException{
		boolean key=true;
		if(listLabAmbientInfo!=null&&listLabAmbientInfo.size()>0){
			try{
				super.deleteAll(listLabAmbientInfo);
			}catch(Exception ex){
				Log4J.error("labAmbientInfoDAOImpl error...." + ex.getMessage(), ex);
				throw new GlobalException("" + ex.getMessage());
			}
		}
		return key;
	}
	@Override
	public LabAmbientInfo getLabAmbientInfo(String id) {
		try {
			LabAmbientInfo labAmbientInfo = (LabAmbientInfo) super.findById(LabAmbientInfo.class, id);
			return labAmbientInfo;
		} catch (Exception ex) {
			Log4J.error("labAmbientInfoDAOImpl error...." + ex.getMessage(), ex);
			return null;
		}
	}

	@Override
	public boolean updateLabAmbientInfo(LabAmbientInfo labAmbientInfo) {
		try {
			super.update(labAmbientInfo);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labAmbientInfoDAOImpl error...." + ex.getMessage(), ex);
			return false;
		}
		return true;
	}

	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabAmbientInfo> getLabAmbientInfoList(String hql) throws GlobalException {
	try {
		List<LabAmbientInfo> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		ex.printStackTrace();
		Log4J.error("labAmbientInfoDAOImpl error...." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabAmbientInfoPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("labAmbientInfoDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}
	
}
