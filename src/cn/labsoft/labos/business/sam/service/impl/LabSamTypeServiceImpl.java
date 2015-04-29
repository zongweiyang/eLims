package cn.labsoft.labos.business.sam.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.business.sam.dao.ILabSamTypeDAO;
import cn.labsoft.labos.business.sam.entity.LabSamType;
import cn.labsoft.labos.business.sam.service.ILabSamTypeService;
import cn.labsoft.labos.business.sam.vo.LabSamTypeVo;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.klg.dao.ILabItemDAO;
import cn.labsoft.labos.source.klg.entity.LabItem;
import cn.labsoft.labos.utils.StrUtils;
@Service("labSamTypeService")
public class LabSamTypeServiceImpl implements ILabSamTypeService {
	private ILabSamTypeDAO labSamTypeDAO;
	private ILabItemDAO labItemDAO;
	
	@Resource
	public void setLabSamTypeDAO(ILabSamTypeDAO labSamTypeDAO) {
		this.labSamTypeDAO = labSamTypeDAO;
	}
	@Resource
	public void setLabItemDAO(ILabItemDAO labItemDAO) {
		this.labItemDAO = labItemDAO;
	}

	@Override
	public LabSamTypeVo addLabSamType(LabSamTypeVo labSamTypeVo) throws GlobalException {
		LabSamType parent = labSamTypeDAO.getLabSamType(labSamTypeVo.getPid());

		LabSamType labSamType = new LabSamType();
		BeanUtils.copyProperties(labSamTypeVo, labSamType, new String[] { "" });
		labSamType.setLabSamType(parent);
		labSamType.setName(labSamTypeVo.getName());
		labSamType.setIsDefault(Constants_Business.N);
		labSamType.setCode(labSamTypeDAO.getLevel(parent, 0) + 1 + "");
		labSamType = labSamTypeDAO.addLabSamType(labSamType);
		labSamTypeVo.setId(labSamType.getId());
		return labSamTypeVo;
	}

	@Override
	public boolean ajaxIsExistName(String name) throws GlobalException {
		boolean exist = false;
		String hql = "FROM LabSamType where name = '" + name + "' AND isDel='N'";
		List<LabSamType> list = labSamTypeDAO.getLabSamTypeList(hql);
		if (null != list && list.size() > 0) {
			exist = true;
		}
		return exist;
	}

	@Override
	public boolean deleteLabSamType(String id) throws GlobalException {
		if (null != id && id.equals("")) {
			labSamTypeDAO.deleteLabSamType(id);
			return true;
		}
		return false;
	}


	private String getLName(LabSamType type, String lName) {
		if (null != type && null != type.getLabSamType()  && !"0".equals(type.getLabSamType().getId())  ) {
			String sName = type.getLabSamType().getName() + " ——>" + lName;
			return getLName(type.getLabSamType(), sName);
		}
		return lName;
	}

	

	@Override
	public List<LabSamTypeVo> getLabSamTypeList(LabSamTypeVo vo) throws GlobalException {
		List<LabSamTypeVo> typelist=new ArrayList<LabSamTypeVo>();
		String whereHql="";
		if(vo.getPid()!=null&&!vo.getPid().equals("")){
			whereHql+=" AND labSamType.id='"+vo.getPid()+"'";
		}
		if(vo.getName()!=null&&!vo.getName().equals("")){
			whereHql+=" AND name like '%"+vo.getName()+"%'";
		}
		List<LabSamTypeVo> list =this.getLabSamTypeVoByWhere(whereHql);
		for (LabSamTypeVo labSamTypeVo : list) {
			labSamTypeVo.setName("　"+labSamTypeVo.getName());
			typelist.add(labSamTypeVo);
			typelist.addAll(this.getLabSamTypeLevelSon(labSamTypeVo, "　"));
		}
		return typelist;
	}

