package cn.labsoft.labos.source.quality.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import cn.labsoft.labos.base.code.dao.ILabCodeDAO;
import cn.labsoft.labos.base.code.entity.LabCode;
import cn.labsoft.labos.base.code.vo.LabCodeVo;
import cn.labsoft.labos.base.org.entity.LabOrg;
import cn.labsoft.labos.common.number.action.ThreadNumber;
import cn.labsoft.labos.common.number.dao.ILabNumberDAO;
import cn.labsoft.labos.common.workflow.action.LabWfConstant;
import cn.labsoft.labos.common.workflow.dao.ILabWfProcessInsDAO;
import cn.labsoft.labos.constants.Constants_Source;
import cn.labsoft.labos.framework.common.exception.GlobalException;
import cn.labsoft.labos.framework.common.log.Log4J;
import cn.labsoft.labos.framework.common.page.PageResult;
import cn.labsoft.labos.framework.common.service.BaseService;
import cn.labsoft.labos.source.quality.dao.ILabQuaAuditPlanEleDAO;
import cn.labsoft.labos.source.quality.dao.ILabQuaAuditPlanEleDetailDAO;
import cn.labsoft.labos.source.quality.dao.ILabQuaAuditRecordDAO;
import cn.labsoft.labos.source.quality.dao.ILabQuaAuditRecordDetailDAO;
import cn.labsoft.labos.source.quality.dao.ILabQuaInitAuditPlanDAO;
import cn.labsoft.labos.source.quality.entity.LabQuaAuditPlanEle;
import cn.labsoft.labos.source.quality.entity.LabQuaAuditPlanEleDetail;
import cn.labsoft.labos.source.quality.entity.LabQuaAuditRecord;
import cn.labsoft.labos.source.quality.entity.LabQuaAuditRecordDetail;
import cn.labsoft.labos.source.quality.entity.LabQuaInitAuditPlan;
import cn.labsoft.labos.source.quality.service.ILabQuaAuditRecordService;
import cn.labsoft.labos.source.quality.vo.LabQuaAuditRecordDetailVo;
import cn.labsoft.labos.source.quality.vo.LabQuaAuditRecordVo;
import cn.labsoft.labos.utils.StrUtils;


@Service("labQuaAuditRecordService")
public class LabQuaAuditRecordServiceImpl extends BaseService implements ILabQuaAuditRecordService {
	public  ExecutorService poolSer = Executors.newSingleThreadExecutor();
	private ILabQuaAuditRecordDAO labQuaAuditRecordDAO;
	private ILabQuaInitAuditPlanDAO labQuaInitAuditPlanDAO;
	private ILabQuaAuditPlanEleDAO labQuaAuditPlanEleDAO;
	private ILabQuaAuditRecordDetailDAO labQuaAuditRecordDetailDAO;
	private ILabQuaAuditPlanEleDetailDAO labQuaAuditPlanEleDetailDAO;
	private ILabWfProcessInsDAO labWfProcessInsDAO;
	private ILabNumberDAO labNumberDAO;
	private ILabCodeDAO labCodeDAO;

