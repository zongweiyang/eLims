package cn.labsoft.labos.source.klg.dao.impl;


import java.util.List;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.klg.dao.ILabItemDAO;
import cn.labsoft.labos.source.klg.entity.LabItem;


@Repository(value="labItemDAO")
public class LabItemDAOImpl extends BaseDAO implements ILabItemDAO {
	@Override
	public LabItem addLabItem(LabItem item) throws GlobalException {
		try {
			super.save(item);
		} catch (Exception ex) {
			Log4J.error("LabItemDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return item;
	}
	public LabItem getLabItem(String id) throws GlobalException {
		try {
			LabItem LabItem = (LabItem) super.findById(LabItem.class, id);
			return LabItem;
		} catch (Exception ex) {
			Log4J.error("LabItemDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public List<LabItem> getLabItemListByParentId(String parentId) throws GlobalException {
		try {
			String hql="FROM LabItem WHERE isDel='"+Constants_Source.N+"' AND labItem.id='"+parentId+"'";
			return super.find(hql);
		} catch (Exception ex) {
			Log4J.error("LabItemDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
	}

	@Override
	public boolean updateLabItem(LabItem item) throws GlobalException {
		try {
			super.update(item);
		} catch (Exception ex) {
			Log4J.error("LabItemDAOImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}
	@Override
	public boolean hasChildren(String itemId) throws GlobalException {
		List<LabItem> list=this.getLabItemListByParentId(itemId);
		if(null!=list&&list.size()>0) return true;
		else return false;
	}
}
