package cn.labsoft.labos.source.klg.dao.impl;


import java.util.List;

import org.springframework.stereotype.Repository;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.source.klg.dao.ILabStandardItemMethodDAO;
import cn.labsoft.labos.source.klg.entity.LabStandardItemMethod;


@Repository(value="labStandardItemMethodDAO")
public class LabStandardItemMethodDAOImpl extends BaseDAO implements ILabStandardItemMethodDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<LabStandardItemMethod> getListByItemId(String itemId) throws GlobalException {
		String hql="FROM LabStandardItemMethod WHERE isDel='"+Constants_Source.N+"'AND item.id='"+itemId+"'";
		return super.find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabStandardItemMethod> getListByStandardId(String standId) throws GlobalException {
		String hql="FROM LabStandardItemMethod WHERE isDel='"+Constants_Source.N+"'AND standard.id='"+standId+"'";
		return super.find(hql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabStandardItemMethod> getListByStandardIdAndItemId(String standId,
			String itemId) throws GlobalException {
		String hql="FROM LabStandardItemMethod WHERE isDel='"+Constants_Source.N+"'";
		if (standId!=null&&!"".equals(standId)) {
			hql+=" AND standard.id='"+standId+"'";
		}
		if (itemId!=null&&!"".equals(itemId)) {
			hql+=" AND item.id='"+itemId+"'";
		}
		return super.find(hql);
	}

	@Override
	public LabStandardItemMethod getLabStandardItemMethod(String id) {
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<LabStandardItemMethod> getListByMethodId(String methodId) throws GlobalException {
		String hql="FROM LabStandardItemMethod WHERE isDel = '"+Constants_Source.N+"' AND method.id='"+methodId+"'";
		return super.find(hql);
	}

	@Override
	public boolean addLabStandardItemMethod(
			LabStandardItemMethod labStandardItemMethod) throws GlobalException {
		boolean key=true;
		if(labStandardItemMethod!=null){
			labStandardItemMethod.setIsDel(Constants_Source.N);
			try{
				super.save(labStandardItemMethod);
			}catch(Exception e){
				key=false;
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}

	@Override
	public boolean updateLabStandardItemMethod(
			LabStandardItemMethod labStandardItemMethod) throws GlobalException {
		try{
			super.update(labStandardItemMethod);
		}catch(Exception e){
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

	@Override
	public void deleteAll(List<LabStandardItemMethod> listLabStandardItemMethod) {
		super.deleteAll(listLabStandardItemMethod);
	}

	@Override
	public LabStandardItemMethod getListBysampIdAndItemIdAndMethodId(String sampTypeId, String itemId, String methodId) throws GlobalException {
		String hql="FROM LabStandardItemMethod WHERE isDel = '"+Constants_Source.N+"' AND method.id='"+methodId+"'";
		hql+=" AND item.id='"+itemId+"'";
		return (LabStandardItemMethod)super.find(hql,0);
	}
	
	
}
