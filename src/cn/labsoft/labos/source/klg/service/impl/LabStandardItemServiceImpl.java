package cn.labsoft.labos.source.klg.service.impl;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.business.sam.entity.LabSamType;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.klg.dao.ILabItemDAO;
import cn.labsoft.labos.source.klg.dao.ILabMethodDAO;
import cn.labsoft.labos.source.klg.dao.ILabStandardDAO;
import cn.labsoft.labos.source.klg.dao.ILabStandardItemDAO;
import cn.labsoft.labos.source.klg.dao.ILabStandardItemMethodDAO;
import cn.labsoft.labos.source.klg.entity.LabItem;
import cn.labsoft.labos.source.klg.entity.LabMethod;
import cn.labsoft.labos.source.klg.entity.LabStandard;
import cn.labsoft.labos.source.klg.entity.LabStandardItem;
import cn.labsoft.labos.source.klg.entity.LabStandardItemMethod;
import cn.labsoft.labos.source.klg.service.ILabStandardItemService;
import cn.labsoft.labos.source.klg.vo.LabItemVo;
import cn.labsoft.labos.source.klg.vo.LabMethodVo;
import cn.labsoft.labos.source.klg.vo.LabStandardItemMethodVo;
import cn.labsoft.labos.source.klg.vo.LabStandardItemVo;
import cn.labsoft.labos.utils.StrUtils;

@Service("labStandardItemService")
public class LabStandardItemServiceImpl implements ILabStandardItemService {
	 private ILabMethodDAO labMethodDAO;
	 private ILabItemDAO labItemDAO;
	 private ILabStandardDAO labStandardDAO;
	 private ILabStandardItemDAO labStandardItemDAO;
	 private ILabStandardItemMethodDAO labStandardItemMethodDAO;
	@Resource
	 public void setLabMethodDAO(ILabMethodDAO labMethodDAO) {
		 this.labMethodDAO = labMethodDAO;
	 }
	 @Resource
	 public void setLabItemDAO(ILabItemDAO labItemDAO) {
		 this.labItemDAO = labItemDAO;
	 }
	 @Resource
	 public void setLabStandardDAO(ILabStandardDAO labStandardDAO) {
		 this.labStandardDAO = labStandardDAO;
	 }
	 @Resource
	 public void setLabStandardItemDAO(ILabStandardItemDAO labStandardItemDAO) {
		 this.labStandardItemDAO = labStandardItemDAO;
	 }
	 @Resource
	 public void setLabStandardItemMethodDAO(
			 ILabStandardItemMethodDAO labStandardItemMethodDAO) {
		 this.labStandardItemMethodDAO = labStandardItemMethodDAO;
	 }
 
