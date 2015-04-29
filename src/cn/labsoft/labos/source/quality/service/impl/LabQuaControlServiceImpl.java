package cn.labsoft.labos.source.quality.service.impl;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import javax.annotation.Resource;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.common.number.action.ThreadNumber;
import cn.labsoft.labos.common.number.dao.ILabNumberDAO;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.source.quality.dao.ILabQuaControlDAO;
import cn.labsoft.labos.source.quality.dao.ILabQuaControlDetailDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaControl;
import cn.labsoft.labos.source.quality.entity.LabQuaControlDetail;
import cn.labsoft.labos.source.quality.service.ILabQuaControlService;
import cn.labsoft.labos.source.quality.vo.LabQuaControlDetailVo;
import cn.labsoft.labos.source.quality.vo.LabQuaControlVo;
import cn.labsoft.labos.utils.StrUtils;


@Service("labQuaControlService")
public class LabQuaControlServiceImpl extends BaseService implements ILabQuaControlService{
	public   ExecutorService pool = Executors. newSingleThreadExecutor();
	private ILabQuaControlDAO labQuaControlDAO;
	private ILabQuaControlDetailDAO labQuaControlDetailDAO;
	private ILabNumberDAO labNumberDAO;
	
	@Resource
	public void setLabQuaControlDAO(ILabQuaControlDAO labQuaControlDAO) {
		this.labQuaControlDAO = labQuaControlDAO;
	}
	
	@Resource
	public void setLabQuaControlDetailDAO(
			ILabQuaControlDetailDAO labQuaControlDetailDAO) {
		this.labQuaControlDetailDAO = labQuaControlDetailDAO;
	}

	@Resource
	public void setLabNumberDAO(ILabNumberDAO labNumberDAO) {
		this.labNumberDAO = labNumberDAO;
	}

	@Override
	public boolean update2DelLabQuaControl(String[] ids) throws GlobalException {
		if( ids.length > 0 ){
			for(String id : ids ){
				LabQuaControl labQuaControl=labQuaControlDAO.getLabQuaControl(id);
				labQuaControl.setIsDel(Constants_Source.Y);
				labQuaControlDAO.updateLabQuaControl(labQuaControl);
			}
		}
		return true;
	}

