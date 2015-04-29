package cn.labsoft.labos.source.klg.service.impl;





import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.klg.dao.ILabItemDAO;
import cn.labsoft.labos.source.klg.dao.ILabMethodDAO;
import cn.labsoft.labos.source.klg.dao.ILabStandardItemDAO;
import cn.labsoft.labos.source.klg.dao.ILabStandardItemMethodDAO;
import cn.labsoft.labos.source.klg.dao.ILabStandardTypeDAO;
import cn.labsoft.labos.source.klg.entity.LabItem;
import cn.labsoft.labos.source.klg.entity.LabMethod;
import cn.labsoft.labos.source.klg.entity.LabStandardItem;
import cn.labsoft.labos.source.klg.entity.LabStandardItemMethod;
import cn.labsoft.labos.source.klg.entity.LabStandardType;
import cn.labsoft.labos.source.klg.service.ILabItemService;
import cn.labsoft.labos.source.klg.vo.LabItemVo;
import cn.labsoft.labos.source.klg.vo.LabMethodVo;
import cn.labsoft.labos.source.klg.vo.LabStandardItemVo;
import cn.labsoft.labos.source.klg.vo.LabStandardVo;
import cn.labsoft.labos.utils.ChineseFCUtil;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;


@Service("labItemService")
public class LabItemServiceImpl implements ILabItemService {
	
	private ILabItemDAO labItemDAO;
	private ILabMethodDAO labMethodDAO;
	private ILabStandardTypeDAO labStandardTypeDAO;
	private ILabStandardItemMethodDAO labStandardItemMethodDAO;
	private ILabStandardItemDAO labStandardItemDAO;
	
	@Resource
	public void setLabItemDAO(ILabItemDAO labItemDAO) {
		this.labItemDAO = labItemDAO;
	}
	@Resource
	public void setLabMethodDAO(ILabMethodDAO labMethodDAO) {
		this.labMethodDAO = labMethodDAO;
	}
	@Resource
	public void setLabStandardTypeDAO(ILabStandardTypeDAO labStandardTypeDAO) {
		this.labStandardTypeDAO = labStandardTypeDAO;
	}
	@Resource
	public void setLabStandardItemMethodDAO(
			ILabStandardItemMethodDAO labStandardItemMethodDAO) {
		this.labStandardItemMethodDAO = labStandardItemMethodDAO;
	}
	@Resource
	public void setLabStandardItemDAO(ILabStandardItemDAO labStandardItemDAO) {
		this.labStandardItemDAO = labStandardItemDAO;
	}
	
