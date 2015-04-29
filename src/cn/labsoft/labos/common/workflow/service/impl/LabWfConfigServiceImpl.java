package cn.labsoft.labos.common.workflow.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.function.dao.ILabFunctionDAO;
import cn.labsoft.labos.base.function.entity.LabFunction;
import cn.labsoft.labos.base.function.vo.LabFunctionVo;
import cn.labsoft.labos.constants.Constants_Base;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.common.workflow.dao.ILabWfConfigDAO;
import cn.labsoft.labos.common.workflow.dao.ILabWfProcessDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfConfig;
import cn.labsoft.labos.common.workflow.entity.LabWfProcess;
import cn.labsoft.labos.common.workflow.service.ILabWfConfigService;
import cn.labsoft.labos.common.workflow.vo.LabWfConfigVo;

@Service("labWfConfigService")
public class LabWfConfigServiceImpl implements ILabWfConfigService {
	private ILabWfConfigDAO labWfConfigDAO;
	private ILabWfProcessDAO labWfProcessDAO;
	private ILabFunctionDAO labFunctionDAO;
	
	@Resource
	public void setLabWfProcessDAO(ILabWfProcessDAO labWfProcessDAO) {
		this.labWfProcessDAO = labWfProcessDAO;
	}
	@Resource
	public void setLabFunctionDAO(ILabFunctionDAO labFunctionDAO) {
		this.labFunctionDAO = labFunctionDAO;
	}
	@Resource
	public void setLabWfConfigDAO(ILabWfConfigDAO labWfConfigDAO) {
		this.labWfConfigDAO = labWfConfigDAO;
	}

