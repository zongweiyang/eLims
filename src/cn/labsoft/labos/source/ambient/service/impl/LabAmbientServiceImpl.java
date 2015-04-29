package cn.labsoft.labos.source.ambient.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.source.ambient.dao.ILabAmbientDAO;
import cn.labsoft.labos.source.ambient.entity.LabAmbient;
import cn.labsoft.labos.source.ambient.entity.LabAmbientInfo;
import cn.labsoft.labos.source.ambient.service.ILabAmbientService;
import cn.labsoft.labos.source.ambient.vo.LabAmbientVo;
import cn.labsoft.labos.utils.StrUtils;

@Service("labAmbientService")
public class LabAmbientServiceImpl implements ILabAmbientService {
	private ILabAmbientDAO labAmbientDAO;
	@Override
	public LabAmbientVo addLabAmbient(LabAmbientVo labAmbientVo) throws GlobalException {
		// TODO Auto-generated method stub
		
		LabAmbient labAmbient=new LabAmbient();
		try{
			labAmbient=this.vo2Po(labAmbientVo, labAmbient);
			labAmbientDAO.addLabAmbient(labAmbient);
			labAmbientVo.setId(labAmbient.getId());
		}catch(Exception e){
			Log4J.error("LabAmbientServiceImpl addLabAmbient  error..."+e.getMessage(), e);
		}
		return labAmbientVo;
	}

	@Override
	public boolean deleteLabAmbient(String[] ids) {
		// TODO Auto-generated method stub
		boolean key=true;
		try{
			if(ids!=null&&ids.length>0){
				key=labAmbientDAO.deleteLabAmbient(ids);
			}
		}catch(Exception e){
			key=false;
			Log4J.error("LabAmbientServiceImpl deleteLabAmbient  error..."+e.getMessage(), e);
		}
		return key;
	}

	@Override
	public LabAmbientVo getLabAmbient(String id) throws GlobalException {
		// TODO Auto-generated method stub
		LabAmbientVo labAmbientVo=new LabAmbientVo();
		if(!StrUtils.isBlankOrNull(id)){
			try{
				LabAmbient labAmbient=labAmbientDAO.getLabAmbient(id);
				labAmbientVo=this.po2Vo(labAmbient, labAmbientVo);
			}catch(Exception e){
				Log4J.error("LabAmbientServiceImpl getLabAmbient  error..."+e.getMessage(), e);
			}
		}
		return labAmbientVo;
	}

	@Override
	public List<LabAmbientVo> getLabAmbientList(LabAmbientVo labAmbientVo) throws GlobalException {
		// TODO Auto-generated method stub
		String wereHql="";
		
		return this.getLabAmbientVoListByWhere(wereHql);
	}

	@Override
	public PageResult getLabAmbientPR(LabAmbientVo labAmbientVo, PageResult pageResult)
			throws GlobalException {
		// TODO Auto-generated method stub
		String hql="FROM LabAmbient WHERE isDel='"+Constants_Source.N+"'";
		if(!StrUtils.isBlankOrNull(labAmbientVo.getName()))
			hql+=" AND name LIKE '%"+labAmbientVo.getName()+"%'";
		pageResult=labAmbientDAO.getPageResult(hql, pageResult);
		if(pageResult.getResultList()!=null&&pageResult.getResultList().size()>0){
			List<LabAmbientVo> listLabAmbientVo=new ArrayList<LabAmbientVo>();
			List<LabAmbient> listLabAmbient=new ArrayList<LabAmbient>();
			listLabAmbient=pageResult.getResultList();
			for(LabAmbient labAmbient:listLabAmbient){
				LabAmbientVo vo=new LabAmbientVo();
				vo=this.po2Vo(labAmbient, vo);
				listLabAmbientVo.add(vo);
			}
			pageResult.setResultList(listLabAmbientVo);
		}
		return pageResult;
	}

	@Override
	public boolean updateLabAmbient(LabAmbientVo labAmbientVo) throws GlobalException {
		// TODO Auto-generated method stub
		LabAmbient labAmbient=new LabAmbient();
		boolean key=true;
		try{
			labAmbient=this.vo2Po(labAmbientVo, labAmbient);
			labAmbientDAO.updateLabAmbient(labAmbient);
		}catch(Exception e){
			key=false;
			Log4J.error("LabAmbientServiceImpl updateLabAmbient  error..."+e.getMessage(), e);
		}
		return key;
	}

	@Override
	public boolean updateLabAmbient4Del(String[] ids) throws GlobalException {
		// TODO Auto-generated method stub
		boolean key=true;
		if(ids!=null&&ids.length>0){
			try{
				for(String id:ids){
					LabAmbient labAmbient=labAmbientDAO.getLabAmbient(id);
					labAmbient.setIsDel(Constants_Source.Y);
					labAmbientDAO.updateLabAmbient(labAmbient);
				}
			}catch(Exception e){
				key=false;
				Log4J.error("LabAmbientServiceImpl updateLabAmbient4Del  error..."+e.getMessage(), e);
			}
		}
		return key;
	}
	public List<LabAmbientVo> getLabAmbientVoListByWhere(String wereHql) throws GlobalException{
		List<LabAmbientVo> listLabAmbientVo=new ArrayList<LabAmbientVo>();
		String hql="FROM LabAmbient WHERE isDel='"+Constants_Source.N+"' ";
		if(!StrUtils.isBlankOrNull(wereHql))hql+=wereHql;
		List<LabAmbient> listLabAmbient=labAmbientDAO.find(hql);
		if(listLabAmbient!=null&&listLabAmbient.size()>0){
			for(LabAmbient labAmbient:listLabAmbient){
				LabAmbientVo labAmbientVo=new LabAmbientVo();
				labAmbientVo=this.po2Vo(labAmbient, labAmbientVo);
				listLabAmbientVo.add(labAmbientVo);
			}
		}
		return listLabAmbientVo;
	}
	public LabAmbient vo2Po(LabAmbientVo labAmbientVo,LabAmbient labAmbient){
		BeanUtils.copyProperties(labAmbientVo, labAmbient,new String[]{"isDel"});
		return labAmbient;
	}
	public LabAmbientVo po2Vo(LabAmbient labAmbient,LabAmbientVo labAmbientVo){
		BeanUtils.copyProperties(labAmbient, labAmbientVo);
		return labAmbientVo;
	}

	@Override
	public String isDelete4LabAmbient(String[] ids) throws GlobalException {
		// TODO Auto-generated method stub
		String returnValue="0";
		if(ids!=null&&ids.length>0){
			for(String id:ids){
				List<LabAmbientInfo> listLabAmbientInfo=labAmbientDAO.find("FROM LabAmbientInfo WHERE isDel='"+Constants_Source.N+"' AND labAmbient.id='"+id+"'");
				try{
					if(listLabAmbientInfo.size()>0){
						returnValue="1";
						break;
					}
				}catch(Exception e){
				}
					
			}
		}
		return returnValue;
	}
	@Resource
	public void setLabAmbientDAO(ILabAmbientDAO labAmbientDAO) {
		this.labAmbientDAO = labAmbientDAO;
	}


}