	@Resource
	public void setLabWfProcessInsDAO(ILabWfProcessInsDAO labWfProcessInsDAO) {
		this.labWfProcessInsDAO = labWfProcessInsDAO;
	}
	@Resource
	public void setLabNumberDAO(ILabNumberDAO labNumberDAO) {
		this.labNumberDAO = labNumberDAO;
	}
	@Resource
	public void setLabCodeDAO(ILabCodeDAO labCodeDAO) {
		this.labCodeDAO = labCodeDAO;
	}
	@Resource
	public void setLabQuaAuditRecordDAO(ILabQuaAuditRecordDAO labQuaAuditRecordDAO) {
		this.labQuaAuditRecordDAO = labQuaAuditRecordDAO;
	}
	@Resource
	public void setLabQuaInitAuditPlanDAO(
			ILabQuaInitAuditPlanDAO labQuaInitAuditPlanDAO) {
		this.labQuaInitAuditPlanDAO = labQuaInitAuditPlanDAO;
	}
	@Resource
	public void setLabQuaAuditPlanEleDAO(
			ILabQuaAuditPlanEleDAO labQuaAuditPlanEleDAO) {
		this.labQuaAuditPlanEleDAO = labQuaAuditPlanEleDAO;
	}
	@Resource
	public void setLabQuaAuditRecordDetailDAO(
			ILabQuaAuditRecordDetailDAO labQuaAuditRecordDetailDAO) {
		this.labQuaAuditRecordDetailDAO = labQuaAuditRecordDetailDAO;
	}
	@Resource
	public void setLabQuaAuditPlanEleDetailDAO(
			ILabQuaAuditPlanEleDetailDAO labQuaAuditPlanEleDetailDAO) {
		this.labQuaAuditPlanEleDetailDAO = labQuaAuditPlanEleDetailDAO;
	}
	@Override
	public boolean update2DelLabQuaAuditRecord(String[] ids) throws GlobalException {
		if(ids.length > 0 ){
			for(String id : ids ){
				LabQuaAuditRecord labQuaAuditRecord=labQuaAuditRecordDAO.getLabQuaAuditRecord(id);
				labQuaAuditRecord.setIsDel(Constants_Source.Y);
				labQuaAuditRecordDAO.updateLabQuaAuditRecord(labQuaAuditRecord);
//				labWfProcessInsDAO.updateLabWfProcessIns4delByBusId(labQuaAuditRecord.getId());
			}
		}
		return true;
	}