	private LabItemVo po2vo(LabItem po,LabItemVo labItemVo){
		BeanUtils.copyProperties(po, labItemVo, new String[]{"item"});
		return labItemVo;
	}
	@SuppressWarnings("static-access")
	@Override
	public boolean addLabItem(LabItemVo labItemVo) throws GlobalException {
		LabItem po=new LabItem();
		BeanUtils.copyProperties(labItemVo, po, new String[]{"isDel","createTime"});
		po.setNameIndex(ChineseFCUtil.cn2py(po.getName()));
		po.setIsDel(Constants_Source.N);
		try {
			labItemDAO.addLabItem(po);
			return true;
		} catch (Exception ex) {
			Log4J.error(ex.getMessage(), ex);
			throw new GlobalException("增加项目出错！" + ex.getMessage());
		}
		
	}
	@SuppressWarnings("unchecked")
	@Override
	public boolean exist4LabItemName(String name, String parentId)
			throws GlobalException {
		String hql="FROM LabItem WHERE isDel='"+Constants_Source.N+"'";
			hql+=" AND name LIKE'"+name+"'";
		if(!StrUtils.isBlankOrNull(parentId)){
			hql+=" AND item.id='"+parentId+"'";
		}
		List<LabItem> list=labItemDAO.find(hql);
		if(null!=list&&list.size()>0)return true;
		else return false;
	}
	@Override
	public LabItemVo getLabItem(String id) throws GlobalException {
		LabItem item = labItemDAO.getLabItem(id);
		LabItemVo itemVo=new LabItemVo();
		this.po2vo(item, itemVo);
		if(!StrUtils.isBlankOrNull(String.valueOf(item.getPrice()))){
			NumberFormat numberFormat = NumberFormat.getNumberInstance();
			 String numberString = numberFormat.format(item.getPrice());
			 if(numberString.indexOf(".") == -1){
				 itemVo.setDemo5(numberString+".0");
			 }else{
				 itemVo.setDemo5(numberString);
			 }
		}
		return itemVo;
	}
	public String getTypeIdAndSubIdStrById(String id) throws GlobalException{
		String idStr=id+",";
		List<LabStandardType> list=labStandardTypeDAO.getStandTypeListByParentId(id);
		if(null!=list&&list.size()>0){
			for (LabStandardType labStandardType : list) {
				idStr+=getTypeIdAndSubIdStrById(labStandardType.getId());
			}
		}
		return idStr;
	}
	@SuppressWarnings("unchecked")
	@Override
	public PageResult  getLabItemPR(LabItemVo labItemVo, PageResult pageResult)
			throws GlobalException{
		try {
			String hql = "FROM LabItem WHERE 1=1 AND isDel = '"+Constants_Source.N+"' ";
			//添加查询条件
			if(!StrUtils.isBlankOrNull(labItemVo.getType())){
		 		String type=getTypeIdAndSubIdStrById(labItemVo.getType());
		 		hql+=" AND type in ('"+type.replace(",", "','")+"')";
		 	}
			if(null!=labItemVo.getName()&&!"".equals(labItemVo.getName())){
				hql+=" AND name LIKE '%" + labItemVo.getName() + "%'";
			}
			if(null!=labItemVo.getCode()&&!"".equals(labItemVo.getCode())){
				hql+=" AND code LIKE '%" + labItemVo.getCode() + "%'";
			}
			pageResult = labItemDAO.getPageResult(hql, pageResult);
			List<LabItem> poList = pageResult.getResultList();
			List<LabItemVo> voList = null;
			if(null!=poList&&poList.size()>0){
				voList = new ArrayList<LabItemVo>();
				for(LabItem po:poList){
					LabItemVo vo = new LabItemVo();
					this.po2vo(po, vo);
					if(!StrUtils.isBlankOrNull(String.valueOf(po.getPrice()))){
						NumberFormat numberFormat = NumberFormat.getNumberInstance();
						 String numberString = numberFormat.format(po.getPrice());
						 if(numberString.indexOf(".") == -1){
							 vo.setDemo5(numberString+".0");
						 }else{
							 vo.setDemo5(numberString);
						 }
					}
					voList.add(vo);
				}
			}
			pageResult.setResultList(voList);
			return pageResult;
		} catch (Exception e) {
			Log4J.error(e.getMessage(), e);
 			throw new GlobalException("项目分页查询出错！" + e.getMessage());
		}
	}
	@Override
	public List<LabItemVo> getLabItemListByParentId(String parentId)
			throws GlobalException {
		List<LabItemVo> voList =new ArrayList<LabItemVo>();
		try {
			List<LabItem> itemList = labItemDAO.getLabItemListByParentId(parentId);
			if(null!=itemList&&itemList.size()>0){
				for(LabItem po:itemList){
					LabItemVo itemVo = new LabItemVo();
					this.po2vo(po, itemVo);
					voList.add(itemVo);
				}
			}
			return voList;
		} catch (Exception e) {	
			//e.printStackTrace();
			throw new GlobalException("根据父id获取子项目列表出错！" + e.getMessage());
		}
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<LabItemVo> getLabItemList(LabItemVo labItemVo)
			throws GlobalException {
		String hql = "FROM LabItem  WHERE 1=1 AND isDel = '"+Constants_Source.N+"' ";
		//添加查询条件
		if(!StrUtils.isBlankOrNull(labItemVo.getType())){
	 		String type=getTypeIdAndSubIdStrById(labItemVo.getType());
	 		hql+=" AND type in ('"+type.replace(",", "','")+"')";
	 	}
		if(null!=labItemVo.getName()&&!"".equals(labItemVo.getName())){
			String items=labItemVo.getName().replaceAll("，", ",").trim().replaceAll(" ", "");
			hql+=" AND (";
			for(String item:items.split(",")){
				hql+="  name LIKE '%" + item + "%' OR";
			}
			hql=hql.substring(0,hql.length()-2);
			hql+=" )";
		}
		if(null!=labItemVo.getCode()&&!"".equals(labItemVo.getCode())){
			hql+=" AND code LIKE '%" + labItemVo.getCode() + "%'";
		}
		if(null!=labItemVo.getCategoryIds()&&!"".equals(labItemVo.getCategoryIds())){
			hql+=" AND categoryIds LIKE '%" + labItemVo.getCategoryIds() + "%'";
		}
		List<LabItem> itemList = labItemDAO.find4All(hql);
		List<LabItemVo> voList = new ArrayList<LabItemVo>();
		if(null!=itemList&&itemList.size()>0){
			for(LabItem po:itemList){
				LabItemVo itemVo = new LabItemVo();
				this.po2vo(po, itemVo);
				if(!StrUtils.isBlankOrNull(String.valueOf(po.getPrice()))){
					NumberFormat numberFormat = NumberFormat.getNumberInstance();
					 String numberString = numberFormat.format(po.getPrice());
					 if(numberString.indexOf(".") == -1){
						 itemVo.setDemo5(numberString+".0");
					 }else{
						 itemVo.setDemo5(numberString);
					 }
				}
				//标准
				String standId=null;
				List<LabStandardVo> standList=new ArrayList<LabStandardVo>();
				List<LabStandardItem> standlist=labStandardItemDAO.getListByItemId(po.getId());
				if(standlist!=null&&standlist.size()>0){
					for (LabStandardItem labStandardItem: standlist) {
						LabStandardVo standVo=new LabStandardVo();
						standVo.setId(labStandardItem.getStandard().getId());
						standVo.setName(labStandardItem.getStandard().getName());
						standList.add(standVo);
					}
					standId=standlist.get(0).getStandard().getId();
				}
				itemVo.setStandardList(standList);
				//方法
				List<LabMethodVo> methodList=new ArrayList<LabMethodVo>();
				List<LabStandardItemMethod> list=labStandardItemMethodDAO.getListByStandardIdAndItemId(standId, po.getId());
				if(list!=null){
					for (LabStandardItemMethod labStandardItemMethod : list) {
						if(labStandardItemMethod.getMethod()!=null){
							LabMethodVo methodVo=new LabMethodVo();
							methodVo.setId(labStandardItemMethod.getMethod().getId());
							methodVo.setName(labStandardItemMethod.getMethod().getName());
							methodList.add(methodVo);
						}
					}
				}
				itemVo.setMethodList(methodList);
				voList.add(itemVo);
			}
		}
		return voList;
	}
	
//	@Override
//	public List<LabItemVo> getAZLabItemList(LabItemVo labItemVo)
//			throws GlobalException {
//		String hql = "FROM LabItem WHERE 1=1 AND isDel = '"+Constants.N+"' ";
//		//添加查询条件
//		if(null!=labItemVo&&!StrUtils.isBlankOrNull(labItemVo.getParentId())){
//			hql +=" AND labItem.id ='"+labItemVo.getParentId()+"' "; 
//		}else{
//			hql +=" AND labItem IS NULL "; 
//		}
//		if(null!=labItemVo&&!StrUtils.isBlankOrNull(labItemVo.getCategoryIds())){
//			hql +=" AND categoryIds LIKE '%"+labItemVo.getCategoryIds()+"%' "; 
//		}
//		hql+="ORDER BY nameIndex";
//		List<LabItem> itemList = labItemDAO.find(hql);
//		List<LabItemVo> voList = null;
//		if(null!=itemList&&itemList.size()>0){
//			voList = new ArrayList();
//			for(LabItem po:itemList){
//				LabItemVo itemVo = new LabItemVo();
//				this.po2vo(po, itemVo);
//				boolean bb=this.hasChildren(po.getId());
//				itemVo.setHasChildren(bb);
//				voList.add(itemVo);
//			}
//		}
//		return voList;
//	}
	@SuppressWarnings("unchecked")
	@Override
	public List<LabItemVo> getLabItemTreeList(LabItemVo labItemVo)
			throws GlobalException {
		String hql = "FROM LabItem WHERE 1=1 AND isDel = '"+Constants_Source.N+"' ";
		//添加查询条件
		if(null!=labItemVo&&!StrUtils.isBlankOrNull(labItemVo.getParentId())){
			hql +=" AND labItem.id ='"+labItemVo.getParentId()+"' "; 
		}else{
			hql +=" AND labItem IS NULL "; 
		}
		List<LabItem> itemList = labItemDAO.find(hql);
		List<LabItemVo> voList =new ArrayList<LabItemVo>();
		if(null!=itemList&&itemList.size()>0){
			for(LabItem po:itemList){
				LabItemVo itemVo = new LabItemVo();
				this.po2vo(po, itemVo);
				boolean bb=this.hasChildren(po.getId());
				itemVo.setHasChildren(bb);
				voList.add(itemVo);
			}
		}
		return voList;
	}
	@Override
	public int getMaxSort(String parentId) throws GlobalException {
		List<LabItem> itemList = labItemDAO.getLabItemListByParentId(parentId);
		if(null!=itemList&&itemList.size()>0){
			LabItem item=itemList.get(itemList.size()-1);
			return item.getSort();
		}else{
			return 0;
		}
	}
	@Override
	public boolean hasChildren(String id) throws GlobalException {
		return labItemDAO.hasChildren(id);
	}
	@Override
	public boolean update2DelLabItem(String[] ids) throws GlobalException {
		boolean key = false;
		try {
			for(String id:ids){
				List<LabStandardItemMethod> labStandardItemMethodList = labStandardItemMethodDAO.getListByItemId(id);
				if( null != labStandardItemMethodList && labStandardItemMethodList.size() > 0){
					for(LabStandardItemMethod labStandardItemMethod: labStandardItemMethodList){
						labStandardItemMethod.setIsDel(Constants_Source.Y);
						labStandardItemMethodDAO.updateLabStandardItemMethod(labStandardItemMethod);
					}
				}
				List<LabStandardItem> labStandardItemList = labStandardItemDAO.getListByItemId(id);
				if( null != labStandardItemList && labStandardItemList.size() > 0){
					for(LabStandardItem labStandardItem: labStandardItemList){
						labStandardItem.setIsDel(Constants_Source.Y);
						labStandardItemDAO.updateLabStandardItem(labStandardItem);
					}
				}
				LabItem item = (LabItem) labItemDAO.findById(LabItem.class, id.trim());
				item.setIsDel(Constants_Source.Y);
				labItemDAO.updateLabItem(item);
			}
			key = true;
		} catch (Exception e) {
			Log4J.error(e.getMessage(), e);
			//e.printStackTrace();
			throw new GlobalException("删除检测项目出错！" + e.getMessage());
		}
		return key;
	}
	@Override
	public boolean updateLabItem(LabItemVo labItemVo) throws GlobalException {
		if(null!=labItemVo&&!StrUtils.isBlankOrNull(labItemVo.getId())){
			LabItem itemPo=labItemDAO.getLabItem(labItemVo.getId());
			BeanUtils.copyProperties(labItemVo, itemPo, new String []{"isDel","createTime","tenantId","createUserId"});
			itemPo.setNameIndex(cn.labsoft.labos.utils.ChineseFCUtil.cn2py(itemPo.getName()));
			return labItemDAO.updateLabItem(itemPo);
		}else{
			return false;
		}
	}
	
	@Override
	public boolean updateListItem(List<LabItemVo> listLabItemVo) throws GlobalException {
		try{
		if(listLabItemVo!=null&&listLabItemVo.size()>0){
			for(LabItemVo labItemVo:listLabItemVo){
				LabItem itemPo=labItemDAO.getLabItem(labItemVo.getId());
				itemPo.setPrice(labItemVo.getPrice());
				labItemDAO.updateLabItem(itemPo);
			}
		}
		return true;
		}catch(Exception e){
			throw new GlobalException("" + e.getMessage());
		}
    }
	@SuppressWarnings("unchecked")
	@Override
	public boolean addLabItem(LabItemVo labItemVo, List<LabMethodVo> methodList)
			throws GlobalException {
		LabItem po=new LabItem();
		BeanUtils.copyProperties(labItemVo, po, new String[]{"isDel","createTime"});
		po.setNameIndex(ChineseFCUtil.cn2py(po.getName()));
		po.setIsDel(Constants_Source.N);
		labItemDAO.addLabItem(po);
		if(null!=methodList&&methodList.size()>0){
			for (LabMethodVo methodVo : methodList) {
				LabMethod method=null;
				if(!StrUtils.isBlankOrNull(methodVo.getId())){
					String hql="FROM LabMethod WHERE　isDel='"+Constants_Source.N+"' AND name LIKE '"+methodVo.getName()+"'";
					List<LabMethod> list=labItemDAO.find(hql);
					if(list!=null&&list.size()>0){
						method=list.get(0);
					}
				}
				if(null==method){
					method=new LabMethod();
					method.setCode(methodVo.getCode());
					method.setName(methodVo.getName());
					method.setPrice(methodVo.getPrice());
					method.setContext(methodVo.getContext());
					labMethodDAO.addLabMethod(method);
				}
				LabStandardItemMethod  kim=new LabStandardItemMethod();
				kim.setItem(po);
				kim.setMethod(method);
				kim.setCreateTime(DateUtils.getCurrDateTimeStr());
				labStandardItemMethodDAO.addLabStandardItemMethod(kim);
			}
		}
		return true;
	}
	@SuppressWarnings("unchecked")
	@Override
	public PageResult getMinLabItem(PageResult pageResult,LabStandardItemVo labStandardItemVo) throws GlobalException{
		String hql = "FROM LabItem WHERE 1=1 AND isDel = '"+Constants_Source.N+"' ";
		if(!StrUtils.isBlankOrNull(labStandardItemVo.getItemId())){
				hql+=" AND id!='"+labStandardItemVo.getItemId()+"'";
		}
        pageResult = labItemDAO.getPageResult(hql, pageResult);
		List<LabItem> poList = pageResult.getResultList();
		List<LabItemVo> voList = null;
		if(null!=poList&&poList.size()>0){
			voList = new ArrayList<LabItemVo>();
			for(LabItem po:poList){
				LabItemVo vo = new LabItemVo();
				this.po2vo(po, vo);
				voList.add(vo);
			}
			pageResult.setResultList(voList);
		}
		return pageResult;
	}
	@SuppressWarnings("unchecked")
	@Override
	public List<LabItemVo> getMinLabItem(LabStandardItemVo labStandardItemVo) throws GlobalException{
		String hql = "FROM LabItem WHERE 1=1 AND isDel = '"+Constants_Source.N+"' ";
		if(!StrUtils.isBlankOrNull(labStandardItemVo.getItemId())){
				hql+=" AND id!='"+labStandardItemVo.getItemId()+"'";
		}
		List<LabItem> poList  = labItemDAO.find(hql);
		List<LabItemVo> voList = null;
		if(null!=poList&&poList.size()>0){
			voList = new ArrayList<LabItemVo>();
			for(LabItem po:poList){
				LabItemVo vo = new LabItemVo();
				this.po2vo(po, vo);
				voList.add(vo);
			}
		}
		return voList;
	}
}