	@SuppressWarnings("unchecked")
	public List<LabSamTypeVo> getLabSamTypeLevelSon(LabSamTypeVo labSamTypeVo,String str) throws GlobalException {
		String str1="　"+str;
		List<LabSamTypeVo> LabSamTypeVoList = new ArrayList<LabSamTypeVo>();
		if (!StrUtils.isBlankOrNull(labSamTypeVo.getId())) {
			String hql=" FROM LabSamType WHERE isDel='N' AND  labSamType.id='"+labSamTypeVo.getId()+"'";
			List<LabSamType> poList=labSamTypeDAO.find(hql);
			if(poList!=null){
				for (LabSamType labSamType : poList) {
					LabSamTypeVo vo =new LabSamTypeVo();
					vo.setPid(labSamTypeVo.getId());
					vo.setId(labSamType.getId());
					vo.setName(str1+labSamType.getName());
					vo.setIsDefault(labSamType.getIsDefault());
					LabSamTypeVoList.add(vo);
					if (this.getLabSamTypeListByPid(vo.getId()).size() > 0) {
						vo.setPid(vo.getId());
						LabSamTypeVoList.addAll(getLabSamTypeLevelSon(vo,str1));
					}
				}
			}
		}
		return LabSamTypeVoList;
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabSamTypePR(LabSamTypeVo vo, PageResult pageResult) throws GlobalException {
		pageResult.setOrderColumn("labSamType.id");
		pageResult.setOrder(PageResult.ORDER_ASC);
		String hql = "FROM LabSamType WHERE 1=1 AND isDel = 'N' ";
		if (!StrUtils.isBlankOrNull(vo.getSearchName())) {
			hql += " AND name like '%" + vo.getSearchName() + "%'";
		}
		pageResult = labSamTypeDAO.getLabSamTypePR(hql, pageResult);
		List<LabSamType> resultList = pageResult.getResultList();
		List<LabSamTypeVo> voList = new ArrayList<LabSamTypeVo>();
		if (null != resultList && resultList.size() > 0) {
			for (LabSamType po : resultList) {
				LabSamTypeVo samTypeVo = new LabSamTypeVo();
				BeanUtils.copyProperties(po, samTypeVo, new String[] { "" });
				if (null != po.getLabSamType()) {
					samTypeVo.setPid(po.getLabSamType().getId());
				}
				samTypeVo.setName(po.getName().trim());
				samTypeVo.setWhere(getLName(po, po.getName()));
				voList.add(samTypeVo);
			}
		}
		pageResult.setResultList(voList);
		return pageResult;
	}

	@Override
	public boolean updateLabSamType(LabSamTypeVo labSamTypeVo) throws GlobalException {
		try {
			LabSamType parent = null;
			if (StringUtils.isNotBlank(labSamTypeVo.getPid())) {
				parent = labSamTypeDAO.getLabSamType(labSamTypeVo.getPid());
			}
			LabSamType items = labSamTypeDAO.getLabSamType(labSamTypeVo.getId());
			items.setName(labSamTypeVo.getName());
			items.setIsDel(Constants_Business.N);
			if (null != parent) {
				if (!parent.getId().equals(items.getLabSamType().getId())) {
					items.setLabSamType(parent);
					// 根据父类的code 获取新的code
					items.setCode(labSamTypeDAO.getLevel(parent, 0) + 1 + "");
				}
			}
			labSamTypeDAO.updateLabSamType(items);
		} catch (Exception e) {
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

	@Override
	public boolean updateLabSamType4Del(String id) throws GlobalException {
		LabSamType labSamType = labSamTypeDAO.getLabSamType(id);
		labSamType.setIsDel(Constants_Business.Y);
		boolean key = labSamTypeDAO.updateLabSamType(labSamType);
		return key;
	}

	@Override
	public LabSamTypeVo getLabSamTypeById(String id) throws GlobalException {
		LabSamType po = labSamTypeDAO.getLabSamType(id);
		LabSamTypeVo vo = new LabSamTypeVo();
		BeanUtils.copyProperties(po, vo, new String[] { "" });
		return vo;
	}

	@Override
	public boolean deleteLabSamType4Batch(String[] ids) throws GlobalException {
		for (String id : ids) {
			LabSamType po = labSamTypeDAO.getLabSamType(id);
			po.setIsDel(Constants_Business.Y);
			labSamTypeDAO.updateLabSamType(po);
		}
		return true;
	}

	@Override
	public boolean getLabSamTypeByPid(LabSamTypeVo labSamTypeVo) throws GlobalException {
		boolean key = true;
		if (labSamTypeVo != null) {
			if (labSamTypeVo.getIds() != null && labSamTypeVo.getIds().length > 0) {
				for (String id : labSamTypeVo.getIds()) {
					List<LabSamType> list = labSamTypeDAO.getLabSamTypeList("FROM LabSamType Where labSamType.id='" + id + "' AND isDel='N'");
					if (list != null && list.size() > 0) {
						key = false;
					}
				}
			}
		}
		return key;
	}

	public List<LabSamTypeVo> getLabSamTypeListByPid(String pid) throws GlobalException {
		List<LabSamTypeVo> listLabSamTypeVo = new ArrayList<LabSamTypeVo>();
		String whereHql = "";
		if (!StrUtils.isBlankOrNull(pid)) {
			whereHql = " AND labSamType.id='" + pid + "' ORDER BY code ASC";
		}
		try {
			listLabSamTypeVo = this.getLabSamTypeVoByWhere(whereHql);
		} catch (Exception e) {
			Log4J.error("LabSamTypeServiceImpl getLabOrgListByPId  error..." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return listLabSamTypeVo;
	}

	public List<LabSamTypeVo> getLabSamTypeVoByWhere(String whereHql) throws GlobalException {
		List<LabSamTypeVo> listLabSamTypeVo = new ArrayList<LabSamTypeVo>();
		List<LabSamType> listLabSamType = new ArrayList<LabSamType>();
		String hql = "FROM LabSamType WHERE isDel='" + Constants_Business.N + "' ";
		if (!StrUtils.isBlankOrNull(whereHql)) {
			hql += whereHql;
		}
		try {
			listLabSamType = labSamTypeDAO.getLabSamTypeList(hql);
			if (listLabSamType != null && listLabSamType.size() > 0) {
				for (LabSamType labsamtype : listLabSamType) {
					LabSamTypeVo labSamTypeVo = new LabSamTypeVo();
					BeanUtils.copyProperties(labsamtype, labSamTypeVo, new String[] { "" });
					if (labsamtype.getLabSamType() != null) {
						labSamTypeVo.setPid(labsamtype.getLabSamType().getId());
					}
					listLabSamTypeVo.add(labSamTypeVo);
				}
			}
		} catch (Exception e) {
			Log4J.error("LabSamTypeServiceImpl getSysOrgVoByWhere error..." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return listLabSamTypeVo;
	}

	@Override
	public LabSamTypeVo getLabSamTypeByVo(LabSamTypeVo labSamTypeVo) throws GlobalException {
		String hql="from LabSamType where isDel='"+Constants_Business.N+"'";
		if (labSamTypeVo.getId()!=null) {
			hql+=" and id='"+labSamTypeVo.getId()+"'";
		}
		if(labSamTypeVo.getName()!=null)
		{
			hql+="and name='"+labSamTypeVo.getName()+"'";
		}
		LabSamType labSamType = (LabSamType) labSamTypeDAO.find(hql, 0);
		LabSamTypeVo vo=new LabSamTypeVo();
		BeanUtils.copyProperties(labSamType, vo, new String[] { "" });
        return vo;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void addLabSamTypeItem(LabSamTypeVo labSamTypeVo) throws GlobalException {
		String itemIds=labSamTypeVo.getItemId();
		LabSamType labSamType=labSamTypeDAO.getLabSamType(labSamTypeVo.getId());
		String hql="FROM LabItem WHERE isDel='"+Constants_Business.N+"'";
		hql+=" AND categoryIds like '%"+labSamType.getId()+"%'";
		List<LabItem> itemList=labItemDAO.find(hql);
		if(itemList!=null&&itemList.size()>0){
			for (LabItem labItem : itemList) {
				if(!itemIds.contains(labItem.getId())){
					String type=labItem.getCategoryIds();
					type=type.replace(labSamType.getId(),"");
					type=type.replace(" ","");
					type=type.replace(",,", ",");
					if(type.startsWith(",")){
						type=type.substring(1);
					}
					labItem.setCategoryIds(type);
					String typeName=labItem.getCategoryNames();
					typeName=typeName.replace(labSamType.getName(),"");
					typeName=typeName.replace(" ","");
					typeName=typeName.replace(",,", ",");
					if(typeName.startsWith(",")){
						typeName=typeName.substring(1);
					}
					labItem.setCategoryNames(typeName);
					labItemDAO.updateLabItem(labItem);
				}
			}
		}
		for(String itemId:itemIds.split(",")){
			if(itemId==null||itemId.trim().length()==0){
				continue;
			}
			LabItem item=labItemDAO.getLabItem(itemId.trim());
			String typeId=item.getCategoryIds();
			String typeName=item.getCategoryNames();
			if(typeId!=null&&typeId.trim().length()>0){
				if(!typeId.contains(labSamType.getId())&&typeId.endsWith(",")){
					typeId+=labSamType.getId();
					typeName+=labSamType.getName();
					item.setCategoryIds(typeId.trim());
					item.setCategoryNames(typeName.trim());
					labItemDAO.updateLabItem(item);
				}else if(!typeId.contains(labSamType.getId())){
					typeId+=","+labSamType.getId();
					typeName+=","+labSamType.getName();
					item.setCategoryIds(typeId.trim());
					item.setCategoryNames(typeName.trim());
					labItemDAO.updateLabItem(item);
				}
			}else{
				typeId=labSamType.getId();
				typeName=labSamType.getName();
				item.setCategoryIds(typeId.trim());
				item.setCategoryNames(typeName.trim());
				labItemDAO.updateLabItem(item);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public LabSamTypeVo getLabSamTypeItem(LabSamTypeVo labSamTypeVo) throws GlobalException {
		String hql="FROM LabItem WHERE isDel='"+Constants_Business.N+"'";
			hql+=" AND categoryIds like '%"+labSamTypeVo.getId()+"%'";
		List<LabItem> labItemList=labItemDAO.find(hql);
		String itemName="";
		String itemId="";
		if(null!=labItemList&&labItemList.size()>0){
			for(LabItem labitem:labItemList){
				itemId+=labitem.getId()+",";
				itemName+=labitem.getName()+",";
			}
			labSamTypeVo.setItemId(itemId.substring(0, itemId.length()-1));
			labSamTypeVo.setItemName(itemName.substring(0, itemName.length()-1));
		}
		return labSamTypeVo;
	}
}
