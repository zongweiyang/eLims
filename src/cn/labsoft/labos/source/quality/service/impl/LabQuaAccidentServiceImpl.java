package cn.labsoft.labos.source.quality.service.impl;


import java.util.ArrayList;
import java.util.List;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.source.customer.entity.LabCustomer;
import cn.labsoft.labos.source.quality.dao.ILabQuaAccidentDAO;
import cn.labsoft.labos.source.quality.dao.ILabQuaAuditPlanEleDAO;
import cn.labsoft.labos.source.quality.dao.ILabQuaComplainDAO;
import cn.labsoft.labos.source.quality.dao.ILabQuaControlDAO;
import cn.labsoft.labos.source.quality.dao.ILabQuaCusVisitDAO;
import cn.labsoft.labos.source.quality.dao.ILabQuaManageCheckPlanDAO;
import cn.labsoft.labos.source.quality.dao.ILabQuaProficiencyPlanDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaAccident;
import cn.labsoft.labos.source.quality.entity.LabQuaAuditPlanEle;
import cn.labsoft.labos.source.quality.entity.LabQuaComplain;
import cn.labsoft.labos.source.quality.entity.LabQuaControl;
import cn.labsoft.labos.source.quality.entity.LabQuaCusVisit;
import cn.labsoft.labos.source.quality.entity.LabQuaManageCheckPlan;
import cn.labsoft.labos.source.quality.entity.LabQuaProficiencyPlan;
import cn.labsoft.labos.source.quality.service.ILabQuaAccidentService;
import cn.labsoft.labos.source.quality.vo.LabQuaAccidentVo;
import cn.labsoft.labos.utils.StrUtils;



@Service("labQuaAccidentService")
public class LabQuaAccidentServiceImpl extends BaseService implements ILabQuaAccidentService{
	
	private ILabQuaAccidentDAO labQuaAccidentDAO;
	private ILabQuaAuditPlanEleDAO labQuaAuditPlanEleDAO;
	private ILabQuaManageCheckPlanDAO labQuaManageCheckPlanDAO;
	private ILabQuaComplainDAO labQuaComplainDAO;
	private ILabQuaControlDAO labQuaControlDAO;
	private ILabQuaProficiencyPlanDAO labQuaProficiencyPlanDAO;
	private ILabQuaCusVisitDAO labQuaCusVisitDAO;
	
	@Resource
	public void setLabQuaAccidentDAO(ILabQuaAccidentDAO labQuaAccidentDAO) {
		this.labQuaAccidentDAO = labQuaAccidentDAO;
	}
	
	@Resource
	public void setLabQuaAuditPlanEleDAO(
			ILabQuaAuditPlanEleDAO labQuaAuditPlanEleDAO) {
		this.labQuaAuditPlanEleDAO = labQuaAuditPlanEleDAO;
	}

	@Resource
	public void setLabQuaManageCheckPlanDAO(
			ILabQuaManageCheckPlanDAO labQuaManageCheckPlanDAO) {
		this.labQuaManageCheckPlanDAO = labQuaManageCheckPlanDAO;
	}

	@Resource
	public void setLabQuaComplainDAO(ILabQuaComplainDAO labQuaComplainDAO) {
		this.labQuaComplainDAO = labQuaComplainDAO;
	}

	@Resource
	public void setLabQuaControlDAO(ILabQuaControlDAO labQuaControlDAO) {
		this.labQuaControlDAO = labQuaControlDAO;
	}

	@Resource
	public void setLabQuaProficiencyPlanDAO(
			ILabQuaProficiencyPlanDAO labQuaProficiencyPlanDAO) {
		this.labQuaProficiencyPlanDAO = labQuaProficiencyPlanDAO;
	}

	@Resource
	public void setLabQuaCusVisitDAO(ILabQuaCusVisitDAO labQuaCusVisitDAO) {
		this.labQuaCusVisitDAO = labQuaCusVisitDAO;
	}


