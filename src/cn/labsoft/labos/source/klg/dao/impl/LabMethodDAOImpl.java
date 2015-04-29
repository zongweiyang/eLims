package cn.labsoft.labos.source.klg.dao.impl;

import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.klg.dao.ILabMethodDAO;
import cn.labsoft.labos.source.klg.entity.LabMethod;
import cn.labsoft.labos.utils.DateUtils;

@Repository(value="labMethodDAO")
public class LabMethodDAOImpl  extends BaseDAO implements ILabMethodDAO {
	@Override
	public LabMethod getLabMethod(String id) {
		return (LabMethod)super.findById(LabMethod.class, id);
	}
	@Override
	public LabMethod getLabMethodByName(String name) throws GlobalException {
		String hql = "FROM LabMethod WHERE name = '"+name+"' AND isDel = '"+Constants_Source.N+"' "; 
		List<LabMethod> list = super.find(hql);
		return null!=list&&list.size()>0?list.get(0):null;
	}
	@Override
	public LabMethod addLabMethod(LabMethod labMethod) throws GlobalException {
		try {
			labMethod.setIsDel(Constants_Source.N);
			labMethod.setCreateTime(DateUtils.getCurrDateTimeStr());
			super.save(labMethod);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("LabMethodDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return labMethod;
	}
	@Override
	public boolean updateLabMethod(LabMethod labMethod) throws GlobalException {
		try {
			super.update(labMethod);
		} catch (Exception ex) {
			ex.printStackTrace();
			Log4J.error("LabMethodDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
}
