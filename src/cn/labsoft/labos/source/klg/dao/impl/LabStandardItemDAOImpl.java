package cn.labsoft.labos.source.klg.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.klg.dao.ILabStandardItemDAO;
import cn.labsoft.labos.source.klg.entity.LabStandardItem;
import cn.labsoft.labos.source.klg.vo.LabStandardItemVo;
import cn.labsoft.labos.utils.StrUtils;


@Repository(value="labStandardItemDAO")
public class LabStandardItemDAOImpl extends BaseDAO implements ILabStandardItemDAO {

	@Override
	public List<LabStandardItem> getListByItemId(String itemId) throws GlobalException {
		String hql="FROM LabStandardItem WHERE item.id='"+itemId+"'";
		return super.find(hql);
	}

	@Override

	public List<LabStandardItem> getChildrenList(String itemId,String standId) throws GlobalException {
		String hql="FROM LabStandardItem WHERE item.id='"+itemId+"' AND standard.id='"+standId+"' AND minItem IS NOT NULL GROUP BY minItem";
		return super.find(hql);
	}

	public List<LabStandardItem> getListByStandId(String standId) throws GlobalException {
		String hql="FROM LabStandardItem WHERE standard.id='"+standId+"'";
		return super.find(hql);
	}

	@Override
	public LabStandardItem findById(String id) {
		return (LabStandardItem)super.findById(LabStandardItem.class, id);
	}

	@Override
	public boolean addLabStandardItem(LabStandardItem labStandardItem) throws GlobalException {
		// TODO Auto-generated method stub
		boolean key=true;
		try{
			if(labStandardItem!=null){
				labStandardItem.setIsDel(Constants_Source.N);
			 super.save(labStandardItem);
		}
		}catch(Exception e){
			key=false;
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public boolean updateLabStandardItem(LabStandardItem labStandardItem) throws GlobalException {
		// TODO Auto-generated method stub
		try{
			super.update(labStandardItem);
		}catch(Exception ex){
			Log4J.error("labStandardItemImpl error...." + ex.getMessage(), ex);
			throw new GlobalException("" + ex.getMessage());
		}
		return true;
	}

	@Override
	public void deleteAll(List<LabStandardItem> listLabStandardItem) {
		// TODO Auto-generated method stub
		super.deleteAll(listLabStandardItem);
	}
	@Override
	public List<LabStandardItemVo> getLabStandardItemByItemId(String itemId) throws GlobalException {
		// TODO Auto-generated method stub
		List<LabStandardItemVo> listLabStandardItemVo=new ArrayList<LabStandardItemVo>();
		if(!StrUtils.isBlankOrNull(itemId)){
			String hql="FROM LabStandardItem WHERE isDel='"+Constants_Source.N+"' AND item.id='"+itemId+"'";
			List<LabStandardItem> listLabStandardItem=super.find(hql);
			if(listLabStandardItem!=null&&listLabStandardItem.size()>0){
				for(LabStandardItem labStandardItem:listLabStandardItem){
					LabStandardItemVo labStandardItemVo=new LabStandardItemVo();
					BeanUtils.copyProperties(labStandardItem, labStandardItemVo,new String[]{"standard","item","minItem"});
					labStandardItemVo.setStandardId(labStandardItem.getStandard().getId());
					String describe="";
					String norm="";
					if(!StrUtils.isBlankOrNull(labStandardItem.getMaxKey())){
						describe+=showDescribe(labStandardItem.getMaxKey());
						describe+=labStandardItem.getMaxValue();
						describe+="("+labStandardItem.getItem().getUnit()+")";
						describe+=",";
						norm+=labStandardItem.getMaxKey();
						norm+=":";
						norm+=labStandardItem.getMaxValue();
						norm+=",";
					}
					if(!StrUtils.isBlankOrNull(labStandardItem.getMinKey())){
						describe+=showDescribe(labStandardItem.getMinKey());
						describe+=labStandardItem.getMinValue();
						describe+="("+labStandardItem.getItem().getUnit()+")";
						describe+=",";
						norm+=labStandardItem.getMinKey();
						norm+=":";
						norm+=labStandardItem.getMinValue();
						norm+=",";
					}
					if(describe.length()>0)describe=describe.substring(0,describe.length()-1);
					if(norm.length()>0)norm=norm.substring(0,norm.length()-1);
					labStandardItemVo.setTestTime(describe);
					labStandardItemVo.setDemo(norm);
					listLabStandardItemVo.add(labStandardItemVo);
				}
			}
		}
		return listLabStandardItemVo;
	}
	public String showDescribe(String mark){
		String describe="";
		if(mark.trim().equals("<"))describe="小于";
		else if(mark.trim().equals("<="))describe="小于等于";
		else if(mark.trim().equals(">"))describe="大于";
		else if(mark.trim().equals(">="))describe="大于等于";
		else if(mark.trim().equals("="))describe="等于";
		return describe;
	}
}