	@Override
	public boolean update2DelLabQuaAccident(String[] ids) throws GlobalException {
		if( ids.length > 0 ){
			for(String id : ids ){
				LabQuaAccident labQuaAccident=labQuaAccidentDAO.getLabQuaAccident(id);
				labQuaAccident.setIsDel(Constants_Source.Y);
				labQuaAccidentDAO.updateLabQuaAccident(labQuaAccident);
			}
		}
		return true;
	}

	@Override
	public List<LabQuaAccidentVo> getLabQuaAccidentVoList(
			LabQuaAccidentVo labQuaAccidentVo) throws GlobalException {
		// TODO Auto-generated method stub
		String hql = "FROM LabQuaAccident WHERE isDel= '"+Constants_Source.N+"'";
		if(!StrUtils.isBlankOrNull(labQuaAccidentVo.getAccStatus())){
			hql += " AND accStatus = '"+labQuaAccidentVo.getAccStatus()+"' ";
		}
		if(!StrUtils.isBlankOrNull(labQuaAccidentVo.getAccType())){
			hql += " AND accType = '"+labQuaAccidentVo.getAccType()+"' ";
		}
		if(!StrUtils.isBlankOrNull(labQuaAccidentVo.getAccUnitSearch())){
			hql += " AND labCustomer.name like '%"+labQuaAccidentVo.getAccUnitSearch()+"%' ";
		}
		if(!StrUtils.isBlankOrNull(labQuaAccidentVo.getRepPeopleSearch())){
			hql += " AND repPeople like '%"+labQuaAccidentVo.getRepPeopleSearch()+"%'  ";
		}
		if(!StrUtils.isBlankOrNull(labQuaAccidentVo.getChePeople())){
			hql += " AND chePeople LIKE '%"+labQuaAccidentVo.getChePeople()+"%' ";
		}
		List<LabQuaAccident> labQuaAccidentList=labQuaAccidentDAO.getLabQuaAccidentByHQL(hql);
		List<LabQuaAccidentVo> labQuaAccidentVoList=new ArrayList<LabQuaAccidentVo>();
		if (null!=labQuaAccidentList && labQuaAccidentList.size()>0) {
			for (LabQuaAccident labQuaAccident : labQuaAccidentList) {
				LabQuaAccidentVo labQuaAccidentVoOne=new LabQuaAccidentVo();
				BeanUtils.copyProperties(labQuaAccident, labQuaAccidentVoOne, new String[]{"isDel"});
				if(null != labQuaAccident.getUnitOrg()){
					labQuaAccidentVoOne.setUnitOrgId(labQuaAccident.getUnitOrg().getId());
					labQuaAccidentVoOne.setUnitOrgName(labQuaAccident.getUnitOrg().getName());
				}
				if(null != labQuaAccident.getLabCustomer()){
					labQuaAccidentVoOne.setAccUnitId(labQuaAccident.getLabCustomer().getId());
					labQuaAccidentVoOne.setAccUnit(labQuaAccident.getLabCustomer().getName());
				}
				String other = checkName(labQuaAccident.getAccType());
				labQuaAccidentVoOne.setOther(other);
				labQuaAccidentVoList.add(labQuaAccidentVoOne);
			} 
		}
		return labQuaAccidentVoList;
	}

