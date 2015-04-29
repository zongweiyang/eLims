package cn.labsoft.labos.base.org.dao.impl;

import java.util.ArrayList;
import java.util.List;


import org.springframework.stereotype.Repository;

import cn.labsoft.labos.base.org.dao.ILabOrgDAO;
import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.constants.Constants_Common;
import cn.labsoft.labos.framework.common.dao.BaseDAO;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
@Repository(value="labOrgDAO")
public class LabOrgDAOImpl extends BaseDAO implements ILabOrgDAO {

	@Override
	public LabOrg addLabOrg(LabOrg labOrg) throws GlobalException {
		try{
			super.save(labOrg);
		}catch(Exception e){
			Log4J.error("SysOrgAdd error..." + e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return labOrg;
	}

	@Override
	public boolean delLabOrg(String[] ids) throws GlobalException {
		try{
			List<LabOrg> listLabOrg=new ArrayList<LabOrg>();
			for(String id:ids){
				listLabOrg.add((LabOrg)super.findById(LabOrg.class, id));
			}
			super.deleteAll(listLabOrg);
		}catch(Exception e){
			Log4J.error("SysOrgDelete error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}

	@Override
	public LabOrg getLabOrg(String id) throws GlobalException {
		LabOrg sysOrg=new LabOrg();
		try{
			sysOrg=(LabOrg) super.findById(LabOrg.class, id);
		}catch(Exception e){
			Log4J.error("SysOrgFindById error..."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
		return sysOrg;
	}

	@SuppressWarnings("unchecked")
	public List<LabOrg> getLabOrgList(String hql) throws GlobalException {
		List<LabOrg>  listSysOrg=new ArrayList<LabOrg>();
		try{
			listSysOrg=super.find(hql);
		}catch(Exception e){
			Log4J.error("SysOrgList error..."+e.getMessage(),e);
			throw new GlobalException("" + e.getMessage());
		}
		return listSysOrg;
	}

	@Override
	public boolean updateLabOrg(LabOrg labOrg) throws GlobalException {
		try{
			super.update(labOrg);
		}catch(Exception e){
			Log4J.error("SysOrgUpdate error...."+e.getMessage(),e);
			
			throw new GlobalException("" + e.getMessage());
		}
		return true;
	}
	@Override
	public PageResult getLabOrgPR(String hql, PageResult pageReuslt) throws GlobalException {
		pageReuslt=super.getPageResult(hql, pageReuslt.getCurrentPage(), pageReuslt.getPagerMethod(), pageReuslt.getPageSize());
		return pageReuslt;
	}

	@Override
	public String[] getLabOrgList4Sub(String pid) throws GlobalException {
		List<String> ids=new ArrayList<String>();
		ids.add(pid);
		List<LabOrg> orgList=getsubList(pid);
		if(orgList!=null&&orgList.size()>0){
			for (LabOrg labOrg : orgList) {
				ids.add(labOrg.getId());
			}
		}
		String[] idx=new String[ids.size()];
		int i=0;
		for (String id : ids) {
			idx[i]=id;
			i++;
		}
		return idx;
	}
	@SuppressWarnings("unchecked")
	public List<LabOrg> getsubList(String pid) throws GlobalException{
		List<LabOrg> orgList=new ArrayList<LabOrg>();
		String hql="FROM LabOrg WHERE isDel='"+Constants_Common.N+"' AND labOrg.id='"+pid+"'";
		List<LabOrg> suborgList=super.find(hql);
		if(suborgList!=null&&suborgList.size()>0){
			for (LabOrg labOrg : suborgList) {
				orgList.add(labOrg);
				orgList.addAll(getsubList(labOrg.getId()));
			}
		}
		return orgList;
	}
}
