package cn.labsoft.labos.base.user.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.base.user.dao.ILabUserCredDAO;
import cn.labsoft.labos.base.user.entity.LabUserCred;
import cn.labsoft.labos.base.user.service.ILabUserCredService;
import cn.labsoft.labos.base.user.vo.LabUserCredVo;

@Service("labUserCredService")
public class LabUserCredServiceImpl implements ILabUserCredService {
	private ILabUserCredDAO labUserCredDAO;
	
	@Resource
	public void setLabUserCredDAO(ILabUserCredDAO labUserCredDAO) {
		this.labUserCredDAO = labUserCredDAO;
	}

	@Override
	public LabUserCredVo addLabUserCred(LabUserCredVo labUserCredVo) throws GlobalException {
		
		LabUserCred labUserCred=new LabUserCred();
		try{
			labUserCred=this.vo2Po(labUserCredVo, labUserCred);
			labUserCredDAO.addLabUserCred(labUserCred);
			labUserCredVo.setId(labUserCred.getId());
		}catch(Exception e){
			Log4J.error("LabUserCredServiceImpl addLabUserCred  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return labUserCredVo;
	}

	@Override
	public boolean deleteLabUserCred(String[] ids) throws GlobalException {
		boolean key=false;
		try{
			if(ids!=null&&ids.length>0){
				key=labUserCredDAO.deleteLabUserCred(ids);
			}
		}catch(Exception e){
			Log4J.error("LabUserCredServiceImpl deleteLabUserCred  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public LabUserCredVo getLabUserCred(String id) throws GlobalException {
		LabUserCredVo labUserCredVo=new LabUserCredVo();
		if(!StrUtils.isBlankOrNull(id)){
			try{
				LabUserCred labUserCred=labUserCredDAO.getLabUserCred(id);
				labUserCredVo=this.po2Vo(labUserCred, labUserCredVo);
			}catch(Exception e){
				Log4J.error("LabUserCredServiceImpl getLabUserCred  error..."+e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return labUserCredVo;
	}

	@Override
	public List<LabUserCredVo> getLabUserCredList(LabUserCredVo labUserCredVo) throws GlobalException {
		String wereHql="";
		if(labUserCredVo.getUserId()!=null){
			wereHql+=" AND userId='"+labUserCredVo.getUserId()+"'";
		}
		if(labUserCredVo.getUserName()!=null){
			wereHql+=" AND userName LIKE '%"+labUserCredVo.getUserName()+"%'";
		}
		if(labUserCredVo.getName()!=null){
			wereHql+=" AND name LIKE '%"+labUserCredVo.getName()+"%'";
		}
		if(labUserCredVo.getCode()!=null){
			wereHql+=" AND code LIKE '%"+labUserCredVo.getCode()+"%'";
		}
		if(labUserCredVo.getUnit()!=null){
			wereHql+=" AND unit LIKE '%"+labUserCredVo.getUnit()+"%'";
		}
		return this.getLabUserCredVoListByWhere(wereHql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabUserCredPR(LabUserCredVo labUserCredVo, PageResult pageResult)
			throws GlobalException {
		String hql="FROM LabUserCred WHERE isDel='"+Constants_Common.N+"'";
		if(labUserCredVo.getUserId()!=null){
			hql+=" AND userId='"+labUserCredVo.getUserId()+"'";
		}
		if(labUserCredVo.getUserName()!=null){
			hql+=" AND userName LIKE '%"+labUserCredVo.getUserName()+"%'";
		}
		if(labUserCredVo.getName()!=null){
			hql+=" AND name LIKE '%"+labUserCredVo.getName()+"%'";
		}
		if(labUserCredVo.getCode()!=null){
			hql+=" AND code LIKE '%"+labUserCredVo.getCode()+"%'";
		}
		if(labUserCredVo.getUnit()!=null){
			hql+=" AND unit LIKE '%"+labUserCredVo.getUnit()+"%'";
		}
		
		pageResult=labUserCredDAO.getPageResult(hql, pageResult);
		if(pageResult.getResultList()!=null&&pageResult.getResultList().size()>0){
			List<LabUserCredVo> listLabUserCredVo=new ArrayList<LabUserCredVo>();
			List<LabUserCred> listLabUserCred=new ArrayList<LabUserCred>();
			listLabUserCred=pageResult.getResultList();
			for(LabUserCred labUserCred:listLabUserCred){
				LabUserCredVo vo=new LabUserCredVo();
				vo=this.po2Vo(labUserCred, vo);
				listLabUserCredVo.add(vo);
			}
			pageResult.setResultList(listLabUserCredVo);
		}
		return pageResult;
	}

	@Override
	public boolean updateLabUserCred(LabUserCredVo labUserCredVo) throws GlobalException {
		LabUserCred labUserCred=new LabUserCred();
		boolean key=false;
		try{
			labUserCred=this.vo2Po(labUserCredVo, labUserCred);
			key=labUserCredDAO.updateLabUserCred(labUserCred);
		}catch(Exception e){
			Log4J.error("LabUserCredServiceImpl updateLabUserCred  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public boolean updateLabUserCred4Del(String[] ids) throws GlobalException {
		boolean key=false;
		if(ids!=null&&ids.length>0){
			try{
				for(String id:ids){
					LabUserCred labUserCred=labUserCredDAO.getLabUserCred(id);
					labUserCred.setIsDel(Constants_Common.Y);
					labUserCredDAO.updateLabUserCred(labUserCred);
				}
				key=true;
			}catch(Exception e){
				Log4J.error("LabUserCredServiceImpl updateLabUserCred4Del  error..."+e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}
	@SuppressWarnings("unchecked")
	public List<LabUserCredVo> getLabUserCredVoListByWhere(String wereHql) throws GlobalException{
		List<LabUserCredVo> listLabUserCredVo=new ArrayList<LabUserCredVo>();
		String hql="FROM LabUserCred WHERE isDel='"+Constants_Common.N+"' ";
		if(!StrUtils.isBlankOrNull(wereHql))hql+=wereHql;
		List<LabUserCred> listLabUserCred=labUserCredDAO.find(hql);
		if(listLabUserCred!=null&&listLabUserCred.size()>0){
			for(LabUserCred labUserCred:listLabUserCred){
				LabUserCredVo labUserCredVo=new LabUserCredVo();
				labUserCredVo=this.po2Vo(labUserCred, labUserCredVo);
				listLabUserCredVo.add(labUserCredVo);
			}
		}
		return listLabUserCredVo;
	}
	public LabUserCred vo2Po(LabUserCredVo labUserCredVo,LabUserCred labUserCred){
		BeanUtils.copyProperties(labUserCredVo, labUserCred,new String[]{"isDel"});
		return labUserCred;
	}
	public LabUserCredVo po2Vo(LabUserCred labUserCred,LabUserCredVo labUserCredVo){
		BeanUtils.copyProperties(labUserCred, labUserCredVo);
		return labUserCredVo;
	}
}
