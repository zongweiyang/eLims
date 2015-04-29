package cn.labsoft.labos.source.klg.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.common.upload.dao.ILabUploadDAO;
import cn.labsoft.labos.common.upload.entity.LabUpload;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.klg.dao.ILabItemDAO;
import cn.labsoft.labos.source.klg.dao.ILabMethodDAO;
import cn.labsoft.labos.source.klg.dao.ILabStandardDAO;
import cn.labsoft.labos.source.klg.dao.ILabStandardItemDAO;
import cn.labsoft.labos.source.klg.dao.ILabStandardItemMethodDAO;
import cn.labsoft.labos.source.klg.dao.ILabStandardTypeDAO;
import cn.labsoft.labos.source.klg.entity.LabItem;
import cn.labsoft.labos.source.klg.entity.LabMethod;
import cn.labsoft.labos.source.klg.entity.LabStandard;
import cn.labsoft.labos.source.klg.entity.LabStandardItem;
import cn.labsoft.labos.source.klg.entity.LabStandardItemMethod;
import cn.labsoft.labos.source.klg.entity.LabStandardType;
import cn.labsoft.labos.source.klg.service.ILabStandardService;
import cn.labsoft.labos.source.klg.vo.LabStandardVo;
import cn.labsoft.labos.utils.OperationExcel;
import cn.labsoft.labos.utils.StrUtils;


@Service("labStandardService")
public class LabStandardServiceImpl implements ILabStandardService {
	
	private ILabStandardDAO labStandardDAO;
	private ILabStandardTypeDAO labStandardTypeDAO;
	private ILabUploadDAO labUploadDAO;
	private ILabItemDAO labItemDAO;
	private ILabMethodDAO labMethodDAO;
	private ILabStandardItemMethodDAO labStandardItemMethodDAO;
	private ILabStandardItemDAO labStandardItemDAO;

