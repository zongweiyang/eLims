package cn.labsoft.labos.source.klg.service.impl;


import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.klg.dao.ILabStandardTypeDAO;
import cn.labsoft.labos.source.klg.entity.LabStandardType;
import cn.labsoft.labos.source.klg.service.ILabStandardTypeService;
import cn.labsoft.labos.source.klg.vo.LabStandardTypeVo;
import cn.labsoft.labos.utils.StrUtils;

@Service("labStandardTypeService")
public class LabStandardTypeServiceImpl implements ILabStandardTypeService {
	private ILabStandardTypeDAO labStandardTypeDAO;
	
	@Resource
	public void setLabStandardTypeDAO(ILabStandardTypeDAO labStandardTypeDAO) {
		this.labStandardTypeDAO = labStandardTypeDAO;
	}

	@Override
	public LabStandardTypeVo addLabStandardType(LabStandardTypeVo labStandardTypeVo) throws GlobalException {
		LabStandardType labStandardType = new LabStandardType();
		LabStandardType labStandardTypePid = new LabStandardType();
		BeanUtils.copyProperties(labStandardTypeVo, labStandardType, new String[] { "parentid" });
		
		if(!StrUtils.isBlankOrNull(labStandardTypeVo.getParentid())){
			labStandardTypePid=(LabStandardType) labStandardTypeDAO.findById(LabStandardType.class,labStandardTypeVo.getParentid());
		}else{
			labStandardTypePid=(LabStandardType) labStandardTypeDAO.findById(LabStandardType.class,"0");
		}
		labStandardType.setStandardType(labStandardTypePid);
		labStandardType.setIsDel(Constants_Source.N);
		labStandardTypeDAO.addLabStandardType(labStandardType);
		labStandardTypeVo.setId(labStandardType.getId());
		return labStandardTypeVo;
	}

	@Override
	public boolean deleteLabStandardType(String[] ids)
			throws GlobalException {
		if (null != ids && 0 < ids.length) {
			for (String id : ids) {
				LabStandardType labStandardType = (LabStandardType) labStandardTypeDAO.findById(LabStandardType.class,
						id);
				labStandardType.setIsDel(Constants_Source.Y);
				labStandardTypeDAO.updateLabStandardType(labStandardType);
			}
		}
		return true;
	}
	
	@Override
	public LabStandardTypeVo getLabStandardType(String id) throws GlobalException {
		LabStandardType labStandardType = (LabStandardType) labStandardTypeDAO.findById(LabStandardType.class, id);
		LabStandardTypeVo labStandardTypeVo = new LabStandardTypeVo();
		BeanUtils.copyProperties(labStandardType, labStandardTypeVo, new String[] { "" });
		if(null!=labStandardType.getStandardType()){
			labStandardTypeVo.setParentid(labStandardType.getStandardType().getId());
		}
		return labStandardTypeVo;
	}

	@Override
	public PageResult getLabStandardTypePR(LabStandardTypeVo labStandardTypeVo,
			PageResult pageResult)throws GlobalException {
		String hql = "FROM LabStandardType WHERE 1=1 AND isDel='"+Constants_Source.N+"'";
		if(!StrUtils.isBlankOrNull(labStandardTypeVo.getParentid())){
			hql+=" AND standardType.id='"+labStandardTypeVo.getParentid()+"'";
		}else{
			hql+=" AND standardType.id='0'";
		}
		 pageResult = labStandardTypeDAO.getPageResult(hql, pageResult);
		return pageResult;
	}

	@Override
	public LabStandardTypeVo updateLabStandardType(LabStandardTypeVo labStandardTypeVo) throws GlobalException {
		LabStandardType LabStandardType = (LabStandardType) labStandardTypeDAO.findById(LabStandardType.class,
				labStandardTypeVo.getId());
		BeanUtils.copyProperties(labStandardTypeVo, LabStandardType, new String[] { "isDel","createTime","tenantId","createUserId" });
		labStandardTypeDAO.updateLabStandardType(LabStandardType);
		return labStandardTypeVo;
	}