	@Override
	public LabWfConfigVo addLabWfConfig(LabWfConfigVo labWfConfigVo) throws GlobalException {
		
		LabWfConfig labWfConfig=new LabWfConfig();
		try{
			labWfConfig=this.vo2Po(labWfConfigVo, labWfConfig);
			LabFunction fun=labFunctionDAO.getLabFunction(labWfConfigVo.getFunId());
			labWfConfig.setFunId(fun.getId());
			labWfConfig.setFunName(fun.getName());
			LabWfProcess pro=labWfProcessDAO.getWfProcess(labWfConfigVo.getProcessId());
			labWfConfig.setWfProcess(pro);
			labWfConfig.setProcessName(pro.getName());
			labWfConfigDAO.addLabWfConfig(labWfConfig);
			labWfConfigVo.setId(labWfConfig.getId());
		}catch(Exception e){
			Log4J.error("LabWfConfigServiceImpl addLabWfConfig  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return labWfConfigVo;
	}

	@Override
	public boolean deleteLabWfConfig(String[] ids) throws GlobalException {
		boolean key=true;
		try{
			if(ids!=null&&ids.length>0){
				key=labWfConfigDAO.deleteLabWfConfig(ids);
			}
		}catch(Exception e){
			key=false;
			Log4J.error("LabWfConfigServiceImpl deleteLabWfConfig  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public LabWfConfigVo getLabWfConfig(String id) throws GlobalException {
		LabWfConfigVo labWfConfigVo=new LabWfConfigVo();
		if(!StrUtils.isBlankOrNull(id)){
			try{
				LabWfConfig labWfConfig=labWfConfigDAO.getLabWfConfig(id);
				labWfConfigVo=this.po2Vo(labWfConfig, labWfConfigVo);
				if(labWfConfig.getWfProcess()!=null){
					labWfConfigVo.setProcessId(labWfConfig.getWfProcess().getId());
				}
			}catch(Exception e){
				Log4J.error("LabWfConfigServiceImpl getLabWfConfig  error..."+e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return labWfConfigVo;
	}

	@Override
	public List<LabWfConfigVo> getLabWfConfigList(LabWfConfigVo labWfConfigVo) throws GlobalException {
		String wereHql="";
		
		return this.getLabWfConfigVoListByWhere(wereHql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabWfConfigPR(LabWfConfigVo labWfConfigVo, PageResult pageResult)
			throws GlobalException {
		StringBuffer hql = new StringBuffer("FROM LabFunction WHERE 1=1 AND isDel = '" + Constants_Base.N + "'");
		hql.append(" AND id<>'0'");
		hql.append(" AND type='0'");
		hql.append(" AND isProcess='Y'");
		hql.append(" ORDER BY code ASC");
		pageResult = labFunctionDAO.getLabFunctionPR(hql.toString(), pageResult);
		List<LabFunction> list=pageResult.getResultList();
		if(list!=null){
			List<LabWfConfigVo> labWfConfigVoList=new ArrayList<LabWfConfigVo>();
			for(LabFunction fun:list){
				String subhql="FROM LabWfConfig WHERE isDel='"+Constants_Base.N+"' AND funId='"+fun.getId()+"'";
				LabWfConfig labWfConfig=(LabWfConfig)labWfConfigDAO.find(subhql, 0);
				LabWfConfigVo vo=new LabWfConfigVo();
				if(labWfConfig!=null){
					vo=this.po2Vo(labWfConfig, vo);
				}else{
					vo.setFunId(fun.getId());
					vo.setFunName(fun.getName());
					vo.setCode(fun.getWfcode());
					vo.setProcessName("<font color=\"red\">未配置</font>");
				}
				labWfConfigVoList.add(vo);
			}
			pageResult.setResultList(labWfConfigVoList);
		}
		
//		String hql="FROM LabWfConfig WHERE isDel='"+Constants.N+"'";
//		pageResult=labWfConfigDAO.getPageResult(hql, pageResult);
//		if(pageResult.getResultList()!=null&&pageResult.getResultList().size()>0){
//			List<LabWfConfigVo> labWfConfigVoList=new ArrayList<LabWfConfigVo>();
//			List<LabWfConfig> listLabWfConfig=new ArrayList<LabWfConfig>();
//			listLabWfConfig=pageResult.getResultList();
//			for(LabWfConfig labWfConfig:listLabWfConfig){
//				LabWfConfigVo vo=new LabWfConfigVo();
//				vo=this.po2Vo(labWfConfig, vo);
//				labWfConfigVoList.add(vo);
//			}
//			pageResult.setResultList(labWfConfigVoList);
//		}
		return pageResult;
	}

	@Override
	public boolean updateLabWfConfig(LabWfConfigVo labWfConfigVo) throws GlobalException {
		LabWfConfig labWfConfig=labWfConfigDAO.getLabWfConfig(labWfConfigVo.getId());
		boolean key=true;
		try{
			labWfConfig=this.vo2Po(labWfConfigVo, labWfConfig);
			LabFunction fun=labFunctionDAO.getLabFunction(labWfConfigVo.getFunId());
			labWfConfig.setFunId(fun.getId());
			labWfConfig.setFunName(fun.getName());
			LabWfProcess pro=labWfProcessDAO.getWfProcess(labWfConfigVo.getProcessId());
			labWfConfig.setWfProcess(pro);
			labWfConfig.setProcessName(pro.getName());
			labWfConfigDAO.updateLabWfConfig(labWfConfig);
		}catch(Exception e){
			key=false;
			Log4J.error("LabWfConfigServiceImpl updateLabWfConfig  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public boolean updateLabWfConfig4Del(String[] ids) throws GlobalException {
		boolean key=true;
		if(ids!=null&&ids.length>0){
			try{
				for(String id:ids){
					LabWfConfig labWfConfig=labWfConfigDAO.getLabWfConfig(id);
					labWfConfig.setIsDel(Constants_Base.Y);
					labWfConfigDAO.updateLabWfConfig(labWfConfig);
				}
			}catch(Exception e){
				key=false;
				Log4J.error("LabWfConfigServiceImpl updateLabWfConfig4Del  error..."+e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}
	@SuppressWarnings("unchecked")
	public List<LabWfConfigVo> getLabWfConfigVoListByWhere(String wereHql) throws GlobalException{
		List<LabWfConfigVo> labWfConfigVoList=new ArrayList<LabWfConfigVo>();
		String hql="FROM LabWfConfig WHERE isDel='"+Constants_Base.N+"' ";
		if(!StrUtils.isBlankOrNull(wereHql))hql+=wereHql;
		List<LabWfConfig> labWfConfigList=labWfConfigDAO.find(hql);
		if(labWfConfigList!=null&&labWfConfigList.size()>0){
			for(LabWfConfig labWfConfig:labWfConfigList){
				LabWfConfigVo labWfConfigVo=new LabWfConfigVo();
				labWfConfigVo=this.po2Vo(labWfConfig, labWfConfigVo);
				labWfConfigVoList.add(labWfConfigVo);
			}
		}
		return labWfConfigVoList;
	}
	public LabWfConfig vo2Po(LabWfConfigVo labWfConfigVo,LabWfConfig labWfConfig){
		BeanUtils.copyProperties(labWfConfigVo, labWfConfig,new String[]{"isDel"});
		return labWfConfig;
	}
	public LabWfConfigVo po2Vo(LabWfConfig labWfConfig,LabWfConfigVo labWfConfigVo){
		BeanUtils.copyProperties(labWfConfig, labWfConfigVo);
		return labWfConfigVo;
	}

//	@SuppressWarnings("unchecked")
//	@Override
//	public boolean isExist4Code(String id, String code) throws GlobalException {
//		String hql="FROM LabWfConfig WHERE isDel='"+Constants.N+"'";
//		hql+=" AND code like '"+code+"'";
//		if(!StrUtils.isBlankOrNull(id)){
//			hql+=" AND id <>'"+id+"'";
//		}
//		List<LabWfConfig> ls=labWfConfigDAO.find(hql);
//		if(ls!=null&&ls.size()>0){
//			return true;
//		}else{
//			return false;
//		}
//	}
}