	@SuppressWarnings("unchecked")
	@Override
	public boolean addLabStandardItem(LabStandardItemVo labStandardItemVo) throws GlobalException {
		LabStandard labStandard=null;
		boolean key=true;
		if(!StrUtils.isBlankOrNull(labStandardItemVo.getStandardId())){
			labStandard=(LabStandard) labStandardDAO.findById(LabStandard.class, labStandardItemVo.getStandardId());
		}
		try{
			if(labStandardItemVo.getItemsIds()!=null&&labStandardItemVo.getItemsIds().length>0){
				for(String itemsId:labStandardItemVo.getItemsIds()){
					if(!StrUtils.isBlankOrNull(itemsId)){
						LabStandardItem LabStandardItem=new LabStandardItem();
						LabItem labItem=(LabItem) labItemDAO.findById(LabItem.class, itemsId);
						String hql="FROM LabStandardItem WHERE isDel='"+Constants_Source.N+"' AND standard.id='"+labStandard.getId()+"' AND item.id='"+itemsId+"'";
						List<LabStandardItem> listLabStandardItem=labStandardItemDAO.find(hql);
						//if(listLabStandardItem!=null&&listLabStandardItem.size()>0)continue;
						LabStandardItem.setStandard(labStandard);
						LabStandardItem.setItem(labItem);
						LabStandardItem.setScores(labItem.getDemo1());
						labStandardItemDAO.addLabStandardItem(LabStandardItem);
					}
				}
			}
		}catch(Exception e){
			//e.printStackTrace();
			key=false;	
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<LabStandardItemVo> getLabStandardItemVoByStandId(String id,String name) throws GlobalException {
		List<LabStandardItemVo> listLabStandardItemVo=new ArrayList<LabStandardItemVo>();
		if(!StrUtils.isBlankOrNull(id)){
			String hql="FROM LabStandardItem WHERE isDel='"+Constants_Source.N+"' AND standard.id='"+id+"'";
			/*if(!StrUtils.isBlankOrNull(name)){
				hql +=" AND item.name like '%"+name+"%'";
			}*/
			if(!StrUtils.isBlankOrNull(name)){
				String items=name.replaceAll("，", ",").trim().replaceAll(" ", "");
				hql+=" AND (";
				for(String item:items.split(",")){
					hql+="  item.name LIKE '%" + item + "%' OR";
				}
				hql=hql.substring(0,hql.length()-2);
				hql+=" )";
			}
			/*if(labStandardItemDAO.find(hql).size()>0){
				hql+="GROUP BY item.id";
			}*/
			List<LabStandardItem> listLabStandardItem=labStandardItemDAO.find(hql);
			if(listLabStandardItem!=null&&listLabStandardItem.size()>0){
				for(LabStandardItem labStandardItem:listLabStandardItem){
					List<LabStandardItemVo> listMinItemVo=new ArrayList<LabStandardItemVo>();
					if(labStandardItem!=null&&labStandardItem.getItem()!=null){
						LabStandardItemVo labStandardItemVo=new LabStandardItemVo();
						BeanUtils.copyProperties(labStandardItem, labStandardItemVo,new String[]{"standard","item","minItem"});
						if (null!=labStandardItem.getLabSamType()) {
							labStandardItemVo.setLabSamTypeId(labStandardItem.getLabSamType().getId());
							labStandardItemVo.setLabSamTypeName(labStandardItem.getLabSamType().getName());
						}
						labStandardItemVo.setTestType(labStandardItem.getTestType());
						labStandardItemVo.setItemType(labStandardItem.getItemType());
						
						labStandardItemVo.setStandardId(labStandardItem.getStandard().getId());
						labStandardItemVo.setItemId(labStandardItem.getItem().getId());
						labStandardItemVo.setItemName(labStandardItem.getItem().getName());
						labStandardItemVo.setDecimalCount(labStandardItem.getItem().getDemo2());
						String[] methodIdName=getLabStandMethod(labStandardItem.getItem().getId(),labStandardItem.getStandard().getId());
						labStandardItemVo.setMethodIds(methodIdName[0]);
						labStandardItemVo.setMethodNames(methodIdName[1]);
						
						//子类项目
						String sonHql="FROM LabStandardItem WHERE isDel='"+Constants_Source.N+"' AND standard.id='"+id+"' AND item.id='"+labStandardItem.getItem().getId()+"' AND minItem.id!=NULL";
						List<LabStandardItem> sonLabStandardItem=labStandardItemDAO.find(sonHql);
						if(sonLabStandardItem!=null&&sonLabStandardItem.size()>0){
							for(LabStandardItem minItem:sonLabStandardItem){
								LabStandardItemVo vo=new LabStandardItemVo();
								BeanUtils.copyProperties(minItem, vo,new String[]{"standard","item","minItem"});
								if(minItem.getStandard()!=null)vo.setStandardId(minItem.getStandard().getId());
								if(minItem.getItem()!=null)vo.setItemId(minItem.getItem().getId());
								if(minItem.getMinItem()!=null)vo.setMinItemId(minItem.getMinItem().getId());
								String[] methodSonIdName=getLabStandMethod(minItem.getMinItem().getId(),minItem.getStandard().getId());
								vo.setMethodIds(methodSonIdName[0]);
								vo.setMethodNames(methodSonIdName[1]);
								if (null!=minItem.getLabSamType()) {
									vo.setLabSamTypeId(minItem.getLabSamType().getId());
									vo.setLabSamTypeName(minItem.getLabSamType().getName());
								}
								vo.setTestType(minItem.getTestType());
								vo.setItemType(minItem.getItemType());
								listMinItemVo.add(vo);
							}
							labStandardItemVo.setMinSItemList(listMinItemVo);
						}
						listLabStandardItemVo.add(labStandardItemVo);
					}
				}
			}
		}
		return listLabStandardItemVo;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabStandardItemVoByStandId(String id,String name,String sampTypeId,PageResult pageResult) throws GlobalException {
		List<LabStandardItemVo> listLabStandardItemVo=new ArrayList<LabStandardItemVo>();
		if(!StrUtils.isBlankOrNull(id)){
			String hql="FROM LabStandardItem WHERE isDel='"+Constants_Source.N+"' AND standard.id='"+id+"'";
			/*if(!StrUtils.isBlankOrNull(name)){
				hql +=" AND item.name like '%"+name+"%'";
			}*/
			if(!StrUtils.isBlankOrNull(name)){
				String items=name.replaceAll("，", ",").trim().replaceAll(" ", "");
				hql+=" AND (";
				for(String item:items.split(",")){
					hql+="  item.name LIKE '%" + item + "%' OR";
				}
				hql=hql.substring(0,hql.length()-2);
				hql+=" )";
			}
			if(!StrUtils.isBlankOrNull(sampTypeId)){
				hql += " AND labSamType.id = '"+sampTypeId+"'";
			}
			/*if(labStandardItemDAO.find(hql).size()>0){
				hql+="GROUP BY item.id";
			}*/
			pageResult = labStandardItemDAO.getPageResult(hql, pageResult);
			List<LabStandardItem> listLabStandardItem=pageResult.getResultList();
			if(listLabStandardItem!=null&&listLabStandardItem.size()>0){
				for(LabStandardItem labStandardItem:listLabStandardItem){
					List<LabStandardItemVo> listMinItemVo=new ArrayList<LabStandardItemVo>();
					if(labStandardItem!=null&&labStandardItem.getItem()!=null){
						LabStandardItemVo labStandardItemVo=new LabStandardItemVo();
						BeanUtils.copyProperties(labStandardItem, labStandardItemVo,new String[]{"standard","item","minItem"});
						if (null!=labStandardItem.getLabSamType()) {
							labStandardItemVo.setLabSamTypeId(labStandardItem.getLabSamType().getId());
							labStandardItemVo.setLabSamTypeName(labStandardItem.getLabSamType().getName());
						}
						labStandardItemVo.setTestType(labStandardItem.getTestType());
						labStandardItemVo.setItemType(labStandardItem.getItemType());
						
						labStandardItemVo.setStandardId(labStandardItem.getStandard().getId());
						labStandardItemVo.setItemId(labStandardItem.getItem().getId());
						labStandardItemVo.setItemName(labStandardItem.getItem().getName());
						if(!StrUtils.isBlankOrNull(labStandardItem.getItem().getDemo2())){
							if(!StrUtils.isBlankOrNull(labStandardItem.getDecimalCount())&&!labStandardItem.getDecimalCount().equals(labStandardItem.getItem().getDemo2())){
								labStandardItemVo.setDecimalCount(labStandardItem.getDecimalCount());
							}else labStandardItemVo.setDecimalCount(labStandardItem.getItem().getDemo2());
						}else labStandardItemVo.setDecimalCount(labStandardItem.getDecimalCount());
						String[] methodIdName=getLabStandMethod(labStandardItem.getItem().getId(),labStandardItem.getStandard().getId());
						if( null != methodIdName){
							labStandardItemVo.setMethodIds(methodIdName[0]);
							labStandardItemVo.setMethodNames(methodIdName[1]);
						}
						
						//子类项目
						String sonHql="FROM LabStandardItem WHERE isDel='"+Constants_Source.N+"' AND standard.id='"+id+"' AND item.id='"+labStandardItem.getItem().getId()+"' AND minItem.id!=NULL";
						List<LabStandardItem> sonLabStandardItem=labStandardItemDAO.find(sonHql);
						if(sonLabStandardItem!=null&&sonLabStandardItem.size()>0){
							for(LabStandardItem minItem:sonLabStandardItem){
								LabStandardItemVo vo=new LabStandardItemVo();
								BeanUtils.copyProperties(minItem, vo,new String[]{"standard","item","minItem"});
								if(minItem.getStandard()!=null)vo.setStandardId(minItem.getStandard().getId());
								if(minItem.getItem()!=null)vo.setItemId(minItem.getItem().getId());
								if(minItem.getMinItem()!=null)vo.setMinItemId(minItem.getMinItem().getId());
								String[] methodSonIdName=getLabStandMethod(minItem.getMinItem().getId(),minItem.getStandard().getId());
								vo.setMethodIds(methodSonIdName[0]);
								vo.setMethodNames(methodSonIdName[1]);
								if (null!=minItem.getLabSamType()) {
									vo.setLabSamTypeId(minItem.getLabSamType().getId());
									vo.setLabSamTypeName(minItem.getLabSamType().getName());
								}
								vo.setTestType(minItem.getTestType());
								vo.setItemType(minItem.getItemType());
								listMinItemVo.add(vo);
							}
							labStandardItemVo.setMinSItemList(listMinItemVo);
						}
						listLabStandardItemVo.add(labStandardItemVo);
					}
				}
			}
			pageResult.setResultList(listLabStandardItemVo);
		}
		return pageResult;
	}
	
	@SuppressWarnings("unchecked")
	public String[] getLabStandMethod(String itemsId,String standardId) throws GlobalException{
		String[] method=new String[2];
		//检测项目
		String methodIds="";
		String methodNames="";
		String methodHql="FROM LabStandardItemMethod WHERE isDel='"+Constants_Source.N+"' AND item.id='"+itemsId+"' AND standard.id='"+standardId+"'";
		List<LabStandardItemMethod> listLabStandardItemMethod=labStandardItemMethodDAO.find(methodHql);
		if(listLabStandardItemMethod!=null&&listLabStandardItemMethod.size()>0){
			for(LabStandardItemMethod labStandardItemMethod:listLabStandardItemMethod){
				if(labStandardItemMethod!=null&&labStandardItemMethod.getMethod()!=null){
					methodIds+=labStandardItemMethod.getMethod().getId();
					methodNames+=labStandardItemMethod.getMethod().getName();
					methodIds+=",";
					methodNames+=",";
				}
			}
		}
		if(!StrUtils.isBlankOrNull(methodIds)&&!StrUtils.isBlankOrNull(methodNames)){
			if(methodIds.indexOf(",")>-1){
				methodIds=methodIds.substring(0,methodIds.length()-1);
			}
			if(methodNames.indexOf(",")>-1){
				methodNames=methodNames.substring(0,methodNames.length()-1);
			}
		}
		method[0]=methodIds;
		method[1]=methodNames;
		return method;
	}
	@Override
	public boolean addLabStandardMinItem(LabStandardItemVo labStandardItemVo) throws GlobalException {
		LabStandard labStandard=null;
		LabItem labItem=null;
		boolean key=true;
		if(!StrUtils.isBlankOrNull(labStandardItemVo.getStandardId())){
			labStandard=(LabStandard) labStandardDAO.findById(LabStandard.class, labStandardItemVo.getStandardId());
		}
		if(!StrUtils.isBlankOrNull(labStandardItemVo.getItemId())){
			labItem=(LabItem) labItemDAO.findById(LabItem.class, labStandardItemVo.getItemId());
		}
		try{
			if(labStandardItemVo.getItemsIds()!=null&&labStandardItemVo.getItemsIds().length>0){
				for(String itemsId:labStandardItemVo.getItemsIds()){
					if(!StrUtils.isBlankOrNull(itemsId)){
						LabStandardItem LabStandardItem=new LabStandardItem();
						LabItem itemPo=(LabItem) labItemDAO.findById(LabItem.class, itemsId);
						LabStandardItem.setStandard(labStandard);
						if(labItem!=null){
							LabStandardItem.setItem(labItem);
						}
						LabStandardItem.setMinItem(itemPo);
						if(!StrUtils.isBlankOrNull(labItem.getDemo1()))
						LabStandardItem.setScores(labItem.getDemo1());
						labStandardItemDAO.addLabStandardItem(LabStandardItem);
					}
				}
			}
		}catch(Exception e){
			//e.printStackTrace();
			key=false;	
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean addLabStandardItemMethod(
			LabStandardItemMethodVo labStandardItemMethodVo)
			throws GlobalException {
		//先删除  后添加
		boolean key=true;
		if(!StrUtils.isBlankOrNull(labStandardItemMethodVo.getStandardId())&&!StrUtils.isBlankOrNull(labStandardItemMethodVo.getItemId())){
			String hql="FROM LabStandardItemMethod WHERE isDel='"+Constants_Source.N+"' AND standard.id='"+labStandardItemMethodVo.getStandardId()+"' AND item.id='"+labStandardItemMethodVo.getItemId()+"'";
			List<LabStandardItemMethod> listLabStandardItemMethod=labStandardItemMethodDAO.find(hql);
			if(listLabStandardItemMethod!=null&&listLabStandardItemMethod.size()>0){
				labStandardItemMethodDAO.deleteAll(listLabStandardItemMethod);
			}
			LabStandard labStandard=labStandardDAO.getLabStandard(labStandardItemMethodVo.getStandardId());
			LabItem labItem=labItemDAO.getLabItem(labStandardItemMethodVo.getItemId());
			if(labStandard!=null&&labItem!=null&&labStandardItemMethodVo.getIds()!=null){
				for(String id:labStandardItemMethodVo.getIds()){
					LabStandardItemMethod labStandardItemMethod=new LabStandardItemMethod();
					if(!StrUtils.isBlankOrNull(id)){
						LabMethod labMethod=(LabMethod) labMethodDAO.findById(LabMethod.class, id);
						if(labMethod!=null){
							labStandardItemMethod.setMethod(labMethod);
							labStandardItemMethod.setItem(labItem);
							labStandardItemMethod.setStandard(labStandard);
							labStandardItemMethod.setStandardName(labStandard.getName());
							labStandardItemMethodDAO.addLabStandardItemMethod(labStandardItemMethod);
						}
						
					}
				}
			}else if(labStandard!=null&&labItem!=null&&labStandardItemMethodVo.getMethodIds()!=null){
				String[] methodIds = labStandardItemMethodVo.getMethodIds().split(",");
				for(String methodid : methodIds){
					LabStandardItemMethod labStandardItemMethod=new LabStandardItemMethod();
					LabMethod labMethod=(LabMethod) labMethodDAO.findById(LabMethod.class, methodid);
						if(labMethod!=null){
							labStandardItemMethod.setMethod(labMethod);
							labStandardItemMethod.setItem(labItem);
							labStandardItemMethod.setStandard(labStandard);
							labStandardItemMethod.setStandardName(labStandard.getName());
							labStandardItemMethodDAO.addLabStandardItemMethod(labStandardItemMethod);
					}
				}
			}
		}
		return key;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public boolean updateImportLabStandardItem(LabStandardItemVo standItemVo, List<LabStandardItemVo> standItemList) throws GlobalException {
		LabStandard standard = labStandardDAO.getLabStandard(standItemVo.getStandardId());
		if (null == standard) {
			return false;
		}
		if (null != standItemList) {
			for (LabStandardItemVo sItemVo : standItemList) {
				String standId = standard.getId();
				String itemId = sItemVo.getItemId();
				List<LabStandardItemVo> minSItemList = sItemVo.getMinSItemList();
				// 关联方法标准
				// ....

				// 是否有小项
				if (null != minSItemList && minSItemList.size() > 0) {// 有小项
					for (LabStandardItemVo sItemVo1 : minSItemList) {
						String minItemId = sItemVo1.getMinItemId();
						List<LabStandardItemVo> indexSItemList = sItemVo1.getIndexSItemList();
						for (LabStandardItemVo indexSItemVo : indexSItemList) {
							if (!StrUtils.isBlankOrNull(indexSItemVo.getStandIndexId())) {
								String hql0 = "FROM LabStandardItem WHERE standard.id='" + standId + "'";
								hql0 += " AND isDel='"+Constants_Source.N+"' AND item.id='" + itemId + "'";
								hql0 += " AND minItem.id='" + minItemId + "'";
								hql0 += " AND standIndex.id='" + indexSItemVo.getStandIndexId() + "'";
								List<LabStandardItem> indexList = labStandardItemDAO.find(hql0);
								LabStandardItem po0 = null;
								if (null != indexList && indexList.size() > 0) {
									po0 = indexList.get(0);
									if (null != indexSItemVo.getKey() && (indexSItemVo.getKey().equals(">") || indexSItemVo.getKey().equals(">="))) {
										po0.setMinKey(indexSItemVo.getKey());
										po0.setMinValue(indexSItemVo.getValue());
										po0.setMaxKey(null);
										po0.setMaxValue(null);
										po0.setDesc(null);
									} else if (null != indexSItemVo.getKey() && (indexSItemVo.getKey().equals("<") || indexSItemVo.getKey().equals("<="))) {
										po0.setMaxKey(indexSItemVo.getKey());
										po0.setMaxValue(indexSItemVo.getValue());
										po0.setMinKey(null);
										po0.setMinValue(null);
										po0.setDesc(null);
									} else {
										po0.setMinKey(null);
										po0.setMinValue(null);
										po0.setMaxKey(null);
										po0.setMaxValue(null);
										po0.setDesc(indexSItemVo.getValue());
									}

									if (indexSItemVo.getMaxKey() != null && !"".equals(indexSItemVo.getMaxKey())) {
										po0.setMaxKey(indexSItemVo.getMaxKey());
										po0.setMaxValue(indexSItemVo.getMaxValue());
									}
									if (indexSItemVo.getMinKey() != null && !"".equals(indexSItemVo.getMinKey())) {
										po0.setMinKey(indexSItemVo.getMinKey());
										po0.setMinValue(indexSItemVo.getMinValue());
									}
									po0.setScores(indexSItemVo.getScores());
									po0.setTestTime(indexSItemVo.getTestTime());
									labStandardItemDAO.updateLabStandardItem(po0);
								} else {
									po0 = new LabStandardItem();
									po0.setStandard(standard);
									LabItem item = labItemDAO.getLabItem(itemId);
									po0.setItem(item);
									LabItem minItem = labItemDAO.getLabItem(minItemId);
									po0.setMinItem(minItem);
									if (null != indexSItemVo.getKey() && (indexSItemVo.getKey().equals(">") || indexSItemVo.getKey().equals(">="))) {
										po0.setMinKey(indexSItemVo.getKey());
										po0.setMinValue(indexSItemVo.getValue());
									} else if (null != indexSItemVo.getKey() && (indexSItemVo.getKey().equals("<") || indexSItemVo.getKey().equals("<="))) {
										po0.setMaxKey(indexSItemVo.getKey());
										po0.setMaxValue(indexSItemVo.getValue());
									} else {
										po0.setDesc(indexSItemVo.getValue());
									}

									if (indexSItemVo.getMaxKey() != null && !"".equals(indexSItemVo.getMaxKey())) {
										po0.setMaxKey(indexSItemVo.getMaxKey());
										po0.setMaxValue(indexSItemVo.getMaxValue());
									}
									if (indexSItemVo.getMinKey() != null && !"".equals(indexSItemVo.getMinKey())) {
										po0.setMinKey(indexSItemVo.getMinKey());
										po0.setMinValue(indexSItemVo.getMinValue());
									}

									po0.setScores(indexSItemVo.getScores());
									po0.setTestTime(indexSItemVo.getTestTime());
									labStandardItemDAO.addLabStandardItem(po0);
								}
							} else {
								String hql0 = "FROM LabStandardItem WHERE standard.id='" + standId + "'";
								hql0 += " AND isDel='"+Constants_Source.N+"' AND item.id='" + itemId +  "'";
								hql0 += " AND minItem.id='" + minItemId + "'";
								List<LabStandardItem> indexList = labStandardItemDAO.find(hql0);
								LabStandardItem po0 = null;
								if (null != indexList && indexList.size() > 0) {
									po0 = indexList.get(0);
									if (null != indexSItemVo.getKey() && (indexSItemVo.getKey().equals(">") || indexSItemVo.getKey().equals(">="))) {
										po0.setMinKey(indexSItemVo.getKey());
										po0.setMinValue(indexSItemVo.getValue());
									} else if (null != indexSItemVo.getKey() && (indexSItemVo.getKey().equals("<") || indexSItemVo.getKey().equals("<="))) {
										po0.setMaxKey(indexSItemVo.getKey());
										po0.setMaxValue(indexSItemVo.getValue());
									} else {
										po0.setDesc(indexSItemVo.getDesc());
									}
									if (indexSItemVo.getMaxKey() != null && !"".equals(indexSItemVo.getMaxKey())) {
										po0.setMaxKey(indexSItemVo.getMaxKey());
										po0.setMaxValue(indexSItemVo.getMaxValue());
									}
									if (indexSItemVo.getMinKey() != null && !"".equals(indexSItemVo.getMinKey())) {
										po0.setMinKey(indexSItemVo.getMinKey());
										po0.setMinValue(indexSItemVo.getMinValue());
									}
									po0.setScores(indexSItemVo.getScores());
									po0.setTestTime(indexSItemVo.getTestTime());
									labStandardItemDAO.updateLabStandardItem(po0);
								} else {
									po0 = new LabStandardItem();
									po0.setStandard(standard);
									LabItem item = labItemDAO.getLabItem(itemId);
									po0.setItem(item);
									LabItem minItem = labItemDAO.getLabItem(minItemId);
									po0.setMinItem(minItem);
									if (null != indexSItemVo.getKey() && (indexSItemVo.getKey().equals(">") || indexSItemVo.getKey().equals(">="))) {
										po0.setMinKey(indexSItemVo.getKey());
										po0.setMinValue(indexSItemVo.getValue());
									} else if (null != indexSItemVo.getKey() && (indexSItemVo.getKey().equals("<") || indexSItemVo.getKey().equals("<="))) {
										po0.setMaxKey(indexSItemVo.getKey());
										po0.setMaxValue(indexSItemVo.getValue());
									} else {
										po0.setDesc(indexSItemVo.getValue());
									}

									if (indexSItemVo.getMaxKey() != null && !"".equals(indexSItemVo.getMaxKey())) {
										po0.setMaxKey(indexSItemVo.getMaxKey());
										po0.setMaxValue(indexSItemVo.getMaxValue());
									}
									if (indexSItemVo.getMinKey() != null && !"".equals(indexSItemVo.getMinKey())) {
										po0.setMinKey(indexSItemVo.getMinKey());
										po0.setMinValue(indexSItemVo.getMinValue());
									}

									po0.setScores(indexSItemVo.getScores());
									po0.setTestTime(indexSItemVo.getTestTime());
									labStandardItemDAO.addLabStandardItem(po0);
								}
							}
						}
					}
				} else {// 无小项直接限量
					List<LabStandardItemVo> indexSItemList = sItemVo.getIndexSItemList();
					// 分类限量
					for (LabStandardItemVo indexSItemVo : indexSItemList) {
						if (!StrUtils.isBlankOrNull(indexSItemVo.getStandIndexId())) {
							String hql0 = "FROM LabStandardItem WHERE standard.id='" + standId + "'";
							hql0 += " AND isDel='"+Constants_Source.N+"' AND item.id='" + itemId + "'";
							hql0 += " AND standIndex.id='" + indexSItemVo.getStandIndexId() + "'";
							List<LabStandardItem> indexList = labStandardItemDAO.find(hql0);
							LabStandardItem po0 = null;
							if (null != indexList && indexList.size() > 0) {
								po0 = indexList.get(0);
								if (null != indexSItemVo.getKey() && (indexSItemVo.getKey().equals(">") || indexSItemVo.getKey().equals(">="))) {
									po0.setMinKey(indexSItemVo.getKey());
									po0.setMinValue(indexSItemVo.getValue());
									po0.setMaxKey(null);
									po0.setMaxValue(null);
									po0.setDesc(null);
								} else if (null != indexSItemVo.getKey() && (indexSItemVo.getKey().equals("<") || indexSItemVo.getKey().equals("<="))) {
									po0.setMaxKey(indexSItemVo.getKey());
									po0.setMaxValue(indexSItemVo.getValue());
									po0.setMinKey(null);
									po0.setMinValue(null);
									po0.setDesc(null);
								} else {
									po0.setMinKey(null);
									po0.setMinValue(null);
									po0.setMaxKey(null);
									po0.setMaxValue(null);
									po0.setDesc(indexSItemVo.getValue());
								}

								if (indexSItemVo.getMaxKey() != null && !"".equals(indexSItemVo.getMaxKey())) {
									po0.setMaxKey(indexSItemVo.getMaxKey());
									po0.setMaxValue(indexSItemVo.getMaxValue());
								}
								if (indexSItemVo.getMinKey() != null && !"".equals(indexSItemVo.getMinKey())) {
									po0.setMinKey(indexSItemVo.getMinKey());
									po0.setMinValue(indexSItemVo.getMinValue());
								}

								po0.setScores(indexSItemVo.getScores());
								po0.setTestTime(indexSItemVo.getTestTime());
								labStandardItemDAO.updateLabStandardItem(po0);
							} else {
								po0 = new LabStandardItem();
								po0.setStandard(standard);
								LabItem item = labItemDAO.getLabItem(itemId);
								po0.setItem(item);
								if (null != indexSItemVo.getKey() && (indexSItemVo.getKey().equals(">") || indexSItemVo.getKey().equals(">="))) {
									po0.setMinKey(indexSItemVo.getKey());
									po0.setMinValue(indexSItemVo.getValue());
								} else if (null != indexSItemVo.getKey() && (indexSItemVo.getKey().equals("<") || indexSItemVo.getKey().equals("<="))) {
									po0.setMaxKey(indexSItemVo.getKey());
									po0.setMaxValue(indexSItemVo.getValue());
								} else {
									po0.setDesc(indexSItemVo.getValue());
								}

								if (indexSItemVo.getMaxKey() != null && !"".equals(indexSItemVo.getMaxKey())) {
									po0.setMaxKey(indexSItemVo.getMaxKey());
									po0.setMaxValue(indexSItemVo.getMaxValue());
								}
								if (indexSItemVo.getMinKey() != null && !"".equals(indexSItemVo.getMinKey())) {
									po0.setMinKey(indexSItemVo.getMinKey());
									po0.setMinValue(indexSItemVo.getMinValue());
								}

								po0.setScores(indexSItemVo.getScores());
								po0.setTestTime(indexSItemVo.getTestTime());
								labStandardItemDAO.addLabStandardItem(po0);
							}
						} else {
							String hql0 = "FROM LabStandardItem WHERE standard.id='" + standId + "'";
							hql0 += " AND isDel='"+Constants_Source.N+"' AND item.id='" + itemId + "'";
							List<LabStandardItem> indexList = labStandardItemDAO.find(hql0);
							LabStandardItem po0 = null;
							if (null != indexList && indexList.size() > 0) {
								po0 = indexList.get(0);
								if (null != indexSItemVo.getKey() && (indexSItemVo.getKey().equals(">") || indexSItemVo.getKey().equals(">="))) {
									po0.setMinKey(indexSItemVo.getKey());
									po0.setMinValue(indexSItemVo.getValue());
								} else if (null != indexSItemVo.getKey() && (indexSItemVo.getKey().equals("<") || indexSItemVo.getKey().equals("<="))) {
									po0.setMaxKey(indexSItemVo.getKey());
									po0.setMaxValue(indexSItemVo.getValue());
								} else {
									po0.setDesc(indexSItemVo.getDesc());
								}
								if (indexSItemVo.getMaxKey() != null && !"".equals(indexSItemVo.getMaxKey())) {
									po0.setMaxKey(indexSItemVo.getMaxKey());
									po0.setMaxValue(indexSItemVo.getMaxValue());
								}
								if (indexSItemVo.getMinKey() != null && !"".equals(indexSItemVo.getMinKey())) {
									po0.setMinKey(indexSItemVo.getMinKey());
									po0.setMinValue(indexSItemVo.getMinValue());
								}
								po0.setScores(indexSItemVo.getScores());
								po0.setTestTime(indexSItemVo.getTestTime());
								labStandardItemDAO.updateLabStandardItem(po0);
							} else {
								po0 = new LabStandardItem();
								po0.setStandard(standard);
								LabItem item = labItemDAO.getLabItem(itemId);
								po0.setItem(item);
								if (null != indexSItemVo.getKey() && (indexSItemVo.getKey().equals(">") || indexSItemVo.getKey().equals(">="))) {
									po0.setMinKey(indexSItemVo.getKey());
									po0.setMinValue(indexSItemVo.getValue());
								} else if (null != indexSItemVo.getKey() && (indexSItemVo.getKey().equals("<") || indexSItemVo.getKey().equals("<="))) {
									po0.setMaxKey(indexSItemVo.getKey());
									po0.setMaxValue(indexSItemVo.getValue());
								} else {
									po0.setDesc(indexSItemVo.getValue());
								}

								if (indexSItemVo.getMaxKey() != null && !"".equals(indexSItemVo.getMaxKey())) {
									po0.setMaxKey(indexSItemVo.getMaxKey());
									po0.setMaxValue(indexSItemVo.getMaxValue());
								}
								if (indexSItemVo.getMinKey() != null && !"".equals(indexSItemVo.getMinKey())) {
									po0.setMinKey(indexSItemVo.getMinKey());
									po0.setMinValue(indexSItemVo.getMinValue());
								}

								po0.setScores(indexSItemVo.getScores());
								po0.setTestTime(indexSItemVo.getTestTime());
								labStandardItemDAO.addLabStandardItem(po0);
							}
						}
					}
				}
			}
		}
		return true;
	}
	
	@Override
	public boolean updateLabStandardItem(List<LabStandardItemVo> listLabStandardItemVo) throws GlobalException{
		boolean key=true;
		if(listLabStandardItemVo!=null&&listLabStandardItemVo.size()>0){
			for(LabStandardItemVo labStandardItemVo:listLabStandardItemVo){
				if(!StrUtils.isBlankOrNull(labStandardItemVo.getId())){
					LabStandardItem  labStandardItem=labStandardItemDAO.findById(labStandardItemVo.getId());
					if(!StrUtils.isBlankOrNull(labStandardItemVo.getScores())){
						labStandardItem.setScores(labStandardItemVo.getScores());
					}
					if(!StrUtils.isBlankOrNull(labStandardItemVo.getMinKey())&&!StrUtils.isBlankOrNull(labStandardItemVo.getMinValue())){
						labStandardItem.setMinKey(labStandardItemVo.getMinKey());
						labStandardItem.setMinValue(labStandardItemVo.getMinValue());
					}
					if(!StrUtils.isBlankOrNull(labStandardItemVo.getMaxKey())&&!StrUtils.isBlankOrNull(labStandardItemVo.getMaxValue())){
						labStandardItem.setMaxKey(labStandardItemVo.getMaxKey());
						labStandardItem.setMaxValue(labStandardItemVo.getMaxValue());
					}
					if(!StrUtils.isBlankOrNull(labStandardItemVo.getDecimalCount())){
						labStandardItem.setDecimalCount(labStandardItemVo.getDecimalCount());
					}
					if (!StrUtils.isBlankOrNull(labStandardItemVo.getLabSamTypeId())) {
						LabSamType labSamType=(LabSamType) labMethodDAO.findById(LabSamType.class, labStandardItemVo.getLabSamTypeId());
						labStandardItem.setLabSamType(labSamType);
					}
					if (!StrUtils.isBlankOrNull(labStandardItemVo.getItemType())) {
						labStandardItem.setItemType(labStandardItemVo.getItemType());
					}
					labStandardItem.setTestType(labStandardItemVo.getTestType());
					/*if (!StrUtils.isBlankOrNull(labStandardItemVo.getTestType())) {
						
					}*/
					labStandardItemDAO.updateLabStandardItem(labStandardItem);
					if(labStandardItemVo.getMinSItemList()!=null&&labStandardItemVo.getMinSItemList().size()>0){
						for(LabStandardItemVo sonItemVo:labStandardItemVo.getMinSItemList()){
							if(!StrUtils.isBlankOrNull(sonItemVo.getId())){
								LabStandardItem  sonItem=labStandardItemDAO.findById(sonItemVo.getId());
								if(!StrUtils.isBlankOrNull(sonItemVo.getScores())){
									sonItem.setScores(sonItemVo.getScores());
								}
								if(!StrUtils.isBlankOrNull(sonItemVo.getMinKey())&&!StrUtils.isBlankOrNull(sonItemVo.getMinValue())){
									sonItem.setMinKey(sonItemVo.getMinKey());
									sonItem.setMinValue(sonItemVo.getMinValue());
								}
								if(!StrUtils.isBlankOrNull(sonItemVo.getMaxKey())&&!StrUtils.isBlankOrNull(sonItemVo.getMaxValue())){
									sonItem.setMaxKey(sonItemVo.getMaxKey());
									sonItem.setMaxValue(sonItemVo.getMaxValue());
								}
								if(!StrUtils.isBlankOrNull(sonItemVo.getDecimalCount())){
									sonItem.setDecimalCount(sonItemVo.getDecimalCount());
								}
								if (!StrUtils.isBlankOrNull(sonItemVo.getLabSamTypeId())) {
									LabSamType labSamType=(LabSamType) labMethodDAO.findById(LabSamType.class, sonItemVo.getLabSamTypeId());
									sonItem.setLabSamType(labSamType);
								}
								if (!StrUtils.isBlankOrNull(sonItemVo.getItemType())) {
									sonItem.setItemType(sonItemVo.getItemType());
								}
								if (!StrUtils.isBlankOrNull(sonItemVo.getTestType())) {
									sonItem.setTestType(sonItemVo.getTestType());
								}
								labStandardItemDAO.updateLabStandardItem(sonItem);
							}
						}
					}
				}
				
			}
		}
		return key;
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean deleteLabStandardItem(LabStandardItemVo labStandardItemVo)
			throws GlobalException {
		boolean key=false;
		String hql="";
		if(labStandardItemVo.getTypex().equals(Constants_Source.TRUE)){
			//处理子项
			hql="FROM LabStandardItem WHERE isDel='"+Constants_Source.N+"' AND minItem.id='"+labStandardItemVo.getItemId()+"'";
		}else{
			hql="FROM LabStandardItem WHERE isDel='"+Constants_Source.N+"' AND id='"+labStandardItemVo.getId()+"'";
		}
		
		if(!StrUtils.isBlankOrNull(hql)){
			try{
				List<LabStandardItem> listLabStandardItem=labStandardItemDAO.find(hql);
				if(listLabStandardItem!=null&&listLabStandardItem.size()>0){
					LabStandardItem labStandardItem = listLabStandardItem.get(0);
					labStandardItem.setIsDel(Constants_Source.Y);
					labStandardItemDAO.updateLabStandardItem(labStandardItem);
				}
				key=true;
			}catch(Exception e){
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabStandardItemVo> getLabStandardItemVoByStandId(String standId, String itemName,String itemId) throws GlobalException {
		String hql = "FROM LabStandardItem WHERE 1=1 AND isDel='"+Constants_Source.N+"'";
		if (!StrUtils.isBlankOrNull(standId))
			hql += " AND standard.id = '" + standId + "'";
		if (!StrUtils.isBlankOrNull(itemId))
			hql += " AND item.id = '" + itemId + "'";
		if (!StrUtils.isBlankOrNull(itemName))
			hql += " AND itemName LIKE '%" + itemName + "%'";
		hql += " GROUP BY item.id";
		List<LabStandardItem> standardItemList = labStandardItemDAO.find(hql);
		List<LabStandardItemVo> standardItemVoList = new ArrayList<LabStandardItemVo>();
		if (null != standardItemList) {
			for (LabStandardItem standItem : standardItemList) {// 取项目
				LabStandardItemVo standItemVo = new LabStandardItemVo();
				standItemVo.setStandardId(standItem.getStandard().getId());
				standItemVo.setStandardName(standItem.getStandard().getName());
				standItemVo.setFlag(standItem.getStandard().getCode());
				standItemVo.setItemId(standItem.getItem().getId());
				standItemVo.setId(standItem.getId());
				standItemVo.setItemUnit(standItem.getItem().getUnit());
				standItemVo.setScores(standItem.getScores());
				standItemVo.setTestTime(standItem.getTestTime());
				standItemVo.setSort(standItem.getSort());
				standItemVo.setIsGeneralItems(standItem.getIsGeneralItems());
				if ((standItem.getItem().getPrice()) != null) {
					standItemVo.setPrice(standItem.getItem().getPrice());
				}
				// 方法标准
				String methodIds = "";
				String methodNames = "";
				// 获得检测项目
				List<LabStandardItemMethod> simList = labStandardItemMethodDAO.getListByStandardIdAndItemId(standId, standItem.getItem().getId());
				List<LabMethodVo> methodList = new ArrayList<LabMethodVo>();
				if (null != simList && simList.size() > 0) {
					for (LabStandardItemMethod sim : simList) {
						methodIds += sim.getMethod().getId() + ",";
						methodNames += sim.getMethod().getCode() + ",";
						LabMethodVo vo = new LabMethodVo();
						vo.setId(sim.getMethod().getId());
						vo.setName(sim.getMethod().getName());
						vo.setPrice(sim.getMethod().getPrice());
						vo.setCode(sim.getMethod().getCode());
						methodList.add(vo);
					}
				}
				if (methodIds.length() > 0) {
					methodIds = methodIds.substring(0, methodIds.length() - 1);
				}
				if (methodNames.length() > 0) {
					methodNames = methodNames.substring(0, methodNames.length() - 1);
				}
				standItemVo.setMethodIds(methodIds);
				standItemVo.setMethodNames(methodNames);
				standItemVo.setMethodList(methodList);
				// 是否有小项，有则取值
				String hql1 = "FROM LabStandardItem WHERE isDel='"+Constants_Source.N+"' AND standard.id='" + standId + "'";
				hql1 += " AND item.id='" + standItem.getItem().getId() + "'";
				hql1 += " AND minItem IS NOT NULL";
				hql1 += " GROUP BY minItem.id";
				hql1 += " ORDER BY createTime,";
				hql1 += " id ASC";
				List<LabStandardItem> minSItemList = labStandardItemDAO.find(hql1);
				if (null != minSItemList && minSItemList.size() > 0) {
					List<LabStandardItemVo> minSItemVoList = new ArrayList<LabStandardItemVo>();
					standItemVo.setIsHasMinItem("1");
					for (LabStandardItem subStandItem : minSItemList) {
						LabStandardItemVo minItemVo = new LabStandardItemVo();
						minItemVo.setMinItemId(subStandItem.getMinItem().getId());
						minItemVo.setMinItemUnit(subStandItem.getMinItem().getName());
						// 方法标准
						String mmethodIds = "";
						String mmethodNames = "";
						List<LabStandardItemMethod> simList1 = labStandardItemMethodDAO.getListByStandardIdAndItemId(standId, minItemVo.getMinItemId());
						methodList = new ArrayList<LabMethodVo>();
						if (null != simList1 && simList1.size() > 0) {
							for (LabStandardItemMethod sim : simList1) {
								mmethodIds += sim.getMethod().getId() + ",";
								mmethodNames += sim.getMethod().getCode() + ",";
								LabMethodVo vo = new LabMethodVo();
								vo.setId(sim.getMethod().getId());
								vo.setName(sim.getMethod().getName());                    
								vo.setPrice(sim.getMethod().getPrice());
								methodList.add(vo);
							}
						}
						if (mmethodIds.length() > 0) {
							mmethodIds = mmethodIds.substring(0, mmethodIds.length() - 1);
						}
						if (mmethodNames.length() > 0) {
							mmethodNames = mmethodNames.substring(0, mmethodNames.length() - 1);
						}
						minItemVo.setMethodList(methodList);
						minItemVo.setMethodIds(mmethodIds);
						minItemVo.setMethodNames(mmethodNames);
						minSItemVoList.add(minItemVo);
					}
					standItemVo.setMinSItemList(minSItemVoList);
				}
				standardItemVoList.add(standItemVo);
			}
		}
		return standardItemVoList;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<LabStandardItemMethodVo> getLabStandardItemMethodList()throws GlobalException {
		String hqlTwo = "FROM LabStandard WHERE isDel='"+Constants_Source.N+"'";
		List<LabStandard> labStandardList = labStandardDAO.find(hqlTwo);
		List<LabStandardItemMethodVo> labStandardItemMethodVoList = new ArrayList<LabStandardItemMethodVo>();
		if( null != labStandardList && labStandardList.size() > 0 ){
			for(LabStandard labStandard : labStandardList){
				LabStandardItemMethodVo labStandardItemMethodVo = new LabStandardItemMethodVo();
				String hql = "FROM LabStandardItem WHERE isDel='"+Constants_Source.N+"'";
				hql += " AND standard.id = '"+labStandard.getId()+"'";
				List<LabStandardItem> labStandardItemList = labStandardItemDAO.find(hql);
				if( null != labStandardItemList && labStandardItemList.size() > 0 ){
					String methodNames = "";
					for(LabStandardItem labStandardItem : labStandardItemList ){
						String sql = "FROM LabStandardItemMethod WHERE isDel = '"+Constants_Source.N+"'";
						sql +=" AND  standard.id='"+labStandardItem.getStandard().getId()+"'";
						sql += " AND  item.id='"+labStandardItem.getItem().getId()+"'";
						List<LabStandardItemMethod> labStandardItemMethodList = labStandardItemMethodDAO.find(sql);
						if( null != labStandardItemMethodList && labStandardItemMethodList.size() > 0 ){
							for(LabStandardItemMethod labStandardItemMethod : labStandardItemMethodList){
								if(labStandardItemMethod.getMethod()!=null){
									methodNames += labStandardItemMethod.getMethod().getName() + ",";
								}
							}
						}
						if(methodNames.length() > 0){
							labStandardItemMethodVo.setMethodName(methodNames.substring(0,methodNames.length()-1));
						}
						labStandardItemMethodVo.setDemo1(labStandardItem.getMaxKey());
						labStandardItemMethodVo.setDemo2(labStandardItem.getMinKey());
						labStandardItemMethodVo.setDemo3(labStandardItem.getMaxValue());
						labStandardItemMethodVo.setDemo4(labStandardItem.getMinValue());
						labStandardItemMethodVo.setDemo5(labStandardItem.getScores());
					}
			}
				labStandardItemMethodVo.setType(labStandard.getStandTypeName());
				labStandardItemMethodVo.setDemo7(labStandard.getCode());
				labStandardItemMethodVo.setStandardName(labStandard.getName());
				labStandardItemMethodVoList.add(labStandardItemMethodVo);
		}
	}
		return labStandardItemMethodVoList;
}
	@SuppressWarnings("unchecked")
	@Override
	public List<LabMethodVo> getLabMethodList(String standId, String itemId) throws GlobalException {
		String hql = "FROM LabStandardItemMethod WHERE 1=1 AND isDel='"+Constants_Source.N+"'";
			hql += " AND standard.id = '" + standId + "'";
			hql += " AND item.id = '" + itemId + "'";
		hql += " GROUP BY item.id";
		List<LabStandardItemMethod> standardItemList = labStandardItemDAO.find(hql);
		List<LabMethodVo> methodVoList = new ArrayList<LabMethodVo>();
		if (null != standardItemList) {
			for (LabStandardItemMethod standItemMehtod : standardItemList) {// 取项目
				LabMethodVo methodVo = new LabMethodVo();
				methodVo.setId(standItemMehtod.getMethod().getId());
				methodVo.setName(standItemMehtod.getMethod().getName());
				methodVoList.add(methodVo);
			}
		}
		return methodVoList;
	}
	@Override
	public String getLabStandardItemMethodListByTestType(
			String testType) throws GlobalException {
		String hql="select distinct(standard.id),standard.code from LabStandardItem where isDel='"+Constants_Source.N+"' and testType='"+testType+"'";
		List<Object[]> list=labStandardItemDAO.find(hql);
		String result="";
		if (list.size()>0) {
			for(int i=0;i<list.size();i++)
			{
				result+=list.get(i)[1]+",";
			}
		}
		if (result.length()>0){
			result=result.substring(0, result.length()-1);
		}
		return result;
	}
	@Override
	public List<LabStandardItemVo> getLabStandardItemByNoType(String testType,String itemname) throws GlobalException {
		String hql="from LabStandardItem where isDel='"+Constants_Source.N+"'";
		if (!StrUtils.isBlankOrNull(testType)) {
			hql+="and testType!='"+testType+"'";
		}
		if (!StrUtils.isBlankOrNull(itemname)) {
			hql+="and item.name='"+itemname+"'";
		}
		List<LabStandardItem> poList=labStandardItemDAO.find(hql);
		List<LabStandardItemVo> voList=new ArrayList<LabStandardItemVo>();
		if (poList.size()>0) {
			for(LabStandardItem po:poList)
			{
				LabStandardItemVo vo=new LabStandardItemVo();
				BeanUtils.copyProperties(po, vo, new String[]{});
				if (null!=po.getStandard()) {
					vo.setStandardName(po.getStandard().getCode());
					vo.setStandardId(po.getStandard().getId());
				 }
				if (null!=po.getItem()) {
					vo.setItemId(po.getItem().getId());
					vo.setItemName(po.getItem().getName());
				}
				voList.add(vo);
			}
		}
		return voList;
	}
}