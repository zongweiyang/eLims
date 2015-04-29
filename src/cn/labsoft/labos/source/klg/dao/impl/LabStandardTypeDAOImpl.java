package cn.labsoft.labos.source.klg.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.klg.dao.ILabStandardTypeDAO;
import cn.labsoft.labos.source.klg.entity.LabStandardType;
import cn.labsoft.labos.utils.StrUtils;

@Repository(value="labStandardTypeDAO")
public class LabStandardTypeDAOImpl extends BaseDAO implements ILabStandardTypeDAO {
	public boolean addLabStandardType(LabStandardType labStandardType) {
		super.save(labStandardType);
		return true;
	}

	@Override
	public boolean deleteLabStandardType(LabStandardType labStandardType) {
		super.delete(labStandardType);
		return true;
	}

	@Override
	public boolean updateLabStandardType(LabStandardType labStandardType) {
		super.update(labStandardType);
		return true;
	}

	public List<LabStandardType> getStandTypeListByParentId(String parentId) throws GlobalException {
		String hql="FROM LabStandardType WHERE isDel='"+Constants_Source.N+"'";
		if(!StrUtils.isBlankOrNull(parentId)){
		hql += " AND standardType.id='"+parentId+"'";	
		}
		return super.find(hql);
	}
}
