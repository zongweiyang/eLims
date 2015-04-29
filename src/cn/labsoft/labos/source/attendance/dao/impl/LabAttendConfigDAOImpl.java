package cn.labsoft.labos.source.attendance.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.source.attendance.entity.LabAttendConfig;
import cn.labsoft.labos.source.attendance.dao.ILabAttendConfigDAO;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
@Repository(value="labAttendConfigDAO")
public class LabAttendConfigDAOImpl extends BaseDAO implements ILabAttendConfigDAO {
	
	@Override
	public LabAttendConfig addLabAttendConfig(LabAttendConfig labAttendConfig) throws GlobalException {
		try {
			labAttendConfig.setIsDel(Constants_Source.N);
			labAttendConfig.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labAttendConfig);
		} catch (Exception ex) {
			Log4J.error("labAttendConfigDAOImpl error.." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labAttendConfig;
	}

	@Override
	public boolean deleteLabAttendConfig(String ids[]) throws GlobalException{
		try {
			for (String id : ids) {
				super.delete(this.getLabAttendConfig(id));
			}
		} catch (Exception ex) {
			Log4J.error("labAttendConfigDAOImpl error.." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public LabAttendConfig getLabAttendConfig(String id) throws GlobalException {
		try {
			LabAttendConfig labAttendConfig = (LabAttendConfig) super.findById(LabAttendConfig.class, id);
			return labAttendConfig;
		} catch (Exception ex) {
			Log4J.error("labAttendConfigDAOImpl error.." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabAttendConfig(LabAttendConfig labAttendConfig) throws GlobalException {
		try {
			super.update(labAttendConfig);
		} catch (Exception ex) {
			Log4J.error("labAttendConfigDAOImpl error.." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	@Override
	public boolean deleteLabAttendConfig(LabAttendConfig labAttendConfig) throws GlobalException {
		try {
			super.delete(labAttendConfig);
		} catch (Exception ex) {
			Log4J.error("labAttendConfigDAOImpl error.." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabAttendConfig> getLabAttendConfigList(String hql) throws GlobalException {
	try {
		List<LabAttendConfig> userList = super.find(hql);
		return userList;
	} catch (Exception ex) {
		Log4J.error("labAttendConfigDAOImpl error.." + ex.getMessage(), ex);
		throw new GlobalException("" + ex.getMessage());
	}
	}

	@Override
	public PageResult getLabAttendConfigPR(String hql, PageResult pageResult) throws GlobalException {
		try {
			pageResult = super.getPageResult(hql, pageResult);
			return pageResult;
		} catch (Exception ex) {
			Log4J.error("labAttendConfigDAOImpl error.." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public LabAttendConfig getLabAttendConfig4Month(String month) throws GlobalException {
		String hql="FROM LabAttendConfig WHERE isDel='"+Constants_Source.N+"' AND startDay<='"+month+"-01' AND endDay>='"+month+"-31' ORDER BY createTime DESC";
		LabAttendConfig attendConfig =(LabAttendConfig)super.find(hql,0);
		if(attendConfig==null){
			hql="FROM LabAttendConfig WHERE isDel='"+Constants_Source.N+"' AND startDay<='"+DateUtils.getCurrDateStr()+"' AND endDay>='"+DateUtils.getCurrDateStr()+"' ORDER BY createTime DESC";
			attendConfig =(LabAttendConfig)super.find(hql,0);
			if(attendConfig==null){
				hql="FROM LabAttendConfig WHERE isDel='"+Constants_Source.N+"' ORDER BY createTime DESC";
				attendConfig =(LabAttendConfig)super.find(hql,0);
			}
		}
		return attendConfig;
	}

	@Override
	public LabAttendConfig getLabAttendConfig4Cur() throws GlobalException {
		String hql="FROM LabAttendConfig WHERE isDel='"+Constants_Source.N+"' AND startDay<='"+DateUtils.getCurrDateStr()+"' AND endDay>='"+DateUtils.getCurrDateStr()+"' ORDER BY createTime DESC";
		LabAttendConfig attendConfig =(LabAttendConfig)super.find(hql,0);
		if(attendConfig==null){
			hql="FROM LabAttendConfig WHERE isDel='"+Constants_Source.N+"' ORDER BY createTime DESC";
			attendConfig =(LabAttendConfig)super.find(hql,0);
		}
		return attendConfig;
	}
	
}