	@Override
	public List<LabQuaAuditRecordVo> getLabQuaAuditRecordList(
			LabQuaAuditRecordVo labQuaAuditRecordVo) throws GlobalException {
		// TODO Auto-generated method stub
		String hql = "FROM LabQuaAuditRecord WHERE isDel= '"+Constants_Source.N+"'";
		if(!StrUtils.isBlankOrNull(labQuaAuditRecordVo.getQuaAuditPlanEleId())){
			hql += " AND labQuaAuditPlanEle.id = '"+labQuaAuditRecordVo.getQuaAuditPlanEleId()+"'";
		}
		if(!StrUtils.isBlankOrNull(labQuaAuditRecordVo.getQuaAuditPlanEleName())){
			hql += " AND labQuaAuditPlanEle.purpose like '%"+labQuaAuditRecordVo.getQuaAuditPlanEleName()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labQuaAuditRecordVo.getAuditTime())){
			hql += " AND auditTime like '%"+labQuaAuditRecordVo.getAuditTime()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labQuaAuditRecordVo.getIsFile())){
			hql += " AND isFile = '"+labQuaAuditRecordVo.getIsFile()+"'";
		}
		if(!StrUtils.isBlankOrNull(labQuaAuditRecordVo.getLabOrgId())){
			hql += " AND org.id = '"+labQuaAuditRecordVo.getLabOrgId()+"'";
		}
		List<LabQuaAuditRecord> labQuaAuditRecordList=labQuaAuditRecordDAO.getLabQuaAuditRecordByHQL(hql);
		List<LabQuaAuditRecordVo> labQuaAuditRecordVoList=new ArrayList<LabQuaAuditRecordVo>();
		if (null!=labQuaAuditRecordList && labQuaAuditRecordList.size()>0) {
			for (LabQuaAuditRecord labQuaAuditRecord : labQuaAuditRecordList) {
				LabQuaAuditRecordVo labQuaAuditRecordVoOne=new LabQuaAuditRecordVo();
				BeanUtils.copyProperties(labQuaAuditRecord, labQuaAuditRecordVoOne, new String[]{"isDel"});
				if(null != labQuaAuditRecord.getOrg()){
					labQuaAuditRecordVoOne.setLabOrgId(labQuaAuditRecord.getOrg().getId());
					labQuaAuditRecordVoOne.setLabOrgName(labQuaAuditRecord.getOrg().getName());
				}
				labQuaAuditRecordVoList.add(labQuaAuditRecordVoOne);
			} 
		}
		return labQuaAuditRecordVoList;
	}

	@Override
	public PageResult getLabQuaAuditRecordPR(LabQuaAuditRecordVo labQuaAuditRecordVo, PageResult pageResult) throws GlobalException {
		String hql = "FROM LabQuaAuditRecord WHERE isDel= '"+Constants_Source.N+"'";
		if(!StrUtils.isBlankOrNull(labQuaAuditRecordVo.getQuaAuditPlanEleId())){
			hql += " AND labQuaAuditPlanEle.id = '"+labQuaAuditRecordVo.getQuaAuditPlanEleId()+"'";
		}
		if(!StrUtils.isBlankOrNull(labQuaAuditRecordVo.getAuditTime())){
			hql += " AND auditTime like '%"+labQuaAuditRecordVo.getAuditTime()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labQuaAuditRecordVo.getQuaAuditPlanEleName())){
			hql += " AND labQuaAuditPlanEle.purpose like '%"+labQuaAuditRecordVo.getQuaAuditPlanEleName()+"%'";
		}
		if(!StrUtils.isBlankOrNull(labQuaAuditRecordVo.getIsFile())){
			hql += " AND isFile = '"+labQuaAuditRecordVo.getIsFile()+"'";
		}
		if(!StrUtils.isBlankOrNull(labQuaAuditRecordVo.getLabOrgId())){
			hql += " AND org.id = '"+labQuaAuditRecordVo.getLabOrgId()+"'";
		}
		if(!StrUtils.isBlankOrNull(labQuaAuditRecordVo.getStatus())){
			hql += " AND processIns.status = '"+labQuaAuditRecordVo.getStatus()+"'";
		}
		pageResult = labQuaAuditRecordDAO.getLabQuaAuditRecordPR(hql, pageResult);
		List<LabQuaAuditRecord> labQuaAuditRecordList=pageResult.getResultList();
		List<LabQuaAuditRecordVo> labQuaAuditRecordVoList=new ArrayList<LabQuaAuditRecordVo>();
		if (null!=labQuaAuditRecordList && labQuaAuditRecordList.size()>0) {
			for (LabQuaAuditRecord labQuaAuditRecord : labQuaAuditRecordList) {
				LabQuaAuditRecordVo labQuaAuditRecordVoOne=new LabQuaAuditRecordVo();
				BeanUtils.copyProperties(labQuaAuditRecord, labQuaAuditRecordVoOne, new String[]{"isDel"});
				if(null != labQuaAuditRecord.getOrg()){
					labQuaAuditRecordVoOne.setLabOrgId(labQuaAuditRecord.getOrg().getId());
					labQuaAuditRecordVoOne.setLabOrgName(labQuaAuditRecord.getOrg().getName());
				}
				if(null != labQuaAuditRecord.getLabQuaAuditPlanEle()){
					labQuaAuditRecordVoOne.setQuaAuditPlanEleId(labQuaAuditRecord.getLabQuaAuditPlanEle().getId());
					labQuaAuditRecordVoOne.setQuaAuditPlanEleName(labQuaAuditRecord.getLabQuaAuditPlanEle().getPurpose());
				}
				if (labQuaAuditRecord.getProcessIns() != null) {
					String status = labQuaAuditRecord.getProcessIns().getStatus();
					if (status.equals(LabWfConstant.BUS_PROCESS_END)) {
						labQuaAuditRecordVoOne.setStatus("已完结");
					} else {
						if (status.contains(getSessionContainer().getFunId())) {
							labQuaAuditRecordVoOne.setIsOper("Y");
						}
						String str = labWfProcessInsDAO
								.getLabWfStepStrByInsId(labQuaAuditRecord.getProcessIns()
										.getId());
						labQuaAuditRecordVoOne.setStatus(str);
					}
				}
				labQuaAuditRecordVoList.add(labQuaAuditRecordVoOne);
			} 
			pageResult.setResultList(labQuaAuditRecordVoList);
		}
		return pageResult;

	}

	@Override
	public LabQuaAuditRecordVo getLabQuaAuditRecord(String id) throws GlobalException {
		LabQuaAuditRecord labQuaAuditRecord = labQuaAuditRecordDAO.getLabQuaAuditRecord(id);
		LabQuaAuditRecordVo labQuaAuditRecordVo = new LabQuaAuditRecordVo();
		BeanUtils.copyProperties(labQuaAuditRecord, labQuaAuditRecordVo, new String[] { "isDel"});
		if(null != labQuaAuditRecord.getOrg()){
			labQuaAuditRecordVo.setLabOrgId(labQuaAuditRecord.getOrg().getId());
			labQuaAuditRecordVo.setLabOrgName(labQuaAuditRecord.getOrg().getName());
		}
		if(null != labQuaAuditRecord.getLabQuaAuditPlanEle()){
			labQuaAuditRecordVo.setQuaAuditPlanEleId(labQuaAuditRecord.getLabQuaAuditPlanEle().getId());
			labQuaAuditRecordVo.setQuaAuditPlanEleName(labQuaAuditRecord.getLabQuaAuditPlanEle().getPurpose());
			labQuaAuditRecordVo.setPurpose(labQuaAuditRecord.getLabQuaAuditPlanEle().getPurpose());
			labQuaAuditRecordVo.setImplement(labQuaAuditRecord.getLabQuaAuditPlanEle().getImplement());
			labQuaAuditRecordVo.setRange(labQuaAuditRecord.getLabQuaAuditPlanEle().getRange());
			labQuaAuditRecordVo.setAuditType(labQuaAuditRecord.getLabQuaAuditPlanEle().getAuditType());
			labQuaAuditRecordVo.setAuditTime(labQuaAuditRecord.getLabQuaAuditPlanEle().getAuditTime());
		}
		List<LabQuaAuditRecordDetailVo> labQuaAuditRecordDetailVoList = getLabQuaAuditRecordDetailList(id, labQuaAuditRecord.getLabQuaAuditPlanEle().getId(), labQuaAuditRecord.getMonth(),"");
		labQuaAuditRecordVo.setLabQuaAuditRecordDetailVoList(labQuaAuditRecordDetailVoList);
		return labQuaAuditRecordVo;
	}

	@Override
	public LabQuaAuditRecordVo addLabQuaAuditRecord(LabQuaAuditRecordVo labQuaAuditRecordVo) throws GlobalException {
		LabQuaAuditRecord labQuaAuditRecord = new LabQuaAuditRecord();
		BeanUtils.copyProperties(labQuaAuditRecordVo, labQuaAuditRecord, new String[] {"isDel"});
		labQuaAuditRecord.setIsDel(Constants_Source.N);
		if(!StrUtils.isBlankOrNull(labQuaAuditRecordVo.getQuaAuditPlanEleId())){
			LabQuaAuditPlanEle labQuaAuditPlanEle = labQuaAuditPlanEleDAO.getLabQuaAuditPlanEle(labQuaAuditRecordVo.getQuaAuditPlanEleId());
			labQuaAuditRecord.setLabQuaAuditPlanEle(labQuaAuditPlanEle);
			if (labQuaAuditRecord.getMonth().equals("12")) {
				labQuaAuditPlanEle.setStatus("20");
			} else {
				labQuaAuditPlanEle.setStatus("10");
			}
			labQuaAuditPlanEleDAO.updateLabQuaAuditPlanEle(labQuaAuditPlanEle);
		}
		if(!StrUtils.isBlankOrNull(labQuaAuditRecordVo.getLabOrgId())){
			LabOrg labOrg = (LabOrg)labQuaAuditRecordDAO.findById(LabOrg.class, labQuaAuditRecordVo.getLabOrgId());
			labQuaAuditRecord.setOrg(labOrg);
		}
		ThreadNumber threadNumber=new ThreadNumber(null,labNumberDAO,Constants_Source.CODE_NB,new String[]{""},Constants_Source.CODE_USE_RUN);
		try {
			labQuaAuditRecord.setReportNo(poolSer.submit(threadNumber).get().toString());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		} catch (ExecutionException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
			throw new GlobalException("" + e.getMessage());
		}
		labQuaAuditRecord.setCreateUserId(getSessionContainer().getUserId());
		labQuaAuditRecord.setIsFile("1");
		labQuaAuditRecordDAO.addLabQuaAuditRecord(labQuaAuditRecord);
		List<LabQuaAuditRecordDetailVo> labQuaAuditRecordDetailVoList = labQuaAuditRecordVo.getLabQuaAuditRecordDetailVoList();
		if (null != labQuaAuditRecordDetailVoList && labQuaAuditRecordDetailVoList.size() > 0 )
			for (LabQuaAuditRecordDetailVo labQuaAuditRecordDetailVo : labQuaAuditRecordDetailVoList) {
				if (null == labQuaAuditRecordDetailVo)
					continue;
				LabQuaAuditRecordDetail labQuaAuditRecordDetail = new LabQuaAuditRecordDetail();
				BeanUtils.copyProperties(labQuaAuditRecordDetailVo, labQuaAuditRecordDetail,new String[]{""});
				labQuaAuditRecordDetail.setLabQuaAuditRecord(labQuaAuditRecord);
				if(!StrUtils.isBlankOrNull(labQuaAuditRecordDetailVo.getInitAuditPlanId())){
					LabQuaInitAuditPlan labQuaInitAuditPlan = labQuaInitAuditPlanDAO.getLabQuaInitAuditPlan(labQuaAuditRecordDetailVo.getInitAuditPlanId());
					labQuaAuditRecordDetail.setLabQuaInitAuditPlan(labQuaInitAuditPlan);
				}
				if(!StrUtils.isBlankOrNull(labQuaAuditRecordDetailVo.getInitAuditPlanContentId())){
					LabQuaInitAuditPlan labQuaInitAuditPlanContent = labQuaInitAuditPlanDAO.getLabQuaInitAuditPlan(labQuaAuditRecordDetailVo.getInitAuditPlanContentId());
					labQuaAuditRecordDetail.setLabQuaInitAuditPlanContent(labQuaInitAuditPlanContent);
				}
				if(!StrUtils.isBlankOrNull(labQuaAuditRecordDetailVo.getInitAuditPlanKeyId())){
					LabQuaInitAuditPlan labQuaInitAuditPlanKey = labQuaInitAuditPlanDAO.getLabQuaInitAuditPlan(labQuaAuditRecordDetailVo.getInitAuditPlanKeyId());
					labQuaAuditRecordDetail.setLabQuaInitAuditPlanKey(labQuaInitAuditPlanKey);
				}
				labQuaAuditRecordDetailDAO.addLabQuaAuditRecordDetail(labQuaAuditRecordDetail);
			}
		labQuaAuditRecordVo.setId(labQuaAuditRecord.getId());
//		// 流程实例
//		LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(labQuaAuditRecord
//				.getId(), Constants.WF_CODE_QUA_AUDIT, getSessionContainer().getFunId(),
//				labQuaAuditRecordVo.getAuditMessage(), labQuaAuditRecordVo.getAuditResult());
//		if (ins != null) {
//			labQuaAuditRecord.setProcessIns(ins);
//			labQuaAuditRecordDAO.updateLabQuaAuditRecord(labQuaAuditRecord);
//		} else {
//			throw new GlobalException("LabQuaManageCheckServiceImpl...流程实例化出错！");
//		}
		return labQuaAuditRecordVo;
	}

	@Override
	public boolean updateLabQuaAuditRecord(LabQuaAuditRecordVo labQuaAuditRecordVo) throws GlobalException {
		LabQuaAuditRecord labQuaAuditRecord = labQuaAuditRecordDAO.getLabQuaAuditRecord(labQuaAuditRecordVo.getId());
		BeanUtils.copyProperties(labQuaAuditRecordVo, labQuaAuditRecord, new String[] {"isDel","createTime","tenantId","createUserId"});
		if(!StrUtils.isBlankOrNull(labQuaAuditRecordVo.getQuaAuditPlanEleId())){
			LabQuaAuditPlanEle labQuaAuditPlanEle = labQuaAuditPlanEleDAO.getLabQuaAuditPlanEle(labQuaAuditRecordVo.getQuaAuditPlanEleId());
			labQuaAuditRecord.setLabQuaAuditPlanEle(labQuaAuditPlanEle);
		}
		if(!StrUtils.isBlankOrNull(labQuaAuditRecordVo.getLabOrgId())){
			LabOrg labOrg = (LabOrg)labQuaAuditRecordDAO.findById(LabOrg.class, labQuaAuditRecordVo.getLabOrgId());
			labQuaAuditRecord.setOrg(labOrg);
		}
		if(StrUtils.isBlankOrNull(labQuaAuditRecordVo.getReportNo())){
			ThreadNumber threadNumber=new ThreadNumber(null,labNumberDAO,Constants_Source.CODE_NB,new String[]{""},Constants_Source.CODE_USE_RUN);
			try {
				labQuaAuditRecord.setReportNo(poolSer.submit(threadNumber).get().toString());
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				//e.printStackTrace();
				throw new GlobalException("" + e.getMessage());
			}
		}
		labQuaAuditRecordDAO.updateLabQuaAuditRecord(labQuaAuditRecord);
		List<LabQuaAuditRecordDetailVo> labQuaAuditRecordDetailVoList = labQuaAuditRecordVo.getLabQuaAuditRecordDetailVoList();
			if (null != labQuaAuditRecordDetailVoList && labQuaAuditRecordDetailVoList.size() > 0) {
				for (LabQuaAuditRecordDetailVo labQuaAuditRecordDetailVo : labQuaAuditRecordDetailVoList) {
					if (null == labQuaAuditRecordDetailVo)
						continue;
					LabQuaAuditRecordDetail labQuaAuditRecordDetail = labQuaAuditRecordDetailDAO.getLabQuaAuditRecordDetail(labQuaAuditRecordDetailVo.getId());
					BeanUtils.copyProperties(labQuaAuditRecordDetailVo, labQuaAuditRecordDetail,new String[]{""});
					labQuaAuditRecordDetail.setLabQuaAuditRecord(labQuaAuditRecord);
					if(!StrUtils.isBlankOrNull(labQuaAuditRecordDetailVo.getInitAuditPlanId())){
						LabQuaInitAuditPlan labQuaInitAuditPlan = labQuaInitAuditPlanDAO.getLabQuaInitAuditPlan(labQuaAuditRecordDetailVo.getInitAuditPlanId());
						labQuaAuditRecordDetail.setLabQuaInitAuditPlan(labQuaInitAuditPlan);
					}
					if(!StrUtils.isBlankOrNull(labQuaAuditRecordDetailVo.getInitAuditPlanContentId())){
						LabQuaInitAuditPlan labQuaInitAuditPlanContent = labQuaInitAuditPlanDAO.getLabQuaInitAuditPlan(labQuaAuditRecordDetailVo.getInitAuditPlanContentId());
						labQuaAuditRecordDetail.setLabQuaInitAuditPlanContent(labQuaInitAuditPlanContent);
					}
					if(!StrUtils.isBlankOrNull(labQuaAuditRecordDetailVo.getInitAuditPlanKeyId())){
						LabQuaInitAuditPlan labQuaInitAuditPlanKey = labQuaInitAuditPlanDAO.getLabQuaInitAuditPlan(labQuaAuditRecordDetailVo.getInitAuditPlanKeyId());
						labQuaAuditRecordDetail.setLabQuaInitAuditPlanKey(labQuaInitAuditPlanKey);
					}
					labQuaAuditRecordDetailDAO.updateLabQuaAuditRecordDetail(labQuaAuditRecordDetail);
				}
			}
//			// 流程实例
//			LabWfProcessIns ins = labWfProcessInsDAO.addLabWfStepIns(
//					labQuaAuditRecord.getId(), Constants.WF_CODE_QUA_AUDIT, getSessionContainer()
//							.getFunId(), labQuaAuditRecordVo.getAuditMessage(),
//							labQuaAuditRecordVo.getAuditResult());
//			if (ins == null) {
//				throw new GlobalException("LabQuaManageCheckServiceImpl...流程实例化出错！");
//			}
		return true;
	}


	@Override
	public List<LabQuaAuditRecordDetailVo> getLabQuaAuditRecordDetailList(String quaAuditRecordId, String quaAuditPlanEleId, String month, String address)throws GlobalException{
		String hql = "FROM LabQuaAuditPlanEleDetail WHERE 1=1  AND quaAuditPlanEle.id='" + quaAuditPlanEleId + "'";
		List<LabQuaAuditPlanEleDetail> labQuaAuditPlanEleDetailList = labQuaAuditPlanEleDetailDAO.getLabQuaAuditPlanEleDetailByHQL(hql);
		List<LabQuaAuditRecordDetailVo> labQuaAuditRecordDetailVoVoList = new ArrayList<LabQuaAuditRecordDetailVo>();
		if (null != labQuaAuditPlanEleDetailList && labQuaAuditPlanEleDetailList.size() > 0) {
			for (LabQuaAuditPlanEleDetail labQuaAuditPlanEleDetail : labQuaAuditPlanEleDetailList) {
				String months = labQuaAuditPlanEleDetail.getMonth();
				LabQuaInitAuditPlan labQuaInitAuditPlan = new LabQuaInitAuditPlan();
				LabQuaAuditRecordDetailVo labQuaAuditRecordDetailVo = new LabQuaAuditRecordDetailVo();
				// 得到要素
				if (null != months) {
					String[] str = months.split(", ");
					for (String ss : str) {
						if (ss.equals(month)) {
							labQuaInitAuditPlan = labQuaAuditPlanEleDetail.getQuaInitAuditPlan();
						}
					}
				}
				if (null != labQuaInitAuditPlan) {
					labQuaAuditRecordDetailVo.setInitAuditPlanId(labQuaInitAuditPlan.getId());
					labQuaAuditRecordDetailVo.setInitAuditPlanName(labQuaInitAuditPlan.getName());
					// 得到检查内容
					String hql2 = "FROM LabQuaInitAuditPlan  WHERE isDel = '"+Constants_Source.N+"' AND quaInitAuditPlan.id='" + labQuaInitAuditPlan.getId() + "'";
					List<LabQuaInitAuditPlan> labQuaInitAuditPlanContentList = labQuaInitAuditPlanDAO.getLabQuaInitAuditPlanByHQL(hql2);
					List<LabQuaAuditRecordDetailVo> contentVoList = new ArrayList<LabQuaAuditRecordDetailVo>();
					if (null != labQuaInitAuditPlanContentList && labQuaInitAuditPlanContentList.size() > 0) {
						for (LabQuaInitAuditPlan labQuaInitAuditPlanContent : labQuaInitAuditPlanContentList) {
							LabQuaAuditRecordDetailVo ContentVo = new LabQuaAuditRecordDetailVo();
							ContentVo.setInitAuditPlanContentId(labQuaInitAuditPlanContent.getId());
							ContentVo.setInitAuditPlanContentName(labQuaInitAuditPlanContent.getName());
							// 得到检查重点
							String hql3 = "FROM LabQuaInitAuditPlan WHERE isDel = '"+Constants_Source.N+"' AND quaInitAuditPlan.id='" + labQuaInitAuditPlanContent.getId() + "'";
							List<LabQuaInitAuditPlan> labQuaInitAuditPlanKeyList = labQuaInitAuditPlanDAO.getLabQuaInitAuditPlanByHQL(hql3);
							List<LabQuaAuditRecordDetailVo> KeyVoList = new ArrayList<LabQuaAuditRecordDetailVo>();
							if (null != labQuaInitAuditPlanKeyList && labQuaInitAuditPlanKeyList.size() > 0) {
								for (LabQuaInitAuditPlan labQuaInitAuditPlanKey : labQuaInitAuditPlanKeyList) {
									LabQuaAuditRecordDetailVo keyVo = new LabQuaAuditRecordDetailVo();
									keyVo.setInitAuditPlanKeyId(labQuaInitAuditPlanKey.getId());
									keyVo.setInitAuditPlanKeyName(labQuaInitAuditPlanKey.getName());
									String hql4 = "FROM LabQuaAuditRecordDetail WHERE 1=1 AND labQuaAuditRecord.id='"+ quaAuditRecordId + "' AND labQuaInitAuditPlanKey.id='" + labQuaInitAuditPlanKey.getId() + "' ";
									List<LabQuaAuditRecordDetail> labQuaAuditRecordDetailList = labQuaAuditRecordDetailDAO.getLabQuaAuditRecordDetailByHQL(hql4);
									if (null != labQuaAuditRecordDetailList && labQuaAuditRecordDetailList.size() > 0) {
										for (LabQuaAuditRecordDetail labQuaAuditRecordDetail : labQuaAuditRecordDetailList) {
											keyVo.setId(labQuaAuditRecordDetail.getId());
											keyVo.setMeetStatus(labQuaAuditRecordDetail.getMeetStatus());
											keyVo.setProblem(labQuaAuditRecordDetail.getProblem());
											keyVo.setTrackPeople(labQuaAuditRecordDetail.getTrackPeople());
											keyVo.setRecTime(labQuaAuditRecordDetail.getRecTime());
										}
									}
									if (!StrUtils.isBlankOrNull(address)&&!StrUtils.isBlankOrNull(keyVo.getMeetStatus())) {
										if (address.contains(keyVo.getMeetStatus())) {
											KeyVoList.add(keyVo);
										}
									} else if (StrUtils.isBlankOrNull(address)) {
										KeyVoList.add(keyVo);
									}
								}
								ContentVo.setLabQuaAuditRecordDetailVoList(KeyVoList);
								contentVoList.add(ContentVo);
							}
						}
						labQuaAuditRecordDetailVo.setLabQuaAuditRecordDetailVoList(contentVoList);

					}
				}
				labQuaAuditRecordDetailVoVoList.add(labQuaAuditRecordDetailVo);
			}
		}
		return labQuaAuditRecordDetailVoVoList;
	}
	
	@Override
	public boolean updateLabQuaAuditRecord4Report(LabQuaAuditRecordVo labQuaAuditRecordVo) throws GlobalException {
		LabQuaAuditRecord labQuaAuditRecord=new LabQuaAuditRecord();
		boolean key=true;
		try{
			labQuaAuditRecord=labQuaAuditRecordDAO.getLabQuaAuditRecord(labQuaAuditRecordVo.getId());
			labQuaAuditRecord.setReportTempId(labQuaAuditRecordVo.getReportTempId());
			String path=labQuaAuditRecordVo.getReportPath();
			path=path.replace("\\", "/");
			labQuaAuditRecord.setReportPath(path);
			labQuaAuditRecordDAO.updateLabQuaAuditRecord(labQuaAuditRecord);
		}catch(Exception e){
			key=false;
			Log4J.error("LabQuaAuditRecordServiceImpl  error..."+e.getMessage(), e);
			throw new GlobalException("" + e.getMessage());
		}
		return key;
	}
	@Override
	public List<LabCodeVo> getLabCodeByPlanEle(String code,String planId)
	throws GlobalException {
	List<LabCodeVo> voList = new ArrayList<LabCodeVo>();
	try {
		String hql2 = "FROM LabCode po WHERE po.isDel='"+Constants_Source.N+"' and po.labType.code='" + code.trim()
				+ "' ORDER BY po.sort";
		List<LabCode> poList = labCodeDAO.find(hql2);
		if (poList.size() > 0) {
			for (LabCode po : poList) {
				LabCodeVo vo = new LabCodeVo();
				BeanUtils.copyProperties(po, vo, new String[] {});
				String sql = "FROM LabQuaAuditRecord WHERE month = '"+po.getCode()+"' AND labQuaAuditPlanEle.id = '"+planId+"'";
				List<LabQuaAuditRecord> labQuaAuditRecordList = labQuaAuditRecordDAO.find(sql);
				String hql = "FROM LabQuaAuditPlanEleDetail WHERE quaAuditPlanEle.id= '"+planId+"' AND month LIKE '%"+po.getCode()+"%'";
				List<LabQuaAuditPlanEleDetail> labQuaAuditPlanEleDetailList = labQuaAuditPlanEleDetailDAO.find(hql);
				if(labQuaAuditRecordList.size() == 0 && labQuaAuditPlanEleDetailList.size() > 0 ){
					voList.add(vo);
				}
			}
		}
	} catch (Exception e) {
		Log4J.error("LabQuaAuditRecordServiceImpl  error..." + e.getMessage());
		throw new GlobalException("" + e.getMessage());
	}
	return voList;
	}
}