	@Override
	public PageResult getLabQuaAccidentVoPR(LabQuaAccidentVo labQuaAccidentVo, PageResult pageResult)
			throws GlobalException {
			String hql = "FROM LabQuaAccident WHERE isDel= '"+Constants_Source.N+"' AND accStatus IS NOT NULL";
			if(!StrUtils.isBlankOrNull(labQuaAccidentVo.getAccStatus())){
				String[] accstatusArray = labQuaAccidentVo.getAccStatus().split(",");
				String accStatus = "";
				if(accstatusArray.length > 0){
					for(String status : accstatusArray){
						accStatus += "'"+status+"',";
					}
					if(accStatus.split(",").length > 0){
						accStatus = accStatus.substring(0,accStatus.length()-1);
					}
				}
				hql += " AND accStatus IN ("+accStatus+")";
			}
			if(!StrUtils.isBlankOrNull(labQuaAccidentVo.getAccType())){
				hql += " AND accType = '"+labQuaAccidentVo.getAccType()+"' ";
			}
			if(!StrUtils.isBlankOrNull(labQuaAccidentVo.getAccUnitSearch())){
				hql += " AND labCustomer.name like '%"+labQuaAccidentVo.getAccUnitSearch()+"%' ";
			}
			if(!StrUtils.isBlankOrNull(labQuaAccidentVo.getRepPeopleSearch())){
				hql += " AND repPeople like '%"+labQuaAccidentVo.getRepPeopleSearch()+"%'  ";
			}
			if(!StrUtils.isBlankOrNull(labQuaAccidentVo.getChePeople())){
				hql += " AND chePeople LIKE '%"+labQuaAccidentVo.getChePeople()+"%' ";
			}
			pageResult=labQuaAccidentDAO.getLabQuaAccidentPR(hql, pageResult);
			List<LabQuaAccident> labQuaAccidentList=pageResult.getResultList();
			List<LabQuaAccidentVo> labQuaAccidentVoList=new ArrayList<LabQuaAccidentVo>();
			if (null!=labQuaAccidentList && labQuaAccidentList.size()>0) {
				for (LabQuaAccident labQuaAccident : labQuaAccidentList) {
					LabQuaAccidentVo labQuaAccidentVoOne=new LabQuaAccidentVo();
					BeanUtils.copyProperties(labQuaAccident, labQuaAccidentVoOne, new String[]{"isDel"});
					if(null != labQuaAccident.getUnitOrg()){
						labQuaAccidentVoOne.setUnitOrgId(labQuaAccident.getUnitOrg().getId());
						labQuaAccidentVoOne.setUnitOrgName(labQuaAccident.getUnitOrg().getName());
					}
					if(null != labQuaAccident.getLabCustomer()){
						labQuaAccidentVoOne.setAccUnitId(labQuaAccident.getLabCustomer().getId());
						labQuaAccidentVoOne.setAccUnit(labQuaAccident.getLabCustomer().getName());
					}
					String other = checkName(labQuaAccident.getAccType());
					labQuaAccidentVoOne.setOther(other);
					labQuaAccidentVoList.add(labQuaAccidentVoOne);
				} 
				pageResult.setResultList(labQuaAccidentVoList);
			}
			
			return pageResult;
		
	}
	
	@Override
	public LabQuaAccidentVo getLabQuaAccident(String id)
			throws GlobalException {
		LabQuaAccident labQuaAccident=labQuaAccidentDAO.getLabQuaAccident(id);
		LabQuaAccidentVo labQuaAccidentVo=new LabQuaAccidentVo();
		BeanUtils.copyProperties(labQuaAccident, labQuaAccidentVo, new String[]{"isDel"});
		if(null != labQuaAccident.getUnitOrg()){
			labQuaAccidentVo.setUnitOrgId(labQuaAccident.getUnitOrg().getId());
			labQuaAccidentVo.setUnitOrgName(labQuaAccident.getUnitOrg().getName());
		}
		if(null != labQuaAccident.getLabCustomer()){
			labQuaAccidentVo.setAccUnitId(labQuaAccident.getLabCustomer().getId());
			labQuaAccidentVo.setAccUnit(labQuaAccident.getLabCustomer().getName());
		}
		String other = checkName(labQuaAccident.getAccType());
		labQuaAccidentVo.setOther(other);
		return labQuaAccidentVo;
		
	}
	public String checkName(String accType){
		String other = "";
		if(accType.equals(Constants_Source.ACC_NBSP)){
			other="内部评审";
		}
		if(accType.equals(Constants_Source.ACC_GLPS)){
			other="管理评审";
		}
		if(accType.equals(Constants_Source.ACC_YWTS)){
			other="业务投诉";
		}
		if(accType.equals(Constants_Source.ACC_JDCC)){
			other="监督抽查";
		}
		if(accType.equals(Constants_Source.ACC_NLYZ)){
			other="能力验证";
		}
		if(accType.equals(Constants_Source.ACC_KHHFJL)){
			other="客户回访记录";
		}
		if(accType.equals(Constants_Source.ACC_SJGL)){
			other="数据管理";
		}
		return other;
	}

