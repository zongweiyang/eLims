package cn.labsoft.labos.business.samreport.service.impl;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.apache.struts2.ServletActionContext;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.common.workflow.action.LabWfConstant;
import cn.labsoft.labos.common.workflow.dao.ILabWfProcessInsDAO;
import cn.labsoft.labos.common.workflow.entity.LabWfProcessIns;
import cn.labsoft.labos.constants.Constants_Business;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.sesseionutils.SessionContainer;
import cn.labsoft.labos.utils.StrUtils;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.business.samreport.dao.ILabSamReportDAO;
import cn.labsoft.labos.business.samreport.dao.ILabSamReportDataDAO;
import cn.labsoft.labos.business.samreport.dao.ILabSamReportEndDAO;
import cn.labsoft.labos.business.samreport.entity.LabSamReport;
import cn.labsoft.labos.business.samreport.entity.LabSamReportData;
import cn.labsoft.labos.business.samreport.entity.LabSamReportEnd;
import cn.labsoft.labos.business.samreport.service.ILabSamReportService;
import cn.labsoft.labos.business.samreport.vo.LabSamReportDataVo;
import cn.labsoft.labos.business.samreport.vo.LabSamReportEndVo;
import cn.labsoft.labos.business.samreport.vo.LabSamReportVo;

@Service("labSamReportService")
public class LabSamReportServiceImpl implements ILabSamReportService {
	private ILabSamReportDAO labSamReportDAO;
	private ILabSamReportDataDAO labSamReportDataDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;
	private ILabSamReportEndDAO labSamReportEndDAO;
	@Resource
	public void setLabSamReportEndDAO(ILabSamReportEndDAO labSamReportEndDAO) {
		this.labSamReportEndDAO = labSamReportEndDAO;
	}
	@Resource
	public void setLabWfProcessInsDAO(ILabWfProcessInsDAO labWfProcessInsDAO) {
		this.labWfProcessInsDAO = labWfProcessInsDAO;
	}
	@Resource
	public void setLabSamReportDataDAO(ILabSamReportDataDAO labSamReportDataDAO) {
		this.labSamReportDataDAO = labSamReportDataDAO;
	}
	@Resource
	public void setLabSamReportDAO(ILabSamReportDAO labSamReportDAO) {
		this.labSamReportDAO = labSamReportDAO;
	}

