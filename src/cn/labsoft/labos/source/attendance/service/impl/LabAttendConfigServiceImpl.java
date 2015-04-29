package cn.labsoft.labos.source.attendance.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.DateUtils;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.source.attendance.dao.ILabAttendConfigDAO;
import cn.labsoft.labos.source.attendance.entity.LabAttendConfig;
import cn.labsoft.labos.source.attendance.service.ILabAttendConfigService;
import cn.labsoft.labos.source.attendance.vo.LabAttendConfigVo;

@Service("labAttendConfigService")
public class LabAttendConfigServiceImpl implements ILabAttendConfigService {
	private ILabAttendConfigDAO labAttendConfigDAO;
	
	@Resource
	public void setLabAttendConfigDAO(ILabAttendConfigDAO labAttendConfigDAO) {
		this.labAttendConfigDAO = labAttendConfigDAO;
	}

	@Override
	public LabAttendConfigVo addLabAttendConfig(LabAttendConfigVo labAttendConfigVo) throws GlobalException {
		
		LabAttendConfig labAttendConfig=new LabAttendConfig();
		try{
			labAttendConfig=this.vo2Po(labAttendConfigVo, labAttendConfig);
			labAttendConfigDAO.addLabAttendConfig(labAttendConfig);
			labAttendConfigVo.setId(labAttendConfig.getId());
		}catch(Exception e){
			Log4J.error("LabAttendConfigServiceImpl addLabAttendConfig  error.."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return labAttendConfigVo;
	}

	@Override
	public boolean deleteLabAttendConfig(String[] ids) throws GlobalException {
		boolean key=true;
		try{
			if(ids!=null&&ids.length>0){
				key=labAttendConfigDAO.deleteLabAttendConfig(ids);
			}
		}catch(Exception e){
			key=false;
			Log4J.error("LabAttendConfigServiceImpl deleteLabAttendConfig  error.."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public LabAttendConfigVo getLabAttendConfig(String id) throws GlobalException {
		LabAttendConfigVo labAttendConfigVo=new LabAttendConfigVo();
		if(!StrUtils.isBlankOrNull(id)){
			try{
				LabAttendConfig labAttendConfig=labAttendConfigDAO.getLabAttendConfig(id);
				labAttendConfigVo=this.po2Vo(labAttendConfig, labAttendConfigVo);
			}catch(Exception e){
				Log4J.error("LabAttendConfigServiceImpl getLabAttendConfig  error.."+e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return labAttendConfigVo;
	}

	@Override
	public List<LabAttendConfigVo> getLabAttendConfigList(LabAttendConfigVo labAttendConfigVo) throws GlobalException {
		String wereHql="";
		
		return this.getLabAttendConfigVoListByWhere(wereHql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabAttendConfigPR(LabAttendConfigVo labAttendConfigVo, PageResult pageResult)
			throws GlobalException {
		String hql="FROM LabAttendConfig WHERE isDel='"+Constants_Source.N+"'";
		pageResult=labAttendConfigDAO.getPageResult(hql, pageResult);
		if(pageResult.getResultList()!=null&&pageResult.getResultList().size()>0){
			List<LabAttendConfigVo> labAttendConfigVoList=new ArrayList<LabAttendConfigVo>();
			List<LabAttendConfig> listLabAttendConfig=new ArrayList<LabAttendConfig>();
			listLabAttendConfig=pageResult.getResultList();
			for(LabAttendConfig cofg:listLabAttendConfig){
				LabAttendConfigVo vo=new LabAttendConfigVo();
				vo=this.po2Vo(cofg, vo);
				String workDay="";
				if(cofg.getWorkDay().contains("1")){
					workDay+="周一，";
				}
				if(cofg.getWorkDay().contains("2")){
					workDay+="周二，";
				}
				if(cofg.getWorkDay().contains("3")){
					workDay+="周三，";
				}
				if(cofg.getWorkDay().contains("4")){
					workDay+="周四，";
				}
				if(cofg.getWorkDay().contains("5")){
					workDay+="周五，";
				}
				if(cofg.getWorkDay().contains("6")){
					workDay+="周六，";
				}
				if(cofg.getWorkDay().contains("7")){
					workDay+="周日，";
				}
				if(workDay.endsWith("，")){
					workDay=workDay.substring(0, workDay.length()-1);
				}
				vo.setWorkDay(workDay);
				labAttendConfigVoList.add(vo);
			}
			pageResult.setResultList(labAttendConfigVoList);
		}
		return pageResult;
	}

	@Override
	public boolean updateLabAttendConfig(LabAttendConfigVo labAttendConfigVo) throws GlobalException {
		boolean key=true;
		try{
			if(!StrUtils.isBlankOrNull(labAttendConfigVo.getId())){
				LabAttendConfig labAttendConfig=labAttendConfigDAO.getLabAttendConfig(labAttendConfigVo.getId());
				if(labAttendConfig!=null){
					labAttendConfig=this.vo2Po(labAttendConfigVo, labAttendConfig);
					labAttendConfigDAO.updateLabAttendConfig(labAttendConfig);
				}
			}
		}catch(Exception e){
			key=false;
			Log4J.error("LabAttendConfigServiceImpl updateLabAttendConfig  error.."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public boolean updateLabAttendConfig4Del(String[] ids) throws GlobalException {
		boolean key=true;
		if(ids!=null&&ids.length>0){
			try{
				for(String id:ids){
					LabAttendConfig labAttendConfig=labAttendConfigDAO.getLabAttendConfig(id);
					labAttendConfig.setIsDel(Constants_Source.Y);
					labAttendConfigDAO.updateLabAttendConfig(labAttendConfig);
				}
			}catch(Exception e){
				key=false;
				Log4J.error("LabAttendConfigServiceImpl updateLabAttendConfig4Del  error.."+e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}
	@SuppressWarnings("unchecked")
	public List<LabAttendConfigVo> getLabAttendConfigVoListByWhere(String wereHql) throws GlobalException{
		List<LabAttendConfigVo> labAttendConfigVoList=new ArrayList<LabAttendConfigVo>();
		String hql="FROM LabAttendConfig WHERE isDel='"+Constants_Source.N+"' ";
		if(!StrUtils.isBlankOrNull(wereHql))hql+=wereHql;
		List<LabAttendConfig> labAttendConfigList=labAttendConfigDAO.find(hql);
		if(labAttendConfigList!=null&&labAttendConfigList.size()>0){
			for(LabAttendConfig labAttendConfig:labAttendConfigList){
				LabAttendConfigVo labAttendConfigVo=new LabAttendConfigVo();
				labAttendConfigVo=this.po2Vo(labAttendConfig, labAttendConfigVo);
				labAttendConfigVoList.add(labAttendConfigVo);
			}
		}
		return labAttendConfigVoList;
	}
	public LabAttendConfig vo2Po(LabAttendConfigVo labAttendConfigVo,LabAttendConfig labAttendConfig){
		BeanUtils.copyProperties(labAttendConfigVo, labAttendConfig,new String[]{"isDel"});
		return labAttendConfig;
	}
	public LabAttendConfigVo po2Vo(LabAttendConfig labAttendConfig,LabAttendConfigVo labAttendConfigVo){
		BeanUtils.copyProperties(labAttendConfig, labAttendConfigVo);
		return labAttendConfigVo;
	}

	@Override
	public LabAttendConfigVo getNowLabAttendConfigVo(String datetime) throws GlobalException {
		 
		return null;
	}
}