	@Override
	public LabQuaAccidentVo addLabQuaAccident(LabQuaAccidentVo labQuaAccidentVo)
			throws GlobalException {
		LabQuaAccident labQuaAccident=new LabQuaAccident();
		BeanUtils.copyProperties(labQuaAccidentVo, labQuaAccident, new String[] {"isDel"});
		if(!StrUtils.isBlankOrNull(labQuaAccidentVo.getUnitOrgId())){
			LabOrg unitOrg = (LabOrg)labQuaAccidentDAO.findById(LabOrg.class, labQuaAccidentVo.getUnitOrgId());
			labQuaAccident.setUnitOrg(unitOrg);
		}
		if(!StrUtils.isBlankOrNull(labQuaAccidentVo.getAccUnitId())){
			LabCustomer labCustomer = (LabCustomer)labQuaAccidentDAO.findById(LabCustomer.class, labQuaAccidentVo.getAccUnitId());
			labQuaAccident.setLabCustomer(labCustomer);
		}
		labQuaAccident.setAccStatus("0");
		labQuaAccidentDAO.addLabQuaAccident(labQuaAccident);
		labQuaAccidentVo.setId(labQuaAccident.getId());
		if(!StrUtils.isBlankOrNull(labQuaAccidentVo.getOtherId())){
			if(labQuaAccidentVo.getAccType().equals(Constants_Source.ACC_NBSP)){
				LabQuaAuditPlanEle labQuaAuditPlanEle = labQuaAuditPlanEleDAO.getLabQuaAuditPlanEle(labQuaAccidentVo.getOtherId());
				labQuaAuditPlanEle.setAccStatus(Constants_Source.TRUE);
				labQuaAuditPlanEleDAO.updateLabQuaAuditPlanEle(labQuaAuditPlanEle);
			}
			if(labQuaAccidentVo.getAccType().equals(Constants_Source.ACC_GLPS)){
				LabQuaManageCheckPlan labQuaManageCheckPlan = labQuaManageCheckPlanDAO.getLabQuaManageCheckPlan(labQuaAccidentVo.getOtherId());
				labQuaManageCheckPlan.setAccStatus(Constants_Source.TRUE);
				labQuaManageCheckPlanDAO.updateLabQuaManageCheckPlan(labQuaManageCheckPlan);
			}
			if(labQuaAccidentVo.getAccType().equals(Constants_Source.ACC_YWTS)){
				LabQuaComplain labQuaComplain = labQuaComplainDAO.getLabQuaComplain(labQuaAccidentVo.getOtherId());
				labQuaComplain.setAccStatus(Constants_Source.TRUE);
				labQuaComplainDAO.updateLabQuaComplain(labQuaComplain);
			}
			if(labQuaAccidentVo.getAccType().equals(Constants_Source.ACC_JDCC)){
				LabQuaControl labQuaControl = labQuaControlDAO.getLabQuaControl(labQuaAccidentVo.getOtherId());
				labQuaControl.setAccStatus(Constants_Source.TRUE);
				labQuaControlDAO.updateLabQuaControl(labQuaControl);
			}
			if(labQuaAccidentVo.getAccType().equals(Constants_Source.ACC_NLYZ)){
				LabQuaProficiencyPlan labQuaProficiencyPlan = labQuaProficiencyPlanDAO.getLabQuaProficiencyPlan(labQuaAccidentVo.getOtherId());
				labQuaProficiencyPlan.setAccStatus(Constants_Source.TRUE);
				labQuaProficiencyPlanDAO.updateLabQuaProficiencyPlan(labQuaProficiencyPlan);
			}
			if(labQuaAccidentVo.getAccType().equals(Constants_Source.ACC_KHHFJL)){
				LabQuaCusVisit labQuaCusVisit = labQuaCusVisitDAO.getLabQuaCusVisit(labQuaAccidentVo.getOtherId());
				labQuaCusVisit.setAccStatus(Constants_Source.TRUE);
				labQuaCusVisitDAO.updateLabQuaCusVisit(labQuaCusVisit);
			}
		}
		return labQuaAccidentVo;
	}