	@Override
	public LabSamReportVo addLabSamReport(LabSamReportVo labSamReportVo) throws GlobalException {
		
		LabSamReport labSamReport=new LabSamReport();
		try{
			labSamReport=this.vo2Po(labSamReportVo, labSamReport);
			labSamReportDAO.addLabSamReport(labSamReport);
			labSamReportVo.setId(labSamReport.getId());
		}catch(Exception e){
			Log4J.error("LabSamReportServiceImpl addLabSamReport  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return labSamReportVo;
	}

	@Override
	public boolean deleteLabSamReport(String[] ids) throws GlobalException {
		boolean key=true;
		try{
			if(ids!=null&&ids.length>0){
				key=labSamReportDAO.deleteLabSamReport(ids);
			}
		}catch(Exception e){
			key=false;
			Log4J.error("LabSamReportServiceImpl deleteLabSamReport  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}

	@Override
	public LabSamReportVo getLabSamReport(String id) throws GlobalException {
		LabSamReportVo labSamReportVo=new LabSamReportVo();
		if(!StrUtils.isBlankOrNull(id)){
			try{
				LabSamReport labSamReport=labSamReportDAO.getLabSamReport(id);
				labSamReportVo=this.po2Vo(labSamReport, labSamReportVo);
				String hql="FROM LabSamReportData WHERE isDel='"+Constants_Business.N+"'";
				hql+=" AND reportNo='"+labSamReport.getReportNo()+"'";
				List<LabSamReportData> dateList=labSamReportDataDAO.getLabSamReportDataList(hql);
				List<LabSamReportDataVo> dateVoList=new ArrayList<LabSamReportDataVo>();
				if(dateList!=null){
					for (LabSamReportData data : dateList) {
						LabSamReportDataVo dataVo=new LabSamReportDataVo();
						BeanUtils.copyProperties(data, dataVo);
						dateVoList.add(dataVo);
					}
				}
				labSamReportVo.setReportDataList(dateVoList);
			}catch(Exception e){
				Log4J.error("LabSamReportServiceImpl getLabSamReport  error..."+e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return labSamReportVo;
	}

	@Override
	public List<LabSamReportVo> getLabSamReportList(LabSamReportVo labSamReportVo) throws GlobalException {
		String wereHql="";
		
		return this.getLabSamReportVoListByWhere(wereHql);
	}

	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabSamReportPR(LabSamReportVo labSamReportVo, PageResult pageResult)
			throws GlobalException {
		SessionContainer son=(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		String hql="FROM LabSamReport WHERE isDel='"+Constants_Business.N+"'";
		if(!StrUtils.isBlankOrNull(labSamReportVo.getBusNo())){
			hql+=" AND busNo like '%"+labSamReportVo.getBusNo()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labSamReportVo.getReportNo())){
			hql+=" AND reportNo like '%"+labSamReportVo.getReportNo()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labSamReportVo.getLabCustomerName())){
			hql+=" AND labCustomerName like '%"+labSamReportVo.getLabCustomerName()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labSamReportVo.getStartDate())){
			hql+=" AND finishDate >= '"+labSamReportVo.getStartDate()+"'";
		}
		if(!StrUtils.isBlankOrNull(labSamReportVo.getEndDate())){
		}
		if(!StrUtils.isBlankOrNull(labSamReportVo.getBusNo())){
			hql+=" AND busNo like '%"+labSamReportVo.getBusNo()+"%'";
		}
		if (!StrUtils.isBlankOrNull(labSamReportVo.getStatus())
				&& son.getFunId().equals(labSamReportVo.getStatus())) {//当前功能下
			String subhql="SELECT busId FROM LabWfStepIns WHERE isDel='"+Constants_Business.N+"' AND busType='"+Constants_Business.WF_CODE_BUS_RW+"'";
			subhql+=" AND status<>'"+LabWfConstant.BUS_PROCESS_END+"' ";
			subhql+=" AND code='"+son.getFunId()+"'";
			subhql+=" AND tenantId LIKE '"+son.getFunDataStr()+"%' ";
			hql += " AND busId in(" + subhql+ ")";
		}else if(!StrUtils.isBlankOrNull(labSamReportVo.getStatus())
				&&labSamReportVo.getStatus().equals(LabWfConstant.BUS_PROCESS_END)){//已完结
			String subhql="SELECT busId FROM LabWfProcessIns WHERE isDel='"+Constants_Business.N+"' AND type='"+Constants_Business.WF_CODE_BUS_RW+"' AND status='"+LabWfConstant.BUS_PROCESS_END+"'  AND tenantId LIKE '"+son.getFunDataStr()+"%' ";
			hql += " AND busId in(" + subhql+ ")";
		}else if(!StrUtils.isBlankOrNull(labSamReportVo.getStatus())
				&&!son.getFunId().equals(labSamReportVo.getStatus())){//其他功能下
			String subhql="SELECT busId FROM LabWfStepIns WHERE isDel='"+Constants_Business.N+"' AND busType='"+Constants_Business.WF_CODE_BUS_RW+"'";
			subhql+=" AND status='"+LabWfConstant.BUS_PROCESS_END+"'";
			subhql+=" AND code='"+son.getFunId()+"'";
			subhql+=" AND process.status like'%"+labSamReportVo.getStatus()+"%'";
			subhql+=" AND userId ='"+son.getUserId()+"' ";
			hql += " AND busId in(" + subhql+ ")";
		}else{
			String subhql="SELECT busId FROM LabWfStepIns WHERE isDel='"+Constants_Business.N+"' AND busType='"+Constants_Business.WF_CODE_BUS_RW+"' AND code='"+son.getFunId()+"'  AND tenantId LIKE '"+son.getFunDataStr()+"%' ";
			hql += " AND busId in(" + subhql+ ")";
		}
		pageResult=labSamReportDAO.getPageResult(hql, pageResult);
		if(pageResult.getResultList()!=null&&pageResult.getResultList().size()>0){
			List<LabSamReportVo> labSamReportVoList=new ArrayList<LabSamReportVo>();
			List<LabSamReport> listLabSamReport=new ArrayList<LabSamReport>();
			listLabSamReport=pageResult.getResultList();
			for(LabSamReport labSamReport:listLabSamReport){
				LabSamReportVo vo=new LabSamReportVo();
				vo=this.po2Vo(labSamReport, vo);
				LabWfProcessIns  ins =labSamReport.getProcessIns();
				if (ins != null) {
					String status = ins.getStatus();
					if (status.equals(LabWfConstant.BUS_PROCESS_END)) {
						vo.setStatus("已完结");
					} else {
						if (status.contains(son.getFunId())) {
							vo.setIsOper(Constants_Business.Y);
							boolean flag=labWfProcessInsDAO.checkLabWfProcessIns4isBack(labSamReport.getBusId(),labSamReport.getId(),son.getFunId());
							if(flag){
								vo.setIsBack(Constants_Business.Y);
							}else{
								vo.setIsBack(Constants_Business.N);
							}
						}
						String str = labWfProcessInsDAO.getLabWfStepStrByInsId(ins.getId());
						vo.setStatus(str);
					}
				}
				labSamReportVoList.add(vo);
			}
			pageResult.setResultList(labSamReportVoList);
		}
		return pageResult;
	}
	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabSamReportPR4Audit(LabSamReportVo labSamReportVo, PageResult pageResult)
			throws GlobalException {
		SessionContainer son=(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		
		String hql="FROM LabSamReport WHERE isDel='"+Constants_Business.N+"'";
		if(!StrUtils.isBlankOrNull(labSamReportVo.getBusNo())){
			hql+=" AND busNo like '%"+labSamReportVo.getBusNo()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labSamReportVo.getReportNo())){
			hql+=" AND reportNo like '%"+labSamReportVo.getReportNo()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labSamReportVo.getLabCustomerName())){
			hql+=" AND labCustomerName like '%"+labSamReportVo.getLabCustomerName()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labSamReportVo.getStartDate())){
			hql+=" AND finishDate >= '"+labSamReportVo.getStartDate()+"'";
		}
		if(!StrUtils.isBlankOrNull(labSamReportVo.getEndDate())){
			hql+=" AND finishDate <= '"+labSamReportVo.getEndDate()+"'";
		}
		if(!StrUtils.isBlankOrNull(labSamReportVo.getBusNo())){
			hql+=" AND busNo like '%"+labSamReportVo.getBusNo()+"%'";
		}
		if (!StrUtils.isBlankOrNull(labSamReportVo.getStatus())
				&& son.getFunId().equals(labSamReportVo.getStatus())) {//当前功能下
			String subhql="SELECT busId FROM LabWfStepIns WHERE isDel='"+Constants_Business.N+"' AND busType='"+Constants_Business.WF_CODE_BUS_RW+"'";
			subhql+=" AND status<>'"+LabWfConstant.BUS_PROCESS_END+"' ";
			subhql+=" AND code='"+son.getFunId()+"'";
			subhql+=" AND tenantId LIKE '"+son.getFunDataStr()+"%' ";
			hql += " AND busId in(" + subhql+ ")";
		}else if(!StrUtils.isBlankOrNull(labSamReportVo.getStatus())
				&&labSamReportVo.getStatus().equals(LabWfConstant.BUS_PROCESS_END)){//已完结
			String subhql="SELECT busId FROM LabWfProcessIns WHERE isDel='"+Constants_Business.N+"' AND type='"+Constants_Business.WF_CODE_BUS_RW+"' AND status='"+LabWfConstant.BUS_PROCESS_END+"'  AND tenantId LIKE '"+son.getFunDataStr()+"%' ";
			hql += " AND busId in(" + subhql+ ")";
		}else if(!StrUtils.isBlankOrNull(labSamReportVo.getStatus())
				&&!son.getFunId().equals(labSamReportVo.getStatus())){//其他功能下
			String subhql="SELECT busId FROM LabWfStepIns WHERE isDel='"+Constants_Business.N+"' AND busType='"+Constants_Business.WF_CODE_BUS_RW+"'";
			subhql+=" AND status='"+LabWfConstant.BUS_PROCESS_END+"'";
			subhql+=" AND code='"+son.getFunId()+"'";
			subhql+=" AND process.status like'%"+labSamReportVo.getStatus()+"%'";
			subhql+=" AND userId ='"+son.getUserId()+"' ";
			hql += " AND busId in(" + subhql+ ")";
		}else{
			String subhql="SELECT busId FROM LabWfStepIns WHERE isDel='"+Constants_Business.N+"' AND busType='"+Constants_Business.WF_CODE_BUS_RW+"' AND code='"+son.getFunId()+"'  AND tenantId LIKE '"+son.getFunDataStr()+"%' ";
			hql += " AND busId in(" + subhql+ ")";
		}
		pageResult=labSamReportDAO.getPageResult(hql, pageResult);
		if(pageResult.getResultList()!=null&&pageResult.getResultList().size()>0){
			List<LabSamReportVo> labSamReportVoList=new ArrayList<LabSamReportVo>();
			List<LabSamReport> listLabSamReport=new ArrayList<LabSamReport>();
			listLabSamReport=pageResult.getResultList();
			for(LabSamReport labSamReport:listLabSamReport){
				LabSamReportVo vo=new LabSamReportVo();
				vo=this.po2Vo(labSamReport, vo);
				LabWfProcessIns ins=labSamReport.getProcessIns();
				if (ins != null) {
					String status = ins.getStatus();
					if (status.equals(LabWfConstant.BUS_PROCESS_END)) {
						vo.setStatus("已完结");
					} else {
						if (status.contains(son.getFunId())) {
							vo.setIsOper(Constants_Business.Y);
							boolean flag=labWfProcessInsDAO.checkLabWfProcessIns4isBack(labSamReport.getBusId(),labSamReport.getId(),son.getFunId());
							if(flag){
								vo.setIsBack(Constants_Business.Y);
							}else{
								vo.setIsBack(Constants_Business.N);
							}
						}
						String str = labWfProcessInsDAO.getLabWfStepStrByInsId(ins.getId());
						vo.setStatus(str);
					}
				}
				labSamReportVoList.add(vo);
			}
			pageResult.setResultList(labSamReportVoList);
		}
		return pageResult;
	}
	@SuppressWarnings("unchecked")
	@Override
	public PageResult getLabSamReportPR4Send(LabSamReportVo labSamReportVo, PageResult pageResult)
			throws GlobalException {
		SessionContainer son=(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		
		String hql="FROM LabSamReport WHERE isDel='"+Constants_Business.N+"'";
		if(!StrUtils.isBlankOrNull(labSamReportVo.getBusNo())){
			hql+=" AND busNo like '%"+labSamReportVo.getBusNo()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labSamReportVo.getReportNo())){
			hql+=" AND reportNo like '%"+labSamReportVo.getReportNo()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labSamReportVo.getLabCustomerName())){
			hql+=" AND labCustomerName like '%"+labSamReportVo.getLabCustomerName()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labSamReportVo.getStartDate())){
			hql+=" AND finishDate >= '"+labSamReportVo.getStartDate()+"'";
		}
		if(!StrUtils.isBlankOrNull(labSamReportVo.getEndDate())){
			hql+=" AND finishDate <= '"+labSamReportVo.getEndDate()+"'";
		}
		if(!StrUtils.isBlankOrNull(labSamReportVo.getBusNo())){
			hql+=" AND busNo like '%"+labSamReportVo.getBusNo()+"%'";
		}
		if (!StrUtils.isBlankOrNull(labSamReportVo.getStatus())
				&& son.getFunId().equals(labSamReportVo.getStatus())) {//当前功能下
			String subhql="SELECT busId FROM LabWfStepIns WHERE isDel='"+Constants_Business.N+"' AND busType='"+Constants_Business.WF_CODE_BUS_RW+"'";
			subhql+=" AND status<>'"+LabWfConstant.BUS_PROCESS_END+"' ";
			subhql+=" AND code='"+son.getFunId()+"'";
			subhql+=" AND tenantId LIKE '"+son.getFunDataStr()+"%' ";
			hql += " AND busId in(" + subhql+ ")";
		}else if(!StrUtils.isBlankOrNull(labSamReportVo.getStatus())
				&&labSamReportVo.getStatus().equals(LabWfConstant.BUS_PROCESS_END)){//已完结
			String subhql="SELECT busId FROM LabWfProcessIns WHERE isDel='"+Constants_Business.N+"' AND type='"+Constants_Business.WF_CODE_BUS_RW+"' AND status='"+LabWfConstant.BUS_PROCESS_END+"'  AND tenantId LIKE '"+son.getFunDataStr()+"%' ";
			hql += " AND busId in(" + subhql+ ")";
		}else if(!StrUtils.isBlankOrNull(labSamReportVo.getStatus())
				&&!son.getFunId().equals(labSamReportVo.getStatus())){//其他功能下
			String subhql="SELECT busId FROM LabWfStepIns WHERE isDel='"+Constants_Business.N+"' AND busType='"+Constants_Business.WF_CODE_BUS_RW+"'";
			subhql+=" AND status='"+LabWfConstant.BUS_PROCESS_END+"'";
			subhql+=" AND code='"+son.getFunId()+"'";
			subhql+=" AND process.status like'%"+labSamReportVo.getStatus()+"%'";
			subhql+=" AND userId ='"+son.getUserId()+"' ";
			hql += " AND busId in(" + subhql+ ")";
		}else{
			String subhql="SELECT busId FROM LabWfStepIns WHERE isDel='"+Constants_Business.N+"' AND busType='"+Constants_Business.WF_CODE_BUS_RW+"' AND code='"+son.getFunId()+"'  AND tenantId LIKE '"+son.getFunDataStr()+"%' ";
			hql += " AND busId in(" + subhql+ ")";
		}
		pageResult=labSamReportDAO.getPageResult(hql, pageResult);
		if(pageResult.getResultList()!=null&&pageResult.getResultList().size()>0){
			List<LabSamReportVo> labSamReportVoList=new ArrayList<LabSamReportVo>();
			List<LabSamReport> listLabSamReport=new ArrayList<LabSamReport>();
			listLabSamReport=pageResult.getResultList();
			for(LabSamReport labSamReport:listLabSamReport){
				LabSamReportVo vo=new LabSamReportVo();
				vo=this.po2Vo(labSamReport, vo);
				LabWfProcessIns ins=labSamReport.getProcessIns();
				if (ins != null) {
					String status = ins.getStatus();
					if (status.equals(LabWfConstant.BUS_PROCESS_END)) {
						vo.setStatus("已完结");
					} else {
						if (status.contains(son.getFunId())) {
							vo.setIsOper(Constants_Business.Y);
							boolean flag=labWfProcessInsDAO.checkLabWfProcessIns4isBack(labSamReport.getBusId(),labSamReport.getId(),son.getFunId());
							if(flag){
								vo.setIsBack(Constants_Business.Y);
							}else{
								vo.setIsBack(Constants_Business.N);
							}
						}
						String str = labWfProcessInsDAO.getLabWfStepStrByInsId(ins.getId());
						vo.setStatus(str);
					}
				}
				labSamReportVoList.add(vo);
			}
			pageResult.setResultList(labSamReportVoList);
		}
		return pageResult;
	}
	@Override
	public boolean updateLabSamReport(LabSamReportVo labSamReportVo) throws GlobalException {
		SessionContainer son=(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		LabSamReport labSamReport=new LabSamReport();
		boolean key=true;
		try{
			labSamReport=labSamReportDAO.getLabSamReport(labSamReportVo.getId());;
			labSamReport.setTestResult(labSamReportVo.getTestResult());
			labSamReport.setRemark(labSamReportVo.getRemark());
			labSamReportDAO.updateLabSamReport(labSamReport);
		}catch(Exception e){
			key=false;
			Log4J.error("LabSamReportServiceImpl updateLabSamReport  error..."+e.getMessage(), e);
		}
		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labSamReportVo.getId(), labSamReportVo.getId(),
				Constants_Business.WF_CODE_BUS_RW, son.getFunId(),"报告编制提交："+labSamReport.getReportNo(),
				labSamReportVo.getAuditResult(), LabWfConstant.BUS_STEP_ALL,null,null);
		if(ins==null){
			throw new GlobalException("LabSampRegisterServiceImpl...流程实例化出错！");
		}
		return key;
	}
	@Override
	public boolean updateLabSamReport4Audit(LabSamReportVo labSamReportVo) throws GlobalException {
		SessionContainer son=(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		boolean key=true;
		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labSamReportVo.getId(), labSamReportVo.getId(),
				Constants_Business.WF_CODE_BUS_RW, son.getFunId(),labSamReportVo.getAuditMessage(),
				labSamReportVo.getAuditResult(), LabWfConstant.BUS_STEP_ALL,null,null);
		if(ins==null){
			throw new GlobalException("LabSampRegisterServiceImpl...流程实例化出错！");
		}
		return key;
	}
	@Override
	public boolean updateLabSamReport4Send(LabSamReportVo labSamReportVo) throws GlobalException {
		SessionContainer son=(SessionContainer)ServletActionContext.getRequest().getSession().getAttribute(SessionContainer.Session_Container);
		boolean key=true;
		try{
			LabSamReport labSamReport=labSamReportDAO.getLabSamReport(labSamReportVo.getId());;
			LabSamReportEndVo samReportEndVo=labSamReportVo.getSamReportEndVo();
			if(samReportEndVo!=null){
				String hql="FROM LabSamReportEnd WHERE reportId='"+labSamReport.getId()+"'";
				LabSamReportEnd samReportEnd=(LabSamReportEnd)labSamReportEndDAO.find(hql, 0);
				if(samReportEnd!=null){
					BeanUtils.copyProperties(samReportEndVo, samReportEnd,new String[]{"id","isDel","createTime","createUserId","tenantId"});
					samReportEnd.setBusId(labSamReport.getBusId());
					samReportEnd.setReportId(labSamReport.getId());
					samReportEnd.setBusNo(labSamReport.getBusNo());
					samReportEnd.setReportNo(labSamReport.getReportNo());
					labSamReportEndDAO.updateLabSamReportEnd(samReportEnd);
				}else{
					samReportEnd=new LabSamReportEnd();
					BeanUtils.copyProperties(samReportEndVo, samReportEnd);
					samReportEnd.setBusId(labSamReport.getBusId());
					samReportEnd.setReportId(labSamReport.getId());
					samReportEnd.setBusNo(labSamReport.getBusNo());
					samReportEnd.setReportNo(labSamReport.getReportNo());
					labSamReportEndDAO.addLabSamReportEnd(samReportEnd);
				}
			}
			
		}catch(Exception e){
			key=false;
			Log4J.error("LabSamReportServiceImpl updateLabSamReport  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labSamReportVo.getId(), labSamReportVo.getId(),
				Constants_Business.WF_CODE_BUS_RW, son.getFunId(),"报告发送",
				labSamReportVo.getAuditResult(), LabWfConstant.BUS_STEP_ALL,null,null);
		if(ins==null){
			throw new GlobalException("LabSampRegisterServiceImpl...流程实例化出错！");
		}
		return key;
	}
	@Override
	public boolean updateLabSamReport4Report(LabSamReportVo labSamReportVo) throws GlobalException {
		LabSamReport labSamReport=new LabSamReport();
		boolean key=true;
		try{
			labSamReport=labSamReportDAO.getLabSamReport(labSamReportVo.getId());
			labSamReport.setReportTempId(labSamReportVo.getReportTempId());
			String path=labSamReportVo.getReportPath();
			path=path.replace("\\", "/");
			labSamReport.setReportPath(path);
			labSamReportDAO.updateLabSamReport(labSamReport);
		}catch(Exception e){
			key=false;
			Log4J.error("LabSamReportServiceImpl updateLabSamReport  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}
	@Override
	public boolean updateLabSamReport4Del(String[] ids) throws GlobalException {
		boolean key=true;
		if(ids!=null&&ids.length>0){
			try{
				for(String id:ids){
					LabSamReport labSamReport=labSamReportDAO.getLabSamReport(id);
					labSamReport.setIsDel(Constants_Business.Y);
					labSamReportDAO.updateLabSamReport(labSamReport);
				}
			}catch(Exception e){
				key=false;
				Log4J.error("LabSamReportServiceImpl updateLabSamReport4Del  error..."+e.getMessage(),
						e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return key;
	}
	@SuppressWarnings("unchecked")
	public List<LabSamReportVo> getLabSamReportVoListByWhere(String wereHql) throws GlobalException{
		List<LabSamReportVo> labSamReportVoList=new ArrayList<LabSamReportVo>();
		String hql="FROM LabSamReport WHERE isDel='"+Constants_Business.N+"' ";
		if(!StrUtils.isBlankOrNull(wereHql))hql+=wereHql;
		List<LabSamReport> labSamReportList=labSamReportDAO.find(hql);
		if(labSamReportList!=null&&labSamReportList.size()>0){
			for(LabSamReport labSamReport:labSamReportList){
				LabSamReportVo labSamReportVo=new LabSamReportVo();
				labSamReportVo=this.po2Vo(labSamReport, labSamReportVo);
				labSamReportVoList.add(labSamReportVo);
			}
		}
		return labSamReportVoList;
	}
	public LabSamReport vo2Po(LabSamReportVo labSamReportVo,LabSamReport labSamReport){
		BeanUtils.copyProperties(labSamReportVo, labSamReport,new String[]{"isDel"});
		return labSamReport;
	}
	public LabSamReportVo po2Vo(LabSamReport labSamReport,LabSamReportVo labSamReportVo){
		BeanUtils.copyProperties(labSamReport, labSamReportVo);
		return labSamReportVo;
	}
	@Override
	public LabSamReportVo getLabSamReport4Send(String id) throws GlobalException {
		LabSamReportVo labSamReportVo=new LabSamReportVo();
		if(!StrUtils.isBlankOrNull(id)){
			try{
				LabSamReport labSamReport=labSamReportDAO.getLabSamReport(id);
				labSamReportVo=this.po2Vo(labSamReport, labSamReportVo);
				String hql="FROM LabSamReportEnd WHERE 1=1";
				hql+=" AND reportId='"+labSamReport.getId()+"'";
				LabSamReportEnd reportend=(LabSamReportEnd)labSamReportEndDAO.find(hql, 0);
				if(reportend!=null){
					LabSamReportEndVo endVo=new LabSamReportEndVo();
					BeanUtils.copyProperties(reportend, endVo);
					labSamReportVo.setSamReportEndVo(endVo);
				}else{
					LabSamReportEndVo endVo=new LabSamReportEndVo();
					endVo.setLabCustomerName(labSamReport.getLabCustomerName());
					endVo.setToUser(labSamReport.getUser());
					endVo.setFax(labSamReport.getFax());
					endVo.setEmail(labSamReport.getEmail());
					endVo.setReportPost(labSamReport.getReportPost());
					endVo.setTelephone(labSamReport.getTelephone());
					endVo.setAddress(labSamReport.getAddress());
					labSamReportVo.setSamReportEndVo(endVo);
				}
			}catch(Exception e){
				Log4J.error("LabSamReportServiceImpl getLabSamReport  error..."+e.getMessage(), e);
				throw new GlobalException("" + e.getMessage());
			}
		}
		return labSamReportVo;
	}
}