	@Resource
	public void setLabStandardDAO(ILabStandardDAO labStandardDAO) {
		this.labStandardDAO = labStandardDAO;
	}
	@Resource
	public void setLabStandardTypeDAO(ILabStandardTypeDAO labStandardTypeDAO) {
		this.labStandardTypeDAO = labStandardTypeDAO;
	}
	@Resource
	public void setLabUploadDAO(ILabUploadDAO labUploadDAO) {
		this.labUploadDAO = labUploadDAO;
	}
	@Resource
	public void setLabItemDAO(ILabItemDAO labItemDAO) {
		this.labItemDAO = labItemDAO;
	}
	@Resource
	public void setLabMethodDAO(ILabMethodDAO labMethodDAO) {
		this.labMethodDAO = labMethodDAO;
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

	@Override
	public boolean addLabStandard(LabStandardVo labStandardVo)
			throws GlobalException {
		boolean falg=true;
      try{
			LabStandard labStandard=new LabStandard();
			labStandard=this.LabStandardVoTOLabStandard(labStandardVo, labStandard);
			if(!StrUtils.isBlankOrNull(labStandardVo.getIsUsed())&&labStandardVo.getIsUsed().equals(Constants_Source.TRUE)){
				if(!StrUtils.isBlankOrNull(labStandardVo.getReplaceIds())){
					labStandard.setStandStatus(Constants_Source.TRUE);
				}else{
					labStandard.setStandStatus(Constants_Source.FALSE);
				}
			}else{
				labStandard.setStandStatus(Constants_Source.FALSE);
			}
			labStandardDAO.addLabStandard(labStandard);
			List<LabUpload> loadList=labUploadDAO.getLabUploadList(labStandardVo.getUuid(),"klg-standard");
			if(loadList!=null){
				for (LabUpload labUpload : loadList) {
					labUpload.setBusId(labStandard.getId());
					labUploadDAO.updateLabUpload(labUpload);
				}
			}
      	}catch(Exception e){
      		//e.printStackTrace();
      		falg=false;
      		throw new GlobalException("" + e.getMessage());
      	}
		return falg;
	}

	@Override
	public boolean exist4StandsCode(String standsNo)
			throws GlobalException {
		 String hql="FROM LabStandard WHERE 1=1 AND isDel='"+Constants_Source.N+"' AND code='"+standsNo+"' ";
		 List<LabStandard> list= labStandardDAO.find(hql);
		 if(list!=null&&list.size()>0){
			 return true;
		 }else{
			 return false;
		 }
	}

	@Override
	public LabStandardVo getLabStandard(String id) throws GlobalException {
		LabStandardVo LabStandardVo=new LabStandardVo();
		if(id!=null&&!id.equals("")){
			LabStandard LabStandard=(LabStandard) labStandardDAO.findById(LabStandard.class, id);
			if(null!=LabStandard){
				this.LabStandardTOLabStandardVo(LabStandard, LabStandardVo);
			}
		}
		return LabStandardVo;
	}

	@Override
	public PageResult getLabStandardPR(LabStandardVo LabStandardVo,PageResult pageResult)
		throws GlobalException{
	    String hql="FROM LabStandard WHERE 1=1 AND isDel='"+Constants_Source.N+"' ";
	 	if(!StrUtils.isBlankOrNull(LabStandardVo.getSearchName())){
	 		hql+=" AND name LIKE '%"+LabStandardVo.getSearchName()+"%'";
	 	}
	 	if(!StrUtils.isBlankOrNull(LabStandardVo.getSearchCode())){
	 		hql+=" AND code LIKE '%"+LabStandardVo.getSearchCode()+"%'";
	 	}
	 	if(!StrUtils.isBlankOrNull(LabStandardVo.getStandTypeId())){
	 		String type=getTypeIdAndSubIdStrById(LabStandardVo.getStandTypeId());
	 		hql+=" AND standType.id IN ('"+type.replace(",", "','")+"')";
	 	}
	    pageResult=labStandardDAO.getPageResult(hql, pageResult);
	 	List<LabStandard> LabStandardPoList=pageResult.getResultList();
	 	List<LabStandardVo> LabStandardVoList=new ArrayList<LabStandardVo>();
	 	if(LabStandardPoList.size()>0){
	 	for(LabStandard po:LabStandardPoList){
	 		LabStandardVo vo=new LabStandardVo();
	 		this.LabStandardTOLabStandardVo(po, vo);
	 		LabStandardVoList.add(vo);
	 		}
	 	}
	 	pageResult.setResultList(LabStandardVoList);
		return pageResult;
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
	@Override
	public boolean update2DelLabStandard(String[] ids) throws GlobalException {
		boolean key=true;
		try{
	    if( null != ids && ids.length > 0){
	    	for( String id : ids ){
	    		List<LabStandardItemMethod> labStandardItemMethodList = labStandardItemMethodDAO.getListByStandardId(id);
				if( null != labStandardItemMethodList && labStandardItemMethodList.size() > 0){
					for(LabStandardItemMethod labStandardItemMethod: labStandardItemMethodList){
						labStandardItemMethod.setIsDel(Constants_Source.Y);
						labStandardItemMethodDAO.updateLabStandardItemMethod(labStandardItemMethod);
					}
				}
				List<LabStandardItem> labStandardItemList = labStandardItemDAO.getListByStandId(id);
				if( null != labStandardItemList && labStandardItemList.size() > 0){
					for(LabStandardItem labStandardItem: labStandardItemList){
						labStandardItem.setIsDel(Constants_Source.Y);
						labStandardItemDAO.updateLabStandardItem(labStandardItem);
					}
				}
		    	 LabStandard labStandard = (LabStandard) labStandardDAO.findById(LabStandard.class, id);
		    	 labStandard.setIsDel(Constants_Source.Y);
		    	 labStandardDAO.updateLabStandard(labStandard);
	    	}
	    }
		}catch(Exception e){
			key=false;
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public boolean updateLabStandard(LabStandardVo LabStandardVo)
			throws GlobalException {
		boolean key=true;
		try{
			LabStandard labStandard=labStandardDAO.getLabStandard(LabStandardVo.getId());
			this.LabStandardVoTOLabStandard(LabStandardVo, labStandard);
			if(LabStandardVo.getIsUsed().equals(Constants_Source.TRUE)){
				if(!StrUtils.isBlankOrNull(LabStandardVo.getReplaceIds())){
					this.updateLabStandard4Status(LabStandardVo.getReplaceIds(),Constants_Source.TRUE);
				}else{
					this.updateLabStandard4Status(LabStandardVo.getReplaceIds(),Constants_Source.FALSE);
				}
			}else{
				this.updateLabStandard4Status(LabStandardVo.getReplaceIds(),Constants_Source.FALSE);
			}
			labStandardDAO.updateLabStandard(labStandard);
		}catch(Exception e){
			//e.printStackTrace();
			key=false;
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}
	
	
	private LabStandardVo LabStandardTOLabStandardVo(LabStandard po,LabStandardVo labStandardVo) throws GlobalException{
		BeanUtils.copyProperties(po, labStandardVo, new String[]{});
		if(null!=po.getStandType()){
			labStandardVo.setStandTypeId(po.getStandType().getId());
			labStandardVo.setStandTypeName(po.getStandType().getName());
		}
		String hql  = "FROM LabStandardItem WHERE standard.id = '"+po.getId()+"' AND isDel = '"+Constants_Source.N+"'";
		labStandardVo.setList(labStandardTypeDAO.find(hql));
		return labStandardVo;
	}
	private LabStandard LabStandardVoTOLabStandard(LabStandardVo labStandardVo,LabStandard po){
		BeanUtils.copyProperties(labStandardVo, po, new String[]{"isDel","standType","createTime","tenantId","createUserId"});
		if(!StrUtils.isBlankOrNull(labStandardVo.getStandTypeId())){
			LabStandardType kt=(LabStandardType)labStandardTypeDAO.findById(LabStandardType.class, labStandardVo.getStandTypeId());
			po.setStandType(kt);
		}
		if(!StrUtils.isBlankOrNull(labStandardVo.getCode())){
			String code=labStandardVo.getCode().trim();
			code=code.replace(" ", "");
			code=code.replace("　", "");
			po.setCode(code);
		}
		return po;
	}
	@Override
	public List<LabStandardVo> getLabStandardList(LabStandardVo labStandardVo) throws GlobalException {
		String hql="FROM LabStandard WHERE isDel='"+Constants_Source.N+"' ";
		if(null!=labStandardVo.getName()&&!"".equals(labStandardVo.getName())){
			hql +=" AND name = '"+labStandardVo.getName().trim()+"'";
		}
		if(null!=labStandardVo.getCode()&&!"".equals(labStandardVo.getCode())){
			hql +=" AND code = '"+labStandardVo.getCode().trim()+"'";
		}
		if(null!=labStandardVo.getCode()&&!"".equals(labStandardVo.getStandTypeId())){
			hql +=" AND standType.id = '"+labStandardVo.getStandTypeId().trim()+"'";
		}
		if(null!=labStandardVo.getSearchCode()&&!"".equals(labStandardVo.getSearchCode())){
			hql +=" AND ( code LIKE '%"+labStandardVo.getSearchCode()+"%'";
			hql +=" OR name LIKE '%"+labStandardVo.getSearchCode()+"%')";
		}
		if(!StrUtils.isBlankOrNull(labStandardVo.getType())){
			hql+="AND type IN (";
			for(String typeName:labStandardVo.getType().split(",")){
				hql+="'"+typeName+"',";
			}
			hql=hql.substring(0, hql.length()-1);
			
			hql+=")";
		}
		List<LabStandard> labStandardList=labStandardDAO.find(hql);
		List<LabStandardVo> standardVoList=new ArrayList<LabStandardVo>();
		if(labStandardList.size()>0){
		for(LabStandard labStandard:labStandardList){
				LabStandardVo vo=new LabStandardVo();
				this.LabStandardTOLabStandardVo(labStandard, vo);
				standardVoList.add(vo);
			}
		}
		return standardVoList;
	}

	@Override
	public List<LabStandardVo> getLabStandardVoList(LabStandardVo vo) throws GlobalException {
		String hql="FROM LabStandard WHERE ISDEL='N' ";
		if(!StrUtils.isBlankOrNull(vo.getType())){
			hql+=" AND type IN (";
			for(String type:vo.getType().split(",")){
				hql+="'"+type+"',";
			}
			hql=hql.substring(0, hql.length()-1);
			hql+=")";
		}
		List<LabStandard> listLabStandard=labStandardDAO.find(hql);
		List<LabStandardVo> listLabStandardVo=new ArrayList<LabStandardVo>();
		if(listLabStandard!=null&&listLabStandard.size()>0){
			for(LabStandard po:listLabStandard){
				LabStandardVo labStandardVo=new LabStandardVo();
				labStandardVo=this.LabStandardTOLabStandardVo(po, labStandardVo);
				listLabStandardVo.add(labStandardVo);
			}
		}
		return listLabStandardVo;
	}

	@Override
	public List<LabStandardVo> getLabStandardByType(String LabTypeid) throws GlobalException {
		List<LabStandardVo> listLabStandardVo=new ArrayList<LabStandardVo>();
		String hql="FROM LabStandard WHERE isDel='"+Constants_Source.N+"' AND standType.id='"+LabTypeid+"'";
		List<LabStandard> listLabStandard=labStandardDAO.find(hql);
		if(listLabStandard!=null&&listLabStandard.size()>0){
			for(LabStandard po:listLabStandard){
				LabStandardVo labStandardVo=new LabStandardVo();
				labStandardVo=this.LabStandardTOLabStandardVo(po, labStandardVo);
				listLabStandardVo.add(labStandardVo);
			}
		}
		return listLabStandardVo;
	}
	//导入excel
	public boolean importExcel() throws GlobalException {
		String value[][];
		try {
			value = OperationExcel.readExcel("F://link.xls",0);
			if (null != value && value.length > 0) {
				for(int j=1;j<value.length;j++){
					if(value[j].length<=2)continue;
					LabItem item=new LabItem();
					item.setName(value[j][1]);
					if(value[j].length>=6){
						item.setUnit(value[j][5]);
					}
					if(value[j].length>=10){
						double price=Double.valueOf(value[j][9]==null||value[j][9]==""?"0":value[j][9].trim());
						item.setPrice(price);
					}
					item.setIsDel(Constants_Source.N);
					item.setSort(j);
					
					//项目 存在就不在添加
					String subHql = "FROM LabItem WHERE name = '"+item.getName()+"' ";
					List<LabItem> list = labStandardDAO.find(subHql);
					if(null!=list&&list.size()>0){
						item = list.get(0);
					}else{
						labItemDAO.addLabItem(item);
					}
					
					LabMethod method=new LabMethod();
					if(value[j].length>=9){
						method.setName(value[j][8]);
					}
					if(value[j].length>=8){
						method.setCode(value[j][7]);
					}
					if(value[j].length>=7){
						method.setDemo1(value[j][6]);
					}
					method.setIsDel(Constants_Source.N);
					method.setSort(j);
					//方法 存在就不在添加
					subHql = "FROM LabMethod WHERE code = '"+method.getCode()+"' ";
					List<LabMethod> methodList = labStandardDAO.find(subHql);
					if(null!=methodList&&methodList.size()>0){
						method = methodList.get(0);
					}else{
						labMethodDAO.addLabMethod(method);
					}
					
					String hql="FROM LabStandard WHERE demo1='"+value[j][0].trim()+"'";
					List<LabStandard> ls=labStandardDAO.find(hql);
					if(null!=ls&&ls.size()>0){
						LabStandard stand=ls.get(0);
						LabStandardItem si=new LabStandardItem();
						si.setStandard(stand);
						si.setItem(item);
						if(value[j].length>5){
							si.setMaxValue(value[j][2]);
							si.setMaxKey("<=");
							si.setMinKey(">=");
							si.setMinValue(value[j][3]);
							si.setDesc(value[j][4]);
						}
						labStandardItemDAO.addLabStandardItem(si);
						LabStandardItemMethod sim=new LabStandardItemMethod();
						sim.setStandard(stand);
						sim.setStandardName(stand.getName());
						sim.setItem(item);
						sim.setMethod(method);
						labStandardItemMethodDAO.addLabStandardItemMethod(sim);
					}else{
						
						
					}
					
				}
			}	
		} catch (Exception e) {
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

	@Override
	public List<LabStandardVo> getLabStandardByStatusPR(LabStandardVo labStandardVo) throws GlobalException {
		// TODO Auto-generated method stub
		List<LabStandardVo> resultList=new ArrayList<LabStandardVo>();
		String hql="FROM LabStandard WHERE isDel='"+Constants_Source.N+"' AND isUsed='"+Constants_Source.Y+"'";
		if(!StrUtils.isBlankOrNull(labStandardVo.getSearchName())){
	 		hql+=" AND name LIKE '%"+labStandardVo.getSearchName()+"%'";
	 	}
	 	if(!StrUtils.isBlankOrNull(labStandardVo.getSearchCode())){
	 		hql+=" AND code LIKE '%"+labStandardVo.getSearchCode()+"%'";
	 	}
		List<LabStandard> listLabStandard = labStandardDAO.find(hql);
		if(listLabStandard!=null&&listLabStandard.size()>0){
			for(LabStandard po:listLabStandard){
				labStandardVo=new LabStandardVo();
				labStandardVo=this.LabStandardTOLabStandardVo(po, labStandardVo);
				resultList.add(labStandardVo);
			}
		}
		return resultList;
	}
	@Override
	public List<LabStandardVo> getLabStandardByName(String name) throws GlobalException {
		// TODO Auto-generated method stub
				List<LabStandardVo> labStandardVoList=new ArrayList<LabStandardVo>();
				String hql="FROM LabStandard WHERE isDel='"+Constants_Source.N+"'";
				if(!StrUtils.isBlankOrNull(name)){
					hql+=" AND name IN ('"+name.replace(",", "','")+"')";
				}
				List<LabStandard> listLabStandard = labStandardDAO.find(hql);
				if(listLabStandard!=null&&listLabStandard.size()>0){
					for(LabStandard po:listLabStandard){
						LabStandardVo labStandardVo=new LabStandardVo();
						labStandardVo=this.LabStandardTOLabStandardVo(po, labStandardVo);
						labStandardVoList.add(labStandardVo);
					}
				}
		return labStandardVoList;
	}

	@Override
	public boolean updateLabStandard4Status(String ids,String type)
			throws GlobalException {
		// TODO Auto-generated method stub
		boolean key=true;
		try{
			if(!StrUtils.isBlankOrNull(ids)){
				for(String id:ids.split(",")){
					LabStandard labStandard=labStandardDAO.getLabStandard(id);
					labStandard.setStandStatus(type);
					labStandardDAO.updateLabStandard(labStandard);
				}
			}
		}catch(Exception e){
			key=false;
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}
	@Override
	public LabStandardVo getLabStandardVoByCode(String code) throws GlobalException {
		String hql ="FROM LabStandard WHERE isDel='"+Constants_Source.N+"'";
		if(!StrUtils.isBlankOrNull(code)){
			hql +=" AND code = '"+code+"'";
		}
		List<LabStandard> poList = labStandardDAO.find(hql);
		if(poList.size()>0){
			LabStandardVo vo = new LabStandardVo();
			vo.toVo(poList.get(0));
			return vo;
		}
		return null;
	}
}