	@Override
	public boolean updateLabQuaAccident(LabQuaAccidentVo labQuaAccidentVo)
			throws GlobalException {
		LabQuaAccident labQuaAccident=labQuaAccidentDAO.getLabQuaAccident(labQuaAccidentVo.getId());
		BeanUtils.copyProperties(labQuaAccidentVo, labQuaAccident, new String[] {"isDel","createTime","tenantId","createUserId"});
		if(!StrUtils.isBlankOrNull(labQuaAccidentVo.getUnitOrgId())){
			LabOrg unitOrg = (LabOrg)labQuaAccidentDAO.findById(LabOrg.class, labQuaAccidentVo.getUnitOrgId());
			labQuaAccident.setUnitOrg(unitOrg);
		}
		if(!StrUtils.isBlankOrNull(labQuaAccidentVo.getAccUnitId())){
			LabCustomer labCustomer = (LabCustomer)labQuaAccidentDAO.findById(LabCustomer.class, labQuaAccidentVo.getAccUnitId());
			labQuaAccident.setLabCustomer(labCustomer);
		}
		labQuaAccidentDAO.updateLabQuaAccident(labQuaAccident);
		if(!StrUtils.isBlankOrNull(labQuaAccidentVo.getAccStatus())&&labQuaAccidentVo.getAccStatus().equals("2")){
			if(!StrUtils.isBlankOrNull(labQuaAccidentVo.getOtherId())){
				if(labQuaAccidentVo.getAccType().equals(Constants_Source.ACC_NBSP)){
					LabQuaAuditPlanEle labQuaAuditPlanEle = labQuaAuditPlanEleDAO.getLabQuaAuditPlanEle(labQuaAccidentVo.getOtherId());
					labQuaAuditPlanEle.setAccStatus("2");
					labQuaAuditPlanEleDAO.updateLabQuaAuditPlanEle(labQuaAuditPlanEle);
				}
				if(labQuaAccidentVo.getAccType().equals(Constants_Source.ACC_GLPS)){
					LabQuaManageCheckPlan labQuaManageCheckPlan = labQuaManageCheckPlanDAO.getLabQuaManageCheckPlan(labQuaAccidentVo.getOtherId());
					labQuaManageCheckPlan.setAccStatus("2");
					labQuaManageCheckPlanDAO.updateLabQuaManageCheckPlan(labQuaManageCheckPlan);
				}
				if(labQuaAccidentVo.getAccType().equals(Constants_Source.ACC_YWTS)){
					LabQuaComplain labQuaComplain = labQuaComplainDAO.getLabQuaComplain(labQuaAccidentVo.getOtherId());
					labQuaComplain.setAccStatus("2");
					labQuaComplainDAO.updateLabQuaComplain(labQuaComplain);
				}
				if(labQuaAccidentVo.getAccType().equals(Constants_Source.ACC_JDCC)){
					LabQuaControl labQuaControl = labQuaControlDAO.getLabQuaControl(labQuaAccidentVo.getOtherId());
					labQuaControl.setAccStatus("2");
					labQuaControlDAO.updateLabQuaControl(labQuaControl);
				}
				if(labQuaAccidentVo.getAccType().equals(Constants_Source.ACC_NLYZ)){
					LabQuaProficiencyPlan labQuaProficiencyPlan = labQuaProficiencyPlanDAO.getLabQuaProficiencyPlan(labQuaAccidentVo.getOtherId());
					labQuaProficiencyPlan.setAccStatus("2");
					labQuaProficiencyPlanDAO.updateLabQuaProficiencyPlan(labQuaProficiencyPlan);
				}
				if(labQuaAccidentVo.getAccType().equals(Constants_Source.ACC_KHHFJL)){
					LabQuaCusVisit labQuaCusVisit = labQuaCusVisitDAO.getLabQuaCusVisit(labQuaAccidentVo.getOtherId());
					labQuaCusVisit.setAccStatus("2");
					labQuaCusVisitDAO.updateLabQuaCusVisit(labQuaCusVisit);
				}
			}
		}
		return true;
	}
}
