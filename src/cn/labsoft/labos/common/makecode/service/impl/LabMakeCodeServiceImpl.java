package cn.labsoft.labos.common.makecode.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.common.makecode.dao.ILabMakeCodeDAO;
import cn.labsoft.labos.common.makecode.entity.LabMakeCode;
import cn.labsoft.labos.common.makecode.service.ILabMakeCodeService;
import cn.labsoft.labos.common.makecode.vo.LabMakeCodeVo;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.StrUtils;

@Service("labMakeCodeService")
public class LabMakeCodeServiceImpl implements ILabMakeCodeService {
	private ILabMakeCodeDAO labMakeCodeDAO;
	
	@Override
	public LabMakeCodeVo addLabMakeCode(LabMakeCodeVo labMakeCodeVo) throws GlobalException {
		
		LabMakeCode labMakeCode=new LabMakeCode();
		try{
			labMakeCode=this.vo2Po(labMakeCodeVo, labMakeCode);
			labMakeCodeDAO.addLabMakeCode(labMakeCode);
			labMakeCodeVo.setId(labMakeCode.getId());
		}catch(Exception e){
			Log4J.error("LabMakeCodeServiceImpl addLabMakeCode  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return labMakeCodeVo;
	}

	@Override
	public boolean deleteLabMakeCode(String[] ids) throws GlobalException {
		boolean key=true;
		try{
			if(ids!=null&&ids.length>0){
				key=labMakeCodeDAO.deleteLabMakeCode(ids);
			}
		}catch(Exception e){
			key=false;
			Log4J.error("LabMakeCodeServiceImpl deleteLabMakeCode  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public LabMakeCodeVo getLabMakeCode(String id) throws GlobalException {
		LabMakeCodeVo labMakeCodeVo=new LabMakeCodeVo();
		if(!StrUtils.isBlankOrNull(id)){
			try{
				LabMakeCode labMakeCode=labMakeCodeDAO.getLabMakeCode(id);
				labMakeCodeVo=this.po2Vo(labMakeCode, labMakeCodeVo);
			}catch(Exception e){
				Log4J.error("LabMakeCodeServiceImpl getLabMakeCode  error..."+e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return labMakeCodeVo;
	}

	@Override
	public List<LabMakeCodeVo> getLabMakeCodeList(LabMakeCodeVo labMakeCodeVo) throws GlobalException {
		String wereHql="";
		
		return this.getLabMakeCodeVoListByWhere(wereHql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabMakeCodePR(LabMakeCodeVo labMakeCodeVo, PageResult pageResult)
			throws GlobalException {
		String hql="FROM LabMakeCode WHERE isDel='"+Constants_Base.N+"'";
		if(!StrUtils.isBlankOrNull(labMakeCodeVo.getNameChin()))
			hql+=" AND nameChin LIKE '%"+labMakeCodeVo.getNameChin()+"%'";
		pageResult=labMakeCodeDAO.getPageResult(hql, pageResult);
		if(pageResult.getResultList()!=null&&pageResult.getResultList().size()>0){
			List<LabMakeCodeVo> listLabMakeCodeVo=new ArrayList<LabMakeCodeVo>();
			List<LabMakeCode> listLabMakeCode=new ArrayList<LabMakeCode>();
			listLabMakeCode=pageResult.getResultList();
			for(LabMakeCode labMakeCode:listLabMakeCode){
				LabMakeCodeVo vo=new LabMakeCodeVo();
				vo=this.po2Vo(labMakeCode, vo);
				listLabMakeCodeVo.add(vo);
			}
			pageResult.setResultList(listLabMakeCodeVo);
		}
		return pageResult;
	}

	@Override
	public boolean updateLabMakeCode(LabMakeCodeVo labMakeCodeVo) throws GlobalException {
		LabMakeCode labMakeCode=new LabMakeCode();
		boolean key=true;
		try{
			labMakeCode=this.vo2Po(labMakeCodeVo, labMakeCode);
			labMakeCodeDAO.updateLabMakeCode(labMakeCode);
		}catch(Exception e){
			key=false;
			Log4J.error("LabMakeCodeServiceImpl updateLabMakeCode  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public boolean updateLabMakeCode4Del(String[] ids) throws GlobalException {
		boolean key=true;
		if(ids!=null&&ids.length>0){
			try{
				for(String id:ids){
					LabMakeCode labMakeCode=labMakeCodeDAO.getLabMakeCode(id);
					labMakeCode.setIsDel(Constants_Base.Y);
					labMakeCodeDAO.updateLabMakeCode(labMakeCode);
				}
			}catch(Exception e){
				key=false;
				Log4J.error("LabMakeCodeServiceImpl updateLabMakeCode4Del  error..."+e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}
	@SuppressWarnings("unchecked")
	public List<LabMakeCodeVo> getLabMakeCodeVoListByWhere(String wereHql) throws GlobalException{
		List<LabMakeCodeVo> listLabMakeCodeVo=new ArrayList<LabMakeCodeVo>();
		String hql="FROM LabMakeCode WHERE isDel='"+Constants_Base.N+"' ";
		if(!StrUtils.isBlankOrNull(wereHql))hql+=wereHql;
		List<LabMakeCode> listLabMakeCode=labMakeCodeDAO.find(hql);
		if(listLabMakeCode!=null&&listLabMakeCode.size()>0){
			for(LabMakeCode labMakeCode:listLabMakeCode){
				LabMakeCodeVo labMakeCodeVo=new LabMakeCodeVo();
				labMakeCodeVo=this.po2Vo(labMakeCode, labMakeCodeVo);
				listLabMakeCodeVo.add(labMakeCodeVo);
			}
		}
		return listLabMakeCodeVo;
	}
	public LabMakeCode vo2Po(LabMakeCodeVo labMakeCodeVo,LabMakeCode labMakeCode){
		BeanUtils.copyProperties(labMakeCodeVo, labMakeCode,new String[]{"isDel"});
		return labMakeCode;
	}
	public LabMakeCodeVo po2Vo(LabMakeCode labMakeCode,LabMakeCodeVo labMakeCodeVo){
		BeanUtils.copyProperties(labMakeCode, labMakeCodeVo);
		return labMakeCodeVo;
	}
	@Resource
	public void setLabMakeCodeDAO(ILabMakeCodeDAO labMakeCodeDAO) {
		this.labMakeCodeDAO = labMakeCodeDAO;
	}
}