	@Override
	public List<LabStandardTypeVo> getLabStandardTypeList(
			LabStandardTypeVo labStandardTypeVo) throws GlobalException {
		String hql = "FROM LabStandardType WHERE 1=1 AND isDel='"+Constants_Source.N+"'";
		if(!StrUtils.isBlankOrNull(labStandardTypeVo.getParentid()) && !labStandardTypeVo.getParentid().equals("0")){
			hql+=" AND standardType.id='"+labStandardTypeVo.getParentid()+"'";
		}
		if(!StrUtils.isBlankOrNull(labStandardTypeVo.getName())){
			hql+=" AND name='"+labStandardTypeVo.getName()+"'";
		}
		List<LabStandardType> labStandardTypeList = labStandardTypeDAO.find(hql);
		List<LabStandardTypeVo> labStandardTypeVoList = new ArrayList<LabStandardTypeVo>();
		if( null != labStandardTypeList && labStandardTypeList.size() > 0 ){
			for(LabStandardType po :labStandardTypeList){
				LabStandardTypeVo vo = new LabStandardTypeVo();
				BeanUtils.copyProperties(po, vo, new String[] { "" });
				labStandardTypeVoList.add(vo);
			}
		}
		return labStandardTypeVoList;
	}
	
	
	@Override
	public List<LabStandardTypeVo> getLabStandardTypeByPid(String pid) throws GlobalException {
		List<LabStandardTypeVo> listLabStandardTypeVo=new ArrayList<LabStandardTypeVo>();
		List<LabStandardType> listLabStandardType=new ArrayList<LabStandardType>();
	    if(!StrUtils.isBlankOrNull(pid)){
	    	String hql="FROM LabStandardType WHERE isDel='"+Constants_Source.N+"'";
	    	hql+=" AND standardType.id = '" + pid + "'";
	    	listLabStandardType = labStandardTypeDAO.find(hql);
	    }
	    if(listLabStandardType.size()>0){
	    	for(LabStandardType po:listLabStandardType){
	    		LabStandardTypeVo vo=new LabStandardTypeVo();
	    		vo=this.po2vo(po, vo);
	    		listLabStandardTypeVo.add(vo);
	    	}
	    }
		return listLabStandardTypeVo;
	}
	private LabStandardTypeVo po2vo(LabStandardType po,LabStandardTypeVo vo){
		BeanUtils.copyProperties(po, vo,new String[]{"standardType"});
		vo.setParentid(po.getId());
		return vo;
	}

	@Override
	public List<LabStandardTypeVo> getLabStandardTypeList(String type)
			throws GlobalException {
		// TODO Auto-generated method stub
		List<LabStandardTypeVo> typelist = new ArrayList<LabStandardTypeVo>();
		if (StrUtils.isBlankOrNull(type) || type.equals("0")) {
			List<LabStandardTypeVo> typelistFirst = getLabStandardTypeByPid("0");
			if (null != typelistFirst && typelistFirst.size() > 0) {
				for (LabStandardTypeVo vo : typelistFirst) {
					LabStandardTypeVo labStandardTypeVo = getLabStandardType(vo.getId());
					typelist.add(labStandardTypeVo);
				}
			}
		} else {
			List<LabStandardTypeVo> typelistFirst = getLabStandardTypeByPid(type);
			if (null != typelistFirst && typelistFirst.size() > 0) {
				LabStandardTypeVo labStandardTypeVo = getLabStandardType(type);
				typelist.add(labStandardTypeVo);
				for (LabStandardTypeVo vo : typelistFirst) {
					LabStandardTypeVo labStandardTypeVoFirst = getLabStandardType(vo.getId());
					typelist.add(labStandardTypeVoFirst);
				}
			} else {
				LabStandardTypeVo labStandardTypeVo = getLabStandardType(type);
				typelist.add(labStandardTypeVo);
			}
		}
		return typelist;
	}
	
	
}