	@Override
	public List<LabQuaControlVo> getLabQuaControlList(
			LabQuaControlVo labQuaControlVo) throws GlobalException {
		// TODO Auto-generated method stub
		String hql = "FROM LabQuaControl WHERE isDel= '"+Constants_Source.N+"'";
		if(!StrUtils.isBlankOrNull(labQuaControlVo.getAppCodeSearch())){
			hql += " AND appCode like '%"+labQuaControlVo.getAppCodeSearch()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labQuaControlVo.getAnaNoSearch())){
			hql += " AND anaNo like '%"+labQuaControlVo.getAnaNoSearch()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labQuaControlVo.getUnitOrgId())){
			hql += " AND unitOrg.id = '"+labQuaControlVo.getUnitOrgId()+"'";
		}
		if(!StrUtils.isBlankOrNull(labQuaControlVo.getLabOrgId())){
			hql += " AND labOrg.id = '"+labQuaControlVo.getLabOrgId()+"' ";
		}
		if(!StrUtils.isBlankOrNull(labQuaControlVo.getAccStatus())){
			hql += " AND accStatus = '"+labQuaControlVo.getAccStatus()+"' ";
		}
		List<LabQuaControl> labQuaControlList=labQuaControlDAO.getLabQuaControlByHQL(hql);
		List<LabQuaControlVo> labQuaControlVoList=new ArrayList<LabQuaControlVo>();
		if (null!=labQuaControlList && labQuaControlList.size()>0) {
			for (LabQuaControl labQuaControl : labQuaControlList) {
				LabQuaControlVo labQuaControlVoOne=new LabQuaControlVo();
				BeanUtils.copyProperties(labQuaControl, labQuaControlVo, new String[]{"isDel"});
				if(null != labQuaControl.getUnitOrg()){
					labQuaControlVoOne.setUnitOrgId(labQuaControl.getUnitOrg().getId());
					labQuaControlVoOne.setUnitOrgName(labQuaControl.getUnitOrg().getName());
				}
				if(null != labQuaControl.getLabOrg()){
					labQuaControlVoOne.setLabOrgId(labQuaControl.getLabOrg().getId());
					labQuaControlVoOne.setLabOrgName(labQuaControl.getLabOrg().getName());
				}
				labQuaControlVoList.add(labQuaControlVo);
			} 
		}
		return labQuaControlVoList;
	}

	@Override
	public PageResult getLabQuaControlPR(LabQuaControlVo labQuaControlVo, PageResult pageResult)
			throws GlobalException {
			String hql = "FROM LabQuaControl WHERE isDel= '"+Constants_Source.N+"'";
			if(!StrUtils.isBlankOrNull(labQuaControlVo.getAppCodeSearch())){
				hql += " AND appCode like '%"+labQuaControlVo.getAppCodeSearch()+"%'";
			}
			if(!StrUtils.isBlankOrNull(labQuaControlVo.getAnaNoSearch())){
				hql += " AND anaNo like '%"+labQuaControlVo.getAnaNoSearch()+"%'";
			}
			if(!StrUtils.isBlankOrNull(labQuaControlVo.getUnitOrgSearch())){
				hql += " AND unitOrg.id = '"+labQuaControlVo.getUnitOrgSearch()+"'";
			}
			if(!StrUtils.isBlankOrNull(labQuaControlVo.getLabOrgSearch())){
				hql += " AND labOrg.id = '"+labQuaControlVo.getLabOrgSearch()+"' ";
			}
			if(!StrUtils.isBlankOrNull(labQuaControlVo.getAccStatus())){
				hql += " AND accStatus = '"+labQuaControlVo.getAccStatus()+"' ";
			}
			pageResult=labQuaControlDAO.getLabQuaControlPR(hql, pageResult);
			List<LabQuaControl> labQuaControlList=pageResult.getResultList();
			List<LabQuaControlVo> labQuaControlVoList=new ArrayList<LabQuaControlVo>();
			if (null!=labQuaControlList && labQuaControlList.size()>0) {
				for (LabQuaControl labQuaControl : labQuaControlList) {
					LabQuaControlVo labQuaControlVoOne=new LabQuaControlVo();
					BeanUtils.copyProperties(labQuaControl, labQuaControlVoOne, new String[]{"isDel"});
					if(null != labQuaControl.getUnitOrg()){
						labQuaControlVoOne.setUnitOrgId(labQuaControl.getUnitOrg().getId());
						labQuaControlVoOne.setUnitOrgName(labQuaControl.getUnitOrg().getName());
					}
					if(null != labQuaControl.getLabOrg()){
						labQuaControlVoOne.setLabOrgId(labQuaControl.getLabOrg().getId());
						labQuaControlVoOne.setLabOrgName(labQuaControl.getLabOrg().getName());
					}
					labQuaControlVoList.add(labQuaControlVoOne);
				} 
				pageResult.setResultList(labQuaControlVoList);
			}
			return pageResult;
		
	}

	@Override
	public LabQuaControlVo getLabQuaControl(String id)
			throws GlobalException {
		LabQuaControl labQuaControl=labQuaControlDAO.getLabQuaControl(id);
		LabQuaControlVo labQuaControlVo=new LabQuaControlVo();
		BeanUtils.copyProperties(labQuaControl, labQuaControlVo, new String[]{"isDel"});
		if(null != labQuaControl.getUnitOrg()){
			labQuaControlVo.setUnitOrgId(labQuaControl.getUnitOrg().getId());
			labQuaControlVo.setUnitOrgName(labQuaControl.getUnitOrg().getName());
		}
		if(null != labQuaControl.getLabOrg()){
			labQuaControlVo.setLabOrgId(labQuaControl.getLabOrg().getId());
			labQuaControlVo.setLabOrgName(labQuaControl.getLabOrg().getName());
		}
		String hql = " FROM LabQuaControlDetail WHERE 1=1 AND labQuaControl.id = '"+labQuaControl.getId()+"'";
		List<LabQuaControlDetail> labQuaControlDetailList = labQuaControlDetailDAO.getLabQuaControlDetailByHQL(hql);
		List<LabQuaControlDetailVo> labQuaControlDetailListVo = new ArrayList<LabQuaControlDetailVo>();
		if(null != labQuaControlDetailList && labQuaControlDetailList.size() > 0 ){
			for(LabQuaControlDetail labQuaControlDetail : labQuaControlDetailList){
				LabQuaControlDetailVo labQuaControlDetailVo = new LabQuaControlDetailVo();
				BeanUtils.copyProperties(labQuaControlDetail, labQuaControlDetailVo, new String[]{""});
				labQuaControlDetailListVo.add(labQuaControlDetailVo);
			}
			labQuaControlVo.setLabQuaControlDetailVoList(labQuaControlDetailListVo);
		}
		return labQuaControlVo;
		
	}

	@Override
	public LabQuaControlVo addLabQuaControl(LabQuaControlVo labQuaControlVo)
			throws GlobalException {
		LabQuaControl labQuaControl=new LabQuaControl();
		BeanUtils.copyProperties(labQuaControlVo, labQuaControl, new String[] {"isDel"});
		if(!StrUtils.isBlankOrNull(labQuaControlVo.getUnitOrgId())){
			LabOrg unitOrg=(LabOrg)labQuaControlDAO.findById(LabOrg.class, labQuaControlVo.getUnitOrgId());
			labQuaControl.setUnitOrg(unitOrg);
		}
		if(!StrUtils.isBlankOrNull(labQuaControlVo.getLabOrgId())){
			LabOrg labOrg=(LabOrg)labQuaControlDAO.findById(LabOrg.class, labQuaControlVo.getLabOrgId());
			labQuaControl.setLabOrg(labOrg);
		}
		ThreadNumber threadNumber=new ThreadNumber(null,labNumberDAO,Constants_Source.CODE_CC,new String[]{},Constants_Source.CODE_USE_RUN);
		try {
			labQuaControl.setAnaNo(pool.submit(threadNumber).get().toString());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		labQuaControl.setAccStatus("0");
		labQuaControlDAO.addLabQuaControl(labQuaControl);
		List<LabQuaControlDetailVo> labQuaControlDetailVoList = labQuaControlVo.getLabQuaControlDetailVoList();
		if(null != labQuaControlDetailVoList && labQuaControlDetailVoList.size() > 0 ){
			for(LabQuaControlDetailVo labQuaControlDetailVo : labQuaControlDetailVoList ){
				LabQuaControlDetail labQuaControlDetail = new LabQuaControlDetail();
				BeanUtils.copyProperties(labQuaControlDetailVo,labQuaControlDetail,new String[]{""});
				labQuaControlDetail.setLabQuaControl(labQuaControl);
				labQuaControlDetailDAO.addLabQuaControlDetail(labQuaControlDetail);
			}
		}
		labQuaControlVo.setId(labQuaControl.getId());
		return labQuaControlVo;
	}

	@Override
	public boolean updateLabQuaControl(LabQuaControlVo labQuaControlVo)
			throws GlobalException {
		LabQuaControl labQuaControl=labQuaControlDAO.getLabQuaControl(labQuaControlVo.getId());
		BeanUtils.copyProperties(labQuaControlVo, labQuaControl, new String[] {"isDel","createTime","tenantId","createUserId","accStatus"});
		if(!StrUtils.isBlankOrNull(labQuaControlVo.getUnitOrgId())){
			LabOrg unitOrg=(LabOrg)labQuaControlDAO.findById(LabOrg.class, labQuaControlVo.getUnitOrgId());
			labQuaControl.setUnitOrg(unitOrg);
		}
		if(!StrUtils.isBlankOrNull(labQuaControlVo.getLabOrgId())){
			LabOrg labOrg=(LabOrg)labQuaControlDAO.findById(LabOrg.class, labQuaControlVo.getLabOrgId());
			labQuaControl.setLabOrg(labOrg);
		}
		labQuaControlDAO.updateLabQuaControl(labQuaControl);
		List<LabQuaControlDetailVo> labQuaControlDetailVoList = labQuaControlVo.getLabQuaControlDetailVoList();
		if(null != labQuaControlDetailVoList && labQuaControlDetailVoList.size() > 0 ){
			for(LabQuaControlDetailVo labQuaControlDetailVo : labQuaControlDetailVoList ){
				LabQuaControlDetail labQuaControlDetail = labQuaControlDetailDAO.getLabQuaControlDetail(labQuaControlDetailVo.getId());
				labQuaControlDetail.setLabQuaControl(labQuaControl);
				labQuaControlDetail.setProResult(labQuaControlDetailVo.getProResult());
				labQuaControlDetail.setStatusDesc(labQuaControlDetailVo.getStatusDesc());
				labQuaControlDetailDAO.updateLabQuaControlDetail(labQuaControlDetail);
			}
		}
		return true;
	}
	
}
